package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.query.MediaCodecQuerier;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class HdrMetadataType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ HdrMetadataType[] $VALUES;
    public static final HdrMetadataType HDR10 = new HdrMetadataType("HDR10", 0, MediaCodecQuerier.HDR_METADATA_TYPE_SMPTE_ST_2086);
    public static final HdrMetadataType HDR10_PLUS = new HdrMetadataType("HDR10_PLUS", 1, MediaCodecQuerier.HDR_METADATA_TYPE_SMPTE_ST_2094_40);

    @NotNull
    public final String value;

    public static final /* synthetic */ HdrMetadataType[] $values() {
        return new HdrMetadataType[]{HDR10, HDR10_PLUS};
    }

    static {
        HdrMetadataType[] hdrMetadataTypeArr$values = $values();
        $VALUES = hdrMetadataTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(hdrMetadataTypeArr$values);
    }

    public HdrMetadataType(String str, int i, String str2) {
        this.value = str2;
    }

    @NotNull
    public static EnumEntries<HdrMetadataType> getEntries() {
        return $ENTRIES;
    }

    public static HdrMetadataType valueOf(String str) {
        return (HdrMetadataType) Enum.valueOf(HdrMetadataType.class, str);
    }

    public static HdrMetadataType[] values() {
        return (HdrMetadataType[]) $VALUES.clone();
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
