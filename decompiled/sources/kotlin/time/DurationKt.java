package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import kotlin.time.Duration;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1068:1\n1021#1,6:1070\n1024#1,3:1076\n1021#1,6:1079\n1021#1,6:1085\n1024#1,3:1091\n1#2:1069\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n*L\n936#1:1070,6\n970#1:1076,3\n973#1:1079,6\n976#1:1085,6\n1021#1:1091,3\n*E\n"})
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    public static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    public static final long access$millisToNanos(long j) {
        return j * ((long) 1000000);
    }

    public static final long access$nanosToMillis(long j) {
        return j / ((long) 1000000);
    }

    public static final long durationOf(long j, int i) {
        long j2 = (j << 1) + ((long) i);
        Duration.m1925constructorimpl(j2);
        return j2;
    }

    public static final long durationOfMillis(long j) {
        long j2 = (j << 1) + 1;
        Duration.m1925constructorimpl(j2);
        return j2;
    }

    public static final long durationOfMillisNormalized(long j) {
        return (-4611686018426L > j || j >= 4611686018427L) ? durationOfMillis(RangesKt___RangesKt.coerceIn(j, -4611686018427387903L, 4611686018427387903L)) : durationOfNanos(j * ((long) 1000000));
    }

    public static final long durationOfNanos(long j) {
        long j2 = j << 1;
        Duration.m1925constructorimpl(j2);
        return j2;
    }

    public static final long durationOfNanosNormalized(long j) {
        return (-4611686018426999999L > j || j >= 4611686018427000000L) ? durationOfMillis(j / ((long) 1000000)) : durationOfNanos(j);
    }

    public static final long millisToNanos(long j) {
        return j * ((long) 1000000);
    }

    public static final long nanosToMillis(long j) {
        return j / ((long) 1000000);
    }

    public static final long parseDuration(String str, boolean z) {
        char cCharAt;
        char cCharAt2;
        boolean z2;
        char cCharAt3;
        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException("The string is empty");
        }
        Duration.Companion companion = Duration.Companion;
        companion.getClass();
        long jM1954plusLRDsOJo = Duration.ZERO;
        char cCharAt4 = str.charAt(0);
        int i = (cCharAt4 == '+' || cCharAt4 == '-') ? 1 : 0;
        boolean z3 = i > 0;
        boolean z4 = z3 && StringsKt__StringsKt.startsWith$default((CharSequence) str, '-', false, 2, (Object) null);
        if (length <= i) {
            throw new IllegalArgumentException("No components");
        }
        char c = ':';
        char c2 = '0';
        if (str.charAt(i) == 'P') {
            int i2 = i + 1;
            if (i2 == length) {
                throw new IllegalArgumentException();
            }
            DurationUnit durationUnit = null;
            boolean z5 = false;
            while (i2 < length) {
                if (str.charAt(i2) != 'T') {
                    int i3 = i2;
                    while (i3 < str.length() && ((c2 <= (cCharAt3 = str.charAt(i3)) && cCharAt3 < c) || StringsKt__StringsKt.contains$default((CharSequence) "+-.", cCharAt3, false, 2, (Object) null))) {
                        i3++;
                        c2 = '0';
                        c = ':';
                    }
                    String strSubstring = str.substring(i2, i3);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    if (strSubstring.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int length2 = strSubstring.length() + i2;
                    if (length2 < 0 || length2 >= str.length()) {
                        throw new IllegalArgumentException("Missing unit for value ".concat(strSubstring));
                    }
                    char cCharAt5 = str.charAt(length2);
                    int i4 = length2 + 1;
                    DurationUnit durationUnitDurationUnitByIsoChar = DurationUnitKt__DurationUnitKt.durationUnitByIsoChar(cCharAt5, z5);
                    if (durationUnit != null && durationUnit.compareTo(durationUnitDurationUnitByIsoChar) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) strSubstring, '.', 0, false, 6, (Object) null);
                    if (durationUnitDurationUnitByIsoChar != DurationUnit.SECONDS || iIndexOf$default <= 0) {
                        z2 = z5;
                        jM1954plusLRDsOJo = Duration.m1954plusLRDsOJo(jM1954plusLRDsOJo, toDuration(parseOverLongIsoComponent(strSubstring), durationUnitDurationUnitByIsoChar));
                    } else {
                        String strSubstring2 = strSubstring.substring(0, iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        z2 = z5;
                        long jM1954plusLRDsOJo2 = Duration.m1954plusLRDsOJo(jM1954plusLRDsOJo, toDuration(parseOverLongIsoComponent(strSubstring2), durationUnitDurationUnitByIsoChar));
                        String strSubstring3 = strSubstring.substring(iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        jM1954plusLRDsOJo = Duration.m1954plusLRDsOJo(jM1954plusLRDsOJo2, toDuration(Double.parseDouble(strSubstring3), durationUnitDurationUnitByIsoChar));
                    }
                    z5 = z2;
                    durationUnit = durationUnitDurationUnitByIsoChar;
                    i2 = i4;
                    c2 = '0';
                    c = ':';
                } else {
                    if (z5 || (i2 = i2 + 1) == length) {
                        throw new IllegalArgumentException();
                    }
                    z5 = true;
                }
            }
        } else {
            if (z) {
                throw new IllegalArgumentException();
            }
            char c3 = '0';
            if (StringsKt__StringsJVMKt.regionMatches(str, i, "Infinity", 0, Math.max(length - i, 8), true)) {
                companion.getClass();
                jM1954plusLRDsOJo = Duration.INFINITE;
            } else {
                boolean z6 = !z3;
                if (z3 && str.charAt(i) == '(' && StringsKt___StringsKt.last(str) == ')') {
                    i++;
                    length--;
                    if (i == length) {
                        throw new IllegalArgumentException("No components");
                    }
                    z6 = true;
                }
                boolean z7 = false;
                DurationUnit durationUnit2 = null;
                while (i < length) {
                    if (z7 && z6) {
                        while (i < str.length() && str.charAt(i) == ' ') {
                            i++;
                        }
                    }
                    int i5 = i;
                    while (i5 < str.length() && ((c3 <= (cCharAt2 = str.charAt(i5)) && cCharAt2 < ':') || cCharAt2 == '.')) {
                        i5++;
                    }
                    String strSubstring4 = str.substring(i, i5);
                    Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
                    if (strSubstring4.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int length3 = strSubstring4.length() + i;
                    int i6 = length3;
                    while (i6 < str.length() && 'a' <= (cCharAt = str.charAt(i6)) && cCharAt < '{') {
                        i6++;
                    }
                    String strSubstring5 = str.substring(length3, i6);
                    Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
                    int length4 = length3 + strSubstring5.length();
                    DurationUnit durationUnitDurationUnitByShortName = DurationUnitKt__DurationUnitKt.durationUnitByShortName(strSubstring5);
                    if (durationUnit2 != null && durationUnit2.compareTo(durationUnitDurationUnitByShortName) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) strSubstring4, '.', 0, false, 6, (Object) null);
                    if (iIndexOf$default2 > 0) {
                        String strSubstring6 = strSubstring4.substring(0, iIndexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring6, "substring(...)");
                        long jM1954plusLRDsOJo3 = Duration.m1954plusLRDsOJo(jM1954plusLRDsOJo, toDuration(Long.parseLong(strSubstring6), durationUnitDurationUnitByShortName));
                        String strSubstring7 = strSubstring4.substring(iIndexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring7, "substring(...)");
                        jM1954plusLRDsOJo = Duration.m1954plusLRDsOJo(jM1954plusLRDsOJo3, toDuration(Double.parseDouble(strSubstring7), durationUnitDurationUnitByShortName));
                        if (length4 < length) {
                            throw new IllegalArgumentException("Fractional component must be last");
                        }
                    } else {
                        jM1954plusLRDsOJo = Duration.m1954plusLRDsOJo(jM1954plusLRDsOJo, toDuration(Long.parseLong(strSubstring4), durationUnitDurationUnitByShortName));
                    }
                    durationUnit2 = durationUnitDurationUnitByShortName;
                    i = length4;
                    z7 = true;
                    c3 = '0';
                }
            }
        }
        return z4 ? Duration.m1969unaryMinusUwyO8pc(jM1954plusLRDsOJo) : jM1954plusLRDsOJo;
    }

    public static final long parseOverLongIsoComponent(String str) {
        char cCharAt;
        int length = str.length();
        int i = (length <= 0 || !StringsKt__StringsKt.contains$default((CharSequence) "+-", str.charAt(0), false, 2, (Object) null)) ? 0 : 1;
        if (length - i > 16) {
            int i2 = i;
            while (true) {
                if (i < length) {
                    char cCharAt2 = str.charAt(i);
                    if (cCharAt2 == '0') {
                        if (i2 == i) {
                            i2++;
                        }
                    } else if ('1' > cCharAt2 || cCharAt2 >= ':') {
                        break;
                    }
                    i++;
                } else if (length - i2 > 16) {
                    return str.charAt(0) == '-' ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
            }
        }
        return (!StringsKt__StringsJVMKt.startsWith$default(str, "+", false, 2, null) || length <= 1 || '0' > (cCharAt = str.charAt(1)) || cCharAt >= ':') ? Long.parseLong(str) : Long.parseLong(StringsKt___StringsKt.drop(str, 1));
    }

    public static final int skipWhile(String str, int i, Function1<? super Character, Boolean> function1) {
        while (i < str.length() && function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    public static final String substringWhile(String str, int i, Function1<? super Character, Boolean> function1) {
        int i2 = i;
        while (i2 < str.length() && function1.invoke(Character.valueOf(str.charAt(i2))).booleanValue()) {
            i2++;
        }
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    @InlineOnly
    /* JADX INFO: renamed from: times-kIfJnKk, reason: not valid java name */
    public static final long m2021timeskIfJnKk(double d, long j) {
        return Duration.m1955timesUwyO8pc(j, d);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    @InlineOnly
    /* JADX INFO: renamed from: times-mvk6XK0, reason: not valid java name */
    public static final long m2022timesmvk6XK0(int i, long j) {
        return Duration.m1956timesUwyO8pc(j, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final long toDuration(int i, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return unit.compareTo(DurationUnit.SECONDS) <= 0 ? durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(i, unit, DurationUnit.NANOSECONDS)) : toDuration(i, unit);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final long toDuration(long j, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        DurationUnit durationUnit = DurationUnit.NANOSECONDS;
        long jConvertDurationUnitOverflow = DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(MAX_NANOS, durationUnit, unit);
        if ((-jConvertDurationUnitOverflow) <= j && j <= jConvertDurationUnitOverflow) {
            return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(j, unit, durationUnit));
        }
        return durationOfMillis(RangesKt___RangesKt.coerceIn(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L));
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final long toDuration(double d, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double dConvertDurationUnit = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, unit, DurationUnit.NANOSECONDS);
        if (!Double.isNaN(dConvertDurationUnit)) {
            long jRoundToLong = MathKt__MathJVMKt.roundToLong(dConvertDurationUnit);
            if (-4611686018426999999L <= jRoundToLong && jRoundToLong < 4611686018427000000L) {
                return durationOfNanos(jRoundToLong);
            }
            return durationOfMillisNormalized(MathKt__MathJVMKt.roundToLong(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, unit, DurationUnit.MILLISECONDS)));
        }
        throw new IllegalArgumentException("Duration value cannot be NaN.");
    }
}
