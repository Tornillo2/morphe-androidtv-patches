package kotlinx.coroutines.debug.internal;

import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class StackTraceFrame implements CoroutineStackFrame {

    @Nullable
    public final CoroutineStackFrame callerFrame;

    @NotNull
    public final StackTraceElement stackTraceElement;

    public StackTraceFrame(@Nullable CoroutineStackFrame coroutineStackFrame, @NotNull StackTraceElement stackTraceElement) {
        this.callerFrame = coroutineStackFrame;
        this.stackTraceElement = stackTraceElement;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        return this.callerFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @NotNull
    public StackTraceElement getStackTraceElement() {
        return this.stackTraceElement;
    }
}
