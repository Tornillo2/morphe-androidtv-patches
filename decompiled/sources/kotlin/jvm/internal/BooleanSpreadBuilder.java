package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BooleanSpreadBuilder extends PrimitiveSpreadBuilder<boolean[]> {

    @NotNull
    public final boolean[] values;

    public BooleanSpreadBuilder(int i) {
        super(i);
        this.values = new boolean[i];
    }

    public final void add(boolean z) {
        boolean[] zArr = this.values;
        int i = this.position;
        this.position = i + 1;
        zArr[i] = z;
    }

    @NotNull
    public final boolean[] toArray() {
        boolean[] zArr = this.values;
        boolean[] zArr2 = new boolean[size()];
        toArray(zArr, zArr2);
        return zArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length;
    }
}
