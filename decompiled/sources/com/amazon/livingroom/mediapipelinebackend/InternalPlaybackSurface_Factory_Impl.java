package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
public final class InternalPlaybackSurface_Factory_Impl implements InternalPlaybackSurface.Factory {
    public final C0055InternalPlaybackSurface_Factory delegateFactory;

    public InternalPlaybackSurface_Factory_Impl(C0055InternalPlaybackSurface_Factory c0055InternalPlaybackSurface_Factory) {
        this.delegateFactory = c0055InternalPlaybackSurface_Factory;
    }

    public static Provider<InternalPlaybackSurface.Factory> createFactoryProvider(C0055InternalPlaybackSurface_Factory c0055InternalPlaybackSurface_Factory) {
        return InstanceFactory.create(new InternalPlaybackSurface_Factory_Impl(c0055InternalPlaybackSurface_Factory));
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface.Factory
    public InternalPlaybackSurface create(String str) {
        return this.delegateFactory.get(str);
    }

    public static javax.inject.Provider<InternalPlaybackSurface.Factory> create(C0055InternalPlaybackSurface_Factory c0055InternalPlaybackSurface_Factory) {
        return InstanceFactory.create(new InternalPlaybackSurface_Factory_Impl(c0055InternalPlaybackSurface_Factory));
    }
}
