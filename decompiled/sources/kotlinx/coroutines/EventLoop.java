package kotlinx.coroutines;

import java.lang.reflect.InvocationTargetException;
import kotlinx.coroutines.internal.ArrayQueue;
import kotlinx.coroutines.internal.LimitedDispatcherKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class EventLoop extends CoroutineDispatcher {
    public boolean shared;

    @Nullable
    public ArrayQueue<DispatchedTask<?>> unconfinedQueue;
    public long useCount;

    public static /* synthetic */ void decrementUseCount$default(EventLoop eventLoop, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decrementUseCount");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        eventLoop.decrementUseCount(z);
    }

    public static /* synthetic */ void incrementUseCount$default(EventLoop eventLoop, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        eventLoop.incrementUseCount(z);
    }

    public final void decrementUseCount(boolean z) {
        long jDelta = this.useCount - delta(z);
        this.useCount = jDelta;
        if (jDelta <= 0 && this.shared) {
            shutdown();
        }
    }

    public final long delta(boolean z) {
        return z ? 4294967296L : 1L;
    }

    public final void dispatchUnconfined(@NotNull DispatchedTask<?> dispatchedTask) {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null) {
            arrayQueue = new ArrayQueue<>();
            this.unconfinedQueue = arrayQueue;
        }
        arrayQueue.addLast(dispatchedTask);
    }

    public long getNextTime() {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        return (arrayQueue == null || arrayQueue.isEmpty()) ? Long.MAX_VALUE : 0L;
    }

    public final void incrementUseCount(boolean z) {
        this.useCount = delta(z) + this.useCount;
        if (z) {
            return;
        }
        this.shared = true;
    }

    public final boolean isActive() {
        return this.useCount > 0;
    }

    public boolean isEmpty() {
        return isUnconfinedQueueEmpty();
    }

    public final boolean isUnconfinedLoopActive() {
        return this.useCount >= 4294967296L;
    }

    public final boolean isUnconfinedQueueEmpty() {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue != null) {
            return arrayQueue.isEmpty();
        }
        return true;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public final CoroutineDispatcher limitedParallelism(int i) {
        LimitedDispatcherKt.checkParallelism(i);
        return this;
    }

    public long processNextEvent() {
        return !processUnconfinedEvent() ? Long.MAX_VALUE : 0L;
    }

    public final boolean processUnconfinedEvent() throws IllegalAccessException, InvocationTargetException {
        DispatchedTask<?> dispatchedTaskRemoveFirstOrNull;
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null || (dispatchedTaskRemoveFirstOrNull = arrayQueue.removeFirstOrNull()) == null) {
            return false;
        }
        dispatchedTaskRemoveFirstOrNull.run();
        return true;
    }

    public boolean shouldBeProcessedFromContext() {
        return false;
    }

    public void shutdown() {
    }
}
