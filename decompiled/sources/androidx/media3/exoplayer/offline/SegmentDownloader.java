package androidx.media3.exoplayer.offline;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheKeyFactory;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.datasource.cache.ContentMetadata;
import androidx.media3.exoplayer.offline.Downloader;
import androidx.media3.exoplayer.offline.FilterableManifest;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class SegmentDownloader<M extends FilterableManifest<M>> implements Downloader {
    public static final int BUFFER_SIZE_BYTES = 131072;
    public static final long DEFAULT_MAX_MERGED_SEGMENT_START_TIME_DIFF_MS = 20000;
    public final ArrayList<RunnableFutureTask<?, ?>> activeRunnables;
    public final Cache cache;
    public final CacheDataSource.Factory cacheDataSourceFactory;
    public final CacheKeyFactory cacheKeyFactory;
    public final Executor executor;
    public volatile boolean isCanceled;
    public final DataSpec manifestDataSpec;
    public final ParsingLoadable.Parser<M> manifestParser;
    public final long maxMergedSegmentStartTimeDiffUs;

    @Nullable
    public final PriorityTaskManager priorityTaskManager;
    public final ArrayList<StreamKey> streamKeys;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ProgressNotifier implements CacheWriter.ProgressListener {
        public long bytesDownloaded;
        public final long contentLength;
        public final Downloader.ProgressListener progressListener;
        public int segmentsDownloaded;
        public final int totalSegments;

        public ProgressNotifier(Downloader.ProgressListener progressListener, long j, int i, long j2, int i2) {
            this.progressListener = progressListener;
            this.contentLength = j;
            this.totalSegments = i;
            this.bytesDownloaded = j2;
            this.segmentsDownloaded = i2;
        }

        public final float getPercentDownloaded() {
            long j = this.contentLength;
            if (j != -1 && j != 0) {
                return (this.bytesDownloaded * 100.0f) / j;
            }
            int i = this.totalSegments;
            if (i != 0) {
                return (this.segmentsDownloaded * 100.0f) / i;
            }
            return -1.0f;
        }

        @Override // androidx.media3.datasource.cache.CacheWriter.ProgressListener
        public void onProgress(long j, long j2, long j3) {
            long j4 = this.bytesDownloaded + j3;
            this.bytesDownloaded = j4;
            this.progressListener.onProgress(this.contentLength, j4, getPercentDownloaded());
        }

        public void onSegmentDownloaded() {
            this.segmentsDownloaded++;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long j, DataSpec dataSpec) {
            this.startTimeUs = j;
            this.dataSpec = dataSpec;
        }

        @Override // java.lang.Comparable
        public int compareTo(Segment segment) {
            return Util.compareLong(this.startTimeUs, segment.startTimeUs);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SegmentDownloadRunnable extends RunnableFutureTask<Void, IOException> {
        public final CacheWriter cacheWriter;
        public final CacheDataSource dataSource;

        @Nullable
        public final ProgressNotifier progressNotifier;
        public final Segment segment;
        public final byte[] temporaryBuffer;

        public SegmentDownloadRunnable(Segment segment, CacheDataSource cacheDataSource, @Nullable ProgressNotifier progressNotifier, byte[] bArr) {
            this.segment = segment;
            this.dataSource = cacheDataSource;
            this.progressNotifier = progressNotifier;
            this.temporaryBuffer = bArr;
            this.cacheWriter = new CacheWriter(cacheDataSource, segment.dataSpec, bArr, progressNotifier);
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        public void cancelWork() {
            this.cacheWriter.isCanceled = true;
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        public /* bridge */ /* synthetic */ Void doWork() throws Exception {
            doWork2();
            return null;
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        /* JADX INFO: renamed from: doWork, reason: avoid collision after fix types in other method */
        public Void doWork2() throws IOException {
            this.cacheWriter.cache();
            ProgressNotifier progressNotifier = this.progressNotifier;
            if (progressNotifier == null) {
                return null;
            }
            progressNotifier.onSegmentDownloaded();
            return null;
        }
    }

    @Deprecated
    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> parser, CacheDataSource.Factory factory, Executor executor) {
        this(mediaItem, parser, factory, executor, 20000L);
    }

    public static boolean canMergeSegments(DataSpec dataSpec, DataSpec dataSpec2) {
        if (!dataSpec.uri.equals(dataSpec2.uri)) {
            return false;
        }
        long j = dataSpec.length;
        return j != -1 && dataSpec.position + j == dataSpec2.position && Util.areEqual(dataSpec.key, dataSpec2.key) && dataSpec.flags == dataSpec2.flags && dataSpec.httpMethod == dataSpec2.httpMethod && dataSpec.httpRequestHeaders.equals(dataSpec2.httpRequestHeaders);
    }

    public static DataSpec getCompressibleDataSpec(Uri uri) {
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.uri = uri;
        builder.flags = 1;
        return builder.build();
    }

    public static void mergeSegments(List<Segment> list, CacheKeyFactory cacheKeyFactory, long j) {
        HashMap map = new HashMap();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Segment segment = list.get(i2);
            String strBuildCacheKey = cacheKeyFactory.buildCacheKey(segment.dataSpec);
            Integer num = (Integer) map.get(strBuildCacheKey);
            Segment segment2 = num == null ? null : list.get(num.intValue());
            if (segment2 == null || segment.startTimeUs > segment2.startTimeUs + j || !canMergeSegments(segment2.dataSpec, segment.dataSpec)) {
                map.put(strBuildCacheKey, Integer.valueOf(i));
                list.set(i, segment);
                i++;
            } else {
                long j2 = segment.dataSpec.length;
                DataSpec dataSpecSubrange = segment2.dataSpec.subrange(0L, j2 != -1 ? segment2.dataSpec.length + j2 : -1L);
                num.getClass();
                list.set(num.intValue(), new Segment(segment2.startTimeUs, dataSpecSubrange));
            }
        }
        Util.removeRange(list, i, list.size());
    }

    public final <T> void addActiveRunnable(RunnableFutureTask<T, ?> runnableFutureTask) throws InterruptedException {
        synchronized (this.activeRunnables) {
            try {
                if (this.isCanceled) {
                    throw new InterruptedException();
                }
                this.activeRunnables.add(runnableFutureTask);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void cancel() {
        synchronized (this.activeRunnables) {
            try {
                this.isCanceled = true;
                for (int i = 0; i < this.activeRunnables.size(); i++) {
                    this.activeRunnables.get(i).cancel(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void download(@Nullable Downloader.ProgressListener progressListener) throws InterruptedException, IOException {
        CacheDataSource cacheDataSourceCreateDataSourceForDownloading;
        byte[] bArr;
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayDeque arrayDeque2 = new ArrayDeque();
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null) {
            priorityTaskManager.add(-1000);
        }
        try {
            CacheDataSource cacheDataSourceCreateDataSourceForDownloading2 = this.cacheDataSourceFactory.createDataSourceForDownloading();
            FilterableManifest manifest = getManifest(cacheDataSourceCreateDataSourceForDownloading2, this.manifestDataSpec, false);
            if (!this.streamKeys.isEmpty()) {
                manifest = (FilterableManifest) manifest.copy(this.streamKeys);
            }
            List<Segment> segments = getSegments(cacheDataSourceCreateDataSourceForDownloading2, manifest, false);
            Collections.sort(segments);
            mergeSegments(segments, this.cacheKeyFactory, this.maxMergedSegmentStartTimeDiffUs);
            int size = segments.size();
            long j = 0;
            long j2 = 0;
            int i = 0;
            for (int size2 = segments.size() - 1; size2 >= 0; size2--) {
                DataSpec dataSpec = segments.get(size2).dataSpec;
                String strBuildCacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
                long j3 = dataSpec.length;
                if (j3 == -1) {
                    long contentLength = ContentMetadata.CC.getContentLength(this.cache.getContentMetadata(strBuildCacheKey));
                    if (contentLength != -1) {
                        j3 = contentLength - dataSpec.position;
                    }
                }
                long j4 = j3;
                long cachedBytes = this.cache.getCachedBytes(strBuildCacheKey, dataSpec.position, j4);
                j2 += cachedBytes;
                if (j4 != -1) {
                    if (j4 == cachedBytes) {
                        i++;
                        segments.remove(size2);
                    }
                    if (j != -1) {
                        j += j4;
                    }
                } else {
                    j = -1;
                }
            }
            ProgressNotifier progressNotifier = progressListener != null ? new ProgressNotifier(progressListener, j, size, j2, i) : null;
            arrayDeque.addAll(segments);
            while (!this.isCanceled && !arrayDeque.isEmpty()) {
                PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
                if (priorityTaskManager2 != null) {
                    priorityTaskManager2.proceed(-1000);
                }
                if (arrayDeque2.isEmpty()) {
                    cacheDataSourceCreateDataSourceForDownloading = this.cacheDataSourceFactory.createDataSourceForDownloading();
                    bArr = new byte[131072];
                } else {
                    SegmentDownloadRunnable segmentDownloadRunnable = (SegmentDownloadRunnable) arrayDeque2.removeFirst();
                    cacheDataSourceCreateDataSourceForDownloading = segmentDownloadRunnable.dataSource;
                    bArr = segmentDownloadRunnable.temporaryBuffer;
                }
                SegmentDownloadRunnable segmentDownloadRunnable2 = new SegmentDownloadRunnable((Segment) arrayDeque.removeFirst(), cacheDataSourceCreateDataSourceForDownloading, progressNotifier, bArr);
                addActiveRunnable(segmentDownloadRunnable2);
                this.executor.execute(segmentDownloadRunnable2);
                for (int size3 = this.activeRunnables.size() - 1; size3 >= 0; size3--) {
                    SegmentDownloadRunnable segmentDownloadRunnable3 = (SegmentDownloadRunnable) this.activeRunnables.get(size3);
                    if (arrayDeque.isEmpty() || segmentDownloadRunnable3.finished.isOpen()) {
                        try {
                            segmentDownloadRunnable3.get();
                            removeActiveRunnable(size3);
                            arrayDeque2.addLast(segmentDownloadRunnable3);
                        } catch (ExecutionException e) {
                            Throwable cause = e.getCause();
                            cause.getClass();
                            if (!(cause instanceof PriorityTaskManager.PriorityTooLowException)) {
                                if (!(cause instanceof IOException)) {
                                    throw cause;
                                }
                                throw ((IOException) cause);
                            }
                            arrayDeque.addFirst(segmentDownloadRunnable3.segment);
                            removeActiveRunnable(size3);
                            arrayDeque2.addLast(segmentDownloadRunnable3);
                        }
                    }
                }
                segmentDownloadRunnable2.blockUntilStarted();
            }
            for (int i2 = 0; i2 < this.activeRunnables.size(); i2++) {
                this.activeRunnables.get(i2).cancel(true);
            }
            for (int size4 = this.activeRunnables.size() - 1; size4 >= 0; size4--) {
                this.activeRunnables.get(size4).blockUntilFinished();
                removeActiveRunnable(size4);
            }
            PriorityTaskManager priorityTaskManager3 = this.priorityTaskManager;
            if (priorityTaskManager3 != null) {
                priorityTaskManager3.remove(-1000);
            }
        } catch (Throwable th) {
            for (int i3 = 0; i3 < this.activeRunnables.size(); i3++) {
                this.activeRunnables.get(i3).cancel(true);
            }
            for (int size5 = this.activeRunnables.size() - 1; size5 >= 0; size5--) {
                this.activeRunnables.get(size5).blockUntilFinished();
                removeActiveRunnable(size5);
            }
            PriorityTaskManager priorityTaskManager4 = this.priorityTaskManager;
            if (priorityTaskManager4 != null) {
                priorityTaskManager4.remove(-1000);
            }
            throw th;
        }
    }

    public final <T> T execute(RunnableFutureTask<T, ?> runnableFutureTask, boolean z) throws Throwable {
        if (z) {
            runnableFutureTask.run();
            try {
                return runnableFutureTask.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                cause.getClass();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                Util.sneakyThrow(e);
                throw null;
            }
        }
        while (!this.isCanceled) {
            PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
            if (priorityTaskManager != null) {
                priorityTaskManager.proceed(-1000);
            }
            addActiveRunnable(runnableFutureTask);
            this.executor.execute(runnableFutureTask);
            try {
                return runnableFutureTask.get();
            } catch (ExecutionException e2) {
                Throwable cause2 = e2.getCause();
                cause2.getClass();
                if (!(cause2 instanceof PriorityTaskManager.PriorityTooLowException)) {
                    if (cause2 instanceof IOException) {
                        throw ((IOException) cause2);
                    }
                    Util.sneakyThrow(e2);
                    throw null;
                }
            } finally {
                runnableFutureTask.blockUntilFinished();
                removeActiveRunnable((RunnableFutureTask<?, ?>) runnableFutureTask);
            }
        }
        throw new InterruptedException();
    }

    public final M getManifest(final DataSource dataSource, final DataSpec dataSpec, boolean z) throws InterruptedException, IOException {
        return (M) execute(new RunnableFutureTask<M, IOException>() { // from class: androidx.media3.exoplayer.offline.SegmentDownloader.1
            @Override // androidx.media3.common.util.RunnableFutureTask
            public M doWork() throws IOException {
                return (M) ParsingLoadable.load(dataSource, SegmentDownloader.this.manifestParser, dataSpec, 4);
            }
        }, z);
    }

    public abstract List<Segment> getSegments(DataSource dataSource, M m, boolean z) throws InterruptedException, IOException;

    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void remove() {
        CacheDataSource cacheDataSourceCreateDataSourceForRemovingDownload = this.cacheDataSourceFactory.createDataSourceForRemovingDownload();
        try {
            try {
                List<Segment> segments = getSegments(cacheDataSourceCreateDataSourceForRemovingDownload, getManifest(cacheDataSourceCreateDataSourceForRemovingDownload, this.manifestDataSpec, true), true);
                for (int i = 0; i < segments.size(); i++) {
                    this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(segments.get(i).dataSpec));
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (Exception unused2) {
            }
        } finally {
            this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(this.manifestDataSpec));
        }
    }

    public final void removeActiveRunnable(RunnableFutureTask<?, ?> runnableFutureTask) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(runnableFutureTask);
        }
    }

    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> parser, CacheDataSource.Factory factory, Executor executor, long j) {
        mediaItem.localConfiguration.getClass();
        this.manifestDataSpec = getCompressibleDataSpec(mediaItem.localConfiguration.uri);
        this.manifestParser = parser;
        this.streamKeys = new ArrayList<>(mediaItem.localConfiguration.streamKeys);
        this.cacheDataSourceFactory = factory;
        this.executor = executor;
        Cache cache = factory.cache;
        cache.getClass();
        this.cache = cache;
        this.cacheKeyFactory = factory.cacheKeyFactory;
        this.priorityTaskManager = factory.upstreamPriorityTaskManager;
        this.activeRunnables = new ArrayList<>();
        this.maxMergedSegmentStartTimeDiffUs = Util.msToUs(j);
    }

    public final void removeActiveRunnable(int i) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(i);
        }
    }
}
