package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AudioTimestampPoller {
    public static final int ERROR_POLL_INTERVAL_US = 500000;
    public static final int FAST_POLL_INTERVAL_US = 10000;
    public static final int INITIALIZING_DURATION_US = 500000;
    public static final int SLOW_POLL_INTERVAL_US = 10000000;
    public static final int STATE_ERROR = 4;
    public static final int STATE_INITIALIZING = 0;
    public static final int STATE_NO_TIMESTAMP = 3;
    public static final int STATE_TIMESTAMP = 1;
    public static final int STATE_TIMESTAMP_ADVANCING = 2;

    @Nullable
    public final AudioTimestampV19 audioTimestamp;
    public long initialTimestampPositionFrames;
    public long initializeSystemTimeUs;
    public long lastTimestampSampleTimeUs;
    public long sampleIntervalUs;
    public int state;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(19)
    public static final class AudioTimestampV19 {
        public final AudioTimestamp audioTimestamp = new AudioTimestamp();
        public final AudioTrack audioTrack;
        public long lastTimestampPositionFrames;
        public long lastTimestampRawPositionFrames;
        public long rawTimestampFramePositionWrapCount;

        public AudioTimestampV19(AudioTrack audioTrack) {
            this.audioTrack = audioTrack;
        }

        public long getTimestampPositionFrames() {
            return this.lastTimestampPositionFrames;
        }

        public long getTimestampSystemTimeUs() {
            return this.audioTimestamp.nanoTime / 1000;
        }

        public boolean maybeUpdateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastTimestampRawPositionFrames > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastTimestampRawPositionFrames = j;
                this.lastTimestampPositionFrames = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }
    }

    public AudioTimestampPoller(AudioTrack audioTrack) {
        if (Util.SDK_INT >= 19) {
            this.audioTimestamp = new AudioTimestampV19(audioTrack);
            reset();
        } else {
            this.audioTimestamp = null;
            updateState(3);
        }
    }

    public void acceptTimestamp() {
        if (this.state == 4) {
            reset();
        }
    }

    @TargetApi(19)
    public long getTimestampPositionFrames() {
        AudioTimestampV19 audioTimestampV19 = this.audioTimestamp;
        if (audioTimestampV19 != null) {
            return audioTimestampV19.lastTimestampPositionFrames;
        }
        return -1L;
    }

    @TargetApi(19)
    public long getTimestampSystemTimeUs() {
        AudioTimestampV19 audioTimestampV19 = this.audioTimestamp;
        if (audioTimestampV19 != null) {
            return audioTimestampV19.getTimestampSystemTimeUs();
        }
        return -9223372036854775807L;
    }

    public boolean hasAdvancingTimestamp() {
        return this.state == 2;
    }

    public boolean hasTimestamp() {
        int i = this.state;
        return i == 1 || i == 2;
    }

    public boolean maybePollTimestamp(long j) {
        return maybePollTimestamp(j, false);
    }

    public void rejectTimestamp() {
        updateState(4);
    }

    public void reset() {
        if (this.audioTimestamp != null) {
            updateState(0);
        }
    }

    public final void updateState(int i) {
        this.state = i;
        if (i == 0) {
            this.lastTimestampSampleTimeUs = 0L;
            this.initialTimestampPositionFrames = -1L;
            this.initializeSystemTimeUs = System.nanoTime() / 1000;
            this.sampleIntervalUs = 10000L;
            return;
        }
        if (i == 1) {
            this.sampleIntervalUs = 10000L;
            return;
        }
        if (i == 2 || i == 3) {
            this.sampleIntervalUs = 10000000L;
        } else {
            if (i != 4) {
                throw new IllegalStateException();
            }
            this.sampleIntervalUs = 500000L;
        }
    }

    @TargetApi(19)
    public boolean maybePollTimestamp(long j, boolean z) {
        if (!z && (this.audioTimestamp == null || j - this.lastTimestampSampleTimeUs < this.sampleIntervalUs)) {
            return false;
        }
        this.lastTimestampSampleTimeUs = j;
        boolean zMaybeUpdateTimestamp = this.audioTimestamp.maybeUpdateTimestamp();
        int i = this.state;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException();
                        }
                    } else if (zMaybeUpdateTimestamp) {
                        reset();
                        return zMaybeUpdateTimestamp;
                    }
                } else if (!zMaybeUpdateTimestamp) {
                    reset();
                    return zMaybeUpdateTimestamp;
                }
            } else {
                if (!zMaybeUpdateTimestamp) {
                    reset();
                    return zMaybeUpdateTimestamp;
                }
                if (this.audioTimestamp.lastTimestampPositionFrames > this.initialTimestampPositionFrames) {
                    updateState(2);
                    return zMaybeUpdateTimestamp;
                }
            }
        } else {
            if (zMaybeUpdateTimestamp) {
                if (this.audioTimestamp.getTimestampSystemTimeUs() < this.initializeSystemTimeUs && !z) {
                    return false;
                }
                this.initialTimestampPositionFrames = this.audioTimestamp.lastTimestampPositionFrames;
                updateState(1);
                return zMaybeUpdateTimestamp;
            }
            if (j - this.initializeSystemTimeUs > 500000) {
                updateState(3);
            }
        }
        return zMaybeUpdateTimestamp;
    }
}
