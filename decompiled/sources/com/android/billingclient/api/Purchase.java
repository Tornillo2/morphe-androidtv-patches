package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Purchase {
    public final String zza;
    public final String zzb;
    public final JSONObject zzc;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @zzg
    public static final class AutoPayDetails {
        public final JSONObject mParsedJson;

        public AutoPayDetails(JSONObject jSONObject) {
            this.mParsedJson = jSONObject;
        }

        @NonNull
        public List<String> getProductIds() {
            JSONArray jSONArrayOptJSONArray;
            ArrayList arrayList = new ArrayList();
            if (this.mParsedJson.has("productIds") && (jSONArrayOptJSONArray = this.mParsedJson.optJSONArray("productIds")) != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i));
                }
            }
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @zzr
    public static final class PendingPurchaseUpdate {
        public final JSONObject mParsedJson;

        public PendingPurchaseUpdate(JSONObject jSONObject) {
            this.mParsedJson = jSONObject;
        }

        @NonNull
        public List<String> getProducts() {
            JSONArray jSONArrayOptJSONArray;
            ArrayList arrayList = new ArrayList();
            if (this.mParsedJson.has("productIds") && (jSONArrayOptJSONArray = this.mParsedJson.optJSONArray("productIds")) != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i));
                }
            }
            return arrayList;
        }

        @NonNull
        public String getPurchaseToken() {
            return this.mParsedJson.optString("purchaseToken");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface PurchaseState {
        public static final int PENDING = 2;
        public static final int PURCHASED = 1;
        public static final int UNSPECIFIED_STATE = 0;
    }

    public Purchase(@NonNull String str, @NonNull String str2) throws JSONException {
        this.zza = str;
        this.zzb = str2;
        this.zzc = new JSONObject(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) obj;
        return TextUtils.equals(this.zza, purchase.getOriginalJson()) && TextUtils.equals(this.zzb, purchase.getSignature());
    }

    @Nullable
    public AccountIdentifiers getAccountIdentifiers() {
        JSONObject jSONObject = this.zzc;
        String strOptString = jSONObject.optString("obfuscatedAccountId");
        String strOptString2 = jSONObject.optString("obfuscatedProfileId");
        if (strOptString == null && strOptString2 == null) {
            return null;
        }
        return new AccountIdentifiers(strOptString, strOptString2);
    }

    @Nullable
    @zzg
    public AutoPayDetails getAutoPayDetails() {
        JSONObject jSONObjectOptJSONObject = this.zzc.optJSONObject("autoPayDetails");
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        return new AutoPayDetails(jSONObjectOptJSONObject);
    }

    @Nullable
    @zzg
    public String getAutoPayPurchaseToken() {
        String strOptString = this.zzc.optString("autoPayPurchaseToken");
        if (TextUtils.isEmpty(strOptString)) {
            return null;
        }
        return strOptString;
    }

    @NonNull
    public String getDeveloperPayload() {
        return this.zzc.optString("developerPayload");
    }

    @Nullable
    public String getOrderId() {
        String strOptString = this.zzc.optString("orderId");
        if (TextUtils.isEmpty(strOptString)) {
            return null;
        }
        return strOptString;
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
    }

    @NonNull
    public String getPackageName() {
        return this.zzc.optString("packageName");
    }

    @Nullable
    @zzr
    public PendingPurchaseUpdate getPendingPurchaseUpdate() {
        JSONObject jSONObjectOptJSONObject = this.zzc.optJSONObject("pendingPurchaseUpdate");
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        return new PendingPurchaseUpdate(jSONObjectOptJSONObject);
    }

    @NonNull
    public List<String> getProducts() {
        return zza();
    }

    public int getPurchaseState() {
        return this.zzc.optInt("purchaseState", 1) != 4 ? 1 : 2;
    }

    public long getPurchaseTime() {
        return this.zzc.optLong("purchaseTime");
    }

    @NonNull
    public String getPurchaseToken() {
        JSONObject jSONObject = this.zzc;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public int getQuantity() {
        return this.zzc.optInt("quantity", 1);
    }

    @NonNull
    public String getSignature() {
        return this.zzb;
    }

    @NonNull
    @Deprecated
    public ArrayList<String> getSkus() {
        return zza();
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public boolean isAcknowledged() {
        return this.zzc.optBoolean("acknowledged", true);
    }

    public boolean isAutoRenewing() {
        return this.zzc.optBoolean("autoRenewing");
    }

    @NonNull
    public String toString() {
        return "Purchase. Json: ".concat(String.valueOf(this.zza));
    }

    public final ArrayList zza() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = this.zzc;
        if (jSONObject.has("productIds")) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("productIds");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i));
                }
            }
        } else if (jSONObject.has("productId")) {
            arrayList.add(jSONObject.optString("productId"));
        }
        return arrayList;
    }
}
