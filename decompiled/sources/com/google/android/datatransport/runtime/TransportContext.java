package com.google.android.datatransport.runtime;

import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.AutoValue_TransportContext;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class TransportContext {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AutoValue.Builder
    public static abstract class Builder {
        public abstract TransportContext build();

        public abstract Builder setBackendName(String str);

        public abstract Builder setExtras(@Nullable byte[] bArr);

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public abstract Builder setPriority(Priority priority);
    }

    public static Builder builder() {
        AutoValue_TransportContext.Builder builder = new AutoValue_TransportContext.Builder();
        builder.setPriority(Priority.DEFAULT);
        return builder;
    }

    public abstract String getBackendName();

    @Nullable
    public abstract byte[] getExtras();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract Priority getPriority();

    public boolean shouldUploadClientHealthMetrics() {
        return getExtras() != null;
    }

    public final String toString() {
        return String.format("TransportContext(%s, %s, %s)", getBackendName(), getPriority(), getExtras() == null ? "" : Base64.encodeToString(getExtras(), 2));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public TransportContext withPriority(Priority priority) {
        Builder builder = builder();
        builder.setBackendName(getBackendName());
        builder.setPriority(priority);
        ((AutoValue_TransportContext.Builder) builder).extras = getExtras();
        return builder.build();
    }
}
