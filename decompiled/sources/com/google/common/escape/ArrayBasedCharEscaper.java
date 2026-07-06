package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    public final char[][] replacements;
    public final int replacementsLength;
    public final char safeMax;
    public final char safeMin;

    public ArrayBasedCharEscaper(ArrayBasedEscaperMap escaperMap, char safeMin, char safeMax) {
        escaperMap.getClass();
        char[][] cArr = escaperMap.replacementArray;
        this.replacements = cArr;
        this.replacementsLength = cArr.length;
        if (safeMax < safeMin) {
            safeMax = 0;
            safeMin = CharCompanionObject.MAX_VALUE;
        }
        this.safeMin = safeMin;
        this.safeMax = safeMax;
    }

    @Override // com.google.common.escape.CharEscaper
    public final char[] escape(char c) {
        char[] cArr;
        if (c < this.replacementsLength && (cArr = this.replacements[c]) != null) {
            return cArr;
        }
        if (c < this.safeMin || c > this.safeMax) {
            return escapeUnsafe(c);
        }
        return null;
    }

    public abstract char[] escapeUnsafe(char c);

    @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
    public final String escape(String s) {
        s.getClass();
        for (int i = 0; i < s.length(); i++) {
            char cCharAt = s.charAt(i);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMax || cCharAt < this.safeMin) {
                return escapeSlow(s, i);
            }
        }
        return s;
    }

    public ArrayBasedCharEscaper(Map<Character, String> replacementMap, char safeMin, char safeMax) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin, safeMax);
    }
}
