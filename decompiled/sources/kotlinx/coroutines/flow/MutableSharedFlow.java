package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface MutableSharedFlow<T> extends SharedFlow<T>, FlowCollector<T> {
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    Object emit(T t, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    StateFlow<Integer> getSubscriptionCount();

    @ExperimentalCoroutinesApi
    void resetReplayCache();

    boolean tryEmit(T t);
}
