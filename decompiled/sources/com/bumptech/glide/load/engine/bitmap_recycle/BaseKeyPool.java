package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.bumptech.glide.util.Util;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseKeyPool<T extends Poolable> {
    public static final int MAX_SIZE = 20;
    public final Queue<T> keyPool = Util.createQueue(20);

    public abstract T create();

    public T get() {
        T tPoll = this.keyPool.poll();
        return tPoll == null ? (T) create() : tPoll;
    }

    public void offer(T t) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(t);
        }
    }
}
