package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");

    @NotNull
    volatile /* synthetic */ Object _state;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class LockCont extends LockWaiter {

        @NotNull
        public final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public LockCont(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            super(obj);
            this.cont = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter() {
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockCont[" + this.owner + ", " + this.cont + "] for " + MutexImpl.this;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean tryResumeLockWaiter() {
            if (!take()) {
                return false;
            }
            CancellableContinuation<Unit> cancellableContinuation = this.cont;
            Unit unit = Unit.INSTANCE;
            final MutexImpl mutexImpl = MutexImpl.this;
            return cancellableContinuation.tryResume(unit, null, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockCont$tryResumeLockWaiter$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    mutexImpl.unlock(this.owner);
                }
            }) != null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class LockSelect<R> extends LockWaiter {

        @JvmField
        @NotNull
        public final Function2<Mutex, Continuation<? super R>, Object> block;

        @JvmField
        @NotNull
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public LockSelect(@Nullable Object obj, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            super(obj);
            this.select = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter() {
            Function2<Mutex, Continuation<? super R>, Object> function2 = this.block;
            MutexImpl mutexImpl = MutexImpl.this;
            Continuation<R> completion = this.select.getCompletion();
            final MutexImpl mutexImpl2 = MutexImpl.this;
            CancellableKt.startCoroutineCancellable(function2, mutexImpl, completion, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockSelect$completeResumeLockWaiter$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    mutexImpl2.unlock(this.owner);
                }
            });
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockSelect[" + this.owner + ", " + this.select + "] for " + MutexImpl.this;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean tryResumeLockWaiter() {
            return take() && this.select.trySelect();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        public static final /* synthetic */ AtomicIntegerFieldUpdater isTaken$FU = AtomicIntegerFieldUpdater.newUpdater(LockWaiter.class, "isTaken");

        @NotNull
        private volatile /* synthetic */ int isTaken = 0;

        @JvmField
        @Nullable
        public final Object owner;

        public LockWaiter(@Nullable Object obj) {
            this.owner = obj;
        }

        public abstract void completeResumeLockWaiter();

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            mo2129remove();
        }

        public final boolean take() {
            return isTaken$FU.compareAndSet(this, 0, 1);
        }

        public abstract boolean tryResumeLockWaiter();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LockedQueue extends LockFreeLinkedListHead {

        @JvmField
        @NotNull
        public volatile Object owner;

        public LockedQueue(@NotNull Object obj) {
            this.owner = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockedQueue[" + this.owner + AbstractJsonLexerKt.END_LIST;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TryLockDesc extends AtomicDesc {

        @JvmField
        @NotNull
        public final MutexImpl mutex;

        @JvmField
        @Nullable
        public final Object owner;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class PrepareOp extends OpDescriptor {

            @NotNull
            public final AtomicOp<?> atomicOp;

            public PrepareOp(@NotNull AtomicOp<?> atomicOp) {
                this.atomicOp = atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            @NotNull
            public AtomicOp<?> getAtomicOp() {
                return this.atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            @Nullable
            public Object perform(@Nullable Object obj) {
                Object obj2 = this.atomicOp.isDecided() ? MutexKt.EMPTY_UNLOCKED : this.atomicOp;
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
                }
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, (MutexImpl) obj, this, obj2);
                return null;
            }
        }

        public TryLockDesc(@NotNull MutexImpl mutexImpl, @Nullable Object obj) {
            this.mutex = mutexImpl;
            this.owner = obj;
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj) {
            Empty empty;
            if (obj != null) {
                empty = MutexKt.EMPTY_UNLOCKED;
            } else {
                Object obj2 = this.owner;
                empty = obj2 == null ? MutexKt.EMPTY_LOCKED : new Empty(obj2);
            }
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, this.mutex, atomicOp, empty);
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        @Nullable
        public Object prepare(@NotNull AtomicOp<?> atomicOp) {
            PrepareOp prepareOp = new PrepareOp(atomicOp);
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, this.mutex, MutexKt.EMPTY_UNLOCKED, prepareOp)) {
                return MutexKt.LOCK_FAIL;
            }
            prepareOp.perform(this.mutex);
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnlockOp extends AtomicOp<MutexImpl> {

        @JvmField
        @NotNull
        public final LockedQueue queue;

        public UnlockOp(@NotNull LockedQueue lockedQueue) {
            this.queue = lockedQueue;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(@NotNull MutexImpl mutexImpl, @Nullable Object obj) {
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, mutexImpl, this, obj == null ? MutexKt.EMPTY_UNLOCKED : this.queue);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        @Nullable
        public Object prepare(@NotNull MutexImpl mutexImpl) {
            if (this.queue.isEmpty()) {
                return null;
            }
            return MutexKt.UNLOCK_FAIL;
        }
    }

    public MutexImpl(boolean z) {
        this._state = z ? MutexKt.EMPTY_LOCKED : MutexKt.EMPTY_UNLOCKED;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(@NotNull Object obj) {
        Object obj2 = this._state;
        return obj2 instanceof Empty ? ((Empty) obj2).locked == obj : (obj2 instanceof LockedQueue) && ((LockedQueue) obj2).owner == obj;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                return ((Empty) obj).locked != MutexKt.UNLOCKED;
            }
            if (obj instanceof LockedQueue) {
                return true;
            }
            if (!(obj instanceof OpDescriptor)) {
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Illegal state ", obj));
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core() {
        Object obj = this._state;
        return (obj instanceof LockedQueue) && ((LockedQueue) obj).isEmpty();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    @Nullable
    public Object lock(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        if (tryLock(obj)) {
            return Unit.INSTANCE;
        }
        Object objLockSuspend = lockSuspend(obj, continuation);
        return objLockSuspend == CoroutineSingletons.COROUTINE_SUSPENDED ? objLockSuspend : Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0065, code lost:
    
        kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r7, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0068, code lost:
    
        r6 = r7.getResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006e, code lost:
    
        if (r6 != kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0070, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object lockSuspend(final java.lang.Object r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            kotlin.coroutines.Continuation r7 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r7)
            kotlinx.coroutines.CancellableContinuationImpl r7 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r7)
            kotlinx.coroutines.sync.MutexImpl$LockCont r0 = new kotlinx.coroutines.sync.MutexImpl$LockCont
            r0.<init>(r6, r7)
        Ld:
            java.lang.Object r1 = r5._state
            boolean r2 = r1 instanceof kotlinx.coroutines.sync.Empty
            if (r2 == 0) goto L46
            r2 = r1
            kotlinx.coroutines.sync.Empty r2 = (kotlinx.coroutines.sync.Empty) r2
            java.lang.Object r3 = r2.locked
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.sync.MutexKt.UNLOCKED
            if (r3 == r4) goto L29
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = kotlinx.coroutines.sync.MutexImpl._state$FU
            kotlinx.coroutines.sync.MutexImpl$LockedQueue r4 = new kotlinx.coroutines.sync.MutexImpl$LockedQueue
            java.lang.Object r2 = r2.locked
            r4.<init>(r2)
            androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r3, r5, r1, r4)
            goto Ld
        L29:
            if (r6 != 0) goto L2e
            kotlinx.coroutines.sync.Empty r2 = kotlinx.coroutines.sync.MutexKt.EMPTY_LOCKED
            goto L33
        L2e:
            kotlinx.coroutines.sync.Empty r2 = new kotlinx.coroutines.sync.Empty
            r2.<init>(r6)
        L33:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = kotlinx.coroutines.sync.MutexImpl._state$FU
            boolean r1 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r3, r5, r1, r2)
            if (r1 == 0) goto Ld
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlinx.coroutines.sync.MutexImpl$lockSuspend$2$1$1 r1 = new kotlinx.coroutines.sync.MutexImpl$lockSuspend$2$1$1
            r1.<init>()
            r7.resume(r0, r1)
            goto L68
        L46:
            boolean r2 = r1 instanceof kotlinx.coroutines.sync.MutexImpl.LockedQueue
            if (r2 == 0) goto L84
            r2 = r1
            kotlinx.coroutines.sync.MutexImpl$LockedQueue r2 = (kotlinx.coroutines.sync.MutexImpl.LockedQueue) r2
            java.lang.Object r3 = r2.owner
            if (r3 == r6) goto L74
            r2.addLast(r0)
            java.lang.Object r2 = r5._state
            if (r2 == r1) goto L65
            boolean r1 = r0.take()
            if (r1 != 0) goto L5f
            goto L65
        L5f:
            kotlinx.coroutines.sync.MutexImpl$LockCont r0 = new kotlinx.coroutines.sync.MutexImpl$LockCont
            r0.<init>(r6, r7)
            goto Ld
        L65:
            kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r7, r0)
        L68:
            java.lang.Object r6 = r7.getResult()
            kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r6 != r7) goto L71
            return r6
        L71:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L74:
            java.lang.String r7 = "Already locked by "
            java.lang.String r6 = com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m(r7, r6)
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L84:
            boolean r2 = r1 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r2 == 0) goto L8f
            kotlinx.coroutines.internal.OpDescriptor r1 = (kotlinx.coroutines.internal.OpDescriptor) r1
            r1.perform(r5)
            goto Ld
        L8f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Illegal state "
            java.lang.String r7 = kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0.m(r7, r1)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexImpl.lockSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, @Nullable Object obj, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                Empty empty = (Empty) obj2;
                if (empty.locked != MutexKt.UNLOCKED) {
                    AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, new LockedQueue(empty.locked));
                } else {
                    Object objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(new TryLockDesc(this, obj));
                    if (objPerformAtomicTrySelect == null) {
                        UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                        return;
                    } else {
                        if (objPerformAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                            return;
                        }
                        if (objPerformAtomicTrySelect != MutexKt.LOCK_FAIL && objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                            throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("performAtomicTrySelect(TryLockDesc) returned ", objPerformAtomicTrySelect));
                        }
                    }
                }
            } else if (obj2 instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj2;
                if (lockedQueue.owner == obj) {
                    throw new IllegalStateException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Already locked by ", obj).toString());
                }
                LockSelect lockSelect = new LockSelect(obj, selectInstance, function2);
                lockedQueue.addLast(lockSelect);
                if (this._state == obj2 || !lockSelect.take()) {
                    selectInstance.disposeOnSelect(lockSelect);
                    return;
                }
            } else {
                if (!(obj2 instanceof OpDescriptor)) {
                    throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Illegal state ", obj2));
                }
                ((OpDescriptor) obj2).perform(this);
            }
        }
    }

    @NotNull
    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                return "Mutex[" + ((Empty) obj).locked + AbstractJsonLexerKt.END_LIST;
            }
            if (!(obj instanceof OpDescriptor)) {
                if (!(obj instanceof LockedQueue)) {
                    throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Illegal state ", obj));
                }
                return "Mutex[" + ((LockedQueue) obj).owner + AbstractJsonLexerKt.END_LIST;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(@Nullable Object obj) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                if (((Empty) obj2).locked != MutexKt.UNLOCKED) {
                    return false;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, obj == null ? MutexKt.EMPTY_LOCKED : new Empty(obj))) {
                    return true;
                }
            } else {
                if (obj2 instanceof LockedQueue) {
                    if (((LockedQueue) obj2).owner != obj) {
                        return false;
                    }
                    throw new IllegalStateException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Already locked by ", obj).toString());
                }
                if (!(obj2 instanceof OpDescriptor)) {
                    throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Illegal state ", obj2));
                }
                ((OpDescriptor) obj2).perform(this);
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(@Nullable Object obj) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                if (obj != null) {
                    Empty empty = (Empty) obj2;
                    if (empty.locked != obj) {
                        throw new IllegalStateException(("Mutex is locked by " + empty.locked + " but expected " + obj).toString());
                    }
                } else if (((Empty) obj2).locked == MutexKt.UNLOCKED) {
                    throw new IllegalStateException("Mutex is not locked");
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, MutexKt.EMPTY_UNLOCKED)) {
                    return;
                }
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else {
                if (!(obj2 instanceof LockedQueue)) {
                    throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Illegal state ", obj2));
                }
                if (obj != null) {
                    LockedQueue lockedQueue = (LockedQueue) obj2;
                    if (lockedQueue.owner != obj) {
                        throw new IllegalStateException(("Mutex is locked by " + lockedQueue.owner + " but expected " + obj).toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) obj2;
                LockFreeLinkedListNode lockFreeLinkedListNodeRemoveFirstOrNull = lockedQueue2.removeFirstOrNull();
                if (lockFreeLinkedListNodeRemoveFirstOrNull == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) lockFreeLinkedListNodeRemoveFirstOrNull;
                    if (lockWaiter.tryResumeLockWaiter()) {
                        Object obj3 = lockWaiter.owner;
                        if (obj3 == null) {
                            obj3 = MutexKt.LOCKED;
                        }
                        lockedQueue2.owner = obj3;
                        lockWaiter.completeResumeLockWaiter();
                        return;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    @NotNull
    public SelectClause2<Object, Mutex> getOnLock() {
        return this;
    }
}
