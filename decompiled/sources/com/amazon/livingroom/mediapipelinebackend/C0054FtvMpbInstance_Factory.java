package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance_Factory, reason: case insensitive filesystem */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class C0054FtvMpbInstance_Factory {
    public final Provider<ApplicationVisibilityMonitor> applicationVisibilityMonitorProvider;
    public final Provider<DisplayModeManager> displayModeManagerProvider;
    public final Provider<ExternalPlaybackSurfaceRegistry> externalPlaybackSurfaceRegistryProvider;

    public C0054FtvMpbInstance_Factory(Provider<DisplayModeManager> provider, Provider<ExternalPlaybackSurfaceRegistry> provider2, Provider<ApplicationVisibilityMonitor> provider3) {
        this.displayModeManagerProvider = provider;
        this.externalPlaybackSurfaceRegistryProvider = provider2;
        this.applicationVisibilityMonitorProvider = provider3;
    }

    public static C0054FtvMpbInstance_Factory create(Provider<DisplayModeManager> provider, Provider<ExternalPlaybackSurfaceRegistry> provider2, Provider<ApplicationVisibilityMonitor> provider3) {
        return new C0054FtvMpbInstance_Factory(provider, provider2, provider3);
    }

    public static FtvMpbInstance newInstance(FtvMpbApi ftvMpbApi, MediaCodecRenderer mediaCodecRenderer, MediaPipelineListener mediaPipelineListener, PlaybackSurface playbackSurface, DisplayModeManager displayModeManager, ExternalPlaybackSurfaceRegistry externalPlaybackSurfaceRegistry, ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        return new FtvMpbInstance(ftvMpbApi, mediaCodecRenderer, mediaPipelineListener, playbackSurface, displayModeManager, externalPlaybackSurfaceRegistry, applicationVisibilityMonitor);
    }

    public FtvMpbInstance get(FtvMpbApi ftvMpbApi, MediaCodecRenderer mediaCodecRenderer, MediaPipelineListener mediaPipelineListener, PlaybackSurface playbackSurface) {
        return new FtvMpbInstance(ftvMpbApi, mediaCodecRenderer, mediaPipelineListener, playbackSurface, this.displayModeManagerProvider.get(), this.externalPlaybackSurfaceRegistryProvider.get(), this.applicationVisibilityMonitorProvider.get());
    }
}
