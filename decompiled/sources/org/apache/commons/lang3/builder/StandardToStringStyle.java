package org.apache.commons.lang3.builder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class StandardToStringStyle extends ToStringStyle {
    public static final long serialVersionUID = 1;

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getArrayEnd() {
        return this.arrayEnd;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getArraySeparator() {
        return this.arraySeparator;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getArrayStart() {
        return this.arrayStart;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getContentEnd() {
        return this.contentEnd;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getContentStart() {
        return this.contentStart;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getFieldNameValueSeparator() {
        return this.fieldNameValueSeparator;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getFieldSeparator() {
        return this.fieldSeparator;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getNullText() {
        return this.nullText;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getSizeEndText() {
        return this.sizeEndText;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getSizeStartText() {
        return this.sizeStartText;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getSummaryObjectEndText() {
        return this.summaryObjectEndText;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public String getSummaryObjectStartText() {
        return this.summaryObjectStartText;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isArrayContentDetail() {
        return this.arrayContentDetail;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isDefaultFullDetail() {
        return this.defaultFullDetail;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isFieldSeparatorAtEnd() {
        return this.fieldSeparatorAtEnd;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isFieldSeparatorAtStart() {
        return this.fieldSeparatorAtStart;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isUseClassName() {
        return this.useClassName;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isUseFieldNames() {
        return this.useFieldNames;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isUseIdentityHashCode() {
        return this.useIdentityHashCode;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public boolean isUseShortClassName() {
        return this.useShortClassName;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setArrayContentDetail(boolean z) {
        this.arrayContentDetail = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setArrayEnd(String str) {
        super.setArrayEnd(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setArraySeparator(String str) {
        super.setArraySeparator(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setArrayStart(String str) {
        super.setArrayStart(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setContentEnd(String str) {
        super.setContentEnd(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setContentStart(String str) {
        super.setContentStart(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setDefaultFullDetail(boolean z) {
        this.defaultFullDetail = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setFieldNameValueSeparator(String str) {
        super.setFieldNameValueSeparator(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setFieldSeparator(String str) {
        super.setFieldSeparator(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setFieldSeparatorAtEnd(boolean z) {
        this.fieldSeparatorAtEnd = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setFieldSeparatorAtStart(boolean z) {
        this.fieldSeparatorAtStart = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setNullText(String str) {
        super.setNullText(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setSizeEndText(String str) {
        super.setSizeEndText(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setSizeStartText(String str) {
        super.setSizeStartText(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setSummaryObjectEndText(String str) {
        super.setSummaryObjectEndText(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setSummaryObjectStartText(String str) {
        super.setSummaryObjectStartText(str);
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setUseClassName(boolean z) {
        this.useClassName = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setUseFieldNames(boolean z) {
        this.useFieldNames = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setUseIdentityHashCode(boolean z) {
        this.useIdentityHashCode = z;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void setUseShortClassName(boolean z) {
        this.useShortClassName = z;
    }
}
