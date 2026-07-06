package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.metrics.LogSessionId;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.Pair;
import androidx.annotation.DoNotInline;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration$$ExternalSyntheticApiModelOutline0;
import com.amazon.avod.mpb.media.playback.render.AvSyncHeaderFormatter;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.AudioTrackPositionTracker;
import com.google.android.exoplayer2.audio.DefaultAudioTrackBufferSizeProvider;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.InlineMeValidationDisabled;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import kotlinx.coroutines.DebugKt;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultAudioSink implements AudioSink {
    public static final int AUDIO_TRACK_RETRY_DURATION_MS = 100;
    public static final int AUDIO_TRACK_SMALLER_BUFFER_RETRY_SIZE = 1000000;
    public static final float DEFAULT_PLAYBACK_SPEED = 1.0f;
    public static final boolean DEFAULT_SKIP_SILENCE = false;
    public static final int ERROR_NATIVE_DEAD_OBJECT = -32;
    public static final float MAX_PITCH = 8.0f;
    public static final float MAX_PLAYBACK_SPEED = 8.0f;
    public static final float MIN_PITCH = 0.1f;
    public static final float MIN_PLAYBACK_SPEED = 0.1f;
    public static final int OFFLOAD_MODE_DISABLED = 0;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_DISABLED = 3;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_NOT_REQUIRED = 2;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_REQUIRED = 1;
    public static final int OUTPUT_MODE_OFFLOAD = 1;
    public static final int OUTPUT_MODE_PASSTHROUGH = 2;
    public static final int OUTPUT_MODE_PCM = 0;
    public static final String TAG = "DefaultAudioSink";
    public static boolean failOnSpuriousAudioTimestamp = false;

    @GuardedBy("releaseExecutorLock")
    public static int pendingReleaseCount;

    @Nullable
    @GuardedBy("releaseExecutorLock")
    public static ExecutorService releaseExecutor;
    public final boolean DBG;
    public final boolean VDBG;
    public AudioProcessor[] activeAudioProcessors;

    @Nullable
    public MediaPositionParameters afterDrainParameters;
    public AudioAttributes audioAttributes;
    public final AudioCapabilities audioCapabilities;

    @Nullable
    public final ExoPlayer.AudioOffloadListener audioOffloadListener;
    public final com.google.android.exoplayer2.audio.AudioProcessorChain audioProcessorChain;
    public int audioSessionId;

    @Nullable
    public AudioTrack audioTrack;
    public final AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
    public PlaybackParameters audioTrackPlaybackParameters;
    public final AudioTrackPositionTracker audioTrackPositionTracker;
    public AuxEffectInfo auxEffectInfo;

    @Nullable
    public ByteBuffer avSyncHeader;
    public int bytesUntilNextAvSync;
    public final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    public Configuration configuration;
    public int drainingAudioProcessorIndex;
    public final boolean enableAudioTrackPlaybackParams;
    public final boolean enableFloatOutput;
    public boolean externalAudioSessionIdProvided;
    public int framesPerEncodedSample;
    public boolean handledEndOfStream;
    public final PendingExceptionHolder<AudioSink.InitializationException> initializationExceptionPendingExceptionHolder;

    @Nullable
    public ByteBuffer inputBuffer;
    public int inputBufferAccessUnitCount;
    public boolean isWaitingForOffloadEndOfStreamHandled;
    public long lastFeedElapsedRealtimeMs;

    @Nullable
    public AudioSink.Listener listener;
    public final Logger log;
    public MediaPositionParameters mediaPositionParameters;
    public final ArrayDeque<MediaPositionParameters> mediaPositionParametersCheckpoints;
    public boolean offloadDisabledUntilNextConfiguration;
    public final int offloadMode;
    public StreamEventCallbackV29 offloadStreamEventCallbackV29;

    @Nullable
    public ByteBuffer outputBuffer;
    public ByteBuffer[] outputBuffers;

    @Nullable
    public Configuration pendingConfiguration;

    @Nullable
    public PlayerId playerId;
    public boolean playing;
    public byte[] preV21OutputBuffer;
    public int preV21OutputBufferOffset;

    @Nullable
    public AudioDeviceInfoApi23 preferredDevice;
    public final ConditionVariable releasingConditionVariable;
    public long startMediaTimeUs;
    public boolean startMediaTimeUsNeedsInit;
    public boolean startMediaTimeUsNeedsSync;
    public boolean stoppedAudioTrack;
    public long submittedEncodedFrames;
    public long submittedPcmBytes;
    public final AudioProcessor[] toFloatPcmAvailableAudioProcessors;
    public final AudioProcessor[] toIntPcmAvailableAudioProcessors;
    public final TrimmingAudioProcessor trimmingAudioProcessor;
    public boolean tunneling;
    public float volume;
    public final PendingExceptionHolder<AudioSink.WriteException> writeExceptionPendingExceptionHolder;
    public long writtenEncodedFrames;
    public long writtenPcmBytes;
    public static final Object releaseExecutorLock = new Object();
    public static final boolean isLatencyQuirkEnabled = AmazonQuirks.isLatencyQuirkEnabled();
    public static final boolean isLegacyPassthroughQuirkEnabled = AmazonQuirks.isFireTVGen1Family();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static final class Api23 {
        @DoNotInline
        public static void setPreferredDeviceOnAudioTrack(AudioTrack audioTrack, @Nullable AudioDeviceInfoApi23 audioDeviceInfoApi23) {
            audioTrack.setPreferredDevice(audioDeviceInfoApi23 == null ? null : audioDeviceInfoApi23.audioDeviceInfo);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class Api31 {
        @DoNotInline
        public static void setLogSessionIdOnAudioTrack(AudioTrack audioTrack, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                return;
            }
            audioTrack.setLogSessionId(logSessionId);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static final class AudioDeviceInfoApi23 {
        public final AudioDeviceInfo audioDeviceInfo;

        public AudioDeviceInfoApi23(AudioDeviceInfo audioDeviceInfo) {
            this.audioDeviceInfo = audioDeviceInfo;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public interface AudioProcessorChain extends com.google.android.exoplayer2.audio.AudioProcessorChain {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AudioTrackBufferSizeProvider {
        public static final AudioTrackBufferSizeProvider DEFAULT = new DefaultAudioTrackBufferSizeProvider(new DefaultAudioTrackBufferSizeProvider.Builder());

        int getBufferSizeInBytes(int i, int i2, int i3, int i4, int i5, int i6, double d);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {

        @Nullable
        public ExoPlayer.AudioOffloadListener audioOffloadListener;

        @Nullable
        public com.google.android.exoplayer2.audio.AudioProcessorChain audioProcessorChain;
        public boolean enableAudioTrackPlaybackParams;
        public boolean enableFloatOutput;
        public AudioCapabilities audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
        public int offloadMode = 0;
        public AudioTrackBufferSizeProvider audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;

        public DefaultAudioSink build() {
            if (this.audioProcessorChain == null) {
                this.audioProcessorChain = new DefaultAudioProcessorChain(new AudioProcessor[0]);
            }
            return new DefaultAudioSink(this);
        }

        @CanIgnoreReturnValue
        public Builder setAudioCapabilities(AudioCapabilities audioCapabilities) {
            audioCapabilities.getClass();
            this.audioCapabilities = audioCapabilities;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudioProcessorChain(com.google.android.exoplayer2.audio.AudioProcessorChain audioProcessorChain) {
            audioProcessorChain.getClass();
            this.audioProcessorChain = audioProcessorChain;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudioProcessors(AudioProcessor[] audioProcessorArr) {
            audioProcessorArr.getClass();
            this.audioProcessorChain = new DefaultAudioProcessorChain(audioProcessorArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudioTrackBufferSizeProvider(AudioTrackBufferSizeProvider audioTrackBufferSizeProvider) {
            this.audioTrackBufferSizeProvider = audioTrackBufferSizeProvider;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setEnableAudioTrackPlaybackParams(boolean z) {
            this.enableAudioTrackPlaybackParams = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setEnableFloatOutput(boolean z) {
            this.enableFloatOutput = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setExperimentalAudioOffloadListener(@Nullable ExoPlayer.AudioOffloadListener audioOffloadListener) {
            this.audioOffloadListener = audioOffloadListener;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setOffloadMode(int i) {
            this.offloadMode = i;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Configuration {
        public final AudioProcessor[] availableAudioProcessors;
        public final int bufferSize;
        public final Format inputFormat;
        public final int inputPcmFrameSize;
        public final int outputChannelConfig;
        public final int outputEncoding;
        public final int outputMode;
        public final int outputPcmFrameSize;
        public final int outputSampleRate;

        public Configuration(Format format, int i, int i2, int i3, int i4, int i5, int i6, int i7, AudioProcessor[] audioProcessorArr) {
            this.inputFormat = format;
            this.inputPcmFrameSize = i;
            this.outputMode = i2;
            this.outputPcmFrameSize = i3;
            this.outputSampleRate = i4;
            this.outputChannelConfig = i5;
            this.outputEncoding = i6;
            this.bufferSize = i7;
            this.availableAudioProcessors = audioProcessorArr;
        }

        @RequiresApi(21)
        public static android.media.AudioAttributes getAudioTrackAttributesV21(AudioAttributes audioAttributes, boolean z) {
            return z ? getAudioTrackTunnelingAttributesV21() : audioAttributes.getAudioAttributesV21().audioAttributes;
        }

        @RequiresApi(21)
        public static android.media.AudioAttributes getAudioTrackTunnelingAttributesV21() {
            return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
        }

        public boolean applyDolbyPassthroughQuirk() {
            return this.outputMode != 0 && DefaultAudioSink.isLegacyPassthroughQuirkEnabled;
        }

        public AudioTrack buildAudioTrack(boolean z, AudioAttributes audioAttributes, int i) throws AudioSink.InitializationException {
            try {
                AudioTrack audioTrackCreateAudioTrack = createAudioTrack(z, audioAttributes, i);
                int state = audioTrackCreateAudioTrack.getState();
                if (state == 1) {
                    return audioTrackCreateAudioTrack;
                }
                try {
                    audioTrackCreateAudioTrack.release();
                } catch (Exception unused) {
                }
                throw new AudioSink.InitializationException(state, this.outputSampleRate, this.outputChannelConfig, this.bufferSize, this.inputFormat, outputModeIsOffload(), null);
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                throw new AudioSink.InitializationException(0, this.outputSampleRate, this.outputChannelConfig, this.bufferSize, this.inputFormat, outputModeIsOffload(), e);
            }
        }

        public boolean canReuseAudioTrack(Configuration configuration) {
            return configuration.outputMode == this.outputMode && configuration.outputEncoding == this.outputEncoding && configuration.outputSampleRate == this.outputSampleRate && configuration.outputChannelConfig == this.outputChannelConfig && configuration.outputPcmFrameSize == this.outputPcmFrameSize;
        }

        public Configuration copyWithBufferSize(int i) {
            return new Configuration(this.inputFormat, this.inputPcmFrameSize, this.outputMode, this.outputPcmFrameSize, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, i, this.availableAudioProcessors);
        }

        public final AudioTrack createAudioTrack(boolean z, AudioAttributes audioAttributes, int i) {
            int i2 = Util.SDK_INT;
            return i2 >= 29 ? createAudioTrackV29(z, audioAttributes, i) : i2 >= 21 ? createAudioTrackV21(z, audioAttributes, i) : createAudioTrackV9(audioAttributes, i);
        }

        @RequiresApi(21)
        public final AudioTrack createAudioTrackV21(boolean z, AudioAttributes audioAttributes, int i) {
            int i2 = i != 0 ? i : 0;
            return applyDolbyPassthroughQuirk() ? new DolbyPassthroughAudioTrack(getAudioTrackAttributesV21(audioAttributes, z), DefaultAudioSink.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding), this.bufferSize, 1, i2) : new AudioTrack(getAudioTrackAttributesV21(audioAttributes, z), DefaultAudioSink.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding), this.bufferSize, 1, i2);
        }

        @RequiresApi(29)
        public final AudioTrack createAudioTrackV29(boolean z, AudioAttributes audioAttributes, int i) {
            return DefaultAudioSink$Configuration$$ExternalSyntheticApiModelOutline0.m().setAudioAttributes(getAudioTrackAttributesV21(audioAttributes, z)).setAudioFormat(DefaultAudioSink.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding)).setTransferMode(1).setBufferSizeInBytes(this.bufferSize).setSessionId(i).setOffloadedPlayback(this.outputMode == 1).build();
        }

        public final AudioTrack createAudioTrackV9(AudioAttributes audioAttributes, int i) {
            int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(audioAttributes.usage);
            return i == 0 ? applyDolbyPassthroughQuirk() ? new DolbyPassthroughAudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, 0) : new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1) : applyDolbyPassthroughQuirk() ? new DolbyPassthroughAudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, i) : new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, i);
        }

        public long framesToDurationUs(long j) {
            return (j * 1000000) / ((long) this.outputSampleRate);
        }

        public long inputFramesToDurationUs(long j) {
            return (j * 1000000) / ((long) this.inputFormat.sampleRate);
        }

        public boolean outputModeIsOffload() {
            return this.outputMode == 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        public final AudioProcessor[] audioProcessors;
        public final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        public final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this(audioProcessorArr, new SilenceSkippingAudioProcessor(), new SonicAudioProcessor());
        }

        @Override // com.google.android.exoplayer2.audio.AudioProcessorChain
        public PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters) {
            this.sonicAudioProcessor.setSpeed(playbackParameters.speed);
            this.sonicAudioProcessor.setPitch(playbackParameters.pitch);
            return playbackParameters;
        }

        @Override // com.google.android.exoplayer2.audio.AudioProcessorChain
        public boolean applySkipSilenceEnabled(boolean z) {
            this.silenceSkippingAudioProcessor.enabled = z;
            return z;
        }

        @Override // com.google.android.exoplayer2.audio.AudioProcessorChain
        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        @Override // com.google.android.exoplayer2.audio.AudioProcessorChain
        public long getMediaDuration(long j) {
            return this.sonicAudioProcessor.getMediaDuration(j);
        }

        @Override // com.google.android.exoplayer2.audio.AudioProcessorChain
        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.skippedFrames;
        }

        public DefaultAudioProcessorChain(AudioProcessor[] audioProcessorArr, SilenceSkippingAudioProcessor silenceSkippingAudioProcessor, SonicAudioProcessor sonicAudioProcessor) {
            AudioProcessor[] audioProcessorArr2 = new AudioProcessor[audioProcessorArr.length + 2];
            this.audioProcessors = audioProcessorArr2;
            System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 0, audioProcessorArr.length);
            this.silenceSkippingAudioProcessor = silenceSkippingAudioProcessor;
            this.sonicAudioProcessor = sonicAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length] = silenceSkippingAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length + 1] = sonicAudioProcessor;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        public InvalidAudioTrackTimestampException(String str) {
            super(str);
        }

        public InvalidAudioTrackTimestampException(String str, AnonymousClass1 anonymousClass1) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaPositionParameters {
        public final long audioTrackPositionUs;
        public final long mediaTimeUs;
        public final PlaybackParameters playbackParameters;
        public final boolean skipSilence;

        public MediaPositionParameters(PlaybackParameters playbackParameters, boolean z, long j, long j2) {
            this.playbackParameters = playbackParameters;
            this.skipSilence = z;
            this.mediaTimeUs = j;
            this.audioTrackPositionUs = j2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface OffloadMode {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface OutputMode {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PendingExceptionHolder<T extends Exception> {

        @Nullable
        public T pendingException;
        public long throwDeadlineMs;
        public final long throwDelayMs;

        public PendingExceptionHolder(long j) {
            this.throwDelayMs = j;
        }

        public void clear() {
            this.pendingException = null;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: T extends java.lang.Exception */
        public void throwExceptionIfDeadlineIsReached(T t) throws Exception {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (this.pendingException == null) {
                this.pendingException = t;
                this.throwDeadlineMs = this.throwDelayMs + jElapsedRealtime;
            }
            if (jElapsedRealtime >= this.throwDeadlineMs) {
                T t2 = this.pendingException;
                if (t2 != t) {
                    t2.addSuppressed(t);
                }
                T t3 = this.pendingException;
                this.pendingException = null;
                throw t3;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        public PositionTrackerListener() {
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrackPositionTracker.Listener
        public void onInvalidLatency(long j) {
            DefaultAudioSink.this.log.w("Ignoring impossibly large audio latency: " + j);
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrackPositionTracker.Listener
        public void onPositionAdvancing(long j) {
            if (DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onPositionAdvancing(j);
            }
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrackPositionTracker.Listener
        public void onPositionFramesMismatch(long j, long j2, long j3, long j4) {
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("Spurious audio timestamp (frame position mismatch): ", j, ", ");
            sbM.append(j2);
            sbM.append(", ");
            sbM.append(j3);
            sbM.append(", ");
            sbM.append(j4);
            sbM.append(", ");
            sbM.append(DefaultAudioSink.this.getSubmittedFrames());
            sbM.append(", ");
            sbM.append(DefaultAudioSink.this.getWrittenFrames());
            String string = sbM.toString();
            if (DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                throw new InvalidAudioTrackTimestampException(string);
            }
            DefaultAudioSink.this.log.w(string);
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrackPositionTracker.Listener
        public void onSystemTimeUsMismatch(long j, long j2, long j3, long j4) {
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("Spurious audio timestamp (system clock mismatch): ", j, ", ");
            sbM.append(j2);
            sbM.append(", ");
            sbM.append(j3);
            sbM.append(", ");
            sbM.append(j4);
            sbM.append(", ");
            sbM.append(DefaultAudioSink.this.getSubmittedFrames());
            sbM.append(", ");
            sbM.append(DefaultAudioSink.this.getWrittenFrames());
            String string = sbM.toString();
            if (DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                throw new InvalidAudioTrackTimestampException(string);
            }
            DefaultAudioSink.this.log.w(string);
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrackPositionTracker.Listener
        public void onUnderrun(int i, long j) {
            if (DefaultAudioSink.this.listener != null) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                DefaultAudioSink defaultAudioSink = DefaultAudioSink.this;
                defaultAudioSink.listener.onUnderrun(i, j, jElapsedRealtime - defaultAudioSink.lastFeedElapsedRealtimeMs);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public final class StreamEventCallbackV29 {
        public final AudioTrack.StreamEventCallback callback;
        public final Handler handler = new Handler(Looper.myLooper());

        public StreamEventCallbackV29() {
            this.callback = new AudioTrack.StreamEventCallback() { // from class: com.google.android.exoplayer2.audio.DefaultAudioSink.StreamEventCallbackV29.1
                @Override // android.media.AudioTrack.StreamEventCallback
                public void onDataRequest(AudioTrack audioTrack, int i) {
                    DefaultAudioSink defaultAudioSink;
                    AudioSink.Listener listener;
                    if (audioTrack.equals(DefaultAudioSink.this.audioTrack) && (listener = (defaultAudioSink = DefaultAudioSink.this).listener) != null && defaultAudioSink.playing) {
                        listener.onOffloadBufferEmptying();
                    }
                }

                @Override // android.media.AudioTrack.StreamEventCallback
                public void onTearDown(AudioTrack audioTrack) {
                    DefaultAudioSink defaultAudioSink;
                    AudioSink.Listener listener;
                    if (audioTrack.equals(DefaultAudioSink.this.audioTrack) && (listener = (defaultAudioSink = DefaultAudioSink.this).listener) != null && defaultAudioSink.playing) {
                        listener.onOffloadBufferEmptying();
                    }
                }
            };
        }

        public void register(AudioTrack audioTrack) {
            Handler handler = this.handler;
            Objects.requireNonNull(handler);
            audioTrack.registerStreamEventCallback(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler), this.callback);
        }

        public void unregister(AudioTrack audioTrack) {
            audioTrack.unregisterStreamEventCallback(this.callback);
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public static /* synthetic */ void $r8$lambda$Hgr4l8_uAAUx9oJtugixlqv_1qY(AudioTrack audioTrack, ConditionVariable conditionVariable) {
        try {
            Logger logger = new Logger(Logger.Module.Audio, "DefaultAudioSink");
            logger.i("audioTrack.flush");
            audioTrack.flush();
            logger.i("audioTrack.release");
            audioTrack.release();
            conditionVariable.open();
            synchronized (releaseExecutorLock) {
                try {
                    int i = pendingReleaseCount - 1;
                    pendingReleaseCount = i;
                    if (i == 0) {
                        releaseExecutor.shutdown();
                        releaseExecutor = null;
                    }
                } finally {
                }
            }
        } catch (Throwable th) {
            conditionVariable.open();
            synchronized (releaseExecutorLock) {
                try {
                    int i2 = pendingReleaseCount - 1;
                    pendingReleaseCount = i2;
                    if (i2 == 0) {
                        releaseExecutor.shutdown();
                        releaseExecutor = null;
                    }
                    throw th;
                } finally {
                }
            }
        }
    }

    @RequiresApi(21)
    public static AudioFormat getAudioFormat(int i, int i2, int i3) {
        return new AudioFormat.Builder().setSampleRate(i).setChannelMask(i2).setEncoding(i3).build();
    }

    public static int getAudioTrackMinBufferSize(int i, int i2, int i3) {
        int minBufferSize = AudioTrack.getMinBufferSize(i, i2, i3);
        Assertions.checkState(minBufferSize != -2);
        return minBufferSize;
    }

    public static int getFramesPerEncodedSample(int i, ByteBuffer byteBuffer) {
        switch (i) {
            case 5:
            case 6:
            case 18:
                return Ac3Util.parseAc3SyncframeAudioSampleCount(byteBuffer);
            case 7:
            case 8:
                return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
            case 9:
                int mpegAudioFrameSampleCount = MpegAudioUtil.parseMpegAudioFrameSampleCount(Util.getBigEndianInt(byteBuffer, byteBuffer.position()));
                if (mpegAudioFrameSampleCount != -1) {
                    return mpegAudioFrameSampleCount;
                }
                throw new IllegalArgumentException();
            case 10:
                return 1024;
            case 11:
            case 12:
                return 2048;
            case 13:
            case 19:
            default:
                throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unexpected audio encoding: ", i));
            case 14:
                int iFindTrueHdSyncframeOffset = Ac3Util.findTrueHdSyncframeOffset(byteBuffer);
                if (iFindTrueHdSyncframeOffset == -1) {
                    return 0;
                }
                return Ac3Util.parseTrueHdSyncframeAudioSampleCount(byteBuffer, iFindTrueHdSyncframeOffset) * 16;
            case 15:
                return 512;
            case 16:
                return 1024;
            case 17:
                return Ac4Util.parseAc4SyncframeAudioSampleCount(byteBuffer);
            case 20:
                return OpusUtil.parsePacketAudioSampleCount(byteBuffer);
        }
    }

    public static boolean isAudioTrackDeadObject(int i) {
        return (Util.SDK_INT >= 24 && i == -6) || i == -32;
    }

    public static boolean isOffloadedPlayback(AudioTrack audioTrack) {
        return Util.SDK_INT >= 29 && audioTrack.isOffloadedPlayback();
    }

    public static void releaseAudioTrackAsync(final AudioTrack audioTrack, final ConditionVariable conditionVariable) {
        conditionVariable.close();
        synchronized (releaseExecutorLock) {
            try {
                if (releaseExecutor == null) {
                    releaseExecutor = Util.newSingleThreadExecutor("ExoPlayer:AudioTrackReleaseThread");
                }
                pendingReleaseCount++;
                releaseExecutor.execute(new Runnable() { // from class: com.google.android.exoplayer2.audio.DefaultAudioSink$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultAudioSink.$r8$lambda$Hgr4l8_uAAUx9oJtugixlqv_1qY(audioTrack, conditionVariable);
                    }
                });
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RequiresApi(21)
    public static void setVolumeInternalV21(AudioTrack audioTrack, float f) {
        audioTrack.setVolume(f);
    }

    public static void setVolumeInternalV3(AudioTrack audioTrack, float f) {
        audioTrack.setStereoVolume(f, f);
    }

    @RequiresApi(21)
    public static int writeNonBlockingV21(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer, i, 1);
    }

    public final void applyAudioProcessorPlaybackParametersAndSkipSilence(long j) {
        PlaybackParameters playbackParametersApplyPlaybackParameters = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applyPlaybackParameters(getMediaPositionParameters().playbackParameters) : PlaybackParameters.DEFAULT;
        boolean zApplySkipSilenceEnabled = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applySkipSilenceEnabled(getMediaPositionParameters().skipSilence) : false;
        this.mediaPositionParametersCheckpoints.add(new MediaPositionParameters(playbackParametersApplyPlaybackParameters, zApplySkipSilenceEnabled, Math.max(0L, j), this.configuration.framesToDurationUs(getWrittenFrames())));
        setupAudioProcessors();
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onSkipSilenceEnabledChanged(zApplySkipSilenceEnabled);
        }
    }

    public boolean applyDolbyPassthroughQuirk() {
        return this.configuration.outputMode != 0 && isLegacyPassthroughQuirkEnabled;
    }

    public final long applyMediaPositionParameters(long j) {
        while (!this.mediaPositionParametersCheckpoints.isEmpty() && j >= this.mediaPositionParametersCheckpoints.getFirst().audioTrackPositionUs) {
            this.mediaPositionParameters = this.mediaPositionParametersCheckpoints.remove();
        }
        MediaPositionParameters mediaPositionParameters = this.mediaPositionParameters;
        long j2 = j - mediaPositionParameters.audioTrackPositionUs;
        if (mediaPositionParameters.playbackParameters.equals(PlaybackParameters.DEFAULT)) {
            return this.mediaPositionParameters.mediaTimeUs + j2;
        }
        if (this.mediaPositionParametersCheckpoints.isEmpty()) {
            return this.mediaPositionParameters.mediaTimeUs + this.audioProcessorChain.getMediaDuration(j2);
        }
        MediaPositionParameters first = this.mediaPositionParametersCheckpoints.getFirst();
        return first.mediaTimeUs - Util.getMediaDurationForPlayoutDuration(first.audioTrackPositionUs - j, this.mediaPositionParameters.playbackParameters.speed);
    }

    public final long applySkipping(long j) {
        return this.configuration.framesToDurationUs(this.audioProcessorChain.getSkippedOutputFrameCount()) + j;
    }

    public final AudioTrack buildAudioTrack(Configuration configuration) throws AudioSink.InitializationException {
        try {
            AudioTrack audioTrackBuildAudioTrack = configuration.buildAudioTrack(this.tunneling, this.audioAttributes, this.audioSessionId);
            if (this.audioOffloadListener == null) {
                return audioTrackBuildAudioTrack;
            }
            isOffloadedPlayback(audioTrackBuildAudioTrack);
            return audioTrackBuildAudioTrack;
        } catch (AudioSink.InitializationException e) {
            AudioSink.Listener listener = this.listener;
            if (listener != null) {
                listener.onAudioSinkError(e);
            }
            throw e;
        }
    }

    public final AudioTrack buildAudioTrackWithRetry() throws AudioSink.InitializationException {
        try {
            Configuration configuration = this.configuration;
            configuration.getClass();
            return buildAudioTrack(configuration);
        } catch (AudioSink.InitializationException e) {
            Configuration configuration2 = this.configuration;
            if (configuration2.bufferSize > 1000000) {
                Configuration configurationCopyWithBufferSize = configuration2.copyWithBufferSize(1000000);
                try {
                    AudioTrack audioTrackBuildAudioTrack = buildAudioTrack(configurationCopyWithBufferSize);
                    this.configuration = configurationCopyWithBufferSize;
                    return audioTrackBuildAudioTrack;
                } catch (AudioSink.InitializationException e2) {
                    e.addSuppressed(e2);
                    maybeDisableOffload();
                    throw e;
                }
            }
            maybeDisableOffload();
            throw e;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void configure(Format format, int i, @Nullable int[] iArr) throws AudioSink.ConfigurationException {
        AudioProcessor[] audioProcessorArr;
        int i2;
        int iIntValue;
        int iIntValue2;
        int pcmFrameSize;
        int pcmFrameSize2;
        int i3;
        int i4;
        int bufferSizeInBytes;
        int[] iArr2;
        if ("audio/raw".equals(format.sampleMimeType)) {
            Assertions.checkArgument(Util.isEncodingLinearPcm(format.pcmEncoding));
            pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
            audioProcessorArr = shouldUseFloatOutput(format.pcmEncoding) ? this.toFloatPcmAvailableAudioProcessors : this.toIntPcmAvailableAudioProcessors;
            TrimmingAudioProcessor trimmingAudioProcessor = this.trimmingAudioProcessor;
            int i5 = format.encoderDelay;
            int i6 = format.encoderPadding;
            trimmingAudioProcessor.trimStartFrames = i5;
            trimmingAudioProcessor.trimEndFrames = i6;
            if (Util.SDK_INT < 21 && format.channelCount == 8 && iArr == null) {
                iArr2 = new int[6];
                for (int i7 = 0; i7 < 6; i7++) {
                    iArr2[i7] = i7;
                }
            } else {
                iArr2 = iArr;
            }
            this.channelMappingAudioProcessor.pendingOutputChannels = iArr2;
            AudioProcessor.AudioFormat audioFormat = new AudioProcessor.AudioFormat(format.sampleRate, format.channelCount, format.pcmEncoding);
            for (AudioProcessor audioProcessor : audioProcessorArr) {
                try {
                    AudioProcessor.AudioFormat audioFormatConfigure = audioProcessor.configure(audioFormat);
                    if (audioProcessor.isActive()) {
                        audioFormat = audioFormatConfigure;
                    }
                } catch (AudioProcessor.UnhandledAudioFormatException e) {
                    throw new AudioSink.ConfigurationException(e, format);
                }
            }
            int i8 = audioFormat.encoding;
            i2 = audioFormat.sampleRate;
            int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(audioFormat.channelCount);
            pcmFrameSize2 = Util.getPcmFrameSize(i8, audioFormat.channelCount);
            iIntValue = i8;
            iIntValue2 = audioTrackChannelConfig;
            i3 = 0;
        } else {
            audioProcessorArr = new AudioProcessor[0];
            i2 = format.sampleRate;
            if (useOffloadedPlayback(format, this.audioAttributes)) {
                String str = format.sampleMimeType;
                str.getClass();
                iIntValue = MimeTypes.getEncoding(str, format.codecs);
                iIntValue2 = Util.getAudioTrackChannelConfig(format.channelCount);
                pcmFrameSize = -1;
                pcmFrameSize2 = -1;
                i3 = 1;
            } else {
                Pair<Integer, Integer> encodingAndChannelConfigForPassthrough = this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format);
                if (encodingAndChannelConfigForPassthrough == null) {
                    throw new AudioSink.ConfigurationException("Unable to configure passthrough for: " + format, format);
                }
                iIntValue = ((Integer) encodingAndChannelConfigForPassthrough.first).intValue();
                iIntValue2 = ((Integer) encodingAndChannelConfigForPassthrough.second).intValue();
                pcmFrameSize = -1;
                pcmFrameSize2 = -1;
                i3 = 2;
            }
        }
        if (iIntValue == 0) {
            throw new AudioSink.ConfigurationException("Invalid output encoding (mode=" + i3 + ") for: " + format, format);
        }
        if (iIntValue2 == 0) {
            throw new AudioSink.ConfigurationException("Invalid output channel config (mode=" + i3 + ") for: " + format, format);
        }
        if (i != 0) {
            bufferSizeInBytes = i;
            i4 = i2;
        } else {
            i4 = i2;
            bufferSizeInBytes = this.audioTrackBufferSizeProvider.getBufferSizeInBytes(getAudioTrackMinBufferSize(i2, iIntValue2, iIntValue), iIntValue, i3, pcmFrameSize2 != -1 ? pcmFrameSize2 : 1, i4, format.bitrate, this.enableAudioTrackPlaybackParams ? 8.0d : 1.0d);
        }
        this.offloadDisabledUntilNextConfiguration = false;
        Configuration configuration = new Configuration(format, pcmFrameSize, i3, pcmFrameSize2, i4, iIntValue2, iIntValue, bufferSizeInBytes, audioProcessorArr);
        if (isAudioTrackInitialized()) {
            this.pendingConfiguration = configuration;
        } else {
            this.configuration = configuration;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            flush();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0029 -> B:5:0x0009). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean drainToEndOfStream() throws java.lang.Exception {
        /*
            r9 = this;
            int r0 = r9.drainingAudioProcessorIndex
            r1 = 1
            r2 = 0
            r3 = -1
            if (r0 != r3) goto Lb
            r9.drainingAudioProcessorIndex = r2
        L9:
            r0 = 1
            goto Lc
        Lb:
            r0 = 0
        Lc:
            int r4 = r9.drainingAudioProcessorIndex
            com.google.android.exoplayer2.audio.AudioProcessor[] r5 = r9.activeAudioProcessors
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L2f
            r4 = r5[r4]
            if (r0 == 0) goto L1f
            r4.queueEndOfStream()
        L1f:
            r9.processBuffers(r7)
            boolean r0 = r4.isEnded()
            if (r0 != 0) goto L29
            return r2
        L29:
            int r0 = r9.drainingAudioProcessorIndex
            int r0 = r0 + r1
            r9.drainingAudioProcessorIndex = r0
            goto L9
        L2f:
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 == 0) goto L3b
            r9.writeBuffer(r0, r7)
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 == 0) goto L3b
            return r2
        L3b:
            r9.drainingAudioProcessorIndex = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.drainToEndOfStream():boolean");
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void enableTunnelingV21() {
        this.log.i("calling enableTunnelingV21");
        Assertions.checkState(Util.SDK_INT >= 21);
        Assertions.checkState(this.externalAudioSessionIdProvided);
        if (this.tunneling) {
            return;
        }
        this.tunneling = true;
        flush();
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void experimentalFlushWithoutAudioTrackRelease() {
        if (Util.SDK_INT < 25) {
            flush();
            return;
        }
        this.writeExceptionPendingExceptionHolder.pendingException = null;
        this.initializationExceptionPendingExceptionHolder.pendingException = null;
        if (isAudioTrackInitialized()) {
            resetSinkStateForFlush();
            if (this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }
            this.audioTrack.flush();
            this.audioTrackPositionTracker.reset();
            AudioTrackPositionTracker audioTrackPositionTracker = this.audioTrackPositionTracker;
            AudioTrack audioTrack = this.audioTrack;
            Configuration configuration = this.configuration;
            audioTrackPositionTracker.setAudioTrack(audioTrack, configuration.outputMode == 2, configuration.outputEncoding, configuration.outputPcmFrameSize, configuration.bufferSize, applyDolbyPassthroughQuirk());
            this.startMediaTimeUsNeedsInit = true;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void flush() {
        this.log.i("calling flush/reset");
        if (isAudioTrackInitialized()) {
            resetSinkStateForFlush();
            if (this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }
            if (isOffloadedPlayback(this.audioTrack)) {
                StreamEventCallbackV29 streamEventCallbackV29 = this.offloadStreamEventCallbackV29;
                streamEventCallbackV29.getClass();
                streamEventCallbackV29.unregister(this.audioTrack);
            }
            if (Util.SDK_INT < 21 && !this.externalAudioSessionIdProvided) {
                this.audioSessionId = 0;
            }
            Configuration configuration = this.pendingConfiguration;
            if (configuration != null) {
                this.configuration = configuration;
                this.pendingConfiguration = null;
            }
            this.audioTrackPositionTracker.reset();
            releaseAudioTrackAsync(this.audioTrack, this.releasingConditionVariable);
            this.audioTrack = null;
        }
        this.writeExceptionPendingExceptionHolder.pendingException = null;
        this.initializationExceptionPendingExceptionHolder.pendingException = null;
    }

    public final void flushAudioProcessors() {
        int i = 0;
        while (true) {
            AudioProcessor[] audioProcessorArr = this.activeAudioProcessors;
            if (i >= audioProcessorArr.length) {
                return;
            }
            AudioProcessor audioProcessor = audioProcessorArr[i];
            audioProcessor.flush();
            this.outputBuffers[i] = audioProcessor.getOutput();
            i++;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public final PlaybackParameters getAudioProcessorPlaybackParameters() {
        return getMediaPositionParameters().playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public long getCurrentPositionUs(boolean z) {
        if (!isAudioTrackInitialized() || this.startMediaTimeUsNeedsInit) {
            return Long.MIN_VALUE;
        }
        return applySkipping(applyMediaPositionParameters(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(z), this.configuration.framesToDurationUs(getWrittenFrames()))));
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public int getFormatSupport(Format format) {
        if (!"audio/raw".equals(format.sampleMimeType)) {
            return ((this.offloadDisabledUntilNextConfiguration || !useOffloadedPlayback(format, this.audioAttributes)) && this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format) == null) ? 0 : 2;
        }
        if (Util.isEncodingLinearPcm(format.pcmEncoding)) {
            int i = format.pcmEncoding;
            return (i == 2 || (this.enableFloatOutput && i == 4)) ? 2 : 1;
        }
        Log.w("DefaultAudioSink", "Invalid PCM encoding: " + format.pcmEncoding);
        return 0;
    }

    public final MediaPositionParameters getMediaPositionParameters() {
        MediaPositionParameters mediaPositionParameters = this.afterDrainParameters;
        return mediaPositionParameters != null ? mediaPositionParameters : !this.mediaPositionParametersCheckpoints.isEmpty() ? this.mediaPositionParametersCheckpoints.getLast() : this.mediaPositionParameters;
    }

    @RequiresApi(29)
    @SuppressLint({"InlinedApi"})
    public final int getOffloadedPlaybackSupport(AudioFormat audioFormat, android.media.AudioAttributes audioAttributes) {
        int i = Util.SDK_INT;
        if (i >= 31) {
            return AudioManager.getPlaybackOffloadSupport(audioFormat, audioAttributes);
        }
        if (AudioManager.isOffloadedPlaybackSupported(audioFormat, audioAttributes)) {
            return (i == 30 && Util.MODEL.startsWith("Pixel")) ? 2 : 1;
        }
        return 0;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.enableAudioTrackPlaybackParams ? this.audioTrackPlaybackParameters : getMediaPositionParameters().playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean getSkipSilenceEnabled() {
        return getMediaPositionParameters().skipSilence;
    }

    public final long getSubmittedFrames() {
        Configuration configuration = this.configuration;
        return configuration.outputMode == 0 ? this.submittedPcmBytes / ((long) configuration.inputPcmFrameSize) : this.submittedEncodedFrames;
    }

    public final long getWrittenFrames() {
        Configuration configuration = this.configuration;
        return configuration.outputMode == 0 ? this.writtenPcmBytes / ((long) configuration.outputPcmFrameSize) : this.writtenEncodedFrames;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ef, code lost:
    
        if (r5 == 0) goto L67;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0182  */
    @Override // com.google.android.exoplayer2.audio.AudioSink
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean handleBuffer(java.nio.ByteBuffer r19, long r20, int r22) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.handleBuffer(java.nio.ByteBuffer, long, int):boolean");
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void handleDiscontinuity() {
        this.startMediaTimeUsNeedsSync = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean hasPendingData() {
        return isAudioTrackInitialized() && this.audioTrackPositionTracker.hasPendingData(getWrittenFrames());
    }

    public final boolean initializeAudioTrack() throws AudioSink.InitializationException {
        PlayerId playerId;
        if (!this.releasingConditionVariable.isOpen()) {
            return false;
        }
        AudioTrack audioTrackBuildAudioTrackWithRetry = buildAudioTrackWithRetry();
        this.audioTrack = audioTrackBuildAudioTrackWithRetry;
        if (isOffloadedPlayback(audioTrackBuildAudioTrackWithRetry)) {
            registerStreamEventCallbackV29(this.audioTrack);
            if (this.offloadMode != 3) {
                AudioTrack audioTrack = this.audioTrack;
                Format format = this.configuration.inputFormat;
                audioTrack.setOffloadDelayPadding(format.encoderDelay, format.encoderPadding);
            }
        }
        int i = Util.SDK_INT;
        if (i >= 31 && (playerId = this.playerId) != null) {
            Api31.setLogSessionIdOnAudioTrack(this.audioTrack, playerId);
        }
        this.audioSessionId = this.audioTrack.getAudioSessionId();
        AudioTrackPositionTracker audioTrackPositionTracker = this.audioTrackPositionTracker;
        AudioTrack audioTrack2 = this.audioTrack;
        Configuration configuration = this.configuration;
        audioTrackPositionTracker.setAudioTrack(audioTrack2, configuration.outputMode == 2, configuration.outputEncoding, configuration.outputPcmFrameSize, configuration.bufferSize, applyDolbyPassthroughQuirk());
        setVolumeInternal();
        int i2 = this.auxEffectInfo.effectId;
        if (i2 != 0) {
            this.audioTrack.attachAuxEffect(i2);
            this.audioTrack.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
        }
        AudioDeviceInfoApi23 audioDeviceInfoApi23 = this.preferredDevice;
        if (audioDeviceInfoApi23 != null && i >= 23) {
            Api23.setPreferredDeviceOnAudioTrack(this.audioTrack, audioDeviceInfoApi23);
        }
        this.startMediaTimeUsNeedsInit = true;
        return true;
    }

    public final boolean isAudioTrackInitialized() {
        return this.audioTrack != null;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean isEnded() {
        if (!isAudioTrackInitialized()) {
            return true;
        }
        if (this.handledEndOfStream) {
            return applyDolbyPassthroughQuirk() || !hasPendingData();
        }
        return false;
    }

    public final void maybeDisableOffload() {
        if (this.configuration.outputModeIsOffload()) {
            this.offloadDisabledUntilNextConfiguration = true;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void pause() {
        this.log.i("calling pause");
        this.playing = false;
        if (isAudioTrackInitialized() && this.audioTrackPositionTracker.pause()) {
            this.audioTrack.pause();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void play() {
        this.playing = true;
        if (isAudioTrackInitialized()) {
            this.audioTrackPositionTracker.start();
            this.audioTrack.play();
        }
    }

    public final void playPendingData() {
        if (this.stoppedAudioTrack) {
            return;
        }
        this.stoppedAudioTrack = true;
        if (!applyDolbyPassthroughQuirk()) {
            this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
        }
        this.audioTrack.stop();
        this.bytesUntilNextAvSync = 0;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void playToEndOfStream() throws AudioSink.WriteException {
        this.log.i("calling playToEndOfStream");
        if (!this.handledEndOfStream && isAudioTrackInitialized() && drainToEndOfStream()) {
            playPendingData();
            this.handledEndOfStream = true;
        }
    }

    public final void processBuffers(long j) throws Exception {
        ByteBuffer byteBuffer;
        int length = this.activeAudioProcessors.length;
        int i = length;
        while (i >= 0) {
            if (i > 0) {
                byteBuffer = this.outputBuffers[i - 1];
            } else {
                byteBuffer = this.inputBuffer;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.EMPTY_BUFFER;
                }
            }
            if (i == length) {
                writeBuffer(byteBuffer, j);
            } else {
                AudioProcessor audioProcessor = this.activeAudioProcessors[i];
                if (i > this.drainingAudioProcessorIndex) {
                    audioProcessor.queueInput(byteBuffer);
                }
                ByteBuffer output = audioProcessor.getOutput();
                this.outputBuffers[i] = output;
                if (output.hasRemaining()) {
                    i++;
                }
            }
            if (byteBuffer.hasRemaining()) {
                return;
            } else {
                i--;
            }
        }
    }

    @RequiresApi(29)
    public final void registerStreamEventCallbackV29(AudioTrack audioTrack) {
        if (this.offloadStreamEventCallbackV29 == null) {
            this.offloadStreamEventCallbackV29 = new StreamEventCallbackV29();
        }
        this.offloadStreamEventCallbackV29.register(audioTrack);
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void reset() {
        this.log.i("calling reset");
        flush();
        for (AudioProcessor audioProcessor : this.toIntPcmAvailableAudioProcessors) {
            audioProcessor.reset();
        }
        for (AudioProcessor audioProcessor2 : this.toFloatPcmAvailableAudioProcessors) {
            audioProcessor2.reset();
        }
        this.playing = false;
        this.offloadDisabledUntilNextConfiguration = false;
    }

    public final void resetSinkStateForFlush() {
        this.submittedPcmBytes = 0L;
        this.submittedEncodedFrames = 0L;
        this.writtenPcmBytes = 0L;
        this.writtenEncodedFrames = 0L;
        this.isWaitingForOffloadEndOfStreamHandled = false;
        this.framesPerEncodedSample = 0;
        this.mediaPositionParameters = new MediaPositionParameters(getMediaPositionParameters().playbackParameters, getMediaPositionParameters().skipSilence, 0L, 0L);
        this.startMediaTimeUs = 0L;
        this.afterDrainParameters = null;
        this.mediaPositionParametersCheckpoints.clear();
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        this.outputBuffer = null;
        this.stoppedAudioTrack = false;
        this.handledEndOfStream = false;
        this.drainingAudioProcessorIndex = -1;
        this.avSyncHeader = null;
        this.bytesUntilNextAvSync = 0;
        this.trimmingAudioProcessor.trimmedFrameCount = 0L;
        flushAudioProcessors();
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        if (this.audioAttributes.equals(audioAttributes)) {
            return;
        }
        this.audioAttributes = audioAttributes;
        if (this.tunneling) {
            return;
        }
        flush();
    }

    public final void setAudioProcessorPlaybackParametersAndSkipSilence(PlaybackParameters playbackParameters, boolean z) {
        MediaPositionParameters mediaPositionParameters = getMediaPositionParameters();
        if (playbackParameters.equals(mediaPositionParameters.playbackParameters) && z == mediaPositionParameters.skipSilence) {
            return;
        }
        MediaPositionParameters mediaPositionParameters2 = new MediaPositionParameters(playbackParameters, z, -9223372036854775807L, -9223372036854775807L);
        if (isAudioTrackInitialized()) {
            this.afterDrainParameters = mediaPositionParameters2;
        } else {
            this.mediaPositionParameters = mediaPositionParameters2;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAudioSessionId(int i) {
        this.log.i("calling setAudioSessionId = " + i);
        if (this.audioSessionId != i) {
            this.audioSessionId = i;
            this.externalAudioSessionIdProvided = i != 0;
            flush();
        }
    }

    @RequiresApi(23)
    public final void setAudioTrackPlaybackParametersV23(PlaybackParameters playbackParameters) {
        if (isAudioTrackInitialized()) {
            try {
                this.audioTrack.setPlaybackParams(DefaultAudioSink$$ExternalSyntheticApiModelOutline0.m().allowDefaults().setSpeed(playbackParameters.speed).setPitch(playbackParameters.pitch).setAudioFallbackMode(2));
            } catch (IllegalArgumentException e) {
                Log.w("DefaultAudioSink", "Failed to set playback params", e);
            }
            playbackParameters = new PlaybackParameters(this.audioTrack.getPlaybackParams().getSpeed(), this.audioTrack.getPlaybackParams().getPitch());
            this.audioTrackPositionTracker.setAudioTrackPlaybackSpeed(playbackParameters.speed);
        }
        this.audioTrackPlaybackParameters = playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        if (this.auxEffectInfo.equals(auxEffectInfo)) {
            return;
        }
        int i = auxEffectInfo.effectId;
        float f = auxEffectInfo.sendLevel;
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            if (this.auxEffectInfo.effectId != i) {
                audioTrack.attachAuxEffect(i);
            }
            if (i != 0) {
                this.audioTrack.setAuxEffectSendLevel(f);
            }
        }
        this.auxEffectInfo = auxEffectInfo;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setListener(AudioSink.Listener listener) {
        this.listener = listener;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        PlaybackParameters playbackParameters2 = new PlaybackParameters(Util.constrainValue(playbackParameters.speed, 0.1f, 8.0f), Util.constrainValue(playbackParameters.pitch, 0.1f, 8.0f));
        if (!this.enableAudioTrackPlaybackParams || Util.SDK_INT < 23) {
            setAudioProcessorPlaybackParametersAndSkipSilence(playbackParameters2, getMediaPositionParameters().skipSilence);
        } else {
            setAudioTrackPlaybackParametersV23(playbackParameters2);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setPlayerId(@Nullable PlayerId playerId) {
        this.playerId = playerId;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    @RequiresApi(23)
    public void setPreferredDevice(@Nullable AudioDeviceInfo audioDeviceInfo) {
        AudioDeviceInfoApi23 audioDeviceInfoApi23 = audioDeviceInfo == null ? null : new AudioDeviceInfoApi23(audioDeviceInfo);
        this.preferredDevice = audioDeviceInfoApi23;
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            Api23.setPreferredDeviceOnAudioTrack(audioTrack, audioDeviceInfoApi23);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setSkipSilenceEnabled(boolean z) {
        setAudioProcessorPlaybackParametersAndSkipSilence(getMediaPositionParameters().playbackParameters, z);
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setVolume(float f) {
        if (this.volume != f) {
            this.log.i("setVolume: volume = " + f);
            this.volume = f;
            setVolumeInternal();
        }
    }

    public final void setVolumeInternal() {
        if (isAudioTrackInitialized()) {
            if (Util.SDK_INT >= 21) {
                this.audioTrack.setVolume(this.volume);
                return;
            }
            AudioTrack audioTrack = this.audioTrack;
            float f = this.volume;
            audioTrack.setStereoVolume(f, f);
        }
    }

    public final void setupAudioProcessors() {
        AudioProcessor[] audioProcessorArr = this.configuration.availableAudioProcessors;
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : audioProcessorArr) {
            if (audioProcessor.isActive()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.flush();
            }
        }
        int size = arrayList.size();
        this.activeAudioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.outputBuffers = new ByteBuffer[size];
        flushAudioProcessors();
    }

    public final boolean shouldApplyAudioProcessorPlaybackParameters() {
        return (this.tunneling || !"audio/raw".equals(this.configuration.inputFormat.sampleMimeType) || shouldUseFloatOutput(this.configuration.inputFormat.pcmEncoding)) ? false : true;
    }

    public final boolean shouldUseFloatOutput(int i) {
        return this.enableFloatOutput && Util.isEncodingHighResolutionPcm(i);
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean supportsFormat(Format format) {
        return getFormatSupport(format) != 0;
    }

    public final boolean useOffloadedPlayback(Format format, AudioAttributes audioAttributes) {
        int audioTrackChannelConfig;
        int offloadedPlaybackSupport;
        if (Util.SDK_INT >= 29 && this.offloadMode != 0) {
            String str = format.sampleMimeType;
            str.getClass();
            int encoding = MimeTypes.getEncoding(str, format.codecs);
            if (encoding != 0 && (audioTrackChannelConfig = Util.getAudioTrackChannelConfig(format.channelCount)) != 0 && (offloadedPlaybackSupport = getOffloadedPlaybackSupport(getAudioFormat(format.sampleRate, audioTrackChannelConfig, encoding), audioAttributes.getAudioAttributesV21().audioAttributes)) != 0) {
                if (offloadedPlaybackSupport == 1) {
                    return ((format.encoderDelay != 0 || format.encoderPadding != 0) && (this.offloadMode == 1)) ? false : true;
                }
                if (offloadedPlaybackSupport == 2) {
                    return true;
                }
                throw new IllegalStateException();
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void writeBuffer(java.nio.ByteBuffer r13, long r14) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.writeBuffer(java.nio.ByteBuffer, long):void");
    }

    @RequiresApi(21)
    public final int writeNonBlockingWithAvSyncV21(AudioTrack audioTrack, ByteBuffer byteBuffer, int i, long j) {
        if (Util.SDK_INT >= 26) {
            return audioTrack.write(byteBuffer, i, 1, j * 1000);
        }
        if (this.avSyncHeader == null) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
            this.avSyncHeader = byteBufferAllocate;
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(AvSyncHeaderFormatter.AVSYNC_HEADER_START_CODE);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i);
            this.avSyncHeader.putLong(8, j * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i;
        }
        int iRemaining = this.avSyncHeader.remaining();
        if (iRemaining > 0) {
            int iWrite = audioTrack.write(this.avSyncHeader, iRemaining, 1);
            if (iWrite < 0) {
                this.bytesUntilNextAvSync = 0;
                return iWrite;
            }
            if (iWrite < iRemaining) {
                return 0;
            }
        }
        int iWrite2 = audioTrack.write(byteBuffer, i, 1);
        if (iWrite2 < 0) {
            this.bytesUntilNextAvSync = 0;
            return iWrite2;
        }
        this.bytesUntilNextAvSync -= iWrite2;
        return iWrite2;
    }

    @InlineMe(imports = {"com.google.android.exoplayer2.audio.DefaultAudioSink"}, replacement = "new DefaultAudioSink.Builder().setAudioCapabilities(audioCapabilities).setAudioProcessors(audioProcessors).build()")
    @InlineMeValidationDisabled("Migrate constructor to Builder")
    @Deprecated
    public DefaultAudioSink(@Nullable AudioCapabilities audioCapabilities, AudioProcessor[] audioProcessorArr) {
        Builder builder = new Builder();
        builder.audioCapabilities = (AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES);
        builder.setAudioProcessors(audioProcessorArr);
        this(builder);
    }

    @InlineMe(imports = {"com.google.android.exoplayer2.audio.DefaultAudioSink"}, replacement = "new DefaultAudioSink.Builder().setAudioCapabilities(audioCapabilities).setAudioProcessors(audioProcessors).setEnableFloatOutput(enableFloatOutput).build()")
    @InlineMeValidationDisabled("Migrate constructor to Builder")
    @Deprecated
    public DefaultAudioSink(@Nullable AudioCapabilities audioCapabilities, AudioProcessor[] audioProcessorArr, boolean z) {
        Builder builder = new Builder();
        builder.audioCapabilities = (AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES);
        builder.setAudioProcessors(audioProcessorArr);
        builder.enableFloatOutput = z;
        this(builder);
    }

    @InlineMe(imports = {"com.google.android.exoplayer2.audio.DefaultAudioSink"}, replacement = "new DefaultAudioSink.Builder().setAudioCapabilities(audioCapabilities).setAudioProcessorChain(audioProcessorChain).setEnableFloatOutput(enableFloatOutput).setEnableAudioTrackPlaybackParams(enableAudioTrackPlaybackParams).setOffloadMode(offloadMode).build()")
    @InlineMeValidationDisabled("Migrate constructor to Builder")
    @Deprecated
    public DefaultAudioSink(@Nullable AudioCapabilities audioCapabilities, AudioProcessorChain audioProcessorChain, boolean z, boolean z2, int i) {
        Builder builder = new Builder();
        builder.audioCapabilities = (AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES);
        audioProcessorChain.getClass();
        builder.audioProcessorChain = audioProcessorChain;
        builder.enableFloatOutput = z;
        builder.enableAudioTrackPlaybackParams = z2;
        builder.offloadMode = i;
        this(builder);
    }

    @RequiresNonNull({"#1.audioProcessorChain"})
    public DefaultAudioSink(Builder builder) {
        Logger logger = new Logger(Logger.Module.Audio, "DefaultAudioSink");
        this.log = logger;
        this.DBG = logger.allowDebug();
        this.VDBG = logger.allowVerbose();
        this.audioCapabilities = builder.audioCapabilities;
        com.google.android.exoplayer2.audio.AudioProcessorChain audioProcessorChain = builder.audioProcessorChain;
        this.audioProcessorChain = audioProcessorChain;
        int i = Util.SDK_INT;
        this.enableFloatOutput = i >= 21 && builder.enableFloatOutput;
        this.enableAudioTrackPlaybackParams = i >= 23 && builder.enableAudioTrackPlaybackParams;
        this.offloadMode = i >= 29 ? builder.offloadMode : 0;
        this.audioTrackBufferSizeProvider = builder.audioTrackBufferSizeProvider;
        ConditionVariable conditionVariable = new ConditionVariable(Clock.DEFAULT);
        this.releasingConditionVariable = conditionVariable;
        conditionVariable.open();
        PositionTrackerListener positionTrackerListener = new PositionTrackerListener();
        boolean z = isLatencyQuirkEnabled;
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(positionTrackerListener, z);
        ChannelMappingAudioProcessor channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor;
        TrimmingAudioProcessor trimmingAudioProcessor = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = trimmingAudioProcessor;
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new ResamplingAudioProcessor(), channelMappingAudioProcessor, trimmingAudioProcessor);
        Collections.addAll(arrayList, audioProcessorChain.getAudioProcessors());
        this.toIntPcmAvailableAudioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[0]);
        this.toFloatPcmAvailableAudioProcessors = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.volume = 1.0f;
        StringBuilder sb = new StringBuilder("Amazon quirks: Latency:");
        String str = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
        sb.append(z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        sb.append("; Dolby");
        sb.append(isLegacyPassthroughQuirkEnabled ? DebugKt.DEBUG_PROPERTY_VALUE_ON : str);
        sb.append(". On Sdk: ");
        sb.append(i);
        logger.i(sb.toString());
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
        this.mediaPositionParameters = new MediaPositionParameters(playbackParameters, false, 0L, 0L);
        this.audioTrackPlaybackParameters = playbackParameters;
        this.drainingAudioProcessorIndex = -1;
        this.activeAudioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.mediaPositionParametersCheckpoints = new ArrayDeque<>();
        this.initializationExceptionPendingExceptionHolder = new PendingExceptionHolder<>(100L);
        this.writeExceptionPendingExceptionHolder = new PendingExceptionHolder<>(100L);
        this.audioOffloadListener = builder.audioOffloadListener;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public /* synthetic */ void setOutputStreamOffsetUs(long j) {
    }
}
