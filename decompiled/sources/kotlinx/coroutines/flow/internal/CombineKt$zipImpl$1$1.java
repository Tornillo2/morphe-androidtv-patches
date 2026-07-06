package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"second"}, s = {"L$0"})
public final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Flow<T1> $flow;
    public final /* synthetic */ Flow<T2> $flow2;
    public final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    public final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2", f = "Combine.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Object $cnt;
        public final /* synthetic */ Flow<T1> $flow;
        public final /* synthetic */ CoroutineContext $scopeContext;
        public final /* synthetic */ ReceiveChannel<Object> $second;
        public final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
        public final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
        public int label;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class AnonymousClass1<T> implements FlowCollector {
            public final /* synthetic */ Object $cnt;
            public final /* synthetic */ CoroutineContext $scopeContext;
            public final /* synthetic */ ReceiveChannel<Object> $second;
            public final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
            public final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", f = "Combine.kt", i = {}, l = {132, 135, 135}, m = "invokeSuspend", n = {}, s = {})
            public static final class C00301 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                public final /* synthetic */ ReceiveChannel<Object> $second;
                public final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
                public final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
                public final /* synthetic */ T1 $value;
                public Object L$0;
                public int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00301(ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, T1 t1, Continuation<? super C00301> continuation) {
                    super(2, continuation);
                    this.$second = receiveChannel;
                    this.$this_unsafeFlow = flowCollector;
                    this.$transform = function3;
                    this.$value = t1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C00301(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, continuation);
                }

                /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
                
                    if (r1.emit(r9, r8) != r0) goto L30;
                 */
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) throws java.lang.Throwable {
                    /*
                        r8 = this;
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r8.label
                        r2 = 0
                        r3 = 3
                        r4 = 2
                        r5 = 1
                        if (r1 == 0) goto L2c
                        if (r1 == r5) goto L24
                        if (r1 == r4) goto L1c
                        if (r1 != r3) goto L14
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L6b
                    L14:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r0)
                        throw r9
                    L1c:
                        java.lang.Object r1 = r8.L$0
                        kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L60
                    L24:
                        kotlin.ResultKt.throwOnFailure(r9)
                        kotlinx.coroutines.channels.ChannelResult r9 = (kotlinx.coroutines.channels.ChannelResult) r9
                        java.lang.Object r9 = r9.holder
                        goto L3a
                    L2c:
                        kotlin.ResultKt.throwOnFailure(r9)
                        kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r9 = r8.$second
                        r8.label = r5
                        java.lang.Object r9 = r9.mo2082receiveCatchingJP2dKIU(r8)
                        if (r9 != r0) goto L3a
                        goto L6a
                    L3a:
                        kotlinx.coroutines.flow.FlowCollector<R> r1 = r8.$this_unsafeFlow
                        boolean r5 = r9 instanceof kotlinx.coroutines.channels.ChannelResult.Failed
                        if (r5 == 0) goto L4c
                        java.lang.Throwable r9 = kotlinx.coroutines.channels.ChannelResult.m2093exceptionOrNullimpl(r9)
                        if (r9 != 0) goto L4b
                        kotlinx.coroutines.flow.internal.AbortFlowException r9 = new kotlinx.coroutines.flow.internal.AbortFlowException
                        r9.<init>(r1)
                    L4b:
                        throw r9
                    L4c:
                        kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r5 = r8.$transform
                        T1 r6 = r8.$value
                        kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
                        if (r9 != r7) goto L55
                        r9 = r2
                    L55:
                        r8.L$0 = r1
                        r8.label = r4
                        java.lang.Object r9 = r5.invoke(r6, r9, r8)
                        if (r9 != r0) goto L60
                        goto L6a
                    L60:
                        r8.L$0 = r2
                        r8.label = r3
                        java.lang.Object r9 = r1.emit(r9, r8)
                        if (r9 != r0) goto L6b
                    L6a:
                        return r0
                    L6b:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.AnonymousClass2.AnonymousClass1.C00301.invokeSuspend(java.lang.Object):java.lang.Object");
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull Unit unit, @Nullable Continuation<? super Unit> continuation) {
                    return ((C00301) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
                this.$scopeContext = coroutineContext;
                this.$cnt = obj;
                this.$second = receiveChannel;
                this.$this_unsafeFlow = flowCollector;
                this.$transform = function3;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object emit(T1 r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
                /*
                    r11 = this;
                    boolean r0 = r13 instanceof kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r13
                    kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1 r0 = (kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1 r0 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1
                    r0.<init>(r11, r13)
                L18:
                    java.lang.Object r13 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L2f
                    if (r2 != r3) goto L27
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L4e
                L27:
                    java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                    java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                    r12.<init>(r13)
                    throw r12
                L2f:
                    kotlin.ResultKt.throwOnFailure(r13)
                    kotlin.coroutines.CoroutineContext r13 = r11.$scopeContext
                    kotlin.Unit r2 = kotlin.Unit.INSTANCE
                    java.lang.Object r4 = r11.$cnt
                    kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1 r5 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1
                    kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r6 = r11.$second
                    kotlinx.coroutines.flow.FlowCollector<R> r7 = r11.$this_unsafeFlow
                    kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r8 = r11.$transform
                    r10 = 0
                    r9 = r12
                    r5.<init>(r6, r7, r8, r9, r10)
                    r0.label = r3
                    java.lang.Object r12 = kotlinx.coroutines.flow.internal.ChannelFlowKt.withContextUndispatched(r13, r2, r4, r5, r0)
                    if (r12 != r1) goto L4e
                    return r1
                L4e:
                    kotlin.Unit r12 = kotlin.Unit.INSTANCE
                    return r12
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.AnonymousClass2.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Flow<? extends T1> flow, CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$flow = flow;
            this.$scopeContext = coroutineContext;
            this.$cnt = obj;
            this.$second = receiveChannel;
            this.$this_unsafeFlow = flowCollector;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.$flow, this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, continuation);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2 for r9v1 'this'  kotlin.coroutines.Continuation
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
            /*
                r9 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 1
                if (r1 == 0) goto L15
                if (r1 != r2) goto Ld
                kotlin.ResultKt.throwOnFailure(r10)
                goto L32
            Ld:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L15:
                kotlin.ResultKt.throwOnFailure(r10)
                kotlinx.coroutines.flow.Flow<T1> r10 = r9.$flow
                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1 r3 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1
                kotlin.coroutines.CoroutineContext r4 = r9.$scopeContext
                java.lang.Object r5 = r9.$cnt
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r6 = r9.$second
                kotlinx.coroutines.flow.FlowCollector<R> r7 = r9.$this_unsafeFlow
                kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r8 = r9.$transform
                r3.<init>(r4, r5, r6, r7, r8)
                r9.label = r2
                java.lang.Object r10 = r10.collect(r3, r9)
                if (r10 != r0) goto L32
                return r0
            L32:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull Unit unit, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CombineKt$zipImpl$1$1(FlowCollector<? super R> flowCollector, Flow<? extends T2> flow, Flow<? extends T1> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a0 A[Catch: all -> 0x0015, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0015, blocks: (B:6:0x0011, B:30:0x0096, B:35:0x00a0), top: B:38:0x0008 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) throws java.lang.Throwable {
        /*
            r24 = this;
            r4 = r24
            kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r0 = r4.label
            r8 = 1
            r9 = 0
            if (r0 == 0) goto L23
            if (r0 != r8) goto L1b
            java.lang.Object r0 = r4.L$0
            r1 = r0
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            kotlin.ResultKt.throwOnFailure(r25)     // Catch: java.lang.Throwable -> L15 kotlinx.coroutines.flow.internal.AbortFlowException -> L18
            goto L84
        L15:
            r0 = move-exception
            goto La1
        L18:
            r0 = move-exception
            goto L96
        L1b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L23:
            kotlin.ResultKt.throwOnFailure(r25)
            java.lang.Object r0 = r4.L$0
            r10 = r0
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1 r13 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1
            kotlinx.coroutines.flow.Flow<T2> r0 = r4.$flow2
            r13.<init>(r0, r9)
            r14 = 3
            r15 = 0
            r11 = 0
            r12 = 0
            kotlinx.coroutines.channels.ReceiveChannel r20 = kotlinx.coroutines.channels.ProduceKt.produce$default(r10, r11, r12, r13, r14, r15)
            kotlinx.coroutines.CompletableJob r0 = kotlinx.coroutines.JobKt__JobKt.Job$default(r9, r8, r9)
            r1 = r20
            kotlinx.coroutines.channels.SendChannel r1 = (kotlinx.coroutines.channels.SendChannel) r1
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1 r2 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1
            kotlinx.coroutines.flow.FlowCollector<R> r3 = r4.$this_unsafeFlow
            r2.<init>()
            r1.invokeOnClose(r2)
            kotlin.coroutines.CoroutineContext r18 = r10.getCoroutineContext()     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            java.lang.Object r19 = kotlinx.coroutines.internal.ThreadContextKt.threadContextElements(r18)     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlin.coroutines.CoroutineContext r1 = r10.getCoroutineContext()     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlin.coroutines.CoroutineContext r0 = r1.plus(r0)     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2 r16 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlinx.coroutines.flow.Flow<T1> r2 = r4.$flow     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlinx.coroutines.flow.FlowCollector<R> r3 = r4.$this_unsafeFlow     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r5 = r4.$transform     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            r23 = 0
            r17 = r2
            r21 = r3
            r22 = r5
            r16.<init>(r17, r18, r19, r20, r21, r22, r23)     // Catch: java.lang.Throwable -> L8e kotlinx.coroutines.flow.internal.AbortFlowException -> L92
            r10 = r20
            r4.L$0 = r10     // Catch: java.lang.Throwable -> L88 kotlinx.coroutines.flow.internal.AbortFlowException -> L8b
            r4.label = r8     // Catch: java.lang.Throwable -> L88 kotlinx.coroutines.flow.internal.AbortFlowException -> L8b
            r2 = 0
            r5 = 4
            r6 = 0
            r3 = r16
            java.lang.Object r0 = kotlinx.coroutines.flow.internal.ChannelFlowKt.withContextUndispatched$default(r0, r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L88 kotlinx.coroutines.flow.internal.AbortFlowException -> L8b
            if (r0 != r7) goto L83
            return r7
        L83:
            r1 = r10
        L84:
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(r1, r9, r8, r9)
            goto L9d
        L88:
            r0 = move-exception
        L89:
            r1 = r10
            goto La1
        L8b:
            r0 = move-exception
        L8c:
            r1 = r10
            goto L96
        L8e:
            r0 = move-exception
            r10 = r20
            goto L89
        L92:
            r0 = move-exception
            r10 = r20
            goto L8c
        L96:
            kotlinx.coroutines.flow.FlowCollector<R> r2 = r4.$this_unsafeFlow     // Catch: java.lang.Throwable -> L15
            kotlinx.coroutines.flow.FlowCollector<?> r3 = r0.owner     // Catch: java.lang.Throwable -> L15
            if (r3 != r2) goto La0
            goto L84
        L9d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        La0:
            throw r0     // Catch: java.lang.Throwable -> L15
        La1:
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(r1, r9, r8, r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
