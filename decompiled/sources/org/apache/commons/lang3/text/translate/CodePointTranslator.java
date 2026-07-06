package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public abstract class CodePointTranslator extends CharSequenceTranslator {
    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public final int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        return translate(Character.codePointAt(charSequence, i), writer) ? 1 : 0;
    }

    public abstract boolean translate(int i, Writer writer) throws IOException;
}
