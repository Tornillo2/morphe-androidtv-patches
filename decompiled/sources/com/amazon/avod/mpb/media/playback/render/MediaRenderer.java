package com.amazon.avod.mpb.media.playback.render;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.amazon.avod.mpb.annotate.Positive;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.playback.avsync.TimeSource;
import com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipelineContext;
import com.google.common.annotations.VisibleForTesting;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@NotThreadSafe
public abstract class MediaRenderer extends AbstractMediaComponent {
    public static final long ADJUSTED_TIME_NOT_AVAILABLE = -3;

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int DELAY_FOR_BUFFER = 0;
    public static final long FPS_SAMPLE_WINDOW_MILLIS = 1000;
    public static final int RELEASE_BUFFER_DROP = -1;
    public static final int RELEASE_BUFFER_RENDER = -2;
    public long fpsSampleStartUs;

    @JvmField
    @NotNull
    public AtomicBoolean hasTriggeredReadyToPlay;
    public boolean isResuming;
    public volatile float lastFpsSample;

    @JvmField
    @NotNull
    public final MediaPipelineContext mediaPipelineContext;
    public boolean playWhenReady;
    public int renderedFramesInCurrentSample;
    public final boolean shouldCountOverlappedSampleToFrameDrop;
    public int totalDroppedFrames;
    public int totalSkippedFrames;

    @NotNull
    public final TimeSource wallClock;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @VisibleForTesting
        public static /* synthetic */ void getFPS_SAMPLE_WINDOW_MILLIS$annotations() {
        }
    }

    @VisibleForTesting
    public MediaRenderer(@NotNull MediaPipelineContext mediaPipelineContext, @NotNull TimeSource wallClock, @NotNull MPBInternalConfig config) {
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(wallClock, "wallClock");
        Intrinsics.checkNotNullParameter(config, "config");
        this.mediaPipelineContext = mediaPipelineContext;
        this.wallClock = wallClock;
        this.shouldCountOverlappedSampleToFrameDrop = config.getCountOverlappedSampleToFrameDrop();
        this.lastFpsSample = -1.0f;
        this.hasTriggeredReadyToPlay = new AtomicBoolean(false);
    }

    public void configure(@NotNull MediaFormat mediaFormat, @Nullable Integer num, int i) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
        configure$core_mpb_release();
    }

    @NotNull
    public abstract MediaFormat estimateInitialOutputFormat(@NotNull MediaFormat mediaFormat, boolean z);

    public abstract void flush() throws MediaPipelineBackendException;

    public final float getLastFpsSample() {
        return this.lastFpsSample;
    }

    @Nullable
    public abstract String getPassthroughMimeType();

    @Nullable
    public abstract TimeSource getTimeSource();

    public final int getTotalDroppedFrames() {
        return this.totalDroppedFrames;
    }

    public final int getTotalSkippedFrames() {
        return this.totalSkippedFrames;
    }

    public abstract boolean hasUnderrun();

    public abstract boolean isPassthroughSupported(@NotNull String str);

    public abstract boolean rendersToSurface();

    public final void setPlayWhenReady() {
        this.playWhenReady = true;
    }

    public abstract void setPlaybackSpeed(@Positive float f);

    public abstract void setVolume(float f);

    public final boolean shouldPlayWhenReady() {
        return !this.mediaPipelineContext.isReadyToPlayGatedOnRendererState || this.playWhenReady;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void start() throws MediaPipelineBackendException {
        super.start();
        this.fpsSampleStartUs = 0L;
        this.renderedFramesInCurrentSample = 0;
        this.lastFpsSample = 0.0f;
        this.isResuming = true;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        super.stop();
        this.playWhenReady = false;
        this.mediaPipelineContext.onPlaybackStop();
    }

    @NotNull
    public abstract SubmitBufferResult submitBuffer(long j, long j2, boolean z, int i, int i2, int i3, @Nullable ByteBuffer byteBuffer, long j3) throws MediaPipelineBackendException;

    @NotNull
    public final SubmitBufferResult submitBuffer(long j, @NotNull MediaCodec.BufferInfo bufferInfo, @Nullable ByteBuffer byteBuffer, long j2, boolean z) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(bufferInfo, "bufferInfo");
        SubmitBufferResult submitBufferResultSubmitBuffer = submitBuffer(j, bufferInfo.presentationTimeUs, this.isResuming, bufferInfo.size, bufferInfo.offset, bufferInfo.flags, byteBuffer, j2);
        int i = submitBufferResultSubmitBuffer.bytesRead;
        boolean z2 = this.isResuming;
        if (z2 && i == -1) {
            this.totalSkippedFrames++;
        }
        if (i == -1 && !z2 && (this.shouldCountOverlappedSampleToFrameDrop || !z)) {
            this.totalDroppedFrames++;
        } else if (i == -2) {
            if (z2) {
                this.isResuming = false;
                this.mediaPipelineContext.onPlaybackStarted();
            }
            this.renderedFramesInCurrentSample++;
        }
        long currentRealTimeUs = this.wallClock.getCurrentRealTimeUs();
        long millis = TimeUnit.MICROSECONDS.toMillis(currentRealTimeUs - this.fpsSampleStartUs);
        if (millis >= 1000) {
            this.lastFpsSample = (this.renderedFramesInCurrentSample * TimeUnit.SECONDS.toMillis(1L)) / millis;
            this.renderedFramesInCurrentSample = 0;
            this.fpsSampleStartUs = currentRealTimeUs;
        }
        return submitBufferResultSubmitBuffer;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaRenderer(@NotNull MediaPipelineContext mediaPipelineContext) {
        this(mediaPipelineContext, TimeSource.DEFAULT_INSTANCE, DefaultMPBInternalConfig.INSTANCE);
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
    }
}
