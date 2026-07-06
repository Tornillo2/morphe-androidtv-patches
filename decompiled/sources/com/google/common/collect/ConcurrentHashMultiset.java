package com.google.common.collect;

import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Serialization;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class ConcurrentHashMultiset<E> extends AbstractMultiset<E> implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient ConcurrentMap<E, AtomicInteger> countMap;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class EntrySet extends AbstractMultiset<E>.EntrySet {
        public EntrySet() {
            super();
        }

        public final List<Multiset.Entry<E>> snapshot() {
            ArrayList arrayListNewArrayListWithExpectedSize = Lists.newArrayListWithExpectedSize(size());
            Iterators.addAll(arrayListNewArrayListWithExpectedSize, iterator());
            return arrayListNewArrayListWithExpectedSize;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return ((ArrayList) snapshot()).toArray();
        }

        @Override // com.google.common.collect.AbstractMultiset.EntrySet, com.google.common.collect.Multisets.EntrySet
        public ConcurrentHashMultiset<E> multiset() {
            return ConcurrentHashMultiset.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) ((ArrayList) snapshot()).toArray(tArr);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FieldSettersHolder {
        public static final Serialization.FieldSetter<? super ConcurrentHashMultiset<?>> COUNT_MAP_FIELD_SETTER = Serialization.getFieldSetter(ConcurrentHashMultiset.class, "countMap");
    }

    @VisibleForTesting
    public ConcurrentHashMultiset(ConcurrentMap<E, AtomicInteger> countMap) {
        Preconditions.checkArgument(countMap.isEmpty(), "the backing map (%s) must be empty", countMap);
        this.countMap = countMap;
    }

    public static <E> ConcurrentHashMultiset<E> create() {
        return new ConcurrentHashMultiset<>(new ConcurrentHashMap());
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        Object object = stream.readObject();
        Objects.requireNonNull(object);
        FieldSettersHolder.COUNT_MAP_FIELD_SETTER.set(this, (ConcurrentMap) object);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.countMap);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(E element, int occurrences) {
        AtomicInteger atomicIntegerPutIfAbsent;
        int i;
        AtomicInteger atomicInteger;
        element.getClass();
        if (occurrences == 0) {
            return count(element);
        }
        CollectPreconditions.checkPositive(occurrences, "occurrences");
        do {
            atomicIntegerPutIfAbsent = (AtomicInteger) Maps.safeGet(this.countMap, element);
            if (atomicIntegerPutIfAbsent == null && (atomicIntegerPutIfAbsent = this.countMap.putIfAbsent(element, new AtomicInteger(occurrences))) == null) {
                return 0;
            }
            do {
                i = atomicIntegerPutIfAbsent.get();
                if (i == 0) {
                    atomicInteger = new AtomicInteger(occurrences);
                    if (this.countMap.putIfAbsent(element, atomicInteger) == null) {
                        break;
                    }
                } else {
                    try {
                    } catch (ArithmeticException unused) {
                        throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Overflow adding ", occurrences, " occurrences to a count of ", i));
                    }
                }
            } while (!atomicIntegerPutIfAbsent.compareAndSet(i, IntMath.checkedAdd(i, occurrences)));
            return i;
        } while (!this.countMap.replace(element, atomicIntegerPutIfAbsent, atomicInteger));
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.countMap.clear();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(Object element) {
        return super.contains(element);
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        AtomicInteger atomicInteger = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (atomicInteger == null) {
            return 0;
        }
        return atomicInteger.get();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Set<E> createElementSet() {
        final Set<E> setKeySet = this.countMap.keySet();
        return new ForwardingSet<E>(this) { // from class: com.google.common.collect.ConcurrentHashMultiset.1
            public final /* synthetic */ ConcurrentHashMultiset this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
            public boolean contains(Object object) {
                return object != null && Collections2.safeContains(setKeySet, object);
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> collection) {
                return standardContainsAll(collection);
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
            public boolean remove(Object object) {
                return object != null && Collections2.safeRemove(setKeySet, object);
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> c) {
                return standardRemoveAll(c);
            }

            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
            public Set<E> delegate() {
                return setKeySet;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset
    @Deprecated
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return this.countMap.size();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<E> elementIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        final AbstractIterator<Multiset.Entry<E>> abstractIterator = new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.ConcurrentHashMultiset.2
            public final Iterator<Map.Entry<E, AtomicInteger>> mapEntries;

            {
                this.mapEntries = ConcurrentHashMultiset.this.countMap.entrySet().iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public Multiset.Entry<E> computeNext() {
                while (this.mapEntries.hasNext()) {
                    Map.Entry<E, AtomicInteger> next = this.mapEntries.next();
                    int i = next.getValue().get();
                    if (i != 0) {
                        return new Multisets.ImmutableEntry(next.getKey(), i);
                    }
                }
                endOfData();
                return null;
            }
        };
        return new ForwardingIterator<Multiset.Entry<E>>(this) { // from class: com.google.common.collect.ConcurrentHashMultiset.3
            public Multiset.Entry<E> last;
            public final /* synthetic */ ConcurrentHashMultiset this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
            public void remove() {
                Preconditions.checkState(this.last != null, "no calls to next() since the last call to remove()");
                this.this$0.setCount(this.last.getElement(), 0);
                this.last = null;
            }

            @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
            public Iterator<Multiset.Entry<E>> delegate() {
                return abstractIterator;
            }

            @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
            public Multiset.Entry<E> next() {
                Multiset.Entry<E> entry = (Multiset.Entry) super.next();
                this.last = entry;
                return entry;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.countMap.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object element, int occurrences) {
        int i;
        int iMax;
        if (occurrences == 0) {
            return count(element);
        }
        CollectPreconditions.checkPositive(occurrences, "occurrences");
        AtomicInteger atomicInteger = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (atomicInteger == null) {
            return 0;
        }
        do {
            i = atomicInteger.get();
            if (i == 0) {
                return 0;
            }
            iMax = Math.max(0, i - occurrences);
        } while (!atomicInteger.compareAndSet(i, iMax));
        if (iMax == 0) {
            this.countMap.remove(element, atomicInteger);
        }
        return i;
    }

    @CanIgnoreReturnValue
    public boolean removeExactly(Object element, int occurrences) {
        int i;
        int i2;
        if (occurrences == 0) {
            return true;
        }
        CollectPreconditions.checkPositive(occurrences, "occurrences");
        AtomicInteger atomicInteger = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (atomicInteger == null) {
            return false;
        }
        do {
            i = atomicInteger.get();
            if (i < occurrences) {
                return false;
            }
            i2 = i - occurrences;
        } while (!atomicInteger.compareAndSet(i, i2));
        if (i2 == 0) {
            this.countMap.remove(element, atomicInteger);
        }
        return true;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(E element, int count) {
        AtomicInteger atomicIntegerPutIfAbsent;
        int i;
        AtomicInteger atomicInteger;
        element.getClass();
        CollectPreconditions.checkNonnegative(count, "count");
        do {
            atomicIntegerPutIfAbsent = (AtomicInteger) Maps.safeGet(this.countMap, element);
            if (atomicIntegerPutIfAbsent == null && (count == 0 || (atomicIntegerPutIfAbsent = this.countMap.putIfAbsent(element, new AtomicInteger(count))) == null)) {
                return 0;
            }
            do {
                i = atomicIntegerPutIfAbsent.get();
                if (i == 0) {
                    if (count != 0) {
                        atomicInteger = new AtomicInteger(count);
                        if (this.countMap.putIfAbsent(element, atomicInteger) == null) {
                            break;
                        }
                    } else {
                        return 0;
                    }
                }
            } while (!atomicIntegerPutIfAbsent.compareAndSet(i, count));
            if (count == 0) {
                this.countMap.remove(element, atomicIntegerPutIfAbsent);
            }
            return i;
        } while (!this.countMap.replace(element, atomicIntegerPutIfAbsent, atomicInteger));
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        Iterator<AtomicInteger> it = this.countMap.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += (long) it.next().get();
        }
        return Ints.saturatedCast(j);
    }

    public final List<E> snapshot() {
        ArrayList arrayListNewArrayListWithExpectedSize = Lists.newArrayListWithExpectedSize(size());
        for (Multiset.Entry<E> entry : super.entrySet()) {
            E element = entry.getElement();
            for (int count = entry.getCount(); count > 0; count--) {
                arrayListNewArrayListWithExpectedSize.add(element);
            }
        }
        return arrayListNewArrayListWithExpectedSize;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return ((ArrayList) snapshot()).toArray();
    }

    public static <E> ConcurrentHashMultiset<E> create(Iterable<? extends E> elements) {
        ConcurrentHashMultiset<E> concurrentHashMultisetCreate = create();
        Iterables.addAll(concurrentHashMultisetCreate, elements);
        return concurrentHashMultisetCreate;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ((ArrayList) snapshot()).toArray(tArr);
    }

    public static <E> ConcurrentHashMultiset<E> create(ConcurrentMap<E, AtomicInteger> countMap) {
        return new ConcurrentHashMultiset<>(countMap);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(E element, int expectedOldCount, int newCount) {
        element.getClass();
        CollectPreconditions.checkNonnegative(expectedOldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        AtomicInteger atomicInteger = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (atomicInteger == null) {
            if (expectedOldCount != 0) {
                return false;
            }
            return newCount == 0 || this.countMap.putIfAbsent(element, new AtomicInteger(newCount)) == null;
        }
        int i = atomicInteger.get();
        if (i == expectedOldCount) {
            if (i == 0) {
                if (newCount == 0) {
                    this.countMap.remove(element, atomicInteger);
                    return true;
                }
                AtomicInteger atomicInteger2 = new AtomicInteger(newCount);
                return this.countMap.putIfAbsent(element, atomicInteger2) == null || this.countMap.replace(element, atomicInteger, atomicInteger2);
            }
            if (atomicInteger.compareAndSet(i, newCount)) {
                if (newCount == 0) {
                    this.countMap.remove(element, atomicInteger);
                }
                return true;
            }
        }
        return false;
    }
}
