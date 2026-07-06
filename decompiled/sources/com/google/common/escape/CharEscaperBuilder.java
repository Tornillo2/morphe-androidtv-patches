package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class CharEscaperBuilder {
    public int max = -1;
    public final Map<Character, String> map = new HashMap();

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscape(char c, String r) {
        Map<Character, String> map = this.map;
        Character chValueOf = Character.valueOf(c);
        r.getClass();
        map.put(chValueOf, r);
        if (c > this.max) {
            this.max = c;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscapes(char[] cs, String r) {
        r.getClass();
        for (char c : cs) {
            addEscape(c, r);
        }
        return this;
    }

    public char[][] toArray() {
        char[][] cArr = new char[this.max + 1][];
        for (Map.Entry<Character, String> entry : this.map.entrySet()) {
            cArr[entry.getKey().charValue()] = entry.getValue().toCharArray();
        }
        return cArr;
    }

    public Escaper toEscaper() {
        return new CharArrayDecorator(toArray());
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CharArrayDecorator extends CharEscaper {
        public final int replaceLength;
        public final char[][] replacements;

        public CharArrayDecorator(char[][] replacements) {
            this.replacements = replacements;
            this.replaceLength = replacements.length;
        }

        @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
        public String escape(String s) {
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = s.charAt(i);
                char[][] cArr = this.replacements;
                if (cCharAt < cArr.length && cArr[cCharAt] != null) {
                    return escapeSlow(s, i);
                }
            }
            return s;
        }

        @Override // com.google.common.escape.CharEscaper
        public char[] escape(char c) {
            if (c < this.replaceLength) {
                return this.replacements[c];
            }
            return null;
        }
    }
}
