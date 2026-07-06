package kotlinx.coroutines.sync;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SemaphoreKt {
    public static final int MAX_SPIN_CYCLES = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, (Object) null);

    @NotNull
    public static final Symbol PERMIT = new Symbol("PERMIT");

    @NotNull
    public static final Symbol TAKEN = new Symbol("TAKEN");

    @NotNull
    public static final Symbol BROKEN = new Symbol("BROKEN");

    @NotNull
    public static final Symbol CANCELLED = new Symbol("CANCELLED");
    public static final int SEGMENT_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, (Object) null);

    /* JADX INFO: renamed from: kotlinx.coroutines.sync.SemaphoreKt$withPermit$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.sync.SemaphoreKt", f = "Semaphore.kt", i = {0, 0}, l = {85}, m = "withPermit", n = {"$this$withPermit", "action"}, s = {"L$0", "L$1"})
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
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
            return SemaphoreKt.withPermit(null, null, this);
        }
    }

    @NotNull
    public static final Semaphore Semaphore(int i, int i2) {
        return new SemaphoreImpl(i, i2);
    }

    public static Semaphore Semaphore$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return new SemaphoreImpl(i, i2);
    }

    public static final SemaphoreSegment createSegment(long j, SemaphoreSegment semaphoreSegment) {
        return new SemaphoreSegment(j, semaphoreSegment, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object withPermit(@org.jetbrains.annotations.NotNull kotlinx.coroutines.sync.Semaphore r4, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends T> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.sync.SemaphoreKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.sync.SemaphoreKt$withPermit$1 r0 = (kotlinx.coroutines.sync.SemaphoreKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.sync.SemaphoreKt$withPermit$1 r0 = new kotlinx.coroutines.sync.SemaphoreKt$withPermit$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r4 = r0.L$1
            r5 = r4
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.sync.Semaphore r4 = (kotlinx.coroutines.sync.Semaphore) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L48
        L30:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L38:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r4.acquire(r0)
            if (r6 != r1) goto L48
            return r1
        L48:
            java.lang.Object r5 = r5.invoke()     // Catch: java.lang.Throwable -> L50
            r4.release()
            return r5
        L50:
            r5 = move-exception
            r4.release()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreKt.withPermit(kotlinx.coroutines.sync.Semaphore, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <T> Object withPermit$$forInline(Semaphore semaphore, Function0<? extends T> function0, Continuation<? super T> continuation) {
        semaphore.acquire(continuation);
        try {
            return function0.invoke();
        } finally {
            semaphore.release();
        }
    }

    public static /* synthetic */ void getBROKEN$annotations() {
    }

    public static /* synthetic */ void getCANCELLED$annotations() {
    }

    public static /* synthetic */ void getMAX_SPIN_CYCLES$annotations() {
    }

    public static /* synthetic */ void getPERMIT$annotations() {
    }

    public static /* synthetic */ void getSEGMENT_SIZE$annotations() {
    }

    public static /* synthetic */ void getTAKEN$annotations() {
    }
}
