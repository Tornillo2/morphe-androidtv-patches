package com.amazon.livingroom.di;

import com.amazon.livingroom.deviceproperties.DefaultDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformProperty;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class CoreModule_Companion_ProvidePlatformDevicePropertiesFactory implements Factory<PlatformDeviceProperties> {
    public final Provider<DefaultDeviceProperties> devicePropertiesProvider;
    public final Provider<List<PlatformProperty<?>>> platformPropertiesProvider;

    public CoreModule_Companion_ProvidePlatformDevicePropertiesFactory(Provider<DefaultDeviceProperties> provider, Provider<List<PlatformProperty<?>>> provider2) {
        this.devicePropertiesProvider = provider;
        this.platformPropertiesProvider = provider2;
    }

    public static CoreModule_Companion_ProvidePlatformDevicePropertiesFactory create(Provider<DefaultDeviceProperties> provider, Provider<List<PlatformProperty<?>>> provider2) {
        return new CoreModule_Companion_ProvidePlatformDevicePropertiesFactory(provider, provider2);
    }

    public static PlatformDeviceProperties providePlatformDeviceProperties(DefaultDeviceProperties defaultDeviceProperties, List<PlatformProperty<?>> list) {
        return CoreModule.Companion.providePlatformDeviceProperties(defaultDeviceProperties, list);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PlatformDeviceProperties get() {
        return CoreModule.Companion.providePlatformDeviceProperties(this.devicePropertiesProvider.get(), this.platformPropertiesProvider.get());
    }
}
