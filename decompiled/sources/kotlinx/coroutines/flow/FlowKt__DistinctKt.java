package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class FlowKt__DistinctKt {

    @NotNull
    public static final Function1<Object, Object> defaultKeySelector = FlowKt__DistinctKt$defaultKeySelector$1.INSTANCE;

    @NotNull
    public static final Function2<Object, Object, Boolean> defaultAreEquivalent = FlowKt__DistinctKt$defaultAreEquivalent$1.INSTANCE;

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Flow<T> distinctUntilChanged(@NotNull Flow<? extends T> flow) {
        return flow instanceof StateFlow ? flow : distinctUntilChangedBy$FlowKt__DistinctKt(flow, defaultKeySelector, defaultAreEquivalent);
    }

    @NotNull
    public static final <T, K> Flow<T> distinctUntilChangedBy(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends K> function1) {
        return distinctUntilChangedBy$FlowKt__DistinctKt(flow, function1, defaultAreEquivalent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> distinctUntilChangedBy$FlowKt__DistinctKt(Flow<? extends T> flow, Function1<? super T, ? extends Object> function1, Function2<Object, Object, Boolean> function2) {
        if (flow instanceof DistinctFlowImpl) {
            DistinctFlowImpl distinctFlowImpl = (DistinctFlowImpl) flow;
            if (distinctFlowImpl.keySelector == function1 && distinctFlowImpl.areEquivalent == function2) {
                return flow;
            }
        }
        return new DistinctFlowImpl(flow, function1, function2);
    }

    @NotNull
    public static final <T> Flow<T> distinctUntilChanged(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super T, Boolean> function2) {
        Function1<Object, Object> function1 = defaultKeySelector;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
        return distinctUntilChangedBy$FlowKt__DistinctKt(flow, function1, function2);
    }

    public static /* synthetic */ void getDefaultAreEquivalent$annotations$FlowKt__DistinctKt() {
    }

    public static /* synthetic */ void getDefaultKeySelector$annotations$FlowKt__DistinctKt() {
    }
}
