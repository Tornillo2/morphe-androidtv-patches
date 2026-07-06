package org.apache.commons.text.translate;

import com.amazon.ion.impl._Private_IonTextAppender;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class JavaUnicodeEscaper extends UnicodeEscaper {
    public JavaUnicodeEscaper(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    public static JavaUnicodeEscaper above(int i) {
        return outsideOf(0, i);
    }

    public static JavaUnicodeEscaper below(int i) {
        return outsideOf(i, Integer.MAX_VALUE);
    }

    public static JavaUnicodeEscaper between(int i, int i2) {
        return new JavaUnicodeEscaper(i, i2, true);
    }

    public static JavaUnicodeEscaper outsideOf(int i, int i2) {
        return new JavaUnicodeEscaper(i, i2, false);
    }

    @Override // org.apache.commons.text.translate.UnicodeEscaper
    public String toUtf16Escape(int i) {
        char[] chars = Character.toChars(i);
        return _Private_IonTextAppender.HEX_4_PREFIX + CharSequenceTranslator.hex(chars[0]) + _Private_IonTextAppender.HEX_4_PREFIX + CharSequenceTranslator.hex(chars[1]);
    }
}
