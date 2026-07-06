package org.apache.commons.lang3.concurrent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    public static final Object NO_INIT = new Object();
    public volatile T object = (T) NO_INIT;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() throws ConcurrentException {
        T tInitialize;
        T t = this.object;
        Object obj = NO_INIT;
        if (t != obj) {
            return t;
        }
        synchronized (this) {
            try {
                tInitialize = this.object;
                if (tInitialize == obj) {
                    tInitialize = initialize();
                    this.object = tInitialize;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return tInitialize;
    }

    public abstract T initialize() throws ConcurrentException;
}
