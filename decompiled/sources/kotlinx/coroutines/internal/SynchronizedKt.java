package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SynchronizedKt {
    @InternalCoroutinesApi
    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <T> T m2141synchronized(@NotNull Object obj, @NotNull Function0<? extends T> function0) {
        T tInvoke;
        synchronized (obj) {
            tInvoke = function0.invoke();
        }
        return tInvoke;
    }

    @InternalCoroutinesApi
    public static /* synthetic */ void SynchronizedObject$annotations() {
    }
}
