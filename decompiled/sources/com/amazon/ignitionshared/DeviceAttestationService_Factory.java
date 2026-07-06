package com.amazon.ignitionshared;

import com.amazon.ignitionshared.DeviceAttestationService;
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
public final class DeviceAttestationService_Factory implements Factory<DeviceAttestationService> {
    public final Provider<DeviceAttestationService.Attestor> attestorProvider;

    public DeviceAttestationService_Factory(Provider<DeviceAttestationService.Attestor> provider) {
        this.attestorProvider = provider;
    }

    public static DeviceAttestationService_Factory create(Provider<DeviceAttestationService.Attestor> provider) {
        return new DeviceAttestationService_Factory(provider);
    }

    public static DeviceAttestationService newInstance(DeviceAttestationService.Attestor attestor) {
        return new DeviceAttestationService(attestor);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DeviceAttestationService get() {
        return new DeviceAttestationService(this.attestorProvider.get());
    }
}
