package kotlinx.coroutines.internal;

import android.R;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class LockFreeTaskQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    public static final int CAPACITY_BITS = 30;
    public static final long CLOSED_MASK = 2305843009213693952L;
    public static final int CLOSED_SHIFT = 61;
    public static final long FROZEN_MASK = 1152921504606846976L;
    public static final int FROZEN_SHIFT = 60;
    public static final long HEAD_MASK = 1073741823;
    public static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    public static final int MAX_CAPACITY_MASK = 1073741823;
    public static final int MIN_ADD_SPIN_CAPACITY = 1024;
    public static final long TAIL_MASK = 1152921503533105152L;
    public static final int TAIL_SHIFT = 30;

    @NotNull
    private volatile /* synthetic */ Object _next = null;

    @NotNull
    private volatile /* synthetic */ long _state = 0;

    @NotNull
    public /* synthetic */ AtomicReferenceArray array;
    public final int capacity;
    public final int mask;
    public final boolean singleConsumer;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");
    public static final /* synthetic */ AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final int addFailReason(long j) {
            return (j & LockFreeTaskQueueCore.CLOSED_MASK) != 0 ? 2 : 1;
        }

        public final long updateHead(long j, int i) {
            return (j & (-1073741824)) | ((long) i);
        }

        public final long updateTail(long j, int i) {
            return (j & (-1152921503533105153L)) | (((long) i) << 30);
        }

        public final <T> T withState(long j, @NotNull Function2<? super Integer, ? super Integer, ? extends T> function2) {
            return function2.invoke(Integer.valueOf((int) (LockFreeTaskQueueCore.HEAD_MASK & j)), Integer.valueOf((int) ((j & LockFreeTaskQueueCore.TAIL_MASK) >> 30)));
        }

        public final long wo(long j, long j2) {
            return j & (~j2);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Placeholder {

        @JvmField
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    public LockFreeTaskQueueCore(int i, boolean z) {
        this.capacity = i;
        this.singleConsumer = z;
        int i2 = i - 1;
        this.mask = i2;
        this.array = new AtomicReferenceArray(i);
        if (i2 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i & i2) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int addLast(@org.jetbrains.annotations.NotNull E r12) {
        /*
            r11 = this;
        L0:
            long r2 = r11._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r6 = 0
            int r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r4 == 0) goto L12
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r12 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.Companion
            int r12 = r12.addFailReason(r2)
            return r12
        L12:
            r0 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r0 = r0 & r2
            int r1 = (int) r0
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r0 = 30
            long r4 = r4 >> r0
            int r8 = (int) r4
            int r9 = r11.mask
            int r0 = r8 + 2
            r0 = r0 & r9
            r4 = r1 & r9
            r5 = 1
            if (r0 != r4) goto L2c
            return r5
        L2c:
            boolean r0 = r11.singleConsumer
            r4 = 1073741823(0x3fffffff, float:1.9999999)
            if (r0 != 0) goto L4e
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r11.array
            r10 = r8 & r9
            java.lang.Object r0 = r0.get(r10)
            if (r0 == 0) goto L4e
            int r0 = r11.capacity
            r2 = 1024(0x400, float:1.435E-42)
            if (r0 < r2) goto L4d
            int r8 = r8 - r1
            r1 = r8 & r4
            int r0 = r0 >> 1
            if (r1 <= r0) goto L4b
            goto L4d
        L4b:
            r1 = r11
            goto L0
        L4d:
            return r5
        L4e:
            int r0 = r8 + 1
            r0 = r0 & r4
            r1 = r0
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.internal.LockFreeTaskQueueCore._state$FU
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r4 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.Companion
            long r4 = r4.updateTail(r2, r1)
            r1 = r11
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L0
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r1.array
            r2 = r8 & r9
            r0.set(r2, r12)
            r0 = r1
        L69:
            long r2 = r0._state
            r4 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r2 = r2 & r4
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 == 0) goto L7c
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r0 = r0.next()
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r0 = r0.fillPlaceholder(r8, r12)
            if (r0 != 0) goto L69
        L7c:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeTaskQueueCore.addLast(java.lang.Object):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final LockFreeTaskQueueCore<E> allocateNextCopy(long j) {
        LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = new LockFreeTaskQueueCore<>(this.capacity * 2, this.singleConsumer);
        int i = (int) (HEAD_MASK & j);
        int i2 = (int) ((TAIL_MASK & j) >> 30);
        while (true) {
            int i3 = this.mask;
            if ((i & i3) == (i2 & i3)) {
                Companion.getClass();
                lockFreeTaskQueueCore._state = j & (-1152921504606846977L);
                return lockFreeTaskQueueCore;
            }
            Object placeholder = this.array.get(i3 & i);
            if (placeholder == null) {
                placeholder = new Placeholder(i);
            }
            lockFreeTaskQueueCore.array.set(lockFreeTaskQueueCore.mask & i, placeholder);
            i++;
        }
    }

    public final LockFreeTaskQueueCore<E> allocateOrGetNextCopy(long j) {
        while (true) {
            LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._next;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, null, allocateNextCopy(j));
        }
    }

    public final boolean close() {
        long j;
        do {
            j = this._state;
            if ((j & CLOSED_MASK) != 0) {
                return true;
            }
            if ((FROZEN_MASK & j) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j, j | CLOSED_MASK));
        return true;
    }

    public final LockFreeTaskQueueCore<E> fillPlaceholder(int i, E e) {
        Object obj = this.array.get(this.mask & i);
        if (!(obj instanceof Placeholder) || ((Placeholder) obj).index != i) {
            return null;
        }
        this.array.set(i & this.mask, e);
        return this;
    }

    public final int getSize() {
        long j = this._state;
        return 1073741823 & (((int) ((j & TAIL_MASK) >> 30)) - ((int) (HEAD_MASK & j)));
    }

    public final boolean isClosed() {
        return (this._state & CLOSED_MASK) != 0;
    }

    public final boolean isEmpty() {
        long j = this._state;
        return ((int) (HEAD_MASK & j)) == ((int) ((j & TAIL_MASK) >> 30));
    }

    @NotNull
    public final <R> List<R> map(@NotNull Function1<? super E, ? extends R> function1) {
        ArrayList arrayList = new ArrayList(this.capacity);
        long j = this._state;
        int i = (int) (HEAD_MASK & j);
        int i2 = (int) ((j & TAIL_MASK) >> 30);
        while (true) {
            int i3 = this.mask;
            if ((i & i3) == (i2 & i3)) {
                return arrayList;
            }
            R.bool boolVar = (Object) this.array.get(i3 & i);
            if (boolVar != null && !(boolVar instanceof Placeholder)) {
                arrayList.add(function1.invoke(boolVar));
            }
            i++;
        }
    }

    public final long markFrozen() {
        long j;
        long j2;
        do {
            j = this._state;
            if ((j & FROZEN_MASK) != 0) {
                return j;
            }
            j2 = j | FROZEN_MASK;
        } while (!_state$FU.compareAndSet(this, j, j2));
        return j2;
    }

    @NotNull
    public final LockFreeTaskQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    @Nullable
    public final Object removeFirstOrNull() {
        while (true) {
            long j = this._state;
            if ((FROZEN_MASK & j) != 0) {
                return REMOVE_FROZEN;
            }
            int i = (int) (HEAD_MASK & j);
            int i2 = (int) ((TAIL_MASK & j) >> 30);
            int i3 = this.mask;
            if ((i2 & i3) == (i & i3)) {
                return null;
            }
            Object obj = this.array.get(i3 & i);
            if (obj == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else {
                if (obj instanceof Placeholder) {
                    return null;
                }
                int i4 = (i + 1) & 1073741823;
                if (_state$FU.compareAndSet(this, j, Companion.updateHead(j, i4))) {
                    this.array.set(this.mask & i, null);
                    return obj;
                }
                if (this.singleConsumer) {
                    LockFreeTaskQueueCore<E> lockFreeTaskQueueCoreRemoveSlowPath = this;
                    do {
                        lockFreeTaskQueueCoreRemoveSlowPath = lockFreeTaskQueueCoreRemoveSlowPath.removeSlowPath(i, i4);
                    } while (lockFreeTaskQueueCoreRemoveSlowPath != null);
                    return obj;
                }
            }
        }
    }

    public final LockFreeTaskQueueCore<E> removeSlowPath(int i, int i2) {
        long j;
        int i3;
        do {
            j = this._state;
            i3 = (int) (HEAD_MASK & j);
            if ((FROZEN_MASK & j) != 0) {
                return next();
            }
        } while (!_state$FU.compareAndSet(this, j, Companion.updateHead(j, i2)));
        this.array.set(i3 & this.mask, null);
        return null;
    }
}
