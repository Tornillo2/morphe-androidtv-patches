package com.google.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class WrappingMediaSource extends CompositeMediaSource<Void> {
    public static final Void CHILD_SOURCE_ID = null;
    public final MediaSource mediaSource;

    public WrappingMediaSource(MediaSource mediaSource) {
        this.mediaSource = mediaSource;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return this.mediaSource.createPeriod(mediaPeriodId, allocator, j);
    }

    public final void disableChildSource() {
        disableChildSource(CHILD_SOURCE_ID);
    }

    public final void enableChildSource() {
        enableChildSource(CHILD_SOURCE_ID);
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource, com.google.android.exoplayer2.source.MediaSource
    @Nullable
    public Timeline getInitialTimeline() {
        return this.mediaSource.getInitialTimeline();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId;
    }

    public long getMediaTimeForChildMediaTime(long j) {
        return j;
    }

    public int getWindowIndexForChildWindowIndex(int i) {
        return i;
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource, com.google.android.exoplayer2.source.MediaSource
    public boolean isSingleWindow() {
        return this.mediaSource.isSingleWindow();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Void r1, MediaSource mediaSource, Timeline timeline) {
        onChildSourceInfoRefreshed(timeline);
    }

    public final void prepareChildSource() {
        prepareChildSource(CHILD_SOURCE_ID, this.mediaSource);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public final void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareSourceInternal();
    }

    public final void releaseChildSource() {
        releaseChildSource(CHILD_SOURCE_ID);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.mediaSource.releasePeriod(mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void r1, MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaPeriodIdForChildMediaPeriodId(mediaPeriodId);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Void r1, long j) {
        return getMediaTimeForChildMediaTime(j);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
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
    public final long getMediaTimeForChildMediaTime2(Void r1, long j) {
        return getMediaTimeForChildMediaTime(j);
    }

    /* JADX INFO: renamed from: getWindowIndexForChildWindowIndex, reason: avoid collision after fix types in other method */
    public final int getWindowIndexForChildWindowIndex2(Void r1, int i) {
        return getWindowIndexForChildWindowIndex(i);
    }

    public void onChildSourceInfoRefreshed(Timeline timeline) {
        refreshSourceInfo(timeline);
    }
}
