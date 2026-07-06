package kotlinx.coroutines.flow;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StartedWhileSubscribed implements SharingStarted {
    public final long replayExpiration;
    public final long stopTimeout;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$1", f = "SharingStarted.kt", i = {1, 2, 3}, l = {178, 180, 182, 183, 185}, m = "invokeSuspend", n = {"$this$transformLatest", "$this$transformLatest", "$this$transformLatest"}, s = {"L$0", "L$0", "L$0"})
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super SharingCommand>, Integer, Continuation<? super Unit>, Object> {
        public /* synthetic */ int I$0;
        public /* synthetic */ Object L$0;
        public int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super SharingCommand> flowCollector, Integer num, Continuation<? super Unit> continuation) {
            return invoke(flowCollector, num.intValue(), continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
        
            if (r1.emit(r10, r9) == r0) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0091, code lost:
        
            if (r1.emit(r10, r9) != r0) goto L35;
         */
        /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0086 A[PHI: r1
          0x0086: PHI (r1v5 kotlinx.coroutines.flow.FlowCollector) = 
          (r1v3 kotlinx.coroutines.flow.FlowCollector)
          (r1v4 kotlinx.coroutines.flow.FlowCollector)
          (r1v11 kotlinx.coroutines.flow.FlowCollector)
         binds: [B:25:0x0068, B:30:0x0083, B:12:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 5
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                if (r1 == 0) goto L3a
                if (r1 == r6) goto L36
                if (r1 == r5) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                goto L36
            L16:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L1e:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L86
            L26:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L77
            L2e:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L60
            L36:
                kotlin.ResultKt.throwOnFailure(r10)
                goto L94
            L3a:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                r1 = r10
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                int r10 = r9.I$0
                if (r10 <= 0) goto L51
                kotlinx.coroutines.flow.SharingCommand r10 = kotlinx.coroutines.flow.SharingCommand.START
                r9.label = r6
                java.lang.Object r10 = r1.emit(r10, r9)
                if (r10 != r0) goto L94
                goto L93
            L51:
                kotlinx.coroutines.flow.StartedWhileSubscribed r10 = kotlinx.coroutines.flow.StartedWhileSubscribed.this
                long r6 = r10.stopTimeout
                r9.L$0 = r1
                r9.label = r5
                java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r6, r9)
                if (r10 != r0) goto L60
                goto L93
            L60:
                kotlinx.coroutines.flow.StartedWhileSubscribed r10 = kotlinx.coroutines.flow.StartedWhileSubscribed.this
                long r5 = r10.replayExpiration
                r7 = 0
                int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r10 <= 0) goto L86
                kotlinx.coroutines.flow.SharingCommand r10 = kotlinx.coroutines.flow.SharingCommand.STOP
                r9.L$0 = r1
                r9.label = r4
                java.lang.Object r10 = r1.emit(r10, r9)
                if (r10 != r0) goto L77
                goto L93
            L77:
                kotlinx.coroutines.flow.StartedWhileSubscribed r10 = kotlinx.coroutines.flow.StartedWhileSubscribed.this
                long r4 = r10.replayExpiration
                r9.L$0 = r1
                r9.label = r3
                java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r4, r9)
                if (r10 != r0) goto L86
                goto L93
            L86:
                kotlinx.coroutines.flow.SharingCommand r10 = kotlinx.coroutines.flow.SharingCommand.STOP_AND_RESET_REPLAY_CACHE
                r3 = 0
                r9.L$0 = r3
                r9.label = r2
                java.lang.Object r10 = r1.emit(r10, r9)
                if (r10 != r0) goto L94
            L93:
                return r0
            L94:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StartedWhileSubscribed.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super SharingCommand> flowCollector, int i, @Nullable Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = StartedWhileSubscribed.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = flowCollector;
            anonymousClass1.I$0 = i;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$2", f = "SharingStarted.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Boolean>, Object> {
        public /* synthetic */ Object L$0;
        public int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(((SharingCommand) this.L$0) != SharingCommand.START);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull SharingCommand sharingCommand, @Nullable Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(sharingCommand, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public StartedWhileSubscribed(long j, long j2) {
        this.stopTimeout = j;
        this.replayExpiration = j2;
        if (j < 0) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("stopTimeout(", j, " ms) cannot be negative").toString());
        }
        if (j2 < 0) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("replayExpiration(", j2, " ms) cannot be negative").toString());
        }
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    @NotNull
    public Flow<SharingCommand> command(@NotNull StateFlow<Integer> stateFlow) {
        return FlowKt__DistinctKt.distinctUntilChanged(new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(FlowKt__MergeKt.transformLatest(stateFlow, new AnonymousClass1(null)), new AnonymousClass2(2, null)));
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof StartedWhileSubscribed)) {
            return false;
        }
        StartedWhileSubscribed startedWhileSubscribed = (StartedWhileSubscribed) obj;
        return this.stopTimeout == startedWhileSubscribed.stopTimeout && this.replayExpiration == startedWhileSubscribed.replayExpiration;
    }

    @IgnoreJRERequirement
    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.replayExpiration) + (FloatFloatPair$$ExternalSyntheticBackport0.m(this.stopTimeout) * 31);
    }

    @NotNull
    public String toString() {
        ListBuilder listBuilder = new ListBuilder(2);
        if (this.stopTimeout > 0) {
            listBuilder.add("stopTimeout=" + this.stopTimeout + "ms");
        }
        if (this.replayExpiration < Long.MAX_VALUE) {
            listBuilder.add("replayExpiration=" + this.replayExpiration + "ms");
        }
        return "SharingStarted.WhileSubscribed(" + CollectionsKt___CollectionsKt.joinToString$default(listBuilder.build(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
