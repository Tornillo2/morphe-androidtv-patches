package com.amazon.livingroom.mediapipelinebackend;

import android.os.Build;
import androidx.annotation.OptIn;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
@SourceDebugExtension({"SMAP\nCodecQuerier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CodecQuerier.kt\ncom/amazon/livingroom/mediapipelinebackend/CodecQuerier\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,190:1\n295#2,2:191\n*S KotlinDebug\n*F\n+ 1 CodecQuerier.kt\ncom/amazon/livingroom/mediapipelinebackend/CodecQuerier\n*L\n172#1:191,2\n*E\n"})
public final class CodecQuerier {

    @NotNull
    public final DeviceProperties deviceProperties;

    @Inject
    public CodecQuerier(@NotNull DeviceProperties deviceProperties) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        this.deviceProperties = deviceProperties;
    }

    @NotNull
    public final ResultHolder<Integer> canDecodeAudio(@NotNull String codecName, int i, @NotNull String channelFormat, int i2) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        Intrinsics.checkNotNullParameter(channelFormat, "channelFormat");
        String mediaMimeType = MimeTypes.getMediaMimeType(codecName);
        if (mediaMimeType == null) {
            MpbLog.w("Mime Type unknown for codec=" + codecName + ExternalFourCCMapper.CODEC_NAME_SPLITTER);
            return ResultHolder.fromResult(0);
        }
        if (mediaMimeType.equals("audio/eac3")) {
            Object obj = this.deviceProperties.get(DeviceProperties.SUPPORTS_SURROUND_SOUND);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ResultHolder.fromResult(Integer.valueOf(((Boolean) obj).booleanValue() ? 2 : 0));
        }
        int channelCount = CodecQuerierKt.getChannelCount(channelFormat);
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType(mediaMimeType);
        builder.codecs = codecName;
        builder.channelCount = channelCount;
        builder.sampleRate = i2;
        builder.averageBitrate = i;
        return checkSupport(mediaMimeType, false, false, new Format(builder));
    }

    @NotNull
    public final ResultHolder<Integer> canDecodeVideo(@NotNull String codecName, int i, int i2, int i3, float f, @NotNull String hdrMetadataType, @NotNull String colorGamut, @NotNull String colorTransfer) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        Intrinsics.checkNotNullParameter(hdrMetadataType, "hdrMetadataType");
        Intrinsics.checkNotNullParameter(colorGamut, "colorGamut");
        Intrinsics.checkNotNullParameter(colorTransfer, "colorTransfer");
        ColorInfo.Builder builder = new ColorInfo.Builder();
        if (Build.VERSION.SDK_INT >= 24) {
            Integer num = CodecQuerierKt.getColorGamutMap().get(colorGamut);
            if (num != null) {
                builder.colorSpace = num.intValue();
            }
            Integer num2 = CodecQuerierKt.transferFunctionMap.get(colorTransfer);
            if (num2 != null) {
                builder.colorTransfer = num2.intValue();
            }
        } else if (!colorGamut.equals(MediaCodecEnumeratorImpl.COLOR_GAMUT_REC_2020) || !colorTransfer.equals(MediaCodecEnumeratorImpl.TRANSFER_FUNCTION_PQ)) {
            return ResultHolder.fromResult(0);
        }
        ColorInfo colorInfoBuild = builder.build();
        String mediaMimeType = MimeTypes.getMediaMimeType(codecName);
        if (mediaMimeType == null) {
            MpbLog.w("Mime Type unknown for codec=" + codecName + ExternalFourCCMapper.CODEC_NAME_SPLITTER);
            return ResultHolder.fromResult(0);
        }
        Format.Builder builder2 = new Format.Builder();
        builder2.sampleMimeType = MimeTypes.normalizeMimeType(mediaMimeType);
        builder2.codecs = codecName;
        builder2.width = i2;
        builder2.height = i3;
        builder2.frameRate = f;
        builder2.colorInfo = colorInfoBuild;
        builder2.peakBitrate = i;
        Format format = new Format(builder2);
        Object obj = this.deviceProperties.get(DeviceProperties.TUNNELED_VIDEO_PLAYBACK_ENABLED);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        String str = format.sampleMimeType;
        if (str != null) {
            int iHashCode = str.hashCode();
            if (iHashCode != -1851077871) {
                if (iHashCode != -1662735862) {
                    if (iHashCode == -1662541442 && str.equals("video/hevc")) {
                        if (!((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_HEVC)).booleanValue()) {
                            return ResultHolder.fromResult(0);
                        }
                        if (hdrMetadataType.equals(HdrMetadataType.HDR10.value) && !((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_HDR10)).booleanValue()) {
                            return ResultHolder.fromResult(0);
                        }
                        if (hdrMetadataType.equals(HdrMetadataType.HDR10_PLUS.value) && !((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_HDR10_PLUS)).booleanValue()) {
                            return ResultHolder.fromResult(0);
                        }
                    }
                } else if (str.equals("video/av01")) {
                    if (!((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_AV1)).booleanValue()) {
                        return ResultHolder.fromResult(0);
                    }
                    if (hdrMetadataType.equals(HdrMetadataType.HDR10.value) && !((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_HDR10)).booleanValue()) {
                        return ResultHolder.fromResult(0);
                    }
                    if (hdrMetadataType.equals(HdrMetadataType.HDR10_PLUS.value) && !((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_HDR10_PLUS)).booleanValue()) {
                        return ResultHolder.fromResult(0);
                    }
                }
            } else if (str.equals("video/dolby-vision") && !((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_DOLBY_VISION)).booleanValue()) {
                return ResultHolder.fromResult(0);
            }
        }
        return checkSupport(mediaMimeType, true, zBooleanValue, format);
    }

    public final ResultHolder<Integer> checkSupport(String str, boolean z, boolean z2, Format format) {
        Object next;
        try {
            List<MediaCodecInfo> decoderInfos = MediaCodecUtil.getDecoderInfos(str, z, z2);
            Intrinsics.checkNotNullExpressionValue(decoderInfos, "getDecoderInfos(...)");
            Iterator<T> it = decoderInfos.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (((MediaCodecInfo) next).isFormatSupported(format)) {
                    break;
                }
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) next;
            if (mediaCodecInfo == null) {
                return ResultHolder.fromResult(0);
            }
            return ResultHolder.fromResult(Integer.valueOf(mediaCodecInfo.hardwareAccelerated ? 2 : 1));
        } catch (MediaCodecUtil.DecoderQueryException e) {
            MpbLog.e("DecoderQueryException for codec=" + str + ": " + e.getMessage());
            return ResultHolder.fromErrorCode(ErrorCode.DECODER_QUERY_EXCEPTION);
        }
    }
}
