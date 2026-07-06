package androidx.media3.exoplayer.video;

import android.content.Context;
import android.view.Surface;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class VideoFrameReleaseControl {
    public static final int FRAME_RELEASE_DROP = 2;
    public static final int FRAME_RELEASE_IGNORE = 4;
    public static final int FRAME_RELEASE_IMMEDIATELY = 0;
    public static final int FRAME_RELEASE_SCHEDULED = 1;
    public static final int FRAME_RELEASE_SKIP = 3;
    public static final int FRAME_RELEASE_TRY_AGAIN_LATER = 5;
    public static final long MAX_EARLY_US_THRESHOLD = 50000;
    public final long allowedJoiningTimeMs;
    public final VideoFrameReleaseHelper frameReleaseHelper;
    public final FrameTimingEvaluator frameTimingEvaluator;
    public long lastReleaseRealtimeUs;
    public boolean started;
    public int firstFrameState = 0;
    public long initialPositionUs = -9223372036854775807L;
    public long lastPresentationTimeUs = -9223372036854775807L;
    public long joiningDeadlineMs = -9223372036854775807L;
    public float playbackSpeed = 1.0f;
    public Clock clock = Clock.DEFAULT;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @UnstableApi
    public @interface FrameReleaseAction {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FrameReleaseInfo {
        public long earlyUs = -9223372036854775807L;
        public long releaseTimeNs = -9223372036854775807L;

        public long getEarlyUs() {
            return this.earlyUs;
        }

        public long getReleaseTimeNs() {
            return this.releaseTimeNs;
        }

        public final void reset() {
            this.earlyUs = -9223372036854775807L;
            this.releaseTimeNs = -9223372036854775807L;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface FrameTimingEvaluator {
        boolean shouldDropFrame(long j, long j2, boolean z);

        boolean shouldForceReleaseFrame(long j, long j2);

        boolean shouldIgnoreFrame(long j, long j2, long j3, boolean z, boolean z2) throws ExoPlaybackException;
    }

    public VideoFrameReleaseControl(Context context, FrameTimingEvaluator frameTimingEvaluator, long j) {
        this.frameTimingEvaluator = frameTimingEvaluator;
        this.allowedJoiningTimeMs = j;
        this.frameReleaseHelper = new VideoFrameReleaseHelper(context);
    }

    public final long calculateEarlyTimeUs(long j, long j2, long j3) {
        long j4 = (long) ((j3 - j) / ((double) this.playbackSpeed));
        return this.started ? j4 - (Util.msToUs(this.clock.elapsedRealtime()) - j2) : j4;
    }

    public int getFrameReleaseAction(long j, long j2, long j3, long j4, boolean z, FrameReleaseInfo frameReleaseInfo) throws ExoPlaybackException {
        frameReleaseInfo.reset();
        if (this.initialPositionUs == -9223372036854775807L) {
            this.initialPositionUs = j2;
        }
        if (this.lastPresentationTimeUs != j) {
            this.frameReleaseHelper.onNextFrame(j);
            this.lastPresentationTimeUs = j;
        }
        long jCalculateEarlyTimeUs = calculateEarlyTimeUs(j2, j3, j);
        frameReleaseInfo.earlyUs = jCalculateEarlyTimeUs;
        if (shouldForceRelease(j2, jCalculateEarlyTimeUs, j4)) {
            return 0;
        }
        if (!this.started || j2 == this.initialPositionUs) {
            return 5;
        }
        long jNanoTime = this.clock.nanoTime();
        long jAdjustReleaseTime = this.frameReleaseHelper.adjustReleaseTime((frameReleaseInfo.earlyUs * 1000) + jNanoTime);
        frameReleaseInfo.releaseTimeNs = jAdjustReleaseTime;
        long j5 = (jAdjustReleaseTime - jNanoTime) / 1000;
        frameReleaseInfo.earlyUs = j5;
        boolean z2 = this.joiningDeadlineMs != -9223372036854775807L;
        if (this.frameTimingEvaluator.shouldIgnoreFrame(j5, j2, j3, z, z2)) {
            return 4;
        }
        return this.frameTimingEvaluator.shouldDropFrame(frameReleaseInfo.earlyUs, j3, z) ? z2 ? 3 : 2 : frameReleaseInfo.earlyUs > MAX_EARLY_US_THRESHOLD ? 5 : 1;
    }

    public boolean isReady(boolean z) {
        if (z && this.firstFrameState == 3) {
            this.joiningDeadlineMs = -9223372036854775807L;
            return true;
        }
        if (this.joiningDeadlineMs == -9223372036854775807L) {
            return false;
        }
        if (this.clock.elapsedRealtime() < this.joiningDeadlineMs) {
            return true;
        }
        this.joiningDeadlineMs = -9223372036854775807L;
        return false;
    }

    public void join() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? this.clock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

    public final void lowerFirstFrameState(int i) {
        this.firstFrameState = Math.min(this.firstFrameState, i);
    }

    public void onDisabled() {
        lowerFirstFrameState(0);
    }

    public void onEnabled(boolean z) {
        this.firstFrameState = z ? 1 : 0;
    }

    public boolean onFrameReleasedIsFirstFrame() {
        boolean z = this.firstFrameState != 3;
        this.firstFrameState = 3;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        return z;
    }

    public void onProcessedStreamChange() {
        lowerFirstFrameState(2);
    }

    public void onStarted() {
        this.started = true;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        this.frameReleaseHelper.onStarted();
    }

    public void onStopped() {
        this.started = false;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.frameReleaseHelper.onStopped();
    }

    public void reset() {
        this.frameReleaseHelper.resetAdjustment();
        this.lastPresentationTimeUs = -9223372036854775807L;
        this.initialPositionUs = -9223372036854775807L;
        lowerFirstFrameState(1);
        this.joiningDeadlineMs = -9223372036854775807L;
    }

    public void setChangeFrameRateStrategy(int i) {
        this.frameReleaseHelper.setChangeFrameRateStrategy(i);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void setFrameRate(float f) {
        this.frameReleaseHelper.onFormatChanged(f);
    }

    public void setOutputSurface(@Nullable Surface surface) {
        this.frameReleaseHelper.onSurfaceChanged(surface);
        lowerFirstFrameState(1);
    }

    public void setPlaybackSpeed(float f) {
        this.playbackSpeed = f;
        this.frameReleaseHelper.onPlaybackSpeed(f);
    }

    public final boolean shouldForceRelease(long j, long j2, long j3) {
        if (this.joiningDeadlineMs != -9223372036854775807L) {
            return false;
        }
        int i = this.firstFrameState;
        if (i == 0) {
            return this.started;
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new IllegalStateException();
                }
                long jMsToUs = Util.msToUs(this.clock.elapsedRealtime()) - this.lastReleaseRealtimeUs;
                if (!this.started || !this.frameTimingEvaluator.shouldForceReleaseFrame(j2, jMsToUs)) {
                    return false;
                }
            } else if (j < j3) {
                return false;
            }
        }
        return true;
    }

    public void allowReleaseFirstFrameBeforeStarted() {
    }
}
