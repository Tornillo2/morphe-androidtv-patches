package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class OctalUnescaper extends CharSequenceTranslator {
    public final boolean isOctalDigit(char c) {
        return c >= '0' && c <= '7';
    }

    public final boolean isZeroToThree(char c) {
        return c >= '0' && c <= '3';
    }

    @Override // org.apache.commons.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        int length = (charSequence.length() - i) - 1;
        StringBuilder sb = new StringBuilder();
        if (charSequence.charAt(i) != '\\' || length <= 0) {
            return 0;
        }
        int i2 = i + 1;
        if (!isOctalDigit(charSequence.charAt(i2))) {
            return 0;
        }
        int i3 = i + 2;
        int i4 = i + 3;
        sb.append(charSequence.charAt(i2));
        if (length > 1 && isOctalDigit(charSequence.charAt(i3))) {
            sb.append(charSequence.charAt(i3));
            if (length > 2 && isZeroToThree(charSequence.charAt(i2)) && isOctalDigit(charSequence.charAt(i4))) {
                sb.append(charSequence.charAt(i4));
            }
        }
        writer.write(Integer.parseInt(sb.toString(), 8));
        return sb.length() + 1;
    }
}
