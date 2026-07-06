package kotlinx.coroutines.internal;

import kotlin.PublishedApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class LockFreeLinkedListKt {
    public static final int FAILURE = 2;
    public static final int SUCCESS = 1;
    public static final int UNDECIDED = 0;

    @NotNull
    public static final Object CONDITION_FALSE = new Symbol("CONDITION_FALSE");

    @NotNull
    public static final Object LIST_EMPTY = new Symbol("LIST_EMPTY");

    @NotNull
    public static final Object getCONDITION_FALSE() {
        return CONDITION_FALSE;
    }

    @NotNull
    public static final Object getLIST_EMPTY() {
        return LIST_EMPTY;
    }

    @PublishedApi
    @NotNull
    public static final LockFreeLinkedListNode unwrap(@NotNull Object obj) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed = obj instanceof Removed ? (Removed) obj : null;
        return (removed == null || (lockFreeLinkedListNode = removed.ref) == null) ? (LockFreeLinkedListNode) obj : lockFreeLinkedListNode;
    }

    @PublishedApi
    public static /* synthetic */ void getCONDITION_FALSE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getFAILURE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getLIST_EMPTY$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSUCCESS$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getUNDECIDED$annotations() {
    }
}
