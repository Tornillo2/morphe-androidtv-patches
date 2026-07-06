package com.amazon.livingroom.mediapipelinebackend;

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
@QualifierMetadata
public final class DrmProvisioner_Factory implements Factory<DrmProvisioner> {
    public final Provider<RequestQueue> requestQueueProvider;

    public DrmProvisioner_Factory(Provider<RequestQueue> provider) {
        this.requestQueueProvider = provider;
    }

    public static DrmProvisioner_Factory create(Provider<RequestQueue> provider) {
        return new DrmProvisioner_Factory(provider);
    }

    public static DrmProvisioner newInstance(RequestQueue requestQueue) {
        return new DrmProvisioner(requestQueue);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DrmProvisioner get() {
        return new DrmProvisioner(this.requestQueueProvider.get());
    }
}
