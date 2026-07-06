package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.AutoValue_LogRequest;
import com.google.auto.value.AutoValue;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class LogRequest {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AutoValue.Builder
    public static abstract class Builder {
        @NonNull
        public abstract LogRequest build();

        @NonNull
        public abstract Builder setClientInfo(@Nullable ClientInfo clientInfo);

        @NonNull
        public abstract Builder setLogEvents(@Nullable List<LogEvent> list);

        @NonNull
        public abstract Builder setLogSource(@Nullable Integer num);

        @NonNull
        public abstract Builder setLogSourceName(@Nullable String str);

        @NonNull
        public abstract Builder setQosTier(@Nullable QosTier qosTier);

        @NonNull
        public abstract Builder setRequestTimeMs(long j);

        @NonNull
        public abstract Builder setRequestUptimeMs(long j);

        @NonNull
        public Builder setSource(int i) {
            return setLogSource(Integer.valueOf(i));
        }

        @NonNull
        public Builder setSource(@NonNull String str) {
            return setLogSourceName(str);
        }
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_LogRequest.Builder();
    }

    @Nullable
    public abstract ClientInfo getClientInfo();

    @Nullable
    @Encodable.Field(name = "logEvent")
    public abstract List<LogEvent> getLogEvents();

    @Nullable
    public abstract Integer getLogSource();

    @Nullable
    public abstract String getLogSourceName();

    @Nullable
    public abstract QosTier getQosTier();

    public abstract long getRequestTimeMs();

    public abstract long getRequestUptimeMs();
}
