package kotlinx.coroutines.scheduling;

import kotlin.jvm.JvmName;
import kotlinx.coroutines.scheduling.CoroutineScheduler;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CoroutineSchedulerKt {
    @JvmName(name = "isSchedulerWorker")
    public static final boolean isSchedulerWorker(@NotNull Thread thread) {
        return thread instanceof CoroutineScheduler.Worker;
    }

    @JvmName(name = "mayNotBlock")
    public static final boolean mayNotBlock(@NotNull Thread thread) {
        return (thread instanceof CoroutineScheduler.Worker) && ((CoroutineScheduler.Worker) thread).state == CoroutineScheduler.WorkerState.CPU_ACQUIRED;
    }
}
