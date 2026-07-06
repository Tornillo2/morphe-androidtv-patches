package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class UnicodeUnescaper extends CharSequenceTranslator {
    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        int i2;
        int i3;
        if (charSequence.charAt(i) != '\\' || (i2 = i + 1) >= charSequence.length() || charSequence.charAt(i2) != 'u') {
            return 0;
        }
        int i4 = 2;
        while (true) {
            i3 = i + i4;
            if (i3 >= charSequence.length() || charSequence.charAt(i3) != 'u') {
                break;
            }
            i4++;
        }
        if (i3 < charSequence.length() && charSequence.charAt(i3) == '+') {
            i4++;
        }
        int i5 = i + i4;
        int i6 = i5 + 4;
        if (i6 > charSequence.length()) {
            throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + ((Object) charSequence.subSequence(i, charSequence.length())) + "' due to end of CharSequence");
        }
        CharSequence charSequenceSubSequence = charSequence.subSequence(i5, i6);
        try {
            writer.write((char) Integer.parseInt(charSequenceSubSequence.toString(), 16));
            return i4 + 4;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unable to parse unicode value: " + ((Object) charSequenceSubSequence), e);
        }
    }
}
