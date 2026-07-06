package com.amazon.avod.mpb.api.core;

import androidx.exifinterface.media.ExifInterface;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TrackConfiguration {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ TrackConfiguration[] $VALUES;
    public static final TrackConfiguration AV = new TrackConfiguration("AV", 0);
    public static final TrackConfiguration A = new TrackConfiguration(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, 1);
    public static final TrackConfiguration V = new TrackConfiguration(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, 2);

    public static final /* synthetic */ TrackConfiguration[] $values() {
        return new TrackConfiguration[]{AV, A, V};
    }

    static {
        TrackConfiguration[] trackConfigurationArr$values = $values();
        $VALUES = trackConfigurationArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(trackConfigurationArr$values);
    }

    public TrackConfiguration(String str, int i) {
    }

    @NotNull
    public static EnumEntries<TrackConfiguration> getEntries() {
        return $ENTRIES;
    }

    public static TrackConfiguration valueOf(String str) {
        return (TrackConfiguration) Enum.valueOf(TrackConfiguration.class, str);
    }

    public static TrackConfiguration[] values() {
        return (TrackConfiguration[]) $VALUES.clone();
    }
}
