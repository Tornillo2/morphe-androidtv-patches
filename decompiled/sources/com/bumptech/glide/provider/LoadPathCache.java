package com.bumptech.glide.provider;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.util.MultiClassKey;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LoadPathCache {
    public static final LoadPath<?, ?, ?> NO_PATHS_SIGNAL = new LoadPath<>(Object.class, Object.class, Object.class, Collections.singletonList(new DecodePath(Object.class, Object.class, Object.class, Collections.EMPTY_LIST, new UnitTranscoder(), null)), null);
    public final ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> cache = new ArrayMap<>();
    public final AtomicReference<MultiClassKey> keyRef = new AtomicReference<>();

    @Nullable
    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> get(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        LoadPath<Data, TResource, Transcode> loadPath;
        MultiClassKey key = getKey(cls, cls2, cls3);
        synchronized (this.cache) {
            loadPath = (LoadPath) this.cache.get(key);
        }
        this.keyRef.set(key);
        return loadPath;
    }

    public final MultiClassKey getKey(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        MultiClassKey andSet = this.keyRef.getAndSet(null);
        if (andSet == null) {
            andSet = new MultiClassKey();
        }
        andSet.set(cls, cls2, cls3);
        return andSet;
    }

    public boolean isEmptyLoadPath(@Nullable LoadPath<?, ?, ?> loadPath) {
        return NO_PATHS_SIGNAL.equals(loadPath);
    }

    public void put(Class<?> cls, Class<?> cls2, Class<?> cls3, @Nullable LoadPath<?, ?, ?> loadPath) {
        synchronized (this.cache) {
            ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> arrayMap = this.cache;
            MultiClassKey multiClassKey = new MultiClassKey(cls, cls2, cls3);
            if (loadPath == null) {
                loadPath = NO_PATHS_SIGNAL;
            }
            arrayMap.put(multiClassKey, loadPath);
        }
    }
}
