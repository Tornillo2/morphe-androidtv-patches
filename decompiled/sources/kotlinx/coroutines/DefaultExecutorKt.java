package kotlinx.coroutines;

import kotlinx.coroutines.internal.MissingMainCoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultExecutorKt {
    public static final boolean defaultMainDelayOptIn = SystemPropsKt__SystemProps_commonKt.systemProp("kotlinx.coroutines.main.delay", false);

    @NotNull
    public static final Delay DefaultDelay = initializeDefaultDelay();

    @NotNull
    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Delay initializeDefaultDelay() {
        if (!defaultMainDelayOptIn) {
            return DefaultExecutor.INSTANCE;
        }
        MainCoroutineDispatcher main = Dispatchers.getMain();
        return ((main.getImmediate() instanceof MissingMainCoroutineDispatcher) || !(main instanceof Delay)) ? DefaultExecutor.INSTANCE : (Delay) main;
    }
}
