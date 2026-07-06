package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.CombineKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$1<R> implements Flow<R> {
    public final /* synthetic */ Flow $flow$inlined;
    public final /* synthetic */ Flow $this_combine$inlined;
    public final /* synthetic */ Function3 $transform$inlined;

    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(Flow flow, Flow flow2, Function3 function3) {
        this.$this_combine$inlined = flow;
        this.$flow$inlined = flow2;
        this.$transform$inlined = function3;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super R> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, new Flow[]{this.$this_combine$inlined, this.$flow$inlined}, FlowKt__ZipKt$nullArrayFactory$1.INSTANCE, new FlowKt__ZipKt$combine$1$1(this.$transform$inlined, null), continuation);
        return objCombineInternal == CoroutineSingletons.COROUTINE_SUSPENDED ? objCombineInternal : Unit.INSTANCE;
    }
}
