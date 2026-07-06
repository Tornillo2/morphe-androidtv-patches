package kotlinx.coroutines.debug.internal;

import java.io.Serializable;
import java.lang.Thread;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@PublishedApi
public final class DebuggerInfo implements Serializable {

    @Nullable
    public final Long coroutineId;

    @Nullable
    public final String dispatcher;

    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace;

    @Nullable
    public final String lastObservedThreadName;

    @Nullable
    public final String lastObservedThreadState;

    @Nullable
    public final String name;
    public final long sequenceNumber;

    @NotNull
    public final String state;

    public DebuggerInfo(@NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @NotNull CoroutineContext coroutineContext) {
        Thread.State state;
        CoroutineId coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key);
        this.coroutineId = coroutineId != null ? Long.valueOf(coroutineId.id) : null;
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        this.dispatcher = continuationInterceptor != null ? continuationInterceptor.toString() : null;
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
        this.name = coroutineName != null ? coroutineName.name : null;
        this.state = debugCoroutineInfoImpl._state;
        Thread thread = debugCoroutineInfoImpl.lastObservedThread;
        this.lastObservedThreadState = (thread == null || (state = thread.getState()) == null) ? null : state.toString();
        Thread thread2 = debugCoroutineInfoImpl.lastObservedThread;
        this.lastObservedThreadName = thread2 != null ? thread2.getName() : null;
        this.lastObservedStackTrace = debugCoroutineInfoImpl.lastObservedStackTrace();
        this.sequenceNumber = debugCoroutineInfoImpl.sequenceNumber;
    }

    @Nullable
    public final Long getCoroutineId() {
        return this.coroutineId;
    }

    @Nullable
    public final String getDispatcher() {
        return this.dispatcher;
    }

    @NotNull
    public final List<StackTraceElement> getLastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }

    @Nullable
    public final String getLastObservedThreadName() {
        return this.lastObservedThreadName;
    }

    @Nullable
    public final String getLastObservedThreadState() {
        return this.lastObservedThreadState;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }
}
