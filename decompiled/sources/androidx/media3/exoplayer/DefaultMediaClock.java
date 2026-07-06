package androidx.media3.exoplayer;

import androidx.annotation.Nullable;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultMediaClock implements MediaClock {
    public boolean isUsingStandaloneClock = true;
    public final PlaybackParametersListener listener;

    @Nullable
    public MediaClock rendererClock;

    @Nullable
    public Renderer rendererClockSource;
    public final StandaloneMediaClock standaloneClock;
    public boolean standaloneClockIsStarted;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PlaybackParametersListener {
        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);
    }

    public DefaultMediaClock(PlaybackParametersListener playbackParametersListener, Clock clock) {
        this.listener = playbackParametersListener;
        this.standaloneClock = new StandaloneMediaClock(clock);
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        MediaClock mediaClock = this.rendererClock;
        return mediaClock != null ? mediaClock.getPlaybackParameters() : this.standaloneClock.playbackParameters;
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public long getPositionUs() {
        if (this.isUsingStandaloneClock) {
            return this.standaloneClock.getPositionUs();
        }
        MediaClock mediaClock = this.rendererClock;
        mediaClock.getClass();
        return mediaClock.getPositionUs();
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public boolean hasSkippedSilenceSinceLastCall() {
        if (this.isUsingStandaloneClock) {
            this.standaloneClock.getClass();
            return false;
        }
        MediaClock mediaClock = this.rendererClock;
        mediaClock.getClass();
        return mediaClock.hasSkippedSilenceSinceLastCall();
    }

    public void onRendererDisabled(Renderer renderer) {
        if (renderer == this.rendererClockSource) {
            this.rendererClock = null;
            this.rendererClockSource = null;
            this.isUsingStandaloneClock = true;
        }
    }

    public void onRendererEnabled(Renderer renderer) throws ExoPlaybackException {
        MediaClock mediaClock;
        MediaClock mediaClock2 = renderer.getMediaClock();
        if (mediaClock2 == null || mediaClock2 == (mediaClock = this.rendererClock)) {
            return;
        }
        if (mediaClock != null) {
            throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."), 1000);
        }
        this.rendererClock = mediaClock2;
        this.rendererClockSource = renderer;
        mediaClock2.setPlaybackParameters(this.standaloneClock.playbackParameters);
    }

    public void resetPosition(long j) {
        this.standaloneClock.resetPosition(j);
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        MediaClock mediaClock = this.rendererClock;
        if (mediaClock != null) {
            mediaClock.setPlaybackParameters(playbackParameters);
            playbackParameters = this.rendererClock.getPlaybackParameters();
        }
        this.standaloneClock.setPlaybackParameters(playbackParameters);
    }

    public final boolean shouldUseStandaloneClock(boolean z) {
        Renderer renderer = this.rendererClockSource;
        if (renderer == null || renderer.isEnded()) {
            return true;
        }
        if (z && this.rendererClockSource.getState() != 2) {
            return true;
        }
        if (this.rendererClockSource.isReady()) {
            return false;
        }
        return z || this.rendererClockSource.hasReadStreamToEnd();
    }

    public void start() {
        this.standaloneClockIsStarted = true;
        this.standaloneClock.start();
    }

    public void stop() {
        this.standaloneClockIsStarted = false;
        this.standaloneClock.stop();
    }

    public long syncAndGetPositionUs(boolean z) {
        syncClocks(z);
        return getPositionUs();
    }

    public final void syncClocks(boolean z) {
        if (shouldUseStandaloneClock(z)) {
            this.isUsingStandaloneClock = true;
            if (this.standaloneClockIsStarted) {
                this.standaloneClock.start();
                return;
            }
            return;
        }
        MediaClock mediaClock = this.rendererClock;
        mediaClock.getClass();
        long positionUs = mediaClock.getPositionUs();
        if (this.isUsingStandaloneClock) {
            if (positionUs < this.standaloneClock.getPositionUs()) {
                this.standaloneClock.stop();
                return;
            } else {
                this.isUsingStandaloneClock = false;
                if (this.standaloneClockIsStarted) {
                    this.standaloneClock.start();
                }
            }
        }
        this.standaloneClock.resetPosition(positionUs);
        PlaybackParameters playbackParameters = mediaClock.getPlaybackParameters();
        if (playbackParameters.equals(this.standaloneClock.playbackParameters)) {
            return;
        }
        this.standaloneClock.setPlaybackParameters(playbackParameters);
        this.listener.onPlaybackParametersChanged(playbackParameters);
    }
}
