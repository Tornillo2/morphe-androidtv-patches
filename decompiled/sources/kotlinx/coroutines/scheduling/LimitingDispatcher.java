package kotlinx.coroutines.scheduling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class LimitingDispatcher extends ExecutorCoroutineDispatcher implements TaskContext, Executor {
    public static final /* synthetic */ AtomicIntegerFieldUpdater inFlightTasks$FU = AtomicIntegerFieldUpdater.newUpdater(LimitingDispatcher.class, "inFlightTasks");

    @NotNull
    public final ExperimentalCoroutineDispatcher dispatcher;

    @Nullable
    public final String name;
    public final int parallelism;
    public final int taskMode;

    @NotNull
    public final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

    @NotNull
    private volatile /* synthetic */ int inFlightTasks = 0;

    public LimitingDispatcher(@NotNull ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher, int i, @Nullable String str, int i2) {
        this.dispatcher = experimentalCoroutineDispatcher;
        this.parallelism = i;
        this.name = str;
        this.taskMode = i2;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
        Runnable runnablePoll = this.queue.poll();
        if (runnablePoll != null) {
            this.dispatcher.dispatchWithContext$kotlinx_coroutines_core(runnablePoll, this, true);
            return;
        }
        inFlightTasks$FU.decrementAndGet(this);
        Runnable runnablePoll2 = this.queue.poll();
        if (runnablePoll2 == null) {
            return;
        }
        dispatch(runnablePoll2, true);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* JADX INFO: renamed from: dispatch */
    public void mo2130dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        dispatch(runnable, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        dispatch(runnable, true);
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable runnable) {
        dispatch(runnable, false);
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        return super.toString() + "[dispatcher = " + this.dispatcher + AbstractJsonLexerKt.END_LIST;
    }

    public final void dispatch(Runnable runnable, boolean z) {
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = inFlightTasks$FU;
            if (atomicIntegerFieldUpdater.incrementAndGet(this) <= this.parallelism) {
                this.dispatcher.dispatchWithContext$kotlinx_coroutines_core(runnable, this, z);
                return;
            }
            this.queue.add(runnable);
            if (atomicIntegerFieldUpdater.decrementAndGet(this) >= this.parallelism) {
                return;
            } else {
                runnable = this.queue.poll();
            }
        } while (runnable != null);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this;
    }
}
