package kotlinx.coroutines.flow;

import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class FlowKt__DelayKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", f = "Delay.kt", i = {0, 1, 2}, l = {314, 316, 317}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$0"})
    public static final class C01193 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $delayMillis;
        public final /* synthetic */ long $initialDelayMillis;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01193(long j, long j2, Continuation<? super C01193> continuation) {
            super(2, continuation);
            this.$initialDelayMillis = j;
            this.$delayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01193 c01193 = new C01193(this.$initialDelayMillis, this.$delayMillis, continuation);
            c01193.L$0 = obj;
            return c01193;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x004e A[PHI: r1
          0x004e: PHI (r1v4 kotlinx.coroutines.channels.ProducerScope) = (r1v3 kotlinx.coroutines.channels.ProducerScope), (r1v8 kotlinx.coroutines.channels.ProducerScope) binds: [B:16:0x004b, B:10:0x0018] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0058 -> B:15:0x003d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L28
                if (r1 == r4) goto L20
                if (r1 == r3) goto L18
                if (r1 != r2) goto L10
                goto L20
            L10:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L18:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L4e
            L20:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3d
            L28:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                r1 = r8
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                long r5 = r7.$initialDelayMillis
                r7.L$0 = r1
                r7.label = r4
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r7)
                if (r8 != r0) goto L3d
                goto L5a
            L3d:
                kotlinx.coroutines.channels.SendChannel r8 = r1.getChannel()
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                r7.L$0 = r1
                r7.label = r3
                java.lang.Object r8 = r8.send(r4, r7)
                if (r8 != r0) goto L4e
                goto L5a
            L4e:
                long r4 = r7.$delayMillis
                r7.L$0 = r1
                r7.label = r2
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r4, r7)
                if (r8 != r0) goto L3d
            L5a:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C01193.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super Unit> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01193) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", f = "Delay.kt", i = {0, 0, 0, 0}, l = {352}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "ticker"}, s = {"L$0", "L$1", "L$2", "L$3"})
    public static final class C01202<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $periodMillis;
        public final /* synthetic */ Flow<T> $this_sample;
        public /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01202(long j, Flow<? extends T> flow, Continuation<? super C01202> continuation) {
            super(3, continuation);
            this.$periodMillis = j;
            this.$this_sample = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            Ref.ObjectRef objectRef;
            ReceiveChannel receiveChannelFixedPeriodTicker$default;
            FlowCollector flowCollector;
            ReceiveChannel receiveChannel;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                FlowCollector flowCollector2 = (FlowCollector) this.L$1;
                ReceiveChannel receiveChannelProduce$default = ProduceKt.produce$default(coroutineScope, null, -1, new FlowKt__DelayKt$sample$2$values$1(this.$this_sample, null), 1, null);
                objectRef = new Ref.ObjectRef();
                receiveChannelFixedPeriodTicker$default = FlowKt__DelayKt.fixedPeriodTicker$default(coroutineScope, this.$periodMillis, 0L, 2, null);
                flowCollector = flowCollector2;
                receiveChannel = receiveChannelProduce$default;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ReceiveChannel receiveChannel2 = (ReceiveChannel) this.L$3;
                Ref.ObjectRef objectRef2 = (Ref.ObjectRef) this.L$2;
                receiveChannel = (ReceiveChannel) this.L$1;
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                objectRef = objectRef2;
                receiveChannelFixedPeriodTicker$default = receiveChannel2;
            }
            while (objectRef.element != NullSurrogateKt.DONE) {
                this.L$0 = flowCollector;
                this.L$1 = receiveChannel;
                this.L$2 = objectRef;
                this.L$3 = receiveChannelFixedPeriodTicker$default;
                this.label = 1;
                SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(this);
                try {
                    selectBuilderImpl.invoke(receiveChannel.getOnReceiveCatching(), new FlowKt__DelayKt$sample$2$1$1(objectRef, receiveChannelFixedPeriodTicker$default, null));
                    selectBuilderImpl.invoke(receiveChannelFixedPeriodTicker$default.getOnReceive(), new FlowKt__DelayKt$sample$2$1$2(objectRef, flowCollector, null));
                } catch (Throwable th) {
                    selectBuilderImpl.handleBuilderException(th);
                }
                Object result = selectBuilderImpl.getResult();
                CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (result == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            C01202 c01202 = new C01202(this.$periodMillis, this.$this_sample, continuation);
            c01202.L$0 = coroutineScope;
            c01202.L$1 = flowCollector;
            return c01202.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @FlowPreview
    @NotNull
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, final long j) {
        if (j >= 0) {
            return j == 0 ? flow : debounceInternal$FlowKt__DelayKt(flow, new Function1<T, Long>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt.debounce.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Long invoke(T t) {
                    return Long.valueOf(j);
                }

                @Override // kotlin.jvm.functions.Function1
                public Long invoke(Object obj) {
                    return Long.valueOf(j);
                }
            });
        }
        throw new IllegalArgumentException("Debounce timeout should not be negative");
    }

    @FlowPreview
    @NotNull
    /* JADX INFO: renamed from: debounce-HG0u8IE, reason: not valid java name */
    public static final <T> Flow<T> m2111debounceHG0u8IE(@NotNull Flow<? extends T> flow, long j) {
        return debounce(flow, DelayKt.m2069toDelayMillisLRDsOJo(j));
    }

    @FlowPreview
    @JvmName(name = "debounceDuration")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T> Flow<T> debounceDuration(@NotNull Flow<? extends T> flow, @NotNull final Function1<? super T, Duration> function1) {
        return debounceInternal$FlowKt__DelayKt(flow, new Function1<T, Long>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt.debounce.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Long invoke(T t) {
                return Long.valueOf(DelayKt.m2069toDelayMillisLRDsOJo(function1.invoke(t).rawValue));
            }
        });
    }

    public static final <T> Flow<T> debounceInternal$FlowKt__DelayKt(Flow<? extends T> flow, Function1<? super T, Long> function1) {
        return new FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1(new FlowKt__DelayKt$debounceInternal$1(function1, flow, null));
    }

    @NotNull
    public static final ReceiveChannel<Unit> fixedPeriodTicker(@NotNull CoroutineScope coroutineScope, long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Expected non-negative delay, but has ", j, " ms").toString());
        }
        if (j2 >= 0) {
            return ProduceKt.produce$default(coroutineScope, null, 0, new C01193(j2, j, null), 1, null);
        }
        throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Expected non-negative initial delay, but has ", j2, " ms").toString());
    }

    public static ReceiveChannel fixedPeriodTicker$default(CoroutineScope coroutineScope, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        return fixedPeriodTicker(coroutineScope, j, j2);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> sample(@NotNull Flow<? extends T> flow, long j) {
        if (j > 0) {
            return new FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1(new C01202(j, flow, null));
        }
        throw new IllegalArgumentException("Sample period should be positive");
    }

    @FlowPreview
    @NotNull
    /* JADX INFO: renamed from: sample-HG0u8IE, reason: not valid java name */
    public static final <T> Flow<T> m2112sampleHG0u8IE(@NotNull Flow<? extends T> flow, long j) {
        return sample(flow, DelayKt.m2069toDelayMillisLRDsOJo(j));
    }

    @FlowPreview
    @OverloadResolutionByLambdaReturnType
    @NotNull
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Long> function1) {
        return debounceInternal$FlowKt__DelayKt(flow, function1);
    }
}
