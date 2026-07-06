package androidx.emoji2.text;

import android.content.res.AssetManager;
import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@AnyThread
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class MetadataListReader {
    public static final int EMJI_TAG = 1164798569;
    public static final int EMJI_TAG_DEPRECATED = 1701669481;
    public static final int META_TABLE_NAME = 1835365473;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ByteBufferReader implements OpenTypeReader {

        @NonNull
        public final ByteBuffer mByteBuffer;

        public ByteBufferReader(@NonNull ByteBuffer byteBuffer) {
            this.mByteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long getPosition() {
            return this.mByteBuffer.position();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readTag() throws IOException {
            return this.mByteBuffer.getInt();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long readUnsignedInt() throws IOException {
            return ((long) this.mByteBuffer.getInt()) & 4294967295L;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readUnsignedShort() throws IOException {
            return this.mByteBuffer.getShort() & UShort.MAX_VALUE;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public void skip(int i) throws IOException {
            ByteBuffer byteBuffer = this.mByteBuffer;
            byteBuffer.position(byteBuffer.position() + i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InputStreamOpenTypeReader implements OpenTypeReader {

        @NonNull
        public final byte[] mByteArray;

        @NonNull
        public final ByteBuffer mByteBuffer;

        @NonNull
        public final InputStream mInputStream;
        public long mPosition = 0;

        public InputStreamOpenTypeReader(@NonNull InputStream inputStream) {
            this.mInputStream = inputStream;
            byte[] bArr = new byte[4];
            this.mByteArray = bArr;
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.mByteBuffer = byteBufferWrap;
            byteBufferWrap.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long getPosition() {
            return this.mPosition;
        }

        public final void read(@IntRange(from = 0, to = 4) int i) throws IOException {
            if (this.mInputStream.read(this.mByteArray, 0, i) != i) {
                throw new IOException("read failed");
            }
            this.mPosition += (long) i;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readTag() throws IOException {
            this.mByteBuffer.position(0);
            read(4);
            return this.mByteBuffer.getInt();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long readUnsignedInt() throws IOException {
            this.mByteBuffer.position(0);
            read(4);
            return ((long) this.mByteBuffer.getInt()) & 4294967295L;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readUnsignedShort() throws IOException {
            this.mByteBuffer.position(0);
            read(2);
            return this.mByteBuffer.getShort() & UShort.MAX_VALUE;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public void skip(int i) throws IOException {
            while (i > 0) {
                int iSkip = (int) this.mInputStream.skip(i);
                if (iSkip < 1) {
                    throw new IOException("Skip didn't move at least 1 byte forward");
                }
                i -= iSkip;
                this.mPosition += (long) iSkip;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class OffsetInfo {
        public final long mLength;
        public final long mStartOffset;

        public OffsetInfo(long j, long j2) {
            this.mStartOffset = j;
            this.mLength = j2;
        }

        public long getLength() {
            return this.mLength;
        }

        public long getStartOffset() {
            return this.mStartOffset;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OpenTypeReader {
        public static final int UINT16_BYTE_COUNT = 2;
        public static final int UINT32_BYTE_COUNT = 4;

        long getPosition();

        int readTag() throws IOException;

        long readUnsignedInt() throws IOException;

        int readUnsignedShort() throws IOException;

        void skip(int i) throws IOException;
    }

    public static OffsetInfo findOffsetInfo(OpenTypeReader openTypeReader) throws IOException {
        long unsignedInt;
        openTypeReader.skip(4);
        int unsignedShort = openTypeReader.readUnsignedShort();
        if (unsignedShort > 100) {
            throw new IOException("Cannot read metadata.");
        }
        openTypeReader.skip(6);
        int i = 0;
        while (true) {
            if (i >= unsignedShort) {
                unsignedInt = -1;
                break;
            }
            int tag = openTypeReader.readTag();
            openTypeReader.skip(4);
            unsignedInt = openTypeReader.readUnsignedInt();
            openTypeReader.skip(4);
            if (1835365473 == tag) {
                break;
            }
            i++;
        }
        if (unsignedInt != -1) {
            openTypeReader.skip((int) (unsignedInt - openTypeReader.getPosition()));
            openTypeReader.skip(12);
            long unsignedInt2 = openTypeReader.readUnsignedInt();
            for (int i2 = 0; i2 < unsignedInt2; i2++) {
                int tag2 = openTypeReader.readTag();
                long unsignedInt3 = openTypeReader.readUnsignedInt();
                long unsignedInt4 = openTypeReader.readUnsignedInt();
                if (1164798569 == tag2 || 1701669481 == tag2) {
                    return new OffsetInfo(unsignedInt3 + unsignedInt, unsignedInt4);
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public static MetadataList read(InputStream inputStream) throws IOException {
        InputStreamOpenTypeReader inputStreamOpenTypeReader = new InputStreamOpenTypeReader(inputStream);
        OffsetInfo offsetInfoFindOffsetInfo = findOffsetInfo(inputStreamOpenTypeReader);
        inputStreamOpenTypeReader.skip((int) (offsetInfoFindOffsetInfo.mStartOffset - inputStreamOpenTypeReader.mPosition));
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) offsetInfoFindOffsetInfo.mLength);
        int i = inputStream.read(byteBufferAllocate.array());
        if (i == offsetInfoFindOffsetInfo.mLength) {
            return MetadataList.getRootAsMetadataList(byteBufferAllocate);
        }
        throw new IOException("Needed " + offsetInfoFindOffsetInfo.mLength + " bytes, got " + i);
    }

    public static long toUnsignedInt(int i) {
        return ((long) i) & 4294967295L;
    }

    public static int toUnsignedShort(short s) {
        return s & UShort.MAX_VALUE;
    }

    public static MetadataList read(ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position((int) findOffsetInfo(new ByteBufferReader(byteBufferDuplicate)).mStartOffset);
        return MetadataList.getRootAsMetadataList(byteBufferDuplicate);
    }

    public static MetadataList read(AssetManager assetManager, String str) throws IOException {
        InputStream inputStreamOpen = assetManager.open(str);
        try {
            MetadataList metadataList = read(inputStreamOpen);
            inputStreamOpen.close();
            return metadataList;
        } catch (Throwable th) {
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
