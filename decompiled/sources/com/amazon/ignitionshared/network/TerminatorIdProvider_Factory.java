package com.amazon.ignitionshared.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class TerminatorIdProvider_Factory implements Factory<TerminatorIdProvider> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final TerminatorIdProvider_Factory INSTANCE = new TerminatorIdProvider_Factory();
    }

    public static TerminatorIdProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static TerminatorIdProvider newInstance() {
        return new TerminatorIdProvider();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public TerminatorIdProvider get() {
        return new TerminatorIdProvider();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new TerminatorIdProvider();
    }
}
