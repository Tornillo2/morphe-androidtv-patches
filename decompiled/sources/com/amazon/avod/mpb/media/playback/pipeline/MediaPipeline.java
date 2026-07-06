package com.amazon.avod.mpb.media.playback.pipeline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.view.Surface;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.RequiresApi;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.sample.BufferedMediaSample;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.drm.MediaCryptoSession;
import com.amazon.avod.mpb.media.playback.avsync.MediaClock;
import com.amazon.avod.mpb.media.playback.avsync.TimeSource;
import com.amazon.avod.mpb.media.playback.render.MediaRenderer;
import com.amazon.avod.mpb.media.playback.source.MediaSource;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.mpb.threading.Tickers;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import j$.util.concurrent.ConcurrentHashMap;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nMediaPipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaPipeline.kt\ncom/amazon/avod/mpb/media/playback/pipeline/MediaPipeline\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1190:1\n1143#1,5:1192\n1143#1,5:1197\n1143#1,5:1202\n1#2:1191\n*S KotlinDebug\n*F\n+ 1 MediaPipeline.kt\ncom/amazon/avod/mpb/media/playback/pipeline/MediaPipeline\n*L\n463#1:1192,5\n483#1:1197,5\n718#1:1202,5\n*E\n"})
public abstract class MediaPipeline extends MediaPipelineStateManager {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long FPS_SAMPLE_WINDOW_MILLIS = 1000;
    public static final long STANDARD_LOGGING_THRESHOLD = 20;
    public static final long VERBOSE_LOGGING_THRESHOLD = -1;

    @NotNull
    public final Context appContext;
    public final boolean areTunnelModePipelineStatsEnabled;
    public final int audioBitrate;

    @Nullable
    public final Integer audioSessionId;
    public int availableOutputBufferIndex;

    @NotNull
    public MediaCodec.BufferInfo availableOutputBufferInfo;

    @Nullable
    public MediaFormat cachedAudioOutputFormat;

    @NotNull
    public final MediaCodecDeviceCapabilityDetector capabilityDetector;

    @Nullable
    public MediaCodec codec;

    @NotNull
    public final MediaCodecFactory codecFactory;

    @NotNull
    public final Stopwatch drainTaskTimer;

    @NotNull
    public final Stopwatch feedTaskTimer;
    public final boolean handleAudioTrackDeadError;
    public boolean hasInputStreamEnded;

    @NotNull
    public final AtomicBoolean hasOutputStreamEnded;

    @NotNull
    public final AtomicBoolean hasTriggeredReadyToPlay;

    @Nullable
    public ByteBuffer[] inputBuffers;

    @Nullable
    public MediaFormat inputFormat;
    public final boolean isAudioPipeline;
    public boolean isFirstSamplePtsReported;
    public boolean isResuming;
    public final boolean isVerboseLoggingEnabled;
    public long lastDecodedNonOverlappingSamplePtsUs;
    public long lastDecodedPresentationTimeUs;
    public final int maxConcurrentSampleCount;

    @NotNull
    public final MediaClock mediaClock;

    @Nullable
    public MediaCryptoSession mediaCryptoSession;

    @NotNull
    public final MediaPipelineContext mediaPipelineContext;

    @Nullable
    public String mimeType;

    @NotNull
    public final Object mutex;

    @Nullable
    public ByteBuffer[] outputBuffers;

    @Nullable
    public MediaFormat passthroughMediaFormat;

    @NotNull
    public final Stopwatch pipelineTimer;

    @Nullable
    public final Integer profile;

    @Nullable
    public MediaRenderer renderer;

    @NotNull
    public final MediaRendererFactory rendererFactory;
    public final boolean shouldReportAVSyncStats;
    public final boolean shouldUseNewGetBufferApi;
    public final boolean shouldValidateVideoDecodingCadence;

    @NotNull
    public final MediaSource source;

    @Nullable
    public Surface surface;
    public final int tunnelModeDroppedFrameDetectionThresholdMs;

    @Nullable
    public TunnelModeStatistics tunnelModeStatistics;

    @NotNull
    public final Stopwatch videoDecodeCadenceStopwatch;
    public final long videoDecodingCadenceLoggingThresholdInMillis;
    public final long videoDecodingCadenceThresholdInMillis;
    public final long warningThresholdMillis;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @VisibleForTesting
        public final boolean shouldReconfigureRenderer(@Nullable MediaFormat mediaFormat, @Nullable MediaFormat mediaFormat2) {
            return (mediaFormat != null && mediaFormat.containsKey("channel-count") && mediaFormat.containsKey("sample-rate") && mediaFormat.containsKey("mime") && mediaFormat2 != null && mediaFormat2.containsKey("channel-count") && mediaFormat2.containsKey("sample-rate") && mediaFormat2.containsKey("mime") && mediaFormat.getInteger("channel-count") == mediaFormat2.getInteger("channel-count") && mediaFormat.getInteger("sample-rate") == mediaFormat2.getInteger("sample-rate") && Intrinsics.areEqual(mediaFormat.getString("mime"), mediaFormat2.getString("mime"))) ? false : true;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    @SuppressLint({"UseRequiresApi"})
    public final class TunnelModeStatistics implements MediaCodec.OnFrameRenderedListener {
        public int _totalDroppedFrames;
        public long fpsSampleStartUs;
        public float lastFpsSample;

        @NotNull
        public final ConcurrentHashMap<Long, Long> renderPendingVideoFrames;
        public int renderedFramesInCurrentSample;
        public final /* synthetic */ MediaPipeline this$0;

        @NotNull
        public final TimeSource timeSource;

        public TunnelModeStatistics(@NotNull MediaPipeline mediaPipeline, MediaCodec codec) {
            Intrinsics.checkNotNullParameter(codec, "codec");
            this.this$0 = mediaPipeline;
            this.timeSource = TimeSource.DEFAULT_INSTANCE;
            this.renderPendingVideoFrames = new ConcurrentHashMap<>();
            this.lastFpsSample = -1.0f;
            HandlerThread handlerThread = new HandlerThread(TunnelModeStatistics.class.getSimpleName());
            handlerThread.start();
            codec.setOnFrameRenderedListener(this, new Handler(handlerThread.getLooper()));
        }

        public final void clearPendingFrames() {
            this.renderPendingVideoFrames.clear();
        }

        public final int getTotalDroppedFrames() {
            long millis = TimeUnit.MICROSECONDS.toMillis(this.timeSource.getCurrentRealTimeUs());
            for (Map.Entry<Long, Long> entry : this.renderPendingVideoFrames.entrySet()) {
                Intrinsics.checkNotNullExpressionValue(entry, "next(...)");
                Map.Entry<Long, Long> entry2 = entry;
                Long value = entry2.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
                long jLongValue = value.longValue();
                if (millis - jLongValue > this.this$0.tunnelModeDroppedFrameDetectionThresholdMs) {
                    Long key = entry2.getKey();
                    Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
                    long jLongValue2 = key.longValue();
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(this.this$0.tunnelModeDroppedFrameDetectionThresholdMs);
                    StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("MediaPipeline detected DROP frame pts ", jLongValue2, " ms, submitted to codec at ");
                    sbM.append(jLongValue);
                    sbM.append(" ms, not rendered for > ");
                    sbM.append(seconds);
                    sbM.append(" sec");
                    MpbLog.logf(sbM.toString(), new Object[0]);
                    this._totalDroppedFrames++;
                    this.renderPendingVideoFrames.remove(Long.valueOf(jLongValue2));
                }
            }
            return this._totalDroppedFrames;
        }

        @Override // android.media.MediaCodec.OnFrameRenderedListener
        public void onFrameRendered(@NotNull MediaCodec codec, long j, long j2) {
            Intrinsics.checkNotNullParameter(codec, "codec");
            this.this$0.handlePictureAspectRatioChange();
            this.renderedFramesInCurrentSample++;
            long currentRealTimeUs = this.timeSource.getCurrentRealTimeUs();
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            long millis = timeUnit.toMillis(currentRealTimeUs - this.fpsSampleStartUs);
            if (millis >= 1000) {
                this.lastFpsSample = (this.renderedFramesInCurrentSample * TimeUnit.SECONDS.toMillis(1L)) / millis;
                this.renderedFramesInCurrentSample = 0;
                this.fpsSampleStartUs = currentRealTimeUs;
            }
            long millis2 = timeUnit.toMillis(j);
            this.renderPendingVideoFrames.remove(Long.valueOf(millis2));
            MediaPipeline mediaPipeline = this.this$0;
            if (mediaPipeline.isResuming) {
                mediaPipeline.isResuming = false;
                for (Long l : this.renderPendingVideoFrames.keySet()) {
                    Intrinsics.checkNotNullExpressionValue(l, "next(...)");
                    long jLongValue = l.longValue();
                    if (jLongValue < millis2) {
                        MpbLog.logf(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(AbstractResolvableFuture$$ExternalSyntheticOutline2.m("MediaPipeline detected SKIP frame pts ", jLongValue, " ms, first frame rendered on resume "), millis2, " ms"), new Object[0]);
                        this.renderPendingVideoFrames.remove(Long.valueOf(jLongValue));
                    }
                }
            }
        }

        public final void onFrameSubmittedToCodec(long j) {
            ConcurrentHashMap<Long, Long> concurrentHashMap = this.renderPendingVideoFrames;
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            concurrentHashMap.put(Long.valueOf(timeUnit.toMillis(j)), Long.valueOf(timeUnit.toMillis(this.timeSource.getCurrentRealTimeUs())));
        }
    }

    public MediaPipeline(@NotNull MediaSource source, @NotNull MediaCodecFactory codecFactory, @NotNull MediaRendererFactory rendererFactory, @NotNull Context appContext, @NotNull MediaClock mediaClock, @NotNull MediaPipelineContext mediaPipelineContext, @Nullable Integer num, boolean z, @NotNull MPBInternalConfig config, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @Nullable Integer num2, int i) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(codecFactory, "codecFactory");
        Intrinsics.checkNotNullParameter(rendererFactory, "rendererFactory");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaClock, "mediaClock");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        this.source = source;
        this.codecFactory = codecFactory;
        this.rendererFactory = rendererFactory;
        this.appContext = appContext;
        this.mediaClock = mediaClock;
        this.mediaPipelineContext = mediaPipelineContext;
        this.audioSessionId = num;
        this.isAudioPipeline = z;
        this.capabilityDetector = capabilityDetector;
        this.profile = num2;
        this.audioBitrate = i;
        this.shouldUseNewGetBufferApi = mediaPipelineContext.devicePropertyProvider.isNewGetBufferApiEnabled();
        boolean zIsVerboseAvSyncLoggingEnabled = mediaPipelineContext.devicePropertyProvider.isVerboseAvSyncLoggingEnabled();
        this.isVerboseLoggingEnabled = zIsVerboseAvSyncLoggingEnabled;
        this.shouldReportAVSyncStats = config.getReportAvSyncStats();
        this.maxConcurrentSampleCount = config.getMaxConcurrentSampleCount();
        this.shouldValidateVideoDecodingCadence = config.getValidateVideoDecodingCadence();
        this.videoDecodingCadenceThresholdInMillis = config.getVideoDecodingCadenceThresholdMs();
        this.videoDecodingCadenceLoggingThresholdInMillis = config.getVideoDecodingCadenceLoggingThresholdMs();
        this.handleAudioTrackDeadError = config.getHandleAudioTrackDeadError();
        this.areTunnelModePipelineStatsEnabled = config.getTunnelModePipelineStatsEnabled();
        this.tunnelModeDroppedFrameDetectionThresholdMs = config.getTunnelModeDroppedFrameDetectionThresholdMs();
        this.mutex = new Object();
        this.pipelineTimer = new Stopwatch(Tickers.androidTicker());
        Ticker ticker = Tickers.ANDROID_TICKER;
        this.feedTaskTimer = new Stopwatch(ticker);
        this.drainTaskTimer = new Stopwatch(ticker);
        this.videoDecodeCadenceStopwatch = new Stopwatch(ticker);
        this.hasOutputStreamEnded = new AtomicBoolean(false);
        this.warningThresholdMillis = zIsVerboseAvSyncLoggingEnabled ? -1L : 20L;
        this.hasTriggeredReadyToPlay = new AtomicBoolean(false);
        this.availableOutputBufferIndex = -1;
        this.availableOutputBufferInfo = new MediaCodec.BufferInfo();
        this.lastDecodedPresentationTimeUs = -1L;
        this.lastDecodedNonOverlappingSamplePtsUs = -1L;
    }

    public final void checkUnderrun() {
        if (this.source.hasUnderrun()) {
            if (!this.isAudioPipeline) {
                this.source.onPipelineUnderrun();
                this.mediaPipelineContext.onVideoBufferUnderrun();
                return;
            }
            MediaRenderer mediaRenderer = this.renderer;
            Intrinsics.checkNotNull(mediaRenderer);
            if (mediaRenderer.hasUnderrun()) {
                this.source.onPipelineUnderrun();
                this.mediaPipelineContext.onAudioBufferUnderrun();
            }
        }
    }

    public final void clearAvailableOutputBufferInfo() {
        this.availableOutputBufferIndex = -1;
        this.availableOutputBufferInfo.set(0, 0, 0L, 0);
    }

    public final void configure(@NotNull MediaFormat mediaFormat, @Nullable Surface surface, @Nullable MediaCryptoSession mediaCryptoSession) throws MediaPipelineBackendException {
        MediaFormat format = mediaFormat;
        Intrinsics.checkNotNullParameter(format, "format");
        synchronized (this.mutex) {
            if (!isUnconfigured()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring configure() in " + this.state + " state", new Object[0]);
                return;
            }
            configure$core_mpb_release();
            this.inputFormat = format;
            String string = format.getString("mime");
            Intrinsics.checkNotNull(string);
            this.mimeType = string;
            MediaRenderer mediaRendererNewMediaRenderer = this.rendererFactory.newMediaRenderer(string, this.appContext, this.mediaPipelineContext, this.capabilityDetector, isAsynchronous());
            this.renderer = mediaRendererNewMediaRenderer;
            if (mediaRendererNewMediaRenderer.isPassthroughSupported(string)) {
                MpbLog.logf("Passthrough is supported for the mimeType: ".concat(string), new Object[0]);
                this.passthroughMediaFormat = format;
                format.setString("mime", mediaRendererNewMediaRenderer.getPassthroughMimeType());
            }
            this.mediaCryptoSession = mediaCryptoSession;
            MediaCrypto mediaCrypto = mediaCryptoSession != null ? mediaCryptoSession.getMediaCrypto(this.isAudioPipeline) : null;
            Stopwatch stopwatch = this.pipelineTimer;
            stopwatch.reset();
            stopwatch.start();
            MediaCodec mediaCodecCreateCodec = this.codecFactory.createCodec(format, mediaCrypto, this.profile, this.isAudioPipeline);
            this.codec = mediaCodecCreateCodec;
            Stopwatch stopwatch2 = this.pipelineTimer;
            stopwatch2.stop();
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            logOperation("MediaCodec.init", stopwatch2.elapsed(timeUnit));
            Surface surface2 = mediaRendererNewMediaRenderer.rendersToSurface() ? surface : null;
            if (surface2 != null) {
                try {
                    try {
                        if (!surface2.isValid()) {
                            MpbLog.logf("Invalid surface is detected before codec configuration", new Object[0]);
                            throw new MediaPipelineBackendException("Surface already released", MediaPipelineBackendResultCode.AV_MPB_NULL_OR_INVALID_VIDEO_SURFACE);
                        }
                    } catch (MediaPipelineBackendException e) {
                        throw e;
                    }
                } catch (Exception e2) {
                    this.codecFactory.resetAllCodecs(e2);
                    throw new MediaPipelineBackendException("MediaCodec for " + string + " threw exception during configuration, Surface: " + (surface2 == null ? AbstractJsonLexerKt.NULL : surface2.isValid() ? "valid" : "invalid") + ", exception: " + e2, this.isAudioPipeline ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECODER_ERROR : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECODER_ERROR, e2);
                }
            }
            configureTunnelPlaybackFeature(mediaFormat);
            configureCallbacks();
            MpbLog.logf("MediaCodec configuring with format: " + format + ", surface: " + surface2 + ", crypto " + mediaCrypto, new Object[0]);
            Stopwatch stopwatch3 = this.pipelineTimer;
            stopwatch3.reset();
            stopwatch3.start();
            mediaCodecCreateCodec.configure(format, surface2, mediaCrypto, 0);
            Stopwatch stopwatch4 = this.pipelineTimer;
            stopwatch4.stop();
            logOperation("MediaCodec.configure", stopwatch4.elapsed(timeUnit));
            this.surface = surface2;
            Stopwatch stopwatch5 = this.pipelineTimer;
            stopwatch5.reset();
            stopwatch5.start();
            mediaCodecCreateCodec.start();
            Stopwatch stopwatch6 = this.pipelineTimer;
            stopwatch6.stop();
            logOperation("MediaCodec.start", stopwatch6.elapsed(timeUnit));
            if (!this.shouldUseNewGetBufferApi && !isAsynchronous()) {
                this.inputBuffers = mediaCodecCreateCodec.getInputBuffers();
                this.outputBuffers = mediaCodecCreateCodec.getOutputBuffers();
            }
            MediaFormat mediaFormat2 = this.passthroughMediaFormat;
            if (mediaFormat2 != null) {
                mediaFormat2.setString("mime", string);
            }
            boolean z = mediaFormat2 != null;
            if (z) {
                format = mediaFormat2;
            }
            configureRendererIfRequired(mediaRendererNewMediaRenderer.estimateInitialOutputFormat(format, z));
        }
    }

    public abstract void configureCallbacks();

    public final void configureRendererIfRequired(MediaFormat mediaFormat) throws MediaPipelineBackendException {
        MediaRenderer mediaRenderer = this.renderer;
        Intrinsics.checkNotNull(mediaRenderer);
        boolean zIsRunning = mediaRenderer.isRunning();
        if (zIsRunning && this.isAudioPipeline && Companion.shouldReconfigureRenderer(this.cachedAudioOutputFormat, mediaFormat)) {
            mediaRenderer.stop();
            mediaRenderer.release();
        }
        if (mediaRenderer.isUnconfigured()) {
            mediaRenderer.configure(mediaFormat, this.audioSessionId, this.audioBitrate);
            if (zIsRunning) {
                mediaRenderer.start();
            }
        }
        if (this.isAudioPipeline) {
            this.cachedAudioOutputFormat = mediaFormat;
        } else {
            handlePictureAspectRatioChange();
        }
    }

    public final void configureTunnelPlaybackFeature(MediaFormat mediaFormat) {
        Integer num;
        if (this.isAudioPipeline || (num = this.audioSessionId) == null) {
            return;
        }
        MpbLog.logf("Configuring video MediaPipeline for tunneled playback, audioSessionId " + num, new Object[0]);
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", this.audioSessionId.intValue());
        if (!this.areTunnelModePipelineStatsEnabled || Build.VERSION.SDK_INT < 23) {
            return;
        }
        MediaCodec mediaCodec = this.codec;
        Intrinsics.checkNotNull(mediaCodec);
        this.tunnelModeStatistics = new TunnelModeStatistics(this, mediaCodec);
    }

    public abstract void executePipelineTask(@NotNull PipelineTaskType pipelineTaskType) throws MediaPipelineBackendException;

    public final void feedEndOfStreamFlag(int i) {
        if (i < 0) {
            throw new IllegalStateException("Check failed.");
        }
        Stopwatch stopwatch = this.feedTaskTimer;
        stopwatch.reset();
        stopwatch.start();
        MediaCodec mediaCodec = this.codec;
        Intrinsics.checkNotNull(mediaCodec);
        mediaCodec.queueInputBuffer(i, 0, 0, 0L, 4);
        Stopwatch stopwatch2 = this.feedTaskTimer;
        stopwatch2.stop();
        long jElapsed = stopwatch2.elapsed(TimeUnit.MILLISECONDS);
        if (jElapsed > this.warningThresholdMillis) {
            logOperationExceededThreshold("MediaCodec.queueInputBuffer", jElapsed);
        }
        MpbLog.logf("Pipeline for " + this.inputFormat + " read end of stream from source", new Object[0]);
        this.hasInputStreamEnded = true;
    }

    public final void feedInputBuffer(int i) throws MediaPipelineBackendException {
        ByteBuffer inputBuffer;
        int i2;
        MediaCodec.CryptoInfo cryptoInfo;
        if (i < 0) {
            throw new IllegalStateException("Check failed.");
        }
        MediaCodec mediaCodec = this.codec;
        Intrinsics.checkNotNull(mediaCodec);
        if (this.shouldUseNewGetBufferApi || isAsynchronous()) {
            inputBuffer = mediaCodec.getInputBuffer(i);
        } else {
            ByteBuffer[] byteBufferArr = this.inputBuffers;
            Intrinsics.checkNotNull(byteBufferArr);
            inputBuffer = byteBufferArr[i];
        }
        if (inputBuffer == null) {
            MpbLog.warnf("MediaCodec for " + this.mimeType + " feed input buffer was unexpectedly null, index: " + i, new Object[0]);
            return;
        }
        BufferedMediaSample sampleData = this.source.readSampleData(inputBuffer);
        byte[] bArr = sampleData.codecData;
        int size = ((int) sampleData.mediaSample.getSize()) + (bArr != null ? bArr.length : 0);
        MediaCryptoSession mediaCryptoSession = this.mediaCryptoSession;
        MediaCodec.CryptoInfo cryptoInfoGenerateCryptoInfo = mediaCryptoSession != null ? mediaCryptoSession.generateCryptoInfo(sampleData) : null;
        Stopwatch stopwatch = this.feedTaskTimer;
        stopwatch.reset();
        stopwatch.start();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long micros = timeUnit.toMicros(sampleData.mediaSample.getPtsMs());
        try {
            if (cryptoInfoGenerateCryptoInfo == null) {
                mediaCodec.queueInputBuffer(i, 0, size, micros, sampleData.flags);
                i2 = i;
                cryptoInfo = cryptoInfoGenerateCryptoInfo;
            } else {
                i2 = i;
                cryptoInfo = cryptoInfoGenerateCryptoInfo;
                mediaCodec.queueSecureInputBuffer(i2, 0, cryptoInfo, micros, sampleData.flags);
            }
            if (this.isVerboseLoggingEnabled) {
                MpbLog.logf("MediaPipeline for " + this.inputFormat + " queued input buffer index: " + i2 + " ptsMs: " + sampleData.mediaSample.getPtsMs() + " flags: " + sampleData.flags, new Object[0]);
            }
            Stopwatch stopwatch2 = this.feedTaskTimer;
            stopwatch2.stop();
            long jElapsed = stopwatch2.elapsed(timeUnit);
            if (jElapsed > this.warningThresholdMillis) {
                logOperationExceededThreshold(cryptoInfo == null ? MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("MediaCodec.queueInputBuffer index ", i2) : MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("MediaCodec.queueSecureInputBuffer index ", i2), jElapsed);
            }
            if (!this.isAudioPipeline && this.audioSessionId != null && !this.hasTriggeredReadyToPlay.getAndSet(true)) {
                this.mediaPipelineContext.onVideoRendererReadyToPlay(micros);
            }
            if (!this.isAudioPipeline) {
                Stopwatch stopwatch3 = this.videoDecodeCadenceStopwatch;
                stopwatch3.reset();
                stopwatch3.start();
            }
            if (this.shouldReportAVSyncStats && !this.isFirstSamplePtsReported) {
                this.mediaPipelineContext.rendererDebugTracker.updateFirstSampleInStreamPTS(this.isAudioPipeline, micros);
                this.isFirstSamplePtsReported = true;
            }
            this.source.advance();
            TunnelModeStatistics tunnelModeStatistics = this.tunnelModeStatistics;
            if (tunnelModeStatistics != null) {
                tunnelModeStatistics.onFrameSubmittedToCodec(micros);
            }
        } catch (MediaCodec.CryptoException e) {
            String str = "Decryption failed because of the following CryptoException errorCode:" + e.getErrorCode() + ", full:" + e;
            int errorCode = e.getErrorCode();
            throw new MediaPipelineBackendException(str, errorCode != 1 ? errorCode != 2 ? errorCode != 4 ? errorCode != 6 ? this.isAudioPipeline ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECRYPTOR_ERROR : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECRYPTOR_ERROR : this.isAudioPipeline ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION : MediaPipelineBackendResultCode.AV_MPB_INSUFFICIENT_OUTPUT_PROTECTION : this.isAudioPipeline ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECRYPTOR_ERROR_EXPIRED_KEY : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECRYPTOR_ERROR_EXPIRED_KEY : this.isAudioPipeline ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECRYPTOR_ERROR_MISSING_KEY : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECRYPTOR_ERROR_MISSING_KEY);
        }
    }

    public void flush() throws MediaPipelineBackendException {
        synchronized (this.mutex) {
            if (!isIdle()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring flush() in " + this.state + " state", new Object[0]);
                return;
            }
            clearAvailableOutputBufferInfo();
            this.lastDecodedPresentationTimeUs = -1L;
            TunnelModeStatistics tunnelModeStatistics = this.tunnelModeStatistics;
            if (tunnelModeStatistics != null) {
                tunnelModeStatistics.clearPendingFrames();
            }
            MediaRenderer mediaRenderer = this.renderer;
            Intrinsics.checkNotNull(mediaRenderer);
            if (mediaRenderer.isIdle()) {
                mediaRenderer.flush();
            }
            MediaCodec mediaCodec = this.codec;
            if (mediaCodec != null) {
                Stopwatch stopwatch = this.pipelineTimer;
                stopwatch.reset();
                stopwatch.start();
                try {
                    mediaCodec.flush();
                } catch (IllegalStateException e) {
                    MpbLog.warnf(e, "MediaCodec for " + this.mimeType + " (state = " + this.state + ") threw unexpected IllegalStateException calling flush(): " + e.getMessage(), new Object[0]);
                }
                Stopwatch stopwatch2 = this.pipelineTimer;
                stopwatch2.stop();
                logOperation("MediaCodec.flush", stopwatch2.elapsed(TimeUnit.MILLISECONDS));
            }
            this.mediaPipelineContext.rendererDebugTracker.clearPTS();
            this.isFirstSamplePtsReported = false;
            this.lastDecodedNonOverlappingSamplePtsUs = -1L;
            this.hasTriggeredReadyToPlay.set(false);
        }
    }

    @Nullable
    public final Integer getAudioSessionId() {
        return this.audioSessionId;
    }

    public final int getAvailableOutputBufferIndex() {
        return this.availableOutputBufferIndex;
    }

    @NotNull
    public final MediaCodec.BufferInfo getAvailableOutputBufferInfo() {
        return this.availableOutputBufferInfo;
    }

    @Nullable
    public final MediaCodec getCodec() {
        return this.codec;
    }

    @NotNull
    public final Stopwatch getDrainTaskTimer() {
        return this.drainTaskTimer;
    }

    @NotNull
    public final Stopwatch getFeedTaskTimer() {
        return this.feedTaskTimer;
    }

    public final boolean getHasInputStreamEnded() {
        return this.hasInputStreamEnded;
    }

    @NotNull
    public final AtomicBoolean getHasOutputStreamEnded() {
        return this.hasOutputStreamEnded;
    }

    @Nullable
    public final MediaFormat getInputFormat() {
        return this.inputFormat;
    }

    public final long getLastDecodedPresentationTimeUs() {
        return this.lastDecodedPresentationTimeUs;
    }

    public final int getMaxConcurrentSampleCount() {
        return this.maxConcurrentSampleCount;
    }

    @NotNull
    public final MediaClock getMediaClock() {
        return this.mediaClock;
    }

    @NotNull
    public final MediaPipelineContext getMediaPipelineContext() {
        return this.mediaPipelineContext;
    }

    @Nullable
    public final String getMimeType() {
        return this.mimeType;
    }

    @NotNull
    public final Object getMutex() {
        return this.mutex;
    }

    @Nullable
    public final ByteBuffer[] getOutputBuffers() {
        return this.outputBuffers;
    }

    @NotNull
    public final Stopwatch getPipelineTimer() {
        return this.pipelineTimer;
    }

    @Nullable
    public final MediaRenderer getRenderer() {
        return this.renderer;
    }

    public final boolean getShouldUseNewGetBufferApi() {
        return this.shouldUseNewGetBufferApi;
    }

    public final boolean getShouldValidateVideoDecodingCadence() {
        return this.shouldValidateVideoDecodingCadence;
    }

    @NotNull
    public final MediaSource getSource() {
        return this.source;
    }

    @Nullable
    public final Surface getSurface() {
        return this.surface;
    }

    @Nullable
    public final TimeSource getTimeSource() {
        TimeSource timeSource;
        synchronized (this.mutex) {
            if (isUnconfigured()) {
                throw new IllegalStateException("Cannot getTimeSource() while in UNCONFIGURED state");
            }
            MediaRenderer mediaRenderer = this.renderer;
            Intrinsics.checkNotNull(mediaRenderer);
            timeSource = mediaRenderer.getTimeSource();
        }
        return timeSource;
    }

    public final int getTotalDroppedFrames() {
        if (isUnconfigured()) {
            MpbLog.warnf("Cannot getTotalDroppedFrames() while in UNCONFIGURED state, returning 0", new Object[0]);
            return 0;
        }
        TunnelModeStatistics tunnelModeStatistics = this.tunnelModeStatistics;
        if (tunnelModeStatistics != null) {
            return tunnelModeStatistics.getTotalDroppedFrames();
        }
        MediaRenderer mediaRenderer = this.renderer;
        Intrinsics.checkNotNull(mediaRenderer);
        return mediaRenderer.totalDroppedFrames;
    }

    @NotNull
    public final Stopwatch getVideoDecodeCadenceStopwatch() {
        return this.videoDecodeCadenceStopwatch;
    }

    public final long getWarningThresholdMillis() {
        return this.warningThresholdMillis;
    }

    public final void handleAudioTrackDeadError(long j) throws MediaPipelineBackendException {
        if (!this.handleAudioTrackDeadError) {
            throw new MediaPipelineBackendException("Audio track dead object detected", MediaPipelineBackendResultCode.AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED);
        }
        MpbLog.warnf("Pipeline for " + this.inputFormat + " encountered AudioTrack dead object error @ ptsUs: " + j + ", recreating audio renderer", new Object[0]);
        MediaRenderer mediaRenderer = this.renderer;
        Intrinsics.checkNotNull(mediaRenderer);
        boolean zIsRunning = mediaRenderer.isRunning();
        if (zIsRunning) {
            mediaRenderer.stop();
        }
        mediaRenderer.flush();
        this.mediaClock.start(j);
        if (zIsRunning) {
            mediaRenderer.start();
        }
    }

    public final void handleCodecException(@NotNull MediaCodec.CodecException e, @NotNull String context) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.mediaCryptoSession == null) {
            logUnexpectedIllegalStateException(e, context);
            return;
        }
        MpbLog.exceptionf(e, "MediaCodec exception ".concat(context), new Object[0]);
        throw new MediaPipelineBackendException("MediaCodec internal codec error (" + context + "): " + e.getMessage(), MediaPipelineBackendResultCode.DRM_SYSTEM_RESET_REQUIRED, e);
    }

    public final void handleEndOfStream() {
        this.hasOutputStreamEnded.set(true);
        if (this.isAudioPipeline) {
            this.mediaPipelineContext.onAudioEndOfStream();
        } else {
            this.mediaPipelineContext.onVideoEndOfStream();
        }
    }

    public final void handleOutputFormatChanged(@NotNull MediaFormat mediaFormat) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
        MediaFormat mediaFormat2 = this.passthroughMediaFormat;
        if (mediaFormat2 != null) {
            mediaFormat = mediaFormat2;
        }
        Intrinsics.checkNotNull(mediaFormat);
        MpbLog.logf("MediaCodec for " + this.inputFormat + " indicates output format changed to " + mediaFormat, new Object[0]);
        configureRendererIfRequired(mediaFormat);
    }

    public final void handlePictureAspectRatioChange() {
        this.mediaPipelineContext.changeVideoSizeIfRequired(this.lastDecodedPresentationTimeUs);
    }

    @ChecksSdkIntAtLeast(api = 23)
    public final void ifTunnelModeStatisticsAvailable(Function1<? super TunnelModeStatistics, Unit> function1) {
        TunnelModeStatistics tunnelModeStatistics = this.tunnelModeStatistics;
        if (tunnelModeStatistics != null) {
            function1.invoke(tunnelModeStatistics);
        }
    }

    public abstract boolean isAsynchronous();

    public final boolean isAudioPipeline() {
        return this.isAudioPipeline;
    }

    public final boolean isResuming() {
        return this.isResuming;
    }

    public final boolean isSampleOverlapped(long j) {
        if (!this.isAudioPipeline) {
            long j2 = this.lastDecodedNonOverlappingSamplePtsUs;
            if (j < j2) {
                long j3 = j - j2;
                StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("Decoded sample ptsUs ", j, " overlaps with last decoded sample ptsUs ");
                sbM.append(j2);
                sbM.append(", diffUs ");
                sbM.append(j3);
                MpbLog.devf(sbM.toString(), new Object[0]);
                return true;
            }
        }
        this.lastDecodedNonOverlappingSamplePtsUs = j;
        return false;
    }

    public final boolean isVerboseLoggingEnabled() {
        return this.isVerboseLoggingEnabled;
    }

    public final void logOperation(@NotNull String operation, long j) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        MpbLog.logf("Operation " + operation + " took " + j + " ms (type=" + this.mimeType + ")", new Object[0]);
    }

    public final void logOperationExceededThreshold(@NotNull String operation, long j) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        long j2 = this.warningThresholdMillis;
        String str = this.mimeType;
        StringBuilder sb = new StringBuilder("Operation ");
        sb.append(operation);
        sb.append(" exceeded threshold (expected=");
        sb.append(j2);
        sb.append(" ms, actual=");
        sb.append(j);
        sb.append(" ms type=");
        MpbLog.warnf(ActivityCompat$$ExternalSyntheticOutline0.m(sb, str, ")"), new Object[0]);
    }

    public final void logUnexpectedIllegalStateException(@NotNull IllegalStateException e, @NotNull String context) {
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(context, "context");
        MpbLog.warnf(e, "MediaCodec for " + this.inputFormat + " (state = " + this.state + ") threw unexpected IllegalStateException " + context + ": " + e.getMessage(), new Object[0]);
    }

    public final void notifySurfaceDestroyed() {
        synchronized (this.mutex) {
            if (this.isAudioPipeline) {
                throw new IllegalStateException("Only video pipeline can handle notifySurfaceDestroyed()");
            }
            MpbLog.warnf("MediaPipeline " + this.inputFormat + " notifySurfaceDestroyed() deactivating pipeline.", new Object[0]);
            stop();
            release();
        }
    }

    public final void notifySurfaceRecreated(@NotNull MediaFormat inputFormat, @NotNull Surface surface, @Nullable MediaCryptoSession mediaCryptoSession) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(inputFormat, "inputFormat");
        Intrinsics.checkNotNullParameter(surface, "surface");
        synchronized (this.mutex) {
            if (this.isAudioPipeline) {
                throw new IllegalStateException("Only video pipeline can handle notifySurfaceRecreated()");
            }
            MpbLog.warnf("MediaPipeline " + inputFormat + " notifySurfaceRecreated() recreating pipeline with new surface", new Object[0]);
            configure(inputFormat, surface, mediaCryptoSession);
            start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0099 A[Catch: all -> 0x0038, TRY_LEAVE, TryCatch #2 {, blocks: (B:4:0x000d, B:6:0x0014, B:11:0x003b, B:13:0x0043, B:15:0x0049, B:17:0x004f, B:21:0x0090, B:20:0x006c, B:22:0x0095, B:24:0x0099, B:25:0x00a1, B:29:0x00cf, B:30:0x00e7, B:34:0x0117, B:33:0x00ee, B:28:0x00a6, B:35:0x0129), top: B:44:0x000d, inners: #0, #1, #3 }] */
    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void release() {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline.release():void");
    }

    public final void setAvailableOutputBufferIndex(int i) {
        this.availableOutputBufferIndex = i;
    }

    public final void setAvailableOutputBufferInfo(@NotNull MediaCodec.BufferInfo bufferInfo) {
        Intrinsics.checkNotNullParameter(bufferInfo, "<set-?>");
        this.availableOutputBufferInfo = bufferInfo;
    }

    public final void setCodec(@Nullable MediaCodec mediaCodec) {
        this.codec = mediaCodec;
    }

    public final void setHasInputStreamEnded(boolean z) {
        this.hasInputStreamEnded = z;
    }

    public final void setInputFormat(@Nullable MediaFormat mediaFormat) {
        this.inputFormat = mediaFormat;
    }

    public final void setLastDecodedPresentationTimeUs(long j) {
        this.lastDecodedPresentationTimeUs = j;
    }

    public final void setMimeType(@Nullable String str) {
        this.mimeType = str;
    }

    public final void setOutputBuffers(@Nullable ByteBuffer[] byteBufferArr) {
        this.outputBuffers = byteBufferArr;
    }

    @RequiresApi(23)
    public final void setOutputSurface(@NotNull Surface surface) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(surface, "surface");
        synchronized (this.mutex) {
            if (this.isAudioPipeline) {
                throw new IllegalStateException("Only video pipeline can handle setOutputSurface()");
            }
            if (!canSetOutputSurface()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring setOutputSurface() in " + this.state + " state", new Object[0]);
                return;
            }
            try {
                MpbLog.logf("MediaPipeline " + this.inputFormat + " setOutputSurface() switching output surface to " + surface, new Object[0]);
                MediaCodec mediaCodec = this.codec;
                Intrinsics.checkNotNull(mediaCodec);
                mediaCodec.setOutputSurface(surface);
            } catch (Exception e) {
                String str = "MediaPipeline " + this.mimeType + " setOutputSurface() failed state " + this.state + " surface " + surface + " error " + e;
                MpbLog.warnf(e, str, new Object[0]);
                throw new MediaPipelineBackendException(str, MediaPipelineBackendResultCode.AV_MPB_SET_VIDEO_OUTPUT_SURFACE_ERROR, e);
            }
        }
    }

    public final void setPlayWhenReady() {
        synchronized (this.mutex) {
            MpbLog.logf("MediaPipeline " + this.mimeType + " setPlayWhenReady", new Object[0]);
            MediaRenderer mediaRenderer = this.renderer;
            Intrinsics.checkNotNull(mediaRenderer);
            mediaRenderer.playWhenReady = true;
        }
    }

    public final void setRenderer(@Nullable MediaRenderer mediaRenderer) {
        this.renderer = mediaRenderer;
    }

    public final void setResuming(boolean z) {
        this.isResuming = z;
    }

    public final void setSurface(@Nullable Surface surface) {
        this.surface = surface;
    }

    public final void setVolume(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("gain must be non-negative");
        }
        if (!this.isAudioPipeline) {
            throw new UnsupportedOperationException("This operation is not supported for non-audio media pipeline");
        }
        synchronized (this.mutex) {
            MediaRenderer mediaRenderer = this.renderer;
            if (mediaRenderer != null) {
                mediaRenderer.setVolume(f);
            }
        }
    }

    public final boolean shouldRenderBufferOnRelease() {
        return !this.isAudioPipeline;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void start() throws MediaPipelineBackendException {
        synchronized (this.mutex) {
            if (!isIdle()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring start() in " + this.state + " state", new Object[0]);
                return;
            }
            super.start();
            this.hasInputStreamEnded = false;
            this.hasOutputStreamEnded.set(false);
            this.isFirstSamplePtsReported = false;
            this.isResuming = true;
            MediaRenderer mediaRenderer = this.renderer;
            Intrinsics.checkNotNull(mediaRenderer);
            if (mediaRenderer.isIdle()) {
                mediaRenderer.start();
            }
        }
    }

    public final void stopDrainTaskTimer(int i) {
        Stopwatch stopwatch = this.drainTaskTimer;
        stopwatch.stop();
        long jElapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        if (jElapsed > this.warningThresholdMillis) {
            logOperationExceededThreshold("MediaCodec.releaseOutputBuffer index " + i, jElapsed);
        }
    }

    public final void validateVideoDecodingCadence() throws MediaPipelineBackendException {
        long jElapsed = this.videoDecodeCadenceStopwatch.elapsed(TimeUnit.MILLISECONDS);
        if (jElapsed > this.videoDecodingCadenceLoggingThresholdInMillis) {
            long millis = TimeUnit.MICROSECONDS.toMillis(this.lastDecodedPresentationTimeUs);
            long j = this.videoDecodingCadenceLoggingThresholdInMillis;
            MediaFormat mediaFormat = this.inputFormat;
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("MediaPipeline lastDecodedSamplePts ", millis, " ms, threshold ");
            sbM.append(j);
            sbM.append(" ms breached ");
            sbM.append(jElapsed);
            sbM.append(" ms since last video sample was submitted ");
            sbM.append(mediaFormat);
            MpbLog.warnf(sbM.toString(), new Object[0]);
        }
        if (jElapsed <= this.videoDecodingCadenceThresholdInMillis) {
            return;
        }
        long millis2 = TimeUnit.MICROSECONDS.toMillis(this.lastDecodedPresentationTimeUs);
        long j2 = this.videoDecodingCadenceThresholdInMillis;
        MediaFormat mediaFormat2 = this.inputFormat;
        StringBuilder sbM2 = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("MediaPipeline triggering player fatal lastDecodedSamplePts ", millis2, " ms, threshold ");
        sbM2.append(j2);
        sbM2.append(" ms breached ");
        sbM2.append(jElapsed);
        sbM2.append(" ms since last video sample was submitted ");
        sbM2.append(mediaFormat2);
        String string = sbM2.toString();
        MpbLog.errorf(string, new Object[0]);
        throw new MediaPipelineBackendException(string, MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECODER_STALL);
    }
}
