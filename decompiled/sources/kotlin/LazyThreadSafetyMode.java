package kotlin;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LazyThreadSafetyMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ LazyThreadSafetyMode[] $VALUES;
    public static final LazyThreadSafetyMode SYNCHRONIZED = new LazyThreadSafetyMode("SYNCHRONIZED", 0);
    public static final LazyThreadSafetyMode PUBLICATION = new LazyThreadSafetyMode("PUBLICATION", 1);
    public static final LazyThreadSafetyMode NONE = new LazyThreadSafetyMode("NONE", 2);

    public static final /* synthetic */ LazyThreadSafetyMode[] $values() {
        return new LazyThreadSafetyMode[]{SYNCHRONIZED, PUBLICATION, NONE};
    }

    static {
        LazyThreadSafetyMode[] lazyThreadSafetyModeArr$values = $values();
        $VALUES = lazyThreadSafetyModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(lazyThreadSafetyModeArr$values);
    }

    public LazyThreadSafetyMode(String str, int i) {
    }

    @NotNull
    public static EnumEntries<LazyThreadSafetyMode> getEntries() {
        return $ENTRIES;
    }

    public static LazyThreadSafetyMode valueOf(String str) {
        return (LazyThreadSafetyMode) Enum.valueOf(LazyThreadSafetyMode.class, str);
    }

    public static LazyThreadSafetyMode[] values() {
        return (LazyThreadSafetyMode[]) $VALUES.clone();
    }
}
