package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class LockFreeTaskQueue<E> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _cur$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueue.class, Object.class, "_cur");

    @NotNull
    private volatile /* synthetic */ Object _cur;

    public LockFreeTaskQueue(boolean z) {
        this._cur = new LockFreeTaskQueueCore(8, z);
    }

    public final boolean addLast(@NotNull E e) {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            int iAddLast = lockFreeTaskQueueCore.addLast(e);
            if (iAddLast == 0) {
                return true;
            }
            if (iAddLast == 1) {
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
            } else if (iAddLast == 2) {
                return false;
            }
        }
    }

    public final void close() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            if (lockFreeTaskQueueCore.close()) {
                return;
            } else {
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
            }
        }
    }

    public final int getSize() {
        return ((LockFreeTaskQueueCore) this._cur).getSize();
    }

    public final boolean isClosed() {
        return ((LockFreeTaskQueueCore) this._cur).isClosed();
    }

    public final boolean isEmpty() {
        return ((LockFreeTaskQueueCore) this._cur).isEmpty();
    }

    @NotNull
    public final <R> List<R> map(@NotNull Function1<? super E, ? extends R> function1) {
        return ((LockFreeTaskQueueCore) this._cur).map(function1);
    }

    @Nullable
    public final E removeFirstOrNull() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            E e = (E) lockFreeTaskQueueCore.removeFirstOrNull();
            if (e != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                return e;
            }
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }
}
