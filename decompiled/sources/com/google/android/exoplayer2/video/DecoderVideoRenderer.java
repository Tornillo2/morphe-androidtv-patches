package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.CryptoConfig;
import com.google.android.exoplayer2.decoder.Decoder;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderException;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.decoder.VideoDecoderOutputBuffer;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.TimedValueQueue;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class DecoderVideoRenderer extends BaseRenderer {
    public static final int REINITIALIZATION_STATE_NONE = 0;
    public static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    public static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    public static final String TAG = "DecoderVideoRenderer";
    public final long allowedJoiningTimeMs;
    public int buffersInCodecCount;
    public int consecutiveDroppedFrameCount;

    @Nullable
    public Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder;
    public DecoderCounters decoderCounters;

    @Nullable
    public DrmSession decoderDrmSession;
    public boolean decoderReceivedBuffers;
    public int decoderReinitializationState;
    public long droppedFrameAccumulationStartTimeMs;
    public int droppedFrames;
    public final VideoRendererEventListener.EventDispatcher eventDispatcher;
    public final DecoderInputBuffer flagsOnlyBuffer;
    public final TimedValueQueue<Format> formatQueue;

    @Nullable
    public VideoFrameMetadataListener frameMetadataListener;
    public long initialPositionUs;
    public DecoderInputBuffer inputBuffer;
    public Format inputFormat;
    public boolean inputStreamEnded;
    public long joiningDeadlineMs;
    public long lastRenderTimeUs;
    public final Logger log;
    public final int maxDroppedFramesToNotify;
    public boolean mayRenderFirstFrameAfterEnableIfNotStarted;

    @Nullable
    public Object output;
    public VideoDecoderOutputBuffer outputBuffer;

    @Nullable
    public VideoDecoderOutputBufferRenderer outputBufferRenderer;
    public Format outputFormat;
    public int outputMode;
    public boolean outputStreamEnded;
    public long outputStreamOffsetUs;

    @Nullable
    public Surface outputSurface;
    public boolean renderedFirstFrameAfterEnable;
    public boolean renderedFirstFrameAfterReset;

    @Nullable
    public VideoSize reportedVideoSize;

    @Nullable
    public DrmSession sourceDrmSession;
    public boolean waitingForFirstSampleInFormat;

    public DecoderVideoRenderer(long j, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        super(2);
        this.log = new Logger(Logger.Module.Video, "DecoderVideoRenderer");
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.reportedVideoSize = null;
        this.formatQueue = new TimedValueQueue<>();
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.decoderReinitializationState = 0;
        this.outputMode = -1;
    }

    private void clearRenderedFirstFrame() {
        this.renderedFirstFrameAfterReset = false;
    }

    private void clearReportedVideoSize() {
        this.reportedVideoSize = null;
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException, DecoderException {
        if (this.outputBuffer == null) {
            VideoDecoderOutputBuffer videoDecoderOutputBufferDequeueOutputBuffer = this.decoder.dequeueOutputBuffer();
            this.outputBuffer = videoDecoderOutputBufferDequeueOutputBuffer;
            if (videoDecoderOutputBufferDequeueOutputBuffer == null) {
                return false;
            }
            DecoderCounters decoderCounters = this.decoderCounters;
            int i = decoderCounters.skippedOutputBufferCount;
            int i2 = videoDecoderOutputBufferDequeueOutputBuffer.skippedOutputBufferCount;
            decoderCounters.skippedOutputBufferCount = i + i2;
            this.buffersInCodecCount -= i2;
        }
        if (!this.outputBuffer.getFlag(4)) {
            boolean zProcessOutputBuffer = processOutputBuffer(j, j2);
            if (zProcessOutputBuffer) {
                onProcessedOutputBuffer(this.outputBuffer.timeUs);
                this.outputBuffer = null;
            }
            return zProcessOutputBuffer;
        }
        if (this.decoderReinitializationState == 2) {
            releaseDecoder();
            maybeInitDecoder();
            return false;
        }
        this.outputBuffer.release();
        this.outputBuffer = null;
        this.outputStreamEnded = true;
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean feedInputBuffer() throws com.google.android.exoplayer2.ExoPlaybackException, com.google.android.exoplayer2.decoder.DecoderException {
        /*
            r7 = this;
            com.google.android.exoplayer2.decoder.Decoder<com.google.android.exoplayer2.decoder.DecoderInputBuffer, ? extends com.google.android.exoplayer2.decoder.VideoDecoderOutputBuffer, ? extends com.google.android.exoplayer2.decoder.DecoderException> r0 = r7.decoder
            r1 = 0
            if (r0 == 0) goto L99
            int r2 = r7.decoderReinitializationState
            r3 = 2
            if (r2 == r3) goto L99
            boolean r2 = r7.inputStreamEnded
            if (r2 == 0) goto L10
            goto L99
        L10:
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r2 = r7.inputBuffer
            if (r2 != 0) goto L20
            java.lang.Object r0 = r0.dequeueInputBuffer()
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r0 = (com.google.android.exoplayer2.decoder.DecoderInputBuffer) r0
            r7.inputBuffer = r0
            if (r0 != 0) goto L20
            goto L99
        L20:
            int r0 = r7.decoderReinitializationState
            r2 = 4
            r4 = 0
            r5 = 1
            if (r0 != r5) goto L35
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r0 = r7.inputBuffer
            r0.flags = r2
            com.google.android.exoplayer2.decoder.Decoder<com.google.android.exoplayer2.decoder.DecoderInputBuffer, ? extends com.google.android.exoplayer2.decoder.VideoDecoderOutputBuffer, ? extends com.google.android.exoplayer2.decoder.DecoderException> r2 = r7.decoder
            r2.queueInputBuffer(r0)
            r7.inputBuffer = r4
            r7.decoderReinitializationState = r3
            return r1
        L35:
            com.google.android.exoplayer2.FormatHolder r0 = r7.getFormatHolder()
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r3 = r7.inputBuffer
            int r3 = r7.readSource(r0, r3, r1)
            r6 = -5
            if (r3 == r6) goto L95
            r0 = -4
            if (r3 == r0) goto L4f
            r0 = -3
            if (r3 != r0) goto L49
            goto L99
        L49:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L4f:
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r0 = r7.inputBuffer
            boolean r0 = r0.getFlag(r2)
            if (r0 == 0) goto L63
            r7.inputStreamEnded = r5
            com.google.android.exoplayer2.decoder.Decoder<com.google.android.exoplayer2.decoder.DecoderInputBuffer, ? extends com.google.android.exoplayer2.decoder.VideoDecoderOutputBuffer, ? extends com.google.android.exoplayer2.decoder.DecoderException> r0 = r7.decoder
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r2 = r7.inputBuffer
            r0.queueInputBuffer(r2)
            r7.inputBuffer = r4
            return r1
        L63:
            boolean r0 = r7.waitingForFirstSampleInFormat
            if (r0 == 0) goto L74
            com.google.android.exoplayer2.util.TimedValueQueue<com.google.android.exoplayer2.Format> r0 = r7.formatQueue
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r2 = r7.inputBuffer
            long r2 = r2.timeUs
            com.google.android.exoplayer2.Format r6 = r7.inputFormat
            r0.add(r2, r6)
            r7.waitingForFirstSampleInFormat = r1
        L74:
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r0 = r7.inputBuffer
            r0.flip()
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r0 = r7.inputBuffer
            com.google.android.exoplayer2.Format r1 = r7.inputFormat
            r0.format = r1
            com.google.android.exoplayer2.decoder.Decoder<com.google.android.exoplayer2.decoder.DecoderInputBuffer, ? extends com.google.android.exoplayer2.decoder.VideoDecoderOutputBuffer, ? extends com.google.android.exoplayer2.decoder.DecoderException> r1 = r7.decoder
            r1.queueInputBuffer(r0)
            int r0 = r7.buffersInCodecCount
            int r0 = r0 + r5
            r7.buffersInCodecCount = r0
            r7.decoderReceivedBuffers = r5
            com.google.android.exoplayer2.decoder.DecoderCounters r0 = r7.decoderCounters
            int r1 = r0.queuedInputBufferCount
            int r1 = r1 + r5
            r0.queuedInputBufferCount = r1
            r7.inputBuffer = r4
            return r5
        L95:
            r7.onInputFormatChanged(r0)
            return r5
        L99:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.DecoderVideoRenderer.feedInputBuffer():boolean");
    }

    private static boolean isBufferLate(long j) {
        return j < androidx.media3.exoplayer.video.MediaCodecVideoRenderer.MIN_EARLY_US_LATE_THRESHOLD;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < androidx.media3.exoplayer.video.MediaCodecVideoRenderer.MIN_EARLY_US_VERY_LATE_THRESHOLD;
    }

    private void maybeInitDecoder() throws ExoPlaybackException {
        CryptoConfig cryptoConfig;
        if (this.decoder != null) {
            return;
        }
        setDecoderDrmSession(this.sourceDrmSession);
        DrmSession drmSession = this.decoderDrmSession;
        if (drmSession != null) {
            cryptoConfig = drmSession.getCryptoConfig();
            if (cryptoConfig == null && this.decoderDrmSession.getError() == null) {
                return;
            }
        } else {
            cryptoConfig = null;
        }
        try {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.decoder = createDecoder(this.inputFormat, cryptoConfig);
            setDecoderOutputMode(this.outputMode);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            this.eventDispatcher.decoderInitialized(this.decoder.getName(), jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
            this.decoderCounters.decoderInitCount++;
        } catch (DecoderException e) {
            this.log.e("Video codec error", e);
            this.eventDispatcher.videoCodecError(e);
            throw createRendererException(e, this.inputFormat, false, 4001);
        } catch (OutOfMemoryError e2) {
            throw createRendererException(e2, this.inputFormat, false, 4001);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, jElapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = jElapsedRealtime;
        }
    }

    private void maybeNotifyRenderedFirstFrame() {
        this.renderedFirstFrameAfterEnable = true;
        if (this.renderedFirstFrameAfterReset) {
            return;
        }
        this.renderedFirstFrameAfterReset = true;
        this.eventDispatcher.renderedFirstFrame(this.output);
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrameAfterReset) {
            this.eventDispatcher.renderedFirstFrame(this.output);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null) {
            this.eventDispatcher.videoSizeChanged(videoSize);
        }
    }

    private void setDecoderDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.CC.replaceSession(this.decoderDrmSession, drmSession);
        this.decoderDrmSession = drmSession;
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

    private void setSourceDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.CC.replaceSession(this.sourceDrmSession, drmSession);
        this.sourceDrmSession = drmSession;
    }

    public DecoderReuseEvaluation canReuseDecoder(String str, Format format, Format format2) {
        return new DecoderReuseEvaluation(str, format, format2, 0, 1);
    }

    public abstract Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> createDecoder(Format format, @Nullable CryptoConfig cryptoConfig) throws DecoderException;

    public void dropOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer) {
        updateDroppedBufferCounters(0, 1);
        videoDecoderOutputBuffer.release();
    }

    @CallSuper
    public void flushDecoder() throws ExoPlaybackException {
        this.buffersInCodecCount = 0;
        if (this.decoderReinitializationState != 0) {
            releaseDecoder();
            maybeInitDecoder();
            return;
        }
        this.inputBuffer = null;
        VideoDecoderOutputBuffer videoDecoderOutputBuffer = this.outputBuffer;
        if (videoDecoderOutputBuffer != null) {
            videoDecoderOutputBuffer.release();
            this.outputBuffer = null;
        }
        this.decoder.flush();
        this.decoderReceivedBuffers = false;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setOutput(obj);
        } else if (i == 7) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        }
    }

    public final boolean hasOutput() {
        return this.outputMode != -1;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        if (this.inputFormat != null && ((isSourceReady() || this.outputBuffer != null) && (this.renderedFirstFrameAfterReset || !hasOutput()))) {
            this.joiningDeadlineMs = -9223372036854775807L;
            return true;
        }
        if (this.joiningDeadlineMs == -9223372036854775807L) {
            return false;
        }
        if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
            return true;
        }
        this.joiningDeadlineMs = -9223372036854775807L;
        return false;
    }

    public boolean maybeDropBuffersToKeyframe(long j) throws ExoPlaybackException {
        int iSkipSource = skipSource(j);
        if (iSkipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(iSkipSource, this.buffersInCodecCount);
        flushDecoder();
        return true;
    }

    public final void maybeNotifyVideoSizeChanged(int i, int i2) {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null && videoSize.width == i && videoSize.height == i2) {
            return;
        }
        VideoSize videoSize2 = new VideoSize(i, i2);
        this.reportedVideoSize = videoSize2;
        this.eventDispatcher.videoSizeChanged(videoSize2);
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.reportedVideoSize = null;
        this.renderedFirstFrameAfterReset = false;
        try {
            setSourceDrmSession(null);
            releaseDecoder();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        DecoderCounters decoderCounters = new DecoderCounters();
        this.decoderCounters = decoderCounters;
        this.eventDispatcher.enabled(decoderCounters);
        this.mayRenderFirstFrameAfterEnableIfNotStarted = z2;
        this.renderedFirstFrameAfterEnable = false;
    }

    @CallSuper
    public void onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        this.waitingForFirstSampleInFormat = true;
        Format format = formatHolder.format;
        format.getClass();
        setSourceDrmSession(formatHolder.drmSession);
        Format format2 = this.inputFormat;
        this.inputFormat = format;
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder = this.decoder;
        if (decoder == null) {
            maybeInitDecoder();
            this.eventDispatcher.inputFormatChanged(this.inputFormat, null);
            return;
        }
        DecoderReuseEvaluation decoderReuseEvaluation = this.sourceDrmSession != this.decoderDrmSession ? new DecoderReuseEvaluation(decoder.getName(), format2, format, 0, 128) : canReuseDecoder(decoder.getName(), format2, format);
        if (decoderReuseEvaluation.result == 0) {
            if (this.decoderReceivedBuffers) {
                this.decoderReinitializationState = 1;
            } else {
                releaseDecoder();
                maybeInitDecoder();
            }
        }
        this.eventDispatcher.inputFormatChanged(this.inputFormat, decoderReuseEvaluation);
    }

    public final void onOutputChanged() {
        maybeRenotifyVideoSizeChanged();
        this.renderedFirstFrameAfterReset = false;
        if (this.state == 2) {
            setJoiningDeadlineMs();
        }
    }

    public final void onOutputRemoved() {
        this.reportedVideoSize = null;
        this.renderedFirstFrameAfterReset = false;
    }

    public final void onOutputReset() {
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.renderedFirstFrameAfterReset = false;
        this.initialPositionUs = -9223372036854775807L;
        this.consecutiveDroppedFrameCount = 0;
        if (this.decoder != null) {
            flushDecoder();
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = -9223372036854775807L;
        }
        this.formatQueue.clear();
    }

    @CallSuper
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2) throws ExoPlaybackException {
        this.outputStreamOffsetUs = j2;
    }

    public final boolean processOutputBuffer(long j, long j2) throws ExoPlaybackException, DecoderException {
        if (this.initialPositionUs == -9223372036854775807L) {
            this.initialPositionUs = j;
        }
        long j3 = this.outputBuffer.timeUs - j;
        if (hasOutput()) {
            long j4 = this.outputBuffer.timeUs - this.outputStreamOffsetUs;
            Format formatPollFloor = this.formatQueue.pollFloor(j4);
            if (formatPollFloor != null) {
                this.outputFormat = formatPollFloor;
            }
            long jElapsedRealtime = (SystemClock.elapsedRealtime() * 1000) - this.lastRenderTimeUs;
            boolean z = this.state == 2;
            if (this.renderedFirstFrameAfterEnable ? this.renderedFirstFrameAfterReset : !z && !this.mayRenderFirstFrameAfterEnableIfNotStarted) {
                if (!z || !shouldForceRenderOutputBuffer(j3, jElapsedRealtime)) {
                    if (z && j != this.initialPositionUs && (!shouldDropBuffersToKeyframe(j3, j2) || !maybeDropBuffersToKeyframe(j))) {
                        if (shouldDropOutputBuffer(j3, j2)) {
                            dropOutputBuffer(this.outputBuffer);
                            return true;
                        }
                        if (j3 < 30000) {
                            renderOutputBuffer(this.outputBuffer, j4, this.outputFormat);
                            return true;
                        }
                    }
                }
            }
            renderOutputBuffer(this.outputBuffer, j4, this.outputFormat);
            return true;
        }
        if (isBufferLate(j3)) {
            skipOutputBuffer(this.outputBuffer);
            return true;
        }
        return false;
    }

    @CallSuper
    public void releaseDecoder() {
        this.inputBuffer = null;
        this.outputBuffer = null;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
        this.buffersInCodecCount = 0;
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder = this.decoder;
        if (decoder != null) {
            this.decoderCounters.decoderReleaseCount++;
            decoder.release();
            this.eventDispatcher.decoderReleased(this.decoder.getName());
            this.decoder = null;
        }
        setDecoderDrmSession(null);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            return;
        }
        if (this.inputFormat == null) {
            FormatHolder formatHolder = getFormatHolder();
            this.flagsOnlyBuffer.clear();
            int source = readSource(formatHolder, this.flagsOnlyBuffer, 2);
            if (source != -5) {
                if (source == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.getFlag(4));
                    this.inputStreamEnded = true;
                    this.outputStreamEnded = true;
                    return;
                }
                return;
            }
            onInputFormatChanged(formatHolder);
        }
        maybeInitDecoder();
        if (this.decoder != null) {
            try {
                TraceUtil.beginSection("drainAndFeed");
                while (drainOutputBuffer(j, j2)) {
                }
                while (feedInputBuffer()) {
                }
                TraceUtil.endSection();
                synchronized (this.decoderCounters) {
                }
            } catch (DecoderException e) {
                this.log.e("Video codec error", e);
                this.eventDispatcher.videoCodecError(e);
                throw createRendererException(e, this.inputFormat, false, 4003);
            }
        }
    }

    public void renderOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer, long j, Format format) throws DecoderException {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, System.nanoTime(), format, null);
        }
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime() * 1000);
        int i = videoDecoderOutputBuffer.mode;
        boolean z = i == 1 && this.outputSurface != null;
        boolean z2 = i == 0 && this.outputBufferRenderer != null;
        if (!z2 && !z) {
            dropOutputBuffer(videoDecoderOutputBuffer);
            return;
        }
        maybeNotifyVideoSizeChanged(videoDecoderOutputBuffer.width, videoDecoderOutputBuffer.height);
        if (z2) {
            this.outputBufferRenderer.setOutputBuffer(videoDecoderOutputBuffer);
        } else {
            renderOutputBufferToSurface(videoDecoderOutputBuffer, this.outputSurface);
        }
        this.consecutiveDroppedFrameCount = 0;
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
    }

    public abstract void renderOutputBufferToSurface(VideoDecoderOutputBuffer videoDecoderOutputBuffer, Surface surface) throws DecoderException;

    public abstract void setDecoderOutputMode(int i);

    public final void setOutput(@Nullable Object obj) {
        if (obj instanceof Surface) {
            this.outputSurface = (Surface) obj;
            this.outputBufferRenderer = null;
            this.outputMode = 1;
        } else if (obj instanceof VideoDecoderOutputBufferRenderer) {
            this.outputSurface = null;
            this.outputBufferRenderer = (VideoDecoderOutputBufferRenderer) obj;
            this.outputMode = 0;
        } else {
            this.outputSurface = null;
            this.outputBufferRenderer = null;
            this.outputMode = -1;
            obj = null;
        }
        if (this.output == obj) {
            if (obj != null) {
                onOutputReset();
                return;
            }
            return;
        }
        this.output = obj;
        if (obj == null) {
            onOutputRemoved();
            return;
        }
        if (this.decoder != null) {
            setDecoderOutputMode(this.outputMode);
        }
        onOutputChanged();
    }

    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return isBufferVeryLate(j);
    }

    public boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    public void skipOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer) {
        this.decoderCounters.skippedOutputBufferCount++;
        videoDecoderOutputBuffer.release();
    }

    public void updateDroppedBufferCounters(int i, int i2) {
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedInputBufferCount += i;
        int i3 = i + i2;
        decoderCounters.droppedBufferCount += i3;
        this.droppedFrames += i3;
        int i4 = this.consecutiveDroppedFrameCount + i3;
        this.consecutiveDroppedFrameCount = i4;
        decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(i4, decoderCounters.maxConsecutiveDroppedBufferCount);
        int i5 = this.maxDroppedFramesToNotify;
        if (i5 <= 0 || this.droppedFrames < i5) {
            return;
        }
        maybeNotifyDroppedFrames();
    }

    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
    }
}
