package com.amazon.avod.mpb.media.playback.util;

import android.annotation.SuppressLint;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.util.ColorParser$$ExternalSyntheticOutline0;
import androidx.media3.common.util.ColorParser$$ExternalSyntheticOutline1;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.android.billingclient.api.ProxyBillingActivity;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Pair;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.MatchResult;
import kotlin.text.MatcherMatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CodecString {

    @NotNull
    public static final HashMap<String, Integer> AV1_BIT_DEPTH_TO_PROFILE;

    @NotNull
    public static final HashMap<Integer, Integer> AV1_LEVEL_NUMBER_TO_CONST;

    @NotNull
    public static final Regex AV1_LEVEL_TIER_PATTERN;

    @NotNull
    public static final HashMap<Integer, Integer> AVC_LEVEL_NUMBER_TO_CONST;

    @NotNull
    public static final HashMap<Integer, Integer> AVC_PROFILE_NUMBER_TO_CONST;

    @NotNull
    public static final HashMap<String, Integer> DOLBY_VISION_STRING_TO_LEVEL;

    @NotNull
    public static final HashMap<String, Integer> DOLBY_VISION_STRING_TO_PROFILE;

    @NotNull
    public static final HashMap<String, Integer> HEVC_CODEC_STRING_TO_PROFILE_LEVEL;

    @NotNull
    public final String codec;

    @NotNull
    public static final Companion Companion = new Companion();
    public static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Codec {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Codec[] $VALUES;

        @NotNull
        public static final Companion Companion;

        @NotNull
        public final String codec;
        public static final Codec AVC1 = new Codec("AVC1", 0, "avc1");
        public static final Codec AVC2 = new Codec("AVC2", 1, "avc2");
        public static final Codec HEV1 = new Codec("HEV1", 2, "hev1");
        public static final Codec HVC1 = new Codec("HVC1", 3, "hvc1");
        public static final Codec DVHE = new Codec("DVHE", 4, DefaultMPBInternalConfig.DOLBY_VISION_FOUR_CC);
        public static final Codec DVH1 = new Codec("DVH1", 5, "dvh1");
        public static final Codec DOVI_P9 = new Codec("DOVI_P9", 6, "dvav");
        public static final Codec AV01 = new Codec("AV01", 7, "av01");

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @SourceDebugExtension({"SMAP\nCodecString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CodecString.kt\ncom/amazon/avod/mpb/media/playback/util/CodecString$Codec$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,368:1\n13472#2,2:369\n*S KotlinDebug\n*F\n+ 1 CodecString.kt\ncom/amazon/avod/mpb/media/playback/util/CodecString$Codec$Companion\n*L\n40#1:369,2\n*E\n"})
        public static final class Companion {
            public Companion() {
            }

            @Nullable
            public final Codec fromString(@NotNull String codecString) {
                Intrinsics.checkNotNullParameter(codecString, "codecString");
                for (Codec codec : Codec.values()) {
                    if (StringsKt__StringsJVMKt.equals(codec.codec, codecString, true)) {
                        return codec;
                    }
                }
                return null;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public static final /* synthetic */ Codec[] $values() {
            return new Codec[]{AVC1, AVC2, HEV1, HVC1, DVHE, DVH1, DOVI_P9, AV01};
        }

        static {
            Codec[] codecArr$values = $values();
            $VALUES = codecArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(codecArr$values);
            Companion = new Companion();
        }

        public Codec(String str, int i, String str2) {
            this.codec = str2;
        }

        @NotNull
        public static EnumEntries<Codec> getEntries() {
            return $ENTRIES;
        }

        public static Codec valueOf(String str) {
            return (Codec) Enum.valueOf(Codec.class, str);
        }

        public static Codec[] values() {
            return (Codec[]) $VALUES.clone();
        }

        @NotNull
        public final String getCodec() {
            return this.codec;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SuppressLint({"InlinedApi"})
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Codec.values().length];
            try {
                iArr[Codec.AVC1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Codec.AVC2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Codec.HEV1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Codec.HVC1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Codec.DVHE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Codec.DVH1.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Codec.DOVI_P9.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Codec.AV01.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        HashMap<Integer, Integer> map = new HashMap<>();
        AVC_PROFILE_NUMBER_TO_CONST = map;
        HashMap<Integer, Integer> map2 = new HashMap<>();
        AVC_LEVEL_NUMBER_TO_CONST = map2;
        HashMap<String, Integer> map3 = new HashMap<>();
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL = map3;
        HashMap<String, Integer> map4 = new HashMap<>();
        DOLBY_VISION_STRING_TO_PROFILE = map4;
        HashMap<String, Integer> map5 = new HashMap<>();
        DOLBY_VISION_STRING_TO_LEVEL = map5;
        HashMap<String, Integer> map6 = new HashMap<>();
        AV1_BIT_DEPTH_TO_PROFILE = map6;
        HashMap<Integer, Integer> map7 = new HashMap<>();
        AV1_LEVEL_NUMBER_TO_CONST = map7;
        AV1_LEVEL_TIER_PATTERN = new Regex("^(\\d{1,2})([MH])$");
        map.put(66, 1);
        map.put(77, 2);
        map.put(88, 4);
        map.put(100, 8);
        map.put(Integer.valueOf(ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW), 16);
        map.put(122, 32);
        map.put(244, 64);
        map2.put(10, 1);
        map2.put(11, 4);
        map2.put(12, 8);
        map2.put(13, 16);
        map2.put(20, 32);
        map2.put(21, 64);
        map2.put(22, 128);
        map2.put(30, 256);
        map2.put(31, 512);
        map2.put(32, 1024);
        map2.put(40, 2048);
        map2.put(41, 4096);
        map2.put(42, 8192);
        map2.put(50, 16384);
        map2.put(51, 32768);
        map2.put(52, 65536);
        map3.put("L30", 1);
        map3.put("L60", 4);
        map3.put("L63", 16);
        map3.put("L90", 64);
        map3.put("L93", 256);
        map3.put("L120", 1024);
        map3.put("L123", 4096);
        ColorParser$$ExternalSyntheticOutline1.m(map3, "L150", 16384, 65536, "L153");
        ColorParser$$ExternalSyntheticOutline0.m(262144, map3, "L156", 1048576, "L180");
        ColorParser$$ExternalSyntheticOutline0.m(4194304, map3, "L183", 16777216, "L186");
        map3.put("H30", 2);
        map3.put("H60", 8);
        map3.put("H63", 32);
        map3.put("H90", 128);
        map3.put("H93", 512);
        map3.put("H120", 2048);
        ColorParser$$ExternalSyntheticOutline1.m(map3, "H123", 8192, 32768, "H150");
        ColorParser$$ExternalSyntheticOutline0.m(131072, map3, "H153", 524288, "H156");
        ColorParser$$ExternalSyntheticOutline0.m(2097152, map3, "H180", 8388608, "H183");
        map3.put("H186", 33554432);
        map4.put(TarConstants.VERSION_POSIX, 1);
        map4.put("01", 2);
        map4.put("02", 4);
        map4.put("03", 8);
        map4.put("04", 16);
        map4.put("05", 32);
        map4.put("06", 64);
        map4.put("07", 128);
        map4.put("08", 256);
        map4.put("09", 512);
        map5.put("01", 1);
        map5.put("02", 2);
        map5.put("03", 4);
        map5.put("04", 8);
        map5.put("05", 16);
        map5.put("06", 32);
        map5.put("07", 64);
        map5.put("08", 128);
        map5.put("09", 256);
        map4.put("10", 1024);
        map6.put("08", 1);
        map6.put("10", 2);
        map7.put(0, 1);
        map7.put(1, 2);
        map7.put(2, 4);
        map7.put(3, 8);
        map7.put(4, 16);
        map7.put(5, 32);
        map7.put(6, 64);
        map7.put(7, 128);
        map7.put(8, 256);
        map7.put(9, 512);
        map7.put(10, 1024);
        map7.put(11, 2048);
        map7.put(12, 4096);
        map7.put(13, 8192);
        map7.put(14, 16384);
        map7.put(15, 32768);
        map7.put(16, 65536);
        map7.put(17, 131072);
        map7.put(18, 262144);
        map7.put(19, 524288);
        map7.put(20, 1048576);
        map7.put(21, 2097152);
        map7.put(22, 4194304);
        map7.put(23, 8388608);
    }

    public CodecString(@NotNull String codec) {
        Intrinsics.checkNotNullParameter(codec, "codec");
        this.codec = codec;
    }

    public static /* synthetic */ CodecString copy$default(CodecString codecString, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = codecString.codec;
        }
        return codecString.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.codec;
    }

    @NotNull
    public final CodecString copy(@NotNull String codec) {
        Intrinsics.checkNotNullParameter(codec, "codec");
        return new CodecString(codec);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CodecString) && Intrinsics.areEqual(this.codec, ((CodecString) obj).codec);
    }

    public final Pair<Integer, Integer> getAv1ProfileAndLevel() {
        String[] parts = CodecStringKt.getParts(this.codec);
        if (parts.length < 4) {
            MpbLog.warnf("Ignoring malformed AV1 codec string (need 4 parts): %s", this.codec);
            return null;
        }
        String str = parts[1];
        if (!Intrinsics.areEqual(str, "0")) {
            MpbLog.warnf("Unsupported AV1 profile: %s (codec=%s)", str, this.codec);
            return null;
        }
        MatchResult matchResultMatchEntire = AV1_LEVEL_TIER_PATTERN.matchEntire(parts[2]);
        if (matchResultMatchEntire == null) {
            MpbLog.warnf("Ignoring malformed AV1 level/tier string: %s", parts[2]);
            return null;
        }
        int i = Integer.parseInt(((MatcherMatchResult) matchResultMatchEntire).getGroupValues().get(1));
        String str2 = parts[3];
        Integer num = AV1_BIT_DEPTH_TO_PROFILE.get(str2);
        if (num == null) {
            MpbLog.warnf("Unknown AV1 bitDepth: %s (codec=%s)", str2, this.codec);
            return null;
        }
        Integer num2 = AV1_LEVEL_NUMBER_TO_CONST.get(Integer.valueOf(i));
        if (num2 != null) {
            return new Pair<>(num, num2);
        }
        MpbLog.warnf("Unknown AV1 level_idx: %s (codec=%s)", Integer.valueOf(i), this.codec);
        return null;
    }

    public final Pair<Integer, Integer> getAvcProfileAndLevel() {
        int i;
        int i2;
        String[] parts = CodecStringKt.getParts(this.codec);
        if (parts.length < 2) {
            MpbLog.warnf("Ignoring malformed AVC codec string: %s", this.codec);
            return null;
        }
        try {
            if (parts[1].length() == 6) {
                String strSubstring = parts[1].substring(0, 2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                CharsKt__CharJVMKt.checkRadix(16);
                i = Integer.parseInt(strSubstring, 16);
                String strSubstring2 = parts[1].substring(4);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                CharsKt__CharJVMKt.checkRadix(16);
                i2 = Integer.parseInt(strSubstring2, 16);
            } else {
                if (parts.length < 3) {
                    MpbLog.warnf("Ignoring malformed AVC codec string: %s", this.codec);
                    return null;
                }
                i = Integer.parseInt(parts[1]);
                i2 = Integer.parseInt(parts[2]);
            }
            Integer num = AVC_PROFILE_NUMBER_TO_CONST.get(Integer.valueOf(i));
            if (num == null) {
                MpbLog.warnf("Unknown AVC profile: %s", Integer.valueOf(i));
                return null;
            }
            Integer num2 = AVC_LEVEL_NUMBER_TO_CONST.get(Integer.valueOf(i2));
            if (num2 != null) {
                return new Pair<>(num, num2);
            }
            MpbLog.warnf("Unknown AVC level: %s", Integer.valueOf(i2));
            return null;
        } catch (NumberFormatException unused) {
            MpbLog.warnf("Ignoring malformed AVC codec string: %s", this.codec);
            return null;
        }
    }

    @NotNull
    public final String getCodec() {
        return this.codec;
    }

    @Nullable
    public final Pair<Integer, Integer> getCodecProfileAndLevel() {
        if (this.codec.length() == 0) {
            return null;
        }
        String[] parts = CodecStringKt.getParts(this.codec);
        Codec codecFromString = Codec.Companion.fromString(parts[0]);
        switch (codecFromString == null ? -1 : WhenMappings.$EnumSwitchMapping$0[codecFromString.ordinal()]) {
            case 3:
            case 4:
                if (parts.length >= 4) {
                }
                break;
        }
        return null;
    }

    public final Pair<Integer, Integer> getDolbyVisionProfileAndLevel() {
        String[] parts = CodecStringKt.getParts(this.codec);
        if (parts.length < 3) {
            MpbLog.warnf("Ignoring malformed Dolby Vision codec string: %s", this.codec);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(parts[1]);
        if (!matcher.matches()) {
            MpbLog.warnf("Ignoring malformed Dolby Vision codec string: %s", this.codec);
            return null;
        }
        String strGroup = matcher.group(1);
        if (strGroup == null) {
            return null;
        }
        Integer num = DOLBY_VISION_STRING_TO_PROFILE.get(strGroup);
        if (num == null) {
            MpbLog.warnf("Unknown Dolby Vision profile string: %s", strGroup);
            return null;
        }
        String str = parts[2];
        Integer num2 = DOLBY_VISION_STRING_TO_LEVEL.get(str);
        if (num2 != null) {
            return new Pair<>(num, num2);
        }
        MpbLog.warnf("Unknown Dolby Vision level string: %s", str);
        return null;
    }

    public final Pair<Integer, Integer> getHevcProfileAndLevel() {
        int i;
        String[] parts = CodecStringKt.getParts(this.codec);
        if (parts.length < 4) {
            MpbLog.warnf("Ignoring malformed HEVC codec string: %s", this.codec);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(parts[1]);
        if (!matcher.matches()) {
            MpbLog.warnf("Ignoring malformed HEVC codec string: %s", this.codec);
            return null;
        }
        String strGroup = matcher.group(1);
        if (Intrinsics.areEqual(strGroup, "1")) {
            i = 1;
        } else {
            if (!Intrinsics.areEqual(strGroup, ExifInterface.GPS_MEASUREMENT_2D)) {
                MpbLog.warnf("Unknown HEVC profile string: %s", strGroup);
                return null;
            }
            i = 2;
        }
        String str = parts[3];
        Integer num = HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(str);
        if (num != null) {
            return new Pair<>(Integer.valueOf(i), num);
        }
        MpbLog.warnf("Unknown HEVC level string: %s", str);
        return null;
    }

    @Nullable
    public final Integer getLevel() {
        Pair<Integer, Integer> codecProfileAndLevel = getCodecProfileAndLevel();
        if (codecProfileAndLevel != null) {
            return codecProfileAndLevel.second;
        }
        return null;
    }

    @Nullable
    public final Integer getProfile() {
        Pair<Integer, Integer> codecProfileAndLevel = getCodecProfileAndLevel();
        if (codecProfileAndLevel != null) {
            return codecProfileAndLevel.first;
        }
        return null;
    }

    public int hashCode() {
        return this.codec.hashCode();
    }

    @NotNull
    public String toString() {
        return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("CodecString(codec=", this.codec, ")");
    }
}
