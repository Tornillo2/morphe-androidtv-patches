package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.internal.NopCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class FlowKt__CollectKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectKt$collect$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass3<T> implements FlowCollector<T> {
        public final /* synthetic */ Function2<T, Continuation<? super Unit>, Object> $action;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
            this.$action = function2;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        @Nullable
        public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
            Object objInvoke = this.$action.invoke(t, continuation);
            return objInvoke == CoroutineSingletons.COROUTINE_SUSPENDED ? objInvoke : Unit.INSTANCE;
        }

        @Nullable
        public Object emit$$forInline(T t, @NotNull final Continuation<? super Unit> continuation) {
            new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__CollectKt$collect$3$emit$1
                public int label;
                public /* synthetic */ Object result;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    this.result = obj;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.emit(null, this);
                }
            };
            this.$action.invoke(t, continuation);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectKt$collectIndexed$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2<T> implements FlowCollector<T> {
        public final /* synthetic */ Function3<Integer, T, Continuation<? super Unit>, Object> $action;
        public int index;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function3<? super Integer, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
            this.$action = function3;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        @Nullable
        public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
            Function3<Integer, T, Continuation<? super Unit>, Object> function3 = this.$action;
            int i = this.index;
            this.index = i + 1;
            if (i < 0) {
                throw new ArithmeticException("Index overflow has happened");
            }
            Object objInvoke = function3.invoke(new Integer(i), t, continuation);
            return objInvoke == CoroutineSingletons.COROUTINE_SUSPENDED ? objInvoke : Unit.INSTANCE;
        }

        @Nullable
        public Object emit$$forInline(T t, @NotNull final Continuation<? super Unit> continuation) {
            new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__CollectKt$collectIndexed$2$emit$1
                public int label;
                public /* synthetic */ Object result;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    this.result = obj;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.emit(null, this);
                }
            };
            Function3<Integer, T, Continuation<? super Unit>, Object> function3 = this.$action;
            int i = this.index;
            this.index = i + 1;
            if (i < 0) {
                throw new ArithmeticException("Index overflow has happened");
            }
            function3.invoke(Integer.valueOf(i), t, continuation);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectKt$launchIn$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectKt$launchIn$1", f = "Collect.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Flow<T> $this_launchIn;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Flow<? extends T> flow, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_launchIn = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_launchIn, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<T> flow = this.$this_launchIn;
                this.label = 1;
                if (FlowKt__CollectKt.collect(flow, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Nullable
    public static final Object collect(@NotNull Flow<?> flow, @NotNull Continuation<? super Unit> continuation) {
        Object objCollect = flow.collect(NopCollector.INSTANCE, continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Backwards compatibility with JS and K/N")
    public static final /* synthetic */ <T> Object collect$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        flow.collect(new AnonymousClass3(function2), continuation);
        return Unit.INSTANCE;
    }

    @Nullable
    public static final <T> Object collectIndexed(@NotNull Flow<? extends T> flow, @NotNull Function3<? super Integer, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, @NotNull Continuation<? super Unit> continuation) {
        Object objCollect = flow.collect(new AnonymousClass2(function3), continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }

    public static final <T> Object collectIndexed$$forInline(Flow<? extends T> flow, Function3<? super Integer, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        flow.collect(new AnonymousClass2(function3), continuation);
        return Unit.INSTANCE;
    }

    @Nullable
    public static final <T> Object collectLatest(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        Object objCollect = collect(FlowKt__ContextKt.buffer$default(FlowKt__MergeKt.mapLatest(flow, function2), 0, null, 2, null), continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }

    @Nullable
    public static final <T> Object emitAll(@NotNull FlowCollector<? super T> flowCollector, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__EmittersKt.ensureActive(flowCollector);
        Object objCollect = flow.collect(flowCollector, continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }

    @NotNull
    public static final <T> Job launchIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(flow, null), 3, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Backwards compatibility with JS and K/N")
    public static final <T> Object collect(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objCollect = flow.collect(new AnonymousClass3(function2), continuation);
        return objCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect : Unit.INSTANCE;
    }
}
