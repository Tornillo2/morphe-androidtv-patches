package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SuspendKt {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final <R> Function1<Continuation<? super R>, Object> suspend(Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block;
    }
}
