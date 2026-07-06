package org.apache.commons.text.translate;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CsvTranslators {
    public static final char CSV_DELIMITER = ',';
    public static final String CSV_ESCAPED_QUOTE_STR;
    public static final char CSV_QUOTE = '\"';
    public static final String CSV_QUOTE_STR;
    public static final char[] CSV_SEARCH_CHARS;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CsvEscaper extends SinglePassTranslator {
        @Override // org.apache.commons.text.translate.SinglePassTranslator, org.apache.commons.text.translate.CharSequenceTranslator
        public /* bridge */ /* synthetic */ int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
            return super.translate(charSequence, i, writer);
        }

        @Override // org.apache.commons.text.translate.SinglePassTranslator
        public void translateWhole(CharSequence charSequence, Writer writer) throws IOException {
            String string = charSequence.toString();
            if (StringUtils.containsNone(string, CsvTranslators.CSV_SEARCH_CHARS)) {
                writer.write(string);
                return;
            }
            writer.write(34);
            writer.write(StringUtils.replace(string, CsvTranslators.CSV_QUOTE_STR, CsvTranslators.CSV_ESCAPED_QUOTE_STR, -1, false));
            writer.write(34);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CsvUnescaper extends SinglePassTranslator {
        @Override // org.apache.commons.text.translate.SinglePassTranslator, org.apache.commons.text.translate.CharSequenceTranslator
        public /* bridge */ /* synthetic */ int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
            return super.translate(charSequence, i, writer);
        }

        @Override // org.apache.commons.text.translate.SinglePassTranslator
        public void translateWhole(CharSequence charSequence, Writer writer) throws IOException {
            if (charSequence.charAt(0) != '\"' || charSequence.charAt(charSequence.length() - 1) != '\"') {
                writer.write(charSequence.toString());
                return;
            }
            String string = charSequence.subSequence(1, charSequence.length() - 1).toString();
            if (StringUtils.containsAny(string, CsvTranslators.CSV_SEARCH_CHARS)) {
                writer.write(StringUtils.replace(string, CsvTranslators.CSV_ESCAPED_QUOTE_STR, CsvTranslators.CSV_QUOTE_STR, -1, false));
            } else {
                writer.write(charSequence.toString());
            }
        }
    }

    static {
        String strValueOf = String.valueOf('\"');
        CSV_QUOTE_STR = strValueOf;
        CSV_ESCAPED_QUOTE_STR = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strValueOf, strValueOf);
        CSV_SEARCH_CHARS = new char[]{',', '\"', CharUtils.CR, '\n'};
    }
}
