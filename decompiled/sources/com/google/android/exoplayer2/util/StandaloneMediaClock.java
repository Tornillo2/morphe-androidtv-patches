package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.PlaybackParameters;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class StandaloneMediaClock implements MediaClock {
    public long baseElapsedMs;
    public long baseUs;
    public final Clock clock;
    public PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
    public boolean started;

    public StandaloneMediaClock(Clock clock) {
        this.clock = clock;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        long j = this.baseUs;
        if (!this.started) {
            return j;
        }
        long jElapsedRealtime = this.clock.elapsedRealtime() - this.baseElapsedMs;
        PlaybackParameters playbackParameters = this.playbackParameters;
        return (playbackParameters.speed == 1.0f ? Util.msToUs(jElapsedRealtime) : jElapsedRealtime * ((long) playbackParameters.scaledUsPerMs)) + j;
    }

    public void resetPosition(long j) {
        this.baseUs = j;
        if (this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
        }
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (this.started) {
            resetPosition(getPositionUs());
        }
        this.playbackParameters = playbackParameters;
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.baseElapsedMs = this.clock.elapsedRealtime();
        this.started = true;
    }

    public void stop() {
        if (this.started) {
            resetPosition(getPositionUs());
            this.started = false;
        }
    }
}
