package com.amazon.ion;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import com.amazon.ion.impl.IonTokenConstsX;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.util.IonTextUtils;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Timestamp implements Comparable<Timestamp>, Cloneable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final boolean APPLY_OFFSET_NO = false;
    public static final boolean APPLY_OFFSET_YES = true;
    public static final int END_OF_DAY = 10;
    public static final int END_OF_MINUTES = 16;
    public static final int END_OF_MONTH = 7;
    public static final int END_OF_SECONDS = 19;
    public static final int END_OF_YEAR = 4;
    public static final int FLAG_DAY = 4;
    public static final int FLAG_MINUTE = 8;
    public static final int FLAG_MONTH = 2;
    public static final int FLAG_SECOND = 16;
    public static final int FLAG_YEAR = 1;
    public static final long MAXIMUM_TIMESTAMP_IN_EPOCH_SECONDS = 253402300800L;
    public static final long MINIMUM_TIMESTAMP_IN_EPOCH_SECONDS = -62135769600L;
    public static final int NO_DAY = 0;
    public static final BigDecimal NO_FRACTIONAL_SECONDS = null;
    public static final int NO_HOURS = 0;
    public static final int NO_MINUTES = 0;
    public static final int NO_MONTH = 0;
    public static final int NO_SECONDS = 0;
    public static final String NULL_TIMESTAMP_IMAGE = "null.timestamp";
    public byte _day;
    public BigDecimal _fraction;
    public byte _hour;
    public byte _minute;
    public byte _month;
    public Integer _offset;
    public Precision _precision;
    public byte _second;
    public short _year;
    public static final long MINIMUM_TIMESTAMP_IN_MILLIS = -62135769600000L;
    public static final BigDecimal MINIMUM_TIMESTAMP_IN_MILLIS_DECIMAL = new BigDecimal(MINIMUM_TIMESTAMP_IN_MILLIS);
    public static final long MAXIMUM_TIMESTAMP_IN_MILLIS = 253402300800000L;
    public static final BigDecimal MAXIMUM_ALLOWED_TIMESTAMP_IN_MILLIS_DECIMAL = new BigDecimal(MAXIMUM_TIMESTAMP_IN_MILLIS);
    public static final Integer UNKNOWN_OFFSET = null;
    public static final Integer UTC_OFFSET = 0;
    public static final int HASH_SIGNATURE = 1021414227;
    public static final int[] LEAP_DAYS_IN_MONTH = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int[] NORMAL_DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int LEN_OF_NULL_IMAGE = 14;

    /* JADX INFO: renamed from: com.amazon.ion.Timestamp$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$Timestamp$Precision;

        static {
            int[] iArr = new int[Precision.values().length];
            $SwitchMap$com$amazon$ion$Timestamp$Precision = iArr;
            try {
                iArr[Precision.FRACTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.MINUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Precision {
        YEAR(1),
        MONTH(3),
        DAY(7),
        MINUTE(15),
        SECOND(31),
        FRACTION(31);

        public final int flags;

        Precision(int i) {
            this.flags = i;
        }

        public final boolean alwaysUnknownOffset() {
            return ordinal() <= DAY.ordinal();
        }

        public boolean includes(Precision precision) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[precision.ordinal()]) {
                case 1:
                case 2:
                    return (this.flags & 16) != 0;
                case 3:
                    return (this.flags & 8) != 0;
                case 4:
                    return (this.flags & 4) != 0;
                case 5:
                    return (this.flags & 2) != 0;
                case 6:
                    return (this.flags & 1) != 0;
                default:
                    throw new IllegalStateException("unrecognized precision" + precision);
            }
        }
    }

    public Timestamp(int i) {
        this(Precision.YEAR, i, 0, 0, 0, 0, 0, NO_FRACTIONAL_SECONDS, UNKNOWN_OFFSET, false);
    }

    public static byte checkAndCastDay(int i, int i2, int i3) {
        int iLast_day_in_month = last_day_in_month(i2, i3);
        if (i < 1 || i > iLast_day_in_month) {
            throw new IllegalArgumentException(String.format("Day %s for year %s and month %s must be between 1 and %s inclusive", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(iLast_day_in_month)));
        }
        return (byte) i;
    }

    public static byte checkAndCastHour(int i) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException(String.format("Hour %s must be between 0 and 23 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    public static byte checkAndCastMinute(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException(String.format("Minute %s must be between between 0 and 59 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    public static byte checkAndCastMonth(int i) {
        if (i < 1 || i > 12) {
            throw new IllegalArgumentException(String.format("Month %s must be between 1 and 12 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    public static byte checkAndCastSecond(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException(String.format("Second %s must be between between 0 and 59 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    public static short checkAndCastYear(int i) {
        if (i < 1 || i > 9999) {
            throw new IllegalArgumentException(String.format("Year %s must be between 1 and 9999 inclusive", Integer.valueOf(i)));
        }
        return (short) i;
    }

    public static Precision checkFraction(Precision precision, BigDecimal bigDecimal) {
        if (precision.includes(Precision.SECOND)) {
            if (bigDecimal != null && (bigDecimal.signum() == -1 || BigDecimal.ONE.compareTo(bigDecimal) != 1)) {
                throw new IllegalArgumentException(String.format("Fractional seconds %s must be greater than or equal to 0 and less than 1", bigDecimal));
            }
        } else if (bigDecimal != null) {
            throw new IllegalArgumentException("Fraction must be null for non-second precision: " + bigDecimal);
        }
        return precision;
    }

    @Deprecated
    public static Timestamp createFromUtcFields(Precision precision, int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal, Integer num) {
        return new Timestamp(precision, i, i2, i3, i4, i5, i6, bigDecimal, num, false);
    }

    public static IllegalArgumentException fail(CharSequence charSequence, String str) {
        return new IllegalArgumentException("invalid timestamp: " + str + ": " + ((Object) IonTextUtils.printString(charSequence)));
    }

    public static Timestamp forCalendar(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new Timestamp(calendar);
    }

    public static Timestamp forDateZ(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime(), UTC_OFFSET);
    }

    public static Timestamp forDay(int i, int i2, int i3) {
        return new Timestamp(i, i2, i3);
    }

    public static Timestamp forEpochSecond(long j, int i, Integer num) {
        Timestamp timestamp = new Timestamp(j * 1000, num);
        if (i == 0) {
            return timestamp;
        }
        if (i < 0 || i > 999999999) {
            throw new IllegalArgumentException("nanoOffset must be between 0 and 999,999,999");
        }
        timestamp._fraction = timestamp._fraction.add(BigDecimal.valueOf(i).movePointLeft(9));
        return timestamp;
    }

    public static Timestamp forMillis(long j, Integer num) {
        return new Timestamp(j, num);
    }

    public static Timestamp forMinute(int i, int i2, int i3, int i4, int i5, Integer num) {
        return new Timestamp(i, i2, i3, i4, i5, num);
    }

    public static Timestamp forMonth(int i, int i2) {
        return new Timestamp(i, i2);
    }

    public static Timestamp forSecond(int i, int i2, int i3, int i4, int i5, int i6, Integer num) {
        return new Timestamp(i, i2, i3, i4, i5, i6, num);
    }

    public static Timestamp forSqlTimestampZ(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Timestamp timestamp2 = new Timestamp(timestamp.getTime(), UTC_OFFSET);
        timestamp2._fraction = BigDecimal.valueOf(timestamp.getNanos()).movePointLeft(9);
        return timestamp2;
    }

    public static Timestamp forYear(int i) {
        return new Timestamp(i);
    }

    public static boolean isValidFollowChar(char c) {
        if (c == '\t' || c == '\n' || c == '\r' || c == '\"' || c == ',' || c == '{' || c == '}') {
            return true;
        }
        switch (c) {
            case '\'':
            case '(':
            case ')':
                return true;
            default:
                switch (c) {
                    case '[':
                    case '\\':
                    case ']':
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static int last_day_in_month(int i, int i2) {
        return (i % 4 != 0 || (i % 100 == 0 && i % 400 != 0)) ? NORMAL_DAYS_IN_MONTH[i2] : LEAP_DAYS_IN_MONTH[i2];
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis(), UNKNOWN_OFFSET);
    }

    public static Timestamp nowZ() {
        return new Timestamp(System.currentTimeMillis(), UTC_OFFSET);
    }

    public static void print_digits(Appendable appendable, int i, int i2) throws IOException {
        char[] cArr = new char[i2];
        int i3 = i2;
        while (i3 > 0) {
            i3--;
            int i4 = i / 10;
            cArr[i3] = (char) ((i - (i4 * 10)) + 48);
            i = i4;
        }
        while (i3 > 0) {
            i3--;
            cArr[i3] = '0';
        }
        for (int i5 = 0; i5 < i2; i5++) {
            appendable.append(cArr[i5]);
        }
    }

    public static void print_fractional_digits(Appendable appendable, BigDecimal bigDecimal) throws IOException {
        String plainString = bigDecimal.toPlainString();
        if (plainString.charAt(0) == '0') {
            plainString = plainString.substring(1);
        }
        appendable.append(plainString);
    }

    public static int read_digits(CharSequence charSequence, int i, int i2, int i3, String str) {
        int i4 = i + i2;
        if (charSequence.length() < i4) {
            throw fail(charSequence, str + " requires " + i2 + " digits");
        }
        int i5 = 0;
        while (i < i4) {
            char cCharAt = charSequence.charAt(i);
            if (!Character.isDigit(cCharAt)) {
                StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, " has non-digit character ");
                sbM.append(IonTextUtils.printCodePointAsString(cCharAt));
                throw fail(charSequence, sbM.toString());
            }
            i5 = (i5 * 10) + (cCharAt - '0');
            i++;
        }
        if (i3 != -1) {
            if (i >= charSequence.length() || charSequence.charAt(i) != i3) {
                StringBuilder sbM2 = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, " should end with ");
                sbM2.append(IonTextUtils.printCodePointAsString(i3));
                throw fail(charSequence, sbM2.toString());
            }
        } else if (i < charSequence.length() && Character.isDigit(charSequence.charAt(i))) {
            throw fail(charSequence, str + " requires " + i2 + " digits but has more");
        }
        return i5;
    }

    public static byte requireByte(int i, String str) {
        if (i > 127 || i < -128) {
            throw new IllegalArgumentException(String.format("%s of %d is out of range.", str, Integer.valueOf(i)));
        }
        return (byte) i;
    }

    public static short requireShort(int i, String str) {
        if (i > 32767 || i < -32768) {
            throw new IllegalArgumentException(String.format("%s of %d is out of range.", str, Integer.valueOf(i)));
        }
        return (short) i;
    }

    public static void throwTimestampOutOfRangeError(Number number) {
        throw new IllegalArgumentException("millis: " + number + " is outside of valid the range: from -62135769600000 (0001T), inclusive, to 253402300800000 (10000T) , exclusive");
    }

    public static Timestamp valueOf(CharSequence charSequence) {
        int i;
        int i2;
        int i3;
        int i4;
        Precision precision;
        BigDecimal bigDecimal;
        int i5;
        Integer numValueOf;
        int i6;
        int i7;
        int length = charSequence.length();
        if (length == 0) {
            throw fail(charSequence);
        }
        int i8 = 0;
        if (charSequence.charAt(0) == 'n') {
            int i9 = LEN_OF_NULL_IMAGE;
            if (length < i9 || !NULL_TIMESTAMP_IMAGE.contentEquals(charSequence.subSequence(0, i9))) {
                throw fail(charSequence);
            }
            if (length <= i9 || isValidFollowChar(charSequence.charAt(i9))) {
                return null;
            }
            throw fail(charSequence);
        }
        if (length < 5) {
            throw fail(charSequence, "year is too short (must be at least yyyyT)");
        }
        Precision precision2 = Precision.YEAR;
        int i10 = read_digits(charSequence, 0, 4, -1, "year");
        char cCharAt = charSequence.charAt(4);
        if (cCharAt == 'T') {
            precision = precision2;
            bigDecimal = null;
            i3 = 1;
            i2 = 4;
            i4 = 1;
            i5 = 0;
            i = 0;
        } else {
            if (cCharAt != '-') {
                throw fail(charSequence, "expected \"-\" between year and month, found " + IonTextUtils.printCodePointAsString(cCharAt));
            }
            if (length < 8) {
                throw fail(charSequence, "month is too short (must be yyyy-mmT)");
            }
            Precision precision3 = Precision.MONTH;
            i = 0;
            int i11 = read_digits(charSequence, 5, 2, -1, "month");
            i2 = 7;
            char cCharAt2 = charSequence.charAt(7);
            if (cCharAt2 == 'T') {
                i4 = i11;
                precision = precision3;
                i8 = 0;
                i3 = 1;
            } else {
                if (cCharAt2 != '-') {
                    throw fail(charSequence, "expected \"-\" between month and day, found " + IonTextUtils.printCodePointAsString(cCharAt2));
                }
                if (length < 10) {
                    throw fail(charSequence, "too short for yyyy-mm-dd");
                }
                Precision precision4 = Precision.DAY;
                i3 = read_digits(charSequence, 8, 2, -1, "day");
                if (length != 10) {
                    char cCharAt3 = charSequence.charAt(10);
                    if (cCharAt3 != 'T') {
                        throw fail(charSequence, "expected \"T\" after day, found " + IonTextUtils.printCodePointAsString(cCharAt3));
                    }
                    if (length != 11) {
                        i2 = 16;
                        if (length < 16) {
                            throw fail(charSequence, "too short for yyyy-mm-ddThh:mm");
                        }
                        int i12 = read_digits(charSequence, 11, 2, 58, "hour");
                        int i13 = read_digits(charSequence, 14, 2, -1, "minutes");
                        Precision precision5 = Precision.MINUTE;
                        if (length <= 16 || charSequence.charAt(16) != ':') {
                            i4 = i11;
                            i8 = i13;
                            precision = precision5;
                            bigDecimal = null;
                        } else {
                            i2 = 19;
                            if (length < 19) {
                                throw fail(charSequence, "too short for yyyy-mm-ddThh:mm:ss");
                            }
                            int i14 = read_digits(charSequence, 17, 2, -1, "seconds");
                            Precision precision6 = Precision.SECOND;
                            if (length <= 19 || charSequence.charAt(19) != '.') {
                                i = i14;
                                bigDecimal = null;
                            } else {
                                int i15 = 20;
                                while (length > i15 && Character.isDigit(charSequence.charAt(i15))) {
                                    i15++;
                                }
                                if (i15 <= 20) {
                                    throw fail(charSequence, "must have at least one digit after decimal point");
                                }
                                bigDecimal = new BigDecimal(charSequence.subSequence(19, i15).toString());
                                i2 = i15;
                                i = i14;
                            }
                            i4 = i11;
                            i8 = i13;
                            precision = precision6;
                        }
                        i5 = i12;
                    }
                }
                i4 = i11;
                precision = precision4;
                i8 = 0;
                i2 = 10;
            }
            bigDecimal = null;
            i5 = 0;
        }
        char cCharAt4 = i2 < length ? charSequence.charAt(i2) : '\n';
        if (cCharAt4 == 'Z') {
            i2++;
            numValueOf = 0;
        } else {
            if (cCharAt4 != '+' && cCharAt4 != '-') {
                int i16 = AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[precision.ordinal()];
                if (i16 != 4 && i16 != 5 && i16 != 6) {
                    throw fail(charSequence, "missing local offset");
                }
                i6 = 1;
                numValueOf = null;
                i7 = i2 + i6;
                if (length > i7 || isValidFollowChar(charSequence.charAt(i7))) {
                    return new Timestamp(precision, i10, i4, i3, i5, i8, i, bigDecimal, numValueOf, true);
                }
                throw fail(charSequence, "invalid excess characters");
            }
            if (length < i2 + 5) {
                throw fail(charSequence, "local offset too short");
            }
            int i17 = read_digits(charSequence, i2 + 1, 2, 58, "local offset hours");
            if (i17 < 0 || i17 > 23) {
                throw fail(charSequence, "local offset hours must be between 0 and 23 inclusive");
            }
            int i18 = read_digits(charSequence, i2 + 4, 2, -1, "local offset minutes");
            if (i18 > 59) {
                throw fail(charSequence, "local offset minutes must be between 0 and 59 inclusive");
            }
            i2 += 6;
            int i19 = (i17 * 60) + i18;
            if (cCharAt4 == '-') {
                i19 = -i19;
            }
            numValueOf = (i19 == 0 && cCharAt4 == '-') ? null : Integer.valueOf(i19);
        }
        i6 = 1;
        i7 = i2 + i6;
        if (length > i7) {
        }
        return new Timestamp(precision, i10, i4, i3, i5, i8, i, bigDecimal, numValueOf, true);
    }

    public final Timestamp addDay(int i) {
        return addMillisForPrecision(((long) i) * 86400000, Precision.DAY, false);
    }

    public final Timestamp addHour(int i) {
        return addMillisForPrecision(((long) i) * DateUtils.MILLIS_PER_HOUR, Precision.MINUTE, false);
    }

    public final Timestamp addMillis(long j) {
        BigDecimal bigDecimal;
        return (j != 0 || !this._precision.includes(Precision.SECOND) || (bigDecimal = this._fraction) == null || bigDecimal.scale() < 3) ? addMillisForPrecision(j, Precision.SECOND, true) : this;
    }

    public final Timestamp addMillisForPrecision(long j, Precision precision, boolean z) {
        if (!z && j == 0 && this._precision == precision) {
            return this;
        }
        BigDecimal bigDecimalAdd = make_localtime().getDecimalMillis().add(BigDecimal.valueOf(j));
        if (this._precision.includes(precision)) {
            precision = this._precision;
        }
        Timestamp timestamp = new Timestamp(bigDecimalAdd, precision, this._offset);
        int iMax = z ? 3 : 0;
        BigDecimal bigDecimal = this._fraction;
        if (bigDecimal != null) {
            iMax = Math.max(iMax, bigDecimal.scale());
        }
        BigDecimal bigDecimal2 = timestamp._fraction;
        if (bigDecimal2 != null) {
            timestamp._fraction = iMax == 0 ? null : bigDecimal2.setScale(iMax, RoundingMode.FLOOR);
        }
        Integer num = this._offset;
        if (num != null && num.intValue() != 0) {
            timestamp.apply_offset(this._offset.intValue());
        }
        return timestamp;
    }

    public final Timestamp addMinute(int i) {
        return addMillisForPrecision(((long) i) * 60000, Precision.MINUTE, false);
    }

    public final Timestamp addMonth(int i) {
        if (i == 0 && this._precision.includes(Precision.MONTH)) {
            return this;
        }
        Precision precision = this._precision;
        Precision precision2 = Precision.MONTH;
        if (precision.includes(precision2)) {
            precision2 = this._precision;
        }
        return addMonthForPrecision(i, precision2);
    }

    public final Timestamp addMonthForPrecision(int i, Precision precision) {
        Calendar calendarCalendarValue = calendarValue();
        calendarCalendarValue.add(2, i);
        return new Timestamp(calendarCalendarValue, precision, this._fraction, this._offset);
    }

    public final Timestamp addSecond(int i) {
        return addMillisForPrecision(((long) i) * 1000, Precision.SECOND, false);
    }

    public final Timestamp addYear(int i) {
        if (i == 0) {
            return this;
        }
        Calendar calendarCalendarValue = calendarValue();
        calendarCalendarValue.add(1, i);
        return new Timestamp(calendarCalendarValue, this._precision, this._fraction, this._offset);
    }

    public final Timestamp adjustDay(int i) {
        return adjustMillis(((long) i) * 86400000);
    }

    public final Timestamp adjustHour(int i) {
        return adjustMillis(((long) i) * DateUtils.MILLIS_PER_HOUR);
    }

    public final Timestamp adjustMillis(long j) {
        if (j == 0) {
            return this;
        }
        Timestamp timestampAddMillisForPrecision = addMillisForPrecision(j, this._precision, false);
        timestampAddMillisForPrecision.clearUnusedPrecision();
        if (timestampAddMillisForPrecision._precision.includes(Precision.SECOND)) {
            if (this._fraction == null) {
                timestampAddMillisForPrecision._fraction = null;
                return timestampAddMillisForPrecision;
            }
            if (timestampAddMillisForPrecision._fraction.scale() > this._fraction.scale()) {
                timestampAddMillisForPrecision._fraction = timestampAddMillisForPrecision._fraction.setScale(this._fraction.scale(), RoundingMode.FLOOR);
            }
        }
        return timestampAddMillisForPrecision;
    }

    public final Timestamp adjustMinute(int i) {
        return adjustMillis(((long) i) * 60000);
    }

    public final Timestamp adjustMonth(int i) {
        return i == 0 ? this : addMonthForPrecision(i, this._precision);
    }

    public final Timestamp adjustSecond(int i) {
        return adjustMillis(((long) i) * 1000);
    }

    public final Timestamp adjustYear(int i) {
        return addYear(i);
    }

    public final void apply_offset(int i) {
        if (i == 0) {
            return;
        }
        if (i < -1440 || i > 1440) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("bad offset ", i));
        }
        int i2 = -i;
        int i3 = i2 / 60;
        int i4 = i2 - (i3 * 60);
        if (i2 >= 0) {
            byte b = (byte) (this._minute + i4);
            this._minute = b;
            byte b2 = (byte) (this._hour + i3);
            this._hour = b2;
            if (b > 59) {
                this._minute = (byte) (b - 60);
                this._hour = (byte) (b2 + 1);
            }
            byte b3 = this._hour;
            if (b3 < 24) {
                return;
            }
            this._hour = (byte) (b3 - Ascii.CAN);
            byte b4 = (byte) (this._day + 1);
            this._day = b4;
            if (b4 <= last_day_in_month(this._year, this._month)) {
                return;
            }
            this._day = (byte) 1;
            byte b5 = (byte) (this._month + 1);
            this._month = b5;
            if (b5 <= 12) {
                return;
            }
            this._month = (byte) (b5 - Ascii.FF);
            short s = (short) (this._year + 1);
            this._year = s;
            if (s > 9999) {
                throw new IllegalArgumentException("year exceeds 9999");
            }
            return;
        }
        byte b6 = (byte) (this._minute + i4);
        this._minute = b6;
        byte b7 = (byte) (this._hour + i3);
        this._hour = b7;
        if (b6 < 0) {
            this._minute = (byte) (b6 + 60);
            this._hour = (byte) (b7 - 1);
        }
        byte b8 = this._hour;
        if (b8 >= 0) {
            return;
        }
        this._hour = (byte) (b8 + Ascii.CAN);
        byte b9 = (byte) (this._day - 1);
        this._day = b9;
        if (b9 >= 1) {
            return;
        }
        byte b10 = (byte) (this._month - 1);
        this._month = b10;
        if (b10 >= 1) {
            this._day = (byte) (last_day_in_month(this._year, b10) + b9);
            return;
        }
        byte b11 = (byte) (b10 + Ascii.FF);
        this._month = b11;
        short s2 = (short) (this._year - 1);
        this._year = s2;
        if (s2 < 1) {
            throw new IllegalArgumentException("year is less than 1");
        }
        this._day = (byte) (last_day_in_month(s2, b11) + b9);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Calendar calendarValue() {
        /*
            r6 = this;
            java.util.GregorianCalendar r0 = new java.util.GregorianCalendar
            java.util.TimeZone r1 = com.amazon.ion.impl._Private_Utils.UTC
            r0.<init>(r1)
            long r1 = r6.getMillis()
            java.lang.Integer r3 = r6._offset
            if (r3 == 0) goto L29
            int r4 = r3.intValue()
            if (r4 == 0) goto L29
            int r3 = r3.intValue()
            r4 = 60000(0xea60, float:8.4078E-41)
            int r3 = r3 * r4
            long r4 = (long) r3
            long r1 = r1 + r4
            r0.setTimeInMillis(r1)
            r1 = 15
            r0.set(r1, r3)
            goto L2c
        L29:
            r0.setTimeInMillis(r1)
        L2c:
            int[] r1 = com.amazon.ion.Timestamp.AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision
            com.amazon.ion.Timestamp$Precision r2 = r6._precision
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 14
            r3 = 2
            if (r1 == r3) goto L60
            r4 = 3
            if (r1 == r4) goto L58
            r4 = 4
            if (r1 == r4) goto L4e
            r4 = 5
            if (r1 == r4) goto L4b
            r5 = 6
            if (r1 == r5) goto L48
            goto L67
        L48:
            r0.clear(r3)
        L4b:
            r0.clear(r4)
        L4e:
            r1 = 11
            r0.clear(r1)
            r1 = 12
            r0.clear(r1)
        L58:
            r1 = 13
            r0.clear(r1)
            r0.clear(r2)
        L60:
            java.math.BigDecimal r1 = r6._fraction
            if (r1 != 0) goto L67
            r0.clear(r2)
        L67:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.Timestamp.calendarValue():java.util.Calendar");
    }

    public final void clearUnusedPrecision() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[this._precision.ordinal()];
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i != 6) {
                        return;
                    } else {
                        this._month = (byte) 1;
                    }
                }
                this._day = (byte) 1;
            }
            this._hour = (byte) 0;
            this._minute = (byte) 0;
        }
        this._second = (byte) 0;
        this._fraction = null;
    }

    public Date dateValue() {
        return new Date(getMillis());
    }

    public boolean equals(Object obj) {
        if (obj instanceof Timestamp) {
            return equals((Timestamp) obj);
        }
        return false;
    }

    public final BigDecimal fastRoundZeroFloor(BigDecimal bigDecimal) {
        return isIntegralZero(bigDecimal) ? bigDecimal.signum() < 0 ? BigDecimal.ONE.negate() : BigDecimal.ZERO : bigDecimal.setScale(0, RoundingMode.FLOOR);
    }

    public int getDay() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._day;
    }

    public BigDecimal getDecimalMillis() {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[this._precision.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(Date.UTC(this._year - 1900, this._month - 1, this._day, this._hour, this._minute, this._second));
                BigDecimal bigDecimal = this._fraction;
                return bigDecimal != null ? bigDecimalValueOf.add(bigDecimal.movePointRight(3)) : bigDecimalValueOf;
            default:
                throw new IllegalArgumentException();
        }
    }

    public BigDecimal getDecimalSecond() {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(this._second);
        BigDecimal bigDecimal = this._fraction;
        return bigDecimal != null ? bigDecimalValueOf.add(bigDecimal) : bigDecimalValueOf;
    }

    @Deprecated
    public BigDecimal getFractionalSecond() {
        return this._fraction;
    }

    public int getHour() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._hour;
    }

    public Integer getLocalOffset() {
        return this._offset;
    }

    public long getMillis() {
        long jUTC = Date.UTC(this._year - 1900, this._month - 1, this._day, this._hour, this._minute, this._second);
        BigDecimal bigDecimal = this._fraction;
        if (bigDecimal == null) {
            return jUTC;
        }
        BigDecimal bigDecimalMovePointRight = bigDecimal.movePointRight(3);
        return jUTC + ((long) (isIntegralZero(bigDecimalMovePointRight) ? 0 : bigDecimalMovePointRight.intValue()));
    }

    public int getMinute() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._minute;
    }

    public int getMonth() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._month;
    }

    public Precision getPrecision() {
        return this._precision;
    }

    public int getSecond() {
        return this._second;
    }

    public int getYear() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._year;
    }

    public int getZDay() {
        return this._day;
    }

    public BigDecimal getZDecimalSecond() {
        return getDecimalSecond();
    }

    @Deprecated
    public BigDecimal getZFractionalSecond() {
        return this._fraction;
    }

    public int getZHour() {
        return this._hour;
    }

    public int getZMinute() {
        return this._minute;
    }

    public int getZMonth() {
        return this._month;
    }

    public int getZSecond() {
        return this._second;
    }

    public int getZYear() {
        return this._year;
    }

    public int hashCode() {
        int i = HASH_SIGNATURE * IonTokenConstsX.KW_ALL_BITS;
        BigDecimal bigDecimal = this._fraction;
        int iHashCode = i + (bigDecimal != null ? bigDecimal.hashCode() : 0);
        int i2 = ((((((((((((iHashCode ^ ((iHashCode << 19) ^ (iHashCode >> 13))) * IonTokenConstsX.KW_ALL_BITS) + this._year) * IonTokenConstsX.KW_ALL_BITS) + this._month) * IonTokenConstsX.KW_ALL_BITS) + this._day) * IonTokenConstsX.KW_ALL_BITS) + this._hour) * IonTokenConstsX.KW_ALL_BITS) + this._minute) * IonTokenConstsX.KW_ALL_BITS) + this._second;
        int i3 = i2 ^ ((i2 << 19) ^ (i2 >> 13));
        Precision precision = this._precision;
        if (precision == Precision.FRACTION) {
            precision = Precision.SECOND;
        }
        int iHashCode2 = precision.toString().hashCode() + (i3 * IonTokenConstsX.KW_ALL_BITS);
        int i4 = (((iHashCode2 << 19) ^ (iHashCode2 >> 13)) ^ iHashCode2) * IonTokenConstsX.KW_ALL_BITS;
        Integer num = this._offset;
        int iHashCode3 = i4 + (num != null ? num.hashCode() : 0);
        return iHashCode3 ^ ((iHashCode3 << 19) ^ (iHashCode3 >> 13));
    }

    public final boolean isIntegralZero(BigDecimal bigDecimal) {
        return bigDecimal.signum() == 0 || bigDecimal.scale() < -63 || bigDecimal.precision() - bigDecimal.scale() <= 0;
    }

    public final Timestamp make_localtime() {
        Integer num = this._offset;
        int iIntValue = num != null ? num.intValue() : 0;
        Timestamp timestamp = new Timestamp(this._precision, this._year, this._month, this._day, this._hour, this._minute, this._second, this._fraction, this._offset, false);
        timestamp.apply_offset(-iIntValue);
        return timestamp;
    }

    public void print(Appendable appendable) throws IOException {
        Integer num = this._offset;
        print(appendable, (num == null || num.intValue() == 0) ? this : make_localtime());
    }

    public void printZ(Appendable appendable) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[this._precision.ordinal()]) {
            case 1:
            case 2:
            case 3:
                Timestamp timestampM242clone = m242clone();
                timestampM242clone._offset = UTC_OFFSET;
                timestampM242clone.print(appendable);
                break;
            case 4:
            case 5:
            case 6:
                print(appendable);
                break;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0018. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void set_fields_from_calendar(java.util.Calendar r7, com.amazon.ion.Timestamp.Precision r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.Timestamp.set_fields_from_calendar(java.util.Calendar, com.amazon.ion.Timestamp$Precision, boolean):void");
    }

    public final void set_fields_from_millis(long j) {
        if (j < MINIMUM_TIMESTAMP_IN_MILLIS) {
            throw new IllegalArgumentException("year is less than 1");
        }
        Date date = new Date(j);
        this._minute = requireByte(date.getMinutes(), "Minute");
        this._second = requireByte(date.getSeconds(), "Second");
        this._hour = requireByte(date.getHours(), "Hour");
        this._day = requireByte(date.getDate(), "Day");
        this._month = requireByte(date.getMonth() + 1, "Month");
        int i = -date.getTimezoneOffset();
        if (i >= 0 || MINIMUM_TIMESTAMP_IN_MILLIS - ((long) i) <= j) {
            this._year = requireShort(date.getYear() + 1900, "Year");
        } else {
            this._year = (short) 0;
        }
        apply_offset(i);
        this._year = checkAndCastYear(this._year);
        byte bCheckAndCastMonth = checkAndCastMonth(this._month);
        this._month = bCheckAndCastMonth;
        this._day = checkAndCastDay(this._day, this._year, bCheckAndCastMonth);
        this._hour = checkAndCastHour(this._hour);
        this._minute = checkAndCastMinute(this._minute);
        this._second = checkAndCastSecond(this._second);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        try {
            print(sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Exception printing to StringBuilder", e);
        }
    }

    public String toZString() {
        StringBuilder sb = new StringBuilder(32);
        try {
            printZ(sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Exception printing to StringBuilder", e);
        }
    }

    public Timestamp withLocalOffset(Integer num) {
        Precision precision = this._precision;
        return (precision.alwaysUnknownOffset() || _Private_Utils.safeEquals(num, this._offset)) ? this : createFromUtcFields(precision, this._year, this._month, this._day, this._hour, this._minute, this._second, this._fraction, num);
    }

    public Timestamp(int i, int i2) {
        this(Precision.MONTH, i, i2, 0, 0, 0, 0, NO_FRACTIONAL_SECONDS, UNKNOWN_OFFSET, false);
    }

    public static Timestamp forMillis(BigDecimal bigDecimal, Integer num) {
        return new Timestamp(bigDecimal, num);
    }

    public static Timestamp forSecond(int i, int i2, int i3, int i4, int i5, BigDecimal bigDecimal, Integer num) {
        int iIntValue = bigDecimal.intValue();
        return new Timestamp(Precision.SECOND, i, i2, i3, i4, i5, iIntValue, bigDecimal.subtract(BigDecimal.valueOf(iIntValue)), num, true);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Timestamp m242clone() {
        return new Timestamp(this._precision, this._year, this._month, this._day, this._hour, this._minute, this._second, this._fraction, this._offset, false);
    }

    @Override // java.lang.Comparable
    public int compareTo(Timestamp timestamp) {
        long millis = getMillis();
        long millis2 = timestamp.getMillis();
        if (millis != millis2) {
            return millis < millis2 ? -1 : 1;
        }
        BigDecimal bigDecimal = this._fraction;
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }
        BigDecimal bigDecimal2 = timestamp._fraction;
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        return bigDecimal.compareTo(bigDecimal2);
    }

    @Deprecated
    public Timestamp(int i, int i2, int i3) {
        this(Precision.DAY, i, i2, i3, 0, 0, 0, NO_FRACTIONAL_SECONDS, UNKNOWN_OFFSET, false);
    }

    public static IllegalArgumentException fail(CharSequence charSequence) {
        return new IllegalArgumentException("invalid timestamp: " + ((Object) IonTextUtils.printString(charSequence)));
    }

    public boolean equals(Timestamp timestamp) {
        if (this == timestamp) {
            return true;
        }
        if (timestamp == null) {
            return false;
        }
        Precision precision = this._precision;
        Precision precision2 = Precision.SECOND;
        Precision precision3 = precision.includes(precision2) ? precision2 : this._precision;
        if (!timestamp._precision.includes(precision2)) {
            precision2 = timestamp._precision;
        }
        if (precision3 != precision2) {
            return false;
        }
        Integer num = this._offset;
        if (num == null) {
            if (timestamp._offset != null) {
                return false;
            }
        } else if (timestamp._offset == null) {
            return false;
        }
        if (this._year != timestamp._year || this._month != timestamp._month || this._day != timestamp._day || this._hour != timestamp._hour || this._minute != timestamp._minute || this._second != timestamp._second) {
            return false;
        }
        if (num != null && num.intValue() != timestamp._offset.intValue()) {
            return false;
        }
        BigDecimal bigDecimal = this._fraction;
        if ((bigDecimal != null && timestamp._fraction == null) || (bigDecimal == null && timestamp._fraction != null)) {
            return false;
        }
        if (bigDecimal == null && timestamp._fraction == null) {
            return true;
        }
        return bigDecimal.equals(timestamp._fraction);
    }

    @Deprecated
    public Timestamp(int i, int i2, int i3, int i4, int i5, Integer num) {
        this(Precision.MINUTE, i, i2, i3, i4, i5, 0, NO_FRACTIONAL_SECONDS, num, true);
    }

    public static void print(Appendable appendable, Timestamp timestamp) throws IOException {
        if (timestamp == null) {
            appendable.append(NULL_TIMESTAMP_IMAGE);
            return;
        }
        print_digits(appendable, timestamp._year, 4);
        if (timestamp._precision == Precision.YEAR) {
            appendable.append("T");
            return;
        }
        appendable.append("-");
        print_digits(appendable, timestamp._month, 2);
        if (timestamp._precision == Precision.MONTH) {
            appendable.append("T");
            return;
        }
        appendable.append("-");
        print_digits(appendable, timestamp._day, 2);
        if (timestamp._precision == Precision.DAY) {
            return;
        }
        appendable.append("T");
        print_digits(appendable, timestamp._hour, 2);
        appendable.append(":");
        print_digits(appendable, timestamp._minute, 2);
        if (timestamp._precision == Precision.SECOND) {
            appendable.append(":");
            print_digits(appendable, timestamp._second, 2);
            BigDecimal bigDecimal = timestamp._fraction;
            if (bigDecimal != null) {
                print_fractional_digits(appendable, bigDecimal);
            }
        }
        Integer num = timestamp._offset;
        if (num != UNKNOWN_OFFSET) {
            int iIntValue = num.intValue();
            if (iIntValue == 0) {
                appendable.append('Z');
                return;
            }
            if (iIntValue < 0) {
                iIntValue = -iIntValue;
                appendable.append('-');
            } else {
                appendable.append('+');
            }
            int i = iIntValue / 60;
            print_digits(appendable, i, 2);
            appendable.append(":");
            print_digits(appendable, iIntValue - (i * 60), 2);
            return;
        }
        appendable.append("-00:00");
    }

    @Deprecated
    public Timestamp(int i, int i2, int i3, int i4, int i5, int i6, Integer num) {
        this(Precision.SECOND, i, i2, i3, i4, i5, i6, NO_FRACTIONAL_SECONDS, num, true);
    }

    @Deprecated
    public Timestamp(int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal, Integer num) {
        this(Precision.SECOND, i, i2, i3, i4, i5, i6, bigDecimal, num, true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Timestamp(com.amazon.ion.Timestamp.Precision r4, int r5, int r6, int r7, int r8, int r9, int r10, java.math.BigDecimal r11, java.lang.Integer r12, boolean r13) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 1
            r3._month = r0
            r3._day = r0
            int[] r1 = com.amazon.ion.Timestamp.AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision
            int r2 = r4.ordinal()
            r1 = r1[r2]
            r2 = 0
            switch(r1) {
                case 1: goto L1e;
                case 2: goto L1e;
                case 3: goto L39;
                case 4: goto L47;
                case 5: goto L1c;
                case 6: goto L4e;
                default: goto L14;
            }
        L14:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "invalid Precision passed to constructor"
            r4.<init>(r5)
            throw r4
        L1c:
            r0 = 0
            goto L47
        L1e:
            if (r11 == 0) goto L30
            java.math.BigDecimal r1 = java.math.BigDecimal.ZERO
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L29
            goto L30
        L29:
            java.math.BigDecimal r11 = r11.abs()
            r3._fraction = r11
            goto L33
        L30:
            r11 = 0
            r3._fraction = r11
        L33:
            byte r10 = checkAndCastSecond(r10)
            r3._second = r10
        L39:
            byte r9 = checkAndCastMinute(r9)
            r3._minute = r9
            byte r8 = checkAndCastHour(r8)
            r3._hour = r8
            r3._offset = r12
        L47:
            byte r8 = checkAndCastMonth(r6)
            r3._month = r8
            r2 = r0
        L4e:
            short r8 = checkAndCastYear(r5)
            r3._year = r8
            if (r2 == 0) goto L5c
            byte r5 = checkAndCastDay(r7, r5, r6)
            r3._day = r5
        L5c:
            java.math.BigDecimal r5 = r3._fraction
            checkFraction(r4, r5)
            r3._precision = r4
            if (r13 == 0) goto L6e
            if (r12 == 0) goto L6e
            int r4 = r12.intValue()
            r3.apply_offset(r4)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.Timestamp.<init>(com.amazon.ion.Timestamp$Precision, int, int, int, int, int, int, java.math.BigDecimal, java.lang.Integer, boolean):void");
    }

    @Deprecated
    public Timestamp(Calendar calendar) {
        Precision precision;
        this._month = (byte) 1;
        this._day = (byte) 1;
        if (!calendar.isSet(14) && !calendar.isSet(13)) {
            if (!calendar.isSet(11) && !calendar.isSet(12)) {
                if (calendar.isSet(5)) {
                    precision = Precision.DAY;
                } else if (calendar.isSet(2)) {
                    precision = Precision.MONTH;
                } else if (calendar.isSet(1)) {
                    precision = Precision.YEAR;
                } else {
                    throw new IllegalArgumentException("Calendar has no fields set");
                }
            } else {
                precision = Precision.MINUTE;
            }
        } else {
            precision = Precision.SECOND;
        }
        set_fields_from_calendar(calendar, precision, true);
    }

    public Timestamp(Calendar calendar, Precision precision, BigDecimal bigDecimal, Integer num) {
        this._month = (byte) 1;
        this._day = (byte) 1;
        set_fields_from_calendar(calendar, precision, false);
        this._fraction = bigDecimal;
        if (num != null) {
            this._offset = num;
            apply_offset(num.intValue());
        }
    }

    public Timestamp(BigDecimal bigDecimal, Precision precision, Integer num) {
        this._month = (byte) 1;
        this._day = (byte) 1;
        if (bigDecimal.compareTo(MINIMUM_TIMESTAMP_IN_MILLIS_DECIMAL) >= 0 && MAXIMUM_ALLOWED_TIMESTAMP_IN_MILLIS_DECIMAL.compareTo(bigDecimal) > 0) {
            set_fields_from_millis(isIntegralZero(bigDecimal) ? 0L : bigDecimal.longValue());
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[precision.ordinal()];
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i == 6) {
                            this._month = (byte) 1;
                        }
                    }
                    this._day = (byte) 1;
                }
                this._hour = (byte) 0;
                this._minute = (byte) 0;
                this._second = (byte) 0;
            } else {
                this._second = (byte) 0;
            }
            this._offset = num;
            if (precision.includes(Precision.SECOND) && bigDecimal.scale() > -3) {
                BigDecimal bigDecimalMovePointLeft = bigDecimal.movePointLeft(3);
                this._fraction = bigDecimalMovePointLeft.subtract(fastRoundZeroFloor(bigDecimalMovePointLeft));
            } else {
                this._fraction = null;
            }
            checkFraction(precision, this._fraction);
            this._precision = precision;
            return;
        }
        throwTimestampOutOfRangeError(bigDecimal);
        throw null;
    }

    @Deprecated
    public Timestamp(BigDecimal bigDecimal, Integer num) {
        this._month = (byte) 1;
        this._day = (byte) 1;
        if (bigDecimal != null) {
            if (bigDecimal.compareTo(MINIMUM_TIMESTAMP_IN_MILLIS_DECIMAL) >= 0 && MAXIMUM_ALLOWED_TIMESTAMP_IN_MILLIS_DECIMAL.compareTo(bigDecimal) >= 0) {
                set_fields_from_millis(isIntegralZero(bigDecimal) ? 0L : bigDecimal.longValue());
                if (bigDecimal.scale() <= -3) {
                    this._precision = Precision.SECOND;
                    this._fraction = null;
                } else {
                    BigDecimal bigDecimalMovePointLeft = bigDecimal.movePointLeft(3);
                    BigDecimal bigDecimalSubtract = bigDecimalMovePointLeft.subtract(fastRoundZeroFloor(bigDecimalMovePointLeft));
                    this._fraction = bigDecimalSubtract;
                    Precision precision = Precision.SECOND;
                    checkFraction(precision, bigDecimalSubtract);
                    this._precision = precision;
                }
                this._offset = num;
                return;
            }
            throwTimestampOutOfRangeError(bigDecimal);
            throw null;
        }
        throw new NullPointerException("millis is null");
    }

    @Deprecated
    public Timestamp(long j, Integer num) {
        this._month = (byte) 1;
        this._day = (byte) 1;
        if (j >= MINIMUM_TIMESTAMP_IN_MILLIS && j < MAXIMUM_TIMESTAMP_IN_MILLIS) {
            set_fields_from_millis(j);
            BigDecimal bigDecimalMovePointLeft = BigDecimal.valueOf(j).movePointLeft(3);
            BigDecimal bigDecimalSubtract = bigDecimalMovePointLeft.subtract(bigDecimalMovePointLeft.setScale(0, RoundingMode.FLOOR));
            this._fraction = bigDecimalSubtract;
            Precision precision = Precision.SECOND;
            checkFraction(precision, bigDecimalSubtract);
            this._precision = precision;
            this._offset = num;
            return;
        }
        throwTimestampOutOfRangeError(Long.valueOf(j));
        throw null;
    }
}
