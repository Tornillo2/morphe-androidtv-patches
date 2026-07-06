package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine;
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
public final class MediaPipelineBackendEngineManager_Factory implements Factory<MediaPipelineBackendEngineManager> {
    public final Provider<AudioFocusManager> audioFocusManagerProvider;
    public final Provider<CodecQuerier> codecQuerierProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<MediaSessionAdapter> mediaSessionAdapterProvider;
    public final Provider<MediaPipelineBackendEngine.Factory> mpbEngineFactoryProvider;
    public final Provider<SonyCalibratedModeController> sonyCalibratedModeControllerProvider;
    public final Provider<InternalPlaybackSurface.Factory> surfaceFactoryProvider;
    public final Provider<WakeLocker> wakeLockerProvider;

    public MediaPipelineBackendEngineManager_Factory(Provider<MediaPipelineBackendEngine.Factory> provider, Provider<DeviceProperties> provider2, Provider<SonyCalibratedModeController> provider3, Provider<MediaSessionAdapter> provider4, Provider<AudioFocusManager> provider5, Provider<WakeLocker> provider6, Provider<CodecQuerier> provider7, Provider<InternalPlaybackSurface.Factory> provider8) {
        this.mpbEngineFactoryProvider = provider;
        this.devicePropertiesProvider = provider2;
        this.sonyCalibratedModeControllerProvider = provider3;
        this.mediaSessionAdapterProvider = provider4;
        this.audioFocusManagerProvider = provider5;
        this.wakeLockerProvider = provider6;
        this.codecQuerierProvider = provider7;
        this.surfaceFactoryProvider = provider8;
    }

    public static MediaPipelineBackendEngineManager_Factory create(Provider<MediaPipelineBackendEngine.Factory> provider, Provider<DeviceProperties> provider2, Provider<SonyCalibratedModeController> provider3, Provider<MediaSessionAdapter> provider4, Provider<AudioFocusManager> provider5, Provider<WakeLocker> provider6, Provider<CodecQuerier> provider7, Provider<InternalPlaybackSurface.Factory> provider8) {
        return new MediaPipelineBackendEngineManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static MediaPipelineBackendEngineManager newInstance(Object obj, DeviceProperties deviceProperties, SonyCalibratedModeController sonyCalibratedModeController, MediaSessionAdapter mediaSessionAdapter, AudioFocusManager audioFocusManager, WakeLocker wakeLocker, CodecQuerier codecQuerier, InternalPlaybackSurface.Factory factory) {
        return new MediaPipelineBackendEngineManager((MediaPipelineBackendEngine.Factory) obj, deviceProperties, sonyCalibratedModeController, mediaSessionAdapter, audioFocusManager, wakeLocker, codecQuerier, factory);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MediaPipelineBackendEngineManager get() {
        return newInstance(this.mpbEngineFactoryProvider.get(), this.devicePropertiesProvider.get(), this.sonyCalibratedModeControllerProvider.get(), this.mediaSessionAdapterProvider.get(), this.audioFocusManagerProvider.get(), this.wakeLockerProvider.get(), this.codecQuerierProvider.get(), this.surfaceFactoryProvider.get());
    }
}
