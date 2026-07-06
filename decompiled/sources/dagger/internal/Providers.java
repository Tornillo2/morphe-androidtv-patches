package dagger.internal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Providers {
    public static <T> Provider<T> asDaggerProvider(final javax.inject.Provider<T> provider) {
        provider.getClass();
        return provider instanceof Provider ? (Provider) provider : new Provider<T>() { // from class: dagger.internal.Providers.1
            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                return (T) provider.get();
            }
        };
    }
}
