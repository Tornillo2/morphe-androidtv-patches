package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface SelectClause2<P, Q> {
    @InternalCoroutinesApi
    <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2);
}
