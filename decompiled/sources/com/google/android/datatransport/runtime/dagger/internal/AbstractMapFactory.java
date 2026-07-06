package com.google.android.datatransport.runtime.dagger.internal;

import j$.util.DesugarCollections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {
    public final Map<K, Provider<V>> contributingMap;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Builder<K, V, V2> {
        public final LinkedHashMap<K, Provider<V>> map;

        public Builder(int i) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
        }

        public Builder<K, V, V2> put(K k, Provider<V> provider) {
            LinkedHashMap<K, Provider<V>> linkedHashMap = this.map;
            Preconditions.checkNotNull(k, "key");
            Preconditions.checkNotNull(provider, "provider");
            linkedHashMap.put(k, provider);
            return this;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Builder<K, V, V2> putAll(Provider<Map<K, V2>> provider) {
            if (!(provider instanceof DelegateFactory)) {
                this.map.putAll((Map<? extends K, ? extends Provider<V>>) ((AbstractMapFactory) provider).contributingMap);
                return this;
            }
            Provider<T> provider2 = ((DelegateFactory) provider).delegate;
            provider2.getClass();
            return putAll(provider2);
        }
    }

    public AbstractMapFactory(Map<K, Provider<V>> map) {
        this.contributingMap = DesugarCollections.unmodifiableMap(map);
    }

    public final Map<K, Provider<V>> contributingMap() {
        return this.contributingMap;
    }
}
