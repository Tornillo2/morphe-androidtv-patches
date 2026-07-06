package kotlinx.coroutines.channels;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AbstractChannelKt {
    public static final int RECEIVE_RESULT = 1;
    public static final int RECEIVE_THROWS_ON_CLOSE = 0;

    @JvmField
    @NotNull
    public static final Symbol EMPTY = new Symbol("EMPTY");

    @JvmField
    @NotNull
    public static final Symbol OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");

    @JvmField
    @NotNull
    public static final Symbol OFFER_FAILED = new Symbol("OFFER_FAILED");

    @JvmField
    @NotNull
    public static final Symbol POLL_FAILED = new Symbol("POLL_FAILED");

    @JvmField
    @NotNull
    public static final Symbol ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");

    @JvmField
    @NotNull
    public static final Symbol HANDLER_INVOKED = new Symbol("ON_CLOSE_HANDLER_INVOKED");

    public static final <E> Object toResult(Object obj) {
        if (!(obj instanceof Closed)) {
            ChannelResult.Companion.getClass();
            return obj;
        }
        ChannelResult.Companion companion = ChannelResult.Companion;
        Throwable th = ((Closed) obj).closeCause;
        companion.getClass();
        return new ChannelResult.Closed(th);
    }

    public static final <E> Object toResult(Closed<?> closed) {
        ChannelResult.Companion companion = ChannelResult.Companion;
        Throwable th = closed.closeCause;
        companion.getClass();
        return new ChannelResult.Closed(th);
    }

    public static /* synthetic */ void getEMPTY$annotations() {
    }

    public static /* synthetic */ void getENQUEUE_FAILED$annotations() {
    }

    public static /* synthetic */ void getHANDLER_INVOKED$annotations() {
    }

    public static /* synthetic */ void getOFFER_FAILED$annotations() {
    }

    public static /* synthetic */ void getOFFER_SUCCESS$annotations() {
    }

    public static /* synthetic */ void getPOLL_FAILED$annotations() {
    }
}
