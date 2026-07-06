package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ReceiveChannel$onReceiveOrNull$1<E> implements SelectClause1<E> {
    public final /* synthetic */ ReceiveChannel<E> this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public ReceiveChannel$onReceiveOrNull$1(ReceiveChannel<? extends E> receiveChannel) {
        this.this$0 = receiveChannel;
    }

    @Override // kotlinx.coroutines.selects.SelectClause1
    @InternalCoroutinesApi
    public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        this.this$0.getOnReceiveCatching().registerSelectClause1(selectInstance, new ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(function2, null));
    }
}
