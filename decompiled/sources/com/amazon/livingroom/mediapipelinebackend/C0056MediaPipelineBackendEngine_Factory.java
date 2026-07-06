package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import android.os.Handler;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.ignitionshared.TextToSpeechStatusProvider;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine_Factory, reason: case insensitive filesystem */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class C0056MediaPipelineBackendEngine_Factory {
    public final Provider<Context> contextProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<HdcpChecker> hdcpCheckerProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<SonyCalibratedModeController> sonyCalibratedModeControllerProvider;
    public final Provider<TextToSpeechStatusProvider> ttsStatusProvider;
    public final Provider<ApplicationVisibilityMonitor> visibilityMonitorProvider;

    public C0056MediaPipelineBackendEngine_Factory(Provider<Context> provider, Provider<ApplicationVisibilityMonitor> provider2, Provider<HdcpChecker> provider3, Provider<DeviceProperties> provider4, Provider<MinervaMetrics> provider5, Provider<TextToSpeechStatusProvider> provider6, Provider<SonyCalibratedModeController> provider7) {
        this.contextProvider = provider;
        this.visibilityMonitorProvider = provider2;
        this.hdcpCheckerProvider = provider3;
        this.devicePropertiesProvider = provider4;
        this.minervaMetricsProvider = provider5;
        this.ttsStatusProvider = provider6;
        this.sonyCalibratedModeControllerProvider = provider7;
    }

    public static C0056MediaPipelineBackendEngine_Factory create(Provider<Context> provider, Provider<ApplicationVisibilityMonitor> provider2, Provider<HdcpChecker> provider3, Provider<DeviceProperties> provider4, Provider<MinervaMetrics> provider5, Provider<TextToSpeechStatusProvider> provider6, Provider<SonyCalibratedModeController> provider7) {
        return new C0056MediaPipelineBackendEngine_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MediaPipelineBackendEngine newInstance(Context context, Handler handler, MediaPipelineListener mediaPipelineListener, MediaPipelineBackendEngine.DestroyInterceptor destroyInterceptor, PlaybackSurface playbackSurface, int i, ApplicationVisibilityMonitor applicationVisibilityMonitor, HdcpChecker hdcpChecker, DeviceProperties deviceProperties, MinervaMetrics minervaMetrics, TextToSpeechStatusProvider textToSpeechStatusProvider, SonyCalibratedModeController sonyCalibratedModeController) {
        return new MediaPipelineBackendEngine(context, handler, mediaPipelineListener, destroyInterceptor, playbackSurface, i, applicationVisibilityMonitor, hdcpChecker, deviceProperties, minervaMetrics, textToSpeechStatusProvider, sonyCalibratedModeController);
    }

    public MediaPipelineBackendEngine get(Handler handler, MediaPipelineListener mediaPipelineListener, MediaPipelineBackendEngine.DestroyInterceptor destroyInterceptor, PlaybackSurface playbackSurface, int i) {
        return new MediaPipelineBackendEngine(this.contextProvider.get(), handler, mediaPipelineListener, destroyInterceptor, playbackSurface, i, this.visibilityMonitorProvider.get(), this.hdcpCheckerProvider.get(), this.devicePropertiesProvider.get(), this.minervaMetricsProvider.get(), this.ttsStatusProvider.get(), this.sonyCalibratedModeControllerProvider.get());
    }
}
