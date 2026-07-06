package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ProductDetails {
    public final String zza;
    public final JSONObject zzb;
    public final String zzc;
    public final String zzd;
    public final String zze;
    public final String zzf;
    public final String zzg;
    public final String zzh;
    public final String zzi;
    public final String zzj;

    @Nullable
    public final String zzk;

    @Nullable
    public final List zzl;

    @Nullable
    public final List zzm;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @zzm
    public static final class InstallmentPlanDetails {
        public final int commitmentPaymentsCount;
        public final int subsequentCommitmentPaymentsCount;

        public InstallmentPlanDetails(JSONObject jSONObject) throws JSONException {
            this.commitmentPaymentsCount = jSONObject.getInt("commitmentPaymentsCount");
            this.subsequentCommitmentPaymentsCount = jSONObject.optInt("subsequentCommitmentPaymentsCount");
        }

        @zzm
        public int getInstallmentPlanCommitmentPaymentsCount() {
            return this.commitmentPaymentsCount;
        }

        @zzm
        public int getSubsequentInstallmentPlanCommitmentPaymentsCount() {
            return this.subsequentCommitmentPaymentsCount;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OneTimePurchaseOfferDetails {
        public final String zza;
        public final long zzb;
        public final String zzc;

        @Nullable
        public final String zzd;

        @Nullable
        public final String zze;

        @Nullable
        public final String zzf;
        public final List zzg;

        @Nullable
        public final Long zzh;

        @Nullable
        public final DiscountDisplayInfo zzi;

        @Nullable
        public final ValidTimeWindow zzj;

        @Nullable
        public final LimitedQuantityInfo zzk;

        @Nullable
        public final String zzl;

        @Nullable
        public final PreorderDetails zzm;

        @Nullable
        public final RentalDetails zzn;

        @Nullable
        public final AutoPayDetails zzo;

        @Nullable
        public final PricingPhases zzp;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @zzg
        public static final class AutoPayDetails {
            public final String type;

            public AutoPayDetails(JSONObject jSONObject) throws JSONException {
                this.type = jSONObject.getString("type");
            }

            @NonNull
            @zzg
            public String getType() {
                return this.type;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @zzo
        public static final class DiscountDisplayInfo {

            @Nullable
            public final Integer zza;

            @Nullable
            public final DiscountAmount zzb;

            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            @zzo
            public static final class DiscountAmount {
                public final String zza;
                public final long zzb;
                public final String zzc;

                public DiscountAmount(JSONObject jSONObject) {
                    this.zza = jSONObject.optString("formattedDiscountAmount");
                    this.zzb = jSONObject.optLong("discountAmountMicros");
                    this.zzc = jSONObject.optString("discountAmountCurrencyCode");
                }

                @NonNull
                public String getDiscountAmountCurrencyCode() {
                    return this.zzc;
                }

                public long getDiscountAmountMicros() {
                    return this.zzb;
                }

                @NonNull
                public String getFormattedDiscountAmount() {
                    return this.zza;
                }
            }

            public DiscountDisplayInfo(JSONObject jSONObject) throws JSONException {
                this.zza = jSONObject.has("percentageDiscount") ? Integer.valueOf(jSONObject.optInt("percentageDiscount")) : null;
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("discountAmount");
                this.zzb = jSONObjectOptJSONObject != null ? new DiscountAmount(jSONObjectOptJSONObject) : null;
            }

            @Nullable
            @zzo
            public DiscountAmount getDiscountAmount() {
                return this.zzb;
            }

            @Nullable
            @zzo
            public Integer getPercentageDiscount() {
                return this.zza;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @zzo
        public static final class LimitedQuantityInfo {
            public final int zza;
            public final int zzb;

            public LimitedQuantityInfo(JSONObject jSONObject) throws JSONException {
                this.zza = jSONObject.getInt("maximumQuantity");
                this.zzb = jSONObject.getInt("remainingQuantity");
            }

            @zzo
            public int getMaximumQuantity() {
                return this.zza;
            }

            @zzo
            public int getRemainingQuantity() {
                return this.zzb;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @zzp
        public static final class PreorderDetails {
            public final long preorderPresaleEndTimeMillis;
            public final long preorderReleaseTimeMillis;

            public PreorderDetails(JSONObject jSONObject) throws JSONException {
                this.preorderReleaseTimeMillis = jSONObject.getLong("preorderReleaseTimeMillis");
                this.preorderPresaleEndTimeMillis = jSONObject.getLong("preorderPresaleEndTimeMillis");
            }

            @zzp
            public long getPreorderPresaleEndTimeMillis() {
                return this.preorderPresaleEndTimeMillis;
            }

            @zzp
            public long getPreorderReleaseTimeMillis() {
                return this.preorderReleaseTimeMillis;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @zzq
        public static final class RentalDetails {

            @Nullable
            public final String rentalExpirationPeriod;
            public final String rentalPeriod;

            public RentalDetails(JSONObject jSONObject) throws JSONException {
                this.rentalPeriod = jSONObject.getString("rentalPeriod");
                String strOptString = jSONObject.optString("rentalExpirationPeriod");
                this.rentalExpirationPeriod = true == strOptString.isEmpty() ? null : strOptString;
            }

            @Nullable
            @zzq
            public String getRentalExpirationPeriod() {
                return this.rentalExpirationPeriod;
            }

            @NonNull
            @zzq
            public String getRentalPeriod() {
                return this.rentalPeriod;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @zzo
        public static final class ValidTimeWindow {

            @Nullable
            public final Long zza;

            @Nullable
            public final Long zzb;

            public ValidTimeWindow(JSONObject jSONObject) throws JSONException {
                this.zza = jSONObject.has("startTimeMillis") ? Long.valueOf(jSONObject.optLong("startTimeMillis")) : null;
                this.zzb = jSONObject.has("endTimeMillis") ? Long.valueOf(jSONObject.optLong("endTimeMillis")) : null;
            }

            @Nullable
            @zzo
            public Long getEndTimeMillis() {
                return this.zzb;
            }

            @Nullable
            @zzo
            public Long getStartTimeMillis() {
                return this.zza;
            }
        }

        public OneTimePurchaseOfferDetails(JSONObject jSONObject) throws JSONException {
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            String strOptString = jSONObject.optString("offerIdToken");
            this.zzd = true == strOptString.isEmpty() ? null : strOptString;
            String strOptString2 = jSONObject.optString("offerId");
            this.zze = true == strOptString2.isEmpty() ? null : strOptString2;
            String strOptString3 = jSONObject.optString("purchaseOptionId");
            this.zzf = true == strOptString3.isEmpty() ? null : strOptString3;
            jSONObject.optInt("offerType");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("offerTags");
            this.zzg = new ArrayList();
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    this.zzg.add(jSONArrayOptJSONArray.getString(i));
                }
            }
            this.zzh = jSONObject.has("fullPriceMicros") ? Long.valueOf(jSONObject.optLong("fullPriceMicros")) : null;
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("discountDisplayInfo");
            this.zzi = jSONObjectOptJSONObject == null ? null : new DiscountDisplayInfo(jSONObjectOptJSONObject);
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("validTimeWindow");
            this.zzj = jSONObjectOptJSONObject2 == null ? null : new ValidTimeWindow(jSONObjectOptJSONObject2);
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("limitedQuantityInfo");
            this.zzk = jSONObjectOptJSONObject3 == null ? null : new LimitedQuantityInfo(jSONObjectOptJSONObject3);
            this.zzl = jSONObject.optString("serializedDocid");
            JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("preorderDetails");
            this.zzm = jSONObjectOptJSONObject4 == null ? null : new PreorderDetails(jSONObjectOptJSONObject4);
            JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("rentalDetails");
            this.zzn = jSONObjectOptJSONObject5 == null ? null : new RentalDetails(jSONObjectOptJSONObject5);
            JSONObject jSONObjectOptJSONObject6 = jSONObject.optJSONObject("autoPayDetails");
            this.zzo = jSONObjectOptJSONObject6 == null ? null : new AutoPayDetails(jSONObjectOptJSONObject6);
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("pricingPhases");
            this.zzp = jSONArrayOptJSONArray2 != null ? new PricingPhases(jSONArrayOptJSONArray2) : null;
        }

        @Nullable
        @zzg
        public AutoPayDetails getAutoPayDetails() {
            return this.zzo;
        }

        @Nullable
        @zzo
        public DiscountDisplayInfo getDiscountDisplayInfo() {
            return this.zzi;
        }

        @NonNull
        public String getFormattedPrice() {
            return this.zza;
        }

        @Nullable
        @zzo
        public Long getFullPriceMicros() {
            return this.zzh;
        }

        @Nullable
        @zzo
        public LimitedQuantityInfo getLimitedQuantityInfo() {
            return this.zzk;
        }

        @Nullable
        @zzo
        @zzq
        public String getOfferId() {
            return this.zze;
        }

        @Nullable
        @zzo
        public List<String> getOfferTags() {
            return this.zzg;
        }

        @Nullable
        @zzo
        @zzq
        public String getOfferToken() {
            return this.zzd;
        }

        @Nullable
        @zzp
        public PreorderDetails getPreorderDetails() {
            return this.zzm;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        @NonNull
        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        @Nullable
        @zzg
        public PricingPhases getPricingPhases() {
            return this.zzp;
        }

        @Nullable
        @zzq
        public String getPurchaseOptionId() {
            return this.zzf;
        }

        @Nullable
        @zzq
        public RentalDetails getRentalDetails() {
            return this.zzn;
        }

        @Nullable
        @zzo
        public ValidTimeWindow getValidTimeWindow() {
            return this.zzj;
        }

        @Nullable
        public final String zza() {
            return this.zzl;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PricingPhase {
        public final String zza;
        public final long zzb;
        public final String zzc;
        public final String zzd;
        public final int zze;
        public final int zzf;

        public PricingPhase(JSONObject jSONObject) {
            this.zzd = jSONObject.optString("billingPeriod");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzf = jSONObject.optInt("recurrenceMode");
            this.zze = jSONObject.optInt("billingCycleCount");
        }

        public int getBillingCycleCount() {
            return this.zze;
        }

        @NonNull
        public String getBillingPeriod() {
            return this.zzd;
        }

        @NonNull
        public String getFormattedPrice() {
            return this.zza;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        @NonNull
        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public int getRecurrenceMode() {
            return this.zzf;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PricingPhases {
        public final List zza;

        public PricingPhases(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        arrayList.add(new PricingPhase(jSONObjectOptJSONObject));
                    }
                }
            }
            this.zza = arrayList;
        }

        @NonNull
        public List<PricingPhase> getPricingPhaseList() {
            return this.zza;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface RecurrenceMode {
        public static final int FINITE_RECURRING = 2;
        public static final int INFINITE_RECURRING = 1;
        public static final int NON_RECURRING = 3;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SubscriptionOfferDetails {
        public final String zza;

        @Nullable
        public final String zzb;
        public final String zzc;
        public final PricingPhases zzd;
        public final List zze;

        @Nullable
        public final InstallmentPlanDetails zzf;

        @Nullable
        public final TransitionPlanDetails zzg;

        public SubscriptionOfferDetails(JSONObject jSONObject) throws JSONException {
            this.zza = jSONObject.optString("basePlanId");
            String strOptString = jSONObject.optString("offerId");
            this.zzb = true == strOptString.isEmpty() ? null : strOptString;
            this.zzc = jSONObject.getString("offerIdToken");
            this.zzd = new PricingPhases(jSONObject.getJSONArray("pricingPhases"));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("installmentPlanDetails");
            this.zzf = jSONObjectOptJSONObject == null ? null : new InstallmentPlanDetails(jSONObjectOptJSONObject);
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("transitionPlanDetails");
            this.zzg = jSONObjectOptJSONObject2 != null ? new TransitionPlanDetails(jSONObjectOptJSONObject2) : null;
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("offerTags");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                }
            }
            this.zze = arrayList;
        }

        @NonNull
        public String getBasePlanId() {
            return this.zza;
        }

        @Nullable
        @zzm
        public InstallmentPlanDetails getInstallmentPlanDetails() {
            return this.zzf;
        }

        @Nullable
        public String getOfferId() {
            return this.zzb;
        }

        @NonNull
        public List<String> getOfferTags() {
            return this.zze;
        }

        @NonNull
        public String getOfferToken() {
            return this.zzc;
        }

        @NonNull
        public PricingPhases getPricingPhases() {
            return this.zzd;
        }

        @Nullable
        @zzw
        public TransitionPlanDetails getTransitionPlanDetails() {
            return this.zzg;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @zzw
    public static final class TransitionPlanDetails {
        public final String basePlanId;
        public final String description;
        public final String name;

        @Nullable
        public final PricingPhase pricingPhase;
        public final String productId;
        public final String title;

        public TransitionPlanDetails(JSONObject jSONObject) throws JSONException {
            this.productId = jSONObject.getString("productId");
            this.title = jSONObject.optString("title");
            this.name = jSONObject.optString("name");
            this.description = jSONObject.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
            this.basePlanId = jSONObject.optString("basePlanId");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("pricingPhase");
            this.pricingPhase = jSONObjectOptJSONObject == null ? null : new PricingPhase(jSONObjectOptJSONObject);
        }

        @NonNull
        @zzw
        public String getBasePlanId() {
            return this.basePlanId;
        }

        @Nullable
        @zzw
        public String getDescription() {
            return this.description;
        }

        @Nullable
        @zzw
        public String getName() {
            return this.name;
        }

        @Nullable
        @zzw
        public PricingPhase getPricingPhase() {
            return this.pricingPhase;
        }

        @NonNull
        @zzw
        public String getProductId() {
            return this.productId;
        }

        @Nullable
        @zzw
        public String getTitle() {
            return this.title;
        }
    }

    public ProductDetails(String str) throws JSONException {
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        String strOptString = jSONObject.optString("productId");
        this.zzc = strOptString;
        String strOptString2 = jSONObject.optString("type");
        this.zzd = strOptString2;
        if (TextUtils.isEmpty(strOptString)) {
            throw new IllegalArgumentException("Product id cannot be empty.");
        }
        if (TextUtils.isEmpty(strOptString2)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.zze = jSONObject.optString("title");
        this.zzf = jSONObject.optString("name");
        this.zzg = jSONObject.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
        this.zzi = jSONObject.optString("packageDisplayName");
        this.zzj = jSONObject.optString("iconUrl");
        this.zzh = jSONObject.optString("skuDetailsToken");
        this.zzk = jSONObject.optString("serializedDocid");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("subscriptionOfferDetails");
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(new SubscriptionOfferDetails(jSONArrayOptJSONArray.getJSONObject(i)));
            }
            this.zzl = arrayList;
        } else {
            this.zzl = (strOptString2.equals("subs") || strOptString2.equals("play_pass_subs")) ? new ArrayList() : null;
        }
        JSONObject jSONObjectOptJSONObject = this.zzb.optJSONObject("oneTimePurchaseOfferDetails");
        JSONArray jSONArrayOptJSONArray2 = this.zzb.optJSONArray("oneTimePurchaseOfferDetailsList");
        ArrayList arrayList2 = new ArrayList();
        if (jSONArrayOptJSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                arrayList2.add(new OneTimePurchaseOfferDetails(jSONArrayOptJSONArray2.getJSONObject(i2)));
            }
            this.zzm = arrayList2;
            return;
        }
        if (jSONObjectOptJSONObject == null) {
            this.zzm = null;
        } else {
            arrayList2.add(new OneTimePurchaseOfferDetails(jSONObjectOptJSONObject));
            this.zzm = arrayList2;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProductDetails) {
            return TextUtils.equals(this.zza, ((ProductDetails) obj).zza);
        }
        return false;
    }

    @NonNull
    public String getDescription() {
        return this.zzg;
    }

    @NonNull
    @zzi
    public String getIconUrl() {
        return this.zzj;
    }

    @NonNull
    public String getName() {
        return this.zzf;
    }

    @Nullable
    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() {
        List list = this.zzm;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (OneTimePurchaseOfferDetails) list.get(0);
    }

    @Nullable
    @zzo
    @zzq
    public List<OneTimePurchaseOfferDetails> getOneTimePurchaseOfferDetailsList() {
        return this.zzm;
    }

    @NonNull
    @zzi
    public String getPackageDisplayName() {
        return this.zzi;
    }

    @NonNull
    @zzi
    public String getPackageName() {
        return this.zzb.optString("packageName");
    }

    @NonNull
    public String getProductId() {
        return this.zzc;
    }

    @NonNull
    public String getProductType() {
        return this.zzd;
    }

    @Nullable
    public List<SubscriptionOfferDetails> getSubscriptionOfferDetails() {
        return this.zzl;
    }

    @NonNull
    public String getTitle() {
        return this.zze;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    @NonNull
    public String toString() {
        List list = this.zzl;
        return "ProductDetails{jsonString='" + this.zza + "', parsedJson=" + this.zzb.toString() + ", productId='" + this.zzc + "', productType='" + this.zzd + "', title='" + this.zze + "', productDetailsToken='" + this.zzh + "', subscriptionOfferDetails=" + String.valueOf(list) + "}";
    }

    public final String zza() {
        return this.zzh;
    }

    @Nullable
    public final String zzb(@Nullable String str) {
        List<OneTimePurchaseOfferDetails> list;
        if (!TextUtils.isEmpty(str) && (list = this.zzm) != null && !list.isEmpty()) {
            for (OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails : list) {
                if (!TextUtils.isEmpty(oneTimePurchaseOfferDetails.zzl) && Objects.equals(oneTimePurchaseOfferDetails.zzd, str)) {
                    return oneTimePurchaseOfferDetails.zzl;
                }
            }
        }
        return this.zzk;
    }
}
