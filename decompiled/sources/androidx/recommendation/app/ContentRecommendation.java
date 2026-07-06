package androidx.recommendation.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ContentRecommendation {
    public static final String CONTENT_MATURITY_ALL = "android.contentMaturity.all";
    public static final String CONTENT_MATURITY_HIGH = "android.contentMaturity.high";
    public static final String CONTENT_MATURITY_LOW = "android.contentMaturity.low";
    public static final String CONTENT_MATURITY_MEDIUM = "android.contentMaturity.medium";
    public static final String CONTENT_PRICING_FREE = "android.contentPrice.free";
    public static final String CONTENT_PRICING_PREORDER = "android.contentPrice.preorder";
    public static final String CONTENT_PRICING_PURCHASE = "android.contentPrice.purchase";
    public static final String CONTENT_PRICING_RENTAL = "android.contentPrice.rental";
    public static final String CONTENT_PRICING_SUBSCRIPTION = "android.contentPrice.subscription";
    public static final int CONTENT_STATUS_AVAILABLE = 2;
    public static final int CONTENT_STATUS_PENDING = 1;
    public static final int CONTENT_STATUS_READY = 0;
    public static final int CONTENT_STATUS_UNAVAILABLE = 3;
    public static final String CONTENT_TYPE_APP = "android.contentType.app";
    public static final String CONTENT_TYPE_BOOK = "android.contentType.book";
    public static final String CONTENT_TYPE_COMIC = "android.contentType.comic";
    public static final String CONTENT_TYPE_GAME = "android.contentType.game";
    public static final String CONTENT_TYPE_MAGAZINE = "android.contentType.magazine";
    public static final String CONTENT_TYPE_MOVIE = "android.contentType.movie";
    public static final String CONTENT_TYPE_MUSIC = "android.contentType.music";
    public static final String CONTENT_TYPE_NEWS = "android.contentType.news";
    public static final String CONTENT_TYPE_PODCAST = "android.contentType.podcast";
    public static final String CONTENT_TYPE_RADIO = "android.contentType.radio";
    public static final String CONTENT_TYPE_SERIAL = "android.contentType.serial";
    public static final String CONTENT_TYPE_SPORTS = "android.contentType.sports";
    public static final String CONTENT_TYPE_TRAILER = "android.contentType.trailer";
    public static final String CONTENT_TYPE_VIDEO = "android.contentType.video";
    public static final String CONTENT_TYPE_WEBSITE = "android.contentType.website";
    public static final int INTENT_TYPE_ACTIVITY = 1;
    public static final int INTENT_TYPE_BROADCAST = 2;
    public static final int INTENT_TYPE_SERVICE = 3;
    public boolean mAutoDismiss;
    public final String mBackgroundImageUri;
    public final int mBadgeIconId;
    public final int mColor;
    public final String[] mContentGenres;
    public final Bitmap mContentImage;
    public final IntentData mContentIntentData;
    public final String[] mContentTypes;
    public final IntentData mDismissIntentData;
    public String mGroup;
    public final String mIdTag;
    public final String mMaturityRating;
    public final String mPriceType;
    public final String mPriceValue;
    public int mProgressAmount;
    public int mProgressMax;
    public final long mRunningTime;
    public String mSortKey;
    public final String mSourceName;
    public int mStatus;
    public final String mText;
    public final String mTitle;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean mBuilderAutoDismiss;
        public String mBuilderBackgroundImageUri;
        public int mBuilderBadgeIconId;
        public int mBuilderColor;
        public String[] mBuilderContentGenres;
        public Bitmap mBuilderContentImage;
        public IntentData mBuilderContentIntentData;
        public String[] mBuilderContentTypes;
        public IntentData mBuilderDismissIntentData;
        public String mBuilderGroup;
        public String mBuilderIdTag;
        public String mBuilderMaturityRating;
        public String mBuilderPriceType;
        public String mBuilderPriceValue;
        public int mBuilderProgressAmount;
        public int mBuilderProgressMax;
        public long mBuilderRunningTime;
        public String mBuilderSortKey;
        public String mBuilderSourceName;
        public int mBuilderStatus;
        public String mBuilderText;
        public String mBuilderTitle;

        public ContentRecommendation build() {
            return new ContentRecommendation(this);
        }

        public Builder setAutoDismiss(boolean z) {
            this.mBuilderAutoDismiss = z;
            return this;
        }

        public Builder setBackgroundImageUri(@Nullable String str) {
            this.mBuilderBackgroundImageUri = str;
            return this;
        }

        public Builder setBadgeIcon(@DrawableRes int i) {
            this.mBuilderBadgeIconId = i;
            return this;
        }

        public Builder setColor(@ColorInt int i) {
            this.mBuilderColor = i;
            return this;
        }

        public Builder setContentImage(Bitmap bitmap) {
            bitmap.getClass();
            this.mBuilderContentImage = bitmap;
            return this;
        }

        public Builder setContentIntentData(int i, Intent intent, int i2, @Nullable Bundle bundle) {
            if (i != 1 && i != 2 && i != 3) {
                throw new IllegalArgumentException("Invalid Intent type specified.");
            }
            IntentData intentData = new IntentData();
            this.mBuilderContentIntentData = intentData;
            intentData.mType = i;
            intent.getClass();
            intentData.mIntent = intent;
            IntentData intentData2 = this.mBuilderContentIntentData;
            intentData2.mRequestCode = i2;
            intentData2.mOptions = bundle;
            return this;
        }

        public Builder setContentTypes(String[] strArr) {
            strArr.getClass();
            this.mBuilderContentTypes = strArr;
            return this;
        }

        public Builder setDismissIntentData(int i, @Nullable Intent intent, int i2, @Nullable Bundle bundle) {
            if (intent == null) {
                this.mBuilderDismissIntentData = null;
                return this;
            }
            if (i != 1 && i != 2 && i != 3) {
                throw new IllegalArgumentException("Invalid Intent type specified.");
            }
            IntentData intentData = new IntentData();
            this.mBuilderDismissIntentData = intentData;
            intentData.mType = i;
            intentData.mIntent = intent;
            intentData.mRequestCode = i2;
            intentData.mOptions = bundle;
            return this;
        }

        public Builder setGenres(String[] strArr) {
            this.mBuilderContentGenres = strArr;
            return this;
        }

        public Builder setGroup(@Nullable String str) {
            this.mBuilderGroup = str;
            return this;
        }

        public Builder setIdTag(String str) {
            str.getClass();
            this.mBuilderIdTag = str;
            return this;
        }

        public Builder setMaturityRating(String str) {
            str.getClass();
            this.mBuilderMaturityRating = str;
            return this;
        }

        public Builder setPricingInformation(String str, @Nullable String str2) {
            str.getClass();
            this.mBuilderPriceType = str;
            this.mBuilderPriceValue = str2;
            return this;
        }

        public Builder setProgress(int i, int i2) {
            if (i < 0 || i2 < 0) {
                throw new IllegalArgumentException();
            }
            this.mBuilderProgressMax = i;
            this.mBuilderProgressAmount = i2;
            return this;
        }

        public Builder setRunningTime(long j) {
            if (j < 0) {
                throw new IllegalArgumentException();
            }
            this.mBuilderRunningTime = j;
            return this;
        }

        public Builder setSortKey(@Nullable String str) {
            this.mBuilderSortKey = str;
            return this;
        }

        public Builder setSourceName(@Nullable String str) {
            this.mBuilderSourceName = str;
            return this;
        }

        public Builder setStatus(int i) {
            this.mBuilderStatus = i;
            return this;
        }

        public Builder setText(@Nullable String str) {
            this.mBuilderText = str;
            return this;
        }

        public Builder setTitle(String str) {
            str.getClass();
            this.mBuilderTitle = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentMaturity {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentPricing {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentStatus {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IntentData {
        public Intent mIntent;
        public Bundle mOptions;
        public int mRequestCode;
        public int mType;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface IntentType {
    }

    public ContentRecommendation(Builder builder) {
        this.mIdTag = builder.mBuilderIdTag;
        this.mTitle = builder.mBuilderTitle;
        this.mText = builder.mBuilderText;
        this.mSourceName = builder.mBuilderSourceName;
        this.mContentImage = builder.mBuilderContentImage;
        this.mBadgeIconId = builder.mBuilderBadgeIconId;
        this.mBackgroundImageUri = builder.mBuilderBackgroundImageUri;
        this.mColor = builder.mBuilderColor;
        this.mContentIntentData = builder.mBuilderContentIntentData;
        this.mDismissIntentData = builder.mBuilderDismissIntentData;
        this.mContentTypes = builder.mBuilderContentTypes;
        this.mContentGenres = builder.mBuilderContentGenres;
        this.mPriceType = builder.mBuilderPriceType;
        this.mPriceValue = builder.mBuilderPriceValue;
        this.mMaturityRating = builder.mBuilderMaturityRating;
        this.mRunningTime = builder.mBuilderRunningTime;
        this.mGroup = builder.mBuilderGroup;
        this.mSortKey = builder.mBuilderSortKey;
        this.mProgressAmount = builder.mBuilderProgressAmount;
        this.mProgressMax = builder.mBuilderProgressMax;
        this.mAutoDismiss = builder.mBuilderAutoDismiss;
        this.mStatus = builder.mBuilderStatus;
    }

    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ContentRecommendation) {
            return TextUtils.equals(this.mIdTag, ((ContentRecommendation) obj).mIdTag);
        }
        return false;
    }

    public String getBackgroundImageUri() {
        return this.mBackgroundImageUri;
    }

    public int getBadgeImageResourceId() {
        return this.mBadgeIconId;
    }

    public int getColor() {
        return this.mColor;
    }

    public Bitmap getContentImage() {
        return this.mContentImage;
    }

    public IntentData getContentIntent() {
        return this.mContentIntentData;
    }

    public String[] getContentTypes() {
        String[] strArr = this.mContentTypes;
        return strArr != null ? (String[]) Arrays.copyOf(strArr, strArr.length) : strArr;
    }

    public IntentData getDismissIntent() {
        return this.mDismissIntentData;
    }

    public String[] getGenres() {
        String[] strArr = this.mContentGenres;
        return strArr != null ? (String[]) Arrays.copyOf(strArr, strArr.length) : strArr;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public String getIdTag() {
        return this.mIdTag;
    }

    public String getMaturityRating() {
        return this.mMaturityRating;
    }

    public Notification getNotificationObject(Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        RecommendationExtender recommendationExtender = new RecommendationExtender();
        builder.setCategory(NotificationCompat.CATEGORY_RECOMMENDATION);
        builder.setContentTitle(this.mTitle);
        builder.setContentText(this.mText);
        builder.setContentInfo(this.mSourceName);
        builder.setLargeIcon(this.mContentImage);
        builder.setSmallIcon(this.mBadgeIconId);
        if (this.mBackgroundImageUri != null) {
            builder.getExtras().putString(NotificationCompat.EXTRA_BACKGROUND_IMAGE_URI, this.mBackgroundImageUri);
        }
        builder.setColor(this.mColor);
        builder.setGroup(this.mGroup);
        builder.setSortKey(this.mSortKey);
        builder.setProgress(this.mProgressMax, this.mProgressAmount, false);
        builder.setAutoCancel(this.mAutoDismiss);
        IntentData intentData = this.mContentIntentData;
        if (intentData != null) {
            int i = intentData.mType;
            builder.setContentIntent(i == 1 ? PendingIntent.getActivity(context, intentData.mRequestCode, intentData.mIntent, 134217728, intentData.mOptions) : i == 3 ? PendingIntent.getService(context, intentData.mRequestCode, intentData.mIntent, 134217728) : PendingIntent.getBroadcast(context, intentData.mRequestCode, intentData.mIntent, 134217728));
        }
        IntentData intentData2 = this.mDismissIntentData;
        if (intentData2 != null) {
            int i2 = intentData2.mType;
            builder.setDeleteIntent(i2 == 1 ? PendingIntent.getActivity(context, intentData2.mRequestCode, intentData2.mIntent, 134217728, intentData2.mOptions) : i2 == 3 ? PendingIntent.getService(context, intentData2.mRequestCode, intentData2.mIntent, 134217728) : PendingIntent.getBroadcast(context, intentData2.mRequestCode, intentData2.mIntent, 134217728));
        }
        recommendationExtender.mTypes = this.mContentTypes;
        recommendationExtender.mGenres = this.mContentGenres;
        String str = this.mPriceType;
        String str2 = this.mPriceValue;
        recommendationExtender.mPricingType = str;
        recommendationExtender.mPricingValue = str2;
        recommendationExtender.mContentStatus = this.mStatus;
        recommendationExtender.mMaturityRating = this.mMaturityRating;
        recommendationExtender.setRunningTime(this.mRunningTime);
        builder.extend(recommendationExtender);
        return builder.build();
    }

    public String getPricingType() {
        return this.mPriceType;
    }

    public String getPricingValue() {
        return this.mPriceValue;
    }

    public String getPrimaryContentType() {
        String[] strArr = this.mContentTypes;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    public int getProgressMax() {
        return this.mProgressMax;
    }

    public int getProgressValue() {
        return this.mProgressAmount;
    }

    public long getRunningTime() {
        return this.mRunningTime;
    }

    public String getSortKey() {
        return this.mSortKey;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public String getText() {
        return this.mText;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean hasProgressInfo() {
        return this.mProgressMax != 0;
    }

    public int hashCode() {
        String str = this.mIdTag;
        if (str != null) {
            return str.hashCode();
        }
        return Integer.MAX_VALUE;
    }

    public boolean isAutoDismiss() {
        return this.mAutoDismiss;
    }

    public void setAutoDismiss(boolean z) {
        this.mAutoDismiss = z;
    }

    public void setGroup(String str) {
        this.mGroup = str;
    }

    public void setProgress(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        this.mProgressMax = i;
        this.mProgressAmount = i2;
    }

    public void setSortKey(String str) {
        this.mSortKey = str;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }
}
