package com.amazon.avod.mpb.media.playback.mediacodec;

import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.sample.AudioMetadata;
import com.amazon.avod.mpb.api.sample.VideoMetadata;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.AudioStreamType;
import com.amazon.avod.mpb.media.VideoStreamType;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumerator;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaFormatFactory {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String FMM_COMPATIBLE_KEY = "vendor.amazon.filmmaker-mode";
    public static final int FMM_COMPATIBLE_VALUE = 1;

    @NotNull
    public final MediaCodecDeviceCapabilityDetector capabilityDetector;

    @NotNull
    public final MediaCodecEnumerator codecEnumerator;

    @NotNull
    public final MPBInternalConfig config;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @VisibleForTesting
        public static /* synthetic */ void getFMM_COMPATIBLE_KEY$core_mpb_release$annotations() {
        }
    }

    @VisibleForTesting
    public MediaFormatFactory(@NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @NotNull MPBInternalConfig config, @NotNull MediaCodecEnumerator codecEnumerator) {
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(codecEnumerator, "codecEnumerator");
        this.capabilityDetector = capabilityDetector;
        this.config = config;
        this.codecEnumerator = codecEnumerator;
    }

    @VisibleForTesting
    @NotNull
    public final String getSupportedMimeType$core_mpb_release(@NotNull String fourCC, boolean z) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        List<String> mimeTypesByFourCC = this.config.getMimeTypesByFourCC(fourCC);
        if (mimeTypesByFourCC.isEmpty()) {
            throw new MediaPipelineBackendException("Unrecognized fourCC ".concat(fourCC), z ? MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_AUDIO_CODEC_TYPE : MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE);
        }
        Pair<String, MediaCodecInfo> supportedCodec = this.codecEnumerator.getSupportedCodec(mimeTypesByFourCC);
        if (supportedCodec != null) {
            return supportedCodec.first;
        }
        if (fourCC.equalsIgnoreCase(DefaultMPBInternalConfig.DDP_FOUR_CC) || (fourCC.equalsIgnoreCase(DefaultMPBInternalConfig.ATMO_FOUR_CC) && this.capabilityDetector.isDolbyDigitalPlusSupported())) {
            MpbLog.warnf("No supported on device codec found for fourCC=%s; support detected via passthrough using mimeType=%s.", fourCC, "audio/eac3");
            return "audio/eac3";
        }
        throw new MediaPipelineBackendException("Couldn't find supported codec for fourCC=" + fourCC + ", mimeTypes=" + mimeTypesByFourCC, z ? MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_AUDIO_CODEC_TYPE : MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE);
    }

    @NotNull
    public final MediaFormat newAudioFormat(@NotNull AudioMetadata audioMetadata) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(audioMetadata, "audioMetadata");
        AudioStreamType audioStreamType = audioMetadata.codecType.audioStreamType;
        if (audioStreamType != null) {
            MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(getSupportedMimeType$core_mpb_release(audioStreamType.fourCC, true), audioMetadata.sampleRate, audioMetadata.numChannels);
            Intrinsics.checkNotNullExpressionValue(mediaFormatCreateAudioFormat, "createAudioFormat(...)");
            return mediaFormatCreateAudioFormat;
        }
        throw new MediaPipelineBackendException("Unsupported audio codec " + audioMetadata.codecType, MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_AUDIO_CODEC_TYPE);
    }

    @NotNull
    public final MediaFormat newVideoFormat(@NotNull VideoMetadata videoMetadata) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(videoMetadata, "videoMetadata");
        VideoStreamType videoStreamType = videoMetadata.codecType.videoStreamType;
        if (videoStreamType == null) {
            throw new MediaPipelineBackendException("Unsupported video codec " + videoMetadata.codecType, MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE);
        }
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(getSupportedMimeType$core_mpb_release(videoStreamType.fourCC, false), videoMetadata.pixelWidth, videoMetadata.pixelHeight);
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateVideoFormat, "createVideoFormat(...)");
        mediaFormatCreateVideoFormat.setInteger("max-width", videoMetadata.pixelWidth);
        mediaFormatCreateVideoFormat.setInteger("max-height", videoMetadata.pixelHeight);
        setMaxInputSize(mediaFormatCreateVideoFormat);
        mediaFormatCreateVideoFormat.setInteger("push-blank-buffers-on-shutdown", 1);
        return mediaFormatCreateVideoFormat;
    }

    public final void setFilmMakerMode(@NotNull MediaFormat videoFormat) {
        Intrinsics.checkNotNullParameter(videoFormat, "videoFormat");
        MpbLog.logf("Filmmaker mode compatibility flag has been set to 1 (true) due to manifest metadata and server config gating.", new Object[0]);
        videoFormat.setInteger(FMM_COMPATIBLE_KEY, 1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0045, code lost:
    
        if (r2.equals("video/hevc") != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r2.equals("video/dolby-vision") != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0067, code lost:
    
        r1 = ((r0 * r1) * 3) / (r4.config.getMinCompressionRatioHevc() * 2);
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setMaxInputSize(@org.jetbrains.annotations.NotNull android.media.MediaFormat r5) {
        /*
            r4 = this;
            java.lang.String r0 = "videoFormat"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "max-width"
            int r0 = r5.getInteger(r0)
            java.lang.String r1 = "max-height"
            int r1 = r5.getInteger(r1)
            java.lang.String r2 = "mime"
            java.lang.String r2 = r5.getString(r2)
            if (r2 == 0) goto L7b
            int r3 = r2.hashCode()
            switch(r3) {
                case -1851077871: goto L5f;
                case -1662735862: goto L48;
                case -1662541442: goto L3f;
                case 1331836730: goto L21;
                default: goto L20;
            }
        L20:
            goto L7b
        L21:
            java.lang.String r3 = "video/avc"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L7b
            int r0 = r0 + 15
            int r0 = r0 / 16
            int r1 = r1 + 15
            int r1 = r1 / 16
            int r1 = r1 * r0
            int r1 = r1 * 768
            com.amazon.avod.mpb.config.MPBInternalConfig r0 = r4.config
            int r0 = r0.getMinCompressionRatioAvc()
            int r0 = r0 * 2
            int r1 = r1 / r0
            goto L75
        L3f:
            java.lang.String r3 = "video/hevc"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L7b
            goto L67
        L48:
            java.lang.String r3 = "video/av01"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L7b
            int r0 = r0 * r1
            int r0 = r0 * 15
            int r0 = r0 >> 3
            com.amazon.avod.mpb.config.MPBInternalConfig r1 = r4.config
            int r1 = r1.getMinCompressionRatioAv1()
            int r1 = r0 / r1
            goto L75
        L5f:
            java.lang.String r3 = "video/dolby-vision"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L7b
        L67:
            int r0 = r0 * r1
            int r0 = r0 * 3
            com.amazon.avod.mpb.config.MPBInternalConfig r1 = r4.config
            int r1 = r1.getMinCompressionRatioHevc()
            int r1 = r1 * 2
            int r1 = r0 / r1
        L75:
            java.lang.String r0 = "max-input-size"
            r5.setInteger(r0, r1)
            return
        L7b:
            java.lang.UnsupportedOperationException r5 = new java.lang.UnsupportedOperationException
            java.lang.String r0 = "setMaxInputSize mime type unexpected: "
            java.lang.String r0 = androidx.core.app.RemoteInput$$ExternalSyntheticOutline0.m(r0, r2)
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.mediacodec.MediaFormatFactory.setMaxInputSize(android.media.MediaFormat):void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaFormatFactory(@NotNull MediaCodecDeviceCapabilityDetector capabilityDetector) {
        this(capabilityDetector, DefaultMPBInternalConfig.INSTANCE, MediaCodecEnumeratorImpl.Companion.getInstance());
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
    }
}
