package kotlinx.serialization.json;

import kotlin.UByte$$ExternalSyntheticBackport3;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class JsonElementKt$$ExternalSyntheticBackport1 {
    public static /* synthetic */ String m(long j, int i) {
        if (j == 0) {
            return "0";
        }
        if (j > 0) {
            return Long.toString(j, i);
        }
        if (i < 2 || i > 36) {
            i = 10;
        }
        int i2 = 64;
        char[] cArr = new char[64];
        int i3 = i - 1;
        if ((i & i3) == 0) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            do {
                i2--;
                cArr[i2] = Character.forDigit(((int) j) & i3, i);
                j >>>= iNumberOfTrailingZeros;
            } while (j != 0);
        } else {
            long jM = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : UByte$$ExternalSyntheticBackport3.m(j, i);
            long j2 = i;
            cArr[63] = Character.forDigit((int) (j - (jM * j2)), i);
            i2 = 63;
            while (jM > 0) {
                i2--;
                cArr[i2] = Character.forDigit((int) (jM % j2), i);
                jM /= j2;
            }
        }
        return new String(cArr, i2, 64 - i2);
    }
}
