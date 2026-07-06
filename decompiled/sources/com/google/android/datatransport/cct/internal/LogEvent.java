package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.AutoValue_LogEvent;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class LogEvent {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AutoValue.Builder
    public static abstract class Builder {
        @NonNull
        public abstract LogEvent build();

        @NonNull
        public abstract Builder setEventCode(@Nullable Integer num);

        @NonNull
        public abstract Builder setEventTimeMs(long j);

        @NonNull
        public abstract Builder setEventUptimeMs(long j);

        @NonNull
        public abstract Builder setNetworkConnectionInfo(@Nullable NetworkConnectionInfo networkConnectionInfo);

        @NonNull
        public abstract Builder setSourceExtension(@Nullable byte[] bArr);

        @NonNull
        public abstract Builder setSourceExtensionJsonProto3(@Nullable String str);

        @NonNull
        public abstract Builder setTimezoneOffsetSeconds(long j);
    }

    public static Builder builder() {
        return new AutoValue_LogEvent.Builder();
    }

    @NonNull
    public static Builder jsonBuilder(@NonNull String str) {
        AutoValue_LogEvent.Builder builder = new AutoValue_LogEvent.Builder();
        builder.sourceExtensionJsonProto3 = str;
        return builder;
    }

    @NonNull
    public static Builder protoBuilder(@NonNull byte[] bArr) {
        AutoValue_LogEvent.Builder builder = new AutoValue_LogEvent.Builder();
        builder.sourceExtension = bArr;
        return builder;
    }

    @Nullable
    public abstract Integer getEventCode();

    public abstract long getEventTimeMs();

    public abstract long getEventUptimeMs();

    @Nullable
    public abstract NetworkConnectionInfo getNetworkConnectionInfo();

    @Nullable
    public abstract byte[] getSourceExtension();

    @Nullable
    public abstract String getSourceExtensionJsonProto3();

    public abstract long getTimezoneOffsetSeconds();
}
