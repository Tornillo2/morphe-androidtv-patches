package org.apache.commons.text;

import java.util.Formattable;
import java.util.Formatter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class FormattableUtils {
    public static final String SIMPLEST_FORMAT = "%s";

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i, int i2, int i3) {
        append(charSequence, formatter, i, i2, i3, ' ', null);
        return formatter;
    }

    public static String toString(Formattable formattable) {
        return String.format("%s", formattable);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i, int i2, int i3, char c) {
        append(charSequence, formatter, i, i2, i3, c, null);
        return formatter;
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i, int i2, int i3, CharSequence charSequence2) {
        append(charSequence, formatter, i, i2, i3, ' ', charSequence2);
        return formatter;
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i, int i2, int i3, char c, CharSequence charSequence2) {
        if (charSequence2 != null && i3 >= 0 && charSequence2.length() > i3) {
            throw new IllegalArgumentException(String.format("Specified ellipsis '%s' exceeds precision of %s", charSequence2, Integer.valueOf(i3)));
        }
        StringBuilder sb = new StringBuilder(charSequence);
        if (i3 >= 0 && i3 < charSequence.length()) {
            if (charSequence2 == null) {
                charSequence2 = "";
            }
            sb.replace(i3 - charSequence2.length(), charSequence.length(), charSequence2.toString());
        }
        boolean z = (i & 1) == 1;
        for (int length = sb.length(); length < i2; length++) {
            sb.insert(z ? length : 0, c);
        }
        formatter.format(sb.toString(), new Object[0]);
        return formatter;
    }
}
