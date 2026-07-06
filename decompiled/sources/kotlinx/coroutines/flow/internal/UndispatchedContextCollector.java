package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class UndispatchedContextCollector<T> implements FlowCollector<T> {

    @NotNull
    public final Object countOrElement;

    @NotNull
    public final CoroutineContext emitContext;

    @NotNull
    public final Function2<T, Continuation<? super Unit>, Object> emitRef;

    public UndispatchedContextCollector(@NotNull FlowCollector<? super T> flowCollector, @NotNull CoroutineContext coroutineContext) {
        this.emitContext = coroutineContext;
        this.countOrElement = ThreadContextKt.threadContextElements(coroutineContext);
        this.emitRef = new UndispatchedContextCollector$emitRef$1(flowCollector, null);
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
        Object objWithContextUndispatched = ChannelFlowKt.withContextUndispatched(this.emitContext, t, this.countOrElement, this.emitRef, continuation);
        return objWithContextUndispatched == CoroutineSingletons.COROUTINE_SUSPENDED ? objWithContextUndispatched : Unit.INSTANCE;
    }
}
