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
public final class VideoCapabilitiesProvider_Factory implements Factory<VideoCapabilitiesProvider> {
    public final Provider<DecoderCapabilitiesProvider> decoderCapabilitiesProvider;
    public final Provider<DisplayPropertiesProvider> displayPropertiesProvider;

    public VideoCapabilitiesProvider_Factory(Provider<DisplayPropertiesProvider> provider, Provider<DecoderCapabilitiesProvider> provider2) {
        this.displayPropertiesProvider = provider;
        this.decoderCapabilitiesProvider = provider2;
    }

    public static VideoCapabilitiesProvider_Factory create(Provider<DisplayPropertiesProvider> provider, Provider<DecoderCapabilitiesProvider> provider2) {
        return new VideoCapabilitiesProvider_Factory(provider, provider2);
    }

    public static VideoCapabilitiesProvider newInstance(Object obj, DecoderCapabilitiesProvider decoderCapabilitiesProvider) {
        return new VideoCapabilitiesProvider((DisplayPropertiesProvider) obj, decoderCapabilitiesProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public VideoCapabilitiesProvider get() {
        return newInstance(this.displayPropertiesProvider.get(), this.decoderCapabilitiesProvider.get());
    }
}
