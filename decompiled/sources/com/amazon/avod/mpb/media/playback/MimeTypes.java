package com.amazon.avod.mpb.media.playback;

import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MimeTypes {

    @NotNull
    public static final String APPLICATION_PLAYREADY = "application/vnd.ms-playready.media.pyv";

    @NotNull
    public static final String AUDIO_AAC = "audio/mp4a-latm";

    @NotNull
    public static final String AUDIO_AC3 = "audio/ac3";

    @NotNull
    public static final String AUDIO_EAC3 = "audio/eac3";

    @NotNull
    public static final String AUDIO_RAW = "audio/raw";

    @NotNull
    public static final String DOLBY_VISION = "video/dolby-vision";

    @NotNull
    public static final MimeTypes INSTANCE = new MimeTypes();

    @NotNull
    public static final String PREFIX_APPLICATION = "application/";

    @NotNull
    public static final String PREFIX_AUDIO = "audio/";

    @NotNull
    public static final String PREFIX_VIDEO = "video/";

    @NotNull
    public static final String VIDEO_AV01 = "video/av01";

    @NotNull
    public static final String VIDEO_AVC = "video/avc";

    @NotNull
    public static final String VIDEO_HEVC = "video/hevc";

    @JvmStatic
    public static final boolean isAudio(@Nullable String str) {
        return str != null && StringsKt__StringsJVMKt.startsWith$default(str, PREFIX_AUDIO, false, 2, null);
    }

    @JvmStatic
    public static final boolean isVideo(@Nullable String str) {
        return str != null && StringsKt__StringsJVMKt.startsWith$default(str, PREFIX_VIDEO, false, 2, null);
    }
}
