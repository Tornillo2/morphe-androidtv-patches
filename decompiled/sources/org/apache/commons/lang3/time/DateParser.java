package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface DateParser {
    Locale getLocale();

    String getPattern();

    TimeZone getTimeZone();

    Date parse(String str) throws ParseException;

    Date parse(String str, ParsePosition parsePosition);

    boolean parse(String str, ParsePosition parsePosition, Calendar calendar);

    Object parseObject(String str) throws ParseException;

    Object parseObject(String str, ParsePosition parsePosition);
}
