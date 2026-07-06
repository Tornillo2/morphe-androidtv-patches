package com.amazon.primevideo.nativebilling;

import com.amazon.ignitionshared.GMBMessageProcessor;
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
public final class BillingServiceInitializer_Factory implements Factory<BillingServiceInitializer> {
    public final Provider<BillingProvider> billingProvider;
    public final Provider<GMBMessageProcessor> gmbMessageProcessorProvider;

    public BillingServiceInitializer_Factory(Provider<GMBMessageProcessor> provider, Provider<BillingProvider> provider2) {
        this.gmbMessageProcessorProvider = provider;
        this.billingProvider = provider2;
    }

    public static BillingServiceInitializer_Factory create(Provider<GMBMessageProcessor> provider, Provider<BillingProvider> provider2) {
        return new BillingServiceInitializer_Factory(provider, provider2);
    }

    public static BillingServiceInitializer newInstance(GMBMessageProcessor gMBMessageProcessor, BillingProvider billingProvider) {
        return new BillingServiceInitializer(gMBMessageProcessor, billingProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BillingServiceInitializer get() {
        return new BillingServiceInitializer(this.gmbMessageProcessorProvider.get(), this.billingProvider.get());
    }
}
