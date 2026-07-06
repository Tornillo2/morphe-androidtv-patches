package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DebugStringsKt {
    @NotNull
    public static final String getClassSimpleName(@NotNull Object obj) {
        return obj.getClass().getSimpleName();
    }

    @NotNull
    public static final String getHexAddress(@NotNull Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    @NotNull
    public static final String toDebugString(@NotNull Continuation<?> continuation) {
        Object objCreateFailure;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            objCreateFailure = continuation + ObjectUtils.AT_SIGN + getHexAddress(continuation);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        if (Result.m583exceptionOrNullimpl(objCreateFailure) != null) {
            objCreateFailure = continuation.getClass().getName() + ObjectUtils.AT_SIGN + getHexAddress(continuation);
        }
        return (String) objCreateFailure;
    }
}
