package com.amazon.ignitionshared.recommendation.handler;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.impl.WorkManagerImpl;
import com.amazon.ignitionshared.service.UpdateRecommendationsWorker;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nRecommendationHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RecommendationHandler.kt\ncom/amazon/ignitionshared/recommendation/handler/RecommendationHandler\n+ 2 OneTimeWorkRequest.kt\nandroidx/work/OneTimeWorkRequestKt\n*L\n1#1,34:1\n29#2:35\n*S KotlinDebug\n*F\n+ 1 RecommendationHandler.kt\ncom/amazon/ignitionshared/recommendation/handler/RecommendationHandler\n*L\n20#1:35\n*E\n"})
public final class RecommendationHandler {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String UPDATE_RECOMMENDATION_ONE_TIME_WORK = "updateRecommendationsOneTimeWork";

    @NotNull
    public final Context context;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public RecommendationHandler(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final void updateRecommendations() {
        Constraints.Builder builder = new Constraints.Builder();
        builder.mRequiredNetworkType = NetworkType.CONNECTED;
        WorkManagerImpl.getInstance(this.context).beginUniqueWork(UPDATE_RECOMMENDATION_ONE_TIME_WORK, ExistingWorkPolicy.APPEND_OR_REPLACE, new OneTimeWorkRequest.Builder(UpdateRecommendationsWorker.class).setConstraints(new Constraints(builder)).build()).enqueue();
    }
}
