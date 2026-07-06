package com.amazon.livingroom.mediapipelinebackend;

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
public final class HdcpChecker_Factory implements Factory<HdcpChecker> {
    public final Provider<HdcpVersionProvider> hdcpVersionProvider;

    public HdcpChecker_Factory(Provider<HdcpVersionProvider> provider) {
        this.hdcpVersionProvider = provider;
    }

    public static HdcpChecker_Factory create(Provider<HdcpVersionProvider> provider) {
        return new HdcpChecker_Factory(provider);
    }

    public static HdcpChecker newInstance(HdcpVersionProvider hdcpVersionProvider) {
        return new HdcpChecker(hdcpVersionProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public HdcpChecker get() {
        return new HdcpChecker(this.hdcpVersionProvider.get());
    }
}
