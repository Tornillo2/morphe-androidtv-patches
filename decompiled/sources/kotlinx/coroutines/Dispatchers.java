package kotlinx.coroutines;

import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Dispatchers {

    @NotNull
    public static final Dispatchers INSTANCE = new Dispatchers();

    @NotNull
    public static final CoroutineDispatcher Default = DefaultScheduler.INSTANCE;

    @NotNull
    public static final CoroutineDispatcher Unconfined = Unconfined.INSTANCE;

    @NotNull
    public static final CoroutineDispatcher IO = DefaultIoScheduler.INSTANCE;

    @NotNull
    public static final CoroutineDispatcher getDefault() {
        return Default;
    }

    @NotNull
    public static final CoroutineDispatcher getIO() {
        return IO;
    }

    @NotNull
    public static final MainCoroutineDispatcher getMain() {
        return MainDispatcherLoader.dispatcher;
    }

    @NotNull
    public static final CoroutineDispatcher getUnconfined() {
        return Unconfined;
    }

    @DelicateCoroutinesApi
    public final void shutdown() {
        DefaultExecutor.INSTANCE.shutdown();
        DefaultScheduler.INSTANCE.shutdown$kotlinx_coroutines_core();
    }

    @JvmStatic
    public static /* synthetic */ void getDefault$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getIO$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getMain$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getUnconfined$annotations() {
    }
}
