package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.MetadataItem;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MetadataList extends Table {
    public static int access$000(int i, ByteBuffer byteBuffer) {
        return byteBuffer.getInt(i) + i;
    }

    public static void addList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addSourceSha(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int createListVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createMetadataList(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        flatBufferBuilder.addOffset(2, i3, 0);
        flatBufferBuilder.addOffset(1, i2, 0);
        flatBufferBuilder.addInt(0, i, 0);
        return flatBufferBuilder.endTable();
    }

    public static int endMetadataList(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static void finishMetadataListBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finish(i);
    }

    public static void finishSizePrefixedMetadataListBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finishSizePrefixed(i);
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer) {
        MetadataList metadataList = new MetadataList();
        getRootAsMetadataList(byteBuffer, metadataList);
        return metadataList;
    }

    public static void startListVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startMetadataList(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public MetadataList __assign(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
        return this;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MetadataItem list(int i) {
        return list(new MetadataItem(), i);
    }

    public int listLength() {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public MetadataItem.Vector listVector() {
        return listVector(new MetadataItem.Vector());
    }

    public String sourceSha() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer sourceShaAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public ByteBuffer sourceShaInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public int version() {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return this.bb.getInt(i__offset + this.bb_pos);
        }
        return 0;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MetadataList get(int i) {
            MetadataList metadataList = new MetadataList();
            get(metadataList, i);
            return metadataList;
        }

        public MetadataList get(MetadataList metadataList, int i) {
            int i__element = __element(i);
            metadataList.__reset(this.bb.getInt(i__element) + i__element, this.bb);
            return metadataList;
        }
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer, MetadataList metadataList) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        metadataList.__reset(byteBuffer.position() + byteBuffer.getInt(byteBuffer.position()), byteBuffer);
        return metadataList;
    }

    public MetadataItem list(MetadataItem metadataItem, int i) {
        int i__offset = __offset(6);
        if (i__offset == 0) {
            return null;
        }
        metadataItem.__reset(__indirect((i * 4) + __vector(i__offset)), this.bb);
        return metadataItem;
    }

    public MetadataItem.Vector listVector(MetadataItem.Vector vector) {
        int i__offset = __offset(6);
        if (i__offset == 0) {
            return null;
        }
        vector.__reset(__vector(i__offset), 4, this.bb);
        return vector;
    }

    public static void ValidateVersion() {
    }
}
