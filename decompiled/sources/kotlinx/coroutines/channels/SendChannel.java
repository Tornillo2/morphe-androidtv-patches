package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface SendChannel<E> {
    boolean close(@Nullable Throwable th);

    @NotNull
    SelectClause2<E, SendChannel<E>> getOnSend();

    @ExperimentalCoroutinesApi
    void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1);

    boolean isClosedForSend();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    boolean offer(E e);

    @Nullable
    Object send(E e, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    Object mo2084trySendJP2dKIU(E e);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean close$default(SendChannel sendChannel, Throwable th, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
            }
            if ((i & 1) != 0) {
                th = null;
            }
            return sendChannel.close(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
        public static <E> boolean offer(@NotNull SendChannel<? super E> sendChannel, E e) throws Throwable {
            Object objMo2084trySendJP2dKIU = sendChannel.mo2084trySendJP2dKIU(e);
            if (!(objMo2084trySendJP2dKIU instanceof ChannelResult.Failed)) {
                return true;
            }
            Throwable thM2093exceptionOrNullimpl = ChannelResult.m2093exceptionOrNullimpl(objMo2084trySendJP2dKIU);
            if (thM2093exceptionOrNullimpl == null) {
                return false;
            }
            StackTraceRecoveryKt.recoverStackTrace(thM2093exceptionOrNullimpl);
            throw thM2093exceptionOrNullimpl;
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isClosedForSend$annotations() {
        }
    }
}
