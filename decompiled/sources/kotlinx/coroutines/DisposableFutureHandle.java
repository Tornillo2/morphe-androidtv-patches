package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DisposableFutureHandle implements DisposableHandle {

    @NotNull
    public final Future<?> future;

    public DisposableFutureHandle(@NotNull Future<?> future) {
        this.future = future;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        this.future.cancel(false);
    }

    @NotNull
    public String toString() {
        return "DisposableFutureHandle[" + this.future + AbstractJsonLexerKt.END_LIST;
    }
}
