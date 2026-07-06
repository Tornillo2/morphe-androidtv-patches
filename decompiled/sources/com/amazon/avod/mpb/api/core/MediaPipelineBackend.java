package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.callback.DisplayModeManager;
import com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks;
import com.amazon.avod.mpb.api.callback.SurfaceResizerCallback;
import com.amazon.avod.mpb.api.core.PropertyDefinition;
import com.amazon.avod.mpb.api.sample.AudioMetadata;
import com.amazon.avod.mpb.api.sample.AudioSample;
import com.amazon.avod.mpb.api.sample.DiagnosticInfo;
import com.amazon.avod.mpb.api.sample.VideoMetadata;
import com.amazon.avod.mpb.api.sample.VideoSample;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystem;
import com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine;
import java.util.Set;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaPipelineBackend {

    @JvmField
    @NotNull
    public static final PropertyDefinition<Integer> AUDIO_BITRATE;

    @JvmField
    @NotNull
    public static final PropertyDefinition<Boolean> AUDIO_MUTE;

    @JvmField
    @NotNull
    public static final PropertyDefinition<Double> AUDIO_VOLUME;

    @JvmField
    @NotNull
    public static final PropertyDefinition<Boolean> DISABLE_TUNNELING_MODE;

    @JvmField
    @NotNull
    public static final PropertyDefinition<Float> DISPLAY_ASPECT_RATIO;

    @JvmField
    @NotNull
    public static final PropertyDefinition<Boolean> DISPLAY_MODE_CHANGING;

    @JvmField
    @NotNull
    public static final PropertyDefinition<Boolean> MATCH_REFRESH_RATE;

    @NotNull
    public static final Properties Properties = Properties.$$INSTANCE;

    @JvmField
    @NotNull
    public static final PropertyDefinition<String> ZOOM_LEVEL;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InitConfig {

        @NotNull
        public final Set<TrackConfiguration> trackConfigs;

        /* JADX WARN: Multi-variable type inference failed */
        public InitConfig(@NotNull Set<? extends TrackConfiguration> trackConfigs) {
            Intrinsics.checkNotNullParameter(trackConfigs, "trackConfigs");
            this.trackConfigs = trackConfigs;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ InitConfig copy$default(InitConfig initConfig, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                set = initConfig.trackConfigs;
            }
            return initConfig.copy(set);
        }

        @NotNull
        public final Set<TrackConfiguration> component1() {
            return this.trackConfigs;
        }

        @NotNull
        public final InitConfig copy(@NotNull Set<? extends TrackConfiguration> trackConfigs) {
            Intrinsics.checkNotNullParameter(trackConfigs, "trackConfigs");
            return new InitConfig(trackConfigs);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof InitConfig) && Intrinsics.areEqual(this.trackConfigs, ((InitConfig) obj).trackConfigs);
        }

        @NotNull
        public final Set<TrackConfiguration> getTrackConfigs() {
            return this.trackConfigs;
        }

        public int hashCode() {
            return this.trackConfigs.hashCode();
        }

        @NotNull
        public String toString() {
            return "InitConfig(trackConfigs=" + this.trackConfigs + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Properties {
        public static final /* synthetic */ Properties $$INSTANCE = new Properties();
    }

    static {
        PropertyDefinition.Companion companion = PropertyDefinition.Companion;
        companion.getClass();
        AUDIO_VOLUME = new PropertyDefinition<>(MediaPipelineBackendEngine.MPBPropertyConstants.VOLUME_KEY, PropertyDefinition.doubleSerializer, false, false, 12, null);
        companion.getClass();
        PropertyDefinition.Serializer<Boolean> serializer = PropertyDefinition.booleanSerializer;
        AUDIO_MUTE = new PropertyDefinition<>("audioMute", serializer, false, false, 12, null);
        companion.getClass();
        AUDIO_BITRATE = new PropertyDefinition<>("audioBitrate", PropertyDefinition.intSerializer, false, false, 12, null);
        companion.getClass();
        MATCH_REFRESH_RATE = new PropertyDefinition<>("matchRefreshRate", serializer, false, false, 12, null);
        companion.getClass();
        DISPLAY_MODE_CHANGING = new PropertyDefinition<>("displayModeChanging", serializer, false, true);
        companion.getClass();
        DISPLAY_ASPECT_RATIO = new PropertyDefinition<>(MediaCodecRenderer.DISPLAY_ASPECT_RATIO_KEY, PropertyDefinition.floatSerializer, false, false, 12, null);
        companion.getClass();
        DISABLE_TUNNELING_MODE = new PropertyDefinition<>("disableTunnelingMode", serializer, false, false, 12, null);
        companion.getClass();
        ZOOM_LEVEL = new PropertyDefinition<>("zoomLevel", PropertyDefinition.stringSerializer, true, true);
    }

    void audioOnMetadata(@NotNull AudioMetadata audioMetadata) throws MediaPipelineBackendException;

    int audioOnSample(@NotNull AudioSample audioSample) throws MediaPipelineBackendException;

    void audioOnStreamFinish() throws MediaPipelineBackendException;

    @NotNull
    DiagnosticInfo getDiagnosticInfo();

    long getPlaybackTimeUs();

    @Nullable
    String getProperty(@NotNull String str) throws MediaPipelineBackendException;

    void init(@NotNull InitConfig initConfig, @NotNull SurfaceResizerCallback surfaceResizerCallback, @NotNull DisplayModeManager displayModeManager) throws MediaPipelineBackendException;

    void pause() throws MediaPipelineBackendException;

    void play() throws MediaPipelineBackendException;

    void registerCallbacks(@NotNull MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks) throws MediaPipelineBackendException;

    void seek(long j) throws MediaPipelineBackendException;

    void setDrmSystem(@NotNull AndroidDrmSystem androidDrmSystem) throws MediaPipelineBackendException;

    void setProperty(@NotNull String str, @Nullable String str2) throws MediaPipelineBackendException;

    void setVideoOutputPosition(int i, int i2, int i3, int i4, int i5) throws MediaPipelineBackendException;

    void shutdown() throws MediaPipelineBackendException;

    void stop() throws MediaPipelineBackendException;

    void videoOnMetadata(@NotNull VideoMetadata videoMetadata) throws MediaPipelineBackendException;

    int videoOnSample(@NotNull VideoSample videoSample) throws MediaPipelineBackendException;

    void videoOnStreamFinish() throws MediaPipelineBackendException;
}
