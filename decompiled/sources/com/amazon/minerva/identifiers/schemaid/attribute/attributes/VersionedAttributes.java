package com.amazon.minerva.identifiers.schemaid.attribute.attributes;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class VersionedAttributes {
    public final Map<IAttributeEnum, Boolean> booleanMap = new HashMap();
    public final Map<IAttributeEnum, Integer> integerMap = new HashMap();

    public Boolean getBooleanValue(IAttributeEnum iAttributeEnum) {
        if (this.booleanMap.containsKey(iAttributeEnum)) {
            return this.booleanMap.get(iAttributeEnum);
        }
        throw new IllegalStateException("Unexpected value: ".concat(String.valueOf(iAttributeEnum)));
    }

    public Integer getIntegerValue(IAttributeEnum iAttributeEnum) {
        if (this.integerMap.containsKey(iAttributeEnum)) {
            return this.integerMap.get(iAttributeEnum);
        }
        throw new IllegalStateException("Unexpected value: ".concat(String.valueOf(iAttributeEnum)));
    }

    public void setBooleanValue(IAttributeEnum iAttributeEnum, Boolean bool) throws IllegalStateException {
        this.booleanMap.put(iAttributeEnum, bool);
    }

    public void setIntegerValue(IAttributeEnum iAttributeEnum, Integer num) throws IllegalStateException {
        this.integerMap.put(iAttributeEnum, num);
    }
}
