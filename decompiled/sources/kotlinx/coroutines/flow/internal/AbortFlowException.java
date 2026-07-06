package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class AbortFlowException extends CancellationException {

    @JvmField
    @NotNull
    public final transient FlowCollector<?> owner;

    public AbortFlowException(@NotNull FlowCollector<?> flowCollector) {
        super("Flow was aborted, no more elements needed");
        this.owner = flowCollector;
    }

    @Override // java.lang.Throwable
    @NotNull
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
