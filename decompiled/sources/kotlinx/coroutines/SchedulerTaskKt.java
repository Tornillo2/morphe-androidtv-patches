package kotlinx.coroutines;

import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SchedulerTaskKt {
    public static final void afterTask(@NotNull TaskContext taskContext) {
        taskContext.afterTask();
    }

    @NotNull
    public static final TaskContext getTaskContext(@NotNull Task task) {
        return task.taskContext;
    }

    public static /* synthetic */ void getTaskContext$annotations(Task task) {
    }
}
