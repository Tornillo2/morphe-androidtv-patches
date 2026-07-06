package kotlinx.coroutines;

import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public enum CoroutineStart {
    DEFAULT,
    LAZY,
    ATOMIC,
    UNDISPATCHED;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CoroutineStart.values().length];
            iArr[CoroutineStart.DEFAULT.ordinal()] = 1;
            iArr[CoroutineStart.ATOMIC.ordinal()] = 2;
            iArr[CoroutineStart.UNDISPATCHED.ordinal()] = 3;
            iArr[CoroutineStart.LAZY.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @InternalCoroutinesApi
    public final <T> void invoke(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            CancellableKt.startCoroutineCancellable(function1, continuation);
            return;
        }
        if (i == 2) {
            ContinuationKt.startCoroutine(function1, continuation);
        } else if (i == 3) {
            UndispatchedKt.startCoroutineUndispatched(function1, continuation);
        } else if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean isLazy() {
        return this == LAZY;
    }

    @InternalCoroutinesApi
    public final <R, T> void invoke(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            CancellableKt.startCoroutineCancellable$default(function2, r, continuation, null, 4, null);
            return;
        }
        if (i == 2) {
            ContinuationKt.startCoroutine(function2, r, continuation);
        } else if (i == 3) {
            UndispatchedKt.startCoroutineUndispatched(function2, r, continuation);
        } else if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
    }

    @InternalCoroutinesApi
    public static /* synthetic */ void isLazy$annotations() {
    }
}
