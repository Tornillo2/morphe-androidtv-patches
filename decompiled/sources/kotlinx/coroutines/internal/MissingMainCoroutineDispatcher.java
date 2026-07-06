package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {

    @Nullable
    public final Throwable cause;

    @Nullable
    public final String errorHint;

    public MissingMainCoroutineDispatcher(@Nullable Throwable th, @Nullable String str) {
        this.cause = th;
        this.errorHint = str;
    }

    @Override // kotlinx.coroutines.Delay
    @Nullable
    public Object delay(long j, @NotNull Continuation<?> continuation) {
        missing();
        throw null;
    }

    @NotNull
    public Void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(@NotNull CoroutineContext coroutineContext) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public CoroutineDispatcher limitedParallelism(int i) {
        missing();
        throw null;
    }

    public final Void missing() {
        String strConcat;
        if (this.cause == null) {
            MainDispatchersKt.throwMissingMainDispatcherException();
            throw null;
        }
        String str = this.errorHint;
        if (str == null || (strConcat = ". ".concat(str)) == null) {
            strConcat = "";
        }
        throw new IllegalStateException("Module with the Main dispatcher had failed to initialize".concat(strConcat), this.cause);
    }

    @NotNull
    public Void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Dispatchers.Main[missing");
        if (this.cause != null) {
            str = ", cause=" + this.cause;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* JADX INFO: renamed from: dispatch, reason: collision with other method in class */
    public void mo2130dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.Delay
    /* JADX INFO: renamed from: scheduleResumeAfterDelay, reason: collision with other method in class */
    public void mo2131scheduleResumeAfterDelay(long j, CancellableContinuation cancellableContinuation) {
        missing();
        throw null;
    }

    public /* synthetic */ MissingMainCoroutineDispatcher(Throwable th, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i & 2) != 0 ? null : str);
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    @NotNull
    public MainCoroutineDispatcher getImmediate() {
        return this;
    }
}
