package com.amazon.avod.mpb.media.playback.pipeline;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PipelineTaskType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ PipelineTaskType[] $VALUES;
    public static final PipelineTaskType DRAIN = new PipelineTaskType("DRAIN", 0);
    public static final PipelineTaskType FEED = new PipelineTaskType("FEED", 1);

    public static final /* synthetic */ PipelineTaskType[] $values() {
        return new PipelineTaskType[]{DRAIN, FEED};
    }

    static {
        PipelineTaskType[] pipelineTaskTypeArr$values = $values();
        $VALUES = pipelineTaskTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(pipelineTaskTypeArr$values);
    }

    public PipelineTaskType(String str, int i) {
    }

    @NotNull
    public static EnumEntries<PipelineTaskType> getEntries() {
        return $ENTRIES;
    }

    public static PipelineTaskType valueOf(String str) {
        return (PipelineTaskType) Enum.valueOf(PipelineTaskType.class, str);
    }

    public static PipelineTaskType[] values() {
        return (PipelineTaskType[]) $VALUES.clone();
    }
}
