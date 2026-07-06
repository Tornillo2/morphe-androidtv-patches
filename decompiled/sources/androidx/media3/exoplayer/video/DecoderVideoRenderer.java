package androidx.media3.exoplayer.video;

import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
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
    public int firstFrameState;
    public final DecoderInputBuffer flagsOnlyBuffer;
    public final TimedValueQueue<Format> formatQueue;

    @Nullable
    public VideoFrameMetadataListener frameMetadataListener;
    public long initialPositionUs;

    @Nullable
    public DecoderInputBuffer inputBuffer;

    @Nullable
    public Format inputFormat;
    public boolean inputStreamEnded;
    public long joiningDeadlineMs;
    public long lastRenderTimeUs;
    public final int maxDroppedFramesToNotify;

    @Nullable
    public Object output;

    @Nullable
    public VideoDecoderOutputBuffer outputBuffer;

    @Nullable
    public VideoDecoderOutputBufferRenderer outputBufferRenderer;

    @Nullable
    public Format outputFormat;
    public int outputMode;
    public boolean outputStreamEnded;
    public long outputStreamOffsetUs;

    @Nullable
    public Surface outputSurface;

    @Nullable
    public VideoSize reportedVideoSize;

    @Nullable
    public DrmSession sourceDrmSession;
    public boolean waitingForFirstSampleInFormat;

    public DecoderVideoRenderer(long j, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        super(2);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.formatQueue = new TimedValueQueue<>();
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.decoderReinitializationState = 0;
        this.outputMode = -1;
        this.firstFrameState = 0;
        this.decoderCounters = new DecoderCounters();
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException, DecoderException {
        if (this.outputBuffer == null) {
            Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder = this.decoder;
            decoder.getClass();
            VideoDecoderOutputBuffer videoDecoderOutputBufferDequeueOutputBuffer = decoder.dequeueOutputBuffer();
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
                VideoDecoderOutputBuffer videoDecoderOutputBuffer = this.outputBuffer;
                videoDecoderOutputBuffer.getClass();
                onProcessedOutputBuffer(videoDecoderOutputBuffer.timeUs);
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
    private boolean feedInputBuffer() throws androidx.media3.exoplayer.ExoPlaybackException, androidx.media3.decoder.DecoderException {
        /*
            r8 = this;
            androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.VideoDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r0 = r8.decoder
            r1 = 0
            if (r0 == 0) goto La9
            int r2 = r8.decoderReinitializationState
            r3 = 2
            if (r2 == r3) goto La9
            boolean r2 = r8.inputStreamEnded
            if (r2 == 0) goto L10
            goto La9
        L10:
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.inputBuffer
            if (r2 != 0) goto L20
            java.lang.Object r0 = r0.dequeueInputBuffer()
            androidx.media3.decoder.DecoderInputBuffer r0 = (androidx.media3.decoder.DecoderInputBuffer) r0
            r8.inputBuffer = r0
            if (r0 != 0) goto L20
            goto La9
        L20:
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            r0.getClass()
            int r2 = r8.decoderReinitializationState
            r4 = 4
            r5 = 0
            r6 = 1
            if (r2 != r6) goto L3b
            r0.flags = r4
            androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.VideoDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r2 = r8.decoder
            r2.getClass()
            r2.queueInputBuffer(r0)
            r8.inputBuffer = r5
            r8.decoderReinitializationState = r3
            return r1
        L3b:
            androidx.media3.exoplayer.FormatHolder r2 = r8.getFormatHolder()
            int r3 = r8.readSource(r2, r0, r1)
            r7 = -5
            if (r3 == r7) goto La5
            r2 = -4
            if (r3 == r2) goto L53
            r0 = -3
            if (r3 != r0) goto L4d
            goto La9
        L4d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L53:
            boolean r2 = r0.getFlag(r4)
            if (r2 == 0) goto L66
            r8.inputStreamEnded = r6
            androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.VideoDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r2 = r8.decoder
            r2.getClass()
            r2.queueInputBuffer(r0)
            r8.inputBuffer = r5
            return r1
        L66:
            boolean r2 = r8.waitingForFirstSampleInFormat
            if (r2 == 0) goto L78
            androidx.media3.common.util.TimedValueQueue<androidx.media3.common.Format> r2 = r8.formatQueue
            long r3 = r0.timeUs
            androidx.media3.common.Format r7 = r8.inputFormat
            r7.getClass()
            r2.add(r3, r7)
            r8.waitingForFirstSampleInFormat = r1
        L78:
            long r1 = r0.timeUs
            long r3 = r8.lastResetPositionUs
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 >= 0) goto L85
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0.addFlag(r1)
        L85:
            r0.flip()
            androidx.media3.common.Format r1 = r8.inputFormat
            r0.format = r1
            androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.VideoDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r1 = r8.decoder
            r1.getClass()
            r1.queueInputBuffer(r0)
            int r0 = r8.buffersInCodecCount
            int r0 = r0 + r6
            r8.buffersInCodecCount = r0
            r8.decoderReceivedBuffers = r6
            androidx.media3.exoplayer.DecoderCounters r0 = r8.decoderCounters
            int r1 = r0.queuedInputBufferCount
            int r1 = r1 + r6
            r0.queuedInputBufferCount = r1
            r8.inputBuffer = r5
            return r6
        La5:
            r8.onInputFormatChanged(r2)
            return r6
        La9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.DecoderVideoRenderer.feedInputBuffer():boolean");
    }

    public static boolean isBufferLate(long j) {
        return j < MediaCodecVideoRenderer.MIN_EARLY_US_LATE_THRESHOLD;
    }

    public static boolean isBufferVeryLate(long j) {
        return j < MediaCodecVideoRenderer.MIN_EARLY_US_VERY_LATE_THRESHOLD;
    }

    private void lowerFirstFrameState(int i) {
        this.firstFrameState = Math.min(this.firstFrameState, i);
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
            Format format = this.inputFormat;
            format.getClass();
            Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoderCreateDecoder = createDecoder(format, cryptoConfig);
            this.decoder = decoderCreateDecoder;
            decoderCreateDecoder.setOutputStartTimeUs(this.lastResetPositionUs);
            setDecoderOutputMode(this.outputMode);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            VideoRendererEventListener.EventDispatcher eventDispatcher = this.eventDispatcher;
            Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder = this.decoder;
            decoder.getClass();
            eventDispatcher.decoderInitialized(decoder.getName(), jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
            this.decoderCounters.decoderInitCount++;
        } catch (DecoderException e) {
            Log.e("DecoderVideoRenderer", "Video codec error", e);
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
        if (this.firstFrameState != 3) {
            this.firstFrameState = 3;
            Object obj = this.output;
            if (obj != null) {
                this.eventDispatcher.renderedFirstFrame(obj);
            }
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        Object obj;
        if (this.firstFrameState != 3 || (obj = this.output) == null) {
            return;
        }
        this.eventDispatcher.renderedFirstFrame(obj);
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

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public void enableMayRenderStartOfStream() {
        if (this.firstFrameState == 0) {
            this.firstFrameState = 1;
        }
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
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder = this.decoder;
        decoder.getClass();
        decoder.flush();
        decoder.setOutputStartTimeUs(this.lastResetPositionUs);
        this.decoderReceivedBuffers = false;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
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

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        if (this.inputFormat != null && ((isSourceReady() || this.outputBuffer != null) && (this.firstFrameState == 3 || !hasOutput()))) {
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

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.reportedVideoSize = null;
        lowerFirstFrameState(0);
        try {
            setSourceDrmSession(null);
            releaseDecoder();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        DecoderCounters decoderCounters = new DecoderCounters();
        this.decoderCounters = decoderCounters;
        this.eventDispatcher.enabled(decoderCounters);
        this.firstFrameState = z2 ? 1 : 0;
    }

    @CallSuper
    public void onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation decoderReuseEvaluationCanReuseDecoder;
        this.waitingForFirstSampleInFormat = true;
        Format format = formatHolder.format;
        format.getClass();
        setSourceDrmSession(formatHolder.drmSession);
        Format format2 = this.inputFormat;
        this.inputFormat = format;
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder = this.decoder;
        if (decoder == null) {
            maybeInitDecoder();
            VideoRendererEventListener.EventDispatcher eventDispatcher = this.eventDispatcher;
            Format format3 = this.inputFormat;
            format3.getClass();
            eventDispatcher.inputFormatChanged(format3, null);
            return;
        }
        if (this.sourceDrmSession != this.decoderDrmSession) {
            String name = decoder.getName();
            format2.getClass();
            decoderReuseEvaluationCanReuseDecoder = new DecoderReuseEvaluation(name, format2, format, 0, 128);
        } else {
            String name2 = decoder.getName();
            format2.getClass();
            decoderReuseEvaluationCanReuseDecoder = canReuseDecoder(name2, format2, format);
        }
        if (decoderReuseEvaluationCanReuseDecoder.result == 0) {
            if (this.decoderReceivedBuffers) {
                this.decoderReinitializationState = 1;
            } else {
                releaseDecoder();
                maybeInitDecoder();
            }
        }
        VideoRendererEventListener.EventDispatcher eventDispatcher2 = this.eventDispatcher;
        Format format4 = this.inputFormat;
        format4.getClass();
        eventDispatcher2.inputFormatChanged(format4, decoderReuseEvaluationCanReuseDecoder);
    }

    public final void onOutputChanged() {
        maybeRenotifyVideoSizeChanged();
        lowerFirstFrameState(1);
        if (this.state == 2) {
            setJoiningDeadlineMs();
        }
    }

    public final void onOutputRemoved() {
        this.reportedVideoSize = null;
        lowerFirstFrameState(1);
    }

    public final void onOutputReset() {
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        lowerFirstFrameState(1);
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

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime());
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        this.outputStreamOffsetUs = j2;
    }

    public final boolean processOutputBuffer(long j, long j2) throws ExoPlaybackException, DecoderException {
        if (this.initialPositionUs == -9223372036854775807L) {
            this.initialPositionUs = j;
        }
        VideoDecoderOutputBuffer videoDecoderOutputBuffer = this.outputBuffer;
        videoDecoderOutputBuffer.getClass();
        long j3 = videoDecoderOutputBuffer.timeUs;
        long j4 = j3 - j;
        if (!hasOutput()) {
            if (!isBufferLate(j4)) {
                return false;
            }
            skipOutputBuffer(videoDecoderOutputBuffer);
            return true;
        }
        Format formatPollFloor = this.formatQueue.pollFloor(j3);
        if (formatPollFloor != null) {
            this.outputFormat = formatPollFloor;
        } else if (this.outputFormat == null) {
            this.outputFormat = this.formatQueue.pollFirst();
        }
        long j5 = j3 - this.outputStreamOffsetUs;
        if (shouldForceRender(j4)) {
            Format format = this.outputFormat;
            format.getClass();
            renderOutputBuffer(videoDecoderOutputBuffer, j5, format);
            return true;
        }
        if (this.state != 2 || j == this.initialPositionUs) {
            return false;
        }
        if (shouldDropBuffersToKeyframe(j4, j2) && maybeDropBuffersToKeyframe(j)) {
            return false;
        }
        if (shouldDropOutputBuffer(j4, j2)) {
            dropOutputBuffer(videoDecoderOutputBuffer);
            return true;
        }
        if (j4 >= 30000) {
            return false;
        }
        Format format2 = this.outputFormat;
        format2.getClass();
        renderOutputBuffer(videoDecoderOutputBuffer, j5, format2);
        return true;
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

    @Override // androidx.media3.exoplayer.Renderer
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
                Log.e("DecoderVideoRenderer", "Video codec error", e);
                this.eventDispatcher.videoCodecError(e);
                throw createRendererException(e, this.inputFormat, false, 4003);
            }
        }
    }

    public void renderOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer, long j, Format format) throws DecoderException {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            Clock clock = this.clock;
            clock.getClass();
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, clock.nanoTime(), format, null);
        }
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime());
        int i = videoDecoderOutputBuffer.mode;
        boolean z = i == 1 && this.outputSurface != null;
        boolean z2 = i == 0 && this.outputBufferRenderer != null;
        if (!z2 && !z) {
            dropOutputBuffer(videoDecoderOutputBuffer);
            return;
        }
        maybeNotifyVideoSizeChanged(videoDecoderOutputBuffer.width, videoDecoderOutputBuffer.height);
        if (z2) {
            VideoDecoderOutputBufferRenderer videoDecoderOutputBufferRenderer = this.outputBufferRenderer;
            videoDecoderOutputBufferRenderer.getClass();
            videoDecoderOutputBufferRenderer.setOutputBuffer(videoDecoderOutputBuffer);
        } else {
            Surface surface = this.outputSurface;
            surface.getClass();
            renderOutputBufferToSurface(videoDecoderOutputBuffer, surface);
        }
        this.consecutiveDroppedFrameCount = 0;
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
    }

    public abstract void renderOutputBufferToSurface(VideoDecoderOutputBuffer videoDecoderOutputBuffer, Surface surface) throws DecoderException;

    public abstract void setDecoderOutputMode(int i);

    public final void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

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

    public final boolean shouldForceRender(long j) {
        boolean z = this.state == 2;
        int i = this.firstFrameState;
        if (i == 0) {
            return z;
        }
        if (i != 1) {
            if (i != 3) {
                throw new IllegalStateException();
            }
            long jMsToUs = Util.msToUs(SystemClock.elapsedRealtime()) - this.lastRenderTimeUs;
            if (!z || !shouldForceRenderOutputBuffer(j, jMsToUs)) {
                return false;
            }
        }
        return true;
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
