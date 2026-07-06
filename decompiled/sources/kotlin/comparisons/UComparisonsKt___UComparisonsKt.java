package kotlin.comparisons;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class UComparisonsKt___UComparisonsKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: maxOf-5PvTz6A, reason: not valid java name */
    public static final short m1766maxOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) >= 0 ? s : s2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: maxOf-J1ME1BU, reason: not valid java name */
    public static int m1767maxOfJ1ME1BU(int i, int i2) {
        return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0 ? i : i2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: maxOf-Kr8caGY, reason: not valid java name */
    public static final byte m1768maxOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) >= 0 ? b : b2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: maxOf-Md2H83M, reason: not valid java name */
    public static final int m1769maxOfMd2H83M(int i, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i2 : other) {
            i = m1767maxOfJ1ME1BU(i, i2);
        }
        return i;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: maxOf-R03FKyM, reason: not valid java name */
    public static final long m1770maxOfR03FKyM(long j, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j2 : other) {
            j = m1775maxOfeb3DHEI(j, j2);
        }
        return j;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: maxOf-VKSA0NQ, reason: not valid java name */
    public static final short m1771maxOfVKSA0NQ(short s, short s2, short s3) {
        return m1766maxOf5PvTz6A(s, m1766maxOf5PvTz6A(s2, s3));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: maxOf-WZ9TVnA, reason: not valid java name */
    public static final int m1772maxOfWZ9TVnA(int i, int i2, int i3) {
        return m1767maxOfJ1ME1BU(i, m1767maxOfJ1ME1BU(i2, i3));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: maxOf-Wr6uiD8, reason: not valid java name */
    public static final byte m1773maxOfWr6uiD8(byte b, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b2 : other) {
            b = m1768maxOfKr8caGY(b, b2);
        }
        return b;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: maxOf-b33U2AM, reason: not valid java name */
    public static final byte m1774maxOfb33U2AM(byte b, byte b2, byte b3) {
        return m1768maxOfKr8caGY(b, m1768maxOfKr8caGY(b2, b3));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: maxOf-eb3DHEI, reason: not valid java name */
    public static long m1775maxOfeb3DHEI(long j, long j2) {
        return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) >= 0 ? j : j2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: maxOf-sambcqE, reason: not valid java name */
    public static final long m1776maxOfsambcqE(long j, long j2, long j3) {
        return m1775maxOfeb3DHEI(j, m1775maxOfeb3DHEI(j2, j3));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: maxOf-t1qELG4, reason: not valid java name */
    public static final short m1777maxOft1qELG4(short s, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s2 : other) {
            s = m1766maxOf5PvTz6A(s, s2);
        }
        return s;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: minOf-5PvTz6A, reason: not valid java name */
    public static final short m1778minOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) <= 0 ? s : s2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: minOf-J1ME1BU, reason: not valid java name */
    public static int m1779minOfJ1ME1BU(int i, int i2) {
        return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i : i2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: minOf-Kr8caGY, reason: not valid java name */
    public static final byte m1780minOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) <= 0 ? b : b2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: minOf-Md2H83M, reason: not valid java name */
    public static final int m1781minOfMd2H83M(int i, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i2 : other) {
            i = m1779minOfJ1ME1BU(i, i2);
        }
        return i;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: minOf-R03FKyM, reason: not valid java name */
    public static final long m1782minOfR03FKyM(long j, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j2 : other) {
            j = m1787minOfeb3DHEI(j, j2);
        }
        return j;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: minOf-VKSA0NQ, reason: not valid java name */
    public static final short m1783minOfVKSA0NQ(short s, short s2, short s3) {
        return m1778minOf5PvTz6A(s, m1778minOf5PvTz6A(s2, s3));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: minOf-WZ9TVnA, reason: not valid java name */
    public static final int m1784minOfWZ9TVnA(int i, int i2, int i3) {
        return m1779minOfJ1ME1BU(i, m1779minOfJ1ME1BU(i2, i3));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: minOf-Wr6uiD8, reason: not valid java name */
    public static final byte m1785minOfWr6uiD8(byte b, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b2 : other) {
            b = m1780minOfKr8caGY(b, b2);
        }
        return b;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: minOf-b33U2AM, reason: not valid java name */
    public static final byte m1786minOfb33U2AM(byte b, byte b2, byte b3) {
        return m1780minOfKr8caGY(b, m1780minOfKr8caGY(b2, b3));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: minOf-eb3DHEI, reason: not valid java name */
    public static long m1787minOfeb3DHEI(long j, long j2) {
        return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) <= 0 ? j : j2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: minOf-sambcqE, reason: not valid java name */
    public static final long m1788minOfsambcqE(long j, long j2, long j3) {
        return m1787minOfeb3DHEI(j, m1787minOfeb3DHEI(j2, j3));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: minOf-t1qELG4, reason: not valid java name */
    public static final short m1789minOft1qELG4(short s, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s2 : other) {
            s = m1778minOf5PvTz6A(s, s2);
        }
        return s;
    }
}
