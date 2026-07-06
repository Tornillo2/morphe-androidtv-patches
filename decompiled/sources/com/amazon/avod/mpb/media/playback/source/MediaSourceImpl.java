package com.amazon.avod.mpb.media.playback.source;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.sample.AudioCodecType;
import com.amazon.avod.mpb.api.sample.AudioMetadata;
import com.amazon.avod.mpb.api.sample.AudioSample;
import com.amazon.avod.mpb.api.sample.BufferedMediaSample;
import com.amazon.avod.mpb.api.sample.MediaSample;
import com.amazon.avod.mpb.api.sample.VideoMetadata;
import com.amazon.avod.mpb.api.sample.VideoSample;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.VideoStreamType;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.mpb.media.playback.source.NalUnitUtil;
import com.amazon.avod.mpb.media.playback.util.TrackAdapterImpl;
import com.amazon.avod.mpb.media.playback.util.TrackAdaptionStatus;
import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nMediaSourceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaSourceImpl.kt\ncom/amazon/avod/mpb/media/playback/source/MediaSourceImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,485:1\n1#2:486\n*E\n"})
public final class MediaSourceImpl implements MediaSource {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final float DELTA = 1.0E-4f;
    public static final int H264_NAL_UNIT_TYPE = 7;
    public static final int H265_NAL_UNIT_TYPE = 33;

    @Nullable
    public final byte[] audioCodecData;

    @NotNull
    public final AtomicLong bufferedDurationMs;
    public float currentAspectRatio;
    public int currentVideoWidth;

    @NotNull
    public final AtomicBoolean hasStreamFinished;

    @NotNull
    public final AtomicBoolean hasTriggeredReadyToPlay;
    public final boolean isAudio;
    public final int maxQueueSize;

    @NotNull
    public final MediaPipelineContext mediaPipelineContext;

    @NotNull
    public final AtomicBoolean pendingCodecDataSubmission;
    public final int readyToPlayDurationMs;

    @NotNull
    public final Deque<BufferedMediaSample> sampleQueue;

    @NotNull
    public final TrackAdaptionStatus trackAdaptionStatus;
    public final boolean verboseLoggingEnabled;

    @NotNull
    public final AtomicReference<byte[]> videoCodecData;

    @NotNull
    public final VideoMetadata videoMetadata;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoStreamType.values().length];
            try {
                iArr[VideoStreamType.H265.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VideoStreamType.DVHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VideoStreamType.H264.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MediaSourceImpl(boolean z, @NotNull MediaPipelineContext mediaPipelineContext, @NotNull VideoMetadata videoMetadata, @NotNull AudioMetadata audioMetadata, @NotNull MPBInternalConfig config) {
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(videoMetadata, "videoMetadata");
        Intrinsics.checkNotNullParameter(audioMetadata, "audioMetadata");
        Intrinsics.checkNotNullParameter(config, "config");
        this.isAudio = z;
        this.mediaPipelineContext = mediaPipelineContext;
        this.videoMetadata = videoMetadata;
        this.trackAdaptionStatus = new TrackAdaptionStatus();
        this.sampleQueue = new ConcurrentLinkedDeque();
        this.videoCodecData = new AtomicReference<>();
        this.pendingCodecDataSubmission = new AtomicBoolean(true);
        this.hasStreamFinished = new AtomicBoolean(false);
        this.bufferedDurationMs = new AtomicLong(0L);
        this.hasTriggeredReadyToPlay = new AtomicBoolean(false);
        this.audioCodecData = buildAudioCodecData(audioMetadata);
        this.maxQueueSize = config.getMediaSourceMaxSize();
        this.readyToPlayDurationMs = config.getMediaSourceReadyToPlayDurationMs();
        this.verboseLoggingEnabled = mediaPipelineContext.devicePropertyProvider.isVerboseAvSyncLoggingEnabled();
        this.currentAspectRatio = -1.0f;
        this.currentVideoWidth = videoMetadata.pixelWidth;
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public void advance() {
        BufferedMediaSample bufferedMediaSamplePoll = this.sampleQueue.poll();
        if (bufferedMediaSamplePoll == null) {
            MpbLog.warnf("MediaSource advance() no-op, empty queue", new Object[0]);
        } else {
            this.mediaPipelineContext.removeSampleReference(bufferedMediaSamplePoll.mediaSample.getData());
        }
    }

    public final byte[] buildAudioCodecData(AudioMetadata audioMetadata) throws MediaPipelineBackendException {
        if (audioMetadata.codecType != AudioCodecType.AUDIO_CODEC_AAC) {
            return null;
        }
        return AacCodecSpecificDataFactory.INSTANCE.buildAacCodecSpecificData(audioMetadata.numChannels, audioMetadata.sampleRate);
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public void flush() {
        Iterator<BufferedMediaSample> it = this.sampleQueue.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            this.mediaPipelineContext.removeSampleReference(it.next().mediaSample.getData());
        }
        if (this.isAudio) {
            this.mediaPipelineContext.onAudioBufferFlush();
        } else {
            this.mediaPipelineContext.onVideoBufferFlush();
        }
        this.sampleQueue.clear();
        this.hasStreamFinished.set(false);
        this.videoCodecData.set(null);
        this.pendingCodecDataSubmission.set(true);
        this.bufferedDurationMs.set(0L);
        this.hasTriggeredReadyToPlay.set(false);
    }

    public final float getDisplayAspectRatio(int i, int i2, float f) {
        return (i * f) / i2;
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public int getNumberOfSamplesInBuffer() {
        return this.sampleQueue.size();
    }

    public final void handleAudioCodecData(MediaSample mediaSample) {
        byte[] bArr = this.audioCodecData;
        if (bArr == null || !this.pendingCodecDataSubmission.get()) {
            return;
        }
        this.pendingCodecDataSubmission.set(false);
        ByteBuffer byteBufferPut = ByteBuffer.allocate(bArr.length).put(bArr);
        byteBufferPut.flip();
        queueSample(new MediaSample(byteBufferPut, bArr.length, mediaSample.getPtsMs(), mediaSample.getDurationMs(), false, null), null, 2);
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public boolean hasNext() {
        return !this.sampleQueue.isEmpty();
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public boolean hasStreamFinished() {
        return this.hasStreamFinished.get();
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public boolean hasUnderrun() {
        boolean z = this.sampleQueue.isEmpty() && !this.hasStreamFinished.get() && this.hasTriggeredReadyToPlay.get();
        if (this.verboseLoggingEnabled) {
            MpbLog.logf("MediaSource:%s hasUnderrun:%s queueEmpty:%s streamFinished:%s readyToPlay:%s", this.isAudio ? "AUDIO" : "VIDEO", Boolean.valueOf(z), Boolean.valueOf(this.sampleQueue.isEmpty()), Boolean.valueOf(this.hasStreamFinished.get()), Boolean.valueOf(this.hasTriggeredReadyToPlay.get()));
        }
        return z;
    }

    public final boolean isDisplayAspectRatioDifferent(float f, float f2) {
        return Math.abs(f - f2) > 1.0E-4f;
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public void onPipelineUnderrun() {
        this.hasTriggeredReadyToPlay.set(false);
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public void onStreamFinish() {
        this.hasStreamFinished.set(true);
    }

    @Nullable
    public final SpsData processParameterSetData(@NotNull byte[] data) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(data, "data");
        boolean[] zArr = new boolean[3];
        int i = 0;
        while (i < data.length) {
            NalUnitUtil nalUnitUtil = NalUnitUtil.INSTANCE;
            int iFindNalUnit = nalUnitUtil.findNalUnit(data, i, data.length, zArr);
            if (iFindNalUnit >= data.length) {
                return null;
            }
            VideoStreamType videoStreamType = this.videoMetadata.codecType.videoStreamType;
            int i2 = videoStreamType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[videoStreamType.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (nalUnitUtil.getH265NalUnitType(data, iFindNalUnit) == 33) {
                    NalUnitUtil.H265SpsData h265SpsNalUnit = nalUnitUtil.parseH265SpsNalUnit(data, iFindNalUnit + 3, data.length);
                    return new SpsData(h265SpsNalUnit.width, h265SpsNalUnit.height, h265SpsNalUnit.pixelWidthHeightRatio);
                }
            } else {
                if (i2 != 3) {
                    return null;
                }
                if (nalUnitUtil.getNalUnitType(data, iFindNalUnit) == 7) {
                    NalUnitUtil.SpsData spsNalUnit = nalUnitUtil.parseSpsNalUnit(data, iFindNalUnit + 3, data.length);
                    return new SpsData(spsNalUnit.width, spsNalUnit.height, spsNalUnit.pixelWidthHeightRatio);
                }
            }
            i = iFindNalUnit + 3;
        }
        throw new MediaPipelineBackendException("Invalid sps: NAL unit type not found", MediaPipelineBackendResultCode.AV_MPB_VIDEO_INVALID_PARAMETER_SET);
    }

    public final void queueSample(MediaSample mediaSample, byte[] bArr, int i) {
        this.sampleQueue.add(new BufferedMediaSample(mediaSample, bArr, i));
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    @NotNull
    public BufferedMediaSample readSampleData(@NotNull ByteBuffer mediaCodecBuffer) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(mediaCodecBuffer, "mediaCodecBuffer");
        BufferedMediaSample bufferedMediaSamplePeek = this.sampleQueue.peek();
        int length = 0;
        if (bufferedMediaSamplePeek == null) {
            MpbLog.errorf("MediaSource readSampleData() failed empty queue!", new Object[0]);
            throw new MediaPipelineBackendException("MediaSource readSampleData() failed empty queue!", MediaPipelineBackendResultCode.AV_MPB_READ_SAMPLE_DATA_FAILED_EMPTY_QUEUE);
        }
        mediaCodecBuffer.clear();
        byte[] bArr = bufferedMediaSamplePeek.codecData;
        if (bArr != null) {
            mediaCodecBuffer.put(bArr);
            length = bArr.length;
            SpsData spsDataProcessParameterSetData = processParameterSetData(bArr);
            if (spsDataProcessParameterSetData != null) {
                int i = spsDataProcessParameterSetData.height;
                if (i == 0) {
                    throw new MediaPipelineBackendException("Invalid sps: 0 height", MediaPipelineBackendResultCode.AV_MPB_VIDEO_INVALID_PARAMETER_SET);
                }
                float displayAspectRatio = getDisplayAspectRatio(spsDataProcessParameterSetData.width, i, spsDataProcessParameterSetData.pixelWidthHeightRatio);
                if (isDisplayAspectRatioDifferent(this.currentAspectRatio, displayAspectRatio)) {
                    this.mediaPipelineContext.onVideoAspectRatioChange(TimeUnit.MILLISECONDS.toMicros(bufferedMediaSamplePeek.mediaSample.getPtsMs()), displayAspectRatio, spsDataProcessParameterSetData.width);
                    this.currentAspectRatio = displayAspectRatio;
                    this.currentVideoWidth = spsDataProcessParameterSetData.width;
                }
            }
        }
        ByteBuffer data = bufferedMediaSamplePeek.mediaSample.getData();
        data.rewind();
        TrackAdapterImpl.INSTANCE.writeAdaptedSample(data, mediaCodecBuffer, data.limit(), this.trackAdaptionStatus);
        if (this.trackAdaptionStatus.errorCode == MediaPipelineBackendResultCode.AV_MPB_SUCCESS) {
            if (mediaCodecBuffer.position() != bufferedMediaSamplePeek.mediaSample.getSize() + ((long) length)) {
                throw new MediaPipelineBackendException("Failed to adapt sample completely", MediaPipelineBackendResultCode.AV_MPB_SAMPLE_ADAPTION_FAILURE);
            }
            this.bufferedDurationMs.addAndGet(-bufferedMediaSamplePeek.mediaSample.getDurationMs());
            return bufferedMediaSamplePeek;
        }
        long ptsMs = bufferedMediaSamplePeek.mediaSample.getPtsMs();
        String strName = this.trackAdaptionStatus.errorCode.name();
        TrackAdaptionStatus trackAdaptionStatus = this.trackAdaptionStatus;
        throw new MediaPipelineBackendException("Failed to adapt sample @ " + ptsMs + " ms, Error " + strName + ", Diagnostics: sampleSize: " + trackAdaptionStatus.sampleBufferSize + ", sampleRemaining: " + trackAdaptionStatus.sampleBufferRemaining + ", decoderRemaining: " + trackAdaptionStatus.decoderBufferRemaining + ", bytesWritten: " + trackAdaptionStatus.bytesWritten, this.trackAdaptionStatus.errorCode);
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public void setDisplayAspectRatio(float f) {
        MediaSample mediaSample;
        if (this.isAudio) {
            MpbLog.warnf("setDisplayAspectRatio called on audio MediaSource, ignoring", new Object[0]);
            return;
        }
        MpbLog.logf("MediaSource applying DAR for AV1 : %s", Float.valueOf(f));
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        BufferedMediaSample bufferedMediaSamplePeekLast = this.sampleQueue.peekLast();
        this.mediaPipelineContext.onVideoAspectRatioChange(timeUnit.toMicros((bufferedMediaSamplePeekLast == null || (mediaSample = bufferedMediaSamplePeekLast.mediaSample) == null) ? 0L : mediaSample.getPtsMs()), f, this.currentVideoWidth);
    }

    @Override // com.amazon.avod.mpb.media.playback.source.MediaSource
    public int submitSample(@NotNull MediaSample mediaSample) {
        byte[] bArr;
        Intrinsics.checkNotNullParameter(mediaSample, "mediaSample");
        if (this.sampleQueue.size() >= this.maxQueueSize) {
            return MediaPipelineBackendResultCode.AV_MPB_BUFFER_FULL.resultCode;
        }
        boolean z = mediaSample instanceof AudioSample;
        if (z) {
            handleAudioCodecData(mediaSample);
        } else {
            VideoSample videoSample = (VideoSample) mediaSample;
            if (videoSample.isParameterSet) {
                this.videoCodecData.set(MediaSource.Companion.getContentsAsByteArray(videoSample.data));
                this.pendingCodecDataSubmission.set(true);
                return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
            }
        }
        if ((mediaSample instanceof VideoSample) && this.pendingCodecDataSubmission.get()) {
            this.pendingCodecDataSubmission.set(false);
            bArr = this.videoCodecData.get();
        } else {
            bArr = null;
        }
        queueSample(mediaSample, bArr, 0);
        this.mediaPipelineContext.addSampleReference(mediaSample.getData());
        if (this.bufferedDurationMs.addAndGet(mediaSample.getDurationMs()) >= this.readyToPlayDurationMs && !this.hasTriggeredReadyToPlay.getAndSet(true)) {
            if (z) {
                this.mediaPipelineContext.onAudioBufferReadyToPlay();
            } else {
                this.mediaPipelineContext.onVideoBufferReadyToPlay();
            }
        }
        return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
    }
}
