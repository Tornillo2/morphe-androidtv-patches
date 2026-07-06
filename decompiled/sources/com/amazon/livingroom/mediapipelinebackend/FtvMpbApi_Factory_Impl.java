package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.core.MediaCodecMediaPipelineBackendApiImpl;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendApiCallbacks;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
public final class FtvMpbApi_Factory_Impl implements FtvMpbApi.Factory {
    public final C0052FtvMpbApi_Factory delegateFactory;

    public FtvMpbApi_Factory_Impl(C0052FtvMpbApi_Factory c0052FtvMpbApi_Factory) {
        this.delegateFactory = c0052FtvMpbApi_Factory;
    }

    public static Provider<FtvMpbApi.Factory> createFactoryProvider(C0052FtvMpbApi_Factory c0052FtvMpbApi_Factory) {
        return InstanceFactory.create(new FtvMpbApi_Factory_Impl(c0052FtvMpbApi_Factory));
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.FtvMpbApi.Factory
    public FtvMpbApi create(MediaCodecMediaPipelineBackendApiImpl mediaCodecMediaPipelineBackendApiImpl, MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks) {
        return this.delegateFactory.get(mediaCodecMediaPipelineBackendApiImpl, mediaPipelineBackendApiCallbacks);
    }

    public static javax.inject.Provider<FtvMpbApi.Factory> create(C0052FtvMpbApi_Factory c0052FtvMpbApi_Factory) {
        return InstanceFactory.create(new FtvMpbApi_Factory_Impl(c0052FtvMpbApi_Factory));
    }
}
