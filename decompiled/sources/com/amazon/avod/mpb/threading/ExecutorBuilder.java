package com.amazon.avod.mpb.threading;

import com.amazon.avod.mpb.threading.internal.NamedThreadFactory;
import com.google.common.base.Preconditions;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@NotThreadSafe
public final class ExecutorBuilder {
    public boolean allowCoreThreadExpiry;
    public long coreThreadExpiryTime;

    @NotNull
    public TimeUnit coreThreadExpiryTimeUnit;

    @NotNull
    public final String name;

    @NotNull
    public final RejectedExecutionHandler rejectedExecutionHandler;
    public int threadPoolSize;

    public ExecutorBuilder(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.threadPoolSize = 1;
        this.coreThreadExpiryTimeUnit = TimeUnit.SECONDS;
        this.rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
    }

    @NotNull
    public final ExecutorBuilder allowCoreThreadExpiry(@Nonnegative long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        Preconditions.checkArgument(j >= 0, "time < 0 (%s)", j);
        this.allowCoreThreadExpiry = true;
        this.coreThreadExpiryTime = j;
        this.coreThreadExpiryTimeUnit = timeUnit;
        return this;
    }

    @NotNull
    public final ThreadPoolExecutor build() {
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory(this.name);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        int i = this.threadPoolSize;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, linkedBlockingQueue, namedThreadFactory, this.rejectedExecutionHandler);
        if (this.allowCoreThreadExpiry) {
            threadPoolExecutor.setKeepAliveTime(this.coreThreadExpiryTime, this.coreThreadExpiryTimeUnit);
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        return threadPoolExecutor;
    }

    @NotNull
    public final ExecutorBuilder withFixedThreadPoolSize(@Nonnegative int i) {
        Preconditions.checkArgument(i > 0, "threadPoolSize <= 0 (%s)", i);
        this.threadPoolSize = i;
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExecutorBuilder(@NotNull Class<?> cls) {
        this(cls.getSimpleName());
        Intrinsics.checkNotNullParameter(cls, "cls");
    }
}
