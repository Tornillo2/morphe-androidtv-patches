package kotlinx.coroutines;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class JobSupportKt {
    public static final int FALSE = 0;
    public static final int RETRY = -1;
    public static final int TRUE = 1;

    @NotNull
    public static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");

    @JvmField
    @NotNull
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");

    @NotNull
    public static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");

    @NotNull
    public static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");

    @NotNull
    public static final Symbol SEALED = new Symbol("SEALED");

    @NotNull
    public static final Empty EMPTY_NEW = new Empty(false);

    @NotNull
    public static final Empty EMPTY_ACTIVE = new Empty(true);

    @Nullable
    public static final Object boxIncomplete(@Nullable Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    @Nullable
    public static final Object unboxState(@Nullable Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }

    public static /* synthetic */ void getCOMPLETING_ALREADY$annotations() {
    }

    public static /* synthetic */ void getCOMPLETING_RETRY$annotations() {
    }

    public static /* synthetic */ void getCOMPLETING_WAITING_CHILDREN$annotations() {
    }

    public static /* synthetic */ void getEMPTY_ACTIVE$annotations() {
    }

    public static /* synthetic */ void getEMPTY_NEW$annotations() {
    }

    public static /* synthetic */ void getSEALED$annotations() {
    }

    public static /* synthetic */ void getTOO_LATE_TO_CANCEL$annotations() {
    }
}
