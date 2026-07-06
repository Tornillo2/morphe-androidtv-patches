package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.Range;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class NumericEntityEscaper extends CodePointTranslator {
    public final boolean between;
    public final Range<Integer> range;

    public NumericEntityEscaper(int i, int i2, boolean z) {
        this.range = Range.between(Integer.valueOf(i), Integer.valueOf(i2));
        this.between = z;
    }

    public static NumericEntityEscaper above(int i) {
        return outsideOf(0, i);
    }

    public static NumericEntityEscaper below(int i) {
        return outsideOf(i, Integer.MAX_VALUE);
    }

    public static NumericEntityEscaper between(int i, int i2) {
        return new NumericEntityEscaper(i, i2, true);
    }

    public static NumericEntityEscaper outsideOf(int i, int i2) {
        return new NumericEntityEscaper(i, i2, false);
    }

    @Override // org.apache.commons.text.translate.CodePointTranslator
    public boolean translate(int i, Writer writer) throws IOException {
        if (this.between != this.range.contains(Integer.valueOf(i))) {
            return false;
        }
        writer.write("&#");
        writer.write(Integer.toString(i, 10));
        writer.write(59);
        return true;
    }

    public NumericEntityEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }
}
