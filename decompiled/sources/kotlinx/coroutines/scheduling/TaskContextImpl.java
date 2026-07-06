package kotlinx.coroutines.scheduling;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class TaskContextImpl implements TaskContext {
    public final int taskMode;

    public TaskContextImpl(int i) {
        this.taskMode = i;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
    }
}
