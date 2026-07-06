package kotlinx.coroutines.flow.internal;

import kotlin.PublishedApi;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowExceptions_commonKt {
    @PublishedApi
    public static final int checkIndexOverflow(int i) {
        if (i >= 0) {
            return i;
        }
        throw new ArithmeticException("Index overflow has happened");
    }

    public static final void checkOwnership(@NotNull AbortFlowException abortFlowException, @NotNull FlowCollector<?> flowCollector) {
        if (abortFlowException.owner != flowCollector) {
            throw abortFlowException;
        }
    }
}
