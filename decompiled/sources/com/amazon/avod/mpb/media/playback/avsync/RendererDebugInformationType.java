package com.amazon.avod.mpb.media.playback.avsync;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RendererDebugInformationType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ RendererDebugInformationType[] $VALUES;
    public static final RendererDebugInformationType IncompleteHeader = new RendererDebugInformationType("IncompleteHeader", 0);
    public static final RendererDebugInformationType AVPTSMisMatch = new RendererDebugInformationType("AVPTSMisMatch", 1);

    public static final /* synthetic */ RendererDebugInformationType[] $values() {
        return new RendererDebugInformationType[]{IncompleteHeader, AVPTSMisMatch};
    }

    static {
        RendererDebugInformationType[] rendererDebugInformationTypeArr$values = $values();
        $VALUES = rendererDebugInformationTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(rendererDebugInformationTypeArr$values);
    }

    public RendererDebugInformationType(String str, int i) {
    }

    @NotNull
    public static EnumEntries<RendererDebugInformationType> getEntries() {
        return $ENTRIES;
    }

    public static RendererDebugInformationType valueOf(String str) {
        return (RendererDebugInformationType) Enum.valueOf(RendererDebugInformationType.class, str);
    }

    public static RendererDebugInformationType[] values() {
        return (RendererDebugInformationType[]) $VALUES.clone();
    }
}
