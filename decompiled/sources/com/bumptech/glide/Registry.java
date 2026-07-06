package com.bumptech.glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.DataRewinderRegistry;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ModelLoaderRegistry;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.TranscoderRegistry;
import com.bumptech.glide.provider.EncoderRegistry;
import com.bumptech.glide.provider.ImageHeaderParserRegistry;
import com.bumptech.glide.provider.LoadPathCache;
import com.bumptech.glide.provider.ModelToResourceClassCache;
import com.bumptech.glide.provider.ResourceDecoderRegistry;
import com.bumptech.glide.provider.ResourceEncoderRegistry;
import com.bumptech.glide.util.pool.FactoryPools;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Registry {
    public static final String BUCKET_APPEND_ALL = "legacy_append";
    public static final String BUCKET_BITMAP = "Bitmap";
    public static final String BUCKET_BITMAP_DRAWABLE = "BitmapDrawable";
    public static final String BUCKET_GIF = "Gif";
    public static final String BUCKET_PREPEND_ALL = "legacy_prepend_all";
    public final DataRewinderRegistry dataRewinderRegistry;
    public final ResourceDecoderRegistry decoderRegistry;
    public final EncoderRegistry encoderRegistry;
    public final ImageHeaderParserRegistry imageHeaderParserRegistry;
    public final ModelLoaderRegistry modelLoaderRegistry;
    public final ResourceEncoderRegistry resourceEncoderRegistry;
    public final Pools.Pool<List<Throwable>> throwableListPool;
    public final TranscoderRegistry transcoderRegistry;
    public final ModelToResourceClassCache modelToResourceClassCache = new ModelToResourceClassCache();
    public final LoadPathCache loadPathCache = new LoadPathCache();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(@NonNull String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(@NonNull Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(@NonNull Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public Registry() {
        Pools.Pool<List<Throwable>> poolThreadSafeList = FactoryPools.threadSafeList(20);
        this.throwableListPool = poolThreadSafeList;
        this.modelLoaderRegistry = new ModelLoaderRegistry(poolThreadSafeList);
        this.encoderRegistry = new EncoderRegistry();
        this.decoderRegistry = new ResourceDecoderRegistry();
        this.resourceEncoderRegistry = new ResourceEncoderRegistry();
        this.dataRewinderRegistry = new DataRewinderRegistry();
        this.transcoderRegistry = new TranscoderRegistry();
        this.imageHeaderParserRegistry = new ImageHeaderParserRegistry();
        setResourceDecoderBucketPriorityList(Arrays.asList(BUCKET_GIF, BUCKET_BITMAP, BUCKET_BITMAP_DRAWABLE));
    }

    @NonNull
    public <Data> Registry append(@NonNull Class<Data> cls, @NonNull Encoder<Data> encoder) {
        this.encoderRegistry.append(cls, encoder);
        return this;
    }

    @NonNull
    public final <Data, TResource, Transcode> List<DecodePath<Data, TResource, Transcode>> getDecodePaths(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull Class<Transcode> cls3) {
        Class<Data> cls4 = cls;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) this.decoderRegistry.getResourceClasses(cls4, cls2);
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            Class cls5 = (Class) arrayList2.get(i);
            ArrayList arrayList3 = (ArrayList) this.transcoderRegistry.getTranscodeClasses(cls5, cls3);
            int size2 = arrayList3.size();
            for (int i3 = 0; i3 < size2; i3++) {
                Class cls6 = (Class) arrayList3.get(i3);
                arrayList.add(new DecodePath(cls4, cls5, cls6, this.decoderRegistry.getDecoders(cls4, cls5), this.transcoderRegistry.get(cls5, cls6), this.throwableListPool));
                cls4 = cls;
            }
            cls4 = cls;
            i = i2;
        }
        return arrayList;
    }

    @NonNull
    public List<ImageHeaderParser> getImageHeaderParsers() {
        List<ImageHeaderParser> parsers = this.imageHeaderParserRegistry.getParsers();
        if (parsers.isEmpty()) {
            throw new NoImageHeaderParserException();
        }
        return parsers;
    }

    @Nullable
    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> getLoadPath(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull Class<Transcode> cls3) {
        Class<Data> cls4;
        Class<TResource> cls5;
        Class<Transcode> cls6;
        LoadPath<Data, TResource, Transcode> loadPath = this.loadPathCache.get(cls, cls2, cls3);
        LoadPath<Data, TResource, Transcode> loadPath2 = null;
        if (this.loadPathCache.isEmptyLoadPath(loadPath)) {
            return null;
        }
        if (loadPath != null) {
            return loadPath;
        }
        List<DecodePath<Data, TResource, Transcode>> decodePaths = getDecodePaths(cls, cls2, cls3);
        if (((ArrayList) decodePaths).isEmpty()) {
            cls4 = cls;
            cls5 = cls2;
            cls6 = cls3;
        } else {
            cls4 = cls;
            cls5 = cls2;
            cls6 = cls3;
            loadPath2 = new LoadPath<>(cls4, cls5, cls6, decodePaths, this.throwableListPool);
        }
        this.loadPathCache.put(cls4, cls5, cls6, loadPath2);
        return loadPath2;
    }

    @NonNull
    public <Model> List<ModelLoader<Model, ?>> getModelLoaders(@NonNull Model model) {
        return this.modelLoaderRegistry.getModelLoaders(model);
    }

    @NonNull
    public <Model, TResource, Transcode> List<Class<?>> getRegisteredResourceClasses(@NonNull Class<Model> cls, @NonNull Class<TResource> cls2, @NonNull Class<Transcode> cls3) {
        List<Class<?>> list = this.modelToResourceClassCache.get(cls, cls2, cls3);
        List<Class<?>> list2 = list;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            Iterator<Class<?>> it = this.modelLoaderRegistry.getDataClasses(cls).iterator();
            while (it.hasNext()) {
                ArrayList arrayList2 = (ArrayList) this.decoderRegistry.getResourceClasses(it.next(), cls2);
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    Class cls4 = (Class) obj;
                    if (!((ArrayList) this.transcoderRegistry.getTranscodeClasses(cls4, cls3)).isEmpty() && !arrayList.contains(cls4)) {
                        arrayList.add(cls4);
                    }
                }
            }
            this.modelToResourceClassCache.put(cls, cls2, cls3, DesugarCollections.unmodifiableList(arrayList));
            list2 = arrayList;
        }
        return list2;
    }

    @NonNull
    public <X> ResourceEncoder<X> getResultEncoder(@NonNull Resource<X> resource) throws NoResultEncoderAvailableException {
        ResourceEncoder<X> resourceEncoder = this.resourceEncoderRegistry.get(resource.getResourceClass());
        if (resourceEncoder != null) {
            return resourceEncoder;
        }
        throw new NoResultEncoderAvailableException(resource.getResourceClass());
    }

    @NonNull
    public <X> DataRewinder<X> getRewinder(@NonNull X x) {
        return this.dataRewinderRegistry.build(x);
    }

    @NonNull
    public <X> Encoder<X> getSourceEncoder(@NonNull X x) throws NoSourceEncoderAvailableException {
        Encoder<X> encoder = this.encoderRegistry.getEncoder(x.getClass());
        if (encoder != null) {
            return encoder;
        }
        throw new NoSourceEncoderAvailableException(x.getClass());
    }

    public boolean isResourceEncoderAvailable(@NonNull Resource<?> resource) {
        return this.resourceEncoderRegistry.get(resource.getResourceClass()) != null;
    }

    @NonNull
    public <Data> Registry prepend(@NonNull Class<Data> cls, @NonNull Encoder<Data> encoder) {
        this.encoderRegistry.prepend(cls, encoder);
        return this;
    }

    @NonNull
    @Deprecated
    public <Data> Registry register(@NonNull Class<Data> cls, @NonNull Encoder<Data> encoder) {
        return append(cls, encoder);
    }

    @NonNull
    public <Model, Data> Registry replace(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.modelLoaderRegistry.replace(cls, cls2, modelLoaderFactory);
        return this;
    }

    @NonNull
    public final Registry setResourceDecoderBucketPriorityList(@NonNull List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        arrayList.add(0, BUCKET_PREPEND_ALL);
        arrayList.add(BUCKET_APPEND_ALL);
        this.decoderRegistry.setBucketPriorityList(arrayList);
        return this;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(@NonNull Object obj) {
            super("Failed to find any ModelLoaders registered for model class: " + obj.getClass());
        }

        public <M> NoModelLoaderAvailableException(@NonNull M m, @NonNull List<ModelLoader<M, ?>> list) {
            super("Found ModelLoaders for model class: " + list + ", but none that handle this specific model instance: " + m);
        }

        public NoModelLoaderAvailableException(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    @NonNull
    public <Data, TResource> Registry append(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        append(BUCKET_APPEND_ALL, cls, cls2, resourceDecoder);
        return this;
    }

    @NonNull
    public <Data, TResource> Registry prepend(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        prepend(BUCKET_PREPEND_ALL, cls, cls2, resourceDecoder);
        return this;
    }

    @NonNull
    @Deprecated
    public <TResource> Registry register(@NonNull Class<TResource> cls, @NonNull ResourceEncoder<TResource> resourceEncoder) {
        return append((Class) cls, (ResourceEncoder) resourceEncoder);
    }

    @NonNull
    public <Data, TResource> Registry append(@NonNull String str, @NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        this.decoderRegistry.append(str, resourceDecoder, cls, cls2);
        return this;
    }

    @NonNull
    public <Data, TResource> Registry prepend(@NonNull String str, @NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        this.decoderRegistry.prepend(str, resourceDecoder, cls, cls2);
        return this;
    }

    @NonNull
    public Registry register(@NonNull DataRewinder.Factory<?> factory) {
        this.dataRewinderRegistry.register(factory);
        return this;
    }

    @NonNull
    public <TResource> Registry append(@NonNull Class<TResource> cls, @NonNull ResourceEncoder<TResource> resourceEncoder) {
        this.resourceEncoderRegistry.append(cls, resourceEncoder);
        return this;
    }

    @NonNull
    public <TResource> Registry prepend(@NonNull Class<TResource> cls, @NonNull ResourceEncoder<TResource> resourceEncoder) {
        this.resourceEncoderRegistry.prepend(cls, resourceEncoder);
        return this;
    }

    @NonNull
    public <TResource, Transcode> Registry register(@NonNull Class<TResource> cls, @NonNull Class<Transcode> cls2, @NonNull ResourceTranscoder<TResource, Transcode> resourceTranscoder) {
        this.transcoderRegistry.register(cls, cls2, resourceTranscoder);
        return this;
    }

    @NonNull
    public <Model, Data> Registry append(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<Model, Data> modelLoaderFactory) {
        this.modelLoaderRegistry.append(cls, cls2, modelLoaderFactory);
        return this;
    }

    @NonNull
    public <Model, Data> Registry prepend(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<Model, Data> modelLoaderFactory) {
        this.modelLoaderRegistry.prepend(cls, cls2, modelLoaderFactory);
        return this;
    }

    @NonNull
    public Registry register(@NonNull ImageHeaderParser imageHeaderParser) {
        this.imageHeaderParserRegistry.add(imageHeaderParser);
        return this;
    }
}
