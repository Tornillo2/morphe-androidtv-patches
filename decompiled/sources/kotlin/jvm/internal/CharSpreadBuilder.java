package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CharSpreadBuilder extends PrimitiveSpreadBuilder<char[]> {

    @NotNull
    public final char[] values;

    public CharSpreadBuilder(int i) {
        super(i);
        this.values = new char[i];
    }

    public final void add(char c) {
        char[] cArr = this.values;
        int i = this.position;
        this.position = i + 1;
        cArr[i] = c;
    }

    @NotNull
    public final char[] toArray() {
        char[] cArr = this.values;
        char[] cArr2 = new char[size()];
        toArray(cArr, cArr2);
        return cArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length;
    }
}
