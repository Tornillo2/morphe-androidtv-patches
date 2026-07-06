package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.billingclient.api.ProductDetails;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BillingFlowParams {
    public boolean zza;
    public String zzb;
    public String zzc;
    public SubscriptionUpdateParams zzd;
    public com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zze;
    public ArrayList zzf;
    public boolean zzg;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ProductDetailsParams {

        @Nullable
        @zzg
        public final Integer mAutoPayBalanceThreshold;
        public final ProductDetails zza;

        @Nullable
        public final String zzb;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class Builder {

            @Nullable
            @zzg
            public Integer mAutoPayBalanceThreshold;
            public ProductDetails zza;

            @Nullable
            public String zzb;

            public Builder() {
                throw null;
            }

            @NonNull
            public ProductDetailsParams build() {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbe.zzc(this.zza, "ProductDetails is required for constructing ProductDetailsParams.");
                return new ProductDetailsParams(this, null);
            }

            @NonNull
            @zzg
            public Builder setAutoPayBalanceThreshold(@NonNull Integer num) {
                this.mAutoPayBalanceThreshold = num;
                return this;
            }

            @NonNull
            public Builder setOfferToken(@NonNull String str) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("offerToken can not be empty");
                }
                this.zzb = str;
                return this;
            }

            @NonNull
            public Builder setProductDetails(@NonNull ProductDetails productDetails) {
                this.zza = productDetails;
                if (productDetails.getOneTimePurchaseOfferDetails() != null) {
                    productDetails.getOneTimePurchaseOfferDetails().getClass();
                    String str = productDetails.getOneTimePurchaseOfferDetails().zzd;
                    if (str != null) {
                        this.zzb = str;
                    }
                }
                return this;
            }

            public /* synthetic */ Builder(zzcl zzclVar) {
            }
        }

        public /* synthetic */ ProductDetailsParams(Builder builder, zzcl zzclVar) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
            this.mAutoPayBalanceThreshold = builder.mAutoPayBalanceThreshold;
        }

        @NonNull
        public static Builder newBuilder() {
            return new Builder();
        }

        @Nullable
        @zzg
        public Integer getAutoPayBalanceThreshold() {
            return this.mAutoPayBalanceThreshold;
        }

        @NonNull
        public final ProductDetails zza() {
            return this.zza;
        }

        @Nullable
        public final String zzb() {
            return this.zzb;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SubscriptionUpdateParams {
        public String zza;
        public String zzb;
        public int zzc = 0;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class Builder {
            public String zza;
            public String zzb;
            public boolean zzc;
            public int zzd = 0;

            public Builder() {
            }

            public static /* synthetic */ Builder zza(Builder builder) {
                builder.zzc = true;
                return builder;
            }

            @NonNull
            public SubscriptionUpdateParams build() {
                boolean z = true;
                zzcl zzclVar = null;
                if (TextUtils.isEmpty(this.zza) && TextUtils.isEmpty(null)) {
                    z = false;
                }
                boolean zIsEmpty = TextUtils.isEmpty(this.zzb);
                if (z && !zIsEmpty) {
                    throw new IllegalArgumentException("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                }
                if (!this.zzc && !z && zIsEmpty) {
                    throw new IllegalArgumentException("Old SKU purchase information(token/id) or original external transaction id must be provided.");
                }
                SubscriptionUpdateParams subscriptionUpdateParams = new SubscriptionUpdateParams(zzclVar);
                subscriptionUpdateParams.zza = this.zza;
                subscriptionUpdateParams.zzc = this.zzd;
                subscriptionUpdateParams.zzb = this.zzb;
                return subscriptionUpdateParams;
            }

            @NonNull
            public Builder setOldPurchaseToken(@NonNull String str) {
                this.zza = str;
                return this;
            }

            @NonNull
            @zze
            public Builder setOriginalExternalTransactionId(@NonNull String str) {
                this.zzb = str;
                return this;
            }

            @NonNull
            public Builder setSubscriptionReplacementMode(int i) {
                this.zzd = i;
                return this;
            }

            @NonNull
            @Deprecated
            public final Builder zzb(@NonNull String str) {
                this.zza = str;
                return this;
            }

            public /* synthetic */ Builder(zzcl zzclVar) {
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        public @interface ReplacementMode {
            public static final int CHARGE_FULL_PRICE = 5;
            public static final int CHARGE_PRORATED_PRICE = 2;
            public static final int DEFERRED = 6;
            public static final int UNKNOWN_REPLACEMENT_MODE = 0;
            public static final int WITHOUT_PRORATION = 3;
            public static final int WITH_TIME_PRORATION = 1;
        }

        public SubscriptionUpdateParams() {
        }

        @NonNull
        public static Builder newBuilder() {
            return new Builder(null);
        }

        public static Builder zzb(SubscriptionUpdateParams subscriptionUpdateParams) {
            Builder builderNewBuilder = newBuilder();
            builderNewBuilder.zza = subscriptionUpdateParams.zza;
            builderNewBuilder.zzd = subscriptionUpdateParams.zzc;
            builderNewBuilder.zzb = subscriptionUpdateParams.zzb;
            return builderNewBuilder;
        }

        public final int zza() {
            return this.zzc;
        }

        public final String zzc() {
            return this.zza;
        }

        public final String zzd() {
            return this.zzb;
        }

        public /* synthetic */ SubscriptionUpdateParams(zzcl zzclVar) {
        }
    }

    public BillingFlowParams() {
        throw null;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    public int zza() {
        return 0;
    }

    public final int zzb() {
        return this.zzd.zzc;
    }

    public long zzc() {
        return 0L;
    }

    public final BillingResult zzd() {
        ProductDetails.OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails;
        Integer num;
        if (this.zze.isEmpty()) {
            return zzcp.zzi;
        }
        ProductDetailsParams productDetailsParams = (ProductDetailsParams) this.zze.get(0);
        for (int i = 1; i < this.zze.size(); i++) {
            ProductDetailsParams productDetailsParams2 = (ProductDetailsParams) this.zze.get(i);
            if (!productDetailsParams2.zza.zzd.equals(productDetailsParams.zza.zzd) && !productDetailsParams2.zza.zzd.equals("play_pass_subs")) {
                return zzcp.zza(5, "All products should have same ProductType.");
            }
        }
        String packageName = productDetailsParams.zza.getPackageName();
        HashMap map = new HashMap();
        HashSet<String> hashSet = new HashSet();
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zzbrVar = this.zze;
        int size = zzbrVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            ProductDetailsParams productDetailsParams3 = (ProductDetailsParams) zzbrVar.get(i2);
            ProductDetails productDetails = productDetailsParams3.zza;
            if (productDetails.zzl != null && productDetailsParams3.zzb == null) {
                return zzcp.zza(5, String.format("offerToken is required for constructing ProductDetailsParams for subscriptions. Missing value for product id: %s", productDetails.zzc));
            }
            if (map.containsKey(productDetails.zzc)) {
                return zzcp.zza(5, String.format("ProductId can not be duplicated. Invalid product id: %s.", productDetailsParams3.zza.zzc));
            }
            map.put(productDetailsParams3.zza.zzc, productDetailsParams3);
            if (!productDetailsParams.zza.zzd.equals("play_pass_subs") && !productDetailsParams3.zza.zzd.equals("play_pass_subs") && !packageName.equals(productDetailsParams3.zza.getPackageName())) {
                return zzcp.zza(5, "All products must have the same package name.");
            }
        }
        for (String str : hashSet) {
            if (map.containsKey(str)) {
                return zzcp.zza(5, String.format("OldProductId must not be one of the products to be purchased. Invalid old product id: %s.", str));
            }
        }
        List list = productDetailsParams.zza.zzm;
        String str2 = productDetailsParams.zzb;
        if (str2 != null && list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    oneTimePurchaseOfferDetails = null;
                    break;
                }
                oneTimePurchaseOfferDetails = (ProductDetails.OneTimePurchaseOfferDetails) it.next();
                if (str2.equals(oneTimePurchaseOfferDetails.zzd)) {
                    break;
                }
            }
            if (oneTimePurchaseOfferDetails != null && (((num = productDetailsParams.mAutoPayBalanceThreshold) != null && oneTimePurchaseOfferDetails.zzo == null) || (num == null && oneTimePurchaseOfferDetails.zzo != null))) {
                return zzcp.zza(5, "Both autoPayDetails and autoPayBalanceThreshold is required for constructing ProductDetailsParams for autopay.");
            }
        }
        return zzcp.zzi;
    }

    @Nullable
    public final String zze() {
        return this.zzb;
    }

    @Nullable
    public final String zzf() {
        return this.zzc;
    }

    @Nullable
    public String zzg() {
        return null;
    }

    @Nullable
    public final String zzh() {
        return this.zzd.zza;
    }

    @Nullable
    public final String zzi() {
        return this.zzd.zzb;
    }

    @NonNull
    public final ArrayList zzj() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzf);
        return arrayList;
    }

    @NonNull
    public final List zzk() {
        return this.zze;
    }

    public final boolean zzs() {
        return this.zzg;
    }

    public final boolean zzt() {
        if (this.zzb != null || this.zzc != null) {
            return true;
        }
        SubscriptionUpdateParams subscriptionUpdateParams = this.zzd;
        if (subscriptionUpdateParams.zzb != null || subscriptionUpdateParams.zzc != 0 || this.zza || this.zzg) {
            return true;
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zzbrVar = this.zze;
        if (zzbrVar != null) {
            int size = zzbrVar.size();
            for (int i = 0; i < size; i++) {
            }
        }
        return false;
    }

    public /* synthetic */ BillingFlowParams(zzcl zzclVar) {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public String zza;
        public String zzb;
        public List zzc;
        public ArrayList zzd;
        public boolean zze;
        public SubscriptionUpdateParams.Builder zzf;

        public Builder() {
            SubscriptionUpdateParams.Builder builderNewBuilder = SubscriptionUpdateParams.newBuilder();
            builderNewBuilder.zzc = true;
            this.zzf = builderNewBuilder;
        }

        @NonNull
        public BillingFlowParams build() {
            ArrayList arrayList = this.zzd;
            boolean z = true;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            List list = this.zzc;
            boolean z3 = (list == null || list.isEmpty()) ? false : true;
            if (!z2 && !z3) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            }
            if (z2 && z3) {
                throw new IllegalArgumentException("Set SkuDetails or ProductDetailsParams, not both.");
            }
            if (!z2) {
                List list2 = this.zzc;
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        if (((ProductDetailsParams) it.next()) == null) {
                            throw new IllegalArgumentException("ProductDetailsParams cannot be null.");
                        }
                    }
                }
            } else {
                if (this.zzd.contains(null)) {
                    throw new IllegalArgumentException("SKU cannot be null.");
                }
                if (this.zzd.size() > 1) {
                    SkuDetails skuDetails = (SkuDetails) this.zzd.get(0);
                    String type = skuDetails.getType();
                    ArrayList arrayList2 = this.zzd;
                    int size = arrayList2.size();
                    for (int i = 0; i < size; i++) {
                        SkuDetails skuDetails2 = (SkuDetails) arrayList2.get(i);
                        if (!type.equals("play_pass_subs") && !skuDetails2.getType().equals("play_pass_subs") && !type.equals(skuDetails2.getType())) {
                            throw new IllegalArgumentException("SKUs should have the same type.");
                        }
                    }
                    String packageName = skuDetails.getPackageName();
                    ArrayList arrayList3 = this.zzd;
                    int size2 = arrayList3.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        SkuDetails skuDetails3 = (SkuDetails) arrayList3.get(i2);
                        if (!type.equals("play_pass_subs") && !skuDetails3.getType().equals("play_pass_subs") && !packageName.equals(skuDetails3.getPackageName())) {
                            throw new IllegalArgumentException("All SKUs must have the same package name.");
                        }
                    }
                }
            }
            BillingFlowParams billingFlowParams = new BillingFlowParams();
            if ((!z2 || ((SkuDetails) this.zzd.get(0)).getPackageName().isEmpty()) && (!z3 || ((ProductDetailsParams) this.zzc.get(0)).zza.getPackageName().isEmpty())) {
                z = false;
            }
            billingFlowParams.zza = z;
            billingFlowParams.zzb = this.zza;
            billingFlowParams.zzc = this.zzb;
            billingFlowParams.zzd = this.zzf.build();
            ArrayList arrayList4 = this.zzd;
            billingFlowParams.zzf = arrayList4 != null ? new ArrayList(arrayList4) : new ArrayList();
            billingFlowParams.zzg = this.zze;
            List list3 = this.zzc;
            billingFlowParams.zze = list3 != null ? com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzj(list3) : com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk();
            return billingFlowParams;
        }

        @NonNull
        public Builder setIsOfferPersonalized(boolean z) {
            this.zze = z;
            return this;
        }

        @NonNull
        public Builder setObfuscatedAccountId(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setObfuscatedProfileId(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setProductDetailsParamsList(@NonNull List<ProductDetailsParams> list) {
            this.zzc = new ArrayList(list);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSkuDetails(@NonNull SkuDetails skuDetails) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(skuDetails);
            this.zzd = arrayList;
            return this;
        }

        @NonNull
        public Builder setSubscriptionUpdateParams(@NonNull SubscriptionUpdateParams subscriptionUpdateParams) {
            this.zzf = SubscriptionUpdateParams.zzb(subscriptionUpdateParams);
            return this;
        }

        public /* synthetic */ Builder(zzcl zzclVar) {
            SubscriptionUpdateParams.Builder builderNewBuilder = SubscriptionUpdateParams.newBuilder();
            builderNewBuilder.zzc = true;
            this.zzf = builderNewBuilder;
        }
    }
}
