package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_LogRequest extends LogRequest {
    public final ClientInfo clientInfo;
    public final List<LogEvent> logEvents;
    public final Integer logSource;
    public final String logSourceName;
    public final QosTier qosTier;
    public final long requestTimeMs;
    public final long requestUptimeMs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends LogRequest.Builder {
        public ClientInfo clientInfo;
        public List<LogEvent> logEvents;
        public Integer logSource;
        public String logSourceName;
        public QosTier qosTier;
        public Long requestTimeMs;
        public Long requestUptimeMs;

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest build() {
            String strM = this.requestTimeMs == null ? " requestTimeMs" : "";
            if (this.requestUptimeMs == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " requestUptimeMs");
            }
            if (strM.isEmpty()) {
                return new AutoValue_LogRequest(this.requestTimeMs.longValue(), this.requestUptimeMs.longValue(), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier);
            }
            throw new IllegalStateException("Missing required properties:".concat(strM));
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setClientInfo(@Nullable ClientInfo clientInfo) {
            this.clientInfo = clientInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogEvents(@Nullable List<LogEvent> list) {
            this.logEvents = list;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogSource(@Nullable Integer num) {
            this.logSource = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogSourceName(@Nullable String str) {
            this.logSourceName = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setQosTier(@Nullable QosTier qosTier) {
            this.qosTier = qosTier;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setRequestTimeMs(long j) {
            this.requestTimeMs = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setRequestUptimeMs(long j) {
            this.requestUptimeMs = Long.valueOf(j);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogRequest)) {
            return false;
        }
        LogRequest logRequest = (LogRequest) obj;
        if (this.requestTimeMs != logRequest.getRequestTimeMs() || this.requestUptimeMs != logRequest.getRequestUptimeMs()) {
            return false;
        }
        ClientInfo clientInfo = this.clientInfo;
        if (clientInfo == null) {
            if (logRequest.getClientInfo() != null) {
                return false;
            }
        } else if (!clientInfo.equals(logRequest.getClientInfo())) {
            return false;
        }
        Integer num = this.logSource;
        if (num == null) {
            if (logRequest.getLogSource() != null) {
                return false;
            }
        } else if (!num.equals(logRequest.getLogSource())) {
            return false;
        }
        String str = this.logSourceName;
        if (str == null) {
            if (logRequest.getLogSourceName() != null) {
                return false;
            }
        } else if (!str.equals(logRequest.getLogSourceName())) {
            return false;
        }
        List<LogEvent> list = this.logEvents;
        if (list == null) {
            if (logRequest.getLogEvents() != null) {
                return false;
            }
        } else if (!list.equals(logRequest.getLogEvents())) {
            return false;
        }
        QosTier qosTier = this.qosTier;
        return qosTier == null ? logRequest.getQosTier() == null : qosTier.equals(logRequest.getQosTier());
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    @Nullable
    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    @Nullable
    @Encodable.Field(name = "logEvent")
    public List<LogEvent> getLogEvents() {
        return this.logEvents;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    @Nullable
    public Integer getLogSource() {
        return this.logSource;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    @Nullable
    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    @Nullable
    public QosTier getQosTier() {
        return this.qosTier;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    public int hashCode() {
        long j = this.requestTimeMs;
        long j2 = this.requestUptimeMs;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        ClientInfo clientInfo = this.clientInfo;
        int iHashCode = (i ^ (clientInfo == null ? 0 : clientInfo.hashCode())) * 1000003;
        Integer num = this.logSource;
        int iHashCode2 = (iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.logSourceName;
        int iHashCode3 = (iHashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<LogEvent> list = this.logEvents;
        int iHashCode4 = (iHashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        QosTier qosTier = this.qosTier;
        return iHashCode4 ^ (qosTier != null ? qosTier.hashCode() : 0);
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + "}";
    }

    public AutoValue_LogRequest(long j, long j2, @Nullable ClientInfo clientInfo, @Nullable Integer num, @Nullable String str, @Nullable List<LogEvent> list, @Nullable QosTier qosTier) {
        this.requestTimeMs = j;
        this.requestUptimeMs = j2;
        this.clientInfo = clientInfo;
        this.logSource = num;
        this.logSourceName = str;
        this.logEvents = list;
        this.qosTier = qosTier;
    }
}
