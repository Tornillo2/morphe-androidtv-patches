package com.amazon.primevideo.nativebilling;

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
public final class GoogleBillingProperties_Factory implements Factory<GoogleBillingProperties> {
    public final Provider<BillingClientStatusProvider> billingClientStatusProvider;

    public GoogleBillingProperties_Factory(Provider<BillingClientStatusProvider> provider) {
        this.billingClientStatusProvider = provider;
    }

    public static GoogleBillingProperties_Factory create(Provider<BillingClientStatusProvider> provider) {
        return new GoogleBillingProperties_Factory(provider);
    }

    public static GoogleBillingProperties newInstance(BillingClientStatusProvider billingClientStatusProvider) {
        return new GoogleBillingProperties(billingClientStatusProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public GoogleBillingProperties get() {
        return new GoogleBillingProperties(this.billingClientStatusProvider.get());
    }
}
