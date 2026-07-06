package com.amazon.livingroom.auth;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class ApplicationAccessTokenProvider_Factory implements Factory<ApplicationAccessTokenProvider> {
    public final Provider<ApplicationAccessTokenRequester> applicationAccessTokenRequesterProvider;
    public final Provider<Context> contextProvider;
    public final Provider<RefreshTokenProvider> refreshTokenProvider;

    public ApplicationAccessTokenProvider_Factory(Provider<Context> provider, Provider<ApplicationAccessTokenRequester> provider2, Provider<RefreshTokenProvider> provider3) {
        this.contextProvider = provider;
        this.applicationAccessTokenRequesterProvider = provider2;
        this.refreshTokenProvider = provider3;
    }

    public static ApplicationAccessTokenProvider_Factory create(Provider<Context> provider, Provider<ApplicationAccessTokenRequester> provider2, Provider<RefreshTokenProvider> provider3) {
        return new ApplicationAccessTokenProvider_Factory(provider, provider2, provider3);
    }

    public static ApplicationAccessTokenProvider newInstance(Context context, ApplicationAccessTokenRequester applicationAccessTokenRequester, RefreshTokenProvider refreshTokenProvider) {
        return new ApplicationAccessTokenProvider(context, applicationAccessTokenRequester, refreshTokenProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ApplicationAccessTokenProvider get() {
        return new ApplicationAccessTokenProvider(this.contextProvider.get(), this.applicationAccessTokenRequesterProvider.get(), this.refreshTokenProvider.get());
    }
}
