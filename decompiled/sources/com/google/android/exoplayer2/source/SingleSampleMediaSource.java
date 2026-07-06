package com.google.android.exoplayer2.source;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SingleSampleMediaSource extends BaseMediaSource {
    public final DataSource.Factory dataSourceFactory;
    public final DataSpec dataSpec;
    public final long durationUs;
    public final Format format;
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    public final MediaItem mediaItem;
    public final Timeline timeline;

    @Nullable
    public TransferListener transferListener;
    public final boolean treatLoadErrorsAsEndOfStream;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory {
        public final DataSource.Factory dataSourceFactory;
        public LoadErrorHandlingPolicy loadErrorHandlingPolicy;

        @Nullable
        public Object tag;

        @Nullable
        public String trackId;
        public boolean treatLoadErrorsAsEndOfStream;

        public Factory(DataSource.Factory factory) {
            factory.getClass();
            this.dataSourceFactory = factory;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy(-1);
            this.treatLoadErrorsAsEndOfStream = true;
        }

        public SingleSampleMediaSource createMediaSource(MediaItem.SubtitleConfiguration subtitleConfiguration, long j) {
            return new SingleSampleMediaSource(this.trackId, subtitleConfiguration, this.dataSourceFactory, j, this.loadErrorHandlingPolicy, this.treatLoadErrorsAsEndOfStream, this.tag);
        }

        @CanIgnoreReturnValue
        public Factory setLoadErrorHandlingPolicy(@Nullable LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            if (loadErrorHandlingPolicy == null) {
                loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy(-1);
            }
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setTag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Factory setTrackId(@Nullable String str) {
            this.trackId = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setTreatLoadErrorsAsEndOfStream(boolean z) {
            this.treatLoadErrorsAsEndOfStream = z;
            return this;
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new SingleSampleMediaPeriod(this.dataSpec, this.dataSourceFactory, this.transferListener, this.format, this.durationUs, this.loadErrorHandlingPolicy, createEventDispatcher(mediaPeriodId), this.treatLoadErrorsAsEndOfStream);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        this.transferListener = transferListener;
        refreshSourceInfo(this.timeline);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((SingleSampleMediaPeriod) mediaPeriod).release();
    }

    public SingleSampleMediaSource(@Nullable String str, MediaItem.SubtitleConfiguration subtitleConfiguration, DataSource.Factory factory, long j, LoadErrorHandlingPolicy loadErrorHandlingPolicy, boolean z, @Nullable Object obj) {
        this.dataSourceFactory = factory;
        this.durationUs = j;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.treatLoadErrorsAsEndOfStream = z;
        MediaItem.Builder builder = new MediaItem.Builder();
        builder.uri = Uri.EMPTY;
        String string = subtitleConfiguration.uri.toString();
        string.getClass();
        builder.mediaId = string;
        builder.subtitleConfigurations = ImmutableList.copyOf((Collection) ImmutableList.of(subtitleConfiguration));
        builder.tag = obj;
        MediaItem mediaItemBuild = builder.build();
        this.mediaItem = mediaItemBuild;
        Format.Builder builder2 = new Format.Builder();
        builder2.sampleMimeType = (String) MoreObjects.firstNonNull(subtitleConfiguration.mimeType, "text/x-unknown");
        builder2.language = subtitleConfiguration.language;
        builder2.selectionFlags = subtitleConfiguration.selectionFlags;
        builder2.roleFlags = subtitleConfiguration.roleFlags;
        builder2.label = subtitleConfiguration.label;
        String str2 = subtitleConfiguration.id;
        builder2.id = str2 != null ? str2 : str;
        this.format = new Format(builder2);
        DataSpec.Builder builder3 = new DataSpec.Builder();
        builder3.uri = subtitleConfiguration.uri;
        builder3.flags = 1;
        this.dataSpec = builder3.build();
        this.timeline = new SinglePeriodTimeline(j, true, false, false, (Object) null, mediaItemBuild);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource
    public void releaseSourceInternal() {
    }
}
