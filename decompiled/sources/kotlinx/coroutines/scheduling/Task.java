package kotlinx.coroutines.scheduling;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class Task implements Runnable {

    @JvmField
    public long submissionTime;

    @JvmField
    @NotNull
    public TaskContext taskContext;

    public Task(long j, @NotNull TaskContext taskContext) {
        this.submissionTime = j;
        this.taskContext = taskContext;
    }

    public final int getMode() {
        return this.taskContext.getTaskMode();
    }

    public Task() {
        this(0L, TasksKt.NonBlockingContext);
    }
}
