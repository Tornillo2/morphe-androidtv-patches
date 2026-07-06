package com.google.android.datatransport.runtime;

import androidx.annotation.Nullable;
import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import com.google.auto.value.AutoValue;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class EventInternal {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AutoValue.Builder
    public static abstract class Builder {
        public final Builder addMetadata(String str, String str2) {
            getAutoMetadata().put(str, str2);
            return this;
        }

        public abstract EventInternal build();

        public abstract Map<String, String> getAutoMetadata();

        public abstract Builder setAutoMetadata(Map<String, String> map);

        public abstract Builder setCode(Integer num);

        public abstract Builder setEncodedPayload(EncodedPayload encodedPayload);

        public abstract Builder setEventMillis(long j);

        public abstract Builder setTransportName(String str);

        public abstract Builder setUptimeMillis(long j);

        public final Builder addMetadata(String str, long j) {
            getAutoMetadata().put(str, String.valueOf(j));
            return this;
        }

        public final Builder addMetadata(String str, int i) {
            getAutoMetadata().put(str, String.valueOf(i));
            return this;
        }
    }

    public static Builder builder() {
        AutoValue_EventInternal.Builder builder = new AutoValue_EventInternal.Builder();
        builder.autoMetadata = new HashMap();
        return builder;
    }

    public final String get(String str) {
        String str2 = getAutoMetadata().get(str);
        return str2 == null ? "" : str2;
    }

    public abstract Map<String, String> getAutoMetadata();

    @Nullable
    public abstract Integer getCode();

    public abstract EncodedPayload getEncodedPayload();

    public abstract long getEventMillis();

    public final int getInteger(String str) {
        String str2 = getAutoMetadata().get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public final long getLong(String str) {
        String str2 = getAutoMetadata().get(str);
        if (str2 == null) {
            return 0L;
        }
        return Long.valueOf(str2).longValue();
    }

    public final Map<String, String> getMetadata() {
        return DesugarCollections.unmodifiableMap(getAutoMetadata());
    }

    public final String getOrDefault(String str, String str2) {
        String str3 = getAutoMetadata().get(str);
        return str3 == null ? str2 : str3;
    }

    @Deprecated
    public byte[] getPayload() {
        return getEncodedPayload().bytes;
    }

    public abstract String getTransportName();

    public abstract long getUptimeMillis();

    public Builder toBuilder() {
        AutoValue_EventInternal.Builder builder = new AutoValue_EventInternal.Builder();
        builder.setTransportName(getTransportName());
        builder.code = getCode();
        builder.setEncodedPayload(getEncodedPayload());
        builder.setEventMillis(getEventMillis());
        builder.setUptimeMillis(getUptimeMillis());
        builder.autoMetadata = new HashMap(getAutoMetadata());
        return builder;
    }
}
