package com.android.billingclient.api;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@zzt
public final class UnfetchedProduct {
    public final String jsonString;
    public final String productId;
    public final String productType;

    @Nullable
    public final String serializedDocid;
    public final int statusCode;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusCode {

        @zzt
        public static final int INVALID_PRODUCT_ID_FORMAT = 2;

        @zzi
        public static final int NOT_ELIGIBLE_FOR_CROSS_SELL = 5;

        @zzt
        public static final int NO_ELIGIBLE_OFFER = 4;

        @zzt
        public static final int PRODUCT_NOT_FOUND = 3;

        @zzt
        public static final int UNKNOWN = 0;
    }

    public UnfetchedProduct(String str) throws JSONException {
        this.jsonString = str;
        JSONObject jSONObject = new JSONObject(str);
        this.productId = jSONObject.optString("productId");
        String strOptString = jSONObject.optString("type");
        this.productType = strOptString;
        this.statusCode = jSONObject.has("statusCode") ? jSONObject.optInt("statusCode") : 0;
        if (TextUtils.isEmpty(strOptString)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.serializedDocid = jSONObject.optString("serializedDocid");
    }

    @NonNull
    @VisibleForTesting
    public static UnfetchedProduct fromJson(@NonNull String str) throws JSONException {
        return new UnfetchedProduct(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UnfetchedProduct) {
            return TextUtils.equals(this.jsonString, ((UnfetchedProduct) obj).jsonString);
        }
        return false;
    }

    @NonNull
    @zzt
    public String getProductId() {
        return this.productId;
    }

    @NonNull
    @zzt
    public String getProductType() {
        return this.productType;
    }

    @Nullable
    public String getSerializedDocid() {
        return this.serializedDocid;
    }

    @zzt
    public int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return this.jsonString.hashCode();
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("UnfetchedProduct{productId='");
        sb.append(this.productId);
        sb.append("', productType='");
        sb.append(this.productType);
        sb.append("', statusCode=");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.statusCode, "}");
    }
}
