package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class RunnableKt {
    @NotNull
    public static final Runnable Runnable(@NotNull final Function0<Unit> function0) {
        return new Runnable() { // from class: kotlinx.coroutines.RunnableKt.Runnable.1
            @Override // java.lang.Runnable
            public final void run() {
                function0.invoke();
            }
        };
    }
}
