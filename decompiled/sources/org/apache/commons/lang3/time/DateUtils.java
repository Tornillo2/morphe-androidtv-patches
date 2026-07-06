package org.apache.commons.lang3.time;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    public static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DateIterator implements Iterator<Calendar> {
        public final Calendar endFinal;
        public final Calendar spot;

        public DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            calendar.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public Calendar next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return (Calendar) this.spot.clone();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ModifyType {
        TRUNCATE,
        ROUND,
        CEILING
    }

    public static Date add(Date date, int i, int i2) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(i, i2);
        return calendar.getTime();
    }

    public static Date addDays(Date date, int i) {
        return add(date, 5, i);
    }

    public static Date addHours(Date date, int i) {
        return add(date, 11, i);
    }

    public static Date addMilliseconds(Date date, int i) {
        return add(date, 14, i);
    }

    public static Date addMinutes(Date date, int i) {
        return add(date, 12, i);
    }

    public static Date addMonths(Date date, int i) {
        return add(date, 2, i);
    }

    public static Date addSeconds(Date date, int i) {
        return add(date, 13, i);
    }

    public static Date addWeeks(Date date, int i) {
        return add(date, 3, i);
    }

    public static Date addYears(Date date, int i) {
        return add(date, 1, i);
    }

    public static Date ceiling(Date date, int i) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, ModifyType.CEILING);
        return calendar.getTime();
    }

    public static long getFragment(Date date, int i, TimeUnit timeUnit) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, i, timeUnit);
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, TimeUnit.SECONDS);
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(11) == calendar2.get(11) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Iterator<Calendar> iterator(Date date, int i) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return iterator(calendar, i);
    }

    public static void modify(Calendar calendar, int i, ModifyType modifyType) {
        int i2;
        boolean z;
        if (calendar.get(1) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }
        if (i == 14) {
            return;
        }
        Date time = calendar.getTime();
        long time2 = time.getTime();
        int i3 = calendar.get(14);
        ModifyType modifyType2 = ModifyType.TRUNCATE;
        if (modifyType2 == modifyType || i3 < 500) {
            time2 -= (long) i3;
        }
        boolean z2 = i == 13;
        int i4 = calendar.get(13);
        if (!z2 && (modifyType2 == modifyType || i4 < 30)) {
            time2 -= ((long) i4) * 1000;
        }
        if (i == 12) {
            z2 = true;
        }
        int i5 = calendar.get(12);
        if (!z2 && (modifyType2 == modifyType || i5 < 30)) {
            time2 -= ((long) i5) * 60000;
        }
        if (time.getTime() != time2) {
            time.setTime(time2);
            calendar.setTime(time);
        }
        boolean z3 = false;
        for (int[] iArr : fields) {
            for (int i6 : iArr) {
                if (i6 == i) {
                    if (modifyType == ModifyType.CEILING || (modifyType == ModifyType.ROUND && z3)) {
                        if (i == 1001) {
                            if (calendar.get(5) == 1) {
                                calendar.add(5, 15);
                                return;
                            } else {
                                calendar.add(5, -15);
                                calendar.add(2, 1);
                                return;
                            }
                        }
                        if (i != 9) {
                            calendar.add(iArr[0], 1);
                            return;
                        } else if (calendar.get(11) == 0) {
                            calendar.add(11, 12);
                            return;
                        } else {
                            calendar.add(11, -12);
                            calendar.add(5, 1);
                            return;
                        }
                    }
                    return;
                }
            }
            if (i != 9) {
                if (i == 1001 && iArr[0] == 5) {
                    int i7 = calendar.get(5);
                    int i8 = i7 - 1;
                    if (i8 >= 15) {
                        i8 = i7 - 16;
                    }
                    int i9 = i8;
                    z3 = i8 > 7;
                    i2 = i9;
                    z = true;
                }
                i2 = 0;
                z = false;
            } else {
                if (iArr[0] == 11) {
                    int i10 = calendar.get(11);
                    if (i10 >= 12) {
                        i10 -= 12;
                    }
                    int i11 = i10;
                    z3 = i11 >= 6;
                    i2 = i11;
                    z = true;
                }
                i2 = 0;
                z = false;
            }
            if (!z) {
                int actualMinimum = calendar.getActualMinimum(iArr[0]);
                int actualMaximum = calendar.getActualMaximum(iArr[0]);
                int i12 = calendar.get(iArr[0]) - actualMinimum;
                z3 = i12 > (actualMaximum - actualMinimum) / 2;
                i2 = i12;
            }
            if (i2 != 0) {
                int i13 = iArr[0];
                calendar.set(i13, calendar.get(i13) - i2);
            }
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("The field ", i, " is not supported"));
    }

    public static Date parseDate(String str, Locale locale, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, locale, strArr, true);
    }

    public static Date parseDateStrictly(String str, Locale locale, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, locale, strArr, false);
    }

    public static Date parseDateWithLeniency(String str, Locale locale, String[] strArr, boolean z) throws ParseException {
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        TimeZone timeZone = TimeZone.getDefault();
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        calendar.setLenient(z);
        for (String str2 : strArr) {
            FastDateParser fastDateParser = new FastDateParser(str2, timeZone, locale, null);
            calendar.clear();
            try {
                if (fastDateParser.parse(str, parsePosition, calendar) && parsePosition.getIndex() == str.length()) {
                    return calendar.getTime();
                }
            } catch (IllegalArgumentException unused) {
            }
            parsePosition.setIndex(0);
        }
        throw new ParseException("Unable to parse the date: ".concat(str), -1);
    }

    public static Date round(Date date, int i) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, ModifyType.ROUND);
        return calendar.getTime();
    }

    public static Date set(Date date, int i, int i2) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(i, i2);
        return calendar.getTime();
    }

    public static Date setDays(Date date, int i) {
        return set(date, 5, i);
    }

    public static Date setHours(Date date, int i) {
        return set(date, 11, i);
    }

    public static Date setMilliseconds(Date date, int i) {
        return set(date, 14, i);
    }

    public static Date setMinutes(Date date, int i) {
        return set(date, 12, i);
    }

    public static Date setMonths(Date date, int i) {
        return set(date, 2, i);
    }

    public static Date setSeconds(Date date, int i) {
        return set(date, 13, i);
    }

    public static Date setYears(Date date, int i) {
        return set(date, 1, i);
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date truncate(Date date, int i) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, ModifyType.TRUNCATE);
        return calendar.getTime();
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).compareTo(truncate(calendar2, i));
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static void validateDateNotNull(Date date) {
        Validate.isTrue(date != null, "The date must not be null", new Object[0]);
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.SECONDS);
    }

    public static Date parseDate(String str, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, null, strArr, true);
    }

    public static Date parseDateStrictly(String str, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, null, strArr, false);
    }

    public static boolean truncatedEquals(Date date, Date date2, int i) {
        return truncatedCompareTo(date, date2, i) == 0;
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static Calendar toCalendar(Date date, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        return calendar;
    }

    public static int truncatedCompareTo(Date date, Date date2, int i) {
        return truncate(date, i).compareTo(truncate(date2, i));
    }

    public static long getFragment(Calendar calendar, int i, TimeUnit timeUnit) {
        long jConvert;
        if (calendar != null) {
            TimeUnit timeUnit2 = TimeUnit.DAYS;
            int i2 = timeUnit == timeUnit2 ? 0 : 1;
            if (i != 1) {
                jConvert = i != 2 ? 0L : timeUnit.convert(calendar.get(5) - i2, timeUnit2);
            } else {
                jConvert = timeUnit.convert(calendar.get(6) - i2, timeUnit2);
            }
            if (i == 1 || i == 2 || i == 5 || i == 6) {
                jConvert += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
            } else {
                switch (i) {
                    case 11:
                        break;
                    case 12:
                        jConvert += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
                    case 13:
                        return timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS) + jConvert;
                    case 14:
                        return jConvert;
                    default:
                        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("The fragment ", i, " is not supported"));
                }
            }
            jConvert += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
            jConvert += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
            return timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS) + jConvert;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f A[LOOP:0: B:29:0x0069->B:31:0x006f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0079 A[LOOP:1: B:32:0x0073->B:34:0x0079, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Iterator<java.util.Calendar> iterator(java.util.Calendar r8, int r9) {
        /*
            if (r8 == 0) goto L83
            r0 = -1
            r1 = 5
            r2 = 2
            r3 = 1
            r4 = 7
            switch(r9) {
                case 1: goto L34;
                case 2: goto L34;
                case 3: goto L34;
                case 4: goto L34;
                case 5: goto L18;
                case 6: goto L18;
                default: goto La;
            }
        La:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "The range style "
            java.lang.String r1 = " is not valid."
            java.lang.String r9 = androidx.collection.ObjectListKt$$ExternalSyntheticOutline1.m(r0, r9, r1)
            r8.<init>(r9)
            throw r8
        L18:
            java.util.Calendar r8 = truncate(r8, r2)
            java.lang.Object r5 = r8.clone()
            java.util.Calendar r5 = (java.util.Calendar) r5
            r5.add(r2, r3)
            r5.add(r1, r0)
            r6 = 6
            if (r9 != r6) goto L2f
            r6 = r5
            r5 = r8
        L2d:
            r8 = 1
            goto L59
        L2f:
            r6 = r5
            r2 = 1
            r5 = r8
            r8 = 7
            goto L59
        L34:
            java.util.Calendar r5 = truncate(r8, r1)
            java.util.Calendar r6 = truncate(r8, r1)
            if (r9 == r2) goto L2d
            r2 = 3
            if (r9 == r2) goto L53
            r7 = 4
            if (r9 == r7) goto L47
            r8 = 7
            r2 = 1
            goto L59
        L47:
            int r9 = r8.get(r4)
            int r9 = r9 - r2
            int r8 = r8.get(r4)
            int r8 = r8 + r2
            r2 = r9
            goto L59
        L53:
            int r2 = r8.get(r4)
            int r8 = r2 + (-1)
        L59:
            if (r2 >= r3) goto L5d
            int r2 = r2 + 7
        L5d:
            if (r2 <= r4) goto L61
            int r2 = r2 + (-7)
        L61:
            if (r8 >= r3) goto L65
            int r8 = r8 + 7
        L65:
            if (r8 <= r4) goto L69
            int r8 = r8 + (-7)
        L69:
            int r9 = r5.get(r4)
            if (r9 == r2) goto L73
            r5.add(r1, r0)
            goto L69
        L73:
            int r9 = r6.get(r4)
            if (r9 == r8) goto L7d
            r6.add(r1, r3)
            goto L73
        L7d:
            org.apache.commons.lang3.time.DateUtils$DateIterator r8 = new org.apache.commons.lang3.time.DateUtils$DateIterator
            r8.<init>(r5, r6)
            return r8
        L83:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "The date must not be null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.iterator(java.util.Calendar, int):java.util.Iterator");
    }

    public static Calendar ceiling(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, ModifyType.CEILING);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, ModifyType.ROUND);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, ModifyType.TRUNCATE);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static Date ceiling(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return ceiling((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return ceiling((Calendar) obj, i).getTime();
            }
            throw new ClassCastException("Could not find ceiling of for type: " + obj.getClass());
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date round(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return round((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return round((Calendar) obj, i).getTime();
            }
            throw new ClassCastException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Could not round ", obj));
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date truncate(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return truncate((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return truncate((Calendar) obj, i).getTime();
            }
            throw new ClassCastException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Could not truncate ", obj));
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Iterator<?> iterator(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return iterator((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return iterator((Calendar) obj, i);
            }
            throw new ClassCastException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Could not iterate based on ", obj));
        }
        throw new IllegalArgumentException("The date must not be null");
    }
}
