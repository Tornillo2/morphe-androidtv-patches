package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ChannelKt {
    @NotNull
    public static final <E> Channel<E> Channel(int i, @NotNull BufferOverflow bufferOverflow, @Nullable Function1<? super E, Unit> function1) {
        int i2 = 1;
        if (i == -2) {
            if (bufferOverflow == BufferOverflow.SUSPEND) {
                Channel.Factory.getClass();
                i2 = Channel.Factory.CHANNEL_DEFAULT_CAPACITY;
            }
            return new ArrayChannel(i2, bufferOverflow, function1);
        }
        if (i != -1) {
            return i != 0 ? i != Integer.MAX_VALUE ? (i == 1 && bufferOverflow == BufferOverflow.DROP_OLDEST) ? new ConflatedChannel(function1) : new ArrayChannel(i, bufferOverflow, function1) : new LinkedListChannel(function1) : bufferOverflow == BufferOverflow.SUSPEND ? new RendezvousChannel(function1) : new ArrayChannel(1, bufferOverflow, function1);
        }
        if (bufferOverflow == BufferOverflow.SUSPEND) {
            return new ConflatedChannel(function1);
        }
        throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
    }

    public static /* synthetic */ Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return Channel(i, bufferOverflow, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getOrElse-WpGqRn0, reason: not valid java name */
    public static final <T> T m2085getOrElseWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, ? extends T> function1) {
        return obj instanceof ChannelResult.Failed ? function1.invoke(ChannelResult.m2093exceptionOrNullimpl(obj)) : obj;
    }

    @NotNull
    /* JADX INFO: renamed from: onClosed-WpGqRn0, reason: not valid java name */
    public static final <T> Object m2086onClosedWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        if (obj instanceof ChannelResult.Closed) {
            function1.invoke(ChannelResult.m2093exceptionOrNullimpl(obj));
        }
        return obj;
    }

    @NotNull
    /* JADX INFO: renamed from: onFailure-WpGqRn0, reason: not valid java name */
    public static final <T> Object m2087onFailureWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        if (obj instanceof ChannelResult.Failed) {
            function1.invoke(ChannelResult.m2093exceptionOrNullimpl(obj));
        }
        return obj;
    }

    @NotNull
    /* JADX INFO: renamed from: onSuccess-WpGqRn0, reason: not valid java name */
    public static final <T> Object m2088onSuccessWpGqRn0(@NotNull Object obj, @NotNull Function1<? super T, Unit> function1) {
        if (!(obj instanceof ChannelResult.Failed)) {
            function1.invoke(obj);
        }
        return obj;
    }

    public static /* synthetic */ Channel Channel$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return Channel(i);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.4.0, binary compatibility with earlier versions")
    public static final /* synthetic */ Channel Channel(int i) {
        return Channel$default(i, null, null, 6, null);
    }
}
