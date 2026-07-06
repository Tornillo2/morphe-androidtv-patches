package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import j$.time.Duration;
import j$.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class AbstractScheduledService implements Service {
    public static final LazyLogger logger = new LazyLogger(AbstractScheduledService.class);
    public final AbstractService delegate = new ServiceDelegate();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Cancellable {
        void cancel(boolean mayInterruptIfRunning);

        boolean isCancelled();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FutureAsCancellable implements Cancellable {
        public final Future<?> delegate;

        public FutureAsCancellable(Future<?> delegate) {
            this.delegate = delegate;
        }

        @Override // com.google.common.util.concurrent.AbstractScheduledService.Cancellable
        public void cancel(boolean mayInterruptIfRunning) {
            this.delegate.cancel(mayInterruptIfRunning);
        }

        @Override // com.google.common.util.concurrent.AbstractScheduledService.Cancellable
        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Scheduler {
        public Scheduler() {
        }

        @IgnoreJRERequirement
        public static Scheduler newFixedDelaySchedule(Duration initialDelay, Duration delay) {
            return newFixedDelaySchedule(Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
        }

        @IgnoreJRERequirement
        public static Scheduler newFixedRateSchedule(Duration initialDelay, Duration period) {
            return newFixedRateSchedule(Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(period), TimeUnit.NANOSECONDS);
        }

        public abstract Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable);

        public Scheduler(AnonymousClass1 anonymousClass1) {
        }

        public static Scheduler newFixedDelaySchedule(final long initialDelay, final long delay, final TimeUnit unit) {
            unit.getClass();
            Preconditions.checkArgument(delay > 0, "delay must be > 0, found %s", delay);
            return new Scheduler() { // from class: com.google.common.util.concurrent.AbstractScheduledService.Scheduler.1
                @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
                public Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return new FutureAsCancellable(executor.scheduleWithFixedDelay(task, initialDelay, delay, unit));
                }
            };
        }

        public static Scheduler newFixedRateSchedule(final long initialDelay, final long period, final TimeUnit unit) {
            unit.getClass();
            Preconditions.checkArgument(period > 0, "period must be > 0, found %s", period);
            return new Scheduler() { // from class: com.google.common.util.concurrent.AbstractScheduledService.Scheduler.2
                @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
                public Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return new FutureAsCancellable(executor.scheduleAtFixedRate(task, initialDelay, period, unit));
                }
            };
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ServiceDelegate extends AbstractService {
        public volatile ScheduledExecutorService executorService;
        public final ReentrantLock lock;
        public volatile Cancellable runningTask;
        public final Runnable task;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class Task implements Runnable {
            public Task() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ServiceDelegate.this.lock.lock();
                try {
                    Cancellable cancellable = ServiceDelegate.this.runningTask;
                    Objects.requireNonNull(cancellable);
                    if (!cancellable.isCancelled()) {
                        AbstractScheduledService.this.runOneIteration();
                    }
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }

        public static String $r8$lambda$555jsfQwj_vL4u3wQjBDUPUMsR8(ServiceDelegate serviceDelegate) {
            return AbstractScheduledService.this.serviceName() + StringUtils.SPACE + serviceDelegate.snapshot.externalState();
        }

        /* JADX INFO: renamed from: $r8$lambda$AvPpItR_Y1-eZldiG2_mSZpqIgQ, reason: not valid java name */
        public static /* synthetic */ void m558$r8$lambda$AvPpItR_Y1eZldiG2_mSZpqIgQ(ServiceDelegate serviceDelegate) {
            serviceDelegate.lock.lock();
            try {
                AbstractScheduledService.this.getClass();
                Objects.requireNonNull(serviceDelegate.executorService);
                serviceDelegate.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, serviceDelegate.executorService, serviceDelegate.task);
                serviceDelegate.notifyStarted();
            } finally {
                try {
                } finally {
                }
            }
        }

        public static void $r8$lambda$PoCNFKwIH6eWb4Dkv2CvCq6hMLs(ServiceDelegate serviceDelegate) throws Exception {
            serviceDelegate.getClass();
            try {
                serviceDelegate.lock.lock();
                try {
                    if (serviceDelegate.snapshot.externalState() != Service.State.STOPPING) {
                        return;
                    }
                    AbstractScheduledService.this.getClass();
                    serviceDelegate.lock.unlock();
                    serviceDelegate.notifyStopped();
                } finally {
                    serviceDelegate.lock.unlock();
                }
            } catch (Throwable th) {
                Platform.restoreInterruptIfIsInterruptedException(th);
                serviceDelegate.notifyFailed(th);
            }
        }

        public ServiceDelegate() {
            this.lock = new ReentrantLock();
            this.task = new Task();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            this.executorService = MoreExecutors.renamingDecorator(AbstractScheduledService.this.executor(), (Supplier<String>) new Supplier() { // from class: com.google.common.util.concurrent.AbstractScheduledService$ServiceDelegate$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return AbstractScheduledService.ServiceDelegate.$r8$lambda$555jsfQwj_vL4u3wQjBDUPUMsR8(this.f$0);
                }
            });
            this.executorService.execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractScheduledService$ServiceDelegate$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractScheduledService.ServiceDelegate.m558$r8$lambda$AvPpItR_Y1eZldiG2_mSZpqIgQ(this.f$0);
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            Objects.requireNonNull(this.runningTask);
            Objects.requireNonNull(this.executorService);
            this.runningTask.cancel(false);
            this.executorService.execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractScheduledService$ServiceDelegate$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    AbstractScheduledService.ServiceDelegate.$r8$lambda$PoCNFKwIH6eWb4Dkv2CvCq6hMLs(this.f$0);
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractScheduledService.this.toString();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public ScheduledExecutorService executor() {
        final ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.google.common.util.concurrent.AbstractScheduledService.1ThreadFactoryImpl
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
            }
        });
        addListener(new Service.Listener(this) { // from class: com.google.common.util.concurrent.AbstractScheduledService.1
            public final /* synthetic */ AbstractScheduledService this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void failed(Service.State from, Throwable failure) {
                scheduledExecutorServiceNewSingleThreadScheduledExecutor.shutdown();
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void terminated(Service.State from) {
                scheduledExecutorServiceNewSingleThreadScheduledExecutor.shutdown();
            }
        }, DirectExecutor.INSTANCE);
        return scheduledExecutorServiceNewSingleThreadScheduledExecutor;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.snapshot.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public abstract void runOneIteration() throws Exception;

    public abstract Scheduler scheduler();

    public String serviceName() {
        return getClass().getSimpleName();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() throws Exception {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.snapshot.externalState();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() throws Exception {
        this.delegate.stopAsync();
        return this;
    }

    public String toString() {
        return serviceName() + " [" + state() + "]";
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class CustomScheduler extends Scheduler {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class ReschedulableCallable implements Callable<Void> {

            @GuardedBy("lock")
            public SupplantableFuture cancellationDelegate;
            public final ScheduledExecutorService executor;
            public final ReentrantLock lock = new ReentrantLock();
            public final AbstractService service;
            public final Runnable wrappedRunnable;

            public ReschedulableCallable(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
                this.wrappedRunnable = runnable;
                this.executor = executor;
                this.service = service;
            }

            @Override // java.util.concurrent.Callable
            public /* bridge */ /* synthetic */ Void call() throws Exception {
                call2();
                return null;
            }

            @GuardedBy("lock")
            public final Cancellable initializeOrUpdateCancellationDelegate(Schedule schedule) {
                SupplantableFuture supplantableFuture = this.cancellationDelegate;
                if (supplantableFuture == null) {
                    SupplantableFuture supplantableFuture2 = new SupplantableFuture(this.lock, submitToExecutor(schedule));
                    this.cancellationDelegate = supplantableFuture2;
                    return supplantableFuture2;
                }
                if (!supplantableFuture.currentFuture.isCancelled()) {
                    this.cancellationDelegate.currentFuture = submitToExecutor(schedule);
                }
                return this.cancellationDelegate;
            }

            @CanIgnoreReturnValue
            public Cancellable reschedule() throws Exception {
                Cancellable futureAsCancellable;
                try {
                    Schedule nextSchedule = CustomScheduler.this.getNextSchedule();
                    this.lock.lock();
                    try {
                        futureAsCancellable = initializeOrUpdateCancellationDelegate(nextSchedule);
                        this.lock.unlock();
                        th = null;
                    } catch (Throwable th) {
                        th = th;
                        try {
                            futureAsCancellable = new FutureAsCancellable(Futures.immediateCancelledFuture());
                        } finally {
                            this.lock.unlock();
                        }
                    }
                    if (th != null) {
                        this.service.notifyFailed(th);
                    }
                    return futureAsCancellable;
                } catch (Throwable th2) {
                    Platform.restoreInterruptIfIsInterruptedException(th2);
                    this.service.notifyFailed(th2);
                    return new FutureAsCancellable(Futures.immediateCancelledFuture());
                }
            }

            public final ScheduledFuture<Void> submitToExecutor(Schedule schedule) {
                return this.executor.schedule(this, schedule.delay, schedule.unit);
            }

            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
            public Void call2() throws Exception {
                this.wrappedRunnable.run();
                reschedule();
                return null;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class SupplantableFuture implements Cancellable {

            @GuardedBy("lock")
            public Future<Void> currentFuture;
            public final ReentrantLock lock;

            public SupplantableFuture(ReentrantLock lock, Future<Void> currentFuture) {
                this.lock = lock;
                this.currentFuture = currentFuture;
            }

            @Override // com.google.common.util.concurrent.AbstractScheduledService.Cancellable
            public void cancel(boolean mayInterruptIfRunning) {
                this.lock.lock();
                try {
                    this.currentFuture.cancel(mayInterruptIfRunning);
                } finally {
                    this.lock.unlock();
                }
            }

            @Override // com.google.common.util.concurrent.AbstractScheduledService.Cancellable
            public boolean isCancelled() {
                this.lock.lock();
                try {
                    return this.currentFuture.isCancelled();
                } finally {
                    this.lock.unlock();
                }
            }
        }

        public abstract Schedule getNextSchedule() throws Exception;

        @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
        public final Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
            return new ReschedulableCallable(service, executor, runnable).reschedule();
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Schedule {
            public final long delay;
            public final TimeUnit unit;

            public Schedule(long delay, TimeUnit unit) {
                this.delay = delay;
                unit.getClass();
                this.unit = unit;
            }

            @IgnoreJRERequirement
            public Schedule(Duration delay) {
                this(Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
            }
        }
    }

    public void shutDown() throws Exception {
    }

    public void startUp() throws Exception {
    }
}
