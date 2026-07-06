package kotlin.random;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FallbackThreadLocalRandom$implStorage$1 extends ThreadLocal<java.util.Random> {
    @Override // java.lang.ThreadLocal
    public java.util.Random initialValue() {
        return new java.util.Random();
    }

    @Override // java.lang.ThreadLocal
    /* JADX INFO: renamed from: initialValue, reason: avoid collision after fix types in other method */
    public java.util.Random initialValue2() {
        return new java.util.Random();
    }
}
