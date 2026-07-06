package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {
    public final Provider<Context> applicationContextProvider;
    public final Provider<CreationContextFactory> creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(Provider<Context> provider, Provider<CreationContextFactory> provider2) {
        this.applicationContextProvider = provider;
        this.creationContextFactoryProvider = provider2;
    }

    public static MetadataBackendRegistry_Factory create(Provider<Context> provider, Provider<CreationContextFactory> provider2) {
        return new MetadataBackendRegistry_Factory(provider, provider2);
    }

    public static MetadataBackendRegistry newInstance(Context context, Object obj) {
        return new MetadataBackendRegistry(context, (CreationContextFactory) obj);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MetadataBackendRegistry get() {
        return newInstance(this.applicationContextProvider.get(), this.creationContextFactoryProvider.get());
    }
}
