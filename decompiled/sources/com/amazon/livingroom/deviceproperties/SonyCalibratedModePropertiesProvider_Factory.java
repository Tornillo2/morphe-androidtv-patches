package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class SonyCalibratedModePropertiesProvider_Factory implements Factory<SonyCalibratedModePropertiesProvider> {
    public final Provider<SonyCalibratedModeConnector> sonyCalibratedModeConnectorProvider;

    public SonyCalibratedModePropertiesProvider_Factory(Provider<SonyCalibratedModeConnector> provider) {
        this.sonyCalibratedModeConnectorProvider = provider;
    }

    public static SonyCalibratedModePropertiesProvider_Factory create(Provider<SonyCalibratedModeConnector> provider) {
        return new SonyCalibratedModePropertiesProvider_Factory(provider);
    }

    public static SonyCalibratedModePropertiesProvider newInstance(SonyCalibratedModeConnector sonyCalibratedModeConnector) {
        return new SonyCalibratedModePropertiesProvider(sonyCalibratedModeConnector);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SonyCalibratedModePropertiesProvider get() {
        return new SonyCalibratedModePropertiesProvider(this.sonyCalibratedModeConnectorProvider.get());
    }
}
