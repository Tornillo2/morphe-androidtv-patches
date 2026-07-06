package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlinx.coroutines.EventLoopImplBase;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    @NotNull
    public abstract Thread getThread();

    public void reschedule(long j, @NotNull EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.INSTANCE.schedule(j, delayedTask);
    }

    public final void unpark() {
        Unit unit;
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
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
    }
}
