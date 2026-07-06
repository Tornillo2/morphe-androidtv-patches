package com.bumptech.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.bumptech.glide.load.model.stream.QMediaStoreUriLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapImageDecoderResourceDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.InputStreamBitmapImageDecoderResourceDecoder;
import com.bumptech.glide.load.resource.bitmap.ParcelFileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Glide implements ComponentCallbacks2 {
    public static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";
    public static final String TAG = "Glide";
    public static volatile Glide glide;
    public static volatile boolean isInitializing;
    public final ArrayPool arrayPool;
    public final BitmapPool bitmapPool;

    @Nullable
    @GuardedBy("this")
    public BitmapPreFiller bitmapPreFiller;
    public final ConnectivityMonitorFactory connectivityMonitorFactory;
    public final RequestOptionsFactory defaultRequestOptionsFactory;
    public final Engine engine;
    public final GlideContext glideContext;
    public final MemoryCache memoryCache;
    public final Registry registry;
    public final RequestManagerRetriever requestManagerRetriever;
    public final List<RequestManager> managers = new ArrayList();
    public MemoryCategory memoryCategory = MemoryCategory.NORMAL;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface RequestOptionsFactory {
        @NonNull
        RequestOptions build();
    }

    public Glide(@NonNull Context context, @NonNull Engine engine, @NonNull MemoryCache memoryCache, @NonNull BitmapPool bitmapPool, @NonNull ArrayPool arrayPool, @NonNull RequestManagerRetriever requestManagerRetriever, @NonNull ConnectivityMonitorFactory connectivityMonitorFactory, int i, @NonNull RequestOptionsFactory requestOptionsFactory, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull List<RequestListener<Object>> list, boolean z, boolean z2) {
        ResourceDecoder byteBufferBitmapDecoder;
        ResourceDecoder streamBitmapDecoder;
        this.engine = engine;
        this.bitmapPool = bitmapPool;
        this.arrayPool = arrayPool;
        this.memoryCache = memoryCache;
        this.requestManagerRetriever = requestManagerRetriever;
        this.connectivityMonitorFactory = connectivityMonitorFactory;
        this.defaultRequestOptionsFactory = requestOptionsFactory;
        Resources resources = context.getResources();
        Registry registry = new Registry();
        this.registry = registry;
        registry.register(new DefaultImageHeaderParser());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 27) {
            registry.register(new ExifInterfaceImageHeaderParser());
        }
        List<ImageHeaderParser> imageHeaderParsers = registry.getImageHeaderParsers();
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context, imageHeaderParsers, bitmapPool, arrayPool, ByteBufferGifDecoder.PARSER_POOL, ByteBufferGifDecoder.GIF_DECODER_FACTORY);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoderParcel = VideoDecoder.parcel(bitmapPool);
        Downsampler downsampler = new Downsampler(registry.getImageHeaderParsers(), resources.getDisplayMetrics(), bitmapPool, arrayPool);
        if (!z2 || i2 < 28) {
            byteBufferBitmapDecoder = new ByteBufferBitmapDecoder(downsampler);
            streamBitmapDecoder = new StreamBitmapDecoder(downsampler, arrayPool);
        } else {
            streamBitmapDecoder = new InputStreamBitmapImageDecoderResourceDecoder();
            byteBufferBitmapDecoder = new ByteBufferBitmapImageDecoderResourceDecoder();
        }
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context);
        ResourceLoader.StreamFactory streamFactory = new ResourceLoader.StreamFactory(resources);
        ResourceLoader.UriFactory uriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory = new ResourceLoader.FileDescriptorFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory = new ResourceLoader.AssetFileDescriptorFactory(resources);
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool);
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        ContentResolver contentResolver = context.getContentResolver();
        registry.append(ByteBuffer.class, new ByteBufferEncoder());
        registry.append(InputStream.class, new StreamEncoder(arrayPool));
        registry.append(Registry.BUCKET_BITMAP, ByteBuffer.class, Bitmap.class, byteBufferBitmapDecoder);
        registry.append(Registry.BUCKET_BITMAP, InputStream.class, Bitmap.class, streamBitmapDecoder);
        registry.append(Registry.BUCKET_BITMAP, ParcelFileDescriptor.class, Bitmap.class, new ParcelFileDescriptorBitmapDecoder(downsampler));
        registry.append(Registry.BUCKET_BITMAP, ParcelFileDescriptor.class, Bitmap.class, resourceDecoderParcel);
        registry.append(Registry.BUCKET_BITMAP, AssetFileDescriptor.class, Bitmap.class, VideoDecoder.asset(bitmapPool));
        UnitModelLoader.Factory<?> factory = UnitModelLoader.Factory.FACTORY;
        registry.append(Bitmap.class, Bitmap.class, factory);
        registry.append(Registry.BUCKET_BITMAP, Bitmap.class, Bitmap.class, new UnitBitmapDecoder());
        registry.append(Bitmap.class, (ResourceEncoder) bitmapEncoder);
        registry.append(Registry.BUCKET_BITMAP_DRAWABLE, ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, byteBufferBitmapDecoder));
        registry.append(Registry.BUCKET_BITMAP_DRAWABLE, InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, streamBitmapDecoder));
        registry.append(Registry.BUCKET_BITMAP_DRAWABLE, ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, resourceDecoderParcel));
        registry.append(BitmapDrawable.class, (ResourceEncoder) new BitmapDrawableEncoder(bitmapPool, bitmapEncoder));
        registry.append(Registry.BUCKET_GIF, InputStream.class, GifDrawable.class, new StreamGifDecoder(imageHeaderParsers, byteBufferGifDecoder, arrayPool));
        registry.append(Registry.BUCKET_GIF, ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder);
        registry.append(GifDrawable.class, (ResourceEncoder) new GifDrawableEncoder());
        registry.append(GifDecoder.class, GifDecoder.class, factory);
        registry.append(Registry.BUCKET_BITMAP, GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool));
        registry.append(Uri.class, Drawable.class, resourceDrawableDecoder);
        registry.append(Uri.class, Bitmap.class, new ResourceBitmapDecoder(resourceDrawableDecoder, bitmapPool));
        registry.register(new ByteBufferRewinder.Factory());
        registry.append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory());
        registry.append(File.class, InputStream.class, new FileLoader.StreamFactory());
        registry.append(File.class, File.class, new FileDecoder());
        registry.append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory());
        registry.append(File.class, File.class, factory);
        registry.register(new InputStreamRewinder.Factory(arrayPool));
        registry.register(new ParcelFileDescriptorRewinder.Factory());
        Class cls = Integer.TYPE;
        registry.append(cls, InputStream.class, streamFactory);
        registry.append(cls, ParcelFileDescriptor.class, fileDescriptorFactory);
        registry.append(Integer.class, InputStream.class, streamFactory);
        registry.append(Integer.class, ParcelFileDescriptor.class, fileDescriptorFactory);
        registry.append(Integer.class, Uri.class, uriFactory);
        registry.append(cls, AssetFileDescriptor.class, assetFileDescriptorFactory);
        registry.append(Integer.class, AssetFileDescriptor.class, assetFileDescriptorFactory);
        registry.append(cls, Uri.class, uriFactory);
        registry.append(String.class, InputStream.class, new DataUrlLoader.StreamFactory());
        registry.append(Uri.class, InputStream.class, new DataUrlLoader.StreamFactory());
        registry.append(String.class, InputStream.class, new StringLoader.StreamFactory());
        registry.append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory());
        registry.append(String.class, AssetFileDescriptor.class, new StringLoader.AssetFileDescriptorFactory());
        registry.append(Uri.class, InputStream.class, new HttpUriLoader.Factory());
        registry.append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets()));
        registry.append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets()));
        registry.append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context));
        registry.append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context));
        if (i2 >= 29) {
            registry.append(Uri.class, InputStream.class, new QMediaStoreUriLoader.InputStreamFactory(context));
            registry.append(Uri.class, ParcelFileDescriptor.class, new QMediaStoreUriLoader.FileDescriptorFactory(context));
        }
        registry.append(Uri.class, InputStream.class, new UriLoader.StreamFactory(contentResolver));
        registry.append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(contentResolver));
        registry.append(Uri.class, AssetFileDescriptor.class, new UriLoader.AssetFileDescriptorFactory(contentResolver));
        registry.append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory());
        registry.append(URL.class, InputStream.class, new UrlLoader.StreamFactory());
        registry.append(Uri.class, File.class, new MediaStoreFileLoader.Factory(context));
        registry.append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory());
        registry.append(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory());
        registry.append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory());
        registry.append(Uri.class, Uri.class, factory);
        registry.append(Drawable.class, Drawable.class, factory);
        registry.append(Drawable.class, Drawable.class, new UnitDrawableDecoder());
        registry.register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources));
        registry.register(Bitmap.class, byte[].class, bitmapBytesTranscoder);
        registry.register(Drawable.class, byte[].class, new DrawableBytesTranscoder(bitmapPool, bitmapBytesTranscoder, gifDrawableBytesTranscoder));
        registry.register(GifDrawable.class, byte[].class, gifDrawableBytesTranscoder);
        if (i2 >= 23) {
            ResourceDecoder<ByteBuffer, Bitmap> resourceDecoderByteBuffer = VideoDecoder.byteBuffer(bitmapPool);
            registry.append(ByteBuffer.class, Bitmap.class, resourceDecoderByteBuffer);
            registry.append(ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, resourceDecoderByteBuffer));
        }
        this.glideContext = new GlideContext(context, arrayPool, registry, new ImageViewTargetFactory(), requestOptionsFactory, map, list, engine, z, i);
    }

    @GuardedBy("Glide.class")
    public static void checkAndInitializeGlide(@NonNull Context context, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        if (isInitializing) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        isInitializing = true;
        initializeGlide(context, generatedAppGlideModule);
        isInitializing = false;
    }

    @NonNull
    public static Glide get(@NonNull Context context) {
        if (glide == null) {
            GeneratedAppGlideModule annotationGeneratedGlideModules = getAnnotationGeneratedGlideModules(context.getApplicationContext());
            synchronized (Glide.class) {
                try {
                    if (glide == null) {
                        checkAndInitializeGlide(context, annotationGeneratedGlideModules);
                    }
                } finally {
                }
            }
        }
        return glide;
    }

    @Nullable
    public static GeneratedAppGlideModule getAnnotationGeneratedGlideModules(Context context) {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e) {
            throwIncorrectGlideModule(e);
            throw null;
        } catch (InstantiationException e2) {
            throwIncorrectGlideModule(e2);
            throw null;
        } catch (NoSuchMethodException e3) {
            throwIncorrectGlideModule(e3);
            throw null;
        } catch (InvocationTargetException e4) {
            throwIncorrectGlideModule(e4);
            throw null;
        }
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context) {
        return getPhotoCacheDir(context, "image_manager_disk_cache");
    }

    @NonNull
    public static RequestManagerRetriever getRetriever(@Nullable Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    @VisibleForTesting
    @Deprecated
    public static synchronized void init(Glide glide2) {
        try {
            if (glide != null) {
                tearDown();
            }
            glide = glide2;
        } catch (Throwable th) {
            throw th;
        }
    }

    @GuardedBy("Glide.class")
    public static void initializeGlide(@NonNull Context context, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        initializeGlide(context, new GlideBuilder(), generatedAppGlideModule);
    }

    @VisibleForTesting
    public static synchronized void tearDown() {
        try {
            if (glide != null) {
                glide.getContext().getApplicationContext().unregisterComponentCallbacks(glide);
                glide.engine.shutdown();
            }
            glide = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void throwIncorrectGlideModule(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    @NonNull
    public static RequestManager with(@NonNull Context context) {
        return getRetriever(context).get(context);
    }

    public void clearDiskCache() {
        Util.assertBackgroundThread();
        this.engine.clearDiskCache();
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.memoryCache.clearMemory();
        this.bitmapPool.clearMemory();
        this.arrayPool.clearMemory();
    }

    @NonNull
    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    @NonNull
    public BitmapPool getBitmapPool() {
        return this.bitmapPool;
    }

    public ConnectivityMonitorFactory getConnectivityMonitorFactory() {
        return this.connectivityMonitorFactory;
    }

    @NonNull
    public Context getContext() {
        return this.glideContext.getBaseContext();
    }

    @NonNull
    public GlideContext getGlideContext() {
        return this.glideContext;
    }

    @NonNull
    public Registry getRegistry() {
        return this.registry;
    }

    @NonNull
    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.requestManagerRetriever;
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        clearMemory();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        trimMemory(i);
    }

    public synchronized void preFillBitmapPool(@NonNull PreFillType.Builder... builderArr) {
        try {
            if (this.bitmapPreFiller == null) {
                this.bitmapPreFiller = new BitmapPreFiller(this.memoryCache, this.bitmapPool, (DecodeFormat) this.defaultRequestOptionsFactory.build().options.get(Downsampler.DECODE_FORMAT));
            }
            this.bitmapPreFiller.preFill(builderArr);
        } catch (Throwable th) {
            throw th;
        }
    }

    public void registerRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            try {
                if (this.managers.contains(requestManager)) {
                    throw new IllegalStateException("Cannot register already registered manager");
                }
                this.managers.add(requestManager);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean removeFromManagers(@NonNull Target<?> target) {
        synchronized (this.managers) {
            try {
                Iterator<RequestManager> it = this.managers.iterator();
                while (it.hasNext()) {
                    if (it.next().untrack(target)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @NonNull
    public MemoryCategory setMemoryCategory(@NonNull MemoryCategory memoryCategory) {
        Util.assertMainThread();
        this.memoryCache.setSizeMultiplier(memoryCategory.multiplier);
        this.bitmapPool.setSizeMultiplier(memoryCategory.multiplier);
        MemoryCategory memoryCategory2 = this.memoryCategory;
        this.memoryCategory = memoryCategory;
        return memoryCategory2;
    }

    public void trimMemory(int i) {
        Util.assertMainThread();
        Iterator<RequestManager> it = this.managers.iterator();
        while (it.hasNext()) {
            it.next().onTrimMemory(i);
        }
        this.memoryCache.trimMemory(i);
        this.bitmapPool.trimMemory(i);
        this.arrayPool.trimMemory(i);
    }

    public void unregisterRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            try {
                if (!this.managers.contains(requestManager)) {
                    throw new IllegalStateException("Cannot unregister not yet registered manager");
                }
                this.managers.remove(requestManager);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context, @NonNull String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            if (Log.isLoggable("Glide", 6)) {
                Log.e("Glide", "default disk cache dir is null");
            }
            return null;
        }
        File file = new File(cacheDir, str);
        if (file.mkdirs() || (file.exists() && file.isDirectory())) {
            return file;
        }
        return null;
    }

    @GuardedBy("Glide.class")
    public static void initializeGlide(@NonNull Context context, @NonNull GlideBuilder glideBuilder, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        Context applicationContext = context.getApplicationContext();
        List list = Collections.EMPTY_LIST;
        List<GlideModule> list2 = new ManifestParser(applicationContext).parse();
        if (generatedAppGlideModule != null && !generatedAppGlideModule.getExcludedModuleClasses().isEmpty()) {
            Set<Class<?>> excludedModuleClasses = generatedAppGlideModule.getExcludedModuleClasses();
            Iterator<GlideModule> it = list2.iterator();
            while (it.hasNext()) {
                GlideModule next = it.next();
                if (excludedModuleClasses.contains(next.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator<GlideModule> it2 = list2.iterator();
            while (it2.hasNext()) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        glideBuilder.requestManagerFactory = null;
        Iterator<GlideModule> it3 = list2.iterator();
        while (it3.hasNext()) {
            it3.next().applyOptions(applicationContext, glideBuilder);
        }
        Glide glideBuild = glideBuilder.build(applicationContext);
        for (GlideModule glideModule : list2) {
            try {
                glideModule.registerComponents(applicationContext, glideBuild, glideBuild.registry);
            } catch (AbstractMethodError e) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: ".concat(glideModule.getClass().getName()), e);
            }
        }
        applicationContext.registerComponentCallbacks(glideBuild);
        glide = glideBuild;
    }

    @NonNull
    public static RequestManager with(@NonNull Activity activity) {
        return getRetriever(activity).get(activity);
    }

    @NonNull
    public static RequestManager with(@NonNull FragmentActivity fragmentActivity) {
        return getRetriever(fragmentActivity).get(fragmentActivity);
    }

    @NonNull
    public static RequestManager with(@NonNull Fragment fragment) {
        return getRetriever(fragment.getContext()).get(fragment);
    }

    @VisibleForTesting
    public static void init(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        GeneratedAppGlideModule annotationGeneratedGlideModules = getAnnotationGeneratedGlideModules(context);
        synchronized (Glide.class) {
            try {
                if (glide != null) {
                    tearDown();
                }
                initializeGlide(context, glideBuilder, annotationGeneratedGlideModules);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @NonNull
    @Deprecated
    public static RequestManager with(@NonNull android.app.Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    @NonNull
    public static RequestManager with(@NonNull View view) {
        return getRetriever(view.getContext()).get(view);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }
}
