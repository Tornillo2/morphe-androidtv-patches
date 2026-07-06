package androidx.media3.exoplayer.offline;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.SparseArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.cache.CacheDataSource;
import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class DefaultDownloaderFactory implements DownloaderFactory {
    public static final SparseArray<Constructor<? extends Downloader>> CONSTRUCTORS = createDownloaderConstructors();
    public final CacheDataSource.Factory cacheDataSourceFactory;
    public final Executor executor;

    @Deprecated
    public DefaultDownloaderFactory(CacheDataSource.Factory factory) {
        this(factory, new DefaultDownloaderFactory$$ExternalSyntheticLambda0());
    }

    public static SparseArray<Constructor<? extends Downloader>> createDownloaderConstructors() {
        SparseArray<Constructor<? extends Downloader>> sparseArray = new SparseArray<>();
        try {
            sparseArray.put(0, getDownloaderConstructor(Class.forName("androidx.media3.exoplayer.dash.offline.DashDownloader")));
        } catch (ClassNotFoundException unused) {
        }
        try {
            sparseArray.put(2, getDownloaderConstructor(Class.forName("androidx.media3.exoplayer.hls.offline.HlsDownloader")));
        } catch (ClassNotFoundException unused2) {
        }
        try {
            sparseArray.put(1, getDownloaderConstructor(Class.forName("androidx.media3.exoplayer.smoothstreaming.offline.SsDownloader")));
        } catch (ClassNotFoundException unused3) {
        }
        return sparseArray;
    }

    public static Constructor<? extends Downloader> getDownloaderConstructor(Class<?> cls) {
        try {
            return cls.asSubclass(Downloader.class).getConstructor(MediaItem.class, CacheDataSource.Factory.class, Executor.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Downloader constructor missing", e);
        }
    }

    @Override // androidx.media3.exoplayer.offline.DownloaderFactory
    public Downloader createDownloader(DownloadRequest downloadRequest) {
        int iInferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(downloadRequest.uri, downloadRequest.mimeType);
        if (iInferContentTypeForUriAndMimeType == 0 || iInferContentTypeForUriAndMimeType == 1 || iInferContentTypeForUriAndMimeType == 2) {
            return createDownloader(downloadRequest, iInferContentTypeForUriAndMimeType);
        }
        if (iInferContentTypeForUriAndMimeType != 4) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unsupported type: ", iInferContentTypeForUriAndMimeType));
        }
        MediaItem.Builder builder = new MediaItem.Builder();
        builder.uri = downloadRequest.uri;
        builder.customCacheKey = downloadRequest.customCacheKey;
        return new ProgressiveDownloader(builder.build(), this.cacheDataSourceFactory, this.executor);
    }

    public DefaultDownloaderFactory(CacheDataSource.Factory factory, Executor executor) {
        factory.getClass();
        this.cacheDataSourceFactory = factory;
        executor.getClass();
        this.executor = executor;
    }

    public final Downloader createDownloader(DownloadRequest downloadRequest, int i) {
        Constructor<? extends Downloader> constructor = CONSTRUCTORS.get(i);
        if (constructor != null) {
            MediaItem.Builder builder = new MediaItem.Builder();
            builder.uri = downloadRequest.uri;
            builder.setStreamKeys(downloadRequest.streamKeys);
            builder.customCacheKey = downloadRequest.customCacheKey;
            try {
                return constructor.newInstance(builder.build(), this.cacheDataSourceFactory, this.executor);
            } catch (Exception e) {
                throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Failed to instantiate downloader for content type ", i), e);
            }
        }
        throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Module missing for content type ", i));
    }
}
