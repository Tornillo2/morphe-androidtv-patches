package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@InternalCoroutinesApi
public interface ChildHandle extends DisposableHandle {
    @InternalCoroutinesApi
    boolean childCancelled(@NotNull Throwable th);

    @Nullable
    Job getParent();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @InternalCoroutinesApi
        public static /* synthetic */ void getParent$annotations() {
        }
    }
}
