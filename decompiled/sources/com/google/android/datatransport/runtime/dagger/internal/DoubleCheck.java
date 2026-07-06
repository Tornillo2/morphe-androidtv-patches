package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

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

    public static <P extends Provider<T>, T> Lazy<T> lazy(P p) {
        if (p instanceof Lazy) {
            return (Lazy) p;
        }
        p.getClass();
        return new DoubleCheck(p);
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P p) {
        p.getClass();
        return p instanceof DoubleCheck ? p : new DoubleCheck(p);
    }

    public static Object reentrantCheck(Object obj, Object obj2) {
        if (obj == UNINITIALIZED || (obj instanceof MemoizedSentinel) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public T get() {
        T t;
        T t2 = (T) this.instance;
        Object obj = UNINITIALIZED;
        if (t2 != obj) {
            return t2;
        }
        synchronized (this) {
            try {
                t = (T) this.instance;
                if (t == obj) {
                    t = this.provider.get();
                    reentrantCheck(this.instance, t);
                    this.instance = t;
                    this.provider = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }
}
