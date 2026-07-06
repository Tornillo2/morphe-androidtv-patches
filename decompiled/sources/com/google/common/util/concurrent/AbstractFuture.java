package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.j2objc.annotations.ReflectionSupport;
import j$.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ObjectUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible
public abstract class AbstractFuture<V> extends AbstractFutureState<V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Cancellation {
        public static final Cancellation CAUSELESS_CANCELLED;
        public static final Cancellation CAUSELESS_INTERRUPTED;
        public final Throwable cause;
        public final boolean wasInterrupted;

        static {
            if (AbstractFutureState.GENERATE_CANCELLATION_CAUSES) {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
            } else {
                CAUSELESS_CANCELLED = new Cancellation(false, null);
                CAUSELESS_INTERRUPTED = new Cancellation(true, null);
            }
        }

        public Cancellation(boolean wasInterrupted, Throwable cause) {
            this.wasInterrupted = wasInterrupted;
            this.cause = cause;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DelegatingToFuture<V> implements Runnable {
        public final ListenableFuture<? extends V> future;
        public final AbstractFuture<V> owner;

        public DelegatingToFuture(AbstractFuture<V> owner, ListenableFuture<? extends V> future) {
            this.owner = owner;
            this.future = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.owner.value != this) {
                return;
            }
            Object futureValue = AbstractFuture.getFutureValue(this.future);
            if (AbstractFutureState.ATOMIC_HELPER.casValue(this.owner, this, futureValue)) {
                AbstractFuture.complete(this.owner, false);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Trusted<V> extends ListenableFuture<V> {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class TrustedFuture<V> extends AbstractFuture<V> implements Trusted<V> {
        @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable listener, Executor executor) {
            super.addListener(listener, executor);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public boolean cancel(boolean mayInterruptIfRunning) {
            return super.cancel(mayInterruptIfRunning);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        @ParametricNullness
        public V get() throws ExecutionException, InterruptedException {
            return blockingGet();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public boolean isCancelled() {
            return this.value instanceof Cancellation;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        @ParametricNullness
        public V get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
            return blockingGet(timeout, unit);
        }
    }

    public static CancellationException cancellationExceptionWithCause(String message, Throwable cause) {
        CancellationException cancellationException = new CancellationException(message);
        cancellationException.initCause(cause);
        return cancellationException;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void complete(AbstractFuture<?> abstractFuture, boolean z) {
        Listener listener = null;
        while (true) {
            abstractFuture.releaseWaiters();
            if (z) {
                abstractFuture.interruptTask();
                z = false;
            }
            abstractFuture.afterDone();
            Listener listenerClearListeners = abstractFuture.clearListeners(listener);
            while (listenerClearListeners != null) {
                listener = listenerClearListeners.next;
                Runnable runnable = listenerClearListeners.task;
                Objects.requireNonNull(runnable);
                Runnable runnable2 = runnable;
                if (runnable2 instanceof DelegatingToFuture) {
                    DelegatingToFuture delegatingToFuture = (DelegatingToFuture) runnable2;
                    abstractFuture = delegatingToFuture.owner;
                    if (abstractFuture.value == delegatingToFuture) {
                        if (AbstractFutureState.ATOMIC_HELPER.casValue(abstractFuture, delegatingToFuture, getFutureValue(delegatingToFuture.future))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = listenerClearListeners.executor;
                    Objects.requireNonNull(executor);
                    executeListener(runnable2, executor);
                }
                listenerClearListeners = listener;
            }
            return;
        }
    }

    public static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            AbstractFutureState.log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ParametricNullness
    public static <V> V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
        }
        if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).exception);
        }
        if (obj == AbstractFutureState.NULL) {
            return null;
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static Object getFutureValue(ListenableFuture<?> future) {
        Throwable thTryInternalFastPathGetFailure;
        if (future instanceof Trusted) {
            Object cancellation = ((AbstractFuture) future).value;
            if (cancellation instanceof Cancellation) {
                Cancellation cancellation2 = (Cancellation) cancellation;
                if (cancellation2.wasInterrupted) {
                    cancellation = cancellation2.cause != null ? new Cancellation(false, cancellation2.cause) : Cancellation.CAUSELESS_CANCELLED;
                }
            }
            Objects.requireNonNull(cancellation);
            return cancellation;
        }
        if ((future instanceof InternalFutureFailureAccess) && (thTryInternalFastPathGetFailure = ((InternalFutureFailureAccess) future).tryInternalFastPathGetFailure()) != null) {
            return new Failure(thTryInternalFastPathGetFailure);
        }
        boolean zIsCancelled = future.isCancelled();
        if ((!AbstractFutureState.GENERATE_CANCELLATION_CAUSES) && zIsCancelled) {
            Cancellation cancellation3 = Cancellation.CAUSELESS_CANCELLED;
            Objects.requireNonNull(cancellation3);
            return cancellation3;
        }
        try {
            Object uninterruptibly = getUninterruptibly(future);
            if (!zIsCancelled) {
                return uninterruptibly == null ? AbstractFutureState.NULL : uninterruptibly;
            }
            return new Cancellation(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + future));
        } catch (Error | Exception e) {
            return new Failure(e);
        } catch (CancellationException e2) {
            if (zIsCancelled) {
                return new Cancellation(false, e2);
            }
            return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + future, e2));
        } catch (ExecutionException e3) {
            if (!zIsCancelled) {
                return new Failure(e3.getCause());
            }
            return new Cancellation(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + future, e3));
        }
    }

    @ParametricNullness
    private static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Platform.interruptCurrentThread();
                }
                throw th;
            }
        }
        if (z) {
            Platform.interruptCurrentThread();
        }
        return v;
    }

    public static boolean notInstanceOfDelegatingToFuture(Object obj) {
        return !(obj instanceof DelegatingToFuture);
    }

    public final void addDoneString(StringBuilder builder) {
        try {
            Object uninterruptibly = getUninterruptibly(this);
            builder.append("SUCCESS, result=[");
            appendResultObject(builder, uninterruptibly);
            builder.append("]");
        } catch (CancellationException unused) {
            builder.append("CANCELLED");
        } catch (ExecutionException e) {
            builder.append("FAILURE, cause=[");
            builder.append(e.getCause());
            builder.append("]");
        } catch (Exception e2) {
            builder.append("UNKNOWN, cause=[");
            builder.append(e2.getClass());
            builder.append(" thrown from get()]");
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor executor) {
        Listener listener2;
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        if (!isDone() && (listener2 = this.listeners) != Listener.TOMBSTONE) {
            Listener listener3 = new Listener(listener, executor);
            do {
                listener3.next = listener2;
                if (AbstractFutureState.ATOMIC_HELPER.casListeners(this, listener2, listener3)) {
                    return;
                } else {
                    listener2 = this.listeners;
                }
            } while (listener2 != Listener.TOMBSTONE);
        }
        executeListener(listener, executor);
    }

    public final void addPendingString(StringBuilder builder) {
        String strEmptyToNull;
        int length = builder.length();
        builder.append("PENDING");
        Object obj = this.value;
        if (obj instanceof DelegatingToFuture) {
            builder.append(", setFuture=[");
            appendUserObject(builder, ((DelegatingToFuture) obj).future);
            builder.append("]");
        } else {
            try {
                strEmptyToNull = com.google.common.base.Platform.emptyToNull(pendingToString());
            } catch (Throwable th) {
                Platform.rethrowIfErrorOtherThanStackOverflow(th);
                strEmptyToNull = "Exception thrown from implementation: " + th.getClass();
            }
            if (strEmptyToNull != null) {
                builder.append(", info=[");
                builder.append(strEmptyToNull);
                builder.append("]");
            }
        }
        if (isDone()) {
            builder.delete(length, builder.length());
            addDoneString(builder);
        }
    }

    public final void appendResultObject(StringBuilder builder, Object o) {
        if (o == null) {
            builder.append(AbstractJsonLexerKt.NULL);
        } else {
            if (o == this) {
                builder.append("this future");
                return;
            }
            builder.append(o.getClass().getName());
            builder.append("@");
            builder.append(Integer.toHexString(System.identityHashCode(o)));
        }
    }

    public final void appendUserObject(StringBuilder builder, Object o) {
        try {
            if (o == this) {
                builder.append("this future");
            } else {
                builder.append(o);
            }
        } catch (Throwable th) {
            Platform.rethrowIfErrorOtherThanStackOverflow(th);
            builder.append("Exception thrown from implementation: ");
            builder.append(th.getClass());
        }
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public boolean cancel(boolean mayInterruptIfRunning) {
        Cancellation cancellation;
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof DelegatingToFuture)) {
            return false;
        }
        if (AbstractFutureState.GENERATE_CANCELLATION_CAUSES) {
            cancellation = new Cancellation(mayInterruptIfRunning, new CancellationException("Future.cancel() was called."));
        } else {
            cancellation = mayInterruptIfRunning ? Cancellation.CAUSELESS_INTERRUPTED : Cancellation.CAUSELESS_CANCELLED;
            Objects.requireNonNull(cancellation);
        }
        boolean z = false;
        AbstractFuture<V> abstractFuture = this;
        while (true) {
            if (AbstractFutureState.ATOMIC_HELPER.casValue(abstractFuture, obj, cancellation)) {
                complete(abstractFuture, mayInterruptIfRunning);
                if (!(obj instanceof DelegatingToFuture)) {
                    break;
                }
                ListenableFuture<? extends V> listenableFuture = ((DelegatingToFuture) obj).future;
                if (!(listenableFuture instanceof Trusted)) {
                    listenableFuture.cancel(mayInterruptIfRunning);
                    break;
                }
                abstractFuture = (AbstractFuture) listenableFuture;
                obj = abstractFuture.value;
                if (!(obj == null) && !(obj instanceof DelegatingToFuture)) {
                    break;
                }
                z = true;
            } else {
                obj = abstractFuture.value;
                if (!(obj instanceof DelegatingToFuture)) {
                    return z;
                }
            }
        }
        return true;
    }

    public final Listener clearListeners(Listener onto) {
        Listener listenerGasListeners = AbstractFutureState.ATOMIC_HELPER.gasListeners(this, Listener.TOMBSTONE);
        Listener listener = onto;
        Listener listener2 = listenerGasListeners;
        while (listener2 != null) {
            Listener listener3 = listener2.next;
            listener2.next = listener;
            listener = listener2;
            listener2 = listener3;
        }
        return listener;
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    @ParametricNullness
    public V get() throws ExecutionException, InterruptedException {
        return blockingGet();
    }

    @ParametricNullness
    public final V getFromAlreadyDoneTrustedFuture() throws ExecutionException {
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof DelegatingToFuture)) {
            return (V) getDoneValue(obj);
        }
        throw new IllegalStateException("Cannot get() on a pending future.");
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return (!(r0 instanceof DelegatingToFuture)) & (this.value != null);
    }

    public final void maybePropagateCancellationTo(Future<?> related) {
        if ((related != null) && isCancelled()) {
            related.cancel(wasInterrupted());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String pendingToString() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @CanIgnoreReturnValue
    public boolean set(@ParametricNullness V v) {
        if (v == null) {
            v = (V) AbstractFutureState.NULL;
        }
        if (!AbstractFutureState.ATOMIC_HELPER.casValue(this, null, v)) {
            return false;
        }
        complete(this, false);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean setException(Throwable throwable) {
        throwable.getClass();
        if (!AbstractFutureState.ATOMIC_HELPER.casValue(this, null, new Failure(throwable))) {
            return false;
        }
        complete(this, false);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean setFuture(ListenableFuture<? extends V> future) {
        Failure failure;
        future.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (future.isDone()) {
                if (!AbstractFutureState.ATOMIC_HELPER.casValue(this, null, getFutureValue(future))) {
                    return false;
                }
                complete(this, false);
                return true;
            }
            DelegatingToFuture delegatingToFuture = new DelegatingToFuture(this, future);
            if (AbstractFutureState.ATOMIC_HELPER.casValue(this, null, delegatingToFuture)) {
                try {
                    future.addListener(delegatingToFuture, DirectExecutor.INSTANCE);
                } catch (Throwable th) {
                    try {
                        failure = new Failure(th);
                    } catch (Error | Exception unused) {
                        failure = Failure.FALLBACK_INSTANCE;
                    }
                    AbstractFutureState.casValue(this, delegatingToFuture, failure);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof Cancellation) {
            future.cancel(((Cancellation) obj).wasInterrupted);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append(ObjectUtils.AT_SIGN);
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(sb);
        } else {
            addPendingString(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.common.util.concurrent.internal.InternalFutureFailureAccess
    public final Throwable tryInternalFastPathGetFailure() {
        if (!(this instanceof Trusted)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof Cancellation) && ((Cancellation) obj).wasInterrupted;
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    @ParametricNullness
    public V get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        return blockingGet(timeout, unit);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Listener {
        public static final Listener TOMBSTONE = new Listener();
        public final Executor executor;
        public Listener next;
        public final Runnable task;

        public Listener(Runnable task, Executor executor) {
            this.task = task;
            this.executor = executor;
        }

        public Listener() {
            this.task = null;
            this.executor = null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Failure {
        public static final Failure FALLBACK_INSTANCE = new Failure(new AnonymousClass1("Failure occurred while trying to finish a future."));
        public final Throwable exception;

        public Failure(Throwable exception) {
            exception.getClass();
            this.exception = exception;
        }

        /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractFuture$Failure$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends Throwable {
            public AnonymousClass1(String message) {
                super(message);
            }

            @Override // java.lang.Throwable
            public Throwable fillInStackTrace() {
                return this;
            }
        }
    }

    @ForOverride
    public void afterDone() {
    }

    public void interruptTask() {
    }
}
