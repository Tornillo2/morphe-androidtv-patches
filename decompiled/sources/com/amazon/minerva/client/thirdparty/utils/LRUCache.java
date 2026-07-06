package com.amazon.minerva.client.thirdparty.utils;

import j$.util.Objects;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    public final int CAPACITY;
    public LinkedHashMap<K, V> map;

    public LRUCache(int i) {
        super(i, 0.75f, true);
        this.CAPACITY = i;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Objects.requireNonNull(obj, "Key cannot be null in get method.");
        if (containsKey(obj)) {
            return (V) super.get(obj);
        }
        return null;
    }

    public int getCapacity() {
        return this.CAPACITY;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Objects.requireNonNull(k, "Key cannot be null in put method.");
        Objects.requireNonNull(v, "Value cannot be null in put method.");
        return (V) super.put(k, v);
    }

    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry entry) {
        return size() > this.CAPACITY;
    }
}
