package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Provider<T> provider;

    public ProviderOfLazy(Provider<T> provider) {
        this.provider = provider;
    }

    public static <T> Provider<Lazy<T>> create(Provider<T> provider) {
        provider.getClass();
        return new ProviderOfLazy(provider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.provider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return DoubleCheck.lazy(this.provider);
    }
}
