package com.android.billingclient.api;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@zzr
public final class PendingPurchasesParams {
    public final boolean enableOneTimeProducts;
    public final boolean enablePrepaidPlans;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @zzr
    public static final class Builder {
        public boolean enableOneTimeProducts;
        public boolean enablePrepaidPlans;

        public Builder() {
        }

        @NonNull
        public PendingPurchasesParams build() {
            if (this.enableOneTimeProducts) {
                return new PendingPurchasesParams(true, this.enablePrepaidPlans);
            }
            throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
        }

        @NonNull
        public Builder enableOneTimeProducts() {
            this.enableOneTimeProducts = true;
            return this;
        }

        @NonNull
        public Builder enablePrepaidPlans() {
            this.enablePrepaidPlans = true;
            return this;
        }

        public Builder(zzcx zzcxVar) {
        }
    }

    public PendingPurchasesParams(boolean z, boolean z2) {
        this.enableOneTimeProducts = z;
        this.enablePrepaidPlans = z2;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isEnabledForOneTimeProducts() {
        return this.enableOneTimeProducts;
    }

    public boolean isEnabledForPrepaidPlans() {
        return this.enablePrepaidPlans;
    }
}
