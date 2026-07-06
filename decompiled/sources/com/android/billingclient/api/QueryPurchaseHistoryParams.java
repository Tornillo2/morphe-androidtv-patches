package com.android.billingclient.api;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class QueryPurchaseHistoryParams {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public String zza;

        public Builder() {
            throw null;
        }

        @NonNull
        public QueryPurchaseHistoryParams build() {
            if (this.zza != null) {
                return new QueryPurchaseHistoryParams();
            }
            throw new IllegalArgumentException("Product type must be set");
        }

        @NonNull
        public Builder setProductType(@NonNull String str) {
            this.zza = str;
            return this;
        }

        public /* synthetic */ Builder(zzde zzdeVar) {
        }
    }

    public /* synthetic */ QueryPurchaseHistoryParams(Builder builder, zzde zzdeVar) {
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }
}
