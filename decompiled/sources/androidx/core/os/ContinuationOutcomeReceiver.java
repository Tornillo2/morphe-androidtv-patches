package androidx.core.os;

import android.os.OutcomeReceiver;
import androidx.annotation.RequiresApi;
import java.lang.Throwable;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(31)
public final class ContinuationOutcomeReceiver<R, E extends Throwable> extends AtomicBoolean implements OutcomeReceiver<R, E> {

    @NotNull
    public final Continuation<R> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationOutcomeReceiver(@NotNull Continuation<? super R> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // android.os.OutcomeReceiver
    public void onError(@NotNull E e) {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(ResultKt.createFailure(e));
        }
    }

    @Override // android.os.OutcomeReceiver
    public void onResult(R r) {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(r);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    @NotNull
    public String toString() {
        return "ContinuationOutcomeReceiver(outcomeReceived = " + get() + ')';
    }
}
