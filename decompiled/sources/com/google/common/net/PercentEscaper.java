package com.google.common.net;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.UnicodeEscaper;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class PercentEscaper extends UnicodeEscaper {
    public static final char[] plusSign = {'+'};
    public static final char[] upperHexDigits = "0123456789ABCDEF".toCharArray();
    public final boolean plusForSpace;
    public final boolean[] safeOctets;

    public PercentEscaper(String safeChars, boolean plusForSpace) {
        safeChars.getClass();
        if (safeChars.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        String strConcat = safeChars.concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        if (plusForSpace && strConcat.contains(StringUtils.SPACE)) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.plusForSpace = plusForSpace;
        this.safeOctets = createSafeOctets(strConcat);
    }

    public static boolean[] createSafeOctets(String safeChars) {
        char[] charArray = safeChars.toCharArray();
        int iMax = -1;
        for (char c : charArray) {
            iMax = Math.max((int) c, iMax);
        }
        boolean[] zArr = new boolean[iMax + 1];
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        return zArr;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public char[] escape(int cp) {
        boolean[] zArr = this.safeOctets;
        if (cp < zArr.length && zArr[cp]) {
            return null;
        }
        if (cp == 32 && this.plusForSpace) {
            return plusSign;
        }
        if (cp <= 127) {
            char[] cArr = upperHexDigits;
            return new char[]{'%', cArr[cp >>> 4], cArr[cp & 15]};
        }
        if (cp <= 2047) {
            char[] cArr2 = upperHexDigits;
            return new char[]{'%', cArr2[(cp >>> 10) | 12], cArr2[(cp >>> 6) & 15], '%', cArr2[((cp >>> 4) & 3) | 8], cArr2[cp & 15]};
        }
        if (cp <= 65535) {
            char[] cArr3 = upperHexDigits;
            return new char[]{'%', 'E', cArr3[cp >>> 12], '%', cArr3[((cp >>> 10) & 3) | 8], cArr3[(cp >>> 6) & 15], '%', cArr3[((cp >>> 4) & 3) | 8], cArr3[cp & 15]};
        }
        if (cp > 1114111) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid unicode character value ", cp));
        }
        char[] cArr4 = upperHexDigits;
        return new char[]{'%', 'F', cArr4[(cp >>> 18) & 7], '%', cArr4[((cp >>> 16) & 3) | 8], cArr4[(cp >>> 12) & 15], '%', cArr4[((cp >>> 10) & 3) | 8], cArr4[(cp >>> 6) & 15], '%', cArr4[((cp >>> 4) & 3) | 8], cArr4[cp & 15]};
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public int nextEscapeIndex(CharSequence csq, int index, int end) {
        csq.getClass();
        while (index < end) {
            char cCharAt = csq.charAt(index);
            boolean[] zArr = this.safeOctets;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                break;
            }
            index++;
        }
        return index;
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public String escape(String s) {
        s.getClass();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = s.charAt(i);
            boolean[] zArr = this.safeOctets;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                return escapeSlow(s, i);
            }
        }
        return s;
    }
}
