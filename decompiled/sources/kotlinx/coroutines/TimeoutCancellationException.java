package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TimeoutCancellationException extends CancellationException implements CopyableThrowable<TimeoutCancellationException> {

    @JvmField
    @Nullable
    public final transient Job coroutine;

    public TimeoutCancellationException(@NotNull String str, @Nullable Job job) {
        super(str);
        this.coroutine = job;
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    @NotNull
    public TimeoutCancellationException createCopy() {
        String message = getMessage();
        if (message == null) {
            message = "";
        }
        TimeoutCancellationException timeoutCancellationException = new TimeoutCancellationException(message, this.coroutine);
        timeoutCancellationException.initCause(this);
        return timeoutCancellationException;
    }

    public TimeoutCancellationException(@NotNull String str) {
        this(str, null);
    }
}
