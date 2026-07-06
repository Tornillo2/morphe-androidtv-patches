package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingMap<K, V> extends ForwardingObject implements Map<K, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class StandardEntrySet extends Maps.EntrySet<K, V> {
        public StandardEntrySet() {
        }

        @Override // com.google.common.collect.Maps.EntrySet
        public Map<K, V> map() {
            return ForwardingMap.this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class StandardKeySet extends Maps.KeySet<K, V> {
        public StandardKeySet() {
            super(ForwardingMap.this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class StandardValues extends Maps.Values<K, V> {
        public StandardValues() {
            super(ForwardingMap.this);
        }
    }

    public void clear() {
        delegate().clear();
    }

    public boolean containsKey(Object key) {
        return delegate().containsKey(key);
    }

    public boolean containsValue(Object value) {
        return delegate().containsValue(value);
    }

    @Override // com.google.common.collect.ForwardingObject
    public abstract Map<K, V> delegate();

    public Set<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    public boolean equals(Object object) {
        return object == this || delegate().equals(object);
    }

    public V get(Object key) {
        return delegate().get(key);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public Set<K> keySet() {
        return delegate().keySet();
    }

    @CanIgnoreReturnValue
    public V put(@ParametricNullness K key, @ParametricNullness V value) {
        return delegate().put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    @CanIgnoreReturnValue
    public V remove(Object key) {
        return delegate().remove(key);
    }

    public int size() {
        return delegate().size();
    }

    public void standardClear() {
        Iterators.clear(entrySet().iterator());
    }

    public boolean standardContainsKey(Object key) {
        return Maps.containsKeyImpl(this, key);
    }

    public boolean standardContainsValue(Object value) {
        return Maps.containsValueImpl(this, value);
    }

    public boolean standardEquals(Object object) {
        return Maps.equalsImpl(this, object);
    }

    public int standardHashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    public boolean standardIsEmpty() {
        return !entrySet().iterator().hasNext();
    }

    public void standardPutAll(Map<? extends K, ? extends V> map) {
        Maps.putAllImpl(this, map);
    }

    public V standardRemove(Object key) {
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (Objects.equal(next.getKey(), key)) {
                V value = next.getValue();
                it.remove();
                return value;
            }
        }
        return null;
    }

    public String standardToString() {
        return Maps.toStringImpl(this);
    }

    public Collection<V> values() {
        return delegate().values();
    }
}
