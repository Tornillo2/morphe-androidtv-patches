package kotlinx.coroutines.scheduling;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class TaskImpl extends Task {

    @JvmField
    @NotNull
    public final Runnable block;

    public TaskImpl(@NotNull Runnable runnable, long j, @NotNull TaskContext taskContext) {
        super(j, taskContext);
        this.block = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.block.run();
        } finally {
            this.taskContext.afterTask();
        }
    }

    @NotNull
    public String toString() {
        return "Task[" + DebugStringsKt.getClassSimpleName(this.block) + ObjectUtils.AT_SIGN + DebugStringsKt.getHexAddress(this.block) + ", " + this.submissionTime + ", " + this.taskContext + AbstractJsonLexerKt.END_LIST;
    }
}
