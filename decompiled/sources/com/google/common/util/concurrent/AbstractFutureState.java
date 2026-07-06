package com.google.common.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzck$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.j2objc.annotations.ReflectionSupport;
import j$.util.Objects;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
public abstract class AbstractFutureState<V> extends InternalFutureFailureAccess implements ListenableFuture<V> {
    public static final AtomicHelper ATOMIC_HELPER;
    public static final boolean GENERATE_CANCELLATION_CAUSES;
    public static final long SPIN_THRESHOLD_NANOS = 1000;
    public volatile AbstractFuture.Listener listeners;
    public volatile Object value;
    public volatile Waiter waiters;
    public static final Object NULL = new Object();
    public static final LazyLogger log = new LazyLogger(AbstractFuture.class);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class AtomicHelper {
        public AtomicHelper() {
        }

        public abstract boolean casListeners(AbstractFutureState<?> future, AbstractFuture.Listener expect, AbstractFuture.Listener update);

        public abstract boolean casValue(AbstractFutureState<?> future, Object expect, Object update);

        public abstract boolean casWaiters(AbstractFutureState<?> future, Waiter expect, Waiter update);

        public abstract AbstractFuture.Listener gasListeners(AbstractFutureState<?> future, AbstractFuture.Listener update);

        public abstract Waiter gasWaiters(AbstractFutureState<?> future, Waiter update);

        public abstract void putNext(Waiter waiter, Waiter newValue);

        public abstract void putThread(Waiter waiter, Thread newValue);

        public AtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AtomicReferenceFieldUpdaterAtomicHelper extends AtomicHelper {
        public static final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater = AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread");
        public static final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater = AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next");
        public static final AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, Waiter> waitersUpdater = AbstractFutureState.waitersUpdaterFromWithinAbstractFutureState();
        public static final AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, AbstractFuture.Listener> listenersUpdater = AbstractFutureState.listenersUpdaterFromWithinAbstractFutureState();
        public static final AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, Object> valueUpdater = AbstractFutureState.valueUpdaterFromWithinAbstractFutureState();

        public AtomicReferenceFieldUpdaterAtomicHelper() {
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casListeners(AbstractFutureState<?> future, AbstractFuture.Listener expect, AbstractFuture.Listener update) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(listenersUpdater, future, expect, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casValue(AbstractFutureState<?> future, Object expect, Object update) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(valueUpdater, future, expect, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casWaiters(AbstractFutureState<?> future, Waiter expect, Waiter update) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(waitersUpdater, future, expect, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public AbstractFuture.Listener gasListeners(AbstractFutureState<?> future, AbstractFuture.Listener update) {
            return listenersUpdater.getAndSet(future, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public Waiter gasWaiters(AbstractFutureState<?> future, Waiter update) {
            return waitersUpdater.getAndSet(future, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putNext(Waiter waiter, Waiter newValue) {
            waiterNextUpdater.lazySet(waiter, newValue);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putThread(Waiter waiter, Thread newValue) {
            waiterThreadUpdater.lazySet(waiter, newValue);
        }

        public AtomicReferenceFieldUpdaterAtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedHelper extends AtomicHelper {
        public SynchronizedHelper() {
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casListeners(AbstractFutureState<?> future, AbstractFuture.Listener expect, AbstractFuture.Listener update) {
            synchronized (future) {
                try {
                    if (future.listeners != expect) {
                        return false;
                    }
                    future.listeners = update;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casValue(AbstractFutureState<?> future, Object expect, Object update) {
            synchronized (future) {
                try {
                    if (future.value != expect) {
                        return false;
                    }
                    future.value = update;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casWaiters(AbstractFutureState<?> future, Waiter expect, Waiter update) {
            synchronized (future) {
                try {
                    if (future.waiters != expect) {
                        return false;
                    }
                    future.waiters = update;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public AbstractFuture.Listener gasListeners(AbstractFutureState<?> future, AbstractFuture.Listener update) {
            AbstractFuture.Listener listener;
            synchronized (future) {
                try {
                    listener = future.listeners;
                    if (listener != update) {
                        future.listeners = update;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return listener;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public Waiter gasWaiters(AbstractFutureState<?> future, Waiter update) {
            Waiter waiter;
            synchronized (future) {
                try {
                    waiter = future.waiters;
                    if (waiter != update) {
                        future.waiters = update;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return waiter;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putNext(Waiter waiter, Waiter newValue) {
            waiter.next = newValue;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putThread(Waiter waiter, Thread newValue) {
            waiter.thread = newValue;
        }

        public SynchronizedHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnsafeAtomicHelper extends AtomicHelper {
        public static final long LISTENERS_OFFSET;
        public static final Unsafe UNSAFE;
        public static final long VALUE_OFFSET;
        public static final long WAITERS_OFFSET;
        public static final long WAITER_NEXT_OFFSET;
        public static final long WAITER_THREAD_OFFSET;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(new AbstractFutureState$UnsafeAtomicHelper$$ExternalSyntheticLambda0());
            }
            try {
                WAITERS_OFFSET = unsafe.objectFieldOffset(AbstractFutureState.class.getDeclaredField("waiters"));
                LISTENERS_OFFSET = unsafe.objectFieldOffset(AbstractFutureState.class.getDeclaredField("listeners"));
                VALUE_OFFSET = unsafe.objectFieldOffset(AbstractFutureState.class.getDeclaredField("value"));
                WAITER_THREAD_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("thread"));
                WAITER_NEXT_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("next"));
                UNSAFE = unsafe;
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException(e2);
            }
        }

        public UnsafeAtomicHelper() {
        }

        public static /* synthetic */ Unsafe lambda$static$0() throws Exception {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            throw new NoSuchFieldError("the Unsafe");
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casListeners(AbstractFutureState<?> future, AbstractFuture.Listener expect, AbstractFuture.Listener update) {
            return zzck$$ExternalSyntheticBackportWithForwarding0.m(UNSAFE, future, LISTENERS_OFFSET, expect, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casValue(AbstractFutureState<?> future, Object expect, Object update) {
            return zzck$$ExternalSyntheticBackportWithForwarding0.m(UNSAFE, future, VALUE_OFFSET, expect, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casWaiters(AbstractFutureState<?> future, Waiter expect, Waiter update) {
            return zzck$$ExternalSyntheticBackportWithForwarding0.m(UNSAFE, future, WAITERS_OFFSET, expect, update);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public AbstractFuture.Listener gasListeners(AbstractFutureState<?> future, AbstractFuture.Listener update) {
            AbstractFuture.Listener listener;
            do {
                listener = future.listeners;
                if (update == listener) {
                    break;
                }
            } while (!casListeners(future, listener, update));
            return listener;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public Waiter gasWaiters(AbstractFutureState<?> future, Waiter update) {
            Waiter waiter;
            do {
                waiter = future.waiters;
                if (update == waiter) {
                    break;
                }
            } while (!casWaiters(future, waiter, update));
            return waiter;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putNext(Waiter waiter, Waiter newValue) {
            UNSAFE.putObject(waiter, WAITER_NEXT_OFFSET, newValue);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putThread(Waiter waiter, Thread newValue) {
            UNSAFE.putObject(waiter, WAITER_THREAD_OFFSET, newValue);
        }

        public UnsafeAtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Waiter {
        public static final Waiter TOMBSTONE = new Waiter();
        public volatile Waiter next;
        public volatile Thread thread;

        public Waiter(boolean unused) {
        }

        public void setNext(Waiter next) {
            AbstractFutureState.putNext(this, next);
        }

        public void unpark() {
            Thread thread = this.thread;
            if (thread != null) {
                this.thread = null;
                LockSupport.unpark(thread);
            }
        }

        public Waiter() {
            AbstractFutureState.putThread(this, Thread.currentThread());
        }
    }

    static {
        boolean z;
        AtomicHelper synchronizedHelper;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        GENERATE_CANCELLATION_CAUSES = z;
        Throwable e = null;
        try {
            synchronizedHelper = new UnsafeAtomicHelper();
            e = null;
        } catch (Error | Exception e2) {
            e = e2;
            try {
                synchronizedHelper = new AtomicReferenceFieldUpdaterAtomicHelper();
            } catch (Error | Exception e3) {
                e = e3;
                synchronizedHelper = new SynchronizedHelper();
            }
        }
        ATOMIC_HELPER = synchronizedHelper;
        if (e != null) {
            LazyLogger lazyLogger = log;
            Logger logger = lazyLogger.get();
            Level level = Level.SEVERE;
            logger.log(level, "UnsafeAtomicHelper is broken!", e);
            lazyLogger.get().log(level, "AtomicReferenceFieldUpdaterAtomicHelper is broken!", e);
        }
    }

    public static boolean casValue(AbstractFutureState<?> future, Object expect, Object update) {
        return ATOMIC_HELPER.casValue(future, expect, update);
    }

    public static AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, AbstractFuture.Listener> listenersUpdaterFromWithinAbstractFutureState() {
        return AtomicReferenceFieldUpdater.newUpdater(AbstractFutureState.class, AbstractFuture.Listener.class, "listeners");
    }

    public static void putNext(Waiter waiter, Waiter newValue) {
        ATOMIC_HELPER.putNext(waiter, newValue);
    }

    public static void putThread(Waiter waiter, Thread newValue) {
        ATOMIC_HELPER.putThread(waiter, newValue);
    }

    public static AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, Object> valueUpdaterFromWithinAbstractFutureState() {
        return AtomicReferenceFieldUpdater.newUpdater(AbstractFutureState.class, Object.class, "value");
    }

    public static AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, Waiter> waitersUpdaterFromWithinAbstractFutureState() {
        return AtomicReferenceFieldUpdater.newUpdater(AbstractFutureState.class, Waiter.class, "waiters");
    }

    @ParametricNullness
    public final V blockingGet(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.value;
        if ((obj != null) && (!(obj instanceof AbstractFuture.DelegatingToFuture))) {
            return (V) AbstractFuture.getDoneValue(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            Waiter waiter = this.waiters;
            if (waiter != Waiter.TOMBSTONE) {
                Waiter waiter2 = new Waiter();
                do {
                    putNext(waiter2, waiter);
                    if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                        do {
                            OverflowAvoidingLockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.value;
                            if ((obj2 != null) && (!(obj2 instanceof AbstractFuture.DelegatingToFuture))) {
                                return (V) AbstractFuture.getDoneValue(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        removeWaiter(waiter2);
                    } else {
                        waiter = this.waiters;
                    }
                } while (waiter != Waiter.TOMBSTONE);
            }
            Object obj3 = this.value;
            Objects.requireNonNull(obj3);
            return (V) AbstractFuture.getDoneValue(obj3);
        }
        while (nanos > 0) {
            Object obj4 = this.value;
            if ((obj4 != null) && (!(obj4 instanceof AbstractFuture.DelegatingToFuture))) {
                return (V) AbstractFuture.getDoneValue(obj4);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("Waited ", j, StringUtils.SPACE);
        sbM.append(timeUnit.toString().toLowerCase(locale));
        String string3 = sbM.toString();
        if (nanos + 1000 < 0) {
            String strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(string3, " (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strM2 = strM + jConvert + StringUtils.SPACE + lowerCase;
                if (z) {
                    strM2 = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM2, ",");
                }
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM2, StringUtils.SPACE);
            }
            if (z) {
                strM = strM + nanos2 + " nanoseconds ";
            }
            string3 = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, "delay)");
        }
        if (isDone()) {
            throw new TimeoutException(AbstractResolvableFuture$$ExternalSyntheticOutline0.m(string3, " but future completed as timeout expired"));
        }
        throw new TimeoutException(AbstractResolvableFuture$$ExternalSyntheticOutline1.m(string3, " for ", string));
    }

    public final boolean casListeners(AbstractFuture.Listener expect, AbstractFuture.Listener update) {
        return ATOMIC_HELPER.casListeners(this, expect, update);
    }

    public final boolean casWaiters(Waiter expect, Waiter update) {
        return ATOMIC_HELPER.casWaiters(this, expect, update);
    }

    public final AbstractFuture.Listener gasListeners(AbstractFuture.Listener update) {
        return ATOMIC_HELPER.gasListeners(this, update);
    }

    public final Waiter gasWaiters(Waiter update) {
        return ATOMIC_HELPER.gasWaiters(this, update);
    }

    public final AbstractFuture.Listener listeners() {
        return this.listeners;
    }

    public final void releaseWaiters() {
        for (Waiter waiterGasWaiters = ATOMIC_HELPER.gasWaiters(this, Waiter.TOMBSTONE); waiterGasWaiters != null; waiterGasWaiters = waiterGasWaiters.next) {
            waiterGasWaiters.unpark();
        }
    }

    public final void removeWaiter(Waiter node) {
        node.thread = null;
        while (true) {
            Waiter waiter = this.waiters;
            if (waiter == Waiter.TOMBSTONE) {
                return;
            }
            Waiter waiter2 = null;
            while (waiter != null) {
                Waiter waiter3 = waiter.next;
                if (waiter.thread != null) {
                    waiter2 = waiter;
                } else if (waiter2 != null) {
                    waiter2.next = waiter3;
                    if (waiter2.thread == null) {
                        break;
                    }
                } else if (!ATOMIC_HELPER.casWaiters(this, waiter, waiter3)) {
                    break;
                }
                waiter = waiter3;
            }
            return;
        }
    }

    public final Object value() {
        return this.value;
    }

    @ParametricNullness
    public final V blockingGet() throws ExecutionException, InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) & (!(obj2 instanceof AbstractFuture.DelegatingToFuture))) {
                return (V) AbstractFuture.getDoneValue(obj2);
            }
            Waiter waiter = this.waiters;
            if (waiter != Waiter.TOMBSTONE) {
                Waiter waiter2 = new Waiter();
                do {
                    putNext(waiter2, waiter);
                    if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof AbstractFuture.DelegatingToFuture))));
                        return (V) AbstractFuture.getDoneValue(obj);
                    }
                    waiter = this.waiters;
                } while (waiter != Waiter.TOMBSTONE);
            }
            Object obj3 = this.value;
            Objects.requireNonNull(obj3);
            return (V) AbstractFuture.getDoneValue(obj3);
        }
        throw new InterruptedException();
    }
}
