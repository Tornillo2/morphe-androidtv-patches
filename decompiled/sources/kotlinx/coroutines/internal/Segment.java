package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater cleanedAndPointers$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");

    @NotNull
    private volatile /* synthetic */ int cleanedAndPointers;
    public final long id;

    public Segment(long j, @Nullable S s, int i) {
        super(s);
        this.id = j;
        this.cleanedAndPointers = i << 16;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return cleanedAndPointers$FU.addAndGet(this, -65536) == getMaxSlots() && !isTail();
    }

    public final long getId() {
        return this.id;
    }

    public abstract int getMaxSlots();

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean getRemoved() {
        return this.cleanedAndPointers == getMaxSlots() && !isTail();
    }

    public final void onSlotCleaned() {
        if (cleanedAndPointers$FU.incrementAndGet(this) != getMaxSlots() || isTail()) {
            return;
        }
        remove();
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        int i;
        do {
            i = this.cleanedAndPointers;
            if (i == getMaxSlots() && !isTail()) {
                return false;
            }
        } while (!cleanedAndPointers$FU.compareAndSet(this, i, 65536 + i));
        return true;
    }
}
