package com.amazon.avod.mpb.media.playback;

import android.media.MediaCodecInfo;
import com.amazon.avod.mpb.api.query.AudioCodecQueryAttributes;
import com.amazon.avod.mpb.api.query.VideoCodecQueryAttributes;
import java.util.List;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaCodecEnumerator {
    @Nullable
    MediaCodecInfo getDecoderInfoForMimeAndProfile(@NotNull String str, int i);

    @Nullable
    MediaCodecInfo getSupportedCodec(@NotNull AudioCodecQueryAttributes audioCodecQueryAttributes);

    @Nullable
    MediaCodecInfo getSupportedCodec(@NotNull VideoCodecQueryAttributes videoCodecQueryAttributes);

    @Nullable
    MediaCodecInfo getSupportedCodec(@NotNull String str);

    @Nullable
    Pair<String, MediaCodecInfo> getSupportedCodec(@NotNull List<String> list);
}
