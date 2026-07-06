package com.amazon.ignitionshared.recommendation.publisher;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import com.amazon.ignitionshared.recommendation.factory.NotificationFactory;
import com.amazon.ignitionshared.recommendation.model.Recommendation;
import com.amazon.ignitionshared.recommendation.model.RecommendationException;
import com.amazon.reporting.Log;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NotificationPublisher implements RecommendationPublisher {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "NotificationPublisher";

    @NotNull
    public final Context context;

    @NotNull
    public final ComponentName deepLinkActivityName;

    @NotNull
    public final NotificationFactory notificationFactory;

    @NotNull
    public final NotificationManager notificationManager;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public NotificationPublisher(@NotNull Context context, @NotNull NotificationFactory notificationFactory, @NotNull ComponentName deepLinkActivityName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationFactory, "notificationFactory");
        Intrinsics.checkNotNullParameter(deepLinkActivityName, "deepLinkActivityName");
        this.context = context;
        this.notificationFactory = notificationFactory;
        this.deepLinkActivityName = deepLinkActivityName;
        Object systemService = context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        this.notificationManager = (NotificationManager) systemService;
    }

    @Override // com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher
    public boolean clearRecommendations() {
        Log.d(TAG, "Clearing recommendations");
        this.notificationManager.cancelAll();
        return true;
    }

    @Override // com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher
    public boolean publishRecommendations(@NotNull Collection<Recommendation> recommendations) {
        Intrinsics.checkNotNullParameter(recommendations, "recommendations");
        Log.i(TAG, "Updating recommendation cards");
        int size = recommendations.size();
        try {
            for (Recommendation recommendation : recommendations) {
                this.notificationManager.notify(recommendation.hashedId, this.notificationFactory.createNotification(this.context, recommendation, this.deepLinkActivityName, String.valueOf(1.0f - ((recommendation.position + 1.0f) / size))));
            }
            return true;
        } catch (RecommendationException e) {
            Log.e(TAG, "Unable to update recommendation", e);
            return false;
        }
    }
}
