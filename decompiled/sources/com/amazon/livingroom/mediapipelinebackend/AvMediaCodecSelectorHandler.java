package com.amazon.livingroom.mediapipelinebackend;

import android.media.MediaCodecInfo;
import androidx.annotation.OptIn;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public class AvMediaCodecSelectorHandler implements MediaCodecSelector {
    public static final String MIMETYPE_VIDEO_DOLBY_VISION = "video/dolby-vision";
    public final MediaCodecSelector mediaCodecSelector;

    public AvMediaCodecSelectorHandler(MediaCodecSelector mediaCodecSelector) {
        this.mediaCodecSelector = mediaCodecSelector;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecSelector
    @NotNull
    public List<MediaCodecInfo> getDecoderInfos(@NotNull String str, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = this.mediaCodecSelector.getDecoderInfos(str, z, z2);
        if (!str.equals("video/dolby-vision")) {
            return decoderInfos;
        }
        ArrayList arrayList = new ArrayList();
        for (MediaCodecInfo mediaCodecInfo : decoderInfos) {
            if (hasDolbyVisionStn(mediaCodecInfo)) {
                arrayList.add(mediaCodecInfo);
            }
        }
        return arrayList;
    }

    public final boolean hasDolbyVisionStn(MediaCodecInfo mediaCodecInfo) {
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getProfileLevels()) {
            if (codecProfileLevel.profile == 32) {
                return true;
            }
        }
        return false;
    }
}
