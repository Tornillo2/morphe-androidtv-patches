package com.amazon.livingroom.deviceproperties;

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
public final class DeviceBuildProperties_Factory implements Factory<DeviceBuildProperties> {
    public final Provider<SystemProperties> systemPropertiesProvider;

    public DeviceBuildProperties_Factory(Provider<SystemProperties> provider) {
        this.systemPropertiesProvider = provider;
    }

    public static DeviceBuildProperties_Factory create(Provider<SystemProperties> provider) {
        return new DeviceBuildProperties_Factory(provider);
    }

    public static DeviceBuildProperties newInstance(Object obj) {
        return new DeviceBuildProperties((SystemProperties) obj);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DeviceBuildProperties get() {
        return newInstance(this.systemPropertiesProvider.get());
    }
}
