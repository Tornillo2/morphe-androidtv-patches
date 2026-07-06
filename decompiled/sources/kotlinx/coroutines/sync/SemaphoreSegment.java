package kotlinx.coroutines.sync;

import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.coroutines.internal.Segment;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SemaphoreSegment extends Segment<SemaphoreSegment> {

    @NotNull
    public /* synthetic */ AtomicReferenceArray acquirers;

    public SemaphoreSegment(long j, @Nullable SemaphoreSegment semaphoreSegment, int i) {
        super(j, semaphoreSegment, i);
        this.acquirers = new AtomicReferenceArray(SemaphoreKt.SEGMENT_SIZE);
    }

    public final void cancel(int i) {
        this.acquirers.set(i, SemaphoreKt.CANCELLED);
        onSlotCleaned();
    }

    public final boolean cas(int i, @Nullable Object obj, @Nullable Object obj2) {
        return Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.acquirers, i, obj, obj2);
    }

    @Nullable
    public final Object get(int i) {
        return this.acquirers.get(i);
    }

    @Nullable
    public final Object getAndSet(int i, @Nullable Object obj) {
        return this.acquirers.getAndSet(i, obj);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        return SemaphoreKt.SEGMENT_SIZE;
    }

    public final void set(int i, @Nullable Object obj) {
        this.acquirers.set(i, obj);
    }

    @NotNull
    public String toString() {
        return "SemaphoreSegment[id=" + this.id + ", hashCode=" + hashCode() + AbstractJsonLexerKt.END_LIST;
    }
}
