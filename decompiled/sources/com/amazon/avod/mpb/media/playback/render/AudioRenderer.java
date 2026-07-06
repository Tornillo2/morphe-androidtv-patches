package com.amazon.avod.mpb.media.playback.render;

import android.media.AudioTrack;
import android.media.MediaFormat;
import android.media.PlaybackParams;
import android.os.Build;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.audio.DefaultAudioSink$$ExternalSyntheticApiModelOutline0;
import com.amazon.avod.mpb.annotate.Positive;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.playback.avsync.TimeSource;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@NotThreadSafe
@SourceDebugExtension({"SMAP\nAudioRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AudioRenderer.kt\ncom/amazon/avod/mpb/media/playback/render/AudioRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,922:1\n1#2:923\n*E\n"})
public final class AudioRenderer extends MediaRenderer {
    public static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    public static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    public static final int MICROSECONDS_PER_SECOND = 1000000;
    public static final float NORMAL_AUDIO_GAIN = 1.0f;
    public static final float NORMAL_PLAYBACK_SPEED = 1.0f;
    public int audioBitrate;

    @Nullable
    public Integer audioSessionId;

    @Nullable
    public AudioTrack audioTrack;

    @NotNull
    public final AvSyncHeaderFormatter avSyncHeaderFormatter;
    public final long backoffTimeForRetriesOnAudioTrackCreationFailureMillis;

    @NotNull
    public final MediaCodecDeviceCapabilityDetector capabilityDetector;
    public float currentAudioTrackSpeed;
    public float desiredPlaybackSpeed;
    public int frameSize;
    public final boolean isNonBlockingAudioRenderingEnabled;
    public boolean isPassThrough;
    public final boolean isPipelineAsynchronous;
    public final boolean isTunnelModeNewWriteAPIForAudioBufferEnabled;
    public final boolean isVerboseMediaClockLoggingEnabled;
    public boolean lastWriteFailedWithDeadObjectError;

    @NotNull
    public final ReadWriteLock lock;

    @Nullable
    public MediaFormat mediaFormat;
    public final int numTriesForAudioTrackCreationFailure;
    public int outputSampleRate;

    @NotNull
    public final String passthroughMimeType;

    @NotNull
    public final PendingExceptionHolder<MediaPipelineBackendException> pendingWriteExceptionHolder;

    @Nullable
    public AudioTrackPositionTracker positionTracker;
    public final boolean shouldFlushBeforeAudioTrackStop;
    public final boolean shouldReportAVSyncStats;
    public final boolean shouldRetryOnAudioTrackCreationFailure;
    public final boolean shouldValidateAudioTrackWriteResult;

    @NotNull
    public final SubmitBufferResult submitAudioBufferResult;

    @NotNull
    public final TimeSource timeSource;
    public long totalFramesWritten;

    @NotNull
    public final AudioTrackFactory trackFactory;
    public float volumeGain;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AudioTrackOperation {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AudioTrackOperation[] $VALUES;
        public static final AudioTrackOperation CREATE = new AudioTrackOperation("CREATE", 0);
        public static final AudioTrackOperation PLAY = new AudioTrackOperation("PLAY", 1);
        public static final AudioTrackOperation PAUSE = new AudioTrackOperation("PAUSE", 2);
        public static final AudioTrackOperation STOP = new AudioTrackOperation("STOP", 3);
        public static final AudioTrackOperation WRITE = new AudioTrackOperation("WRITE", 4);
        public static final AudioTrackOperation FLUSH = new AudioTrackOperation("FLUSH", 5);
        public static final AudioTrackOperation SET_VOLUME = new AudioTrackOperation("SET_VOLUME", 6);
        public static final AudioTrackOperation SET_PLAYBACK_PARAMS = new AudioTrackOperation("SET_PLAYBACK_PARAMS", 7);
        public static final AudioTrackOperation SET_PLAYBACK_RATE = new AudioTrackOperation("SET_PLAYBACK_RATE", 8);
        public static final AudioTrackOperation RELEASE = new AudioTrackOperation("RELEASE", 9);

        public static final /* synthetic */ AudioTrackOperation[] $values() {
            return new AudioTrackOperation[]{CREATE, PLAY, PAUSE, STOP, WRITE, FLUSH, SET_VOLUME, SET_PLAYBACK_PARAMS, SET_PLAYBACK_RATE, RELEASE};
        }

        static {
            AudioTrackOperation[] audioTrackOperationArr$values = $values();
            $VALUES = audioTrackOperationArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(audioTrackOperationArr$values);
        }

        public AudioTrackOperation(String str, int i) {
        }

        @NotNull
        public static EnumEntries<AudioTrackOperation> getEntries() {
            return $ENTRIES;
        }

        public static AudioTrackOperation valueOf(String str) {
            return (AudioTrackOperation) Enum.valueOf(AudioTrackOperation.class, str);
        }

        public static AudioTrackOperation[] values() {
            return (AudioTrackOperation[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @VisibleForTesting
        public final int parseAc3SyncframeAudioSampleCount(@NotNull ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            int iPosition = buffer.position();
            if (buffer.remaining() < 6) {
                throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Buffer too small for AC3/EAC3 header: ", buffer.remaining(), " bytes").toString());
            }
            if (((buffer.get(iPosition + 5) & 248) >> 3) <= 10) {
                return 1536;
            }
            int i = iPosition + 4;
            return AudioRenderer.BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[((buffer.get(i) & 192) >> 6) != 3 ? (buffer.get(i) & 48) >> 4 : 3] * 256;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public AudioRenderer(@NotNull AudioTrackFactory trackFactory, @NotNull MPBInternalConfig config, @NotNull MediaPipelineContext mediaPipelineContext, @NotNull AvSyncHeaderFormatter avSyncHeaderFormatter, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, boolean z, boolean z2) {
        super(mediaPipelineContext);
        Intrinsics.checkNotNullParameter(trackFactory, "trackFactory");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(avSyncHeaderFormatter, "avSyncHeaderFormatter");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        this.trackFactory = trackFactory;
        this.avSyncHeaderFormatter = avSyncHeaderFormatter;
        this.capabilityDetector = capabilityDetector;
        this.isPipelineAsynchronous = z;
        this.isVerboseMediaClockLoggingEnabled = z2;
        this.lock = new ReentrantReadWriteLock();
        this.isNonBlockingAudioRenderingEnabled = config.getNonBlockingAudioRenderingEnabled();
        this.isTunnelModeNewWriteAPIForAudioBufferEnabled = config.getTunnelModeNewAudioTrackWriteApiEnabled();
        this.shouldFlushBeforeAudioTrackStop = config.getFlushBeforeAudioTrackStop();
        this.shouldReportAVSyncStats = config.getReportAvSyncStats();
        this.backoffTimeForRetriesOnAudioTrackCreationFailureMillis = config.getBackoffTimeForRetriesOnAudioTrackCreationFailureMs();
        this.numTriesForAudioTrackCreationFailure = config.getNumTriesForAudioTrackCreationFailure();
        this.shouldRetryOnAudioTrackCreationFailure = config.getRetryOnAudioTrackCreationFailure();
        this.shouldValidateAudioTrackWriteResult = config.getValidateAudioTrackWriteResult();
        this.submitAudioBufferResult = new SubmitBufferResult(0, 0L, 3, null);
        this.pendingWriteExceptionHolder = new PendingExceptionHolder<>(config.getAudioTrackWriteFailureRetryDurationMs());
        this.desiredPlaybackSpeed = 1.0f;
        this.currentAudioTrackSpeed = 1.0f;
        this.timeSource = new TimeSource() { // from class: com.amazon.avod.mpb.media.playback.render.AudioRenderer$timeSource$1
            public volatile boolean hasStartedTicking;

            @Override // com.amazon.avod.mpb.media.playback.avsync.TimeSource
            public long getCurrentRealTimeUs() {
                long currentRealTimeUsInternal = getCurrentRealTimeUsInternal();
                if (this.this$0.isVerboseMediaClockLoggingEnabled) {
                    MpbLog.logf("AudioRenderer currentRealTimeMs: " + TimeUnit.MICROSECONDS.toMillis(currentRealTimeUsInternal), new Object[0]);
                }
                return currentRealTimeUsInternal;
            }

            public final long getCurrentRealTimeUsInternal() {
                this.this$0.lock.readLock().lock();
                AudioTrack audioTrack = this.this$0.audioTrack;
                if (audioTrack != null) {
                    try {
                        if (audioTrack.getPlayState() == 3) {
                            AudioTrackPositionTracker audioTrackPositionTracker = this.this$0.positionTracker;
                            Intrinsics.checkNotNull(audioTrackPositionTracker);
                            long currentPositionUs = audioTrackPositionTracker.getCurrentPositionUs();
                            updateTickingStatus(currentPositionUs);
                            return currentPositionUs;
                        }
                    } finally {
                        this.this$0.lock.readLock().unlock();
                    }
                }
                this.hasStartedTicking = false;
                this.this$0.lock.readLock().unlock();
                return 0L;
            }

            @Override // com.amazon.avod.mpb.media.playback.avsync.TimeSource
            public boolean hasStartedTicking() {
                return this.hasStartedTicking;
            }

            public final void updateTickingStatus(long j) {
                this.hasStartedTicking = j > 0;
            }
        };
        this.volumeGain = 1.0f;
        this.passthroughMimeType = "audio/raw";
    }

    public final void adjustAudioTrackSpeed() {
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack == null || this.currentAudioTrackSpeed == this.desiredPlaybackSpeed) {
            return;
        }
        AudioTrackPositionTracker audioTrackPositionTracker = this.positionTracker;
        Intrinsics.checkNotNull(audioTrackPositionTracker);
        audioTrackPositionTracker.setAudioTrackPlaybackSpeed(this.desiredPlaybackSpeed);
        if (Build.VERSION.SDK_INT < 23) {
            float sampleRate = audioTrack.getSampleRate();
            float f = this.desiredPlaybackSpeed;
            int i = (int) (sampleRate * f);
            logAudioTrackOperation(AudioTrackOperation.SET_PLAYBACK_RATE, "playbackSpeed: " + f + " sampleRate: " + i);
            int playbackRate = audioTrack.setPlaybackRate(i);
            if (playbackRate == 0) {
                this.currentAudioTrackSpeed = this.desiredPlaybackSpeed;
                return;
            }
            MpbLog.errorf("AudioRenderer:Failed to adjust AudioTrack's playback speed to " + this.desiredPlaybackSpeed + " due to " + playbackRate, new Object[0]);
            return;
        }
        PlaybackParams speed = DefaultAudioSink$$ExternalSyntheticApiModelOutline0.m().setSpeed(this.desiredPlaybackSpeed);
        try {
            logAudioTrackOperation(AudioTrackOperation.SET_PLAYBACK_PARAMS, "playbackSpeed: " + this.desiredPlaybackSpeed);
            audioTrack.setPlaybackParams(speed);
            this.currentAudioTrackSpeed = this.desiredPlaybackSpeed;
        } catch (IllegalArgumentException e) {
            MpbLog.exceptionf(e, "AudioRenderer: Failed to adjust AudioTrack's playback speed to " + this.desiredPlaybackSpeed + " due to: " + e, new Object[0]);
        } catch (IllegalStateException e2) {
            MpbLog.exceptionf(e2, "AudioRenderer: Failed to adjust AudioTrack's playback speed to " + this.desiredPlaybackSpeed + " due to: " + e2, new Object[0]);
        }
    }

    public final boolean canWrite(int i) {
        if (i == 1) {
            return true;
        }
        AudioTrack audioTrack = this.audioTrack;
        Intrinsics.checkNotNull(audioTrack);
        return audioTrack.getPlayState() == 3;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void configure(@NotNull MediaFormat mediaFormat, @Nullable Integer num, int i) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
        this.lock.writeLock().lock();
        try {
            configure$core_mpb_release();
            if (this.audioTrack != null) {
                throw new MediaPipelineBackendException("Attempted to configure AudioRenderer that was already configured", MediaPipelineBackendResultCode.AV_MPB_AUDIO_TRACK_REDUNDANT_CONFIGURE);
            }
            this.mediaFormat = mediaFormat;
            this.audioSessionId = num;
            this.audioBitrate = i;
            String string = mediaFormat.getString("mime");
            Intrinsics.checkNotNull(string);
            this.isPassThrough = isPassthroughSupported(string);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public final MediaPipelineBackendException createWriteException(int i, long j) {
        String str = "AudioTrack write failed with error code: " + i + ", presentationTimeUs: " + j;
        MpbLog.warnf(str, new Object[0]);
        return new MediaPipelineBackendException(str, MediaPipelineBackendResultCode.AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED);
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    @NotNull
    public MediaFormat estimateInitialOutputFormat(@NotNull MediaFormat inputFormat, boolean z) {
        Intrinsics.checkNotNullParameter(inputFormat, "inputFormat");
        if (z) {
            return inputFormat;
        }
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/raw", inputFormat.getInteger("sample-rate"), inputFormat.getInteger("channel-count"));
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateAudioFormat, "createAudioFormat(...)");
        return mediaFormatCreateAudioFormat;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void flush() throws MediaPipelineBackendException {
        if (!isIdle()) {
            throw new IllegalStateException("Can only call flush() when in the IDLE state");
        }
        this.lock.writeLock().lock();
        try {
            AudioTrack audioTrack = this.audioTrack;
            if (audioTrack != null) {
                logAudioTrackOperation(AudioTrackOperation.FLUSH);
                audioTrack.flush();
                this.totalFramesWritten = 0L;
                this.hasTriggeredReadyToPlay.set(false);
                this.mediaPipelineContext.onAudioRendererFlush();
                releaseAudioTrack();
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    @NotNull
    public String getPassthroughMimeType() {
        return this.passthroughMimeType;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    @NotNull
    public TimeSource getTimeSource() {
        return this.timeSource;
    }

    public final int getWriteMode() {
        if (!this.isPipelineAsynchronous && this.audioSessionId == null) {
            return this.isNonBlockingAudioRenderingEnabled ? 1 : 0;
        }
        return 0;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public boolean hasUnderrun() {
        this.lock.readLock().lock();
        try {
            AudioTrackPositionTracker audioTrackPositionTracker = this.positionTracker;
            if (this.audioTrack != null && isRunning() && audioTrackPositionTracker != null && this.outputSampleRate != 0) {
                long currentPositionUs = audioTrackPositionTracker.getCurrentPositionUs();
                long j = (((long) this.outputSampleRate) * currentPositionUs) / ((long) 1000000);
                long j2 = this.totalFramesWritten;
                long j3 = j2 - j;
                boolean z = j3 <= 0;
                if (this.isVerboseMediaClockLoggingEnabled) {
                    MpbLog.logf("AudioRenderer hasUnderrun: " + z + " written: " + j2 + " consumed: " + j + " buffered: " + j3 + " positionMs: " + TimeUnit.MICROSECONDS.toMillis(currentPositionUs) + StringUtils.SPACE, new Object[0]);
                }
                return z;
            }
            return false;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public final AudioTrack initAudioTrack(Integer num) throws MediaPipelineBackendException {
        AudioTrackFactory audioTrackFactory = this.trackFactory;
        MediaFormat mediaFormat = this.mediaFormat;
        Intrinsics.checkNotNull(mediaFormat);
        AudioTrackWrapper audioTrackWrapperNewAudioTrackWrapper = audioTrackFactory.newAudioTrackWrapper(mediaFormat, this.isPassThrough, num, this.audioBitrate);
        AudioTrack audioTrack = audioTrackWrapperNewAudioTrackWrapper.audioTrack;
        this.audioTrack = audioTrack;
        int state = audioTrack.getState();
        if (state != 1) {
            MpbLog.warnf(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("AudioRenderer: AudioTrack(", audioTrack.hashCode(), ") not in STATE_INITIALIZED after configuring, state ", state), new Object[0]);
            throw new MediaPipelineBackendException(ObjectListKt$$ExternalSyntheticOutline1.m("AudioRenderer: AudioTrack is in state ", state, " after configuring"), MediaPipelineBackendResultCode.AV_MPB_AUDIO_RENDERER_ERROR_TRACK_INIT_FAILED);
        }
        this.positionTracker = AudioTrackPositionTracker.Companion.create(audioTrack, this.isPassThrough, audioTrackWrapperNewAudioTrackWrapper.outputEncoding, audioTrackWrapperNewAudioTrackWrapper.channelCount, audioTrackWrapperNewAudioTrackWrapper.audioTrackBufferSizeBytes, this.isVerboseMediaClockLoggingEnabled);
        adjustAudioTrackSpeed();
        float f = this.volumeGain;
        if (f != 1.0f) {
            setVolume(f);
        }
        int sampleRate = audioTrack.getSampleRate();
        this.outputSampleRate = sampleRate;
        if (sampleRate <= 0) {
            throw new MediaPipelineBackendException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid sample rate: ", this.outputSampleRate), MediaPipelineBackendResultCode.AV_MPB_AUDIO_TRACK_INVALID_SAMPLE_RATE);
        }
        this.frameSize = audioTrackWrapperNewAudioTrackWrapper.channelCount * 2;
        this.totalFramesWritten = 0L;
        return audioTrack;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007f A[LOOP:0: B:43:0x0023->B:22:0x007f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0083 A[EDGE_INSN: B:45:0x0083->B:24:0x0083 BREAK  A[LOOP:0: B:43:0x0023->B:22:0x007f], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.media.AudioTrack initAudioTrackWithRetry() throws com.amazon.avod.mpb.api.core.MediaPipelineBackendException {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.render.AudioRenderer.initAudioTrackWithRetry():android.media.AudioTrack");
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public boolean isPassthroughSupported(@NotNull String mimeType) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return mimeType.equalsIgnoreCase("audio/eac3") && this.capabilityDetector.isDolbyPassthroughSupported();
    }

    public final void logAudioTrackOperation(AudioTrackOperation audioTrackOperation) {
        AudioTrack audioTrack = this.audioTrack;
        MpbLog.logf("AudioRenderer AudioTrack(" + (audioTrack != null ? audioTrack.hashCode() : 0) + ") operation: " + audioTrackOperation, new Object[0]);
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        this.lock.writeLock().lock();
        try {
            super.release();
            releaseAudioTrack();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public final void releaseAudioTrack() {
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack == null) {
            return;
        }
        try {
            if (audioTrack.getState() != 0 && audioTrack.getPlayState() != 1) {
                if (this.shouldFlushBeforeAudioTrackStop) {
                    logAudioTrackOperation(AudioTrackOperation.FLUSH);
                    audioTrack.flush();
                }
                logAudioTrackOperation(AudioTrackOperation.STOP);
                audioTrack.stop();
            }
        } catch (IllegalStateException e) {
            MpbLog.exceptionf(e, "failed to flush/stop AudioTrack", new Object[0]);
        }
        logAudioTrackOperation(AudioTrackOperation.RELEASE);
        this.trackFactory.releaseTrack(audioTrack);
        this.audioTrack = null;
        this.positionTracker = null;
        this.currentAudioTrackSpeed = 1.0f;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public boolean rendersToSurface() {
        return false;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void setPlaybackSpeed(@Positive float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Playback speed must be positive");
        }
        this.lock.writeLock().lock();
        try {
            MpbLog.logf("AudioRenderer setting desired AudioTrack playback speed to: " + f, new Object[0]);
            this.desiredPlaybackSpeed = f;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void setVolume(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("gain cannot be negative");
        }
        this.lock.writeLock().lock();
        try {
            this.volumeGain = f;
            AudioTrack audioTrack = this.audioTrack;
            if (audioTrack != null) {
                logAudioTrackOperation(AudioTrackOperation.SET_VOLUME, "gain: " + f);
                audioTrack.setVolume(f);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer, com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void start() throws MediaPipelineBackendException {
        this.lock.writeLock().lock();
        try {
            AudioTrack audioTrackInitAudioTrackWithRetry = this.audioTrack;
            if (audioTrackInitAudioTrackWithRetry == null) {
                audioTrackInitAudioTrackWithRetry = initAudioTrackWithRetry();
            }
            super.start();
            MpbLog.logf("AudioRenderer calling AudioTrackPositionTracker.start()", new Object[0]);
            AudioTrackPositionTracker audioTrackPositionTracker = this.positionTracker;
            Intrinsics.checkNotNull(audioTrackPositionTracker);
            audioTrackPositionTracker.start();
            logAudioTrackOperation(AudioTrackOperation.PLAY);
            audioTrackInitAudioTrackWithRetry.play();
            this.lock.writeLock().unlock();
        } catch (Throwable th) {
            this.lock.writeLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer, com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        this.lock.writeLock().lock();
        try {
            super.stop();
            AudioTrack audioTrack = this.audioTrack;
            if (audioTrack != null) {
                MpbLog.logf("AudioRenderer calling AudioTrackPositionTracker.stop()", new Object[0]);
                AudioTrackPositionTracker audioTrackPositionTracker = this.positionTracker;
                Intrinsics.checkNotNull(audioTrackPositionTracker);
                audioTrackPositionTracker.stop();
                logAudioTrackOperation(AudioTrackOperation.PAUSE);
                audioTrack.pause();
                this.pendingWriteExceptionHolder.pendingException = null;
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    @VisibleForTesting
    @NotNull
    public SubmitBufferResult submitBuffer(long j, long j2, boolean z, int i, int i2, int i3, @Nullable ByteBuffer byteBuffer, long j3) throws MediaPipelineBackendException {
        int i4;
        ByteBuffer byteBuffer2;
        int ac3SyncframeAudioSampleCount;
        long j4;
        this.lock.readLock().lock();
        int iWriteToAudioTrack = 0;
        try {
            if (byteBuffer == null) {
                MpbLog.errorf("Received null audio buffer with audioPlayheadUs=" + j + " presentationTimeUs=" + j2 + " isResuming=" + z + " size=" + i + " offset=" + i2 + " flags=" + i3 + " systemTimeAtStartOfLoopUs=" + j3, new Object[0]);
                throw new MediaPipelineBackendException("Received null audio output buffer", MediaPipelineBackendResultCode.AV_MPB_AUDIO_OUTPUT_BUFFER_NULL);
            }
            adjustAudioTrackSpeed();
            if (i2 == 0) {
                if (!this.isPassThrough) {
                    ac3SyncframeAudioSampleCount = i / this.frameSize;
                } else if ((i3 & 4) != 0) {
                    j4 = 0;
                    this.totalFramesWritten += j4;
                } else {
                    ac3SyncframeAudioSampleCount = Companion.parseAc3SyncframeAudioSampleCount(byteBuffer);
                }
                j4 = ac3SyncframeAudioSampleCount;
                this.totalFramesWritten += j4;
            }
            int writeMode = getWriteMode();
            if (isUnconfigured() || !canWrite(writeMode)) {
                i4 = i2;
                byteBuffer2 = byteBuffer;
            } else if (!shouldPlayWhenReady()) {
                if (!this.hasTriggeredReadyToPlay.getAndSet(true)) {
                    this.mediaPipelineContext.onAudioRendererReadyToPlay(j2);
                }
                i4 = i2;
                byteBuffer2 = byteBuffer;
            } else if (this.audioSessionId != null) {
                byteBuffer2 = byteBuffer;
                i4 = i2;
                iWriteToAudioTrack = writeToAudioTrackTunnelMode(byteBuffer, i, i2, j2);
            } else {
                i4 = i2;
                byteBuffer2 = byteBuffer;
                iWriteToAudioTrack = writeToAudioTrack(byteBuffer2, i, j2);
            }
            if (iWriteToAudioTrack > 0) {
                this.pendingWriteExceptionHolder.pendingException = null;
            }
            if (this.isVerboseMediaClockLoggingEnabled) {
                logAudioTrackOperation(AudioTrackOperation.WRITE, "bytesWritten: " + iWriteToAudioTrack + " sizeBytes: " + i + " offset: " + i4 + " ptsMs: " + TimeUnit.MICROSECONDS.toMillis(j2) + " writeMode: " + getWriteMode() + " hasRemaining: " + byteBuffer2.hasRemaining());
            }
            SubmitBufferResult submitBufferResult = this.submitAudioBufferResult;
            if (iWriteToAudioTrack >= i) {
                iWriteToAudioTrack = -2;
            }
            submitBufferResult.bytesRead = iWriteToAudioTrack;
            this.lock.readLock().unlock();
            return submitBufferResult;
        } catch (Throwable th) {
            this.lock.readLock().unlock();
            throw th;
        }
    }

    public final void validateAudioTrackWriteResult(int i, long j) throws MediaPipelineBackendException {
        if (i >= 0) {
            this.lastWriteFailedWithDeadObjectError = false;
            return;
        }
        if (i != -6) {
            if (this.shouldValidateAudioTrackWriteResult) {
                throw createWriteException(i, j);
            }
        } else {
            if (this.lastWriteFailedWithDeadObjectError) {
                throw createWriteException(i, j);
            }
            this.lastWriteFailedWithDeadObjectError = true;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: T */
    public final int writeToAudioTrack(ByteBuffer byteBuffer, int i, long j) throws IllegalAccessException, T, MediaPipelineBackendException, InvocationTargetException {
        try {
            AudioTrack audioTrack = this.audioTrack;
            Intrinsics.checkNotNull(audioTrack);
            int iWrite = audioTrack.write(byteBuffer, i, getWriteMode());
            validateAudioTrackWriteResult(iWrite, j);
            return iWrite;
        } catch (Exception e) {
            this.pendingWriteExceptionHolder.throwExceptionIfDeadlineIsReached(new MediaPipelineBackendException("AudioRenderer: Failed to write to AudioTrack", MediaPipelineBackendResultCode.AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED, e));
            return 0;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: T */
    public final int writeToAudioTrackTunnelMode(ByteBuffer byteBuffer, int i, int i2, long j) throws IllegalAccessException, T, MediaPipelineBackendException, InvocationTargetException {
        try {
            int writeMode = getWriteMode();
            if (this.isTunnelModeNewWriteAPIForAudioBufferEnabled && Build.VERSION.SDK_INT >= 23) {
                AudioTrack audioTrack = this.audioTrack;
                Intrinsics.checkNotNull(audioTrack);
                int iWrite = audioTrack.write(byteBuffer, i, writeMode, TimeUnit.MICROSECONDS.toNanos(j));
                validateAudioTrackWriteResult(iWrite, j);
                return iWrite;
            }
            if (i2 == 0) {
                this.avSyncHeaderFormatter.setAvSyncHeader(i, j);
                AudioTrack audioTrack2 = this.audioTrack;
                Intrinsics.checkNotNull(audioTrack2);
                AvSyncHeaderFormatter avSyncHeaderFormatter = this.avSyncHeaderFormatter;
                int iWrite2 = audioTrack2.write(avSyncHeaderFormatter.avSyncHeader, avSyncHeaderFormatter.avSyncHeaderSize, writeMode);
                validateAudioTrackWriteResult(iWrite2, j);
                if (this.shouldReportAVSyncStats && iWrite2 < this.avSyncHeaderFormatter.avSyncHeaderSize) {
                    this.mediaPipelineContext.rendererDebugTracker.updateDebugInfo("IncompleteHeader", "PTSUs:" + j + ", Bytes:" + iWrite2 + ";");
                }
            }
            AudioTrack audioTrack3 = this.audioTrack;
            Intrinsics.checkNotNull(audioTrack3);
            int iWrite3 = audioTrack3.write(byteBuffer, i, writeMode);
            validateAudioTrackWriteResult(iWrite3, j);
            return iWrite3;
        } catch (Exception e) {
            this.pendingWriteExceptionHolder.throwExceptionIfDeadlineIsReached(new MediaPipelineBackendException("AudioRenderer: Failed to write to AudioTrack", MediaPipelineBackendResultCode.AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED, e));
            return 0;
        }
    }

    public final void logAudioTrackOperation(AudioTrackOperation audioTrackOperation, String str) {
        AudioTrack audioTrack = this.audioTrack;
        MpbLog.logf("AudioRenderer AudioTrack(" + (audioTrack != null ? audioTrack.hashCode() : 0) + ") operation: " + audioTrackOperation + " note: " + str, new Object[0]);
    }

    @VisibleForTesting
    public static /* synthetic */ void getTimeSource$annotations() {
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AudioRenderer(@NotNull MediaPipelineContext mediaPipelineContext, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, boolean z) {
        this(AudioTrackFactory.instance, DefaultMPBInternalConfig.INSTANCE, mediaPipelineContext, new AvSyncHeaderFormatter(), capabilityDetector, z, mediaPipelineContext.devicePropertyProvider.isVerboseAvSyncLoggingEnabled());
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        AudioTrackFactory.Companion.getClass();
    }
}
