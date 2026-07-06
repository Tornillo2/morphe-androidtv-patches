package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1<R> implements Flow<R> {
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ Function2 $transform$inlined$1;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2<T> implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ Function2 $transform$inlined;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2", f = "Transform.kt", i = {0}, l = {223, 224}, m = "emit", n = {"$this$mapNotNull_u24lambda_u2d5"}, s = {"L$0"})
        public static final class AnonymousClass1 extends ContinuationImpl {
            public Object L$0;
            public int label;
            public /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
            this.$this_unsafeFlow = flowCollector;
            this.$transform$inlined = function2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
        
            if (r7.emit(r8, r0) == r1) goto L24;
         */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
            /*
                r6 = this;
                boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r8
                kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$1
                r0.<init>(r8)
            L18:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L3a
                if (r2 == r4) goto L32
                if (r2 != r3) goto L2a
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5e
            L2a:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L32:
                java.lang.Object r7 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L4f
            L3a:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                kotlin.jvm.functions.Function2 r2 = r6.$transform$inlined
                r0.L$0 = r8
                r0.label = r4
                java.lang.Object r7 = r2.invoke(r7, r0)
                if (r7 != r1) goto L4c
                goto L5d
            L4c:
                r5 = r8
                r8 = r7
                r7 = r5
            L4f:
                if (r8 != 0) goto L52
                goto L5e
            L52:
                r2 = 0
                r0.L$0 = r2
                r0.label = r3
                java.lang.Object r7 = r7.emit(r8, r0)
                if (r7 != r1) goto L5e
            L5d:
                return r1
            L5e:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Nullable
        public final Object emit$$forInline(Object obj, @NotNull Continuation continuation) {
            new AnonymousClass1(continuation);
            FlowCollector flowCollector = this.$this_unsafeFlow;
            Object objInvoke = this.$transform$inlined.invoke(obj, continuation);
            if (objInvoke != null) {
                flowCollector.emit(objInvoke, continuation);
            }
            return Unit.INSTANCE;
        }
    }

    public FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1(Flow flow, Function2 function2) {
        this.$this_unsafeTransform$inlined = flow;
        this.$transform$inlined$1 = function2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
        Object objCollect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.$transform$inlined$1), continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }

    @Nullable
    public Object collect$$forInline(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
        new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1.1
            public int label;
            public /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1.this.collect(null, this);
            }
        };
        this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.$transform$inlined$1), continuation);
        return Unit.INSTANCE;
    }
}
