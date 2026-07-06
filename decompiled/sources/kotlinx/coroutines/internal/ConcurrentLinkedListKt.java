package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrentLinkedListKt {

    @NotNull
    public static final Symbol CLOSED = new Symbol("CLOSED");
    public static final int POINTERS_SHIFT = 16;

    @NotNull
    public static final <N extends ConcurrentLinkedListNode<N>> N close(@NotNull N n) {
        while (true) {
            Object obj = ((ConcurrentLinkedListNode) n)._next;
            if (obj == CLOSED) {
                return n;
            }
            ConcurrentLinkedListNode concurrentLinkedListNode = (ConcurrentLinkedListNode) obj;
            if (concurrentLinkedListNode != null) {
                n = (N) concurrentLinkedListNode;
            } else if (n.markAsClosed()) {
                return n;
            }
        }
    }

    public static final <S extends Segment<S>> Object findSegmentInternal(S s, long j, Function2<? super Long, ? super S, ? extends S> function2) {
        while (true) {
            if (s.id >= j && !s.getRemoved()) {
                return s;
            }
            Object obj = ((ConcurrentLinkedListNode) s)._next;
            Symbol symbol = CLOSED;
            if (obj == symbol) {
                return symbol;
            }
            S sInvoke = (S) ((ConcurrentLinkedListNode) obj);
            if (sInvoke == null) {
                sInvoke = function2.invoke(Long.valueOf(s.id + 1), s);
                if (s.trySetNext(sInvoke)) {
                    if (s.getRemoved()) {
                        s.remove();
                    }
                }
            }
            s = (Object) sInvoke;
        }
    }

    public static /* synthetic */ void getCLOSED$annotations() {
    }
}
