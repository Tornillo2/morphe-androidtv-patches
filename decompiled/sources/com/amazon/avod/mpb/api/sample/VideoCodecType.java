package com.amazon.avod.mpb.api.sample;

import com.amazon.avod.mpb.media.VideoStreamType;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoCodecType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ VideoCodecType[] $VALUES;
    public static final VideoCodecType VIDEO_CODEC_DAV1;
    public static final VideoCodecType VIDEO_CODEC_DVA1;
    public static final VideoCodecType VIDEO_CODEC_DVAV;
    public static final VideoCodecType VIDEO_CODEC_DVC1;
    public static final VideoCodecType VIDEO_CODEC_DVH1;
    public static final VideoCodecType VIDEO_CODEC_DVHE;
    public static final VideoCodecType VIDEO_CODEC_DVHE_DTB;
    public static final VideoCodecType VIDEO_CODEC_DVHE_DTR;
    public static final VideoCodecType VIDEO_CODEC_DVHE_SE;
    public static final VideoCodecType VIDEO_CODEC_DVHE_ST;
    public static final VideoCodecType VIDEO_CODEC_DVHE_STN;
    public static final VideoCodecType VIDEO_CODEC_DVI1;

    @Nullable
    public final Integer profile;

    @Nullable
    public final VideoStreamType videoStreamType;
    public static final VideoCodecType VIDEO_CODEC_AVC = new VideoCodecType("VIDEO_CODEC_AVC", 0, VideoStreamType.H264, null, 2, null);
    public static final VideoCodecType VIDEO_CODEC_HEVC = new VideoCodecType("VIDEO_CODEC_HEVC", 1, VideoStreamType.H265, null, 2, null);
    public static final VideoCodecType VIDEO_CODEC_VVC = new VideoCodecType("VIDEO_CODEC_VVC", 2, null == true ? 1 : 0, null, 3, null);
    public static final VideoCodecType VIDEO_CODEC_AV1 = new VideoCodecType("VIDEO_CODEC_AV1", 3, VideoStreamType.AV01, null, 2, null);
    public static final VideoCodecType VIDEO_CODEC_AV2 = new VideoCodecType("VIDEO_CODEC_AV2", 4, null == true ? 1 : 0, null, 3, null);
    public static final VideoCodecType VIDEO_CODEC_VP8 = new VideoCodecType("VIDEO_CODEC_VP8", 5, null == true ? 1 : 0, null, 3, null);
    public static final VideoCodecType VIDEO_CODEC_VP9 = new VideoCodecType("VIDEO_CODEC_VP9", 6, null == true ? 1 : 0, null, 3, null);

    public static final /* synthetic */ VideoCodecType[] $values() {
        return new VideoCodecType[]{VIDEO_CODEC_AVC, VIDEO_CODEC_HEVC, VIDEO_CODEC_VVC, VIDEO_CODEC_AV1, VIDEO_CODEC_AV2, VIDEO_CODEC_VP8, VIDEO_CODEC_VP9, VIDEO_CODEC_DVHE, VIDEO_CODEC_DVHE_DTR, VIDEO_CODEC_DVHE_STN, VIDEO_CODEC_DVHE_DTB, VIDEO_CODEC_DVHE_ST, VIDEO_CODEC_DVHE_SE, VIDEO_CODEC_DVAV, VIDEO_CODEC_DVA1, VIDEO_CODEC_DAV1, VIDEO_CODEC_DVH1, VIDEO_CODEC_DVC1, VIDEO_CODEC_DVI1};
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        VideoStreamType videoStreamType = VideoStreamType.DVHE;
        VIDEO_CODEC_DVHE = new VideoCodecType("VIDEO_CODEC_DVHE", 7, videoStreamType, null, 2, null);
        VIDEO_CODEC_DVHE_DTR = new VideoCodecType("VIDEO_CODEC_DVHE_DTR", 8, videoStreamType, 16);
        VIDEO_CODEC_DVHE_STN = new VideoCodecType("VIDEO_CODEC_DVHE_STN", 9, videoStreamType, 32);
        VIDEO_CODEC_DVHE_DTB = new VideoCodecType("VIDEO_CODEC_DVHE_DTB", 10, videoStreamType, 128);
        int i = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        VIDEO_CODEC_DVHE_ST = new VideoCodecType("VIDEO_CODEC_DVHE_ST", 11, videoStreamType, null, i, defaultConstructorMarker);
        VIDEO_CODEC_DVHE_SE = new VideoCodecType("VIDEO_CODEC_DVHE_SE", 12, videoStreamType, null == true ? 1 : 0, i, defaultConstructorMarker);
        VIDEO_CODEC_DVAV = new VideoCodecType("VIDEO_CODEC_DVAV", 13, null == true ? 1 : 0, null, 3, null);
        VIDEO_CODEC_DVA1 = new VideoCodecType("VIDEO_CODEC_DVA1", 14, null == true ? 1 : 0, null, 3, null);
        VIDEO_CODEC_DAV1 = new VideoCodecType("VIDEO_CODEC_DAV1", 15, null == true ? 1 : 0, null, 3, null);
        VIDEO_CODEC_DVH1 = new VideoCodecType("VIDEO_CODEC_DVH1", 16, null == true ? 1 : 0, null, 3, null);
        VIDEO_CODEC_DVC1 = new VideoCodecType("VIDEO_CODEC_DVC1", 17, null == true ? 1 : 0, null, 3, null == true ? 1 : 0);
        VIDEO_CODEC_DVI1 = new VideoCodecType("VIDEO_CODEC_DVI1", 18, null == true ? 1 : 0, null, 3, null);
        VideoCodecType[] videoCodecTypeArr$values = $values();
        $VALUES = videoCodecTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(videoCodecTypeArr$values);
    }

    public VideoCodecType(String str, int i, VideoStreamType videoStreamType, Integer num) {
        this.videoStreamType = videoStreamType;
        this.profile = num;
    }

    @NotNull
    public static EnumEntries<VideoCodecType> getEntries() {
        return $ENTRIES;
    }

    public static VideoCodecType valueOf(String str) {
        return (VideoCodecType) Enum.valueOf(VideoCodecType.class, str);
    }

    public static VideoCodecType[] values() {
        return (VideoCodecType[]) $VALUES.clone();
    }

    @Nullable
    public final Integer getProfile() {
        return this.profile;
    }

    @Nullable
    public final VideoStreamType getVideoStreamType() {
        return this.videoStreamType;
    }

    public /* synthetic */ VideoCodecType(String str, int i, VideoStreamType videoStreamType, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 1) != 0 ? null : videoStreamType, (i2 & 2) != 0 ? null : num);
    }
}
