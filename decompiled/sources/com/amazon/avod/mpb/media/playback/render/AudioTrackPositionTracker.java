package com.amazon.avod.mpb.media.playback.render;

import android.media.AudioTrack;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.util.Preconditions2;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt__MathJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nAudioTrackPositionTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AudioTrackPositionTracker.kt\ncom/amazon/avod/mpb/media/playback/render/AudioTrackPositionTracker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,480:1\n1#2:481\n*E\n"})
public final class AudioTrackPositionTracker {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int ENCODING_PCM_16BIT_BIG_ENDIAN = 268435456;
    public static final int ENCODING_PCM_24BIT = Integer.MIN_VALUE;
    public static final int ENCODING_PCM_32BIT = 1073741824;
    public static final int MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    public static final int MAX_LATENCY_US = 5000000;
    public static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    public static final int MIN_LATENCY_SAMPLE_INTERVAL_US = 500000;
    public static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    public static final int MODE_SWITCH_SMOOTHING_DURATION_US = 1000000;
    public static final int NO_VALUE = -1;

    @NotNull
    public final AudioTimestampPoller audioTimestampPoller;

    @NotNull
    public final AudioTrack audioTrack;
    public float audioTrackPlaybackSpeed;
    public final long bufferSizeUs;
    public long forceResetWorkaroundTimeMs;

    @Nullable
    public Method getLatencyMethod;
    public final boolean isOutputPcm;
    public final boolean isVerboseLoggingEnabled;
    public long lastLatencySampleTimeUs;
    public long lastPlayheadSampleTimeUs;
    public long lastPositionUs;
    public long lastRawPlaybackHeadPosition;
    public boolean lastSampleUsedGetTimestampMode;
    public long lastSystemTimeUs;
    public long latencyUs;

    @NotNull
    public final Object mutex;
    public final boolean needsPassthroughWorkarounds;
    public int nextPlayheadOffsetIndex;
    public final int outputSampleRate;
    public long passthroughWorkaroundPauseOffset;
    public int playheadOffsetCount;

    @NotNull
    public final long[] playheadOffsets;
    public long previousModePositionUs;
    public long previousModeSystemTimeUs;
    public long rawPlaybackHeadWrapCount;
    public long smoothedPlayheadOffsetUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final AudioTrackPositionTracker create(@NotNull AudioTrack audioTrack, boolean z, int i, int i2, int i3, boolean z2) {
            Intrinsics.checkNotNullParameter(audioTrack, "audioTrack");
            return new AudioTrackPositionTracker(audioTrack, z, i, i2, i3, z2);
        }

        public final int getPcmFrameSizeBytes(int i, int i2) {
            if (i == Integer.MIN_VALUE) {
                return i2 * 3;
            }
            if (i != 268435456) {
                if (i != 1073741824) {
                    if (i != 2) {
                        if (i == 3) {
                            return i2;
                        }
                        if (i != 4) {
                            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("AudioTrackFactory: Unknown pcmEncoding ", i));
                        }
                    }
                }
                return i2 * 4;
            }
            return i2 * 2;
        }

        public final boolean isEncodingLinearPcm(int i) {
            return i == 3 || i == 2 || i == 268435456 || i == Integer.MIN_VALUE || i == 1073741824 || i == 4;
        }

        public final boolean needsPassthroughWorkarounds(int i) {
            if (Preconditions2.isSdkIntAtLeast(23)) {
                return false;
            }
            return i == 5 || i == 6;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public AudioTrackPositionTracker(@NotNull AudioTrack audioTrack, boolean z, int i, int i2, int i3, boolean z2) {
        Intrinsics.checkNotNullParameter(audioTrack, "audioTrack");
        this.audioTrack = audioTrack;
        this.isVerboseLoggingEnabled = z2;
        this.mutex = new Object();
        this.playheadOffsets = new long[10];
        this.audioTimestampPoller = new AudioTimestampPoller(audioTrack, z2);
        this.outputSampleRate = audioTrack.getSampleRate();
        this.needsPassthroughWorkarounds = z && Companion.needsPassthroughWorkarounds(i);
        boolean zIsEncodingLinearPcm = Companion.isEncodingLinearPcm(i);
        this.isOutputPcm = zIsEncodingLinearPcm;
        this.bufferSizeUs = zIsEncodingLinearPcm ? framesToDurationUs(i3 / r13.getPcmFrameSizeBytes(i, i2)) : -9223372036854775807L;
        this.audioTrackPlaybackSpeed = 1.0f;
        Method method = null;
        try {
            method = AudioTrack.class.getMethod("getLatency", null);
        } catch (NoSuchMethodException e) {
            MpbLog.exceptionf(e, "AudioTrackPositionTracker error retrieving the audio track latency method", new Object[0]);
        }
        this.getLatencyMethod = method;
        this.forceResetWorkaroundTimeMs = -9223372036854775807L;
        AudioTrack audioTrack2 = this.audioTrack;
        long j = this.bufferSizeUs;
        int i4 = this.outputSampleRate;
        boolean z3 = this.needsPassthroughWorkarounds;
        boolean z4 = this.isOutputPcm;
        StringBuilder sb = new StringBuilder("Initialized AudioTrackPositionTracker{audioTrack=");
        sb.append(audioTrack2);
        sb.append(", isPassthrough=");
        sb.append(z);
        sb.append(", outputEncoding=");
        AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb, i, ", channelCount=", i2, ", bufferSizeBytes=");
        sb.append(i3);
        sb.append(", bufferSizeUs=");
        sb.append(j);
        sb.append(", outputSampleRate=");
        sb.append(i4);
        sb.append(", needsPassthroughWorkarounds=");
        sb.append(z3);
        sb.append(", isOutputPcm=");
        sb.append(z4);
        sb.append("}");
        MpbLog.logf(sb.toString(), new Object[0]);
    }

    public final long framesToDurationUs(long j) {
        return (TimeUnit.SECONDS.toMicros(1L) * j) / ((long) this.outputSampleRate);
    }

    public final long getCurrentPositionUs() {
        long jMax;
        synchronized (this.mutex) {
            try {
                if (this.audioTrack.getPlayState() == 3) {
                    maybeSampleSyncParams();
                }
                long micros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
                boolean zHasAdvancingTimestamp = this.audioTimestampPoller.hasAdvancingTimestamp();
                if (zHasAdvancingTimestamp) {
                    jMax = framesToDurationUs(this.audioTimestampPoller.timestampWrapper.timestampPositionFrames) + getMediaDurationForPlayoutDuration(micros - this.audioTimestampPoller.timestampWrapper.getTimestampSystemTimeUs(), this.audioTrackPlaybackSpeed);
                    if (this.isVerboseLoggingEnabled) {
                        MpbLog.logf("AudioTrackPositionTracker using timestamp mode, positionMs: %d", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(jMax)));
                    }
                } else {
                    jMax = Math.max(0L, (this.playheadOffsetCount == 0 ? getPlaybackHeadPositionUs() : this.smoothedPlayheadOffsetUs + micros) - this.latencyUs);
                    if (this.isVerboseLoggingEnabled) {
                        MpbLog.logf("AudioTrackPositionTracker using playhead mode, positionMs: %d", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(jMax)));
                    }
                }
                boolean z = this.lastSampleUsedGetTimestampMode;
                if (z != zHasAdvancingTimestamp) {
                    if (this.isVerboseLoggingEnabled) {
                        MpbLog.logf("AudioTrackPositionTracker switched timestamp sampling modes, oldMode: %s newMode: %s", Boolean.valueOf(z), Boolean.valueOf(zHasAdvancingTimestamp));
                    }
                    this.previousModeSystemTimeUs = this.lastSystemTimeUs;
                    this.previousModePositionUs = this.lastPositionUs;
                }
                long j = micros - this.previousModeSystemTimeUs;
                if (j < 1000000) {
                    long mediaDurationForPlayoutDuration = this.previousModePositionUs + getMediaDurationForPlayoutDuration(j, this.audioTrackPlaybackSpeed);
                    long j2 = 1000;
                    long j3 = (j * j2) / ((long) 1000000);
                    jMax = (((j2 - j3) * mediaDurationForPlayoutDuration) + (jMax * j3)) / j2;
                    if (this.isVerboseLoggingEnabled) {
                        MpbLog.logf("AudioTrackPositionTracker smoothened positionMs: %s", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(jMax)));
                    }
                }
                this.lastSystemTimeUs = micros;
                this.lastPositionUs = jMax;
                this.lastSampleUsedGetTimestampMode = zHasAdvancingTimestamp;
            } catch (Throwable th) {
                throw th;
            }
        }
        return jMax;
    }

    public final long getMediaDurationForPlayoutDuration(long j, float f) {
        return f == 1.0f ? j : MathKt__MathJVMKt.roundToLong(j * ((double) f));
    }

    public final long getPlaybackHeadPosition() {
        int playState = this.audioTrack.getPlayState();
        if (playState == 1) {
            return 0L;
        }
        long playbackHeadPosition = ((long) this.audioTrack.getPlaybackHeadPosition()) & 4294967295L;
        if (this.needsPassthroughWorkarounds) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
            }
            playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
        }
        if (Build.VERSION.SDK_INT <= 29) {
            if (playbackHeadPosition == 0 && this.lastRawPlaybackHeadPosition > 0 && playState == 3) {
                if (this.forceResetWorkaroundTimeMs == -9223372036854775807L) {
                    this.forceResetWorkaroundTimeMs = SystemClock.elapsedRealtime();
                }
                return this.lastRawPlaybackHeadPosition;
            }
            this.forceResetWorkaroundTimeMs = -9223372036854775807L;
        }
        if (this.lastRawPlaybackHeadPosition > playbackHeadPosition) {
            this.rawPlaybackHeadWrapCount++;
        }
        this.lastRawPlaybackHeadPosition = playbackHeadPosition;
        return playbackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
    }

    public final long getPlaybackHeadPositionUs() {
        long jFramesToDurationUs = framesToDurationUs(getPlaybackHeadPosition());
        if (this.isVerboseLoggingEnabled) {
            MpbLog.logf("AudioTrackPositionTracker getPlaybackHeadPositionMs: %s", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(jFramesToDurationUs)));
        }
        return jFramesToDurationUs;
    }

    public final void maybePollAndCheckTimestamp(long j, long j2) {
        if (this.audioTimestampPoller.maybePollTimestamp(j)) {
            long timestampSystemTimeUs = this.audioTimestampPoller.timestampWrapper.getTimestampSystemTimeUs();
            long j3 = this.audioTimestampPoller.timestampWrapper.timestampPositionFrames;
            if (Math.abs(timestampSystemTimeUs - j) > 5000000) {
                MpbLog.warnf("AudioTrackPositionTracker spurious audio timestamp (system clock mismatch): audioTimestampSystemTimeUs %s systemTimeUs %s ", Long.valueOf(timestampSystemTimeUs), Long.valueOf(j));
                this.audioTimestampPoller.updateState(4);
            } else if (Math.abs(framesToDurationUs(j3) - j2) > 5000000) {
                MpbLog.warnf("AudioTrackPositionTracker spurious audio timestamp (system clock mismatch): audioTimestampPositionFrames %s playbackPositionUs %s ", Long.valueOf(j3), Long.valueOf(j2));
                this.audioTimestampPoller.updateState(4);
            } else {
                if (this.isVerboseLoggingEnabled) {
                    MpbLog.warnf("AudioTrackPositionTracker maybePollAndCheckTimestamp updated audio timestamp, audioTimestampPositionFrames: %s playbackPositionMs: %s", Long.valueOf(j3), Long.valueOf(TimeUnit.MICROSECONDS.toMillis(j2)));
                }
                this.audioTimestampPoller.acceptTimestamp();
            }
        }
    }

    public final void maybeSampleSyncParams() {
        long playbackHeadPositionUs = getPlaybackHeadPositionUs();
        if (playbackHeadPositionUs == 0) {
            if (this.isVerboseLoggingEnabled) {
                MpbLog.logf("AudioTrackPositionTracker maybeSampleSyncParams no-op as playheadPositionUs is 0", new Object[0]);
                return;
            }
            return;
        }
        long micros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
        if (micros - this.lastPlayheadSampleTimeUs >= 30000) {
            long[] jArr = this.playheadOffsets;
            int i = this.nextPlayheadOffsetIndex;
            jArr[i] = playbackHeadPositionUs - micros;
            this.nextPlayheadOffsetIndex = (i + 1) % 10;
            int i2 = this.playheadOffsetCount;
            if (i2 < 10) {
                this.playheadOffsetCount = i2 + 1;
            }
            this.lastPlayheadSampleTimeUs = micros;
            this.smoothedPlayheadOffsetUs = 0L;
            int i3 = this.playheadOffsetCount;
            for (int i4 = 0; i4 < i3; i4++) {
                this.smoothedPlayheadOffsetUs = (this.playheadOffsets[i4] / ((long) this.playheadOffsetCount)) + this.smoothedPlayheadOffsetUs;
            }
        }
        if (this.needsPassthroughWorkarounds) {
            return;
        }
        maybePollAndCheckTimestamp(micros, playbackHeadPositionUs);
        maybeUpdateLatency(micros);
    }

    public final void maybeUpdateLatency(long j) {
        Method method = this.getLatencyMethod;
        if (!this.isOutputPcm || method == null || j - this.lastLatencySampleTimeUs < 500000) {
            return;
        }
        try {
            long j2 = this.latencyUs;
            Object objInvoke = method.invoke(this.audioTrack, null);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Int");
            long jIntValue = (((long) ((Integer) objInvoke).intValue()) * 1000) - this.bufferSizeUs;
            this.latencyUs = jIntValue;
            long jMax = Math.max(jIntValue, 0L);
            this.latencyUs = jMax;
            if (jMax > 5000000) {
                this.latencyUs = 0L;
            }
            long j3 = this.latencyUs;
            if (j2 != j3) {
                MpbLog.logf("AudioTrackPositionTracker updated latencyMs: %d", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(j3)));
            }
        } catch (Exception unused) {
            this.getLatencyMethod = null;
        }
        this.lastLatencySampleTimeUs = j;
    }

    public final void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.lastSystemTimeUs = 0L;
        this.previousModeSystemTimeUs = 0L;
    }

    public final void setAudioTrackPlaybackSpeed(float f) {
        synchronized (this.mutex) {
            this.audioTrackPlaybackSpeed = f;
            this.audioTimestampPoller.updateState(0);
        }
    }

    public final void start() {
        synchronized (this.mutex) {
            this.audioTimestampPoller.updateState(0);
        }
    }

    public final void stop() {
        synchronized (this.mutex) {
            resetSyncParams();
            this.audioTimestampPoller.stop();
            this.lastRawPlaybackHeadPosition = 0L;
            this.rawPlaybackHeadWrapCount = 0L;
        }
    }
}
