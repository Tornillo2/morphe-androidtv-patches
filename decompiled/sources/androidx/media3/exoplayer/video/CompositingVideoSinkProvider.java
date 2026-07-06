package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.Pair;
import android.view.Surface;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PreviewingVideoGraph;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.video.VideoFrameRenderControl;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.RegularImmutableList;
import j$.util.Objects;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
@UnstableApi
public final class CompositingVideoSinkProvider implements VideoSinkProvider, VideoGraph.Listener, VideoFrameRenderControl.FrameRenderer {
    public static final Executor NO_OP_EXECUTOR = new CompositingVideoSinkProvider$$ExternalSyntheticLambda5();
    public static final int STATE_CREATED = 0;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_RELEASED = 2;
    public Clock clock;
    public final Context context;

    @Nullable
    public Pair<Surface, Size> currentSurfaceAndSize;
    public HandlerWrapper handler;
    public VideoSink.Listener listener;
    public Executor listenerExecutor;
    public Format outputFormat;
    public int pendingFlushCount;
    public final PreviewingVideoGraph.Factory previewingVideoGraphFactory;
    public int state;
    public List<Effect> videoEffects;
    public VideoFrameMetadataListener videoFrameMetadataListener;
    public VideoFrameReleaseControl videoFrameReleaseControl;
    public VideoFrameRenderControl videoFrameRenderControl;
    public PreviewingVideoGraph videoGraph;
    public VideoSinkImpl videoSinkImpl;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean built;
        public final Context context;
        public PreviewingVideoGraph.Factory previewingVideoGraphFactory;
        public VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public Builder(Context context) {
            this.context = context;
        }

        public CompositingVideoSinkProvider build() {
            Assertions.checkState(!this.built);
            if (this.previewingVideoGraphFactory == null) {
                if (this.videoFrameProcessorFactory == null) {
                    this.videoFrameProcessorFactory = new ReflectiveDefaultVideoFrameProcessorFactory();
                }
                this.previewingVideoGraphFactory = new ReflectivePreviewingSingleInputVideoGraphFactory(this.videoFrameProcessorFactory);
            }
            CompositingVideoSinkProvider compositingVideoSinkProvider = new CompositingVideoSinkProvider(this);
            this.built = true;
            return compositingVideoSinkProvider;
        }

        public Builder setPreviewingVideoGraphFactory(PreviewingVideoGraph.Factory factory) {
            this.previewingVideoGraphFactory = factory;
            return this;
        }

        public Builder setVideoFrameProcessorFactory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ReflectiveDefaultVideoFrameProcessorFactory implements VideoFrameProcessor.Factory {
        public static final Supplier<VideoFrameProcessor.Factory> VIDEO_FRAME_PROCESSOR_FACTORY_SUPPLIER = Suppliers.memoize(new CompositingVideoSinkProvider$ReflectiveDefaultVideoFrameProcessorFactory$$ExternalSyntheticLambda0());

        public static VideoFrameProcessor.Factory $r8$lambda$TJ59_YgST3VJV61yINw6I4DUL8w() {
            try {
                Class<?> cls = Class.forName("androidx.media3.effect.DefaultVideoFrameProcessor$Factory$Builder");
                Object objInvoke = cls.getMethod("build", null).invoke(cls.getConstructor(null).newInstance(null), null);
                objInvoke.getClass();
                return (VideoFrameProcessor.Factory) objInvoke;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        public ReflectiveDefaultVideoFrameProcessorFactory() {
        }

        @Override // androidx.media3.common.VideoFrameProcessor.Factory
        public VideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, VideoFrameProcessor.Listener listener) throws VideoFrameProcessingException {
            return VIDEO_FRAME_PROCESSOR_FACTORY_SUPPLIER.get().create(context, debugViewProvider, colorInfo, z, executor, listener);
        }

        public ReflectiveDefaultVideoFrameProcessorFactory(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ReflectivePreviewingSingleInputVideoGraphFactory implements PreviewingVideoGraph.Factory {
        public final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public ReflectivePreviewingSingleInputVideoGraphFactory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
        }

        @Override // androidx.media3.common.PreviewingVideoGraph.Factory
        public PreviewingVideoGraph create(Context context, ColorInfo colorInfo, ColorInfo colorInfo2, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, List<Effect> list, long j) throws VideoFrameProcessingException {
            try {
                return ((PreviewingVideoGraph.Factory) Class.forName("androidx.media3.effect.PreviewingSingleInputVideoGraph$Factory").getConstructor(VideoFrameProcessor.Factory.class).newInstance(this.videoFrameProcessorFactory)).create(context, colorInfo, colorInfo2, debugViewProvider, listener, executor, list, j);
            } catch (Exception e) {
                throw VideoFrameProcessingException.from(e);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class VideoSinkImpl implements VideoSink {
        public final CompositingVideoSinkProvider compositingVideoSinkProvider;
        public final Context context;
        public boolean hasRegisteredFirstInputStream;

        @Nullable
        public Format inputFormat;
        public long inputStreamOffsetUs;
        public int inputType;
        public long pendingInputStreamBufferPresentationTimeUs;
        public boolean pendingInputStreamOffsetChange;

        @Nullable
        public Effect rotationEffect;
        public final VideoFrameProcessor videoFrameProcessor;
        public final int videoFrameProcessorMaxPendingFrameCount;
        public final ArrayList<Effect> videoEffects = new ArrayList<>();
        public long finalBufferPresentationTimeUs = -9223372036854775807L;
        public long lastBufferPresentationTimeUs = -9223372036854775807L;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class ScaleAndRotateAccessor {
            public static Method buildScaleAndRotateTransformationMethod;
            public static Constructor<?> scaleAndRotateTransformationBuilderConstructor;
            public static Method setRotationMethod;

            public static Effect createRotationEffect(float f) {
                try {
                    prepare();
                    Object objNewInstance = scaleAndRotateTransformationBuilderConstructor.newInstance(null);
                    setRotationMethod.invoke(objNewInstance, Float.valueOf(f));
                    Object objInvoke = buildScaleAndRotateTransformationMethod.invoke(objNewInstance, null);
                    objInvoke.getClass();
                    return (Effect) objInvoke;
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }

            @EnsuresNonNull({"scaleAndRotateTransformationBuilderConstructor", "setRotationMethod", "buildScaleAndRotateTransformationMethod"})
            public static void prepare() throws NoSuchMethodException, ClassNotFoundException {
                if (scaleAndRotateTransformationBuilderConstructor == null || setRotationMethod == null || buildScaleAndRotateTransformationMethod == null) {
                    Class<?> cls = Class.forName("androidx.media3.effect.ScaleAndRotateTransformation$Builder");
                    scaleAndRotateTransformationBuilderConstructor = cls.getConstructor(null);
                    setRotationMethod = cls.getMethod("setRotationDegrees", Float.TYPE);
                    buildScaleAndRotateTransformationMethod = cls.getMethod("build", null);
                }
            }
        }

        public VideoSinkImpl(Context context, CompositingVideoSinkProvider compositingVideoSinkProvider, PreviewingVideoGraph previewingVideoGraph) throws VideoFrameProcessingException {
            this.context = context;
            this.compositingVideoSinkProvider = compositingVideoSinkProvider;
            this.videoFrameProcessorMaxPendingFrameCount = Util.getMaxPendingFramesCountForMediaCodecDecoders(context);
            this.videoFrameProcessor = previewingVideoGraph.getProcessor(previewingVideoGraph.registerInput());
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void flush() {
            this.videoFrameProcessor.flush();
            this.hasRegisteredFirstInputStream = false;
            this.finalBufferPresentationTimeUs = -9223372036854775807L;
            this.lastBufferPresentationTimeUs = -9223372036854775807L;
            this.compositingVideoSinkProvider.flush();
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public Surface getInputSurface() {
            return this.videoFrameProcessor.getInputSurface();
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean isEnded() {
            long j = this.finalBufferPresentationTimeUs;
            return j != -9223372036854775807L && this.compositingVideoSinkProvider.hasReleasedFrame(j);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean isFrameDropAllowedOnInput() {
            return Util.isFrameDropAllowedOnSurfaceInput(this.context);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean isReady() {
            return this.compositingVideoSinkProvider.isReady();
        }

        public final void maybeRegisterInputStream() {
            if (this.inputFormat == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            Effect effect = this.rotationEffect;
            if (effect != null) {
                arrayList.add(effect);
            }
            arrayList.addAll(this.videoEffects);
            Format format = this.inputFormat;
            format.getClass();
            VideoFrameProcessor videoFrameProcessor = this.videoFrameProcessor;
            int i = this.inputType;
            FrameInfo.Builder builder = new FrameInfo.Builder(CompositingVideoSinkProvider.getAdjustedInputColorInfo(format.colorInfo), format.width, format.height);
            builder.pixelWidthHeightRatio = format.pixelWidthHeightRatio;
            videoFrameProcessor.registerInputStream(i, arrayList, builder.build());
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean queueBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
            VideoFrameProcessor videoFrameProcessor = this.videoFrameProcessor;
            Assertions.checkStateNotNull(videoFrameProcessor);
            return videoFrameProcessor.queueInputBitmap(bitmap, timestampIterator);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public long registerInputFrame(long j, boolean z) {
            Assertions.checkState(this.videoFrameProcessorMaxPendingFrameCount != -1);
            long j2 = this.pendingInputStreamBufferPresentationTimeUs;
            if (j2 != -9223372036854775807L) {
                if (!this.compositingVideoSinkProvider.hasReleasedFrame(j2)) {
                    return -9223372036854775807L;
                }
                maybeRegisterInputStream();
                this.pendingInputStreamBufferPresentationTimeUs = -9223372036854775807L;
            }
            if (this.videoFrameProcessor.getPendingInputFrameCount() >= this.videoFrameProcessorMaxPendingFrameCount || !this.videoFrameProcessor.registerInputFrame()) {
                return -9223372036854775807L;
            }
            long j3 = this.inputStreamOffsetUs;
            long j4 = j + j3;
            if (this.pendingInputStreamOffsetChange) {
                this.compositingVideoSinkProvider.onStreamOffsetChange(j4, j3);
                this.pendingInputStreamOffsetChange = false;
            }
            this.lastBufferPresentationTimeUs = j4;
            if (z) {
                this.finalBufferPresentationTimeUs = j4;
            }
            return j4 * 1000;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void registerInputStream(int i, Format format) {
            int i2;
            Format format2;
            if (i != 1 && i != 2) {
                throw new UnsupportedOperationException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unsupported input type ", i));
            }
            if (i != 1 || Util.SDK_INT >= 21 || (i2 = format.rotationDegrees) == -1 || i2 == 0) {
                this.rotationEffect = null;
            } else if (this.rotationEffect == null || (format2 = this.inputFormat) == null || format2.rotationDegrees != i2) {
                this.rotationEffect = ScaleAndRotateAccessor.createRotationEffect(i2);
            }
            this.inputType = i;
            this.inputFormat = format;
            if (this.hasRegisteredFirstInputStream) {
                Assertions.checkState(this.lastBufferPresentationTimeUs != -9223372036854775807L);
                this.pendingInputStreamBufferPresentationTimeUs = this.lastBufferPresentationTimeUs;
            } else {
                maybeRegisterInputStream();
                this.hasRegisteredFirstInputStream = true;
                this.pendingInputStreamBufferPresentationTimeUs = -9223372036854775807L;
            }
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void render(long j, long j2) throws VideoSink.VideoSinkException {
            try {
                this.compositingVideoSinkProvider.render(j, j2);
            } catch (ExoPlaybackException e) {
                Format format = this.inputFormat;
                if (format == null) {
                    format = new Format(new Format.Builder());
                }
                throw new VideoSink.VideoSinkException(e, format);
            }
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setListener(VideoSink.Listener listener, Executor executor) {
            this.compositingVideoSinkProvider.setListener(listener, executor);
        }

        public void setPendingVideoEffects(List<Effect> list) {
            this.videoEffects.clear();
            this.videoEffects.addAll(list);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setPlaybackSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
            this.compositingVideoSinkProvider.setPlaybackSpeed(f);
        }

        public void setStreamOffsetUs(long j) {
            this.pendingInputStreamOffsetChange = this.inputStreamOffsetUs != j;
            this.inputStreamOffsetUs = j;
        }

        public void setVideoEffects(List<Effect> list) {
            setPendingVideoEffects(list);
            maybeRegisterInputStream();
        }
    }

    public static /* synthetic */ void $r8$lambda$7nNKOZEuhILgeUxYc1X5x_n1te8(CompositingVideoSinkProvider compositingVideoSinkProvider, VideoSink.Listener listener, VideoFrameProcessingException videoFrameProcessingException) {
        VideoSinkImpl videoSinkImpl = compositingVideoSinkProvider.videoSinkImpl;
        Assertions.checkStateNotNull(videoSinkImpl);
        Format format = videoSinkImpl.inputFormat;
        Assertions.checkStateNotNull(format);
        listener.onError(videoSinkImpl, new VideoSink.VideoSinkException(videoFrameProcessingException, format));
    }

    public static /* synthetic */ void $r8$lambda$LLDxx0VssCIlSpsG9k8uVjcQw3A(CompositingVideoSinkProvider compositingVideoSinkProvider, VideoSink.Listener listener) {
        VideoSinkImpl videoSinkImpl = compositingVideoSinkProvider.videoSinkImpl;
        Assertions.checkStateNotNull(videoSinkImpl);
        listener.onFrameDropped(videoSinkImpl);
    }

    public static ColorInfo getAdjustedInputColorInfo(@Nullable ColorInfo colorInfo) {
        return (colorInfo == null || !ColorInfo.isTransferHdr(colorInfo)) ? ColorInfo.SDR_BT709_LIMITED : colorInfo;
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void clearOutputSurfaceInfo() {
        Size size = Size.UNKNOWN;
        maybeSetOutputSurfaceInfo(null, size.width, size.height);
        this.currentSurfaceAndSize = null;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameRenderControl.FrameRenderer
    public void dropFrame() {
        final VideoSink.Listener listener = this.listener;
        this.listenerExecutor.execute(new Runnable() { // from class: androidx.media3.exoplayer.video.CompositingVideoSinkProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CompositingVideoSinkProvider.$r8$lambda$LLDxx0VssCIlSpsG9k8uVjcQw3A(this.f$0, listener);
            }
        });
        PreviewingVideoGraph previewingVideoGraph = this.videoGraph;
        Assertions.checkStateNotNull(previewingVideoGraph);
        previewingVideoGraph.renderOutputFrame(-2L);
    }

    public final void flush() {
        this.pendingFlushCount++;
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        videoFrameRenderControl.flush();
        HandlerWrapper handlerWrapper = this.handler;
        Assertions.checkStateNotNull(handlerWrapper);
        handlerWrapper.post(new Runnable() { // from class: androidx.media3.exoplayer.video.CompositingVideoSinkProvider$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.flushInternal();
            }
        });
    }

    public final void flushInternal() {
        int i = this.pendingFlushCount - 1;
        this.pendingFlushCount = i;
        if (i > 0) {
            return;
        }
        if (i < 0) {
            throw new IllegalStateException(String.valueOf(this.pendingFlushCount));
        }
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        videoFrameRenderControl.flush();
    }

    @Nullable
    public Surface getOutputSurface() {
        Pair<Surface, Size> pair = this.currentSurfaceAndSize;
        if (pair != null) {
            return (Surface) pair.first;
        }
        return null;
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public VideoSink getSink() {
        VideoSinkImpl videoSinkImpl = this.videoSinkImpl;
        Assertions.checkStateNotNull(videoSinkImpl);
        return videoSinkImpl;
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    @Nullable
    public VideoFrameReleaseControl getVideoFrameReleaseControl() {
        return this.videoFrameReleaseControl;
    }

    public final boolean hasReleasedFrame(long j) {
        if (this.pendingFlushCount != 0) {
            return false;
        }
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        return videoFrameRenderControl.hasReleasedFrame(j);
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void initialize(Format format) throws VideoSink.VideoSinkException {
        ColorInfo colorInfoBuild;
        boolean z = false;
        Assertions.checkState(this.state == 0);
        Assertions.checkStateNotNull(this.videoEffects);
        if (this.videoFrameRenderControl != null && this.videoFrameReleaseControl != null) {
            z = true;
        }
        Assertions.checkState(z);
        Clock clock = this.clock;
        Looper looperMyLooper = Looper.myLooper();
        Assertions.checkStateNotNull(looperMyLooper);
        this.handler = clock.createHandler(looperMyLooper, null);
        ColorInfo adjustedInputColorInfo = getAdjustedInputColorInfo(format.colorInfo);
        if (adjustedInputColorInfo.colorTransfer == 7) {
            ColorInfo.Builder builder = new ColorInfo.Builder(adjustedInputColorInfo);
            builder.colorTransfer = 6;
            colorInfoBuild = builder.build();
        } else {
            colorInfoBuild = adjustedInputColorInfo;
        }
        try {
            PreviewingVideoGraph.Factory factory = this.previewingVideoGraphFactory;
            Context context = this.context;
            DebugViewProvider debugViewProvider = DebugViewProvider.NONE;
            final HandlerWrapper handlerWrapper = this.handler;
            Objects.requireNonNull(handlerWrapper);
            try {
                this.videoGraph = factory.create(context, adjustedInputColorInfo, colorInfoBuild, debugViewProvider, this, new Executor() { // from class: androidx.media3.exoplayer.video.CompositingVideoSinkProvider$$ExternalSyntheticLambda3
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        handlerWrapper.post(runnable);
                    }
                }, RegularImmutableList.EMPTY, 0L);
                Pair<Surface, Size> pair = this.currentSurfaceAndSize;
                if (pair != null) {
                    Surface surface = (Surface) pair.first;
                    Size size = (Size) pair.second;
                    maybeSetOutputSurfaceInfo(surface, size.width, size.height);
                }
                VideoSinkImpl videoSinkImpl = new VideoSinkImpl(this.context, this, this.videoGraph);
                this.videoSinkImpl = videoSinkImpl;
                List<Effect> list = this.videoEffects;
                list.getClass();
                videoSinkImpl.setVideoEffects(list);
                this.state = 1;
            } catch (VideoFrameProcessingException e) {
                e = e;
                throw new VideoSink.VideoSinkException(e, format);
            }
        } catch (VideoFrameProcessingException e2) {
            e = e2;
        }
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public boolean isInitialized() {
        return this.state == 1;
    }

    public final boolean isReady() {
        if (this.pendingFlushCount != 0) {
            return false;
        }
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        return videoFrameRenderControl.isReady();
    }

    public final void maybeSetOutputSurfaceInfo(@Nullable Surface surface, int i, int i2) {
        if (this.videoGraph != null) {
            this.videoGraph.setOutputSurfaceInfo(surface != null ? new SurfaceInfo(surface, i, i2, 0) : null);
            VideoFrameReleaseControl videoFrameReleaseControl = this.videoFrameReleaseControl;
            videoFrameReleaseControl.getClass();
            videoFrameReleaseControl.setOutputSurface(surface);
        }
    }

    @Override // androidx.media3.common.VideoGraph.Listener
    public void onEnded(long j) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.common.VideoGraph.Listener
    public void onError(final VideoFrameProcessingException videoFrameProcessingException) {
        final VideoSink.Listener listener = this.listener;
        this.listenerExecutor.execute(new Runnable() { // from class: androidx.media3.exoplayer.video.CompositingVideoSinkProvider$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CompositingVideoSinkProvider.$r8$lambda$7nNKOZEuhILgeUxYc1X5x_n1te8(this.f$0, listener, videoFrameProcessingException);
            }
        });
    }

    @Override // androidx.media3.common.VideoGraph.Listener
    public void onOutputFrameAvailableForRendering(long j) {
        if (this.pendingFlushCount > 0) {
            return;
        }
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        videoFrameRenderControl.onOutputFrameAvailableForRendering(j);
    }

    @Override // androidx.media3.common.VideoGraph.Listener
    public void onOutputSizeChanged(int i, int i2) {
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        videoFrameRenderControl.onOutputSizeChanged(i, i2);
    }

    public final void onStreamOffsetChange(long j, long j2) {
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        videoFrameRenderControl.onStreamOffsetChange(j, j2);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameRenderControl.FrameRenderer
    public void onVideoSizeChanged(final VideoSize videoSize) {
        Format.Builder builder = new Format.Builder();
        builder.width = videoSize.width;
        builder.height = videoSize.height;
        builder.sampleMimeType = MimeTypes.normalizeMimeType(MimeTypes.VIDEO_RAW);
        this.outputFormat = new Format(builder);
        final VideoSinkImpl videoSinkImpl = this.videoSinkImpl;
        Assertions.checkStateNotNull(videoSinkImpl);
        final VideoSink.Listener listener = this.listener;
        this.listenerExecutor.execute(new Runnable() { // from class: androidx.media3.exoplayer.video.CompositingVideoSinkProvider$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                listener.getClass();
            }
        });
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void release() {
        if (this.state == 2) {
            return;
        }
        HandlerWrapper handlerWrapper = this.handler;
        if (handlerWrapper != null) {
            handlerWrapper.removeCallbacksAndMessages(null);
        }
        PreviewingVideoGraph previewingVideoGraph = this.videoGraph;
        if (previewingVideoGraph != null) {
            previewingVideoGraph.release();
        }
        this.currentSurfaceAndSize = null;
        this.state = 2;
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.pendingFlushCount == 0) {
            VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
            Assertions.checkStateNotNull(videoFrameRenderControl);
            videoFrameRenderControl.render(j, j2);
        }
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameRenderControl.FrameRenderer
    public void renderFrame(long j, long j2, long j3, boolean z) {
        if (z && this.listenerExecutor != NO_OP_EXECUTOR) {
            final VideoSinkImpl videoSinkImpl = this.videoSinkImpl;
            Assertions.checkStateNotNull(videoSinkImpl);
            final VideoSink.Listener listener = this.listener;
            this.listenerExecutor.execute(new Runnable() { // from class: androidx.media3.exoplayer.video.CompositingVideoSinkProvider$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    listener.onFirstFrameRendered(videoSinkImpl);
                }
            });
        }
        if (this.videoFrameMetadataListener != null) {
            Format format = this.outputFormat;
            this.videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j2 - j3, this.clock.nanoTime(), format == null ? new Format(new Format.Builder()) : format, null);
        }
        PreviewingVideoGraph previewingVideoGraph = this.videoGraph;
        Assertions.checkStateNotNull(previewingVideoGraph);
        previewingVideoGraph.renderOutputFrame(j);
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setClock(Clock clock) {
        Assertions.checkState(!isInitialized());
        this.clock = clock;
    }

    public final void setListener(VideoSink.Listener listener, Executor executor) {
        if (Objects.equals(listener, this.listener)) {
            Assertions.checkState(Objects.equals(executor, this.listenerExecutor));
        } else {
            this.listener = listener;
            this.listenerExecutor = executor;
        }
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setOutputSurfaceInfo(Surface surface, Size size) {
        Pair<Surface, Size> pair = this.currentSurfaceAndSize;
        if (pair != null && ((Surface) pair.first).equals(surface) && ((Size) this.currentSurfaceAndSize.second).equals(size)) {
            return;
        }
        this.currentSurfaceAndSize = Pair.create(surface, size);
        maybeSetOutputSurfaceInfo(surface, size.width, size.height);
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setPendingVideoEffects(List<Effect> list) {
        this.videoEffects = list;
        if (isInitialized()) {
            VideoSinkImpl videoSinkImpl = this.videoSinkImpl;
            Assertions.checkStateNotNull(videoSinkImpl);
            videoSinkImpl.setPendingVideoEffects(list);
        }
    }

    public final void setPlaybackSpeed(float f) {
        VideoFrameRenderControl videoFrameRenderControl = this.videoFrameRenderControl;
        Assertions.checkStateNotNull(videoFrameRenderControl);
        videoFrameRenderControl.setPlaybackSpeed(f);
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setStreamOffsetUs(long j) {
        VideoSinkImpl videoSinkImpl = this.videoSinkImpl;
        Assertions.checkStateNotNull(videoSinkImpl);
        videoSinkImpl.setStreamOffsetUs(j);
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setVideoEffects(List<Effect> list) {
        this.videoEffects = list;
        if (isInitialized()) {
            VideoSinkImpl videoSinkImpl = this.videoSinkImpl;
            Assertions.checkStateNotNull(videoSinkImpl);
            videoSinkImpl.setVideoEffects(list);
        }
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        this.videoFrameMetadataListener = videoFrameMetadataListener;
    }

    @Override // androidx.media3.exoplayer.video.VideoSinkProvider
    public void setVideoFrameReleaseControl(VideoFrameReleaseControl videoFrameReleaseControl) {
        Assertions.checkState(!isInitialized());
        this.videoFrameReleaseControl = videoFrameReleaseControl;
        this.videoFrameRenderControl = new VideoFrameRenderControl(this, videoFrameReleaseControl);
    }

    public CompositingVideoSinkProvider(Builder builder) {
        this.context = builder.context;
        PreviewingVideoGraph.Factory factory = builder.previewingVideoGraphFactory;
        Assertions.checkStateNotNull(factory);
        this.previewingVideoGraphFactory = factory;
        this.clock = Clock.DEFAULT;
        this.listener = VideoSink.Listener.NO_OP;
        this.listenerExecutor = NO_OP_EXECUTOR;
        this.state = 0;
    }

    public static /* synthetic */ void $r8$lambda$lk3rHqsqCZNBfYhevuuRP0Dt9tM(Runnable runnable) {
    }

    public static /* synthetic */ void lambda$static$0(Runnable runnable) {
    }
}
