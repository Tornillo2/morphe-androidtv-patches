package kotlinx.coroutines;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ThreadPoolDispatcherKt {
    @DelicateCoroutinesApi
    @NotNull
    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(final int i, @NotNull final String str) {
        if (i < 1) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Expected at least one thread, but ", i, " specified").toString());
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        return new ExecutorCoroutineDispatcherImpl(Executors.newScheduledThreadPool(i, new ThreadFactory() { // from class: kotlinx.coroutines.ThreadPoolDispatcherKt$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return ThreadPoolDispatcherKt.m2076newFixedThreadPoolContext$lambda1(i, str, atomicInteger, runnable);
            }
        }));
    }

    /* JADX INFO: renamed from: newFixedThreadPoolContext$lambda-1, reason: not valid java name */
    public static final Thread m2076newFixedThreadPoolContext$lambda1(int i, String str, AtomicInteger atomicInteger, Runnable runnable) {
        if (i != 1) {
            str = str + '-' + atomicInteger.incrementAndGet();
        }
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(true);
        return thread;
    }

    @DelicateCoroutinesApi
    @NotNull
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(@NotNull String str) {
        return newFixedThreadPoolContext(1, str);
    }
}
