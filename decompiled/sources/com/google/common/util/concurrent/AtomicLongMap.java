package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtCompatible
public final class AtomicLongMap<K> implements Serializable {

    @LazyInit
    public transient Map<K, Long> asMap;
    public final ConcurrentHashMap<K, AtomicLong> map;

    public AtomicLongMap(ConcurrentHashMap<K, AtomicLong> map) {
        map.getClass();
        this.map = map;
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    @CanIgnoreReturnValue
    public long addAndGet(K key, long delta) {
        AtomicLong atomicLongPutIfAbsent;
        long j;
        long j2;
        do {
            atomicLongPutIfAbsent = this.map.get(key);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(key, new AtomicLong(delta))) == null) {
                break;
            }
            do {
                j = atomicLongPutIfAbsent.get();
                if (j != 0) {
                    j2 = j + delta;
                }
            } while (!atomicLongPutIfAbsent.compareAndSet(j, j2));
            return j2;
        } while (!this.map.replace(key, atomicLongPutIfAbsent, new AtomicLong(delta)));
        return delta;
    }

    public Map<K, Long> asMap() {
        Map<K, Long> map = this.asMap;
        if (map != null) {
            return map;
        }
        Map<K, Long> mapCreateAsMap = createAsMap();
        this.asMap = mapCreateAsMap;
        return mapCreateAsMap;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public final Map<K, Long> createAsMap() {
        return DesugarCollections.unmodifiableMap(Maps.transformValues(this.map, new Function<AtomicLong, Long>() { // from class: com.google.common.util.concurrent.AtomicLongMap.1
            @Override // com.google.common.base.Function
            public Long apply(AtomicLong atomic) {
                return Long.valueOf(atomic.get());
            }
        }));
    }

    @CanIgnoreReturnValue
    public long decrementAndGet(K key) {
        return addAndGet(key, -1L);
    }

    public long get(K key) {
        AtomicLong atomicLong = this.map.get(key);
        if (atomicLong == null) {
            return 0L;
        }
        return atomicLong.get();
    }

    @CanIgnoreReturnValue
    public long getAndAdd(K key, long delta) {
        AtomicLong atomicLongPutIfAbsent;
        long j;
        do {
            atomicLongPutIfAbsent = this.map.get(key);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(key, new AtomicLong(delta))) == null) {
                return 0L;
            }
            do {
                j = atomicLongPutIfAbsent.get();
                if (j == 0) {
                }
            } while (!atomicLongPutIfAbsent.compareAndSet(j, j + delta));
            return j;
        } while (!this.map.replace(key, atomicLongPutIfAbsent, new AtomicLong(delta)));
        return 0L;
    }

    @CanIgnoreReturnValue
    public long getAndDecrement(K key) {
        return getAndAdd(key, -1L);
    }

    @CanIgnoreReturnValue
    public long getAndIncrement(K key) {
        return getAndAdd(key, 1L);
    }

    @CanIgnoreReturnValue
    public long incrementAndGet(K key) {
        return addAndGet(key, 1L);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @CanIgnoreReturnValue
    public long put(K key, long newValue) {
        AtomicLong atomicLongPutIfAbsent;
        long j;
        do {
            atomicLongPutIfAbsent = this.map.get(key);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(key, new AtomicLong(newValue))) == null) {
                return 0L;
            }
            do {
                j = atomicLongPutIfAbsent.get();
                if (j == 0) {
                }
            } while (!atomicLongPutIfAbsent.compareAndSet(j, newValue));
            return j;
        } while (!this.map.replace(key, atomicLongPutIfAbsent, new AtomicLong(newValue)));
        return 0L;
    }

    public void putAll(Map<? extends K, ? extends Long> m) {
        for (Map.Entry<? extends K, ? extends Long> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue().longValue());
        }
    }

    public long putIfAbsent(K key, long newValue) {
        AtomicLong atomicLongPutIfAbsent;
        do {
            atomicLongPutIfAbsent = this.map.get(key);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(key, new AtomicLong(newValue))) == null) {
                return 0L;
            }
            long j = atomicLongPutIfAbsent.get();
            if (j != 0) {
                return j;
            }
        } while (!this.map.replace(key, atomicLongPutIfAbsent, new AtomicLong(newValue)));
        return 0L;
    }

    @CanIgnoreReturnValue
    public long remove(K key) {
        long j;
        AtomicLong atomicLong = this.map.get(key);
        if (atomicLong == null) {
            return 0L;
        }
        do {
            j = atomicLong.get();
            if (j == 0) {
                break;
            }
        } while (!atomicLong.compareAndSet(j, 0L));
        this.map.remove(key, atomicLong);
        return j;
    }

    public void removeAllZeros() {
        Iterator<Map.Entry<K, AtomicLong>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            AtomicLong value = it.next().getValue();
            if (value != null && value.get() == 0) {
                it.remove();
            }
        }
    }

    @CanIgnoreReturnValue
    public boolean removeIfZero(K key) {
        return remove(key, 0L);
    }

    public boolean replace(K key, long expectedOldValue, long newValue) {
        if (expectedOldValue == 0) {
            return putIfAbsent(key, newValue) == 0;
        }
        AtomicLong atomicLong = this.map.get(key);
        if (atomicLong == null) {
            return false;
        }
        return atomicLong.compareAndSet(expectedOldValue, newValue);
    }

    public int size() {
        return this.map.size();
    }

    public long sum() {
        Iterator<AtomicLong> it = this.map.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().get();
        }
        return j;
    }

    public String toString() {
        return this.map.toString();
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> m) {
        AtomicLongMap<K> atomicLongMapCreate = create();
        atomicLongMapCreate.putAll(m);
        return atomicLongMapCreate;
    }

    public boolean remove(K key, long value) {
        AtomicLong atomicLong = this.map.get(key);
        if (atomicLong == null) {
            return false;
        }
        long j = atomicLong.get();
        if (j != value) {
            return false;
        }
        if (j != 0 && !atomicLong.compareAndSet(j, 0L)) {
            return false;
        }
        this.map.remove(key, atomicLong);
        return true;
    }
}
