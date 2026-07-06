package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
public final class MediaPipelineBackendEngine_Factory_Impl implements MediaPipelineBackendEngine.Factory {
    public final C0056MediaPipelineBackendEngine_Factory delegateFactory;

    public MediaPipelineBackendEngine_Factory_Impl(C0056MediaPipelineBackendEngine_Factory c0056MediaPipelineBackendEngine_Factory) {
        this.delegateFactory = c0056MediaPipelineBackendEngine_Factory;
    }

    public static Provider<MediaPipelineBackendEngine.Factory> createFactoryProvider(C0056MediaPipelineBackendEngine_Factory c0056MediaPipelineBackendEngine_Factory) {
        return InstanceFactory.create(new MediaPipelineBackendEngine_Factory_Impl(c0056MediaPipelineBackendEngine_Factory));
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine.Factory
    public MediaPipelineBackendEngine create(Handler handler, MediaPipelineListener mediaPipelineListener, MediaPipelineBackendEngine.DestroyInterceptor destroyInterceptor, PlaybackSurface playbackSurface, int i) {
        return this.delegateFactory.get(handler, mediaPipelineListener, destroyInterceptor, playbackSurface, i);
    }

    public static javax.inject.Provider<MediaPipelineBackendEngine.Factory> create(C0056MediaPipelineBackendEngine_Factory c0056MediaPipelineBackendEngine_Factory) {
        return InstanceFactory.create(new MediaPipelineBackendEngine_Factory_Impl(c0056MediaPipelineBackendEngine_Factory));
    }
}
