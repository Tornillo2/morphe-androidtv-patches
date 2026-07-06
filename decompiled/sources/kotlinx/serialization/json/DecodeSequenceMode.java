package kotlinx.serialization.json;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public final class DecodeSequenceMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ DecodeSequenceMode[] $VALUES;
    public static final DecodeSequenceMode WHITESPACE_SEPARATED = new DecodeSequenceMode("WHITESPACE_SEPARATED", 0);
    public static final DecodeSequenceMode ARRAY_WRAPPED = new DecodeSequenceMode("ARRAY_WRAPPED", 1);
    public static final DecodeSequenceMode AUTO_DETECT = new DecodeSequenceMode("AUTO_DETECT", 2);

    public static final /* synthetic */ DecodeSequenceMode[] $values() {
        return new DecodeSequenceMode[]{WHITESPACE_SEPARATED, ARRAY_WRAPPED, AUTO_DETECT};
    }

    static {
        DecodeSequenceMode[] decodeSequenceModeArr$values = $values();
        $VALUES = decodeSequenceModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(decodeSequenceModeArr$values);
    }

    public DecodeSequenceMode(String str, int i) {
    }

    @NotNull
    public static EnumEntries<DecodeSequenceMode> getEntries() {
        return $ENTRIES;
    }

    public static DecodeSequenceMode valueOf(String str) {
        return (DecodeSequenceMode) Enum.valueOf(DecodeSequenceMode.class, str);
    }

    public static DecodeSequenceMode[] values() {
        return (DecodeSequenceMode[]) $VALUES.clone();
    }
}
