package dagger.internal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DelegateFactory<T> implements Factory<T> {
    public Provider<T> delegate;

    public static <T> void setDelegate(Provider<T> delegateFactory, Provider<T> delegate) {
        setDelegateInternal((DelegateFactory) delegateFactory, delegate);
    }

    public static <T> void setDelegateInternal(DelegateFactory<T> delegateFactory, Provider<T> delegate) {
        delegate.getClass();
        if (delegateFactory.delegate != null) {
            throw new IllegalStateException();
        }
        delegateFactory.delegate = delegate;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public T get() {
        Provider<T> provider = this.delegate;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }

    public Provider<T> getDelegate() {
        Provider<T> provider = this.delegate;
        provider.getClass();
        return provider;
    }

    @Deprecated
    public void setDelegatedProvider(javax.inject.Provider<T> delegate) {
        setDelegateInternal(this, Providers.asDaggerProvider(delegate));
    }

    @Deprecated
    public static <T> void setDelegate(javax.inject.Provider<T> delegateFactory, javax.inject.Provider<T> delegate) {
        setDelegateInternal((DelegateFactory) delegateFactory, Providers.asDaggerProvider(delegate));
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> delegate) {
        setDelegateInternal(this, delegate);
    }
}
