package com.amazon.ignitionshared.recommendation;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
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
public final class BootstrapRequestStructureBuilder_Factory implements Factory<BootstrapRequestStructureBuilder> {
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public BootstrapRequestStructureBuilder_Factory(Provider<DeviceProperties> provider) {
        this.devicePropertiesProvider = provider;
    }

    public static BootstrapRequestStructureBuilder_Factory create(Provider<DeviceProperties> provider) {
        return new BootstrapRequestStructureBuilder_Factory(provider);
    }

    public static BootstrapRequestStructureBuilder newInstance(DeviceProperties deviceProperties) {
        return new BootstrapRequestStructureBuilder(deviceProperties);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BootstrapRequestStructureBuilder get() {
        return new BootstrapRequestStructureBuilder(this.devicePropertiesProvider.get());
    }
}
