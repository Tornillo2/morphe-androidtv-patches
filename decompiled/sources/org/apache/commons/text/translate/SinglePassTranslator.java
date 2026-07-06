package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class SinglePassTranslator extends CharSequenceTranslator {
    public final String getClassName() {
        Class<?> cls = getClass();
        return cls.isAnonymousClass() ? cls.getName() : cls.getSimpleName();
    }

    @Override // org.apache.commons.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        if (i != 0) {
            throw new IllegalArgumentException(getClassName().concat(".translate(final CharSequence input, final int index, final Writer out) can not handle a non-zero index."));
        }
        translateWhole(charSequence, writer);
        return Character.codePointCount(charSequence, i, charSequence.length());
    }

    public abstract void translateWhole(CharSequence charSequence, Writer writer) throws IOException;
}
