package com.amazon.avod.mpb.media.playback.render;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MpbLog;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioTimestampPoller {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int ERROR_POLL_INTERVAL_US = 500000;
    public static final int FAST_POLL_INTERVAL_US = 10000;
    public static final int INITIALIZING_DURATION_US = 500000;
    public static final int POSITION_UNSET = -1;
    public static final int SLOW_POLL_INTERVAL_US = 10000000;
    public static final int STATE_ERROR = 4;
    public static final int STATE_INITIALIZING = 0;
    public static final int STATE_NO_TIMESTAMP = 3;
    public static final int STATE_TIMESTAMP = 1;
    public static final int STATE_TIMESTAMP_ADVANCING = 2;
    public static final long TIME_UNSET = -9223372036854775807L;
    public long initialTimestampPositionFrames;
    public long initializeSystemTimeUs;
    public final boolean isVerboseLoggingEnabled;
    public long lastTimestampSampleTimeUs;
    public long sampleIntervalUs;
    public int state;

    @NotNull
    public final AudioTimestampWrapper timestampWrapper;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AudioTimestampWrapper {

        @NotNull
        public AudioTimestamp audioTimestamp;

        @NotNull
        public final AudioTrack audioTrack;
        public long lastTimestampRawPositionFrames;
        public long rawTimestampFramePositionWrapCount;
        public long timestampPositionFrames;

        public AudioTimestampWrapper(@NotNull AudioTrack audioTrack) {
            Intrinsics.checkNotNullParameter(audioTrack, "audioTrack");
            this.audioTrack = audioTrack;
            this.audioTimestamp = new AudioTimestamp();
        }

        public final long getTimestampPositionFrames() {
            return this.timestampPositionFrames;
        }

        public final long getTimestampSystemTimeUs() {
            return TimeUnit.NANOSECONDS.toMicros(this.audioTimestamp.nanoTime);
        }

        public final boolean maybeUpdateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastTimestampRawPositionFrames > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastTimestampRawPositionFrames = j;
                this.timestampPositionFrames = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }

        public final void reset() {
            this.rawTimestampFramePositionWrapCount = 0L;
            this.lastTimestampRawPositionFrames = 0L;
            this.timestampPositionFrames = 0L;
            this.audioTimestamp = new AudioTimestamp();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public AudioTimestampPoller(@NotNull AudioTrack audioTrack, boolean z) {
        Intrinsics.checkNotNullParameter(audioTrack, "audioTrack");
        this.isVerboseLoggingEnabled = z;
        this.timestampWrapper = new AudioTimestampWrapper(audioTrack);
        updateState(0);
    }

    public final void acceptTimestamp() {
        if (this.state == 4) {
            updateState(0);
        }
    }

    public final long getTimestampPositionFrames() {
        return this.timestampWrapper.timestampPositionFrames;
    }

    public final long getTimestampSystemTimeUs() {
        return this.timestampWrapper.getTimestampSystemTimeUs();
    }

    public final boolean hasAdvancingTimestamp() {
        return this.state == 2;
    }

    public final boolean hasTimestamp() {
        int i = this.state;
        return i == 1 || i == 2;
    }

    public final boolean maybePollTimestamp(long j) {
        if (j - this.lastTimestampSampleTimeUs < this.sampleIntervalUs) {
            return false;
        }
        this.lastTimestampSampleTimeUs = j;
        boolean zMaybeUpdateTimestamp = this.timestampWrapper.maybeUpdateTimestamp();
        int i = this.state;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("AudioTimestampPoller unknown state ", this.state));
                        }
                    } else if (zMaybeUpdateTimestamp) {
                        updateState(0);
                        return zMaybeUpdateTimestamp;
                    }
                } else if (!zMaybeUpdateTimestamp) {
                    updateState(0);
                    return zMaybeUpdateTimestamp;
                }
            } else {
                if (!zMaybeUpdateTimestamp) {
                    updateState(0);
                    return zMaybeUpdateTimestamp;
                }
                if (this.timestampWrapper.timestampPositionFrames > this.initialTimestampPositionFrames) {
                    updateState(2);
                    return zMaybeUpdateTimestamp;
                }
            }
        } else {
            if (zMaybeUpdateTimestamp) {
                if (this.timestampWrapper.getTimestampSystemTimeUs() < this.initializeSystemTimeUs) {
                    return false;
                }
                this.initialTimestampPositionFrames = this.timestampWrapper.timestampPositionFrames;
                updateState(1);
                return zMaybeUpdateTimestamp;
            }
            if (j - this.initializeSystemTimeUs > 500000) {
                updateState(3);
            }
        }
        return zMaybeUpdateTimestamp;
    }

    public final void rejectTimestamp() {
        updateState(4);
    }

    public final void reset() {
        updateState(0);
    }

    public final void stop() {
        updateState(0);
        this.timestampWrapper.reset();
    }

    public final void updateState(int i) {
        if (this.isVerboseLoggingEnabled) {
            MpbLog.logf(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("AudioTimestampPoller updated state to ", i), new Object[0]);
        }
        if (i == 0) {
            this.lastTimestampSampleTimeUs = 0L;
            this.initialTimestampPositionFrames = -1L;
            this.initializeSystemTimeUs = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
            this.sampleIntervalUs = 10000L;
        } else if (i == 1) {
            this.sampleIntervalUs = 10000L;
        } else if (i == 2 || i == 3) {
            this.sampleIntervalUs = 10000000L;
        } else {
            if (i != 4) {
                throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("AudioTimestampPoller unknown state ", i));
            }
            this.sampleIntervalUs = 500000L;
        }
        this.state = i;
    }
}
