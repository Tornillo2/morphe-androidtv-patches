package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
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
public final class SonyCalibratedModeController_Factory implements Factory<SonyCalibratedModeController> {
    public final Provider<SonyCalibratedModeConnector> connectorProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<ApplicationVisibilityMonitor> visibilityMonitorProvider;

    public SonyCalibratedModeController_Factory(Provider<SonyCalibratedModeConnector> provider, Provider<DeviceProperties> provider2, Provider<ApplicationVisibilityMonitor> provider3) {
        this.connectorProvider = provider;
        this.devicePropertiesProvider = provider2;
        this.visibilityMonitorProvider = provider3;
    }

    public static SonyCalibratedModeController_Factory create(Provider<SonyCalibratedModeConnector> provider, Provider<DeviceProperties> provider2, Provider<ApplicationVisibilityMonitor> provider3) {
        return new SonyCalibratedModeController_Factory(provider, provider2, provider3);
    }

    public static SonyCalibratedModeController newInstance(SonyCalibratedModeConnector sonyCalibratedModeConnector, DeviceProperties deviceProperties, ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        return new SonyCalibratedModeController(sonyCalibratedModeConnector, deviceProperties, applicationVisibilityMonitor);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SonyCalibratedModeController get() {
        return new SonyCalibratedModeController(this.connectorProvider.get(), this.devicePropertiesProvider.get(), this.visibilityMonitorProvider.get());
    }
}
