package androidx.core.os;

import android.os.OutcomeReceiver;
import androidx.annotation.RequiresApi;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(31)
public final class OutcomeReceiverKt {
    @RequiresApi(31)
    @NotNull
    public static final <R, E extends Throwable> OutcomeReceiver<R, E> asOutcomeReceiver(@NotNull Continuation<? super R> continuation) {
        return new ContinuationOutcomeReceiver(continuation);
    }
}
