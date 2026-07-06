package org.apache.commons.lang3.concurrent;

import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class BasicThreadFactory implements ThreadFactory {
    public final Boolean daemon;
    public final String namingPattern;
    public final Integer priority;
    public final AtomicLong threadCounter;
    public final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    public final ThreadFactory wrappedFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder implements org.apache.commons.lang3.builder.Builder<BasicThreadFactory> {
        public Boolean daemon;
        public Thread.UncaughtExceptionHandler exceptionHandler;
        public String namingPattern;
        public Integer priority;
        public ThreadFactory wrappedFactory;

        public Builder daemon(boolean z) {
            this.daemon = Boolean.valueOf(z);
            return this;
        }

        public Builder namingPattern(String str) {
            Validate.notNull(str, "Naming pattern must not be null!", new Object[0]);
            this.namingPattern = str;
            return this;
        }

        public Builder priority(int i) {
            this.priority = Integer.valueOf(i);
            return this;
        }

        public void reset() {
            this.wrappedFactory = null;
            this.exceptionHandler = null;
            this.namingPattern = null;
            this.priority = null;
            this.daemon = null;
        }

        public Builder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            Validate.notNull(uncaughtExceptionHandler, "Uncaught exception handler must not be null!", new Object[0]);
            this.exceptionHandler = uncaughtExceptionHandler;
            return this;
        }

        public Builder wrappedFactory(ThreadFactory threadFactory) {
            Validate.notNull(threadFactory, "Wrapped ThreadFactory must not be null!", new Object[0]);
            this.wrappedFactory = threadFactory;
            return this;
        }

        @Override // org.apache.commons.lang3.builder.Builder
        public BasicThreadFactory build() {
            BasicThreadFactory basicThreadFactory = new BasicThreadFactory(this);
            reset();
            return basicThreadFactory;
        }
    }

    public final Boolean getDaemonFlag() {
        return this.daemon;
    }

    public final String getNamingPattern() {
        return this.namingPattern;
    }

    public final Integer getPriority() {
        return this.priority;
    }

    public long getThreadCount() {
        return this.threadCounter.get();
    }

    public final Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    public final ThreadFactory getWrappedFactory() {
        return this.wrappedFactory;
    }

    public final void initializeThread(Thread thread) {
        if (this.namingPattern != null) {
            thread.setName(String.format(this.namingPattern, Long.valueOf(this.threadCounter.incrementAndGet())));
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        Integer num = this.priority;
        if (num != null) {
            thread.setPriority(num.intValue());
        }
        Boolean bool = this.daemon;
        if (bool != null) {
            thread.setDaemon(bool.booleanValue());
        }
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.wrappedFactory.newThread(runnable);
        initializeThread(threadNewThread);
        return threadNewThread;
    }

    public BasicThreadFactory(Builder builder) {
        ThreadFactory threadFactory = builder.wrappedFactory;
        if (threadFactory == null) {
            this.wrappedFactory = Executors.defaultThreadFactory();
        } else {
            this.wrappedFactory = threadFactory;
        }
        this.namingPattern = builder.namingPattern;
        this.priority = builder.priority;
        this.daemon = builder.daemon;
        this.uncaughtExceptionHandler = builder.exceptionHandler;
        this.threadCounter = new AtomicLong();
    }
}
