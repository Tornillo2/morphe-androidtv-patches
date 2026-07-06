package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CombineKt$zipImpl$$inlined$unsafeFlow$1<R> implements Flow<R> {
    public final /* synthetic */ Flow $flow$inlined;
    public final /* synthetic */ Flow $flow2$inlined;
    public final /* synthetic */ Function3 $transform$inlined;

    public CombineKt$zipImpl$$inlined$unsafeFlow$1(Flow flow, Flow flow2, Function3 function3) {
        this.$flow2$inlined = flow;
        this.$flow$inlined = flow2;
        this.$transform$inlined = function3;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super R> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new CombineKt$zipImpl$1$1(flowCollector, this.$flow2$inlined, this.$flow$inlined, this.$transform$inlined, null), continuation);
        return objCoroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? objCoroutineScope : Unit.INSTANCE;
    }
}
