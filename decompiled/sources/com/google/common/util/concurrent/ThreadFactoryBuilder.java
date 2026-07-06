package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.lang.Thread;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class ThreadFactoryBuilder {
    public String nameFormat = null;
    public Boolean daemon = null;
    public Integer priority = null;
    public Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;
    public ThreadFactory backingThreadFactory = null;

    public static ThreadFactory doBuild(ThreadFactoryBuilder builder) {
        final String str = builder.nameFormat;
        final Boolean bool = builder.daemon;
        final Integer num = builder.priority;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
        ThreadFactory threadFactoryDefaultThreadFactory = builder.backingThreadFactory;
        if (threadFactoryDefaultThreadFactory == null) {
            threadFactoryDefaultThreadFactory = Executors.defaultThreadFactory();
        }
        final ThreadFactory threadFactory = threadFactoryDefaultThreadFactory;
        final AtomicLong atomicLong = str != null ? new AtomicLong(0L) : null;
        return new ThreadFactory() { // from class: com.google.common.util.concurrent.ThreadFactoryBuilder.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread threadNewThread = threadFactory.newThread(runnable);
                Objects.requireNonNull(threadNewThread);
                String str2 = str;
                if (str2 != null) {
                    AtomicLong atomicLong2 = atomicLong;
                    Objects.requireNonNull(atomicLong2);
                    threadNewThread.setName(String.format(Locale.ROOT, str2, Long.valueOf(atomicLong2.getAndIncrement())));
                }
                Boolean bool2 = bool;
                if (bool2 != null) {
                    threadNewThread.setDaemon(bool2.booleanValue());
                }
                Integer num2 = num;
                if (num2 != null) {
                    threadNewThread.setPriority(num2.intValue());
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = uncaughtExceptionHandler;
                if (uncaughtExceptionHandler2 != null) {
                    threadNewThread.setUncaughtExceptionHandler(uncaughtExceptionHandler2);
                }
                return threadNewThread;
            }
        };
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }

    public ThreadFactory build() {
        return doBuild(this);
    }

    @CanIgnoreReturnValue
    public ThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = Boolean.valueOf(daemon);
        return this;
    }

    @CanIgnoreReturnValue
    public ThreadFactoryBuilder setNameFormat(String nameFormat) {
        format(nameFormat, 0);
        this.nameFormat = nameFormat;
        return this;
    }

    @CanIgnoreReturnValue
    public ThreadFactoryBuilder setPriority(int priority) {
        Preconditions.checkArgument(priority >= 1, "Thread priority (%s) must be >= %s", priority, 1);
        Preconditions.checkArgument(priority <= 10, "Thread priority (%s) must be <= %s", priority, 10);
        this.priority = Integer.valueOf(priority);
        return this;
    }

    @CanIgnoreReturnValue
    public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
        backingThreadFactory.getClass();
        this.backingThreadFactory = backingThreadFactory;
        return this;
    }

    @CanIgnoreReturnValue
    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        uncaughtExceptionHandler.getClass();
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        return this;
    }
}
