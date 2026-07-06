package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IntVector extends BaseVector {
    public IntVector __assign(int i, ByteBuffer byteBuffer) {
        __reset(i, 4, byteBuffer);
        return this;
    }

    public int get(int i) {
        return this.bb.getInt(__element(i));
    }

    public long getAsUnsigned(int i) {
        return ((long) get(i)) & 4294967295L;
    }
}
