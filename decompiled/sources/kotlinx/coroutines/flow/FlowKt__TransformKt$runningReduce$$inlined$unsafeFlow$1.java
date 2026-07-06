package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1<T> implements Flow<T> {
    public final /* synthetic */ Function3 $operation$inlined;
    public final /* synthetic */ Flow $this_runningReduce$inlined;

    public FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_runningReduce$inlined = flow;
        this.$operation$inlined = function3;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) NullSurrogateKt.NULL;
        Object objCollect = this.$this_runningReduce$inlined.collect(new FlowKt__TransformKt$runningReduce$1$1(objectRef, this.$operation$inlined, flowCollector), continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }
}
