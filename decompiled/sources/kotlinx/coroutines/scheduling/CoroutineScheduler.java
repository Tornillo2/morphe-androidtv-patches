package kotlinx.coroutines.scheduling;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.amazon.ion.impl.bin.WriteBuffer;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    public static final long BLOCKING_MASK = 4398044413952L;
    public static final int BLOCKING_SHIFT = 21;
    public static final int CLAIMED = 0;
    public static final long CPU_PERMITS_MASK = 9223367638808264704L;
    public static final int CPU_PERMITS_SHIFT = 42;
    public static final long CREATED_MASK = 2097151;
    public static final int MAX_SUPPORTED_POOL_SIZE = 2097150;
    public static final int MIN_SUPPORTED_POOL_SIZE = 1;
    public static final int PARKED = -1;
    public static final long PARKED_INDEX_MASK = 2097151;
    public static final long PARKED_VERSION_INC = 2097152;
    public static final long PARKED_VERSION_MASK = -2097152;
    public static final int TERMINATED = 1;

    @NotNull
    private volatile /* synthetic */ int _isTerminated;

    @NotNull
    volatile /* synthetic */ long controlState;

    @JvmField
    public final int corePoolSize;

    @JvmField
    @NotNull
    public final GlobalQueue globalBlockingQueue;

    @JvmField
    @NotNull
    public final GlobalQueue globalCpuQueue;

    @JvmField
    public final long idleWorkerKeepAliveNs;

    @JvmField
    public final int maxPoolSize;

    @NotNull
    private volatile /* synthetic */ long parkedWorkersStack;

    @JvmField
    @NotNull
    public final String schedulerName;

    @JvmField
    @NotNull
    public final ResizableAtomicArray<Worker> workers;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    public static final /* synthetic */ AtomicLongFieldUpdater parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
    public static final /* synthetic */ AtomicLongFieldUpdater controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
    public static final /* synthetic */ AtomicIntegerFieldUpdater _isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WorkerState.values().length];
            iArr[WorkerState.PARKING.ordinal()] = 1;
            iArr[WorkerState.BLOCKING.ordinal()] = 2;
            iArr[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            iArr[WorkerState.DORMANT.ordinal()] = 4;
            iArr[WorkerState.TERMINATED.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int i, int i2, long j, @NotNull String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if (i < 1) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Core pool size ", i, " should be at least 1").toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Max pool size ", i2, " should be greater than or equals to core pool size ", i).toString());
        }
        if (i2 > 2097150) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Max pool size ", i2, " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j <= 0) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Idle worker keep alive time ", j, " must be positive").toString());
        }
        this.globalCpuQueue = new GlobalQueue(false);
        this.globalBlockingQueue = new GlobalQueue(false);
        this.parkedWorkersStack = 0L;
        this.workers = new ResizableAtomicArray<>(i + 1);
        this.controlState = ((long) i) << 42;
        this._isTerminated = 0;
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            taskContext = TasksKt.NonBlockingContext;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        coroutineScheduler.dispatch(runnable, taskContext, z);
    }

    public static /* synthetic */ boolean tryCreateWorker$default(CoroutineScheduler coroutineScheduler, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineScheduler.controlState;
        }
        return coroutineScheduler.tryCreateWorker(j);
    }

    public final boolean addToGlobalQueue(Task task) {
        return task.taskContext.getTaskMode() == 1 ? this.globalBlockingQueue.addLast(task) : this.globalCpuQueue.addLast(task);
    }

    public final int availableCpuPermits(long j) {
        return (int) ((j & CPU_PERMITS_MASK) >> 42);
    }

    public final int blockingTasks(long j) {
        return (int) ((j & BLOCKING_MASK) >> 21);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws InterruptedException {
        shutdown(10000L);
    }

    public final int createNewWorker() {
        synchronized (this.workers) {
            if (this._isTerminated != 0) {
                return -1;
            }
            long j = this.controlState;
            int i = (int) (j & 2097151);
            int i2 = i - ((int) ((j & BLOCKING_MASK) >> 21));
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 >= this.corePoolSize) {
                return 0;
            }
            if (i >= this.maxPoolSize) {
                return 0;
            }
            int i3 = ((int) (this.controlState & 2097151)) + 1;
            if (i3 <= 0 || this.workers.get(i3) != null) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            Worker worker = new Worker(this, i3);
            this.workers.setSynchronized(i3, worker);
            if (i3 != ((int) (2097151 & controlState$FU.incrementAndGet(this)))) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            worker.start();
            return i2 + 1;
        }
    }

    @NotNull
    public final Task createTask(@NotNull Runnable runnable, @NotNull TaskContext taskContext) {
        long jNanoTime = TasksKt.schedulerTimeSource.nanoTime();
        if (!(runnable instanceof Task)) {
            return new TaskImpl(runnable, jNanoTime, taskContext);
        }
        Task task = (Task) runnable;
        task.submissionTime = jNanoTime;
        task.taskContext = taskContext;
        return task;
    }

    public final int createdWorkers(long j) {
        return (int) (j & 2097151);
    }

    public final Worker currentWorker() {
        Thread threadCurrentThread = Thread.currentThread();
        Worker worker = threadCurrentThread instanceof Worker ? (Worker) threadCurrentThread : null;
        if (worker == null || !Intrinsics.areEqual(CoroutineScheduler.this, this)) {
            return null;
        }
        return worker;
    }

    public final void decrementBlockingTasks() {
        controlState$FU.addAndGet(this, PARKED_VERSION_MASK);
    }

    public final int decrementCreatedWorkers() {
        return (int) (controlState$FU.getAndDecrement(this) & 2097151);
    }

    public final void dispatch(@NotNull Runnable runnable, @NotNull TaskContext taskContext, boolean z) {
        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
        if (abstractTimeSource != null) {
            abstractTimeSource.trackTask();
        }
        Task taskCreateTask = createTask(runnable, taskContext);
        Worker workerCurrentWorker = currentWorker();
        Task taskSubmitToLocalQueue = submitToLocalQueue(workerCurrentWorker, taskCreateTask, z);
        if (taskSubmitToLocalQueue != null && !addToGlobalQueue(taskSubmitToLocalQueue)) {
            throw new RejectedExecutionException(ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.schedulerName, " was terminated"));
        }
        boolean z2 = z && workerCurrentWorker != null;
        if (taskCreateTask.taskContext.getTaskMode() != 0) {
            signalBlockingWork(z2);
        } else {
            if (z2) {
                return;
            }
            signalCpuWork();
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable runnable) {
        dispatch$default(this, runnable, null, false, 6, null);
    }

    public final int getAvailableCpuPermits() {
        return (int) ((this.controlState & CPU_PERMITS_MASK) >> 42);
    }

    public final int getCreatedWorkers() {
        return (int) (this.controlState & 2097151);
    }

    public final long incrementBlockingTasks() {
        return controlState$FU.addAndGet(this, 2097152L);
    }

    public final int incrementCreatedWorkers() {
        return (int) (controlState$FU.incrementAndGet(this) & 2097151);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean isTerminated() {
        return this._isTerminated;
    }

    public final int parkedWorkersStackNextIndex(Worker worker) {
        Object nextParkedWorker = worker.getNextParkedWorker();
        while (nextParkedWorker != NOT_IN_STACK) {
            if (nextParkedWorker == null) {
                return 0;
            }
            Worker worker2 = (Worker) nextParkedWorker;
            int indexInArray = worker2.getIndexInArray();
            if (indexInArray != 0) {
                return indexInArray;
            }
            nextParkedWorker = worker2.getNextParkedWorker();
        }
        return -1;
    }

    public final Worker parkedWorkersStackPop() {
        while (true) {
            long j = this.parkedWorkersStack;
            Worker worker = this.workers.get((int) (2097151 & j));
            if (worker == null) {
                return null;
            }
            long j2 = (2097152 + j) & PARKED_VERSION_MASK;
            int iParkedWorkersStackNextIndex = parkedWorkersStackNextIndex(worker);
            if (iParkedWorkersStackNextIndex >= 0 && parkedWorkersStack$FU.compareAndSet(this, j, ((long) iParkedWorkersStackNextIndex) | j2)) {
                worker.setNextParkedWorker(NOT_IN_STACK);
                return worker;
            }
        }
    }

    public final boolean parkedWorkersStackPush(@NotNull Worker worker) {
        long j;
        long j2;
        int indexInArray;
        if (worker.getNextParkedWorker() != NOT_IN_STACK) {
            return false;
        }
        do {
            j = this.parkedWorkersStack;
            j2 = (2097152 + j) & PARKED_VERSION_MASK;
            indexInArray = worker.getIndexInArray();
            worker.setNextParkedWorker(this.workers.get((int) (2097151 & j)));
        } while (!parkedWorkersStack$FU.compareAndSet(this, j, j2 | ((long) indexInArray)));
        return true;
    }

    public final void parkedWorkersStackTopUpdate(@NotNull Worker worker, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack;
            int iParkedWorkersStackNextIndex = (int) (2097151 & j);
            long j2 = (2097152 + j) & PARKED_VERSION_MASK;
            if (iParkedWorkersStackNextIndex == i) {
                iParkedWorkersStackNextIndex = i2 == 0 ? parkedWorkersStackNextIndex(worker) : i2;
            }
            if (iParkedWorkersStackNextIndex >= 0 && parkedWorkersStack$FU.compareAndSet(this, j, j2 | ((long) iParkedWorkersStackNextIndex))) {
                return;
            }
        }
    }

    public final long releaseCpuPermit() {
        return controlState$FU.addAndGet(this, WriteBuffer.VAR_UINT_7_OCTET_MIN_VALUE);
    }

    public final void runSafely(@NotNull Task task) {
        try {
            task.run();
        } catch (Throwable th) {
            try {
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
                if (abstractTimeSource != null) {
                    abstractTimeSource.unTrackTask();
                }
            } finally {
                AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.timeSource;
                if (abstractTimeSource2 != null) {
                    abstractTimeSource2.unTrackTask();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void shutdown(long r8) throws java.lang.InterruptedException {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler._isTerminated$FU
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r7, r1, r2)
            if (r0 != 0) goto Lb
            return
        Lb:
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r0 = r7.currentWorker()
            kotlinx.coroutines.internal.ResizableAtomicArray<kotlinx.coroutines.scheduling.CoroutineScheduler$Worker> r1 = r7.workers
            monitor-enter(r1)
            long r3 = r7.controlState     // Catch: java.lang.Throwable -> L7b
            r5 = 2097151(0x1fffff, double:1.0361303E-317)
            long r3 = r3 & r5
            int r4 = (int) r3
            monitor-exit(r1)
            if (r2 > r4) goto L43
            r1 = 1
        L1d:
            kotlinx.coroutines.internal.ResizableAtomicArray<kotlinx.coroutines.scheduling.CoroutineScheduler$Worker> r3 = r7.workers
            java.lang.Object r3 = r3.get(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r3 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r3
            if (r3 == r0) goto L3e
        L2a:
            boolean r5 = r3.isAlive()
            if (r5 == 0) goto L37
            java.util.concurrent.locks.LockSupport.unpark(r3)
            r3.join(r8)
            goto L2a
        L37:
            kotlinx.coroutines.scheduling.WorkQueue r3 = r3.localQueue
            kotlinx.coroutines.scheduling.GlobalQueue r5 = r7.globalBlockingQueue
            r3.offloadAllWorkTo(r5)
        L3e:
            if (r1 == r4) goto L43
            int r1 = r1 + 1
            goto L1d
        L43:
            kotlinx.coroutines.scheduling.GlobalQueue r8 = r7.globalBlockingQueue
            r8.close()
            kotlinx.coroutines.scheduling.GlobalQueue r8 = r7.globalCpuQueue
            r8.close()
        L4d:
            if (r0 == 0) goto L55
            kotlinx.coroutines.scheduling.Task r8 = r0.findTask(r2)
            if (r8 != 0) goto L77
        L55:
            kotlinx.coroutines.scheduling.GlobalQueue r8 = r7.globalCpuQueue
            java.lang.Object r8 = r8.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r8 = (kotlinx.coroutines.scheduling.Task) r8
            if (r8 != 0) goto L77
            kotlinx.coroutines.scheduling.GlobalQueue r8 = r7.globalBlockingQueue
            java.lang.Object r8 = r8.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r8 = (kotlinx.coroutines.scheduling.Task) r8
            if (r8 != 0) goto L77
            if (r0 == 0) goto L70
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r8 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.TERMINATED
            r0.tryReleaseCpu(r8)
        L70:
            r8 = 0
            r7.parkedWorkersStack = r8
            r7.controlState = r8
            return
        L77:
            r7.runSafely(r8)
            goto L4d
        L7b:
            r8 = move-exception
            monitor-exit(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.shutdown(long):void");
    }

    public final void signalBlockingWork(boolean z) {
        long jAddAndGet = controlState$FU.addAndGet(this, 2097152L);
        if (z || tryUnpark() || tryCreateWorker(jAddAndGet)) {
            return;
        }
        tryUnpark();
    }

    public final void signalCpuWork() {
        if (tryUnpark() || tryCreateWorker$default(this, 0L, 1, null)) {
            return;
        }
        tryUnpark();
    }

    public final Task submitToLocalQueue(Worker worker, Task task, boolean z) {
        if (worker == null || worker.state == WorkerState.TERMINATED) {
            return task;
        }
        if (task.taskContext.getTaskMode() == 0 && worker.state == WorkerState.BLOCKING) {
            return task;
        }
        worker.mayHaveLocalTasks = true;
        return worker.localQueue.add(task, z);
    }

    @NotNull
    public String toString() {
        ArrayList arrayList = new ArrayList();
        int iCurrentLength = this.workers.currentLength();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < iCurrentLength; i6++) {
            Worker worker = this.workers.get(i6);
            if (worker != null) {
                int size$kotlinx_coroutines_core = worker.localQueue.getSize$kotlinx_coroutines_core();
                int i7 = WhenMappings.$EnumSwitchMapping$0[worker.state.ordinal()];
                if (i7 == 1) {
                    i3++;
                } else if (i7 == 2) {
                    i2++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(size$kotlinx_coroutines_core);
                    sb.append('b');
                    arrayList.add(sb.toString());
                } else if (i7 == 3) {
                    i++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(size$kotlinx_coroutines_core);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (i7 == 4) {
                    i4++;
                    if (size$kotlinx_coroutines_core > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(size$kotlinx_coroutines_core);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (i7 == 5) {
                    i5++;
                }
            }
        }
        long j = this.controlState;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.schedulerName);
        sb4.append(ObjectUtils.AT_SIGN);
        sb4.append(DebugStringsKt.getHexAddress(this));
        sb4.append("[Pool Size {core = ");
        sb4.append(this.corePoolSize);
        sb4.append(", max = ");
        AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb4, this.maxPoolSize, "}, Worker States {CPU = ", i, ", blocking = ");
        AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb4, i2, ", parked = ", i3, ", dormant = ");
        AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb4, i4, ", terminated = ", i5, "}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.globalCpuQueue.getSize());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.globalBlockingQueue.getSize());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((BLOCKING_MASK & j) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(this.corePoolSize - ((int) ((CPU_PERMITS_MASK & j) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }

    public final boolean tryAcquireCpuPermit() {
        long j;
        do {
            j = this.controlState;
            if (((int) ((CPU_PERMITS_MASK & j) >> 42)) == 0) {
                return false;
            }
        } while (!controlState$FU.compareAndSet(this, j, j - WriteBuffer.VAR_UINT_7_OCTET_MIN_VALUE));
        return true;
    }

    public final boolean tryCreateWorker(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & BLOCKING_MASK) >> 21));
        if (i < 0) {
            i = 0;
        }
        if (i < this.corePoolSize) {
            int iCreateNewWorker = createNewWorker();
            if (iCreateNewWorker == 1 && this.corePoolSize > 1) {
                createNewWorker();
            }
            if (iCreateNewWorker > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean tryUnpark() {
        Worker workerParkedWorkersStackPop;
        do {
            workerParkedWorkersStackPop = parkedWorkersStackPop();
            if (workerParkedWorkersStackPop == null) {
                return false;
            }
        } while (!Worker.workerCtl$FU.compareAndSet(workerParkedWorkersStackPop, -1, 0));
        LockSupport.unpark(workerParkedWorkersStackPop);
        return true;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class Worker extends Thread {
        public static final /* synthetic */ AtomicIntegerFieldUpdater workerCtl$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");
        private volatile int indexInArray;

        @JvmField
        @NotNull
        public final WorkQueue localQueue;

        @JvmField
        public boolean mayHaveLocalTasks;
        public long minDelayUntilStealableTaskNs;

        @Nullable
        private volatile Object nextParkedWorker;
        public int rngState;

        @JvmField
        @NotNull
        public WorkerState state;
        public long terminationDeadline;

        @NotNull
        volatile /* synthetic */ int workerCtl;

        public Worker() {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.state = WorkerState.DORMANT;
            this.workerCtl = 0;
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            Random.Default.getClass();
            this.rngState = Random.defaultRandom.nextInt();
        }

        public final void afterTask(int i) {
            if (i == 0) {
                return;
            }
            CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, CoroutineScheduler.PARKED_VERSION_MASK);
            if (this.state != WorkerState.TERMINATED) {
                this.state = WorkerState.DORMANT;
            }
        }

        public final void beforeTask(int i) {
            if (i != 0 && tryReleaseCpu(WorkerState.BLOCKING)) {
                CoroutineScheduler.this.signalCpuWork();
            }
        }

        public final void executeTask(Task task) {
            int taskMode = task.taskContext.getTaskMode();
            idleReset(taskMode);
            beforeTask(taskMode);
            CoroutineScheduler.this.runSafely(task);
            afterTask(taskMode);
        }

        public final Task findAnyTask(boolean z) {
            Task taskPollGlobalQueues;
            Task taskPollGlobalQueues2;
            if (z) {
                boolean z2 = nextInt(CoroutineScheduler.this.corePoolSize * 2) == 0;
                if (z2 && (taskPollGlobalQueues2 = pollGlobalQueues()) != null) {
                    return taskPollGlobalQueues2;
                }
                Task taskPoll = this.localQueue.poll();
                if (taskPoll != null) {
                    return taskPoll;
                }
                if (!z2 && (taskPollGlobalQueues = pollGlobalQueues()) != null) {
                    return taskPollGlobalQueues;
                }
            } else {
                Task taskPollGlobalQueues3 = pollGlobalQueues();
                if (taskPollGlobalQueues3 != null) {
                    return taskPollGlobalQueues3;
                }
            }
            return trySteal(false);
        }

        @Nullable
        public final Task findTask(boolean z) {
            Task taskRemoveFirstOrNull;
            if (tryAcquireCpuPermit()) {
                return findAnyTask(z);
            }
            if (!z || (taskRemoveFirstOrNull = this.localQueue.poll()) == null) {
                taskRemoveFirstOrNull = CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            }
            return taskRemoveFirstOrNull == null ? trySteal(true) : taskRemoveFirstOrNull;
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        @Nullable
        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        @NotNull
        public final CoroutineScheduler getScheduler() {
            return CoroutineScheduler.this;
        }

        public final void idleReset(int i) {
            this.terminationDeadline = 0L;
            if (this.state == WorkerState.PARKING) {
                this.state = WorkerState.BLOCKING;
            }
        }

        public final boolean inStack() {
            return this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK;
        }

        public final int nextInt(int i) {
            int i2 = this.rngState;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.rngState = i5;
            int i6 = i - 1;
            return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
        }

        public final void park() {
            if (this.terminationDeadline == 0) {
                this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
            }
            LockSupport.parkNanos(CoroutineScheduler.this.idleWorkerKeepAliveNs);
            if (System.nanoTime() - this.terminationDeadline >= 0) {
                this.terminationDeadline = 0L;
                tryTerminateWorker();
            }
        }

        public final Task pollGlobalQueues() {
            if (nextInt(2) == 0) {
                Task taskRemoveFirstOrNull = CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
                return taskRemoveFirstOrNull != null ? taskRemoveFirstOrNull : CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            }
            Task taskRemoveFirstOrNull2 = CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            return taskRemoveFirstOrNull2 != null ? taskRemoveFirstOrNull2 : CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            runWorker();
        }

        public final void runWorker() {
            loop0: while (true) {
                boolean z = false;
                while (!CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                    Task taskFindTask = findTask(this.mayHaveLocalTasks);
                    if (taskFindTask != null) {
                        this.minDelayUntilStealableTaskNs = 0L;
                        executeTask(taskFindTask);
                    } else {
                        this.mayHaveLocalTasks = false;
                        if (this.minDelayUntilStealableTaskNs == 0) {
                            tryPark();
                        } else if (z) {
                            tryReleaseCpu(WorkerState.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                            this.minDelayUntilStealableTaskNs = 0L;
                        } else {
                            z = true;
                        }
                    }
                }
                break loop0;
            }
            tryReleaseCpu(WorkerState.TERMINATED);
        }

        public final void setIndexInArray(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.schedulerName);
            sb.append("-worker-");
            sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(sb.toString());
            this.indexInArray = i;
        }

        public final void setNextParkedWorker(@Nullable Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryAcquireCpuPermit() {
            long j;
            if (this.state == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            do {
                j = coroutineScheduler.controlState;
                if (((int) ((CoroutineScheduler.CPU_PERMITS_MASK & j) >> 42)) == 0) {
                    return false;
                }
            } while (!CoroutineScheduler.controlState$FU.compareAndSet(coroutineScheduler, j, j - WriteBuffer.VAR_UINT_7_OCTET_MIN_VALUE));
            this.state = WorkerState.CPU_ACQUIRED;
            return true;
        }

        public final void tryPark() {
            if (!inStack()) {
                CoroutineScheduler.this.parkedWorkersStackPush(this);
                return;
            }
            this.workerCtl = -1;
            while (inStack() && this.workerCtl == -1 && !CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                tryReleaseCpu(WorkerState.PARKING);
                Thread.interrupted();
                park();
            }
        }

        public final boolean tryReleaseCpu(@NotNull WorkerState workerState) {
            WorkerState workerState2 = this.state;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, WriteBuffer.VAR_UINT_7_OCTET_MIN_VALUE);
            }
            if (workerState2 != workerState) {
                this.state = workerState;
            }
            return z;
        }

        public final Task trySteal(boolean z) {
            int i = (int) (CoroutineScheduler.this.controlState & 2097151);
            if (i < 2) {
                return null;
            }
            int iNextInt = nextInt(i);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long jMin = Long.MAX_VALUE;
            for (int i2 = 0; i2 < i; i2++) {
                iNextInt++;
                if (iNextInt > i) {
                    iNextInt = 1;
                }
                Worker worker = coroutineScheduler.workers.get(iNextInt);
                if (worker != null && worker != this) {
                    long jTryStealBlockingFrom = z ? this.localQueue.tryStealBlockingFrom(worker.localQueue) : this.localQueue.tryStealFrom(worker.localQueue);
                    if (jTryStealBlockingFrom == -1) {
                        return this.localQueue.poll();
                    }
                    if (jTryStealBlockingFrom > 0) {
                        jMin = Math.min(jMin, jTryStealBlockingFrom);
                    }
                }
            }
            if (jMin == Long.MAX_VALUE) {
                jMin = 0;
            }
            this.minDelayUntilStealableTaskNs = jMin;
            return null;
        }

        public final void tryTerminateWorker() {
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            synchronized (coroutineScheduler.workers) {
                try {
                    if (coroutineScheduler.isTerminated()) {
                        return;
                    }
                    if (((int) (coroutineScheduler.controlState & 2097151)) <= coroutineScheduler.corePoolSize) {
                        return;
                    }
                    if (workerCtl$FU.compareAndSet(this, -1, 1)) {
                        int i = this.indexInArray;
                        setIndexInArray(0);
                        coroutineScheduler.parkedWorkersStackTopUpdate(this, i, 0);
                        int andDecrement = (int) (2097151 & CoroutineScheduler.controlState$FU.getAndDecrement(coroutineScheduler));
                        if (andDecrement != i) {
                            Worker worker = coroutineScheduler.workers.get(andDecrement);
                            Intrinsics.checkNotNull(worker);
                            Worker worker2 = worker;
                            coroutineScheduler.workers.setSynchronized(i, worker2);
                            worker2.setIndexInArray(i);
                            coroutineScheduler.parkedWorkersStackTopUpdate(worker2, andDecrement, i);
                        }
                        coroutineScheduler.workers.setSynchronized(andDecrement, null);
                        this.state = WorkerState.TERMINATED;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public Worker(CoroutineScheduler coroutineScheduler, int i) {
            this();
            setIndexInArray(i);
        }
    }

    public /* synthetic */ CoroutineScheduler(int i, int i2, long j, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? TasksKt.IDLE_WORKER_KEEP_ALIVE_NS : j, (i3 & 8) != 0 ? TasksKt.DEFAULT_SCHEDULER_NAME : str);
    }
}
