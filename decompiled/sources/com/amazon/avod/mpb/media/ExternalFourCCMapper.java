package com.amazon.avod.mpb.media;

import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nExternalFourCCMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExternalFourCCMapper.kt\ncom/amazon/avod/mpb/media/ExternalFourCCMapper\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,37:1\n1#2:38\n*E\n"})
public final class ExternalFourCCMapper {

    @NotNull
    public static final String CODEC_NAME_SPLITTER = ".";
    public static final int VIDEO_CODEC_NAME_INDEX = 0;

    @NotNull
    public static final ExternalFourCCMapper INSTANCE = new ExternalFourCCMapper();

    @NotNull
    public static final Map<String, String> videoFourCCMap = MapsKt__MapsKt.mapOf(new Pair("avc1", "H264"), new Pair("h264", "H264"), new Pair("hev1", "H265"), new Pair("hvc1", "H265"), new Pair(DefaultMPBInternalConfig.DOLBY_VISION_FOUR_CC, "DVHE"), new Pair("av01", "AV01"));

    @NotNull
    public static final Map<String, String> audioFourCCMap = MapsKt__MapsKt.mapOf(new Pair("mp4a.40.2", "AACL"), new Pair("mp4a.40.5", "AACH"), new Pair("mp4a.40.29", "AACHV2"), new Pair(DefaultMPBInternalConfig.DD_FOUR_CC, "DD"), new Pair(DefaultMPBInternalConfig.DDP_FOUR_CC, "DDP"));

    @NotNull
    public final Map<String, String> getAudioFourCCMap() {
        return audioFourCCMap;
    }

    @NotNull
    public final Map<String, String> getVideoFourCCMap() {
        return videoFourCCMap;
    }

    @Nullable
    public final AudioStreamType mapAudioCodecToStreamType(@NotNull String codecName) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = codecName.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String str = audioFourCCMap.get(lowerCase);
        if (str != null) {
            return AudioStreamType.valueOf(str);
        }
        return null;
    }

    @Nullable
    public final VideoStreamType mapVideoCodecToStreamType(@NotNull String codecName) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        String str = (String) CollectionsKt___CollectionsKt.getOrNull(StringsKt__StringsKt.split$default((CharSequence) codecName, new String[]{CODEC_NAME_SPLITTER}, false, 0, 6, (Object) null), 0);
        if (str != null) {
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = str.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String str2 = videoFourCCMap.get(lowerCase);
            if (str2 != null) {
                return VideoStreamType.valueOf(str2);
            }
        }
        return null;
    }
}
