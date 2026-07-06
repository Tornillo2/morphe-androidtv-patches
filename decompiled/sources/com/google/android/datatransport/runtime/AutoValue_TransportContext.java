package com.google.android.datatransport.runtime;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_TransportContext extends TransportContext {
    public final String backendName;
    public final byte[] extras;
    public final Priority priority;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends TransportContext.Builder {
        public String backendName;
        public byte[] extras;
        public Priority priority;

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext build() {
            String strM = this.backendName == null ? " backendName" : "";
            if (this.priority == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " priority");
            }
            if (strM.isEmpty()) {
                return new AutoValue_TransportContext(this.backendName, this.extras, this.priority);
            }
            throw new IllegalStateException("Missing required properties:".concat(strM));
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setBackendName(String str) {
            if (str == null) {
                throw new NullPointerException("Null backendName");
            }
            this.backendName = str;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setExtras(@Nullable byte[] bArr) {
            this.extras = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setPriority(Priority priority) {
            if (priority == null) {
                throw new NullPointerException("Null priority");
            }
            this.priority = priority;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransportContext)) {
            return false;
        }
        TransportContext transportContext = (TransportContext) obj;
        if (this.backendName.equals(transportContext.getBackendName())) {
            return Arrays.equals(this.extras, transportContext instanceof AutoValue_TransportContext ? ((AutoValue_TransportContext) transportContext).extras : transportContext.getExtras()) && this.priority.equals(transportContext.getPriority());
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public String getBackendName() {
        return this.backendName;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    @Nullable
    public byte[] getExtras() {
        return this.extras;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Priority getPriority() {
        return this.priority;
    }

    public int hashCode() {
        return ((((this.backendName.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.extras)) * 1000003) ^ this.priority.hashCode();
    }

    public AutoValue_TransportContext(String str, @Nullable byte[] bArr, Priority priority) {
        this.backendName = str;
        this.extras = bArr;
        this.priority = priority;
    }
}
