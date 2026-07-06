package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DoubleSpreadBuilder extends PrimitiveSpreadBuilder<double[]> {

    @NotNull
    public final double[] values;

    public DoubleSpreadBuilder(int i) {
        super(i);
        this.values = new double[i];
    }

    public final void add(double d) {
        double[] dArr = this.values;
        int i = this.position;
        this.position = i + 1;
        dArr[i] = d;
    }

    @NotNull
    public final double[] toArray() {
        double[] dArr = this.values;
        double[] dArr2 = new double[size()];
        toArray(dArr, dArr2);
        return dArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length;
    }
}
