package com.amazon.livingroom.deviceproperties.dtid;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class DtidProvider_Factory implements Factory<DtidProvider> {
    public final Provider<String> defaultDtidProvider;
    public final Provider<DtidCache> dtidCacheProvider;
    public final Provider<DtidRequester> dtidRequesterProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;

    public DtidProvider_Factory(Provider<DtidRequester> provider, Provider<DtidCache> provider2, Provider<MetricsRecorder> provider3, Provider<String> provider4) {
        this.dtidRequesterProvider = provider;
        this.dtidCacheProvider = provider2;
        this.metricsRecorderProvider = provider3;
        this.defaultDtidProvider = provider4;
    }

    public static DtidProvider_Factory create(Provider<DtidRequester> provider, Provider<DtidCache> provider2, Provider<MetricsRecorder> provider3, Provider<String> provider4) {
        return new DtidProvider_Factory(provider, provider2, provider3, provider4);
    }

    public static DtidProvider newInstance(DtidRequester dtidRequester, DtidCache dtidCache, MetricsRecorder metricsRecorder, String str) {
        return new DtidProvider(dtidRequester, dtidCache, metricsRecorder, str);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DtidProvider get() {
        return new DtidProvider(this.dtidRequesterProvider.get(), this.dtidCacheProvider.get(), this.metricsRecorderProvider.get(), this.defaultDtidProvider.get());
    }
}
