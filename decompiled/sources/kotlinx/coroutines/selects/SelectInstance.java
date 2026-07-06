package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalCoroutinesApi
public interface SelectInstance<R> {
    void disposeOnSelect(@NotNull DisposableHandle disposableHandle);

    @NotNull
    Continuation<R> getCompletion();

    boolean isSelected();

    @Nullable
    Object performAtomicTrySelect(@NotNull AtomicDesc atomicDesc);

    void resumeSelectWithException(@NotNull Throwable th);

    boolean trySelect();

    @Nullable
    Object trySelectOther(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp);
}
