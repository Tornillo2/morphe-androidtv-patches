package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.tvprovider.media.tv.TvContractCompat;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class SkuDetails {
    public final String zza;
    public final JSONObject zzb;

    public SkuDetails(@NonNull String str) throws JSONException {
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        if (TextUtils.isEmpty(jSONObject.optString("productId"))) {
            throw new IllegalArgumentException("SKU cannot be empty.");
        }
        if (TextUtils.isEmpty(jSONObject.optString("type"))) {
            throw new IllegalArgumentException("SkuType cannot be empty.");
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkuDetails) {
            return TextUtils.equals(this.zza, ((SkuDetails) obj).zza);
        }
        return false;
    }

    @NonNull
    public String getDescription() {
        return this.zzb.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
    }

    @NonNull
    public String getFreeTrialPeriod() {
        return this.zzb.optString("freeTrialPeriod");
    }

    @NonNull
    public String getIconUrl() {
        return this.zzb.optString("iconUrl");
    }

    @NonNull
    public String getIntroductoryPrice() {
        return this.zzb.optString("introductoryPrice");
    }

    public long getIntroductoryPriceAmountMicros() {
        return this.zzb.optLong("introductoryPriceAmountMicros");
    }

    public int getIntroductoryPriceCycles() {
        return this.zzb.optInt("introductoryPriceCycles");
    }

    @NonNull
    public String getIntroductoryPricePeriod() {
        return this.zzb.optString("introductoryPricePeriod");
    }

    @NonNull
    @zzi
    public String getName() {
        return this.zzb.optString("name");
    }

    @NonNull
    @zzs
    public String getOfferIdToken() {
        JSONObject jSONObject = this.zzb;
        String strOptString = jSONObject.optString("offerIdToken");
        return strOptString.isEmpty() ? jSONObject.optString("offer_id_token") : strOptString;
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
    }

    @NonNull
    public String getOriginalPrice() {
        JSONObject jSONObject = this.zzb;
        return jSONObject.has("original_price") ? jSONObject.optString("original_price") : getPrice();
    }

    public long getOriginalPriceAmountMicros() {
        JSONObject jSONObject = this.zzb;
        return jSONObject.has("original_price_micros") ? jSONObject.optLong("original_price_micros") : getPriceAmountMicros();
    }

    @NonNull
    @zzi
    public String getPackageDisplayName() {
        return this.zzb.optString("packageDisplayName");
    }

    @NonNull
    @zzi
    public String getPackageName() {
        return this.zzb.optString("packageName");
    }

    @NonNull
    public String getPrice() {
        return this.zzb.optString("price");
    }

    public long getPriceAmountMicros() {
        return this.zzb.optLong("price_amount_micros");
    }

    @NonNull
    public String getPriceCurrencyCode() {
        return this.zzb.optString("price_currency_code");
    }

    @NonNull
    public String getSku() {
        return this.zzb.optString("productId");
    }

    @NonNull
    public String getSubscriptionPeriod() {
        return this.zzb.optString("subscriptionPeriod");
    }

    @NonNull
    public String getTitle() {
        return this.zzb.optString("title");
    }

    @NonNull
    public String getType() {
        return this.zzb.optString("type");
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    @NonNull
    public String toString() {
        return "SkuDetails: ".concat(String.valueOf(this.zza));
    }

    public int zza() {
        return this.zzb.optInt("offer_type");
    }

    @NonNull
    public String zzb() {
        return this.zzb.optString("offer_id");
    }

    @NonNull
    public String zzc() {
        return this.zzb.optString("serializedDocid");
    }

    public final String zzd() {
        return this.zzb.optString("skuDetailsToken");
    }
}
