package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DispatcherExecutor implements Executor {

    @JvmField
    @NotNull
    public final CoroutineDispatcher dispatcher;

    public DispatcherExecutor(@NotNull CoroutineDispatcher coroutineDispatcher) {
        this.dispatcher = coroutineDispatcher;
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable runnable) {
        this.dispatcher.mo2130dispatch(EmptyCoroutineContext.INSTANCE, runnable);
    }

    @NotNull
    public String toString() {
        return this.dispatcher.toString();
    }
}
