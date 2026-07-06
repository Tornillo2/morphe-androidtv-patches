package com.amazon.livingroom.appstartupconfig;

import com.amazon.livingroom.auth.RefreshTokenProvider;
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
public final class AppStartupConfigProvider_Factory implements Factory<AppStartupConfigProvider> {
    public final Provider<AppStartupConfigCache> cacheProvider;
    public final Provider<RefreshTokenProvider> refreshTokenProvider;
    public final Provider<AppStartupConfigRequester> requesterProvider;

    public AppStartupConfigProvider_Factory(Provider<AppStartupConfigRequester> provider, Provider<AppStartupConfigCache> provider2, Provider<RefreshTokenProvider> provider3) {
        this.requesterProvider = provider;
        this.cacheProvider = provider2;
        this.refreshTokenProvider = provider3;
    }

    public static AppStartupConfigProvider_Factory create(Provider<AppStartupConfigRequester> provider, Provider<AppStartupConfigCache> provider2, Provider<RefreshTokenProvider> provider3) {
        return new AppStartupConfigProvider_Factory(provider, provider2, provider3);
    }

    public static AppStartupConfigProvider newInstance(AppStartupConfigRequester appStartupConfigRequester, AppStartupConfigCache appStartupConfigCache, RefreshTokenProvider refreshTokenProvider) {
        return new AppStartupConfigProvider(appStartupConfigRequester, appStartupConfigCache, refreshTokenProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AppStartupConfigProvider get() {
        return new AppStartupConfigProvider(this.requesterProvider.get(), this.cacheProvider.get(), this.refreshTokenProvider.get());
    }
}
