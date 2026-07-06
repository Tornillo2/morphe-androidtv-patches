package com.google.common.util.concurrent;

import androidx.core.provider.FontProvider$ContentQueryWrapperApi24Impl$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ClosingFuture;
import com.google.common.util.concurrent.Futures;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Closeable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use ClosingFuture.from(Futures.immediate*Future)")
@J2ktIncompatible
public final class ClosingFuture<V> {
    public static final LazyLogger logger = new LazyLogger(ClosingFuture.class);
    public final CloseableList closeables;
    public final FluentFuture<V> future;
    public final AtomicReference<State> state;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AsyncClosingCallable<V> {
        ClosingFuture<V> call(DeferredCloser closer) throws Exception;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AsyncClosingFunction<T, U> {
        ClosingFuture<U> apply(DeferredCloser closer, @ParametricNullness T input) throws Exception;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CloseableList extends IdentityHashMap<AutoCloseable, Executor> implements Closeable {
        public volatile boolean closed;
        public final DeferredCloser closer;
        public volatile CountDownLatch whenClosed;

        public CloseableList() {
            this.closer = new DeferredCloser(this);
        }

        public void add(AutoCloseable closeable, Executor executor) {
            executor.getClass();
            if (closeable == null) {
                return;
            }
            synchronized (this) {
                try {
                    if (this.closed) {
                        ClosingFuture.closeQuietly(closeable, executor);
                    } else {
                        put(closeable, executor);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public <V, U> FluentFuture<U> applyAsyncClosingFunction(AsyncClosingFunction<V, U> transformation, @ParametricNullness V input) throws Exception {
            CloseableList closeableList = new CloseableList();
            try {
                ClosingFuture<U> closingFutureApply = transformation.apply(closeableList.closer, input);
                closingFutureApply.becomeSubsumedInto(closeableList);
                return closingFutureApply.future;
            } finally {
                add(closeableList, DirectExecutor.INSTANCE);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <V, U> ListenableFuture<U> applyClosingFunction(ClosingFunction<? super V, U> transformation, @ParametricNullness V input) throws Exception {
            CloseableList closeableList = new CloseableList();
            try {
                return Futures.immediateFuture(transformation.apply(closeableList.closer, input));
            } finally {
                add(closeableList, DirectExecutor.INSTANCE);
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            synchronized (this) {
                try {
                    if (this.closed) {
                        return;
                    }
                    this.closed = true;
                    for (Map.Entry<AutoCloseable, Executor> entry : entrySet()) {
                        ClosingFuture.closeQuietly(entry.getKey(), entry.getValue());
                    }
                    clear();
                    if (this.whenClosed != null) {
                        this.whenClosed.countDown();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public CountDownLatch whenClosedCountDown() {
            if (this.closed) {
                return new CountDownLatch(0);
            }
            synchronized (this) {
                try {
                    if (this.closed) {
                        return new CountDownLatch(0);
                    }
                    Preconditions.checkState(this.whenClosed == null);
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    this.whenClosed = countDownLatch;
                    return countDownLatch;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ClosingCallable<V> {
        @ParametricNullness
        V call(DeferredCloser closer) throws Exception;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ClosingFunction<T, U> {
        @ParametricNullness
        U apply(DeferredCloser closer, @ParametricNullness T input) throws Exception;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DoNotMock("Use ClosingFuture.whenAllSucceed() or .whenAllComplete() instead.")
    public static class Combiner {
        public final boolean allMustSucceed;
        public final CloseableList closeables;
        public final ImmutableList<ClosingFuture<?>> inputs;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface AsyncCombiningCallable<V> {
            ClosingFuture<V> call(DeferredCloser closer, Peeker peeker) throws Exception;
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface CombiningCallable<V> {
            @ParametricNullness
            V call(DeferredCloser closer, Peeker peeker) throws Exception;
        }

        public <V> ClosingFuture<V> call(final CombiningCallable<V> combiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>(futureCombiner().call(new Callable<V>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner.1
                public final /* synthetic */ Combiner this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.util.concurrent.Callable
                @ParametricNullness
                public V call() throws Exception {
                    return (V) new Peeker(this.this$0.inputs).call(combiningCallable, this.this$0.closeables);
                }

                public String toString() {
                    return combiningCallable.toString();
                }
            }, executor));
            closingFuture.closeables.add(this.closeables, DirectExecutor.INSTANCE);
            return closingFuture;
        }

        public <V> ClosingFuture<V> callAsync(final AsyncCombiningCallable<V> asyncCombiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>(futureCombiner().callAsync(new AsyncCallable<V>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner.2
                public final /* synthetic */ Combiner this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.util.concurrent.AsyncCallable
                public ListenableFuture<V> call() throws Exception {
                    return new Peeker(this.this$0.inputs).callAsync(asyncCombiningCallable, this.this$0.closeables);
                }

                public String toString() {
                    return asyncCombiningCallable.toString();
                }
            }, executor));
            closingFuture.closeables.add(this.closeables, DirectExecutor.INSTANCE);
            return closingFuture;
        }

        public final Futures.FutureCombiner<Object> futureCombiner() {
            return this.allMustSucceed ? Futures.whenAllSucceed(inputFutures()) : Futures.whenAllComplete(inputFutures());
        }

        public final ImmutableList<FluentFuture<?>> inputFutures() {
            return FluentIterable.from(this.inputs).transform(new ClosingFuture$Combiner$$ExternalSyntheticLambda0()).toList();
        }

        public Combiner(boolean allMustSucceed, Iterable<? extends ClosingFuture<?>> inputs) {
            this.closeables = new CloseableList();
            this.allMustSucceed = allMustSucceed;
            this.inputs = ImmutableList.copyOf(inputs);
            Iterator<? extends ClosingFuture<?>> it = inputs.iterator();
            while (it.hasNext()) {
                it.next().becomeSubsumedInto(this.closeables);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Combiner2<V1, V2> extends Combiner {
        public final ClosingFuture<V1> future1;
        public final ClosingFuture<V2> future2;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface AsyncClosingFunction2<V1, V2, U> {
            ClosingFuture<U> apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2) throws Exception;
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface ClosingFunction2<V1, V2, U> {
            @ParametricNullness
            U apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction2<V1, V2, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner2.1
                public final /* synthetic */ Combiner2 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return (U) function.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction2<V1, V2, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner2.2
                public final /* synthetic */ Combiner2 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public Combiner2(ClosingFuture<V1> future1, ClosingFuture<V2> future2) {
            super(true, ImmutableList.of((ClosingFuture<V2>) future1, future2));
            this.future1 = future1;
            this.future2 = future2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Combiner3<V1, V2, V3> extends Combiner {
        public final ClosingFuture<V1> future1;
        public final ClosingFuture<V2> future2;
        public final ClosingFuture<V3> future3;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface AsyncClosingFunction3<V1, V2, V3, U> {
            ClosingFuture<U> apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2, @ParametricNullness V3 value3) throws Exception;
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface ClosingFunction3<V1, V2, V3, U> {
            @ParametricNullness
            U apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2, @ParametricNullness V3 value3) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction3<V1, V2, V3, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner3.1
                public final /* synthetic */ Combiner3 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return (U) function.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction3<V1, V2, V3, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner3.2
                public final /* synthetic */ Combiner3 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public Combiner3(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3) {
            super(true, ImmutableList.of((ClosingFuture<V3>) future1, (ClosingFuture<V3>) future2, future3));
            this.future1 = future1;
            this.future2 = future2;
            this.future3 = future3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Combiner4<V1, V2, V3, V4> extends Combiner {
        public final ClosingFuture<V1> future1;
        public final ClosingFuture<V2> future2;
        public final ClosingFuture<V3> future3;
        public final ClosingFuture<V4> future4;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface AsyncClosingFunction4<V1, V2, V3, V4, U> {
            ClosingFuture<U> apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2, @ParametricNullness V3 value3, @ParametricNullness V4 value4) throws Exception;
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface ClosingFunction4<V1, V2, V3, V4, U> {
            @ParametricNullness
            U apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2, @ParametricNullness V3 value3, @ParametricNullness V4 value4) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction4<V1, V2, V3, V4, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner4.1
                public final /* synthetic */ Combiner4 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return (U) function.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction4<V1, V2, V3, V4, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner4.2
                public final /* synthetic */ Combiner4 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public Combiner4(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3, ClosingFuture<V4> future4) {
            super(true, ImmutableList.of((ClosingFuture<V4>) future1, (ClosingFuture<V4>) future2, (ClosingFuture<V4>) future3, future4));
            this.future1 = future1;
            this.future2 = future2;
            this.future3 = future3;
            this.future4 = future4;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Combiner5<V1, V2, V3, V4, V5> extends Combiner {
        public final ClosingFuture<V1> future1;
        public final ClosingFuture<V2> future2;
        public final ClosingFuture<V3> future3;
        public final ClosingFuture<V4> future4;
        public final ClosingFuture<V5> future5;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface AsyncClosingFunction5<V1, V2, V3, V4, V5, U> {
            ClosingFuture<U> apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2, @ParametricNullness V3 value3, @ParametricNullness V4 value4, @ParametricNullness V5 value5) throws Exception;
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface ClosingFunction5<V1, V2, V3, V4, V5, U> {
            @ParametricNullness
            U apply(DeferredCloser closer, @ParametricNullness V1 value1, @ParametricNullness V2 value2, @ParametricNullness V3 value3, @ParametricNullness V4 value4, @ParametricNullness V5 value5) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction5<V1, V2, V3, V4, V5, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner5.1
                public final /* synthetic */ Combiner5 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return (U) function.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4), peeker.getDone(this.this$0.future5));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction5<V1, V2, V3, V4, V5, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.Combiner5.2
                public final /* synthetic */ Combiner5 this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4), peeker.getDone(this.this$0.future5));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public Combiner5(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3, ClosingFuture<V4> future4, ClosingFuture<V5> future5) {
            super(true, ImmutableList.of((ClosingFuture<V5>) future1, (ClosingFuture<V5>) future2, (ClosingFuture<V5>) future3, (ClosingFuture<V5>) future4, future5));
            this.future1 = future1;
            this.future2 = future2;
            this.future3 = future3;
            this.future4 = future4;
            this.future5 = future5;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DeferredCloser {

        @RetainedWith
        public final CloseableList list;

        public DeferredCloser(CloseableList list) {
            this.list = list;
        }

        @CanIgnoreReturnValue
        @ParametricNullness
        public <C extends AutoCloseable> C eventuallyClose(@ParametricNullness C closeable, Executor closingExecutor) {
            closingExecutor.getClass();
            if (closeable != null) {
                this.list.add(closeable, closingExecutor);
            }
            return closeable;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Peeker {
        public volatile boolean beingCalled;
        public final ImmutableList<ClosingFuture<?>> futures;

        @ParametricNullness
        public final <V> V call(Combiner.CombiningCallable<V> combiner, CloseableList closeables) throws Exception {
            this.beingCalled = true;
            CloseableList closeableList = new CloseableList();
            try {
                return combiner.call(closeableList.closer, this);
            } finally {
                closeables.add(closeableList, DirectExecutor.INSTANCE);
                this.beingCalled = false;
            }
        }

        public final <V> FluentFuture<V> callAsync(Combiner.AsyncCombiningCallable<V> combiner, CloseableList closeables) throws Exception {
            this.beingCalled = true;
            CloseableList closeableList = new CloseableList();
            try {
                ClosingFuture<V> closingFutureCall = combiner.call(closeableList.closer, this);
                closingFutureCall.becomeSubsumedInto(closeables);
                return closingFutureCall.future;
            } finally {
                closeables.add(closeableList, DirectExecutor.INSTANCE);
                this.beingCalled = false;
            }
        }

        @ParametricNullness
        public final <D> D getDone(ClosingFuture<D> closingFuture) throws ExecutionException {
            Preconditions.checkState(this.beingCalled);
            Preconditions.checkArgument(this.futures.contains(closingFuture));
            return (D) Futures.getDone(closingFuture.future);
        }

        public Peeker(ImmutableList<ClosingFuture<?>> futures) {
            futures.getClass();
            this.futures = futures;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum State {
        OPEN,
        SUBSUMED,
        WILL_CLOSE,
        CLOSING,
        CLOSED,
        WILL_CREATE_VALUE_AND_CLOSER
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ValueAndCloser<V> {
        public final ClosingFuture<? extends V> closingFuture;

        public ValueAndCloser(ClosingFuture<? extends V> closingFuture) {
            closingFuture.getClass();
            this.closingFuture = closingFuture;
        }

        public void closeAsync() {
            this.closingFuture.close();
        }

        @ParametricNullness
        public V get() throws ExecutionException {
            return (V) Futures.getDone(this.closingFuture.future);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ValueAndCloserConsumer<V> {
        void accept(ValueAndCloser<V> valueAndCloser);
    }

    /* JADX INFO: renamed from: $r8$lambda$2_jmTn9UvItULsyQK22-fD2KrBw, reason: not valid java name */
    public static ClosingFuture m560$r8$lambda$2_jmTn9UvItULsyQK22fD2KrBw(AsyncFunction asyncFunction, DeferredCloser deferredCloser, Object obj) {
        return new ClosingFuture(asyncFunction.apply(obj));
    }

    public static /* synthetic */ void $r8$lambda$SxENyZat_51sDBJwMfuLuMg6kOY(AutoCloseable autoCloseable) {
        try {
            FontProvider$ContentQueryWrapperApi24Impl$$ExternalSyntheticAutoCloseableDispatcher0.m(autoCloseable);
        } catch (Exception e) {
            Platform.restoreInterruptIfIsInterruptedException(e);
            logger.get().log(Level.WARNING, "thrown by close()", (Throwable) e);
        }
    }

    public static void $r8$lambda$dFw7K72K0RcpDEjXnCOnN7VfJ_o(ClosingFuture closingFuture, ValueAndCloserConsumer valueAndCloserConsumer) {
        closingFuture.getClass();
        valueAndCloserConsumer.accept(new ValueAndCloser<>(closingFuture));
    }

    public static /* synthetic */ void $r8$lambda$hRbC5EzYVWnKH_mW2bYxxV2wQBA(ClosingFuture closingFuture) {
        closingFuture.getClass();
        State state = State.WILL_CLOSE;
        State state2 = State.CLOSING;
        closingFuture.checkAndUpdateState(state, state2);
        closingFuture.close();
        closingFuture.checkAndUpdateState(state2, State.CLOSED);
    }

    public static void closeQuietly(final AutoCloseable closeable, Executor executor) {
        if (closeable == null) {
            return;
        }
        try {
            executor.execute(new Runnable() { // from class: com.google.common.util.concurrent.ClosingFuture$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ClosingFuture.$r8$lambda$SxENyZat_51sDBJwMfuLuMg6kOY(closeable);
                }
            });
        } catch (RejectedExecutionException e) {
            LazyLogger lazyLogger = logger;
            Logger logger2 = lazyLogger.get();
            Level level = Level.WARNING;
            if (logger2.isLoggable(level)) {
                lazyLogger.get().log(level, String.format("while submitting close to %s; will close inline", executor), (Throwable) e);
            }
            closeQuietly(closeable, DirectExecutor.INSTANCE);
        }
    }

    @Deprecated
    public static <C extends AutoCloseable> ClosingFuture<C> eventuallyClosing(ListenableFuture<C> future, final Executor closingExecutor) {
        closingExecutor.getClass();
        ClosingFuture<C> closingFuture = new ClosingFuture<>(Futures.nonCancellationPropagating(future));
        Futures.addCallback(future, new FutureCallback<AutoCloseable>() { // from class: com.google.common.util.concurrent.ClosingFuture.3
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(AutoCloseable result) {
                ClosingFuture.this.closeables.closer.eventuallyClose(result, closingExecutor);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable t) {
            }
        }, DirectExecutor.INSTANCE);
        return closingFuture;
    }

    public static <V> ClosingFuture<V> from(ListenableFuture<V> future) {
        return new ClosingFuture<>(future);
    }

    public static <C, V extends C> void provideValueAndCloser(ValueAndCloserConsumer<C> consumer, ClosingFuture<V> closingFuture) {
        consumer.accept(new ValueAndCloser<>(closingFuture));
    }

    public static <V> ClosingFuture<V> submit(final ClosingCallable<V> callable, Executor executor) {
        callable.getClass();
        final CloseableList closeableList = new CloseableList();
        TrustedListenableFutureTask trustedListenableFutureTaskCreate = TrustedListenableFutureTask.create(new Callable<V>() { // from class: com.google.common.util.concurrent.ClosingFuture.1
            @Override // java.util.concurrent.Callable
            @ParametricNullness
            public V call() throws Exception {
                return (V) callable.call(closeableList.closer);
            }

            public String toString() {
                return callable.toString();
            }
        });
        executor.execute(trustedListenableFutureTaskCreate);
        return new ClosingFuture<>(trustedListenableFutureTaskCreate, closeableList);
    }

    public static <V> ClosingFuture<V> submitAsync(final AsyncClosingCallable<V> callable, Executor executor) {
        callable.getClass();
        final CloseableList closeableList = new CloseableList();
        TrustedListenableFutureTask trustedListenableFutureTaskCreate = TrustedListenableFutureTask.create(new AsyncCallable<V>() { // from class: com.google.common.util.concurrent.ClosingFuture.2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<V> call() throws Exception {
                CloseableList closeableList2 = new CloseableList();
                try {
                    ClosingFuture<V> closingFutureCall = callable.call(closeableList2.closer);
                    closingFutureCall.becomeSubsumedInto(closeableList);
                    return closingFutureCall.future;
                } finally {
                    closeableList.add(closeableList2, DirectExecutor.INSTANCE);
                }
            }

            public String toString() {
                return callable.toString();
            }
        });
        executor.execute(trustedListenableFutureTaskCreate);
        return new ClosingFuture<>(trustedListenableFutureTaskCreate, closeableList);
    }

    public static Combiner whenAllComplete(ClosingFuture<?> future1, ClosingFuture<?>... moreFutures) {
        return whenAllComplete(new Lists.OnePlusArrayList(future1, moreFutures));
    }

    public static Combiner whenAllSucceed(Iterable<? extends ClosingFuture<?>> futures) {
        return new Combiner(true, futures);
    }

    public static <V, U> AsyncClosingFunction<V, U> withoutCloser(final AsyncFunction<V, U> function) {
        function.getClass();
        return new AsyncClosingFunction() { // from class: com.google.common.util.concurrent.ClosingFuture$$ExternalSyntheticLambda3
            @Override // com.google.common.util.concurrent.ClosingFuture.AsyncClosingFunction
            public final ClosingFuture apply(ClosingFuture.DeferredCloser deferredCloser, Object obj) {
                return ClosingFuture.m560$r8$lambda$2_jmTn9UvItULsyQK22fD2KrBw(function, deferredCloser, obj);
            }
        };
    }

    public final void becomeSubsumedInto(CloseableList otherCloseables) {
        checkAndUpdateState(State.OPEN, State.SUBSUMED);
        otherCloseables.add(this.closeables, DirectExecutor.INSTANCE);
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean mayInterruptIfRunning) {
        logger.get().log(Level.FINER, "cancelling {0}", this);
        boolean zCancel = this.future.cancel(mayInterruptIfRunning);
        if (zCancel) {
            close();
        }
        return zCancel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <X extends Throwable> ClosingFuture<V> catching(Class<X> exceptionType, ClosingFunction<? super X, ? extends V> fallback, Executor executor) {
        return catchingMoreGeneric(exceptionType, fallback, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <X extends Throwable> ClosingFuture<V> catchingAsync(Class<X> exceptionType, AsyncClosingFunction<? super X, ? extends V> fallback, Executor executor) {
        return catchingAsyncMoreGeneric(exceptionType, fallback, executor);
    }

    public final <X extends Throwable, W extends V> ClosingFuture<V> catchingAsyncMoreGeneric(Class<X> cls, final AsyncClosingFunction<? super X, W> asyncClosingFunction, Executor executor) {
        asyncClosingFunction.getClass();
        AsyncFunction<X, W> asyncFunction = new AsyncFunction<X, W>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.7
            public final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = this;
            }

            public String toString() {
                return asyncClosingFunction.toString();
            }

            /* JADX WARN: Incorrect types in method signature: (TX;)Lcom/google/common/util/concurrent/ListenableFuture<TW;>; */
            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture apply(Throwable exception) throws Exception {
                return this.this$0.closeables.applyAsyncClosingFunction(asyncClosingFunction, exception);
            }
        };
        FluentFuture<V> fluentFuture = this.future;
        fluentFuture.getClass();
        return (ClosingFuture<V>) derive((FluentFuture) AbstractCatchingFuture.createAsync(fluentFuture, cls, asyncFunction, executor));
    }

    public final <X extends Throwable, W extends V> ClosingFuture<V> catchingMoreGeneric(Class<X> cls, final ClosingFunction<? super X, W> closingFunction, Executor executor) {
        closingFunction.getClass();
        AsyncFunction<X, W> asyncFunction = new AsyncFunction<X, W>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.6
            public final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = this;
            }

            public String toString() {
                return closingFunction.toString();
            }

            /* JADX WARN: Incorrect types in method signature: (TX;)Lcom/google/common/util/concurrent/ListenableFuture<TW;>; */
            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture apply(Throwable exception) throws Exception {
                return this.this$0.closeables.applyClosingFunction(closingFunction, exception);
            }
        };
        FluentFuture<V> fluentFuture = this.future;
        fluentFuture.getClass();
        return (ClosingFuture<V>) derive((FluentFuture) AbstractCatchingFuture.createAsync(fluentFuture, cls, asyncFunction, executor));
    }

    public final void checkAndUpdateState(State oldState, State newState) {
        Preconditions.checkState(LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, oldState, newState), "Expected state to be %s, but it was %s", oldState, newState);
    }

    public final void close() {
        logger.get().log(Level.FINER, "closing {0}", this);
        this.closeables.close();
    }

    public final boolean compareAndUpdateState(State oldState, State newState) {
        return LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, oldState, newState);
    }

    public final <U> ClosingFuture<U> derive(FluentFuture<U> future) {
        ClosingFuture<U> closingFuture = new ClosingFuture<>(future);
        becomeSubsumedInto(closingFuture.closeables);
        return closingFuture;
    }

    public void finalize() {
        if (this.state.get().equals(State.OPEN)) {
            logger.get().log(Level.SEVERE, "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
            finishToFuture();
        }
    }

    public FluentFuture<V> finishToFuture() {
        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, State.OPEN, State.WILL_CLOSE)) {
            logger.get().log(Level.FINER, "will close {0}", this);
            this.future.addListener(new Runnable() { // from class: com.google.common.util.concurrent.ClosingFuture$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ClosingFuture.$r8$lambda$hRbC5EzYVWnKH_mW2bYxxV2wQBA(this.f$0);
                }
            }, DirectExecutor.INSTANCE);
        } else {
            int iOrdinal = this.state.get().ordinal();
            if (iOrdinal == 0) {
                throw new AssertionError();
            }
            if (iOrdinal == 1) {
                throw new IllegalStateException("Cannot call finishToFuture() after deriving another step");
            }
            if (iOrdinal == 2 || iOrdinal == 3 || iOrdinal == 4) {
                throw new IllegalStateException("Cannot call finishToFuture() twice");
            }
            if (iOrdinal == 5) {
                throw new IllegalStateException("Cannot call finishToFuture() after calling finishToValueAndCloser()");
            }
        }
        return this.future;
    }

    public void finishToValueAndCloser(final ValueAndCloserConsumer<? super V> consumer, Executor executor) {
        consumer.getClass();
        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, State.OPEN, State.WILL_CREATE_VALUE_AND_CLOSER)) {
            this.future.addListener(new Runnable() { // from class: com.google.common.util.concurrent.ClosingFuture$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ClosingFuture.$r8$lambda$dFw7K72K0RcpDEjXnCOnN7VfJ_o(this.f$0, consumer);
                }
            }, executor);
            return;
        }
        int iOrdinal = this.state.get().ordinal();
        if (iOrdinal == 1) {
            throw new IllegalStateException("Cannot call finishToValueAndCloser() after deriving another step");
        }
        if (iOrdinal == 2 || iOrdinal == 3 || iOrdinal == 4) {
            throw new IllegalStateException("Cannot call finishToValueAndCloser() after calling finishToFuture()");
        }
        if (iOrdinal == 5) {
            throw new IllegalStateException("Cannot call finishToValueAndCloser() twice");
        }
        throw new AssertionError(this.state);
    }

    public ListenableFuture<?> statusFuture() {
        FluentFuture<V> fluentFuture = this.future;
        Functions.ConstantFunction constantFunction = new Functions.ConstantFunction(null);
        DirectExecutor directExecutor = DirectExecutor.INSTANCE;
        fluentFuture.getClass();
        return Futures.nonCancellationPropagating((FluentFuture) AbstractTransformFuture.create(fluentFuture, constantFunction, directExecutor));
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.addHolder("state", this.state.get());
        stringHelper.addHolder().value = this.future;
        return stringHelper.toString();
    }

    public <U> ClosingFuture<U> transform(final ClosingFunction<? super V, U> function, Executor executor) {
        function.getClass();
        AsyncFunction<V, U> asyncFunction = new AsyncFunction<V, U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.4
            public final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture<U> apply(V input) throws Exception {
                return this.this$0.closeables.applyClosingFunction(function, input);
            }

            public String toString() {
                return function.toString();
            }
        };
        FluentFuture<V> fluentFuture = this.future;
        fluentFuture.getClass();
        return derive((FluentFuture) AbstractTransformFuture.createAsync(fluentFuture, asyncFunction, executor));
    }

    public <U> ClosingFuture<U> transformAsync(final AsyncClosingFunction<? super V, U> function, Executor executor) {
        function.getClass();
        AsyncFunction<V, U> asyncFunction = new AsyncFunction<V, U>(this) { // from class: com.google.common.util.concurrent.ClosingFuture.5
            public final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture<U> apply(V input) throws Exception {
                return this.this$0.closeables.applyAsyncClosingFunction(function, input);
            }

            public String toString() {
                return function.toString();
            }
        };
        FluentFuture<V> fluentFuture = this.future;
        fluentFuture.getClass();
        return derive((FluentFuture) AbstractTransformFuture.createAsync(fluentFuture, asyncFunction, executor));
    }

    @VisibleForTesting
    public CountDownLatch whenClosedCountDown() {
        return this.closeables.whenClosedCountDown();
    }

    public ClosingFuture(ListenableFuture<V> future) {
        this(future, new CloseableList());
    }

    public static Combiner whenAllComplete(Iterable<? extends ClosingFuture<?>> futures) {
        return new Combiner(false, futures);
    }

    public static <V1, V2> Combiner2<V1, V2> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2) {
        return new Combiner2<>(future1, future2);
    }

    public ClosingFuture(ListenableFuture<V> future, CloseableList closeables) {
        this.state = new AtomicReference<>(State.OPEN);
        this.future = FluentFuture.from(future);
        this.closeables = closeables;
    }

    public static <V1, V2, V3> Combiner3<V1, V2, V3> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3) {
        return new Combiner3<>(future1, future2, future3);
    }

    public static <V1, V2, V3, V4> Combiner4<V1, V2, V3, V4> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3, ClosingFuture<V4> future4) {
        return new Combiner4<>(future1, future2, future3, future4);
    }

    public static <V1, V2, V3, V4, V5> Combiner5<V1, V2, V3, V4, V5> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3, ClosingFuture<V4> future4, ClosingFuture<V5> future5) {
        return new Combiner5<>(future1, future2, future3, future4, future5);
    }

    public static Combiner whenAllSucceed(ClosingFuture<?> future1, ClosingFuture<?> future2, ClosingFuture<?> future3, ClosingFuture<?> future4, ClosingFuture<?> future5, ClosingFuture<?> future6, ClosingFuture<?>... moreFutures) {
        return whenAllSucceed(FluentIterable.of(future1, future2, future3, future4, future5, future6).append(moreFutures));
    }
}
