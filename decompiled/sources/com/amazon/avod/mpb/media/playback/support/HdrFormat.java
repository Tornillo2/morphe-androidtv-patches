package com.amazon.avod.mpb.media.playback.support;

import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class HdrFormat {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ HdrFormat[] $VALUES;
    public static final HdrFormat Hdr10 = new HdrFormat("Hdr10", 0);
    public static final HdrFormat DolbyVision = new HdrFormat(PictureQualityController.SignalType.DOLBY_VISION, 1);

    public static final /* synthetic */ HdrFormat[] $values() {
        return new HdrFormat[]{Hdr10, DolbyVision};
    }

    static {
        HdrFormat[] hdrFormatArr$values = $values();
        $VALUES = hdrFormatArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(hdrFormatArr$values);
    }

    public HdrFormat(String str, int i) {
    }

    @NotNull
    public static EnumEntries<HdrFormat> getEntries() {
        return $ENTRIES;
    }

    public static HdrFormat valueOf(String str) {
        return (HdrFormat) Enum.valueOf(HdrFormat.class, str);
    }

    public static HdrFormat[] values() {
        return (HdrFormat[]) $VALUES.clone();
    }
}
