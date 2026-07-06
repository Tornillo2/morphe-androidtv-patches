package com.amazon.primevideo.nativebilling;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class BillingClientProvider_Factory implements Factory<BillingClientProvider> {
    public final Provider<Context> contextProvider;

    public BillingClientProvider_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static BillingClientProvider_Factory create(Provider<Context> provider) {
        return new BillingClientProvider_Factory(provider);
    }

    public static BillingClientProvider newInstance(Context context) {
        return new BillingClientProvider(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BillingClientProvider get() {
        return new BillingClientProvider(this.contextProvider.get());
    }
}
