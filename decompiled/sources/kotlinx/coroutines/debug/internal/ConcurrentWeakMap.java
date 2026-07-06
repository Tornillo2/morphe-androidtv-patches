package kotlinx.coroutines.debug.internal;

import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ConcurrentWeakMap<K, V> extends AbstractMutableMap<K, V> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _size$FU = AtomicIntegerFieldUpdater.newUpdater(ConcurrentWeakMap.class, "_size");

    @NotNull
    private volatile /* synthetic */ int _size;

    @NotNull
    volatile /* synthetic */ Object core;

    @Nullable
    public final ReferenceQueue<K> weakRefQueue;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class Core {
        public static final /* synthetic */ AtomicIntegerFieldUpdater load$FU = AtomicIntegerFieldUpdater.newUpdater(Core.class, "load");
        public final int allocated;

        @NotNull
        public /* synthetic */ AtomicReferenceArray keys;

        @NotNull
        private volatile /* synthetic */ int load = 0;
        public final int shift;
        public final int threshold;

        @NotNull
        public /* synthetic */ AtomicReferenceArray values;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class KeyValueIterator<E> implements Iterator<E>, KMutableIterator {

            @NotNull
            public final Function2<K, V, E> factory;
            public int index = -1;
            public K key;
            public V value;

            /* JADX WARN: Multi-variable type inference failed */
            public KeyValueIterator(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
                this.factory = function2;
                findNext();
            }

            public final void findNext() {
                K k;
                while (true) {
                    int i = this.index + 1;
                    this.index = i;
                    if (i >= Core.this.allocated) {
                        return;
                    }
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) Core.this.keys.get(this.index);
                    if (hashedWeakRef != null && (k = (K) hashedWeakRef.get()) != null) {
                        this.key = k;
                        Object obj = (V) Core.this.values.get(this.index);
                        if (obj instanceof Marked) {
                            obj = (V) ((Marked) obj).ref;
                        }
                        if (obj != null) {
                            this.value = (V) obj;
                            return;
                        }
                    }
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < Core.this.allocated;
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.index >= Core.this.allocated) {
                    throw new NoSuchElementException();
                }
                Function2<K, V, E> function2 = this.factory;
                K k = this.key;
                if (k == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("key");
                    throw null;
                }
                V v = this.value;
                if (v == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("value");
                    throw null;
                }
                E eInvoke = function2.invoke(k, v);
                findNext();
                return eInvoke;
            }

            @Override // java.util.Iterator
            @NotNull
            public Void remove() {
                ConcurrentWeakMapKt.noImpl();
                throw null;
            }

            @Override // java.util.Iterator
            public void remove() {
                ConcurrentWeakMapKt.noImpl();
                throw null;
            }
        }

        public Core(int i) {
            this.allocated = i;
            this.shift = Integer.numberOfLeadingZeros(i) + 1;
            this.threshold = (i * 2) / 3;
            this.keys = new AtomicReferenceArray(i);
            this.values = new AtomicReferenceArray(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object putImpl$default(Core core, Object obj, Object obj2, HashedWeakRef hashedWeakRef, int i, Object obj3) {
            if ((i & 4) != 0) {
                hashedWeakRef = null;
            }
            return core.putImpl(obj, obj2, hashedWeakRef);
        }

        public final void cleanWeakRef(@NotNull HashedWeakRef<?> hashedWeakRef) {
            int iIndex = index(hashedWeakRef.hash);
            while (true) {
                HashedWeakRef<?> hashedWeakRef2 = (HashedWeakRef) this.keys.get(iIndex);
                if (hashedWeakRef2 == null) {
                    return;
                }
                if (hashedWeakRef2 == hashedWeakRef) {
                    removeCleanedAt(iIndex);
                    return;
                } else {
                    if (iIndex == 0) {
                        iIndex = this.allocated;
                    }
                    iIndex--;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Nullable
        public final V getImpl(@NotNull K k) {
            int iIndex = index(k.hashCode());
            while (true) {
                HashedWeakRef hashedWeakRef = (HashedWeakRef) this.keys.get(iIndex);
                if (hashedWeakRef == null) {
                    return null;
                }
                Object obj = hashedWeakRef.get();
                if (k.equals(obj)) {
                    V v = (V) this.values.get(iIndex);
                    return v instanceof Marked ? (V) ((Marked) v).ref : v;
                }
                if (obj == null) {
                    removeCleanedAt(iIndex);
                }
                if (iIndex == 0) {
                    iIndex = this.allocated;
                }
                iIndex--;
            }
        }

        public final int index(int i) {
            return (i * (-1640531527)) >>> this.shift;
        }

        @NotNull
        public final <E> Iterator<E> keyValueIterator(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
            return new KeyValueIterator(function2);
        }

        @Nullable
        public final Object putImpl(@NotNull K k, @Nullable V v, @Nullable HashedWeakRef<K> hashedWeakRef) {
            int i;
            Object obj;
            int iIndex = index(k.hashCode());
            boolean z = false;
            while (true) {
                HashedWeakRef hashedWeakRef2 = (HashedWeakRef) this.keys.get(iIndex);
                if (hashedWeakRef2 != null) {
                    Object obj2 = hashedWeakRef2.get();
                    if (!k.equals(obj2)) {
                        if (obj2 == null) {
                            removeCleanedAt(iIndex);
                        }
                        if (iIndex == 0) {
                            iIndex = this.allocated;
                        }
                        iIndex--;
                    } else if (z) {
                        load$FU.decrementAndGet(this);
                    }
                } else if (v != null) {
                    if (!z) {
                        do {
                            i = this.load;
                            if (i >= this.threshold) {
                                return ConcurrentWeakMapKt.REHASH;
                            }
                        } while (!load$FU.compareAndSet(this, i, i + 1));
                        z = true;
                    }
                    if (hashedWeakRef == null) {
                        hashedWeakRef = new HashedWeakRef<>(k, ConcurrentWeakMap.this.weakRefQueue);
                    }
                    if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.keys, iIndex, null, hashedWeakRef)) {
                        break;
                    }
                } else {
                    return null;
                }
            }
            do {
                obj = this.values.get(iIndex);
                if (obj instanceof Marked) {
                    return ConcurrentWeakMapKt.REHASH;
                }
            } while (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.values, iIndex, obj, v));
            return obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final ConcurrentWeakMap<K, V>.Core rehash() {
            int i;
            Object obj;
            while (true) {
                int size = ConcurrentWeakMap.this.getSize();
                if (size < 4) {
                    size = 4;
                }
                ConcurrentWeakMap<K, V>.Core core = (ConcurrentWeakMap<K, V>.Core) ConcurrentWeakMap.this.new Core(Integer.highestOneBit(size) * 4);
                int i2 = this.allocated;
                while (i < i2) {
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) this.keys.get(i);
                    Object obj2 = hashedWeakRef != null ? hashedWeakRef.get() : null;
                    if (hashedWeakRef != null && obj2 == null) {
                        removeCleanedAt(i);
                    }
                    while (true) {
                        obj = this.values.get(i);
                        if (obj instanceof Marked) {
                            obj = ((Marked) obj).ref;
                            break;
                        }
                        if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.values, i, obj, ConcurrentWeakMapKt.mark(obj))) {
                            break;
                        }
                    }
                    i = (obj2 == null || obj == null || core.putImpl(obj2, obj, hashedWeakRef) != ConcurrentWeakMapKt.REHASH) ? i + 1 : 0;
                }
                return core;
            }
        }

        public final void removeCleanedAt(int i) {
            Object obj;
            do {
                obj = this.values.get(i);
                if (obj == null || (obj instanceof Marked)) {
                    return;
                }
            } while (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.values, i, obj, null));
            ConcurrentWeakMap.this.decrementSize();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Entry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        public final K key;
        public final V value;

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            ConcurrentWeakMapKt.noImpl();
            throw null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class KeyValueSet<E> extends AbstractMutableSet<E> {

        @NotNull
        public final Function2<K, V, E> factory;

        /* JADX WARN: Multi-variable type inference failed */
        public KeyValueSet(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
            this.factory = function2;
        }

        @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(E e) {
            ConcurrentWeakMapKt.noImpl();
            throw null;
        }

        @Override // kotlin.collections.AbstractMutableSet
        public int getSize() {
            return ConcurrentWeakMap.this.getSize();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        @NotNull
        public Iterator<E> iterator() {
            Core core = (Core) ConcurrentWeakMap.this.core;
            Function2<K, V, E> function2 = this.factory;
            core.getClass();
            return core.new KeyValueIterator(function2);
        }
    }

    public ConcurrentWeakMap() {
        this(false, 1, null);
    }

    public final void cleanWeakRef(HashedWeakRef<?> hashedWeakRef) {
        ((Core) this.core).cleanWeakRef(hashedWeakRef);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Iterator it = ((KeyValueSet) getKeys()).iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    public final void decrementSize() {
        _size$FU.decrementAndGet(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        return (V) ((Core) this.core).getImpl(obj);
    }

    @Override // kotlin.collections.AbstractMutableMap
    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        return new KeyValueSet(ConcurrentWeakMap$entries$1.INSTANCE);
    }

    @Override // kotlin.collections.AbstractMutableMap
    @NotNull
    public Set<K> getKeys() {
        return new KeyValueSet(ConcurrentWeakMap$keys$1.INSTANCE);
    }

    @Override // kotlin.collections.AbstractMutableMap
    public int getSize() {
        return this._size;
    }

    @Override // kotlin.collections.AbstractMutableMap, java.util.AbstractMap, java.util.Map
    @Nullable
    public V put(@NotNull K k, @NotNull V v) {
        V vPutSynchronized = (V) Core.putImpl$default((Core) this.core, k, v, null, 4, null);
        if (vPutSynchronized == ConcurrentWeakMapKt.REHASH) {
            vPutSynchronized = putSynchronized(k, v);
        }
        if (vPutSynchronized == null) {
            _size$FU.incrementAndGet(this);
        }
        return vPutSynchronized;
    }

    public final synchronized V putSynchronized(K k, V v) {
        V v2;
        Core coreRehash = (Core) this.core;
        while (true) {
            K k2 = k;
            V v3 = v;
            v2 = (V) Core.putImpl$default(coreRehash, k2, v3, null, 4, null);
            if (v2 == ConcurrentWeakMapKt.REHASH) {
                coreRehash = coreRehash.rehash();
                this.core = coreRehash;
                k = k2;
                v = v3;
            }
        }
        return v2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V remove(@Nullable Object obj) {
        if (obj == 0) {
            return null;
        }
        V vPutSynchronized = (V) Core.putImpl$default((Core) this.core, obj, null, null, 4, null);
        if (vPutSynchronized == ConcurrentWeakMapKt.REHASH) {
            vPutSynchronized = putSynchronized(obj, null);
        }
        if (vPutSynchronized != null) {
            _size$FU.decrementAndGet(this);
        }
        return vPutSynchronized;
    }

    public final void runWeakRefQueueCleaningLoopUntilInterrupted() {
        if (this.weakRefQueue == null) {
            throw new IllegalStateException("Must be created with weakRefQueue = true");
        }
        while (true) {
            try {
                Reference<? extends K> referenceRemove = this.weakRefQueue.remove();
                if (referenceRemove == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
                }
                cleanWeakRef((HashedWeakRef) referenceRemove);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public /* synthetic */ ConcurrentWeakMap(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public ConcurrentWeakMap(boolean z) {
        this._size = 0;
        this.core = new Core(16);
        this.weakRefQueue = z ? new ReferenceQueue<>() : null;
    }
}
