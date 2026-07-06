package com.amazon.livingroom.appstartupconfig;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.auth.ApplicationAccessTokenProvider;
import com.amazon.livingroom.auth.RefreshTokenProvider;
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
public final class AppStartupConfigRequester_Factory implements Factory<AppStartupConfigRequester> {
    public final Provider<ApplicationAccessTokenProvider> applicationAccessTokenProvider;
    public final Provider<AppStartupConfigCache> cacheProvider;
    public final Provider<GetAppStartupConfigUriFactory> getAppStartupConfigUriFactoryProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<RefreshTokenProvider> refreshTokenProvider;
    public final Provider<RequestQueue> requestQueueProvider;
    public final Provider<RetryPolicy> retryPolicyProvider;

    public AppStartupConfigRequester_Factory(Provider<RequestQueue> provider, Provider<GetAppStartupConfigUriFactory> provider2, Provider<RetryPolicy> provider3, Provider<AppStartupConfigCache> provider4, Provider<MetricsRecorder> provider5, Provider<ApplicationAccessTokenProvider> provider6, Provider<RefreshTokenProvider> provider7) {
        this.requestQueueProvider = provider;
        this.getAppStartupConfigUriFactoryProvider = provider2;
        this.retryPolicyProvider = provider3;
        this.cacheProvider = provider4;
        this.metricsRecorderProvider = provider5;
        this.applicationAccessTokenProvider = provider6;
        this.refreshTokenProvider = provider7;
    }

    public static AppStartupConfigRequester_Factory create(Provider<RequestQueue> provider, Provider<GetAppStartupConfigUriFactory> provider2, Provider<RetryPolicy> provider3, Provider<AppStartupConfigCache> provider4, Provider<MetricsRecorder> provider5, Provider<ApplicationAccessTokenProvider> provider6, Provider<RefreshTokenProvider> provider7) {
        return new AppStartupConfigRequester_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static AppStartupConfigRequester newInstance(RequestQueue requestQueue, Object obj, RetryPolicy retryPolicy, AppStartupConfigCache appStartupConfigCache, MetricsRecorder metricsRecorder, ApplicationAccessTokenProvider applicationAccessTokenProvider, RefreshTokenProvider refreshTokenProvider) {
        return new AppStartupConfigRequester(requestQueue, (GetAppStartupConfigUriFactory) obj, retryPolicy, appStartupConfigCache, metricsRecorder, applicationAccessTokenProvider, refreshTokenProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AppStartupConfigRequester get() {
        return newInstance(this.requestQueueProvider.get(), this.getAppStartupConfigUriFactoryProvider.get(), this.retryPolicyProvider.get(), this.cacheProvider.get(), this.metricsRecorderProvider.get(), this.applicationAccessTokenProvider.get(), this.refreshTokenProvider.get());
    }
}
