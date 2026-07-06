package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    public final <T extends LockFreeLinkedListNode> void forEach(Function1<? super T, Unit> function1) {
        if (Intrinsics.areEqual((LockFreeLinkedListNode) getNext(), this)) {
            return;
        }
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public final boolean isEmpty() {
        return getNext() == this;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean isRemoved() {
        return false;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @Nullable
    public LockFreeLinkedListNode nextIfRemoved() {
        return null;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    /* JADX INFO: renamed from: remove, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ boolean mo2129remove() {
        remove();
        throw null;
    }

    public final void validate$kotlinx_coroutines_core() {
        for (LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) getNext(); !Intrinsics.areEqual(nextNode, this); nextNode = nextNode.getNextNode()) {
        }
    }

    @NotNull
    public final Void remove() {
        throw new IllegalStateException("head cannot be removed");
    }
}
