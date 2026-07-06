package com.amazon.minerva.identifiers.schemaid.attribute.attributes;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum AttributeEnumV2 implements IAttributeEnum {
    ALLOW_DSN_INFO(0, 0),
    ALLOW_CUSTOMER_INFO(1, 1),
    ALLOW_CHILD_PROFILE(4, 4),
    DEPRECATED_EXEMPT_FROM_3P_OPT_OUT(5, 5),
    USE_SUBJECT_TO_3P_OPT_OUT(6, 6),
    SUBJECT_TO_3P_OPT_OUT(7, 7),
    METRIC_BUSINESS_TYPE(8, 10),
    UPLOAD_PRIORITY(16, 19),
    STORAGE_PRIORITY(20, 23),
    SCHEMA_REVISION(24, 31);

    public int endIndex;
    public int startIndex;

    AttributeEnumV2(int i, int i2) {
        this.startIndex = i;
        this.endIndex = i2;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public int getStartIndex() {
        return this.startIndex;
    }
}
