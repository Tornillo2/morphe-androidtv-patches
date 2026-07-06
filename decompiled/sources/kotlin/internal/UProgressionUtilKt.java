package kotlin.internal;

import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UByte$$ExternalSyntheticBackport1;
import kotlin.UByte$$ExternalSyntheticBackport2;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UProgressionUtilKt {
    /* JADX INFO: renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    public static final int m1792differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM = UByte$$ExternalSyntheticBackport1.m(i, i3);
        int iM2 = UByte$$ExternalSyntheticBackport1.m(i2, i3);
        int iCompare = Integer.compare(iM ^ Integer.MIN_VALUE, iM2 ^ Integer.MIN_VALUE);
        int i4 = iM - iM2;
        return iCompare >= 0 ? i4 : i4 + i3;
    }

    /* JADX INFO: renamed from: differenceModulo-sambcqE, reason: not valid java name */
    public static final long m1793differenceModulosambcqE(long j, long j2, long j3) {
        long jM = UByte$$ExternalSyntheticBackport2.m(j, j3);
        long jM2 = UByte$$ExternalSyntheticBackport2.m(j2, j3);
        int iCompare = Long.compare(jM ^ Long.MIN_VALUE, jM2 ^ Long.MIN_VALUE);
        long j4 = jM - jM2;
        return iCompare >= 0 ? j4 : j4 + j3;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* JADX INFO: renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1794getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) >= 0 ? j2 : j2 - m1793differenceModulosambcqE(j2, j, j3);
        }
        if (j3 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) <= 0 ? j2 : j2 + m1793differenceModulosambcqE(j, j2, -j3);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* JADX INFO: renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1795getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            if (Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) < 0) {
                return i2 - m1792differenceModuloWZ9TVnA(i2, i, i3);
            }
        } else {
            if (i3 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) > 0) {
                return m1792differenceModuloWZ9TVnA(i, i2, -i3) + i2;
            }
        }
        return i2;
    }
}
