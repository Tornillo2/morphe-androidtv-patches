package com.amazon.primevideo;

import android.app.ActivityManager;
import android.app.Application;
import androidx.work.Configuration;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.RendererManager;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.livingroom.auth.ApplicationAccessTokenProvider;
import com.amazon.primevideo.di.PrimeVideoApplicationComponent;
import com.amazon.reporting.Log;
import dagger.Lazy;
import javax.inject.Inject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PrimeVideoApplication extends Application implements Configuration.Provider {

    @NotNull
    public static final String METRIC_NAME = "RecommendationsRefresh.AuthenticationChange";
    public static final int MIN_WORK_MANAGER_JOB_ID = 100;

    @Inject
    public ApplicationAccessTokenProvider applicationAccessTokenProvider;

    @Inject
    public Lazy<RecommendationHandler> recommendationHandler;

    @Inject
    public Lazy<RecommendationsMetricRecorder> recommendationsMetricRecorder;

    @Inject
    public IgniteRenderer renderer;

    @Inject
    public RendererManager rendererManager;

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public static final String TAG = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(PrimeVideoApplication.class)).getSimpleName();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final void onCreate$lambda$0(PrimeVideoApplication primeVideoApplication, boolean z) {
        RecommendationsMetricRecorder.recordRefreshMetric$default(primeVideoApplication.getRecommendationsMetricRecorder().get(), METRIC_NAME, 0, 2, null);
        primeVideoApplication.getRecommendationHandler().get().updateRecommendations();
    }

    @NotNull
    public final ApplicationAccessTokenProvider getApplicationAccessTokenProvider() {
        ApplicationAccessTokenProvider applicationAccessTokenProvider = this.applicationAccessTokenProvider;
        if (applicationAccessTokenProvider != null) {
            return applicationAccessTokenProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("applicationAccessTokenProvider");
        throw null;
    }

    @NotNull
    public final Lazy<RecommendationHandler> getRecommendationHandler() {
        Lazy<RecommendationHandler> lazy = this.recommendationHandler;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationHandler");
        throw null;
    }

    @NotNull
    public final Lazy<RecommendationsMetricRecorder> getRecommendationsMetricRecorder() {
        Lazy<RecommendationsMetricRecorder> lazy = this.recommendationsMetricRecorder;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationsMetricRecorder");
        throw null;
    }

    @NotNull
    public final IgniteRenderer getRenderer() {
        IgniteRenderer igniteRenderer = this.renderer;
        if (igniteRenderer != null) {
            return igniteRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("renderer");
        throw null;
    }

    @NotNull
    public final RendererManager getRendererManager() {
        RendererManager rendererManager = this.rendererManager;
        if (rendererManager != null) {
            return rendererManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rendererManager");
        throw null;
    }

    @Override // androidx.work.Configuration.Provider
    @NotNull
    public Configuration getWorkManagerConfiguration() {
        Configuration.Builder builder = new Configuration.Builder();
        builder.setJobSchedulerJobIdRange(100, Integer.MAX_VALUE);
        return new Configuration(builder);
    }

    @Override // android.app.Application
    public void onCreate() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Log.configure(getApplicationContext());
        PrimeVideoApplicationComponent.Companion companion = PrimeVideoApplicationComponent.Companion;
        companion.init(this);
        companion.getInstance().inject(this);
        super.onCreate();
        getApplicationAccessTokenProvider().addAuthenticationChangeListener(new ApplicationAccessTokenProvider.AuthenticationChangeListener() { // from class: com.amazon.primevideo.PrimeVideoApplication$$ExternalSyntheticLambda0
            @Override // com.amazon.livingroom.auth.ApplicationAccessTokenProvider.AuthenticationChangeListener
            public final void onChange(boolean z) {
                PrimeVideoApplication.onCreate$lambda$0(this.f$0, z);
            }
        });
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        if (runningAppProcessInfo.importance == 100) {
            Log.d(TAG, "Initializing IgnitionX in background mode.");
            getRendererManager().initializeRendering(jCurrentTimeMillis);
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        Log.d(TAG, "onLowMemory called");
        super.onLowMemory();
        getRenderer().onLowMemory();
    }

    public final void setApplicationAccessTokenProvider(@NotNull ApplicationAccessTokenProvider applicationAccessTokenProvider) {
        Intrinsics.checkNotNullParameter(applicationAccessTokenProvider, "<set-?>");
        this.applicationAccessTokenProvider = applicationAccessTokenProvider;
    }

    public final void setRecommendationHandler(@NotNull Lazy<RecommendationHandler> lazy) {
        Intrinsics.checkNotNullParameter(lazy, "<set-?>");
        this.recommendationHandler = lazy;
    }

    public final void setRecommendationsMetricRecorder(@NotNull Lazy<RecommendationsMetricRecorder> lazy) {
        Intrinsics.checkNotNullParameter(lazy, "<set-?>");
        this.recommendationsMetricRecorder = lazy;
    }

    public final void setRenderer(@NotNull IgniteRenderer igniteRenderer) {
        Intrinsics.checkNotNullParameter(igniteRenderer, "<set-?>");
        this.renderer = igniteRenderer;
    }

    public final void setRendererManager(@NotNull RendererManager rendererManager) {
        Intrinsics.checkNotNullParameter(rendererManager, "<set-?>");
        this.rendererManager = rendererManager;
    }
}
