package com.amazon.avod.mpb.api.query;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.AudioStreamType;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.avod.mpb.media.VideoStreamType;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumerator;
import com.amazon.avod.mpb.media.playback.support.HdrFormat;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nMediaCodecQuerier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaCodecQuerier.kt\ncom/amazon/avod/mpb/api/query/MediaCodecQuerier\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,420:1\n774#2:421\n865#2,2:422\n1#3:424\n*S KotlinDebug\n*F\n+ 1 MediaCodecQuerier.kt\ncom/amazon/avod/mpb/api/query/MediaCodecQuerier\n*L\n83#1:421\n83#1:422,2\n*E\n"})
public final class MediaCodecQuerier {
    public static final long CACHE_TTL_MS = 2000;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String HDR_METADATA_TYPE_SMPTE_ST_2086 = "smpteSt2086";

    @NotNull
    public static final String HDR_METADATA_TYPE_SMPTE_ST_2094_10 = "smpteSt2094-10";

    @NotNull
    public static final String HDR_METADATA_TYPE_SMPTE_ST_2094_40 = "smpteSt2094-40";
    public static final int HD_MIN_HEIGHT = 720;
    public static final int HD_MIN_WIDTH = 1280;
    public static final float HFR_MIN_FRAME_RATE = 48.0f;

    @NotNull
    public static final String TAG = "MediaCodecQuerier";

    @NotNull
    public static final Map<String, HdrFormat> hdrFormats;

    @NotNull
    public final MediaCodecDeviceCapabilityDetector capabilityDetector;

    @NotNull
    public final MPBInternalConfig config;

    @NotNull
    public final ConcurrentHashMap<String, DecoderAvailabilityCacheEntry> decoderAvailabilityCache;

    @NotNull
    public final FailoverManager failoverManager;

    @NotNull
    public final ExternalFourCCMapper fourCCMapper;

    @NotNull
    public final Function0<Boolean> isPlaybackActive;

    @NotNull
    public final MediaCodecEnumerator mediaCodecEnumerator;

    @NotNull
    public final DevicePropertyProvider propertyProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DecoderAvailabilityCacheEntry {
        public final long cachedTimeMs;
        public final boolean isAvailable;

        public DecoderAvailabilityCacheEntry(boolean z, long j) {
            this.isAvailable = z;
            this.cachedTimeMs = j;
        }

        public static DecoderAvailabilityCacheEntry copy$default(DecoderAvailabilityCacheEntry decoderAvailabilityCacheEntry, boolean z, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                z = decoderAvailabilityCacheEntry.isAvailable;
            }
            if ((i & 2) != 0) {
                j = decoderAvailabilityCacheEntry.cachedTimeMs;
            }
            decoderAvailabilityCacheEntry.getClass();
            return new DecoderAvailabilityCacheEntry(z, j);
        }

        public final boolean component1() {
            return this.isAvailable;
        }

        public final long component2() {
            return this.cachedTimeMs;
        }

        @NotNull
        public final DecoderAvailabilityCacheEntry copy(boolean z, long j) {
            return new DecoderAvailabilityCacheEntry(z, j);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DecoderAvailabilityCacheEntry)) {
                return false;
            }
            DecoderAvailabilityCacheEntry decoderAvailabilityCacheEntry = (DecoderAvailabilityCacheEntry) obj;
            return this.isAvailable == decoderAvailabilityCacheEntry.isAvailable && this.cachedTimeMs == decoderAvailabilityCacheEntry.cachedTimeMs;
        }

        public final long getCachedTimeMs() {
            return this.cachedTimeMs;
        }

        public int hashCode() {
            return FloatFloatPair$$ExternalSyntheticBackport0.m(this.cachedTimeMs) + (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isAvailable) * 31);
        }

        public final boolean isAvailable() {
            return this.isAvailable;
        }

        @NotNull
        public String toString() {
            return "DecoderAvailabilityCacheEntry(isAvailable=" + this.isAvailable + ", cachedTimeMs=" + this.cachedTimeMs + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.MEDIA_VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaType.MEDIA_AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$pWlpOPsw8ON85cmhmceUTAV5tZI() {
        return false;
    }

    static {
        HdrFormat hdrFormat = HdrFormat.Hdr10;
        hdrFormats = MapsKt__MapsKt.mapOf(new Pair(HDR_METADATA_TYPE_SMPTE_ST_2086, hdrFormat), new Pair(HDR_METADATA_TYPE_SMPTE_ST_2094_40, hdrFormat), new Pair(HDR_METADATA_TYPE_SMPTE_ST_2094_10, HdrFormat.DolbyVision));
    }

    public MediaCodecQuerier(@NotNull MediaCodecEnumerator mediaCodecEnumerator, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @NotNull DevicePropertyProvider propertyProvider, @NotNull FailoverManager failoverManager, @NotNull ExternalFourCCMapper fourCCMapper, @NotNull MPBInternalConfig config, @NotNull Function0<Boolean> isPlaybackActive) {
        Intrinsics.checkNotNullParameter(mediaCodecEnumerator, "mediaCodecEnumerator");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        Intrinsics.checkNotNullParameter(propertyProvider, "propertyProvider");
        Intrinsics.checkNotNullParameter(failoverManager, "failoverManager");
        Intrinsics.checkNotNullParameter(fourCCMapper, "fourCCMapper");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(isPlaybackActive, "isPlaybackActive");
        this.mediaCodecEnumerator = mediaCodecEnumerator;
        this.capabilityDetector = capabilityDetector;
        this.propertyProvider = propertyProvider;
        this.failoverManager = failoverManager;
        this.fourCCMapper = fourCCMapper;
        this.config = config;
        this.isPlaybackActive = isPlaybackActive;
        this.decoderAvailabilityCache = new ConcurrentHashMap<>();
    }

    public static final boolean _init_$lambda$0() {
        return false;
    }

    public static final String executeAudioQuery$lambda$7(MediaCodecQuerier mediaCodecQuerier, CodecQuery codecQuery, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return mediaCodecQuerier.getFourCC(codecQuery.mimeType, it);
    }

    public static final String executeVideoQuery$lambda$4(MediaCodecQuerier mediaCodecQuerier, CodecQuery codecQuery, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return mediaCodecQuerier.getFourCC(codecQuery.mimeType, it);
    }

    public final boolean checkDecoderAvailable(MediaCodecInfo mediaCodecInfo, String str) {
        String name = mediaCodecInfo.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        StringBuilder sb = new StringBuilder("MediaCodecQuerier: Performing sanity test for ");
        sb.append(name);
        sb.append(" (");
        MpbLog.logf(ActivityCompat$$ExternalSyntheticOutline0.m(sb, str, ")"), new Object[0]);
        for (String str2 : CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{name, name.concat(".secure")})) {
            MediaCodec mediaCodecCreateByCodecName = null;
            try {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    mediaCodecCreateByCodecName = MediaCodec.createByCodecName(str2);
                    MpbLog.logf("MediaCodecQuerier: Sanity test PASSED for codec variant " + str2 + StringUtils.SPACE + name + StringUtils.SPACE + str + " in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms", new Object[0]);
                    if (mediaCodecCreateByCodecName != null) {
                        try {
                            mediaCodecCreateByCodecName.release();
                        } catch (Exception e) {
                            MpbLog.errorf("MediaCodecQuerier: Failed to release codec " + str2 + ": " + e, new Object[0]);
                        }
                    }
                } catch (Exception e2) {
                    MpbLog.warnf("MediaCodecQuerier: Sanity test FAILED for codec variant " + str2 + StringUtils.SPACE + name + StringUtils.SPACE + str + " due to " + e2 + " returning false", new Object[0]);
                    if (mediaCodecCreateByCodecName != null) {
                        try {
                            mediaCodecCreateByCodecName.release();
                        } catch (Exception e3) {
                            MpbLog.errorf("MediaCodecQuerier: Failed to release codec " + str2 + ": " + e3, new Object[0]);
                        }
                    }
                    return false;
                }
            } catch (Throwable th) {
                if (mediaCodecCreateByCodecName != null) {
                    try {
                        mediaCodecCreateByCodecName.release();
                    } catch (Exception e4) {
                        MpbLog.errorf("MediaCodecQuerier: Failed to release codec " + str2 + ": " + e4, new Object[0]);
                    }
                }
                throw th;
            }
        }
        return true;
    }

    public final CodecQueryResult executeAudioQuery(final CodecQuery codecQuery) {
        MediaCodecInfo supportedCodec;
        String str = (String) CodecQueryAttributesKt.convertRequiredAttribute(codecQuery, CodecQueryAttributeKey.CODECS, new Function1() { // from class: com.amazon.avod.mpb.api.query.MediaCodecQuerier$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MediaCodecQuerier.executeAudioQuery$lambda$7(this.f$0, codecQuery, (String) obj);
            }
        });
        Iterator<T> it = this.config.getMimeTypesByFourCC(str).iterator();
        while (true) {
            if (!it.hasNext()) {
                supportedCodec = null;
                break;
            }
            supportedCodec = this.mediaCodecEnumerator.getSupportedCodec(AudioCodecQueryAttributes.Factory.from((String) it.next(), codecQuery));
            if (supportedCodec != null) {
                break;
            }
        }
        boolean zIsDolbyFourCC = isDolbyFourCC(str);
        boolean z = !zIsDolbyFourCC || this.capabilityDetector.isDolbyOverBluetoothSupported();
        boolean z2 = supportedCodec != null && (!zIsDolbyFourCC || (this.propertyProvider.supportsSurroundSound() && isDolbyPassthroughAvailable()));
        boolean z3 = zIsDolbyFourCC && this.capabilityDetector.isDolbyPassthroughSupported();
        boolean z4 = z && (z2 || z3);
        CodecQueryResult codecQueryResult = new CodecQueryResult(z4, z4, z4 && (supportedCodec != null ? isSoftwareOnly(codecQuery, supportedCodec) ^ true : z3));
        StringBuilder sb = new StringBuilder();
        sb.append("MediaCodecQuerier: " + codecQuery + ", " + codecQueryResult + ", resolved codec: " + (supportedCodec != null ? supportedCodec.getName() : null));
        if (zIsDolbyFourCC) {
            sb.append(", supported via passthrough: " + z3);
            sb.append(", supported over bluetooth: " + z);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        MpbLog.devf(string, new Object[0]);
        return codecQueryResult;
    }

    @NotNull
    public final CodecQueryResult executeQuery(@NotNull CodecQuery codecQuery) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(codecQuery, "codecQuery");
        Set<CodecQueryAttributeKey> setKeySet = codecQuery.attributes.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (!((CodecQueryAttributeKey) obj).validMediaTypes.contains(codecQuery.mimeType)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            int i = WhenMappings.$EnumSwitchMapping$0[codecQuery.mimeType.ordinal()];
            if (i == 1) {
                return executeVideoQuery(codecQuery);
            }
            if (i == 2) {
                return executeAudioQuery(codecQuery);
            }
            throw new NoWhenBranchMatchedException();
        }
        String str = "MediaCodecQuerier: Invalid attribute keys: " + arrayList + " for " + codecQuery.mimeType;
        MpbLog.errorf(str, new Object[0]);
        throw new MediaPipelineBackendException(str, MediaPipelineBackendResultCode.AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_ATTRIBUTE_KEYS);
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x00a7 A[EDGE_INSN: B:90:0x00a7->B:28:0x00a7 BREAK  A[LOOP:0: B:3:0x0027->B:95:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[LOOP:0: B:3:0x0027->B:95:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.avod.mpb.api.query.CodecQueryResult executeVideoQuery(final com.amazon.avod.mpb.api.query.CodecQuery r13) {
        /*
            Method dump skipped, instruction units count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.api.query.MediaCodecQuerier.executeVideoQuery(com.amazon.avod.mpb.api.query.CodecQuery):com.amazon.avod.mpb.api.query.CodecQueryResult");
    }

    public final String getFourCC(MediaType mediaType, String str) {
        int i = WhenMappings.$EnumSwitchMapping$0[mediaType.ordinal()];
        String str2 = null;
        if (i == 1) {
            VideoStreamType videoStreamTypeMapVideoCodecToStreamType = this.fourCCMapper.mapVideoCodecToStreamType(str);
            if (videoStreamTypeMapVideoCodecToStreamType != null) {
                str2 = videoStreamTypeMapVideoCodecToStreamType.fourCC;
            }
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            AudioStreamType audioStreamTypeMapAudioCodecToStreamType = this.fourCCMapper.mapAudioCodecToStreamType(str);
            if (audioStreamTypeMapAudioCodecToStreamType != null) {
                str2 = audioStreamTypeMapAudioCodecToStreamType.fourCC;
            }
        }
        if (str2 != null) {
            return str2;
        }
        throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("MediaCodecQuerier: codecString: ", str, " unsupported"));
    }

    public final boolean isDecoderAvailabilityWorkAroundEnabled() {
        return this.propertyProvider.decoderBecomingInactiveWorkAround();
    }

    public final boolean isDecoderAvailable(String str, MediaCodecInfo mediaCodecInfo) {
        String strM = AbstractResolvableFuture$$ExternalSyntheticOutline1.m(mediaCodecInfo.getName(), ":", str);
        long jCurrentTimeMillis = System.currentTimeMillis();
        DecoderAvailabilityCacheEntry decoderAvailabilityCacheEntry = this.decoderAvailabilityCache.get(strM);
        if (decoderAvailabilityCacheEntry != null) {
            long j = jCurrentTimeMillis - decoderAvailabilityCacheEntry.cachedTimeMs;
            if (this.isPlaybackActive.invoke().booleanValue()) {
                String name = mediaCodecInfo.getName();
                boolean z = decoderAvailabilityCacheEntry.isAvailable;
                StringBuilder sb = new StringBuilder("MediaCodecQuerier: Playback active, using cached result for ");
                sb.append(name);
                sb.append(": ");
                sb.append(z);
                sb.append(" (age: ");
                MpbLog.logf(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, j, "ms)"), new Object[0]);
                return decoderAvailabilityCacheEntry.isAvailable;
            }
            if (j < 2000) {
                String name2 = mediaCodecInfo.getName();
                boolean z2 = decoderAvailabilityCacheEntry.isAvailable;
                StringBuilder sb2 = new StringBuilder("MediaCodecQuerier: Using cached result for ");
                sb2.append(name2);
                sb2.append(": ");
                sb2.append(z2);
                sb2.append(" (age: ");
                MpbLog.logf(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb2, j, "ms)"), new Object[0]);
                return decoderAvailabilityCacheEntry.isAvailable;
            }
            MpbLog.logf("MediaCodecQuerier: Cache expired for " + mediaCodecInfo.getName() + " (age: " + j + "ms), performing fresh check", new Object[0]);
        }
        MpbLog.logf(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("MediaCodecQuerier: No cached result for ", mediaCodecInfo.getName(), ", performing sanity check"), new Object[0]);
        boolean zCheckDecoderAvailable = checkDecoderAvailable(mediaCodecInfo, str);
        this.decoderAvailabilityCache.put(strM, new DecoderAvailabilityCacheEntry(zCheckDecoderAvailable, jCurrentTimeMillis));
        return zCheckDecoderAvailable;
    }

    public final boolean isDolbyFourCC(String str) {
        return StringsKt__StringsJVMKt.equals(str, DefaultMPBInternalConfig.DDP_FOUR_CC, true) || StringsKt__StringsJVMKt.equals(str, DefaultMPBInternalConfig.ATMO_FOUR_CC, true);
    }

    public final boolean isDolbyPassthroughAvailable() {
        return Build.VERSION.SDK_INT >= 23 ? this.propertyProvider.isOpticalOutputEnabled() || this.propertyProvider.isDolbyHdmiPassthroughAvailable() : this.propertyProvider.isDolbyHdmiPassthroughAvailable();
    }

    public final boolean isHdOrAboveQuery(CodecQuery codecQuery) {
        Integer intOrNull;
        Integer intOrNull2;
        String str = codecQuery.attributes.get(CodecQueryAttributeKey.WIDTH);
        int iIntValue = (str == null || (intOrNull2 = StringsKt__StringNumberConversionsKt.toIntOrNull(str)) == null) ? 0 : intOrNull2.intValue();
        String str2 = codecQuery.attributes.get(CodecQueryAttributeKey.HEIGHT);
        return iIntValue >= 1280 || ((str2 == null || (intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(str2)) == null) ? 0 : intOrNull.intValue()) >= 720;
    }

    public final boolean isHfrQuery(CodecQuery codecQuery) {
        Float floatOrNull;
        String str = codecQuery.attributes.get(CodecQueryAttributeKey.FRAMERATE);
        return ((str == null || (floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(str)) == null) ? 0.0f : floatOrNull.floatValue()) >= 48.0f;
    }

    public final boolean isSoftwareOnly(CodecQuery codecQuery, MediaCodecInfo mediaCodecInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            return mediaCodecInfo.isSoftwareOnly();
        }
        if (codecQuery.mimeType == MediaType.MEDIA_AUDIO) {
            return true;
        }
        String name = mediaCodecInfo.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (StringsKt__StringsJVMKt.startsWith$default(lowerCase, "arc.", false, 2, null)) {
            return false;
        }
        return StringsKt__StringsJVMKt.startsWith$default(lowerCase, "omx.google.", false, 2, null) || StringsKt__StringsJVMKt.startsWith$default(lowerCase, "omx.ffmpeg.", false, 2, null) || (StringsKt__StringsJVMKt.startsWith$default(lowerCase, "omx.sec.", false, 2, null) && StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) ".sw.", false, 2, (Object) null)) || lowerCase.equals("omx.qcom.video.decoder.hevcswvdec") || StringsKt__StringsJVMKt.startsWith$default(lowerCase, "c2.android.", false, 2, null) || StringsKt__StringsJVMKt.startsWith$default(lowerCase, "c2.google.", false, 2, null) || !(StringsKt__StringsJVMKt.startsWith$default(lowerCase, "omx.", false, 2, null) || StringsKt__StringsJVMKt.startsWith$default(lowerCase, "c2.", false, 2, null));
    }

    public /* synthetic */ MediaCodecQuerier(MediaCodecEnumerator mediaCodecEnumerator, MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, DevicePropertyProvider devicePropertyProvider, FailoverManager failoverManager, ExternalFourCCMapper externalFourCCMapper, MPBInternalConfig mPBInternalConfig, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mediaCodecEnumerator, mediaCodecDeviceCapabilityDetector, devicePropertyProvider, failoverManager, (i & 16) != 0 ? ExternalFourCCMapper.INSTANCE : externalFourCCMapper, (i & 32) != 0 ? DefaultMPBInternalConfig.INSTANCE : mPBInternalConfig, (i & 64) != 0 ? new MediaCodecQuerier$$ExternalSyntheticLambda2() : function0);
    }
}
