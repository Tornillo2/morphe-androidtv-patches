package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BlockingCoroutine<T> extends AbstractCoroutine<T> {

    @NotNull
    public final Thread blockedThread;

    @Nullable
    public final EventLoop eventLoop;

    public BlockingCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Thread thread, @Nullable EventLoop eventLoop) {
        super(coroutineContext, true, true);
        this.blockedThread = thread;
        this.eventLoop = eventLoop;
    }

    @Override // kotlinx.coroutines.JobSupport
    public void afterCompletion(@Nullable Object obj) {
        Unit unit;
        if (Intrinsics.areEqual(Thread.currentThread(), this.blockedThread)) {
            return;
        }
        Thread thread = this.blockedThread;
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.unpark(thread);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            LockSupport.unpark(thread);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final T joinBlocking() throws Throwable {
        Unit unit;
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.registerTimeLoopThread();
        }
        try {
            EventLoop eventLoop = this.eventLoop;
            if (eventLoop != null) {
                EventLoop.incrementUseCount$default(eventLoop, false, 1, null);
            }
            while (!Thread.interrupted()) {
                try {
                    EventLoop eventLoop2 = this.eventLoop;
                    long jProcessNextEvent = eventLoop2 != null ? eventLoop2.processNextEvent() : Long.MAX_VALUE;
                    if (isCompleted()) {
                        EventLoop eventLoop3 = this.eventLoop;
                        if (eventLoop3 != null) {
                            EventLoop.decrementUseCount$default(eventLoop3, false, 1, null);
                        }
                        T t = (T) JobSupportKt.unboxState(getState$kotlinx_coroutines_core());
                        CompletedExceptionally completedExceptionally = t instanceof CompletedExceptionally ? (CompletedExceptionally) t : null;
                        if (completedExceptionally == null) {
                            return t;
                        }
                        throw completedExceptionally.cause;
                    }
                    AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.timeSource;
                    if (abstractTimeSource2 != null) {
                        abstractTimeSource2.parkNanos(this, jProcessNextEvent);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        LockSupport.parkNanos(this, jProcessNextEvent);
                    }
                } catch (Throwable th) {
                    EventLoop eventLoop4 = this.eventLoop;
                    if (eventLoop4 != null) {
                        EventLoop.decrementUseCount$default(eventLoop4, false, 1, null);
                    }
                    throw th;
                }
            }
            InterruptedException interruptedException = new InterruptedException();
            cancelImpl$kotlinx_coroutines_core(interruptedException);
            throw interruptedException;
        } finally {
            AbstractTimeSource abstractTimeSource3 = AbstractTimeSourceKt.timeSource;
            if (abstractTimeSource3 != null) {
                abstractTimeSource3.unregisterTimeLoopThread();
            }
        }
    }
}
