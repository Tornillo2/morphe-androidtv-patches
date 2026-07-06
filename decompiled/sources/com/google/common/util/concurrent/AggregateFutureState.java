package com.google.common.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.j2objc.annotations.ReflectionSupport;
import j$.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
public abstract class AggregateFutureState<OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    public static final AtomicHelper ATOMIC_HELPER;
    public static final LazyLogger log = new LazyLogger(AggregateFutureState.class);
    public volatile int remaining;
    public volatile Set<Throwable> seenExceptions = null;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class AtomicHelper {
        public AtomicHelper() {
        }

        public abstract void compareAndSetSeenExceptions(AggregateFutureState<?> state, Set<Throwable> expect, Set<Throwable> update);

        public abstract int decrementAndGetRemainingCount(AggregateFutureState<?> state);

        public AtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SafeAtomicHelper extends AtomicHelper {
        public static final AtomicReferenceFieldUpdater<? super AggregateFutureState<?>, ? super Set<Throwable>> seenExceptionsUpdater = AggregateFutureState.seenExceptionsUpdaterFromWithinAggregateFutureState();
        public static final AtomicIntegerFieldUpdater<? super AggregateFutureState<?>> remainingCountUpdater = AggregateFutureState.remainingCountUpdaterFromWithinAggregateFutureState();

        public SafeAtomicHelper() {
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public void compareAndSetSeenExceptions(AggregateFutureState<?> state, Set<Throwable> expect, Set<Throwable> update) {
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(seenExceptionsUpdater, state, expect, update);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public int decrementAndGetRemainingCount(AggregateFutureState<?> state) {
            return remainingCountUpdater.decrementAndGet(state);
        }

        public SafeAtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedAtomicHelper extends AtomicHelper {
        public SynchronizedAtomicHelper() {
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public void compareAndSetSeenExceptions(AggregateFutureState<?> state, Set<Throwable> expect, Set<Throwable> update) {
            synchronized (state) {
                try {
                    if (state.seenExceptions == expect) {
                        state.seenExceptions = update;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public int decrementAndGetRemainingCount(AggregateFutureState<?> state) {
            int iAccess$606;
            synchronized (state) {
                iAccess$606 = AggregateFutureState.access$606(state);
            }
            return iAccess$606;
        }

        public SynchronizedAtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    static {
        Throwable th;
        AtomicHelper synchronizedAtomicHelper;
        try {
            synchronizedAtomicHelper = new SafeAtomicHelper();
            th = null;
        } catch (Throwable th2) {
            th = th2;
            synchronizedAtomicHelper = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = synchronizedAtomicHelper;
        if (th != null) {
            log.get().log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    public AggregateFutureState(int remainingFutures) {
        this.remaining = remainingFutures;
    }

    public static /* synthetic */ int access$606(AggregateFutureState aggregateFutureState) {
        int i = aggregateFutureState.remaining - 1;
        aggregateFutureState.remaining = i;
        return i;
    }

    public static AtomicIntegerFieldUpdater<? super AggregateFutureState<?>> remainingCountUpdaterFromWithinAggregateFutureState() {
        return AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining");
    }

    public static AtomicReferenceFieldUpdater<? super AggregateFutureState<?>, ? super Set<Throwable>> seenExceptionsUpdaterFromWithinAggregateFutureState() {
        return AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions");
    }

    public abstract void addInitialException(Set<Throwable> seen);

    public final void clearSeenExceptions() {
        this.seenExceptions = null;
    }

    public final int decrementRemainingAndGet() {
        return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
    }

    public final Set<Throwable> getOrInitSeenExceptions() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set<Throwable> setNewConcurrentHashSet = Sets.newConcurrentHashSet();
        addInitialException(setNewConcurrentHashSet);
        ATOMIC_HELPER.compareAndSetSeenExceptions(this, null, setNewConcurrentHashSet);
        Set<Throwable> set2 = this.seenExceptions;
        Objects.requireNonNull(set2);
        return set2;
    }
}
