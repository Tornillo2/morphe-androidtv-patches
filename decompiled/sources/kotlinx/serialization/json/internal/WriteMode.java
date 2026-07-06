package kotlinx.serialization.json.internal;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class WriteMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ WriteMode[] $VALUES;

    @JvmField
    public final char begin;

    @JvmField
    public final char end;
    public static final WriteMode OBJ = new WriteMode("OBJ", 0, '{', '}');
    public static final WriteMode LIST = new WriteMode("LIST", 1, AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.END_LIST);
    public static final WriteMode MAP = new WriteMode("MAP", 2, '{', '}');
    public static final WriteMode POLY_OBJ = new WriteMode("POLY_OBJ", 3, AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.END_LIST);

    public static final /* synthetic */ WriteMode[] $values() {
        return new WriteMode[]{OBJ, LIST, MAP, POLY_OBJ};
    }

    static {
        WriteMode[] writeModeArr$values = $values();
        $VALUES = writeModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(writeModeArr$values);
    }

    public WriteMode(String str, int i, char c, char c2) {
        this.begin = c;
        this.end = c2;
    }

    @NotNull
    public static EnumEntries<WriteMode> getEntries() {
        return $ENTRIES;
    }

    public static WriteMode valueOf(String str) {
        return (WriteMode) Enum.valueOf(WriteMode.class, str);
    }

    public static WriteMode[] values() {
        return (WriteMode[]) $VALUES.clone();
    }
}
