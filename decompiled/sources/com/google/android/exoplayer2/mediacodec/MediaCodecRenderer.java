package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.DtsUtil;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.decoder.CryptoConfig;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.FrameworkCryptoConfig;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.TimedValueQueue;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class MediaCodecRenderer extends BaseRenderer {
    public static final byte[] ADAPTATION_WORKAROUND_BUFFER = {0, 0, 1, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 66, -64, Ascii.VT, ExifInterface.MARKER_SOS, 37, IonRawBinaryWriter.CLOB_TYPE, 0, 0, 1, 104, ExifInterface.MARKER_SOF14, Ascii.SI, 19, 32, 0, 0, 1, 101, -120, -124, 13, ExifInterface.MARKER_SOF14, DtsUtil.FIRST_BYTE_UHD_FTOC_NONSYNC_BE, Ascii.CAN, IonRawBinaryWriter.BLOB_TYPE, 0, 47, -65, Ascii.FS, TarConstants.LF_LINK, ExifInterface.MARKER_SOF3, 39, 93, 120};
    public static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    public static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    public static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    public static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    public static final float CODEC_OPERATING_RATE_UNSET = -1.0f;
    public static final int DRAIN_ACTION_FLUSH = 1;
    public static final int DRAIN_ACTION_FLUSH_AND_UPDATE_DRM_SESSION = 2;
    public static final int DRAIN_ACTION_NONE = 0;
    public static final int DRAIN_ACTION_REINITIALIZE = 3;
    public static final int DRAIN_STATE_NONE = 0;
    public static final int DRAIN_STATE_SIGNAL_END_OF_STREAM = 1;
    public static final int DRAIN_STATE_WAIT_END_OF_STREAM = 2;
    public static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    public static final int RECONFIGURATION_STATE_NONE = 0;
    public static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    public static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    public static final String TAG = "MediaCodecRenderer";
    public final float assumedMinimumCodecOperatingRate;

    @Nullable
    public ArrayDeque<MediaCodecInfo> availableCodecInfos;
    public final DecoderInputBuffer buffer;
    public final BatchBuffer bypassBatchBuffer;
    public boolean bypassDrainAndReinitialize;
    public boolean bypassEnabled;
    public final DecoderInputBuffer bypassSampleBuffer;
    public boolean bypassSampleBufferPending;

    @Nullable
    public C2Mp3TimestampTracker c2Mp3TimestampTracker;

    @Nullable
    public MediaCodecAdapter codec;
    public int codecAdaptationWorkaroundMode;
    public final MediaCodecAdapter.Factory codecAdapterFactory;
    public int codecDrainAction;
    public int codecDrainState;

    @Nullable
    public DrmSession codecDrmSession;
    public boolean codecHasOutputMediaFormat;
    public long codecHotswapDeadlineMs;

    @Nullable
    public MediaCodecInfo codecInfo;

    @Nullable
    public Format codecInputFormat;
    public String codecName;
    public boolean codecNeedsAdaptationWorkaroundBuffer;
    public boolean codecNeedsDiscardToSpsWorkaround;
    public boolean codecNeedsEosBufferTimestampWorkaround;
    public boolean codecNeedsEosFlushWorkaround;
    public boolean codecNeedsEosOutputExceptionWorkaround;
    public boolean codecNeedsEosPropagation;
    public boolean codecNeedsFlushWorkaround;
    public boolean codecNeedsMonoChannelCountWorkaround;
    public boolean codecNeedsSosFlushWorkaround;
    public float codecOperatingRate;

    @Nullable
    public MediaFormat codecOutputMediaFormat;
    public boolean codecOutputMediaFormatChanged;
    public boolean codecReceivedBuffers;
    public boolean codecReceivedEos;
    public int codecReconfigurationState;
    public boolean codecReconfigured;
    public float currentPlaybackSpeed;
    public final ArrayList<Long> decodeOnlyPresentationTimestamps;
    public DecoderCounters decoderCounters;
    public final boolean enableDecoderFallback;

    @Nullable
    public Format inputFormat;
    public int inputIndex;
    public boolean inputStreamEnded;
    public boolean isDecodeOnlyOutputBuffer;
    public boolean isLastOutputBuffer;
    public long largestQueuedPresentationTimeUs;
    public long lastBufferInStreamPresentationTimeUs;
    public long lastProcessedOutputBufferTimeUs;
    public final Logger log;
    public final MediaCodecSelector mediaCodecSelector;

    @Nullable
    public MediaCrypto mediaCrypto;
    public boolean mediaCryptoRequiresSecureDecoder;
    public boolean needToNotifyOutputFormatChangeAfterStreamChange;
    public final DecoderInputBuffer noDataBuffer;

    @Nullable
    public ByteBuffer outputBuffer;
    public final MediaCodec.BufferInfo outputBufferInfo;

    @Nullable
    public Format outputFormat;
    public int outputIndex;
    public boolean outputStreamEnded;
    public OutputStreamInfo outputStreamInfo;
    public boolean pendingOutputEndOfStream;
    public final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;

    @Nullable
    public ExoPlaybackException pendingPlaybackException;

    @Nullable
    public DecoderInitializationException preferredDecoderInitializationException;
    public long renderTimeLimitMs;
    public boolean shouldSkipAdaptationWorkaroundOutputBuffer;

    @Nullable
    public DrmSession sourceDrmSession;
    public float targetPlaybackSpeed;
    public boolean waitingForFirstSampleInFormat;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class Api31 {
        @DoNotInline
        public static void setLogSessionIdToMediaCodecFormat(MediaCodecAdapter.Configuration configuration, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                return;
            }
            configuration.mediaFormat.setString("log-session-id", logSessionId.getStringId());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = new OutputStreamInfo(-9223372036854775807L, -9223372036854775807L, -9223372036854775807L);
        public final TimedValueQueue<Format> formatQueue = new TimedValueQueue<>();
        public final long previousStreamLastBufferTimeUs;
        public final long startPositionUs;
        public final long streamOffsetUs;

        public OutputStreamInfo(long j, long j2, long j3) {
            this.previousStreamLastBufferTimeUs = j;
            this.startPositionUs = j2;
            this.streamOffsetUs = j3;
        }
    }

    public MediaCodecRenderer(int i, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, boolean z, float f) {
        super(i);
        this.log = new Logger(Logger.Module.AudioVideoCommon, "MediaCodecRenderer");
        this.codecName = "CodecNameUnknown";
        this.codecAdapterFactory = factory;
        mediaCodecSelector.getClass();
        this.mediaCodecSelector = mediaCodecSelector;
        this.enableDecoderFallback = z;
        this.assumedMinimumCodecOperatingRate = f;
        this.noDataBuffer = DecoderInputBuffer.newNoDataInstance();
        this.buffer = new DecoderInputBuffer(0, 0);
        this.bypassSampleBuffer = new DecoderInputBuffer(2, 0);
        BatchBuffer batchBuffer = new BatchBuffer();
        this.bypassBatchBuffer = batchBuffer;
        this.decodeOnlyPresentationTimestamps = new ArrayList<>();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.currentPlaybackSpeed = 1.0f;
        this.targetPlaybackSpeed = 1.0f;
        this.renderTimeLimitMs = -9223372036854775807L;
        this.pendingOutputStreamChanges = new ArrayDeque<>();
        setOutputStreamInfo(OutputStreamInfo.UNSET);
        batchBuffer.ensureSpaceForWrite(0);
        batchBuffer.data.order(ByteOrder.nativeOrder());
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecReconfigurationState = 0;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastBufferInStreamPresentationTimeUs = -9223372036854775807L;
        this.lastProcessedOutputBufferTimeUs = -9223372036854775807L;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
    }

    public static boolean codecNeedsDiscardToSpsWorkaround(String str, Format format) {
        return Util.SDK_INT < 21 && format.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    public static boolean codecNeedsEosBufferTimestampWorkaround(String str) {
        if (Util.SDK_INT >= 21 || !"OMX.SEC.mp3.dec".equals(str) || !"samsung".equals(Util.MANUFACTURER)) {
            return false;
        }
        String str2 = Util.DEVICE;
        return str2.startsWith("baffin") || str2.startsWith("grand") || str2.startsWith("fortuna") || str2.startsWith("gprimelte") || str2.startsWith("j2y18lte") || str2.startsWith("ms01");
    }

    public static boolean codecNeedsEosFlushWorkaround(String str) {
        int i = Util.SDK_INT;
        if (i <= 23 && "OMX.google.vorbis.decoder".equals(str)) {
            return true;
        }
        if (i > 19) {
            return false;
        }
        String str2 = Util.DEVICE;
        if ("hb2000".equals(str2) || "stvm8".equals(str2)) {
            return "OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str);
        }
        return false;
    }

    public static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    public static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        int i = Util.SDK_INT;
        if (i <= 25 && "OMX.rk.video_decoder.avc".equals(str)) {
            return true;
        }
        if (i <= 17 && "OMX.allwinner.video.decoder.avc".equals(str)) {
            return true;
        }
        if (i > 29 || !("OMX.broadcom.video_decoder.tunnel".equals(str) || "OMX.broadcom.video_decoder.tunnel.secure".equals(str) || "OMX.bcm.vdec.avc.tunnel".equals(str) || "OMX.bcm.vdec.avc.tunnel.secure".equals(str) || "OMX.bcm.vdec.hevc.tunnel".equals(str) || "OMX.bcm.vdec.hevc.tunnel.secure".equals(str))) {
            return "Amazon".equals(Util.MANUFACTURER) && AmazonQuirks.FIRETV_GEN2_DEVICE_MODEL.equals(Util.MODEL) && mediaCodecInfo.secure;
        }
        return true;
    }

    public static boolean codecNeedsFlushWorkaround(String str) {
        int i = Util.SDK_INT;
        if (i < 18) {
            return true;
        }
        if (i == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) {
            return true;
        }
        if (i == 19 && Util.MODEL.startsWith("SM-G800")) {
            return "OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str);
        }
        return false;
    }

    public static boolean codecNeedsMonoChannelCountWorkaround(String str, Format format) {
        return Util.SDK_INT <= 18 && format.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
    }

    public static boolean codecNeedsSosFlushWorkaround(String str) {
        return Util.SDK_INT == 29 && "c2.android.aac.decoder".equals(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean feedInputBuffer() throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            Method dump skipped, instruction units count: 769
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.feedInputBuffer():boolean");
    }

    public static boolean isMediaCodecException(IllegalStateException illegalStateException) {
        if (Util.SDK_INT >= 21 && (illegalStateException instanceof MediaCodec.CodecException)) {
            return true;
        }
        StackTraceElement[] stackTrace = illegalStateException.getStackTrace();
        return stackTrace.length > 0 && stackTrace[0].getClassName().equals("android.media.MediaCodec");
    }

    @RequiresApi(21)
    public static boolean isMediaCodecExceptionV21(IllegalStateException illegalStateException) {
        return illegalStateException instanceof MediaCodec.CodecException;
    }

    @RequiresApi(21)
    public static boolean isRecoverableMediaCodecExceptionV21(IllegalStateException illegalStateException) {
        if (illegalStateException instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) illegalStateException).isRecoverable();
        }
        return false;
    }

    @TargetApi(23)
    private void processEndOfStream() throws ExoPlaybackException {
        int i = this.codecDrainAction;
        if (i == 1) {
            flushCodec();
            return;
        }
        if (i == 2) {
            flushCodec();
            updateDrmSessionV23();
        } else if (i == 3) {
            reinitializeCodec();
        } else {
            this.outputStreamEnded = true;
            renderToEndOfStream();
        }
    }

    private void setSourceDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.CC.replaceSession(this.sourceDrmSession, drmSession);
        this.sourceDrmSession = drmSession;
    }

    public static boolean supportsFormatDrm(Format format) {
        int i = format.cryptoType;
        return i == 0 || i == 2;
    }

    public final void bypassRead() throws ExoPlaybackException {
        Assertions.checkState(!this.inputStreamEnded);
        FormatHolder formatHolder = getFormatHolder();
        this.bypassSampleBuffer.clear();
        do {
            this.bypassSampleBuffer.clear();
            int source = readSource(formatHolder, this.bypassSampleBuffer, 0);
            if (source == -5) {
                onInputFormatChanged(formatHolder);
                return;
            }
            if (source != -4) {
                if (source != -3) {
                    throw new IllegalStateException();
                }
                return;
            } else {
                if (this.bypassSampleBuffer.getFlag(4)) {
                    this.inputStreamEnded = true;
                    return;
                }
                if (this.waitingForFirstSampleInFormat) {
                    Format format = this.inputFormat;
                    format.getClass();
                    this.outputFormat = format;
                    onOutputFormatChanged(format, null);
                    this.waitingForFirstSampleInFormat = false;
                }
                this.bypassSampleBuffer.flip();
            }
        } while (this.bypassBatchBuffer.append(this.bypassSampleBuffer));
        this.bypassSampleBufferPending = true;
    }

    public final boolean bypassRender(long j, long j2) throws ExoPlaybackException {
        boolean z;
        Assertions.checkState(!this.outputStreamEnded);
        if (this.bypassBatchBuffer.hasSamples()) {
            BatchBuffer batchBuffer = this.bypassBatchBuffer;
            z = false;
            if (!processOutputBuffer(j, j2, null, batchBuffer.data, this.outputIndex, 0, batchBuffer.sampleCount, batchBuffer.timeUs, batchBuffer.getFlag(Integer.MIN_VALUE), this.bypassBatchBuffer.getFlag(4), this.outputFormat)) {
                return false;
            }
            onProcessedOutputBuffer(this.bypassBatchBuffer.lastSampleTimeUs);
            this.bypassBatchBuffer.clear();
        } else {
            z = false;
        }
        if (this.inputStreamEnded) {
            this.outputStreamEnded = true;
            return z;
        }
        if (this.bypassSampleBufferPending) {
            Assertions.checkState(this.bypassBatchBuffer.append(this.bypassSampleBuffer));
            this.bypassSampleBufferPending = z;
        }
        if (this.bypassDrainAndReinitialize) {
            if (this.bypassBatchBuffer.hasSamples()) {
                return true;
            }
            disableBypass();
            this.bypassDrainAndReinitialize = z;
            maybeInitCodecOrBypass();
            if (!this.bypassEnabled) {
                return z;
            }
        }
        bypassRead();
        if (this.bypassBatchBuffer.hasSamples()) {
            this.bypassBatchBuffer.flip();
        }
        if (this.bypassBatchBuffer.hasSamples() || this.inputStreamEnded || this.bypassDrainAndReinitialize) {
            return true;
        }
        return z;
    }

    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, 0, 1);
    }

    public final int codecAdaptationWorkaroundMode(String str) {
        int i = Util.SDK_INT;
        if (i <= 25 && "OMX.Exynos.avc.dec.secure".equals(str)) {
            String str2 = Util.MODEL;
            if (str2.startsWith("SM-T585") || str2.startsWith("SM-A510") || str2.startsWith("SM-A520") || str2.startsWith("SM-J700")) {
                return 2;
            }
        }
        if (i >= 24) {
            return 0;
        }
        if (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) {
            return 0;
        }
        String str3 = Util.DEVICE;
        return ("flounder".equals(str3) || "flounder_lte".equals(str3) || "grouper".equals(str3) || "tilapia".equals(str3)) ? 1 : 0;
    }

    public MediaCodecDecoderException createDecoderException(Throwable th, @Nullable MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecDecoderException(th, mediaCodecInfo);
    }

    public final void disableBypass() {
        this.bypassDrainAndReinitialize = false;
        this.bypassBatchBuffer.clear();
        this.bypassSampleBuffer.clear();
        this.bypassSampleBufferPending = false;
        this.bypassEnabled = false;
    }

    public final boolean drainAndFlushCodec() {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsFlushWorkaround || this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 1;
        }
        return true;
    }

    public final void drainAndReinitializeCodec() throws ExoPlaybackException {
        if (!this.codecReceivedBuffers) {
            reinitializeCodec();
        } else {
            this.codecDrainState = 1;
            this.codecDrainAction = 3;
        }
    }

    @TargetApi(23)
    public final boolean drainAndUpdateCodecDrmSessionV23() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsFlushWorkaround || this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 2;
        } else {
            updateDrmSessionV23();
        }
        return true;
    }

    public final boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException {
        boolean z;
        boolean zProcessOutputBuffer;
        MediaCodecAdapter mediaCodecAdapter;
        ByteBuffer byteBuffer;
        int i;
        MediaCodec.BufferInfo bufferInfo;
        int iDequeueOutputBufferIndex;
        if (this.log.allowVerbose()) {
            Logger logger = this.log;
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("drainOutputBuffer: positionUs = ", j, ", elapsedRealtimeUs = ");
            sbM.append(j2);
            logger.v(sbM.toString());
        }
        if (!hasOutputBuffer()) {
            if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
                try {
                    iDequeueOutputBufferIndex = this.codec.dequeueOutputBufferIndex(this.outputBufferInfo);
                } catch (IllegalStateException unused) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            } else {
                iDequeueOutputBufferIndex = this.codec.dequeueOutputBufferIndex(this.outputBufferInfo);
            }
            if (iDequeueOutputBufferIndex < 0) {
                if (iDequeueOutputBufferIndex == -2) {
                    processOutputMediaFormatChanged();
                    return true;
                }
                if (this.codecNeedsEosPropagation && (this.inputStreamEnded || this.codecDrainState == 2)) {
                    processEndOfStream();
                }
                return false;
            }
            if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                this.codec.releaseOutputBuffer(iDequeueOutputBufferIndex, false);
                return true;
            }
            MediaCodec.BufferInfo bufferInfo2 = this.outputBufferInfo;
            if (bufferInfo2.size == 0 && (bufferInfo2.flags & 4) != 0) {
                processEndOfStream();
                return false;
            }
            this.outputIndex = iDequeueOutputBufferIndex;
            ByteBuffer outputBuffer = this.codec.getOutputBuffer(iDequeueOutputBufferIndex);
            this.outputBuffer = outputBuffer;
            if (outputBuffer != null) {
                outputBuffer.position(this.outputBufferInfo.offset);
                ByteBuffer byteBuffer2 = this.outputBuffer;
                MediaCodec.BufferInfo bufferInfo3 = this.outputBufferInfo;
                byteBuffer2.limit(bufferInfo3.offset + bufferInfo3.size);
            }
            if (this.codecNeedsEosBufferTimestampWorkaround) {
                MediaCodec.BufferInfo bufferInfo4 = this.outputBufferInfo;
                if (bufferInfo4.presentationTimeUs == 0 && (bufferInfo4.flags & 4) != 0) {
                    long j3 = this.largestQueuedPresentationTimeUs;
                    if (j3 != -9223372036854775807L) {
                        bufferInfo4.presentationTimeUs = j3;
                    }
                }
            }
            this.isDecodeOnlyOutputBuffer = isDecodeOnlyBuffer(this.outputBufferInfo.presentationTimeUs);
            long j4 = this.lastBufferInStreamPresentationTimeUs;
            long j5 = this.outputBufferInfo.presentationTimeUs;
            this.isLastOutputBuffer = j4 == j5;
            updateOutputFormatForTime(j5);
        }
        if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
            try {
                mediaCodecAdapter = this.codec;
                byteBuffer = this.outputBuffer;
                i = this.outputIndex;
                bufferInfo = this.outputBufferInfo;
                z = false;
            } catch (IllegalStateException unused2) {
                z = false;
            }
            try {
                zProcessOutputBuffer = processOutputBuffer(j, j2, mediaCodecAdapter, byteBuffer, i, bufferInfo.flags, 1, bufferInfo.presentationTimeUs, this.isDecodeOnlyOutputBuffer, this.isLastOutputBuffer, this.outputFormat);
            } catch (IllegalStateException unused3) {
                processEndOfStream();
                if (this.outputStreamEnded) {
                    releaseCodec();
                }
                return z;
            }
        } else {
            z = false;
            MediaCodecAdapter mediaCodecAdapter2 = this.codec;
            ByteBuffer byteBuffer3 = this.outputBuffer;
            int i2 = this.outputIndex;
            MediaCodec.BufferInfo bufferInfo5 = this.outputBufferInfo;
            zProcessOutputBuffer = processOutputBuffer(j, j2, mediaCodecAdapter2, byteBuffer3, i2, bufferInfo5.flags, 1, bufferInfo5.presentationTimeUs, this.isDecodeOnlyOutputBuffer, this.isLastOutputBuffer, this.outputFormat);
        }
        if (zProcessOutputBuffer) {
            onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
            boolean z2 = (this.outputBufferInfo.flags & 4) != 0;
            resetOutputBuffer();
            if (!z2) {
                return true;
            }
            processEndOfStream();
        }
        return z;
    }

    public final boolean drmNeedsCodecReinitialization(MediaCodecInfo mediaCodecInfo, Format format, @Nullable DrmSession drmSession, @Nullable DrmSession drmSession2) throws ExoPlaybackException {
        FrameworkCryptoConfig frameworkCryptoConfig;
        if (drmSession == drmSession2) {
            return false;
        }
        if (drmSession2 == null || drmSession == null || !drmSession2.getSchemeUuid().equals(drmSession.getSchemeUuid()) || Util.SDK_INT < 23) {
            return true;
        }
        UUID uuid = C.PLAYREADY_UUID;
        if (uuid.equals(drmSession.getSchemeUuid()) || uuid.equals(drmSession2.getSchemeUuid()) || (frameworkCryptoConfig = getFrameworkCryptoConfig(drmSession2)) == null) {
            return true;
        }
        return !mediaCodecInfo.secure && (frameworkCryptoConfig.forceAllowInsecureDecoderComponents ? false : drmSession2.requiresSecureDecoder(format.sampleMimeType));
    }

    public final void flushCodec() {
        try {
            this.codec.flush();
        } finally {
            resetCodecStateForFlush();
        }
    }

    public final boolean flushOrReinitializeCodec() throws ExoPlaybackException {
        boolean zFlushOrReleaseCodec = flushOrReleaseCodec();
        if (zFlushOrReleaseCodec) {
            maybeInitCodecOrBypass();
        }
        return zFlushOrReleaseCodec;
    }

    public boolean flushOrReleaseCodec() {
        if (this.codec == null) {
            return false;
        }
        int i = this.codecDrainAction;
        if (i == 3 || this.codecNeedsFlushWorkaround || ((this.codecNeedsSosFlushWorkaround && !this.codecHasOutputMediaFormat) || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos))) {
            releaseCodec();
            return true;
        }
        if (i == 2) {
            int i2 = Util.SDK_INT;
            Assertions.checkState(i2 >= 23);
            if (i2 >= 23) {
                try {
                    updateDrmSessionV23();
                } catch (ExoPlaybackException e) {
                    Log.w("MediaCodecRenderer", "Failed to update the DRM session, releasing the codec instead.", e);
                    releaseCodec();
                    return true;
                }
            }
        }
        flushCodec();
        return false;
    }

    public final List<MediaCodecInfo> getAvailableCodecInfos(boolean z) throws MediaCodecUtil.DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(this.mediaCodecSelector, this.inputFormat, z);
        if (!((ArrayList) decoderInfos).isEmpty() || !z) {
            return decoderInfos;
        }
        List<MediaCodecInfo> decoderInfos2 = getDecoderInfos(this.mediaCodecSelector, this.inputFormat, false);
        if (!((ArrayList) decoderInfos2).isEmpty()) {
            Log.w("MediaCodecRenderer", "Drm session requires secure decoder for " + this.inputFormat.sampleMimeType + ", but no secure decoder available. Trying to proceed with " + decoderInfos2 + ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        }
        return decoderInfos2;
    }

    @Nullable
    public final MediaCodecAdapter getCodec() {
        return this.codec;
    }

    @Nullable
    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    public boolean getCodecNeedsEosPropagation() {
        return false;
    }

    public float getCodecOperatingRate() {
        return this.codecOperatingRate;
    }

    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        return -1.0f;
    }

    @Nullable
    public final MediaFormat getCodecOutputMediaFormat() {
        return this.codecOutputMediaFormat;
    }

    public abstract List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException;

    @Nullable
    public final FrameworkCryptoConfig getFrameworkCryptoConfig(DrmSession drmSession) throws ExoPlaybackException {
        CryptoConfig cryptoConfig = drmSession.getCryptoConfig();
        if (cryptoConfig == null || (cryptoConfig instanceof FrameworkCryptoConfig)) {
            return (FrameworkCryptoConfig) cryptoConfig;
        }
        throw createRendererException(new IllegalArgumentException("Expecting FrameworkCryptoConfig but found: " + cryptoConfig), this.inputFormat, false, 6001);
    }

    public abstract MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, @Nullable MediaCrypto mediaCrypto, float f);

    public final long getOutputStreamOffsetUs() {
        return this.outputStreamInfo.streamOffsetUs;
    }

    public float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public final boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    public final void initBypass(Format format) {
        disableBypass();
        String str = format.sampleMimeType;
        if ("audio/mp4a-latm".equals(str) || "audio/mpeg".equals(str) || "audio/opus".equals(str)) {
            this.bypassBatchBuffer.setMaxSampleCount(32);
        } else {
            this.bypassBatchBuffer.setMaxSampleCount(1);
        }
        this.bypassEnabled = true;
    }

    public final void initCodec(MediaCodecInfo mediaCodecInfo, MediaCrypto mediaCrypto) throws Exception {
        float codecOperatingRateV23;
        String str = mediaCodecInfo.name;
        int i = Util.SDK_INT;
        if (i < 23) {
            codecOperatingRateV23 = -1.0f;
        } else {
            float f = this.targetPlaybackSpeed;
            Format format = this.inputFormat;
            Format[] formatArr = this.streamFormats;
            formatArr.getClass();
            codecOperatingRateV23 = getCodecOperatingRateV23(f, format, formatArr);
        }
        float f2 = codecOperatingRateV23 > this.assumedMinimumCodecOperatingRate ? codecOperatingRateV23 : -1.0f;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        MediaCodecAdapter.Configuration mediaCodecConfiguration = getMediaCodecConfiguration(mediaCodecInfo, this.inputFormat, mediaCrypto, f2);
        if (i >= 31) {
            PlayerId playerId = this.playerId;
            playerId.getClass();
            Api31.setLogSessionIdToMediaCodecFormat(mediaCodecConfiguration, playerId);
        }
        try {
            TraceUtil.beginSection("createCodec:" + str);
            this.codec = this.codecAdapterFactory.createAdapter(mediaCodecConfiguration);
            TraceUtil.endSection();
            float f3 = f2;
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            if (!mediaCodecInfo.isFormatSupported(this.inputFormat)) {
                Log.w("MediaCodecRenderer", String.format(Locale.US, "Format exceeds selected codec's capabilities [%s, %s]", Format.toLogString(this.inputFormat), str));
            }
            this.codecInfo = mediaCodecInfo;
            this.codecOperatingRate = f3;
            this.codecInputFormat = this.inputFormat;
            this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(str);
            this.codecNeedsDiscardToSpsWorkaround = codecNeedsDiscardToSpsWorkaround(str, this.codecInputFormat);
            this.codecNeedsFlushWorkaround = codecNeedsFlushWorkaround(str);
            this.codecNeedsSosFlushWorkaround = codecNeedsSosFlushWorkaround(str);
            this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(str);
            this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(str);
            this.codecNeedsEosBufferTimestampWorkaround = codecNeedsEosBufferTimestampWorkaround(str);
            this.codecNeedsMonoChannelCountWorkaround = codecNeedsMonoChannelCountWorkaround(str, this.codecInputFormat);
            this.codecNeedsEosPropagation = codecNeedsEosPropagationWorkaround(mediaCodecInfo) || getCodecNeedsEosPropagation();
            this.codec.getClass();
            if ("c2.android.mp3.decoder".equals(mediaCodecInfo.name)) {
                this.c2Mp3TimestampTracker = new C2Mp3TimestampTracker();
            }
            if (this.state == 2) {
                this.codecHotswapDeadlineMs = SystemClock.elapsedRealtime() + 1000;
            }
            this.decoderCounters.decoderInitCount++;
            onCodecInitialized(str, mediaCodecConfiguration, jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
        } catch (Throwable th) {
            TraceUtil.endSection();
            throw th;
        }
    }

    public final boolean isDecodeOnlyBuffer(long j) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i = 0; i < size; i++) {
            if (this.decodeOnlyPresentationTimestamps.get(i).longValue() == j) {
                this.decodeOnlyPresentationTimestamps.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        if (this.inputFormat == null) {
            return false;
        }
        if (isSourceReady() || hasOutputBuffer()) {
            return true;
        }
        return this.codecHotswapDeadlineMs != -9223372036854775807L && SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs;
    }

    public final void maybeInitCodecOrBypass() throws ExoPlaybackException {
        Format format;
        if (this.codec != null || this.bypassEnabled || (format = this.inputFormat) == null) {
            return;
        }
        if (this.sourceDrmSession == null && shouldUseBypass(format)) {
            initBypass(this.inputFormat);
            return;
        }
        setCodecDrmSession(this.sourceDrmSession);
        String str = this.inputFormat.sampleMimeType;
        DrmSession drmSession = this.codecDrmSession;
        if (drmSession != null) {
            if (this.mediaCrypto == null) {
                FrameworkCryptoConfig frameworkCryptoConfig = getFrameworkCryptoConfig(drmSession);
                if (frameworkCryptoConfig != null) {
                    try {
                        MediaCrypto mediaCrypto = new MediaCrypto(frameworkCryptoConfig.uuid, frameworkCryptoConfig.sessionId);
                        this.mediaCrypto = mediaCrypto;
                        this.mediaCryptoRequiresSecureDecoder = !frameworkCryptoConfig.forceAllowInsecureDecoderComponents && mediaCrypto.requiresSecureDecoderComponent(str);
                    } catch (MediaCryptoException e) {
                        throw createRendererException(e, this.inputFormat, false, 6006);
                    }
                } else if (this.codecDrmSession.getError() == null) {
                    return;
                }
            }
            if (FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC) {
                int state = this.codecDrmSession.getState();
                if (state == 1) {
                    DrmSession.DrmSessionException error = this.codecDrmSession.getError();
                    error.getClass();
                    throw createRendererException(error, this.inputFormat, false, error.errorCode);
                }
                if (state != 4) {
                    return;
                }
            }
        }
        try {
            maybeInitCodecWithFallback(this.mediaCrypto, this.mediaCryptoRequiresSecureDecoder);
        } catch (DecoderInitializationException e2) {
            throw createRendererException(e2, this.inputFormat, false, 4001);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00af A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x004c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void maybeInitCodecWithFallback(android.media.MediaCrypto r8, boolean r9) throws com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException {
        /*
            r7 = this;
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r0 = r7.availableCodecInfos
            r1 = 0
            if (r0 != 0) goto L3c
            java.util.List r0 = r7.getAvailableCodecInfos(r9)     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            java.util.ArrayDeque r2 = new java.util.ArrayDeque     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            r2.<init>()     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            r7.availableCodecInfos = r2     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            boolean r3 = r7.enableDecoderFallback     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            if (r3 == 0) goto L1a
            r2.addAll(r0)     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            goto L2e
        L18:
            r8 = move-exception
            goto L31
        L1a:
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            boolean r2 = r0.isEmpty()     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            if (r2 != 0) goto L2e
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r2 = r7.availableCodecInfos     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            r3 = 0
            java.lang.Object r0 = r0.get(r3)     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r0     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            r2.add(r0)     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
        L2e:
            r7.preferredDecoderInitializationException = r1     // Catch: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException -> L18
            goto L3c
        L31:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r1 = r7.inputFormat
            r2 = -49998(0xffffffffffff3cb2, float:NaN)
            r0.<init>(r1, r8, r9, r2)
            throw r0
        L3c:
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r0 = r7.availableCodecInfos
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto Lb5
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r0 = r7.availableCodecInfos
            java.lang.Object r0 = r0.peekFirst()
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r0
        L4c:
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r2 = r7.codec
            if (r2 != 0) goto Lb2
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r2 = r7.availableCodecInfos
            java.lang.Object r2 = r2.peekFirst()
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r2
            boolean r3 = r7.shouldInitCodec(r2)
            if (r3 != 0) goto L5f
            return
        L5f:
            r7.initCodec(r2, r8)     // Catch: java.lang.Exception -> L63
            goto L4c
        L63:
            r3 = move-exception
            java.lang.String r4 = "MediaCodecRenderer"
            if (r2 != r0) goto L78
            java.lang.String r3 = "Preferred decoder instantiation failed. Sleeping for 50ms then retrying."
            com.google.android.exoplayer2.util.Log.w(r4, r3)     // Catch: java.lang.Exception -> L76
            r5 = 50
            java.lang.Thread.sleep(r5)     // Catch: java.lang.Exception -> L76
            r7.initCodec(r2, r8)     // Catch: java.lang.Exception -> L76
            goto L4c
        L76:
            r3 = move-exception
            goto L79
        L78:
            throw r3     // Catch: java.lang.Exception -> L76
        L79:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed to initialize decoder: "
            r5.<init>(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            com.google.android.exoplayer2.util.Log.w(r4, r5, r3)
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r4 = r7.availableCodecInfos
            r4.removeFirst()
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r4 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r5 = r7.inputFormat
            r4.<init>(r5, r3, r9, r2)
            r7.onCodecError(r4)
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r2 = r7.preferredDecoderInitializationException
            if (r2 != 0) goto La0
            r7.preferredDecoderInitializationException = r4
            goto La6
        La0:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r2 = r2.copyWithFallbackException(r4)
            r7.preferredDecoderInitializationException = r2
        La6:
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> r2 = r7.availableCodecInfos
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto Laf
            goto L4c
        Laf:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r8 = r7.preferredDecoderInitializationException
            throw r8
        Lb2:
            r7.availableCodecInfos = r1
            return
        Lb5:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r8 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r0 = r7.inputFormat
            r2 = -49999(0xffffffffffff3cb1, float:NaN)
            r8.<init>(r0, r1, r9, r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.maybeInitCodecWithFallback(android.media.MediaCrypto, boolean):void");
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        setOutputStreamInfo(OutputStreamInfo.UNSET);
        this.pendingOutputStreamChanges.clear();
        flushOrReleaseCodec();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0096  */
    @androidx.annotation.Nullable
    @androidx.annotation.CallSuper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.android.exoplayer2.decoder.DecoderReuseEvaluation onInputFormatChanged(com.google.android.exoplayer2.FormatHolder r12) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.onInputFormatChanged(com.google.android.exoplayer2.FormatHolder):com.google.android.exoplayer2.decoder.DecoderReuseEvaluation");
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.log.i("onPositionReset: " + j + ", joining: " + z);
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.pendingOutputEndOfStream = false;
        if (this.bypassEnabled) {
            this.bypassBatchBuffer.clear();
            this.bypassSampleBuffer.clear();
            this.bypassSampleBufferPending = false;
        } else {
            flushOrReinitializeCodec();
        }
        if (this.outputStreamInfo.formatQueue.size() > 0) {
            this.waitingForFirstSampleInFormat = true;
        }
        this.outputStreamInfo.formatQueue.clear();
        this.pendingOutputStreamChanges.clear();
    }

    @CallSuper
    public void onProcessedOutputBuffer(long j) {
        this.lastProcessedOutputBufferTimeUs = j;
        while (!this.pendingOutputStreamChanges.isEmpty() && j >= this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) {
            setOutputStreamInfo(this.pendingOutputStreamChanges.poll());
            onProcessedStreamChange();
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onReset() {
        try {
            disableBypass();
            releaseCodec();
        } finally {
            setSourceDrmSession(null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0034, code lost:
    
        if (r4 >= r0) goto L14;
     */
    @Override // com.google.android.exoplayer2.BaseRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onStreamChanged(com.google.android.exoplayer2.Format[] r13, long r14, long r16) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r12 = this;
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo r13 = r12.outputStreamInfo
            long r0 = r13.streamOffsetUs
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 != 0) goto L1e
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo r4 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = r14
            r9 = r16
            r4.<init>(r5, r7, r9)
            r12.setOutputStreamInfo(r4)
            return
        L1e:
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo> r13 = r12.pendingOutputStreamChanges
            boolean r13 = r13.isEmpty()
            if (r13 == 0) goto L52
            long r0 = r12.largestQueuedPresentationTimeUs
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L36
            long r4 = r12.lastProcessedOutputBufferTimeUs
            int r13 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r13 == 0) goto L52
            int r13 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r13 < 0) goto L52
        L36:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo r5 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.setOutputStreamInfo(r5)
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo r13 = r12.outputStreamInfo
            long r0 = r13.streamOffsetUs
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L51
            r12.onProcessedStreamChange()
        L51:
            return
        L52:
            java.util.ArrayDeque<com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo> r13 = r12.pendingOutputStreamChanges
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo r5 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$OutputStreamInfo
            long r6 = r12.largestQueuedPresentationTimeUs
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r13.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.onStreamChanged(com.google.android.exoplayer2.Format[], long, long):void");
    }

    public abstract boolean processOutputBuffer(long j, long j2, @Nullable MediaCodecAdapter mediaCodecAdapter, @Nullable ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException;

    public final void processOutputMediaFormatChanged() {
        this.codecHasOutputMediaFormat = true;
        MediaFormat outputFormat = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat.setInteger("channel-count", 1);
        }
        this.codecOutputMediaFormat = outputFormat;
        this.codecOutputMediaFormatChanged = true;
    }

    public final boolean readSourceOmittingSampleData(int i) throws ExoPlaybackException {
        FormatHolder formatHolder = getFormatHolder();
        this.noDataBuffer.clear();
        int source = readSource(formatHolder, this.noDataBuffer, i | 4);
        if (source == -5) {
            onInputFormatChanged(formatHolder);
            return true;
        }
        if (source != -4 || !this.noDataBuffer.getFlag(4)) {
            return false;
        }
        this.inputStreamEnded = true;
        processEndOfStream();
        return false;
    }

    public final void reinitializeCodec() throws ExoPlaybackException {
        releaseCodec();
        maybeInitCodecOrBypass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void releaseCodec() {
        this.log.i("releaseCodec");
        try {
            MediaCodecAdapter mediaCodecAdapter = this.codec;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.release();
                this.decoderCounters.decoderReleaseCount++;
                onCodecReleased(this.codecInfo.name);
            }
            this.codec = null;
            try {
                MediaCrypto mediaCrypto = this.mediaCrypto;
                if (mediaCrypto != null) {
                    mediaCrypto.release();
                }
            } finally {
            }
        } catch (Throwable th) {
            this.codec = null;
            try {
                MediaCrypto mediaCrypto2 = this.mediaCrypto;
                if (mediaCrypto2 != null) {
                    mediaCrypto2.release();
                }
                throw th;
            } finally {
            }
        }
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        boolean z = false;
        if (this.pendingOutputEndOfStream) {
            this.pendingOutputEndOfStream = false;
            processEndOfStream();
        }
        ExoPlaybackException exoPlaybackException = this.pendingPlaybackException;
        if (exoPlaybackException != null) {
            this.pendingPlaybackException = null;
            throw exoPlaybackException;
        }
        try {
            if (this.outputStreamEnded) {
                renderToEndOfStream();
                return;
            }
            if (this.log.allowVerbose()) {
                this.log.v("render: positionUs = " + j + ", elapsedRealtimeUs = " + j2);
            }
            if (this.inputFormat != null || readSourceOmittingSampleData(2)) {
                maybeInitCodecOrBypass();
                if (this.bypassEnabled) {
                    TraceUtil.beginSection("bypassRender");
                    while (bypassRender(j, j2)) {
                    }
                    TraceUtil.endSection();
                } else if (this.codec != null) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    TraceUtil.beginSection("drainAndFeed");
                    while (drainOutputBuffer(j, j2) && shouldContinueRendering(jElapsedRealtime)) {
                    }
                    while (feedInputBuffer() && shouldContinueRendering(jElapsedRealtime)) {
                    }
                    TraceUtil.endSection();
                } else {
                    this.decoderCounters.skippedInputBufferCount += skipSource(j);
                    readSourceOmittingSampleData(1);
                }
                synchronized (this.decoderCounters) {
                }
            }
        } catch (IllegalStateException e) {
            if (!isMediaCodecException(e)) {
                throw e;
            }
            onCodecError(e);
            if (Util.SDK_INT >= 21 && isRecoverableMediaCodecExceptionV21(e)) {
                z = true;
            }
            if (z) {
                releaseCodec();
            }
            throw createRendererException(createDecoderException(e, this.codecInfo), this.inputFormat, z, 4003);
        }
    }

    @CallSuper
    public void resetCodecStateForFlush() {
        resetInputBuffer();
        resetOutputBuffer();
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.codecReceivedEos = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.isDecodeOnlyOutputBuffer = false;
        this.isLastOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastBufferInStreamPresentationTimeUs = -9223372036854775807L;
        this.lastProcessedOutputBufferTimeUs = -9223372036854775807L;
        C2Mp3TimestampTracker c2Mp3TimestampTracker = this.c2Mp3TimestampTracker;
        if (c2Mp3TimestampTracker != null) {
            c2Mp3TimestampTracker.reset();
        }
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.codecReconfigurationState = this.codecReconfigured ? 1 : 0;
    }

    @CallSuper
    public void resetCodecStateForRelease() {
        resetCodecStateForFlush();
        this.pendingPlaybackException = null;
        this.c2Mp3TimestampTracker = null;
        this.availableCodecInfos = null;
        this.codecInfo = null;
        this.codecInputFormat = null;
        this.codecOutputMediaFormat = null;
        this.codecOutputMediaFormatChanged = false;
        this.codecHasOutputMediaFormat = false;
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecNeedsSosFlushWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsEosOutputExceptionWorkaround = false;
        this.codecNeedsEosBufferTimestampWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsEosPropagation = false;
        this.codecReconfigured = false;
        this.codecReconfigurationState = 0;
        this.mediaCryptoRequiresSecureDecoder = false;
    }

    public final void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    public final void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    public final void setCodecDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.CC.replaceSession(this.codecDrmSession, drmSession);
        this.codecDrmSession = drmSession;
    }

    public final void setOutputStreamInfo(OutputStreamInfo outputStreamInfo) {
        this.outputStreamInfo = outputStreamInfo;
        long j = outputStreamInfo.streamOffsetUs;
        if (j != -9223372036854775807L) {
            this.needToNotifyOutputFormatChangeAfterStreamChange = true;
            onOutputStreamOffsetUsChanged(j);
        }
    }

    public final void setPendingOutputEndOfStream() {
        this.pendingOutputEndOfStream = true;
    }

    public final void setPendingPlaybackException(ExoPlaybackException exoPlaybackException) {
        this.pendingPlaybackException = exoPlaybackException;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.Renderer
    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        this.currentPlaybackSpeed = f;
        this.targetPlaybackSpeed = f2;
        updateCodecOperatingRate(this.codecInputFormat);
    }

    public void setRenderTimeLimitMs(long j) {
        this.renderTimeLimitMs = j;
    }

    public final boolean shouldContinueRendering(long j) {
        return this.renderTimeLimitMs == -9223372036854775807L || SystemClock.elapsedRealtime() - j < this.renderTimeLimitMs;
    }

    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    public boolean shouldReinitCodec() {
        return false;
    }

    public boolean shouldUseBypass(Format format) {
        return false;
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public final int supportsFormat(Format format) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, format);
        } catch (MediaCodecUtil.DecoderQueryException e) {
            throw createRendererException(e, format, false, 4002);
        }
    }

    public abstract int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException;

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public final boolean updateCodecOperatingRate() throws ExoPlaybackException {
        return updateCodecOperatingRate(this.codecInputFormat);
    }

    @RequiresApi(23)
    public final void updateDrmSessionV23() throws ExoPlaybackException {
        try {
            this.mediaCrypto.setMediaDrmSession(getFrameworkCryptoConfig(this.sourceDrmSession).sessionId);
            setCodecDrmSession(this.sourceDrmSession);
            this.codecDrainState = 0;
            this.codecDrainAction = 0;
        } catch (MediaCryptoException e) {
            throw createRendererException(e, this.inputFormat, false, 6006);
        }
    }

    public final void updateOutputFormatForTime(long j) throws ExoPlaybackException {
        Format formatPollFloor = this.outputStreamInfo.formatQueue.pollFloor(j);
        if (formatPollFloor == null && this.needToNotifyOutputFormatChangeAfterStreamChange && this.codecOutputMediaFormat != null) {
            formatPollFloor = this.outputStreamInfo.formatQueue.pollFirst();
        }
        if (formatPollFloor != null) {
            this.outputFormat = formatPollFloor;
        } else if (!this.codecOutputMediaFormatChanged || this.outputFormat == null) {
            return;
        }
        onOutputFormatChanged(this.outputFormat, this.codecOutputMediaFormat);
        this.codecOutputMediaFormatChanged = false;
        this.needToNotifyOutputFormatChangeAfterStreamChange = false;
    }

    public final boolean updateCodecOperatingRate(Format format) throws ExoPlaybackException {
        if (Util.SDK_INT >= 23 && this.codec != null && this.codecDrainAction != 3 && this.state != 0) {
            float f = this.targetPlaybackSpeed;
            Format[] formatArr = this.streamFormats;
            formatArr.getClass();
            float codecOperatingRateV23 = getCodecOperatingRateV23(f, format, formatArr);
            float f2 = this.codecOperatingRate;
            if (f2 == codecOperatingRateV23) {
                return true;
            }
            if (codecOperatingRateV23 == -1.0f) {
                drainAndReinitializeCodec();
                return false;
            }
            if (f2 == -1.0f && codecOperatingRateV23 <= this.assumedMinimumCodecOperatingRate) {
                return true;
            }
            Bundle bundle = new Bundle();
            bundle.putFloat("operating-rate", codecOperatingRateV23);
            this.codec.setParameters(bundle);
            this.codecOperatingRate = codecOperatingRateV23;
        }
        return true;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DecoderInitializationException extends Exception {
        public static final int CUSTOM_ERROR_CODE_BASE = -50000;
        public static final int DECODER_QUERY_ERROR = -49998;
        public static final int NO_SUITABLE_DECODER_ERROR = -49999;

        @Nullable
        public final MediaCodecInfo codecInfo;

        @Nullable
        public final String diagnosticInfo;

        @Nullable
        public final DecoderInitializationException fallbackDecoderInitializationException;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, @Nullable Throwable th, boolean z, int i) {
            this("Decoder init failed: [" + i + "], " + format, th, format.sampleMimeType, z, null, buildCustomDiagnosticInfo(i), null);
        }

        public static String buildCustomDiagnosticInfo(int i) {
            return "com.google.android.exoplayer2.mediacodec.MediaCodecRenderer_" + (i < 0 ? "neg_" : "") + Math.abs(i);
        }

        @Nullable
        @RequiresApi(21)
        public static String getDiagnosticInfoV21(@Nullable Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        @CheckResult
        public final DecoderInitializationException copyWithFallbackException(DecoderInitializationException decoderInitializationException) {
            return new DecoderInitializationException(getMessage(), getCause(), this.mimeType, this.secureDecoderRequired, this.codecInfo, this.diagnosticInfo, decoderInitializationException);
        }

        public DecoderInitializationException(Format format, @Nullable Throwable th, boolean z, MediaCodecInfo mediaCodecInfo) {
            this("Decoder init failed: " + mediaCodecInfo.name + ", " + format, th, format.sampleMimeType, z, mediaCodecInfo, Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null, null);
        }

        public DecoderInitializationException(String str, @Nullable Throwable th, String str2, boolean z, @Nullable MediaCodecInfo mediaCodecInfo, @Nullable String str3, @Nullable DecoderInitializationException decoderInitializationException) {
            super(str, th);
            this.mimeType = str2;
            this.secureDecoderRequired = z;
            this.codecInfo = mediaCodecInfo;
            this.diagnosticInfo = str3;
            this.fallbackDecoderInitializationException = decoderInitializationException;
        }
    }

    public void onProcessedStreamChange() {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
    }

    public void renderToEndOfStream() throws ExoPlaybackException {
    }

    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
    }

    public void onCodecError(Exception exc) {
    }

    public void onCodecReleased(String str) {
    }

    public void onOutputStreamOffsetUsChanged(long j) {
    }

    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
    }

    public void onOutputFormatChanged(Format format, @Nullable MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
    }
}
