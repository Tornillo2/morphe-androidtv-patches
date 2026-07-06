package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
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
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.ToInt16PcmAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.AudioTrackPositionTracker;
import androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.OpusUtil;
import com.amazon.avod.mpb.media.playback.render.AvSyncHeaderFormatter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DefaultAudioSink implements AudioSink {
    public static final int AUDIO_TRACK_RETRY_DURATION_MS = 100;
    public static final int AUDIO_TRACK_SMALLER_BUFFER_RETRY_SIZE = 1000000;
    public static final float DEFAULT_PLAYBACK_SPEED = 1.0f;
    public static final boolean DEFAULT_SKIP_SILENCE = false;
    public static final int ERROR_NATIVE_DEAD_OBJECT = -32;
    public static final float MAX_PITCH = 8.0f;
    public static final float MAX_PLAYBACK_SPEED = 8.0f;
    public static final int MINIMUM_REPORT_SKIPPED_SILENCE_DURATION_US = 300000;
    public static final float MIN_PITCH = 0.1f;
    public static final float MIN_PLAYBACK_SPEED = 0.1f;
    public static final int OUTPUT_MODE_OFFLOAD = 1;
    public static final int OUTPUT_MODE_PASSTHROUGH = 2;
    public static final int OUTPUT_MODE_PCM = 0;
    public static final int REPORT_SKIPPED_SILENCE_DELAY_MS = 100;
    public static final String TAG = "DefaultAudioSink";
    public static boolean failOnSpuriousAudioTimestamp = false;

    @GuardedBy("releaseExecutorLock")
    public static int pendingReleaseCount;

    @Nullable
    @GuardedBy("releaseExecutorLock")
    public static ExecutorService releaseExecutor;
    public static final Object releaseExecutorLock = new Object();
    public long accumulatedSkippedSilenceDurationUs;

    @Nullable
    public MediaPositionParameters afterDrainParameters;
    public AudioAttributes audioAttributes;
    public AudioCapabilities audioCapabilities;
    public AudioCapabilitiesReceiver audioCapabilitiesReceiver;

    @Nullable
    public final ExoPlayer.AudioOffloadListener audioOffloadListener;
    public final AudioOffloadSupportProvider audioOffloadSupportProvider;
    public AudioProcessingPipeline audioProcessingPipeline;
    public final androidx.media3.common.audio.AudioProcessorChain audioProcessorChain;
    public int audioSessionId;

    @Nullable
    public AudioTrack audioTrack;
    public final AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
    public final AudioTrackPositionTracker audioTrackPositionTracker;
    public AuxEffectInfo auxEffectInfo;

    @Nullable
    public ByteBuffer avSyncHeader;
    public int bytesUntilNextAvSync;
    public final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    public Configuration configuration;

    @Nullable
    public final Context context;
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
    public long lastTunnelingAvSyncPresentationTimeUs;

    @Nullable
    public AudioSink.Listener listener;
    public MediaPositionParameters mediaPositionParameters;
    public final ArrayDeque<MediaPositionParameters> mediaPositionParametersCheckpoints;
    public boolean offloadDisabledUntilNextConfiguration;
    public int offloadMode;
    public StreamEventCallbackV29 offloadStreamEventCallbackV29;

    @Nullable
    public OnRoutingChangedListenerApi24 onRoutingChangedListener;

    @Nullable
    public ByteBuffer outputBuffer;

    @Nullable
    public Configuration pendingConfiguration;

    @Nullable
    public Looper playbackLooper;
    public PlaybackParameters playbackParameters;

    @Nullable
    public PlayerId playerId;
    public boolean playing;
    public byte[] preV21OutputBuffer;
    public int preV21OutputBufferOffset;
    public final boolean preferAudioTrackPlaybackParams;

    @Nullable
    public AudioDeviceInfoApi23 preferredDevice;
    public final ConditionVariable releasingConditionVariable;
    public Handler reportSkippedSilenceHandler;
    public boolean skipSilenceEnabled;
    public long skippedOutputFrameCountAtLastPosition;
    public long startMediaTimeUs;
    public boolean startMediaTimeUsNeedsInit;
    public boolean startMediaTimeUsNeedsSync;
    public boolean stoppedAudioTrack;
    public long submittedEncodedFrames;
    public long submittedPcmBytes;
    public final ImmutableList<AudioProcessor> toFloatPcmAvailableAudioProcessors;
    public final ImmutableList<AudioProcessor> toIntPcmAvailableAudioProcessors;
    public final TrimmingAudioProcessor trimmingAudioProcessor;
    public boolean tunneling;
    public float volume;
    public final PendingExceptionHolder<AudioSink.WriteException> writeExceptionPendingExceptionHolder;
    public long writtenEncodedFrames;
    public long writtenPcmBytes;

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
    public interface AudioOffloadSupportProvider {
        AudioOffloadSupport getAudioOffloadSupport(Format format, AudioAttributes audioAttributes);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public interface AudioProcessorChain extends androidx.media3.common.audio.AudioProcessorChain {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AudioTrackBufferSizeProvider {
        public static final AudioTrackBufferSizeProvider DEFAULT = new DefaultAudioTrackBufferSizeProvider(new DefaultAudioTrackBufferSizeProvider.Builder());

        int getBufferSizeInBytes(int i, int i2, int i3, int i4, int i5, int i6, double d);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Configuration {
        public final AudioProcessingPipeline audioProcessingPipeline;
        public final int bufferSize;
        public final boolean enableAudioTrackPlaybackParams;
        public final boolean enableOffloadGapless;
        public final Format inputFormat;
        public final int inputPcmFrameSize;
        public final int outputChannelConfig;
        public final int outputEncoding;
        public final int outputMode;
        public final int outputPcmFrameSize;
        public final int outputSampleRate;
        public final boolean tunneling;

        public Configuration(Format format, int i, int i2, int i3, int i4, int i5, int i6, int i7, AudioProcessingPipeline audioProcessingPipeline, boolean z, boolean z2, boolean z3) {
            this.inputFormat = format;
            this.inputPcmFrameSize = i;
            this.outputMode = i2;
            this.outputPcmFrameSize = i3;
            this.outputSampleRate = i4;
            this.outputChannelConfig = i5;
            this.outputEncoding = i6;
            this.bufferSize = i7;
            this.audioProcessingPipeline = audioProcessingPipeline;
            this.enableAudioTrackPlaybackParams = z;
            this.enableOffloadGapless = z2;
            this.tunneling = z3;
        }

        @RequiresApi(21)
        public static android.media.AudioAttributes getAudioTrackAttributesV21(AudioAttributes audioAttributes, boolean z) {
            return z ? getAudioTrackTunnelingAttributesV21() : audioAttributes.getAudioAttributesV21().audioAttributes;
        }

        @RequiresApi(21)
        public static android.media.AudioAttributes getAudioTrackTunnelingAttributesV21() {
            return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
        }

        public AudioTrack buildAudioTrack(androidx.media3.common.AudioAttributes audioAttributes, int i) throws AudioSink.InitializationException {
            try {
                AudioTrack audioTrackCreateAudioTrack = createAudioTrack(audioAttributes, i);
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

        public AudioSink.AudioTrackConfig buildAudioTrackConfig() {
            return new AudioSink.AudioTrackConfig(this.outputEncoding, this.outputSampleRate, this.outputChannelConfig, this.tunneling, this.outputMode == 1, this.bufferSize);
        }

        public boolean canReuseAudioTrack(Configuration configuration) {
            return configuration.outputMode == this.outputMode && configuration.outputEncoding == this.outputEncoding && configuration.outputSampleRate == this.outputSampleRate && configuration.outputChannelConfig == this.outputChannelConfig && configuration.outputPcmFrameSize == this.outputPcmFrameSize && configuration.enableAudioTrackPlaybackParams == this.enableAudioTrackPlaybackParams && configuration.enableOffloadGapless == this.enableOffloadGapless;
        }

        public Configuration copyWithBufferSize(int i) {
            return new Configuration(this.inputFormat, this.inputPcmFrameSize, this.outputMode, this.outputPcmFrameSize, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, i, this.audioProcessingPipeline, this.enableAudioTrackPlaybackParams, this.enableOffloadGapless, this.tunneling);
        }

        public final AudioTrack createAudioTrack(androidx.media3.common.AudioAttributes audioAttributes, int i) {
            int i2 = Util.SDK_INT;
            return i2 >= 29 ? createAudioTrackV29(audioAttributes, i) : i2 >= 21 ? createAudioTrackV21(audioAttributes, i) : createAudioTrackV9(audioAttributes, i);
        }

        @RequiresApi(21)
        public final AudioTrack createAudioTrackV21(androidx.media3.common.AudioAttributes audioAttributes, int i) {
            return new AudioTrack(getAudioTrackAttributesV21(audioAttributes, this.tunneling), Util.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding), this.bufferSize, 1, i);
        }

        @RequiresApi(29)
        public final AudioTrack createAudioTrackV29(androidx.media3.common.AudioAttributes audioAttributes, int i) {
            return DefaultAudioSink$Configuration$$ExternalSyntheticApiModelOutline0.m().setAudioAttributes(getAudioTrackAttributesV21(audioAttributes, this.tunneling)).setAudioFormat(Util.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding)).setTransferMode(1).setBufferSizeInBytes(this.bufferSize).setSessionId(i).setOffloadedPlayback(this.outputMode == 1).build();
        }

        public final AudioTrack createAudioTrackV9(androidx.media3.common.AudioAttributes audioAttributes, int i) {
            int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(audioAttributes.usage);
            return i == 0 ? new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1) : new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, i);
        }

        public long framesToDurationUs(long j) {
            return Util.sampleCountToDurationUs(j, this.outputSampleRate);
        }

        public long inputFramesToDurationUs(long j) {
            return Util.sampleCountToDurationUs(j, this.inputFormat.sampleRate);
        }

        public boolean outputModeIsOffload() {
            return this.outputMode == 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        public final AudioProcessor[] audioProcessors;
        public final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        public final androidx.media3.common.audio.SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this(audioProcessorArr, new SilenceSkippingAudioProcessor(), new androidx.media3.common.audio.SonicAudioProcessor());
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters) {
            this.sonicAudioProcessor.setSpeed(playbackParameters.speed);
            this.sonicAudioProcessor.setPitch(playbackParameters.pitch);
            return playbackParameters;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public boolean applySkipSilenceEnabled(boolean z) {
            this.silenceSkippingAudioProcessor.enabled = z;
            return z;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public long getMediaDuration(long j) {
            return this.sonicAudioProcessor.getMediaDuration(j);
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.skippedFrames;
        }

        public DefaultAudioProcessorChain(AudioProcessor[] audioProcessorArr, SilenceSkippingAudioProcessor silenceSkippingAudioProcessor, androidx.media3.common.audio.SonicAudioProcessor sonicAudioProcessor) {
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

        public MediaPositionParameters(PlaybackParameters playbackParameters, long j, long j2) {
            this.playbackParameters = playbackParameters;
            this.mediaTimeUs = j;
            this.audioTrackPositionUs = j2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static final class OnRoutingChangedListenerApi24 {
        public final AudioTrack audioTrack;
        public final AudioCapabilitiesReceiver capabilitiesReceiver;

        @Nullable
        public AudioRouting.OnRoutingChangedListener listener = new AudioRouting.OnRoutingChangedListener() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink$OnRoutingChangedListenerApi24$$ExternalSyntheticLambda0
            @Override // android.media.AudioRouting.OnRoutingChangedListener
            public final void onRoutingChanged(AudioRouting audioRouting) {
                this.f$0.onRoutingChanged(audioRouting);
            }
        };

        public OnRoutingChangedListenerApi24(AudioTrack audioTrack, AudioCapabilitiesReceiver audioCapabilitiesReceiver) {
            this.audioTrack = audioTrack;
            this.capabilitiesReceiver = audioCapabilitiesReceiver;
            audioTrack.addOnRoutingChangedListener(this.listener, new Handler(Looper.myLooper()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @DoNotInline
        public void onRoutingChanged(AudioRouting audioRouting) {
            if (this.listener == null || audioRouting.getRoutedDevice() == null) {
                return;
            }
            this.capabilitiesReceiver.setRoutedDevice(audioRouting.getRoutedDevice());
        }

        @DoNotInline
        public void release() {
            AudioTrack audioTrack = this.audioTrack;
            AudioRouting.OnRoutingChangedListener onRoutingChangedListener = this.listener;
            onRoutingChangedListener.getClass();
            audioTrack.removeOnRoutingChangedListener(onRoutingChangedListener);
            this.listener = null;
        }
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

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onInvalidLatency(long j) {
            Log.w("DefaultAudioSink", "Ignoring impossibly large audio latency: " + j);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onPositionAdvancing(long j) {
            AudioSink.Listener listener = DefaultAudioSink.this.listener;
            if (listener != null) {
                listener.onPositionAdvancing(j);
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
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
            Log.w("DefaultAudioSink", string);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
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
            Log.w("DefaultAudioSink", string);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
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
            this.callback = new AudioTrack.StreamEventCallback() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink.StreamEventCallbackV29.1
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

        @DoNotInline
        public void register(AudioTrack audioTrack) {
            Handler handler = this.handler;
            Objects.requireNonNull(handler);
            audioTrack.registerStreamEventCallback(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler), this.callback);
        }

        @DoNotInline
        public void unregister(AudioTrack audioTrack) {
            audioTrack.unregisterStreamEventCallback(this.callback);
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public static /* synthetic */ void $r8$lambda$6PFEaAG3accIg_XBqKQUdHZPCJ8(AudioTrack audioTrack, final AudioSink.Listener listener, Handler handler, final AudioSink.AudioTrackConfig audioTrackConfig, ConditionVariable conditionVariable) {
        try {
            audioTrack.flush();
            audioTrack.release();
            if (listener != null && handler.getLooper().getThread().isAlive()) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        listener.onAudioTrackReleased(audioTrackConfig);
                    }
                });
            }
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
            if (listener != null && handler.getLooper().getThread().isAlive()) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        listener.onAudioTrackReleased(audioTrackConfig);
                    }
                });
            }
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
                return OpusUtil.parseOggPacketAudioSampleCount(byteBuffer);
        }
    }

    public static boolean isAudioTrackDeadObject(int i) {
        return (Util.SDK_INT >= 24 && i == -6) || i == -32;
    }

    public static boolean isOffloadedPlayback(AudioTrack audioTrack) {
        return Util.SDK_INT >= 29 && audioTrack.isOffloadedPlayback();
    }

    public static void releaseAudioTrackAsync(final AudioTrack audioTrack, final ConditionVariable conditionVariable, @Nullable final AudioSink.Listener listener, final AudioSink.AudioTrackConfig audioTrackConfig) {
        conditionVariable.close();
        final Handler handler = new Handler(Looper.myLooper());
        synchronized (releaseExecutorLock) {
            try {
                if (releaseExecutor == null) {
                    releaseExecutor = Util.newSingleThreadExecutor("ExoPlayer:AudioTrackReleaseThread");
                }
                pendingReleaseCount++;
                releaseExecutor.execute(new Runnable() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultAudioSink.$r8$lambda$6PFEaAG3accIg_XBqKQUdHZPCJ8(audioTrack, listener, handler, audioTrackConfig, conditionVariable);
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
        PlaybackParameters playbackParametersApplyPlaybackParameters;
        if (useAudioTrackPlaybackParams()) {
            playbackParametersApplyPlaybackParameters = PlaybackParameters.DEFAULT;
        } else {
            playbackParametersApplyPlaybackParameters = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters) : PlaybackParameters.DEFAULT;
            this.playbackParameters = playbackParametersApplyPlaybackParameters;
        }
        PlaybackParameters playbackParameters = playbackParametersApplyPlaybackParameters;
        this.skipSilenceEnabled = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applySkipSilenceEnabled(this.skipSilenceEnabled) : false;
        this.mediaPositionParametersCheckpoints.add(new MediaPositionParameters(playbackParameters, Math.max(0L, j), Util.sampleCountToDurationUs(getWrittenFrames(), this.configuration.outputSampleRate)));
        setupAudioProcessors();
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onSkipSilenceEnabledChanged(this.skipSilenceEnabled);
        }
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
        long skippedOutputFrameCount = this.audioProcessorChain.getSkippedOutputFrameCount();
        long jSampleCountToDurationUs = Util.sampleCountToDurationUs(skippedOutputFrameCount, this.configuration.outputSampleRate) + j;
        long j2 = this.skippedOutputFrameCountAtLastPosition;
        if (skippedOutputFrameCount > j2) {
            long jSampleCountToDurationUs2 = Util.sampleCountToDurationUs(skippedOutputFrameCount - j2, this.configuration.outputSampleRate);
            this.skippedOutputFrameCountAtLastPosition = skippedOutputFrameCount;
            handleSkippedSilence(jSampleCountToDurationUs2);
        }
        return jSampleCountToDurationUs;
    }

    public final AudioTrack buildAudioTrack(Configuration configuration) throws AudioSink.InitializationException {
        try {
            AudioTrack audioTrackBuildAudioTrack = configuration.buildAudioTrack(this.audioAttributes, this.audioSessionId);
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

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void configure(Format format, int i, @Nullable int[] iArr) throws AudioSink.ConfigurationException {
        int i2;
        int iIntValue;
        int iIntValue2;
        boolean z;
        int pcmFrameSize;
        boolean z2;
        int i3;
        AudioProcessingPipeline audioProcessingPipeline;
        int i4;
        int i5;
        int bufferSizeInBytes;
        int[] iArr2;
        maybeStartAudioCapabilitiesReceiver();
        if ("audio/raw".equals(format.sampleMimeType)) {
            Assertions.checkArgument(Util.isEncodingLinearPcm(format.pcmEncoding));
            pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
            ImmutableList.Builder builder = new ImmutableList.Builder(4);
            if (shouldUseFloatOutput(format.pcmEncoding)) {
                builder.addAll((Iterable) this.toFloatPcmAvailableAudioProcessors);
            } else {
                builder.addAll((Iterable) this.toIntPcmAvailableAudioProcessors);
                AudioProcessor[] audioProcessors = this.audioProcessorChain.getAudioProcessors();
                builder.addAll(audioProcessors, audioProcessors.length);
            }
            AudioProcessingPipeline audioProcessingPipeline2 = new AudioProcessingPipeline(builder.build());
            if (audioProcessingPipeline2.equals(this.audioProcessingPipeline)) {
                audioProcessingPipeline2 = this.audioProcessingPipeline;
            }
            TrimmingAudioProcessor trimmingAudioProcessor = this.trimmingAudioProcessor;
            int i6 = format.encoderDelay;
            int i7 = format.encoderPadding;
            trimmingAudioProcessor.trimStartFrames = i6;
            trimmingAudioProcessor.trimEndFrames = i7;
            if (Util.SDK_INT < 21 && format.channelCount == 8 && iArr == null) {
                iArr2 = new int[6];
                for (int i8 = 0; i8 < 6; i8++) {
                    iArr2[i8] = i8;
                }
            } else {
                iArr2 = iArr;
            }
            this.channelMappingAudioProcessor.pendingOutputChannels = iArr2;
            try {
                AudioProcessor.AudioFormat audioFormatConfigure = audioProcessingPipeline2.configure(new AudioProcessor.AudioFormat(format));
                int i9 = audioFormatConfigure.encoding;
                i2 = audioFormatConfigure.sampleRate;
                int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(audioFormatConfigure.channelCount);
                int pcmFrameSize2 = Util.getPcmFrameSize(i9, audioFormatConfigure.channelCount);
                iIntValue = i9;
                iIntValue2 = audioTrackChannelConfig;
                z = this.preferAudioTrackPlaybackParams;
                i3 = 0;
                audioProcessingPipeline = audioProcessingPipeline2;
                i4 = pcmFrameSize2;
                z2 = false;
            } catch (AudioProcessor.UnhandledAudioFormatException e) {
                throw new AudioSink.ConfigurationException(e, format);
            }
        } else {
            AudioProcessingPipeline audioProcessingPipeline3 = new AudioProcessingPipeline(RegularImmutableList.EMPTY);
            i2 = format.sampleRate;
            AudioOffloadSupport formatOffloadSupport = this.offloadMode != 0 ? getFormatOffloadSupport(format) : AudioOffloadSupport.DEFAULT_UNSUPPORTED;
            if (this.offloadMode == 0 || !formatOffloadSupport.isFormatSupported) {
                Pair<Integer, Integer> encodingAndChannelConfigForPassthrough = this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format, this.audioAttributes);
                if (encodingAndChannelConfigForPassthrough == null) {
                    throw new AudioSink.ConfigurationException("Unable to configure passthrough for: " + format, format);
                }
                iIntValue = ((Integer) encodingAndChannelConfigForPassthrough.first).intValue();
                iIntValue2 = ((Integer) encodingAndChannelConfigForPassthrough.second).intValue();
                z = this.preferAudioTrackPlaybackParams;
                pcmFrameSize = -1;
                z2 = false;
                i3 = 2;
                audioProcessingPipeline = audioProcessingPipeline3;
                i4 = -1;
            } else {
                String str = format.sampleMimeType;
                str.getClass();
                int encoding = MimeTypes.getEncoding(str, format.codecs);
                int audioTrackChannelConfig2 = Util.getAudioTrackChannelConfig(format.channelCount);
                z2 = formatOffloadSupport.isGaplessSupported;
                audioProcessingPipeline = audioProcessingPipeline3;
                iIntValue = encoding;
                iIntValue2 = audioTrackChannelConfig2;
                pcmFrameSize = -1;
                i4 = -1;
                z = true;
                i3 = 1;
            }
        }
        if (iIntValue == 0) {
            throw new AudioSink.ConfigurationException("Invalid output encoding (mode=" + i3 + ") for: " + format, format);
        }
        if (iIntValue2 == 0) {
            throw new AudioSink.ConfigurationException("Invalid output channel config (mode=" + i3 + ") for: " + format, format);
        }
        int i10 = format.bitrate;
        int i11 = ("audio/vnd.dts.hd;profile=lbr".equals(format.sampleMimeType) && i10 == -1) ? 768000 : i10;
        if (i != 0) {
            bufferSizeInBytes = i;
            i5 = i2;
        } else {
            i5 = i2;
            bufferSizeInBytes = this.audioTrackBufferSizeProvider.getBufferSizeInBytes(getAudioTrackMinBufferSize(i2, iIntValue2, iIntValue), iIntValue, i3, i4 != -1 ? i4 : 1, i5, i11, z ? 8.0d : 1.0d);
        }
        this.offloadDisabledUntilNextConfiguration = false;
        boolean z3 = z2;
        int i12 = i3;
        Configuration configuration = new Configuration(format, pcmFrameSize, i12, i4, i5, iIntValue2, iIntValue, bufferSizeInBytes, audioProcessingPipeline, z, z3, this.tunneling);
        if (isAudioTrackInitialized()) {
            this.pendingConfiguration = configuration;
        } else {
            this.configuration = configuration;
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            flush();
        }
    }

    public final boolean drainToEndOfStream() throws Exception {
        ByteBuffer byteBuffer;
        if (this.audioProcessingPipeline.isOperational()) {
            this.audioProcessingPipeline.queueEndOfStream();
            processBuffers(Long.MIN_VALUE);
            return this.audioProcessingPipeline.isEnded() && ((byteBuffer = this.outputBuffer) == null || !byteBuffer.hasRemaining());
        }
        ByteBuffer byteBuffer2 = this.outputBuffer;
        if (byteBuffer2 == null) {
            return true;
        }
        writeBuffer(byteBuffer2, Long.MIN_VALUE);
        return this.outputBuffer == null;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void enableTunnelingV21() {
        Assertions.checkState(Util.SDK_INT >= 21);
        Assertions.checkState(this.externalAudioSessionIdProvided);
        if (this.tunneling) {
            return;
        }
        this.tunneling = true;
        flush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void flush() {
        OnRoutingChangedListenerApi24 onRoutingChangedListenerApi24;
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
            int i = Util.SDK_INT;
            if (i < 21 && !this.externalAudioSessionIdProvided) {
                this.audioSessionId = 0;
            }
            AudioSink.AudioTrackConfig audioTrackConfigBuildAudioTrackConfig = this.configuration.buildAudioTrackConfig();
            Configuration configuration = this.pendingConfiguration;
            if (configuration != null) {
                this.configuration = configuration;
                this.pendingConfiguration = null;
            }
            this.audioTrackPositionTracker.reset();
            if (i >= 24 && (onRoutingChangedListenerApi24 = this.onRoutingChangedListener) != null) {
                onRoutingChangedListenerApi24.release();
                this.onRoutingChangedListener = null;
            }
            releaseAudioTrackAsync(this.audioTrack, this.releasingConditionVariable, this.listener, audioTrackConfigBuildAudioTrackConfig);
            this.audioTrack = null;
        }
        this.writeExceptionPendingExceptionHolder.pendingException = null;
        this.initializationExceptionPendingExceptionHolder.pendingException = null;
        this.skippedOutputFrameCountAtLastPosition = 0L;
        this.accumulatedSkippedSilenceDurationUs = 0L;
        Handler handler = this.reportSkippedSilenceHandler;
        if (handler != null) {
            handler.getClass();
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public androidx.media3.common.AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getCurrentPositionUs(boolean z) {
        if (!isAudioTrackInitialized() || this.startMediaTimeUsNeedsInit) {
            return Long.MIN_VALUE;
        }
        return applySkipping(applyMediaPositionParameters(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(z), Util.sampleCountToDurationUs(getWrittenFrames(), this.configuration.outputSampleRate))));
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioOffloadSupport getFormatOffloadSupport(Format format) {
        return this.offloadDisabledUntilNextConfiguration ? AudioOffloadSupport.DEFAULT_UNSUPPORTED : this.audioOffloadSupportProvider.getAudioOffloadSupport(format, this.audioAttributes);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public int getFormatSupport(Format format) {
        maybeStartAudioCapabilitiesReceiver();
        if (!"audio/raw".equals(format.sampleMimeType)) {
            return this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format, this.audioAttributes) != null ? 2 : 0;
        }
        if (Util.isEncodingLinearPcm(format.pcmEncoding)) {
            int i = format.pcmEncoding;
            return (i == 2 || (this.enableFloatOutput && i == 4)) ? 2 : 1;
        }
        Log.w("DefaultAudioSink", "Invalid PCM encoding: " + format.pcmEncoding);
        return 0;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean getSkipSilenceEnabled() {
        return this.skipSilenceEnabled;
    }

    public final long getSubmittedFrames() {
        Configuration configuration = this.configuration;
        return configuration.outputMode == 0 ? this.submittedPcmBytes / ((long) configuration.inputPcmFrameSize) : this.submittedEncodedFrames;
    }

    public final long getWrittenFrames() {
        return this.configuration.outputMode == 0 ? Util.ceilDivide(this.writtenPcmBytes, r0.outputPcmFrameSize) : this.writtenEncodedFrames;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00f1, code lost:
    
        if (r5 == 0) goto L67;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0185  */
    @Override // androidx.media3.exoplayer.audio.AudioSink
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean handleBuffer(java.nio.ByteBuffer r19, long r20, int r22) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioSink.handleBuffer(java.nio.ByteBuffer, long, int):boolean");
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void handleDiscontinuity() {
        this.startMediaTimeUsNeedsSync = true;
    }

    public final void handleSkippedSilence(long j) {
        this.accumulatedSkippedSilenceDurationUs += j;
        if (this.reportSkippedSilenceHandler == null) {
            this.reportSkippedSilenceHandler = new Handler(Looper.myLooper());
        }
        this.reportSkippedSilenceHandler.removeCallbacksAndMessages(null);
        this.reportSkippedSilenceHandler.postDelayed(new Runnable() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.maybeReportSkippedSilence();
            }
        }, 100L);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean hasPendingData() {
        return isAudioTrackInitialized() && this.audioTrackPositionTracker.hasPendingData(getWrittenFrames());
    }

    public final boolean initializeAudioTrack() throws AudioSink.InitializationException {
        AudioCapabilitiesReceiver audioCapabilitiesReceiver;
        PlayerId playerId;
        if (!this.releasingConditionVariable.isOpen()) {
            return false;
        }
        AudioTrack audioTrackBuildAudioTrackWithRetry = buildAudioTrackWithRetry();
        this.audioTrack = audioTrackBuildAudioTrackWithRetry;
        if (isOffloadedPlayback(audioTrackBuildAudioTrackWithRetry)) {
            registerStreamEventCallbackV29(this.audioTrack);
            Configuration configuration = this.configuration;
            if (configuration.enableOffloadGapless) {
                AudioTrack audioTrack = this.audioTrack;
                Format format = configuration.inputFormat;
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
        Configuration configuration2 = this.configuration;
        audioTrackPositionTracker.setAudioTrack(audioTrack2, configuration2.outputMode == 2, configuration2.outputEncoding, configuration2.outputPcmFrameSize, configuration2.bufferSize);
        setVolumeInternal();
        int i2 = this.auxEffectInfo.effectId;
        if (i2 != 0) {
            this.audioTrack.attachAuxEffect(i2);
            this.audioTrack.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
        }
        AudioDeviceInfoApi23 audioDeviceInfoApi23 = this.preferredDevice;
        if (audioDeviceInfoApi23 != null && i >= 23) {
            Api23.setPreferredDeviceOnAudioTrack(this.audioTrack, audioDeviceInfoApi23);
            AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = this.audioCapabilitiesReceiver;
            if (audioCapabilitiesReceiver2 != null) {
                audioCapabilitiesReceiver2.setRoutedDevice(this.preferredDevice.audioDeviceInfo);
            }
        }
        if (i >= 24 && (audioCapabilitiesReceiver = this.audioCapabilitiesReceiver) != null) {
            this.onRoutingChangedListener = new OnRoutingChangedListenerApi24(this.audioTrack, audioCapabilitiesReceiver);
        }
        this.startMediaTimeUsNeedsInit = true;
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onAudioTrackInitialized(this.configuration.buildAudioTrackConfig());
        }
        return true;
    }

    public final boolean isAudioTrackInitialized() {
        return this.audioTrack != null;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean isEnded() {
        if (isAudioTrackInitialized()) {
            return this.handledEndOfStream && !hasPendingData();
        }
        return true;
    }

    public final void maybeDisableOffload() {
        if (this.configuration.outputModeIsOffload()) {
            this.offloadDisabledUntilNextConfiguration = true;
        }
    }

    public final void maybeReportSkippedSilence() {
        if (this.accumulatedSkippedSilenceDurationUs >= 300000) {
            this.listener.onSilenceSkipped();
            this.accumulatedSkippedSilenceDurationUs = 0L;
        }
    }

    public final void maybeStartAudioCapabilitiesReceiver() {
        if (this.audioCapabilitiesReceiver != null || this.context == null) {
            return;
        }
        this.playbackLooper = Looper.myLooper();
        AudioCapabilitiesReceiver audioCapabilitiesReceiver = new AudioCapabilitiesReceiver(this.context, new AudioCapabilitiesReceiver.Listener() { // from class: androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticLambda4
            @Override // androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver.Listener
            public final void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
                this.f$0.onAudioCapabilitiesChanged(audioCapabilities);
            }
        }, this.audioAttributes, this.preferredDevice);
        this.audioCapabilitiesReceiver = audioCapabilitiesReceiver;
        this.audioCapabilities = audioCapabilitiesReceiver.register();
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
        Assertions.checkState(this.playbackLooper == Looper.myLooper());
        if (audioCapabilities.equals(this.audioCapabilities)) {
            return;
        }
        this.audioCapabilities = audioCapabilities;
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onAudioCapabilitiesChanged();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void pause() {
        this.playing = false;
        if (isAudioTrackInitialized()) {
            if (this.audioTrackPositionTracker.pause() || isOffloadedPlayback(this.audioTrack)) {
                this.audioTrack.pause();
            }
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
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
        this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
        this.audioTrack.stop();
        this.bytesUntilNextAvSync = 0;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void playToEndOfStream() throws AudioSink.WriteException {
        if (!this.handledEndOfStream && isAudioTrackInitialized() && drainToEndOfStream()) {
            playPendingData();
            this.handledEndOfStream = true;
        }
    }

    public final void processBuffers(long j) throws Exception {
        ByteBuffer output;
        if (!this.audioProcessingPipeline.isOperational()) {
            ByteBuffer byteBuffer = this.inputBuffer;
            if (byteBuffer == null) {
                byteBuffer = AudioProcessor.EMPTY_BUFFER;
            }
            writeBuffer(byteBuffer, j);
            return;
        }
        while (!this.audioProcessingPipeline.isEnded()) {
            do {
                output = this.audioProcessingPipeline.getOutput();
                if (output.hasRemaining()) {
                    writeBuffer(output, j);
                } else {
                    ByteBuffer byteBuffer2 = this.inputBuffer;
                    if (byteBuffer2 == null || !byteBuffer2.hasRemaining()) {
                        return;
                    } else {
                        this.audioProcessingPipeline.queueInput(this.inputBuffer);
                    }
                }
            } while (!output.hasRemaining());
            return;
        }
    }

    @RequiresApi(29)
    public final void registerStreamEventCallbackV29(AudioTrack audioTrack) {
        if (this.offloadStreamEventCallbackV29 == null) {
            this.offloadStreamEventCallbackV29 = new StreamEventCallbackV29();
        }
        this.offloadStreamEventCallbackV29.register(audioTrack);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void release() {
        AudioCapabilitiesReceiver audioCapabilitiesReceiver = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver != null) {
            audioCapabilitiesReceiver.unregister();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void reset() {
        flush();
        UnmodifiableIterator<AudioProcessor> it = this.toIntPcmAvailableAudioProcessors.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        UnmodifiableIterator<AudioProcessor> it2 = this.toFloatPcmAvailableAudioProcessors.iterator();
        while (it2.hasNext()) {
            it2.next().reset();
        }
        AudioProcessingPipeline audioProcessingPipeline = this.audioProcessingPipeline;
        if (audioProcessingPipeline != null) {
            audioProcessingPipeline.reset();
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
        this.mediaPositionParameters = new MediaPositionParameters(this.playbackParameters, 0L, 0L);
        this.startMediaTimeUs = 0L;
        this.afterDrainParameters = null;
        this.mediaPositionParametersCheckpoints.clear();
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        this.outputBuffer = null;
        this.stoppedAudioTrack = false;
        this.handledEndOfStream = false;
        this.avSyncHeader = null;
        this.bytesUntilNextAvSync = 0;
        this.trimmingAudioProcessor.trimmedFrameCount = 0L;
        setupAudioProcessors();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioAttributes(androidx.media3.common.AudioAttributes audioAttributes) {
        if (this.audioAttributes.equals(audioAttributes)) {
            return;
        }
        this.audioAttributes = audioAttributes;
        if (this.tunneling) {
            return;
        }
        AudioCapabilitiesReceiver audioCapabilitiesReceiver = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver != null) {
            audioCapabilitiesReceiver.setAudioAttributes(audioAttributes);
        }
        flush();
    }

    public final void setAudioProcessorPlaybackParameters(PlaybackParameters playbackParameters) {
        MediaPositionParameters mediaPositionParameters = new MediaPositionParameters(playbackParameters, -9223372036854775807L, -9223372036854775807L);
        if (isAudioTrackInitialized()) {
            this.afterDrainParameters = mediaPositionParameters;
        } else {
            this.mediaPositionParameters = mediaPositionParameters;
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioSessionId(int i) {
        if (this.audioSessionId != i) {
            this.audioSessionId = i;
            this.externalAudioSessionIdProvided = i != 0;
            flush();
        }
    }

    @RequiresApi(23)
    public final void setAudioTrackPlaybackParametersV23() {
        if (isAudioTrackInitialized()) {
            try {
                this.audioTrack.setPlaybackParams(DefaultAudioSink$$ExternalSyntheticApiModelOutline0.m().allowDefaults().setSpeed(this.playbackParameters.speed).setPitch(this.playbackParameters.pitch).setAudioFallbackMode(2));
            } catch (IllegalArgumentException e) {
                Log.w("DefaultAudioSink", "Failed to set playback params", e);
            }
            PlaybackParameters playbackParameters = new PlaybackParameters(this.audioTrack.getPlaybackParams().getSpeed(), this.audioTrack.getPlaybackParams().getPitch());
            this.playbackParameters = playbackParameters;
            this.audioTrackPositionTracker.setAudioTrackPlaybackSpeed(playbackParameters.speed);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
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

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setClock(Clock clock) {
        this.audioTrackPositionTracker.clock = clock;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setListener(AudioSink.Listener listener) {
        this.listener = listener;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    @RequiresApi(29)
    public void setOffloadDelayPadding(int i, int i2) {
        Configuration configuration;
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack == null || !isOffloadedPlayback(audioTrack) || (configuration = this.configuration) == null || !configuration.enableOffloadGapless) {
            return;
        }
        this.audioTrack.setOffloadDelayPadding(i, i2);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    @RequiresApi(29)
    public void setOffloadMode(int i) {
        Assertions.checkState(Util.SDK_INT >= 29);
        this.offloadMode = i;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.playbackParameters = new PlaybackParameters(Util.constrainValue(playbackParameters.speed, 0.1f, 8.0f), Util.constrainValue(playbackParameters.pitch, 0.1f, 8.0f));
        if (useAudioTrackPlaybackParams()) {
            setAudioTrackPlaybackParametersV23();
        } else {
            setAudioProcessorPlaybackParameters(playbackParameters);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlayerId(@Nullable PlayerId playerId) {
        this.playerId = playerId;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    @RequiresApi(23)
    public void setPreferredDevice(@Nullable AudioDeviceInfo audioDeviceInfo) {
        this.preferredDevice = audioDeviceInfo == null ? null : new AudioDeviceInfoApi23(audioDeviceInfo);
        AudioCapabilitiesReceiver audioCapabilitiesReceiver = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver != null) {
            audioCapabilitiesReceiver.setRoutedDevice(audioDeviceInfo);
        }
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            Api23.setPreferredDeviceOnAudioTrack(audioTrack, this.preferredDevice);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setSkipSilenceEnabled(boolean z) {
        this.skipSilenceEnabled = z;
        setAudioProcessorPlaybackParameters(useAudioTrackPlaybackParams() ? PlaybackParameters.DEFAULT : this.playbackParameters);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVolume(float f) {
        if (this.volume != f) {
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
        AudioProcessingPipeline audioProcessingPipeline = this.configuration.audioProcessingPipeline;
        this.audioProcessingPipeline = audioProcessingPipeline;
        audioProcessingPipeline.flush();
    }

    public final boolean shouldApplyAudioProcessorPlaybackParameters() {
        if (this.tunneling) {
            return false;
        }
        Configuration configuration = this.configuration;
        return configuration.outputMode == 0 && !shouldUseFloatOutput(configuration.inputFormat.pcmEncoding);
    }

    public final boolean shouldUseFloatOutput(int i) {
        return this.enableFloatOutput && Util.isEncodingHighResolutionPcm(i);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean supportsFormat(Format format) {
        return getFormatSupport(format) != 0;
    }

    public final boolean useAudioTrackPlaybackParams() {
        Configuration configuration = this.configuration;
        return configuration != null && configuration.enableAudioTrackPlaybackParams && Util.SDK_INT >= 23;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void writeBuffer(java.nio.ByteBuffer r13, long r14) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioSink.writeBuffer(java.nio.ByteBuffer, long):void");
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

    @RequiresNonNull({"#1.audioProcessorChain"})
    public DefaultAudioSink(Builder builder) {
        Context context = builder.context;
        this.context = context;
        androidx.media3.common.AudioAttributes audioAttributes = androidx.media3.common.AudioAttributes.DEFAULT;
        this.audioAttributes = audioAttributes;
        this.audioCapabilities = context != null ? AudioCapabilities.getCapabilities(context, audioAttributes, null) : builder.audioCapabilities;
        this.audioProcessorChain = builder.audioProcessorChain;
        int i = Util.SDK_INT;
        this.enableFloatOutput = i >= 21 && builder.enableFloatOutput;
        this.preferAudioTrackPlaybackParams = i >= 23 && builder.enableAudioTrackPlaybackParams;
        this.offloadMode = 0;
        this.audioTrackBufferSizeProvider = builder.audioTrackBufferSizeProvider;
        AudioOffloadSupportProvider audioOffloadSupportProvider = builder.audioOffloadSupportProvider;
        audioOffloadSupportProvider.getClass();
        this.audioOffloadSupportProvider = audioOffloadSupportProvider;
        ConditionVariable conditionVariable = new ConditionVariable(Clock.DEFAULT);
        this.releasingConditionVariable = conditionVariable;
        conditionVariable.open();
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener());
        ChannelMappingAudioProcessor channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor;
        TrimmingAudioProcessor trimmingAudioProcessor = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = trimmingAudioProcessor;
        this.toIntPcmAvailableAudioProcessors = ImmutableList.of((TrimmingAudioProcessor) new ToInt16PcmAudioProcessor(), (TrimmingAudioProcessor) channelMappingAudioProcessor, trimmingAudioProcessor);
        this.toFloatPcmAvailableAudioProcessors = ImmutableList.of(new ToFloatPcmAudioProcessor());
        this.volume = 1.0f;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
        this.mediaPositionParameters = new MediaPositionParameters(playbackParameters, 0L, 0L);
        this.playbackParameters = playbackParameters;
        this.skipSilenceEnabled = false;
        this.mediaPositionParametersCheckpoints = new ArrayDeque<>();
        this.initializationExceptionPendingExceptionHolder = new PendingExceptionHolder<>(100L);
        this.writeExceptionPendingExceptionHolder = new PendingExceptionHolder<>(100L);
        this.audioOffloadListener = builder.audioOffloadListener;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public AudioCapabilities audioCapabilities;

        @Nullable
        public ExoPlayer.AudioOffloadListener audioOffloadListener;
        public AudioOffloadSupportProvider audioOffloadSupportProvider;

        @Nullable
        public androidx.media3.common.audio.AudioProcessorChain audioProcessorChain;
        public AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
        public boolean buildCalled;

        @Nullable
        public final Context context;
        public boolean enableAudioTrackPlaybackParams;
        public boolean enableFloatOutput;

        @Deprecated
        public Builder() {
            this.context = null;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
            this.audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
        }

        public DefaultAudioSink build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.audioProcessorChain == null) {
                this.audioProcessorChain = new DefaultAudioProcessorChain(new AudioProcessor[0]);
            }
            if (this.audioOffloadSupportProvider == null) {
                this.audioOffloadSupportProvider = new DefaultAudioOffloadSupportProvider(this.context);
            }
            return new DefaultAudioSink(this);
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setAudioCapabilities(AudioCapabilities audioCapabilities) {
            audioCapabilities.getClass();
            this.audioCapabilities = audioCapabilities;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudioOffloadSupportProvider(AudioOffloadSupportProvider audioOffloadSupportProvider) {
            this.audioOffloadSupportProvider = audioOffloadSupportProvider;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudioProcessorChain(androidx.media3.common.audio.AudioProcessorChain audioProcessorChain) {
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

        public Builder(Context context) {
            this.context = context;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
            this.audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public /* synthetic */ void setOutputStreamOffsetUs(long j) {
    }
}
