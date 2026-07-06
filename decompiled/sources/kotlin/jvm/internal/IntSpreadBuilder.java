package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class IntSpreadBuilder extends PrimitiveSpreadBuilder<int[]> {

    @NotNull
    public final int[] values;

    public IntSpreadBuilder(int i) {
        super(i);
        this.values = new int[i];
    }

    public final void add(int i) {
        int[] iArr = this.values;
        int i2 = this.position;
        this.position = i2 + 1;
        iArr[i2] = i;
    }

    @NotNull
    public final int[] toArray() {
        int[] iArr = this.values;
        int[] iArr2 = new int[size()];
        toArray(iArr, iArr2);
        return iArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length;
    }
}
