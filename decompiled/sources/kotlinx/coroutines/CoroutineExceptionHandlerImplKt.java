package kotlinx.coroutines;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CoroutineExceptionHandlerImplKt {

    @NotNull
    public static final List<CoroutineExceptionHandler> handlers = SequencesKt___SequencesKt.toList(SequencesKt__SequencesKt.asSequence(ServiceLoader.load(CoroutineExceptionHandler.class, CoroutineExceptionHandler.class.getClassLoader()).iterator()));

    public static final void handleCoroutineExceptionImpl(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th) {
        Iterator<CoroutineExceptionHandler> it = handlers.iterator();
        while (it.hasNext()) {
            try {
                it.next().handleException(coroutineContext, th);
            } catch (Throwable th2) {
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, CoroutineExceptionHandlerKt.handlerException(th, th2));
            }
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        try {
            ExceptionsKt__ExceptionsKt.addSuppressed(th, new DiagnosticCoroutineContextException(coroutineContext));
        } catch (Throwable th3) {
            ResultKt.createFailure(th3);
        }
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }
}
