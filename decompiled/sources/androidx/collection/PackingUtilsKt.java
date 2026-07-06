package androidx.collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PackingUtilsKt {
    public static final long packFloats(float f, float f2) {
        return (((long) Float.floatToRawIntBits(f2)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32);
    }

    public static final long packInts(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }
}
