package androidx.media3.datasource.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSink;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.FileDataSource;
import androidx.media3.datasource.PlaceholderDataSource;
import androidx.media3.datasource.PriorityDataSource;
import androidx.media3.datasource.TeeDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSink;
import androidx.media3.datasource.cache.ContentMetadata;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CacheDataSource implements DataSource {
    public static final int CACHE_IGNORED_REASON_ERROR = 0;
    public static final int CACHE_IGNORED_REASON_UNSET_LENGTH = 1;
    public static final int CACHE_NOT_IGNORED = -1;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    public static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;

    @Nullable
    public Uri actualUri;
    public final boolean blockOnCache;
    public long bytesRemaining;
    public final Cache cache;
    public final CacheKeyFactory cacheKeyFactory;
    public final DataSource cacheReadDataSource;

    @Nullable
    public final DataSource cacheWriteDataSource;
    public long checkCachePosition;

    @Nullable
    public DataSource currentDataSource;
    public long currentDataSourceBytesRead;

    @Nullable
    public DataSpec currentDataSpec;

    @Nullable
    public CacheSpan currentHoleSpan;
    public boolean currentRequestIgnoresCache;

    @Nullable
    public final EventListener eventListener;
    public final boolean ignoreCacheForUnsetLengthRequests;
    public final boolean ignoreCacheOnError;
    public long readPosition;

    @Nullable
    public DataSpec requestDataSpec;
    public boolean seenCacheError;
    public long totalCachedBytesRead;
    public final DataSource upstreamDataSource;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheIgnoredReason {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface EventListener {
        void onCacheIgnored(int i);

        void onCachedBytesRead(long j, long j2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements DataSource.Factory {
        public Cache cache;
        public boolean cacheIsReadOnly;

        @Nullable
        public DataSink.Factory cacheWriteDataSinkFactory;

        @Nullable
        public EventListener eventListener;
        public int flags;

        @Nullable
        public DataSource.Factory upstreamDataSourceFactory;
        public int upstreamPriority;

        @Nullable
        public PriorityTaskManager upstreamPriorityTaskManager;
        public DataSource.Factory cacheReadDataSourceFactory = new FileDataSource.Factory();
        public CacheKeyFactory cacheKeyFactory = CacheKeyFactory.DEFAULT;

        public CacheDataSource createDataSourceForDownloading() {
            DataSource.Factory factory = this.upstreamDataSourceFactory;
            return createDataSourceInternal(factory != null ? factory.createDataSource() : null, this.flags | 1, -1000);
        }

        public CacheDataSource createDataSourceForRemovingDownload() {
            return createDataSourceInternal(null, this.flags | 1, -1000);
        }

        public final CacheDataSource createDataSourceInternal(@Nullable DataSource dataSource, int i, int i2) {
            DataSink dataSinkCreateDataSink;
            Cache cache = this.cache;
            cache.getClass();
            if (this.cacheIsReadOnly || dataSource == null) {
                dataSinkCreateDataSink = null;
            } else {
                DataSink.Factory factory = this.cacheWriteDataSinkFactory;
                if (factory != null) {
                    dataSinkCreateDataSink = factory.createDataSink();
                } else {
                    CacheDataSink.Factory factory2 = new CacheDataSink.Factory();
                    factory2.cache = cache;
                    dataSinkCreateDataSink = factory2.createDataSink();
                }
            }
            return new CacheDataSource(cache, dataSource, this.cacheReadDataSourceFactory.createDataSource(), dataSinkCreateDataSink, this.cacheKeyFactory, i, this.upstreamPriorityTaskManager, i2, this.eventListener);
        }

        @Nullable
        public Cache getCache() {
            return this.cache;
        }

        public CacheKeyFactory getCacheKeyFactory() {
            return this.cacheKeyFactory;
        }

        @Nullable
        public PriorityTaskManager getUpstreamPriorityTaskManager() {
            return this.upstreamPriorityTaskManager;
        }

        @CanIgnoreReturnValue
        public Factory setCache(Cache cache) {
            this.cache = cache;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
            this.cacheKeyFactory = cacheKeyFactory;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setCacheReadDataSourceFactory(DataSource.Factory factory) {
            this.cacheReadDataSourceFactory = factory;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setCacheWriteDataSinkFactory(@Nullable DataSink.Factory factory) {
            this.cacheWriteDataSinkFactory = factory;
            this.cacheIsReadOnly = factory == null;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setEventListener(@Nullable EventListener eventListener) {
            this.eventListener = eventListener;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setFlags(int i) {
            this.flags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setUpstreamDataSourceFactory(@Nullable DataSource.Factory factory) {
            this.upstreamDataSourceFactory = factory;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setUpstreamPriority(int i) {
            this.upstreamPriority = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setUpstreamPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {
            this.upstreamPriorityTaskManager = priorityTaskManager;
            return this;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public CacheDataSource createDataSource() {
            DataSource.Factory factory = this.upstreamDataSourceFactory;
            return createDataSourceInternal(factory != null ? factory.createDataSource() : null, this.flags, this.upstreamPriority);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static Uri getRedirectedUriOrDefault(Cache cache, String str, Uri uri) {
        Uri redirectedUri = ContentMetadata.CC.getRedirectedUri(cache.getContentMetadata(str));
        return redirectedUri != null ? redirectedUri : uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws IOException {
        this.requestDataSpec = null;
        this.actualUri = null;
        this.readPosition = 0L;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource == null) {
            return;
        }
        try {
            dataSource.close();
        } finally {
            this.currentDataSpec = null;
            this.currentDataSource = null;
            CacheSpan cacheSpan = this.currentHoleSpan;
            if (cacheSpan != null) {
                this.cache.releaseHoleSpan(cacheSpan);
                this.currentHoleSpan = null;
            }
        }
    }

    public Cache getCache() {
        return this.cache;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        return isReadingFromUpstream() ? this.upstreamDataSource.getResponseHeaders() : Collections.EMPTY_MAP;
    }

    @Override // androidx.media3.datasource.DataSource
    @Nullable
    public Uri getUri() {
        return this.actualUri;
    }

    public final void handleBeforeThrow(Throwable th) {
        if (isReadingFromCache() || (th instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    public final boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    public final boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    public final boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    public final boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    public final void notifyBytesRead() {
        EventListener eventListener = this.eventListener;
        if (eventListener == null || this.totalCachedBytesRead <= 0) {
            return;
        }
        eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
        this.totalCachedBytesRead = 0L;
    }

    public final void notifyCacheIgnored(int i) {
        EventListener eventListener = this.eventListener;
        if (eventListener != null) {
            eventListener.onCacheIgnored(i);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            String strBuildCacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
            DataSpec.Builder builder = new DataSpec.Builder(dataSpec);
            builder.key = strBuildCacheKey;
            DataSpec dataSpecBuild = builder.build();
            this.requestDataSpec = dataSpecBuild;
            this.actualUri = getRedirectedUriOrDefault(this.cache, strBuildCacheKey, dataSpecBuild.uri);
            this.readPosition = dataSpec.position;
            int iShouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            boolean z = iShouldIgnoreCacheForRequest != -1;
            this.currentRequestIgnoresCache = z;
            if (z) {
                notifyCacheIgnored(iShouldIgnoreCacheForRequest);
            }
            if (this.currentRequestIgnoresCache) {
                this.bytesRemaining = -1L;
            } else {
                long contentLength = ContentMetadata.CC.getContentLength(this.cache.getContentMetadata(strBuildCacheKey));
                this.bytesRemaining = contentLength;
                if (contentLength != -1) {
                    long j = contentLength - dataSpec.position;
                    this.bytesRemaining = j;
                    if (j < 0) {
                        throw new DataSourceException(2008);
                    }
                }
            }
            long jMin = dataSpec.length;
            if (jMin != -1) {
                long j2 = this.bytesRemaining;
                if (j2 != -1) {
                    jMin = Math.min(j2, jMin);
                }
                this.bytesRemaining = jMin;
            }
            long j3 = this.bytesRemaining;
            if (j3 > 0 || j3 == -1) {
                openNextSource(dataSpecBuild, false);
            }
            long j4 = dataSpec.length;
            return j4 != -1 ? j4 : this.bytesRemaining;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public final void openNextSource(DataSpec dataSpec, boolean z) throws IOException {
        CacheSpan cacheSpanStartReadWrite;
        long j;
        long jMin;
        DataSpec dataSpecBuild;
        DataSource dataSource;
        String str = dataSpec.key;
        Util.castNonNull(str);
        if (this.currentRequestIgnoresCache) {
            cacheSpanStartReadWrite = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpanStartReadWrite = this.cache.startReadWrite(str, this.readPosition, this.bytesRemaining);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            cacheSpanStartReadWrite = this.cache.startReadWriteNonBlocking(str, this.readPosition, this.bytesRemaining);
        }
        if (cacheSpanStartReadWrite == null) {
            dataSource = this.upstreamDataSource;
            DataSpec.Builder builder = new DataSpec.Builder(dataSpec);
            builder.position = this.readPosition;
            builder.length = this.bytesRemaining;
            dataSpecBuild = builder.build();
            j = -1;
        } else if (cacheSpanStartReadWrite.isCached) {
            Uri uriFromFile = Uri.fromFile(cacheSpanStartReadWrite.file);
            long j2 = cacheSpanStartReadWrite.position;
            long j3 = this.readPosition - j2;
            long jMin2 = cacheSpanStartReadWrite.length - j3;
            j = -1;
            long j4 = this.bytesRemaining;
            if (j4 != -1) {
                jMin2 = Math.min(jMin2, j4);
            }
            DataSpec.Builder builder2 = new DataSpec.Builder(dataSpec);
            builder2.uri = uriFromFile;
            builder2.uriPositionOffset = j2;
            builder2.position = j3;
            builder2.length = jMin2;
            dataSpecBuild = builder2.build();
            dataSource = this.cacheReadDataSource;
        } else {
            j = -1;
            if (cacheSpanStartReadWrite.isOpenEnded()) {
                jMin = this.bytesRemaining;
            } else {
                jMin = cacheSpanStartReadWrite.length;
                long j5 = this.bytesRemaining;
                if (j5 != -1) {
                    jMin = Math.min(jMin, j5);
                }
            }
            DataSpec.Builder builder3 = new DataSpec.Builder(dataSpec);
            builder3.position = this.readPosition;
            builder3.length = jMin;
            dataSpecBuild = builder3.build();
            dataSource = this.cacheWriteDataSource;
            if (dataSource == null) {
                dataSource = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                cacheSpanStartReadWrite = null;
            }
        }
        this.checkCachePosition = (this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + 102400;
        if (z) {
            Assertions.checkState(isBypassingCache());
            if (dataSource == this.upstreamDataSource) {
                return;
            }
            try {
                closeCurrentSource();
            } catch (Throwable th) {
                if (!cacheSpanStartReadWrite.isCached) {
                    this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                }
                throw th;
            }
        }
        if (cacheSpanStartReadWrite != null && !cacheSpanStartReadWrite.isCached) {
            this.currentHoleSpan = cacheSpanStartReadWrite;
        }
        this.currentDataSource = dataSource;
        this.currentDataSpec = dataSpecBuild;
        this.currentDataSourceBytesRead = 0L;
        long jOpen = dataSource.open(dataSpecBuild);
        ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
        if (dataSpecBuild.length == j && jOpen != j) {
            this.bytesRemaining = jOpen;
            contentMetadataMutations.checkAndSet("exo_len", Long.valueOf(this.readPosition + jOpen));
        }
        if (isReadingFromUpstream()) {
            Uri uri = dataSource.getUri();
            this.actualUri = uri;
            ContentMetadataMutations.setRedirectedUri(contentMetadataMutations, dataSpec.uri.equals(uri) ? null : this.actualUri);
        }
        if (isWritingToCache()) {
            this.cache.applyContentMetadataMutations(str, contentMetadataMutations);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        DataSpec dataSpec = this.requestDataSpec;
        dataSpec.getClass();
        DataSpec dataSpec2 = this.currentDataSpec;
        dataSpec2.getClass();
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(dataSpec, true);
            }
            DataSource dataSource = this.currentDataSource;
            dataSource.getClass();
            int i3 = dataSource.read(bArr, i, i2);
            if (i3 == -1) {
                if (isReadingFromUpstream()) {
                    long j = dataSpec2.length;
                    if (j == -1 || this.currentDataSourceBytesRead < j) {
                        String str = dataSpec.key;
                        Util.castNonNull(str);
                        setNoBytesRemainingAndMaybeStoreLength(str);
                        return i3;
                    }
                }
                long j2 = this.bytesRemaining;
                if (j2 <= 0) {
                    if (j2 == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(dataSpec, false);
                return read(bArr, i, i2);
            }
            if (isReadingFromCache()) {
                this.totalCachedBytesRead += (long) i3;
            }
            long j3 = i3;
            this.readPosition += j3;
            this.currentDataSourceBytesRead += j3;
            long j4 = this.bytesRemaining;
            if (j4 != -1) {
                this.bytesRemaining = j4 - j3;
                return i3;
            }
            return i3;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public final void setNoBytesRemainingAndMaybeStoreLength(String str) throws IOException {
        this.bytesRemaining = 0L;
        if (isWritingToCache()) {
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            contentMetadataMutations.checkAndSet("exo_len", Long.valueOf(this.readPosition));
            this.cache.applyContentMetadataMutations(str, contentMetadataMutations);
        }
    }

    public final int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (this.ignoreCacheOnError && this.seenCacheError) {
            return 0;
        }
        return (this.ignoreCacheForUnsetLengthRequests && dataSpec.length == -1) ? 1 : -1;
    }

    public CacheDataSource(Cache cache, @Nullable DataSource dataSource) {
        this(cache, dataSource, 0);
    }

    public CacheDataSource(Cache cache, @Nullable DataSource dataSource, int i) {
        this(cache, dataSource, new FileDataSource(false), new CacheDataSink(cache, 5242880L), i, null, null);
    }

    public CacheDataSource(Cache cache, @Nullable DataSource dataSource, DataSource dataSource2, @Nullable DataSink dataSink, int i, @Nullable EventListener eventListener) {
        this(cache, dataSource, dataSource2, dataSink, i, eventListener, null);
    }

    public CacheDataSource(Cache cache, @Nullable DataSource dataSource, DataSource dataSource2, @Nullable DataSink dataSink, int i, @Nullable EventListener eventListener, @Nullable CacheKeyFactory cacheKeyFactory) {
        this(cache, dataSource, dataSource2, dataSink, cacheKeyFactory, i, null, 0, eventListener);
    }

    public CacheDataSource(Cache cache, @Nullable DataSource dataSource, DataSource dataSource2, @Nullable DataSink dataSink, @Nullable CacheKeyFactory cacheKeyFactory, int i, @Nullable PriorityTaskManager priorityTaskManager, int i2, @Nullable EventListener eventListener) {
        this.cache = cache;
        this.cacheReadDataSource = dataSource2;
        this.cacheKeyFactory = cacheKeyFactory == null ? CacheKeyFactory.DEFAULT : cacheKeyFactory;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i & 4) != 0;
        if (dataSource != null) {
            dataSource = priorityTaskManager != null ? new PriorityDataSource(dataSource, priorityTaskManager, i2) : dataSource;
            this.upstreamDataSource = dataSource;
            this.cacheWriteDataSource = dataSink != null ? new TeeDataSource(dataSource, dataSink) : null;
        } else {
            this.upstreamDataSource = PlaceholderDataSource.INSTANCE;
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener;
    }
}
