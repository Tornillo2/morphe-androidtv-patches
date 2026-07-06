package androidx.media3.extractor.avi;

import androidx.annotation.Nullable;
import androidx.media3.common.util.ParsableByteArray;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ListChunk implements AviChunk {
    public final ImmutableList<AviChunk> children;
    public final int type;

    public ListChunk(int i, ImmutableList<AviChunk> immutableList) {
        this.type = i;
        this.children = immutableList;
    }

    @Nullable
    public static AviChunk createBox(int i, int i2, ParsableByteArray parsableByteArray) {
        switch (i) {
            case 1718776947:
                return StreamFormatChunk.parseFrom(i2, parsableByteArray);
            case 1751742049:
                return AviMainHeaderChunk.parseFrom(parsableByteArray);
            case 1752331379:
                return AviStreamHeaderChunk.parseFrom(parsableByteArray);
            case 1852994675:
                return StreamNameChunk.parseFrom(parsableByteArray);
            default:
                return null;
        }
    }

    public static ListChunk parseFrom(int i, ParsableByteArray parsableByteArray) {
        ImmutableList.Builder builder = new ImmutableList.Builder(4);
        int i2 = parsableByteArray.limit;
        int trackType = -2;
        while (parsableByteArray.bytesLeft() > 8) {
            int littleEndianInt = parsableByteArray.readLittleEndianInt();
            int littleEndianInt2 = parsableByteArray.position + parsableByteArray.readLittleEndianInt();
            parsableByteArray.setLimit(littleEndianInt2);
            AviChunk from = littleEndianInt == 1414744396 ? parseFrom(parsableByteArray.readLittleEndianInt(), parsableByteArray) : createBox(littleEndianInt, trackType, parsableByteArray);
            if (from != null) {
                if (from.getType() == 1752331379) {
                    trackType = ((AviStreamHeaderChunk) from).getTrackType();
                }
                builder.add(from);
            }
            parsableByteArray.setPosition(littleEndianInt2);
            parsableByteArray.setLimit(i2);
        }
        return new ListChunk(i, builder.build());
    }

    @Nullable
    public <T extends AviChunk> T getChild(Class<T> cls) {
        UnmodifiableIterator<AviChunk> it = this.children.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (t.getClass() == cls) {
                return t;
            }
        }
        return null;
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return this.type;
    }
}
