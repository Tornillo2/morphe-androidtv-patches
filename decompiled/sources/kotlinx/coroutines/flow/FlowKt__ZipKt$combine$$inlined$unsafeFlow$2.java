package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$2<R> implements Flow<R> {
    public final /* synthetic */ Flow[] $flows$inlined;
    public final /* synthetic */ Function2 $transform$inlined;

    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$2(Flow[] flowArr, Function2 function2) {
        this.$flows$inlined = flowArr;
        this.$transform$inlined = function2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super R> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @Nullable
    public Object collect$$forInline(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
        new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$2.1
            public int label;
            public /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                FlowKt__ZipKt$combine$$inlined$unsafeFlow$2.this.getClass();
                Intrinsics.throwUndefinedForReified();
                throw null;
            }
        };
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
