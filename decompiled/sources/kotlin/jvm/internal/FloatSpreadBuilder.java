package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FloatSpreadBuilder extends PrimitiveSpreadBuilder<float[]> {

    @NotNull
    public final float[] values;

    public FloatSpreadBuilder(int i) {
        super(i);
        this.values = new float[i];
    }

    public final void add(float f) {
        float[] fArr = this.values;
        int i = this.position;
        this.position = i + 1;
        fArr[i] = f;
    }

    @NotNull
    public final float[] toArray() {
        float[] fArr = this.values;
        float[] fArr2 = new float[size()];
        toArray(fArr, fArr2);
        return fArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }
}
