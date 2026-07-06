package com.amazon.ignitionshared;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class IgnitionContextProvider_Factory implements Factory<IgnitionContextProvider> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final IgnitionContextProvider_Factory INSTANCE = new IgnitionContextProvider_Factory();
    }

    public static IgnitionContextProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static IgnitionContextProvider newInstance() {
        return new IgnitionContextProvider();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public IgnitionContextProvider get() {
        return new IgnitionContextProvider();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new IgnitionContextProvider();
    }
}
