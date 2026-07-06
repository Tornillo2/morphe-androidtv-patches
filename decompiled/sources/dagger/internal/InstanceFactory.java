package dagger.internal;

import dagger.Lazy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class InstanceFactory<T> implements Factory<T>, Lazy<T> {
    public static final InstanceFactory<Object> NULL_INSTANCE_FACTORY = new InstanceFactory<>(null);
    public final T instance;

    public InstanceFactory(T instance) {
        this.instance = instance;
    }

    public static <T> Factory<T> create(T instance) {
        Preconditions.checkNotNull(instance, "instance cannot be null");
        return new InstanceFactory(instance);
    }

    public static <T> Factory<T> createNullable(T instance) {
        return instance == null ? NULL_INSTANCE_FACTORY : new InstanceFactory(instance);
    }

    public static <T> InstanceFactory<T> nullInstanceFactory() {
        return (InstanceFactory<T>) NULL_INSTANCE_FACTORY;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public T get() {
        return this.instance;
    }
}
