package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ThreadState implements Function1<Throwable, Unit> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _state$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state");

    @Nullable
    public DisposableHandle cancelHandle;

    @NotNull
    public final Job job;

    @NotNull
    private volatile /* synthetic */ int _state = 0;
    public final Thread targetThread = Thread.currentThread();

    public ThreadState(@NotNull Job job) {
        this.job = job;
    }

    public final void clearInterrupt() {
        while (true) {
            int i = this._state;
            if (i != 0) {
                if (i != 2) {
                    if (i == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        invalidState(i);
                        throw null;
                    }
                }
            } else if (_state$FU.compareAndSet(this, i, 1)) {
                DisposableHandle disposableHandle = this.cancelHandle;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                    return;
                }
                return;
            }
        }
    }

    public final Void invalidState(int i) {
        throw new IllegalStateException(("Illegal state " + i).toString());
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    public final void setup() {
        int i;
        this.cancelHandle = this.job.invokeOnCompletion(true, true, this);
        do {
            i = this._state;
            if (i != 0) {
                if (i == 2 || i == 3) {
                    return;
                }
                invalidState(i);
                throw null;
            }
        } while (!_state$FU.compareAndSet(this, i, 0));
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        int i;
        do {
            i = this._state;
            if (i != 0) {
                if (i == 1 || i == 2 || i == 3) {
                    return;
                }
                invalidState(i);
                throw null;
            }
        } while (!_state$FU.compareAndSet(this, i, 2));
        this.targetThread.interrupt();
        this._state = 3;
    }
}
