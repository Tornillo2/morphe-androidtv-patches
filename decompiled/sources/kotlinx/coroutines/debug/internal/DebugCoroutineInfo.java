package kotlinx.coroutines.debug.internal;

import java.util.List;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@PublishedApi
public final class DebugCoroutineInfo {

    @NotNull
    public final CoroutineContext context;

    @Nullable
    public final CoroutineStackFrame creationStackBottom;

    @NotNull
    public final List<StackTraceElement> creationStackTrace;

    @Nullable
    public final CoroutineStackFrame lastObservedFrame;

    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace;

    @Nullable
    public final Thread lastObservedThread;
    public final long sequenceNumber;

    @NotNull
    public final String state;

    public DebugCoroutineInfo(@NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @NotNull CoroutineContext coroutineContext) {
        this.context = coroutineContext;
        this.creationStackBottom = debugCoroutineInfoImpl.creationStackBottom;
        this.sequenceNumber = debugCoroutineInfoImpl.sequenceNumber;
        this.creationStackTrace = debugCoroutineInfoImpl.creationStackTrace();
        this.state = debugCoroutineInfoImpl._state;
        this.lastObservedThread = debugCoroutineInfoImpl.lastObservedThread;
        this.lastObservedFrame = debugCoroutineInfoImpl.getLastObservedFrame$kotlinx_coroutines_core();
        this.lastObservedStackTrace = debugCoroutineInfoImpl.lastObservedStackTrace();
    }

    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Nullable
    public final CoroutineStackFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return this.creationStackTrace;
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame() {
        return this.lastObservedFrame;
    }

    @Nullable
    public final Thread getLastObservedThread() {
        return this.lastObservedThread;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }

    @JvmName(name = "lastObservedStackTrace")
    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }
}
