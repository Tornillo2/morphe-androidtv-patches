package com.amazon.livingroom.appstartupconfig;

import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.livingroom.localisation.DeviceLocaleProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class GetAppStartupConfigUriFactory_Factory implements Factory<GetAppStartupConfigUriFactory> {
    public final Provider<DeviceLocaleProvider> deviceLocaleProvider;
    public final Provider<PlatformDeviceProperties> devicePropertiesProvider;
    public final Provider<List<String>> languageTagsProvider;
    public final Provider<TerminatorIdProvider> terminatorIdProvider;

    public GetAppStartupConfigUriFactory_Factory(Provider<PlatformDeviceProperties> provider, Provider<TerminatorIdProvider> provider2, Provider<DeviceLocaleProvider> provider3, Provider<List<String>> provider4) {
        this.devicePropertiesProvider = provider;
        this.terminatorIdProvider = provider2;
        this.deviceLocaleProvider = provider3;
        this.languageTagsProvider = provider4;
    }

    public static GetAppStartupConfigUriFactory_Factory create(Provider<PlatformDeviceProperties> provider, Provider<TerminatorIdProvider> provider2, Provider<DeviceLocaleProvider> provider3, Provider<List<String>> provider4) {
        return new GetAppStartupConfigUriFactory_Factory(provider, provider2, provider3, provider4);
    }

    public static GetAppStartupConfigUriFactory newInstance(PlatformDeviceProperties platformDeviceProperties, TerminatorIdProvider terminatorIdProvider, DeviceLocaleProvider deviceLocaleProvider, List<String> list) {
        return new GetAppStartupConfigUriFactory(platformDeviceProperties, terminatorIdProvider, deviceLocaleProvider, list);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public GetAppStartupConfigUriFactory get() {
        return new GetAppStartupConfigUriFactory(this.devicePropertiesProvider.get(), this.terminatorIdProvider.get(), this.deviceLocaleProvider.get(), this.languageTagsProvider.get());
    }
}
