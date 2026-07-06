package com.amazon.avod.mpb.api.query;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ MediaType[] $VALUES;
    public static final MediaType MEDIA_AUDIO = new MediaType("MEDIA_AUDIO", 0);
    public static final MediaType MEDIA_VIDEO = new MediaType("MEDIA_VIDEO", 1);

    public static final /* synthetic */ MediaType[] $values() {
        return new MediaType[]{MEDIA_AUDIO, MEDIA_VIDEO};
    }

    static {
        MediaType[] mediaTypeArr$values = $values();
        $VALUES = mediaTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(mediaTypeArr$values);
    }

    public MediaType(String str, int i) {
    }

    @NotNull
    public static EnumEntries<MediaType> getEntries() {
        return $ENTRIES;
    }

    public static MediaType valueOf(String str) {
        return (MediaType) Enum.valueOf(MediaType.class, str);
    }

    public static MediaType[] values() {
        return (MediaType[]) $VALUES.clone();
    }
}
