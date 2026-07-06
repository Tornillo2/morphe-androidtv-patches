package androidx.media3.exoplayer.mediacodec;

import android.annotation.SuppressLint;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.CheckResult;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil$$ExternalSyntheticOutline0;
import androidx.media3.datasource.cache.SimpleCache$$ExternalSyntheticOutline0;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"InlinedApi"})
@UnstableApi
public final class MediaCodecUtil {
    public static final String CODEC_ID_AV01 = "av01";
    public static final String CODEC_ID_AVC1 = "avc1";
    public static final String CODEC_ID_AVC2 = "avc2";
    public static final String CODEC_ID_HEV1 = "hev1";
    public static final String CODEC_ID_HVC1 = "hvc1";
    public static final String CODEC_ID_MP4A = "mp4a";
    public static final String CODEC_ID_VP09 = "vp09";
    public static final String TAG = "MediaCodecUtil";
    public static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");

    @GuardedBy("MediaCodecUtil.class")
    public static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    public static int maxH264DecodableFrameSize = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CodecKey {
        public final String mimeType;
        public final boolean secure;
        public final boolean tunneling;

        public CodecKey(String str, boolean z, boolean z2) {
            this.mimeType = str;
            this.secure = z;
            this.tunneling = z2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == CodecKey.class) {
                CodecKey codecKey = (CodecKey) obj;
                if (TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure && this.tunneling == codecKey.tunneling) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.mimeType, 31, 31) + (this.secure ? 1231 : 1237)) * 31) + (this.tunneling ? 1231 : 1237);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DecoderQueryException extends Exception {
        public DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface MediaCodecListCompat {
        int getCodecCount();

        android.media.MediaCodecInfo getCodecInfoAt(int i);

        boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        public MediaCodecListCompatV16() {
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public android.media.MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return false;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return "secure-playback".equals(str) && "video/avc".equals(str2);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return false;
        }

        public MediaCodecListCompatV16(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        public final int codecKind;

        @Nullable
        public android.media.MediaCodecInfo[] mediaCodecInfos;

        public MediaCodecListCompatV21(boolean z, boolean z2) {
            this.codecKind = (z || z2) ? 1 : 0;
        }

        @EnsuresNonNull({"mediaCodecInfos"})
        public final void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public android.media.MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureRequired(str);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported(str);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ScoreProvider<T> {
        int getScore(T t);
    }

    public static /* synthetic */ int $r8$lambda$DwqylZu4KTEvi2rFV62ELIMhwqI(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.name.startsWith("OMX.google") ? 1 : 0;
    }

    public static /* synthetic */ int $r8$lambda$M9JQqv23zIwW7j6SScthNgRYy7g(ScoreProvider scoreProvider, Object obj, Object obj2) {
        return scoreProvider.getScore(obj2) - scoreProvider.getScore(obj);
    }

    public static /* synthetic */ int $r8$lambda$Z8dAbbPXfdC1pwNXxAZccc0tgoo(Format format, MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isFormatFunctionallySupported(format) ? 1 : 0;
    }

    public static /* synthetic */ int $r8$lambda$sRfR6bKPqCiqJbA2zRytr7_lgRU(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return 1;
        }
        return (Util.SDK_INT >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : -1;
    }

    public static void applyWorkarounds(String str, List<MediaCodecInfo> list) {
        if ("audio/raw".equals(str)) {
            if (Util.SDK_INT < 26 && Util.DEVICE.equals("R9") && list.size() == 1 && list.get(0).name.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                list.add(MediaCodecInfo.newInstance("OMX.google.raw.decoder", "audio/raw", "audio/raw", null, false, true, false, false, false));
            }
            sortByScore(list, new MediaCodecUtil$$ExternalSyntheticLambda1());
        }
        int i = Util.SDK_INT;
        if (i < 21 && list.size() > 1) {
            String str2 = list.get(0).name;
            if ("OMX.SEC.mp3.dec".equals(str2) || "OMX.SEC.MP3.Decoder".equals(str2) || "OMX.brcm.audio.mp3.decoder".equals(str2)) {
                sortByScore(list, new MediaCodecUtil$$ExternalSyntheticLambda2());
            }
        }
        if (i >= 32 || list.size() <= 1 || !"OMX.qti.audio.decoder.flac".equals(list.get(0).name)) {
            return;
        }
        list.add(list.remove(0));
    }

    public static int av1LevelNumberToConst(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            case 13:
                return 8192;
            case 14:
                return 16384;
            case 15:
                return 32768;
            case 16:
                return 65536;
            case 17:
                return 131072;
            case 18:
                return 262144;
            case 19:
                return 524288;
            case 20:
                return 1048576;
            case 21:
                return 2097152;
            case 22:
                return 4194304;
            case 23:
                return 8388608;
            default:
                return -1;
        }
    }

    public static int avcLevelNumberToConst(int i) {
        switch (i) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 8;
            case 13:
                return 16;
            default:
                switch (i) {
                    case 20:
                        return 32;
                    case 21:
                        return 64;
                    case 22:
                        return 128;
                    default:
                        switch (i) {
                            case 30:
                                return 256;
                            case 31:
                                return 512;
                            case 32:
                                return 1024;
                            default:
                                switch (i) {
                                    case 40:
                                        return 2048;
                                    case 41:
                                        return 4096;
                                    case 42:
                                        return 8192;
                                    default:
                                        switch (i) {
                                            case 50:
                                                return 16384;
                                            case 51:
                                                return 32768;
                                            case 52:
                                                return 65536;
                                            default:
                                                return -1;
                                        }
                                }
                        }
                }
        }
    }

    public static int avcLevelToMaxFrameSize(int i) {
        if (i == 1 || i == 2) {
            return 25344;
        }
        switch (i) {
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            case 131072:
            case 262144:
            case 524288:
                return 35651584;
            default:
                return -1;
        }
    }

    public static int avcProfileNumberToConst(int i) {
        if (i == 66) {
            return 1;
        }
        if (i == 77) {
            return 2;
        }
        if (i == 88) {
            return 4;
        }
        if (i == 100) {
            return 8;
        }
        if (i == 110) {
            return 16;
        }
        if (i != 122) {
            return i != 244 ? -1 : 64;
        }
        return 32;
    }

    @VisibleForTesting
    public static synchronized void clearDecoderInfoCache() {
        decoderInfosCache.clear();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Nullable
    public static Integer dolbyVisionStringToLevel(@Nullable String str) {
        if (str == null) {
            return null;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case 1537:
                if (str.equals("01")) {
                    b = 0;
                }
                break;
            case 1538:
                if (str.equals("02")) {
                    b = 1;
                }
                break;
            case 1539:
                if (str.equals("03")) {
                    b = 2;
                }
                break;
            case 1540:
                if (str.equals("04")) {
                    b = 3;
                }
                break;
            case 1541:
                if (str.equals("05")) {
                    b = 4;
                }
                break;
            case 1542:
                if (str.equals("06")) {
                    b = 5;
                }
                break;
            case 1543:
                if (str.equals("07")) {
                    b = 6;
                }
                break;
            case 1544:
                if (str.equals("08")) {
                    b = 7;
                }
                break;
            case 1545:
                if (str.equals("09")) {
                    b = 8;
                }
                break;
            case 1567:
                if (str.equals("10")) {
                    b = 9;
                }
                break;
            case 1568:
                if (str.equals("11")) {
                    b = 10;
                }
                break;
            case 1569:
                if (str.equals("12")) {
                    b = Ascii.VT;
                }
                break;
            case 1570:
                if (str.equals("13")) {
                    b = Ascii.FF;
                }
                break;
        }
        switch (b) {
        }
        return null;
    }

    @Nullable
    public static Integer dolbyVisionStringToProfile(@Nullable String str) {
        if (str == null) {
            return null;
        }
        switch (str) {
        }
        return null;
    }

    @Nullable
    public static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        int iMp4aAudioObjectTypeToProfile;
        if (strArr.length != 3) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed MP4A codec string: ", str, "MediaCodecUtil");
            return null;
        }
        try {
            if ("audio/mp4a-latm".equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16))) && (iMp4aAudioObjectTypeToProfile = mp4aAudioObjectTypeToProfile(Integer.parseInt(strArr[2]))) != -1) {
                return new Pair<>(Integer.valueOf(iMp4aAudioObjectTypeToProfile), 0);
            }
        } catch (NumberFormatException unused) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed MP4A codec string: ", str, "MediaCodecUtil");
        }
        return null;
    }

    @Nullable
    public static String getAlternativeCodecMimeType(Format format) {
        Pair<Integer, Integer> codecProfileAndLevel;
        if ("audio/eac3-joc".equals(format.sampleMimeType)) {
            return "audio/eac3";
        }
        if (!"video/dolby-vision".equals(format.sampleMimeType) || (codecProfileAndLevel = getCodecProfileAndLevel(format)) == null) {
            return null;
        }
        int iIntValue = ((Integer) codecProfileAndLevel.first).intValue();
        if (iIntValue == 16 || iIntValue == 256) {
            return "video/hevc";
        }
        if (iIntValue == 512) {
            return "video/avc";
        }
        return null;
    }

    public static List<MediaCodecInfo> getAlternativeDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws DecoderQueryException {
        String alternativeCodecMimeType = getAlternativeCodecMimeType(format);
        return alternativeCodecMimeType == null ? RegularImmutableList.EMPTY : mediaCodecSelector.getDecoderInfos(alternativeCodecMimeType, z, z2);
    }

    @Nullable
    public static Pair<Integer, Integer> getAv1ProfileAndLevel(String str, String[] strArr, @Nullable ColorInfo colorInfo) {
        int i;
        if (strArr.length < 4) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed AV1 codec string: ", str, "MediaCodecUtil");
            return null;
        }
        try {
            int i2 = Integer.parseInt(strArr[1]);
            int i3 = Integer.parseInt(strArr[2].substring(0, 2));
            int i4 = Integer.parseInt(strArr[3]);
            if (i2 != 0) {
                NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown AV1 profile: ", i2, "MediaCodecUtil");
                return null;
            }
            if (i4 != 8 && i4 != 10) {
                NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown AV1 bit depth: ", i4, "MediaCodecUtil");
                return null;
            }
            int i5 = i4 != 8 ? (colorInfo == null || !(colorInfo.hdrStaticInfo != null || (i = colorInfo.colorTransfer) == 7 || i == 6)) ? 2 : 4096 : 1;
            int iAv1LevelNumberToConst = av1LevelNumberToConst(i3);
            if (iAv1LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(i5), Integer.valueOf(iAv1LevelNumberToConst));
            }
            NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown AV1 level: ", i3, "MediaCodecUtil");
            return null;
        } catch (NumberFormatException unused) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed AV1 codec string: ", str, "MediaCodecUtil");
            return null;
        }
    }

    @Nullable
    public static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        int i;
        int i2;
        if (strArr.length < 2) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed AVC codec string: ", str, "MediaCodecUtil");
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                i2 = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i = Integer.parseInt(strArr[1].substring(4), 16);
            } else {
                if (strArr.length < 3) {
                    Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + str);
                    return null;
                }
                int i3 = Integer.parseInt(strArr[1]);
                i = Integer.parseInt(strArr[2]);
                i2 = i3;
            }
            int iAvcProfileNumberToConst = avcProfileNumberToConst(i2);
            if (iAvcProfileNumberToConst == -1) {
                NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown AVC profile: ", i2, "MediaCodecUtil");
                return null;
            }
            int iAvcLevelNumberToConst = avcLevelNumberToConst(i);
            if (iAvcLevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(iAvcProfileNumberToConst), Integer.valueOf(iAvcLevelNumberToConst));
            }
            NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown AVC level: ", i, "MediaCodecUtil");
            return null;
        } catch (NumberFormatException unused) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed AVC codec string: ", str, "MediaCodecUtil");
            return null;
        }
    }

    @Nullable
    public static String getCodecMimeType(android.media.MediaCodecInfo mediaCodecInfo, String str, String str2) {
        for (String str3 : mediaCodecInfo.getSupportedTypes()) {
            if (str3.equalsIgnoreCase(str2)) {
                return str3;
            }
        }
        if (str2.equals("video/dolby-vision")) {
            if ("OMX.MS.HEVCDV.Decoder".equals(str)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(str) || "OMX.realtek.video.decoder.tunneled".equals(str)) {
                return "video/dv_hevc";
            }
            return null;
        }
        if (str2.equals("audio/alac") && "OMX.lge.alac.decoder".equals(str)) {
            return "audio/x-lg-alac";
        }
        if (str2.equals("audio/flac") && "OMX.lge.flac.decoder".equals(str)) {
            return "audio/x-lg-flac";
        }
        if (str2.equals("audio/ac3") && "OMX.lge.ac3.decoder".equals(str)) {
            return "audio/lg-ac3";
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(androidx.media3.common.Format r6) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(androidx.media3.common.Format):android.util.Pair");
    }

    @Nullable
    public static MediaCodecInfo getDecoderInfo(String str, boolean z, boolean z2) throws DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(str, z, z2);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z, boolean z2) throws DecoderQueryException {
        try {
            CodecKey codecKey = new CodecKey(str, z, z2);
            HashMap<CodecKey, List<MediaCodecInfo>> map = decoderInfosCache;
            List<MediaCodecInfo> list = map.get(codecKey);
            if (list != null) {
                return list;
            }
            int i = Util.SDK_INT;
            ArrayList<MediaCodecInfo> decoderInfosInternal = getDecoderInfosInternal(codecKey, i >= 21 ? new MediaCodecListCompatV21(z, z2) : new MediaCodecListCompatV16());
            if (z && decoderInfosInternal.isEmpty() && 21 <= i && i <= 23) {
                decoderInfosInternal = getDecoderInfosInternal(codecKey, new MediaCodecListCompatV16());
                if (!decoderInfosInternal.isEmpty()) {
                    Log.w("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + decoderInfosInternal.get(0).name);
                }
            }
            applyWorkarounds(str, decoderInfosInternal);
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) decoderInfosInternal);
            map.put(codecKey, immutableListCopyOf);
            return immutableListCopyOf;
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b A[PHI: r16
      0x008b: PHI (r16v9 boolean) = (r16v5 boolean), (r16v11 boolean) binds: [B:41:0x009b, B:33:0x0088] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fa A[Catch: Exception -> 0x0144, TRY_ENTER, TryCatch #4 {Exception -> 0x0144, blocks: (B:3:0x0008, B:5:0x001b, B:68:0x0119, B:8:0x002b, B:11:0x0036, B:62:0x00f2, B:65:0x00fa, B:67:0x0100, B:69:0x0121, B:70:0x0142), top: B:83:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0121 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<androidx.media3.exoplayer.mediacodec.MediaCodecInfo> getDecoderInfosInternal(androidx.media3.exoplayer.mediacodec.MediaCodecUtil.CodecKey r19, androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat r20) throws androidx.media3.exoplayer.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecUtil.getDecoderInfosInternal(androidx.media3.exoplayer.mediacodec.MediaCodecUtil$CodecKey, androidx.media3.exoplayer.mediacodec.MediaCodecUtil$MediaCodecListCompat):java.util.ArrayList");
    }

    @RequiresNonNull({"#2.sampleMimeType"})
    public static List<MediaCodecInfo> getDecoderInfosSoftMatch(MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(format.sampleMimeType, z, z2);
        List<MediaCodecInfo> alternativeDecoderInfos = getAlternativeDecoderInfos(mediaCodecSelector, format, z, z2);
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll((Iterable) decoderInfos);
        builder.addAll((Iterable) alternativeDecoderInfos);
        return builder.build();
    }

    @CheckResult
    public static List<MediaCodecInfo> getDecoderInfosSortedByFormatSupport(List<MediaCodecInfo> list, final Format format) {
        ArrayList arrayList = new ArrayList(list);
        sortByScore(arrayList, new ScoreProvider() { // from class: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$$ExternalSyntheticLambda3
            @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
            public final int getScore(Object obj) {
                return MediaCodecUtil.$r8$lambda$Z8dAbbPXfdC1pwNXxAZccc0tgoo(format, (MediaCodecInfo) obj);
            }
        });
        return arrayList;
    }

    @Nullable
    public static MediaCodecInfo getDecryptOnlyDecoderInfo() throws DecoderQueryException {
        return getDecoderInfo("audio/raw", false, false);
    }

    @Nullable
    public static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed Dolby Vision codec string: ", str, "MediaCodecUtil");
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed Dolby Vision codec string: ", str, "MediaCodecUtil");
            return null;
        }
        String strGroup = matcher.group(1);
        Integer numDolbyVisionStringToProfile = dolbyVisionStringToProfile(strGroup);
        if (numDolbyVisionStringToProfile == null) {
            SimpleCache$$ExternalSyntheticOutline0.m("Unknown Dolby Vision profile string: ", strGroup, "MediaCodecUtil");
            return null;
        }
        String str2 = strArr[2];
        Integer numDolbyVisionStringToLevel = dolbyVisionStringToLevel(str2);
        if (numDolbyVisionStringToLevel != null) {
            return new Pair<>(numDolbyVisionStringToProfile, numDolbyVisionStringToLevel);
        }
        SimpleCache$$ExternalSyntheticOutline0.m("Unknown Dolby Vision level string: ", str2, "MediaCodecUtil");
        return null;
    }

    @Nullable
    public static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr, @Nullable ColorInfo colorInfo) {
        if (strArr.length < 4) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed HEVC codec string: ", str, "MediaCodecUtil");
            return null;
        }
        int i = 1;
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed HEVC codec string: ", str, "MediaCodecUtil");
            return null;
        }
        String strGroup = matcher.group(1);
        if (!"1".equals(strGroup)) {
            if (!ExifInterface.GPS_MEASUREMENT_2D.equals(strGroup)) {
                SimpleCache$$ExternalSyntheticOutline0.m("Unknown HEVC profile string: ", strGroup, "MediaCodecUtil");
                return null;
            }
            i = (colorInfo == null || colorInfo.colorTransfer != 6) ? 2 : 4096;
        }
        String str2 = strArr[3];
        Integer numHevcCodecStringToProfileLevel = hevcCodecStringToProfileLevel(str2);
        if (numHevcCodecStringToProfileLevel != null) {
            return new Pair<>(Integer.valueOf(i), numHevcCodecStringToProfileLevel);
        }
        SimpleCache$$ExternalSyntheticOutline0.m("Unknown HEVC level string: ", str2, "MediaCodecUtil");
        return null;
    }

    @Nullable
    public static Pair<Integer, Integer> getVp9ProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed VP9 codec string: ", str, "MediaCodecUtil");
            return null;
        }
        try {
            int i = Integer.parseInt(strArr[1]);
            int i2 = Integer.parseInt(strArr[2]);
            int iVp9ProfileNumberToConst = vp9ProfileNumberToConst(i);
            if (iVp9ProfileNumberToConst == -1) {
                NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown VP9 profile: ", i, "MediaCodecUtil");
                return null;
            }
            int iVp9LevelNumberToConst = vp9LevelNumberToConst(i2);
            if (iVp9LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(iVp9ProfileNumberToConst), Integer.valueOf(iVp9LevelNumberToConst));
            }
            NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown VP9 level: ", i2, "MediaCodecUtil");
            return null;
        } catch (NumberFormatException unused) {
            SimpleCache$$ExternalSyntheticOutline0.m("Ignoring malformed VP9 codec string: ", str, "MediaCodecUtil");
            return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Nullable
    public static Integer hevcCodecStringToProfileLevel(@Nullable String str) {
        if (str == null) {
            return null;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case 70821:
                if (str.equals("H30")) {
                    b = 0;
                }
                break;
            case 70914:
                if (str.equals("H60")) {
                    b = 1;
                }
                break;
            case 70917:
                if (str.equals("H63")) {
                    b = 2;
                }
                break;
            case 71007:
                if (str.equals("H90")) {
                    b = 3;
                }
                break;
            case 71010:
                if (str.equals("H93")) {
                    b = 4;
                }
                break;
            case 74665:
                if (str.equals("L30")) {
                    b = 5;
                }
                break;
            case 74758:
                if (str.equals("L60")) {
                    b = 6;
                }
                break;
            case 74761:
                if (str.equals("L63")) {
                    b = 7;
                }
                break;
            case 74851:
                if (str.equals("L90")) {
                    b = 8;
                }
                break;
            case 74854:
                if (str.equals("L93")) {
                    b = 9;
                }
                break;
            case 2193639:
                if (str.equals("H120")) {
                    b = 10;
                }
                break;
            case 2193642:
                if (str.equals("H123")) {
                    b = Ascii.VT;
                }
                break;
            case 2193732:
                if (str.equals("H150")) {
                    b = Ascii.FF;
                }
                break;
            case 2193735:
                if (str.equals("H153")) {
                    b = 13;
                }
                break;
            case 2193738:
                if (str.equals("H156")) {
                    b = Ascii.SO;
                }
                break;
            case 2193825:
                if (str.equals("H180")) {
                    b = Ascii.SI;
                }
                break;
            case 2193828:
                if (str.equals("H183")) {
                    b = 16;
                }
                break;
            case 2193831:
                if (str.equals("H186")) {
                    b = 17;
                }
                break;
            case 2312803:
                if (str.equals("L120")) {
                    b = Ascii.DC2;
                }
                break;
            case 2312806:
                if (str.equals("L123")) {
                    b = 19;
                }
                break;
            case 2312896:
                if (str.equals("L150")) {
                    b = Ascii.DC4;
                }
                break;
            case 2312899:
                if (str.equals("L153")) {
                    b = Ascii.NAK;
                }
                break;
            case 2312902:
                if (str.equals("L156")) {
                    b = Ascii.SYN;
                }
                break;
            case 2312989:
                if (str.equals("L180")) {
                    b = Ascii.ETB;
                }
                break;
            case 2312992:
                if (str.equals("L183")) {
                    b = Ascii.CAN;
                }
                break;
            case 2312995:
                if (str.equals("L186")) {
                    b = Ascii.EM;
                }
                break;
        }
        switch (b) {
        }
        return null;
    }

    public static boolean isAlias(android.media.MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 29 && mediaCodecInfo.isAlias();
    }

    @RequiresApi(29)
    public static boolean isAliasV29(android.media.MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isAlias();
    }

    public static boolean isCodecUsableDecoder(android.media.MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        int i = Util.SDK_INT;
        if (i < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (i < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str)) {
            String str3 = Util.DEVICE;
            if ("a70".equals(str3) || ("Xiaomi".equals(Util.MANUFACTURER) && str3.startsWith("HM"))) {
                return false;
            }
        }
        if (i == 16 && "OMX.qcom.audio.decoder.mp3".equals(str)) {
            String str4 = Util.DEVICE;
            if ("dlxu".equals(str4) || "protou".equals(str4) || "ville".equals(str4) || "villeplus".equals(str4) || "villec2".equals(str4) || str4.startsWith("gee") || "C6602".equals(str4) || "C6603".equals(str4) || "C6606".equals(str4) || "C6616".equals(str4) || "L36h".equals(str4) || "SO-02E".equals(str4)) {
                return false;
            }
        }
        if (i == 16 && "OMX.qcom.audio.decoder.aac".equals(str)) {
            String str5 = Util.DEVICE;
            if ("C1504".equals(str5) || "C1505".equals(str5) || "C1604".equals(str5) || "C1605".equals(str5)) {
                return false;
            }
        }
        if (i < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && "samsung".equals(Util.MANUFACTURER))) {
            String str6 = Util.DEVICE;
            if (str6.startsWith("zeroflte") || str6.startsWith("zerolte") || str6.startsWith("zenlte") || "SC-05G".equals(str6) || "marinelteatt".equals(str6) || "404SC".equals(str6) || "SC-04G".equals(str6) || "SCV31".equals(str6)) {
                return false;
            }
        }
        if (i <= 19 && "OMX.SEC.vp8.dec".equals(str) && "samsung".equals(Util.MANUFACTURER)) {
            String str7 = Util.DEVICE;
            if (str7.startsWith("d2") || str7.startsWith("serrano") || str7.startsWith("jflte") || str7.startsWith("santos") || str7.startsWith("t0")) {
                return false;
            }
        }
        if (i <= 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(str)) {
            return false;
        }
        return (i <= 23 && "audio/eac3-joc".equals(str2) && "OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) ? false : true;
    }

    public static boolean isHardwareAccelerated(android.media.MediaCodecInfo mediaCodecInfo, String str) {
        return Util.SDK_INT >= 29 ? mediaCodecInfo.isHardwareAccelerated() : !isSoftwareOnly(mediaCodecInfo, str);
    }

    @RequiresApi(29)
    public static boolean isHardwareAcceleratedV29(android.media.MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isHardwareAccelerated();
    }

    public static boolean isSoftwareOnly(android.media.MediaCodecInfo mediaCodecInfo, String str) {
        if (Util.SDK_INT >= 29) {
            return mediaCodecInfo.isSoftwareOnly();
        }
        if (MimeTypes.isAudio(str)) {
            return true;
        }
        String lowerCase = Ascii.toLowerCase(mediaCodecInfo.getName());
        if (lowerCase.startsWith("arc.")) {
            return false;
        }
        return lowerCase.startsWith("omx.google.") || lowerCase.startsWith("omx.ffmpeg.") || (lowerCase.startsWith("omx.sec.") && lowerCase.contains(".sw.")) || lowerCase.equals("omx.qcom.video.decoder.hevcswvdec") || lowerCase.startsWith("c2.android.") || lowerCase.startsWith("c2.google.") || !(lowerCase.startsWith("omx.") || lowerCase.startsWith("c2."));
    }

    @RequiresApi(29)
    public static boolean isSoftwareOnlyV29(android.media.MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isSoftwareOnly();
    }

    public static boolean isVendor(android.media.MediaCodecInfo mediaCodecInfo) {
        if (Util.SDK_INT >= 29) {
            return mediaCodecInfo.isVendor();
        }
        String lowerCase = Ascii.toLowerCase(mediaCodecInfo.getName());
        return (lowerCase.startsWith("omx.google.") || lowerCase.startsWith("c2.android.") || lowerCase.startsWith("c2.google.")) ? false : true;
    }

    @RequiresApi(29)
    public static boolean isVendorV29(android.media.MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isVendor();
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int iMax = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo("video/avc", false, false);
            if (decoderInfo != null) {
                MediaCodecInfo.CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int iMax2 = 0;
                while (iMax < length) {
                    iMax2 = Math.max(avcLevelToMaxFrameSize(profileLevels[iMax].level), iMax2);
                    iMax++;
                }
                iMax = Math.max(iMax2, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = iMax;
        }
        return maxH264DecodableFrameSize;
    }

    public static int mp4aAudioObjectTypeToProfile(int i) {
        int i2 = 17;
        if (i != 17) {
            i2 = 20;
            if (i != 20) {
                i2 = 23;
                if (i != 23) {
                    i2 = 29;
                    if (i != 29) {
                        i2 = 39;
                        if (i != 39) {
                            i2 = 42;
                            if (i != 42) {
                                switch (i) {
                                    case 1:
                                        return 1;
                                    case 2:
                                        return 2;
                                    case 3:
                                        return 3;
                                    case 4:
                                        return 4;
                                    case 5:
                                        return 5;
                                    case 6:
                                        return 6;
                                    default:
                                        return -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return i2;
    }

    public static <T> void sortByScore(List<T> list, final ScoreProvider<T> scoreProvider) {
        Collections.sort(list, new Comparator() { // from class: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MediaCodecUtil.$r8$lambda$M9JQqv23zIwW7j6SScthNgRYy7g(scoreProvider, obj, obj2);
            }
        });
    }

    public static int vp9LevelNumberToConst(int i) {
        if (i == 10) {
            return 1;
        }
        if (i == 11) {
            return 2;
        }
        if (i == 20) {
            return 4;
        }
        if (i == 21) {
            return 8;
        }
        if (i == 30) {
            return 16;
        }
        if (i == 31) {
            return 32;
        }
        if (i == 40) {
            return 64;
        }
        if (i == 41) {
            return 128;
        }
        if (i == 50) {
            return 256;
        }
        if (i == 51) {
            return 512;
        }
        switch (i) {
            case 60:
                return 2048;
            case 61:
                return 4096;
            case 62:
                return 8192;
            default:
                return -1;
        }
    }

    public static int vp9ProfileNumberToConst(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? -1 : 8;
        }
        return 4;
    }

    public static void warmDecoderInfoCache(String str, boolean z, boolean z2) {
        try {
            getDecoderInfos(str, z, z2);
        } catch (DecoderQueryException e) {
            Log.e("MediaCodecUtil", "Codec warming failed", e);
        }
    }
}
