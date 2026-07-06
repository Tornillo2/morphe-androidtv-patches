package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LoadPath<Data, ResourceType, Transcode> {
    public final Class<Data> dataClass;
    public final List<? extends DecodePath<Data, ResourceType, Transcode>> decodePaths;
    public final String failureMessage;
    public final Pools.Pool<List<Throwable>> listPool;

    public LoadPath(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<DecodePath<Data, ResourceType, Transcode>> list, Pools.Pool<List<Throwable>> pool) {
        this.dataClass = cls;
        this.listPool = pool;
        Preconditions.checkNotEmpty(list);
        this.decodePaths = list;
        this.failureMessage = "Failed LoadPath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    public Class<Data> getDataClass() {
        return this.dataClass;
    }

    public Resource<Transcode> load(DataRewinder<Data> dataRewinder, @NonNull Options options, int i, int i2, DecodePath.DecodeCallback<ResourceType> decodeCallback) throws GlideException {
        List<Throwable> listAcquire = this.listPool.acquire();
        Preconditions.checkNotNull(listAcquire, "Argument must not be null");
        List<Throwable> list = listAcquire;
        try {
            return loadWithExceptionList(dataRewinder, options, i, i2, decodeCallback, list);
        } finally {
            this.listPool.release(list);
        }
    }

    public final Resource<Transcode> loadWithExceptionList(DataRewinder<Data> dataRewinder, @NonNull Options options, int i, int i2, DecodePath.DecodeCallback<ResourceType> decodeCallback, List<Throwable> list) throws GlideException {
        int size = this.decodePaths.size();
        Resource<Transcode> resourceDecode = null;
        for (int i3 = 0; i3 < size; i3++) {
            try {
                resourceDecode = this.decodePaths.get(i3).decode(dataRewinder, i, i2, options, decodeCallback);
            } catch (GlideException e) {
                list.add(e);
            }
            if (resourceDecode != null) {
                break;
            }
        }
        if (resourceDecode != null) {
            return resourceDecode;
        }
        throw new GlideException(this.failureMessage, new ArrayList(list));
    }

    public String toString() {
        return "LoadPath{decodePaths=" + Arrays.toString(this.decodePaths.toArray()) + '}';
    }
}
