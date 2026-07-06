package androidx.core.os;

import android.content.Context;
import android.os.ProfilingManager;
import android.os.ProfilingResult;
import androidx.annotation.RequiresApi;
import androidx.core.os.Profiling;
import j$.util.function.Consumer$CC;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@JvmName(name = "Profiling")
public final class Profiling {

    @NotNull
    public static final String KEY_BUFFER_FILL_POLICY = "KEY_BUFFER_FILL_POLICY";

    @NotNull
    public static final String KEY_DURATION_MS = "KEY_DURATION_MS";

    @NotNull
    public static final String KEY_FREQUENCY_HZ = "KEY_FREQUENCY_HZ";

    @NotNull
    public static final String KEY_SAMPLING_INTERVAL_BYTES = "KEY_SAMPLING_INTERVAL_BYTES";

    @NotNull
    public static final String KEY_SIZE_KB = "KEY_SIZE_KB";

    @NotNull
    public static final String KEY_TRACK_JAVA_ALLOCATIONS = "KEY_TRACK_JAVA_ALLOCATIONS";
    public static final int VALUE_BUFFER_FILL_POLICY_DISCARD = 1;
    public static final int VALUE_BUFFER_FILL_POLICY_RING_BUFFER = 2;

    /* JADX INFO: renamed from: androidx.core.os.Profiling$registerForAllProfilingResults$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "androidx.core.os.Profiling$registerForAllProfilingResults$1", f = "Profiling.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super ProfilingResult>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        public static final void invokeSuspend$lambda$0(ProducerScope producerScope, ProfilingResult result) {
            Intrinsics.checkNotNullExpressionValue(result, "result");
            producerScope.mo2084trySendJP2dKIU(result);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$context, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final Consumer consumer = new Consumer() { // from class: androidx.core.os.Profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    /* JADX INFO: renamed from: accept */
                    public final void n(Object obj2) {
                        Profiling.AnonymousClass1.invokeSuspend$lambda$0(producerScope, (ProfilingResult) obj2);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer2) {
                        return Consumer$CC.$default$andThen(this, consumer2);
                    }
                };
                final ProfilingManager profilingManagerM = Profiling$$ExternalSyntheticApiModelOutline1.m(this.$context.getSystemService(Profiling$$ExternalSyntheticApiModelOutline0.m()));
                profilingManagerM.registerForAllProfilingResults(new Profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda1(), consumer);
                Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.core.os.Profiling.registerForAllProfilingResults.1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        profilingManagerM.unregisterForAllProfilingResults(consumer);
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
        public final Object invoke(ProducerScope<? super ProfilingResult> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @RequiresApi(api = 35)
    @NotNull
    public static final Flow<ProfilingResult> registerForAllProfilingResults(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return FlowKt__BuildersKt.callbackFlow(new AnonymousClass1(context, null));
    }

    @RequiresApi(api = 35)
    public static final void requestProfiling(@NotNull Context context, @NotNull ProfilingRequest profilingRequest, @Nullable Executor executor, @Nullable Consumer<ProfilingResult> consumer) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(profilingRequest, "profilingRequest");
        Profiling$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(Profiling$$ExternalSyntheticApiModelOutline0.m())).requestProfiling(profilingRequest.profilingType, profilingRequest.params, profilingRequest.tag, profilingRequest.cancellationSignal, executor, consumer);
    }

    @RequiresApi(api = 35)
    public static final void unregisterForAllProfilingResults(@NotNull Context context, @NotNull Consumer<ProfilingResult> listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Profiling$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(Profiling$$ExternalSyntheticApiModelOutline0.m())).unregisterForAllProfilingResults(listener);
    }

    @RequiresApi(api = 35)
    public static final void registerForAllProfilingResults(@NotNull Context context, @NotNull Executor executor, @NotNull Consumer<ProfilingResult> listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Profiling$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(Profiling$$ExternalSyntheticApiModelOutline0.m())).registerForAllProfilingResults(executor, listener);
    }
}
