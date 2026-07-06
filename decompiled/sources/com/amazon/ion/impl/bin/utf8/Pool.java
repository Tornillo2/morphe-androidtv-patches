package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Poolable;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Pool<T extends Poolable<?>> {
    public static final int MAX_QUEUE_SIZE = 128;
    public final Allocator<T> allocator;
    public final ArrayBlockingQueue<T> bufferQueue = new ArrayBlockingQueue<>(128);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Allocator<T extends Poolable<?>> {
        T newInstance(Pool<T> pool);
    }

    public Pool(Allocator<T> allocator) {
        this.allocator = allocator;
    }

    public T getOrCreate() {
        T tPoll = this.bufferQueue.poll();
        return tPoll == null ? (T) this.allocator.newInstance(this) : tPoll;
    }

    public void returnToPool(T t) {
        this.bufferQueue.offer(t);
    }
}
