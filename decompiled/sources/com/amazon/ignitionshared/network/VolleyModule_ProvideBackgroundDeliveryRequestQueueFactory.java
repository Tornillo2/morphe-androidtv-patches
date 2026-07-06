package com.amazon.ignitionshared.network;

import android.content.Context;
import com.amazon.ignitionshared.network.VolleyModule;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named", "com.amazon.livingroom.di.ApplicationContext"})
public final class VolleyModule_ProvideBackgroundDeliveryRequestQueueFactory implements Factory<RequestQueue> {
    public final Provider<Context> contextProvider;
    public final Provider<HurlStack> hurlStackProvider;

    public VolleyModule_ProvideBackgroundDeliveryRequestQueueFactory(Provider<Context> provider, Provider<HurlStack> provider2) {
        this.contextProvider = provider;
        this.hurlStackProvider = provider2;
    }

    public static VolleyModule_ProvideBackgroundDeliveryRequestQueueFactory create(Provider<Context> provider, Provider<HurlStack> provider2) {
        return new VolleyModule_ProvideBackgroundDeliveryRequestQueueFactory(provider, provider2);
    }

    public static RequestQueue provideBackgroundDeliveryRequestQueue(Context context, HurlStack hurlStack) {
        return VolleyModule.CC.provideBackgroundDeliveryRequestQueue(context, hurlStack);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RequestQueue get() {
        return VolleyModule.CC.provideBackgroundDeliveryRequestQueue(this.contextProvider.get(), this.hurlStackProvider.get());
    }
}
