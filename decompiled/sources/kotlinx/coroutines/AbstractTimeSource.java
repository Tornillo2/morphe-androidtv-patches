package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractTimeSource {
    public abstract long currentTimeMillis();

    public abstract long nanoTime();

    public abstract void parkNanos(@NotNull Object obj, long j);

    public abstract void registerTimeLoopThread();

    public abstract void trackTask();

    public abstract void unTrackTask();

    public abstract void unpark(@NotNull Thread thread);

    public abstract void unregisterTimeLoopThread();

    @NotNull
    public abstract Runnable wrapTask(@NotNull Runnable runnable);
}
