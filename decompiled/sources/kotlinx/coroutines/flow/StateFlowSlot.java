package kotlinx.coroutines.flow;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");

    @NotNull
    volatile /* synthetic */ Object _state = null;

    @Nullable
    public final Object awaitPending(@NotNull Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, StateFlowKt.NONE, cancellableContinuationImpl)) {
            cancellableContinuationImpl.resumeWith(Unit.INSTANCE);
        }
        Object result = cancellableContinuationImpl.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public Continuation[] freeLocked(StateFlowImpl<?> stateFlowImpl) {
        this._state = null;
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final void makePending() {
        Symbol symbol;
        while (true) {
            Object obj = this._state;
            if (obj == null || obj == (symbol = StateFlowKt.PENDING)) {
                return;
            }
            Symbol symbol2 = StateFlowKt.NONE;
            if (obj == symbol2) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, symbol)) {
                    return;
                }
            } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, symbol2)) {
                ((CancellableContinuationImpl) obj).resumeWith(Unit.INSTANCE);
                return;
            }
        }
    }

    public final boolean takePending() {
        Object andSet = _state$FU.getAndSet(this, StateFlowKt.NONE);
        Intrinsics.checkNotNull(andSet);
        return andSet == StateFlowKt.PENDING;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(@NotNull StateFlowImpl<?> stateFlowImpl) {
        if (this._state != null) {
            return false;
        }
        this._state = StateFlowKt.NONE;
        return true;
    }

    @NotNull
    /* JADX INFO: renamed from: freeLocked, reason: avoid collision after fix types in other method */
    public Continuation<Unit>[] freeLocked2(@NotNull StateFlowImpl<?> stateFlowImpl) {
        this._state = null;
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }
}
