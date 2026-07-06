package dagger.internal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SingleCheck<T> implements Provider<T> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Object UNINITIALIZED = new Object();
    public volatile Object instance = UNINITIALIZED;
    public volatile Provider<T> provider;

    public SingleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    public static <T> Provider<T> provider(Provider<T> provider) {
        if ((provider instanceof SingleCheck) || (provider instanceof DoubleCheck)) {
            return provider;
        }
        provider.getClass();
        return new SingleCheck(provider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public T get() {
        T t = (T) this.instance;
        if (t != UNINITIALIZED) {
            return t;
        }
        Provider<T> provider = this.provider;
        if (provider == null) {
            return (T) this.instance;
        }
        T t2 = provider.get();
        this.instance = t2;
        this.provider = null;
        return t2;
    }

    public static <P extends javax.inject.Provider<T>, T> javax.inject.Provider<T> provider(P delegate) {
        return provider(Providers.asDaggerProvider(delegate));
    }
}
