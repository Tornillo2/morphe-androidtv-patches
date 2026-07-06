package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DelegateFactory<T> implements Factory<T> {
    public Provider<T> delegate;

    public static <T> void setDelegate(Provider<T> provider, Provider<T> provider2) {
        provider2.getClass();
        DelegateFactory delegateFactory = (DelegateFactory) provider;
        if (delegateFactory.delegate != null) {
            throw new IllegalStateException();
        }
        delegateFactory.delegate = provider2;
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
    public void setDelegatedProvider(Provider<T> provider) {
        setDelegate(this, provider);
    }
}
