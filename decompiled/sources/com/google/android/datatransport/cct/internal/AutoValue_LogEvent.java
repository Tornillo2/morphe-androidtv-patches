package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_LogEvent extends LogEvent {
    public final Integer eventCode;
    public final long eventTimeMs;
    public final long eventUptimeMs;
    public final NetworkConnectionInfo networkConnectionInfo;
    public final byte[] sourceExtension;
    public final String sourceExtensionJsonProto3;
    public final long timezoneOffsetSeconds;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends LogEvent.Builder {
        public Integer eventCode;
        public Long eventTimeMs;
        public Long eventUptimeMs;
        public NetworkConnectionInfo networkConnectionInfo;
        public byte[] sourceExtension;
        public String sourceExtensionJsonProto3;
        public Long timezoneOffsetSeconds;

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent build() {
            String strM = this.eventTimeMs == null ? " eventTimeMs" : "";
            if (this.eventUptimeMs == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " eventUptimeMs");
            }
            if (this.timezoneOffsetSeconds == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " timezoneOffsetSeconds");
            }
            if (strM.isEmpty()) {
                return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo);
            }
            throw new IllegalStateException("Missing required properties:".concat(strM));
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventCode(@Nullable Integer num) {
            this.eventCode = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventTimeMs(long j) {
            this.eventTimeMs = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventUptimeMs(long j) {
            this.eventUptimeMs = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setNetworkConnectionInfo(@Nullable NetworkConnectionInfo networkConnectionInfo) {
            this.networkConnectionInfo = networkConnectionInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setSourceExtension(@Nullable byte[] bArr) {
            this.sourceExtension = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setSourceExtensionJsonProto3(@Nullable String str) {
            this.sourceExtensionJsonProto3 = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setTimezoneOffsetSeconds(long j) {
            this.timezoneOffsetSeconds = Long.valueOf(j);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEvent)) {
            return false;
        }
        LogEvent logEvent = (LogEvent) obj;
        if (this.eventTimeMs != logEvent.getEventTimeMs()) {
            return false;
        }
        Integer num = this.eventCode;
        if (num == null) {
            if (logEvent.getEventCode() != null) {
                return false;
            }
        } else if (!num.equals(logEvent.getEventCode())) {
            return false;
        }
        if (this.eventUptimeMs != logEvent.getEventUptimeMs()) {
            return false;
        }
        if (!Arrays.equals(this.sourceExtension, logEvent instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) logEvent).sourceExtension : logEvent.getSourceExtension())) {
            return false;
        }
        String str = this.sourceExtensionJsonProto3;
        if (str == null) {
            if (logEvent.getSourceExtensionJsonProto3() != null) {
                return false;
            }
        } else if (!str.equals(logEvent.getSourceExtensionJsonProto3())) {
            return false;
        }
        if (this.timezoneOffsetSeconds != logEvent.getTimezoneOffsetSeconds()) {
            return false;
        }
        NetworkConnectionInfo networkConnectionInfo = this.networkConnectionInfo;
        return networkConnectionInfo == null ? logEvent.getNetworkConnectionInfo() == null : networkConnectionInfo.equals(logEvent.getNetworkConnectionInfo());
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    @Nullable
    public Integer getEventCode() {
        return this.eventCode;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    @Nullable
    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    @Nullable
    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    @Nullable
    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    public int hashCode() {
        long j = this.eventTimeMs;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.eventCode;
        int iHashCode = num == null ? 0 : num.hashCode();
        long j2 = this.eventUptimeMs;
        int iHashCode2 = (((((i ^ iHashCode) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003;
        String str = this.sourceExtensionJsonProto3;
        int iHashCode3 = str == null ? 0 : str.hashCode();
        long j3 = this.timezoneOffsetSeconds;
        int i2 = (((iHashCode2 ^ iHashCode3) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo = this.networkConnectionInfo;
        return i2 ^ (networkConnectionInfo != null ? networkConnectionInfo.hashCode() : 0);
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + "}";
    }

    public AutoValue_LogEvent(long j, @Nullable Integer num, long j2, @Nullable byte[] bArr, @Nullable String str, long j3, @Nullable NetworkConnectionInfo networkConnectionInfo) {
        this.eventTimeMs = j;
        this.eventCode = num;
        this.eventUptimeMs = j2;
        this.sourceExtension = bArr;
        this.sourceExtensionJsonProto3 = str;
        this.timezoneOffsetSeconds = j3;
        this.networkConnectionInfo = networkConnectionInfo;
    }
}
