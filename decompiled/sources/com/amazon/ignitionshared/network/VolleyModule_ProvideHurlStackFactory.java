package com.amazon.ignitionshared.network;

import com.amazon.ignitionshared.network.VolleyModule;
import com.android.volley.toolbox.HurlStack;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class VolleyModule_ProvideHurlStackFactory implements Factory<HurlStack> {
    public final Provider<SecureSslSocketFactory> secureSslSocketFactoryProvider;

    public VolleyModule_ProvideHurlStackFactory(Provider<SecureSslSocketFactory> provider) {
        this.secureSslSocketFactoryProvider = provider;
    }

    public static VolleyModule_ProvideHurlStackFactory create(Provider<SecureSslSocketFactory> provider) {
        return new VolleyModule_ProvideHurlStackFactory(provider);
    }

    public static HurlStack provideHurlStack(Object obj) {
        return VolleyModule.CC.provideHurlStack((SecureSslSocketFactory) obj);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public HurlStack get() {
        return VolleyModule.CC.provideHurlStack(this.secureSslSocketFactoryProvider.get());
    }
}
