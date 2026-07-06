package com.android.billingclient.api;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BillingResult {
    public int zza;
    public int zzb;
    public String zzc;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public int zza;
        public int zzb = 0;
        public String zzc = "";

        public Builder() {
        }

        @NonNull
        public BillingResult build() {
            BillingResult billingResult = new BillingResult();
            billingResult.zza = this.zza;
            billingResult.zzb = this.zzb;
            billingResult.zzc = this.zzc;
            return billingResult;
        }

        @NonNull
        public Builder setDebugMessage(@NonNull String str) {
            this.zzc = str;
            return this;
        }

        @NonNull
        @zzn
        public Builder setOnPurchasesUpdatedSubResponseCode(int i) {
            this.zzb = i;
            return this;
        }

        @NonNull
        public Builder setResponseCode(int i) {
            this.zza = i;
            return this;
        }

        public /* synthetic */ Builder(zzco zzcoVar) {
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    @NonNull
    public String getDebugMessage() {
        return this.zzc;
    }

    @zzn
    public int getOnPurchasesUpdatedSubResponseCode() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }

    @NonNull
    public String toString() {
        return "Response Code: " + com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzk(this.zza) + ", Debug Message: " + this.zzc;
    }
}
