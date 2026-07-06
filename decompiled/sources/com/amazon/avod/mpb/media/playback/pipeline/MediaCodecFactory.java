package com.amazon.avod.mpb.media.playback.pipeline;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumerator;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl;
import com.google.common.collect.Sets;
import java.util.Set;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaCodecFactory {

    @NotNull
    public static final String SOFTWARE_DECODER_PREFIX = "OMX.google.";

    @NotNull
    public final MediaCodecEnumerator codecEnumerator = MediaCodecEnumeratorImpl.Companion.getInstance();
    public final boolean isMediaCodecRecoveryEnabled;
    public final boolean shouldUseProfileForCodecResolution;

    @NotNull
    public final Set<MediaCodec> trackedCodecs;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final MediaCodecFactory instance = new MediaCodecFactory();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final MediaCodecFactory getInstance() {
            return MediaCodecFactory.instance;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }
    }

    public MediaCodecFactory() {
        DefaultMPBInternalConfig defaultMPBInternalConfig = DefaultMPBInternalConfig.INSTANCE;
        defaultMPBInternalConfig.getClass();
        this.isMediaCodecRecoveryEnabled = DefaultMPBInternalConfig.mediaCodecRecoveryEnabled;
        defaultMPBInternalConfig.getClass();
        this.shouldUseProfileForCodecResolution = DefaultMPBInternalConfig.useProfileForCodecResolution;
        Set<MediaCodec> setNewConcurrentHashSet = Sets.newConcurrentHashSet();
        Intrinsics.checkNotNullExpressionValue(setNewConcurrentHashSet, "newConcurrentHashSet(...)");
        this.trackedCodecs = setNewConcurrentHashSet;
    }

    @NotNull
    public static final MediaCodecFactory getInstance() {
        Companion.getClass();
        return instance;
    }

    @NotNull
    public final MediaCodec createCodec(@NotNull MediaFormat format, @Nullable MediaCrypto mediaCrypto, @Nullable Integer num, boolean z) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(format, "format");
        String string = format.getString("mime");
        boolean z2 = (mediaCrypto == null || string == null || !mediaCrypto.requiresSecureDecoderComponent(string)) ? false : true;
        MediaCodecInfo decoderInfoForMimeAndProfile = string != null ? this.shouldUseProfileForCodecResolution && num != null ? this.codecEnumerator.getDecoderInfoForMimeAndProfile(string, num.intValue()) : this.codecEnumerator.getSupportedCodec(string) : null;
        if (decoderInfoForMimeAndProfile == null) {
            throw new MediaPipelineBackendException("MediaCodecFactory couldn't find any codec for mimeType=" + string + ", requiresSecureDecoder=" + z2, z ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECODER_NOT_FOUND : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECODER_NOT_FOUND);
        }
        String name = decoderInfoForMimeAndProfile.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        if (z2) {
            name = name.concat(".secure");
        }
        try {
            MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(name);
            Intrinsics.checkNotNull(mediaCodecCreateByCodecName);
            String name2 = mediaCodecCreateByCodecName.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
            MpbLog.logf("MediaCodecFactory instantiated codec " + mediaCodecCreateByCodecName + " with name=" + name + " for mimeType=" + string + ", requiresSecureDecoder=" + z2 + ", isHardwareAccelerated=" + (true ^ StringsKt__StringsJVMKt.startsWith$default(name2, SOFTWARE_DECODER_PREFIX, false, 2, null)), new Object[0]);
            if (this.isMediaCodecRecoveryEnabled) {
                this.trackedCodecs.add(mediaCodecCreateByCodecName);
            }
            return mediaCodecCreateByCodecName;
        } catch (Exception e) {
            resetAllCodecs(e);
            String str = "MediaCodecFactory failed to instantiate codec " + name + " for mimeType=" + string + ", requiresSecureDecoder=" + z2 + ", error: " + e;
            MpbLog.exceptionf(e, str, new Object[0]);
            throw new MediaPipelineBackendException(str, z ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECODER_INITIALIZE_ERROR : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECODER_INITIALIZE_ERROR, e);
        }
    }

    public final void releaseCodec(@NotNull MediaCodec codec) {
        Intrinsics.checkNotNullParameter(codec, "codec");
        codec.release();
        MpbLog.logf("MediaCodecFactory released codec " + codec, new Object[0]);
        if (this.isMediaCodecRecoveryEnabled) {
            this.trackedCodecs.remove(codec);
        }
    }

    public final void resetAllCodecs(@NotNull Exception trigger) {
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        if (this.isMediaCodecRecoveryEnabled) {
            MpbLog.logf("MediaCodecFactory resetAllCodecs called with trigger exception " + trigger, new Object[0]);
            for (MediaCodec mediaCodec : this.trackedCodecs) {
                try {
                    mediaCodec.reset();
                    MpbLog.logf("MediaCodecFactory reset codec " + mediaCodec, new Object[0]);
                } catch (Exception e) {
                    MpbLog.warnf(e, "MediaCodecFactory " + mediaCodec + " threw exception during reset " + e, new Object[0]);
                }
            }
            this.trackedCodecs.clear();
        }
    }
}
