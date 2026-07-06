package org.apache.commons.lang3.text.translate;

import com.amazon.ion.impl._Private_IonTextAppender;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class UnicodeEscaper extends CodePointTranslator {
    public final int above;
    public final int below;
    public final boolean between;

    public UnicodeEscaper(int i, int i2, boolean z) {
        this.below = i;
        this.above = i2;
        this.between = z;
    }

    public static UnicodeEscaper above(int i) {
        return outsideOf(0, i);
    }

    public static UnicodeEscaper below(int i) {
        return outsideOf(i, Integer.MAX_VALUE);
    }

    public static UnicodeEscaper between(int i, int i2) {
        return new UnicodeEscaper(i, i2, true);
    }

    public static UnicodeEscaper outsideOf(int i, int i2) {
        return new UnicodeEscaper(i, i2, false);
    }

    public String toUtf16Escape(int i) {
        return _Private_IonTextAppender.HEX_4_PREFIX + CharSequenceTranslator.hex(i);
    }

    @Override // org.apache.commons.lang3.text.translate.CodePointTranslator
    public boolean translate(int i, Writer writer) throws IOException {
        if (this.between) {
            if (i < this.below || i > this.above) {
                return false;
            }
        } else if (i >= this.below && i <= this.above) {
            return false;
        }
        if (i > 65535) {
            writer.write(toUtf16Escape(i));
            return true;
        }
        writer.write(_Private_IonTextAppender.HEX_4_PREFIX);
        char[] cArr = CharSequenceTranslator.HEX_DIGITS;
        writer.write(cArr[(i >> 12) & 15]);
        writer.write(cArr[(i >> 8) & 15]);
        writer.write(cArr[(i >> 4) & 15]);
        writer.write(cArr[i & 15]);
        return true;
    }

    public UnicodeEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }
}
