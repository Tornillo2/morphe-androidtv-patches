package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManagerProvider;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.ProgressiveMediaPeriod;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ProgressiveMediaSource extends BaseMediaSource implements ProgressiveMediaPeriod.Listener {
    public static final int DEFAULT_LOADING_CHECK_INTERVAL_BYTES = 1048576;
    public final int continueLoadingCheckIntervalBytes;
    public final DataSource.Factory dataSourceFactory;
    public final DrmSessionManager drmSessionManager;
    public final LoadErrorHandlingPolicy loadableLoadErrorHandlingPolicy;
    public final MediaItem.LocalConfiguration localConfiguration;
    public final MediaItem mediaItem;
    public final ProgressiveMediaExtractor.Factory progressiveMediaExtractorFactory;
    public long timelineDurationUs;
    public boolean timelineIsLive;
    public boolean timelineIsPlaceholder;
    public boolean timelineIsSeekable;

    @Nullable
    public TransferListener transferListener;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements MediaSourceFactory {
        public int continueLoadingCheckIntervalBytes;

        @Nullable
        public String customCacheKey;
        public final DataSource.Factory dataSourceFactory;
        public DrmSessionManagerProvider drmSessionManagerProvider;
        public LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        public ProgressiveMediaExtractor.Factory progressiveMediaExtractorFactory;

        @Nullable
        public Object tag;

        /* JADX INFO: renamed from: $r8$lambda$ynE478wrfTfYlYvAxVK-MNZ7MWE, reason: not valid java name */
        public static /* synthetic */ ProgressiveMediaExtractor m477$r8$lambda$ynE478wrfTfYlYvAxVKMNZ7MWE(ExtractorsFactory extractorsFactory, PlayerId playerId) {
            return new BundledExtractorsAdapter(extractorsFactory);
        }

        public Factory(DataSource.Factory factory) {
            this(factory, new DefaultExtractorsFactory());
        }

        @Override // com.google.android.exoplayer2.source.MediaSource.Factory
        public int[] getSupportedTypes() {
            return new int[]{4};
        }

        @CanIgnoreReturnValue
        public Factory setContinueLoadingCheckIntervalBytes(int i) {
            this.continueLoadingCheckIntervalBytes = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSource.Factory
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            setDrmSessionManagerProvider(drmSessionManagerProvider);
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSource.Factory
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            return this;
        }

        public Factory(DataSource.Factory factory, final ExtractorsFactory extractorsFactory) {
            this(factory, new ProgressiveMediaExtractor.Factory() { // from class: com.google.android.exoplayer2.source.ProgressiveMediaSource$Factory$$ExternalSyntheticLambda0
                @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor.Factory
                public final ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
                    return new BundledExtractorsAdapter(extractorsFactory);
                }
            });
        }

        @Override // com.google.android.exoplayer2.source.MediaSource.Factory
        public ProgressiveMediaSource createMediaSource(MediaItem mediaItem) {
            mediaItem.localConfiguration.getClass();
            MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            boolean z = false;
            boolean z2 = localConfiguration.tag == null && this.tag != null;
            if (localConfiguration.customCacheKey == null && this.customCacheKey != null) {
                z = true;
            }
            if (z2 && z) {
                MediaItem.Builder builder = new MediaItem.Builder(mediaItem);
                builder.tag = this.tag;
                builder.customCacheKey = this.customCacheKey;
                mediaItem = builder.build();
            } else if (z2) {
                MediaItem.Builder builder2 = new MediaItem.Builder(mediaItem);
                builder2.tag = this.tag;
                mediaItem = builder2.build();
            } else if (z) {
                MediaItem.Builder builder3 = new MediaItem.Builder(mediaItem);
                builder3.customCacheKey = this.customCacheKey;
                mediaItem = builder3.build();
            }
            MediaItem mediaItem2 = mediaItem;
            return new ProgressiveMediaSource(mediaItem2, this.dataSourceFactory, this.progressiveMediaExtractorFactory, this.drmSessionManagerProvider.get(mediaItem2), this.loadErrorHandlingPolicy, this.continueLoadingCheckIntervalBytes);
        }

        @Override // com.google.android.exoplayer2.source.MediaSource.Factory
        @CanIgnoreReturnValue
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            Assertions.checkNotNull(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
            this.drmSessionManagerProvider = drmSessionManagerProvider;
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSource.Factory
        @CanIgnoreReturnValue
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            Assertions.checkNotNull(loadErrorHandlingPolicy, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            return this;
        }

        public Factory(DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2) {
            this(factory, factory2, new DefaultDrmSessionManagerProvider(), new DefaultLoadErrorHandlingPolicy(-1), 1048576);
        }

        public Factory(DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2, DrmSessionManagerProvider drmSessionManagerProvider, LoadErrorHandlingPolicy loadErrorHandlingPolicy, int i) {
            this.dataSourceFactory = factory;
            this.progressiveMediaExtractorFactory = factory2;
            this.drmSessionManagerProvider = drmSessionManagerProvider;
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            this.continueLoadingCheckIntervalBytes = i;
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        DataSource dataSourceCreateDataSource = this.dataSourceFactory.createDataSource();
        TransferListener transferListener = this.transferListener;
        if (transferListener != null) {
            dataSourceCreateDataSource.addTransferListener(transferListener);
        }
        Uri uri = this.localConfiguration.uri;
        ProgressiveMediaExtractor.Factory factory = this.progressiveMediaExtractorFactory;
        PlayerId playerId = this.playerId;
        Assertions.checkStateNotNull(playerId);
        return new ProgressiveMediaPeriod(uri, dataSourceCreateDataSource, factory.createProgressiveMediaExtractor(playerId), this.drmSessionManager, createDrmEventDispatcher(mediaPeriodId), this.loadableLoadErrorHandlingPolicy, createEventDispatcher(mediaPeriodId), this, allocator, this.localConfiguration.customCacheKey, this.continueLoadingCheckIntervalBytes);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public final void notifySourceInfoRefreshed() {
        Timeline singlePeriodTimeline = new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false, this.timelineIsLive, (Object) null, this.mediaItem);
        if (this.timelineIsPlaceholder) {
            singlePeriodTimeline = new ForwardingTimeline(singlePeriodTimeline) { // from class: com.google.android.exoplayer2.source.ProgressiveMediaSource.1
                @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
                public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
                    super.getPeriod(i, period, z);
                    period.isPlaceholder = true;
                    return period;
                }

                @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
                public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
                    super.getWindow(i, window, j);
                    window.isPlaceholder = true;
                    return window;
                }
            };
        }
        refreshSourceInfo(singlePeriodTimeline);
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaPeriod.Listener
    public void onSourceInfoRefreshed(long j, boolean z, boolean z2) {
        if (j == -9223372036854775807L) {
            j = this.timelineDurationUs;
        }
        if (!this.timelineIsPlaceholder && this.timelineDurationUs == j && this.timelineIsSeekable == z && this.timelineIsLive == z2) {
            return;
        }
        this.timelineDurationUs = j;
        this.timelineIsSeekable = z;
        this.timelineIsLive = z2;
        this.timelineIsPlaceholder = false;
        notifySourceInfoRefreshed();
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        this.transferListener = transferListener;
        DrmSessionManager drmSessionManager = this.drmSessionManager;
        Looper looperMyLooper = Looper.myLooper();
        looperMyLooper.getClass();
        PlayerId playerId = this.playerId;
        Assertions.checkStateNotNull(playerId);
        drmSessionManager.setPlayer(looperMyLooper, playerId);
        this.drmSessionManager.prepare();
        notifySourceInfoRefreshed();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ProgressiveMediaPeriod) mediaPeriod).release();
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.drmSessionManager.release();
    }

    public ProgressiveMediaSource(MediaItem mediaItem, DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, int i) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        this.localConfiguration = localConfiguration;
        this.mediaItem = mediaItem;
        this.dataSourceFactory = factory;
        this.progressiveMediaExtractorFactory = factory2;
        this.drmSessionManager = drmSessionManager;
        this.loadableLoadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.continueLoadingCheckIntervalBytes = i;
        this.timelineIsPlaceholder = true;
        this.timelineDurationUs = -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }
}
