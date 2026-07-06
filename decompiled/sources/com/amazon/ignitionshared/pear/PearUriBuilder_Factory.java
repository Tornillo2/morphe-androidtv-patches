package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class PearUriBuilder_Factory implements Factory<PearUriBuilder> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<Map<String, String>> pearPlacementIdMapProvider;
    public final Provider<String> recommendationApplicationKeyProvider;
    public final Provider<TerminatorIdProvider> terminatorIdProvider;

    public PearUriBuilder_Factory(Provider<DeviceProperties> provider, Provider<TerminatorIdProvider> provider2, Provider<Map<String, String>> provider3, Provider<String> provider4) {
        this.devicePropertiesProvider = provider;
        this.terminatorIdProvider = provider2;
        this.pearPlacementIdMapProvider = provider3;
        this.recommendationApplicationKeyProvider = provider4;
    }

    public static PearUriBuilder_Factory create(Provider<DeviceProperties> provider, Provider<TerminatorIdProvider> provider2, Provider<Map<String, String>> provider3, Provider<String> provider4) {
        return new PearUriBuilder_Factory(provider, provider2, provider3, provider4);
    }

    public static PearUriBuilder newInstance(DeviceProperties deviceProperties, TerminatorIdProvider terminatorIdProvider, Map<String, String> map, String str) {
        return new PearUriBuilder(deviceProperties, terminatorIdProvider, map, str);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PearUriBuilder get() {
        return new PearUriBuilder(this.devicePropertiesProvider.get(), this.terminatorIdProvider.get(), this.pearPlacementIdMapProvider.get(), this.recommendationApplicationKeyProvider.get());
    }
}
