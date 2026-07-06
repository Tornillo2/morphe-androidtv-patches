package com.android.billingclient.api;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ConsumeParams {
    public String zza;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public String zza;

        public Builder() {
            throw null;
        }

        @NonNull
        public ConsumeParams build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            ConsumeParams consumeParams = new ConsumeParams();
            consumeParams.zza = str;
            return consumeParams;
        }

        @NonNull
        public Builder setPurchaseToken(@NonNull String str) {
            this.zza = str;
            return this;
        }

        public /* synthetic */ Builder(zzcq zzcqVar) {
        }
    }

    public ConsumeParams() {
        throw null;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    public String getPurchaseToken() {
        return this.zza;
    }

    public /* synthetic */ ConsumeParams(zzcq zzcqVar) {
    }
}
