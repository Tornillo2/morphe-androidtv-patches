package com.amazon.avod.mpb.media.playback.pipeline;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.VisibleForTesting;
import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import com.amazon.avod.mpb.api.callback.ErrorCallback;
import com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks;
import com.amazon.avod.mpb.api.callback.SurfaceResizerCallback;
import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.media.playback.avsync.RendererDebugTracker;
import com.amazon.avod.mpb.media.playback.mediacodec.ErrorHandler;
import com.amazon.avod.mpb.media.playback.mediacodec.UnderrunHandler;
import com.amazon.avod.mpb.media.playback.zoom.VideoRegion;
import com.amazon.avod.mpb.media.playback.zoom.ZoomCalculator;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nMediaPipelineContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaPipelineContext.kt\ncom/amazon/avod/mpb/media/playback/pipeline/MediaPipelineContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,442:1\n1#2:443\n*E\n"})
public final class MediaPipelineContext {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String TAG = "MediaPipelineContext";

    @NotNull
    public final ArrayDeque<AspectRatioChange> aspectRatioChanges;
    public boolean audioEndOfStream;
    public boolean audioRendererReadyToPlay;
    public boolean audioSourceReadyToPlay;

    @NotNull
    public final DevicePropertyProvider devicePropertyProvider;

    @NotNull
    public final ErrorHandler errorHandler;
    public boolean hasUnderrun;
    public final boolean isDeferredSurfacePlaybackEnabled;
    public final boolean isReadyToPlayGatedOnRendererState;
    public final boolean isVerboseLoggingEnabled;

    @NotNull
    public final MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks;

    @NotNull
    public final Object mutex;
    public boolean playbackStarted;

    @NotNull
    public final RendererDebugTracker rendererDebugTracker;
    public boolean surfaceAvailable;

    @NotNull
    public final SurfaceResizerCallback surfaceResizerCallback;

    @NotNull
    public final UnderrunHandler underrunHandler;
    public boolean videoEndOfStream;
    public boolean videoRendererReadyToPlay;
    public boolean videoSourceReadyToPlay;

    @NotNull
    public final ZoomCalculator zoomCalculator;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AspectRatioChange {
        public final float aspectRatio;
        public final long ptsUs;
        public final int videoWidth;

        public AspectRatioChange(long j, float f, int i) {
            this.ptsUs = j;
            this.aspectRatio = f;
            this.videoWidth = i;
        }

        public static AspectRatioChange copy$default(AspectRatioChange aspectRatioChange, long j, float f, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = aspectRatioChange.ptsUs;
            }
            if ((i2 & 2) != 0) {
                f = aspectRatioChange.aspectRatio;
            }
            if ((i2 & 4) != 0) {
                i = aspectRatioChange.videoWidth;
            }
            aspectRatioChange.getClass();
            return new AspectRatioChange(j, f, i);
        }

        public final long component1() {
            return this.ptsUs;
        }

        public final float component2() {
            return this.aspectRatio;
        }

        public final int component3() {
            return this.videoWidth;
        }

        @NotNull
        public final AspectRatioChange copy(long j, float f, int i) {
            return new AspectRatioChange(j, f, i);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AspectRatioChange)) {
                return false;
            }
            AspectRatioChange aspectRatioChange = (AspectRatioChange) obj;
            return this.ptsUs == aspectRatioChange.ptsUs && Float.compare(this.aspectRatio, aspectRatioChange.aspectRatio) == 0 && this.videoWidth == aspectRatioChange.videoWidth;
        }

        public final float getAspectRatio() {
            return this.aspectRatio;
        }

        public final long getPtsUs() {
            return this.ptsUs;
        }

        public final int getVideoWidth() {
            return this.videoWidth;
        }

        public int hashCode() {
            return ((Float.floatToIntBits(this.aspectRatio) + (FloatFloatPair$$ExternalSyntheticBackport0.m(this.ptsUs) * 31)) * 31) + this.videoWidth;
        }

        @NotNull
        public String toString() {
            return "AspectRatioChange(ptsUs=" + this.ptsUs + ", aspectRatio=" + this.aspectRatio + ", videoWidth=" + this.videoWidth + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @VisibleForTesting
    public MediaPipelineContext(@NotNull MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks, @NotNull SurfaceResizerCallback surfaceResizerCallback, @NotNull DevicePropertyProvider devicePropertyProvider, @NotNull UnderrunHandler underrunHandler, @NotNull ErrorHandler errorHandler, @NotNull ZoomCalculator zoomCalculator, boolean z, boolean z2, @NotNull RendererDebugTracker rendererDebugTracker) {
        Intrinsics.checkNotNullParameter(mediaPipelineBackendCallbacks, "mediaPipelineBackendCallbacks");
        Intrinsics.checkNotNullParameter(surfaceResizerCallback, "surfaceResizerCallback");
        Intrinsics.checkNotNullParameter(devicePropertyProvider, "devicePropertyProvider");
        Intrinsics.checkNotNullParameter(underrunHandler, "underrunHandler");
        Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
        Intrinsics.checkNotNullParameter(zoomCalculator, "zoomCalculator");
        Intrinsics.checkNotNullParameter(rendererDebugTracker, "rendererDebugTracker");
        this.mediaPipelineBackendCallbacks = mediaPipelineBackendCallbacks;
        this.surfaceResizerCallback = surfaceResizerCallback;
        this.devicePropertyProvider = devicePropertyProvider;
        this.underrunHandler = underrunHandler;
        this.errorHandler = errorHandler;
        this.zoomCalculator = zoomCalculator;
        this.surfaceAvailable = z;
        this.isReadyToPlayGatedOnRendererState = z2;
        this.rendererDebugTracker = rendererDebugTracker;
        this.mutex = new Object();
        this.isVerboseLoggingEnabled = devicePropertyProvider.isVerboseAvSyncLoggingEnabled();
        this.isDeferredSurfacePlaybackEnabled = devicePropertyProvider.isDeferredSurfacePlaybackEnabled();
        this.aspectRatioChanges = new ArrayDeque<>();
    }

    public final void addSampleReference(@NotNull ByteBuffer sampleData) {
        Intrinsics.checkNotNullParameter(sampleData, "sampleData");
        if (sampleData.isDirect()) {
            if (this.isVerboseLoggingEnabled) {
                MpbLog.logf(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("MediaPipelineContext.addSampleReference ByteBuffer@", System.identityHashCode(sampleData)), new Object[0]);
            }
            this.mediaPipelineBackendCallbacks.getSampleAddReferenceCallback().onSampleAddReference(sampleData);
        }
    }

    public final void changeVideoSizeIfRequired(long j) {
        synchronized (this.mutex) {
            Float fValueOf = null;
            long j2 = Long.MIN_VALUE;
            int i = 0;
            while (true) {
                AspectRatioChange aspectRatioChangePeek = this.aspectRatioChanges.peek();
                if (aspectRatioChangePeek == null || aspectRatioChangePeek.ptsUs > j) {
                    break;
                }
                this.aspectRatioChanges.remove();
                fValueOf = Float.valueOf(aspectRatioChangePeek.aspectRatio);
                i = aspectRatioChangePeek.videoWidth;
                j2 = aspectRatioChangePeek.ptsUs;
            }
            if (fValueOf == null) {
                return;
            }
            MpbLog.logf("MediaPipelineContext.changeVideoSizeIfRequired committing aspectRatio " + fValueOf + " width=" + i + " ptsUs=" + j2 + " (lastRenderedPtsUs=" + j + ")", new Object[0]);
            this.zoomCalculator.setAspectRatio(i, fValueOf.floatValue());
            VideoRegion videoRegion = this.zoomCalculator.getVideoRegion();
            this.surfaceResizerCallback.setViewport(videoRegion.fromLeft, videoRegion.fromTop, videoRegion.width, videoRegion.height);
        }
    }

    public final void checkEndOfStream() {
        if (!Thread.holdsLock(this.mutex)) {
            throw new IllegalStateException("checkEndOfStream must be called after locking the mutex!");
        }
        if (this.audioEndOfStream && this.videoEndOfStream) {
            MpbLog.logf("MediaPipelineContext.checkEndOfStream both audio and video ended, notifying endOfStream", new Object[0]);
            this.mediaPipelineBackendCallbacks.getEndOfStreamCallback().onEndOfStream();
        }
    }

    public final void checkReadyToPlay() {
        boolean z;
        if (!Thread.holdsLock(this.mutex)) {
            throw new IllegalStateException("checkReadyToPlay must be called after locking the mutex");
        }
        boolean z2 = this.isDeferredSurfacePlaybackEnabled;
        boolean z3 = z2 || this.surfaceAvailable;
        boolean z4 = this.audioSourceReadyToPlay;
        if (z4 && (z = this.videoSourceReadyToPlay)) {
            boolean z5 = this.isReadyToPlayGatedOnRendererState;
            if ((!z5 || (this.audioRendererReadyToPlay && this.videoRendererReadyToPlay)) && z3) {
                MpbLog.logf("MediaPipelineContext.checkReadyToPlay calling onReadyToPlay [audioSource:" + z4 + " videoSource:" + z + " audioRenderer:" + this.audioRendererReadyToPlay + " videoRenderer:" + this.videoRendererReadyToPlay + " readyToPlayGatedOnRendererState:" + z5 + " surface:" + this.surfaceAvailable + " deferred:" + z2 + "]", new Object[0]);
                this.mediaPipelineBackendCallbacks.getReadyToPlayCallback().onReadyToPlay();
                this.hasUnderrun = false;
            }
        }
    }

    public final void checkUnderrun() {
        if (!Thread.holdsLock(this.mutex)) {
            throw new IllegalStateException("checkUnderrun must be called after locking the mutex");
        }
        if (!this.playbackStarted || this.hasUnderrun) {
            return;
        }
        MpbLog.logf("MediaPipelineContext.checkUnderrun audioReady:" + this.audioSourceReadyToPlay + " videoReady:" + this.videoSourceReadyToPlay + ", notifying underrun", new Object[0]);
        this.hasUnderrun = true;
        this.mediaPipelineBackendCallbacks.getBufferUnderrunCallback().onBufferUnderrun();
        this.underrunHandler.handleBufferUnderrun();
    }

    @NotNull
    public final DevicePropertyProvider getDevicePropertyProvider() {
        return this.devicePropertyProvider;
    }

    @NotNull
    public final RendererDebugTracker getRendererDebugTracker() {
        return this.rendererDebugTracker;
    }

    public final boolean isReadyToPlayGatedOnRendererState() {
        return this.isReadyToPlayGatedOnRendererState;
    }

    public final void onAudioBufferFlush() {
        synchronized (this.mutex) {
            if (this.audioSourceReadyToPlay) {
                MpbLog.logf("MediaPipelineContext.onAudioBufferFlush", new Object[0]);
                this.audioSourceReadyToPlay = false;
            }
        }
    }

    public final void onAudioBufferReadyToPlay() {
        synchronized (this.mutex) {
            if (!this.audioSourceReadyToPlay) {
                MpbLog.logf("MediaPipelineContext.onAudioBufferReadyToPlay", new Object[0]);
                this.audioSourceReadyToPlay = true;
                checkReadyToPlay();
            }
        }
    }

    public final void onAudioBufferUnderrun() {
        synchronized (this.mutex) {
            if (this.audioSourceReadyToPlay) {
                MpbLog.logf("MediaPipelineContext.onAudioBufferUnderrun", new Object[0]);
                this.audioSourceReadyToPlay = false;
                checkUnderrun();
            }
        }
    }

    public final void onAudioEndOfStream() {
        synchronized (this.mutex) {
            if (!this.audioEndOfStream) {
                MpbLog.logf("MediaPipelineContext.onAudioEndOfStream", new Object[0]);
                this.audioEndOfStream = true;
                checkEndOfStream();
            }
        }
    }

    public final void onAudioRendererFlush() {
        synchronized (this.mutex) {
            if (this.audioRendererReadyToPlay && this.isReadyToPlayGatedOnRendererState) {
                MpbLog.logf("MediaPipelineContext.onAudioRendererFlush", new Object[0]);
                this.audioRendererReadyToPlay = false;
            }
        }
    }

    public final void onAudioRendererReadyToPlay(long j) {
        synchronized (this.mutex) {
            if (!this.audioRendererReadyToPlay && this.isReadyToPlayGatedOnRendererState) {
                MpbLog.logf("MediaPipelineContext.onAudioRendererReadyToPlay audioPtsMs:" + TimeUnit.MICROSECONDS.toMillis(j), new Object[0]);
                this.audioRendererReadyToPlay = true;
                checkReadyToPlay();
            }
        }
    }

    public final void onError(@NotNull String description, @NotNull MediaPipelineBackendResultCode errorCode, @NotNull String componentName, @NotNull ErrorCallback.ErrorSeverity severity) {
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Intrinsics.checkNotNullParameter(severity, "severity");
        this.errorHandler.onError(description, errorCode, componentName, severity);
    }

    public final void onPlaybackStarted() {
        synchronized (this.mutex) {
            if (!this.playbackStarted) {
                MpbLog.logf("MediaPipelineContext.onPlaybackStarted", new Object[0]);
                this.playbackStarted = true;
                this.mediaPipelineBackendCallbacks.getPlaybackStartedCallback().onPlaybackStarted();
            }
        }
    }

    public final void onPlaybackStop() {
        synchronized (this.mutex) {
            if (this.playbackStarted) {
                MpbLog.logf("MediaPipelineContext.onPlaybackStop", new Object[0]);
                this.playbackStarted = false;
            }
        }
    }

    public final void onSurfaceAvailable() {
        synchronized (this.mutex) {
            if (!this.surfaceAvailable) {
                MpbLog.logf("MediaPipelineContext.onSurfaceAvailable", new Object[0]);
                this.surfaceAvailable = true;
                checkReadyToPlay();
            }
        }
    }

    public final void onSurfaceUnavailable() {
        synchronized (this.mutex) {
            if (this.surfaceAvailable) {
                MpbLog.logf("MediaPipelineContext.onSurfaceUnavailable", new Object[0]);
                this.surfaceAvailable = false;
            }
        }
    }

    public final void onVideoAspectRatioChange(long j, float f, int i) {
        MpbLog.logf("MediaPipelineContext.onVideoAspectRatioChange ptsUs: " + j + " aspectRatio: " + f + " videoWidth: " + i, new Object[0]);
        synchronized (this.mutex) {
            this.aspectRatioChanges.add(new AspectRatioChange(j, f, i));
        }
    }

    public final void onVideoBufferFlush() {
        synchronized (this.mutex) {
            if (this.videoSourceReadyToPlay) {
                MpbLog.logf("MediaPipelineContext.onVideoBufferFlush", new Object[0]);
                this.videoSourceReadyToPlay = false;
                this.aspectRatioChanges.clear();
            }
        }
    }

    public final void onVideoBufferReadyToPlay() {
        synchronized (this.mutex) {
            if (!this.videoSourceReadyToPlay) {
                MpbLog.logf("MediaPipelineContext.onVideoBufferReadyToPlay", new Object[0]);
                this.videoSourceReadyToPlay = true;
                checkReadyToPlay();
            }
        }
    }

    public final void onVideoBufferUnderrun() {
        synchronized (this.mutex) {
            if (this.videoSourceReadyToPlay) {
                MpbLog.logf("MediaPipelineContext.onVideoBufferUnderrun", new Object[0]);
                this.videoSourceReadyToPlay = false;
                checkUnderrun();
            }
        }
    }

    public final void onVideoEndOfStream() {
        synchronized (this.mutex) {
            if (!this.videoEndOfStream) {
                MpbLog.logf("MediaPipelineContext.onVideoEndOfStream", new Object[0]);
                this.videoEndOfStream = true;
                checkEndOfStream();
            }
        }
    }

    public final void onVideoRendererFlush() {
        synchronized (this.mutex) {
            if (this.videoRendererReadyToPlay && this.isReadyToPlayGatedOnRendererState) {
                MpbLog.logf("MediaPipelineContext.onVideoRendererFlush", new Object[0]);
                this.videoRendererReadyToPlay = false;
            }
        }
    }

    public final void onVideoRendererReadyToPlay(long j) {
        synchronized (this.mutex) {
            if (!this.videoRendererReadyToPlay && this.isReadyToPlayGatedOnRendererState) {
                MpbLog.logf("MediaPipelineContext.onVideoRendererReadyToPlay videoPtsMs:" + TimeUnit.MICROSECONDS.toMillis(j), new Object[0]);
                this.videoRendererReadyToPlay = true;
                checkReadyToPlay();
            }
        }
    }

    public final void removeSampleReference(@NotNull ByteBuffer sampleData) {
        Intrinsics.checkNotNullParameter(sampleData, "sampleData");
        if (sampleData.isDirect()) {
            if (this.isVerboseLoggingEnabled) {
                MpbLog.logf(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("MediaPipelineContext.removeSampleReference ByteBuffer@", System.identityHashCode(sampleData)), new Object[0]);
            }
            this.mediaPipelineBackendCallbacks.getSampleRemoveReferenceCallback().onSampleRemoveReference(sampleData);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaPipelineContext(@NotNull MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks, @NotNull SurfaceResizerCallback surfaceResizerCallback, @NotNull DevicePropertyProvider devicePropertyProvider, @NotNull UnderrunHandler underrunHandler, @NotNull ErrorHandler errorHandler, @NotNull ZoomCalculator zoomCalculator, boolean z, boolean z2) {
        this(mediaPipelineBackendCallbacks, surfaceResizerCallback, devicePropertyProvider, underrunHandler, errorHandler, zoomCalculator, z, z2, new RendererDebugTracker());
        Intrinsics.checkNotNullParameter(mediaPipelineBackendCallbacks, "mediaPipelineBackendCallbacks");
        Intrinsics.checkNotNullParameter(surfaceResizerCallback, "surfaceResizerCallback");
        Intrinsics.checkNotNullParameter(devicePropertyProvider, "devicePropertyProvider");
        Intrinsics.checkNotNullParameter(underrunHandler, "underrunHandler");
        Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
        Intrinsics.checkNotNullParameter(zoomCalculator, "zoomCalculator");
    }
}
