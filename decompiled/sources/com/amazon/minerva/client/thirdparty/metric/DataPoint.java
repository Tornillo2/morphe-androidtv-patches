package com.amazon.minerva.client.thirdparty.metric;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DataPoint {
    public final String mName;
    public final ValueType mType;
    public final String mValue;

    public DataPoint(String str, ValueType valueType, String str2) {
        this.mName = str;
        this.mType = valueType;
        this.mValue = str2;
    }

    public String getName() {
        return this.mName;
    }

    public ValueType getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }
}
