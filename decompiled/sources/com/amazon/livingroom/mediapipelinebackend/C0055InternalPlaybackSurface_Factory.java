package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface_Factory, reason: case insensitive filesystem */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class C0055InternalPlaybackSurface_Factory {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<IgnitionContextProvider> ignitionContextProvider;
    public final Provider<Handler> uiThreadHandlerProvider;

    public C0055InternalPlaybackSurface_Factory(Provider<IgnitionContextProvider> provider, Provider<Handler> provider2, Provider<DeviceProperties> provider3) {
        this.ignitionContextProvider = provider;
        this.uiThreadHandlerProvider = provider2;
        this.devicePropertiesProvider = provider3;
    }

    public static C0055InternalPlaybackSurface_Factory create(Provider<IgnitionContextProvider> provider, Provider<Handler> provider2, Provider<DeviceProperties> provider3) {
        return new C0055InternalPlaybackSurface_Factory(provider, provider2, provider3);
    }

    public static InternalPlaybackSurface newInstance(String str, IgnitionContextProvider ignitionContextProvider, Handler handler, DeviceProperties deviceProperties) {
        return new InternalPlaybackSurface(str, ignitionContextProvider, handler, deviceProperties);
    }

    public InternalPlaybackSurface get(String str) {
        return new InternalPlaybackSurface(str, this.ignitionContextProvider.get(), this.uiThreadHandlerProvider.get(), this.devicePropertiesProvider.get());
    }
}
