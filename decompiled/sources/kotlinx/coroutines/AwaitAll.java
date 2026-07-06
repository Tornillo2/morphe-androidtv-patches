package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AwaitAll<T> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater notCompletedCount$FU = AtomicIntegerFieldUpdater.newUpdater(AwaitAll.class, "notCompletedCount");

    @NotNull
    public final Deferred<T>[] deferreds;

    @NotNull
    volatile /* synthetic */ int notCompletedCount;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AwaitAllNode extends JobNode {

        @NotNull
        private volatile /* synthetic */ Object _disposer = null;

        @NotNull
        public final CancellableContinuation<List<? extends T>> continuation;
        public DisposableHandle handle;

        /* JADX WARN: Multi-variable type inference failed */
        public AwaitAllNode(@NotNull CancellableContinuation<? super List<? extends T>> cancellableContinuation) {
            this.continuation = cancellableContinuation;
        }

        @Nullable
        public final AwaitAll<T>.DisposeHandlersOnCancel getDisposer() {
            return (DisposeHandlersOnCancel) this._disposer;
        }

        @NotNull
        public final DisposableHandle getHandle() {
            DisposableHandle disposableHandle = this.handle;
            if (disposableHandle != null) {
                return disposableHandle;
            }
            Intrinsics.throwUninitializedPropertyAccessException("handle");
            throw null;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public final void setDisposer(@Nullable AwaitAll<T>.DisposeHandlersOnCancel disposeHandlersOnCancel) {
            this._disposer = disposeHandlersOnCancel;
        }

        public final void setHandle(@NotNull DisposableHandle disposableHandle) {
            this.handle = disposableHandle;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (th != null) {
                Object objTryResumeWithException = this.continuation.tryResumeWithException(th);
                if (objTryResumeWithException != null) {
                    this.continuation.completeResume(objTryResumeWithException);
                    DisposeHandlersOnCancel disposeHandlersOnCancel = (DisposeHandlersOnCancel) this._disposer;
                    if (disposeHandlersOnCancel != null) {
                        disposeHandlersOnCancel.disposeAll();
                        return;
                    }
                    return;
                }
                return;
            }
            if (AwaitAll.notCompletedCount$FU.decrementAndGet(AwaitAll.this) == 0) {
                CancellableContinuation<List<? extends T>> cancellableContinuation = this.continuation;
                Deferred<T>[] deferredArr = AwaitAll.this.deferreds;
                ArrayList arrayList = new ArrayList(deferredArr.length);
                for (Deferred<T> deferred : deferredArr) {
                    arrayList.add(deferred.getCompleted());
                }
                cancellableContinuation.resumeWith(arrayList);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AwaitAll(@NotNull Deferred<? extends T>[] deferredArr) {
        this.deferreds = deferredArr;
        this.notCompletedCount = deferredArr.length;
    }

    @Nullable
    public final Object await(@NotNull Continuation<? super List<? extends T>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        int length = this.deferreds.length;
        AwaitAllNode[] awaitAllNodeArr = new AwaitAllNode[length];
        for (int i = 0; i < length; i++) {
            Deferred<T> deferred = this.deferreds[i];
            deferred.start();
            AwaitAllNode awaitAllNode = new AwaitAllNode(cancellableContinuationImpl);
            awaitAllNode.handle = deferred.invokeOnCompletion(awaitAllNode);
            awaitAllNodeArr[i] = awaitAllNode;
        }
        AwaitAll<T>.DisposeHandlersOnCancel disposeHandlersOnCancel = new DisposeHandlersOnCancel(awaitAllNodeArr);
        for (int i2 = 0; i2 < length; i2++) {
            awaitAllNodeArr[i2].setDisposer(disposeHandlersOnCancel);
        }
        if (cancellableContinuationImpl.isCompleted()) {
            disposeHandlersOnCancel.disposeAll();
        } else {
            cancellableContinuationImpl.invokeOnCancellation(disposeHandlersOnCancel);
        }
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class DisposeHandlersOnCancel extends CancelHandler {

        @NotNull
        public final AwaitAll<T>.AwaitAllNode[] nodes;

        public DisposeHandlersOnCancel(@NotNull AwaitAll<T>.AwaitAllNode[] awaitAllNodeArr) {
            this.nodes = awaitAllNodeArr;
        }

        public final void disposeAll() {
            for (AwaitAll<T>.AwaitAllNode awaitAllNode : this.nodes) {
                awaitAllNode.getHandle().dispose();
            }
        }

        @Override // kotlin.jvm.functions.Function1
        public Unit invoke(Throwable th) {
            disposeAll();
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "DisposeHandlersOnCancel[" + this.nodes + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            disposeAll();
        }
    }
}
