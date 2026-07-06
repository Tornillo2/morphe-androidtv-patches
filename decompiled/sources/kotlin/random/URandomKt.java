package kotlin.random;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nURandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URandom.kt\nkotlin/random/URandomKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,155:1\n1#2:156\n*E\n"})
public final class URandomKt {
    /* JADX INFO: renamed from: checkUIntRangeBounds-J1ME1BU, reason: not valid java name */
    public static final void m1802checkUIntRangeBoundsJ1ME1BU(int i, int i2) {
        if (Integer.compare(i2 ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE) <= 0) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(new UInt(i), new UInt(i2)).toString());
        }
    }

    /* JADX INFO: renamed from: checkULongRangeBounds-eb3DHEI, reason: not valid java name */
    public static final void m1803checkULongRangeBoundseb3DHEI(long j, long j2) {
        if (Long.compare(j2 ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE) <= 0) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(new ULong(j), new ULong(j2)).toString());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes(@NotNull Random random, int i) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        byte[] storage = random.nextBytes(i);
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: nextUBytes-EVgfTAA, reason: not valid java name */
    public static final byte[] m1804nextUBytesEVgfTAA(@NotNull Random nextUBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: nextUBytes-Wvrt4B4, reason: not valid java name */
    public static final byte[] m1805nextUBytesWvrt4B4(@NotNull Random nextUBytes, @NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array, i, i2);
        return array;
    }

    /* JADX INFO: renamed from: nextUBytes-Wvrt4B4$default, reason: not valid java name */
    public static byte[] m1806nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        m1805nextUBytesWvrt4B4(random, bArr, i, i2);
        return bArr;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int nextUInt(@NotNull Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return random.nextInt();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: nextUInt-a8DCA5k, reason: not valid java name */
    public static final int m1807nextUInta8DCA5k(@NotNull Random nextUInt, int i, int i2) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        m1802checkUIntRangeBoundsJ1ME1BU(i, i2);
        return nextUInt.nextInt(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: nextUInt-qCasIEU, reason: not valid java name */
    public static final int m1808nextUIntqCasIEU(@NotNull Random nextUInt, int i) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        return m1807nextUInta8DCA5k(nextUInt, 0, i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long nextULong(@NotNull Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return random.nextLong();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: nextULong-V1Xi4fY, reason: not valid java name */
    public static final long m1809nextULongV1Xi4fY(@NotNull Random nextULong, long j) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        return m1810nextULongjmpaWc(nextULong, 0L, j);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* JADX INFO: renamed from: nextULong-jmpaW-c, reason: not valid java name */
    public static final long m1810nextULongjmpaWc(@NotNull Random nextULong, long j, long j2) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        m1803checkULongRangeBoundseb3DHEI(j, j2);
        return nextULong.nextLong(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int nextUInt(@NotNull Random random, @NotNull UIntRange range) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (!range.isEmpty()) {
            return Integer.compare(range.last ^ Integer.MIN_VALUE, (-1) ^ Integer.MIN_VALUE) < 0 ? m1807nextUInta8DCA5k(random, range.first, range.last + 1) : Integer.compare(range.first ^ Integer.MIN_VALUE, 0 ^ Integer.MIN_VALUE) > 0 ? m1807nextUInta8DCA5k(random, range.first - 1, range.last) + 1 : random.nextInt();
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + range);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long nextULong(@NotNull Random random, @NotNull ULongRange range) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        if (Long.compare(range.last ^ Long.MIN_VALUE, (-1) ^ Long.MIN_VALUE) < 0) {
            return m1810nextULongjmpaWc(random, range.first, range.last + (4294967295L & ((long) 1)));
        }
        if (Long.compare(range.first ^ Long.MIN_VALUE, 0 ^ Long.MIN_VALUE) <= 0) {
            return random.nextLong();
        }
        long j = 4294967295L & ((long) 1);
        return m1810nextULongjmpaWc(random, range.first - j, range.last) + j;
    }
}
