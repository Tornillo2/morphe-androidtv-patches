package com.amazon.primevideo.nativebilling;

import android.os.Handler;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class BillingClientStatusProvider_Factory implements Factory<BillingClientStatusProvider> {
    public final Provider<BillingClientProvider> billingClientProvider;
    public final Provider<Handler> handlerProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;

    public BillingClientStatusProvider_Factory(Provider<BillingClientProvider> provider, Provider<Handler> provider2, Provider<MetricsRecorder> provider3) {
        this.billingClientProvider = provider;
        this.handlerProvider = provider2;
        this.metricsRecorderProvider = provider3;
    }

    public static BillingClientStatusProvider_Factory create(Provider<BillingClientProvider> provider, Provider<Handler> provider2, Provider<MetricsRecorder> provider3) {
        return new BillingClientStatusProvider_Factory(provider, provider2, provider3);
    }

    public static BillingClientStatusProvider newInstance(BillingClientProvider billingClientProvider, Handler handler, MetricsRecorder metricsRecorder) {
        return new BillingClientStatusProvider(billingClientProvider, handler, metricsRecorder);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BillingClientStatusProvider get() {
        return new BillingClientStatusProvider(this.billingClientProvider.get(), this.handlerProvider.get(), this.metricsRecorderProvider.get());
    }
}
