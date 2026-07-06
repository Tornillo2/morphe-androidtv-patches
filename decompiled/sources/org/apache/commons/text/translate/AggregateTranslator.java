package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class AggregateTranslator extends CharSequenceTranslator {
    public final List<CharSequenceTranslator> translators = new ArrayList();

    public AggregateTranslator(CharSequenceTranslator... charSequenceTranslatorArr) {
        if (charSequenceTranslatorArr != null) {
            for (CharSequenceTranslator charSequenceTranslator : charSequenceTranslatorArr) {
                if (charSequenceTranslator != null) {
                    this.translators.add(charSequenceTranslator);
                }
            }
        }
    }

    @Override // org.apache.commons.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        Iterator<CharSequenceTranslator> it = this.translators.iterator();
        while (it.hasNext()) {
            int iTranslate = it.next().translate(charSequence, i, writer);
            if (iTranslate != 0) {
                return iTranslate;
            }
        }
        return 0;
    }
}
