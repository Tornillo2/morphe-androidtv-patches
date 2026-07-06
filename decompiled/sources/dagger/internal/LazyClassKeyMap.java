package dagger.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LazyClassKeyMap<V> implements Map<Class<?>, V> {
    public final Map<String, V> delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MapFactory<V> implements Factory<Map<Class<?>, V>> {
        public Factory<Map<String, V>> delegate;

        public MapFactory(Factory<Map<String, V>> delegate) {
            this.delegate = delegate;
        }

        public static <V> MapFactory<V> of(Factory<Map<String, V>> delegate) {
            return new MapFactory<>(delegate);
        }

        @Override // javax.inject.Provider, jakarta.inject.Provider
        public Map<Class<?>, V> get() {
            return new LazyClassKeyMap(this.delegate.get());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MapProviderFactory<V> implements Factory<Map<Class<?>, Provider<V>>> {
        public Factory<Map<String, Provider<V>>> delegate;

        public MapProviderFactory(Factory<Map<String, Provider<V>>> delegate) {
            this.delegate = delegate;
        }

        public static <V> MapProviderFactory<V> of(Factory<Map<String, Provider<V>>> delegate) {
            return new MapProviderFactory<>(delegate);
        }

        @Override // javax.inject.Provider, jakarta.inject.Provider
        public Map<Class<?>, Provider<V>> get() {
            return new LazyClassKeyMap(this.delegate.get());
        }
    }

    public LazyClassKeyMap(Map<String, V> delegate) {
        this.delegate = delegate;
    }

    public static <V> Map<Class<?>, V> of(Map<String, V> delegate) {
        return new LazyClassKeyMap(delegate);
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        if (key instanceof Class) {
            return this.delegate.containsKey(((Class) key).getName());
        }
        throw new IllegalArgumentException("Key must be a class");
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return this.delegate.containsValue(value);
    }

    @Override // java.util.Map
    public Set<Map.Entry<Class<?>, V>> entrySet() {
        throw new UnsupportedOperationException("Maps created with @LazyClassKey do not support usage of entrySet(). Consider @ClassKey instead.");
    }

    @Override // java.util.Map
    public V get(Object key) {
        if (key instanceof Class) {
            return this.delegate.get(((Class) key).getName());
        }
        throw new IllegalArgumentException("Key must be a class");
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public Set<Class<?>> keySet() {
        throw new UnsupportedOperationException("Maps created with @LazyClassKey do not support usage of keySet(). Consider @ClassKey instead.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Class<?> key, Object value) {
        put2(key, value);
        throw null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends Class<?>, ? extends V> map) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public V remove(Object key) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public int size() {
        return this.delegate.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.delegate.values();
    }

    /* JADX INFO: renamed from: put, reason: avoid collision after fix types in other method */
    public V put2(Class<?> key, V value) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }
}
