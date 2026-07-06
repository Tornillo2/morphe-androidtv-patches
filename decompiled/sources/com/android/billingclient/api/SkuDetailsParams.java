package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class SkuDetailsParams {
    public String zza;
    public List zzb;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public String zza;
        public List zzb;

        public Builder() {
            throw null;
        }

        @NonNull
        public SkuDetailsParams build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            List list = this.zzb;
            if (list == null) {
                throw new IllegalArgumentException("SKU list must be set");
            }
            SkuDetailsParams skuDetailsParams = new SkuDetailsParams();
            skuDetailsParams.zza = str;
            skuDetailsParams.zzb = list;
            return skuDetailsParams;
        }

        @NonNull
        public Builder setSkusList(@NonNull List<String> list) {
            this.zzb = new ArrayList(list);
            return this;
        }

        @NonNull
        public Builder setType(@NonNull String str) {
            this.zza = str;
            return this;
        }

        public /* synthetic */ Builder(zzdg zzdgVar) {
        }

        @NonNull
        @zzi
        public Builder setSkuPackageName(@NonNull String str) {
            return this;
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    public String getSkuType() {
        return this.zza;
    }

    @NonNull
    public List<String> getSkusList() {
        return this.zzb;
    }
}
