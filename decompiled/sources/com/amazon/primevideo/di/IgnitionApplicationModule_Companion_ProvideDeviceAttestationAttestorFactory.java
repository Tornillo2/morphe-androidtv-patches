package com.amazon.primevideo.di;

import com.amazon.ignitionshared.DeviceAttestationService;
import com.amazon.ignitionshared.NoopDeviceAttestor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class IgnitionApplicationModule_Companion_ProvideDeviceAttestationAttestorFactory implements Factory<DeviceAttestationService.Attestor> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final IgnitionApplicationModule_Companion_ProvideDeviceAttestationAttestorFactory INSTANCE = new IgnitionApplicationModule_Companion_ProvideDeviceAttestationAttestorFactory();
    }

    public static IgnitionApplicationModule_Companion_ProvideDeviceAttestationAttestorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DeviceAttestationService.Attestor provideDeviceAttestationAttestor() {
        IgnitionApplicationModule.Companion.getClass();
        return new NoopDeviceAttestor();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DeviceAttestationService.Attestor get() {
        return provideDeviceAttestationAttestor();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return provideDeviceAttestationAttestor();
    }
}
