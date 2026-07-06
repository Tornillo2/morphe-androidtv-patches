package com.amazon.avod.mpb.media.playback.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaFormat;
import android.os.Process;
import android.view.Surface;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.avod.mpb.annotate.CalledFromIgnite;
import com.amazon.avod.mpb.api.callback.DisplayModeManager;
import com.amazon.avod.mpb.api.callback.ErrorCallback;
import com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks;
import com.amazon.avod.mpb.api.callback.PropertyChangedCallback;
import com.amazon.avod.mpb.api.callback.SurfaceResizerCallback;
import com.amazon.avod.mpb.api.core.CapabilitiesProvider;
import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.api.core.MediaPipelineBackend;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.core.PictureMode;
import com.amazon.avod.mpb.api.core.PropertyDefinition;
import com.amazon.avod.mpb.api.sample.AudioMetadata;
import com.amazon.avod.mpb.api.sample.AudioSample;
import com.amazon.avod.mpb.api.sample.DiagnosticInfo;
import com.amazon.avod.mpb.api.sample.VideoMetadata;
import com.amazon.avod.mpb.api.sample.VideoSample;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystem;
import com.amazon.avod.mpb.media.drm.MediaCryptoSession;
import com.amazon.avod.mpb.media.drm.MediaCryptoSessionImpl;
import com.amazon.avod.mpb.media.playback.SampleType;
import com.amazon.avod.mpb.media.playback.avsync.MediaClock;
import com.amazon.avod.mpb.media.playback.avsync.TimeSource;
import com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.mpb.media.playback.pipeline.PipelineTaskType;
import com.amazon.avod.mpb.media.playback.source.MediaSource;
import com.amazon.avod.mpb.media.playback.source.MediaSourceImpl;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.mpb.media.playback.zoom.VideoRegion;
import com.amazon.avod.mpb.media.playback.zoom.ZoomCalculator;
import com.amazon.avod.mpb.media.playback.zoom.ZoomLevel;
import com.amazon.avod.mpb.threading.ScheduledExecutorBuilder;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import com.google.android.exoplayer2.video.PlaceholderSurface;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import j$.util.function.Consumer$CC;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MediaCodecRenderer implements MediaPipelineBackend, UnderrunHandler, ErrorHandler {
    public static final String DISPLAY_ASPECT_RATIO_KEY = "displayAspectRatio";
    public static final String LOG_TAG = "MediaCodecRenderer";
    public static final double UNITY_GAIN = 1.0d;
    public static final double ZERO_GAIN = 0.0d;
    public final Context mAppContext;
    public final MediaPipelineBackendPropertyStore.Property<Integer> mAudioBitrate;
    public AudioMetadata mAudioMetadata;
    public final MediaPipelineBackendPropertyStore.Property<Boolean> mAudioMute;
    public MediaTrackData mAudioTrack;
    public final MediaPipelineBackendPropertyStore.Property<Double> mAudioVolume;
    public long mCachedLastMediaTimeUs;
    public final CapabilitiesProvider mCapabilitiesProvider;
    public final MediaCodecDeviceCapabilityDetector mCapabilityDetector;
    public final MPBInternalConfig mConfig;
    public double mCurrentVolumeGain;
    public final DiagnosticInfo mDiagnosticInfo;
    public final MediaPipelineBackendPropertyStore.Property<Boolean> mDisableTunnelingMode;
    public final MediaPipelineBackendPropertyStore.Property<Float> mDisplayAspectRatio;
    public boolean mDisplayModeChangeRequested;
    public final MediaPipelineBackendPropertyStore.Property<Boolean> mDisplayModeChanging;
    public DisplayModeManager mDisplayModeManager;
    public Surface mDummySurface;
    public final FailoverManager mFailoverManager;
    public Long mFirstVideoSampleDurationMillis;
    public final MediaFormatFactory mFormatFactory;
    public volatile boolean mHadFatalError;
    public final boolean mHandleMidstreamSurfaceDestroy;
    public final boolean mIsAsyncModeEnabledByConfig;
    public final boolean mIsBackgroundAudioPlaybackSupported;
    public final boolean mIsDeferredSurfacePlaybackEnabled;
    public final boolean mIsDummyVideoSurfaceEnabled;
    public boolean mIsInitialized;
    public volatile boolean mIsRunning;
    public boolean mIsTunneledPlayback;
    public final boolean mIsVerboseLoggingEnabled;
    public volatile boolean mIsVideoPipelineInactive;
    public final boolean mIsVideoSurfaceHotSwapSupported;
    public final MediaPipelineBackendPropertyStore.Property<Boolean> mMatchFrameRate;
    public final MediaClock mMediaClock;
    public MediaCryptoSession mMediaCryptoSession;
    public MediaPipelineBackendCallbacks mMediaPipelineBackendCallbacks;
    public MediaPipelineContext mMediaPipelineContext;
    public final Object mMutex;
    public Float mPendingDisplayAspectRatio;
    public PendingDisplayModeChange mPendingDisplayModeChange;
    public boolean mPendingSurfacePlayback;
    public PictureMode mPictureMode;
    public ScheduledExecutorService mPipelineExecutor;
    public final MediaPipelineFactory mPipelineFactory;
    public final Map<SampleType, List<Future<Void>>> mPipelineTaskFuturesMap;
    public ImmutableList<PipelineTask> mPipelineTasks;
    public int mPreviousDroppedFrameCount;
    public final DevicePropertyProvider mPropertyProvider;
    public MediaPipelineBackendPropertyStore mPropertyStore;
    public final ScheduledExecutorService mSurfaceCallbackExecutor;
    public CountDownLatch mSurfaceLatch;
    public final AtomicReference<Surface> mSurfaceReference;
    public SurfaceResizerCallback mSurfaceResizerCallback;
    public final int mTickIntervalMillis;
    public final TunneledPlaybackEvaluator mTunneledPlaybackEvaluator;
    public ScheduledExecutorService mUnderrunExecutor;
    public final boolean mUseBackgroundThreadForSurfaceCallbacks;
    public final Runnable mVideoBufferConsumer;
    public ScheduledExecutorService mVideoBufferConsumerExecutor;
    public ScheduledFuture mVideoBufferConsumerFuture;
    public VideoMetadata mVideoMetadata;
    public boolean mVideoPipelineConfigured;
    public MediaTrackData mVideoTrack;
    public final ZoomCalculator mZoomCalculator;
    public final MediaPipelineBackendPropertyStore.Property<String> mZoomLevelProperty;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MediaCodecRendererFactory {
        @Nonnull
        public MediaCodecRenderer create(@Nonnull Context context, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, @Nonnull CapabilitiesProvider capabilitiesProvider, @Nonnull PictureMode pictureMode, @Nonnull DevicePropertyProvider devicePropertyProvider, @Nonnull FailoverManager failoverManager) {
            return new MediaCodecRenderer(context, mediaCodecDeviceCapabilityDetector, capabilitiesProvider, pictureMode, devicePropertyProvider, failoverManager);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaTrackData {
        public final MediaSource mBuffer;
        public final MediaFormat mFormat;
        public final MediaPipeline mPipeline;

        public MediaTrackData(@Nonnull MediaFormat mediaFormat, @Nonnull MediaSource mediaSource, @Nonnull MediaPipeline mediaPipeline) {
            Preconditions.checkNotNull(mediaSource, "buffer");
            this.mBuffer = mediaSource;
            Preconditions.checkNotNull(mediaFormat, "format");
            this.mFormat = mediaFormat;
            Preconditions.checkNotNull(mediaPipeline, "pipeline");
            this.mPipeline = mediaPipeline;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class PendingDisplayModeChange implements Consumer<Exception> {
        public static final String CALLBACK_NAME = "PendingDisplayModeChange";
        public boolean mPlayAfterCompletion = true;

        public PendingDisplayModeChange() {
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        @Override // java.util.function.Consumer
        /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public void n(Exception exc) {
            synchronized (MediaCodecRenderer.this.mMutex) {
                try {
                    MediaCodecRenderer mediaCodecRenderer = MediaCodecRenderer.this;
                    if (this != mediaCodecRenderer.mPendingDisplayModeChange) {
                        MpbLog.logf("%s.%s: Display mode change completed but it's no longer relevant", "MediaCodecRenderer", CALLBACK_NAME);
                        return;
                    }
                    mediaCodecRenderer.mPendingDisplayModeChange = null;
                    mediaCodecRenderer.mDisplayModeChanging.set(Boolean.FALSE);
                    if (exc != null) {
                        MediaCodecRenderer.this.onError("Failed to change display mode: " + exc.getMessage(), MediaPipelineBackendResultCode.AV_MPB_SET_REFRESH_RATE_FAILED, CALLBACK_NAME, ErrorCallback.ErrorSeverity.SEV_FATAL);
                        return;
                    }
                    if (this.mPlayAfterCompletion) {
                        MediaCodecRenderer mediaCodecRenderer2 = MediaCodecRenderer.this;
                        if (mediaCodecRenderer2.mIsDeferredSurfacePlaybackEnabled && mediaCodecRenderer2.mSurfaceReference.get() == null) {
                            MpbLog.logf("%s.%s: display mode change has completed, but surface not available yet - deferring playback", "MediaCodecRenderer", CALLBACK_NAME);
                            MediaCodecRenderer.this.mPendingSurfacePlayback = true;
                            return;
                        }
                        MpbLog.logf("%s.%s: display mode change has completed, starting playback", "MediaCodecRenderer", CALLBACK_NAME);
                        try {
                            MediaCodecRenderer.this.prepareAndStartPipelines(true);
                        } catch (MediaPipelineBackendException e) {
                            MediaCodecRenderer.this.onError("Failed to start playback after changing display mode: " + e.getMessage(), e.resultCode, CALLBACK_NAME, ErrorCallback.ErrorSeverity.SEV_FATAL);
                        }
                    } else {
                        MpbLog.logf("%s.%s: display mode change has completed, but we are now paused", "MediaCodecRenderer", CALLBACK_NAME);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public final class PipelineTask implements Callable<Void> {
        public final MediaPipeline mMediaPipeline;
        public final PipelineTaskType mPipelineTaskType;

        public PipelineTask(@Nonnull MediaPipeline mediaPipeline, @Nonnull PipelineTaskType pipelineTaskType) {
            Preconditions.checkNotNull(mediaPipeline, "mediaPipeline");
            this.mMediaPipeline = mediaPipeline;
            Preconditions.checkNotNull(pipelineTaskType, "pipelineTaskType");
            this.mPipelineTaskType = pipelineTaskType;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() throws Exception {
            call2();
            return null;
        }

        @Nonnull
        public SampleType getSampleType() {
            return this.mMediaPipeline.isAudioPipeline ? SampleType.AUDIO_SAMPLE : SampleType.VIDEO_SAMPLE;
        }

        @VisibleForTesting
        @Nonnull
        public String getTaskName() {
            return String.format("MCR:%s:%s", this.mMediaPipeline.isAudioPipeline ? ExifInterface.GPS_MEASUREMENT_IN_PROGRESS : ExifInterface.GPS_MEASUREMENT_INTERRUPTED, this.mPipelineTaskType.toString());
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            Thread.currentThread().setName(getTaskName());
            Process.setThreadPriority(-16);
            while (MediaCodecRenderer.this.mIsRunning && (this.mMediaPipeline.isAudioPipeline || !MediaCodecRenderer.this.mIsVideoPipelineInactive)) {
                try {
                    if (MediaCodecRenderer.this.mIsVerboseLoggingEnabled) {
                        MpbLog.warnf("PipelineTask: %s starting to execute", getTaskName());
                    }
                    this.mMediaPipeline.executePipelineTask(this.mPipelineTaskType);
                    if (MediaCodecRenderer.this.mIsVerboseLoggingEnabled) {
                        MpbLog.warnf("PipelineTask: %s sleeping for: %s ms", getTaskName(), Integer.valueOf(MediaCodecRenderer.this.mTickIntervalMillis));
                    }
                    Thread.sleep(MediaCodecRenderer.this.mTickIntervalMillis);
                } catch (MediaPipelineBackendException e) {
                    MediaCodecRenderer.this.onError(e.toString(), e.resultCode, getTaskName(), ErrorCallback.ErrorSeverity.SEV_FATAL);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    MpbLog.warnf("PipelineTask: %s interrupted: %s", getTaskName(), e2);
                } catch (Throwable th) {
                    String str = String.format(Locale.US, "Exception crashed %s thread: %s", getTaskName(), th);
                    MpbLog.exceptionf(th, str, new Object[0]);
                    MediaCodecRenderer.this.onError(str, MediaPipelineBackendResultCode.AV_MPB_TASK_THREAD_CRASHED, getTaskName(), ErrorCallback.ErrorSeverity.SEV_FATAL);
                    return null;
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class RestartVideoPipelineTask implements Runnable {
        public final Surface mSurface;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (MediaCodecRenderer.this.mMutex) {
                try {
                    if (MediaCodecRenderer.this.mIsInitialized && this.mSurface.isValid()) {
                        MediaCodecRenderer mediaCodecRenderer = MediaCodecRenderer.this;
                        if (mediaCodecRenderer.mVideoTrack != null) {
                            try {
                                if (mediaCodecRenderer.mIsRunning) {
                                    MediaCodecRenderer mediaCodecRenderer2 = MediaCodecRenderer.this;
                                    MediaTrackData mediaTrackData = mediaCodecRenderer2.mVideoTrack;
                                    mediaTrackData.mPipeline.notifySurfaceRecreated(mediaTrackData.mFormat, this.mSurface, mediaCodecRenderer2.mMediaCryptoSession);
                                    MediaCodecRenderer.this.mIsVideoPipelineInactive = false;
                                } else {
                                    MpbLog.warnf("%s.RestartVideoPipelineTask skipping surface recreated notification - renderer not running", "MediaCodecRenderer");
                                }
                            } catch (MediaPipelineBackendException e) {
                                MediaCodecRenderer.this.onError(e.toString(), e.resultCode, "RestartVideoPipelineTask", ErrorCallback.ErrorSeverity.SEV_FATAL);
                            }
                            ScheduledFuture scheduledFuture = MediaCodecRenderer.this.mVideoBufferConsumerFuture;
                            if (scheduledFuture != null) {
                                scheduledFuture.cancel(true);
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public RestartVideoPipelineTask(@Nonnull Surface surface) {
            Preconditions.checkNotNull(surface, "surface");
            this.mSurface = surface;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class StopVideoPipelineTask implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            synchronized (MediaCodecRenderer.this.mMutex) {
                MediaCodecRenderer mediaCodecRenderer = MediaCodecRenderer.this;
                if (mediaCodecRenderer.mIsInitialized && mediaCodecRenderer.mVideoTrack != null) {
                    mediaCodecRenderer.mIsVideoPipelineInactive = true;
                    if (MediaCodecRenderer.this.arePipelinesSynchronous()) {
                        try {
                            Iterator<Future<Void>> it = MediaCodecRenderer.this.mPipelineTaskFuturesMap.get(SampleType.VIDEO_SAMPLE).iterator();
                            while (it.hasNext()) {
                                it.next().get();
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            Thread.currentThread().interrupt();
                            MpbLog.warnf("%s.StopVideoPipelineTask unexpected exception waiting for pipeline task to finish: %s", "MediaCodecRenderer", e);
                        }
                    }
                    MediaCodecRenderer.this.mVideoTrack.mPipeline.notifySurfaceDestroyed();
                    MediaCodecRenderer mediaCodecRenderer2 = MediaCodecRenderer.this;
                    if (mediaCodecRenderer2.mIsBackgroundAudioPlaybackSupported && mediaCodecRenderer2.mFirstVideoSampleDurationMillis != null) {
                        if (mediaCodecRenderer2.mVideoBufferConsumerExecutor == null) {
                            ScheduledExecutorBuilder scheduledExecutorBuilder = new ScheduledExecutorBuilder("VideoBufferConsumer");
                            scheduledExecutorBuilder.withFixedThreadPoolSize(1);
                            scheduledExecutorBuilder.allowCoreThreadExpiry(1L, TimeUnit.SECONDS);
                            mediaCodecRenderer2.mVideoBufferConsumerExecutor = scheduledExecutorBuilder.build();
                        }
                        MediaCodecRenderer mediaCodecRenderer3 = MediaCodecRenderer.this;
                        mediaCodecRenderer3.mVideoBufferConsumerFuture = mediaCodecRenderer3.mVideoBufferConsumerExecutor.scheduleWithFixedDelay(mediaCodecRenderer3.mVideoBufferConsumer, 0L, mediaCodecRenderer3.mFirstVideoSampleDurationMillis.longValue(), TimeUnit.MILLISECONDS);
                    }
                }
            }
        }

        public StopVideoPipelineTask() {
        }
    }

    public MediaCodecRenderer(@Nonnull Context context, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, @Nonnull CapabilitiesProvider capabilitiesProvider, @Nonnull PictureMode pictureMode, @Nonnull DevicePropertyProvider devicePropertyProvider, @Nonnull FailoverManager failoverManager) {
        MediaClock mediaClock = new MediaClock(devicePropertyProvider.isVerboseAvSyncLoggingEnabled());
        MediaFormatFactory mediaFormatFactory = new MediaFormatFactory(mediaCodecDeviceCapabilityDetector);
        MediaPipelineFactory mediaPipelineFactory = MediaPipelineFactory.getInstance();
        TunneledPlaybackEvaluator tunneledPlaybackEvaluator = new TunneledPlaybackEvaluator(mediaCodecDeviceCapabilityDetector, devicePropertyProvider);
        DefaultMPBInternalConfig defaultMPBInternalConfig = DefaultMPBInternalConfig.INSTANCE;
        ZoomCalculator zoomCalculator = new ZoomCalculator(devicePropertyProvider);
        defaultMPBInternalConfig.getClass();
        boolean z = DefaultMPBInternalConfig.videoSurfaceHotSwapSupported;
        defaultMPBInternalConfig.getClass();
        boolean z2 = DefaultMPBInternalConfig.backgroundAudioPlaybackSupported;
        defaultMPBInternalConfig.getClass();
        boolean z3 = DefaultMPBInternalConfig.dummySurfaceEnabled;
        defaultMPBInternalConfig.getClass();
        this(mediaClock, mediaFormatFactory, mediaPipelineFactory, context, tunneledPlaybackEvaluator, mediaCodecDeviceCapabilityDetector, capabilitiesProvider, pictureMode, devicePropertyProvider, defaultMPBInternalConfig, failoverManager, zoomCalculator, z, z2, z3, DefaultMPBInternalConfig.useBgThreadForSurfaceCallbacks, devicePropertyProvider.isHandleMidstreamSurfaceDestroyEnabled(), devicePropertyProvider.isVerboseAvSyncLoggingEnabled());
    }

    public final void applyZoom() {
        synchronized (this.mMutex) {
            try {
                if (!this.mZoomCalculator.isInitialized()) {
                    MpbLog.logf("%s.applyZoom zoom calculator not initialized, skipping", "MediaCodecRenderer");
                    return;
                }
                VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion();
                MpbLog.logf("%s.applyZoom region=%s", "MediaCodecRenderer", videoRegion);
                this.mSurfaceResizerCallback.setViewport(videoRegion.fromLeft, videoRegion.fromTop, videoRegion.width, videoRegion.height);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean arePipelinesSynchronous() {
        MediaTrackData mediaTrackData = this.mAudioTrack;
        return (mediaTrackData == null || this.mVideoTrack == null || mediaTrackData.mPipeline.isAsynchronous() || this.mVideoTrack.mPipeline.isAsynchronous()) ? false : true;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void audioOnMetadata(@Nonnull AudioMetadata audioMetadata) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("audioOnMetadata");
                if (this.mAudioMetadata != null) {
                    throw new MediaPipelineBackendException("Audio Metadata has already been set", MediaPipelineBackendResultCode.AV_MPB_INVALID_STATE);
                }
                MpbLog.logf("%s.audioOnMetadata %s", "MediaCodecRenderer", audioMetadata);
                Preconditions.checkNotNull(audioMetadata, "audioMetadata");
                this.mAudioMetadata = audioMetadata;
                onMetadataReceived();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public int audioOnSample(@Nonnull AudioSample audioSample) throws MediaPipelineBackendException {
        int iSubmitSample;
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("audioOnSample");
                if (this.mAudioTrack == null) {
                    throw new MediaPipelineBackendException("Cannot call audioOnSample before audioOnMetadata", MediaPipelineBackendResultCode.AV_MPB_NOT_READY_TO_RECEIVE_SAMPLES);
                }
                if (this.mIsVerboseLoggingEnabled) {
                    MpbLog.logf("%s.audioOnSample %s", "MediaCodecRenderer", audioSample);
                }
                iSubmitSample = this.mAudioTrack.mBuffer.submitSample(audioSample);
            } catch (Throwable th) {
                throw th;
            }
        }
        return iSubmitSample;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void audioOnStreamFinish() throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("audioOnStreamFinish");
                if (this.mAudioTrack == null) {
                    throw new MediaPipelineBackendException("Cannot call audioOnStreamFinish before audioOnMetadata", MediaPipelineBackendResultCode.AV_MPB_INVALID_STATE);
                }
                MpbLog.logf("%s.audioOnStreamFinish", "MediaCodecRenderer");
                this.mAudioTrack.mBuffer.onStreamFinish();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void checkMPBInitialized(@Nonnull String str) throws MediaPipelineBackendException {
        if (!this.mIsInitialized) {
            throw new MediaPipelineBackendException(String.format(Locale.US, "Cannot call %s before initialization!", str), MediaPipelineBackendResultCode.AV_MPB_NOT_INITIALIZED);
        }
    }

    public final void checkPendingSurfacePlayback() {
        if (this.mPendingSurfacePlayback) {
            MpbLog.logf("%s.checkPendingSurfacePlayback surface now available, starting deferred playback", "MediaCodecRenderer");
            this.mPendingSurfacePlayback = false;
            try {
                if (this.mPendingDisplayModeChange != null) {
                    MpbLog.logf("%s.checkPendingSurfacePlayback display mode change still pending", "MediaCodecRenderer");
                } else {
                    prepareAndStartPipelines(true);
                }
            } catch (MediaPipelineBackendException e) {
                onError("Failed to start deferred playback after surface available: " + e.getMessage(), e.resultCode, "checkPendingSurfacePlayback", ErrorCallback.ErrorSeverity.SEV_FATAL);
            }
        }
    }

    public final void configureAudioPipeline(@Nonnull MediaFormat mediaFormat, @Nullable Integer num, boolean z, int i) throws Exception {
        try {
            MediaSourceImpl mediaSourceImpl = new MediaSourceImpl(true, this.mMediaPipelineContext, this.mVideoMetadata, this.mAudioMetadata, this.mConfig);
            MediaTrackData mediaTrackData = new MediaTrackData(mediaFormat, mediaSourceImpl, this.mPipelineFactory.newMediaPipeline(mediaSourceImpl, this.mAppContext, this.mMediaClock, this.mMediaPipelineContext, true, num, z, this.mCapabilityDetector, null, i));
            this.mAudioTrack = mediaTrackData;
            mediaTrackData.mPipeline.configure(mediaTrackData.mFormat, null, this.mMediaCryptoSession);
            this.mAudioTrack.mPipeline.setVolume(this.mAudioMute.get().booleanValue() ? 0.0f : this.mAudioVolume.get().floatValue());
        } catch (Exception e) {
            MpbLog.exceptionf(e, "Failed to configure audio track", new Object[0]);
            MediaTrackData mediaTrackData2 = this.mAudioTrack;
            if (mediaTrackData2 == null) {
                throw e;
            }
            mediaTrackData2.mPipeline.release();
            throw e;
        }
    }

    public final void configureVideoPipeline() throws MediaPipelineBackendException {
        if (this.mVideoPipelineConfigured) {
            return;
        }
        Surface surface = this.mSurfaceReference.get();
        if (surface == null || !surface.isValid()) {
            throw new MediaPipelineBackendException("failed to configure video pipeline: null or invalid surface", MediaPipelineBackendResultCode.AV_MPB_NULL_OR_INVALID_VIDEO_SURFACE);
        }
        if (this.mPictureMode == PictureMode.FILM_MAKER) {
            this.mFormatFactory.setFilmMakerMode(this.mVideoTrack.mFormat);
        }
        MpbLog.logf("%s configuring video pipeline with picture mode %s", "MediaCodecRenderer", this.mPictureMode);
        MediaTrackData mediaTrackData = this.mVideoTrack;
        mediaTrackData.mPipeline.configure(mediaTrackData.mFormat, surface, this.mMediaCryptoSession);
        this.mVideoPipelineConfigured = true;
    }

    @VisibleForTesting
    public ImmutableList<PipelineTask> constructMediaPipelineTasks(@Nonnull MediaPipeline mediaPipeline, @Nonnull MediaPipeline mediaPipeline2, boolean z) {
        ImmutableList.Builder builder = new ImmutableList.Builder(4);
        PipelineTaskType pipelineTaskType = PipelineTaskType.DRAIN;
        builder.add(new PipelineTask(mediaPipeline2, pipelineTaskType));
        PipelineTaskType pipelineTaskType2 = PipelineTaskType.FEED;
        builder.add(new PipelineTask(mediaPipeline2, pipelineTaskType2));
        builder.add(new PipelineTask(mediaPipeline, pipelineTaskType2));
        if (z) {
            MpbLog.logf("%s.constructMediaPipelineTasks tunnelMode has 3 pipelineTasks: audioFeed, audioDrain, videoFeed", "MediaCodecRenderer");
        } else {
            builder.add(new PipelineTask(mediaPipeline, pipelineTaskType));
        }
        return builder.build();
    }

    public final void createDummySurface() {
        if (!this.mIsDummyVideoSurfaceEnabled || this.mDummySurface != null || this.mSurfaceReference.get() == null || this.mIsTunneledPlayback) {
            return;
        }
        try {
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted();
            PlaceholderSurface placeholderSurfaceNewInstanceV17 = PlaceholderSurface.newInstanceV17(this.mAppContext, this.mMediaCryptoSession != null);
            this.mDummySurface = placeholderSurfaceNewInstanceV17;
            MpbLog.logf("%s.createDummySurface created dummy surface %s took %s ms", "MediaCodecRenderer", placeholderSurfaceNewInstanceV17, Long.valueOf(stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS)));
        } catch (Exception e) {
            MpbLog.errorf("MediaCodecRenderer failed to create dummy surface %s", e);
        }
    }

    @Nonnull
    public final ScheduledExecutorService createPipelineExecutor() {
        ScheduledExecutorBuilder scheduledExecutorBuilder = new ScheduledExecutorBuilder((Class<?>) MediaCodecRenderer.class);
        scheduledExecutorBuilder.withFixedThreadPoolSize(5);
        scheduledExecutorBuilder.allowCoreThreadExpiry(500L, TimeUnit.MILLISECONDS);
        return scheduledExecutorBuilder.build();
    }

    @Nonnull
    public final ScheduledExecutorService createUnderrunExecutor() {
        ScheduledExecutorBuilder scheduledExecutorBuilder = new ScheduledExecutorBuilder("UnderrunExecutor");
        scheduledExecutorBuilder.withFixedThreadPoolSize(1);
        scheduledExecutorBuilder.allowCoreThreadExpiry(1L, TimeUnit.SECONDS);
        return scheduledExecutorBuilder.build();
    }

    public final void emitError(@Nonnull String str, @Nonnull MediaPipelineBackendResultCode mediaPipelineBackendResultCode, @Nonnull String str2, @Nonnull ErrorCallback.ErrorSeverity errorSeverity) {
        MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks = this.mMediaPipelineBackendCallbacks;
        if (mediaPipelineBackendCallbacks == null) {
            return;
        }
        if (errorSeverity == ErrorCallback.ErrorSeverity.SEV_FATAL) {
            this.mHadFatalError = true;
        }
        MpbLog.errorf("%s.emitError description:%s, errorCode:%s, component:%s, severity:%s", "MediaCodecRenderer", str, mediaPipelineBackendResultCode, str2, errorSeverity);
        mediaPipelineBackendCallbacks.getErrorCallback().onError(str, mediaPipelineBackendResultCode, str2, errorSeverity);
    }

    public final void flushPipelinesAndBuffersInternal() throws MediaPipelineBackendException {
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "%s.flushPipelinesAndBuffersInternal must be called after locking the mutex!");
        MediaTrackData mediaTrackData = this.mAudioTrack;
        if (mediaTrackData != null) {
            mediaTrackData.mPipeline.flush();
            this.mAudioTrack.mBuffer.flush();
        }
        MediaTrackData mediaTrackData2 = this.mVideoTrack;
        if (mediaTrackData2 != null) {
            mediaTrackData2.mPipeline.flush();
            this.mVideoTrack.mBuffer.flush();
        }
    }

    @Nullable
    public final Integer generateAudioSessionId() {
        AudioManager audioManager = (AudioManager) this.mAppContext.getSystemService("audio");
        if (audioManager == null) {
            MpbLog.warnf("%s.generateAudioSessionId configuring renderer for non-tunneled playback, failed to get audio manager service", "MediaCodecRenderer");
            return null;
        }
        int iGenerateAudioSessionId = audioManager.generateAudioSessionId();
        if (iGenerateAudioSessionId == -1) {
            MpbLog.warnf("%s.generateAudioSessionId configuring renderer for non-tunneled playback, failed to generate audio session id", "MediaCodecRenderer");
            return null;
        }
        Integer numValueOf = Integer.valueOf(iGenerateAudioSessionId);
        MpbLog.logf("%s.generateAudioSessionId configuring renderer for tunneled playback with audioSessionId %d", "MediaCodecRenderer", numValueOf);
        return numValueOf;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @Nonnull
    @CalledFromIgnite
    public DiagnosticInfo getDiagnosticInfo() {
        synchronized (this.mMutex) {
            try {
                MediaTrackData mediaTrackData = this.mVideoTrack;
                if (mediaTrackData == null) {
                    return DiagnosticInfo.getEmptyDiagnosticInfo();
                }
                if (!mediaTrackData.mPipeline.isUnconfigured() && !this.mIsVideoPipelineInactive) {
                    MediaTrackData mediaTrackData2 = this.mAudioTrack;
                    int numberOfSamplesInBuffer = mediaTrackData2 == null ? 0 : mediaTrackData2.mBuffer.getNumberOfSamplesInBuffer();
                    int totalDroppedFrames = this.mVideoTrack.mPipeline.getTotalDroppedFrames();
                    int i = totalDroppedFrames - this.mPreviousDroppedFrameCount;
                    this.mPreviousDroppedFrameCount = totalDroppedFrames;
                    DiagnosticInfo diagnosticInfo = this.mDiagnosticInfo;
                    diagnosticInfo.numDroppedFrames = i;
                    diagnosticInfo.numCorruptedVideoFrames = 0;
                    diagnosticInfo.numVideoSamplesInBuffer = this.mVideoTrack.mBuffer.getNumberOfSamplesInBuffer();
                    DiagnosticInfo diagnosticInfo2 = this.mDiagnosticInfo;
                    diagnosticInfo2.numAudioSamplesInBuffer = numberOfSamplesInBuffer;
                    this.mFailoverManager.evaluatePerformance(diagnosticInfo2);
                    return this.mDiagnosticInfo;
                }
                return DiagnosticInfo.getEmptyDiagnosticInfo();
            } catch (Exception e) {
                MpbLog.warnf("%s.getDiagnosticInfo failed to read renderer diagnostic info: %s", "MediaCodecRenderer", e);
                return DiagnosticInfo.getEmptyDiagnosticInfo();
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public long getPlaybackTimeUs() {
        long currentMediaTimeUs;
        synchronized (this.mMutex) {
            try {
                currentMediaTimeUs = this.mIsRunning ? this.mMediaClock.getCurrentMediaTimeUs() : this.mCachedLastMediaTimeUs;
            } catch (Throwable th) {
                throw th;
            }
        }
        return currentMediaTimeUs;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public String getProperty(@Nonnull String str) throws MediaPipelineBackendException {
        String value;
        synchronized (this.mMutex) {
            checkMPBInitialized("getProperty");
            Preconditions.checkNotNull(str, "key");
            MpbLog.logf("%s.getProperty %s", "MediaCodecRenderer", str);
            value = this.mPropertyStore.getValue(str);
        }
        return value;
    }

    @Override // com.amazon.avod.mpb.media.playback.mediacodec.UnderrunHandler
    public void handleBufferUnderrun() {
        synchronized (this.mMutex) {
            try {
                if (this.mUnderrunExecutor == null) {
                    this.mUnderrunExecutor = createUnderrunExecutor();
                }
                this.mUnderrunExecutor.execute(new Runnable() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleBufferUnderrun$1();
                    }
                });
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void init(@Nonnull MediaPipelineBackend.InitConfig initConfig, @Nonnull SurfaceResizerCallback surfaceResizerCallback, @Nonnull DisplayModeManager displayModeManager) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                MpbLog.logf("%s.init %s", "MediaCodecRenderer", initConfig);
                Preconditions.checkNotNull(initConfig, "initConfig");
                if (this.mIsInitialized) {
                    throw new MediaPipelineBackendException("This instance is already initialized", MediaPipelineBackendResultCode.AV_MPB_ALREADY_INITIALIZED);
                }
                if (this.mMediaPipelineBackendCallbacks == null) {
                    throw new MediaPipelineBackendException("Callbacks must be registered before initialization", MediaPipelineBackendResultCode.AV_MPB_INVALID_STATE);
                }
                if (!this.mCapabilitiesProvider.getCapabilities().supportedTracks.containsAll(initConfig.trackConfigs)) {
                    throw new MediaPipelineBackendException("Unsupported track configuration", MediaPipelineBackendResultCode.AV_MPB_UNSUPPORTED_TRACK_CONFIG);
                }
                Preconditions.checkNotNull(surfaceResizerCallback, "surfaceResizerCallback");
                this.mSurfaceResizerCallback = surfaceResizerCallback;
                Preconditions.checkNotNull(displayModeManager, "displayModeManager");
                this.mDisplayModeManager = displayModeManager;
                surfaceResizerCallback.resetViewport();
                this.mIsInitialized = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isMediaPipelinePrewarmingSupported() {
        return (!this.mPropertyProvider.isIntraChunkSeekingSupported() || this.mPropertyProvider.isApplicationInBackground() || this.mMatchFrameRate.get().booleanValue()) ? false : true;
    }

    public final void lambda$handleBufferUnderrun$1() {
        try {
            MpbLog.logf("%s.handleBufferUnderrun pausing renderer on underrun", "MediaCodecRenderer");
            pause();
        } catch (MediaPipelineBackendException e) {
            onError("Failed to pause renderer on buffer underrun!", e.resultCode, "handleBufferUnderrun", ErrorCallback.ErrorSeverity.SEV_FATAL);
        }
    }

    public final /* synthetic */ void lambda$new$0() {
        MediaTrackData mediaTrackData;
        synchronized (this.mMutex) {
            try {
                if (!this.mIsInitialized) {
                    this.mVideoBufferConsumerExecutor.shutdown();
                    this.mVideoBufferConsumerExecutor = null;
                    return;
                }
                if (this.mIsVideoPipelineInactive && this.mIsRunning && (mediaTrackData = this.mVideoTrack) != null && mediaTrackData.mBuffer.hasNext()) {
                    this.mVideoTrack.mBuffer.advance();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void notifyPipelinesToPlayWhenReady() {
        MpbLog.logf("%s notifying audio and video pipelines to play when ready", "MediaCodecRenderer");
        MediaTrackData mediaTrackData = this.mAudioTrack;
        if (mediaTrackData != null) {
            mediaTrackData.mPipeline.setPlayWhenReady();
        }
        MediaTrackData mediaTrackData2 = this.mVideoTrack;
        if (mediaTrackData2 != null) {
            mediaTrackData2.mPipeline.setPlayWhenReady();
        }
    }

    public final void notifyPropertyChanged(String str, String str2) {
        MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks = this.mMediaPipelineBackendCallbacks;
        if (mediaPipelineBackendCallbacks == null) {
            MpbLog.warnf("Attempted to report property change %s=%s but callback was not set", str, str2);
        } else {
            mediaPipelineBackendCallbacks.getPropertyChangedCallback().onPropertyChanged(str, str2);
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.mediacodec.ErrorHandler
    public void onError(@Nonnull String str, @Nonnull MediaPipelineBackendResultCode mediaPipelineBackendResultCode, @Nonnull String str2, @Nonnull ErrorCallback.ErrorSeverity errorSeverity) {
        if (errorSeverity == ErrorCallback.ErrorSeverity.SEV_FATAL) {
            this.mFailoverManager.evaluateFatal(mediaPipelineBackendResultCode);
        }
        emitError(str, mediaPipelineBackendResultCode, str2, errorSeverity);
    }

    public final void onMetadataReceived() throws Exception {
        Integer num;
        if (this.mAudioMetadata == null || this.mVideoMetadata == null) {
            return;
        }
        MpbLog.logf("%s audio and video metadata received, preparing audio and video tracks", "MediaCodecRenderer");
        this.mMediaPipelineContext = new MediaPipelineContext(this.mMediaPipelineBackendCallbacks, this.mSurfaceResizerCallback, this.mPropertyProvider, this, this, this.mZoomCalculator, this.mSurfaceReference.get() != null, isMediaPipelinePrewarmingSupported());
        MediaFormat mediaFormatNewVideoFormat = this.mFormatFactory.newVideoFormat(this.mVideoMetadata);
        this.mIsTunneledPlayback = !this.mDisableTunnelingMode.get().booleanValue() && this.mTunneledPlaybackEvaluator.evaluateTunneledPlaybackCapability(mediaFormatNewVideoFormat);
        boolean z = this.mIsAsyncModeEnabledByConfig;
        int iIntValue = this.mAudioBitrate.get().intValue();
        Integer numGenerateAudioSessionId = this.mIsTunneledPlayback ? generateAudioSessionId() : null;
        MediaFormat mediaFormatNewAudioFormat = this.mFormatFactory.newAudioFormat(this.mAudioMetadata);
        try {
            configureAudioPipeline(mediaFormatNewAudioFormat, numGenerateAudioSessionId, z, iIntValue);
            num = numGenerateAudioSessionId;
        } catch (MediaPipelineBackendException e) {
            if (!this.mIsTunneledPlayback) {
                throw e;
            }
            MpbLog.warnf("Failed to initialise audio pipeline for tunneling. Trying again without tunneling", new Object[0]);
            this.mIsTunneledPlayback = false;
            configureAudioPipeline(mediaFormatNewAudioFormat, null, z, iIntValue);
            MpbLog.warnf("Successfully initialised audio pipeline after disabling tunneling", new Object[0]);
            num = null;
        }
        VideoMetadata videoMetadata = this.mVideoMetadata;
        if (videoMetadata.codecType.videoStreamType == null) {
            throw new MediaPipelineBackendException(String.format("Unsupported video codec %s", this.mVideoMetadata.codecType), MediaPipelineBackendResultCode.AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE);
        }
        MediaSourceImpl mediaSourceImpl = new MediaSourceImpl(false, this.mMediaPipelineContext, videoMetadata, this.mAudioMetadata, this.mConfig);
        MediaPipeline mediaPipelineNewMediaPipeline = this.mPipelineFactory.newMediaPipeline(mediaSourceImpl, this.mAppContext, this.mMediaClock, this.mMediaPipelineContext, false, num, z, this.mCapabilityDetector, this.mVideoMetadata.codecType.profile, iIntValue);
        MediaTrackData mediaTrackData = new MediaTrackData(mediaFormatNewVideoFormat, mediaSourceImpl, mediaPipelineNewMediaPipeline);
        this.mVideoTrack = mediaTrackData;
        Float f = this.mPendingDisplayAspectRatio;
        if (f != null) {
            mediaTrackData.mBuffer.setDisplayAspectRatio(f.floatValue());
            this.mPendingDisplayAspectRatio = null;
        }
        Iterator<SampleType> it = SampleType.getEntries().iterator();
        while (it.hasNext()) {
            this.mPipelineTaskFuturesMap.put(it.next(), new ArrayList());
        }
        if (arePipelinesSynchronous()) {
            this.mPipelineTasks = constructMediaPipelineTasks(mediaPipelineNewMediaPipeline, this.mAudioTrack.mPipeline, this.mIsTunneledPlayback);
        }
    }

    public final void onSurfaceCreated(@Nonnull Surface surface) {
        if (this.mSurfaceLatch.getCount() != 0 || !this.mIsInitialized) {
            MpbLog.logf("%s.onSurfaceCreated video surface created %s", "MediaCodecRenderer", surface);
            this.mSurfaceReference.set(surface);
            this.mSurfaceLatch.countDown();
            return;
        }
        MpbLog.logf("%s.onSurfaceCreated video surface recreated mid-stream %s", "MediaCodecRenderer", surface);
        if (this.mIsTunneledPlayback) {
            return;
        }
        if (this.mIsVideoSurfaceHotSwapSupported) {
            restartVideoPipelineWithNewSurface(surface);
        } else if (this.mHandleMidstreamSurfaceDestroy) {
            if (this.mDummySurface != null) {
                setVideoCodecOutputSurface(surface);
            } else {
                onError("Video surface recreated mid flight, hot swap disabled!", MediaPipelineBackendResultCode.AV_MPB_SET_VIDEO_OUTPUT_SURFACE_ERROR, "onSurfaceCreated()", ErrorCallback.ErrorSeverity.SEV_FATAL);
            }
        }
    }

    public final void onSurfaceDestroyed() {
        if (this.mSurfaceLatch.getCount() != 0 || !this.mIsInitialized) {
            MpbLog.logf("%s.onSurfaceDestroyed video surface destroyed", "MediaCodecRenderer");
            this.mSurfaceReference.set(null);
            this.mSurfaceLatch.countDown();
            return;
        }
        MpbLog.logf("%s.onSurfaceDestroyed video surface destroyed mid-stream", "MediaCodecRenderer");
        if (this.mIsTunneledPlayback) {
            return;
        }
        if (this.mIsVideoSurfaceHotSwapSupported) {
            stopVideoPipeline();
            return;
        }
        if (this.mHandleMidstreamSurfaceDestroy) {
            createDummySurface();
            Surface surface = this.mDummySurface;
            if (surface != null) {
                setVideoCodecOutputSurface(surface);
            }
        }
    }

    public final void onZoomLevelChanged(@Nonnull String str) {
        synchronized (this.mMutex) {
            this.mZoomCalculator.setZoomLevel(ZoomLevel.Companion.fromString(str));
            applyZoom();
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void pause() throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            MpbLog.logf("%s.pause", "MediaCodecRenderer");
            checkMPBInitialized("pause");
            stopPipelinesInternal();
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void play() throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                MpbLog.logf("%s.play", "MediaCodecRenderer");
                checkMPBInitialized("play");
                if (this.mMatchFrameRate.get().booleanValue()) {
                    if (!this.mDisplayModeChangeRequested) {
                        MpbLog.devf("%s.play will be deferred until display mode changes", "MediaCodecRenderer");
                        Preconditions.checkState(this.mPendingDisplayModeChange == null);
                        this.mPendingDisplayModeChange = new PendingDisplayModeChange();
                        VideoMetadata videoMetadata = this.mVideoMetadata;
                        Preconditions.checkNotNull(videoMetadata, "mVideoMetadata");
                        float f = (float) videoMetadata.frameRate;
                        this.mDisplayModeChanging.set(Boolean.TRUE);
                        this.mDisplayModeManager.setTargetFrameRate(f, this.mPendingDisplayModeChange);
                        this.mDisplayModeChangeRequested = true;
                    } else if (this.mPendingDisplayModeChange != null) {
                        MpbLog.devf("%s.play will be deferred until a previous display mode change completes", "MediaCodecRenderer");
                        this.mPendingDisplayModeChange.mPlayAfterCompletion = true;
                    } else {
                        MpbLog.devf("%s.play executed after display mode had already been changed", "MediaCodecRenderer");
                        if (this.mIsDeferredSurfacePlaybackEnabled && this.mSurfaceReference.get() == null) {
                            MpbLog.logf("%s.play deferred until surface is available", "MediaCodecRenderer");
                            this.mPendingSurfacePlayback = true;
                            return;
                        }
                        prepareAndStartPipelines(true);
                    }
                } else if (this.mIsDeferredSurfacePlaybackEnabled && this.mSurfaceReference.get() == null) {
                    MpbLog.logf("%s.play deferred until surface is available", "MediaCodecRenderer");
                    this.mPendingSurfacePlayback = true;
                } else {
                    MpbLog.devf("%s.play executed without display mode change", "MediaCodecRenderer");
                    prepareAndStartPipelines(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void prepareAndStartPipelines(boolean z) throws MediaPipelineBackendException {
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "%s.prepareAndStartPipelines must be called after locking the mutex!");
        MpbLog.logf("%s.prepareAndStartPipelines playWhenReady: %s", "MediaCodecRenderer", Boolean.valueOf(z));
        configureVideoPipeline();
        if (z) {
            notifyPipelinesToPlayWhenReady();
        }
        if (!this.mMediaClock.hasActualTimeSource()) {
            MpbLog.warnf("%s.play never had a seek, seeking to 0", "MediaCodecRenderer");
            seek(0L);
        }
        startPipelinesInternal();
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void registerCallbacks(@Nonnull MediaPipelineBackendCallbacks mediaPipelineBackendCallbacks) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            MpbLog.logf("%s.registerCallbacks %s", "MediaCodecRenderer", mediaPipelineBackendCallbacks);
            Preconditions.checkNotNull(mediaPipelineBackendCallbacks, "callbacks");
            this.mMediaPipelineBackendCallbacks = mediaPipelineBackendCallbacks;
            MediaPipelineBackendPropertyStore mediaPipelineBackendPropertyStore = new MediaPipelineBackendPropertyStore();
            this.mPropertyStore = mediaPipelineBackendPropertyStore;
            mediaPipelineBackendPropertyStore.registerProperty(this.mAudioVolume);
            this.mPropertyStore.registerProperty(this.mAudioMute);
            this.mPropertyStore.registerProperty(this.mAudioBitrate);
            this.mPropertyStore.registerProperty(this.mMatchFrameRate);
            this.mPropertyStore.registerProperty(this.mDisplayModeChanging);
            this.mPropertyStore.registerProperty(this.mDisplayAspectRatio);
            this.mPropertyStore.registerProperty(this.mDisableTunnelingMode);
            this.mPropertyStore.registerProperty(this.mZoomLevelProperty);
        }
    }

    public final void releasePipelinesInternal() {
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "%s.releasePipelinesInternal must be called after locking the mutex!");
        MediaTrackData mediaTrackData = this.mAudioTrack;
        if (mediaTrackData != null) {
            mediaTrackData.mPipeline.release();
        }
        MediaTrackData mediaTrackData2 = this.mVideoTrack;
        if (mediaTrackData2 != null) {
            mediaTrackData2.mPipeline.release();
        }
        MediaClock mediaClock = this.mMediaClock;
        if (mediaClock != null) {
            mediaClock.stop(true);
        }
    }

    public final void restartVideoPipelineWithNewSurface(@Nonnull Surface surface) {
        if (this.mIsVideoSurfaceHotSwapSupported) {
            if (this.mDummySurface != null) {
                setVideoCodecOutputSurface(surface);
                return;
            }
            RestartVideoPipelineTask restartVideoPipelineTask = new RestartVideoPipelineTask(surface);
            if (this.mUseBackgroundThreadForSurfaceCallbacks) {
                this.mSurfaceCallbackExecutor.execute(restartVideoPipelineTask);
            } else {
                restartVideoPipelineTask.run();
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void seek(long j) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("seek");
                MpbLog.logf("%s.seek %s", "MediaCodecRenderer", Long.valueOf(j));
                this.mCachedLastMediaTimeUs = TimeUnit.MILLISECONDS.toMicros(j);
                Preconditions.checkNotNull(this.mAudioTrack, "Audio track must be configured before seek()!");
                TimeSource timeSource = this.mAudioTrack.mPipeline.getTimeSource();
                Preconditions.checkNotNull(timeSource, "Audio track time source cannot be null!");
                this.mMediaClock.setTimeSource(timeSource);
                this.mMediaClock.start(this.mCachedLastMediaTimeUs);
                if (isMediaPipelinePrewarmingSupported()) {
                    prepareAndStartPipelines(false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setDisplayAspectRatio(@Nonnull Float f) {
        synchronized (this.mMutex) {
            try {
                if (f.floatValue() <= 0.0f) {
                    MpbLog.logf("Invalid value of aspectRatio=%s, therefore ignoring.", f);
                    return;
                }
                MediaTrackData mediaTrackData = this.mVideoTrack;
                if (mediaTrackData != null) {
                    mediaTrackData.mBuffer.setDisplayAspectRatio(f.floatValue());
                } else {
                    MpbLog.logf("%s.setDisplayAspectRatio video track not ready, storing for later", "MediaCodecRenderer");
                    this.mPendingDisplayAspectRatio = f;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void setDrmSystem(@Nonnull AndroidDrmSystem androidDrmSystem) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(androidDrmSystem, "drmSystem");
            MediaCryptoSessionImpl mediaCryptoSessionImpl = new MediaCryptoSessionImpl(androidDrmSystem, false);
            this.mMediaCryptoSession = mediaCryptoSessionImpl;
            mediaCryptoSessionImpl.initialize();
        }
    }

    public void setPictureMode(@Nonnull PictureMode pictureMode) {
        pictureMode.getClass();
        this.mPictureMode = pictureMode;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void setProperty(@Nonnull String str, @Nullable String str2) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            checkMPBInitialized("setProperty");
            Preconditions.checkNotNull(str, "key");
            MpbLog.logf("%s.setProperty %s, %s", "MediaCodecRenderer", str, str2);
            this.mPropertyStore.setValue(str, str2);
        }
    }

    @CalledFromIgnite
    public void setSurface(@Nullable Surface surface) {
        synchronized (this.mMutex) {
            try {
                MpbLog.logf("%s.setSurface %s", "MediaCodecRenderer", surface);
                if (surface != null) {
                    onSurfaceCreated(surface);
                    MediaPipelineContext mediaPipelineContext = this.mMediaPipelineContext;
                    if (mediaPipelineContext != null) {
                        mediaPipelineContext.onSurfaceAvailable();
                    }
                    checkPendingSurfacePlayback();
                } else {
                    onSurfaceDestroyed();
                    MediaPipelineContext mediaPipelineContext2 = this.mMediaPipelineContext;
                    if (mediaPipelineContext2 != null) {
                        mediaPipelineContext2.onSurfaceUnavailable();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SuppressLint({"UseRequiresApi"})
    @TargetApi(23)
    public final void setVideoCodecOutputSurface(@Nonnull Surface surface) {
        Preconditions.checkNotNull(surface, "surface");
        if (!this.mIsInitialized || !surface.isValid()) {
            MpbLog.warnf("%s.setVideoCodecOutputSurface no-op: renderer not initialised: %s, surface valid: %s", "MediaCodecRenderer", Boolean.valueOf(!this.mIsInitialized), Boolean.valueOf(surface.isValid()));
            return;
        }
        try {
            MediaTrackData mediaTrackData = this.mVideoTrack;
            if (mediaTrackData == null) {
                return;
            }
            mediaTrackData.mPipeline.setOutputSurface(surface);
            if (this.mVideoTrack.mPipeline.isUnconfigured()) {
                if (!this.mIsRunning) {
                    MpbLog.warnf("%s.setVideoCodecOutputSurface skipping surface recreated notification - renderer not running", "MediaCodecRenderer");
                } else {
                    MediaTrackData mediaTrackData2 = this.mVideoTrack;
                    mediaTrackData2.mPipeline.notifySurfaceRecreated(mediaTrackData2.mFormat, surface, this.mMediaCryptoSession);
                }
            }
        } catch (MediaPipelineBackendException e) {
            onError(e.toString(), e.resultCode, "setVideoCodecOutputSurface()", ErrorCallback.ErrorSeverity.SEV_FATAL);
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void setVideoOutputPosition(int i, int i2, int i3, int i4, int i5) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            MpbLog.logf("%s.setVideoOutputPosition x:%d y:%d w:%d h:%d z: %d", "MediaCodecRenderer", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
            checkMPBInitialized("setVideoOutputPosition");
            this.mSurfaceResizerCallback.setViewport(i, i2, i3, i4);
            this.mZoomCalculator.setViewport(i, i2, i3, i4);
            applyZoom();
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void shutdown() throws MediaPipelineBackendException {
        boolean z;
        synchronized (this.mMutex) {
            try {
                MpbLog.logf("%s.shutdown", "MediaCodecRenderer");
                checkMPBInitialized("shutdown");
                if (!this.mHadFatalError) {
                    this.mFailoverManager.getClass();
                }
                this.mFailoverManager.getClass();
                stopInternal();
                releasePipelinesInternal();
                this.mAudioTrack = null;
                this.mVideoTrack = null;
                this.mAudioMetadata = null;
                this.mVideoMetadata = null;
                this.mMediaPipelineContext = null;
                this.mCachedLastMediaTimeUs = 0L;
                this.mFirstVideoSampleDurationMillis = null;
                this.mHadFatalError = false;
                MediaCryptoSession mediaCryptoSession = this.mMediaCryptoSession;
                if (mediaCryptoSession != null) {
                    mediaCryptoSession.release();
                    this.mMediaCryptoSession = null;
                }
                this.mVideoPipelineConfigured = false;
                this.mIsInitialized = false;
                this.mZoomLevelProperty.set(ZoomLevel.Native.INSTANCE.name);
                this.mSurfaceReference.set(null);
                this.mSurfaceLatch = new CountDownLatch(1);
                Surface surface = this.mDummySurface;
                if (surface != null) {
                    MpbLog.logf("%s.shutdown releasing dummy surface %s", "MediaCodecRenderer", surface);
                    this.mDummySurface.release();
                }
                ScheduledExecutorService scheduledExecutorService = this.mPipelineExecutor;
                if (scheduledExecutorService != null) {
                    scheduledExecutorService.shutdown();
                    this.mPipelineExecutor = null;
                }
                ScheduledExecutorService scheduledExecutorService2 = this.mUnderrunExecutor;
                if (scheduledExecutorService2 != null) {
                    scheduledExecutorService2.shutdown();
                    this.mUnderrunExecutor = null;
                }
                z = this.mDisplayModeChangeRequested;
                this.mDisplayModeChangeRequested = false;
                this.mPendingDisplayModeChange = null;
                this.mDisplayModeChanging.set(Boolean.FALSE);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            try {
                this.mDisplayModeManager.clearTargetFrameRate();
            } catch (InterruptedException | CancellationException unused) {
                MpbLog.warnf("%s.shutdown interrupted while waiting for the display mode to be reset", "MediaCodecRenderer");
            } catch (Exception e) {
                throw new MediaPipelineBackendException("Failed to reset display mode", MediaPipelineBackendResultCode.AV_MPB_RESET_REFRESH_RATE_FAILED, e);
            }
        }
    }

    public final void startPipelinesInternal() throws MediaPipelineBackendException {
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "%s.startPipelinesInternal must be called after locking the mutex!");
        if (this.mIsRunning) {
            return;
        }
        MpbLog.logf("%s.startPipelineInternal starting audio and video pipelines", "MediaCodecRenderer");
        MediaTrackData mediaTrackData = this.mAudioTrack;
        if (mediaTrackData != null) {
            mediaTrackData.mPipeline.start();
        }
        MediaTrackData mediaTrackData2 = this.mVideoTrack;
        if (mediaTrackData2 != null) {
            mediaTrackData2.mPipeline.start();
        }
        this.mIsRunning = true;
        if (arePipelinesSynchronous()) {
            if (this.mPipelineExecutor == null) {
                this.mPipelineExecutor = createPipelineExecutor();
            }
            Iterator<List<Future<Void>>> it = this.mPipelineTaskFuturesMap.values().iterator();
            while (it.hasNext()) {
                it.next().clear();
            }
            UnmodifiableIterator<PipelineTask> it2 = this.mPipelineTasks.iterator();
            while (it2.hasNext()) {
                PipelineTask next = it2.next();
                this.mPipelineTaskFuturesMap.get(next.getSampleType()).add(this.mPipelineExecutor.submit(next));
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void stop() throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            MpbLog.logf("%s.stop", "MediaCodecRenderer");
            checkMPBInitialized("stop");
            stopInternal();
        }
    }

    public final void stopInternal() throws MediaPipelineBackendException {
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "%s.stopInternal must be called after locking the mutex!");
        stopPipelinesInternal();
        this.mMediaClock.stop(false);
        flushPipelinesAndBuffersInternal();
        this.mPreviousDroppedFrameCount = 0;
        this.mDisableTunnelingMode.set(Boolean.FALSE);
    }

    public final void stopPipelinesInternal() {
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "%s.stopPipelinesInternal must be called after locking the mutex!");
        if (this.mPendingSurfacePlayback) {
            MpbLog.logf("%s.stopPipelinesInternal clearing pending surface playback", "MediaCodecRenderer");
            this.mPendingSurfacePlayback = false;
        }
        if (this.mIsRunning) {
            MpbLog.logf("%s.stopPipelinesInternal stopping audio and video pipelines", "MediaCodecRenderer");
            PendingDisplayModeChange pendingDisplayModeChange = this.mPendingDisplayModeChange;
            if (pendingDisplayModeChange != null) {
                pendingDisplayModeChange.mPlayAfterCompletion = false;
            }
            this.mCachedLastMediaTimeUs = this.mMediaClock.getCurrentMediaTimeUs();
            this.mIsRunning = false;
            if (arePipelinesSynchronous()) {
                try {
                    Iterator<List<Future<Void>>> it = this.mPipelineTaskFuturesMap.values().iterator();
                    while (it.hasNext()) {
                        Iterator<Future<Void>> it2 = it.next().iterator();
                        while (it2.hasNext()) {
                            it2.next().get();
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    MpbLog.warnf("%s.stopPipelinesInternal unexpected exception waiting for pipeline task to finish: %s", "MediaCodecRenderer", e);
                }
            }
            MediaTrackData mediaTrackData = this.mAudioTrack;
            if (mediaTrackData != null) {
                mediaTrackData.mPipeline.stop();
            }
            MediaTrackData mediaTrackData2 = this.mVideoTrack;
            if (mediaTrackData2 != null) {
                mediaTrackData2.mPipeline.stop();
            }
        }
    }

    public final void stopVideoPipeline() {
        if (this.mIsVideoSurfaceHotSwapSupported) {
            createDummySurface();
            Surface surface = this.mDummySurface;
            if (surface != null) {
                setVideoCodecOutputSurface(surface);
                return;
            }
            StopVideoPipelineTask stopVideoPipelineTask = new StopVideoPipelineTask();
            if (this.mUseBackgroundThreadForSurfaceCallbacks) {
                this.mSurfaceCallbackExecutor.execute(stopVideoPipelineTask);
            } else {
                stopVideoPipelineTask.run();
            }
        }
    }

    public final void updateAudioMuteState(boolean z) {
        synchronized (this.mMutex) {
            try {
                MediaTrackData mediaTrackData = this.mAudioTrack;
                if (mediaTrackData != null) {
                    mediaTrackData.mPipeline.setVolume((float) (z ? 0.0d : this.mCurrentVolumeGain));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void updateVolume(@Nonnull Double d) {
        synchronized (this.mMutex) {
            try {
                MediaTrackData mediaTrackData = this.mAudioTrack;
                if (mediaTrackData != null) {
                    mediaTrackData.mPipeline.setVolume(d.floatValue());
                    this.mCurrentVolumeGain = d.doubleValue();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void videoOnMetadata(@Nonnull VideoMetadata videoMetadata) throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("videoOnMetadata");
                if (this.mVideoMetadata != null) {
                    throw new MediaPipelineBackendException("Video Metadata has already been set", MediaPipelineBackendResultCode.AV_MPB_INVALID_STATE);
                }
                MpbLog.logf("%s.videoOnMetadata %s", "MediaCodecRenderer", videoMetadata);
                Preconditions.checkNotNull(videoMetadata, "videoMetadata");
                this.mVideoMetadata = videoMetadata;
                this.mFailoverManager.getClass();
                ZoomCalculator zoomCalculator = this.mZoomCalculator;
                int i = videoMetadata.pixelWidth;
                zoomCalculator.setAspectRatio(i, i / videoMetadata.pixelHeight);
                applyZoom();
                onMetadataReceived();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public int videoOnSample(@Nonnull VideoSample videoSample) throws MediaPipelineBackendException {
        int iSubmitSample;
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("videoOnSample");
                if (this.mVideoTrack == null) {
                    throw new MediaPipelineBackendException("Cannot call videoOnSample before videoOnMetadata", MediaPipelineBackendResultCode.AV_MPB_NOT_READY_TO_RECEIVE_SAMPLES);
                }
                if (this.mFirstVideoSampleDurationMillis == null) {
                    this.mFirstVideoSampleDurationMillis = Long.valueOf(videoSample.durationMs);
                }
                if (this.mIsVerboseLoggingEnabled) {
                    MpbLog.logf("%s.videoOnSample %s", "MediaCodecRenderer", videoSample);
                }
                iSubmitSample = this.mVideoTrack.mBuffer.submitSample(videoSample);
            } catch (Throwable th) {
                throw th;
            }
        }
        return iSubmitSample;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackend
    @CalledFromIgnite
    public void videoOnStreamFinish() throws MediaPipelineBackendException {
        synchronized (this.mMutex) {
            try {
                checkMPBInitialized("videoOnStreamFinish");
                if (this.mVideoTrack == null) {
                    throw new MediaPipelineBackendException("Cannot call videoOnStreamFinish before videoOnMetadata", MediaPipelineBackendResultCode.AV_MPB_INVALID_STATE);
                }
                MpbLog.logf("%s.videoOnStreamFinish", "MediaCodecRenderer");
                this.mVideoTrack.mBuffer.onStreamFinish();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public MediaCodecRenderer(@Nonnull MediaClock mediaClock, @Nonnull MediaFormatFactory mediaFormatFactory, @Nonnull MediaPipelineFactory mediaPipelineFactory, @Nonnull Context context, @Nonnull TunneledPlaybackEvaluator tunneledPlaybackEvaluator, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, @Nonnull CapabilitiesProvider capabilitiesProvider, @Nonnull PictureMode pictureMode, @Nonnull DevicePropertyProvider devicePropertyProvider, @Nonnull MPBInternalConfig mPBInternalConfig, @Nonnull FailoverManager failoverManager, @Nonnull ZoomCalculator zoomCalculator, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.mMutex = new Object();
        this.mSurfaceReference = new AtomicReference<>(null);
        this.mPipelineTaskFuturesMap = Maps.newEnumMap(SampleType.class);
        this.mDiagnosticInfo = new DiagnosticInfo();
        this.mPreviousDroppedFrameCount = 0;
        this.mAudioVolume = MediaPipelineBackendPropertyStore.Property.CC.of(MediaPipelineBackend.AUDIO_VOLUME, Double.valueOf(1.0d), new MediaPipelineBackendPropertyStore.PropertyChangeAction() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda1
            @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.PropertyChangeAction
            public final void onChange(Object obj) {
                this.f$0.updateVolume((Double) obj);
            }
        });
        PropertyDefinition<Boolean> propertyDefinition = MediaPipelineBackend.AUDIO_MUTE;
        Boolean bool = Boolean.FALSE;
        MediaPipelineBackendPropertyStore.PropertyChangeAction propertyChangeAction = new MediaPipelineBackendPropertyStore.PropertyChangeAction() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda2
            @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.PropertyChangeAction
            public final void onChange(Object obj) {
                this.f$0.updateAudioMuteState(((Boolean) obj).booleanValue());
            }
        };
        MediaPipelineBackendPropertyStore.Property.Companion companion = MediaPipelineBackendPropertyStore.Property.Companion;
        this.mAudioMute = companion.of(propertyDefinition, bool, propertyChangeAction);
        this.mAudioBitrate = companion.of(MediaPipelineBackend.AUDIO_BITRATE, 0);
        this.mMatchFrameRate = companion.of(MediaPipelineBackend.MATCH_REFRESH_RATE, bool);
        this.mDisplayModeChanging = companion.of(MediaPipelineBackend.DISPLAY_MODE_CHANGING, bool, null, new PropertyChangedCallback() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda3
            @Override // com.amazon.avod.mpb.api.callback.PropertyChangedCallback
            public final void onPropertyChanged(String str, String str2) {
                this.f$0.notifyPropertyChanged(str, str2);
            }
        });
        this.mDisplayAspectRatio = companion.of(MediaPipelineBackend.DISPLAY_ASPECT_RATIO, Float.valueOf(Float.NaN), new MediaPipelineBackendPropertyStore.PropertyChangeAction() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda4
            @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.PropertyChangeAction
            public final void onChange(Object obj) {
                this.f$0.setDisplayAspectRatio((Float) obj);
            }
        });
        this.mDisableTunnelingMode = companion.of(MediaPipelineBackend.DISABLE_TUNNELING_MODE, bool);
        this.mZoomLevelProperty = companion.of(MediaPipelineBackend.ZOOM_LEVEL, ZoomLevel.Native.INSTANCE.name, new MediaPipelineBackendPropertyStore.PropertyChangeAction() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda5
            @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.PropertyChangeAction
            public final void onChange(Object obj) {
                this.f$0.onZoomLevelChanged((String) obj);
            }
        }, new PropertyChangedCallback() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda6
            @Override // com.amazon.avod.mpb.api.callback.PropertyChangedCallback
            public final void onPropertyChanged(String str, String str2) {
                this.f$0.notifyPropertyChanged(str, str2);
            }
        });
        this.mSurfaceLatch = new CountDownLatch(1);
        this.mDummySurface = null;
        this.mCachedLastMediaTimeUs = 0L;
        this.mHadFatalError = false;
        this.mVideoPipelineConfigured = false;
        this.mIsRunning = false;
        this.mIsInitialized = false;
        this.mIsVideoPipelineInactive = false;
        this.mFirstVideoSampleDurationMillis = null;
        this.mCurrentVolumeGain = 1.0d;
        this.mPendingDisplayAspectRatio = null;
        Preconditions.checkNotNull(mediaClock, "mediaClock");
        this.mMediaClock = mediaClock;
        Preconditions.checkNotNull(mediaFormatFactory, "mediaFormatFactory");
        this.mFormatFactory = mediaFormatFactory;
        Preconditions.checkNotNull(mediaPipelineFactory, "pipelineFactory");
        this.mPipelineFactory = mediaPipelineFactory;
        Preconditions.checkNotNull(context, "context");
        this.mAppContext = context;
        Preconditions.checkNotNull(zoomCalculator, "zoomCalculator");
        this.mZoomCalculator = zoomCalculator;
        Preconditions.checkNotNull(tunneledPlaybackEvaluator, "tunneledPlaybackEvaluator");
        this.mTunneledPlaybackEvaluator = tunneledPlaybackEvaluator;
        Preconditions.checkNotNull(mediaCodecDeviceCapabilityDetector, "capabilityDetector");
        this.mCapabilityDetector = mediaCodecDeviceCapabilityDetector;
        Preconditions.checkNotNull(capabilitiesProvider, "capabilitiesProvider");
        this.mCapabilitiesProvider = capabilitiesProvider;
        Preconditions.checkNotNull(pictureMode, MediaPipelineBackendEngineManager.PictureModeConstants.PROPERTY_KEY);
        this.mPictureMode = pictureMode;
        Preconditions.checkNotNull(mPBInternalConfig, "config");
        this.mConfig = mPBInternalConfig;
        Preconditions.checkNotNull(devicePropertyProvider, "propertyProvider");
        this.mPropertyProvider = devicePropertyProvider;
        Preconditions.checkNotNull(failoverManager, "failoverManager");
        this.mFailoverManager = failoverManager;
        Preconditions.checkState(mPBInternalConfig.getTickIntervalMillis() > 0, "Tick interval cannot be non positive");
        this.mTickIntervalMillis = mPBInternalConfig.getTickIntervalMillis();
        this.mIsAsyncModeEnabledByConfig = devicePropertyProvider.isAsyncModeEnabled();
        this.mIsVideoSurfaceHotSwapSupported = z;
        this.mIsBackgroundAudioPlaybackSupported = z2;
        this.mIsDummyVideoSurfaceEnabled = z3;
        this.mUseBackgroundThreadForSurfaceCallbacks = z4;
        this.mHandleMidstreamSurfaceDestroy = z5;
        this.mIsVerboseLoggingEnabled = z6;
        this.mIsDeferredSurfacePlaybackEnabled = devicePropertyProvider.isDeferredSurfacePlaybackEnabled();
        ScheduledExecutorBuilder scheduledExecutorBuilder = new ScheduledExecutorBuilder("SurfaceCallbackExecutor");
        scheduledExecutorBuilder.withFixedThreadPoolSize(1);
        scheduledExecutorBuilder.allowCoreThreadExpiry(1L, TimeUnit.SECONDS);
        this.mSurfaceCallbackExecutor = scheduledExecutorBuilder.build();
        this.mVideoBufferConsumer = new Runnable() { // from class: com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        };
    }
}
