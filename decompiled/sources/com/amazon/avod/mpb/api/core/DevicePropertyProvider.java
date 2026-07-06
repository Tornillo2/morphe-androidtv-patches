package com.amazon.avod.mpb.api.core;

import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import org.apache.commons.lang3.time.DateUtils;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DevicePropertyProvider {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static int getCanaryThreshold(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 1;
        }

        public static long getCanaryWindowLengthMs(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return DateUtils.MILLIS_PER_HOUR;
        }

        @NotNull
        public static Set<String> getFailoverHandledErrorSet(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return ArraysKt___ArraysKt.toSet(new String[]{"AV_MPB_AUDIO_DECODER_ERROR", "AV_MPB_AUDIO_DECODER_INITIALIZE_ERROR", "AV_MPB_AUDIO_DECODER_NOT_FOUND", "AV_MPB_AUDIO_PIPELINE_INIT_FAILED", "AV_MPB_AUDIO_RENDERER_ERROR_TRACK_INIT_FAILED", "AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED", "AV_MPB_AUDIO_DECRYPTOR_ERROR", "AV_MPB_AUDIO_DECRYPTOR_ERROR_MISSING_KEY", "AV_MPB_AUDIO_DECRYPTOR_ERROR_EXPIRED_KEY", "AV_MPB_AUDIO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION", "AV_MPB_VIDEO_DECODER_ERROR", "AV_MPB_VIDEO_DECODER_INITIALIZE_ERROR", "AV_MPB_VIDEO_DECODER_NOT_FOUND", "AV_MPB_VIDEO_PIPELINE_INIT_FAILED", "AV_MPB_VIDEO_DECRYPTOR_ERROR", "AV_MPB_VIDEO_DECRYPTOR_ERROR_MISSING_KEY", "AV_MPB_VIDEO_DECRYPTOR_ERROR_EXPIRED_KEY", "AV_MPB_VIDEO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION", "AV_MPB_DECRYPTION_IV_SIZE_ERROR", "AV_MPB_DECRYPTION_MALFORMED_ENCRYPTION_INFO", "AV_MPB_INSUFFICIENT_OUTPUT_PROTECTION", "AV_MPB_SAMPLE_ADAPTION_FAILURE", "AV_MPB_SAMPLE_ADAPTION_FAILURE_DECODER_BUFFER_SIZE_TOO_SMALL", "AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_TOO_SMALL", "AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_ZERO", "AV_MPB_SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SAMPLE_BUFFER_SIZE", "AV_MPB_INTERNAL_ERROR", "DRM_CREATE_SYSTEM_FAILED_TO_CREATE_MEDIA_DRM", "DRM_FAILED_TO_CREATE_MEDIA_CRYPTO", "DRM_FAILED_TO_SET_MEDIA_CRYPTO_SESSION", "DRM_OPEN_SESSION_UNKNOWN_FAILURE", "DRM_GENERATE_KEY_REQUEST_UNKNOWN_FAILURE", "DRM_GENERATE_KEY_REQUEST_NOT_PROVISIONED", "DRM_PROCESS_KEY_RESPONSE_UNKNOWN_FAILURE", "DRM_PROCESS_KEY_RESPONSE_NOT_PROVISIONED", "DRM_PROCESS_RESPONSE_KEY_REQUEST_DENIED"});
        }

        public static int getFailoverThreshold(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 3;
        }

        public static long getFailoverWindowLengthMs(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 1800000L;
        }

        public static float getFrameDropDetectionPercentage(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 10.0f;
        }

        public static float getHfrBurstFrameDropPercentage(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 30.0f;
        }

        public static long getHfrBurstWindowLengthMs(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 30000L;
        }

        public static int getHfrBurstWindowThreshold(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 3;
        }

        public static float getHfrContinualFrameDropPercentage(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 5.0f;
        }

        public static long getHfrContinualWindowLengthMs(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 30000L;
        }

        public static int getHfrContinualWindowThreshold(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 10;
        }

        public static long getHfrFallbackBackoffTimeMs(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 86400000L;
        }

        public static int getHfrMaxFallbacksBeforeBlock(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 3;
        }

        public static float getHfrMinFrameRate(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 48.0f;
        }

        public static long getPerfEvalWindowLengthMs(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 30000L;
        }

        public static int getPerfEvalWindowThreshold(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return 40;
        }

        public static boolean isCanaryEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return true;
        }

        public static boolean isCanaryErrorWildcardEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return true;
        }

        public static boolean isDeferredSurfacePlaybackEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return false;
        }

        public static boolean isFailoverErrorWildcardEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return false;
        }

        public static boolean isFrameDropEvaluatorEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return true;
        }

        public static boolean isHfrEvaluatorEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return true;
        }

        public static boolean isPlayerStackFailoverEnabled(@NotNull DevicePropertyProvider devicePropertyProvider) {
            return true;
        }
    }

    boolean decoderBecomingInactiveWorkAround();

    int getCanaryThreshold();

    long getCanaryWindowLengthMs();

    @NotNull
    Set<String> getFailoverHandledErrorSet();

    int getFailoverThreshold();

    long getFailoverWindowLengthMs();

    float getFrameDropDetectionPercentage();

    float getHfrBurstFrameDropPercentage();

    long getHfrBurstWindowLengthMs();

    int getHfrBurstWindowThreshold();

    float getHfrContinualFrameDropPercentage();

    long getHfrContinualWindowLengthMs();

    int getHfrContinualWindowThreshold();

    long getHfrFallbackBackoffTimeMs();

    int getHfrMaxFallbacksBeforeBlock();

    float getHfrMinFrameRate();

    int getMaxVideoHeight();

    int getMaxVideoWidth();

    long getPerfEvalWindowLengthMs();

    int getPerfEvalWindowThreshold();

    boolean isApplicationInBackground();

    boolean isAsyncModeEnabled();

    boolean isAv1Enabled();

    boolean isCanaryEnabled();

    boolean isCanaryErrorWildcardEnabled();

    boolean isDeferredSurfacePlaybackEnabled();

    boolean isDolbyHdmiPassthroughAvailable();

    boolean isDolbyVisionHDREnabled();

    boolean isFailoverErrorWildcardEnabled();

    boolean isFrameDropEvaluatorEnabled();

    boolean isHandleMidstreamSurfaceDestroyEnabled();

    boolean isHdr10Enabled();

    boolean isHevcEnabled();

    boolean isHfrEvaluatorEnabled();

    boolean isIntraChunkSeekingSupported();

    boolean isNewGetBufferApiEnabled();

    boolean isOpticalOutputEnabled();

    boolean isPlayerStackFailoverEnabled();

    boolean isTunnelModeEnabled();

    boolean isTunnelModeOverBtEnabled();

    boolean isVerboseAvSyncLoggingEnabled();

    boolean supportsSurroundSound();
}
