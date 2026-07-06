package com.amazon.livingroom.mediapipelinebackend;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class WidevineCapabilitiesProvider_Factory implements Factory<WidevineCapabilitiesProvider> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final WidevineCapabilitiesProvider_Factory INSTANCE = new WidevineCapabilitiesProvider_Factory();
    }

    public static WidevineCapabilitiesProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static WidevineCapabilitiesProvider newInstance() {
        return new WidevineCapabilitiesProvider();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WidevineCapabilitiesProvider get() {
        return new WidevineCapabilitiesProvider();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new WidevineCapabilitiesProvider();
    }
}
