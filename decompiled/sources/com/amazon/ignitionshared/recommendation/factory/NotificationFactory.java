package com.amazon.ignitionshared.recommendation.factory;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.core.content.ContextCompat;
import androidx.recommendation.app.ContentRecommendation;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.ignitionshared.recommendation.model.Recommendation;
import com.amazon.ignitionshared.recommendation.model.RecommendationException;
import com.amazon.reporting.Log;
import com.bumptech.glide.Glide;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NotificationFactory {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String GROUP_POSITION_KEY = "GROUP_POSITION_KEY";

    @NotNull
    public static final String ITEM_POSITION_KEY = "ITEM_POSITION_KEY";
    public static final String TAG = "NotificationFactory";
    public final int color;
    public final int icon;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public NotificationFactory(int i, int i2) {
        this.icon = i;
        this.color = i2;
    }

    public final Intent buildPendingIntent(ComponentName componentName, String str) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        DeepLinkIntentParser.Companion.getClass();
        intent.putExtra(DeepLinkIntentParser.Companion.internalDeepLinkKey, str);
        return intent;
    }

    @NotNull
    public final Notification createNotification(@NotNull Context context, @NotNull Recommendation recommendation, @NotNull ComponentName deepLinkActivityName, @NotNull String priority) throws RecommendationException {
        Bitmap bitmapCreateFailure;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(recommendation, "recommendation");
        Intrinsics.checkNotNullParameter(deepLinkActivityName, "deepLinkActivityName");
        Intrinsics.checkNotNullParameter(priority, "priority");
        try {
            bitmapCreateFailure = Glide.getRetriever(context).get(context).asBitmap().load(recommendation.cardImageUrl).submit().get();
        } catch (Throwable th) {
            bitmapCreateFailure = ResultKt.createFailure(th);
        }
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(bitmapCreateFailure);
        if (thM583exceptionOrNullimpl != null) {
            Log.e(TAG, "Error while creating Bitmap");
            throw new RecommendationException(thM583exceptionOrNullimpl);
        }
        Log.d(TAG, "Creating recommendation title: " + recommendation.title + " with Group: " + recommendation.group);
        ContentRecommendation.Builder builder = new ContentRecommendation.Builder();
        builder.setIdTag(String.valueOf(recommendation.hashedId));
        builder.setTitle(recommendation.title);
        builder.mBuilderText = recommendation.description;
        builder.mBuilderGroup = recommendation.group;
        builder.setContentIntentData(1, buildPendingIntent(deepLinkActivityName, recommendation.actionData), recommendation.hashedId, null);
        builder.mBuilderBackgroundImageUri = recommendation.backgroundImageUrl;
        builder.mBuilderBadgeIconId = this.icon;
        builder.setContentImage((Bitmap) bitmapCreateFailure);
        builder.mBuilderColor = ContextCompat.getColor(context, this.color);
        builder.mBuilderSortKey = priority;
        Notification notificationObject = new ContentRecommendation(builder).getNotificationObject(context);
        notificationObject.extras.putInt(GROUP_POSITION_KEY, recommendation.groupPosition);
        notificationObject.extras.putInt(ITEM_POSITION_KEY, recommendation.position);
        return notificationObject;
    }
}
