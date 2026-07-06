package kotlinx.coroutines.internal;

import java.util.List;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class MainDispatchersKt {

    @NotNull
    public static final String FAST_SERVICE_LOADER_PROPERTY_NAME = "kotlinx.coroutines.fast.service.loader";
    public static final boolean SUPPORT_MISSING = false;

    public static final MissingMainCoroutineDispatcher createMissingDispatcher(Throwable th, String str) throws Throwable {
        if (th != null) {
            throw th;
        }
        throwMissingMainDispatcherException();
        throw null;
    }

    public static /* synthetic */ MissingMainCoroutineDispatcher createMissingDispatcher$default(Throwable th, String str, int i, Object obj) throws Throwable {
        if ((i & 1) != 0) {
            th = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        createMissingDispatcher(th, str);
        throw null;
    }

    @InternalCoroutinesApi
    public static final boolean isMissing(@NotNull MainCoroutineDispatcher mainCoroutineDispatcher) {
        return mainCoroutineDispatcher.getImmediate() instanceof MissingMainCoroutineDispatcher;
    }

    @NotNull
    public static final Void throwMissingMainDispatcherException() {
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }

    @InternalCoroutinesApi
    @NotNull
    public static final MainCoroutineDispatcher tryCreateDispatcher(@NotNull MainDispatcherFactory mainDispatcherFactory, @NotNull List<? extends MainDispatcherFactory> list) throws Throwable {
        try {
            return mainDispatcherFactory.createDispatcher(list);
        } catch (Throwable th) {
            createMissingDispatcher(th, mainDispatcherFactory.hintOnError());
            throw null;
        }
    }

    public static /* synthetic */ void getSUPPORT_MISSING$annotations() {
    }
}
