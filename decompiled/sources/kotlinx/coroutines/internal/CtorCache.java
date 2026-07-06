package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class CtorCache {
    @NotNull
    public abstract Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls);
}
