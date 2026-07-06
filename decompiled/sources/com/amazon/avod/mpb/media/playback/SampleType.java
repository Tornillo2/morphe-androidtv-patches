package com.amazon.avod.mpb.media.playback;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SampleType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ SampleType[] $VALUES;
    public static final SampleType AUDIO_SAMPLE = new SampleType("AUDIO_SAMPLE", 0);
    public static final SampleType VIDEO_SAMPLE = new SampleType("VIDEO_SAMPLE", 1);

    public static final /* synthetic */ SampleType[] $values() {
        return new SampleType[]{AUDIO_SAMPLE, VIDEO_SAMPLE};
    }

    static {
        SampleType[] sampleTypeArr$values = $values();
        $VALUES = sampleTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(sampleTypeArr$values);
    }

    public SampleType(String str, int i) {
    }

    @NotNull
    public static EnumEntries<SampleType> getEntries() {
        return $ENTRIES;
    }

    public static SampleType valueOf(String str) {
        return (SampleType) Enum.valueOf(SampleType.class, str);
    }

    public static SampleType[] values() {
        return (SampleType[]) $VALUES.clone();
    }
}
