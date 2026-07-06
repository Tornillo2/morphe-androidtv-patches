package com.amazon.avod.mpb.media.playback.render;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RendererResult {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ RendererResult[] $VALUES;
    public static final RendererResult RENDER = new RendererResult("RENDER", 0);
    public static final RendererResult SKIP = new RendererResult("SKIP", 1);
    public static final RendererResult DROP = new RendererResult("DROP", 2);
    public static final RendererResult DELAY = new RendererResult("DELAY", 3);

    public static final /* synthetic */ RendererResult[] $values() {
        return new RendererResult[]{RENDER, SKIP, DROP, DELAY};
    }

    static {
        RendererResult[] rendererResultArr$values = $values();
        $VALUES = rendererResultArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(rendererResultArr$values);
    }

    public RendererResult(String str, int i) {
    }

    @NotNull
    public static EnumEntries<RendererResult> getEntries() {
        return $ENTRIES;
    }

    public static RendererResult valueOf(String str) {
        return (RendererResult) Enum.valueOf(RendererResult.class, str);
    }

    public static RendererResult[] values() {
        return (RendererResult[]) $VALUES.clone();
    }
}
