package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Escapers {
    public static final Escaper NULL_ESCAPER = new AnonymousClass1();

    /* JADX INFO: renamed from: com.google.common.escape.Escapers$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends CharEscaper {
        @Override // com.google.common.escape.CharEscaper
        public char[] escape(char c) {
            return null;
        }

        @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
        public String escape(String string) {
            string.getClass();
            return string;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final Map<Character, String> replacementMap;
        public char safeMax;
        public char safeMin;
        public String unsafeReplacement;

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        @CanIgnoreReturnValue
        public Builder addEscape(char c, String replacement) {
            replacement.getClass();
            this.replacementMap.put(Character.valueOf(c), replacement);
            return this;
        }

        public Escaper build() {
            return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) { // from class: com.google.common.escape.Escapers.Builder.1
                public final char[] replacementChars;

                {
                    String str = Builder.this.unsafeReplacement;
                    this.replacementChars = str != null ? str.toCharArray() : null;
                }

                @Override // com.google.common.escape.ArrayBasedCharEscaper
                public char[] escapeUnsafe(char c) {
                    return this.replacementChars;
                }
            };
        }

        @CanIgnoreReturnValue
        public Builder setSafeRange(char safeMin, char safeMax) {
            this.safeMin = safeMin;
            this.safeMax = safeMax;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUnsafeReplacement(String unsafeReplacement) {
            this.unsafeReplacement = unsafeReplacement;
            return this;
        }

        public Builder() {
            this.replacementMap = new HashMap();
            this.safeMin = (char) 0;
            this.safeMax = CharCompanionObject.MAX_VALUE;
            this.unsafeReplacement = null;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static String computeReplacement(CharEscaper escaper, char c) {
        return stringOrNull(escaper.escape(c));
    }

    public static Escaper nullEscaper() {
        return NULL_ESCAPER;
    }

    public static String stringOrNull(char[] in) {
        if (in == null) {
            return null;
        }
        return new String(in);
    }

    public static String computeReplacement(UnicodeEscaper escaper, int cp) {
        return stringOrNull(escaper.escape(cp));
    }
}
