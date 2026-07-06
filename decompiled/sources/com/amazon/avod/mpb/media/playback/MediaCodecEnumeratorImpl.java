package com.amazon.avod.mpb.media.playback;

import android.annotation.SuppressLint;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import com.amazon.avod.mpb.api.query.AudioCodecQueryAttributes;
import com.amazon.avod.mpb.api.query.VideoCodecQueryAttributes;
import com.amazon.avod.mpb.util.Preconditions2;
import com.google.android.gms.common.Scopes;
import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nMediaCodecEnumerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaCodecEnumerator.kt\ncom/amazon/avod/mpb/media/playback/MediaCodecEnumeratorImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,331:1\n1#2:332\n1310#3:333\n12637#3,2:334\n1311#3:336\n12637#3,2:337\n*S KotlinDebug\n*F\n+ 1 MediaCodecEnumerator.kt\ncom/amazon/avod/mpb/media/playback/MediaCodecEnumeratorImpl\n*L\n125#1:333\n127#1:334,2\n125#1:336\n91#1:337,2\n*E\n"})
public final class MediaCodecEnumeratorImpl implements MediaCodecEnumerator {

    @NotNull
    public static final String COLOR_GAMUT_REC_2020 = "rec2020";
    public static final int COLOR_GAMUT_REC_2020_VAL = 6;

    @NotNull
    public static final String COLOR_GAMUT_SRGB = "srgb";
    public static final int COLOR_GAMUT_SRGB_VAL = 1;

    @NotNull
    public static final String TRANSFER_FUNCTION_HLG = "hlg";
    public static final int TRANSFER_FUNCTION_HLG_VAL = 7;

    @NotNull
    public static final String TRANSFER_FUNCTION_PQ = "pq";
    public static final int TRANSFER_FUNCTION_PQ_VAL = 6;

    @NotNull
    public static final String TRANSFER_FUNCTION_SRGB = "srgb";
    public static final int TRANSFER_FUNCTION_SRGB_VAL = 3;

    @NotNull
    public final Function3<String, MediaCodecInfo, MediaFormat, Boolean> mediaFormatSupportQuery;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Lazy<Map<String, Integer>> transferFunctionValues$delegate = LazyKt__LazyJVMKt.lazy(new MediaCodecEnumeratorImpl$$ExternalSyntheticLambda3());

    @NotNull
    public static final Lazy<Map<String, Integer>> colorGamutValues$delegate = LazyKt__LazyJVMKt.lazy(new MediaCodecEnumeratorImpl$$ExternalSyntheticLambda4());

    /* JADX INFO: renamed from: com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<String, MediaCodecInfo, MediaFormat, Boolean> {
        public AnonymousClass1(Object obj) {
            super(3, obj, Companion.class, "queryMediaFormatSupport", "queryMediaFormatSupport(Ljava/lang/String;Landroid/media/MediaCodecInfo;Landroid/media/MediaFormat;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Boolean invoke(String p0, MediaCodecInfo p1, MediaFormat p2) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            Intrinsics.checkNotNullParameter(p2, "p2");
            return Boolean.valueOf(((Companion) this.receiver).queryMediaFormatSupport(p0, p1, p2));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SuppressLint({"NewApi"})
    @SourceDebugExtension({"SMAP\nMediaCodecEnumerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaCodecEnumerator.kt\ncom/amazon/avod/mpb/media/playback/MediaCodecEnumeratorImpl$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,331:1\n12637#2,2:332\n*S KotlinDebug\n*F\n+ 1 MediaCodecEnumerator.kt\ncom/amazon/avod/mpb/media/playback/MediaCodecEnumeratorImpl$Companion\n*L\n262#1:332,2\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        public final int ceilDivide(int i, int i2) {
            return ((i + i2) - 1) / i2;
        }

        public final Map<String, Integer> getColorGamutValues() {
            return (Map) MediaCodecEnumeratorImpl.colorGamutValues$delegate.getValue();
        }

        @NotNull
        public final MediaCodecEnumeratorImpl getInstance() {
            SingletonHolder.INSTANCE.getClass();
            return SingletonHolder.instance;
        }

        public final Float getOptionalFloat(MediaFormat mediaFormat, String str) {
            if (mediaFormat.containsKey(str)) {
                return Float.valueOf(mediaFormat.getFloat(str));
            }
            return null;
        }

        public final Integer getOptionalInteger(MediaFormat mediaFormat, String str) {
            if (mediaFormat.containsKey(str)) {
                return Integer.valueOf(mediaFormat.getInteger(str));
            }
            return null;
        }

        public final Map<String, Integer> getTransferFunctionValues() {
            return (Map) MediaCodecEnumeratorImpl.transferFunctionValues$delegate.getValue();
        }

        public final boolean isAlignedSizeAndRateSupported(int i, int i2, double d, MediaCodecInfo.VideoCapabilities videoCapabilities) {
            return videoCapabilities.areSizeAndRateSupported(videoCapabilities.getWidthAlignment() * ceilDivide(i, videoCapabilities.getWidthAlignment()), videoCapabilities.getHeightAlignment() * ceilDivide(i2, videoCapabilities.getHeightAlignment()), Math.floor(d));
        }

        public final boolean isProfileAndLevelSupported(MediaFormat mediaFormat, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            Integer optionalInteger = getOptionalInteger(mediaFormat, Scopes.PROFILE);
            Integer optionalInteger2 = getOptionalInteger(mediaFormat, "level");
            if (optionalInteger != null && optionalInteger2 != null) {
                MediaCodecInfo.CodecProfileLevel[] profileLevels = codecCapabilities.profileLevels;
                Intrinsics.checkNotNullExpressionValue(profileLevels, "profileLevels");
                for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : profileLevels) {
                    if (codecProfileLevel.profile == optionalInteger.intValue() && codecProfileLevel.level >= optionalInteger2.intValue()) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final boolean isVideoFormatSupportedInternal(MediaCodecInfo.CodecCapabilities codecCapabilities, MediaFormat mediaFormat) {
            if (!isProfileAndLevelSupported(mediaFormat, codecCapabilities)) {
                return false;
            }
            Integer optionalInteger = getOptionalInteger(mediaFormat, "width");
            Integer optionalInteger2 = getOptionalInteger(mediaFormat, "height");
            Float optionalFloat = getOptionalFloat(mediaFormat, "frame-rate");
            if (optionalInteger == null || optionalInteger2 == null || optionalFloat == null) {
                return false;
            }
            return isVideoSizeAndRateSupported(optionalInteger.intValue(), optionalInteger2.intValue(), optionalFloat.floatValue(), codecCapabilities);
        }

        public final boolean isVideoSizeAndRateSupported(int i, int i2, double d, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
            if (videoCapabilities == null) {
                return false;
            }
            return isAlignedSizeAndRateSupported(i, i2, d, videoCapabilities);
        }

        public final boolean queryMediaFormatSupport(String str, MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat) {
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
            if (!MimeTypes.isVideo(str)) {
                return capabilitiesForType.isFormatSupported(mediaFormat);
            }
            Intrinsics.checkNotNull(capabilitiesForType);
            return isVideoFormatSupportedInternal(capabilitiesForType, mediaFormat);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SingletonHolder {

        @NotNull
        public static final SingletonHolder INSTANCE = new SingletonHolder();

        @NotNull
        public static volatile MediaCodecEnumeratorImpl instance = new MediaCodecEnumeratorImpl(null, 1, 0 == true ? 1 : 0);

        @NotNull
        public final MediaCodecEnumeratorImpl getInstance() {
            return instance;
        }

        public final void setInstance(@NotNull MediaCodecEnumeratorImpl mediaCodecEnumeratorImpl) {
            Intrinsics.checkNotNullParameter(mediaCodecEnumeratorImpl, "<set-?>");
            instance = mediaCodecEnumeratorImpl;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$B-CyLNlOvtcFAJJKCAaXN8RCWRE, reason: not valid java name */
    public static /* synthetic */ boolean m197$r8$lambda$BCyLNlOvtcFAJJKCAaXN8RCWRE(MediaCodecInfo mediaCodecInfo) {
        getSupportedCodecInternal$lambda$4(mediaCodecInfo);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    public MediaCodecEnumeratorImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static final Map colorGamutValues_delegate$lambda$18() {
        return MapsKt__MapsKt.mapOf(new Pair("srgb", 1), new Pair(COLOR_GAMUT_REC_2020, 6));
    }

    public static final boolean getDecoderInfoForMimeAndProfile$lambda$1(String str, int i, MediaCodecInfo codecInfo) {
        Intrinsics.checkNotNullParameter(codecInfo, "codecInfo");
        MediaCodecInfo.CodecProfileLevel[] profileLevels = codecInfo.getCapabilitiesForType(str).profileLevels;
        Intrinsics.checkNotNullExpressionValue(profileLevels, "profileLevels");
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : profileLevels) {
            if (codecProfileLevel.profile == i) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final MediaCodecEnumeratorImpl getInstance() {
        return Companion.getInstance();
    }

    public static final boolean getSupportedCodec$lambda$3(MediaCodecEnumeratorImpl mediaCodecEnumeratorImpl, String str, MediaFormat mediaFormat, MediaCodecInfo codecInfo) {
        Intrinsics.checkNotNullParameter(codecInfo, "codecInfo");
        return mediaCodecEnumeratorImpl.mediaFormatSupportQuery.invoke(str, codecInfo, mediaFormat).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MediaCodecInfo getSupportedCodecInternal$default(MediaCodecEnumeratorImpl mediaCodecEnumeratorImpl, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new MediaCodecEnumeratorImpl$$ExternalSyntheticLambda2();
        }
        return mediaCodecEnumeratorImpl.getSupportedCodecInternal(str, function1);
    }

    public static final boolean getSupportedCodecInternal$lambda$4(MediaCodecInfo it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return true;
    }

    public static final Map transferFunctionValues_delegate$lambda$17() {
        return MapsKt__MapsKt.mapOf(new Pair("srgb", 3), new Pair(TRANSFER_FUNCTION_PQ, 6), new Pair(TRANSFER_FUNCTION_HLG, 7));
    }

    @SuppressLint({"NewApi"})
    public final MediaFormat createAudioFormat(AudioCodecQueryAttributes audioCodecQueryAttributes) {
        Integer num;
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(audioCodecQueryAttributes.mimeType, audioCodecQueryAttributes.samplerate, audioCodecQueryAttributes.channels);
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateAudioFormat, "createAudioFormat(...)");
        Integer num2 = audioCodecQueryAttributes.bitrate;
        if (num2 != null) {
            mediaFormatCreateAudioFormat.setInteger("bitrate", num2.intValue());
        }
        Integer num3 = audioCodecQueryAttributes.profile;
        if (num3 != null) {
            mediaFormatCreateAudioFormat.setInteger(Scopes.PROFILE, num3.intValue());
        }
        if (Preconditions2.isSdkIntAtLeast(23) && (num = audioCodecQueryAttributes.level) != null) {
            mediaFormatCreateAudioFormat.setInteger("level", num.intValue());
        }
        return mediaFormatCreateAudioFormat;
    }

    @SuppressLint({"NewApi"})
    public final MediaFormat createVideoFormat(VideoCodecQueryAttributes videoCodecQueryAttributes) {
        Integer num;
        Integer num2;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(videoCodecQueryAttributes.mimeType, videoCodecQueryAttributes.width, videoCodecQueryAttributes.height);
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateVideoFormat, "createVideoFormat(...)");
        Integer num3 = videoCodecQueryAttributes.bitrate;
        if (num3 != null) {
            mediaFormatCreateVideoFormat.setInteger("bitrate", num3.intValue());
        }
        Float f = videoCodecQueryAttributes.framerate;
        if (f != null) {
            mediaFormatCreateVideoFormat.setFloat("frame-rate", f.floatValue());
        }
        Integer num4 = videoCodecQueryAttributes.profile;
        if (num4 != null) {
            mediaFormatCreateVideoFormat.setInteger(Scopes.PROFILE, num4.intValue());
        }
        if (Preconditions2.isSdkIntAtLeast(23) && (num2 = videoCodecQueryAttributes.level) != null) {
            mediaFormatCreateVideoFormat.setInteger("level", num2.intValue());
        }
        Companion companion = Companion;
        Integer num5 = companion.getColorGamutValues().get(videoCodecQueryAttributes.colorGamut);
        if (num5 != null) {
            mediaFormatCreateVideoFormat.setInteger("color-format", num5.intValue());
        }
        if (Preconditions2.isSdkIntAtLeast(24) && (num = companion.getTransferFunctionValues().get(videoCodecQueryAttributes.transferFunction)) != null) {
            mediaFormatCreateVideoFormat.setInteger("color-transfer", num.intValue());
        }
        return mediaFormatCreateVideoFormat;
    }

    @Override // com.amazon.avod.mpb.media.playback.MediaCodecEnumerator
    @Nullable
    public MediaCodecInfo getDecoderInfoForMimeAndProfile(@NotNull final String mimeType, final int i) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return getSupportedCodecInternal(mimeType, new Function1() { // from class: com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MediaCodecEnumeratorImpl.getDecoderInfoForMimeAndProfile$lambda$1(mimeType, i, (MediaCodecInfo) obj));
            }
        });
    }

    @Override // com.amazon.avod.mpb.media.playback.MediaCodecEnumerator
    @Nullable
    public MediaCodecInfo getSupportedCodec(@NotNull VideoCodecQueryAttributes attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return getSupportedCodec(attributes.mimeType, createVideoFormat(attributes));
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x004b, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.media.MediaCodecInfo getSupportedCodecInternal(java.lang.String r12, kotlin.jvm.functions.Function1<? super android.media.MediaCodecInfo, java.lang.Boolean> r13) {
        /*
            r11 = this;
            android.media.MediaCodecList r0 = new android.media.MediaCodecList
            r1 = 1
            r0.<init>(r1)
            android.media.MediaCodecInfo[] r0 = r0.getCodecInfos()
            r2 = 0
            if (r0 == 0) goto L4e
            int r3 = r0.length
            r4 = 0
            if (r3 != 0) goto L13
            r3 = 1
            goto L14
        L13:
            r3 = 0
        L14:
            if (r3 != 0) goto L17
            goto L18
        L17:
            r0 = r2
        L18:
            if (r0 == 0) goto L4e
            int r3 = r0.length
            r5 = 0
        L1c:
            if (r5 >= r3) goto L4e
            r6 = r0[r5]
            boolean r7 = r6.isEncoder()
            if (r7 != 0) goto L4b
            java.lang.String[] r7 = r6.getSupportedTypes()
            java.lang.String r8 = "getSupportedTypes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            int r8 = r7.length
            r9 = 0
        L31:
            if (r9 >= r8) goto L4b
            r10 = r7[r9]
            boolean r10 = kotlin.text.StringsKt__StringsJVMKt.equals(r10, r12, r1)
            if (r10 == 0) goto L48
            java.lang.Object r7 = r13.invoke(r6)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L4b
            return r6
        L48:
            int r9 = r9 + 1
            goto L31
        L4b:
            int r5 = r5 + 1
            goto L1c
        L4e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl.getSupportedCodecInternal(java.lang.String, kotlin.jvm.functions.Function1):android.media.MediaCodecInfo");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    public MediaCodecEnumeratorImpl(@NotNull Function3<? super String, ? super MediaCodecInfo, ? super MediaFormat, Boolean> mediaFormatSupportQuery) {
        Intrinsics.checkNotNullParameter(mediaFormatSupportQuery, "mediaFormatSupportQuery");
        this.mediaFormatSupportQuery = mediaFormatSupportQuery;
    }

    @Override // com.amazon.avod.mpb.media.playback.MediaCodecEnumerator
    @Nullable
    public MediaCodecInfo getSupportedCodec(@NotNull AudioCodecQueryAttributes attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return getSupportedCodec(attributes.mimeType, createAudioFormat(attributes));
    }

    public /* synthetic */ MediaCodecEnumeratorImpl(Function3 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new AnonymousClass1(Companion) : function3);
    }

    @Override // com.amazon.avod.mpb.media.playback.MediaCodecEnumerator
    @Nullable
    public MediaCodecInfo getSupportedCodec(@NotNull String mimeType) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return getSupportedCodecInternal$default(this, mimeType, null, 2, null);
    }

    @Override // com.amazon.avod.mpb.media.playback.MediaCodecEnumerator
    @Nullable
    public Pair<String, MediaCodecInfo> getSupportedCodec(@NotNull List<String> mimeTypes) {
        Intrinsics.checkNotNullParameter(mimeTypes, "mimeTypes");
        for (String str : mimeTypes) {
            MediaCodecInfo supportedCodec = getSupportedCodec(str);
            if (supportedCodec != null) {
                return new Pair<>(str, supportedCodec);
            }
        }
        return null;
    }

    public final MediaCodecInfo getSupportedCodec(final String str, final MediaFormat mediaFormat) {
        return getSupportedCodecInternal(str, new Function1() { // from class: com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MediaCodecEnumeratorImpl.getSupportedCodec$lambda$3(this.f$0, str, mediaFormat, (MediaCodecInfo) obj));
            }
        });
    }
}
