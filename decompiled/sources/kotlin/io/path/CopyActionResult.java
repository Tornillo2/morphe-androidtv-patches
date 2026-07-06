package kotlin.io.path;

import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.8")
@ExperimentalPathApi
public final class CopyActionResult {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ CopyActionResult[] $VALUES;
    public static final CopyActionResult CONTINUE = new CopyActionResult("CONTINUE", 0);
    public static final CopyActionResult SKIP_SUBTREE = new CopyActionResult("SKIP_SUBTREE", 1);
    public static final CopyActionResult TERMINATE = new CopyActionResult("TERMINATE", 2);

    public static final /* synthetic */ CopyActionResult[] $values() {
        return new CopyActionResult[]{CONTINUE, SKIP_SUBTREE, TERMINATE};
    }

    static {
        CopyActionResult[] copyActionResultArr$values = $values();
        $VALUES = copyActionResultArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(copyActionResultArr$values);
    }

    public CopyActionResult(String str, int i) {
    }

    @NotNull
    public static EnumEntries<CopyActionResult> getEntries() {
        return $ENTRIES;
    }

    public static CopyActionResult valueOf(String str) {
        return (CopyActionResult) Enum.valueOf(CopyActionResult.class, str);
    }

    public static CopyActionResult[] values() {
        return (CopyActionResult[]) $VALUES.clone();
    }
}
