package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Poolable;
import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Poolable<T extends Poolable<T>> implements Closeable {
    public final Pool<T> pool;

    public Poolable(Pool<T> pool) {
        this.pool = pool;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.pool.returnToPool(this);
    }
}
