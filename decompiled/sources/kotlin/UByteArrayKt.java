package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UByteArrayKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final byte[] UByteArray(int i, Function1<? super Integer, UByte> init) {
        Intrinsics.checkNotNullParameter(init, "init");
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = init.invoke(Integer.valueOf(i2)).data;
        }
        return bArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: ubyteArrayOf-GBYM_sE, reason: not valid java name */
    public static final byte[] m667ubyteArrayOfGBYM_sE(byte... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return elements;
    }
}
