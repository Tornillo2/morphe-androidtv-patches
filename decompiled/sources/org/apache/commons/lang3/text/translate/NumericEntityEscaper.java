package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class NumericEntityEscaper extends CodePointTranslator {
    public final int above;
    public final int below;
    public final boolean between;

    public NumericEntityEscaper(int i, int i2, boolean z) {
        this.below = i;
        this.above = i2;
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

    @Override // org.apache.commons.lang3.text.translate.CodePointTranslator
    public boolean translate(int i, Writer writer) throws IOException {
        if (this.between) {
            if (i < this.below || i > this.above) {
                return false;
            }
        } else if (i >= this.below && i <= this.above) {
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
