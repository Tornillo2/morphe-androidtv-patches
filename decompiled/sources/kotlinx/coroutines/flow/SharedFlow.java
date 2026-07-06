package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface SharedFlow<T> extends Flow<T> {
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<?> continuation);

    @NotNull
    List<T> getReplayCache();
}
