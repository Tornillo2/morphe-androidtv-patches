package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.time.Duration;
import j$.util.Objects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class MoreExecutors {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class ListeningDecorator extends AbstractListeningExecutorService {
        public final ExecutorService delegate;

        public ListeningDecorator(ExecutorService delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.awaitTermination(timeout, unit);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable command) {
            this.delegate.execute(command);
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public final void shutdown() {
            this.delegate.shutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final List<Runnable> shutdownNow() {
            return this.delegate.shutdownNow();
        }

        public final String toString() {
            return super.toString() + "[" + this.delegate + "]";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static final class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
        public final ScheduledExecutorService delegate;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @GwtIncompatible
        public static final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture<Void> implements Runnable {
            public final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable delegate) {
                delegate.getClass();
                this.delegate = delegate;
            }

            @Override // com.google.common.util.concurrent.AbstractFuture
            public String pendingToString() {
                return "task=[" + this.delegate + "]";
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.delegate.run();
                } catch (Throwable th) {
                    setException(th);
                    throw th;
                }
            }
        }

        public ScheduledListeningDecorator(ScheduledExecutorService delegate) {
            super(delegate);
            this.delegate = delegate;
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            public final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> listenableDelegate, ScheduledFuture<?> scheduledDelegate) {
                super(listenableDelegate);
                this.scheduledDelegate = scheduledDelegate;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean zCancel = super.cancel(mayInterruptIfRunning);
                if (zCancel) {
                    this.scheduledDelegate.cancel(mayInterruptIfRunning);
                }
                return zCancel;
            }

            @Override // java.lang.Comparable
            public int compareTo(Delayed other) {
                return this.scheduledDelegate.compareTo(other);
            }

            @Override // java.util.concurrent.Delayed
            public long getDelay(TimeUnit unit) {
                return this.scheduledDelegate.getDelay(unit);
            }

            /* JADX INFO: renamed from: compareTo, reason: avoid collision after fix types in other method */
            public int compareTo2(Delayed other) {
                return this.scheduledDelegate.compareTo(other);
            }
        }

        @Override // com.google.common.util.concurrent.ListeningScheduledExecutorService, java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(command);
            return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleAtFixedRate(neverSuccessfulListenableFutureTask, initialDelay, period, unit));
        }

        @Override // com.google.common.util.concurrent.ListeningScheduledExecutorService, java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(command);
            return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleWithFixedDelay(neverSuccessfulListenableFutureTask, initialDelay, delay, unit));
        }

        @Override // com.google.common.util.concurrent.ListeningScheduledExecutorService, java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            TrustedListenableFutureTask trustedListenableFutureTaskCreate = TrustedListenableFutureTask.create(command, null);
            return new ListenableScheduledTask(trustedListenableFutureTaskCreate, this.delegate.schedule(trustedListenableFutureTaskCreate, delay, unit));
        }

        @Override // com.google.common.util.concurrent.ListeningScheduledExecutorService, java.util.concurrent.ScheduledExecutorService
        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            TrustedListenableFutureTask trustedListenableFutureTaskCreate = TrustedListenableFutureTask.create(callable);
            return new ListenableScheduledTask(trustedListenableFutureTaskCreate, this.delegate.schedule(trustedListenableFutureTaskCreate, delay, unit));
        }
    }

    public static /* synthetic */ void $r8$lambda$92ZkpsLseCTe3GclgVsNqEiiiZ4(Executor executor, AbstractFuture abstractFuture, Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (RejectedExecutionException e) {
            abstractFuture.setException(e);
        }
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static void addDelayedShutdownHook(ExecutorService service, Duration terminationTimeout) {
        addDelayedShutdownHook(service, Internal.toNanosSaturated(terminationTimeout), TimeUnit.NANOSECONDS);
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, Duration terminationTimeout) {
        return getExitingExecutorService(executor, Internal.toNanosSaturated(terminationTimeout), TimeUnit.NANOSECONDS);
    }

    @IgnoreJRERequirement
    @J2ktIncompatible
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, Duration terminationTimeout) {
        return getExitingScheduledExecutorService(executor, Internal.toNanosSaturated(terminationTimeout), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    @IgnoreJRERequirement
    @J2ktIncompatible
    @ParametricNullness
    public static <T> T invokeAnyImpl(ListeningExecutorService listeningExecutorService, Collection<? extends Callable<T>> collection, boolean z, Duration duration) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) invokeAnyImpl(listeningExecutorService, collection, z, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean isAppEngineWithApiClasses() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            Class.forName("com.google.appengine.api.utils.SystemProperty");
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", null).invoke(null, null) != null;
    }

    @GwtIncompatible
    public static ListeningExecutorService listeningDecorator(ExecutorService delegate) {
        return delegate instanceof ListeningExecutorService ? (ListeningExecutorService) delegate : delegate instanceof ScheduledExecutorService ? new ScheduledListeningDecorator((ScheduledExecutorService) delegate) : new ListeningDecorator(delegate);
    }

    @GwtIncompatible
    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService();
    }

    @GwtIncompatible
    public static Executor newSequentialExecutor(Executor delegate) {
        return new SequentialExecutor(delegate);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Thread newThread(String name, Runnable runnable) {
        name.getClass();
        runnable.getClass();
        Thread threadNewThread = platformThreadFactory().newThread(runnable);
        Objects.requireNonNull(threadNewThread);
        try {
            threadNewThread.setName(name);
        } catch (SecurityException unused) {
        }
        return threadNewThread;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ThreadFactory platformThreadFactory() throws Throwable {
        if (!isAppEngineWithApiClasses()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", null).invoke(null, null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
        } catch (InvocationTargetException e2) {
            throw e2.getCause();
        }
    }

    public static Executor rejectionPropagatingExecutor(final Executor delegate, final AbstractFuture<?> future) {
        delegate.getClass();
        future.getClass();
        return delegate == DirectExecutor.INSTANCE ? delegate : new Executor() { // from class: com.google.common.util.concurrent.MoreExecutors$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                MoreExecutors.$r8$lambda$92ZkpsLseCTe3GclgVsNqEiiiZ4(delegate, future, runnable);
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Executor renamingDecorator(Executor executor, Supplier<String> nameSupplier) {
        executor.getClass();
        nameSupplier.getClass();
        return new MoreExecutors$$ExternalSyntheticLambda1(executor, nameSupplier);
    }

    @GwtIncompatible
    @IgnoreJRERequirement
    @CanIgnoreReturnValue
    @J2ktIncompatible
    public static boolean shutdownAndAwaitTermination(ExecutorService service, Duration timeout) {
        return shutdownAndAwaitTermination(service, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService executorService, Callable<T> task, final BlockingQueue<Future<T>> queue) {
        final ListenableFuture<T> listenableFutureSubmit = executorService.submit((Callable) task);
        listenableFutureSubmit.addListener(new Runnable() { // from class: com.google.common.util.concurrent.MoreExecutors$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                queue.add(listenableFutureSubmit);
            }
        }, DirectExecutor.INSTANCE);
        return listenableFutureSubmit;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static void useDaemonThreadFactory(ThreadPoolExecutor executor) {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.daemon = Boolean.TRUE;
        ThreadFactory threadFactory = executor.getThreadFactory();
        threadFactory.getClass();
        threadFactoryBuilder.backingThreadFactory = threadFactory;
        executor.setThreadFactory(ThreadFactoryBuilder.doBuild(threadFactoryBuilder));
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(service, terminationTimeout, timeUnit);
    }

    @CanIgnoreReturnValue
    @J2ktIncompatible
    @GwtIncompatible
    public static boolean shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
        long nanos = unit.toNanos(timeout) / 2;
        service.shutdown();
        try {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            if (!service.awaitTermination(nanos, timeUnit)) {
                service.shutdownNow();
                service.awaitTermination(nanos, timeUnit);
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            service.shutdownNow();
        }
        return service.isTerminated();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    @VisibleForTesting
    @J2ktIncompatible
    public static class Application {
        public static /* synthetic */ void $r8$lambda$xIUkJD_qRRnFAYOx6E6hmxizG00(ExecutorService executorService, long j, TimeUnit timeUnit) {
            executorService.shutdown();
            try {
                executorService.awaitTermination(j, timeUnit);
            } catch (InterruptedException unused) {
            }
        }

        public final void addDelayedShutdownHook(final ExecutorService service, final long terminationTimeout, final TimeUnit timeUnit) {
            service.getClass();
            timeUnit.getClass();
            addShutdownHook(MoreExecutors.newThread("DelayedShutdownHook-for-" + service, new Runnable() { // from class: com.google.common.util.concurrent.MoreExecutors$Application$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MoreExecutors.Application.$r8$lambda$xIUkJD_qRRnFAYOx6E6hmxizG00(service, terminationTimeout, timeUnit);
                }
            }));
        }

        @VisibleForTesting
        public void addShutdownHook(Thread hook) {
            Runtime.getRuntime().addShutdownHook(hook);
        }

        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ExecutorService executorServiceUnconfigurableExecutorService = Executors.unconfigurableExecutorService(executor);
            addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return executorServiceUnconfigurableExecutorService;
        }

        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ScheduledExecutorService scheduledExecutorServiceUnconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(executor);
            addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return scheduledExecutorServiceUnconfigurableScheduledExecutorService;
        }

        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
            return getExitingExecutorService(executor, 120L, TimeUnit.SECONDS);
        }

        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
            return getExitingScheduledExecutorService(executor, 120L, TimeUnit.SECONDS);
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(executor, terminationTimeout, timeUnit);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(executor, terminationTimeout, timeUnit);
    }

    @GwtIncompatible
    @J2ktIncompatible
    @ParametricNullness
    public static <T> T invokeAnyImpl(ListeningExecutorService listeningExecutorService, Collection<? extends Callable<T>> collection, boolean z, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long jNanoTime;
        long jNanoTime2;
        listeningExecutorService.getClass();
        timeUnit.getClass();
        int size = collection.size();
        int i = 0;
        Preconditions.checkArgument(size > 0);
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(size);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        long nanos = timeUnit.toNanos(j);
        if (z) {
            try {
                jNanoTime = System.nanoTime();
            } catch (Throwable th) {
                int size2 = arrayListNewArrayListWithCapacity.size();
                while (i < size2) {
                    Object obj = arrayListNewArrayListWithCapacity.get(i);
                    i++;
                    ((Future) obj).cancel(true);
                }
                throw th;
            }
        } else {
            jNanoTime = 0;
        }
        Iterator<? extends Callable<T>> it = collection.iterator();
        arrayListNewArrayListWithCapacity.add(submitAndAddQueueListener(listeningExecutorService, it.next(), linkedBlockingQueue));
        int i2 = size - 1;
        ExecutionException executionException = null;
        int i3 = 1;
        while (true) {
            Future future = (Future) linkedBlockingQueue.poll();
            if (future != null) {
                jNanoTime2 = jNanoTime;
            } else {
                if (i2 > 0) {
                    i2--;
                    arrayListNewArrayListWithCapacity.add(submitAndAddQueueListener(listeningExecutorService, it.next(), linkedBlockingQueue));
                    i3++;
                } else {
                    if (i3 == 0) {
                        if (executionException == null) {
                            throw new ExecutionException((Throwable) null);
                        }
                        throw executionException;
                    }
                    if (z) {
                        future = (Future) linkedBlockingQueue.poll(nanos, TimeUnit.NANOSECONDS);
                        if (future != null) {
                            jNanoTime2 = System.nanoTime();
                            nanos -= jNanoTime2 - jNanoTime;
                        } else {
                            throw new TimeoutException();
                        }
                    } else {
                        future = (Future) linkedBlockingQueue.take();
                    }
                }
                jNanoTime2 = jNanoTime;
            }
            long j2 = nanos;
            int i4 = i2;
            if (future != null) {
                i3--;
                try {
                    T t = (T) future.get();
                    int size3 = arrayListNewArrayListWithCapacity.size();
                    while (i < size3) {
                        Object obj2 = arrayListNewArrayListWithCapacity.get(i);
                        i++;
                        ((Future) obj2).cancel(true);
                    }
                    return t;
                } catch (InterruptedException e) {
                    throw e;
                } catch (ExecutionException e2) {
                    executionException = e2;
                    i2 = i4;
                    nanos = j2;
                    jNanoTime = jNanoTime2;
                } catch (Exception e3) {
                    executionException = new ExecutionException(e3);
                    i2 = i4;
                    nanos = j2;
                    jNanoTime = jNanoTime2;
                }
            }
            i2 = i4;
            nanos = j2;
            jNanoTime = jNanoTime2;
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ExecutorService renamingDecorator(ExecutorService service, final Supplier<String> nameSupplier) {
        service.getClass();
        nameSupplier.getClass();
        return new WrappingExecutorService(service) { // from class: com.google.common.util.concurrent.MoreExecutors.1
            @Override // com.google.common.util.concurrent.WrappingExecutorService
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) nameSupplier);
            }

            @Override // com.google.common.util.concurrent.WrappingExecutorService
            public Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>) nameSupplier);
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
        return new Application().getExitingExecutorService(executor);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
        return new Application().getExitingScheduledExecutorService(executor);
    }

    @GwtIncompatible
    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService delegate) {
        if (delegate instanceof ListeningScheduledExecutorService) {
            return (ListeningScheduledExecutorService) delegate;
        }
        return new ScheduledListeningDecorator(delegate);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ScheduledExecutorService renamingDecorator(ScheduledExecutorService service, final Supplier<String> nameSupplier) {
        service.getClass();
        nameSupplier.getClass();
        return new WrappingScheduledExecutorService(service) { // from class: com.google.common.util.concurrent.MoreExecutors.2
            @Override // com.google.common.util.concurrent.WrappingExecutorService
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) nameSupplier);
            }

            @Override // com.google.common.util.concurrent.WrappingExecutorService
            public Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>) nameSupplier);
            }
        };
    }
}
