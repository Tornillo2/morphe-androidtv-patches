package kotlinx.coroutines;

import java.io.Closeable;
import java.util.concurrent.Executor;
import kotlin.ExperimentalStdlibApi;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {

    @NotNull
    public static final Key Key = new Key();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @ExperimentalStdlibApi
    public static final class Key extends AbstractCoroutineContextKey<CoroutineDispatcher, ExecutorCoroutineDispatcher> {

        /* JADX INFO: renamed from: kotlinx.coroutines.ExecutorCoroutineDispatcher$Key$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class AnonymousClass1 extends Lambda implements Function1<CoroutineContext.Element, ExecutorCoroutineDispatcher> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1(1);

            public AnonymousClass1() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final ExecutorCoroutineDispatcher invoke(@NotNull CoroutineContext.Element element) {
                if (element instanceof ExecutorCoroutineDispatcher) {
                    return (ExecutorCoroutineDispatcher) element;
                }
                return null;
            }
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public Key() {
            super(CoroutineDispatcher.Key, AnonymousClass1.INSTANCE);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    @NotNull
    public abstract Executor getExecutor();
}
