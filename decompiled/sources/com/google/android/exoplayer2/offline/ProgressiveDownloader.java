package com.google.android.exoplayer2.offline;

import androidx.annotation.Nullable;
import androidx.media3.exoplayer.offline.DefaultDownloaderFactory$$ExternalSyntheticLambda0;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheWriter;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.RunnableFutureTask;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ProgressiveDownloader implements Downloader {
    public final CacheWriter cacheWriter;
    public final CacheDataSource dataSource;
    public final DataSpec dataSpec;
    public volatile RunnableFutureTask<Void, IOException> downloadRunnable;
    public final Executor executor;
    public volatile boolean isCanceled;

    @Nullable
    public final PriorityTaskManager priorityTaskManager;

    @Nullable
    public Downloader.ProgressListener progressListener;

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new DefaultDownloaderFactory$$ExternalSyntheticLambda0());
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public void cancel() {
        this.isCanceled = true;
        RunnableFutureTask<Void, IOException> runnableFutureTask = this.downloadRunnable;
        if (runnableFutureTask != null) {
            runnableFutureTask.cancel(true);
        }
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public void download(@Nullable Downloader.ProgressListener progressListener) throws InterruptedException, IOException {
        this.progressListener = progressListener;
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null) {
            priorityTaskManager.add(-1000);
        }
        boolean z = false;
        while (!z) {
            try {
                if (this.isCanceled) {
                    break;
                }
                this.downloadRunnable = new RunnableFutureTask<Void, IOException>() { // from class: com.google.android.exoplayer2.offline.ProgressiveDownloader.1
                    @Override // com.google.android.exoplayer2.util.RunnableFutureTask
                    public void cancelWork() {
                        ProgressiveDownloader.this.cacheWriter.isCanceled = true;
                    }

                    @Override // com.google.android.exoplayer2.util.RunnableFutureTask
                    public /* bridge */ /* synthetic */ Void doWork() throws Exception {
                        doWork2();
                        return null;
                    }

                    @Override // com.google.android.exoplayer2.util.RunnableFutureTask
                    /* JADX INFO: renamed from: doWork, reason: avoid collision after fix types in other method */
                    public Void doWork2() throws IOException {
                        ProgressiveDownloader.this.cacheWriter.cache();
                        return null;
                    }
                };
                PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
                if (priorityTaskManager2 != null) {
                    priorityTaskManager2.proceed(-1000);
                }
                this.executor.execute(this.downloadRunnable);
                try {
                    this.downloadRunnable.get();
                    z = true;
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    cause.getClass();
                    if (!(cause instanceof PriorityTaskManager.PriorityTooLowException)) {
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        Util.sneakyThrow(cause);
                        throw null;
                    }
                }
            } catch (Throwable th) {
                RunnableFutureTask<Void, IOException> runnableFutureTask = this.downloadRunnable;
                runnableFutureTask.getClass();
                runnableFutureTask.blockUntilFinished();
                PriorityTaskManager priorityTaskManager3 = this.priorityTaskManager;
                if (priorityTaskManager3 != null) {
                    priorityTaskManager3.remove(-1000);
                }
                throw th;
            }
        }
        RunnableFutureTask<Void, IOException> runnableFutureTask2 = this.downloadRunnable;
        runnableFutureTask2.getClass();
        runnableFutureTask2.blockUntilFinished();
        PriorityTaskManager priorityTaskManager4 = this.priorityTaskManager;
        if (priorityTaskManager4 != null) {
            priorityTaskManager4.remove(-1000);
        }
    }

    public final void onProgress(long j, long j2, long j3) {
        Downloader.ProgressListener progressListener = this.progressListener;
        if (progressListener == null) {
            return;
        }
        progressListener.onProgress(j, j2, (j == -1 || j == 0) ? -1.0f : (j2 * 100.0f) / j);
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public void remove() {
        CacheDataSource cacheDataSource = this.dataSource;
        cacheDataSource.cache.removeResource(cacheDataSource.cacheKeyFactory.buildCacheKey(this.dataSpec));
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor) {
        executor.getClass();
        this.executor = executor;
        mediaItem.localConfiguration.getClass();
        DataSpec.Builder builder = new DataSpec.Builder();
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        builder.uri = localConfiguration.uri;
        builder.key = localConfiguration.customCacheKey;
        builder.flags = 4;
        DataSpec dataSpecBuild = builder.build();
        this.dataSpec = dataSpecBuild;
        CacheDataSource cacheDataSourceCreateDataSourceForDownloading = factory.createDataSourceForDownloading();
        this.dataSource = cacheDataSourceCreateDataSourceForDownloading;
        this.cacheWriter = new CacheWriter(cacheDataSourceCreateDataSourceForDownloading, dataSpecBuild, null, new CacheWriter.ProgressListener() { // from class: com.google.android.exoplayer2.offline.ProgressiveDownloader$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.upstream.cache.CacheWriter.ProgressListener
            public final void onProgress(long j, long j2, long j3) {
                this.f$0.onProgress(j, j2, j3);
            }
        });
        this.priorityTaskManager = factory.upstreamPriorityTaskManager;
    }
}
