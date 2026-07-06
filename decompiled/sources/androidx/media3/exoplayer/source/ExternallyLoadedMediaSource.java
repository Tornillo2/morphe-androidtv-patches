package androidx.media3.exoplayer.source;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ExternallyLoadedMediaSource extends BaseMediaSource {
    public final ExternalLoader externalLoader;

    @GuardedBy("this")
    public MediaItem mediaItem;
    public final long timelineDurationUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements MediaSource.Factory {
        public final ExternalLoader externalLoader;
        public final long timelineDurationUs;

        public Factory(long j, ExternalLoader externalLoader) {
            this.timelineDurationUs = j;
            this.externalLoader = externalLoader;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public int[] getSupportedTypes() {
            return new int[]{4};
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public ExternallyLoadedMediaSource createMediaSource(MediaItem mediaItem) {
            return new ExternallyLoadedMediaSource(mediaItem, this.timelineDurationUs, this.externalLoader);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            return this;
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        MediaItem.LocalConfiguration localConfiguration2 = getMediaItem().localConfiguration;
        localConfiguration2.getClass();
        if (localConfiguration == null || !localConfiguration.uri.equals(localConfiguration2.uri) || !Objects.equals(localConfiguration.mimeType, localConfiguration2.mimeType)) {
            return false;
        }
        long j = localConfiguration.imageDurationMs;
        return j == -9223372036854775807L || Util.msToUs(j) == this.timelineDurationUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaItem mediaItem = getMediaItem();
        mediaItem.localConfiguration.getClass();
        Assertions.checkNotNull(mediaItem.localConfiguration.mimeType, "Externally loaded mediaItems require a MIME type.");
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        return new ExternallyLoadedMediaPeriod(localConfiguration.uri, localConfiguration.mimeType, this.externalLoader);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        refreshSourceInfo(new SinglePeriodTimeline(this.timelineDurationUs, true, false, false, (Object) null, getMediaItem()));
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ExternallyLoadedMediaPeriod) mediaPeriod).releasePeriod();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public ExternallyLoadedMediaSource(MediaItem mediaItem, long j, ExternalLoader externalLoader) {
        this.mediaItem = mediaItem;
        this.timelineDurationUs = j;
        this.externalLoader = externalLoader;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
    }
}
