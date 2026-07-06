package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DebugCoroutineInfoImpl {

    @NotNull
    public final WeakReference<CoroutineContext> _context;

    @Nullable
    public WeakReference<CoroutineStackFrame> _lastObservedFrame;

    @NotNull
    public String _state = DebugCoroutineInfoImplKt.CREATED;

    @Nullable
    public final StackTraceFrame creationStackBottom;

    @JvmField
    @Nullable
    public Thread lastObservedThread;

    @JvmField
    public final long sequenceNumber;

    /* JADX INFO: renamed from: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1", f = "DebugCoroutineInfoImpl.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super StackTraceElement>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ StackTraceFrame $bottom;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StackTraceFrame stackTraceFrame, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$bottom = stackTraceFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = DebugCoroutineInfoImpl.this.new AnonymousClass1(this.$bottom, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SequenceScope<? super StackTraceElement> sequenceScope = (SequenceScope) this.L$0;
                DebugCoroutineInfoImpl debugCoroutineInfoImpl = DebugCoroutineInfoImpl.this;
                CoroutineStackFrame coroutineStackFrame = this.$bottom.callerFrame;
                this.label = 1;
                if (debugCoroutineInfoImpl.yieldFrames(sequenceScope, coroutineStackFrame, this) == coroutineSingletons) {
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
        public final Object invoke(@NotNull SequenceScope<? super StackTraceElement> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl", f = "DebugCoroutineInfoImpl.kt", i = {}, l = {80}, m = "yieldFrames", n = {}, s = {})
    public static final class C01181 extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C01181(Continuation<? super C01181> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DebugCoroutineInfoImpl.this.yieldFrames(null, null, this);
        }
    }

    public DebugCoroutineInfoImpl(@Nullable CoroutineContext coroutineContext, @Nullable StackTraceFrame stackTraceFrame, long j) {
        this.creationStackBottom = stackTraceFrame;
        this.sequenceNumber = j;
        this._context = new WeakReference<>(coroutineContext);
    }

    public final List<StackTraceElement> creationStackTrace() {
        StackTraceFrame stackTraceFrame = this.creationStackBottom;
        return stackTraceFrame == null ? EmptyList.INSTANCE : SequencesKt___SequencesKt.toList(SequencesKt__SequenceBuilderKt.sequence(new AnonymousClass1(stackTraceFrame, null)));
    }

    @Nullable
    public final CoroutineContext getContext() {
        return this._context.get();
    }

    @Nullable
    public final StackTraceFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return creationStackTrace();
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
        WeakReference<CoroutineStackFrame> weakReference = this._lastObservedFrame;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @NotNull
    public final String getState() {
        return this._state;
    }

    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        while (lastObservedFrame$kotlinx_coroutines_core != null) {
            StackTraceElement stackTraceElement = lastObservedFrame$kotlinx_coroutines_core.getStackTraceElement();
            if (stackTraceElement != null) {
                arrayList.add(stackTraceElement);
            }
            lastObservedFrame$kotlinx_coroutines_core = lastObservedFrame$kotlinx_coroutines_core.getCallerFrame();
        }
        return arrayList;
    }

    public final void setLastObservedFrame$kotlinx_coroutines_core(@Nullable CoroutineStackFrame coroutineStackFrame) {
        this._lastObservedFrame = coroutineStackFrame != null ? new WeakReference<>(coroutineStackFrame) : null;
    }

    @NotNull
    public String toString() {
        return "DebugCoroutineInfo(state=" + this._state + ",context=" + getContext() + ')';
    }

    public final void updateState$kotlinx_coroutines_core(@NotNull String str, @NotNull Continuation<?> continuation) {
        if (Intrinsics.areEqual(this._state, str) && Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.SUSPENDED) && getLastObservedFrame$kotlinx_coroutines_core() != null) {
            return;
        }
        this._state = str;
        setLastObservedFrame$kotlinx_coroutines_core(continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null);
        this.lastObservedThread = Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.RUNNING) ? Thread.currentThread() : null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0048 -> B:25:0x005f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0059 -> B:24:0x005c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object yieldFrames(kotlin.sequences.SequenceScope<? super java.lang.StackTraceElement> r6, kotlin.coroutines.jvm.internal.CoroutineStackFrame r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.C01181
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.C01181) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = new kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r6 = r0.L$2
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r6 = (kotlin.coroutines.jvm.internal.CoroutineStackFrame) r6
            java.lang.Object r7 = r0.L$1
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl r2 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5c
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
        L3f:
            if (r7 != 0) goto L44
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L44:
            java.lang.StackTraceElement r8 = r7.getStackTraceElement()
            if (r8 == 0) goto L5f
            r0.L$0 = r2
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r6.yield(r8, r0)
            if (r8 != r1) goto L59
            return r1
        L59:
            r4 = r7
            r7 = r6
            r6 = r4
        L5c:
            r4 = r7
            r7 = r6
            r6 = r4
        L5f:
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r7 = r7.getCallerFrame()
            if (r7 == 0) goto L66
            goto L3f
        L66:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames(kotlin.sequences.SequenceScope, kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
