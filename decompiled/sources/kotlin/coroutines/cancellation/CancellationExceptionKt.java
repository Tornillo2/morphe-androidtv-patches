package kotlin.coroutines.cancellation;

import java.util.concurrent.CancellationException;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nCancellationException.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellationException.kt\nkotlin/coroutines/cancellation/CancellationExceptionKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,22:1\n1#2:23\n*E\n"})
public final class CancellationExceptionKt {
    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final CancellationException CancellationException(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final CancellationException CancellationException(Throwable th) {
        CancellationException cancellationException = new CancellationException(th != null ? th.toString() : null);
        cancellationException.initCause(th);
        return cancellationException;
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void CancellationException$annotations() {
    }
}
