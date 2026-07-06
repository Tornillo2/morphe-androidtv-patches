package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SinglePeriodTimeline;
import androidx.media3.exoplayer.upstream.Allocator;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.AvMediaPeriod;
import com.amazon.livingroom.mediapipelinebackend.AvSampleStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public final class AvMediaSource implements MediaSource {
    public final AvSampleStream.AspectRatioSetter aspectRatioSetter;
    public AvMediaPeriod avMediaPeriod;
    public final DeviceProperties deviceProperties;
    public final ExoDrmSessionManager drmSessionManager;
    public final Format[] formats;
    public final AvMediaPeriod.Listener mediaPeriodListener;
    public final MinervaMetrics minervaMetrics;
    public final NativeMediaPipelineBackend nativeMpb;
    public final Timeline timeline;

    public AvMediaSource(NativeMediaPipelineBackend nativeMediaPipelineBackend, Format[] formatArr, AvMediaPeriod.Listener listener, MinervaMetrics minervaMetrics, DeviceProperties deviceProperties, ExoDrmSessionManager exoDrmSessionManager, AvSampleStream.AspectRatioSetter aspectRatioSetter) {
        this.nativeMpb = nativeMediaPipelineBackend;
        this.formats = formatArr;
        this.mediaPeriodListener = listener;
        this.minervaMetrics = minervaMetrics;
        this.deviceProperties = deviceProperties;
        this.drmSessionManager = exoDrmSessionManager;
        this.aspectRatioSetter = aspectRatioSetter;
        MpbLog.t("Creating New AVMediaSource");
        this.timeline = new SinglePeriodTimeline(-9223372036854775807L, false, false, false, (Object) null, new MediaItem.Builder().build());
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ boolean canUpdateMediaItem(MediaItem mediaItem) {
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MpbLog.t("AvMediaSource.createPeriod - periodUid=" + mediaPeriodId.periodUid + " startPositionUs=" + j);
        AvMediaPeriod avMediaPeriod = new AvMediaPeriod(this.nativeMpb, this.formats, this.mediaPeriodListener, this.minervaMetrics, this.deviceProperties, this.drmSessionManager, this.aspectRatioSetter);
        this.avMediaPeriod = avMediaPeriod;
        return avMediaPeriod;
    }

    public String describeState() {
        if (this.avMediaPeriod == null) {
            return "MediaPeriod not initialized - No state info available";
        }
        return "[MediaPeriodState: " + this.avMediaPeriod.describeState() + "]";
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ Timeline getInitialTimeline() {
        return null;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return null;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ boolean isSingleWindow() {
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ void prepareSource(MediaSource.MediaSourceCaller mediaSourceCaller, TransferListener transferListener) {
        prepareSource(mediaSourceCaller, transferListener, PlayerId.UNSET);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((AvMediaPeriod) mediaPeriod).getClass();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void prepareSource(MediaSource.MediaSourceCaller mediaSourceCaller, @Nullable TransferListener transferListener, PlayerId playerId) {
        MpbLog.t("prepareSource notifying listener");
        mediaSourceCaller.onSourceInfoRefreshed(this, this.timeline);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void disable(MediaSource.MediaSourceCaller mediaSourceCaller) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void enable(MediaSource.MediaSourceCaller mediaSourceCaller) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releaseSource(MediaSource.MediaSourceCaller mediaSourceCaller) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ void updateMediaItem(MediaItem mediaItem) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener) {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
    }
}
