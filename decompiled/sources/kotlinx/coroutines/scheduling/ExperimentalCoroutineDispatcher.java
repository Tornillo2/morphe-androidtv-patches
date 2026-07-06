package kotlinx.coroutines.scheduling;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public class ExperimentalCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    public final int corePoolSize;

    @NotNull
    public CoroutineScheduler coroutineScheduler;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;

    @NotNull
    public final String schedulerName;

    public ExperimentalCoroutineDispatcher(int i, int i2, long j, @NotNull String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        this.coroutineScheduler = createScheduler();
    }

    public static /* synthetic */ CoroutineDispatcher blocking$default(ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: blocking");
        }
        if ((i2 & 1) != 0) {
            i = 16;
        }
        return experimentalCoroutineDispatcher.blocking(i);
    }

    @NotNull
    public final CoroutineDispatcher blocking(int i) {
        if (i > 0) {
            return new LimitingDispatcher(this, i, null, 1);
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected positive parallelism level, but have ", i).toString());
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws InterruptedException {
        this.coroutineScheduler.close();
    }

    public final CoroutineScheduler createScheduler() {
        return new CoroutineScheduler(this.corePoolSize, this.maxPoolSize, this.idleWorkerKeepAliveNs, this.schedulerName);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* JADX INFO: renamed from: dispatch */
    public void mo2130dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Runnable runnable2;
        try {
            runnable2 = runnable;
            try {
                CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable2, null, false, 6, null);
            } catch (RejectedExecutionException unused) {
                DefaultExecutor.INSTANCE.enqueue(runnable2);
            }
        } catch (RejectedExecutionException unused2) {
            runnable2 = runnable;
        }
    }

    public final void dispatchWithContext$kotlinx_coroutines_core(@NotNull Runnable runnable, @NotNull TaskContext taskContext, boolean z) {
        try {
            this.coroutineScheduler.dispatch(runnable, taskContext, z);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.enqueue(this.coroutineScheduler.createTask(runnable, taskContext));
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Runnable runnable2;
        try {
            runnable2 = runnable;
            try {
                CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable2, null, true, 2, null);
            } catch (RejectedExecutionException unused) {
                DefaultExecutor.INSTANCE.mo2130dispatch(coroutineContext, runnable2);
            }
        } catch (RejectedExecutionException unused2) {
            runnable2 = runnable;
        }
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this.coroutineScheduler;
    }

    @NotNull
    public final CoroutineDispatcher limited(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected positive parallelism level, but have ", i).toString());
        }
        if (i <= this.corePoolSize) {
            return new LimitingDispatcher(this, i, null, 0);
        }
        throw new IllegalArgumentException(("Expected parallelism level lesser than core pool size (" + this.corePoolSize + "), but have " + i).toString());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        return super.toString() + "[scheduler = " + this.coroutineScheduler + AbstractJsonLexerKt.END_LIST;
    }

    public /* synthetic */ ExperimentalCoroutineDispatcher(int i, int i2, long j, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, j, (i3 & 8) != 0 ? "CoroutineScheduler" : str);
    }

    public /* synthetic */ ExperimentalCoroutineDispatcher(int i, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? TasksKt.CORE_POOL_SIZE : i, (i3 & 2) != 0 ? TasksKt.MAX_POOL_SIZE : i2, (i3 & 4) != 0 ? TasksKt.DEFAULT_SCHEDULER_NAME : str);
    }

    public ExperimentalCoroutineDispatcher(int i, int i2, @NotNull String str) {
        this(i, i2, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, str);
    }

    public /* synthetic */ ExperimentalCoroutineDispatcher(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? TasksKt.CORE_POOL_SIZE : i, (i3 & 2) != 0 ? TasksKt.MAX_POOL_SIZE : i2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility for Ktor 1.0-beta")
    public /* synthetic */ ExperimentalCoroutineDispatcher(int i, int i2) {
        this(i, i2, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, null, 8, null);
    }
}
