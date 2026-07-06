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
public final class OnErrorResult {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ OnErrorResult[] $VALUES;
    public static final OnErrorResult SKIP_SUBTREE = new OnErrorResult("SKIP_SUBTREE", 0);
    public static final OnErrorResult TERMINATE = new OnErrorResult("TERMINATE", 1);

    public static final /* synthetic */ OnErrorResult[] $values() {
        return new OnErrorResult[]{SKIP_SUBTREE, TERMINATE};
    }

    static {
        OnErrorResult[] onErrorResultArr$values = $values();
        $VALUES = onErrorResultArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(onErrorResultArr$values);
    }

    public OnErrorResult(String str, int i) {
    }

    @NotNull
    public static EnumEntries<OnErrorResult> getEntries() {
        return $ENTRIES;
    }

    public static OnErrorResult valueOf(String str) {
        return (OnErrorResult) Enum.valueOf(OnErrorResult.class, str);
    }

    public static OnErrorResult[] values() {
        return (OnErrorResult[]) $VALUES.clone();
    }
}
