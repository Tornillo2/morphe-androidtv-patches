package com.amazon.avod.mpb.media;

import com.google.common.base.Preconditions;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioStreamType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ AudioStreamType[] $VALUES;

    @NotNull
    public final String fourCC;
    public static final AudioStreamType ATMOS = new AudioStreamType("ATMOS", 0, "ATMO");
    public static final AudioStreamType DD = new AudioStreamType("DD", 1, "AC-3");
    public static final AudioStreamType DDP = new AudioStreamType("DDP", 2, "EC-3");
    public static final AudioStreamType AACL = new AudioStreamType("AACL", 3, "AACL");
    public static final AudioStreamType AACH = new AudioStreamType("AACH", 4, "AACH");
    public static final AudioStreamType AACHV2 = new AudioStreamType("AACHV2", 5, "ACH2");
    public static final AudioStreamType UNKNOWN = new AudioStreamType("UNKNOWN", 6, "NULL");

    public static final /* synthetic */ AudioStreamType[] $values() {
        return new AudioStreamType[]{ATMOS, DD, DDP, AACL, AACH, AACHV2, UNKNOWN};
    }

    static {
        AudioStreamType[] audioStreamTypeArr$values = $values();
        $VALUES = audioStreamTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(audioStreamTypeArr$values);
    }

    public AudioStreamType(String str, int i, String str2) {
        this.fourCC = str2;
        Preconditions.checkArgument(str2.length() == 4, "fourCC must be 4 characters long", new Object[0]);
    }

    @NotNull
    public static EnumEntries<AudioStreamType> getEntries() {
        return $ENTRIES;
    }

    public static AudioStreamType valueOf(String str) {
        return (AudioStreamType) Enum.valueOf(AudioStreamType.class, str);
    }

    public static AudioStreamType[] values() {
        return (AudioStreamType[]) $VALUES.clone();
    }

    @NotNull
    public final String getFourCC() {
        return this.fourCC;
    }
}
