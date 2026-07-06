package androidx.recommendation.app;

import android.app.Notification;
import android.os.Bundle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RecommendationExtender implements Notification.Extender {
    public static final String EXTRA_CONTENT_INFO_EXTENDER = "android.CONTENT_INFO_EXTENSIONS";
    public static final String KEY_CONTENT_GENRES = "android.contentGenre";
    public static final String KEY_CONTENT_MATURITY_RATING = "android.contentMaturity";
    public static final String KEY_CONTENT_PRICING_TYPE = "android.contentPricing.type";
    public static final String KEY_CONTENT_PRICING_VALUE = "android.contentPricing.value";
    public static final String KEY_CONTENT_RUN_LENGTH = "android.contentLength";
    public static final String KEY_CONTENT_STATUS = "android.contentStatus";
    public static final String KEY_CONTENT_TYPE = "android.contentType";
    public static final String TAG = "RecommendationExtender";
    public int mContentStatus;
    public String[] mGenres;
    public String mMaturityRating;
    public String mPricingType;
    public String mPricingValue;
    public long mRunLength;
    public String[] mTypes;

    public RecommendationExtender() {
        this.mContentStatus = -1;
        this.mRunLength = -1L;
    }

    @Override // android.app.Notification.Extender
    public Notification.Builder extend(Notification.Builder builder) {
        Bundle bundle = new Bundle();
        String[] strArr = this.mTypes;
        if (strArr != null) {
            bundle.putStringArray(KEY_CONTENT_TYPE, strArr);
        }
        String[] strArr2 = this.mGenres;
        if (strArr2 != null) {
            bundle.putStringArray(KEY_CONTENT_GENRES, strArr2);
        }
        String str = this.mPricingType;
        if (str != null) {
            bundle.putString(KEY_CONTENT_PRICING_TYPE, str);
        }
        String str2 = this.mPricingValue;
        if (str2 != null) {
            bundle.putString(KEY_CONTENT_PRICING_VALUE, str2);
        }
        int i = this.mContentStatus;
        if (i != -1) {
            bundle.putInt(KEY_CONTENT_STATUS, i);
        }
        String str3 = this.mMaturityRating;
        if (str3 != null) {
            bundle.putString(KEY_CONTENT_MATURITY_RATING, str3);
        }
        long j = this.mRunLength;
        if (j > 0) {
            bundle.putLong(KEY_CONTENT_RUN_LENGTH, j);
        }
        builder.getExtras().putBundle(EXTRA_CONTENT_INFO_EXTENDER, bundle);
        return builder;
    }

    public String[] getContentTypes() {
        return this.mTypes;
    }

    public String[] getGenres() {
        return this.mGenres;
    }

    public String getMaturityRating() {
        return this.mMaturityRating;
    }

    public String getPricingType() {
        return this.mPricingType;
    }

    public String getPricingValue() {
        if (this.mPricingType == null) {
            return null;
        }
        return this.mPricingValue;
    }

    public String getPrimaryContentType() {
        String[] strArr = this.mTypes;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        return strArr[0];
    }

    public long getRunningTime() {
        return this.mRunLength;
    }

    public int getStatus() {
        return this.mContentStatus;
    }

    public RecommendationExtender setContentTypes(String[] strArr) {
        this.mTypes = strArr;
        return this;
    }

    public RecommendationExtender setGenres(String[] strArr) {
        this.mGenres = strArr;
        return this;
    }

    public RecommendationExtender setMaturityRating(String str) {
        this.mMaturityRating = str;
        return this;
    }

    public RecommendationExtender setPricingInformation(String str, String str2) {
        this.mPricingType = str;
        this.mPricingValue = str2;
        return this;
    }

    public RecommendationExtender setRunningTime(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Invalid value for Running Time");
        }
        this.mRunLength = j;
        return this;
    }

    public RecommendationExtender setStatus(int i) {
        this.mContentStatus = i;
        return this;
    }

    public RecommendationExtender(Notification notification) {
        this.mContentStatus = -1;
        this.mRunLength = -1L;
        Bundle bundle = notification.extras;
        Bundle bundle2 = bundle == null ? null : bundle.getBundle(EXTRA_CONTENT_INFO_EXTENDER);
        if (bundle2 != null) {
            this.mTypes = bundle2.getStringArray(KEY_CONTENT_TYPE);
            this.mGenres = bundle2.getStringArray(KEY_CONTENT_GENRES);
            this.mPricingType = bundle2.getString(KEY_CONTENT_PRICING_TYPE);
            this.mPricingValue = bundle2.getString(KEY_CONTENT_PRICING_VALUE);
            this.mContentStatus = bundle2.getInt(KEY_CONTENT_STATUS, -1);
            this.mMaturityRating = bundle2.getString(KEY_CONTENT_MATURITY_RATING);
            this.mRunLength = bundle2.getLong(KEY_CONTENT_RUN_LENGTH, -1L);
        }
    }
}
