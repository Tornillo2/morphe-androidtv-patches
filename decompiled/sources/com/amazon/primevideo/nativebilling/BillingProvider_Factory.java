package com.amazon.primevideo.nativebilling;

import android.os.Handler;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.IgnitionContextProvider;
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
public final class BillingProvider_Factory implements Factory<BillingProvider> {
    public final Provider<BillingClientProvider> billingClientProvider;
    public final Provider<IgnitionContextProvider> contextProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;
    public final Provider<Handler> handlerProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<ProductDetailParamsFactory> productDetailParamsFactoryProvider;

    public BillingProvider_Factory(Provider<GMBMessageSender> provider, Provider<MetricsRecorder> provider2, Provider<BillingClientProvider> provider3, Provider<ProductDetailParamsFactory> provider4, Provider<Handler> provider5, Provider<IgnitionContextProvider> provider6) {
        this.gmbMessageSenderProvider = provider;
        this.metricsRecorderProvider = provider2;
        this.billingClientProvider = provider3;
        this.productDetailParamsFactoryProvider = provider4;
        this.handlerProvider = provider5;
        this.contextProvider = provider6;
    }

    public static BillingProvider_Factory create(Provider<GMBMessageSender> provider, Provider<MetricsRecorder> provider2, Provider<BillingClientProvider> provider3, Provider<ProductDetailParamsFactory> provider4, Provider<Handler> provider5, Provider<IgnitionContextProvider> provider6) {
        return new BillingProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static BillingProvider newInstance(GMBMessageSender gMBMessageSender, MetricsRecorder metricsRecorder, BillingClientProvider billingClientProvider, ProductDetailParamsFactory productDetailParamsFactory, Handler handler, IgnitionContextProvider ignitionContextProvider) {
        return new BillingProvider(gMBMessageSender, metricsRecorder, billingClientProvider, productDetailParamsFactory, handler, ignitionContextProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BillingProvider get() {
        return new BillingProvider(this.gmbMessageSenderProvider.get(), this.metricsRecorderProvider.get(), this.billingClientProvider.get(), this.productDetailParamsFactoryProvider.get(), this.handlerProvider.get(), this.contextProvider.get());
    }
}
