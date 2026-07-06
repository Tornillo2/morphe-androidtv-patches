package com.amazon.livingroom.auth;

import com.android.volley.RequestQueue;
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
public final class ApplicationAccessTokenRequester_Factory implements Factory<ApplicationAccessTokenRequester> {
    public final Provider<String> applicationNameProvider;
    public final Provider<RequestQueue> requestQueueProvider;

    public ApplicationAccessTokenRequester_Factory(Provider<RequestQueue> provider, Provider<String> provider2) {
        this.requestQueueProvider = provider;
        this.applicationNameProvider = provider2;
    }

    public static ApplicationAccessTokenRequester_Factory create(Provider<RequestQueue> provider, Provider<String> provider2) {
        return new ApplicationAccessTokenRequester_Factory(provider, provider2);
    }

    public static ApplicationAccessTokenRequester newInstance(RequestQueue requestQueue, String str) {
        return new ApplicationAccessTokenRequester(requestQueue, str);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ApplicationAccessTokenRequester get() {
        return new ApplicationAccessTokenRequester(this.requestQueueProvider.get(), this.applicationNameProvider.get());
    }
}
