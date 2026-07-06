package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@InternalCoroutinesApi
public final class NonDisposableHandle implements DisposableHandle, ChildHandle {

    @NotNull
    public static final NonDisposableHandle INSTANCE = new NonDisposableHandle();

    @Override // kotlinx.coroutines.ChildHandle
    public boolean childCancelled(@NotNull Throwable th) {
        return false;
    }

    @Override // kotlinx.coroutines.ChildHandle
    @Nullable
    public Job getParent() {
        return null;
    }

    @NotNull
    public String toString() {
        return "NonDisposableHandle";
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
    }
}
