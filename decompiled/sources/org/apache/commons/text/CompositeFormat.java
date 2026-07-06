package org.apache.commons.text;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CompositeFormat extends Format {
    public static final long serialVersionUID = -4329119827877627683L;
    public final Format formatter;
    public final Format parser;

    public CompositeFormat(Format format, Format format2) {
        this.parser = format;
        this.formatter = format2;
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return this.formatter.format(obj, stringBuffer, fieldPosition);
    }

    public Format getFormatter() {
        return this.formatter;
    }

    public Format getParser() {
        return this.parser;
    }

    @Override // java.text.Format
    public Object parseObject(String str, ParsePosition parsePosition) {
        return this.parser.parseObject(str, parsePosition);
    }

    public String reformat(String str) throws ParseException {
        return format(parseObject(str));
    }
}
