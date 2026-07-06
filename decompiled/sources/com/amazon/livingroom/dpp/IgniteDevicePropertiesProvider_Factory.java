package com.amazon.livingroom.dpp;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.DisplayInformation;
import com.amazon.livingroom.mediapipelinebackend.HdcpChecker;
import com.amazon.livingroom.mediapipelinebackend.WidevineCapabilitiesProvider;
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
public final class IgniteDevicePropertiesProvider_Factory implements Factory<IgniteDevicePropertiesProvider> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<DisplayInformation> displayInformationProvider;
    public final Provider<HdcpChecker> hdcpCheckerProvider;
    public final Provider<WidevineCapabilitiesProvider> widevineCapabilitiesProvider;

    public IgniteDevicePropertiesProvider_Factory(Provider<DeviceProperties> provider, Provider<DisplayInformation> provider2, Provider<HdcpChecker> provider3, Provider<WidevineCapabilitiesProvider> provider4) {
        this.devicePropertiesProvider = provider;
        this.displayInformationProvider = provider2;
        this.hdcpCheckerProvider = provider3;
        this.widevineCapabilitiesProvider = provider4;
    }

    public static IgniteDevicePropertiesProvider_Factory create(Provider<DeviceProperties> provider, Provider<DisplayInformation> provider2, Provider<HdcpChecker> provider3, Provider<WidevineCapabilitiesProvider> provider4) {
        return new IgniteDevicePropertiesProvider_Factory(provider, provider2, provider3, provider4);
    }

    public static IgniteDevicePropertiesProvider newInstance(DeviceProperties deviceProperties, DisplayInformation displayInformation, HdcpChecker hdcpChecker, WidevineCapabilitiesProvider widevineCapabilitiesProvider) {
        return new IgniteDevicePropertiesProvider(deviceProperties, displayInformation, hdcpChecker, widevineCapabilitiesProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public IgniteDevicePropertiesProvider get() {
        return new IgniteDevicePropertiesProvider(this.devicePropertiesProvider.get(), this.displayInformationProvider.get(), this.hdcpCheckerProvider.get(), this.widevineCapabilitiesProvider.get());
    }
}
