package androidx.media3.exoplayer.source;

import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class WrappingMediaSource extends CompositeMediaSource<Void> {
    public static final Void CHILD_SOURCE_ID = null;
    public final MediaSource mediaSource;

    public WrappingMediaSource(MediaSource mediaSource) {
        this.mediaSource = mediaSource;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return this.mediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return this.mediaSource.createPeriod(mediaPeriodId, allocator, j);
    }

    public final void disableChildSource() {
        disableChildSource(CHILD_SOURCE_ID);
    }

    public final void enableChildSource() {
        enableChildSource(CHILD_SOURCE_ID);
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    @Nullable
    public Timeline getInitialTimeline() {
        return this.mediaSource.getInitialTimeline();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId;
    }

    public long getMediaTimeForChildMediaTime(long j, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        return j;
    }

    public int getWindowIndexForChildWindowIndex(int i) {
        return i;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean isSingleWindow() {
        return this.mediaSource.isSingleWindow();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Void r1, MediaSource mediaSource, Timeline timeline) {
        onChildSourceInfoRefreshed(timeline);
    }

    public final void prepareChildSource() {
        prepareChildSource(CHILD_SOURCE_ID, this.mediaSource);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public final void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareSourceInternal();
    }

    public final void releaseChildSource() {
        releaseChildSource(CHILD_SOURCE_ID);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.mediaSource.releasePeriod(mediaPeriod);
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        this.mediaSource.updateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void r1, MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaPeriodIdForChildMediaPeriodId(mediaPeriodId);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Void r1, long j, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaTimeForChildMediaTime(j, mediaPeriodId);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public int getWindowIndexForChildWindowIndex(Void r1, int i) {
        return getWindowIndexForChildWindowIndex(i);
    }

    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method */
    public final void onChildSourceInfoRefreshed2(Void r1, MediaSource mediaSource, Timeline timeline) {
        onChildSourceInfoRefreshed(timeline);
    }

    public void prepareSourceInternal() {
        prepareChildSource();
    }

    @Nullable
    /* JADX INFO: renamed from: getMediaPeriodIdForChildMediaPeriodId, reason: avoid collision after fix types in other method */
    public final MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId2(Void r1, MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaPeriodIdForChildMediaPeriodId(mediaPeriodId);
    }

    /* JADX INFO: renamed from: getMediaTimeForChildMediaTime, reason: avoid collision after fix types in other method */
    public final long getMediaTimeForChildMediaTime2(Void r1, long j, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaTimeForChildMediaTime(j, mediaPeriodId);
    }

    /* JADX INFO: renamed from: getWindowIndexForChildWindowIndex, reason: avoid collision after fix types in other method */
    public final int getWindowIndexForChildWindowIndex2(Void r1, int i) {
        return getWindowIndexForChildWindowIndex(i);
    }

    public void onChildSourceInfoRefreshed(Timeline timeline) {
        refreshSourceInfo(timeline);
    }
}
