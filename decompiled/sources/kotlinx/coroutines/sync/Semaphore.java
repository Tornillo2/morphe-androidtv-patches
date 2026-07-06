package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface Semaphore {
    @Nullable
    Object acquire(@NotNull Continuation<? super Unit> continuation);

    int getAvailablePermits();

    void release();

    boolean tryAcquire();
}
