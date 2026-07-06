package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.avod.mpb.media.drm.MediaDrmProvisioner;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext_Factory, reason: case insensitive filesystem */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class C0053FtvMpbDrmContext_Factory {
    public final Provider<ApplicationVisibilityMonitor> applicationVisibilityMonitorProvider;

    public C0053FtvMpbDrmContext_Factory(Provider<ApplicationVisibilityMonitor> provider) {
        this.applicationVisibilityMonitorProvider = provider;
    }

    public static C0053FtvMpbDrmContext_Factory create(Provider<ApplicationVisibilityMonitor> provider) {
        return new C0053FtvMpbDrmContext_Factory(provider);
    }

    public static FtvMpbDrmContext newInstance(MediaDrmProvisioner mediaDrmProvisioner, DrmKeyStatusNotifier drmKeyStatusNotifier, FailoverManager failoverManager, ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        return new FtvMpbDrmContext(mediaDrmProvisioner, drmKeyStatusNotifier, failoverManager, applicationVisibilityMonitor);
    }

    public FtvMpbDrmContext get(MediaDrmProvisioner mediaDrmProvisioner, DrmKeyStatusNotifier drmKeyStatusNotifier, FailoverManager failoverManager) {
        return new FtvMpbDrmContext(mediaDrmProvisioner, drmKeyStatusNotifier, failoverManager, this.applicationVisibilityMonitorProvider.get());
    }
}
