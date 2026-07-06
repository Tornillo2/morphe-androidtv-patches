package com.amazon.minerva.identifiers.schemaid.attribute.parser;

import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.VersionedAttributes;
import j$.util.Objects;
import java.util.BitSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Version2AttributesParser extends BaseAttributesParser {
    public static String getAttributesHexString(VersionedAttributes versionedAttributes) {
        BitSet bitSetCreateEmptyBitSet = BaseAttributesParser.createEmptyBitSet();
        AttributeEnumV2 attributeEnumV2 = AttributeEnumV2.ALLOW_DSN_INFO;
        boolean zBooleanValue = versionedAttributes.getBooleanValue(attributeEnumV2).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue ? 1 : 0, attributeEnumV2.startIndex, attributeEnumV2.endIndex);
        AttributeEnumV2 attributeEnumV22 = AttributeEnumV2.ALLOW_CUSTOMER_INFO;
        boolean zBooleanValue2 = versionedAttributes.getBooleanValue(attributeEnumV22).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue2 ? 1 : 0, attributeEnumV22.startIndex, attributeEnumV22.endIndex);
        AttributeEnumV2 attributeEnumV23 = AttributeEnumV2.ALLOW_CHILD_PROFILE;
        boolean zBooleanValue3 = versionedAttributes.getBooleanValue(attributeEnumV23).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue3 ? 1 : 0, attributeEnumV23.startIndex, attributeEnumV23.endIndex);
        AttributeEnumV2 attributeEnumV24 = AttributeEnumV2.DEPRECATED_EXEMPT_FROM_3P_OPT_OUT;
        boolean zBooleanValue4 = versionedAttributes.getBooleanValue(attributeEnumV24).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue4 ? 1 : 0, attributeEnumV24.startIndex, attributeEnumV24.endIndex);
        AttributeEnumV2 attributeEnumV25 = AttributeEnumV2.USE_SUBJECT_TO_3P_OPT_OUT;
        boolean zBooleanValue5 = versionedAttributes.getBooleanValue(attributeEnumV25).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue5 ? 1 : 0, attributeEnumV25.startIndex, attributeEnumV25.endIndex);
        AttributeEnumV2 attributeEnumV26 = AttributeEnumV2.SUBJECT_TO_3P_OPT_OUT;
        boolean zBooleanValue6 = versionedAttributes.getBooleanValue(attributeEnumV26).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue6 ? 1 : 0, attributeEnumV26.startIndex, attributeEnumV26.endIndex);
        AttributeEnumV2 attributeEnumV27 = AttributeEnumV2.METRIC_BUSINESS_TYPE;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV27).intValue(), attributeEnumV27.startIndex, attributeEnumV27.endIndex);
        AttributeEnumV2 attributeEnumV28 = AttributeEnumV2.UPLOAD_PRIORITY;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV28).intValue(), attributeEnumV28.startIndex, attributeEnumV28.endIndex);
        AttributeEnumV2 attributeEnumV29 = AttributeEnumV2.STORAGE_PRIORITY;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV29).intValue(), attributeEnumV29.startIndex, attributeEnumV29.endIndex);
        AttributeEnumV2 attributeEnumV210 = AttributeEnumV2.SCHEMA_REVISION;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV210).intValue(), attributeEnumV210.startIndex, attributeEnumV210.endIndex);
        return BaseAttributesParser.getAttributesHexString(bitSetCreateEmptyBitSet);
    }

    public static VersionedAttributes parseAttributes(String str) {
        VersionedAttributes versionedAttributes = new VersionedAttributes();
        BitSet bitSetCreateBitSet = BaseAttributesParser.createBitSet(str);
        Objects.requireNonNull(bitSetCreateBitSet, "attrBitSet can not be null");
        AttributeEnumV2 attributeEnumV2 = AttributeEnumV2.ALLOW_DSN_INFO;
        versionedAttributes.setBooleanValue(attributeEnumV2, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV2.startIndex)));
        AttributeEnumV2 attributeEnumV22 = AttributeEnumV2.ALLOW_CUSTOMER_INFO;
        versionedAttributes.setBooleanValue(attributeEnumV22, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV22.startIndex)));
        AttributeEnumV2 attributeEnumV23 = AttributeEnumV2.ALLOW_CHILD_PROFILE;
        versionedAttributes.setBooleanValue(attributeEnumV23, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV23.startIndex)));
        AttributeEnumV2 attributeEnumV24 = AttributeEnumV2.DEPRECATED_EXEMPT_FROM_3P_OPT_OUT;
        versionedAttributes.setBooleanValue(attributeEnumV24, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV24.startIndex)));
        AttributeEnumV2 attributeEnumV25 = AttributeEnumV2.USE_SUBJECT_TO_3P_OPT_OUT;
        versionedAttributes.setBooleanValue(attributeEnumV25, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV25.startIndex)));
        AttributeEnumV2 attributeEnumV26 = AttributeEnumV2.SUBJECT_TO_3P_OPT_OUT;
        versionedAttributes.setBooleanValue(attributeEnumV26, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV26.startIndex)));
        AttributeEnumV2 attributeEnumV27 = AttributeEnumV2.METRIC_BUSINESS_TYPE;
        versionedAttributes.setIntegerValue(attributeEnumV27, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV27.startIndex, attributeEnumV27.endIndex)));
        AttributeEnumV2 attributeEnumV28 = AttributeEnumV2.UPLOAD_PRIORITY;
        versionedAttributes.setIntegerValue(attributeEnumV28, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV28.startIndex, attributeEnumV28.endIndex)));
        AttributeEnumV2 attributeEnumV29 = AttributeEnumV2.STORAGE_PRIORITY;
        versionedAttributes.setIntegerValue(attributeEnumV29, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV29.startIndex, attributeEnumV29.endIndex)));
        AttributeEnumV2 attributeEnumV210 = AttributeEnumV2.SCHEMA_REVISION;
        versionedAttributes.setIntegerValue(attributeEnumV210, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV210.startIndex, attributeEnumV210.endIndex)));
        return versionedAttributes;
    }
}
