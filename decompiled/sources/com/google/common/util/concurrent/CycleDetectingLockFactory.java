package com.google.common.util.concurrent;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.j2objc.annotations.Weak;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import org.apache.commons.text.AlphabetConverter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class CycleDetectingLockFactory {
    public static final ThreadLocal<ArrayList<LockGraphNode>> acquiredLocks;
    public static final ConcurrentMap<Class<? extends Enum<?>>, Map<? extends Enum<?>, LockGraphNode>> lockGraphNodesPerType;
    public static final LazyLogger logger;
    public final Policy policy;

    /* JADX INFO: renamed from: com.google.common.util.concurrent.CycleDetectingLockFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends ThreadLocal<ArrayList<LockGraphNode>> {
        @Override // java.lang.ThreadLocal
        public ArrayList<LockGraphNode> initialValue() {
            return Lists.newArrayListWithCapacity(3);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface CycleDetectingLock {
        LockGraphNode getLockGraphNode();

        boolean isAcquiredByCurrentThread();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class CycleDetectingReentrantLock extends ReentrantLock implements CycleDetectingLock {
        public final LockGraphNode lockGraphNode;

        public /* synthetic */ CycleDetectingReentrantLock(CycleDetectingLockFactory cycleDetectingLockFactory, LockGraphNode lockGraphNode, boolean z, AnonymousClass1 anonymousClass1) {
            this(lockGraphNode, z);
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public LockGraphNode getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public boolean isAcquiredByCurrentThread() {
            return isHeldByCurrentThread();
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        public CycleDetectingReentrantLock(LockGraphNode lockGraphNode, boolean fair) {
            super(fair);
            lockGraphNode.getClass();
            this.lockGraphNode = lockGraphNode;
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                return super.tryLock(timeout, unit);
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class CycleDetectingReentrantReadWriteLock extends ReentrantReadWriteLock implements CycleDetectingLock {
        public final LockGraphNode lockGraphNode;
        public final CycleDetectingReentrantReadLock readLock;
        public final CycleDetectingReentrantWriteLock writeLock;

        public /* synthetic */ CycleDetectingReentrantReadWriteLock(CycleDetectingLockFactory cycleDetectingLockFactory, LockGraphNode lockGraphNode, boolean z, AnonymousClass1 anonymousClass1) {
            this(lockGraphNode, z);
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public LockGraphNode getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public boolean isAcquiredByCurrentThread() {
            return isWriteLockedByCurrentThread() || getReadHoldCount() > 0;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public Lock readLock() {
            return this.readLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public Lock writeLock() {
            return this.writeLock;
        }

        public CycleDetectingReentrantReadWriteLock(LockGraphNode lockGraphNode, boolean fair) {
            super(fair);
            this.readLock = CycleDetectingLockFactory.this.new CycleDetectingReentrantReadLock(this);
            this.writeLock = CycleDetectingLockFactory.this.new CycleDetectingReentrantWriteLock(this);
            lockGraphNode.getClass();
            this.lockGraphNode = lockGraphNode;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.ReadLock readLock() {
            return this.readLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.WriteLock writeLock() {
            return this.writeLock;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ExampleStackTrace extends IllegalStateException {
        public static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
        public static final ImmutableSet<String> EXCLUDED_CLASS_NAMES = ImmutableSet.of(CycleDetectingLockFactory.class.getName(), ExampleStackTrace.class.getName(), LockGraphNode.class.getName());

        public ExampleStackTrace(LockGraphNode node1, LockGraphNode node2) {
            super(node1.getLockName() + AlphabetConverter.ARROW + node2.getLockName());
            StackTraceElement[] stackTrace = getStackTrace();
            int length = stackTrace.length;
            for (int i = 0; i < length; i++) {
                if (WithExplicitOrdering.class.getName().equals(stackTrace[i].getClassName())) {
                    setStackTrace(EMPTY_STACK_TRACE);
                    return;
                } else {
                    if (!EXCLUDED_CLASS_NAMES.contains(stackTrace[i].getClassName())) {
                        setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i, length));
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LockGraphNode {
        public final Map<LockGraphNode, ExampleStackTrace> allowedPriorLocks;
        public final Map<LockGraphNode, PotentialDeadlockException> disallowedPriorLocks;
        public final String lockName;

        public LockGraphNode(String lockName) {
            MapMaker mapMaker = new MapMaker();
            MapMakerInternalMap.Strength strength = MapMakerInternalMap.Strength.WEAK;
            mapMaker.setKeyStrength(strength);
            this.allowedPriorLocks = mapMaker.makeMap();
            MapMaker mapMaker2 = new MapMaker();
            mapMaker2.setKeyStrength(strength);
            this.disallowedPriorLocks = mapMaker2.makeMap();
            lockName.getClass();
            this.lockName = lockName;
        }

        public void checkAcquiredLock(Policy policy, LockGraphNode acquiredLock) {
            Preconditions.checkState(this != acquiredLock, "Attempted to acquire multiple locks with the same rank %s", acquiredLock.getLockName());
            if (this.allowedPriorLocks.containsKey(acquiredLock)) {
                return;
            }
            PotentialDeadlockException potentialDeadlockException = this.disallowedPriorLocks.get(acquiredLock);
            if (potentialDeadlockException != null) {
                policy.handlePotentialDeadlock(new PotentialDeadlockException(acquiredLock, this, potentialDeadlockException.conflictingStackTrace));
                return;
            }
            ExampleStackTrace exampleStackTraceFindPathTo = acquiredLock.findPathTo(this, Sets.newIdentityHashSet());
            if (exampleStackTraceFindPathTo == null) {
                this.allowedPriorLocks.put(acquiredLock, new ExampleStackTrace(acquiredLock, this));
                return;
            }
            PotentialDeadlockException potentialDeadlockException2 = new PotentialDeadlockException(acquiredLock, this, exampleStackTraceFindPathTo);
            this.disallowedPriorLocks.put(acquiredLock, potentialDeadlockException2);
            policy.handlePotentialDeadlock(potentialDeadlockException2);
        }

        public void checkAcquiredLocks(Policy policy, List<LockGraphNode> acquiredLocks) {
            Iterator<LockGraphNode> it = acquiredLocks.iterator();
            while (it.hasNext()) {
                checkAcquiredLock(policy, it.next());
            }
        }

        public final ExampleStackTrace findPathTo(LockGraphNode node, Set<LockGraphNode> seen) {
            if (!seen.add(this)) {
                return null;
            }
            ExampleStackTrace exampleStackTrace = this.allowedPriorLocks.get(node);
            if (exampleStackTrace != null) {
                return exampleStackTrace;
            }
            for (Map.Entry<LockGraphNode, ExampleStackTrace> entry : this.allowedPriorLocks.entrySet()) {
                LockGraphNode key = entry.getKey();
                ExampleStackTrace exampleStackTraceFindPathTo = key.findPathTo(node, seen);
                if (exampleStackTraceFindPathTo != null) {
                    ExampleStackTrace exampleStackTrace2 = new ExampleStackTrace(key, this);
                    exampleStackTrace2.setStackTrace(entry.getValue().getStackTrace());
                    exampleStackTrace2.initCause(exampleStackTraceFindPathTo);
                    return exampleStackTrace2;
                }
            }
            return null;
        }

        public String getLockName() {
            return this.lockName;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Policies implements Policy {
        THROW { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.Policies.1
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException e) {
                throw e;
            }
        },
        WARN { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.Policies.2
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException e) {
                CycleDetectingLockFactory.logger.get().log(Level.SEVERE, "Detected potential deadlock", (Throwable) e);
            }
        },
        DISABLED { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.Policies.3
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException e) {
            }
        };

        Policies(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Policy {
        void handlePotentialDeadlock(PotentialDeadlockException exception);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PotentialDeadlockException extends ExampleStackTrace {
        public final ExampleStackTrace conflictingStackTrace;

        public /* synthetic */ PotentialDeadlockException(LockGraphNode lockGraphNode, LockGraphNode lockGraphNode2, ExampleStackTrace exampleStackTrace, AnonymousClass1 anonymousClass1) {
            this(lockGraphNode, lockGraphNode2, exampleStackTrace);
        }

        public ExampleStackTrace getConflictingStackTrace() {
            return this.conflictingStackTrace;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            String message = super.getMessage();
            Objects.requireNonNull(message);
            StringBuilder sb = new StringBuilder(message);
            for (Throwable cause = this.conflictingStackTrace; cause != null; cause = cause.getCause()) {
                sb.append(", ");
                sb.append(cause.getMessage());
            }
            return sb.toString();
        }

        public PotentialDeadlockException(LockGraphNode node1, LockGraphNode node2, ExampleStackTrace conflictingStackTrace) {
            super(node1, node2);
            this.conflictingStackTrace = conflictingStackTrace;
            initCause(conflictingStackTrace);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WithExplicitOrdering<E extends Enum<E>> extends CycleDetectingLockFactory {
        public final Map<E, LockGraphNode> lockGraphNodes;

        @VisibleForTesting
        public WithExplicitOrdering(Policy policy, Map<E, LockGraphNode> lockGraphNodes) {
            super(policy);
            this.lockGraphNodes = lockGraphNodes;
        }

        public ReentrantLock newReentrantLock(E rank) {
            return newReentrantLock((Enum) rank, false);
        }

        public ReentrantReadWriteLock newReentrantReadWriteLock(E rank) {
            return newReentrantReadWriteLock((Enum) rank, false);
        }

        public ReentrantLock newReentrantLock(E rank, boolean fair) {
            if (this.policy == Policies.DISABLED) {
                return new ReentrantLock(fair);
            }
            LockGraphNode lockGraphNode = this.lockGraphNodes.get(rank);
            Objects.requireNonNull(lockGraphNode);
            return new CycleDetectingReentrantLock(lockGraphNode, fair);
        }

        public ReentrantReadWriteLock newReentrantReadWriteLock(E rank, boolean fair) {
            if (this.policy == Policies.DISABLED) {
                return new ReentrantReadWriteLock(fair);
            }
            LockGraphNode lockGraphNode = this.lockGraphNodes.get(rank);
            Objects.requireNonNull(lockGraphNode);
            return new CycleDetectingReentrantReadWriteLock(lockGraphNode, fair);
        }
    }

    static {
        MapMaker mapMaker = new MapMaker();
        mapMaker.setKeyStrength(MapMakerInternalMap.Strength.WEAK);
        lockGraphNodesPerType = mapMaker.makeMap();
        logger = new LazyLogger(CycleDetectingLockFactory.class);
        acquiredLocks = new AnonymousClass1();
    }

    public /* synthetic */ CycleDetectingLockFactory(Policy policy, AnonymousClass1 anonymousClass1) {
        this(policy);
    }

    @VisibleForTesting
    public static <E extends Enum<E>> Map<E, LockGraphNode> createNodes(Class<E> clazz) {
        EnumMap enumMapNewEnumMap = Maps.newEnumMap(clazz);
        E[] enumConstants = clazz.getEnumConstants();
        int length = enumConstants.length;
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(length);
        int i = 0;
        for (E e : enumConstants) {
            LockGraphNode lockGraphNode = new LockGraphNode(getLockName(e));
            arrayListNewArrayListWithCapacity.add(lockGraphNode);
            enumMapNewEnumMap.put(e, lockGraphNode);
        }
        for (int i2 = 1; i2 < length; i2++) {
            ((LockGraphNode) arrayListNewArrayListWithCapacity.get(i2)).checkAcquiredLocks(Policies.THROW, arrayListNewArrayListWithCapacity.subList(0, i2));
        }
        while (i < length - 1) {
            i++;
            ((LockGraphNode) arrayListNewArrayListWithCapacity.get(i)).checkAcquiredLocks(Policies.DISABLED, arrayListNewArrayListWithCapacity.subList(i, length));
        }
        return DesugarCollections.unmodifiableMap(enumMapNewEnumMap);
    }

    public static String getLockName(Enum<?> rank) {
        return rank.getDeclaringClass().getSimpleName() + ExternalFourCCMapper.CODEC_NAME_SPLITTER + rank.name();
    }

    public static <E extends Enum<E>> Map<? extends E, LockGraphNode> getOrCreateNodes(Class<E> clazz) {
        ConcurrentMap<Class<? extends Enum<?>>, Map<? extends Enum<?>, LockGraphNode>> concurrentMap = lockGraphNodesPerType;
        Map<? extends E, LockGraphNode> map = (Map) concurrentMap.get(clazz);
        if (map != null) {
            return map;
        }
        Map<? extends Enum<?>, LockGraphNode> mapCreateNodes = createNodes(clazz);
        return (Map) MoreObjects.firstNonNull(concurrentMap.putIfAbsent(clazz, mapCreateNodes), mapCreateNodes);
    }

    public static void lockStateChanged(CycleDetectingLock lock) {
        if (lock.isAcquiredByCurrentThread()) {
            return;
        }
        ArrayList<LockGraphNode> arrayList = acquiredLocks.get();
        Objects.requireNonNull(arrayList);
        LockGraphNode lockGraphNode = lock.getLockGraphNode();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == lockGraphNode) {
                arrayList.remove(size);
                return;
            }
        }
    }

    public static CycleDetectingLockFactory newInstance(Policy policy) {
        return new CycleDetectingLockFactory(policy);
    }

    public static <E extends Enum<E>> WithExplicitOrdering<E> newInstanceWithExplicitOrdering(Class<E> enumClass, Policy policy) {
        enumClass.getClass();
        policy.getClass();
        return new WithExplicitOrdering<>(policy, getOrCreateNodes(enumClass));
    }

    public final void aboutToAcquire(CycleDetectingLock lock) {
        if (lock.isAcquiredByCurrentThread()) {
            return;
        }
        ArrayList<LockGraphNode> arrayList = acquiredLocks.get();
        Objects.requireNonNull(arrayList);
        LockGraphNode lockGraphNode = lock.getLockGraphNode();
        lockGraphNode.checkAcquiredLocks(this.policy, arrayList);
        arrayList.add(lockGraphNode);
    }

    public ReentrantLock newReentrantLock(String lockName) {
        return newReentrantLock(lockName, false);
    }

    public ReentrantReadWriteLock newReentrantReadWriteLock(String lockName) {
        return newReentrantReadWriteLock(lockName, false);
    }

    public CycleDetectingLockFactory(Policy policy) {
        policy.getClass();
        this.policy = policy;
    }

    public ReentrantLock newReentrantLock(String lockName, boolean fair) {
        return this.policy == Policies.DISABLED ? new ReentrantLock(fair) : new CycleDetectingReentrantLock(new LockGraphNode(lockName), fair);
    }

    public ReentrantReadWriteLock newReentrantReadWriteLock(String lockName, boolean fair) {
        return this.policy == Policies.DISABLED ? new ReentrantReadWriteLock(fair) : new CycleDetectingReentrantReadWriteLock(new LockGraphNode(lockName), fair);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class CycleDetectingReentrantReadLock extends ReentrantReadWriteLock.ReadLock {

        @Weak
        public final CycleDetectingReentrantReadWriteLock readWriteLock;

        public CycleDetectingReentrantReadLock(CycleDetectingReentrantReadWriteLock readWriteLock) {
            super(readWriteLock);
            this.readWriteLock = readWriteLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock(timeout, unit);
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class CycleDetectingReentrantWriteLock extends ReentrantReadWriteLock.WriteLock {

        @Weak
        public final CycleDetectingReentrantReadWriteLock readWriteLock;

        public CycleDetectingReentrantWriteLock(CycleDetectingReentrantReadWriteLock readWriteLock) {
            super(readWriteLock);
            this.readWriteLock = readWriteLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock(timeout, unit);
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }
    }
}
