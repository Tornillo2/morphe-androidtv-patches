package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface SelectClause0 {
    @InternalCoroutinesApi
    <R> void registerSelectClause0(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1);
}
