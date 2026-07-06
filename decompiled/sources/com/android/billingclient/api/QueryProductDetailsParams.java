package com.android.billingclient.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class QueryProductDetailsParams {

    @Nullable
    public final String zza;
    public final com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zzb;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zza;

        @Nullable
        public String zzb;

        public Builder() {
            throw null;
        }

        @NonNull
        public QueryProductDetailsParams build() {
            if (this.zza != null) {
                return new QueryProductDetailsParams(this, null);
            }
            throw new IllegalArgumentException("Product list must be set to a non empty list.");
        }

        @NonNull
        public Builder setProductList(@NonNull List<Product> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Product list cannot be empty.");
            }
            HashSet hashSet = new HashSet();
            for (Product product : list) {
                if (!"play_pass_subs".equals(product.zzb)) {
                    hashSet.add(product.zzb);
                }
            }
            if (hashSet.size() > 1) {
                throw new IllegalArgumentException("All products should be of the same product type.");
            }
            this.zza = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzj(list);
            return this;
        }

        @NonNull
        @zzi
        public Builder setProductPackageName(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        public /* synthetic */ Builder(zzdd zzddVar) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Product {
        public final String zza;
        public final String zzb;

        @Nullable
        public final String zzc;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class Builder {
            public String zza;
            public String zzb;

            @Nullable
            public String zzc;

            public Builder() {
                throw null;
            }

            @NonNull
            public Product build() {
                if ("first_party".equals(this.zzb)) {
                    throw new IllegalArgumentException("Serialized doc id must be provided for first party products.");
                }
                if (this.zza == null) {
                    throw new IllegalArgumentException("Product id must be provided.");
                }
                if (this.zzb != null) {
                    return new Product(this, null);
                }
                throw new IllegalArgumentException("Product type must be provided.");
            }

            @NonNull
            @zzs
            public Builder setOfferToken(@NonNull String str) {
                this.zzc = str;
                return this;
            }

            @NonNull
            public Builder setProductId(@NonNull String str) {
                this.zza = str;
                return this;
            }

            @NonNull
            public Builder setProductType(@NonNull String str) {
                this.zzb = str;
                return this;
            }

            public /* synthetic */ Builder(zzdd zzddVar) {
            }
        }

        public /* synthetic */ Product(Builder builder, zzdd zzddVar) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
            this.zzc = builder.zzc;
        }

        @NonNull
        public static Builder newBuilder() {
            return new Builder();
        }

        @Nullable
        public final String zza() {
            return this.zzc;
        }

        @NonNull
        public final String zzb() {
            return this.zza;
        }

        @NonNull
        public final String zzc() {
            return this.zzb;
        }
    }

    public /* synthetic */ QueryProductDetailsParams(Builder builder, zzdd zzddVar) {
        this.zzb = builder.zza;
        this.zza = builder.zzb;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public final com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zza() {
        return this.zzb;
    }

    @NonNull
    public final String zzb() {
        return ((Product) this.zzb.get(0)).zzb;
    }
}
