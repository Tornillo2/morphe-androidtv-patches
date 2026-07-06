package kotlinx.coroutines.flow.internal;

import kotlin.BuilderInference;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowCoroutineKt {
    @Nullable
    public static final <R> Object flowScope(@BuilderInference @NotNull Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        FlowCoroutine flowCoroutine = new FlowCoroutine(continuation.getContext(), continuation);
        Object objStartUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(flowCoroutine, flowCoroutine, function2);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return objStartUndispatchedOrReturn;
    }

    @NotNull
    public static final <R> Flow<R> scopedFlow(@BuilderInference @NotNull Function3<? super CoroutineScope, ? super FlowCollector<? super R>, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1(function3);
    }
}
