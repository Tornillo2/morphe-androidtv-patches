package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.ScopeCoroutine;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TimeoutCoroutine<U, T extends U> extends ScopeCoroutine<T> implements Runnable {

    @JvmField
    public final long time;

    public TimeoutCoroutine(long j, @NotNull Continuation<? super U> continuation) {
        super(continuation.getContext(), continuation);
        this.time = j;
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport
    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return super.nameString$kotlinx_coroutines_core() + "(timeMillis=" + this.time + ')';
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        cancelImpl$kotlinx_coroutines_core(TimeoutKt.TimeoutCancellationException(this.time, this));
    }
}
