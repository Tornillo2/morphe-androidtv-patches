package com.amazon.avod.mpb.media.drm;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.SerialName;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SessionType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ SessionType[] $VALUES;

    @SerialName("persistent")
    public static final SessionType PERSISTENT = new SessionType("PERSISTENT", 0);

    @SerialName("non-persistent")
    public static final SessionType NON_PERSISTENT = new SessionType("NON_PERSISTENT", 1);

    public static final /* synthetic */ SessionType[] $values() {
        return new SessionType[]{PERSISTENT, NON_PERSISTENT};
    }

    static {
        SessionType[] sessionTypeArr$values = $values();
        $VALUES = sessionTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(sessionTypeArr$values);
    }

    public SessionType(String str, int i) {
    }

    @NotNull
    public static EnumEntries<SessionType> getEntries() {
        return $ENTRIES;
    }

    public static SessionType valueOf(String str) {
        return (SessionType) Enum.valueOf(SessionType.class, str);
    }

    public static SessionType[] values() {
        return (SessionType[]) $VALUES.clone();
    }
}
