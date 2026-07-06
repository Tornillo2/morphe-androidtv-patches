package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ArrayPool {
    public static final int STANDARD_BUFFER_SIZE_BYTES = 65536;

    void clearMemory();

    <T> T get(int i, Class<T> cls);

    <T> T getExact(int i, Class<T> cls);

    <T> void put(T t);

    @Deprecated
    <T> void put(T t, Class<T> cls);

    void trimMemory(int i);
}
