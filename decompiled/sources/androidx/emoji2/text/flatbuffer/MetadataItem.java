package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MetadataItem extends Table {
    public static int access$000(int i, ByteBuffer byteBuffer) {
        return byteBuffer.getInt(i) + i;
    }

    public static void addCodepoints(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addCompatAdded(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(3, s, 0);
    }

    public static void addEmojiStyle(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(5, s, 0);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addSdkAdded(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(2, s, 0);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(4, s, 0);
    }

    public static int createCodepointsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createMetadataItem(FlatBufferBuilder flatBufferBuilder, int i, boolean z, short s, short s2, short s3, short s4, int i2) {
        flatBufferBuilder.startTable(7);
        flatBufferBuilder.addOffset(6, i2, 0);
        flatBufferBuilder.addInt(0, i, 0);
        flatBufferBuilder.addShort(5, s4, 0);
        flatBufferBuilder.addShort(4, s3, 0);
        flatBufferBuilder.addShort(3, s2, 0);
        flatBufferBuilder.addShort(2, s, 0);
        flatBufferBuilder.addBoolean(1, z, false);
        return flatBufferBuilder.endTable();
    }

    public static int endMetadataItem(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer) {
        MetadataItem metadataItem = new MetadataItem();
        getRootAsMetadataItem(byteBuffer, metadataItem);
        return metadataItem;
    }

    public static void startCodepointsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startMetadataItem(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
    }

    public MetadataItem __assign(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
        return this;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public int codepoints(int i) {
        int i__offset = __offset(16);
        if (i__offset == 0) {
            return 0;
        }
        return this.bb.getInt((i * 4) + __vector(i__offset));
    }

    public ByteBuffer codepointsAsByteBuffer() {
        return __vector_as_bytebuffer(16, 4);
    }

    public ByteBuffer codepointsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 4);
    }

    public int codepointsLength() {
        int i__offset = __offset(16);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public IntVector codepointsVector() {
        return codepointsVector(new IntVector());
    }

    public short compatAdded() {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public boolean emojiStyle() {
        int i__offset = __offset(6);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public short height() {
        int i__offset = __offset(14);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public int id() {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return this.bb.getInt(i__offset + this.bb_pos);
        }
        return 0;
    }

    public short sdkAdded() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public short width() {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos);
        }
        return (short) 0;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MetadataItem get(int i) {
            MetadataItem metadataItem = new MetadataItem();
            get(metadataItem, i);
            return metadataItem;
        }

        public MetadataItem get(MetadataItem metadataItem, int i) {
            int i__element = __element(i);
            metadataItem.__reset(this.bb.getInt(i__element) + i__element, this.bb);
            return metadataItem;
        }
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer, MetadataItem metadataItem) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        metadataItem.__reset(byteBuffer.position() + byteBuffer.getInt(byteBuffer.position()), byteBuffer);
        return metadataItem;
    }

    public IntVector codepointsVector(IntVector intVector) {
        int i__offset = __offset(16);
        if (i__offset == 0) {
            return null;
        }
        intVector.__reset(__vector(i__offset), 4, this.bb);
        return intVector;
    }

    public static void ValidateVersion() {
    }
}
