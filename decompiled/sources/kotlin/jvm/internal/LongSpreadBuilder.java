package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LongSpreadBuilder extends PrimitiveSpreadBuilder<long[]> {

    @NotNull
    public final long[] values;

    public LongSpreadBuilder(int i) {
        super(i);
        this.values = new long[i];
    }

    public final void add(long j) {
        long[] jArr = this.values;
        int i = this.position;
        this.position = i + 1;
        jArr[i] = j;
    }

    @NotNull
    public final long[] toArray() {
        long[] jArr = this.values;
        long[] jArr2 = new long[size()];
        toArray(jArr, jArr2);
        return jArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length;
    }
}
