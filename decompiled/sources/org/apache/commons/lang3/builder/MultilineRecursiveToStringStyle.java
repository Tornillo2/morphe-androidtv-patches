package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MultilineRecursiveToStringStyle extends RecursiveToStringStyle {
    public static final int INDENT = 2;
    public static final long serialVersionUID = 1;
    public int spaces = 2;

    public MultilineRecursiveToStringStyle() {
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.RecursiveToStringStyle, org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object obj) {
        if (ClassUtils.isPrimitiveWrapper(obj.getClass()) || String.class.equals(obj.getClass())) {
            super.appendDetail(stringBuffer, str, obj);
            return;
        }
        this.spaces += 2;
        resetIndent();
        stringBuffer.append(ReflectionToStringBuilder.toString(obj, this));
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void reflectionAppendArrayDetail(StringBuffer stringBuffer, String str, Object obj) {
        this.spaces += 2;
        resetIndent();
        super.reflectionAppendArrayDetail(stringBuffer, str, obj);
        this.spaces -= 2;
        resetIndent();
    }

    public final void resetIndent() {
        setArrayStart("{" + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setArraySeparator("," + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setArrayEnd(System.lineSeparator() + ((Object) spacer(this.spaces + (-2))) + "}");
        StringBuilder sb = new StringBuilder("[");
        sb.append(System.lineSeparator());
        sb.append((Object) spacer(this.spaces));
        setContentStart(sb.toString());
        setFieldSeparator("," + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setContentEnd(System.lineSeparator() + ((Object) spacer(this.spaces + (-2))) + "]");
    }

    public final StringBuilder spacer(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(StringUtils.SPACE);
        }
        return sb;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object[] objArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, objArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, long[] jArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, jArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, int[] iArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, iArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, short[] sArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, sArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, byte[] bArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, bArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, char[] cArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, cArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, double[] dArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, dArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, float[] fArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, fArr);
        this.spaces -= 2;
        resetIndent();
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, boolean[] zArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, zArr);
        this.spaces -= 2;
        resetIndent();
    }
}
