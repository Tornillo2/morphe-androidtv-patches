package com.amazon.livingroom.auth;

import com.amazon.ignitionshared.filesystem.LocalStorage;
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
public final class RefreshTokenProvider_Factory implements Factory<RefreshTokenProvider> {
    public final Provider<LocalStorage> localStorageProvider;
    public final Provider<RefreshTokenParser> parserProvider;

    public RefreshTokenProvider_Factory(Provider<LocalStorage> provider, Provider<RefreshTokenParser> provider2) {
        this.localStorageProvider = provider;
        this.parserProvider = provider2;
    }

    public static RefreshTokenProvider_Factory create(Provider<LocalStorage> provider, Provider<RefreshTokenParser> provider2) {
        return new RefreshTokenProvider_Factory(provider, provider2);
    }

    public static RefreshTokenProvider newInstance(LocalStorage localStorage, RefreshTokenParser refreshTokenParser) {
        return new RefreshTokenProvider(localStorage, refreshTokenParser);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RefreshTokenProvider get() {
        return new RefreshTokenProvider(this.localStorageProvider.get(), this.parserProvider.get());
    }
}
