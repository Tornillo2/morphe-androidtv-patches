package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "UnsignedKt")
public final class UnsignedKt {
    @PublishedApi
    public static final int doubleToUInt(double d) {
        if (Double.isNaN(d) || d <= uintToDouble(0)) {
            return 0;
        }
        if (d >= uintToDouble(-1)) {
            return -1;
        }
        return d <= 2.147483647E9d ? (int) d : ((int) (d - ((double) Integer.MAX_VALUE))) + Integer.MAX_VALUE;
    }

    @PublishedApi
    public static final long doubleToULong(double d) {
        if (Double.isNaN(d) || d <= ulongToDouble(0L)) {
            return 0L;
        }
        if (d >= ulongToDouble(-1L)) {
            return -1L;
        }
        return d < 9.223372036854776E18d ? (long) d : ((long) (d - 9.223372036854776E18d)) - Long.MIN_VALUE;
    }

    @PublishedApi
    @InlineOnly
    public static final int floatToUInt(float f) {
        return doubleToUInt(f);
    }

    @PublishedApi
    @InlineOnly
    public static final long floatToULong(float f) {
        return doubleToULong(f);
    }

    @PublishedApi
    public static final int uintCompare(int i, int i2) {
        return Intrinsics.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    /* JADX INFO: renamed from: uintDivide-J1ME1BU, reason: not valid java name */
    public static final int m931uintDivideJ1ME1BU(int i, int i2) {
        return (int) ((((long) i) & 4294967295L) / (((long) i2) & 4294967295L));
    }

    @PublishedApi
    /* JADX INFO: renamed from: uintRemainder-J1ME1BU, reason: not valid java name */
    public static final int m932uintRemainderJ1ME1BU(int i, int i2) {
        return (int) ((((long) i) & 4294967295L) % (((long) i2) & 4294967295L));
    }

    @PublishedApi
    public static final double uintToDouble(int i) {
        return (((double) ((i >>> 31) << 30)) * ((double) 2)) + ((double) (Integer.MAX_VALUE & i));
    }

    @PublishedApi
    @InlineOnly
    public static final float uintToFloat(int i) {
        return (float) uintToDouble(i);
    }

    @PublishedApi
    @InlineOnly
    public static final long uintToLong(int i) {
        return ((long) i) & 4294967295L;
    }

    @InlineOnly
    public static final String uintToString(int i) {
        return String.valueOf(((long) i) & 4294967295L);
    }

    @PublishedApi
    @InlineOnly
    public static final long uintToULong(int i) {
        return ((long) i) & 4294967295L;
    }

    @PublishedApi
    public static final int ulongCompare(long j, long j2) {
        return Intrinsics.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
    }

    @PublishedApi
    /* JADX INFO: renamed from: ulongDivide-eb3DHEI, reason: not valid java name */
    public static final long m933ulongDivideeb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) < 0 ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + ((long) (Long.compare((j - (j3 * j2)) ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) < 0 ? 0 : 1));
    }

    @PublishedApi
    /* JADX INFO: renamed from: ulongRemainder-eb3DHEI, reason: not valid java name */
    public static final long m934ulongRemaindereb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) < 0 ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (Long.compare(j3 ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) < 0) {
            j2 = 0;
        }
        return j3 - j2;
    }

    @PublishedApi
    public static final double ulongToDouble(long j) {
        return ((j >>> 11) * ((double) 2048)) + (j & 2047);
    }

    @PublishedApi
    @InlineOnly
    public static final float ulongToFloat(long j) {
        return (float) ulongToDouble(j);
    }

    @InlineOnly
    public static final String ulongToString(long j) {
        return ulongToString(j, 10);
    }

    @InlineOnly
    public static final String uintToString(int i, int i2) {
        return ulongToString(((long) i) & 4294967295L, i2);
    }

    @NotNull
    public static final String ulongToString(long j, int i) {
        if (j >= 0) {
            CharsKt__CharJVMKt.checkRadix(i);
            String string = Long.toString(j, i);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
        long j2 = i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        CharsKt__CharJVMKt.checkRadix(i);
        String string2 = Long.toString(j3, i);
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        CharsKt__CharJVMKt.checkRadix(i);
        String string3 = Long.toString(j4, i);
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        return string2.concat(string3);
    }
}
