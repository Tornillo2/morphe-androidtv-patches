package com.amazon.ignitionshared.recommendation.scheduler;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import com.amazon.ignitionshared.service.PeriodicUpdateRecommendationsWorker;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRecommendationUpdaterPeriodicWorkRequestBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RecommendationUpdaterPeriodicWorkRequestBuilder.kt\ncom/amazon/ignitionshared/recommendation/scheduler/RecommendationUpdaterPeriodicWorkRequestBuilder\n+ 2 PeriodicWorkRequest.kt\nandroidx/work/PeriodicWorkRequestKt\n*L\n1#1,29:1\n63#2,6:30\n*S KotlinDebug\n*F\n+ 1 RecommendationUpdaterPeriodicWorkRequestBuilder.kt\ncom/amazon/ignitionshared/recommendation/scheduler/RecommendationUpdaterPeriodicWorkRequestBuilder\n*L\n19#1:30,6\n*E\n"})
public final class RecommendationUpdaterPeriodicWorkRequestBuilder {
    @Inject
    public RecommendationUpdaterPeriodicWorkRequestBuilder() {
    }

    @NotNull
    public final PeriodicWorkRequest buildWithIntervalInMinutes(long j) {
        Constraints.Builder builder = new Constraints.Builder();
        builder.mRequiredNetworkType = NetworkType.CONNECTED;
        Constraints constraints = new Constraints(builder);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        return new PeriodicWorkRequest.Builder(PeriodicUpdateRecommendationsWorker.class, j, timeUnit, (long) (j * 0.25d), timeUnit).setConstraints(constraints).build();
    }
}
