package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {

    @NotNull
    public final CoroutineContext context;

    public DiagnosticCoroutineContextException(@NotNull CoroutineContext coroutineContext) {
        this.context = coroutineContext;
    }

    @Override // java.lang.Throwable
    @NotNull
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    @NotNull
    public String getLocalizedMessage() {
        return this.context.toString();
    }
}
