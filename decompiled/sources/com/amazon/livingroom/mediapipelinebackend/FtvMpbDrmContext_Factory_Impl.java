package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.avod.mpb.media.drm.MediaDrmProvisioner;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
public final class FtvMpbDrmContext_Factory_Impl implements FtvMpbDrmContext.Factory {
    public final C0053FtvMpbDrmContext_Factory delegateFactory;

    public FtvMpbDrmContext_Factory_Impl(C0053FtvMpbDrmContext_Factory c0053FtvMpbDrmContext_Factory) {
        this.delegateFactory = c0053FtvMpbDrmContext_Factory;
    }

    public static Provider<FtvMpbDrmContext.Factory> createFactoryProvider(C0053FtvMpbDrmContext_Factory c0053FtvMpbDrmContext_Factory) {
        return InstanceFactory.create(new FtvMpbDrmContext_Factory_Impl(c0053FtvMpbDrmContext_Factory));
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext.Factory
    public FtvMpbDrmContext create(MediaDrmProvisioner mediaDrmProvisioner, DrmKeyStatusNotifier drmKeyStatusNotifier, FailoverManager failoverManager) {
        return this.delegateFactory.get(mediaDrmProvisioner, drmKeyStatusNotifier, failoverManager);
    }

    public static javax.inject.Provider<FtvMpbDrmContext.Factory> create(C0053FtvMpbDrmContext_Factory c0053FtvMpbDrmContext_Factory) {
        return InstanceFactory.create(new FtvMpbDrmContext_Factory_Impl(c0053FtvMpbDrmContext_Factory));
    }
}
