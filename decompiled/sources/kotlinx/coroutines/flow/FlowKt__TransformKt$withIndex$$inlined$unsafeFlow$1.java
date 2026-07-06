package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1<T> implements Flow<IndexedValue<? extends T>> {
    public final /* synthetic */ Flow $this_withIndex$inlined;

    public FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1(Flow flow) {
        this.$this_withIndex$inlined = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super IndexedValue<? extends T>> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Object objCollect = this.$this_withIndex$inlined.collect(new FlowKt__TransformKt$withIndex$1$1(flowCollector, new Ref.IntRef()), continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }
}
