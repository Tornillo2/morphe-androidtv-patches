package com.amazon.avod.mpb.media.playback.pipeline;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.view.Surface;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.avod.mpb.api.callback.ErrorCallback;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.playback.avsync.MediaClock;
import com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline;
import com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline.FeedInputBufferTask;
import com.amazon.avod.mpb.media.playback.render.MediaRenderer;
import com.amazon.avod.mpb.media.playback.render.SubmitBufferResult;
import com.amazon.avod.mpb.media.playback.source.MediaSource;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.mpb.threading.ScheduledExecutorBuilder;
import com.amazon.avod.mpb.threading.Tickers;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nAsyncMediaPipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncMediaPipeline.kt\ncom/amazon/avod/mpb/media/playback/pipeline/AsyncMediaPipeline\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,774:1\n1#2:775\n*E\n"})
public final class AsyncMediaPipeline extends MediaPipeline {

    @NotNull
    public final HashMap<AsyncTaskType, LinkedBlockingDeque<AsyncPipelineTask>> asyncBlockingQueueMap;

    @NotNull
    public final ArrayList<AsyncTaskLooper> asyncTaskLoopers;
    public final int asyncTaskRetryIntervalMillis;

    @Nullable
    public ScheduledExecutorService executor;
    public final int executorShutdownTimeoutMillis;
    public final boolean isAsynchronous;
    public final boolean isVideoSurfaceHotSwapSupported;

    @NotNull
    public final UnderrunWatchdog underrunWatchdog;

    @Nullable
    public VideoDecodeCadenceWatchdog videoDecodingCadenceWatchdog;
    public final int watchdogTickIntervalMillis;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AsyncPipelineTask {
        void executeAsyncPipelineTask() throws MediaPipelineBackendException;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsyncTaskLooper implements Callable<Void> {

        @NotNull
        public final String asyncTaskLooperName;

        @NotNull
        public final AsyncTaskType asyncTaskType;
        public final /* synthetic */ AsyncMediaPipeline this$0;

        public AsyncTaskLooper(@NotNull AsyncMediaPipeline asyncMediaPipeline, AsyncTaskType asyncTaskType) {
            Intrinsics.checkNotNullParameter(asyncTaskType, "asyncTaskType");
            this.this$0 = asyncMediaPipeline;
            this.asyncTaskType = asyncTaskType;
            this.asyncTaskLooperName = asyncMediaPipeline.getTaskName(asyncTaskType.name());
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() {
            call2();
            return null;
        }

        @Override // java.util.concurrent.Callable
        @Nullable
        /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            Thread.currentThread().setName(this.asyncTaskLooperName);
            Process.setThreadPriority(-16);
            while (this.this$0.isRunning()) {
                if (this.this$0.isVerboseLoggingEnabled) {
                    MpbLog.warnf(AbstractResolvableFuture$$ExternalSyntheticOutline0.m(this.asyncTaskLooperName, " waiting for next task"), new Object[0]);
                }
                try {
                    LinkedBlockingDeque<AsyncPipelineTask> linkedBlockingDeque = this.this$0.asyncBlockingQueueMap.get(this.asyncTaskType);
                    Intrinsics.checkNotNull(linkedBlockingDeque);
                    AsyncPipelineTask asyncPipelineTaskTake = linkedBlockingDeque.take();
                    Intrinsics.checkNotNullExpressionValue(asyncPipelineTaskTake, "take(...)");
                    AsyncPipelineTask asyncPipelineTask = asyncPipelineTaskTake;
                    if (this.this$0.isVerboseLoggingEnabled) {
                        MpbLog.warnf(AbstractResolvableFuture$$ExternalSyntheticOutline0.m(this.asyncTaskLooperName, " executing next task"), new Object[0]);
                    }
                    try {
                        asyncPipelineTask.executeAsyncPipelineTask();
                    } catch (MediaPipelineBackendException e) {
                        String str = "Encountered exception " + e + " while executing " + this.asyncTaskLooperName;
                        MpbLog.warnf(e, str, new Object[0]);
                        this.this$0.mediaPipelineContext.onError(str, e.resultCode, this.asyncTaskLooperName, ErrorCallback.ErrorSeverity.SEV_FATAL);
                        return null;
                    }
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    MpbLog.warnf(e2, AbstractResolvableFuture$$ExternalSyntheticOutline0.m(this.asyncTaskLooperName, " async looper interrupted, returning early"), new Object[0]);
                    return null;
                }
            }
            return null;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AsyncTaskType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AsyncTaskType[] $VALUES;
        public static final AsyncTaskType FEED = new AsyncTaskType("FEED", 0);
        public static final AsyncTaskType DRAIN = new AsyncTaskType("DRAIN", 1);

        public static final /* synthetic */ AsyncTaskType[] $values() {
            return new AsyncTaskType[]{FEED, DRAIN};
        }

        static {
            AsyncTaskType[] asyncTaskTypeArr$values = $values();
            $VALUES = asyncTaskTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(asyncTaskTypeArr$values);
        }

        public AsyncTaskType(String str, int i) {
        }

        @NotNull
        public static EnumEntries<AsyncTaskType> getEntries() {
            return $ENTRIES;
        }

        public static AsyncTaskType valueOf(String str) {
            return (AsyncTaskType) Enum.valueOf(AsyncTaskType.class, str);
        }

        public static AsyncTaskType[] values() {
            return (AsyncTaskType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class DrainOutputBufferTask implements AsyncPipelineTask {

        @NotNull
        public final MediaCodec.BufferInfo bufferInfo;
        public final int index;
        public final /* synthetic */ AsyncMediaPipeline this$0;

        public DrainOutputBufferTask(AsyncMediaPipeline asyncMediaPipeline, @NotNull int i, MediaCodec.BufferInfo bufferInfo) {
            Intrinsics.checkNotNullParameter(bufferInfo, "bufferInfo");
            this.this$0 = asyncMediaPipeline;
            this.index = i;
            this.bufferInfo = bufferInfo;
            if (i < 0) {
                throw new IllegalStateException("Check failed.");
            }
            asyncMediaPipeline.lastDecodedPresentationTimeUs = bufferInfo.presentationTimeUs;
        }

        @Override // com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline.AsyncPipelineTask
        public void executeAsyncPipelineTask() throws MediaPipelineBackendException {
            try {
                MediaCodec mediaCodec = this.this$0.codec;
                Intrinsics.checkNotNull(mediaCodec);
                ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(this.index);
                boolean zIsSampleOverlapped = this.this$0.isSampleOverlapped(this.bufferInfo.presentationTimeUs);
                MediaRenderer mediaRenderer = this.this$0.renderer;
                Intrinsics.checkNotNull(mediaRenderer);
                AsyncMediaPipeline asyncMediaPipeline = this.this$0;
                SubmitBufferResult submitBufferResultSubmitBuffer = mediaRenderer.submitBuffer(asyncMediaPipeline.isAudioPipeline ? 0L : asyncMediaPipeline.mediaClock.getCurrentMediaTimeUs(), this.bufferInfo, outputBuffer, TimeUnit.NANOSECONDS.toMicros(System.nanoTime()), zIsSampleOverlapped);
                int i = submitBufferResultSubmitBuffer.bytesRead;
                if (Build.VERSION.SDK_INT >= 24) {
                    AsyncMediaPipeline asyncMediaPipeline2 = this.this$0;
                    if (asyncMediaPipeline2.isAudioPipeline && i == -6) {
                        asyncMediaPipeline2.handleAudioTrackDeadError(this.bufferInfo.presentationTimeUs);
                        if (outputBuffer != null) {
                            outputBuffer.position(0);
                        }
                        i = 0;
                    }
                }
                if (i >= 0) {
                    MediaCodec.BufferInfo bufferInfo = this.bufferInfo;
                    bufferInfo.size -= i;
                    bufferInfo.offset += i;
                    try {
                        Thread.sleep(this.this$0.asyncTaskRetryIntervalMillis);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        MpbLog.devf("MediaCodec " + this + " for " + this.this$0.inputFormat + " interrupted while sleeping before retry", new Object[0]);
                    }
                    this.this$0.addToFrontOfQueueForRetry(AsyncTaskType.DRAIN, this);
                    return;
                }
                this.this$0.handlePictureAspectRatioChange();
                long j = submitBufferResultSubmitBuffer.adjustedReleaseTimeNs;
                Stopwatch stopwatch = this.this$0.drainTaskTimer;
                stopwatch.reset();
                stopwatch.start();
                if (i == -1) {
                    MediaCodec mediaCodec2 = this.this$0.codec;
                    Intrinsics.checkNotNull(mediaCodec2);
                    mediaCodec2.releaseOutputBuffer(this.index, false);
                } else if (j == -3) {
                    MediaCodec mediaCodec3 = this.this$0.codec;
                    Intrinsics.checkNotNull(mediaCodec3);
                    mediaCodec3.releaseOutputBuffer(this.index, !this.this$0.isAudioPipeline);
                } else {
                    MediaCodec mediaCodec4 = this.this$0.codec;
                    Intrinsics.checkNotNull(mediaCodec4);
                    mediaCodec4.releaseOutputBuffer(this.index, j);
                }
                AsyncMediaPipeline asyncMediaPipeline3 = this.this$0;
                if (!asyncMediaPipeline3.isAudioPipeline && i == -2 && asyncMediaPipeline3.isResuming) {
                    asyncMediaPipeline3.isResuming = false;
                }
                asyncMediaPipeline3.stopDrainTaskTimer(this.index);
                if ((this.bufferInfo.flags & 4) != 0) {
                    MpbLog.logf("MediaCodec " + this + " for " + this.this$0.inputFormat + " decoded and rendered end of stream", new Object[0]);
                    this.this$0.handleEndOfStream();
                }
            } catch (MediaCodec.CodecException e) {
                this.this$0.handleCodecException(e, "while draining");
            } catch (IllegalStateException e2) {
                this.this$0.logUnexpectedIllegalStateException(e2, "while draining");
            }
        }

        @NotNull
        public String toString() {
            return DrainOutputBufferTask.class.getSimpleName() + "{index=" + this.index + "}";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class FeedInputBufferTask implements AsyncPipelineTask {
        public final int index;

        public FeedInputBufferTask(int i) {
            this.index = i;
            if (i < 0) {
                throw new IllegalStateException("Check failed.");
            }
        }

        @Override // com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline.AsyncPipelineTask
        public void executeAsyncPipelineTask() throws MediaPipelineBackendException {
            try {
                if (AsyncMediaPipeline.this.source.hasNext() || AsyncMediaPipeline.this.source.hasStreamFinished()) {
                    if (AsyncMediaPipeline.this.source.hasNext()) {
                        AsyncMediaPipeline.this.feedInputBuffer(this.index);
                    }
                    AsyncMediaPipeline asyncMediaPipeline = AsyncMediaPipeline.this;
                    if (asyncMediaPipeline.hasInputStreamEnded || !asyncMediaPipeline.source.hasStreamFinished() || AsyncMediaPipeline.this.source.hasNext()) {
                        return;
                    }
                    AsyncMediaPipeline.this.feedEndOfStreamFlag(this.index);
                    return;
                }
                try {
                    Thread.sleep(AsyncMediaPipeline.this.asyncTaskRetryIntervalMillis);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    MpbLog.devf("MediaCodec " + this + " for " + AsyncMediaPipeline.this.inputFormat + " interrupted while sleeping before retry", new Object[0]);
                }
                AsyncMediaPipeline.this.addToFrontOfQueueForRetry(AsyncTaskType.FEED, this);
            } catch (MediaCodec.CodecException e) {
                AsyncMediaPipeline.this.handleCodecException(e, "while feeding");
            } catch (IllegalStateException e2) {
                AsyncMediaPipeline.this.logUnexpectedIllegalStateException(e2, "while feeding");
            }
        }

        @NotNull
        public String toString() {
            return FeedInputBufferTask.class.getSimpleName() + "{index=" + this.index + "}";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class OutputFormatChangedTask implements AsyncPipelineTask {

        @NotNull
        public final MediaFormat mediaFormat;
        public final /* synthetic */ AsyncMediaPipeline this$0;

        public OutputFormatChangedTask(@NotNull AsyncMediaPipeline asyncMediaPipeline, MediaFormat mediaFormat) {
            Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
            this.this$0 = asyncMediaPipeline;
            this.mediaFormat = mediaFormat;
        }

        @Override // com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline.AsyncPipelineTask
        public void executeAsyncPipelineTask() throws MediaPipelineBackendException {
            this.this$0.handleOutputFormatChanged(this.mediaFormat);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class UnderrunWatchdog implements Callable<Void> {

        @NotNull
        public final String taskName;

        public UnderrunWatchdog() {
            this.taskName = AsyncMediaPipeline.this.getTaskName("UNDERRUN");
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() {
            call2();
            return null;
        }

        @Override // java.util.concurrent.Callable
        @Nullable
        /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            Thread.currentThread().setName(this.taskName);
            while (AsyncMediaPipeline.this.isRunning()) {
                try {
                    AsyncMediaPipeline.this.checkUnderrun();
                    Thread.sleep(AsyncMediaPipeline.this.watchdogTickIntervalMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    MpbLog.warnf(e, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("UnderrunWatchdog: ", this.taskName, " interrupted"), new Object[0]);
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class VideoDecodeCadenceWatchdog implements Callable<Void> {

        @NotNull
        public final String taskName;

        public VideoDecodeCadenceWatchdog() {
            this.taskName = AsyncMediaPipeline.this.getTaskName("WATCHDOG");
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() {
            call2();
            return null;
        }

        @Override // java.util.concurrent.Callable
        @Nullable
        /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            Thread.currentThread().setName(this.taskName);
            while (AsyncMediaPipeline.this.isRunning()) {
                try {
                    AsyncMediaPipeline.this.validateVideoDecodingCadence();
                    Thread.sleep(AsyncMediaPipeline.this.watchdogTickIntervalMillis);
                } catch (MediaPipelineBackendException e) {
                    String str = "Stashing encountered exception " + e + " while executing " + this.taskName;
                    MpbLog.warnf(e, str, new Object[0]);
                    AsyncMediaPipeline.this.mediaPipelineContext.onError(str, e.resultCode, this.taskName, ErrorCallback.ErrorSeverity.SEV_FATAL);
                    return null;
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    MpbLog.warnf(e2, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("VideoDecodeCadenceWatchdog: ", this.taskName, " interrupted"), new Object[0]);
                }
            }
            return null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public AsyncMediaPipeline(@NotNull MediaSource mediaSource, @NotNull MediaCodecFactory codecFactory, @NotNull MediaRendererFactory rendererFactory, @NotNull Context appContext, @NotNull MediaClock mediaClock, @NotNull MediaPipelineContext mediaPipelineContext, @Nullable Integer num, boolean z, @NotNull MPBInternalConfig config, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @Nullable Integer num2, int i) {
        super(mediaSource, codecFactory, rendererFactory, appContext, mediaClock, mediaPipelineContext, num, z, config, capabilityDetector, num2, i);
        Intrinsics.checkNotNullParameter(mediaSource, "mediaSource");
        Intrinsics.checkNotNullParameter(codecFactory, "codecFactory");
        Intrinsics.checkNotNullParameter(rendererFactory, "rendererFactory");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaClock, "mediaClock");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        this.asyncTaskRetryIntervalMillis = config.getAsyncTaskRetryIntervalMs();
        this.executorShutdownTimeoutMillis = config.getAsyncExecutorShutdownTimeoutMs();
        this.watchdogTickIntervalMillis = config.getAsyncWatchdogTickIntervalMs();
        this.isVideoSurfaceHotSwapSupported = config.getVideoSurfaceHotSwapSupported();
        this.asyncBlockingQueueMap = new HashMap<>();
        this.asyncTaskLoopers = new ArrayList<>();
        this.underrunWatchdog = new UnderrunWatchdog();
        for (AsyncTaskType asyncTaskType : AsyncTaskType.getEntries()) {
            this.asyncBlockingQueueMap.put(asyncTaskType, new LinkedBlockingDeque<>());
            this.asyncTaskLoopers.add(new AsyncTaskLooper(this, asyncTaskType));
        }
        if (!z && this.shouldValidateVideoDecodingCadence) {
            this.videoDecodingCadenceWatchdog = new VideoDecodeCadenceWatchdog();
        }
        this.isAsynchronous = true;
    }

    public final void addToFrontOfQueueForRetry(AsyncTaskType asyncTaskType, AsyncPipelineTask asyncPipelineTask) {
        try {
            LinkedBlockingDeque<AsyncPipelineTask> linkedBlockingDeque = this.asyncBlockingQueueMap.get(asyncTaskType);
            Intrinsics.checkNotNull(linkedBlockingDeque);
            linkedBlockingDeque.putFirst(asyncPipelineTask);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            MpbLog.warnf(e, "MediaCodec " + asyncPipelineTask + " for " + this.inputFormat + " interrupted while adding to front of queue for retry", new Object[0]);
        }
    }

    public final void clearAsyncTasks() {
        if (!Thread.holdsLock(this.mutex)) {
            throw new IllegalStateException("Check failed.");
        }
        Iterator<AsyncTaskType> it = AsyncTaskType.getEntries().iterator();
        while (it.hasNext()) {
            LinkedBlockingDeque<AsyncPipelineTask> linkedBlockingDeque = this.asyncBlockingQueueMap.get(it.next());
            if (linkedBlockingDeque != null) {
                linkedBlockingDeque.clear();
            }
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public void configureCallbacks() {
        synchronized (this.mutex) {
            try {
                MediaCodec.Callback callback = new MediaCodec.Callback() { // from class: com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline$configureCallbacks$1$callback$1
                    @Override // android.media.MediaCodec.Callback
                    public void onError(MediaCodec codec, MediaCodec.CodecException e) {
                        Intrinsics.checkNotNullParameter(codec, "codec");
                        Intrinsics.checkNotNullParameter(e, "e");
                        String str = "MediaCodec for " + this.this$0.inputFormat + " threw unexpected codec exception: " + e;
                        AsyncMediaPipeline asyncMediaPipeline = this.this$0;
                        Surface surface = asyncMediaPipeline.surface;
                        if (!asyncMediaPipeline.isAudioPipeline && asyncMediaPipeline.isVideoSurfaceHotSwapSupported && surface != null && !surface.isValid()) {
                            MpbLog.warnf(str, new Object[0]);
                        } else {
                            AsyncMediaPipeline asyncMediaPipeline2 = this.this$0;
                            asyncMediaPipeline2.mediaPipelineContext.onError(str, asyncMediaPipeline2.isAudioPipeline ? MediaPipelineBackendResultCode.AV_MPB_AUDIO_DECODER_ERROR : MediaPipelineBackendResultCode.AV_MPB_VIDEO_DECODER_ERROR, "MediaCodec.onError()", ErrorCallback.ErrorSeverity.SEV_FATAL);
                        }
                    }

                    @Override // android.media.MediaCodec.Callback
                    public void onInputBufferAvailable(MediaCodec codec, int i) {
                        Intrinsics.checkNotNullParameter(codec, "codec");
                        AsyncMediaPipeline asyncMediaPipeline = this.this$0;
                        if (asyncMediaPipeline.isVerboseLoggingEnabled) {
                            MpbLog.warnf("MediaCodec.onInputBufferAvailable callback " + asyncMediaPipeline.mimeType + " index " + i, new Object[0]);
                        }
                        try {
                            LinkedBlockingDeque<AsyncMediaPipeline.AsyncPipelineTask> linkedBlockingDeque = this.this$0.asyncBlockingQueueMap.get(AsyncMediaPipeline.AsyncTaskType.FEED);
                            Intrinsics.checkNotNull(linkedBlockingDeque);
                            linkedBlockingDeque.put(this.this$0.new FeedInputBufferTask(i));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            MpbLog.warnf(e, "MediaCodec for " + this.this$0.inputFormat + " interrupted queuing available input buffer", new Object[0]);
                        }
                    }

                    @Override // android.media.MediaCodec.Callback
                    public void onOutputBufferAvailable(MediaCodec codec, int i, MediaCodec.BufferInfo info) {
                        Intrinsics.checkNotNullParameter(codec, "codec");
                        Intrinsics.checkNotNullParameter(info, "info");
                        AsyncMediaPipeline asyncMediaPipeline = this.this$0;
                        if (asyncMediaPipeline.isVerboseLoggingEnabled) {
                            MpbLog.warnf("MediaCodec.onOutputBufferAvailable callback " + asyncMediaPipeline.mimeType + " index " + i, new Object[0]);
                        }
                        try {
                            LinkedBlockingDeque<AsyncMediaPipeline.AsyncPipelineTask> linkedBlockingDeque = this.this$0.asyncBlockingQueueMap.get(AsyncMediaPipeline.AsyncTaskType.DRAIN);
                            Intrinsics.checkNotNull(linkedBlockingDeque);
                            linkedBlockingDeque.put(new AsyncMediaPipeline.DrainOutputBufferTask(this.this$0, i, info));
                        } catch (IllegalStateException e) {
                            AsyncMediaPipeline asyncMediaPipeline2 = this.this$0;
                            MpbLog.warnf(e, "MediaCodec for " + asyncMediaPipeline2.inputFormat + " (state = " + asyncMediaPipeline2.state + ") threw unexpected IllegalStateException queuing output buffer", new Object[0]);
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                            MpbLog.warnf(e2, "MediaCodec for " + this.this$0.inputFormat + " interrupted queuing available output buffer", new Object[0]);
                        }
                    }

                    @Override // android.media.MediaCodec.Callback
                    public void onOutputFormatChanged(MediaCodec codec, MediaFormat format) {
                        Intrinsics.checkNotNullParameter(codec, "codec");
                        Intrinsics.checkNotNullParameter(format, "format");
                        AsyncMediaPipeline asyncMediaPipeline = this.this$0;
                        if (asyncMediaPipeline.isVerboseLoggingEnabled) {
                            MpbLog.warnf("MediaCodec.onOutputFormatChanged callback " + asyncMediaPipeline.mimeType + " format " + format, new Object[0]);
                        }
                        try {
                            LinkedBlockingDeque<AsyncMediaPipeline.AsyncPipelineTask> linkedBlockingDeque = this.this$0.asyncBlockingQueueMap.get(AsyncMediaPipeline.AsyncTaskType.DRAIN);
                            Intrinsics.checkNotNull(linkedBlockingDeque);
                            linkedBlockingDeque.put(new AsyncMediaPipeline.OutputFormatChangedTask(this.this$0, format));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            MpbLog.warnf(e, "MediaCodec for " + this.this$0.inputFormat + " interrupted queuing output format change", new Object[0]);
                        }
                    }
                };
                if (Build.VERSION.SDK_INT >= 23) {
                    HandlerThread handlerThread = new HandlerThread(getTaskName("CALLBACK"));
                    handlerThread.start();
                    MediaCodec mediaCodec = this.codec;
                    Intrinsics.checkNotNull(mediaCodec);
                    mediaCodec.setCallback(callback, new Handler(handlerThread.getLooper()));
                } else {
                    MediaCodec mediaCodec2 = this.codec;
                    Intrinsics.checkNotNull(mediaCodec2);
                    mediaCodec2.setCallback(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final ScheduledExecutorService createPipelineExecutor() {
        if (!Thread.holdsLock(this.mutex)) {
            throw new IllegalStateException("MediaPipeline executor must be created after locking mutex");
        }
        ScheduledExecutorBuilder scheduledExecutorBuilder = new ScheduledExecutorBuilder((Class<?>) AsyncMediaPipeline.class);
        scheduledExecutorBuilder.withFixedThreadPoolSize(this.isAudioPipeline ? 3 : 5);
        scheduledExecutorBuilder.allowCoreThreadExpiry(500L, TimeUnit.MILLISECONDS);
        return scheduledExecutorBuilder.build();
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public void executePipelineTask(@NotNull PipelineTaskType pipelineTaskType) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(pipelineTaskType, "pipelineTaskType");
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public void flush() throws MediaPipelineBackendException {
        synchronized (this.mutex) {
            if (!isIdle()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring flush() in " + this.state + " state", new Object[0]);
                return;
            }
            super.flush();
            clearAsyncTasks();
            MediaCodec mediaCodec = this.codec;
            if (mediaCodec != null) {
                Stopwatch stopwatch = this.pipelineTimer;
                stopwatch.reset();
                stopwatch.start();
                try {
                    mediaCodec.start();
                } catch (IllegalStateException e) {
                    MpbLog.warnf(e, "MediaCodec for " + this.mimeType + " (state = " + this.state + ") threw unexpected IllegalStateException calling start(): " + e.getMessage(), new Object[0]);
                }
                Stopwatch stopwatch2 = this.pipelineTimer;
                stopwatch2.stop();
                logOperation("MediaCodec.start", stopwatch2.elapsed(TimeUnit.MILLISECONDS));
            }
        }
    }

    public final String getTaskName(String str) {
        return "AMP:" + (this.isAudioPipeline ? ExifInterface.GPS_MEASUREMENT_IN_PROGRESS : ExifInterface.GPS_MEASUREMENT_INTERRUPTED) + ":" + str;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline
    public boolean isAsynchronous() {
        return this.isAsynchronous;
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline, com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        synchronized (this.mutex) {
            if (canRelease()) {
                super.release();
                clearAsyncTasks();
                return;
            }
            MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring release() in " + this.state + " state.", new Object[0]);
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline, com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void start() throws MediaPipelineBackendException {
        synchronized (this.mutex) {
            if (!isIdle()) {
                MpbLog.warnf("MediaPipeline " + this.inputFormat + " ignoring start() in " + this.state + " state", new Object[0]);
                return;
            }
            super.start();
            if (this.executor != null) {
                throw new IllegalStateException(("MediaPipeline " + this.mimeType + " executor must be null in start()").toString());
            }
            ScheduledExecutorService scheduledExecutorServiceCreatePipelineExecutor = createPipelineExecutor();
            this.executor = scheduledExecutorServiceCreatePipelineExecutor;
            Iterator<AsyncTaskLooper> it = this.asyncTaskLoopers.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                AsyncTaskLooper next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                ((ScheduledThreadPoolExecutor) scheduledExecutorServiceCreatePipelineExecutor).submit(next);
            }
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) scheduledExecutorServiceCreatePipelineExecutor;
            scheduledThreadPoolExecutor.submit(this.underrunWatchdog);
            VideoDecodeCadenceWatchdog videoDecodeCadenceWatchdog = this.videoDecodingCadenceWatchdog;
            if (videoDecodeCadenceWatchdog != null) {
                scheduledThreadPoolExecutor.submit(videoDecodeCadenceWatchdog);
            }
        }
    }

    @Override // com.amazon.avod.mpb.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        synchronized (this.mutex) {
            if (!isRunning()) {
                MpbLog.warnf("MediaPipeline " + this.mimeType + " ignoring stop() in " + this.state + " state", new Object[0]);
                return;
            }
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
            super.stop();
            ScheduledExecutorService scheduledExecutorService = this.executor;
            if (scheduledExecutorService == null) {
                throw new IllegalStateException(("MediaPipeline " + this.mimeType + " executor must nonnull in stop()").toString());
            }
            scheduledExecutorService.shutdownNow();
            try {
                try {
                    if (!scheduledExecutorService.awaitTermination(this.executorShutdownTimeoutMillis, TimeUnit.MILLISECONDS)) {
                        throw new IllegalStateException(("MediaPipeline " + this.mimeType + " terminate executor timeout " + this.executorShutdownTimeoutMillis + " ms exceeded").toString());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    MpbLog.warnf(e, "MediaPipeline " + this.mimeType + " interrupted while trying to terminate executor: " + e.getMessage(), new Object[0]);
                }
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
                MpbLog.logf("MediaPipeline for " + this.inputFormat + " took " + stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS) + " ms to stop", new Object[0]);
            } finally {
                this.executor = null;
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AsyncMediaPipeline(@NotNull MediaSource mediaSource, @NotNull Context appContext, @NotNull MediaClock mediaClock, @NotNull MediaPipelineContext mediaPipelineContext, boolean z, @Nullable Integer num, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @Nullable Integer num2, int i) {
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
}
