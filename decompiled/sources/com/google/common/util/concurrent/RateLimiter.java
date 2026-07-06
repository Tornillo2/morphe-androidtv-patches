package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
@J2ktIncompatible
@GwtIncompatible
public abstract class RateLimiter {
    public volatile Object mutexDoNotUseDirectly;
    public final SleepingStopwatch stopwatch;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SleepingStopwatch {

        /* JADX INFO: renamed from: com.google.common.util.concurrent.RateLimiter$SleepingStopwatch$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends SleepingStopwatch {
            public final Stopwatch stopwatch = Stopwatch.createStarted();

            @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
            public long readMicros() {
                return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
            }

            @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
            public void sleepMicrosUninterruptibly(long micros) {
                if (micros > 0) {
                    Uninterruptibles.sleepUninterruptibly(micros, TimeUnit.MICROSECONDS);
                }
            }
        }

        public static SleepingStopwatch createFromSystemTimer() {
            return new AnonymousClass1();
        }

        public abstract long readMicros();

        public abstract void sleepMicrosUninterruptibly(long micros);
    }

    public RateLimiter(SleepingStopwatch stopwatch) {
        stopwatch.getClass();
        this.stopwatch = stopwatch;
    }

    public static void checkPermits(int permits) {
        Preconditions.checkArgument(permits > 0, "Requested permits (%s) must be positive", permits);
    }

    @VisibleForTesting
    public static RateLimiter create(double permitsPerSecond, SleepingStopwatch stopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(stopwatch, 1.0d);
        smoothBursty.setRate(permitsPerSecond);
        return smoothBursty;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    public final boolean canAcquire(long nowMicros, long timeoutMicros) {
        return queryEarliestAvailable(nowMicros) - timeoutMicros <= nowMicros;
    }

    public abstract double doGetRate();

    public abstract void doSetRate(double permitsPerSecond, long nowMicros);

    public final double getRate() {
        double dDoGetRate;
        synchronized (mutex()) {
            dDoGetRate = doGetRate();
        }
        return dDoGetRate;
    }

    public final Object mutex() {
        Object obj;
        Object obj2 = this.mutexDoNotUseDirectly;
        if (obj2 != null) {
            return obj2;
        }
        synchronized (this) {
            try {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public abstract long queryEarliestAvailable(long nowMicros);

    public final long reserve(int permits) {
        long jReserveAndGetWaitLength;
        checkPermits(permits);
        synchronized (mutex()) {
            jReserveAndGetWaitLength = reserveAndGetWaitLength(permits, this.stopwatch.readMicros());
        }
        return jReserveAndGetWaitLength;
    }

    public final long reserveAndGetWaitLength(int permits, long nowMicros) {
        return Math.max(reserveEarliestAvailable(permits, nowMicros) - nowMicros, 0L);
    }

    public abstract long reserveEarliestAvailable(int permits, long nowMicros);

    public final void setRate(double permitsPerSecond) {
        Preconditions.checkArgument(permitsPerSecond > 0.0d, "rate must be positive");
        synchronized (mutex()) {
            doSetRate(permitsPerSecond, this.stopwatch.readMicros());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    @IgnoreJRERequirement
    public boolean tryAcquire(Duration timeout) {
        return tryAcquire(1, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    public double acquire(int permits) {
        long jReserve = reserve(permits);
        this.stopwatch.sleepMicrosUninterruptibly(jReserve);
        return (jReserve * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) {
        return tryAcquire(1, timeout, unit);
    }

    @IgnoreJRERequirement
    public static RateLimiter create(double permitsPerSecond, Duration warmupPeriod) {
        return create(permitsPerSecond, Internal.toNanosSaturated(warmupPeriod), TimeUnit.NANOSECONDS);
    }

    public boolean tryAcquire(int permits) {
        return tryAcquire(permits, 0L, TimeUnit.MICROSECONDS);
    }

    public static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit) {
        Preconditions.checkArgument(warmupPeriod >= 0, "warmupPeriod must not be negative: %s", warmupPeriod);
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(new SleepingStopwatch.AnonymousClass1(), warmupPeriod, unit, 3.0d);
        smoothWarmingUp.setRate(permitsPerSecond);
        return smoothWarmingUp;
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    @IgnoreJRERequirement
    public boolean tryAcquire(int permits, Duration timeout) {
        return tryAcquire(permits, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) {
        long jMax = Math.max(unit.toMicros(timeout), 0L);
        checkPermits(permits);
        synchronized (mutex()) {
            try {
                long micros = this.stopwatch.readMicros();
                if (!canAcquire(micros, jMax)) {
                    return false;
                }
                this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength(permits, micros));
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit, double coldFactor, SleepingStopwatch stopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(stopwatch, warmupPeriod, unit, coldFactor);
        smoothWarmingUp.setRate(permitsPerSecond);
        return smoothWarmingUp;
    }

    public static RateLimiter create(double permitsPerSecond) {
        return create(permitsPerSecond, new SleepingStopwatch.AnonymousClass1());
    }
}
