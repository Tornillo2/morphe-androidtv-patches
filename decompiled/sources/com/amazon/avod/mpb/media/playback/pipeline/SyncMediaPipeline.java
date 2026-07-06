package com.amazon.avod.mpb.media.playback.pipeline;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.playback.avsync.MediaClock;
import com.amazon.avod.mpb.media.playback.render.MediaRenderer;
import com.amazon.avod.mpb.media.playback.render.SubmitBufferResult;
import com.amazon.avod.mpb.media.playback.source.MediaSource;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
public final class SyncMediaPipeline extends MediaPipeline {
    public final boolean isAsynchronous;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PipelineTaskType.values().length];
            try {
                iArr[PipelineTaskType.DRAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PipelineTaskType.FEED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public SyncMediaPipeline(@NotNull MediaSource mediaSource, @NotNull MediaCodecFactory codecFactory, @NotNull MediaRendererFactory rendererFactory, @NotNull Context appContext, @NotNull MediaClock mediaClock, @NotNull MediaPipelineContext mediaPipelineContext, @Nullable Integer num, boolean z, @NotNull MPBInternalConfig config, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @Nullable Integer num2, int i) {
        super(mediaSource, codecFactory, rendererFactory, appContext, mediaClock, mediaPipelineContext, num, z, config, capabilityDetector, num2, i);
        Intrinsics.checkNotNullParameter(mediaSource, "mediaSource");
        Intrinsics.checkNotNullParameter(codecFactory, "codecFactory");
        Intrinsics.checkNotNullParameter(rendererFactory, "rendererFactory");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaClock, "mediaClock");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
    }

    public final void drainOutputBufferTask() throws MediaPipelineBackendException {
        ByteBuffer outputBuffer;
        try {
            long j = 0;
            long currentMediaTimeUs = this.isAudioPipeline ? 0L : this.mediaClock.getCurrentMediaTimeUs();
            long micros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
            while (!this.hasOutputStreamEnded.get()) {
                if (this.availableOutputBufferIndex < 0) {
                    Stopwatch stopwatch = this.drainTaskTimer;
                    stopwatch.reset();
                    stopwatch.start();
                    MediaCodec mediaCodec = this.codec;
                    Intrinsics.checkNotNull(mediaCodec);
                    this.availableOutputBufferIndex = mediaCodec.dequeueOutputBuffer(this.availableOutputBufferInfo, j);
                    Stopwatch stopwatch2 = this.drainTaskTimer;
                    stopwatch2.stop();
                    long jElapsed = stopwatch2.elapsed(TimeUnit.MILLISECONDS);
                    if (jElapsed > this.warningThresholdMillis) {
                        logOperationExceededThreshold("MediaCodec.dequeueOutputBuffer, index " + this.availableOutputBufferIndex, jElapsed);
                    }
                }
                int i = this.availableOutputBufferIndex;
                if (i == -1) {
                    return;
                }
                if (i == -3) {
                    if (!this.shouldUseNewGetBufferApi) {
                        MediaCodec mediaCodec2 = this.codec;
                        Intrinsics.checkNotNull(mediaCodec2);
                        this.outputBuffers = mediaCodec2.getOutputBuffers();
                    }
                } else if (i == -2) {
                    MediaCodec mediaCodec3 = this.codec;
                    Intrinsics.checkNotNull(mediaCodec3);
                    MediaFormat outputFormat = mediaCodec3.getOutputFormat();
                    Intrinsics.checkNotNullExpressionValue(outputFormat, "getOutputFormat(...)");
                    handleOutputFormatChanged(outputFormat);
                } else {
                    this.lastDecodedPresentationTimeUs = this.availableOutputBufferInfo.presentationTimeUs;
                    if (this.shouldUseNewGetBufferApi) {
                        MediaCodec mediaCodec4 = this.codec;
                        Intrinsics.checkNotNull(mediaCodec4);
                        outputBuffer = mediaCodec4.getOutputBuffer(this.availableOutputBufferIndex);
                    } else {
                        ByteBuffer[] byteBufferArr = this.outputBuffers;
                        Intrinsics.checkNotNull(byteBufferArr);
                        outputBuffer = byteBufferArr[this.availableOutputBufferIndex];
                    }
                    ByteBuffer byteBuffer = outputBuffer;
                    if (this.shouldUseNewGetBufferApi && byteBuffer != null) {
                        byteBuffer.position(byteBuffer.position() + this.availableOutputBufferInfo.offset);
                    }
                    MediaRenderer mediaRenderer = this.renderer;
                    Intrinsics.checkNotNull(mediaRenderer);
                    MediaCodec.BufferInfo bufferInfo = this.availableOutputBufferInfo;
                    SubmitBufferResult submitBufferResultSubmitBuffer = mediaRenderer.submitBuffer(currentMediaTimeUs, bufferInfo, byteBuffer, micros, isSampleOverlapped(bufferInfo.presentationTimeUs));
                    int i2 = submitBufferResultSubmitBuffer.bytesRead;
                    if (Build.VERSION.SDK_INT >= 24 && this.isAudioPipeline && i2 == -6) {
                        handleAudioTrackDeadError(this.availableOutputBufferInfo.presentationTimeUs);
                        if (byteBuffer != null) {
                            byteBuffer.position(0);
                        }
                        i2 = 0;
                    }
                    if (i2 >= 0) {
                        MediaCodec.BufferInfo bufferInfo2 = this.availableOutputBufferInfo;
                        bufferInfo2.size -= i2;
                        bufferInfo2.offset += i2;
                        return;
                    }
                    if (i2 == -1 || i2 == -2) {
                        handlePictureAspectRatioChange();
                        long j2 = submitBufferResultSubmitBuffer.adjustedReleaseTimeNs;
                        Stopwatch stopwatch3 = this.drainTaskTimer;
                        stopwatch3.reset();
                        stopwatch3.start();
                        if (i2 == -1) {
                            MediaCodec mediaCodec5 = this.codec;
                            Intrinsics.checkNotNull(mediaCodec5);
                            mediaCodec5.releaseOutputBuffer(this.availableOutputBufferIndex, false);
                        } else if (j2 == -3) {
                            MediaCodec mediaCodec6 = this.codec;
                            Intrinsics.checkNotNull(mediaCodec6);
                            mediaCodec6.releaseOutputBuffer(this.availableOutputBufferIndex, !this.isAudioPipeline);
                        } else {
                            MediaCodec mediaCodec7 = this.codec;
                            Intrinsics.checkNotNull(mediaCodec7);
                            mediaCodec7.releaseOutputBuffer(this.availableOutputBufferIndex, j2);
                        }
                        if (!this.isAudioPipeline && i2 == -2) {
                            this.isResuming = false;
                        }
                        stopDrainTaskTimer(this.availableOutputBufferIndex);
                        if ((this.availableOutputBufferInfo.flags & 4) != 0) {
                            MpbLog.logf("Pipeline for " + this.inputFormat + " decoded and rendered end of stream", new Object[0]);
                            handleEndOfStream();
                        }
                    }
                }
                clearAvailableOutputBufferInfo();
                j = 0;
            }
        } catch (MediaCodec.CodecException e) {
            handleCodecException(e, "while draining");
        } catch (IllegalStateException e2) {
            logUnexpectedIllegalStateException(e2, "while draining");
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public void executePipelineTask(@NotNull PipelineTaskType pipelineTaskType) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(pipelineTaskType, "pipelineTaskType");
        if (isRunning()) {
            int i = WhenMappings.$EnumSwitchMapping$0[pipelineTaskType.ordinal()];
            if (i == 1) {
                if (!this.isAudioPipeline && this.shouldValidateVideoDecodingCadence) {
                    validateVideoDecodingCadence();
                }
                checkUnderrun();
                drainOutputBufferTask();
                return;
            }
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            feedInputBufferTask();
            if (this.isAudioPipeline || this.audioSessionId == null || this.source.hasNext()) {
                return;
            }
            checkUnderrun();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005f, code lost:
    
        if (r10.hasInputStreamEnded != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0067, code lost:
    
        if (r10.source.hasStreamFinished() == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006f, code lost:
    
        if (r10.source.hasNext() != false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0071, code lost:
    
        r1 = r10.feedTaskTimer;
        r1.reset();
        r1.start();
        r1 = r10.codec;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1 = r1.dequeueInputBuffer(0);
        r2 = r10.feedTaskTimer;
        r2.stop();
        r5 = r2.elapsed(java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0091, code lost:
    
        if (r5 <= r10.warningThresholdMillis) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0093, code lost:
    
        logOperationExceededThreshold("MediaCodec.dequeueInputBuffer, index " + r1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a5, code lost:
    
        if (r1 == (-1)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a7, code lost:
    
        feedEndOfStreamFlag(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00aa, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void feedInputBufferTask() throws com.amazon.avod.mpb.api.core.MediaPipelineBackendException {
        /*
            r10 = this;
            java.lang.String r0 = "while feeding"
            r1 = 0
        L4:
            boolean r2 = r10.isAudioPipeline     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r3 = -1
            java.lang.String r4 = "MediaCodec.dequeueInputBuffer, index "
            r5 = 0
            if (r2 != 0) goto L18
            int r2 = r10.maxConcurrentSampleCount     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            if (r1 >= r2) goto L5d
            goto L18
        L12:
            r1 = move-exception
            goto Lab
        L15:
            r1 = move-exception
            goto Laf
        L18:
            com.amazon.avod.mpb.media.playback.source.MediaSource r2 = r10.source     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            boolean r2 = r2.hasNext()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            if (r2 == 0) goto L5d
            com.google.common.base.Stopwatch r2 = r10.feedTaskTimer     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r2.reset()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r2.start()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            android.media.MediaCodec r2 = r10.codec     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            int r2 = r2.dequeueInputBuffer(r5)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            com.google.common.base.Stopwatch r5 = r10.feedTaskTimer     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r5.stop()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            long r5 = r5.elapsed(r6)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            long r7 = r10.warningThresholdMillis     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L54
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r7.<init>()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r7.append(r4)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r7.append(r2)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            java.lang.String r4 = r7.toString()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r10.logOperationExceededThreshold(r4, r5)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
        L54:
            if (r2 != r3) goto L57
            goto Lb2
        L57:
            r10.feedInputBuffer(r2)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            int r1 = r1 + 1
            goto L4
        L5d:
            boolean r1 = r10.hasInputStreamEnded     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            if (r1 != 0) goto Lb2
            com.amazon.avod.mpb.media.playback.source.MediaSource r1 = r10.source     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            boolean r1 = r1.hasStreamFinished()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            if (r1 == 0) goto Lb2
            com.amazon.avod.mpb.media.playback.source.MediaSource r1 = r10.source     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            boolean r1 = r1.hasNext()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            if (r1 != 0) goto Lb2
            com.google.common.base.Stopwatch r1 = r10.feedTaskTimer     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r1.reset()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r1.start()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            android.media.MediaCodec r1 = r10.codec     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            int r1 = r1.dequeueInputBuffer(r5)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            com.google.common.base.Stopwatch r2 = r10.feedTaskTimer     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r2.stop()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            long r5 = r2.elapsed(r5)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            long r7 = r10.warningThresholdMillis     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 <= 0) goto La5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r2.<init>()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r2.append(r4)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r2.append(r1)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            java.lang.String r2 = r2.toString()     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            r10.logOperationExceededThreshold(r2, r5)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
        La5:
            if (r1 == r3) goto Lb2
            r10.feedEndOfStreamFlag(r1)     // Catch: java.lang.IllegalStateException -> L12 android.media.MediaCodec.CodecException -> L15
            return
        Lab:
            r10.logUnexpectedIllegalStateException(r1, r0)
            goto Lb2
        Laf:
            r10.handleCodecException(r1, r0)
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.pipeline.SyncMediaPipeline.feedInputBufferTask():void");
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public boolean isAsynchronous() {
        return this.isAsynchronous;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        synchronized (this.mutex) {
            if (!isRunning()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring stop() in " + this.state + " state", new Object[0]);
                return;
            }
            super.stop();
            MediaRenderer mediaRenderer = this.renderer;
            Intrinsics.checkNotNull(mediaRenderer);
            if (mediaRenderer.isRunning()) {
                mediaRenderer.stop();
            }
            if (!this.isAudioPipeline) {
                Stopwatch stopwatch = this.videoDecodeCadenceStopwatch;
                if (stopwatch.isRunning) {
                    stopwatch.stop();
                    stopwatch.reset();
                }
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SyncMediaPipeline(@NotNull MediaSource mediaSource, @NotNull Context appContext, @NotNull MediaClock mediaClock, @NotNull MediaPipelineContext mediaPipelineContext, boolean z, @Nullable Integer num, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @Nullable Integer num2, int i) {
        Intrinsics.checkNotNullParameter(mediaSource, "mediaSource");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaClock, "mediaClock");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        MediaCodecFactory.Companion.getClass();
        MediaCodecFactory mediaCodecFactory = MediaCodecFactory.instance;
        MediaRendererFactory.Companion.getClass();
        this(mediaSource, mediaCodecFactory, MediaRendererFactory.instance, appContext, mediaClock, mediaPipelineContext, num, z, DefaultMPBInternalConfig.INSTANCE, capabilityDetector, num2, i);
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public void configureCallbacks() {
    }
}
