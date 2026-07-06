package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$1<T> implements Flow<T> {
    public final /* synthetic */ Function0 $this_asFlow$inlined;

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$1(Function0 function0) {
        this.$this_asFlow$inlined = function0;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Object objEmit = flowCollector.emit((Object) this.$this_asFlow$inlined.invoke(), continuation);
        return objEmit == CoroutineSingletons.COROUTINE_SUSPENDED ? objEmit : Unit.INSTANCE;
    }
}
