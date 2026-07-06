package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ModelCache<A, B> {
    public static final int DEFAULT_SIZE = 250;
    public final LruCache<ModelKey<A>, B> cache;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class ModelKey<A> {
        public static final Queue<ModelKey<?>> KEY_QUEUE = Util.createQueue(0);
        public int height;
        public A model;
        public int width;

        public static <A> ModelKey<A> get(A a, int i, int i2) {
            ModelKey<A> modelKey;
            Queue<ModelKey<?>> queue = KEY_QUEUE;
            synchronized (queue) {
                modelKey = (ModelKey) queue.poll();
            }
            if (modelKey == null) {
                modelKey = new ModelKey<>();
            }
            modelKey.init(a, i, i2);
            return modelKey;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ModelKey) {
                ModelKey modelKey = (ModelKey) obj;
                if (this.width == modelKey.width && this.height == modelKey.height && this.model.equals(modelKey.model)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.model.hashCode() + (((this.height * 31) + this.width) * 31);
        }

        public final void init(A a, int i, int i2) {
            this.model = a;
            this.width = i;
            this.height = i2;
        }

        public void release() {
            Queue<ModelKey<?>> queue = KEY_QUEUE;
            synchronized (queue) {
                queue.offer(this);
            }
        }
    }

    public ModelCache() {
        this(250L);
    }

    public void clear() {
        this.cache.clearMemory();
    }

    @Nullable
    public B get(A a, int i, int i2) {
        ModelKey<A> modelKey = ModelKey.get(a, i, i2);
        B b = this.cache.get(modelKey);
        modelKey.release();
        return b;
    }

    public void put(A a, int i, int i2, B b) {
        this.cache.put(ModelKey.get(a, i, i2), b);
    }

    public ModelCache(long j) {
        this.cache = new LruCache<ModelKey<A>, B>(j) { // from class: com.bumptech.glide.load.model.ModelCache.1
            @Override // com.bumptech.glide.util.LruCache
            public void onItemEvicted(@NonNull ModelKey<A> modelKey, @Nullable B b) {
                modelKey.release();
            }
        };
    }
}
