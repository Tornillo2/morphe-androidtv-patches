package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class GlideExecutor implements ExecutorService {
    public static final String DEFAULT_ANIMATION_EXECUTOR_NAME = "animation";
    public static final String DEFAULT_DISK_CACHE_EXECUTOR_NAME = "disk-cache";
    public static final int DEFAULT_DISK_CACHE_EXECUTOR_THREADS = 1;
    public static final String DEFAULT_SOURCE_EXECUTOR_NAME = "source";
    public static final String DEFAULT_SOURCE_UNLIMITED_EXECUTOR_NAME = "source-unlimited";
    public static final long KEEP_ALIVE_TIME_MS = TimeUnit.SECONDS.toMillis(10);
    public static final int MAXIMUM_AUTOMATIC_THREAD_COUNT = 4;
    public static final String TAG = "GlideExecutor";
    public static volatile int bestThreadCount;
    public final ExecutorService delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public static final long NO_THREAD_TIMEOUT = 0;
        public int corePoolSize;
        public int maximumPoolSize;
        public String name;
        public final boolean preventNetworkOperations;
        public long threadTimeoutMillis;

        @NonNull
        public UncaughtThrowableStrategy uncaughtThrowableStrategy = UncaughtThrowableStrategy.DEFAULT;

        public Builder(boolean z) {
            this.preventNetworkOperations = z;
        }

        public GlideExecutor build() {
            if (TextUtils.isEmpty(this.name)) {
                throw new IllegalArgumentException("Name must be non-null and non-empty, but given: " + this.name);
            }
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize, this.threadTimeoutMillis, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(this.name, this.uncaughtThrowableStrategy, this.preventNetworkOperations));
            if (this.threadTimeoutMillis != 0) {
                threadPoolExecutor.allowCoreThreadTimeOut(true);
            }
            return new GlideExecutor(threadPoolExecutor);
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setThreadCount(@IntRange(from = 1) int i) {
            this.corePoolSize = i;
            this.maximumPoolSize = i;
            return this;
        }

        public Builder setThreadTimeoutMillis(long j) {
            this.threadTimeoutMillis = j;
            return this;
        }

        public Builder setUncaughtThrowableStrategy(@NonNull UncaughtThrowableStrategy uncaughtThrowableStrategy) {
            this.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultThreadFactory implements ThreadFactory {
        public static final int DEFAULT_PRIORITY = 9;
        public final String name;
        public final boolean preventNetworkOperations;
        public int threadNum;
        public final UncaughtThrowableStrategy uncaughtThrowableStrategy;

        public DefaultThreadFactory(String str, UncaughtThrowableStrategy uncaughtThrowableStrategy, boolean z) {
            this.name = str;
            this.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
            this.preventNetworkOperations = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(@NonNull Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-" + this.name + "-thread-" + this.threadNum) { // from class: com.bumptech.glide.load.engine.executor.GlideExecutor.DefaultThreadFactory.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(9);
                    if (DefaultThreadFactory.this.preventNetworkOperations) {
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                    }
                    try {
                        super.run();
                    } catch (Throwable th) {
                        DefaultThreadFactory.this.uncaughtThrowableStrategy.handle(th);
                    }
                }
            };
            this.threadNum = this.threadNum + 1;
            return thread;
        }
    }

    @VisibleForTesting
    public GlideExecutor(ExecutorService executorService) {
        this.delegate = executorService;
    }

    public static int calculateBestThreadCount() {
        if (bestThreadCount == 0) {
            bestThreadCount = Math.min(4, RuntimeCompat.availableProcessors());
        }
        return bestThreadCount;
    }

    public static Builder newAnimationBuilder() {
        int i = calculateBestThreadCount() >= 4 ? 2 : 1;
        Builder builder = new Builder(true);
        builder.corePoolSize = i;
        builder.maximumPoolSize = i;
        builder.name = DEFAULT_ANIMATION_EXECUTOR_NAME;
        return builder;
    }

    public static GlideExecutor newAnimationExecutor() {
        return newAnimationBuilder().build();
    }

    public static Builder newDiskCacheBuilder() {
        Builder builder = new Builder(true);
        builder.corePoolSize = 1;
        builder.maximumPoolSize = 1;
        builder.name = DEFAULT_DISK_CACHE_EXECUTOR_NAME;
        return builder;
    }

    public static GlideExecutor newDiskCacheExecutor() {
        return newDiskCacheBuilder().build();
    }

    public static Builder newSourceBuilder() {
        Builder builder = new Builder(false);
        int iCalculateBestThreadCount = calculateBestThreadCount();
        builder.corePoolSize = iCalculateBestThreadCount;
        builder.maximumPoolSize = iCalculateBestThreadCount;
        builder.name = DEFAULT_SOURCE_EXECUTOR_NAME;
        return builder;
    }

    public static GlideExecutor newSourceExecutor() {
        return newSourceBuilder().build();
    }

    public static GlideExecutor newUnlimitedSourceExecutor() {
        return new GlideExecutor(new ThreadPoolExecutor(0, Integer.MAX_VALUE, KEEP_ALIVE_TIME_MS, TimeUnit.MILLISECONDS, new SynchronousQueue(), new DefaultThreadFactory(DEFAULT_SOURCE_UNLIMITED_EXECUTOR_NAME, UncaughtThrowableStrategy.DEFAULT, false)));
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        this.delegate.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.delegate.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.delegate.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.delegate.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public Future<?> submit(@NonNull Runnable runnable) {
        return this.delegate.submit(runnable);
    }

    public String toString() {
        return this.delegate.toString();
    }

    @Deprecated
    public static GlideExecutor newAnimationExecutor(int i, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        Builder builderNewAnimationBuilder = newAnimationBuilder();
        builderNewAnimationBuilder.corePoolSize = i;
        builderNewAnimationBuilder.maximumPoolSize = i;
        builderNewAnimationBuilder.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
        return builderNewAnimationBuilder.build();
    }

    @Deprecated
    public static GlideExecutor newDiskCacheExecutor(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        Builder builderNewDiskCacheBuilder = newDiskCacheBuilder();
        builderNewDiskCacheBuilder.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
        return builderNewDiskCacheBuilder.build();
    }

    @Deprecated
    public static GlideExecutor newSourceExecutor(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        Builder builderNewSourceBuilder = newSourceBuilder();
        builderNewSourceBuilder.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
        return builderNewSourceBuilder.build();
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection, long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.delegate.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public <T> Future<T> submit(@NonNull Runnable runnable, T t) {
        return this.delegate.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(@NonNull Callable<T> callable) {
        return this.delegate.submit(callable);
    }

    @Deprecated
    public static GlideExecutor newDiskCacheExecutor(int i, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        Builder builderNewDiskCacheBuilder = newDiskCacheBuilder();
        builderNewDiskCacheBuilder.corePoolSize = i;
        builderNewDiskCacheBuilder.maximumPoolSize = i;
        builderNewDiskCacheBuilder.name = str;
        builderNewDiskCacheBuilder.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
        return builderNewDiskCacheBuilder.build();
    }

    @Deprecated
    public static GlideExecutor newSourceExecutor(int i, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        Builder builderNewSourceBuilder = newSourceBuilder();
        builderNewSourceBuilder.corePoolSize = i;
        builderNewSourceBuilder.maximumPoolSize = i;
        builderNewSourceBuilder.name = str;
        builderNewSourceBuilder.uncaughtThrowableStrategy = uncaughtThrowableStrategy;
        return builderNewSourceBuilder.build();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface UncaughtThrowableStrategy {
        public static final UncaughtThrowableStrategy DEFAULT;
        public static final UncaughtThrowableStrategy IGNORE = new AnonymousClass1();
        public static final UncaughtThrowableStrategy LOG;
        public static final UncaughtThrowableStrategy THROW;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.executor.GlideExecutor$UncaughtThrowableStrategy$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass2 implements UncaughtThrowableStrategy {
            @Override // com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
            public void handle(Throwable th) {
                if (th == null || !Log.isLoggable(GlideExecutor.TAG, 6)) {
                    return;
                }
                Log.e(GlideExecutor.TAG, "Request threw uncaught throwable", th);
            }
        }

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.executor.GlideExecutor$UncaughtThrowableStrategy$3, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass3 implements UncaughtThrowableStrategy {
            @Override // com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
            public void handle(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        }

        static {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            LOG = anonymousClass2;
            THROW = new AnonymousClass3();
            DEFAULT = anonymousClass2;
        }

        void handle(Throwable th);

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.executor.GlideExecutor$UncaughtThrowableStrategy$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 implements UncaughtThrowableStrategy {
            @Override // com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
            public void handle(Throwable th) {
            }
        }
    }
}
