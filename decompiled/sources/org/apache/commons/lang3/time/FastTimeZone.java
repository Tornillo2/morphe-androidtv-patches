package org.apache.commons.lang3.time;

import j$.util.DesugarTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class FastTimeZone {
    public static final Pattern GMT_PATTERN = Pattern.compile("^(?:(?i)GMT)?([+-])?(\\d\\d?)?(:?(\\d\\d?))?$");
    public static final TimeZone GREENWICH = new GmtTimeZone(false, 0, 0);

    public static TimeZone getGmtTimeZone() {
        return GREENWICH;
    }

    public static TimeZone getTimeZone(String str) {
        TimeZone gmtTimeZone = getGmtTimeZone(str);
        return gmtTimeZone != null ? gmtTimeZone : DesugarTimeZone.getTimeZone(str);
    }

    public static int parseInt(String str) {
        if (str != null) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    public static boolean parseSign(String str) {
        return str != null && str.charAt(0) == '-';
    }

    public static TimeZone getGmtTimeZone(String str) {
        if ("Z".equals(str) || "UTC".equals(str)) {
            return GREENWICH;
        }
        Matcher matcher = GMT_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        int i = parseInt(matcher.group(2));
        int i2 = parseInt(matcher.group(4));
        return (i == 0 && i2 == 0) ? GREENWICH : new GmtTimeZone(parseSign(matcher.group(1)), i, i2);
    }
}
