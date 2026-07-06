package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
public final class FtvMpbInstance_Factory_Impl implements FtvMpbInstance.Factory {
    public final C0054FtvMpbInstance_Factory delegateFactory;

    public FtvMpbInstance_Factory_Impl(C0054FtvMpbInstance_Factory c0054FtvMpbInstance_Factory) {
        this.delegateFactory = c0054FtvMpbInstance_Factory;
    }

    public static Provider<FtvMpbInstance.Factory> createFactoryProvider(C0054FtvMpbInstance_Factory c0054FtvMpbInstance_Factory) {
        return InstanceFactory.create(new FtvMpbInstance_Factory_Impl(c0054FtvMpbInstance_Factory));
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance.Factory
    public FtvMpbInstance create(FtvMpbApi ftvMpbApi, MediaCodecRenderer mediaCodecRenderer, MediaPipelineListener mediaPipelineListener, PlaybackSurface playbackSurface) {
        return this.delegateFactory.get(ftvMpbApi, mediaCodecRenderer, mediaPipelineListener, playbackSurface);
    }

    public static javax.inject.Provider<FtvMpbInstance.Factory> create(C0054FtvMpbInstance_Factory c0054FtvMpbInstance_Factory) {
        return InstanceFactory.create(new FtvMpbInstance_Factory_Impl(c0054FtvMpbInstance_Factory));
    }
}
