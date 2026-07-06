package com.amazon.avod.mpb.media;

import com.google.common.base.Preconditions;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoStreamType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ VideoStreamType[] $VALUES;

    @NotNull
    public final String fourCC;
    public static final VideoStreamType H264 = new VideoStreamType("H264", 0, "AVC1");
    public static final VideoStreamType H265 = new VideoStreamType("H265", 1, "HEV1");
    public static final VideoStreamType DVHE = new VideoStreamType("DVHE", 2, "DVHE");
    public static final VideoStreamType AV01 = new VideoStreamType("AV01", 3, "AV01");

    public static final /* synthetic */ VideoStreamType[] $values() {
        return new VideoStreamType[]{H264, H265, DVHE, AV01};
    }

    static {
        VideoStreamType[] videoStreamTypeArr$values = $values();
        $VALUES = videoStreamTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(videoStreamTypeArr$values);
    }

    public VideoStreamType(String str, int i, String str2) {
        this.fourCC = str2;
        Preconditions.checkArgument(str2.length() == 4, "fourCC must be 4 characters long", new Object[0]);
    }

    @NotNull
    public static EnumEntries<VideoStreamType> getEntries() {
        return $ENTRIES;
    }

    public static VideoStreamType valueOf(String str) {
        return (VideoStreamType) Enum.valueOf(VideoStreamType.class, str);
    }

    public static VideoStreamType[] values() {
        return (VideoStreamType[]) $VALUES.clone();
    }

    @NotNull
    public final String getFourCC() {
        return this.fourCC;
    }
}
