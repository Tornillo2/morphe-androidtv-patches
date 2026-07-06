package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class CacheLoader<K, V> {

    /* JADX INFO: renamed from: com.google.common.cache.CacheLoader$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends CacheLoader<K, V> {
        public final /* synthetic */ Executor val$executor;

        public AnonymousClass1(final Executor val$executor) {
            this.val$executor = val$executor;
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(K k) throws Exception {
            return (V) CacheLoader.this.load(k);
        }

        @Override // com.google.common.cache.CacheLoader
        public Map<K, V> loadAll(Iterable<? extends K> keys) throws Exception {
            return CacheLoader.this.loadAll(keys);
        }

        @Override // com.google.common.cache.CacheLoader
        public ListenableFuture<V> reload(final K key, final V oldValue) {
            final CacheLoader cacheLoader = CacheLoader.this;
            ListenableFutureTask listenableFutureTaskCreate = ListenableFutureTask.create(new Callable() { // from class: com.google.common.cache.CacheLoader$1$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return cacheLoader.reload(key, oldValue).get();
                }
            });
            this.val$executor.execute(listenableFutureTaskCreate);
            return listenableFutureTaskCreate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FunctionToCacheLoader<K, V> extends CacheLoader<K, V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Function<K, V> computingFunction;

        public FunctionToCacheLoader(Function<K, V> computingFunction) {
            computingFunction.getClass();
            this.computingFunction = computingFunction;
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(K key) {
            Function<K, V> function = this.computingFunction;
            key.getClass();
            return function.apply(key);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String message) {
            super(message);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SupplierToCacheLoader<V> extends CacheLoader<Object, V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Supplier<V> computingSupplier;

        public SupplierToCacheLoader(Supplier<V> computingSupplier) {
            computingSupplier.getClass();
            this.computingSupplier = computingSupplier;
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(Object key) {
            key.getClass();
            return this.computingSupplier.get();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
    }

    @GwtIncompatible
    public static <K, V> CacheLoader<K, V> asyncReloading(CacheLoader<K, V> loader, Executor executor) {
        loader.getClass();
        executor.getClass();
        return loader.new AnonymousClass1(executor);
    }

    public static <K, V> CacheLoader<K, V> from(Function<K, V> function) {
        return new FunctionToCacheLoader(function);
    }

    public abstract V load(K key) throws Exception;

    public Map<K, V> loadAll(Iterable<? extends K> keys) throws Exception {
        throw new UnsupportedLoadingOperationException();
    }

    @GwtIncompatible
    public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
        key.getClass();
        oldValue.getClass();
        return Futures.immediateFuture(load(key));
    }

    public static <V> CacheLoader<Object, V> from(Supplier<V> supplier) {
        return new SupplierToCacheLoader(supplier);
    }
}
