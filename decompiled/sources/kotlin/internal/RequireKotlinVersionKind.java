package kotlin.internal;

import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.2")
public final class RequireKotlinVersionKind {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ RequireKotlinVersionKind[] $VALUES;
    public static final RequireKotlinVersionKind LANGUAGE_VERSION = new RequireKotlinVersionKind("LANGUAGE_VERSION", 0);
    public static final RequireKotlinVersionKind COMPILER_VERSION = new RequireKotlinVersionKind("COMPILER_VERSION", 1);
    public static final RequireKotlinVersionKind API_VERSION = new RequireKotlinVersionKind("API_VERSION", 2);

    public static final /* synthetic */ RequireKotlinVersionKind[] $values() {
        return new RequireKotlinVersionKind[]{LANGUAGE_VERSION, COMPILER_VERSION, API_VERSION};
    }

    static {
        RequireKotlinVersionKind[] requireKotlinVersionKindArr$values = $values();
        $VALUES = requireKotlinVersionKindArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(requireKotlinVersionKindArr$values);
    }

    public RequireKotlinVersionKind(String str, int i) {
    }

    @NotNull
    public static EnumEntries<RequireKotlinVersionKind> getEntries() {
        return $ENTRIES;
    }

    public static RequireKotlinVersionKind valueOf(String str) {
        return (RequireKotlinVersionKind) Enum.valueOf(RequireKotlinVersionKind.class, str);
    }

    public static RequireKotlinVersionKind[] values() {
        return (RequireKotlinVersionKind[]) $VALUES.clone();
    }
}
