package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.OptIn;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import com.amazon.reporting.Log;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public class AvLoadControl implements LoadControl {
    public static final String LOG_TAG = "AvLoadControl";
    public static final long MIN_BUFFER_DURATION_TO_START_PLAYBACK_US = TimeUnit.MILLISECONDS.toMicros(150);
    public final DefaultAllocator allocator = new DefaultAllocator(true, 65536, 0);
    public final boolean debugLogsEnabled = Log.isLoggable(LOG_TAG, 3);

    @Override // androidx.media3.exoplayer.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public long getBackBufferDurationUs() {
        return 0L;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return false;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldContinueLoading(long j, long j2, float f) {
        return true;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldStartPlayback(long j, float f, boolean z, long j2) {
        boolean z2 = j > MIN_BUFFER_DURATION_TO_START_PLAYBACK_US;
        if (this.debugLogsEnabled) {
            Log.d(LOG_TAG, "AvLoadControl.shouldStartPlayback(bufferedDuration=" + j + "us, playbackSpeed=" + f + ", rebuffering=" + z + ", targetLiveOffsetUs=" + j2 + ") = " + z2);
        }
        if (z2) {
            MpbLog.t("AvLoadControl.shouldStartPlayback() returned true: bufferedDuration=" + j + "us, playbackSpeed=" + f + ", rebuffering=" + z + ", targetLiveOffsetUs=" + j2);
        }
        return z2;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onTracksSelected(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        onTracksSelected(rendererArr, trackGroupArray, exoTrackSelectionArr);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldStartPlayback(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j, float f, boolean z, long j2) {
        return shouldStartPlayback(j, f, z, j2);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onPrepared() {
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onReleased() {
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onStopped() {
    }
}
