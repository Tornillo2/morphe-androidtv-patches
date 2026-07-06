package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class TasksKt {

    @JvmField
    @NotNull
    public static final TaskContext BlockingContext;

    @JvmField
    public static final int CORE_POOL_SIZE;

    @NotNull
    public static final String DEFAULT_SCHEDULER_NAME = "DefaultDispatcher";

    @JvmField
    public static final long IDLE_WORKER_KEEP_ALIVE_NS;

    @JvmField
    public static final int MAX_POOL_SIZE;

    @JvmField
    @NotNull
    public static final TaskContext NonBlockingContext;
    public static final int TASK_NON_BLOCKING = 0;
    public static final int TASK_PROBABLY_BLOCKING = 1;

    @JvmField
    public static final long WORK_STEALING_TIME_RESOLUTION_NS = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, (Object) null);

    @JvmField
    @NotNull
    public static SchedulerTimeSource schedulerTimeSource;

    static {
        int available_processors = SystemPropsKt__SystemPropsKt.getAVAILABLE_PROCESSORS();
        CORE_POOL_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.core.pool.size", available_processors < 2 ? 2 : available_processors, 1, 0, 8, (Object) null);
        MAX_POOL_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 0, CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 4, (Object) null);
        IDLE_WORKER_KEEP_ALIVE_NS = TimeUnit.SECONDS.toNanos(SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, (Object) null));
        schedulerTimeSource = NanoTimeSource.INSTANCE;
        NonBlockingContext = new TaskContextImpl(0);
        BlockingContext = new TaskContextImpl(1);
    }

    public static final boolean isBlocking(@NotNull Task task) {
        return task.taskContext.getTaskMode() == 1;
    }
}
