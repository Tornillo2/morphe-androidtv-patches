package dagger.internal;

import dagger.Lazy;
import dagger.internal.AbstractMapFactory;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, Provider<V>> implements Lazy<Map<K, Provider<V>>> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, Provider<V>> {
        public Builder(int size) {
            super(size);
        }

        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // dagger.internal.AbstractMapFactory.Builder
        public AbstractMapFactory.Builder put(Object key, Provider providerOfValue) {
            super.put(key, providerOfValue);
            return this;
        }

        @Override // dagger.internal.AbstractMapFactory.Builder
        public AbstractMapFactory.Builder putAll(Provider mapProviderFactory) {
            super.putAll(mapProviderFactory);
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
        public Builder<K, V> putAll(Provider<Map<K, Provider<V>>> mapProviderFactory) {
            super.putAll((Provider) mapProviderFactory);
            return this;
        }

        @Deprecated
        public Builder<K, V> put(K key, javax.inject.Provider<V> providerOfValue) {
            super.put((Object) key, (Provider) Providers.asDaggerProvider(providerOfValue));
            return this;
        }

        @Deprecated
        public Builder<K, V> putAll(final javax.inject.Provider<Map<K, javax.inject.Provider<V>>> provider) {
            super.putAll((Provider) new Provider<Map<K, Provider<V>>>() { // from class: dagger.internal.MapProviderFactory.Builder.1
                @Override // javax.inject.Provider, jakarta.inject.Provider
                public Map<K, Provider<V>> get() {
                    Map map = (Map) provider.get();
                    if (map.isEmpty()) {
                        return Collections.EMPTY_MAP;
                    }
                    LinkedHashMap linkedHashMapNewLinkedHashMapWithExpectedSize = DaggerCollections.newLinkedHashMapWithExpectedSize(map.size());
                    for (Map.Entry<K, V> entry : map.entrySet()) {
                        linkedHashMapNewLinkedHashMapWithExpectedSize.put(entry.getKey(), Providers.asDaggerProvider((javax.inject.Provider) entry.getValue()));
                    }
                    return DesugarCollections.unmodifiableMap(linkedHashMapNewLinkedHashMapWithExpectedSize);
                }
            });
            return this;
        }
    }

    public MapProviderFactory(Map<K, Provider<V>> contributingMap) {
        super(contributingMap);
    }

    public static <K, V> Builder<K, V> builder(int size) {
        return new Builder<>(size);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return this.contributingMap;
    }

    public MapProviderFactory(Map map, AnonymousClass1 anonymousClass1) {
        super(map);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Map<K, Provider<V>> get() {
        return this.contributingMap;
    }
}
