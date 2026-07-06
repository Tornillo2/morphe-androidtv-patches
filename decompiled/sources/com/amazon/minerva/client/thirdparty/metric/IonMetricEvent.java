package com.amazon.minerva.client.thirdparty.metric;

import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonMetricEvent {
    public IonTimestamp mClientTimestamp;
    public IonStruct mKeyValuePairs;
    public IonString mMetricEventId;
    public IonSymbol mMetricGroupId;
    public IonSymbol mMetricSchemaId;
    public String mRegion;
    public long mSizeInByte;

    public IonMetricEvent(IonSymbol ionSymbol, IonSymbol ionSymbol2, IonTimestamp ionTimestamp, IonString ionString, IonStruct ionStruct) {
        this.mMetricGroupId = ionSymbol;
        this.mMetricSchemaId = ionSymbol2;
        this.mClientTimestamp = ionTimestamp;
        this.mMetricEventId = ionString;
        this.mKeyValuePairs = ionStruct;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            IonMetricEvent ionMetricEvent = (IonMetricEvent) obj;
            if (Objects.equals(this.mMetricGroupId, ionMetricEvent.mMetricGroupId) && Objects.equals(this.mMetricSchemaId, ionMetricEvent.mMetricSchemaId) && Objects.equals(this.mClientTimestamp, ionMetricEvent.mClientTimestamp) && Objects.equals(this.mMetricEventId, ionMetricEvent.mMetricEventId) && Objects.equals(this.mKeyValuePairs, ionMetricEvent.mKeyValuePairs)) {
                return true;
            }
        }
        return false;
    }

    public IonTimestamp getClientTimestamp() {
        return this.mClientTimestamp;
    }

    public IonStruct getKeyValuePairs() {
        return this.mKeyValuePairs;
    }

    public IonString getMetricEventId() {
        return this.mMetricEventId;
    }

    public IonSymbol getMetricGroupId() {
        return this.mMetricGroupId;
    }

    public IonSymbol getMetricSchemaId() {
        return this.mMetricSchemaId;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public long getSizeInByte() {
        return this.mSizeInByte;
    }

    public void setRegion(String str) {
        this.mRegion = str;
    }

    public void setSizeInByte(long j) {
        this.mSizeInByte = j;
    }
}
