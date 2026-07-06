package org.apache.commons.text;

import com.amazon.minerva.identifiers.schemaid.MetricSchemaUUID;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.common.net.MediaType;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.CsvTranslators;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.JavaUnicodeEscaper;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.commons.text.translate.NumericEntityEscaper;
import org.apache.commons.text.translate.NumericEntityUnescaper;
import org.apache.commons.text.translate.OctalUnescaper;
import org.apache.commons.text.translate.UnicodeUnescaper;
import org.apache.commons.text.translate.UnicodeUnpairedSurrogateRemover;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class StringEscapeUtils {
    public static final CharSequenceTranslator ESCAPE_CSV;
    public static final CharSequenceTranslator ESCAPE_ECMASCRIPT;
    public static final CharSequenceTranslator ESCAPE_HTML3;
    public static final CharSequenceTranslator ESCAPE_HTML4;
    public static final CharSequenceTranslator ESCAPE_JAVA;
    public static final CharSequenceTranslator ESCAPE_JSON;
    public static final CharSequenceTranslator ESCAPE_XML10;
    public static final CharSequenceTranslator ESCAPE_XML11;
    public static final CharSequenceTranslator ESCAPE_XSI;
    public static final CharSequenceTranslator UNESCAPE_CSV;
    public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT;
    public static final CharSequenceTranslator UNESCAPE_HTML3;
    public static final CharSequenceTranslator UNESCAPE_HTML4;
    public static final CharSequenceTranslator UNESCAPE_JAVA;
    public static final CharSequenceTranslator UNESCAPE_JSON;
    public static final CharSequenceTranslator UNESCAPE_XML;
    public static final CharSequenceTranslator UNESCAPE_XSI;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final StringBuilder sb;
        public final CharSequenceTranslator translator;

        public Builder append(String str) {
            this.sb.append(str);
            return this;
        }

        public Builder escape(String str) {
            this.sb.append(this.translator.translate(str));
            return this;
        }

        public String toString() {
            return this.sb.toString();
        }

        public Builder(CharSequenceTranslator charSequenceTranslator) {
            this.sb = new StringBuilder();
            this.translator = charSequenceTranslator;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class XsiUnescaper extends CharSequenceTranslator {
        public static final char BACKSLASH = '\\';

        @Override // org.apache.commons.text.translate.CharSequenceTranslator
        public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
            if (i != 0) {
                throw new IllegalStateException("XsiUnescaper should never reach the [1] index");
            }
            String string = charSequence.toString();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int iIndexOf = string.indexOf(92, i2);
                if (iIndexOf == -1) {
                    break;
                }
                if (iIndexOf > i3) {
                    writer.write(string.substring(i3, iIndexOf));
                }
                i3 = iIndexOf + 1;
                i2 = iIndexOf + 2;
            }
            if (i3 < string.length()) {
                writer.write(string.substring(i3));
            }
            return Character.codePointCount(charSequence, 0, charSequence.length());
        }
    }

    static {
        HashMap map = new HashMap();
        map.put(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE, "\\\"");
        map.put(CCTDestination.EXTRAS_DELIMITER, "\\\\");
        LookupTranslator lookupTranslator = new LookupTranslator(DesugarCollections.unmodifiableMap(map));
        Map<CharSequence, CharSequence> map2 = EntityArrays.JAVA_CTRL_CHARS_ESCAPE;
        ESCAPE_JAVA = new AggregateTranslator(lookupTranslator, new LookupTranslator(map2), JavaUnicodeEscaper.outsideOf(32, 127));
        HashMap map3 = new HashMap();
        map3.put("'", "\\'");
        map3.put(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE, "\\\"");
        map3.put(CCTDestination.EXTRAS_DELIMITER, "\\\\");
        map3.put(SchemaId.METRIC_SCHEMA_ID_DELIMITER, "\\/");
        ESCAPE_ECMASCRIPT = new AggregateTranslator(new LookupTranslator(DesugarCollections.unmodifiableMap(map3)), new LookupTranslator(map2), JavaUnicodeEscaper.outsideOf(32, 127));
        HashMap map4 = new HashMap();
        map4.put(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE, "\\\"");
        map4.put(CCTDestination.EXTRAS_DELIMITER, "\\\\");
        map4.put(SchemaId.METRIC_SCHEMA_ID_DELIMITER, "\\/");
        ESCAPE_JSON = new AggregateTranslator(new LookupTranslator(DesugarCollections.unmodifiableMap(map4)), new LookupTranslator(map2), JavaUnicodeEscaper.outsideOf(32, 126));
        HashMap map5 = new HashMap();
        map5.put("\u0000", "");
        map5.put("\u0001", "");
        map5.put("\u0002", "");
        map5.put("\u0003", "");
        map5.put("\u0004", "");
        map5.put("\u0005", "");
        map5.put("\u0006", "");
        map5.put("\u0007", "");
        map5.put("\b", "");
        map5.put("\u000b", "");
        map5.put("\f", "");
        map5.put("\u000e", "");
        map5.put("\u000f", "");
        map5.put("\u0010", "");
        map5.put("\u0011", "");
        map5.put("\u0012", "");
        map5.put("\u0013", "");
        map5.put("\u0014", "");
        map5.put("\u0015", "");
        map5.put("\u0016", "");
        map5.put("\u0017", "");
        map5.put("\u0018", "");
        map5.put("\u0019", "");
        map5.put("\u001a", "");
        map5.put("\u001b", "");
        map5.put("\u001c", "");
        map5.put("\u001d", "");
        map5.put("\u001e", "");
        map5.put("\u001f", "");
        map5.put("\ufffe", "");
        map5.put("\uffff", "");
        Map<CharSequence, CharSequence> map6 = EntityArrays.BASIC_ESCAPE;
        LookupTranslator lookupTranslator2 = new LookupTranslator(map6);
        Map<CharSequence, CharSequence> map7 = EntityArrays.APOS_ESCAPE;
        ESCAPE_XML10 = new AggregateTranslator(lookupTranslator2, new LookupTranslator(map7), new LookupTranslator(DesugarCollections.unmodifiableMap(map5)), NumericEntityEscaper.between(127, 132), NumericEntityEscaper.between(134, 159), new UnicodeUnpairedSurrogateRemover());
        HashMap map8 = new HashMap();
        map8.put("\u0000", "");
        map8.put("\u000b", "&#11;");
        map8.put("\f", "&#12;");
        map8.put("\ufffe", "");
        map8.put("\uffff", "");
        ESCAPE_XML11 = new AggregateTranslator(new LookupTranslator(map6), new LookupTranslator(map7), new LookupTranslator(DesugarCollections.unmodifiableMap(map8)), NumericEntityEscaper.between(1, 8), NumericEntityEscaper.between(14, 31), NumericEntityEscaper.between(127, 132), NumericEntityEscaper.between(134, 159), new UnicodeUnpairedSurrogateRemover());
        LookupTranslator lookupTranslator3 = new LookupTranslator(map6);
        Map<CharSequence, CharSequence> map9 = EntityArrays.ISO8859_1_ESCAPE;
        ESCAPE_HTML3 = new AggregateTranslator(lookupTranslator3, new LookupTranslator(map9));
        ESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(map6), new LookupTranslator(map9), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE));
        ESCAPE_CSV = new CsvTranslators.CsvEscaper();
        HashMap map10 = new HashMap();
        map10.put(MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER, "\\|");
        map10.put("&", "\\&");
        map10.put(";", "\\;");
        map10.put("<", "\\<");
        map10.put(">", "\\>");
        map10.put("(", "\\(");
        map10.put(")", "\\)");
        map10.put("$", "\\$");
        map10.put("`", "\\`");
        map10.put(CCTDestination.EXTRAS_DELIMITER, "\\\\");
        map10.put(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE, "\\\"");
        map10.put("'", "\\'");
        map10.put(StringUtils.SPACE, "\\ ");
        map10.put("\t", "\\\t");
        map10.put("\r\n", "");
        map10.put(StringUtils.LF, "");
        map10.put(MediaType.WILDCARD, "\\*");
        map10.put("?", "\\?");
        map10.put("[", "\\[");
        map10.put("#", "\\#");
        map10.put("~", "\\~");
        map10.put("=", "\\=");
        map10.put("%", "\\%");
        ESCAPE_XSI = new LookupTranslator(DesugarCollections.unmodifiableMap(map10));
        HashMap map11 = new HashMap();
        map11.put("\\\\", CCTDestination.EXTRAS_DELIMITER);
        map11.put("\\\"", ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
        map11.put("\\'", "'");
        map11.put(CCTDestination.EXTRAS_DELIMITER, "");
        AggregateTranslator aggregateTranslator = new AggregateTranslator(new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE), new LookupTranslator(DesugarCollections.unmodifiableMap(map11)));
        UNESCAPE_JAVA = aggregateTranslator;
        UNESCAPE_ECMASCRIPT = aggregateTranslator;
        UNESCAPE_JSON = aggregateTranslator;
        Map<CharSequence, CharSequence> map12 = EntityArrays.BASIC_UNESCAPE;
        LookupTranslator lookupTranslator4 = new LookupTranslator(map12);
        Map<CharSequence, CharSequence> map13 = EntityArrays.ISO8859_1_UNESCAPE;
        UNESCAPE_HTML3 = new AggregateTranslator(lookupTranslator4, new LookupTranslator(map13), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(map12), new LookupTranslator(map13), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_XML = new AggregateTranslator(new LookupTranslator(map12), new LookupTranslator(EntityArrays.APOS_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_CSV = new CsvTranslators.CsvUnescaper();
        UNESCAPE_XSI = new XsiUnescaper();
    }

    public static Builder builder(CharSequenceTranslator charSequenceTranslator) {
        return new Builder(charSequenceTranslator);
    }

    public static final String escapeCsv(String str) {
        return ESCAPE_CSV.translate(str);
    }

    public static final String escapeEcmaScript(String str) {
        return ESCAPE_ECMASCRIPT.translate(str);
    }

    public static final String escapeHtml3(String str) {
        return ESCAPE_HTML3.translate(str);
    }

    public static final String escapeHtml4(String str) {
        return ESCAPE_HTML4.translate(str);
    }

    public static final String escapeJava(String str) {
        return ESCAPE_JAVA.translate(str);
    }

    public static final String escapeJson(String str) {
        return ESCAPE_JSON.translate(str);
    }

    public static final String escapeXSI(String str) {
        return ESCAPE_XSI.translate(str);
    }

    public static String escapeXml10(String str) {
        return ESCAPE_XML10.translate(str);
    }

    public static String escapeXml11(String str) {
        return ESCAPE_XML11.translate(str);
    }

    public static final String unescapeCsv(String str) {
        return UNESCAPE_CSV.translate(str);
    }

    public static final String unescapeEcmaScript(String str) {
        return UNESCAPE_ECMASCRIPT.translate(str);
    }

    public static final String unescapeHtml3(String str) {
        return UNESCAPE_HTML3.translate(str);
    }

    public static final String unescapeHtml4(String str) {
        return UNESCAPE_HTML4.translate(str);
    }

    public static final String unescapeJava(String str) {
        return UNESCAPE_JAVA.translate(str);
    }

    public static final String unescapeJson(String str) {
        return UNESCAPE_JSON.translate(str);
    }

    public static final String unescapeXSI(String str) {
        return UNESCAPE_XSI.translate(str);
    }

    public static final String unescapeXml(String str) {
        return UNESCAPE_XML.translate(str);
    }
}
