package org.apache.commons.lang3.time;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import androidx.core.graphics.PaintCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'";
    public static final Object y = "y";
    public static final Object M = "M";
    public static final Object d = "d";
    public static final Object H = "H";
    public static final Object m = PaintCompat.EM_STRING;
    public static final Object s = CmcdData.Factory.STREAMING_FORMAT_SS;
    public static final Object S = ExifInterface.LATITUDE_SOUTH;

    public static String format(Token[] tokenArr, long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z) {
        int i;
        int i2;
        Token[] tokenArr2 = tokenArr;
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        boolean z2 = false;
        for (int length = tokenArr2.length; i3 < length; length = i2) {
            Token token = tokenArr2[i3];
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuilder) {
                sb.append(value.toString());
                i2 = length;
                i = i3;
            } else {
                if (value.equals(y)) {
                    sb.append(paddedValue(j, z, count));
                } else if (value.equals(M)) {
                    sb.append(paddedValue(j2, z, count));
                } else if (value.equals(d)) {
                    i = i3;
                    sb.append(paddedValue(j3, z, count));
                    i2 = length;
                    z2 = false;
                } else {
                    i = i3;
                    if (value.equals(H)) {
                        i2 = length;
                        sb.append(paddedValue(j4, z, count));
                    } else {
                        i2 = length;
                        if (value.equals(m)) {
                            sb.append(paddedValue(j5, z, count));
                        } else {
                            if (value.equals(s)) {
                                sb.append(paddedValue(j6, z, count));
                                z2 = true;
                            } else if (value.equals(S)) {
                                if (z2) {
                                    sb.append(paddedValue(j7, true, z ? Math.max(3, count) : 3));
                                } else {
                                    sb.append(paddedValue(j7, z, count));
                                }
                                z2 = false;
                            }
                            i3 = i + 1;
                            tokenArr2 = tokenArr;
                        }
                    }
                    z2 = false;
                    i3 = i + 1;
                    tokenArr2 = tokenArr;
                }
                i2 = length;
                i = i3;
                z2 = false;
            }
            i3 = i + 1;
            tokenArr2 = tokenArr;
        }
        return sb.toString();
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "HH:mm:ss.SSS", true);
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String duration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'", true);
        if (z) {
            duration = RemoteInput$$ExternalSyntheticOutline0.m(StringUtils.SPACE, duration);
            String strReplace = StringUtils.replace(duration, " 0 days", "", 1, false);
            if (strReplace.length() != duration.length()) {
                String strReplace2 = StringUtils.replace(strReplace, " 0 hours", "", 1, false);
                if (strReplace2.length() != strReplace.length()) {
                    duration = StringUtils.replace(strReplace2, " 0 minutes", "", 1, false);
                    if (duration.length() != duration.length()) {
                        duration = StringUtils.replace(duration, " 0 seconds", "", 1, false);
                    }
                } else {
                    duration = strReplace;
                }
            }
            if (!duration.isEmpty()) {
                duration = duration.substring(1);
            }
        }
        if (z2) {
            String strReplace3 = StringUtils.replace(duration, " 0 seconds", "", 1, false);
            if (strReplace3.length() != duration.length()) {
                duration = StringUtils.replace(strReplace3, " 0 minutes", "", 1, false);
                if (duration.length() != strReplace3.length()) {
                    String strReplace4 = StringUtils.replace(duration, " 0 hours", "", 1, false);
                    if (strReplace4.length() != duration.length()) {
                        duration = StringUtils.replace(strReplace4, " 0 days", "", 1, false);
                    }
                } else {
                    duration = strReplace3;
                }
            }
        }
        return StringUtils.replace(StringUtils.replace(StringUtils.replace(StringUtils.replace(RemoteInput$$ExternalSyntheticOutline0.m(StringUtils.SPACE, duration), " 1 seconds", " 1 second", 1, false), " 1 minutes", " 1 minute", 1, false), " 1 hours", " 1 hour", 1, false), " 1 days", " 1 day", 1, false).trim();
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.apache.commons.lang3.time.DurationFormatUtils.Token[] lexx(java.lang.String r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r9.length()
            r0.<init>(r1)
            r1 = 0
            r2 = 0
            r5 = r2
            r6 = r5
            r3 = 0
            r4 = 0
        Lf:
            int r7 = r9.length()
            if (r3 >= r7) goto L9f
            char r7 = r9.charAt(r3)
            r8 = 39
            if (r4 == 0) goto L24
            if (r7 == r8) goto L24
            r5.append(r7)
            goto L9b
        L24:
            if (r7 == r8) goto L6b
            r8 = 72
            if (r7 == r8) goto L68
            r8 = 77
            if (r7 == r8) goto L65
            r8 = 83
            if (r7 == r8) goto L62
            r8 = 100
            if (r7 == r8) goto L5f
            r8 = 109(0x6d, float:1.53E-43)
            if (r7 == r8) goto L5c
            r8 = 115(0x73, float:1.61E-43)
            if (r7 == r8) goto L59
            r8 = 121(0x79, float:1.7E-43)
            if (r7 == r8) goto L56
            if (r5 != 0) goto L51
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            org.apache.commons.lang3.time.DurationFormatUtils$Token r8 = new org.apache.commons.lang3.time.DurationFormatUtils$Token
            r8.<init>(r5)
            r0.add(r8)
        L51:
            r5.append(r7)
        L54:
            r7 = r2
            goto L80
        L56:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.y
            goto L80
        L59:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.s
            goto L80
        L5c:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.m
            goto L80
        L5f:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.d
            goto L80
        L62:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.S
            goto L80
        L65:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.M
            goto L80
        L68:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.H
            goto L80
        L6b:
            if (r4 == 0) goto L71
            r5 = r2
            r7 = r5
            r4 = 0
            goto L80
        L71:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            org.apache.commons.lang3.time.DurationFormatUtils$Token r4 = new org.apache.commons.lang3.time.DurationFormatUtils$Token
            r4.<init>(r5)
            r0.add(r4)
            r4 = 1
            goto L54
        L80:
            if (r7 == 0) goto L9b
            if (r6 == 0) goto L92
            java.lang.Object r5 = r6.getValue()
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L92
            r6.increment()
            goto L9a
        L92:
            org.apache.commons.lang3.time.DurationFormatUtils$Token r6 = new org.apache.commons.lang3.time.DurationFormatUtils$Token
            r6.<init>(r7)
            r0.add(r6)
        L9a:
            r5 = r2
        L9b:
            int r3 = r3 + 1
            goto Lf
        L9f:
            if (r4 != 0) goto Lae
            int r9 = r0.size()
            org.apache.commons.lang3.time.DurationFormatUtils$Token[] r9 = new org.apache.commons.lang3.time.DurationFormatUtils.Token[r9]
            java.lang.Object[] r9 = r0.toArray(r9)
            org.apache.commons.lang3.time.DurationFormatUtils$Token[] r9 = (org.apache.commons.lang3.time.DurationFormatUtils.Token[]) r9
            return r9
        Lae:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unmatched quote in format: "
            java.lang.String r9 = r1.concat(r9)
            r0.<init>(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DurationFormatUtils.lexx(java.lang.String):org.apache.commons.lang3.time.DurationFormatUtils$Token[]");
    }

    public static String paddedValue(long j, boolean z, int i) {
        String string = Long.toString(j);
        return z ? StringUtils.leftPad(string, i, '0') : string;
    }

    public static String formatDuration(long j, String str, boolean z) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        Validate.inclusiveBetween(0L, Long.MAX_VALUE, j, "durationMillis must not be negative");
        Token[] tokenArrLexx = lexx(str);
        if (Token.containsTokenWithValue(tokenArrLexx, d)) {
            j3 = j / 86400000;
            j2 = j - (86400000 * j3);
        } else {
            j2 = j;
            j3 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, H)) {
            long j7 = j2 / DateUtils.MILLIS_PER_HOUR;
            j2 -= DateUtils.MILLIS_PER_HOUR * j7;
            j4 = j7;
        } else {
            j4 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, m)) {
            long j8 = j2 / 60000;
            j2 -= 60000 * j8;
            j5 = j8;
        } else {
            j5 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, s)) {
            j6 = j2 / 1000;
            j2 -= 1000 * j6;
        } else {
            j6 = 0;
        }
        return format(tokenArrLexx, 0L, 0L, j3, j4, j5, j6, j2, z);
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int i = 0;
        Validate.isTrue(j <= j2, "startMillis must not be greater than endMillis", new Object[0]);
        Token[] tokenArrLexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j2));
        int i2 = calendar2.get(14) - calendar.get(14);
        int i3 = calendar2.get(13) - calendar.get(13);
        int i4 = calendar2.get(12) - calendar.get(12);
        int i5 = calendar2.get(11) - calendar.get(11);
        int actualMaximum = calendar2.get(5) - calendar.get(5);
        int i6 = calendar2.get(2) - calendar.get(2);
        int i7 = calendar2.get(1) - calendar.get(1);
        while (i2 < 0) {
            i2 += 1000;
            i3--;
        }
        while (i3 < 0) {
            i3 += 60;
            i4--;
        }
        while (i4 < 0) {
            i4 += 60;
            i5--;
        }
        while (i5 < 0) {
            i5 += 24;
            actualMaximum--;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, M)) {
            while (actualMaximum < 0) {
                actualMaximum += calendar.getActualMaximum(5);
                i6--;
                calendar.add(2, 1);
            }
            while (i6 < 0) {
                i6 += 12;
                i7--;
            }
            if (!Token.containsTokenWithValue(tokenArrLexx, y) && i7 != 0) {
                while (i7 != 0) {
                    i6 += i7 * 12;
                    i7 = 0;
                }
            }
        } else {
            if (!Token.containsTokenWithValue(tokenArrLexx, y)) {
                int i8 = calendar2.get(1);
                if (i6 < 0) {
                    i8--;
                }
                while (calendar.get(1) != i8) {
                    int actualMaximum2 = (calendar.getActualMaximum(6) - calendar.get(6)) + actualMaximum;
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum2++;
                    }
                    calendar.add(1, 1);
                    actualMaximum = calendar.get(6) + actualMaximum2;
                }
                i7 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                actualMaximum += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            i6 = 0;
            while (actualMaximum < 0) {
                actualMaximum += calendar.getActualMaximum(5);
                i6--;
                calendar.add(2, 1);
            }
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, d)) {
            i5 += actualMaximum * 24;
            actualMaximum = 0;
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, H)) {
            i4 += i5 * 60;
            i5 = 0;
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, m)) {
            i3 += i4 * 60;
            i4 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, s)) {
            i = i3;
        } else {
            i2 += i3 * 1000;
        }
        return format(tokenArrLexx, i7, i6, actualMaximum, i5, i4, i, i2, z);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Token {
        public int count;
        public final Object value;

        public Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        public static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token token : tokenArr) {
                if (token.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Token) {
                Token token = (Token) obj;
                if (this.value.getClass() != token.value.getClass() || this.count != token.count) {
                    return false;
                }
                Object obj2 = this.value;
                if (obj2 instanceof StringBuilder) {
                    return obj2.toString().equals(token.value.toString());
                }
                if (obj2 instanceof Number) {
                    return obj2.equals(token.value);
                }
                if (obj2 == token.value) {
                    return true;
                }
            }
            return false;
        }

        public int getCount() {
            return this.count;
        }

        public Object getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public void increment() {
            this.count++;
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }

        public Token(Object obj, int i) {
            this.value = obj;
            this.count = i;
        }
    }
}
