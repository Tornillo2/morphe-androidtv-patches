package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Verify;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Uninterruptibles {
    @J2ktIncompatible
    @GwtIncompatible
    public static void awaitTerminationUninterruptibly(ExecutorService executor) {
        Verify.verify(awaitTerminationUninterruptibly(executor, Long.MAX_VALUE, TimeUnit.NANOSECONDS));
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static void awaitUninterruptibly(CountDownLatch latch) {
        boolean z = false;
        while (true) {
            try {
                latch.await();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
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
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static void joinUninterruptibly(Thread toJoin) {
        boolean z = false;
        while (true) {
            try {
                toJoin.join();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> void putUninterruptibly(BlockingQueue<E> queue, E element) {
        boolean z = false;
        while (true) {
            try {
                queue.put(element);
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static void sleepUninterruptibly(Duration sleepFor) {
        sleepUninterruptibly(Internal.toNanosSaturated(sleepFor), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> E takeUninterruptibly(BlockingQueue<E> queue) {
        E eTake;
        boolean z = false;
        while (true) {
            try {
                eTake = queue.take();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return eTake;
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, Duration timeout) {
        return tryAcquireUninterruptibly(semaphore, 1, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean tryLockUninterruptibly(Lock lock, Duration timeout) {
        return tryLockUninterruptibly(lock, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean awaitTerminationUninterruptibly(ExecutorService executor, Duration timeout) {
        return awaitTerminationUninterruptibly(executor, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static void sleepUninterruptibly(long sleepFor, TimeUnit unit) {
        boolean z = false;
        try {
            long nanos = unit.toNanos(sleepFor);
            long jNanoTime = System.nanoTime() + nanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.sleep(nanos);
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = jNanoTime - System.nanoTime();
                }
            }
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean tryLockUninterruptibly(Lock lock, long timeout, TimeUnit unit) {
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return lock.tryLock(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean awaitTerminationUninterruptibly(ExecutorService executor, long timeout, TimeUnit unit) {
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return executor.awaitTermination(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, long timeout, TimeUnit unit) {
        return tryAcquireUninterruptibly(semaphore, 1, timeout, unit);
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean awaitUninterruptibly(CountDownLatch latch, Duration timeout) {
        return awaitUninterruptibly(latch, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    @IgnoreJRERequirement
    @CanIgnoreReturnValue
    @J2ktIncompatible
    @ParametricNullness
    public static <V> V getUninterruptibly(Future<V> future, Duration duration) throws ExecutionException, TimeoutException {
        return (V) getUninterruptibly(future, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static void joinUninterruptibly(Thread toJoin, Duration timeout) {
        joinUninterruptibly(toJoin, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int permits, Duration timeout) {
        return tryAcquireUninterruptibly(semaphore, permits, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean awaitUninterruptibly(CountDownLatch latch, long timeout, TimeUnit unit) {
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return latch.await(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    @ParametricNullness
    public static <V> V getUninterruptibly(Future<V> future, long timeout, TimeUnit unit) throws ExecutionException, TimeoutException {
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return future.get(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static void joinUninterruptibly(Thread toJoin, long timeout, TimeUnit unit) {
        toJoin.getClass();
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            long jNanoTime = System.nanoTime() + nanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.timedJoin(toJoin, nanos);
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = jNanoTime - System.nanoTime();
                }
            }
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int permits, long timeout, TimeUnit unit) {
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return semaphore.tryAcquire(permits, nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean awaitUninterruptibly(Condition condition, Duration timeout) {
        return awaitUninterruptibly(condition, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean awaitUninterruptibly(Condition condition, long timeout, TimeUnit unit) {
        boolean z = false;
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return condition.await(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
