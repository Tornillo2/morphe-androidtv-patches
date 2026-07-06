package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AbstractTimeSourceKt {

    @Nullable
    public static AbstractTimeSource timeSource;

    @InlineOnly
    public static final long currentTimeMillis() {
        AbstractTimeSource abstractTimeSource = timeSource;
        return abstractTimeSource != null ? abstractTimeSource.currentTimeMillis() : System.currentTimeMillis();
    }

    @Nullable
    public static final AbstractTimeSource getTimeSource() {
        return timeSource;
    }

    @InlineOnly
    public static final long nanoTime() {
        AbstractTimeSource abstractTimeSource = timeSource;
        return abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime();
    }

    @InlineOnly
    public static final void parkNanos(Object obj, long j) {
        Unit unit;
        AbstractTimeSource abstractTimeSource = timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.parkNanos(obj, j);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            LockSupport.parkNanos(obj, j);
        }
    }

    @InlineOnly
    public static final void registerTimeLoopThread() {
        AbstractTimeSource abstractTimeSource = timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.registerTimeLoopThread();
        }
    }

    public static final void setTimeSource(@Nullable AbstractTimeSource abstractTimeSource) {
        timeSource = abstractTimeSource;
    }

    @InlineOnly
    public static final void trackTask() {
        AbstractTimeSource abstractTimeSource = timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.trackTask();
        }
    }

    @InlineOnly
    public static final void unTrackTask() {
        AbstractTimeSource abstractTimeSource = timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.unTrackTask();
        }
    }

    @InlineOnly
    public static final void unpark(Thread thread) {
        Unit unit;
        AbstractTimeSource abstractTimeSource = timeSource;
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

    @InlineOnly
    public static final void unregisterTimeLoopThread() {
        AbstractTimeSource abstractTimeSource = timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.unregisterTimeLoopThread();
        }
    }

    @InlineOnly
    public static final Runnable wrapTask(Runnable runnable) {
        Runnable runnableWrapTask;
        AbstractTimeSource abstractTimeSource = timeSource;
        return (abstractTimeSource == null || (runnableWrapTask = abstractTimeSource.wrapTask(runnable)) == null) ? runnable : runnableWrapTask;
    }
}
