package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.jvm.JvmField;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class MainDispatcherLoader {
    public static final boolean FAST_SERVICE_LOADER_ENABLED = false;

    @NotNull
    public static final MainDispatcherLoader INSTANCE;

    @JvmField
    @NotNull
    public static final MainCoroutineDispatcher dispatcher;

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        INSTANCE = mainDispatcherLoader;
        SystemPropsKt__SystemProps_commonKt.systemProp(MainDispatchersKt.FAST_SERVICE_LOADER_PROPERTY_NAME, true);
        dispatcher = mainDispatcherLoader.loadMainDispatcher();
    }

    public final MainCoroutineDispatcher loadMainDispatcher() throws Throwable {
        Object next;
        try {
            List list = SequencesKt___SequencesKt.toList(SequencesKt__SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
            Iterator it = list.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int loadPriority = ((MainDispatcherFactory) next).getLoadPriority();
                    do {
                        Object next2 = it.next();
                        int loadPriority2 = ((MainDispatcherFactory) next2).getLoadPriority();
                        if (loadPriority < loadPriority2) {
                            next = next2;
                            loadPriority = loadPriority2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
            if (mainDispatcherFactory != null) {
                return MainDispatchersKt.tryCreateDispatcher(mainDispatcherFactory, list);
            }
            MainDispatchersKt.createMissingDispatcher$default(null, null, 3, null);
            throw null;
        } catch (Throwable th) {
            MainDispatchersKt.createMissingDispatcher$default(th, null, 2, null);
            throw null;
        }
    }
}
