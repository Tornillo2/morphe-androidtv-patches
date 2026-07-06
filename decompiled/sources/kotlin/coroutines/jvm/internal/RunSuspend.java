package kotlin.coroutines.jvm.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class RunSuspend implements Continuation<Unit> {

    @Nullable
    public Result<Unit> result;

    public final void await() {
        synchronized (this) {
            while (true) {
                try {
                    Result<Unit> result = this.result;
                    if (result == null) {
                        wait();
                    } else {
                        ResultKt.throwOnFailure(result.value);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Nullable
    /* JADX INFO: renamed from: getResult-xLWZpok, reason: not valid java name */
    public final Result<Unit> m1791getResultxLWZpok() {
        return this.result;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        synchronized (this) {
            this.result = new Result<>(obj);
            notifyAll();
        }
    }

    public final void setResult(@Nullable Result<Unit> result) {
        this.result = result;
    }
}
