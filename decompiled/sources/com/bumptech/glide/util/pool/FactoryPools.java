package com.bumptech.glide.util.pool;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FactoryPools {
    public static final int DEFAULT_POOL_SIZE = 20;
    public static final Resetter<Object> EMPTY_RESETTER = new AnonymousClass1();
    public static final String TAG = "FactoryPools";

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.bumptech.glide.util.pool.FactoryPools$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2<T> implements Factory<List<T>> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        @NonNull
        public List<T> create() {
            return new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.bumptech.glide.util.pool.FactoryPools$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3<T> implements Resetter<List<T>> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Resetter
        public void reset(@NonNull List<T> list) {
            list.clear();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory<T> {
        T create();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FactoryPool<T> implements Pools.Pool<T> {
        public final Factory<T> factory;
        public final Pools.Pool<T> pool;
        public final Resetter<T> resetter;

        public FactoryPool(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory, @NonNull Resetter<T> resetter) {
            this.pool = pool;
            this.factory = factory;
            this.resetter = resetter;
        }

        @Override // androidx.core.util.Pools.Pool
        public T acquire() {
            T tAcquire = this.pool.acquire();
            if (tAcquire == null) {
                tAcquire = this.factory.create();
                if (Log.isLoggable(FactoryPools.TAG, 2)) {
                    Log.v(FactoryPools.TAG, "Created new " + tAcquire.getClass());
                }
            }
            if (tAcquire instanceof Poolable) {
                ((Poolable) tAcquire).getVerifier().setRecycled(false);
            }
            return tAcquire;
        }

        @Override // androidx.core.util.Pools.Pool
        public boolean release(@NonNull T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).getVerifier().setRecycled(true);
            }
            this.resetter.reset(t);
            return this.pool.release(t);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Poolable {
        @NonNull
        StateVerifier getVerifier();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Resetter<T> {
        void reset(@NonNull T t);
    }

    @NonNull
    public static <T> Pools.Pool<T> build(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory, @NonNull Resetter<T> resetter) {
        return new FactoryPool(pool, factory, resetter);
    }

    @NonNull
    public static <T> Resetter<T> emptyResetter() {
        return (Resetter<T>) EMPTY_RESETTER;
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> simple(int i, @NonNull Factory<T> factory) {
        return build(new Pools.SimplePool(i), factory);
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> threadSafe(int i, @NonNull Factory<T> factory) {
        return build(new Pools.SynchronizedPool(i), factory);
    }

    @NonNull
    public static <T> Pools.Pool<List<T>> threadSafeList() {
        return threadSafeList(20);
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> build(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory) {
        return new FactoryPool(pool, factory, EMPTY_RESETTER);
    }

    @NonNull
    public static <T> Pools.Pool<List<T>> threadSafeList(int i) {
        return new FactoryPool(new Pools.SynchronizedPool(i), new AnonymousClass2(), new AnonymousClass3());
    }

    /* JADX INFO: renamed from: com.bumptech.glide.util.pool.FactoryPools$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Resetter<Object> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Resetter
        public void reset(@NonNull Object obj) {
        }
    }
}
