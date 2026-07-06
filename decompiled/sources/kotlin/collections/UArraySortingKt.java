package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    public static final int m1039partitionnroSd4(long[] jArr, int i, int i2) {
        long j = jArr[(i + i2) / 2];
        while (i <= i2) {
            while (Long.compare(jArr[i] ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE) < 0) {
                i++;
            }
            while (Long.compare(jArr[i2] ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                long j2 = jArr[i];
                jArr[i] = jArr[i2];
                jArr[i2] = j2;
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    public static final int m1040partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte b = bArr[(i + i2) / 2];
        while (i <= i2) {
            while (true) {
                i3 = b & 255;
                if (Intrinsics.compare(bArr[i] & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(bArr[i2] & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte b2 = bArr[i];
                bArr[i] = bArr[i2];
                bArr[i2] = b2;
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    public static final int m1041partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short s = sArr[(i + i2) / 2];
        while (i <= i2) {
            while (true) {
                int i4 = sArr[i] & UShort.MAX_VALUE;
                i3 = s & UShort.MAX_VALUE;
                if (Intrinsics.compare(i4, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(sArr[i2] & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short s2 = sArr[i];
                sArr[i] = sArr[i2];
                sArr[i2] = s2;
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    public static final int m1042partitionoBK06Vg(int[] iArr, int i, int i2) {
        int i3 = iArr[(i + i2) / 2];
        while (i <= i2) {
            while (Integer.compare(iArr[i] ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) < 0) {
                i++;
            }
            while (Integer.compare(iArr[i2] ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                int i4 = iArr[i];
                iArr[i] = iArr[i2];
                iArr[i2] = i4;
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    public static final void m1043quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM1039partitionnroSd4 = m1039partitionnroSd4(jArr, i, i2);
        int i3 = iM1039partitionnroSd4 - 1;
        if (i < i3) {
            m1043quickSortnroSd4(jArr, i, i3);
        }
        if (iM1039partitionnroSd4 < i2) {
            m1043quickSortnroSd4(jArr, iM1039partitionnroSd4, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    public static final void m1044quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM1040partition4UcCI2c = m1040partition4UcCI2c(bArr, i, i2);
        int i3 = iM1040partition4UcCI2c - 1;
        if (i < i3) {
            m1044quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM1040partition4UcCI2c < i2) {
            m1044quickSort4UcCI2c(bArr, iM1040partition4UcCI2c, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    public static final void m1045quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM1041partitionAa5vz7o = m1041partitionAa5vz7o(sArr, i, i2);
        int i3 = iM1041partitionAa5vz7o - 1;
        if (i < i3) {
            m1045quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM1041partitionAa5vz7o < i2) {
            m1045quickSortAa5vz7o(sArr, iM1041partitionAa5vz7o, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    public static final void m1046quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM1042partitionoBK06Vg = m1042partitionoBK06Vg(iArr, i, i2);
        int i3 = iM1042partitionoBK06Vg - 1;
        if (i < i3) {
            m1046quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM1042partitionoBK06Vg < i2) {
            m1046quickSortoBK06Vg(iArr, iM1042partitionoBK06Vg, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m1047sortArraynroSd4(@NotNull long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1043quickSortnroSd4(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m1048sortArray4UcCI2c(@NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1044quickSort4UcCI2c(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m1049sortArrayAa5vz7o(@NotNull short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1045quickSortAa5vz7o(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m1050sortArrayoBK06Vg(@NotNull int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1046quickSortoBK06Vg(array, i, i2 - 1);
    }
}
