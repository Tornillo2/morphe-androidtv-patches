package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class AbstractBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient Map<K, V> delegate;

    @LazyInit
    public transient Set<Map.Entry<K, V>> entrySet;

    @RetainedWith
    public transient AbstractBiMap<V, K> inverse;

    @LazyInit
    public transient Set<K> keySet;

    @LazyInit
    public transient Set<V> valueSet;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class BiMapEntry extends ForwardingMapEntry<K, V> {
        public final Map.Entry<K, V> delegate;

        public BiMapEntry(Map.Entry<K, V> delegate) {
            this.delegate = delegate;
        }

        @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
        public V setValue(V value) {
            AbstractBiMap.this.checkValue(value);
            Preconditions.checkState(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
            if (Objects.equal(value, getValue())) {
                return value;
            }
            Preconditions.checkArgument(!AbstractBiMap.this.containsValue(value), "value already present: %s", value);
            V value2 = this.delegate.setValue(value);
            Preconditions.checkState(Objects.equal(value, AbstractBiMap.this.get(getKey())), "entry no longer in map");
            AbstractBiMap.this.updateInverseMap(getKey(), true, value2, value);
            return value2;
        }

        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
        public final Set<Map.Entry<K, V>> esDelegate;

        public EntrySet() {
            this.esDelegate = AbstractBiMap.this.delegate.entrySet();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return Maps.containsEntryImpl(delegate(), o);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            return standardContainsAll(c);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractBiMap.this.entrySetIterator();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object object) {
            if (!this.esDelegate.contains(object) || !(object instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) object;
            AbstractBiMap.this.inverse.delegate.remove(entry.getValue());
            this.esDelegate.remove(entry);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            return standardRemoveAll(c);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            return standardRetainAll(c);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<Map.Entry<K, V>> delegate() {
            return this.esDelegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Inverse<K, V> extends AbstractBiMap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public Inverse(Map<K, V> backward, AbstractBiMap<V, K> forward) {
            super((Map) backward, (AbstractBiMap) forward);
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
            stream.defaultReadObject();
            Object object = stream.readObject();
            j$.util.Objects.requireNonNull(object);
            setInverse((AbstractBiMap) object);
        }

        @Override // com.google.common.collect.AbstractBiMap
        @ParametricNullness
        public K checkKey(@ParametricNullness K key) {
            return this.inverse.checkValue(key);
        }

        @Override // com.google.common.collect.AbstractBiMap
        @ParametricNullness
        public V checkValue(@ParametricNullness V value) {
            return this.inverse.checkKey(value);
        }

        @Override // com.google.common.collect.AbstractBiMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.delegate;
        }

        @J2ktIncompatible
        @GwtIncompatible
        public Object readResolve() {
            return inverse().inverse();
        }

        @Override // com.google.common.collect.AbstractBiMap, com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
        public /* bridge */ /* synthetic */ Collection values() {
            return super.values();
        }

        @J2ktIncompatible
        @GwtIncompatible
        public final void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(inverse());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ValueSet extends ForwardingSet<V> {
        public final Set<V> valuesDelegate;

        public ValueSet() {
            this.valuesDelegate = AbstractBiMap.this.inverse.keySet();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return new Maps.AnonymousClass2(AbstractBiMap.this.entrySet().iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<V> delegate() {
            return this.valuesDelegate;
        }
    }

    public AbstractBiMap(Map<K, V> backward, AbstractBiMap<V, K> forward) {
        this.delegate = backward;
        this.inverse = forward;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public void clear() {
        this.delegate.clear();
        this.inverse.delegate.clear();
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public boolean containsValue(Object value) {
        return this.inverse.containsKey(value);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        final Iterator<Map.Entry<K, V>> it = this.delegate.entrySet().iterator();
        return new Iterator<Map.Entry<K, V>>(this) { // from class: com.google.common.collect.AbstractBiMap.1
            public Map.Entry<K, V> entry;
            public final /* synthetic */ AbstractBiMap this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                Map.Entry<K, V> entry = this.entry;
                if (entry == null) {
                    throw new IllegalStateException("no calls to next() since the last call to remove()");
                }
                V value = entry.getValue();
                it.remove();
                this.this$0.removeFromInverseMap(value);
                this.entry = null;
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                Map.Entry<K, V> entry = (Map.Entry) it.next();
                this.entry = entry;
                return new BiMapEntry(entry);
            }
        };
    }

    @Override // com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    public V forcePut(@ParametricNullness K key, @ParametricNullness V value) {
        return putInBothMaps(key, value, true);
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        return this.inverse;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    public AbstractBiMap<V, K> makeInverse(Map<V, K> backward) {
        return new Inverse(backward, this);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    public V put(@ParametricNullness K key, @ParametricNullness V value) {
        return putInBothMaps(key, value, false);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public final V putInBothMaps(@ParametricNullness K key, @ParametricNullness V value, boolean force) {
        checkKey(key);
        checkValue(value);
        boolean zContainsKey = containsKey(key);
        if (zContainsKey && Objects.equal(value, get(key))) {
            return value;
        }
        if (force) {
            inverse().remove(value);
        } else {
            Preconditions.checkArgument(!containsValue(value), "value already present: %s", value);
        }
        V vPut = this.delegate.put(key, value);
        updateInverseMap(key, zContainsKey, vPut, value);
        return vPut;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(Object key) {
        if (containsKey(key)) {
            return removeFromBothMaps(key);
        }
        return null;
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public final V removeFromBothMaps(Object key) {
        V vRemove = this.delegate.remove(key);
        removeFromInverseMap(vRemove);
        return vRemove;
    }

    public final void removeFromInverseMap(@ParametricNullness V oldValue) {
        this.inverse.delegate.remove(oldValue);
    }

    public void setDelegates(Map<K, V> forward, Map<V, K> backward) {
        Preconditions.checkState(this.delegate == null);
        Preconditions.checkState(this.inverse == null);
        Preconditions.checkArgument(forward.isEmpty());
        Preconditions.checkArgument(backward.isEmpty());
        Preconditions.checkArgument(forward != backward);
        this.delegate = forward;
        this.inverse = makeInverse(backward);
    }

    public void setInverse(AbstractBiMap<V, K> inverse) {
        this.inverse = inverse;
    }

    public final void updateInverseMap(@ParametricNullness K key, boolean containedKey, V oldValue, @ParametricNullness V newValue) {
        if (containedKey) {
            removeFromInverseMap(oldValue);
        }
        this.inverse.delegate.put(newValue, key);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class KeySet extends ForwardingSet<K> {
        public KeySet() {
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new Maps.AnonymousClass1(AbstractBiMap.this.entrySet().iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object key) {
            if (!contains(key)) {
                return false;
            }
            AbstractBiMap.this.removeFromBothMaps(key);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> keysToRemove) {
            return standardRemoveAll(keysToRemove);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> keysToRetain) {
            return standardRetainAll(keysToRetain);
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<K> delegate() {
            return AbstractBiMap.this.delegate.keySet();
        }
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<K, V> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set != null) {
            return set;
        }
        ValueSet valueSet = new ValueSet();
        this.valueSet = valueSet;
        return valueSet;
    }

    public AbstractBiMap(Map<K, V> forward, Map<V, K> backward) {
        setDelegates(forward, backward);
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public K checkKey(@ParametricNullness K key) {
        return key;
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public V checkValue(@ParametricNullness V value) {
        return value;
    }
}
