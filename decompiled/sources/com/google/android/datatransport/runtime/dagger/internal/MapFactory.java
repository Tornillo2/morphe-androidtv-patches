package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MapFactory<K, V> extends AbstractMapFactory<K, V, V> {
    public static final Provider<Map<Object, Object>> EMPTY = InstanceFactory.create(Collections.EMPTY_MAP);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, V> {
        public Builder(int i) {
            super(i);
        }

        public MapFactory<K, V> build() {
            return new MapFactory<>(this.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public AbstractMapFactory.Builder put(Object obj, Provider provider) {
            super.put(obj, provider);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public AbstractMapFactory.Builder putAll(Provider provider) {
            super.putAll(provider);
            return this;
        }

        public Builder(int i, AnonymousClass1 anonymousClass1) {
            super(i);
        }

        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> put(K k, Provider<V> provider) {
            super.put((Object) k, (Provider) provider);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> putAll(Provider<Map<K, V>> provider) {
            super.putAll((Provider) provider);
            return this;
        }
    }

    public MapFactory(Map<K, Provider<V>> map) {
        super(map);
    }

    public static <K, V> Builder<K, V> builder(int i) {
        return new Builder<>(i);
    }

    public static <K, V> Provider<Map<K, V>> emptyMapProvider() {
        return (Provider<Map<K, V>>) EMPTY;
    }

    public MapFactory(Map map, AnonymousClass1 anonymousClass1) {
        super(map);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Map<K, V> get() {
        LinkedHashMap linkedHashMapNewLinkedHashMapWithExpectedSize = DaggerCollections.newLinkedHashMapWithExpectedSize(this.contributingMap.size());
        for (Map.Entry<K, Provider<V>> entry : this.contributingMap.entrySet()) {
            linkedHashMapNewLinkedHashMapWithExpectedSize.put(entry.getKey(), entry.getValue().get());
        }
        return DesugarCollections.unmodifiableMap(linkedHashMapNewLinkedHashMapWithExpectedSize);
    }
}
