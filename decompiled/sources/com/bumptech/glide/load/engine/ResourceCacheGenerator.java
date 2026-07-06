package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ResourceCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    public File cacheFile;
    public final DataFetcherGenerator.FetcherReadyCallback cb;
    public ResourceCacheKey currentKey;
    public final DecodeHelper<?> helper;
    public volatile ModelLoader.LoadData<?> loadData;
    public int modelLoaderIndex;
    public List<ModelLoader<File, ?>> modelLoaders;
    public int resourceClassIndex = -1;
    public int sourceIdIndex;
    public Key sourceKey;

    public ResourceCacheGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.cb = fetcherReadyCallback;
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.loadData;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        this.cb.onDataFetcherReady(this.sourceKey, obj, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE, this.currentKey);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(@NonNull Exception exc) {
        this.cb.onDataFetcherFailed(this.currentKey, exc, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        List<Key> cacheKeys = this.helper.getCacheKeys();
        boolean z = false;
        if (!cacheKeys.isEmpty()) {
            List<Class<?>> registeredResourceClasses = this.helper.getRegisteredResourceClasses();
            if (!registeredResourceClasses.isEmpty()) {
                while (true) {
                    if (this.modelLoaders != null && hasNextModelLoader()) {
                        this.loadData = null;
                        while (!z && hasNextModelLoader()) {
                            List<ModelLoader<File, ?>> list = this.modelLoaders;
                            int i = this.modelLoaderIndex;
                            this.modelLoaderIndex = i + 1;
                            ModelLoader<File, ?> modelLoader = list.get(i);
                            File file = this.cacheFile;
                            DecodeHelper<?> decodeHelper = this.helper;
                            this.loadData = modelLoader.buildLoadData(file, decodeHelper.width, decodeHelper.height, decodeHelper.options);
                            if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                                this.loadData.fetcher.loadData(this.helper.priority, this);
                                z = true;
                            }
                        }
                        return z;
                    }
                    int i2 = this.resourceClassIndex + 1;
                    this.resourceClassIndex = i2;
                    if (i2 >= registeredResourceClasses.size()) {
                        int i3 = this.sourceIdIndex + 1;
                        this.sourceIdIndex = i3;
                        if (i3 >= cacheKeys.size()) {
                            break;
                        }
                        this.resourceClassIndex = 0;
                    }
                    Key key = cacheKeys.get(this.sourceIdIndex);
                    Class<?> cls = registeredResourceClasses.get(this.resourceClassIndex);
                    Transformation<Z> transformation = this.helper.getTransformation(cls);
                    ArrayPool arrayPool = this.helper.glideContext.getArrayPool();
                    DecodeHelper<?> decodeHelper2 = this.helper;
                    this.currentKey = new ResourceCacheKey(arrayPool, key, decodeHelper2.signature, decodeHelper2.width, decodeHelper2.height, transformation, cls, decodeHelper2.options);
                    File file2 = decodeHelper2.diskCacheProvider.getDiskCache().get(this.currentKey);
                    this.cacheFile = file2;
                    if (file2 != null) {
                        this.sourceKey = key;
                        this.modelLoaders = this.helper.getModelLoaders(file2);
                        this.modelLoaderIndex = 0;
                    }
                }
            } else if (!File.class.equals(this.helper.transcodeClass)) {
                throw new IllegalStateException("Failed to find any load path from " + this.helper.model.getClass() + " to " + this.helper.transcodeClass);
            }
        }
        return false;
    }
}
