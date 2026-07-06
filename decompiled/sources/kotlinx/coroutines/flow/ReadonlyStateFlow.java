package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ReadonlyStateFlow<T> implements StateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    public final /* synthetic */ StateFlow<T> $$delegate_0;

    @Nullable
    public final Job job;

    /* JADX WARN: Multi-variable type inference failed */
    public ReadonlyStateFlow(@NotNull StateFlow<? extends T> stateFlow, @Nullable Job job) {
        this.job = job;
        this.$$delegate_0 = stateFlow;
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<?> continuation) {
        return this.$$delegate_0.collect(flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    @NotNull
    public Flow<T> fuse(@NotNull CoroutineContext coroutineContext, int i, @NotNull BufferOverflow bufferOverflow) {
        return StateFlowKt.fuseStateFlow(this, coroutineContext, i, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    @NotNull
    public List<T> getReplayCache() {
        return this.$$delegate_0.getReplayCache();
    }

    @Override // kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        return this.$$delegate_0.getValue();
    }
}
