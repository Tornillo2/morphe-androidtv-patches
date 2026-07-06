package com.amazon.livingroom.deviceproperties.dtid;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
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
public final class DtidRequester_Factory implements Factory<DtidRequester> {
    public final Provider<DtidRequestUriFactory> dtidRequestUriFactoryProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<RequestQueue> requestQueueProvider;
    public final Provider<RetryPolicy> retryPolicyProvider;

    public DtidRequester_Factory(Provider<DtidRequestUriFactory> provider, Provider<MetricsRecorder> provider2, Provider<RequestQueue> provider3, Provider<RetryPolicy> provider4) {
        this.dtidRequestUriFactoryProvider = provider;
        this.metricsRecorderProvider = provider2;
        this.requestQueueProvider = provider3;
        this.retryPolicyProvider = provider4;
    }

    public static DtidRequester_Factory create(Provider<DtidRequestUriFactory> provider, Provider<MetricsRecorder> provider2, Provider<RequestQueue> provider3, Provider<RetryPolicy> provider4) {
        return new DtidRequester_Factory(provider, provider2, provider3, provider4);
    }

    public static DtidRequester newInstance(DtidRequestUriFactory dtidRequestUriFactory, MetricsRecorder metricsRecorder, RequestQueue requestQueue, RetryPolicy retryPolicy) {
        return new DtidRequester(dtidRequestUriFactory, metricsRecorder, requestQueue, retryPolicy);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DtidRequester get() {
        return new DtidRequester(this.dtidRequestUriFactoryProvider.get(), this.metricsRecorderProvider.get(), this.requestQueueProvider.get(), this.retryPolicyProvider.get());
    }
}
