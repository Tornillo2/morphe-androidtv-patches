package com.amazon.avod.mpb.media.drm;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.SerialName;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class EncryptionScheme {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ EncryptionScheme[] $VALUES;

    @SerialName("cenc")
    public static final EncryptionScheme CENC = new EncryptionScheme("CENC", 0);

    @SerialName("cbcs")
    public static final EncryptionScheme CBCS = new EncryptionScheme("CBCS", 1);

    public static final /* synthetic */ EncryptionScheme[] $values() {
        return new EncryptionScheme[]{CENC, CBCS};
    }

    static {
        EncryptionScheme[] encryptionSchemeArr$values = $values();
        $VALUES = encryptionSchemeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(encryptionSchemeArr$values);
    }

    public EncryptionScheme(String str, int i) {
    }

    @NotNull
    public static EnumEntries<EncryptionScheme> getEntries() {
        return $ENTRIES;
    }

    public static EncryptionScheme valueOf(String str) {
        return (EncryptionScheme) Enum.valueOf(EncryptionScheme.class, str);
    }

    public static EncryptionScheme[] values() {
        return (EncryptionScheme[]) $VALUES.clone();
    }
}
