package kotlinx.coroutines.flow.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.apache.commons.text.AlphabetConverter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {

    @JvmField
    @NotNull
    public final Flow<S> flow;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2", f = "ChannelFlow.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object L$0;
        public int label;
        public final /* synthetic */ ChannelFlowOperator<S, T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ChannelFlowOperator<S, T> channelFlowOperator, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = channelFlowOperator;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector<? super T> flowCollector = (FlowCollector) this.L$0;
                ChannelFlowOperator<S, T> channelFlowOperator = this.this$0;
                this.label = 1;
                if (channelFlowOperator.flowCollect(flowCollector, this) == coroutineSingletons) {
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
        public final Object invoke(@NotNull FlowCollector<? super T> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowOperator(@NotNull Flow<? extends S> flow, @NotNull CoroutineContext coroutineContext, int i, @NotNull BufferOverflow bufferOverflow) {
        super(coroutineContext, i, bufferOverflow);
        this.flow = flow;
    }

    public static Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, Continuation continuation) {
        if (channelFlowOperator.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext coroutineContextPlus = context.plus(channelFlowOperator.context);
            if (Intrinsics.areEqual(coroutineContextPlus, context)) {
                Object objFlowCollect = channelFlowOperator.flowCollect(flowCollector, continuation);
                return objFlowCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objFlowCollect : Unit.INSTANCE;
            }
            ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
            if (Intrinsics.areEqual(coroutineContextPlus.get(key), context.get(key))) {
                Object objCollectWithContextUndispatched = channelFlowOperator.collectWithContextUndispatched(flowCollector, coroutineContextPlus, continuation);
                return objCollectWithContextUndispatched == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollectWithContextUndispatched : Unit.INSTANCE;
            }
        }
        Object objCollect$suspendImpl = ChannelFlow.collect$suspendImpl(channelFlowOperator, flowCollector, continuation);
        return objCollect$suspendImpl == CoroutineSingletons.COROUTINE_SUSPENDED ? objCollect$suspendImpl : Unit.INSTANCE;
    }

    public static Object collectTo$suspendImpl(ChannelFlowOperator channelFlowOperator, ProducerScope producerScope, Continuation continuation) {
        Object objFlowCollect = channelFlowOperator.flowCollect(new SendingCollector(producerScope), continuation);
        return objFlowCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? objFlowCollect : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        return collect$suspendImpl((ChannelFlowOperator) this, (FlowCollector) flowCollector, (Continuation) continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    @Nullable
    public Object collectTo(@NotNull ProducerScope<? super T> producerScope, @NotNull Continuation<? super Unit> continuation) {
        return collectTo$suspendImpl(this, producerScope, continuation);
    }

    public final Object collectWithContextUndispatched(FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext, Continuation<? super Unit> continuation) {
        Object objWithContextUndispatched$default = ChannelFlowKt.withContextUndispatched$default(coroutineContext, ChannelFlowKt.withUndispatchedContextCollector(flowCollector, continuation.getContext()), null, new AnonymousClass2(this, null), continuation, 4, null);
        return objWithContextUndispatched$default == CoroutineSingletons.COROUTINE_SUSPENDED ? objWithContextUndispatched$default : Unit.INSTANCE;
    }

    @Nullable
    public abstract Object flowCollect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    @NotNull
    public String toString() {
        return this.flow + AlphabetConverter.ARROW + super.toString();
    }
}
