package com.amazon.avod.mpb.config;

import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MPBInternalConfig {
    boolean getAdjustedTimeFrameReleaseEnabled();

    int getAsyncExecutorShutdownTimeoutMs();

    int getAsyncTaskRetryIntervalMs();

    int getAsyncWatchdogTickIntervalMs();

    long getAudioTrackBufferMaxSizeUs();

    long getAudioTrackBufferMinSizeUs();

    int getAudioTrackBufferMultiplicationFactor();

    boolean getAudioTrackRecoveryEnabled();

    int getAudioTrackWriteFailureRetryDurationMs();

    int getAvSyncRenderThresholdV21Us();

    int getAvSyncThresholdUs();

    boolean getBackgroundAudioPlaybackSupported();

    int getBackoffTimeForRetriesOnAudioTrackCreationFailureMs();

    boolean getCountOverlappedSampleToFrameDrop();

    @NotNull
    List<String> getDolbyDigitalPlusMimeTypes();

    boolean getDolbyOverBluetoothAudioOutputSupported();

    boolean getDummySurfaceEnabled();

    boolean getDumpFullMediaSampleInToString();

    boolean getDynamicPassthroughAudioTrackBufferSizeEnabled();

    boolean getEnableAdaptiveRefreshSync();

    boolean getFlushBeforeAudioTrackStop();

    boolean getHandleAudioTrackDeadError();

    boolean getHandleMidstreamSurfaceDestroy();

    boolean getHdmiAudioPassthroughEnabled();

    @NotNull
    List<String> getHevcMimeTypes();

    int getMaxConcurrentSampleCount();

    boolean getMediaCodecRecoveryEnabled();

    int getMediaSourceMaxSize();

    int getMediaSourceReadyToPlayDurationMs();

    @NotNull
    Map<String, List<String>> getMimeTypeByFourCc();

    @NotNull
    List<String> getMimeTypesByFourCC(@NotNull String str);

    int getMinCompressionRatioAv1();

    int getMinCompressionRatioAvc();

    int getMinCompressionRatioHevc();

    boolean getNonBlockingAudioRenderingEnabled();

    int getNumTriesForAudioTrackCreationFailure();

    boolean getOpticalAudioPassthroughEnabled();

    long getPassThroughAudioTrackBufferSizeUs();

    int getPassthroughAudioTrackBufferMultiplicationFactor();

    boolean getReportAvSyncStats();

    boolean getRetryOnAudioTrackCreationFailure();

    int getTickIntervalMillis();

    long getTunnelModeAudioTrackBufferSizeUs();

    float getTunnelModeAudioTrackScaleFactor();

    int getTunnelModeDroppedFrameDetectionThresholdMs();

    boolean getTunnelModeNewAudioTrackWriteApiEnabled();

    boolean getTunnelModePipelineStatsEnabled();

    boolean getUseBgThreadForSurfaceCallbacks();

    boolean getUseProfileForCodecResolution();

    boolean getValidateAudioTrackWriteResult();

    boolean getValidateVideoDecodingCadence();

    long getVideoDecodingCadenceLoggingThresholdMs();

    long getVideoDecodingCadenceThresholdMs();

    boolean getVideoSurfaceHotSwapSupported();

    int getVideoSurfaceMaskTimeoutMs();
}
