package kotlin.time;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.amazon.ion.impl.bin.WriteBuffer;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.EventLoop_commonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.6")
@JvmInline
@WasExperimental(markerClass = {ExperimentalTime.class})
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1068:1\n38#1:1069\n38#1:1070\n38#1:1071\n38#1:1072\n38#1:1073\n501#1:1074\n518#1:1082\n170#2,6:1075\n1#3:1081\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n*L\n39#1:1069\n40#1:1070\n275#1:1071\n295#1:1072\n479#1:1073\n728#1:1074\n819#1:1082\n770#1:1075,6\n*E\n"})
public final class Duration implements Comparable<Duration> {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long INFINITE;
    public static final long NEG_INFINITE;
    public static final long ZERO;
    public final long rawValue;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1972getDaysUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1975getHoursUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1978getMicrosecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1981getMillisecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1984getMinutesUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1987getNanosecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1990getSecondsUwyO8pc$annotations(double d) {
        }

        @ExperimentalTime
        public final double convert(double d, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, sourceUnit, targetUnit);
        }

        /* JADX INFO: renamed from: getDays-UwyO8pc, reason: not valid java name */
        public final long m1994getDaysUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.DAYS);
        }

        /* JADX INFO: renamed from: getHours-UwyO8pc, reason: not valid java name */
        public final long m1997getHoursUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.HOURS);
        }

        /* JADX INFO: renamed from: getINFINITE-UwyO8pc, reason: not valid java name */
        public final long m1999getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* JADX INFO: renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        public final long m2001getMicrosecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.MICROSECONDS);
        }

        /* JADX INFO: renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        public final long m2004getMillisecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.MILLISECONDS);
        }

        /* JADX INFO: renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        public final long m2007getMinutesUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.MINUTES);
        }

        /* JADX INFO: renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib, reason: not valid java name */
        public final long m2009getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.NEG_INFINITE;
        }

        /* JADX INFO: renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        public final long m2011getNanosecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.NANOSECONDS);
        }

        /* JADX INFO: renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        public final long m2014getSecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.SECONDS);
        }

        /* JADX INFO: renamed from: getZERO-UwyO8pc, reason: not valid java name */
        public final long m2016getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* JADX INFO: renamed from: parse-UwyO8pc, reason: not valid java name */
        public final long m2017parseUwyO8pc(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.parseDuration(value, false);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Invalid duration string format: '", value, "'."), e);
            }
        }

        /* JADX INFO: renamed from: parseIsoString-UwyO8pc, reason: not valid java name */
        public final long m2018parseIsoStringUwyO8pc(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.parseDuration(value, true);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Invalid ISO duration string format: '", value, "'."), e);
            }
        }

        @Nullable
        /* JADX INFO: renamed from: parseIsoStringOrNull-FghU774, reason: not valid java name */
        public final Duration m2019parseIsoStringOrNullFghU774(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return new Duration(DurationKt.parseDuration(value, true));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @Nullable
        /* JADX INFO: renamed from: parseOrNull-FghU774, reason: not valid java name */
        public final Duration m2020parseOrNullFghU774(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return new Duration(DurationKt.parseDuration(value, false));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1973getDaysUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1976getHoursUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1979getMicrosecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1982getMillisecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1985getMinutesUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1988getNanosecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1991getSecondsUwyO8pc$annotations(int i) {
        }

        /* JADX INFO: renamed from: getDays-UwyO8pc, reason: not valid java name */
        public final long m1995getDaysUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.DAYS);
        }

        /* JADX INFO: renamed from: getHours-UwyO8pc, reason: not valid java name */
        public final long m1998getHoursUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.HOURS);
        }

        /* JADX INFO: renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        public final long m2002getMicrosecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.MICROSECONDS);
        }

        /* JADX INFO: renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        public final long m2005getMillisecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.MILLISECONDS);
        }

        /* JADX INFO: renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        public final long m2008getMinutesUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.MINUTES);
        }

        /* JADX INFO: renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        public final long m2012getNanosecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.NANOSECONDS);
        }

        /* JADX INFO: renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        public final long m2015getSecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.SECONDS);
        }

        @InlineOnly
        /* JADX INFO: renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1974getDaysUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1977getHoursUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1980getMicrosecondsUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1983getMillisecondsUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1986getMinutesUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1989getNanosecondsUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* JADX INFO: renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m1992getSecondsUwyO8pc$annotations(long j) {
        }

        /* JADX INFO: renamed from: getDays-UwyO8pc, reason: not valid java name */
        public final long m1993getDaysUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.DAYS);
        }

        /* JADX INFO: renamed from: getHours-UwyO8pc, reason: not valid java name */
        public final long m1996getHoursUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.HOURS);
        }

        /* JADX INFO: renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        public final long m2000getMicrosecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.MICROSECONDS);
        }

        /* JADX INFO: renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        public final long m2003getMillisecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.MILLISECONDS);
        }

        /* JADX INFO: renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        public final long m2006getMinutesUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.MINUTES);
        }

        /* JADX INFO: renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        public final long m2010getNanosecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.NANOSECONDS);
        }

        /* JADX INFO: renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        public final long m2013getSecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.SECONDS);
        }
    }

    static {
        m1925constructorimpl(0L);
        ZERO = 0L;
        INFINITE = DurationKt.durationOfMillis(4611686018427387903L);
        NEG_INFINITE = DurationKt.durationOfMillis(-4611686018427387903L);
    }

    public /* synthetic */ Duration(long j) {
        this.rawValue = j;
    }

    /* JADX INFO: renamed from: addValuesMixedRanges-UwyO8pc, reason: not valid java name */
    public static final long m1921addValuesMixedRangesUwyO8pc(long j, long j2, long j3) {
        long j4 = 1000000;
        long j5 = j3 / j4;
        long j6 = j2 + j5;
        if (-4611686018426L > j6 || j6 >= 4611686018427L) {
            return DurationKt.durationOfMillis(RangesKt___RangesKt.coerceIn(j6, -4611686018427387903L, 4611686018427387903L));
        }
        return DurationKt.durationOfNanos((j6 * j4) + (j3 - (j5 * j4)));
    }

    /* JADX INFO: renamed from: appendFractional-impl, reason: not valid java name */
    public static final void m1922appendFractionalimpl(long j, StringBuilder sb, int i, int i2, int i3, String str, boolean z) {
        sb.append(i);
        if (i2 != 0) {
            sb.append('.');
            String strPadStart = StringsKt__StringsKt.padStart(String.valueOf(i2), i3, '0');
            int i4 = -1;
            int length = strPadStart.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i5 = length - 1;
                    if (strPadStart.charAt(length) != '0') {
                        i4 = length;
                        break;
                    } else if (i5 < 0) {
                        break;
                    } else {
                        length = i5;
                    }
                }
            }
            int i6 = i4 + 1;
            if (z || i6 >= 3) {
                sb.append((CharSequence) strPadStart, 0, ((i4 + 3) / 3) * 3);
            } else {
                sb.append((CharSequence) strPadStart, 0, i6);
            }
        }
        sb.append(str);
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m1923boximpl(long j) {
        return new Duration(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1925constructorimpl(long j) {
        if (!DurationJvmKt.getDurationAssertionsEnabled()) {
            return j;
        }
        if (m1949isInNanosimpl(j)) {
            long j2 = j >> 1;
            if (-4611686018426999999L <= j2 && j2 < 4611686018427000000L) {
                return j;
            }
            throw new AssertionError(j2 + " ns is out of nanoseconds range");
        }
        long j3 = j >> 1;
        if (-4611686018427387903L > j3 || j3 >= 4611686018427387904L) {
            throw new AssertionError(j3 + " ms is out of milliseconds range");
        }
        if (-4611686018426L > j3 || j3 >= 4611686018427L) {
            return j;
        }
        throw new AssertionError(j3 + " ms is denormalized");
    }

    /* JADX INFO: renamed from: div-LRDsOJo, reason: not valid java name */
    public static final double m1926divLRDsOJo(long j, long j2) {
        DurationUnit durationUnit = (DurationUnit) ComparisonsKt___ComparisonsJvmKt.maxOf(m1943getStorageUnitimpl(j), m1943getStorageUnitimpl(j2));
        return m1961toDoubleimpl(j, durationUnit) / m1961toDoubleimpl(j2, durationUnit);
    }

    /* JADX INFO: renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m1928divUwyO8pc(long j, int i) {
        if (i == 0) {
            if (m1952isPositiveimpl(j)) {
                return INFINITE;
            }
            if (m1951isNegativeimpl(j)) {
                return NEG_INFINITE;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        }
        if (m1949isInNanosimpl(j)) {
            return DurationKt.durationOfNanos((j >> 1) / ((long) i));
        }
        if (m1950isInfiniteimpl(j)) {
            return m1956timesUwyO8pc(j, Integer.signum(i));
        }
        long j2 = j >> 1;
        long j3 = i;
        long j4 = j2 / j3;
        if (-4611686018426L > j4 || j4 >= 4611686018427L) {
            return DurationKt.durationOfMillis(j4);
        }
        long j5 = 1000000;
        return DurationKt.durationOfNanos((j4 * j5) + (((j2 - (j4 * j3)) * j5) / j3));
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1929equalsimpl(long j, Object obj) {
        return (obj instanceof Duration) && j == ((Duration) obj).rawValue;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1930equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getAbsoluteValue-UwyO8pc, reason: not valid java name */
    public static final long m1931getAbsoluteValueUwyO8pc(long j) {
        return m1951isNegativeimpl(j) ? m1969unaryMinusUwyO8pc(j) : j;
    }

    /* JADX INFO: renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m1932getHoursComponentimpl(long j) {
        if (m1950isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1964toLongimpl(j, DurationUnit.HOURS) % ((long) 24));
    }

    /* JADX INFO: renamed from: getInWholeDays-impl, reason: not valid java name */
    public static final long m1933getInWholeDaysimpl(long j) {
        return m1964toLongimpl(j, DurationUnit.DAYS);
    }

    /* JADX INFO: renamed from: getInWholeHours-impl, reason: not valid java name */
    public static final long m1934getInWholeHoursimpl(long j) {
        return m1964toLongimpl(j, DurationUnit.HOURS);
    }

    /* JADX INFO: renamed from: getInWholeMicroseconds-impl, reason: not valid java name */
    public static final long m1935getInWholeMicrosecondsimpl(long j) {
        return m1964toLongimpl(j, DurationUnit.MICROSECONDS);
    }

    /* JADX INFO: renamed from: getInWholeMilliseconds-impl, reason: not valid java name */
    public static final long m1936getInWholeMillisecondsimpl(long j) {
        return (m1948isInMillisimpl(j) && m1947isFiniteimpl(j)) ? j >> 1 : m1964toLongimpl(j, DurationUnit.MILLISECONDS);
    }

    /* JADX INFO: renamed from: getInWholeMinutes-impl, reason: not valid java name */
    public static final long m1937getInWholeMinutesimpl(long j) {
        return m1964toLongimpl(j, DurationUnit.MINUTES);
    }

    /* JADX INFO: renamed from: getInWholeNanoseconds-impl, reason: not valid java name */
    public static final long m1938getInWholeNanosecondsimpl(long j) {
        long j2 = j >> 1;
        if (m1949isInNanosimpl(j)) {
            return j2;
        }
        if (j2 > EventLoop_commonKt.MAX_MS) {
            return Long.MAX_VALUE;
        }
        if (j2 < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        return j2 * ((long) 1000000);
    }

    /* JADX INFO: renamed from: getInWholeSeconds-impl, reason: not valid java name */
    public static final long m1939getInWholeSecondsimpl(long j) {
        return m1964toLongimpl(j, DurationUnit.SECONDS);
    }

    /* JADX INFO: renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m1940getMinutesComponentimpl(long j) {
        if (m1950isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1964toLongimpl(j, DurationUnit.MINUTES) % ((long) 60));
    }

    /* JADX INFO: renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m1941getNanosecondsComponentimpl(long j) {
        if (m1950isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1948isInMillisimpl(j) ? ((j >> 1) % ((long) 1000)) * ((long) 1000000) : (j >> 1) % ((long) InstantKt.NANOS_PER_SECOND));
    }

    /* JADX INFO: renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m1942getSecondsComponentimpl(long j) {
        if (m1950isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1964toLongimpl(j, DurationUnit.SECONDS) % ((long) 60));
    }

    /* JADX INFO: renamed from: getStorageUnit-impl, reason: not valid java name */
    public static final DurationUnit m1943getStorageUnitimpl(long j) {
        return m1949isInNanosimpl(j) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* JADX INFO: renamed from: getUnitDiscriminator-impl, reason: not valid java name */
    public static final int m1944getUnitDiscriminatorimpl(long j) {
        return ((int) j) & 1;
    }

    /* JADX INFO: renamed from: getValue-impl, reason: not valid java name */
    public static final long m1945getValueimpl(long j) {
        return j >> 1;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1946hashCodeimpl(long j) {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m1947isFiniteimpl(long j) {
        return !m1950isInfiniteimpl(j);
    }

    /* JADX INFO: renamed from: isInMillis-impl, reason: not valid java name */
    public static final boolean m1948isInMillisimpl(long j) {
        return (((int) j) & 1) == 1;
    }

    /* JADX INFO: renamed from: isInNanos-impl, reason: not valid java name */
    public static final boolean m1949isInNanosimpl(long j) {
        return (((int) j) & 1) == 0;
    }

    /* JADX INFO: renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m1950isInfiniteimpl(long j) {
        return j == INFINITE || j == NEG_INFINITE;
    }

    /* JADX INFO: renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m1951isNegativeimpl(long j) {
        return j < 0;
    }

    /* JADX INFO: renamed from: isPositive-impl, reason: not valid java name */
    public static final boolean m1952isPositiveimpl(long j) {
        return j > 0;
    }

    /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
    public static final long m1953minusLRDsOJo(long j, long j2) {
        return m1954plusLRDsOJo(j, m1969unaryMinusUwyO8pc(j2));
    }

    /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final long m1954plusLRDsOJo(long j, long j2) {
        if (m1950isInfiniteimpl(j)) {
            if (m1947isFiniteimpl(j2) || (j2 ^ j) >= 0) {
                return j;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (m1950isInfiniteimpl(j2)) {
            return j2;
        }
        if ((((int) j) & 1) != (((int) j2) & 1)) {
            return m1948isInMillisimpl(j) ? m1921addValuesMixedRangesUwyO8pc(j, j >> 1, j2 >> 1) : m1921addValuesMixedRangesUwyO8pc(j, j2 >> 1, j >> 1);
        }
        long j3 = (j >> 1) + (j2 >> 1);
        return m1949isInNanosimpl(j) ? DurationKt.durationOfNanosNormalized(j3) : DurationKt.durationOfMillisNormalized(j3);
    }

    /* JADX INFO: renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m1956timesUwyO8pc(long j, int i) {
        if (m1950isInfiniteimpl(j)) {
            if (i != 0) {
                return i > 0 ? j : m1969unaryMinusUwyO8pc(j);
            }
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
        }
        if (i == 0) {
            return ZERO;
        }
        long j2 = j >> 1;
        long j3 = i;
        long j4 = j2 * j3;
        if (!m1949isInNanosimpl(j)) {
            if (j4 / j3 == j2) {
                return DurationKt.durationOfMillis(RangesKt___RangesKt.coerceIn(j4, new LongRange(-4611686018427387903L, 4611686018427387903L)));
            }
            return Integer.signum(i) * Long.signum(j2) > 0 ? INFINITE : NEG_INFINITE;
        }
        if (-2147483647L <= j2 && j2 < WriteBuffer.INT32_SIGN_MASK) {
            return DurationKt.durationOfNanos(j4);
        }
        if (j4 / j3 == j2) {
            return DurationKt.durationOfNanosNormalized(j4);
        }
        long j5 = 1000000;
        long j6 = j2 / j5;
        long j7 = j6 * j3;
        long j8 = (((j2 - (j6 * j5)) * j3) / j5) + j7;
        if (j7 / j3 != j6 || (j8 ^ j7) < 0) {
            return Integer.signum(i) * Long.signum(j2) > 0 ? INFINITE : NEG_INFINITE;
        }
        return DurationKt.durationOfMillis(RangesKt___RangesKt.coerceIn(j8, new LongRange(-4611686018427387903L, 4611686018427387903L)));
    }

    /* JADX INFO: renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1960toComponentsimpl(long j, @NotNull Function5<? super Long, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1964toLongimpl(j, DurationUnit.DAYS)), Integer.valueOf(m1932getHoursComponentimpl(j)), Integer.valueOf(m1940getMinutesComponentimpl(j)), Integer.valueOf(m1942getSecondsComponentimpl(j)), Integer.valueOf(m1941getNanosecondsComponentimpl(j)));
    }

    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m1961toDoubleimpl(long j, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if (j == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j >> 1, m1943getStorageUnitimpl(j), unit);
    }

    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m1962toIntimpl(long j, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (int) RangesKt___RangesKt.coerceIn(m1964toLongimpl(j, unit), -2147483648L, 2147483647L);
    }

    @NotNull
    /* JADX INFO: renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m1963toIsoStringimpl(long j) {
        StringBuilder sb = new StringBuilder();
        if (m1951isNegativeimpl(j)) {
            sb.append('-');
        }
        sb.append("PT");
        long jM1931getAbsoluteValueUwyO8pc = m1931getAbsoluteValueUwyO8pc(j);
        long jM1964toLongimpl = m1964toLongimpl(jM1931getAbsoluteValueUwyO8pc, DurationUnit.HOURS);
        int iM1940getMinutesComponentimpl = m1940getMinutesComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        int iM1942getSecondsComponentimpl = m1942getSecondsComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        int iM1941getNanosecondsComponentimpl = m1941getNanosecondsComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        long j2 = m1950isInfiniteimpl(j) ? 9999999999999L : jM1964toLongimpl;
        boolean z = false;
        boolean z2 = j2 != 0;
        boolean z3 = (iM1942getSecondsComponentimpl == 0 && iM1941getNanosecondsComponentimpl == 0) ? false : true;
        if (iM1940getMinutesComponentimpl != 0 || (z3 && z2)) {
            z = true;
        }
        if (z2) {
            sb.append(j2);
            sb.append('H');
        }
        if (z) {
            sb.append(iM1940getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            m1922appendFractionalimpl(j, sb, iM1942getSecondsComponentimpl, iM1941getNanosecondsComponentimpl, 9, ExifInterface.LATITUDE_SOUTH, true);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m1964toLongimpl(long j, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j == INFINITE) {
            return Long.MAX_VALUE;
        }
        if (j == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j >> 1, m1943getStorageUnitimpl(j), unit);
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1965toStringimpl(long j) {
        if (j == 0) {
            return "0s";
        }
        if (j == INFINITE) {
            return "Infinity";
        }
        if (j == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean zM1951isNegativeimpl = m1951isNegativeimpl(j);
        StringBuilder sb = new StringBuilder();
        if (zM1951isNegativeimpl) {
            sb.append('-');
        }
        long jM1931getAbsoluteValueUwyO8pc = m1931getAbsoluteValueUwyO8pc(j);
        long jM1964toLongimpl = m1964toLongimpl(jM1931getAbsoluteValueUwyO8pc, DurationUnit.DAYS);
        int iM1932getHoursComponentimpl = m1932getHoursComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        int iM1940getMinutesComponentimpl = m1940getMinutesComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        int iM1942getSecondsComponentimpl = m1942getSecondsComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        int iM1941getNanosecondsComponentimpl = m1941getNanosecondsComponentimpl(jM1931getAbsoluteValueUwyO8pc);
        int i = 0;
        boolean z = jM1964toLongimpl != 0;
        boolean z2 = iM1932getHoursComponentimpl != 0;
        boolean z3 = iM1940getMinutesComponentimpl != 0;
        boolean z4 = (iM1942getSecondsComponentimpl == 0 && iM1941getNanosecondsComponentimpl == 0) ? false : true;
        if (z) {
            sb.append(jM1964toLongimpl);
            sb.append('d');
            i = 1;
        }
        if (z2 || (z && (z3 || z4))) {
            int i2 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(iM1932getHoursComponentimpl);
            sb.append('h');
            i = i2;
        }
        if (z3 || (z4 && (z2 || z))) {
            int i3 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(iM1940getMinutesComponentimpl);
            sb.append('m');
            i = i3;
        }
        if (z4) {
            int i4 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            if (iM1942getSecondsComponentimpl != 0 || z || z2 || z3) {
                m1922appendFractionalimpl(j, sb, iM1942getSecondsComponentimpl, iM1941getNanosecondsComponentimpl, 9, CmcdData.Factory.STREAMING_FORMAT_SS, false);
            } else if (iM1941getNanosecondsComponentimpl >= 1000000) {
                m1922appendFractionalimpl(j, sb, iM1941getNanosecondsComponentimpl / 1000000, iM1941getNanosecondsComponentimpl % 1000000, 6, "ms", false);
            } else if (iM1941getNanosecondsComponentimpl >= 1000) {
                m1922appendFractionalimpl(j, sb, iM1941getNanosecondsComponentimpl / 1000, iM1941getNanosecondsComponentimpl % 1000, 3, "us", false);
            } else {
                sb.append(iM1941getNanosecondsComponentimpl);
                sb.append("ns");
            }
            i = i4;
        }
        if (zM1951isNegativeimpl && i > 1) {
            sb.insert(1, '(').append(')');
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: toString-impl$default, reason: not valid java name */
    public static /* synthetic */ String m1967toStringimpl$default(long j, DurationUnit durationUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m1966toStringimpl(j, durationUnit, i);
    }

    /* JADX INFO: renamed from: truncateTo-UwyO8pc$kotlin_stdlib, reason: not valid java name */
    public static final long m1968truncateToUwyO8pc$kotlin_stdlib(long j, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        DurationUnit durationUnitM1943getStorageUnitimpl = m1943getStorageUnitimpl(j);
        if (unit.compareTo(durationUnitM1943getStorageUnitimpl) <= 0 || m1950isInfiniteimpl(j)) {
            return j;
        }
        long j2 = j >> 1;
        return DurationKt.toDuration(j2 - (j2 % DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(1L, unit, durationUnitM1943getStorageUnitimpl)), durationUnitM1943getStorageUnitimpl);
    }

    /* JADX INFO: renamed from: unaryMinus-UwyO8pc, reason: not valid java name */
    public static final long m1969unaryMinusUwyO8pc(long j) {
        return DurationKt.durationOf(-(j >> 1), ((int) j) & 1);
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        return m1924compareToLRDsOJo(this.rawValue, duration.rawValue);
    }

    /* JADX INFO: renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m1970compareToLRDsOJo(long j) {
        return m1924compareToLRDsOJo(this.rawValue, j);
    }

    public boolean equals(Object obj) {
        return m1929equalsimpl(this.rawValue, obj);
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.rawValue);
    }

    @NotNull
    public String toString() {
        return m1965toStringimpl(this.rawValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m1971unboximpl() {
        return this.rawValue;
    }

    /* JADX INFO: renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m1924compareToLRDsOJo(long j, long j2) {
        long j3 = j ^ j2;
        if (j3 < 0 || (((int) j3) & 1) == 0) {
            return Intrinsics.compare(j, j2);
        }
        int i = (((int) j) & 1) - (((int) j2) & 1);
        return m1951isNegativeimpl(j) ? -i : i;
    }

    /* JADX INFO: renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1959toComponentsimpl(long j, @NotNull Function4<? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1964toLongimpl(j, DurationUnit.HOURS)), Integer.valueOf(m1940getMinutesComponentimpl(j)), Integer.valueOf(m1942getSecondsComponentimpl(j)), Integer.valueOf(m1941getNanosecondsComponentimpl(j)));
    }

    /* JADX INFO: renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1958toComponentsimpl(long j, @NotNull Function3<? super Long, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1964toLongimpl(j, DurationUnit.MINUTES)), Integer.valueOf(m1942getSecondsComponentimpl(j)), Integer.valueOf(m1941getNanosecondsComponentimpl(j)));
    }

    /* JADX INFO: renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1957toComponentsimpl(long j, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1964toLongimpl(j, DurationUnit.SECONDS)), Integer.valueOf(m1941getNanosecondsComponentimpl(j)));
    }

    /* JADX INFO: renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m1927divUwyO8pc(long j, double d) {
        int iRoundToInt = MathKt__MathJVMKt.roundToInt(d);
        if (iRoundToInt == d && iRoundToInt != 0) {
            return m1928divUwyO8pc(j, iRoundToInt);
        }
        DurationUnit durationUnitM1943getStorageUnitimpl = m1943getStorageUnitimpl(j);
        return DurationKt.toDuration(m1961toDoubleimpl(j, durationUnitM1943getStorageUnitimpl) / d, durationUnitM1943getStorageUnitimpl);
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static final String m1966toStringimpl(long j, @NotNull DurationUnit unit, int i) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (i >= 0) {
            double dM1961toDoubleimpl = m1961toDoubleimpl(j, unit);
            if (Double.isInfinite(dM1961toDoubleimpl)) {
                return String.valueOf(dM1961toDoubleimpl);
            }
            if (i > 12) {
                i = 12;
            }
            return DurationJvmKt.formatToExactDecimals(dM1961toDoubleimpl, i).concat(DurationUnitKt__DurationUnitKt.shortName(unit));
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("decimals must be not negative, but was ", i).toString());
    }

    /* JADX INFO: renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m1955timesUwyO8pc(long j, double d) {
        int iRoundToInt = MathKt__MathJVMKt.roundToInt(d);
        if (iRoundToInt == d) {
            return m1956timesUwyO8pc(j, iRoundToInt);
        }
        DurationUnit durationUnitM1943getStorageUnitimpl = m1943getStorageUnitimpl(j);
        return DurationKt.toDuration(m1961toDoubleimpl(j, durationUnitM1943getStorageUnitimpl) * d, durationUnitM1943getStorageUnitimpl);
    }
}
