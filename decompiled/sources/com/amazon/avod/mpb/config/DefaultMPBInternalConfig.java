package com.amazon.avod.mpb.config;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultMPBInternalConfig implements MPBInternalConfig {

    @NotNull
    public static final String ATMO_FOUR_CC = "atmo";

    @NotNull
    public static final String DDP_FOUR_CC = "ec-3";

    @NotNull
    public static final String DD_FOUR_CC = "ac-3";

    @NotNull
    public static final String DOLBY_VISION_FOUR_CC = "dvhe";

    @NotNull
    public static final DefaultMPBInternalConfig INSTANCE = new DefaultMPBInternalConfig();

    @NotNull
    public static final String PRIMARY_HEVC_FOUR_CC = "hvc1";
    public static final boolean adjustedTimeFrameReleaseEnabled;
    public static final int asyncExecutorShutdownTimeoutMs;
    public static final int asyncTaskRetryIntervalMs;
    public static final int asyncWatchdogTickIntervalMs;
    public static final long audioTrackBufferMaxSizeUs;
    public static final long audioTrackBufferMinSizeUs;
    public static final int audioTrackBufferMultiplicationFactor;
    public static final boolean audioTrackRecoveryEnabled = false;
    public static final int audioTrackWriteFailureRetryDurationMs;
    public static final int avSyncRenderThresholdV21Us;
    public static final int avSyncThresholdUs;
    public static final boolean backgroundAudioPlaybackSupported = false;
    public static final int backoffTimeForRetriesOnAudioTrackCreationFailureMs;
    public static final boolean countOverlappedSampleToFrameDrop = false;
    public static final boolean dolbyOverBluetoothAudioOutputSupported = false;
    public static final boolean dummySurfaceEnabled = false;
    public static final boolean dumpFullMediaSampleInToString = false;
    public static final boolean dynamicPassthroughAudioTrackBufferSizeEnabled;
    public static final boolean enableAdaptiveRefreshSync;
    public static final boolean flushBeforeAudioTrackStop;
    public static final boolean handleAudioTrackDeadError;
    public static final boolean handleMidstreamSurfaceDestroy = false;
    public static final boolean hdmiAudioPassthroughEnabled;
    public static final int maxConcurrentSampleCount;
    public static final boolean mediaCodecRecoveryEnabled;
    public static final int mediaSourceMaxSize;
    public static final int mediaSourceReadyToPlayDurationMs;

    @NotNull
    public static final Map<String, List<String>> mimeTypeByFourCc;
    public static final int minCompressionRatioAv1;
    public static final int minCompressionRatioAvc;
    public static final int minCompressionRatioHevc;
    public static final boolean nonBlockingAudioRenderingEnabled;
    public static final int numTriesForAudioTrackCreationFailure;
    public static final boolean opticalAudioPassthroughEnabled;
    public static final long passThroughAudioTrackBufferSizeUs;
    public static final int passthroughAudioTrackBufferMultiplicationFactor;
    public static final boolean reportAvSyncStats;
    public static final boolean retryOnAudioTrackCreationFailure;
    public static final int tickIntervalMillis;
    public static final long tunnelModeAudioTrackBufferSizeUs;
    public static final float tunnelModeAudioTrackScaleFactor;
    public static final int tunnelModeDroppedFrameDetectionThresholdMs;
    public static final boolean tunnelModeNewAudioTrackWriteApiEnabled;
    public static final boolean tunnelModePipelineStatsEnabled;
    public static final boolean useBgThreadForSurfaceCallbacks;
    public static final boolean useProfileForCodecResolution;
    public static final boolean validateAudioTrackWriteResult;
    public static final boolean validateVideoDecodingCadence = false;
    public static final long videoDecodingCadenceLoggingThresholdMs;
    public static final long videoDecodingCadenceThresholdMs;
    public static final boolean videoSurfaceHotSwapSupported;
    public static final int videoSurfaceMaskTimeoutMs;

    static {
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf("audio/mp4a-latm");
        List listListOf2 = CollectionsKt__CollectionsJVMKt.listOf("video/avc");
        List listListOf3 = CollectionsKt__CollectionsJVMKt.listOf("video/hevc");
        mimeTypeByFourCc = MapsKt__MapsKt.mapOf(new Pair("aacl", listListOf), new Pair("aach", listListOf), new Pair("ach2", listListOf), new Pair(DD_FOUR_CC, CollectionsKt__CollectionsJVMKt.listOf("audio/ac3")), new Pair(DDP_FOUR_CC, CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"audio/eac3", "audio/ac3"})), new Pair(ATMO_FOUR_CC, CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"audio/eac3", "audio/ac3"})), new Pair("avc1", listListOf2), new Pair("h264", listListOf2), new Pair("hvc1", listListOf3), new Pair("hev1", listListOf3), new Pair("av01", CollectionsKt__CollectionsJVMKt.listOf("video/av01")), new Pair(DOLBY_VISION_FOUR_CC, CollectionsKt__CollectionsJVMKt.listOf("video/dolby-vision")));
        nonBlockingAudioRenderingEnabled = true;
        adjustedTimeFrameReleaseEnabled = true;
        maxConcurrentSampleCount = 10;
        tickIntervalMillis = 10;
        videoDecodingCadenceThresholdMs = 6000L;
        videoDecodingCadenceLoggingThresholdMs = 1000L;
        asyncWatchdogTickIntervalMs = 50;
        audioTrackBufferMultiplicationFactor = 4;
        passthroughAudioTrackBufferMultiplicationFactor = 2;
        passThroughAudioTrackBufferSizeUs = 250000L;
        tunnelModeAudioTrackBufferSizeUs = 250000L;
        audioTrackBufferMinSizeUs = 250000L;
        audioTrackBufferMaxSizeUs = 750000L;
        avSyncRenderThresholdV21Us = 50000;
        hdmiAudioPassthroughEnabled = true;
        opticalAudioPassthroughEnabled = true;
        reportAvSyncStats = true;
        avSyncThresholdUs = 100000;
        tunnelModeNewAudioTrackWriteApiEnabled = true;
        tunnelModeAudioTrackScaleFactor = 1.0f;
        flushBeforeAudioTrackStop = true;
        retryOnAudioTrackCreationFailure = true;
        backoffTimeForRetriesOnAudioTrackCreationFailureMs = 200;
        audioTrackWriteFailureRetryDurationMs = 200;
        numTriesForAudioTrackCreationFailure = 3;
        asyncTaskRetryIntervalMs = 10;
        asyncExecutorShutdownTimeoutMs = 10000;
        tunnelModePipelineStatsEnabled = true;
        tunnelModeDroppedFrameDetectionThresholdMs = 10000;
        videoSurfaceHotSwapSupported = true;
        minCompressionRatioAvc = 2;
        minCompressionRatioHevc = 4;
        minCompressionRatioAv1 = 4;
        mediaCodecRecoveryEnabled = true;
        useBgThreadForSurfaceCallbacks = true;
        validateAudioTrackWriteResult = true;
        videoSurfaceMaskTimeoutMs = 10000;
        dynamicPassthroughAudioTrackBufferSizeEnabled = true;
        useProfileForCodecResolution = true;
        enableAdaptiveRefreshSync = true;
        mediaSourceMaxSize = 2000;
        mediaSourceReadyToPlayDurationMs = 450;
        handleAudioTrackDeadError = true;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getAdjustedTimeFrameReleaseEnabled() {
        return adjustedTimeFrameReleaseEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAsyncExecutorShutdownTimeoutMs() {
        return asyncExecutorShutdownTimeoutMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAsyncTaskRetryIntervalMs() {
        return asyncTaskRetryIntervalMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAsyncWatchdogTickIntervalMs() {
        return asyncWatchdogTickIntervalMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public long getAudioTrackBufferMaxSizeUs() {
        return audioTrackBufferMaxSizeUs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public long getAudioTrackBufferMinSizeUs() {
        return audioTrackBufferMinSizeUs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAudioTrackBufferMultiplicationFactor() {
        return audioTrackBufferMultiplicationFactor;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getAudioTrackRecoveryEnabled() {
        return audioTrackRecoveryEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAudioTrackWriteFailureRetryDurationMs() {
        return audioTrackWriteFailureRetryDurationMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAvSyncRenderThresholdV21Us() {
        return avSyncRenderThresholdV21Us;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getAvSyncThresholdUs() {
        return avSyncThresholdUs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getBackgroundAudioPlaybackSupported() {
        return backgroundAudioPlaybackSupported;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getBackoffTimeForRetriesOnAudioTrackCreationFailureMs() {
        return backoffTimeForRetriesOnAudioTrackCreationFailureMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getCountOverlappedSampleToFrameDrop() {
        return countOverlappedSampleToFrameDrop;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    @NotNull
    public List<String> getDolbyDigitalPlusMimeTypes() {
        return getMimeTypesByFourCC(DDP_FOUR_CC);
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getDolbyOverBluetoothAudioOutputSupported() {
        return dolbyOverBluetoothAudioOutputSupported;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getDummySurfaceEnabled() {
        return dummySurfaceEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getDumpFullMediaSampleInToString() {
        return dumpFullMediaSampleInToString;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getDynamicPassthroughAudioTrackBufferSizeEnabled() {
        return dynamicPassthroughAudioTrackBufferSizeEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getEnableAdaptiveRefreshSync() {
        return enableAdaptiveRefreshSync;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getFlushBeforeAudioTrackStop() {
        return flushBeforeAudioTrackStop;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getHandleAudioTrackDeadError() {
        return handleAudioTrackDeadError;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getHandleMidstreamSurfaceDestroy() {
        return handleMidstreamSurfaceDestroy;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getHdmiAudioPassthroughEnabled() {
        return hdmiAudioPassthroughEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    @NotNull
    public List<String> getHevcMimeTypes() {
        return getMimeTypesByFourCC("hvc1");
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getMaxConcurrentSampleCount() {
        return maxConcurrentSampleCount;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getMediaCodecRecoveryEnabled() {
        return mediaCodecRecoveryEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getMediaSourceMaxSize() {
        return mediaSourceMaxSize;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getMediaSourceReadyToPlayDurationMs() {
        return mediaSourceReadyToPlayDurationMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    @NotNull
    public Map<String, List<String>> getMimeTypeByFourCc() {
        return mimeTypeByFourCc;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    @NotNull
    public List<String> getMimeTypesByFourCC(@NotNull String fourCC) {
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        Map<String, List<String>> map = mimeTypeByFourCc;
        String lowerCase = fourCC.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        List<String> list = map.get(lowerCase);
        return list == null ? EmptyList.INSTANCE : list;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getMinCompressionRatioAv1() {
        return minCompressionRatioAv1;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getMinCompressionRatioAvc() {
        return minCompressionRatioAvc;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getMinCompressionRatioHevc() {
        return minCompressionRatioHevc;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getNonBlockingAudioRenderingEnabled() {
        return nonBlockingAudioRenderingEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getNumTriesForAudioTrackCreationFailure() {
        return numTriesForAudioTrackCreationFailure;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getOpticalAudioPassthroughEnabled() {
        return opticalAudioPassthroughEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public long getPassThroughAudioTrackBufferSizeUs() {
        return passThroughAudioTrackBufferSizeUs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getPassthroughAudioTrackBufferMultiplicationFactor() {
        return passthroughAudioTrackBufferMultiplicationFactor;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getReportAvSyncStats() {
        return reportAvSyncStats;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getRetryOnAudioTrackCreationFailure() {
        return retryOnAudioTrackCreationFailure;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getTickIntervalMillis() {
        return tickIntervalMillis;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public long getTunnelModeAudioTrackBufferSizeUs() {
        return tunnelModeAudioTrackBufferSizeUs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public float getTunnelModeAudioTrackScaleFactor() {
        return tunnelModeAudioTrackScaleFactor;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getTunnelModeDroppedFrameDetectionThresholdMs() {
        return tunnelModeDroppedFrameDetectionThresholdMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getTunnelModeNewAudioTrackWriteApiEnabled() {
        return tunnelModeNewAudioTrackWriteApiEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getTunnelModePipelineStatsEnabled() {
        return tunnelModePipelineStatsEnabled;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getUseBgThreadForSurfaceCallbacks() {
        return useBgThreadForSurfaceCallbacks;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getUseProfileForCodecResolution() {
        return useProfileForCodecResolution;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getValidateAudioTrackWriteResult() {
        return validateAudioTrackWriteResult;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getValidateVideoDecodingCadence() {
        return validateVideoDecodingCadence;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public long getVideoDecodingCadenceLoggingThresholdMs() {
        return videoDecodingCadenceLoggingThresholdMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public long getVideoDecodingCadenceThresholdMs() {
        return videoDecodingCadenceThresholdMs;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public boolean getVideoSurfaceHotSwapSupported() {
        return videoSurfaceHotSwapSupported;
    }

    @Override // com.amazon.avod.mpb.config.MPBInternalConfig
    public int getVideoSurfaceMaskTimeoutMs() {
        return videoSurfaceMaskTimeoutMs;
    }
}
