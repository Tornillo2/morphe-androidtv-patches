package kotlinx.serialization.json;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ClassDiscriminatorMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ ClassDiscriminatorMode[] $VALUES;
    public static final ClassDiscriminatorMode NONE = new ClassDiscriminatorMode("NONE", 0);
    public static final ClassDiscriminatorMode ALL_JSON_OBJECTS = new ClassDiscriminatorMode("ALL_JSON_OBJECTS", 1);
    public static final ClassDiscriminatorMode POLYMORPHIC = new ClassDiscriminatorMode("POLYMORPHIC", 2);

    public static final /* synthetic */ ClassDiscriminatorMode[] $values() {
        return new ClassDiscriminatorMode[]{NONE, ALL_JSON_OBJECTS, POLYMORPHIC};
    }

    static {
        ClassDiscriminatorMode[] classDiscriminatorModeArr$values = $values();
        $VALUES = classDiscriminatorModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(classDiscriminatorModeArr$values);
    }

    public ClassDiscriminatorMode(String str, int i) {
    }

    @NotNull
    public static EnumEntries<ClassDiscriminatorMode> getEntries() {
        return $ENTRIES;
    }

    public static ClassDiscriminatorMode valueOf(String str) {
        return (ClassDiscriminatorMode) Enum.valueOf(ClassDiscriminatorMode.class, str);
    }

    public static ClassDiscriminatorMode[] values() {
        return (ClassDiscriminatorMode[]) $VALUES.clone();
    }
}
