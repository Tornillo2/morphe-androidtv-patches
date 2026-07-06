package dagger.internal;

import dagger.Lazy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Object UNINITIALIZED = new Object();
    public volatile Object instance = UNINITIALIZED;
    public volatile Provider<T> provider;

    public DoubleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    public static <T> Lazy<T> lazy(Provider<T> provider) {
        if (provider instanceof Lazy) {
            return (Lazy) provider;
        }
        provider.getClass();
        return new DoubleCheck(provider);
    }

    public static <T> Provider<T> provider(Provider<T> delegate) {
        delegate.getClass();
        return delegate instanceof DoubleCheck ? delegate : new DoubleCheck(delegate);
    }

    private static Object reentrantCheck(Object currentInstance, Object newInstance) {
        if (currentInstance == UNINITIALIZED || currentInstance == newInstance) {
            return newInstance;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + currentInstance + " & " + newInstance + ". This is likely due to a circular dependency.");
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public T get() {
        T t = (T) this.instance;
        return t == UNINITIALIZED ? (T) getSynchronized() : t;
    }

    public final synchronized Object getSynchronized() {
        Object obj;
        obj = this.instance;
        if (obj == UNINITIALIZED) {
            obj = this.provider.get();
            reentrantCheck(this.instance, obj);
            this.instance = obj;
            this.provider = null;
        }
        return obj;
    }

    @Deprecated
    public static <P extends javax.inject.Provider<T>, T> javax.inject.Provider<T> provider(P delegate) {
        return provider(Providers.asDaggerProvider(delegate));
    }

    public static <P extends javax.inject.Provider<T>, T> Lazy<T> lazy(P provider) {
        return lazy(Providers.asDaggerProvider(provider));
    }
}
