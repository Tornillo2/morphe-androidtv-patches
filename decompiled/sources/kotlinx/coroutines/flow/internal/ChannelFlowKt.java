package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ChannelFlowKt {
    @NotNull
    public static final <T> ChannelFlow<T> asChannelFlow(@NotNull Flow<? extends T> flow) {
        ChannelFlow<T> channelFlow = flow instanceof ChannelFlow ? (ChannelFlow) flow : null;
        return channelFlow == null ? new ChannelFlowOperatorImpl(flow, null, 0, null, 14, null) : channelFlow;
    }

    @Nullable
    public static final <T, V> Object withContextUndispatched(@NotNull CoroutineContext coroutineContext, V v, @NotNull Object obj, @NotNull Function2<? super V, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            StackFrameContinuation stackFrameContinuation = new StackFrameContinuation(continuation, coroutineContext);
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
            Object objInvoke = function2.invoke(v, stackFrameContinuation);
            ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
            if (objInvoke == CoroutineSingletons.COROUTINE_SUSPENDED) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return objInvoke;
        } catch (Throwable th) {
            ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
            throw th;
        }
    }

    public static /* synthetic */ Object withContextUndispatched$default(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation, int i, Object obj3) {
        if ((i & 4) != 0) {
            obj2 = ThreadContextKt.threadContextElements(coroutineContext);
        }
        return withContextUndispatched(coroutineContext, obj, obj2, function2, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> FlowCollector<T> withUndispatchedContextCollector(FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext) {
        return flowCollector instanceof SendingCollector ? true : flowCollector instanceof NopCollector ? flowCollector : new UndispatchedContextCollector(flowCollector, coroutineContext);
    }
}
