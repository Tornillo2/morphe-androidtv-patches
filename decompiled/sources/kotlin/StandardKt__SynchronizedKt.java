package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StandardKt__SynchronizedKt extends StandardKt__StandardKt {
    @InlineOnly
    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <R> R m590synchronized(Object lock, Function0<? extends R> block) {
        R rInvoke;
        Intrinsics.checkNotNullParameter(lock, "lock");
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (lock) {
            rInvoke = block.invoke();
        }
        return rInvoke;
    }
}
