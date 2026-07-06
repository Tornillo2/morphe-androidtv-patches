package com.google.android.exoplayer2.mediacodec;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Pair;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.mediacodec.MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.mediacodec.MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline1;
import androidx.media3.exoplayer.mediacodec.MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline2;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaCodecInfo {
    public static final int COVERAGE_RESULT_NO = 1;
    public static final int COVERAGE_RESULT_NO_EMPTY_LIST = 0;
    public static final int COVERAGE_RESULT_YES = 2;
    public static final int MAX_SUPPORTED_INSTANCES_UNKNOWN = -1;
    public static final String TAG = "MediaCodecInfo";
    public final boolean adaptive;

    @Nullable
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String codecMimeType;
    public final boolean hardwareAccelerated;
    public final boolean isVideo;
    public final String mimeType;
    public final String name;
    public final boolean secure;
    public final boolean softwareOnly;
    public final boolean tunneling;
    public final boolean vendor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static final class Api29 {
        @DoNotInline
        public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> supportedPerformancePoints = videoCapabilities.getSupportedPerformancePoints();
            if (supportedPerformancePoints == null || supportedPerformancePoints.isEmpty() || MediaCodecInfo.needsIgnorePerformancePointsWorkaround()) {
                return 0;
            }
            MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline2.m();
            MediaCodecInfo.VideoCapabilities.PerformancePoint performancePointM = MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline1.m(i, i2, (int) d);
            for (int i3 = 0; i3 < supportedPerformancePoints.size(); i3++) {
                if (MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline0.m(supportedPerformancePoints.get(i3)).covers(performancePointM)) {
                    return 2;
                }
            }
            return 1;
        }
    }

    @VisibleForTesting
    public MediaCodecInfo(String str, String str2, String str3, @Nullable MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        str.getClass();
        this.name = str;
        this.mimeType = str2;
        this.codecMimeType = str3;
        this.capabilities = codecCapabilities;
        this.hardwareAccelerated = z;
        this.softwareOnly = z2;
        this.vendor = z3;
        this.adaptive = z4;
        this.tunneling = z5;
        this.secure = z6;
        this.isVideo = MimeTypes.isVideo(str2);
    }

    public static int adjustMaxInputChannelCount(String str, String str2, int i) {
        if (i > 1 || ((Util.SDK_INT >= 26 && i > 0) || "audio/mpeg".equals(str2) || "audio/3gpp".equals(str2) || "audio/amr-wb".equals(str2) || "audio/mp4a-latm".equals(str2) || "audio/vorbis".equals(str2) || "audio/opus".equals(str2) || "audio/raw".equals(str2) || "audio/flac".equals(str2) || "audio/g711-alaw".equals(str2) || "audio/g711-mlaw".equals(str2) || "audio/gsm".equals(str2))) {
            return i;
        }
        int i2 = "audio/ac3".equals(str2) ? 6 : "audio/eac3".equals(str2) ? 16 : 30;
        Log.w("MediaCodecInfo", "AssumedMaxChannelAdjustment: " + str + ", [" + i + " to " + i2 + "]");
        return i2;
    }

    @RequiresApi(21)
    public static boolean areSizeAndRateSupportedV21(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        Point pointAlignVideoSizeV21 = alignVideoSizeV21(videoCapabilities, i, i2);
        int i3 = pointAlignVideoSizeV21.x;
        int i4 = pointAlignVideoSizeV21.y;
        return (d == -1.0d || d < 1.0d) ? videoCapabilities.isSizeSupported(i3, i4) : videoCapabilities.areSizeAndRateSupported(i3, i4, Math.floor(d));
    }

    public static MediaCodecInfo.CodecProfileLevel[] estimateLegacyVp9ProfileLevels(@Nullable MediaCodecInfo.CodecCapabilities codecCapabilities) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        int iIntValue = (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) ? 0 : ((Integer) videoCapabilities.getBitrateRange().getUpper()).intValue();
        int i = iIntValue >= 180000000 ? 1024 : iIntValue >= 120000000 ? 512 : iIntValue >= 60000000 ? 256 : iIntValue >= 30000000 ? 128 : iIntValue >= 18000000 ? 64 : iIntValue >= 12000000 ? 32 : iIntValue >= 7200000 ? 16 : iIntValue >= 3600000 ? 8 : iIntValue >= 1800000 ? 4 : iIntValue >= 800000 ? 2 : 1;
        MediaCodecInfo.CodecProfileLevel codecProfileLevel = new MediaCodecInfo.CodecProfileLevel();
        codecProfileLevel.profile = 1;
        codecProfileLevel.level = i;
        return new MediaCodecInfo.CodecProfileLevel[]{codecProfileLevel};
    }

    @RequiresApi(23)
    public static int getMaxSupportedInstancesV23(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.getMaxSupportedInstances();
    }

    public static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 19 && codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    @RequiresApi(19)
    public static boolean isAdaptiveV19(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    public static boolean isSecure(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && codecCapabilities.isFeatureSupported("secure-playback");
    }

    @RequiresApi(21)
    public static boolean isSecureV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    public static boolean isTunneling(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    @RequiresApi(21)
    public static boolean isTunnelingV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    public static boolean needsAdaptationFlushWorkaround(String str) {
        return "audio/opus".equals(str);
    }

    public static boolean needsAdaptationReconfigureWorkaround(String str) {
        return Util.MODEL.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str);
    }

    public static boolean needsDisableAdaptationWorkaround(String str) {
        if (Util.SDK_INT > 22) {
            return false;
        }
        String str2 = Util.MODEL;
        if ("ODROID-XU3".equals(str2) || "Nexus 10".equals(str2)) {
            return "OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str);
        }
        return false;
    }

    public static boolean needsIgnorePerformancePointsWorkaround() {
        String str = Util.DEVICE;
        if (str.equals("sabrina") || str.equals("boreal")) {
            return true;
        }
        String str2 = Util.MODEL;
        return str2.startsWith("Lenovo TB-X605") || str2.startsWith("Lenovo TB-X606") || str2.startsWith("Lenovo TB-X616");
    }

    public static boolean needsProfileExcludedWorkaround(String str, int i) {
        if (!"video/hevc".equals(str) || 2 != i) {
            return false;
        }
        String str2 = Util.DEVICE;
        return "sailfish".equals(str2) || "marlin".equals(str2);
    }

    public static boolean needsRotatedVerticalResolutionWorkaround(String str) {
        return ("OMX.MTK.VIDEO.DECODER.HEVC".equals(str) && "mcv5a".equals(Util.DEVICE)) ? false : true;
    }

    public static MediaCodecInfo newInstance(String str, String str2, String str3, @Nullable MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return new MediaCodecInfo(str, str2, str3, codecCapabilities, z, z2, z3, (z4 || codecCapabilities == null || !isAdaptive(codecCapabilities) || needsDisableAdaptationWorkaround(str)) ? false : true, codecCapabilities != null && isTunneling(codecCapabilities), z5 || (codecCapabilities != null && isSecure(codecCapabilities)));
    }

    @Nullable
    @RequiresApi(21)
    public Point alignVideoSizeV21(int i, int i2) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) {
            return null;
        }
        return alignVideoSizeV21(videoCapabilities, i, i2);
    }

    public DecoderReuseEvaluation canReuseCodec(Format format, Format format2) {
        Format format3;
        Format format4;
        int i = !Util.areEqual(format.sampleMimeType, format2.sampleMimeType) ? 8 : 0;
        if (this.isVideo) {
            if (format.rotationDegrees != format2.rotationDegrees) {
                i |= 1024;
            }
            if (!this.adaptive && (format.width != format2.width || format.height != format2.height)) {
                i |= 512;
            }
            if (!Util.areEqual(format.colorInfo, format2.colorInfo)) {
                i |= 2048;
            }
            if (needsAdaptationReconfigureWorkaround(this.name) && !format.initializationDataEquals(format2)) {
                i |= 2;
            }
            if (i == 0) {
                return new DecoderReuseEvaluation(this.name, format, format2, format.initializationDataEquals(format2) ? 3 : 2, 0);
            }
            format3 = format;
            format4 = format2;
        } else {
            format3 = format;
            format4 = format2;
            if (format3.channelCount != format4.channelCount) {
                i |= 4096;
            }
            if (format3.sampleRate != format4.sampleRate) {
                i |= 8192;
            }
            if (format3.pcmEncoding != format4.pcmEncoding) {
                i |= 16384;
            }
            if (i == 0 && "audio/mp4a-latm".equals(this.mimeType)) {
                Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format3);
                Pair<Integer, Integer> codecProfileAndLevel2 = MediaCodecUtil.getCodecProfileAndLevel(format4);
                if (codecProfileAndLevel != null && codecProfileAndLevel2 != null) {
                    int iIntValue = ((Integer) codecProfileAndLevel.first).intValue();
                    int iIntValue2 = ((Integer) codecProfileAndLevel2.first).intValue();
                    if (iIntValue == 42 && iIntValue2 == 42) {
                        return new DecoderReuseEvaluation(this.name, format3, format4, 3, 0);
                    }
                }
            }
            if (!format3.initializationDataEquals(format4)) {
                i |= 32;
            }
            if ("audio/opus".equals(this.mimeType)) {
                i |= 2;
            }
            if (i == 0) {
                return new DecoderReuseEvaluation(this.name, format3, format4, 1, 0);
            }
        }
        return new DecoderReuseEvaluation(this.name, format3, format4, 0, i);
    }

    public int getMaxSupportedInstances() {
        MediaCodecInfo.CodecCapabilities codecCapabilities;
        if (Util.SDK_INT < 23 || (codecCapabilities = this.capabilities) == null) {
            return -1;
        }
        return codecCapabilities.getMaxSupportedInstances();
    }

    public MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        return (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) ? new MediaCodecInfo.CodecProfileLevel[0] : codecProfileLevelArr;
    }

    @RequiresApi(21)
    public boolean isAudioChannelCountSupportedV21(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        }
        if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i) {
            return true;
        }
        logNoSupport("channelCount.support, " + i);
        return false;
    }

    @RequiresApi(21)
    public boolean isAudioSampleRateSupportedV21(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        }
        if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        }
        logNoSupport("sampleRate.support, " + i);
        return false;
    }

    public final boolean isCodecProfileAndLevelSupported(Format format, boolean z) {
        Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
        if (codecProfileAndLevel == null) {
            return true;
        }
        int iIntValue = ((Integer) codecProfileAndLevel.first).intValue();
        int iIntValue2 = ((Integer) codecProfileAndLevel.second).intValue();
        if ("video/dolby-vision".equals(format.sampleMimeType)) {
            if (!"video/avc".equals(this.mimeType)) {
                iIntValue = "video/hevc".equals(this.mimeType) ? 2 : 8;
            }
            iIntValue2 = 0;
        }
        if (AmazonQuirks.shouldSkipProfileLevelCheck() || !(this.isVideo || iIntValue == 42)) {
            return true;
        }
        MediaCodecInfo.CodecProfileLevel[] profileLevels = getProfileLevels();
        if (Util.SDK_INT <= 23 && "video/x-vnd.on2.vp9".equals(this.mimeType) && profileLevels.length == 0) {
            profileLevels = estimateLegacyVp9ProfileLevels(this.capabilities);
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : profileLevels) {
            if (codecProfileLevel.profile == iIntValue && ((codecProfileLevel.level >= iIntValue2 || !z) && !needsProfileExcludedWorkaround(this.mimeType, iIntValue))) {
                return true;
            }
        }
        logNoSupport("codec.profileLevel, " + format.codecs + ", " + this.codecMimeType);
        return false;
    }

    public boolean isFormatFunctionallySupported(Format format) {
        return isSampleMimeTypeSupported(format) && isCodecProfileAndLevelSupported(format, false);
    }

    public boolean isFormatSupported(Format format) throws MediaCodecUtil.DecoderQueryException {
        int i;
        int i2;
        int i3;
        if (!isSampleMimeTypeSupported(format) || !isCodecProfileAndLevelSupported(format, true)) {
            return false;
        }
        if (!this.isVideo) {
            return Util.SDK_INT < 21 || (((i = format.sampleRate) == -1 || isAudioSampleRateSupportedV21(i)) && ((i2 = format.channelCount) == -1 || isAudioChannelCountSupportedV21(i2)));
        }
        int i4 = format.width;
        if (i4 <= 0 || (i3 = format.height) <= 0) {
            return true;
        }
        if (Util.SDK_INT >= 21) {
            return isVideoSizeAndRateSupportedV21(i4, i3, format.frameRate);
        }
        boolean z = i4 * i3 <= MediaCodecUtil.maxH264DecodableFrameSize();
        if (!z) {
            logNoSupport("legacyFrameSize, " + format.width + "x" + format.height);
        }
        return z;
    }

    public boolean isHdr10PlusOutOfBandMetadataSupported() {
        if (Util.SDK_INT >= 29 && "video/x-vnd.on2.vp9".equals(this.mimeType)) {
            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : getProfileLevels()) {
                if (codecProfileLevel.profile == 16384) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isSampleMimeTypeSupported(Format format) {
        return this.mimeType.equals(format.sampleMimeType) || this.mimeType.equals(MediaCodecUtil.getAlternativeCodecMimeType(format));
    }

    public boolean isSeamlessAdaptationSupported(Format format) {
        if (this.isVideo) {
            return this.adaptive;
        }
        Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
        return codecProfileAndLevel != null && ((Integer) codecProfileAndLevel.first).intValue() == 42;
    }

    @RequiresApi(21)
    public boolean isVideoSizeAndRateSupportedV21(int i, int i2, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        }
        if (Util.SDK_INT >= 29) {
            int iAreResolutionAndFrameRateCovered = Api29.areResolutionAndFrameRateCovered(videoCapabilities, i, i2, d);
            if (iAreResolutionAndFrameRateCovered == 2) {
                return true;
            }
            if (iAreResolutionAndFrameRateCovered == 1) {
                StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("sizeAndRate.cover, ", i, "x", i2, "@");
                sbM.append(d);
                logNoSupport(sbM.toString());
                return false;
            }
        }
        if (!areSizeAndRateSupportedV21(videoCapabilities, i, i2, d)) {
            if (i >= i2 || !needsRotatedVerticalResolutionWorkaround(this.name) || !areSizeAndRateSupportedV21(videoCapabilities, i2, i, d)) {
                StringBuilder sbM2 = MutableFloatList$$ExternalSyntheticOutline0.m("sizeAndRate.support, ", i, "x", i2, "@");
                sbM2.append(d);
                logNoSupport(sbM2.toString());
                return false;
            }
            StringBuilder sbM3 = MutableFloatList$$ExternalSyntheticOutline0.m("sizeAndRate.rotated, ", i, "x", i2, "@");
            sbM3.append(d);
            logAssumedSupport(sbM3.toString());
        }
        return true;
    }

    public final void logAssumedSupport(String str) {
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("AssumedSupport [", str, "] [");
        sbM.append(this.name);
        sbM.append(", ");
        sbM.append(this.mimeType);
        sbM.append("] [");
        sbM.append(Util.DEVICE_DEBUG_INFO);
        sbM.append("]");
        Log.d("MediaCodecInfo", sbM.toString());
    }

    public final void logNoSupport(String str) {
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("NoSupport [", str, "] [");
        sbM.append(this.name);
        sbM.append(", ");
        sbM.append(this.mimeType);
        sbM.append("] [");
        sbM.append(Util.DEVICE_DEBUG_INFO);
        sbM.append("]");
        Log.d("MediaCodecInfo", sbM.toString());
    }

    public String toString() {
        return this.name;
    }

    @RequiresApi(21)
    public static Point alignVideoSizeV21(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.ceilDivide(i, widthAlignment) * widthAlignment, Util.ceilDivide(i2, heightAlignment) * heightAlignment);
    }

    @Deprecated
    public boolean isSeamlessAdaptationSupported(Format format, Format format2, boolean z) {
        if (!z && format.colorInfo != null && format2.colorInfo == null) {
            Format.Builder builder = new Format.Builder(format2);
            builder.colorInfo = format.colorInfo;
            format2 = new Format(builder);
        }
        int i = canReuseCodec(format, format2).result;
        return i == 2 || i == 3;
    }
}
