package kotlinx.coroutines.channels;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TickerChannelsKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt", f = "TickerChannels.kt", i = {0, 0, 1, 1, 2, 2}, l = {106, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY}, m = "fixedDelayTicker", n = {"channel", "delayMillis", "channel", "delayMillis", "channel", "delayMillis"}, s = {"L$0", "J$0", "L$0", "J$0", "L$0", "J$0"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        public long J$0;
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt", f = "TickerChannels.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, l = {ExifInterface.RAF_OFFSET_TO_JPEG_IMAGE_OFFSET, 88, 94, 96}, m = "fixedPeriodTicker", n = {"channel", "delayMillis", "deadline", "channel", "deadline", "delayNs", "channel", "deadline", "delayNs", "channel", "deadline", "delayNs"}, s = {"L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
    public static final class C01171 extends ContinuationImpl {
        public long J$0;
        public long J$1;
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public C01171(Continuation<? super C01171> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedPeriodTicker(0L, 0L, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.TickerChannelsKt$ticker$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt$ticker$3", f = "TickerChannels.kt", i = {}, l = {72, 73}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $delayMillis;
        public final /* synthetic */ long $initialDelayMillis;
        public final /* synthetic */ TickerMode $mode;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX INFO: renamed from: kotlinx.coroutines.channels.TickerChannelsKt$ticker$3$WhenMappings */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TickerMode.values().length];
                iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
                iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(TickerMode tickerMode, long j, long j2, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$mode = tickerMode;
            this.$delayMillis = j;
            this.$initialDelayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$mode, this.$delayMillis, this.$initialDelayMillis, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
        
            if (kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(r4, r6, r8, r10) == r0) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
        
            if (kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker(r1, r3, r11, r10) == r0) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0059, code lost:
        
            return r0;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r10.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1d
                if (r1 == r3) goto L19
                if (r1 != r2) goto L11
                kotlin.ResultKt.throwOnFailure(r11)
                r6 = r10
                goto L5a
            L11:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L19:
                kotlin.ResultKt.throwOnFailure(r11)
                goto L5a
            L1d:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
                kotlinx.coroutines.channels.TickerMode r1 = r10.$mode
                int[] r4 = kotlinx.coroutines.channels.TickerChannelsKt.AnonymousClass3.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r4[r1]
                if (r1 == r3) goto L46
                if (r1 == r2) goto L33
                goto L5a
            L33:
                long r4 = r10.$delayMillis
                long r6 = r10.$initialDelayMillis
                kotlinx.coroutines.channels.SendChannel r8 = r11.getChannel()
                r10.label = r2
                r9 = r10
                java.lang.Object r11 = kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(r4, r6, r8, r9)
                r6 = r9
                if (r11 != r0) goto L5a
                goto L59
            L46:
                r6 = r10
                long r1 = r6.$delayMillis
                r5 = 1
                long r3 = r6.$initialDelayMillis
                kotlinx.coroutines.channels.SendChannel r11 = r11.getChannel()
                r6.label = r5
                r5 = r11
                java.lang.Object r11 = kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker(r1, r3, r5, r6)
                if (r11 != r0) goto L5a
            L59:
                return r0
            L5a:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super Unit> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007b, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(r6, r0) != r1) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x007b -> B:14:0x0032). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object fixedDelayTicker(long r6, long r8, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Throwable {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.channels.TickerChannelsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = (kotlinx.coroutines.channels.TickerChannelsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L51
            if (r2 == r5) goto L46
            if (r2 == r4) goto L3c
            if (r2 != r3) goto L34
            long r6 = r0.J$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            kotlin.ResultKt.throwOnFailure(r11)
        L32:
            r10 = r8
            goto L61
        L34:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3c:
            long r6 = r0.J$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            kotlin.ResultKt.throwOnFailure(r11)
            goto L71
        L46:
            long r6 = r0.J$0
            java.lang.Object r8 = r0.L$0
            r10 = r8
            kotlinx.coroutines.channels.SendChannel r10 = (kotlinx.coroutines.channels.SendChannel) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L61
        L51:
            kotlin.ResultKt.throwOnFailure(r11)
            r0.L$0 = r10
            r0.J$0 = r6
            r0.label = r5
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r8, r0)
            if (r8 != r1) goto L61
            goto L7d
        L61:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            r0.L$0 = r10
            r0.J$0 = r6
            r0.label = r4
            java.lang.Object r8 = r10.send(r8, r0)
            if (r8 != r1) goto L70
            goto L7d
        L70:
            r8 = r10
        L71:
            r0.L$0 = r8
            r0.J$0 = r6
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r6, r0)
            if (r9 != r1) goto L32
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f5, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(r13 / 1000000, r1) != r2) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x00e3 -> B:19:0x0047). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00f5 -> B:18:0x0042). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object fixedPeriodTicker(long r21, long r23, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @ObsoleteCoroutinesApi
    @NotNull
    public static final ReceiveChannel<Unit> ticker(long j, long j2, @NotNull CoroutineContext coroutineContext, @NotNull TickerMode tickerMode) {
        if (j < 0) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Expected non-negative delay, but has ", j, " ms").toString());
        }
        if (j2 >= 0) {
            return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), 0, new AnonymousClass3(tickerMode, j, j2, null));
        }
        throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Expected non-negative initial delay, but has ", j2, " ms").toString());
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }
}
