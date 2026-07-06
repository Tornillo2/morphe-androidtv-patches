package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class DispatchedContinuationKt {

    @NotNull
    public static final Symbol UNDEFINED = new Symbol("UNDEFINED");

    @JvmField
    @NotNull
    public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");

    public static final boolean executeUnconfined(DispatchedContinuation<?> dispatchedContinuation, Object obj, int i, boolean z, Function0<Unit> function0) {
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (z && eventLoop$kotlinx_coroutines_core.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = obj;
            dispatchedContinuation.resumeMode = i;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            function0.invoke();
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
        return false;
    }

    public static /* synthetic */ boolean executeUnconfined$default(DispatchedContinuation dispatchedContinuation, Object obj, int i, boolean z, Function0 function0, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (z && eventLoop$kotlinx_coroutines_core.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = obj;
            dispatchedContinuation.resumeMode = i;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            function0.invoke();
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008d A[Catch: all -> 0x0068, DONT_GENERATE, TryCatch #0 {all -> 0x0068, blocks: (B:11:0x0043, B:13:0x0053, B:15:0x0059, B:28:0x0090, B:18:0x006a, B:20:0x007a, B:25:0x0087, B:27:0x008d, B:33:0x009d, B:36:0x00a6, B:35:0x00a3, B:23:0x0080), top: B:45:0x0043, inners: #1 }] */
    @kotlinx.coroutines.InternalCoroutinesApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> void resumeCancellableWith(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r6, @org.jetbrains.annotations.NotNull java.lang.Object r7, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r8) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            if (r0 == 0) goto Lb1
            kotlinx.coroutines.internal.DispatchedContinuation r6 = (kotlinx.coroutines.internal.DispatchedContinuation) r6
            java.lang.Object r8 = kotlinx.coroutines.CompletionStateKt.toState(r7, r8)
            kotlinx.coroutines.CoroutineDispatcher r0 = r6.dispatcher
            kotlin.coroutines.Continuation<T> r1 = r6.continuation
            kotlin.coroutines.CoroutineContext r1 = r1.getContext()
            boolean r0 = r0.isDispatchNeeded(r1)
            r1 = 1
            if (r0 == 0) goto L2a
            r6._state = r8
            r6.resumeMode = r1
            kotlinx.coroutines.CoroutineDispatcher r7 = r6.dispatcher
            kotlin.coroutines.Continuation<T> r8 = r6.continuation
            kotlin.coroutines.CoroutineContext r8 = r8.getContext()
            r7.mo2130dispatch(r8, r6)
            goto Lab
        L2a:
            kotlinx.coroutines.ThreadLocalEventLoop r0 = kotlinx.coroutines.ThreadLocalEventLoop.INSTANCE
            kotlinx.coroutines.EventLoop r0 = r0.getEventLoop$kotlinx_coroutines_core()
            boolean r2 = r0.isUnconfinedLoopActive()
            if (r2 == 0) goto L3f
            r6._state = r8
            r6.resumeMode = r1
            r0.dispatchUnconfined(r6)
            goto Lab
        L3f:
            r0.incrementUseCount(r1)
            r2 = 0
            kotlin.coroutines.Continuation<T> r3 = r6.continuation     // Catch: java.lang.Throwable -> L68
            kotlin.coroutines.CoroutineContext r3 = r3.getContext()     // Catch: java.lang.Throwable -> L68
            kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.Key     // Catch: java.lang.Throwable -> L68
            kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L68
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3     // Catch: java.lang.Throwable -> L68
            if (r3 == 0) goto L6a
            boolean r4 = r3.isActive()     // Catch: java.lang.Throwable -> L68
            if (r4 != 0) goto L6a
            java.util.concurrent.CancellationException r7 = r3.getCancellationException()     // Catch: java.lang.Throwable -> L68
            r6.cancelCompletedResult$kotlinx_coroutines_core(r8, r7)     // Catch: java.lang.Throwable -> L68
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)     // Catch: java.lang.Throwable -> L68
            r6.resumeWith(r7)     // Catch: java.lang.Throwable -> L68
            goto L90
        L68:
            r7 = move-exception
            goto La7
        L6a:
            kotlin.coroutines.Continuation<T> r8 = r6.continuation     // Catch: java.lang.Throwable -> L68
            java.lang.Object r3 = r6.countOrElement     // Catch: java.lang.Throwable -> L68
            kotlin.coroutines.CoroutineContext r4 = r8.getContext()     // Catch: java.lang.Throwable -> L68
            java.lang.Object r3 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r4, r3)     // Catch: java.lang.Throwable -> L68
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS     // Catch: java.lang.Throwable -> L68
            if (r3 == r5) goto L7f
            kotlinx.coroutines.UndispatchedCoroutine r8 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r8, r4, r3)     // Catch: java.lang.Throwable -> L68
            goto L80
        L7f:
            r8 = r2
        L80:
            kotlin.coroutines.Continuation<T> r5 = r6.continuation     // Catch: java.lang.Throwable -> L9a
            r5.resumeWith(r7)     // Catch: java.lang.Throwable -> L9a
            if (r8 == 0) goto L8d
            boolean r7 = r8.clearThreadContext()     // Catch: java.lang.Throwable -> L68
            if (r7 == 0) goto L90
        L8d:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r3)     // Catch: java.lang.Throwable -> L68
        L90:
            boolean r7 = r0.processUnconfinedEvent()     // Catch: java.lang.Throwable -> L68
            if (r7 != 0) goto L90
        L96:
            r0.decrementUseCount(r1)
            goto Lab
        L9a:
            r7 = move-exception
            if (r8 == 0) goto La3
            boolean r8 = r8.clearThreadContext()     // Catch: java.lang.Throwable -> L68
            if (r8 == 0) goto La6
        La3:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r3)     // Catch: java.lang.Throwable -> L68
        La6:
            throw r7     // Catch: java.lang.Throwable -> L68
        La7:
            r6.handleFatalException(r7, r2)     // Catch: java.lang.Throwable -> Lac
            goto L96
        Lab:
            return
        Lac:
            r6 = move-exception
            r0.decrementUseCount(r1)
            throw r6
        Lb1:
            r6.resumeWith(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.DispatchedContinuationKt.resumeCancellableWith(kotlin.coroutines.Continuation, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    public static /* synthetic */ void resumeCancellableWith$default(Continuation continuation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        resumeCancellableWith(continuation, obj, function1);
    }

    public static final boolean yieldUndispatched(@NotNull DispatchedContinuation<? super Unit> dispatchedContinuation) {
        Unit unit = Unit.INSTANCE;
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = unit;
            dispatchedContinuation.resumeMode = 1;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            dispatchedContinuation.run();
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
        return false;
    }

    public static /* synthetic */ void getREUSABLE_CLAIMED$annotations() {
    }

    public static /* synthetic */ void getUNDEFINED$annotations() {
    }
}
