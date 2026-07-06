package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class ImmediateFuture<V> implements ListenableFuture<V> {
    public static final ListenableFuture<?> NULL = new ImmediateFuture(null);
    public static final LazyLogger log = new LazyLogger(ImmediateFuture.class);

    @ParametricNullness
    public final V value;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        public static final ImmediateCancelledFuture<Object> INSTANCE;

        static {
            INSTANCE = AbstractFutureState.GENERATE_CANCELLATION_CAUSES ? null : new ImmediateCancelledFuture<>();
        }

        public ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        public ImmediateFailedFuture(Throwable thrown) {
            setException(thrown);
        }
    }

    public ImmediateFuture(@ParametricNullness V value) {
        this.value = value;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor executor) {
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(listener);
        } catch (Exception e) {
            log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override // java.util.concurrent.Future
    @ParametricNullness
    public V get() {
        return this.value;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    public String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
    }

    @Override // java.util.concurrent.Future
    @ParametricNullness
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        unit.getClass();
        return get();
    }
}
