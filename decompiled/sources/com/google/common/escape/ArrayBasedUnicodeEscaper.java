package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ArrayBasedUnicodeEscaper extends UnicodeEscaper {
    public final char[][] replacements;
    public final int replacementsLength;
    public final int safeMax;
    public final char safeMaxChar;
    public final int safeMin;
    public final char safeMinChar;

    public ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap escaperMap, int safeMin, int safeMax, String unsafeReplacement) {
        escaperMap.getClass();
        char[][] cArr = escaperMap.replacementArray;
        this.replacements = cArr;
        this.replacementsLength = cArr.length;
        if (safeMax < safeMin) {
            safeMax = -1;
            safeMin = Integer.MAX_VALUE;
        }
        this.safeMin = safeMin;
        this.safeMax = safeMax;
        if (safeMin >= 55296) {
            this.safeMinChar = CharCompanionObject.MAX_VALUE;
            this.safeMaxChar = (char) 0;
        } else {
            this.safeMinChar = (char) safeMin;
            this.safeMaxChar = (char) Math.min(safeMax, 55295);
        }
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public final char[] escape(int cp) {
        char[] cArr;
        if (cp < this.replacementsLength && (cArr = this.replacements[cp]) != null) {
            return cArr;
        }
        if (cp < this.safeMin || cp > this.safeMax) {
            return escapeUnsafe(cp);
        }
        return null;
    }

    public abstract char[] escapeUnsafe(int cp);

    @Override // com.google.common.escape.UnicodeEscaper
    public final int nextEscapeIndex(CharSequence csq, int index, int end) {
        while (index < end) {
            char cCharAt = csq.charAt(index);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMaxChar || cCharAt < this.safeMinChar) {
                break;
            }
            index++;
        }
        return index;
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public final String escape(String s) {
        s.getClass();
        for (int i = 0; i < s.length(); i++) {
            char cCharAt = s.charAt(i);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMaxChar || cCharAt < this.safeMinChar) {
                return escapeSlow(s, i);
            }
        }
        return s;
    }

    public ArrayBasedUnicodeEscaper(Map<Character, String> replacementMap, int safeMin, int safeMax, String unsafeReplacement) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin, safeMax, unsafeReplacement);
    }
}
