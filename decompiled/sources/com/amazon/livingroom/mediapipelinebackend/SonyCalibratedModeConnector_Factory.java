package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import dagger.Lazy;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class SonyCalibratedModeConnector_Factory implements Factory<SonyCalibratedModeConnector> {
    public final Provider<Context> contextProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<PictureQualityController> pictureQualityControllerProvider;

    public SonyCalibratedModeConnector_Factory(Provider<Context> provider, Provider<PictureQualityController> provider2, Provider<MetricsRecorder> provider3) {
        this.contextProvider = provider;
        this.pictureQualityControllerProvider = provider2;
        this.metricsRecorderProvider = provider3;
    }

    public static SonyCalibratedModeConnector_Factory create(Provider<Context> provider, Provider<PictureQualityController> provider2, Provider<MetricsRecorder> provider3) {
        return new SonyCalibratedModeConnector_Factory(provider, provider2, provider3);
    }

    public static SonyCalibratedModeConnector newInstance(Context context, Lazy<PictureQualityController> lazy, MetricsRecorder metricsRecorder) {
        return new SonyCalibratedModeConnector(context, lazy, metricsRecorder);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SonyCalibratedModeConnector get() {
        return new SonyCalibratedModeConnector(this.contextProvider.get(), DoubleCheck.lazy((Provider) this.pictureQualityControllerProvider), this.metricsRecorderProvider.get());
    }
}
