package com.amazon.livingroom.deviceproperties;

import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.dtid.DtidProvider;
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
public final class DefaultDeviceProperties_Factory implements Factory<DefaultDeviceProperties> {
    public final Provider<AdvertisingProperties> advertisingPropertiesProvider;
    public final Provider<ApplicationPackagePropertiesProvider> applicationPackagePropertiesProvider;
    public final Provider<ApplicationSourcePropertiesProvider> applicationSourcePropertiesProvider;
    public final Provider<AudioProperties> audioPropertiesProvider;
    public final Provider<BillingProperties> billingPropertiesProvider;
    public final Provider<DeviceBuildProperties> deviceBuildPropertiesProvider;
    public final Provider<DeviceIdProvider> deviceIdProvider;
    public final Provider<DisplayModeProperties> displayModePropertiesProvider;
    public final Provider<DtidProvider> dtidProvider;
    public final Provider<NetworkProperties> networkPropertiesProvider;
    public final Provider<OperatingSystemProperties> operatingSystemPropertiesProvider;
    public final Provider<RecommendationsPropertiesProvider> recommendationsPropertiesProvider;
    public final Provider<SonyCalibratedModePropertiesProvider> sonyCalibratedModePropertiesProvider;
    public final Provider<TerminatorIdProvider> terminatorIdProvider;
    public final Provider<VideoCapabilitiesProvider> videoCapabilitiesProvider;

    public DefaultDeviceProperties_Factory(Provider<AdvertisingProperties> provider, Provider<DeviceBuildProperties> provider2, Provider<DeviceIdProvider> provider3, Provider<DtidProvider> provider4, Provider<ApplicationPackagePropertiesProvider> provider5, Provider<VideoCapabilitiesProvider> provider6, Provider<DisplayModeProperties> provider7, Provider<NetworkProperties> provider8, Provider<AudioProperties> provider9, Provider<OperatingSystemProperties> provider10, Provider<ApplicationSourcePropertiesProvider> provider11, Provider<BillingProperties> provider12, Provider<TerminatorIdProvider> provider13, Provider<SonyCalibratedModePropertiesProvider> provider14, Provider<RecommendationsPropertiesProvider> provider15) {
        this.advertisingPropertiesProvider = provider;
        this.deviceBuildPropertiesProvider = provider2;
        this.deviceIdProvider = provider3;
        this.dtidProvider = provider4;
        this.applicationPackagePropertiesProvider = provider5;
        this.videoCapabilitiesProvider = provider6;
        this.displayModePropertiesProvider = provider7;
        this.networkPropertiesProvider = provider8;
        this.audioPropertiesProvider = provider9;
        this.operatingSystemPropertiesProvider = provider10;
        this.applicationSourcePropertiesProvider = provider11;
        this.billingPropertiesProvider = provider12;
        this.terminatorIdProvider = provider13;
        this.sonyCalibratedModePropertiesProvider = provider14;
        this.recommendationsPropertiesProvider = provider15;
    }

    public static DefaultDeviceProperties_Factory create(Provider<AdvertisingProperties> provider, Provider<DeviceBuildProperties> provider2, Provider<DeviceIdProvider> provider3, Provider<DtidProvider> provider4, Provider<ApplicationPackagePropertiesProvider> provider5, Provider<VideoCapabilitiesProvider> provider6, Provider<DisplayModeProperties> provider7, Provider<NetworkProperties> provider8, Provider<AudioProperties> provider9, Provider<OperatingSystemProperties> provider10, Provider<ApplicationSourcePropertiesProvider> provider11, Provider<BillingProperties> provider12, Provider<TerminatorIdProvider> provider13, Provider<SonyCalibratedModePropertiesProvider> provider14, Provider<RecommendationsPropertiesProvider> provider15) {
        return new DefaultDeviceProperties_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    public static DefaultDeviceProperties newInstance(AdvertisingProperties advertisingProperties, Object obj, Object obj2, DtidProvider dtidProvider, Object obj3, VideoCapabilitiesProvider videoCapabilitiesProvider, DisplayModeProperties displayModeProperties, NetworkProperties networkProperties, AudioProperties audioProperties, OperatingSystemProperties operatingSystemProperties, ApplicationSourcePropertiesProvider applicationSourcePropertiesProvider, BillingProperties billingProperties, TerminatorIdProvider terminatorIdProvider, SonyCalibratedModePropertiesProvider sonyCalibratedModePropertiesProvider, RecommendationsPropertiesProvider recommendationsPropertiesProvider) {
        return new DefaultDeviceProperties(advertisingProperties, (DeviceBuildProperties) obj, (DeviceIdProvider) obj2, dtidProvider, (ApplicationPackagePropertiesProvider) obj3, videoCapabilitiesProvider, displayModeProperties, networkProperties, audioProperties, operatingSystemProperties, applicationSourcePropertiesProvider, billingProperties, terminatorIdProvider, sonyCalibratedModePropertiesProvider, recommendationsPropertiesProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DefaultDeviceProperties get() {
        return newInstance(this.advertisingPropertiesProvider.get(), this.deviceBuildPropertiesProvider.get(), this.deviceIdProvider.get(), this.dtidProvider.get(), this.applicationPackagePropertiesProvider.get(), this.videoCapabilitiesProvider.get(), this.displayModePropertiesProvider.get(), this.networkPropertiesProvider.get(), this.audioPropertiesProvider.get(), this.operatingSystemPropertiesProvider.get(), this.applicationSourcePropertiesProvider.get(), this.billingPropertiesProvider.get(), this.terminatorIdProvider.get(), this.sonyCalibratedModePropertiesProvider.get(), this.recommendationsPropertiesProvider.get());
    }
}
