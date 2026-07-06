package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzc {
    public static final Pattern zza = Pattern.compile("\\\\u[0-9a-fA-F]{4}");

    public static String zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = zza.matcher(str);
        StringBuilder sb = null;
        int iEnd = 0;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            int iStart = matcher.start();
            int i = iStart;
            while (i >= 0 && str.charAt(i) == '\\') {
                i--;
            }
            if ((iStart - i) % 2 != 0) {
                int i2 = Integer.parseInt(matcher.group().substring(2), 16);
                sb.append((CharSequence) str, iEnd, matcher.start());
                if (i2 == 92) {
                    sb.append("\\\\");
                } else {
                    sb.append(Character.toChars(i2));
                }
                iEnd = matcher.end();
            }
        }
        if (sb == null) {
            return str;
        }
        if (iEnd < matcher.regionEnd()) {
            sb.append((CharSequence) str, iEnd, matcher.regionEnd());
        }
        return sb.toString();
    }
}
