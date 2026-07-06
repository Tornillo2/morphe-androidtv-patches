package com.amazon.livingroom.mediapipelinebackend;

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
public final class HdcpVersionProvider_Factory implements Factory<HdcpVersionProvider> {
    public final Provider<Context> contextProvider;
    public final Provider<WidevineCapabilitiesProvider> widevineCapabilitiesProvider;

    public HdcpVersionProvider_Factory(Provider<Context> provider, Provider<WidevineCapabilitiesProvider> provider2) {
        this.contextProvider = provider;
        this.widevineCapabilitiesProvider = provider2;
    }

    public static HdcpVersionProvider_Factory create(Provider<Context> provider, Provider<WidevineCapabilitiesProvider> provider2) {
        return new HdcpVersionProvider_Factory(provider, provider2);
    }

    public static HdcpVersionProvider newInstance(Context context, WidevineCapabilitiesProvider widevineCapabilitiesProvider) {
        return new HdcpVersionProvider(context, widevineCapabilitiesProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public HdcpVersionProvider get() {
        return new HdcpVersionProvider(this.contextProvider.get(), this.widevineCapabilitiesProvider.get());
    }
}
