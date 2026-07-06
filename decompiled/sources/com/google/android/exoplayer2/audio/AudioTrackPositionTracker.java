package com.google.android.exoplayer2.audio;

import android.media.AudioTrack;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.Util;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
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
    public static final String TAG = "AudioTrackPositionTracker";
    public final boolean DBG;
    public final boolean VDBG;
    public boolean applyDolbyPassThroughQuirk;

    @Nullable
    public AudioTimestampPoller audioTimestampPoller;

    @Nullable
    public AudioTrack audioTrack;
    public float audioTrackPlaybackSpeed;
    public int bufferSize;
    public long bufferSizeUs;
    public long endPlaybackHeadPosition;
    public long forceResetWorkaroundTimeMs;

    @Nullable
    public Method getLatencyMethod;
    public boolean hasData;
    public boolean isLatencyQuirkEnabled;
    public boolean isOutputPcm;
    public long lastLatencySampleTimeUs;
    public long lastPlayheadSampleTimeUs;
    public long lastPositionUs;
    public long lastRawPlaybackHeadPositionSampleTimeMs;
    public boolean lastSampleUsedGetTimestampMode;
    public long lastSystemTimeUs;
    public long latencyUs;
    public final Listener listener;
    public final Logger log;
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
    public long resumeSystemTimeUs;
    public long smoothedPlayheadOffsetUs;
    public long stopPlaybackHeadPosition;
    public long stopTimestampUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onInvalidLatency(long j);

        void onPositionAdvancing(long j);

        void onPositionFramesMismatch(long j, long j2, long j3, long j4);

        void onSystemTimeUsMismatch(long j, long j2, long j3, long j4);

        void onUnderrun(int i, long j);
    }

    public AudioTrackPositionTracker(Listener listener, boolean z) {
        Logger logger = new Logger(Logger.Module.Audio, TAG);
        this.log = logger;
        this.DBG = logger.allowDebug();
        this.VDBG = logger.allowVerbose();
        listener.getClass();
        this.listener = listener;
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
            } catch (Throwable th) {
                Log.w(TAG, "Some Amazon legacy devices throw unexpected errors", th);
            }
        }
        this.playheadOffsets = new long[10];
        this.isLatencyQuirkEnabled = z;
    }

    public static boolean needsPassthroughWorkarounds(int i) {
        if (Util.SDK_INT < 23) {
            return i == 5 || i == 6;
        }
        return false;
    }

    public final boolean forceHasPendingData() {
        if (this.needsPassthroughWorkarounds) {
            AudioTrack audioTrack = this.audioTrack;
            audioTrack.getClass();
            if (audioTrack.getPlayState() == 2 && getPlaybackHeadPosition() == 0) {
                return true;
            }
        }
        if (!AmazonQuirks.isLatencyQuirkEnabled()) {
            return false;
        }
        AudioTrack audioTrack2 = this.audioTrack;
        audioTrack2.getClass();
        return audioTrack2.getPlayState() == 3 && (System.nanoTime() / 1000) - this.resumeSystemTimeUs < 1000000;
    }

    public final long framesToDurationUs(long j) {
        return (j * 1000000) / ((long) this.outputSampleRate);
    }

    public final int getAudioSWLatencies() {
        Method method = this.getLatencyMethod;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.audioTrack, null)).intValue() * (this.outputSampleRate / 1000);
        } catch (Exception unused) {
            return 0;
        }
    }

    public int getAvailableBufferSize(long j) {
        return this.bufferSize - ((int) (j - (getPlaybackHeadPosition() * ((long) this.outputPcmFrameSize))));
    }

    public long getCurrentPositionUs(boolean z) {
        long j;
        long mediaDurationForPlayoutDuration;
        long jMax;
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        if (audioTrack.getPlayState() == 3 && !this.applyDolbyPassThroughQuirk) {
            maybeSampleSyncParams();
        }
        long jNanoTime = System.nanoTime() / 1000;
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        boolean zHasAdvancingTimestamp = audioTimestampPoller.hasAdvancingTimestamp();
        if (this.applyDolbyPassThroughQuirk) {
            long timestampSystemTimeUs = audioTimestampPoller.maybePollTimestamp(jNanoTime, true) ? audioTimestampPoller.getTimestampSystemTimeUs() : 0L;
            if (this.VDBG) {
                this.log.v("getCurrentPositionUs : applyDolbyPassThroughQuirk positionUs = " + timestampSystemTimeUs);
            }
            return timestampSystemTimeUs;
        }
        if (zHasAdvancingTimestamp) {
            long timestampPositionFrames = audioTimestampPoller.getTimestampPositionFrames();
            long jFramesToDurationUs = framesToDurationUs(timestampPositionFrames);
            long mediaDurationForPlayoutDuration2 = Util.getMediaDurationForPlayoutDuration(jNanoTime - audioTimestampPoller.getTimestampSystemTimeUs(), this.audioTrackPlaybackSpeed);
            jMax = jFramesToDurationUs + mediaDurationForPlayoutDuration2;
            j = 1000;
            if (this.VDBG) {
                Logger logger = this.log;
                StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("getCurrentPositionUs : hasTimestamp: positionUs = ", jMax, " timestampPositionFrames = ");
                sbM.append(timestampPositionFrames);
                sbM.append(" timestampPositionUs = ");
                sbM.append(jFramesToDurationUs);
                sbM.append(" elapsedSinceTimestampUs = ");
                sbM.append(mediaDurationForPlayoutDuration2);
                sbM.append(" systemTimeUs = ");
                sbM.append(jNanoTime);
                sbM.append(" timestampSysTimeUs  = ");
                sbM.append(audioTimestampPoller.getTimestampSystemTimeUs());
                logger.v(sbM.toString());
            }
        } else {
            j = 1000;
            if (this.playheadOffsetCount == 0) {
                mediaDurationForPlayoutDuration = getPlaybackHeadPositionUs();
                if (this.VDBG) {
                    this.log.v("getCurrentPositionUs : pre-latency adjustment positionUs = " + mediaDurationForPlayoutDuration);
                }
            } else {
                mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(this.smoothedPlayheadOffsetUs + jNanoTime, this.audioTrackPlaybackSpeed);
                if (this.VDBG) {
                    Logger logger2 = this.log;
                    StringBuilder sbM2 = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("getCurrentPositionUs : pre-latency adjustment positionUs = ", mediaDurationForPlayoutDuration, " smoothedPlayheadOffsetUs = ");
                    sbM2.append(this.smoothedPlayheadOffsetUs);
                    sbM2.append(" systemTimeUs = ");
                    sbM2.append(jNanoTime);
                    logger2.v(sbM2.toString());
                }
            }
            jMax = !z ? Math.max(0L, mediaDurationForPlayoutDuration - this.latencyUs) : mediaDurationForPlayoutDuration;
            if (this.VDBG) {
                Logger logger3 = this.log;
                StringBuilder sbM3 = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("getCurrentPositionUs : post-latency adjustment positionUs = ", jMax, " latencyUs = ");
                sbM3.append(this.latencyUs);
                logger3.v(sbM3.toString());
            }
        }
        if (this.lastSampleUsedGetTimestampMode != zHasAdvancingTimestamp) {
            this.previousModeSystemTimeUs = this.lastSystemTimeUs;
            this.previousModePositionUs = this.lastPositionUs;
        }
        long j2 = jNanoTime - this.previousModeSystemTimeUs;
        if (j2 < 1000000) {
            long mediaDurationForPlayoutDuration3 = Util.getMediaDurationForPlayoutDuration(j2, this.audioTrackPlaybackSpeed) + this.previousModePositionUs;
            long j3 = (j2 * j) / 1000000;
            jMax = (((j - j3) * mediaDurationForPlayoutDuration3) + (jMax * j3)) / j;
        }
        if (!this.notifiedPositionIncreasing) {
            long j4 = this.lastPositionUs;
            if (jMax > j4) {
                this.notifiedPositionIncreasing = true;
                this.listener.onPositionAdvancing(System.currentTimeMillis() - Util.usToMs(Util.getPlayoutDurationForMediaDuration(Util.usToMs(jMax - j4), this.audioTrackPlaybackSpeed)));
            }
        }
        this.lastSystemTimeUs = jNanoTime;
        this.lastPositionUs = jMax;
        this.lastSampleUsedGetTimestampMode = zHasAdvancingTimestamp;
        return jMax;
    }

    public final long getPlaybackHeadPosition() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.stopTimestampUs;
        if (j != -9223372036854775807L) {
            return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + ((Util.getMediaDurationForPlayoutDuration((jElapsedRealtime * 1000) - j, this.audioTrackPlaybackSpeed) * ((long) this.outputSampleRate)) / 1000000));
        }
        if (jElapsedRealtime - this.lastRawPlaybackHeadPositionSampleTimeMs >= 5) {
            updateRawPlaybackHeadPosition(jElapsedRealtime);
            this.lastRawPlaybackHeadPositionSampleTimeMs = jElapsedRealtime;
        }
        return this.rawPlaybackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
    }

    public final long getPlaybackHeadPositionUs() {
        return framesToDurationUs(getPlaybackHeadPosition());
    }

    public void handleEndOfStream(long j) {
        this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
        this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
        this.endPlaybackHeadPosition = j;
    }

    public boolean hasPendingData(long j) {
        boolean z = this.applyDolbyPassThroughQuirk || j > getPlaybackHeadPosition() || forceHasPendingData();
        if (this.VDBG) {
            this.log.v("hasPendingData = " + z);
        }
        return z;
    }

    public boolean isPlaying() {
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        return audioTrack.getPlayState() == 3;
    }

    public boolean isStalled(long j) {
        return this.forceResetWorkaroundTimeMs != -9223372036854775807L && j > 0 && SystemClock.elapsedRealtime() - this.forceResetWorkaroundTimeMs >= 200;
    }

    public boolean mayHandleBuffer(long j) {
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        int playState = audioTrack.getPlayState();
        if (this.needsPassthroughWorkarounds && !this.applyDolbyPassThroughQuirk) {
            if (playState == 2) {
                this.hasData = false;
                return false;
            }
            if (playState == 1 && getPlaybackHeadPosition() != 0) {
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
        if (audioTimestampPoller.maybePollTimestamp(j, false)) {
            long timestampSystemTimeUs = audioTimestampPoller.getTimestampSystemTimeUs();
            long timestampPositionFrames = audioTimestampPoller.getTimestampPositionFrames();
            long playbackHeadPositionUs = getPlaybackHeadPositionUs();
            if (Math.abs(timestampSystemTimeUs - j) > 5000000) {
                this.listener.onSystemTimeUsMismatch(timestampPositionFrames, timestampSystemTimeUs, j, playbackHeadPositionUs);
                audioTimestampPoller.updateState(4);
            } else if (Math.abs(framesToDurationUs(timestampPositionFrames) - playbackHeadPositionUs) <= 5000000) {
                audioTimestampPoller.acceptTimestamp();
            } else {
                this.listener.onPositionFramesMismatch(timestampPositionFrames, timestampSystemTimeUs, j, playbackHeadPositionUs);
                audioTimestampPoller.updateState(4);
            }
        }
    }

    public final void maybeSampleSyncParams() {
        long jNanoTime = System.nanoTime() / 1000;
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
        if (this.isLatencyQuirkEnabled) {
            AmazonQuirks.getAudioHWLatency();
            this.latencyUs = AmazonQuirks.AUDIO_HARDWARE_LATENCY_FOR_TABLETS;
            return;
        }
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
        if (this.DBG) {
            this.log.d("pause");
        }
        resetSyncParams();
        if (this.stopTimestampUs != -9223372036854775807L) {
            return false;
        }
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        audioTimestampPoller.reset();
        return true;
    }

    public void reset() {
        if (this.DBG) {
            this.log.d("reset");
        }
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

    public void setAudioTrack(AudioTrack audioTrack, boolean z, int i, int i2, int i3, boolean z2) {
        this.audioTrack = audioTrack;
        this.outputPcmFrameSize = i2;
        this.bufferSize = i3;
        this.applyDolbyPassThroughQuirk = z2;
        this.audioTimestampPoller = new AudioTimestampPoller(audioTrack);
        this.outputSampleRate = audioTrack.getSampleRate();
        this.needsPassthroughWorkarounds = z && needsPassthroughWorkarounds(i);
        boolean zIsEncodingLinearPcm = Util.isEncodingLinearPcm(i);
        this.isOutputPcm = zIsEncodingLinearPcm;
        this.bufferSizeUs = zIsEncodingLinearPcm ? framesToDurationUs(i3 / i2) : -9223372036854775807L;
        this.rawPlaybackHeadPosition = 0L;
        this.rawPlaybackHeadWrapCount = 0L;
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

    public void start() {
        if (this.DBG) {
            this.log.d("start");
        }
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        audioTimestampPoller.getClass();
        audioTimestampPoller.reset();
        this.resumeSystemTimeUs = System.nanoTime() / 1000;
    }

    public final void updateRawPlaybackHeadPosition(long j) {
        long playbackHeadPosition;
        AudioTrack audioTrack = this.audioTrack;
        audioTrack.getClass();
        int playState = audioTrack.getPlayState();
        if (playState == 1) {
            return;
        }
        if (this.isLatencyQuirkEnabled) {
            int playbackHeadPosition2 = audioTrack.getPlaybackHeadPosition();
            if (this.VDBG) {
                this.log.v("php = " + playbackHeadPosition2);
            }
            int playState2 = audioTrack.getPlayState();
            if (playState2 == 3 || (playState2 == 2 && playbackHeadPosition2 != 0)) {
                playbackHeadPosition2 += getAudioSWLatencies();
            }
            if (playbackHeadPosition2 < 0 && (System.nanoTime() / 1000) - this.resumeSystemTimeUs < 1000000) {
                this.log.i("php is negative during latency stabilization phase ...resetting to 0");
                playbackHeadPosition2 = 0;
            }
            playbackHeadPosition = 4294967295L & ((long) playbackHeadPosition2);
        } else {
            playbackHeadPosition = 4294967295L & ((long) audioTrack.getPlaybackHeadPosition());
            if (this.VDBG) {
                this.log.v("rawPlaybackHeadPosition = " + playbackHeadPosition);
            }
            if (this.needsPassthroughWorkarounds) {
                if (playState == 2 && playbackHeadPosition == 0) {
                    this.passthroughWorkaroundPauseOffset = this.rawPlaybackHeadPosition;
                }
                playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
            }
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
        if (j2 > playbackHeadPosition && j2 > 2147483647L && j2 - playbackHeadPosition >= 2147483647L) {
            this.log.i("The playback head position wrapped around");
            this.rawPlaybackHeadWrapCount++;
        }
        this.rawPlaybackHeadPosition = playbackHeadPosition;
    }
}
