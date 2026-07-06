package dagger.internal;

import dagger.internal.AbstractMapFactory;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MapFactory<K, V> extends AbstractMapFactory<K, V, V> {
    public static final Provider<Map<Object, Object>> EMPTY = InstanceFactory.create(Collections.EMPTY_MAP);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, V> {
        public Builder(int size) {
            super(size);
        }

        public MapFactory<K, V> build() {
            return new MapFactory<>(this.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // dagger.internal.AbstractMapFactory.Builder
        public AbstractMapFactory.Builder put(Object key, Provider providerOfValue) {
            super.put(key, providerOfValue);
            return this;
        }

        @Override // dagger.internal.AbstractMapFactory.Builder
        public AbstractMapFactory.Builder putAll(Provider mapFactory) {
            super.putAll(mapFactory);
            return this;
        }

        public Builder(int i, AnonymousClass1 anonymousClass1) {
            super(i);
        }

        @Override // dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> put(K key, Provider<V> providerOfValue) {
            super.put((Object) key, (Provider) providerOfValue);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> putAll(Provider<Map<K, V>> mapFactory) {
            super.putAll((Provider) mapFactory);
            return this;
        }

        @Deprecated
        public Builder<K, V> put(K key, javax.inject.Provider<V> providerOfValue) {
            super.put((Object) key, (Provider) Providers.asDaggerProvider(providerOfValue));
            return this;
        }

        @Deprecated
        public Builder<K, V> putAll(javax.inject.Provider<Map<K, V>> mapFactory) {
            super.putAll((Provider) Providers.asDaggerProvider(mapFactory));
            return this;
        }
    }

    public MapFactory(Map<K, Provider<V>> map) {
        super(map);
    }

    public static <K, V> Builder<K, V> builder(int size) {
        return new Builder<>(size);
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
