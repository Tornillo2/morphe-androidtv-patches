package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AggregateFuture<InputT, OutputT> extends AggregateFutureState<OutputT> {
    public static final LazyLogger logger = new LazyLogger(AggregateFuture.class);
    public final boolean allMustSucceed;
    public final boolean collectsValues;

    @LazyInit
    public ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ReleaseResourcesReason {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED
    }

    public AggregateFuture(ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures, boolean allMustSucceed, boolean collectsValues) {
        super(futures.size());
        this.futures = futures;
        this.allMustSucceed = allMustSucceed;
        this.collectsValues = collectsValues;
    }

    public static boolean addCausalChain(Set<Throwable> seen, Throwable param) {
        while (param != null) {
            if (!seen.add(param)) {
                return false;
            }
            param = param.getCause();
        }
        return true;
    }

    public static void log(Throwable throwable) {
        logger.get().log(Level.SEVERE, throwable instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", throwable);
    }

    @Override // com.google.common.util.concurrent.AggregateFutureState
    public final void addInitialException(Set<Throwable> seen) {
        seen.getClass();
        if (isCancelled()) {
            return;
        }
        Throwable thTryInternalFastPathGetFailure = tryInternalFastPathGetFailure();
        Objects.requireNonNull(thTryInternalFastPathGetFailure);
        addCausalChain(seen, thTryInternalFastPathGetFailure);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        releaseResources(ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
        if (isCancelled() && (immutableCollection != null)) {
            boolean zWasInterrupted = wasInterrupted();
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = immutableCollection.iterator();
            while (it.hasNext()) {
                it.next().cancel(zWasInterrupted);
            }
        }
    }

    public abstract void collectOneValue(int index, @ParametricNullness InputT returnValue);

    /* JADX WARN: Multi-variable type inference failed */
    public final void collectValueFromNonCancelledFuture(int index, Future<? extends InputT> future) {
        try {
            collectOneValue(index, Uninterruptibles.getUninterruptibly(future));
        } catch (ExecutionException e) {
            handleException(e.getCause());
        } catch (Throwable th) {
            handleException(th);
        }
    }

    public final void decrementCountAndMaybeComplete(ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
        int iDecrementAndGetRemainingCount = AggregateFutureState.ATOMIC_HELPER.decrementAndGetRemainingCount(this);
        Preconditions.checkState(iDecrementAndGetRemainingCount >= 0, "Less than 0 remaining futures");
        if (iDecrementAndGetRemainingCount == 0) {
            processCompleted(futuresIfNeedToCollectAtCompletion);
        }
    }

    public abstract void handleAllCompleted();

    public final void handleException(Throwable throwable) {
        throwable.getClass();
        if (this.allMustSucceed && !setException(throwable) && addCausalChain(getOrInitSeenExceptions(), throwable)) {
            log(throwable);
        } else if (throwable instanceof Error) {
            log(throwable);
        }
    }

    public final void init() {
        Objects.requireNonNull(this.futures);
        if (this.futures.isEmpty()) {
            handleAllCompleted();
            return;
        }
        if (this.allMustSucceed) {
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
            final int i = 0;
            while (it.hasNext()) {
                final ListenableFuture<? extends InputT> next = it.next();
                int i2 = i + 1;
                if (next.isDone()) {
                    processAllMustSucceedDoneFuture(i, next);
                } else {
                    next.addListener(new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.processAllMustSucceedDoneFuture(i, next);
                        }
                    }, DirectExecutor.INSTANCE);
                }
                i = i2;
            }
            return;
        }
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        final ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection2 = this.collectsValues ? immutableCollection : null;
        Runnable runnable = new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.decrementCountAndMaybeComplete(immutableCollection2);
            }
        };
        UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = immutableCollection.iterator();
        while (it2.hasNext()) {
            ListenableFuture<? extends InputT> next2 = it2.next();
            if (next2.isDone()) {
                decrementCountAndMaybeComplete(immutableCollection2);
            } else {
                next2.addListener(runnable, DirectExecutor.INSTANCE);
            }
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        if (immutableCollection == null) {
            return super.pendingToString();
        }
        return "futures=" + immutableCollection;
    }

    public final void processAllMustSucceedDoneFuture(int index, ListenableFuture<? extends InputT> future) {
        try {
            if (future.isCancelled()) {
                this.futures = null;
                cancel(false);
            } else {
                collectValueFromNonCancelledFuture(index, future);
            }
            decrementCountAndMaybeComplete(null);
        } catch (Throwable th) {
            decrementCountAndMaybeComplete(null);
            throw th;
        }
    }

    public final void processCompleted(ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
        if (futuresIfNeedToCollectAtCompletion != null) {
            UnmodifiableIterator<? extends Future<? extends InputT>> it = futuresIfNeedToCollectAtCompletion.iterator();
            int i = 0;
            while (it.hasNext()) {
                Future<? extends InputT> next = it.next();
                if (!next.isCancelled()) {
                    collectValueFromNonCancelledFuture(i, next);
                }
                i++;
            }
        }
        this.seenExceptions = null;
        handleAllCompleted();
        releaseResources(ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
    }

    @ForOverride
    @OverridingMethodsMustInvokeSuper
    public void releaseResources(ReleaseResourcesReason reason) {
        reason.getClass();
        this.futures = null;
    }
}
