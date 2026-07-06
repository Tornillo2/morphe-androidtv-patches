package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CoroutineContextKt {

    @NotNull
    public static final String DEBUG_THREAD_NAME_SEPARATOR = " @";

    /* JADX INFO: renamed from: kotlinx.coroutines.CoroutineContextKt$foldCopies$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(2);

        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext.Element element) {
            return element instanceof CopyableThreadContextElement ? coroutineContext.plus(((CopyableThreadContextElement) element).copyForChild()) : coroutineContext.plus(element);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.CoroutineContextKt$hasCopyableElements$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00791 extends Lambda implements Function2<Boolean, CoroutineContext.Element, Boolean> {
        public static final C00791 INSTANCE = new C00791(2);

        public C00791() {
            super(2);
        }

        @NotNull
        public final Boolean invoke(boolean z, @NotNull CoroutineContext.Element element) {
            return Boolean.valueOf(z || (element instanceof CopyableThreadContextElement));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool, CoroutineContext.Element element) {
            return invoke(bool.booleanValue(), element);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.Object] */
    public static final CoroutineContext foldCopies(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, final boolean z) {
        boolean zHasCopyableElements = hasCopyableElements(coroutineContext);
        boolean zHasCopyableElements2 = hasCopyableElements(coroutineContext2);
        if (!zHasCopyableElements && !zHasCopyableElements2) {
            return coroutineContext.plus(coroutineContext2);
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = coroutineContext2;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.fold(emptyCoroutineContext, new Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext>() { // from class: kotlinx.coroutines.CoroutineContextKt$foldCopies$folded$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r2v2, types: [T, kotlin.coroutines.CoroutineContext] */
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext4, @NotNull CoroutineContext.Element element) {
                if (!(element instanceof CopyableThreadContextElement)) {
                    return coroutineContext4.plus(element);
                }
                CoroutineContext.Element element2 = objectRef.element.get(element.getKey());
                if (element2 != null) {
                    Ref.ObjectRef<CoroutineContext> objectRef2 = objectRef;
                    objectRef2.element = objectRef2.element.minusKey(element.getKey());
                    return coroutineContext4.plus(((CopyableThreadContextElement) element).mergeForChild(element2));
                }
                CopyableThreadContextElement copyableThreadContextElementCopyForChild = (CopyableThreadContextElement) element;
                if (z) {
                    copyableThreadContextElementCopyForChild = copyableThreadContextElementCopyForChild.copyForChild();
                }
                return coroutineContext4.plus(copyableThreadContextElementCopyForChild);
            }
        });
        if (zHasCopyableElements2) {
            objectRef.element = ((CoroutineContext) objectRef.element).fold(emptyCoroutineContext, AnonymousClass1.INSTANCE);
        }
        return coroutineContext3.plus((CoroutineContext) objectRef.element);
    }

    @Nullable
    public static final String getCoroutineName(@NotNull CoroutineContext coroutineContext) {
        return null;
    }

    public static final boolean hasCopyableElements(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.fold(Boolean.FALSE, C00791.INSTANCE)).booleanValue();
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext) {
        CoroutineContext coroutineContextFoldCopies = foldCopies(coroutineScope.getCoroutineContext(), coroutineContext, true);
        return (coroutineContextFoldCopies == Dispatchers.getDefault() || coroutineContextFoldCopies.get(ContinuationInterceptor.Key) != null) ? coroutineContextFoldCopies : coroutineContextFoldCopies.plus(Dispatchers.Default);
    }

    @Nullable
    public static final UndispatchedCoroutine<?> undispatchedCompletion(@NotNull CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    @Nullable
    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(@NotNull Continuation<?> continuation, @NotNull CoroutineContext coroutineContext, @Nullable Object obj) {
        if (!(continuation instanceof CoroutineStackFrame) || coroutineContext.get(UndispatchedMarker.INSTANCE) == null) {
            return null;
        }
        UndispatchedCoroutine<?> undispatchedCoroutineUndispatchedCompletion = undispatchedCompletion((CoroutineStackFrame) continuation);
        if (undispatchedCoroutineUndispatchedCompletion != null) {
            undispatchedCoroutineUndispatchedCompletion.saveThreadContext(coroutineContext, obj);
        }
        return undispatchedCoroutineUndispatchedCompletion;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> T withContinuationContext(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<?> r2, @org.jetbrains.annotations.Nullable java.lang.Object r3, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends T> r4) {
        /*
            kotlin.coroutines.CoroutineContext r0 = r2.getContext()
            java.lang.Object r3 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r0, r3)
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS
            if (r3 == r1) goto L11
            kotlinx.coroutines.UndispatchedCoroutine r2 = updateUndispatchedCompletion(r2, r0, r3)
            goto L12
        L11:
            r2 = 0
        L12:
            java.lang.Object r4 = r4.invoke()     // Catch: java.lang.Throwable -> L22
            if (r2 == 0) goto L1e
            boolean r2 = r2.clearThreadContext()
            if (r2 == 0) goto L21
        L1e:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r0, r3)
        L21:
            return r4
        L22:
            r4 = move-exception
            if (r2 == 0) goto L2b
            boolean r2 = r2.clearThreadContext()
            if (r2 == 0) goto L2e
        L2b:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r0, r3)
        L2e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.withContinuationContext(kotlin.coroutines.Continuation, java.lang.Object, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    public static final <T> T withCoroutineContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj, @NotNull Function0<? extends T> function0) {
        Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            return function0.invoke();
        } finally {
            ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
        }
    }

    @InternalCoroutinesApi
    @NotNull
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext coroutineContext2) {
        return !hasCopyableElements(coroutineContext2) ? coroutineContext.plus(coroutineContext2) : foldCopies(coroutineContext, coroutineContext2, false);
    }
}
