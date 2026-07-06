package com.amazon.avod.mpb.media.playback.render;

import android.content.Context;
import android.media.MediaFormat;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.mpb.annotate.Positive;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.playback.avsync.TimeSource;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.mpb.media.playback.pipeline.VideoFrameReleaseTimeHelper;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@NotThreadSafe
public final class VideoRenderer extends MediaRenderer {
    public static final long AV_SYNC_THRESHOLD_US = 30000;

    @NotNull
    public static final Companion Companion = new Companion();
    public final int avSyncRenderThresholdV21Us;

    @NotNull
    public final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    public final boolean isAdjustedTimeFrameReleaseEnabled;

    @Nullable
    public final Void passthroughMimeType;

    @NotNull
    public final Set<RendererResult> rendererResultsToLog;

    @NotNull
    public final SubmitBufferResult submitVideoBufferResult;

    @Nullable
    public final Void timeSource;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @VisibleForTesting
        public static /* synthetic */ void getAV_SYNC_THRESHOLD_US$core_mpb_release$annotations() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public VideoRenderer(@NotNull VideoFrameReleaseTimeHelper frameReleaseTimeHelper, @NotNull MPBInternalConfig config, @NotNull MediaPipelineContext mediaPipelineContext) {
        super(mediaPipelineContext);
        Intrinsics.checkNotNullParameter(frameReleaseTimeHelper, "frameReleaseTimeHelper");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        this.frameReleaseTimeHelper = frameReleaseTimeHelper;
        this.avSyncRenderThresholdV21Us = config.getAvSyncRenderThresholdV21Us();
        boolean adjustedTimeFrameReleaseEnabled = config.getAdjustedTimeFrameReleaseEnabled();
        this.isAdjustedTimeFrameReleaseEnabled = adjustedTimeFrameReleaseEnabled;
        this.rendererResultsToLog = mediaPipelineContext.devicePropertyProvider.isVerboseAvSyncLoggingEnabled() ? CollectionsKt___CollectionsKt.toSet(RendererResult.getEntries()) : ArraysKt___ArraysKt.toSet(new RendererResult[]{RendererResult.SKIP, RendererResult.DROP});
        this.submitVideoBufferResult = new SubmitBufferResult(0, 0L, 3, null);
        if (adjustedTimeFrameReleaseEnabled) {
            frameReleaseTimeHelper.enable();
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    @NotNull
    public MediaFormat estimateInitialOutputFormat(@NotNull MediaFormat inputFormat, boolean z) {
        Intrinsics.checkNotNullParameter(inputFormat, "inputFormat");
        return inputFormat;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void flush() {
        this.hasTriggeredReadyToPlay.set(false);
        this.mediaPipelineContext.onVideoRendererFlush();
    }

    public final long getAdjustedFrameReleaseTimeUs(long j, long j2, long j3, long j4) {
        return this.frameReleaseTimeHelper.adjustReleaseTime(j, TimeUnit.MICROSECONDS.toNanos((j - j2) - (TimeUnit.NANOSECONDS.toMicros(System.nanoTime()) - j4)) + j3);
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public String getPassthroughMimeType() {
        return (String) this.passthroughMimeType;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public TimeSource getTimeSource() {
        return (TimeSource) this.timeSource;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public boolean hasUnderrun() {
        return false;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public boolean isPassthroughSupported(@NotNull String mimeType) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return false;
    }

    public final void logRendererResult(RendererResult rendererResult, long j, long j2, long j3) {
        if (this.rendererResultsToLog.contains(rendererResult)) {
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            long millis = timeUnit.toMillis(j);
            long millis2 = timeUnit.toMillis(j2);
            long millis3 = timeUnit.toMillis(j3);
            StringBuilder sb = new StringBuilder("VideoRenderer ");
            sb.append(rendererResult);
            sb.append(" frame (ptsMs = ");
            sb.append(millis);
            sb.append(", audioMs = ");
            sb.append(millis2);
            sb.append(", lateByMs = ");
            MpbLog.logf(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, millis3, ")"), new Object[0]);
        }
    }

    public final void logRendererResultV21(RendererResult rendererResult, long j, long j2, long j3, long j4, long j5) {
        if (this.rendererResultsToLog.contains(rendererResult)) {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long millis = timeUnit.toMillis(j);
            long millis2 = timeUnit.toMillis(j2);
            TimeUnit timeUnit2 = TimeUnit.MICROSECONDS;
            long millis3 = timeUnit2.toMillis(j3);
            long millis4 = timeUnit2.toMillis(j4);
            long millis5 = timeUnit2.toMillis(j5);
            StringBuilder sb = new StringBuilder("VideoRenderer ");
            sb.append(rendererResult);
            sb.append(" frame (releaseMs = ");
            sb.append(millis);
            sb.append(", systemMs = ");
            sb.append(millis2);
            sb.append(", ptsMs = ");
            sb.append(millis3);
            sb.append(", audioMs = ");
            sb.append(millis4);
            sb.append(", lateByMs = ");
            MpbLog.logf(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, millis5, ")"), new Object[0]);
        }
    }

    public final SubmitBufferResult processOutputBuffer(long j, long j2, boolean z) {
        VideoRenderer videoRenderer;
        int i;
        long j3 = j - j2;
        if (j3 >= 30000) {
            logRendererResult((z || !shouldPlayWhenReady()) ? RendererResult.SKIP : RendererResult.DROP, j2, j, j3);
            i = -1;
            videoRenderer = this;
        } else if (Math.abs(j3) <= 30000) {
            if (shouldPlayWhenReady()) {
                videoRenderer = this;
                videoRenderer.logRendererResult(RendererResult.RENDER, j2, j, j3);
                i = -2;
            } else {
                videoRenderer = this;
                videoRenderer.logRendererResult(RendererResult.DELAY, j2, j, j3);
                i = 0;
            }
            if (!videoRenderer.hasTriggeredReadyToPlay.getAndSet(true)) {
                videoRenderer.mediaPipelineContext.onVideoRendererReadyToPlay(j2);
            }
        } else {
            videoRenderer = this;
            videoRenderer.logRendererResult(RendererResult.DELAY, j2, j, j3);
            i = 0;
        }
        SubmitBufferResult submitBufferResult = videoRenderer.submitVideoBufferResult;
        submitBufferResult.bytesRead = i;
        submitBufferResult.adjustedReleaseTimeNs = -3L;
        return submitBufferResult;
    }

    public final SubmitBufferResult processOutputBufferV21(long j, long j2, boolean z, long j3) {
        long j4;
        int i;
        RendererResult rendererResult;
        long jNanoTime = System.nanoTime();
        long adjustedFrameReleaseTimeUs = getAdjustedFrameReleaseTimeUs(j2, j, jNanoTime, j3);
        long micros = TimeUnit.NANOSECONDS.toMicros(jNanoTime - adjustedFrameReleaseTimeUs);
        if (micros >= 30000) {
            j4 = adjustedFrameReleaseTimeUs;
            logRendererResultV21((z || !shouldPlayWhenReady()) ? RendererResult.SKIP : RendererResult.DROP, j4, jNanoTime, j2, j, micros);
            i = -1;
        } else {
            j4 = adjustedFrameReleaseTimeUs;
            int i2 = 0;
            if (Math.abs(micros) <= this.avSyncRenderThresholdV21Us) {
                if (shouldPlayWhenReady()) {
                    rendererResult = RendererResult.RENDER;
                    i2 = -2;
                } else {
                    rendererResult = RendererResult.DELAY;
                }
                logRendererResultV21(rendererResult, j4, jNanoTime, j2, j, micros);
                if (!this.hasTriggeredReadyToPlay.getAndSet(true)) {
                    this.mediaPipelineContext.onVideoRendererReadyToPlay(j2);
                }
                i = i2;
            } else {
                logRendererResultV21(RendererResult.DELAY, j4, jNanoTime, j2, j, micros);
                i = 0;
            }
        }
        SubmitBufferResult submitBufferResult = this.submitVideoBufferResult;
        submitBufferResult.bytesRead = i;
        submitBufferResult.adjustedReleaseTimeNs = j4;
        return submitBufferResult;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        super.release();
        if (this.isAdjustedTimeFrameReleaseEnabled) {
            this.frameReleaseTimeHelper.disable();
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public boolean rendersToSurface() {
        return true;
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void setVolume(float f) {
        throw new UnsupportedOperationException("VideoRenderer does not support volume change.");
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    @VisibleForTesting
    @NotNull
    public SubmitBufferResult submitBuffer(long j, long j2, boolean z, int i, int i2, int i3, @Nullable ByteBuffer byteBuffer, long j3) throws MediaPipelineBackendException {
        return this.isAdjustedTimeFrameReleaseEnabled ? processOutputBufferV21(j, j2, z, j3) : processOutputBuffer(j, j2, z);
    }

    @Nullable
    /* JADX INFO: renamed from: getPassthroughMimeType, reason: collision with other method in class */
    public Void m231getPassthroughMimeType() {
        return this.passthroughMimeType;
    }

    @Nullable
    /* JADX INFO: renamed from: getTimeSource, reason: collision with other method in class */
    public Void m232getTimeSource() {
        return this.timeSource;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public VideoRenderer(@NotNull Context appContext, @NotNull MediaPipelineContext mediaPipelineContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        DefaultMPBInternalConfig defaultMPBInternalConfig = DefaultMPBInternalConfig.INSTANCE;
        this(new VideoFrameReleaseTimeHelper(appContext, defaultMPBInternalConfig), defaultMPBInternalConfig, mediaPipelineContext);
    }

    @Override // com.amazon.avod.mpb.media.playback.render.MediaRenderer
    public void setPlaybackSpeed(@Positive float f) {
    }
}
