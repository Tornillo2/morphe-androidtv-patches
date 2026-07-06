package com.google.common.escape;

import androidx.core.graphics.ColorUtils$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class CharEscaper extends Escaper {
    public static final int DEST_PAD_MULTIPLIER = 2;

    public static char[] growBuffer(char[] dest, int index, int size) {
        if (size < 0) {
            throw new AssertionError("Cannot increase internal buffer any further");
        }
        char[] cArr = new char[size];
        if (index > 0) {
            System.arraycopy(dest, 0, cArr, 0, index);
        }
        return cArr;
    }

    @Override // com.google.common.escape.Escaper
    public String escape(String string) {
        string.getClass();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (escape(string.charAt(i)) != null) {
                return escapeSlow(string, i);
            }
        }
        return string;
    }

    public abstract char[] escape(char c);

    public final String escapeSlow(String s, int index) {
        int length = s.length();
        char[] cArrCharBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int length2 = cArrCharBufferFromThreadLocal.length;
        int i = 0;
        int i2 = 0;
        while (index < length) {
            char[] cArrEscape = escape(s.charAt(index));
            if (cArrEscape != null) {
                int length3 = cArrEscape.length;
                int i3 = index - i;
                int i4 = i2 + i3;
                int i5 = i4 + length3;
                if (length2 < i5) {
                    length2 = ColorUtils$$ExternalSyntheticOutline0.m(length, index, 2, i5);
                    cArrCharBufferFromThreadLocal = growBuffer(cArrCharBufferFromThreadLocal, i2, length2);
                }
                if (i3 > 0) {
                    s.getChars(i, index, cArrCharBufferFromThreadLocal, i2);
                    i2 = i4;
                }
                if (length3 > 0) {
                    System.arraycopy(cArrEscape, 0, cArrCharBufferFromThreadLocal, i2, length3);
                    i2 += length3;
                }
                i = index + 1;
            }
            index++;
        }
        int i6 = length - i;
        if (i6 > 0) {
            int i7 = i6 + i2;
            if (length2 < i7) {
                cArrCharBufferFromThreadLocal = growBuffer(cArrCharBufferFromThreadLocal, i2, i7);
            }
            s.getChars(i, length, cArrCharBufferFromThreadLocal, i2);
            i2 = i7;
        }
        return new String(cArrCharBufferFromThreadLocal, 0, i2);
    }
}
