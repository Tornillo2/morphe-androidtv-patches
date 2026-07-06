package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ActorKt {
    @ObsoleteCoroutinesApi
    @NotNull
    public static final <E> SendChannel<E> actor(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext, int i, @NotNull CoroutineStart coroutineStart, @Nullable Function1<? super Throwable, Unit> function1, @NotNull Function2<? super ActorScope<E>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        CoroutineContext coroutineContextNewCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        Channel channelChannel$default = ChannelKt.Channel$default(i, null, null, 6, null);
        ActorCoroutine lazyActorCoroutine = coroutineStart.isLazy() ? new LazyActorCoroutine(coroutineContextNewCoroutineContext, channelChannel$default, function2) : new ActorCoroutine(coroutineContextNewCoroutineContext, channelChannel$default, true);
        if (function1 != null) {
            ((JobSupport) lazyActorCoroutine).invokeOnCompletion(function1);
        }
        coroutineStart.invoke(function2, lazyActorCoroutine, (Continuation) lazyActorCoroutine);
        return (SendChannel<E>) lazyActorCoroutine;
    }

    public static /* synthetic */ SendChannel actor$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, int i, CoroutineStart coroutineStart, Function1 function1, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        if ((i2 & 8) != 0) {
            function1 = null;
        }
        CoroutineStart coroutineStart2 = coroutineStart;
        return actor(coroutineScope, coroutineContext, i, coroutineStart2, function1, function2);
    }
}
