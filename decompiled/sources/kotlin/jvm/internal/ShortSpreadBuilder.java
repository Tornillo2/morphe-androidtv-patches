package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ShortSpreadBuilder extends PrimitiveSpreadBuilder<short[]> {

    @NotNull
    public final short[] values;

    public ShortSpreadBuilder(int i) {
        super(i);
        this.values = new short[i];
    }

    public final void add(short s) {
        short[] sArr = this.values;
        int i = this.position;
        this.position = i + 1;
        sArr[i] = s;
    }

    @NotNull
    public final short[] toArray() {
        short[] sArr = this.values;
        short[] sArr2 = new short[size()];
        toArray(sArr, sArr2);
        return sArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length;
    }
}
