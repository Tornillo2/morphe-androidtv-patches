package com.amazon.avod.mpb.media.drm;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.SerialName;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DrmSystemName {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ DrmSystemName[] $VALUES;

    @SerialName("com.widevine.alpha")
    public static final DrmSystemName WIDEVINE = new DrmSystemName("WIDEVINE", 0);

    @SerialName("com.microsoft.playready")
    public static final DrmSystemName PLAYREADY = new DrmSystemName("PLAYREADY", 1);

    public static final /* synthetic */ DrmSystemName[] $values() {
        return new DrmSystemName[]{WIDEVINE, PLAYREADY};
    }

    static {
        DrmSystemName[] drmSystemNameArr$values = $values();
        $VALUES = drmSystemNameArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(drmSystemNameArr$values);
    }

    public DrmSystemName(String str, int i) {
    }

    @NotNull
    public static EnumEntries<DrmSystemName> getEntries() {
        return $ENTRIES;
    }

    public static DrmSystemName valueOf(String str) {
        return (DrmSystemName) Enum.valueOf(DrmSystemName.class, str);
    }

    public static DrmSystemName[] values() {
        return (DrmSystemName[]) $VALUES.clone();
    }
}
