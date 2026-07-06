package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.core.MediaCodecMediaPipelineBackendApiImpl;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendApiCallbacks;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance;
import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.FtvMpbApi_Factory, reason: case insensitive filesystem */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class C0052FtvMpbApi_Factory {
    public final Provider<AudioFocusManager> audioFocusManagerProvider;
    public final Provider<FtvMpbInstance.Factory> instanceFactoryProvider;
    public final Provider<InternalPlaybackSurface.Factory> internalSurfaceFactoryProvider;
    public final Provider<MediaSessionAdapter> mediaSessionAdapterProvider;
    public final Provider<WakeLocker> wakeLockerProvider;

    public C0052FtvMpbApi_Factory(Provider<FtvMpbInstance.Factory> provider, Provider<InternalPlaybackSurface.Factory> provider2, Provider<WakeLocker> provider3, Provider<AudioFocusManager> provider4, Provider<MediaSessionAdapter> provider5) {
        this.instanceFactoryProvider = provider;
        this.internalSurfaceFactoryProvider = provider2;
        this.wakeLockerProvider = provider3;
        this.audioFocusManagerProvider = provider4;
        this.mediaSessionAdapterProvider = provider5;
    }

    public static C0052FtvMpbApi_Factory create(Provider<FtvMpbInstance.Factory> provider, Provider<InternalPlaybackSurface.Factory> provider2, Provider<WakeLocker> provider3, Provider<AudioFocusManager> provider4, Provider<MediaSessionAdapter> provider5) {
        return new C0052FtvMpbApi_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static FtvMpbApi newInstance(MediaCodecMediaPipelineBackendApiImpl mediaCodecMediaPipelineBackendApiImpl, MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks, FtvMpbInstance.Factory factory, InternalPlaybackSurface.Factory factory2, WakeLocker wakeLocker, AudioFocusManager audioFocusManager, MediaSessionAdapter mediaSessionAdapter) {
        return new FtvMpbApi(mediaCodecMediaPipelineBackendApiImpl, mediaPipelineBackendApiCallbacks, factory, factory2, wakeLocker, audioFocusManager, mediaSessionAdapter);
    }

    public FtvMpbApi get(MediaCodecMediaPipelineBackendApiImpl mediaCodecMediaPipelineBackendApiImpl, MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks) {
        return new FtvMpbApi(mediaCodecMediaPipelineBackendApiImpl, mediaPipelineBackendApiCallbacks, this.instanceFactoryProvider.get(), this.internalSurfaceFactoryProvider.get(), this.wakeLockerProvider.get(), this.audioFocusManagerProvider.get(), this.mediaSessionAdapterProvider.get());
    }
}
