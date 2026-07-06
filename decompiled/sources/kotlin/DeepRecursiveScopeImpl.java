package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DeepRecursiveScopeImpl<T, R> extends DeepRecursiveScope<T, R> implements Continuation<R> {

    @Nullable
    public Continuation<Object> cont;

    @NotNull
    public Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function;

    @NotNull
    public Object result;

    @Nullable
    public Object value;

    /* JADX WARN: Multi-variable type inference failed */
    public DeepRecursiveScopeImpl(@NotNull Function3<? super DeepRecursiveScope<T, R>, ? super T, ? super Continuation<? super R>, ? extends Object> block, T t) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.function = block;
        this.value = t;
        this.cont = this;
        this.result = DeepRecursiveKt.UNDEFINED_RESULT;
    }

    @Override // kotlin.DeepRecursiveScope
    @Nullable
    public <U, S> Object callRecursive(@NotNull DeepRecursiveFunction<U, S> deepRecursiveFunction, U u, @NotNull Continuation<? super S> continuation) {
        Function3<DeepRecursiveScope<U, S>, U, Continuation<? super S>, Object> function3 = deepRecursiveFunction.block;
        Intrinsics.checkNotNull(function3, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.coroutines.SuspendFunction2<kotlin.DeepRecursiveScope<*, *>, kotlin.Any?, kotlin.Any?>");
        Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function32 = this.function;
        if (function3 != function32) {
            this.function = function3;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            this.cont = crossFunctionCompletion(function32, continuation);
        } else {
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            this.cont = continuation;
        }
        this.value = u;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        DebugProbesKt.probeCoroutineSuspended(continuation);
        return coroutineSingletons;
    }

    public final Continuation<Object> crossFunctionCompletion(final Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function3, final Continuation<Object> continuation) {
        final EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        return new Continuation<Object>() { // from class: kotlin.DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1
            @Override // kotlin.coroutines.Continuation
            public CoroutineContext getContext() {
                return emptyCoroutineContext;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(Object obj) {
                DeepRecursiveScopeImpl deepRecursiveScopeImpl = this;
                deepRecursiveScopeImpl.function = function3;
                deepRecursiveScopeImpl.cont = continuation;
                deepRecursiveScopeImpl.result = obj;
            }
        };
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        this.cont = null;
        this.result = obj;
    }

    public final R runCallLoop() throws Throwable {
        Object objInvoke;
        while (true) {
            R r = (R) this.result;
            Continuation<Object> continuation = this.cont;
            if (continuation == null) {
                ResultKt.throwOnFailure(r);
                return r;
            }
            if (Intrinsics.areEqual(DeepRecursiveKt.UNDEFINED_RESULT, r)) {
                try {
                    Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function3 = this.function;
                    Object obj = this.value;
                    if (function3 instanceof BaseContinuationImpl) {
                        TypeIntrinsics.beforeCheckcastToFunctionOfArity(function3, 3);
                        objInvoke = function3.invoke(this, obj, continuation);
                    } else {
                        objInvoke = IntrinsicsKt__IntrinsicsJvmKt.wrapWithContinuationImpl(function3, this, obj, continuation);
                    }
                    if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        continuation.resumeWith(objInvoke);
                    }
                } catch (Throwable th) {
                    continuation.resumeWith(ResultKt.createFailure(th));
                }
            } else {
                this.result = DeepRecursiveKt.UNDEFINED_RESULT;
                continuation.resumeWith(r);
            }
        }
    }

    @Override // kotlin.DeepRecursiveScope
    @Nullable
    public Object callRecursive(T t, @NotNull Continuation<? super R> continuation) {
        Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        this.cont = continuation;
        this.value = t;
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
