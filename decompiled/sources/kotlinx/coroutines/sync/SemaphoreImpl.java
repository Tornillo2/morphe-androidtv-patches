package kotlinx.coroutines.sync;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SemaphoreImpl implements Semaphore {

    @NotNull
    volatile /* synthetic */ int _availablePermits;

    @NotNull
    private volatile /* synthetic */ long deqIdx = 0;

    @NotNull
    private volatile /* synthetic */ long enqIdx = 0;

    @NotNull
    private volatile /* synthetic */ Object head;

    @NotNull
    public final Function1<Throwable, Unit> onCancellationRelease;
    public final int permits;

    @NotNull
    private volatile /* synthetic */ Object tail;
    public static final /* synthetic */ AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
    public static final /* synthetic */ AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    public static final /* synthetic */ AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    public static final /* synthetic */ AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    public static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        if (i <= 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Semaphore should have at least 1 permit, but had ", i).toString());
        }
        if (i2 < 0 || i2 > i) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("The number of acquired permits should be in 0..", i).toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment;
        this.tail = semaphoreSegment;
        this._availablePermits = i - i2;
        this.onCancellationRelease = new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Throwable th) {
                this.this$0.release();
            }
        };
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    @Nullable
    public Object acquire(@NotNull Continuation<? super Unit> continuation) {
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object objAcquireSlowPath = acquireSlowPath(continuation);
        return objAcquireSlowPath == CoroutineSingletons.COROUTINE_SUSPENDED ? objAcquireSlowPath : Unit.INSTANCE;
    }

    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        while (true) {
            if (addAcquireToQueue(orCreateCancellableContinuation)) {
                break;
            }
            if (_availablePermits$FU.getAndDecrement(this) > 0) {
                orCreateCancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode, kotlinx.coroutines.internal.Segment] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2 */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cancellableContinuation) {
        ?? r5;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail;
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        long j = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        loop0: while (true) {
            r5 = semaphoreSegment;
            while (true) {
                if (r5.id >= j && !r5.getRemoved()) {
                    break;
                }
                Object obj = ((ConcurrentLinkedListNode) r5)._next;
                Symbol symbol = ConcurrentLinkedListKt.CLOSED;
                if (obj == symbol) {
                    r5 = symbol;
                    break;
                }
                ConcurrentLinkedListNode concurrentLinkedListNodeCreateSegment = (Segment) ((ConcurrentLinkedListNode) obj);
                if (concurrentLinkedListNodeCreateSegment == null) {
                    concurrentLinkedListNodeCreateSegment = SemaphoreKt.createSegment(r5.id + 1, (SemaphoreSegment) r5);
                    if (r5.trySetNext(concurrentLinkedListNodeCreateSegment)) {
                        if (r5.getRemoved()) {
                            r5.remove();
                        }
                    }
                }
                r5 = concurrentLinkedListNodeCreateSegment;
            }
            if (!SegmentOrClosed.m2138isClosedimpl(r5)) {
                Segment segmentM2136getSegmentimpl = SegmentOrClosed.m2136getSegmentimpl(r5);
                while (true) {
                    Segment segment = (Segment) this.tail;
                    if (segment.id >= segmentM2136getSegmentimpl.id) {
                        break loop0;
                    }
                    if (!segmentM2136getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                        break;
                    }
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(tail$FU, this, segment, segmentM2136getSegmentimpl)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (segmentM2136getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                        segmentM2136getSegmentimpl.remove();
                    }
                }
            } else {
                break;
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m2136getSegmentimpl(r5);
        int i = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.acquirers, i, null, cancellableContinuation)) {
            cancellableContinuation.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler(semaphoreSegment2, i));
            return true;
        }
        if (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.acquirers, i, SemaphoreKt.PERMIT, SemaphoreKt.TAKEN)) {
            return false;
        }
        cancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
        return true;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        while (true) {
            int i = this._availablePermits;
            if (i >= this.permits) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
            if (_availablePermits$FU.compareAndSet(this, i, i + 1) && (i >= 0 || tryResumeNextFromQueue())) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int i;
        do {
            i = this._availablePermits;
            if (i <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i - 1));
        return true;
    }

    public final boolean tryResumeAcquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        Object objTryResume = cancellableContinuation.tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
        if (objTryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(objTryResume);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode, kotlinx.coroutines.internal.Segment] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2 */
    public final boolean tryResumeNextFromQueue() {
        ?? r5;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.head;
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        long j = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        loop0: while (true) {
            r5 = semaphoreSegment;
            while (true) {
                if (r5.id >= j && !r5.getRemoved()) {
                    break;
                }
                Object obj = ((ConcurrentLinkedListNode) r5)._next;
                Symbol symbol = ConcurrentLinkedListKt.CLOSED;
                if (obj == symbol) {
                    r5 = symbol;
                    break;
                }
                ConcurrentLinkedListNode concurrentLinkedListNodeCreateSegment = (Segment) ((ConcurrentLinkedListNode) obj);
                if (concurrentLinkedListNodeCreateSegment == null) {
                    concurrentLinkedListNodeCreateSegment = SemaphoreKt.createSegment(r5.id + 1, (SemaphoreSegment) r5);
                    if (r5.trySetNext(concurrentLinkedListNodeCreateSegment)) {
                        if (r5.getRemoved()) {
                            r5.remove();
                        }
                    }
                }
                r5 = concurrentLinkedListNodeCreateSegment;
            }
            if (SegmentOrClosed.m2138isClosedimpl(r5)) {
                break;
            }
            Segment segmentM2136getSegmentimpl = SegmentOrClosed.m2136getSegmentimpl(r5);
            while (true) {
                Segment segment = (Segment) this.head;
                if (segment.id >= segmentM2136getSegmentimpl.id) {
                    break loop0;
                }
                if (!segmentM2136getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(head$FU, this, segment, segmentM2136getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (segmentM2136getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    segmentM2136getSegmentimpl.remove();
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m2136getSegmentimpl(r5);
        semaphoreSegment2.cleanPrev();
        if (semaphoreSegment2.id > j) {
            return false;
        }
        int i = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        Object andSet = semaphoreSegment2.acquirers.getAndSet(i, SemaphoreKt.PERMIT);
        if (andSet != null) {
            if (andSet == SemaphoreKt.CANCELLED) {
                return false;
            }
            return tryResumeAcquire((CancellableContinuation) andSet);
        }
        int i2 = SemaphoreKt.MAX_SPIN_CYCLES;
        for (int i3 = 0; i3 < i2; i3++) {
            if (semaphoreSegment2.acquirers.get(i) == SemaphoreKt.TAKEN) {
                return true;
            }
        }
        return !Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.acquirers, i, SemaphoreKt.PERMIT, SemaphoreKt.BROKEN);
    }
}
