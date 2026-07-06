package kotlin.time;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import java.io.IOException;
import kotlin.KotlinNothingValueException;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/InstantKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,799:1\n1#2:800\n*E\n"})
public final class InstantKt {
    public static final int DAYS_0000_TO_1970 = 719528;
    public static final int DAYS_PER_CYCLE = 146097;
    public static final long DISTANT_FUTURE_SECONDS = 3093527980800L;
    public static final long DISTANT_PAST_SECONDS = -3217862419201L;
    public static final int HOURS_PER_DAY = 24;
    public static final long MAX_SECOND = 31556889864403199L;
    public static final int MILLIS_PER_SECOND = 1000;
    public static final long MIN_SECOND = -31557014167219200L;
    public static final int NANOS_PER_MILLI = 1000000;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int NANOS_PER_SECOND = 1000000000;

    @NotNull
    public static final int[] POWERS_OF_TEN = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, NANOS_PER_SECOND};

    @NotNull
    public static final int[] asciiDigitPositionsInIsoStringAfterYear = {1, 2, 4, 5, 7, 8, 10, 11, 13, 14};

    @NotNull
    public static final int[] colonsInIsoOffsetString = {3, 6};

    @NotNull
    public static final int[] asciiDigitsInIsoOffsetString = {1, 2, 4, 5, 7, 8};

    @ExperimentalTime
    public static final String formatIso(Instant instant) throws IOException {
        int i;
        int[] iArr;
        StringBuilder sb = new StringBuilder();
        UnboundLocalDateTime unboundLocalDateTimeFromInstant = UnboundLocalDateTime.Companion.fromInstant(instant);
        int i2 = unboundLocalDateTimeFromInstant.year;
        int i3 = 0;
        if (Math.abs(i2) < 1000) {
            StringBuilder sb2 = new StringBuilder();
            if (i2 >= 0) {
                sb2.append(i2 + 10000);
                Intrinsics.checkNotNullExpressionValue(sb2.deleteCharAt(0), "deleteCharAt(...)");
            } else {
                sb2.append(i2 - 10000);
                Intrinsics.checkNotNullExpressionValue(sb2.deleteCharAt(1), "deleteCharAt(...)");
            }
            sb.append((CharSequence) sb2);
        } else {
            if (i2 >= 10000) {
                sb.append('+');
            }
            sb.append(i2);
        }
        sb.append('-');
        formatIso$lambda$8$appendTwoDigits(sb, sb, unboundLocalDateTimeFromInstant.month);
        sb.append('-');
        formatIso$lambda$8$appendTwoDigits(sb, sb, unboundLocalDateTimeFromInstant.day);
        sb.append('T');
        formatIso$lambda$8$appendTwoDigits(sb, sb, unboundLocalDateTimeFromInstant.hour);
        sb.append(':');
        formatIso$lambda$8$appendTwoDigits(sb, sb, unboundLocalDateTimeFromInstant.minute);
        sb.append(':');
        formatIso$lambda$8$appendTwoDigits(sb, sb, unboundLocalDateTimeFromInstant.second);
        if (unboundLocalDateTimeFromInstant.nanosecond != 0) {
            sb.append('.');
            while (true) {
                i = unboundLocalDateTimeFromInstant.nanosecond;
                iArr = POWERS_OF_TEN;
                int i4 = i3 + 1;
                if (i % iArr[i4] != 0) {
                    break;
                }
                i3 = i4;
            }
            int i5 = i3 - (i3 % 3);
            String strValueOf = String.valueOf((i / iArr[i5]) + iArr[9 - i5]);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String strSubstring = strValueOf.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            sb.append(strSubstring);
        }
        sb.append('Z');
        return sb.toString();
    }

    public static final void formatIso$lambda$8$appendTwoDigits(Appendable appendable, StringBuilder sb, int i) throws IOException {
        if (i < 10) {
            appendable.append('0');
        }
        sb.append(i);
    }

    public static final boolean isDistantFuture(Instant instant) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        return instant.compareTo(Instant.Companion.getDISTANT_FUTURE()) >= 0;
    }

    public static final boolean isDistantPast(Instant instant) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        return instant.compareTo(Instant.Companion.getDISTANT_PAST()) <= 0;
    }

    public static final boolean isLeapYear(int i) {
        if ((i & 3) == 0) {
            return i % 100 != 0 || i % 400 == 0;
        }
        return false;
    }

    public static final int monthLength(int i, boolean z) {
        return i != 2 ? (i == 4 || i == 6 || i == 9 || i == 11) ? 30 : 31 : z ? 29 : 28;
    }

    @ExperimentalTime
    public static final Instant parseIso(CharSequence charSequence) throws Throwable {
        int i;
        int i2;
        int i3;
        char cCharAt;
        char cCharAt2;
        if (charSequence.length() <= 0) {
            throw new IllegalArgumentException("An empty string is not a valid Instant");
        }
        char cCharAt3 = charSequence.charAt(0);
        if (cCharAt3 == '+' || cCharAt3 == '-') {
            i = 1;
        } else {
            cCharAt3 = ' ';
            i = 0;
        }
        int i4 = i;
        int iCharAt = 0;
        while (i4 < charSequence.length() && '0' <= (cCharAt2 = charSequence.charAt(i4)) && cCharAt2 < ':') {
            iCharAt = (iCharAt * 10) + (charSequence.charAt(i4) - '0');
            i4++;
        }
        int i5 = i4 - i;
        Throwable th = null;
        if (i5 > 10) {
            parseIso$parseFailure(charSequence, "Expected at most 10 digits for the year number, got " + i5 + " digits");
            throw null;
        }
        if (i5 == 10 && Intrinsics.compare((int) charSequence.charAt(i), 50) >= 0) {
            parseIso$parseFailure(charSequence, "Expected at most 9 digits for the year number or year 1000000000, got " + i5 + " digits");
            throw null;
        }
        if (i5 < 4) {
            parseIso$parseFailure(charSequence, "The year number must be padded to 4 digits, got " + i5 + " digits");
            throw null;
        }
        if (cCharAt3 == '+' && i5 == 4) {
            parseIso$parseFailure(charSequence, "The '+' sign at the start is only valid for year numbers longer than 4 digits");
            throw null;
        }
        if (cCharAt3 == ' ' && i5 != 4) {
            parseIso$parseFailure(charSequence, "A '+' or '-' sign is required for year numbers longer than 4 digits");
            throw null;
        }
        if (cCharAt3 == '-') {
            iCharAt = -iCharAt;
        }
        int i6 = iCharAt;
        int i7 = i4 + 16;
        if (charSequence.length() < i7) {
            parseIso$parseFailure(charSequence, "The input string is too short");
            throw null;
        }
        parseIso$expect(charSequence, "'-'", i4, new InstantKt$$ExternalSyntheticLambda0());
        parseIso$expect(charSequence, "'-'", i4 + 3, new InstantKt$$ExternalSyntheticLambda1());
        parseIso$expect(charSequence, "'T' or 't'", i4 + 6, new InstantKt$$ExternalSyntheticLambda2());
        parseIso$expect(charSequence, "':'", i4 + 9, new InstantKt$$ExternalSyntheticLambda3());
        parseIso$expect(charSequence, "':'", i4 + 12, new InstantKt$$ExternalSyntheticLambda4());
        int[] iArr = asciiDigitPositionsInIsoStringAfterYear;
        int length = iArr.length;
        int i8 = 0;
        while (i8 < length) {
            parseIso$expect(charSequence, "an ASCII digit", iArr[i8] + i4, new InstantKt$$ExternalSyntheticLambda5());
            i8++;
            th = th;
        }
        Throwable th2 = th;
        int iso$twoDigitNumber = parseIso$twoDigitNumber(charSequence, i4 + 1);
        int iso$twoDigitNumber2 = parseIso$twoDigitNumber(charSequence, i4 + 4);
        int iso$twoDigitNumber3 = parseIso$twoDigitNumber(charSequence, i4 + 7);
        int iso$twoDigitNumber4 = parseIso$twoDigitNumber(charSequence, i4 + 10);
        int iso$twoDigitNumber5 = parseIso$twoDigitNumber(charSequence, i4 + 13);
        int i9 = i4 + 15;
        if (charSequence.charAt(i9) == '.') {
            i9 = i7;
            int iCharAt2 = 0;
            while (i9 < charSequence.length() && '0' <= (cCharAt = charSequence.charAt(i9)) && cCharAt < ':') {
                iCharAt2 = (iCharAt2 * 10) + (charSequence.charAt(i9) - '0');
                i9++;
            }
            int i10 = i9 - i7;
            if (1 > i10 || i10 >= 10) {
                parseIso$parseFailure(charSequence, "1..9 digits are supported for the fraction of the second, got " + i10 + " digits");
                throw th2;
            }
            i2 = iCharAt2 * POWERS_OF_TEN[9 - i10];
        } else {
            i2 = 0;
        }
        if (i9 >= charSequence.length()) {
            parseIso$parseFailure(charSequence, "The UTC offset at the end of the string is missing");
            throw th2;
        }
        char cCharAt4 = charSequence.charAt(i9);
        if (cCharAt4 == '+' || cCharAt4 == '-') {
            int length2 = charSequence.length() - i9;
            if (length2 > 9) {
                parseIso$parseFailure(charSequence, "The UTC offset string \"" + truncateForErrorMessage(charSequence.subSequence(i9, charSequence.length()).toString(), 16) + "\" is too long");
                throw th2;
            }
            if (length2 % 3 != 0) {
                parseIso$parseFailure(charSequence, "Invalid UTC offset string \"" + charSequence.subSequence(i9, charSequence.length()).toString() + '\"');
                throw th2;
            }
            for (int i11 : colonsInIsoOffsetString) {
                int i12 = i9 + i11;
                if (i12 >= charSequence.length()) {
                    break;
                }
                if (charSequence.charAt(i12) != ':') {
                    StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Expected ':' at index ", i12, ", got '");
                    sbM.append(charSequence.charAt(i12));
                    sbM.append('\'');
                    parseIso$parseFailure(charSequence, sbM.toString());
                    throw th2;
                }
            }
            int[] iArr2 = asciiDigitsInIsoOffsetString;
            int length3 = iArr2.length;
            int i13 = 0;
            while (i13 < length3) {
                int i14 = iArr2[i13] + i9;
                if (i14 >= charSequence.length()) {
                    break;
                }
                char cCharAt5 = charSequence.charAt(i14);
                int[] iArr3 = iArr2;
                if ('0' > cCharAt5 || cCharAt5 >= ':') {
                    StringBuilder sbM2 = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Expected an ASCII digit at index ", i14, ", got '");
                    sbM2.append(charSequence.charAt(i14));
                    sbM2.append('\'');
                    parseIso$parseFailure(charSequence, sbM2.toString());
                    throw th2;
                }
                i13++;
                iArr2 = iArr3;
            }
            int iso$twoDigitNumber6 = parseIso$twoDigitNumber(charSequence, i9 + 1);
            int iso$twoDigitNumber7 = length2 > 3 ? parseIso$twoDigitNumber(charSequence, i9 + 4) : 0;
            int iso$twoDigitNumber8 = length2 > 6 ? parseIso$twoDigitNumber(charSequence, i9 + 7) : 0;
            if (iso$twoDigitNumber7 > 59) {
                parseIso$parseFailure(charSequence, "Expected offset-minute-of-hour in 0..59, got " + iso$twoDigitNumber7);
                throw th2;
            }
            if (iso$twoDigitNumber8 > 59) {
                parseIso$parseFailure(charSequence, "Expected offset-second-of-minute in 0..59, got " + iso$twoDigitNumber8);
                throw th2;
            }
            if (iso$twoDigitNumber6 > 17 && (iso$twoDigitNumber6 != 18 || iso$twoDigitNumber7 != 0 || iso$twoDigitNumber8 != 0)) {
                parseIso$parseFailure(charSequence, "Expected an offset in -18:00..+18:00, got " + charSequence.subSequence(i9, charSequence.length()).toString());
                throw th2;
            }
            i3 = (cCharAt4 == '-' ? -1 : 1) * ((iso$twoDigitNumber7 * 60) + (iso$twoDigitNumber6 * 3600) + iso$twoDigitNumber8);
        } else {
            if (cCharAt4 != 'Z' && cCharAt4 != 'z') {
                parseIso$parseFailure(charSequence, "Expected the UTC offset at position " + i9 + ", got '" + cCharAt4 + '\'');
                throw th2;
            }
            int i15 = i9 + 1;
            if (charSequence.length() != i15) {
                parseIso$parseFailure(charSequence, "Extra text after the instant at position " + i15);
                throw th2;
            }
            i3 = 0;
        }
        if (1 > iso$twoDigitNumber || iso$twoDigitNumber >= 13) {
            parseIso$parseFailure(charSequence, "Expected a month number in 1..12, got " + iso$twoDigitNumber);
            throw th2;
        }
        if (1 > iso$twoDigitNumber2 || iso$twoDigitNumber2 > monthLength(iso$twoDigitNumber, isLeapYear(i6))) {
            StringBuilder sbM3 = MutableFloatList$$ExternalSyntheticOutline0.m("Expected a valid day-of-month for month ", iso$twoDigitNumber, " of year ", i6, ", got ");
            sbM3.append(iso$twoDigitNumber2);
            parseIso$parseFailure(charSequence, sbM3.toString());
            throw th2;
        }
        if (iso$twoDigitNumber3 > 23) {
            parseIso$parseFailure(charSequence, "Expected hour in 0..23, got " + iso$twoDigitNumber3);
            throw th2;
        }
        if (iso$twoDigitNumber4 > 59) {
            parseIso$parseFailure(charSequence, "Expected minute-of-hour in 0..59, got " + iso$twoDigitNumber4);
            throw th2;
        }
        if (iso$twoDigitNumber5 <= 59) {
            return new UnboundLocalDateTime(i6, iso$twoDigitNumber, iso$twoDigitNumber2, iso$twoDigitNumber3, iso$twoDigitNumber4, iso$twoDigitNumber5, i2).toInstant(i3);
        }
        parseIso$parseFailure(charSequence, "Expected second-of-minute in 0..59, got " + iso$twoDigitNumber5);
        throw th2;
    }

    public static final void parseIso$expect(CharSequence charSequence, String str, int i, Function1<? super Character, Boolean> function1) {
        char cCharAt = charSequence.charAt(i);
        if (function1.invoke(Character.valueOf(cCharAt)).booleanValue()) {
            return;
        }
        parseIso$parseFailure(charSequence, "Expected " + str + ", but got '" + cCharAt + "' at position " + i);
        throw null;
    }

    public static final boolean parseIso$lambda$1(char c) {
        return c == '-';
    }

    public static final boolean parseIso$lambda$2(char c) {
        return c == '-';
    }

    public static final boolean parseIso$lambda$3(char c) {
        return c == 'T' || c == 't';
    }

    public static final boolean parseIso$lambda$4(char c) {
        return c == ':';
    }

    public static final boolean parseIso$lambda$5(char c) {
        return c == ':';
    }

    public static final boolean parseIso$lambda$6(char c) {
        return '0' <= c && c < ':';
    }

    public static final Void parseIso$parseFailure(CharSequence charSequence, String str) {
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, " when parsing an Instant from \"");
        sbM.append(truncateForErrorMessage(charSequence, 64));
        sbM.append('\"');
        throw new InstantFormatException(sbM.toString());
    }

    public static final int parseIso$twoDigitNumber(CharSequence charSequence, int i) {
        return (charSequence.charAt(i + 1) - '0') + ((charSequence.charAt(i) - '0') * 10);
    }

    public static final long safeAddOrElse(long j, long j2, Function0 function0) {
        long j3 = j + j2;
        if ((j ^ j3) >= 0 || (j ^ j2) < 0) {
            return j3;
        }
        function0.invoke();
        throw new KotlinNothingValueException();
    }

    public static final long safeMultiplyOrElse(long j, long j2, Function0 function0) {
        if (j2 == 1) {
            return j;
        }
        if (j == 1) {
            return j2;
        }
        if (j == 0 || j2 == 0) {
            return 0L;
        }
        long j3 = j * j2;
        if (j3 / j2 == j && ((j != Long.MIN_VALUE || j2 != -1) && (j2 != Long.MIN_VALUE || j != -1))) {
            return j3;
        }
        function0.invoke();
        throw new KotlinNothingValueException();
    }

    public static final String truncateForErrorMessage(CharSequence charSequence, int i) {
        if (charSequence.length() <= i) {
            return charSequence.toString();
        }
        return charSequence.subSequence(0, i).toString() + "...";
    }

    @SinceKotlin(version = "2.1")
    @InlineOnly
    @ExperimentalTime
    public static /* synthetic */ void isDistantFuture$annotations(Instant instant) {
    }

    @SinceKotlin(version = "2.1")
    @InlineOnly
    @ExperimentalTime
    public static /* synthetic */ void isDistantPast$annotations(Instant instant) {
    }
}
