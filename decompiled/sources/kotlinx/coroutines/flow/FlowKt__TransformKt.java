package kotlinx.coroutines.flow;

import kotlin.BuilderInference;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class FlowKt__TransformKt {
    @NotNull
    public static final <T> Flow<T> filter(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__TransformKt$filter$$inlined$unsafeTransform$1(flow, function2);
    }

    public static final <R> Flow<R> filterIsInstance(Flow<?> flow) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> filterNot(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__TransformKt$filterNot$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T> Flow<T> filterNotNull(@NotNull Flow<? extends T> flow) {
        return new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(flow);
    }

    @NotNull
    public static final <T, R> Flow<R> map(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return new FlowKt__TransformKt$map$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T, R> Flow<R> mapNotNull(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return new FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T> Flow<T> onEach(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T, R> Flow<R> runningFold(@NotNull Flow<? extends T> flow, R r, @BuilderInference @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(r, flow, function3);
    }

    @NotNull
    public static final <T> Flow<T> runningReduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1(flow, function3);
    }

    @NotNull
    public static final <T, R> Flow<R> scan(@NotNull Flow<? extends T> flow, R r, @BuilderInference @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(r, flow, function3);
    }

    @NotNull
    public static final <T> Flow<IndexedValue<T>> withIndex(@NotNull Flow<? extends T> flow) {
        return new FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1(flow);
    }
}
