package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import com.amazon.ignitionshared.IgnitionContextProvider;
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
public final class WakeLocker_Factory implements Factory<WakeLocker> {
    public final Provider<Handler> handlerProvider;
    public final Provider<IgnitionContextProvider> ignitionContextProvider;

    public WakeLocker_Factory(Provider<IgnitionContextProvider> provider, Provider<Handler> provider2) {
        this.ignitionContextProvider = provider;
        this.handlerProvider = provider2;
    }

    public static WakeLocker_Factory create(Provider<IgnitionContextProvider> provider, Provider<Handler> provider2) {
        return new WakeLocker_Factory(provider, provider2);
    }

    public static WakeLocker newInstance(IgnitionContextProvider ignitionContextProvider, Handler handler) {
        return new WakeLocker(ignitionContextProvider, handler);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WakeLocker get() {
        return new WakeLocker(this.ignitionContextProvider.get(), this.handlerProvider.get());
    }
}
