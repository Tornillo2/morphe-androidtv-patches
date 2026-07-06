package androidx.media3.exoplayer.audio;

import android.media.AudioTrack;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioTrackPositionTracker {
    public static final long FORCE_RESET_WORKAROUND_TIMEOUT_MS = 200;
    public static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    public static final long MAX_LATENCY_US = 5000000;
    public static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    public static final int MIN_LATENCY_SAMPLE_INTERVAL_US = 500000;
    public static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    public static final long MODE_SWITCH_SMOOTHING_DURATION_US = 1000000;
    public static final int PLAYSTATE_PAUSED = 2;
    public static final int PLAYSTATE_PLAYING = 3;
    public static final int PLAYSTATE_STOPPED = 1;
    public static final long RAW_PLAYBACK_HEAD_POSITION_UPDATE_INTERVAL_MS = 5;

    @Nullable
    public AudioTimestampPoller audioTimestampPoller;

    @Nullable
    public AudioTrack audioTrack;
    public float audioTrackPlaybackSpeed;
    public int bufferSize;
    public long bufferSizeUs;
    public Clock clock;
    public long endPlaybackHeadPosition;
    public boolean expectRawPlaybackHeadReset;
    public long forceResetWorkaroundTimeMs;

    @Nullable
    public Method getLatencyMethod;
    public boolean hasData;
    public boolean isOutputPcm;
    public long lastLatencySampleTimeUs;
    public long lastPlayheadSampleTimeUs;
    public long lastPositionUs;
    public long lastRawPlaybackHeadPositionSampleTimeMs;
    public boolean lastSampleUsedGetTimestampMode;
    public long lastSystemTimeUs;
    public long latencyUs;
    public final Listener listener;
    public boolean needsPassthroughWorkarounds;
    public int nextPlayheadOffsetIndex;
    public boolean notifiedPositionIncreasing;
    public int outputPcmFrameSize;
    public int outputSampleRate;
    public long passthroughWorkaroundPauseOffset;
    public int playheadOffsetCount;
    public final long[] playheadOffsets;
    public long previousModePositionUs;
    public long previousModeSystemTimeUs;
    public long rawPlaybackHeadPosition;
    public long rawPlaybackHeadWrapCount;
    public long smoothedPlayheadOffsetUs;
    public long stopPlaybackHeadPosition;
    public long stopTimestampUs;
    public long sumRawPlaybackHeadPosition;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onInvalidLatency(long j);

        void onPositionAdvancing(long j);

        void onPositionFramesMismatch(long j, long j2, long j3, long j4);

        void onSystemTimeUsMismatch(long j, long j2, long j3, long j4);

        void onUnderrun(int i, long j);
    }

    public AudioTrackPositionTracker(Listener listener) {
        listener.getClass();
        this.listener = listener;
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        this.playheadOffsets = new long[10];
        this.clock = Clock.DEFAULT;
    }

    public static boolean needsPassthroughWorkarounds(int i) {
        if (Util.SDK_INT < 23) {
            return i == 5 || i == 6;
        }
        return false;
    }

    public void expectRawPlaybackHeadReset() {
        this.expectRawPlaybackHeadReset = true;
    }

    public final boolean forceHasPendingData() {
        if (!this.needsPassthroughWorkarounds) {
            return false;
        }
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        return audioTrack.getPlayState() == 2 && getPlaybackHeadPosition() == 0;
    }

    public int getAvailableBufferSize(long j) {
        return this.bufferSize - ((int) (j - (getPlaybackHeadPosition() * ((long) this.outputPcmFrameSize))));
    }

    public long getCurrentPositionUs(boolean z) {
        long playbackHeadPositionUs;
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        if (audioTrack.getPlayState() == 3) {
            maybeSampleSyncParams();
        }
        long jNanoTime = this.clock.nanoTime() / 1000;
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        boolean zHasAdvancingTimestamp = audioTimestampPoller.hasAdvancingTimestamp();
        if (zHasAdvancingTimestamp) {
            playbackHeadPositionUs = Util.getMediaDurationForPlayoutDuration(jNanoTime - audioTimestampPoller.getTimestampSystemTimeUs(), this.audioTrackPlaybackSpeed) + Util.sampleCountToDurationUs(audioTimestampPoller.getTimestampPositionFrames(), this.outputSampleRate);
        } else {
            playbackHeadPositionUs = this.playheadOffsetCount == 0 ? getPlaybackHeadPositionUs() : Util.getMediaDurationForPlayoutDuration(this.smoothedPlayheadOffsetUs + jNanoTime, this.audioTrackPlaybackSpeed);
            if (!z) {
                playbackHeadPositionUs = Math.max(0L, playbackHeadPositionUs - this.latencyUs);
            }
        }
        if (this.lastSampleUsedGetTimestampMode != zHasAdvancingTimestamp) {
            this.previousModeSystemTimeUs = this.lastSystemTimeUs;
            this.previousModePositionUs = this.lastPositionUs;
        }
        long j = jNanoTime - this.previousModeSystemTimeUs;
        if (j < 1000000) {
            long mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(j, this.audioTrackPlaybackSpeed) + this.previousModePositionUs;
            long j2 = (j * 1000) / 1000000;
            playbackHeadPositionUs = (((1000 - j2) * mediaDurationForPlayoutDuration) + (playbackHeadPositionUs * j2)) / 1000;
        }
        if (!this.notifiedPositionIncreasing) {
            long j3 = this.lastPositionUs;
            if (playbackHeadPositionUs > j3) {
                this.notifiedPositionIncreasing = true;
                this.listener.onPositionAdvancing(this.clock.currentTimeMillis() - Util.usToMs(Util.getPlayoutDurationForMediaDuration(Util.usToMs(playbackHeadPositionUs - j3), this.audioTrackPlaybackSpeed)));
            }
        }
        this.lastSystemTimeUs = jNanoTime;
        this.lastPositionUs = playbackHeadPositionUs;
        this.lastSampleUsedGetTimestampMode = zHasAdvancingTimestamp;
        return playbackHeadPositionUs;
    }

    public final long getPlaybackHeadPosition() {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        if (this.stopTimestampUs == -9223372036854775807L) {
            if (jElapsedRealtime - this.lastRawPlaybackHeadPositionSampleTimeMs >= 5) {
                updateRawPlaybackHeadPosition(jElapsedRealtime);
                this.lastRawPlaybackHeadPositionSampleTimeMs = jElapsedRealtime;
            }
            return this.rawPlaybackHeadPosition + this.sumRawPlaybackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
        }
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        if (audioTrack.getPlayState() == 2) {
            return this.stopPlaybackHeadPosition;
        }
        return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + Util.durationUsToSampleCount(Util.getMediaDurationForPlayoutDuration(Util.msToUs(jElapsedRealtime) - this.stopTimestampUs, this.audioTrackPlaybackSpeed), this.outputSampleRate));
    }

    public final long getPlaybackHeadPositionUs() {
        return Util.sampleCountToDurationUs(getPlaybackHeadPosition(), this.outputSampleRate);
    }

    public void handleEndOfStream(long j) {
        this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
        this.stopTimestampUs = Util.msToUs(this.clock.elapsedRealtime());
        this.endPlaybackHeadPosition = j;
    }

    public boolean hasPendingData(long j) {
        return j > Util.durationUsToSampleCount(getCurrentPositionUs(false), this.outputSampleRate) || forceHasPendingData();
    }

    public boolean isPlaying() {
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        return audioTrack.getPlayState() == 3;
    }

    public boolean isStalled(long j) {
        return this.forceResetWorkaroundTimeMs != -9223372036854775807L && j > 0 && this.clock.elapsedRealtime() - this.forceResetWorkaroundTimeMs >= 200;
    }

    public boolean mayHandleBuffer(long j) {
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        int playState = audioTrack.getPlayState();
        if (this.needsPassthroughWorkarounds) {
            if (playState == 2) {
                this.hasData = false;
                return false;
            }
            if (playState == 1 && getPlaybackHeadPosition() == 0) {
                return false;
            }
        }
        boolean z = this.hasData;
        boolean zHasPendingData = hasPendingData(j);
        this.hasData = zHasPendingData;
        if (z && !zHasPendingData && playState != 1) {
            this.listener.onUnderrun(this.bufferSize, Util.usToMs(this.bufferSizeUs));
        }
        return true;
    }

    public final void maybePollAndCheckTimestamp(long j) {
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        if (audioTimestampPoller.maybePollTimestamp(j)) {
            long timestampSystemTimeUs = audioTimestampPoller.getTimestampSystemTimeUs();
            long timestampPositionFrames = audioTimestampPoller.getTimestampPositionFrames();
            long playbackHeadPositionUs = getPlaybackHeadPositionUs();
            if (Math.abs(timestampSystemTimeUs - j) > 5000000) {
                this.listener.onSystemTimeUsMismatch(timestampPositionFrames, timestampSystemTimeUs, j, playbackHeadPositionUs);
                audioTimestampPoller.updateState(4);
            } else if (Math.abs(Util.sampleCountToDurationUs(timestampPositionFrames, this.outputSampleRate) - playbackHeadPositionUs) <= 5000000) {
                audioTimestampPoller.acceptTimestamp();
            } else {
                this.listener.onPositionFramesMismatch(timestampPositionFrames, timestampSystemTimeUs, j, playbackHeadPositionUs);
                audioTimestampPoller.updateState(4);
            }
        }
    }

    public final void maybeSampleSyncParams() {
        long jNanoTime = this.clock.nanoTime() / 1000;
        if (jNanoTime - this.lastPlayheadSampleTimeUs >= 30000) {
            long playbackHeadPositionUs = getPlaybackHeadPositionUs();
            if (playbackHeadPositionUs != 0) {
                this.playheadOffsets[this.nextPlayheadOffsetIndex] = Util.getPlayoutDurationForMediaDuration(playbackHeadPositionUs, this.audioTrackPlaybackSpeed) - jNanoTime;
                this.nextPlayheadOffsetIndex = (this.nextPlayheadOffsetIndex + 1) % 10;
                int i = this.playheadOffsetCount;
                if (i < 10) {
                    this.playheadOffsetCount = i + 1;
                }
                this.lastPlayheadSampleTimeUs = jNanoTime;
                this.smoothedPlayheadOffsetUs = 0L;
                int i2 = 0;
                while (true) {
                    int i3 = this.playheadOffsetCount;
                    if (i2 >= i3) {
                        break;
                    }
                    this.smoothedPlayheadOffsetUs = (this.playheadOffsets[i2] / ((long) i3)) + this.smoothedPlayheadOffsetUs;
                    i2++;
                }
            } else {
                return;
            }
        }
        if (this.needsPassthroughWorkarounds) {
            return;
        }
        maybePollAndCheckTimestamp(jNanoTime);
        maybeUpdateLatency(jNanoTime);
    }

    public final void maybeUpdateLatency(long j) {
        Method method;
        if (!this.isOutputPcm || (method = this.getLatencyMethod) == null || j - this.lastLatencySampleTimeUs < 500000) {
            return;
        }
        try {
            AudioTrack audioTrack = this.audioTrack;
            audioTrack.getClass();
            Integer num = (Integer) method.invoke(audioTrack, null);
            Util.castNonNull(num);
            long jIntValue = (((long) num.intValue()) * 1000) - this.bufferSizeUs;
            this.latencyUs = jIntValue;
            long jMax = Math.max(jIntValue, 0L);
            this.latencyUs = jMax;
            if (jMax > 5000000) {
                this.listener.onInvalidLatency(jMax);
                this.latencyUs = 0L;
            }
        } catch (Exception unused) {
            this.getLatencyMethod = null;
        }
        this.lastLatencySampleTimeUs = j;
    }

    public boolean pause() {
        resetSyncParams();
        if (this.stopTimestampUs != -9223372036854775807L) {
            this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
            return false;
        }
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        audioTimestampPoller.reset();
        return true;
    }

    public void reset() {
        resetSyncParams();
        this.audioTrack = null;
        this.audioTimestampPoller = null;
    }

    public final void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.lastSystemTimeUs = 0L;
        this.previousModeSystemTimeUs = 0L;
        this.notifiedPositionIncreasing = false;
    }

    public void setAudioTrack(AudioTrack audioTrack, boolean z, int i, int i2, int i3) {
        this.audioTrack = audioTrack;
        this.outputPcmFrameSize = i2;
        this.bufferSize = i3;
        this.audioTimestampPoller = new AudioTimestampPoller(audioTrack);
        this.outputSampleRate = audioTrack.getSampleRate();
        this.needsPassthroughWorkarounds = z && needsPassthroughWorkarounds(i);
        boolean zIsEncodingLinearPcm = Util.isEncodingLinearPcm(i);
        this.isOutputPcm = zIsEncodingLinearPcm;
        this.bufferSizeUs = zIsEncodingLinearPcm ? Util.sampleCountToDurationUs(i3 / i2, this.outputSampleRate) : -9223372036854775807L;
        this.rawPlaybackHeadPosition = 0L;
        this.rawPlaybackHeadWrapCount = 0L;
        this.expectRawPlaybackHeadReset = false;
        this.sumRawPlaybackHeadPosition = 0L;
        this.passthroughWorkaroundPauseOffset = 0L;
        this.hasData = false;
        this.stopTimestampUs = -9223372036854775807L;
        this.forceResetWorkaroundTimeMs = -9223372036854775807L;
        this.lastLatencySampleTimeUs = 0L;
        this.latencyUs = 0L;
        this.audioTrackPlaybackSpeed = 1.0f;
    }

    public void setAudioTrackPlaybackSpeed(float f) {
        this.audioTrackPlaybackSpeed = f;
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        if (audioTimestampPoller != null) {
            audioTimestampPoller.reset();
        }
        resetSyncParams();
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void start() {
        if (this.stopTimestampUs != -9223372036854775807L) {
            this.stopTimestampUs = Util.msToUs(this.clock.elapsedRealtime());
        }
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        audioTimestampPoller.reset();
    }

    public final void updateRawPlaybackHeadPosition(long j) {
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        int playState = audioTrack.getPlayState();
        if (playState == 1) {
            return;
        }
        long playbackHeadPosition = ((long) audioTrack.getPlaybackHeadPosition()) & 4294967295L;
        if (this.needsPassthroughWorkarounds) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.passthroughWorkaroundPauseOffset = this.rawPlaybackHeadPosition;
            }
            playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
        }
        if (Util.SDK_INT <= 29) {
            if (playbackHeadPosition == 0 && this.rawPlaybackHeadPosition > 0 && playState == 3) {
                if (this.forceResetWorkaroundTimeMs == -9223372036854775807L) {
                    this.forceResetWorkaroundTimeMs = j;
                    return;
                }
                return;
            }
            this.forceResetWorkaroundTimeMs = -9223372036854775807L;
        }
        long j2 = this.rawPlaybackHeadPosition;
        if (j2 > playbackHeadPosition) {
            if (this.expectRawPlaybackHeadReset) {
                this.sumRawPlaybackHeadPosition += j2;
                this.expectRawPlaybackHeadReset = false;
            } else {
                this.rawPlaybackHeadWrapCount++;
            }
        }
        this.rawPlaybackHeadPosition = playbackHeadPosition;
    }
}
