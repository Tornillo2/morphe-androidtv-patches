package androidx.collection.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Lock {
    public final <T> T synchronizedImpl(@NotNull Function0<? extends T> block) {
        T tInvoke;
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (this) {
            tInvoke = block.invoke();
        }
        return tInvoke;
    }
}
