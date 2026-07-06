package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ByteSpreadBuilder extends PrimitiveSpreadBuilder<byte[]> {

    @NotNull
    public final byte[] values;

    public ByteSpreadBuilder(int i) {
        super(i);
        this.values = new byte[i];
    }

    public final void add(byte b) {
        byte[] bArr = this.values;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    @NotNull
    public final byte[] toArray() {
        byte[] bArr = this.values;
        byte[] bArr2 = new byte[size()];
        toArray(bArr, bArr2);
        return bArr2;
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length;
    }
}
