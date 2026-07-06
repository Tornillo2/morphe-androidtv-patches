package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.EventLoopImplBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
    public static final int ACTIVE = 1;
    public static final long DEFAULT_KEEP_ALIVE_MS = 1000;
    public static final int FRESH = 0;

    @NotNull
    public static final DefaultExecutor INSTANCE;
    public static final long KEEP_ALIVE_NANOS;
    public static final int SHUTDOWN = 4;
    public static final int SHUTDOWN_ACK = 3;
    public static final int SHUTDOWN_REQ = 2;

    @NotNull
    public static final String THREAD_NAME = "kotlinx.coroutines.DefaultExecutor";

    @Nullable
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    static {
        Long l;
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        INSTANCE = defaultExecutor;
        EventLoop.incrementUseCount$default(defaultExecutor, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        KEEP_ALIVE_NANOS = timeUnit.toNanos(l.longValue());
    }

    public final synchronized void acknowledgeShutdownIfNeeded() {
        if (isShutdownRequested()) {
            debugStatus = 3;
            resetAll();
            notifyAll();
        }
    }

    public final synchronized Thread createThreadSync() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, THREAD_NAME);
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public void enqueue(@NotNull Runnable runnable) {
        if (isShutDown()) {
            shutdownError();
            throw null;
        }
        super.enqueue(runnable);
    }

    public final synchronized void ensureStarted$kotlinx_coroutines_core() {
        debugStatus = 0;
        createThreadSync();
        while (debugStatus == 0) {
            wait();
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    @NotNull
    public Thread getThread() {
        Thread thread = _thread;
        return thread == null ? createThreadSync() : thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        return scheduleInvokeOnTimeout(j, runnable);
    }

    public final boolean isShutDown() {
        return debugStatus == 4;
    }

    public final boolean isShutdownRequested() {
        int i = debugStatus;
        return i == 2 || i == 3;
    }

    public final boolean isThreadPresent$kotlinx_coroutines_core() {
        return _thread != null;
    }

    public final synchronized boolean notifyStartup() {
        if (isShutdownRequested()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public void reschedule(long j, @NotNull EventLoopImplBase.DelayedTask delayedTask) {
        shutdownError();
        throw null;
    }

    @Override // java.lang.Runnable
    public void run() {
        Unit unit;
        ThreadLocalEventLoop.INSTANCE.setEventLoop$kotlinx_coroutines_core(this);
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.registerTimeLoopThread();
        }
        try {
            if (!notifyStartup()) {
                _thread = null;
                acknowledgeShutdownIfNeeded();
                AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.timeSource;
                if (abstractTimeSource2 != null) {
                    abstractTimeSource2.unregisterTimeLoopThread();
                }
                if (isEmpty()) {
                    return;
                }
                getThread();
                return;
            }
            long j = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long jProcessNextEvent = processNextEvent();
                if (jProcessNextEvent == Long.MAX_VALUE) {
                    AbstractTimeSource abstractTimeSource3 = AbstractTimeSourceKt.timeSource;
                    long jNanoTime = abstractTimeSource3 != null ? abstractTimeSource3.nanoTime() : System.nanoTime();
                    if (j == Long.MAX_VALUE) {
                        j = KEEP_ALIVE_NANOS + jNanoTime;
                    }
                    long j2 = j - jNanoTime;
                    if (j2 <= 0) {
                        _thread = null;
                        acknowledgeShutdownIfNeeded();
                        AbstractTimeSource abstractTimeSource4 = AbstractTimeSourceKt.timeSource;
                        if (abstractTimeSource4 != null) {
                            abstractTimeSource4.unregisterTimeLoopThread();
                        }
                        if (isEmpty()) {
                            return;
                        }
                        getThread();
                        return;
                    }
                    if (jProcessNextEvent > j2) {
                        jProcessNextEvent = j2;
                    }
                } else {
                    j = Long.MAX_VALUE;
                }
                if (jProcessNextEvent > 0) {
                    if (isShutdownRequested()) {
                        _thread = null;
                        acknowledgeShutdownIfNeeded();
                        AbstractTimeSource abstractTimeSource5 = AbstractTimeSourceKt.timeSource;
                        if (abstractTimeSource5 != null) {
                            abstractTimeSource5.unregisterTimeLoopThread();
                        }
                        if (isEmpty()) {
                            return;
                        }
                        getThread();
                        return;
                    }
                    AbstractTimeSource abstractTimeSource6 = AbstractTimeSourceKt.timeSource;
                    if (abstractTimeSource6 != null) {
                        abstractTimeSource6.parkNanos(this, jProcessNextEvent);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        LockSupport.parkNanos(this, jProcessNextEvent);
                    }
                }
            }
        } catch (Throwable th) {
            _thread = null;
            acknowledgeShutdownIfNeeded();
            AbstractTimeSource abstractTimeSource7 = AbstractTimeSourceKt.timeSource;
            if (abstractTimeSource7 != null) {
                abstractTimeSource7.unregisterTimeLoopThread();
            }
            if (!isEmpty()) {
                getThread();
            }
            throw th;
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.EventLoop
    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    public final void shutdownError() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    public final synchronized void shutdownForTests(long j) {
        Unit unit;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() + j;
            if (!isShutdownRequested()) {
                debugStatus = 2;
            }
            while (debugStatus != 3 && _thread != null) {
                Thread thread = _thread;
                if (thread != null) {
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
                if (jCurrentTimeMillis - System.currentTimeMillis() <= 0) {
                    break;
                } else {
                    wait(j);
                }
            }
            debugStatus = 0;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static /* synthetic */ void get_thread$annotations() {
    }
}
