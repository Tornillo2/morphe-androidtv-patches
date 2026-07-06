package com.amazon.avod.mpb.threading.internal;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.amazon.avod.mpb.annotate.Positive;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NamedThreadFactory implements ThreadFactory {

    @NotNull
    public final AtomicLong threadCount;
    public final ThreadFactory threadFactory;

    @NotNull
    public final String threadName;
    public final int threadPriority;

    public NamedThreadFactory(@NotNull String threadName, @Positive int i) {
        Intrinsics.checkNotNullParameter(threadName, "threadName");
        this.threadName = threadName;
        this.threadPriority = i;
        this.threadFactory = Executors.defaultThreadFactory();
        this.threadCount = new AtomicLong(0L);
        Preconditions.checkArgument(i > 0, ObjectListKt$$ExternalSyntheticOutline1.m("threadPriority <=0 (", i, ")"), new Object[0]);
    }

    @Override // java.util.concurrent.ThreadFactory
    @NotNull
    public Thread newThread(@Nullable Runnable runnable) {
        Thread threadNewThread = this.threadFactory.newThread(runnable);
        threadNewThread.setName(this.threadName + "-" + this.threadCount.getAndIncrement());
        int i = this.threadPriority;
        if (i != 5) {
            threadNewThread.setPriority(i);
        }
        return threadNewThread;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NamedThreadFactory(@NotNull String threadName) {
        this(threadName, 5);
        Intrinsics.checkNotNullParameter(threadName, "threadName");
    }
}
