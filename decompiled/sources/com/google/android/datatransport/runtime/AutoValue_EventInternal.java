package com.google.android.datatransport.runtime;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.android.datatransport.runtime.EventInternal;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_EventInternal extends EventInternal {
    public final Map<String, String> autoMetadata;
    public final Integer code;
    public final EncodedPayload encodedPayload;
    public final long eventMillis;
    public final String transportName;
    public final long uptimeMillis;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends EventInternal.Builder {
        public Map<String, String> autoMetadata;
        public Integer code;
        public EncodedPayload encodedPayload;
        public Long eventMillis;
        public String transportName;
        public Long uptimeMillis;

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal build() {
            String strM = this.transportName == null ? " transportName" : "";
            if (this.encodedPayload == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " encodedPayload");
            }
            if (this.eventMillis == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " eventMillis");
            }
            if (this.uptimeMillis == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " uptimeMillis");
            }
            if (this.autoMetadata == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " autoMetadata");
            }
            if (strM.isEmpty()) {
                return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata);
            }
            throw new IllegalStateException("Missing required properties:".concat(strM));
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public Map<String, String> getAutoMetadata() {
            Map<String, String> map = this.autoMetadata;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setAutoMetadata(Map<String, String> map) {
            if (map == null) {
                throw new NullPointerException("Null autoMetadata");
            }
            this.autoMetadata = map;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setCode(Integer num) {
            this.code = num;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload) {
            if (encodedPayload == null) {
                throw new NullPointerException("Null encodedPayload");
            }
            this.encodedPayload = encodedPayload;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setEventMillis(long j) {
            this.eventMillis = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setTransportName(String str) {
            if (str == null) {
                throw new NullPointerException("Null transportName");
            }
            this.transportName = str;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setUptimeMillis(long j) {
            this.uptimeMillis = Long.valueOf(j);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventInternal)) {
            return false;
        }
        EventInternal eventInternal = (EventInternal) obj;
        if (!this.transportName.equals(eventInternal.getTransportName())) {
            return false;
        }
        Integer num = this.code;
        if (num == null) {
            if (eventInternal.getCode() != null) {
                return false;
            }
        } else if (!num.equals(eventInternal.getCode())) {
            return false;
        }
        return this.encodedPayload.equals(eventInternal.getEncodedPayload()) && this.eventMillis == eventInternal.getEventMillis() && this.uptimeMillis == eventInternal.getUptimeMillis() && this.autoMetadata.equals(eventInternal.getAutoMetadata());
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public Map<String, String> getAutoMetadata() {
        return this.autoMetadata;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    @Nullable
    public Integer getCode() {
        return this.code;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long getEventMillis() {
        return this.eventMillis;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public String getTransportName() {
        return this.transportName;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public int hashCode() {
        int iHashCode = (this.transportName.hashCode() ^ 1000003) * 1000003;
        Integer num = this.code;
        int iHashCode2 = (((iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003;
        long j = this.eventMillis;
        int i = (iHashCode2 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.uptimeMillis;
        return ((i ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode();
    }

    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + "}";
    }

    public AutoValue_EventInternal(String str, @Nullable Integer num, EncodedPayload encodedPayload, long j, long j2, Map<String, String> map) {
        this.transportName = str;
        this.code = num;
        this.encodedPayload = encodedPayload;
        this.eventMillis = j;
        this.uptimeMillis = j2;
        this.autoMetadata = map;
    }
}
