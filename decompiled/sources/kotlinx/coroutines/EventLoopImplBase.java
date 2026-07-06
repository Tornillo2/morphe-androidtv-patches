package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");

    @NotNull
    private volatile /* synthetic */ Object _queue = null;

    @NotNull
    private volatile /* synthetic */ Object _delayed = null;

    @NotNull
    private volatile /* synthetic */ int _isCompleted = 0;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class DelayedResumeTask extends DelayedTask {

        @NotNull
        public final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public DelayedResumeTask(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            super(j);
            this.cont = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.cont.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        @NotNull
        public String toString() {
            return super.toString() + this.cont;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DelayedRunnableTask extends DelayedTask {

        @NotNull
        public final Runnable block;

        public DelayedRunnableTask(long j, @NotNull Runnable runnable) {
            super(j);
            this.block = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.block.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        @NotNull
        public String toString() {
            return super.toString() + this.block;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {

        @Nullable
        private volatile Object _heap;
        public int index = -1;

        @JvmField
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            try {
                Object obj = this._heap;
                Symbol symbol = EventLoop_commonKt.DISPOSED_TASK;
                if (obj == symbol) {
                    return;
                }
                DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
                if (delayedTaskQueue != null) {
                    delayedTaskQueue.remove(this);
                }
                this._heap = symbol;
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        @Nullable
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        public final synchronized int scheduleTask(long j, @NotNull DelayedTaskQueue delayedTaskQueue, @NotNull EventLoopImplBase eventLoopImplBase) {
            if (this._heap == EventLoop_commonKt.DISPOSED_TASK) {
                return 2;
            }
            synchronized (delayedTaskQueue) {
                try {
                    DelayedTask delayedTaskFirstImpl = delayedTaskQueue.firstImpl();
                    if (eventLoopImplBase._isCompleted) {
                        return 1;
                    }
                    if (delayedTaskFirstImpl == null) {
                        delayedTaskQueue.timeNow = j;
                    } else {
                        long j2 = delayedTaskFirstImpl.nanoTime;
                        if (j2 - j < 0) {
                            j = j2;
                        }
                        if (j - delayedTaskQueue.timeNow > 0) {
                            delayedTaskQueue.timeNow = j;
                        }
                    }
                    long j3 = this.nanoTime;
                    long j4 = delayedTaskQueue.timeNow;
                    if (j3 - j4 < 0) {
                        this.nanoTime = j4;
                    }
                    delayedTaskQueue.addImpl(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
            if (this._heap == EventLoop_commonKt.DISPOSED_TASK) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            this._heap = threadSafeHeap;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.index = i;
        }

        public final boolean timeToExecute(long j) {
            return j - this.nanoTime >= 0;
        }

        @NotNull
        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + AbstractJsonLexerKt.END_LIST;
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull DelayedTask delayedTask) {
            long j = this.nanoTime - delayedTask.nanoTime;
            if (j > 0) {
                return 1;
            }
            return j < 0 ? -1 : 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {

        @JvmField
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private final boolean isCompleted() {
        return this._isCompleted;
    }

    public final void closeQueue() {
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, null, EventLoop_commonKt.CLOSED_EMPTY)) {
                    return;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                ((LockFreeTaskQueueCore) obj).close();
                return;
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    return;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, lockFreeTaskQueueCore)) {
                    return;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    public final Runnable dequeue() {
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                Object objRemoveFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                if (objRemoveFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    return (Runnable) objRemoveFirstOrNull;
                }
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, lockFreeTaskQueueCore.next());
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    return null;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, null)) {
                    return (Runnable) obj;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* JADX INFO: renamed from: dispatch */
    public final void mo2130dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        enqueue(runnable);
    }

    public void enqueue(@NotNull Runnable runnable) {
        if (enqueueImpl(runnable)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    public final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            Object obj = this._queue;
            if (this._isCompleted != 0) {
                return false;
            }
            if (obj == null) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                int iAddLast = lockFreeTaskQueueCore.addLast(runnable);
                if (iAddLast == 0) {
                    return true;
                }
                if (iAddLast == 1) {
                    AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, lockFreeTaskQueueCore.next());
                } else if (iAddLast == 2) {
                    return false;
                }
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore2.addLast((Runnable) obj);
                lockFreeTaskQueueCore2.addLast(runnable);
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, lockFreeTaskQueueCore2)) {
                    return true;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.EventLoop
    public long getNextTime() {
        DelayedTask delayedTaskPeek;
        if (super.getNextTime() == 0) {
            return 0L;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (!(obj instanceof LockFreeTaskQueueCore)) {
                return obj == EventLoop_commonKt.CLOSED_EMPTY ? Long.MAX_VALUE : 0L;
            }
            if (!((LockFreeTaskQueueCore) obj).isEmpty()) {
                return 0L;
            }
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue == null || (delayedTaskPeek = delayedTaskQueue.peek()) == null) {
            return Long.MAX_VALUE;
        }
        long j = delayedTaskPeek.nanoTime;
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        long jNanoTime = j - (abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime());
        if (jNanoTime < 0) {
            return 0L;
        }
        return jNanoTime;
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        return Delay.DefaultImpls.invokeOnTimeout(this, j, runnable, coroutineContext);
    }

    @Override // kotlinx.coroutines.EventLoop
    public boolean isEmpty() {
        if (!isUnconfinedQueueEmpty()) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue != null && !delayedTaskQueue.isEmpty()) {
            return false;
        }
        Object obj = this._queue;
        if (obj == null) {
            return true;
        }
        return obj instanceof LockFreeTaskQueueCore ? ((LockFreeTaskQueueCore) obj).isEmpty() : obj == EventLoop_commonKt.CLOSED_EMPTY;
    }

    @Override // kotlinx.coroutines.EventLoop
    public long processNextEvent() {
        DelayedTask delayedTaskRemoveAtImpl;
        if (processUnconfinedEvent()) {
            return 0L;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue != null && !delayedTaskQueue.isEmpty()) {
            AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
            long jNanoTime = abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime();
            do {
                synchronized (delayedTaskQueue) {
                    try {
                        DelayedTask delayedTaskFirstImpl = delayedTaskQueue.firstImpl();
                        if (delayedTaskFirstImpl != null) {
                            DelayedTask delayedTask = delayedTaskFirstImpl;
                            delayedTaskRemoveAtImpl = delayedTask.timeToExecute(jNanoTime) ? enqueueImpl(delayedTask) : false ? delayedTaskQueue.removeAtImpl(0) : null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } while (delayedTaskRemoveAtImpl != null);
        }
        Runnable runnableDequeue = dequeue();
        if (runnableDequeue == null) {
            return getNextTime();
        }
        runnableDequeue.run();
        return 0L;
    }

    public final void rescheduleAllDelayed() {
        DelayedTask delayedTaskRemoveFirstOrNull;
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        long jNanoTime = abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
            if (delayedTaskQueue == null || (delayedTaskRemoveFirstOrNull = delayedTaskQueue.removeFirstOrNull()) == null) {
                return;
            } else {
                reschedule(jNanoTime, delayedTaskRemoveFirstOrNull);
            }
        }
    }

    public final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    public final void schedule(long j, @NotNull DelayedTask delayedTask) {
        int iScheduleImpl = scheduleImpl(j, delayedTask);
        if (iScheduleImpl == 0) {
            if (shouldUnpark(delayedTask)) {
                unpark();
            }
        } else if (iScheduleImpl == 1) {
            reschedule(j, delayedTask);
        } else if (iScheduleImpl != 2) {
            throw new IllegalStateException("unexpected result");
        }
    }

    public final int scheduleImpl(long j, DelayedTask delayedTask) {
        if (this._isCompleted != 0) {
            return 1;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue == null) {
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_delayed$FU, this, null, new DelayedTaskQueue(j));
            Object obj = this._delayed;
            Intrinsics.checkNotNull(obj);
            delayedTaskQueue = (DelayedTaskQueue) obj;
        }
        return delayedTask.scheduleTask(j, delayedTaskQueue, this);
    }

    @NotNull
    public final DisposableHandle scheduleInvokeOnTimeout(long j, @NotNull Runnable runnable) {
        long jDelayToNanos = EventLoop_commonKt.delayToNanos(j);
        if (jDelayToNanos >= 4611686018427387903L) {
            return NonDisposableHandle.INSTANCE;
        }
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        long jNanoTime = abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime();
        DelayedRunnableTask delayedRunnableTask = new DelayedRunnableTask(jDelayToNanos + jNanoTime, runnable);
        schedule(jNanoTime, delayedRunnableTask);
        return delayedRunnableTask;
    }

    @Override // kotlinx.coroutines.Delay
    /* JADX INFO: renamed from: scheduleResumeAfterDelay */
    public void mo2131scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        long jDelayToNanos = EventLoop_commonKt.delayToNanos(j);
        if (jDelayToNanos < 4611686018427387903L) {
            AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
            long jNanoTime = abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(jDelayToNanos + jNanoTime, cancellableContinuation);
            schedule(jNanoTime, delayedResumeTask);
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, delayedResumeTask);
        }
    }

    public final void setCompleted(boolean z) {
        this._isCompleted = z ? 1 : 0;
    }

    public final boolean shouldUnpark(DelayedTask delayedTask) {
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        return (delayedTaskQueue != null ? delayedTaskQueue.peek() : null) == delayedTask;
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        this._isCompleted = 1;
        closeQueue();
        while (processNextEvent() <= 0) {
        }
        rescheduleAllDelayed();
    }
}
