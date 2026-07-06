package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");

    @NotNull
    private volatile /* synthetic */ Object _next = null;

    @NotNull
    private volatile /* synthetic */ Object _prev;

    public ConcurrentLinkedListNode(@Nullable N n) {
        this._prev = n;
    }

    public final void cleanPrev() {
        _prev$FU.lazySet(this, null);
    }

    public final N getLeftmostAliveNode() {
        N n = (N) this._prev;
        while (n != null && n.getRemoved()) {
            n = (N) n._prev;
        }
        return n;
    }

    @Nullable
    public final N getNext() {
        Object obj = this._next;
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (N) obj;
    }

    public final Object getNextOrClosed() {
        return this._next;
    }

    @Nullable
    public final N getPrev() {
        return (N) this._prev;
    }

    public abstract boolean getRemoved();

    public final N getRightmostAliveNode() {
        N n = (N) getNext();
        Intrinsics.checkNotNull(n);
        while (n.getRemoved()) {
            n = (N) n.getNext();
            Intrinsics.checkNotNull(n);
        }
        return n;
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    public final boolean markAsClosed() {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, null, ConcurrentLinkedListKt.CLOSED);
    }

    @Nullable
    public final N nextOrIfClosed(@NotNull Function0 function0) {
        Object obj = this._next;
        if (obj != ConcurrentLinkedListKt.CLOSED) {
            return (N) obj;
        }
        function0.invoke();
        throw new KotlinNothingValueException();
    }

    public final void remove() {
        while (true) {
            ConcurrentLinkedListNode leftmostAliveNode = getLeftmostAliveNode();
            ConcurrentLinkedListNode rightmostAliveNode = getRightmostAliveNode();
            rightmostAliveNode._prev = leftmostAliveNode;
            if (leftmostAliveNode != null) {
                leftmostAliveNode._next = rightmostAliveNode;
            }
            if (!rightmostAliveNode.getRemoved() && (leftmostAliveNode == null || !leftmostAliveNode.getRemoved())) {
                return;
            }
        }
    }

    public final boolean trySetNext(@NotNull N n) {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, null, n);
    }
}
