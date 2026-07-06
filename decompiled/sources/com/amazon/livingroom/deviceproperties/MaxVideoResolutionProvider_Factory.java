package com.amazon.livingroom.deviceproperties;

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
public final class MaxVideoResolutionProvider_Factory implements Factory<MaxVideoResolutionProvider> {
    public final Provider<SystemProperties> systemPropertiesProvider;

    public MaxVideoResolutionProvider_Factory(Provider<SystemProperties> provider) {
        this.systemPropertiesProvider = provider;
    }

    public static MaxVideoResolutionProvider_Factory create(Provider<SystemProperties> provider) {
        return new MaxVideoResolutionProvider_Factory(provider);
    }

    public static MaxVideoResolutionProvider newInstance(Object obj) {
        return new MaxVideoResolutionProvider((SystemProperties) obj);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MaxVideoResolutionProvider get() {
        return newInstance(this.systemPropertiesProvider.get());
    }
}
