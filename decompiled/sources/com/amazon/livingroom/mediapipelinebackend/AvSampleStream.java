package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.source.SampleStream;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.AvCodecType;
import com.amazon.livingroom.mediapipelinebackend.AvSampleStream;
import com.amazon.livingroom.mediapipelinebackend.NalUnitUtil;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import j$.util.Objects;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public class AvSampleStream implements SampleStream {
    public static final String LogTagAudio = "AvSampleStreamA";
    public static final String LogTagVideo = "AvSampleStreamV";
    public static final long READ_FAILURE_UNDERRUN_THRESHOLD_NS = TimeUnit.MILLISECONDS.toNanos(300);
    public static final String ZeroPTSAudioMetricName = "ZeroPTS.Audio";
    public static final String ZeroPTSVideoMetricName = "ZeroPTS.Video";
    public final BufferHolder bufferHolder;
    public final boolean debugLogsEnabled;
    public final DeviceProperties deviceProperties;
    public Format downstreamFormat;
    public final ExoDrmSessionManager drmSessionManager;
    public boolean finished;
    public long lastReadPositionUs;
    public long lastSeekPositionUs;
    public final String logTag;
    public final MinervaMetrics minervaMetrics;
    public final Reader reader;
    public final StreamType streamType;
    public final String zeroPTSMetricName;
    public long lastPTS = 0;
    public long firstReadFailureTimeNs = -9223372036854775807L;
    public int lastReadErrorCode = 0;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AspectRatioSetter {
        void setPendingAspectRatio(float f);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BufferHolder {
        public final AspectRatioSetter aspectRatioSetter;
        public DecoderInputBuffer buffer;
        public boolean bufferRead;
        public final ExoDrmSessionManager drmSessionManager;
        public boolean formatRead;
        public long highestPTSReadUs = 0;
        public long lastSeekPositionUs;
        public int maximumStorageHeight;
        public int maximumStorageWidth;
        public int newBufferSize;
        public Format upstreamFormat;
        public UUID upstreamVideoFormatDrmSchemeId;
        public byte[] upstreamVideoFormatDrmSessionId;

        public BufferHolder(@NonNull ExoDrmSessionManager exoDrmSessionManager, @Nullable AspectRatioSetter aspectRatioSetter) {
            this.drmSessionManager = exoDrmSessionManager;
            this.aspectRatioSetter = aspectRatioSetter;
        }

        public float getAdjustedPixelWidthHeightRatio(float f) {
            return (this.maximumStorageWidth / this.maximumStorageHeight) / f;
        }

        public float getDisplayAspectRatio(float f, float f2, float f3) {
            return (f * f3) / f2;
        }

        public int getNewBufferSize() {
            return this.newBufferSize;
        }

        public void prepare(DecoderInputBuffer decoderInputBuffer, long j) {
            this.buffer = decoderInputBuffer;
            this.lastSeekPositionUs = j;
            this.formatRead = false;
            this.bufferRead = false;
            this.newBufferSize = 0;
        }

        @CalledFromNative
        public int processParameterSetData(byte[] bArr) {
            boolean[] zArr = new boolean[3];
            int i = 0;
            while (true) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, i, bArr.length, zArr);
                if (iFindNalUnit >= bArr.length) {
                    return 0;
                }
                SpsData spsData = null;
                if ("video/hevc".equals(this.upstreamFormat.sampleMimeType) || "video/dolby-vision".equals(this.upstreamFormat.sampleMimeType)) {
                    if (NalUnitUtil.getH265NalUnitType(bArr, iFindNalUnit) == 33) {
                        NalUnitUtil.H265SpsData h265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, iFindNalUnit + 3, bArr.length);
                        spsData = new SpsData(h265SpsNalUnit.width, h265SpsNalUnit.height, h265SpsNalUnit.pixelWidthHeightRatio);
                    }
                } else {
                    if (!"video/avc".equals(this.upstreamFormat.sampleMimeType)) {
                        return ErrorCode.UNSUPPORTED_CODEC;
                    }
                    int i2 = iFindNalUnit + 3;
                    if ((bArr[i2] & 31) == 7) {
                        NalUnitUtil.SpsData spsNalUnit = NalUnitUtil.parseSpsNalUnit(bArr, i2, bArr.length);
                        spsData = new SpsData(spsNalUnit.width, spsNalUnit.height, spsNalUnit.pixelWidthHeightRatio);
                    }
                }
                if (spsData != null) {
                    int i3 = spsData.height;
                    if (i3 == 0) {
                        return ErrorCode.INVALID_PARAMETER_SET;
                    }
                    float displayAspectRatio = getDisplayAspectRatio(spsData.width, i3, spsData.pixelWidthHeightRatio);
                    float adjustedPixelWidthHeightRatio = getAdjustedPixelWidthHeightRatio(displayAspectRatio);
                    Format format = this.upstreamFormat;
                    Format formatCreateVideoFormat = FormatFactory.createVideoFormat(format.sampleMimeType, this.maximumStorageWidth, this.maximumStorageHeight, adjustedPixelWidthHeightRatio, format.frameRate, this.upstreamVideoFormatDrmSchemeId, this.upstreamVideoFormatDrmSessionId);
                    if (AvSampleStream.isDisplayAspectRatioDifferent(this.upstreamFormat, formatCreateVideoFormat)) {
                        this.formatRead = true;
                        this.upstreamFormat = formatCreateVideoFormat;
                        AspectRatioSetter aspectRatioSetter = this.aspectRatioSetter;
                        if (aspectRatioSetter != null) {
                            aspectRatioSetter.setPendingAspectRatio(displayAspectRatio);
                        }
                    }
                }
                i = iFindNalUnit + 3;
            }
        }

        @CalledFromNative
        public int requestBufferResize(int i, long j, boolean z) {
            this.newBufferSize = i;
            this.buffer.timeUs = j;
            setFlags(j, z);
            return 0;
        }

        @CalledFromNative
        public int setAudioFormat(int i, int i2, int i3) {
            this.upstreamFormat = FormatFactory.createAudioFormat(i, i2, i3, this.drmSessionManager.getDrmSchemeId(), this.drmSessionManager.getInitialSessionId());
            this.formatRead = true;
            return 0;
        }

        @CalledFromNative
        public int setEndOfStream() {
            Log.d("MPB_DRM:MPB DRM MML callback", "Buffer was read end of stream, setting true");
            this.bufferRead = true;
            this.buffer.flags = 4;
            return 0;
        }

        public final void setFlags(long j, boolean z) {
            int i = j < this.lastSeekPositionUs ? -2147483647 : 1;
            if (z) {
                i |= 1073741824;
            }
            this.buffer.flags = i;
        }

        @CalledFromNative
        public int setMetadata(int i, long j, boolean z, int i2, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2) {
            this.bufferRead = true;
            if (j > this.highestPTSReadUs) {
                this.highestPTSReadUs = j;
            }
            setFlags(j, z);
            this.buffer.data.position(i);
            DecoderInputBuffer decoderInputBuffer = this.buffer;
            decoderInputBuffer.timeUs = j;
            if (!z) {
                return 0;
            }
            decoderInputBuffer.cryptoInfo.set(i2, iArr, iArr2, bArr, bArr2, 1, 0, 0);
            return 0;
        }

        @CalledFromNative
        public int setVideoFormat(int i, int i2, int i3, double d, float f) {
            UUID drmSchemeId = this.drmSessionManager.getDrmSchemeId();
            byte[] initialSessionId = this.drmSessionManager.getInitialSessionId();
            this.upstreamVideoFormatDrmSchemeId = drmSchemeId;
            this.upstreamVideoFormatDrmSessionId = initialSessionId;
            this.maximumStorageWidth = i2;
            this.maximumStorageHeight = i3;
            if (f > 0.0f) {
                this.upstreamFormat = FormatFactory.createVideoFormat(AvCodecType.Video.findById(i).mimeType, i2, i3, getAdjustedPixelWidthHeightRatio(f), (float) d, drmSchemeId, initialSessionId);
                AspectRatioSetter aspectRatioSetter = this.aspectRatioSetter;
                if (aspectRatioSetter != null) {
                    aspectRatioSetter.setPendingAspectRatio(f);
                }
            } else {
                this.upstreamFormat = FormatFactory.createVideoFormat(i, i2, i3, (float) d, drmSchemeId, initialSessionId);
            }
            this.formatRead = true;
            return 0;
        }

        public boolean wasBufferRead() {
            return this.bufferRead;
        }

        public boolean wasFormatRead() {
            return this.formatRead;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Reader {
        int read(BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SpsData {
        public final int height;
        public final float pixelWidthHeightRatio;
        public final int width;

        public SpsData(int i, int i2, float f) {
            this.width = i;
            this.height = i2;
            this.pixelWidthHeightRatio = f;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum StreamType {
        Audio,
        Video
    }

    public AvSampleStream(@NonNull MinervaMetrics minervaMetrics, @NonNull DeviceProperties deviceProperties, @NonNull Reader reader, StreamType streamType, @NonNull ExoDrmSessionManager exoDrmSessionManager, @Nullable AspectRatioSetter aspectRatioSetter) {
        this.minervaMetrics = minervaMetrics;
        this.deviceProperties = deviceProperties;
        this.bufferHolder = new BufferHolder(exoDrmSessionManager, aspectRatioSetter);
        this.reader = reader;
        this.drmSessionManager = exoDrmSessionManager;
        this.streamType = streamType;
        if (streamType == StreamType.Audio) {
            this.logTag = LogTagAudio;
            this.zeroPTSMetricName = ZeroPTSAudioMetricName;
        } else {
            this.logTag = LogTagVideo;
            this.zeroPTSMetricName = ZeroPTSVideoMetricName;
        }
        this.debugLogsEnabled = Log.isLoggable(this.logTag, 3);
    }

    public static AvSampleStream createForAudio(@NonNull final NativeMediaPipelineBackend nativeMediaPipelineBackend, @NonNull MinervaMetrics minervaMetrics, @NonNull DeviceProperties deviceProperties, @NonNull ExoDrmSessionManager exoDrmSessionManager) {
        Objects.requireNonNull(nativeMediaPipelineBackend);
        return new AvSampleStream(minervaMetrics, deviceProperties, new Reader() { // from class: com.amazon.livingroom.mediapipelinebackend.AvSampleStream$$ExternalSyntheticLambda1
            @Override // com.amazon.livingroom.mediapipelinebackend.AvSampleStream.Reader
            public final int read(AvSampleStream.BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2) {
                return nativeMediaPipelineBackend.readAudioAccessUnit(bufferHolder, byteBuffer, z, z2);
            }
        }, StreamType.Audio, exoDrmSessionManager, null);
    }

    public static AvSampleStream createForVideo(@NonNull final NativeMediaPipelineBackend nativeMediaPipelineBackend, @NonNull MinervaMetrics minervaMetrics, @NonNull DeviceProperties deviceProperties, @NonNull ExoDrmSessionManager exoDrmSessionManager, @NonNull AspectRatioSetter aspectRatioSetter) {
        Objects.requireNonNull(nativeMediaPipelineBackend);
        return new AvSampleStream(minervaMetrics, deviceProperties, new Reader() { // from class: com.amazon.livingroom.mediapipelinebackend.AvSampleStream$$ExternalSyntheticLambda0
            @Override // com.amazon.livingroom.mediapipelinebackend.AvSampleStream.Reader
            public final int read(AvSampleStream.BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2) {
                return nativeMediaPipelineBackend.readVideoAccessUnit(bufferHolder, byteBuffer, z, z2);
            }
        }, StreamType.Video, exoDrmSessionManager, aspectRatioSetter);
    }

    public static boolean isDisplayAspectRatioDifferent(Format format, Format format2) {
        return ((double) Math.abs(((((float) format.width) * format.pixelWidthHeightRatio) / ((float) format.height)) - ((((float) format2.width) * format2.pixelWidthHeightRatio) / ((float) format2.height)))) > 1.0E-4d;
    }

    public final String describeError(int i) {
        if (i == 66001) {
            return "Read from native succeeded but didn't set upstream format";
        }
        if (i == 68004) {
            return "Used a codec that isn't supported";
        }
        switch (i) {
            case ErrorCode.GET_DIRECT_CODEC_BUFFER_FAILED /* 64001 */:
                return "Failed to get address of native MediaCodec buffer";
            case ErrorCode.CODEC_BUFFER_TOO_SMALL /* 64002 */:
                return "MediaCodec buffer is too small to hold access unit";
            case ErrorCode.TOO_MANY_ENCRYPTED_REGIONS /* 64003 */:
                return "Got too many encrypted regions";
            default:
                return ObjectListKt$$ExternalSyntheticOutline1.m("Unknown error ", i, " reading access unit from native");
        }
    }

    public String describeState() {
        String str = "highestPTSReadUs=" + this.bufferHolder.highestPTSReadUs;
        if (this.lastReadErrorCode == 0) {
            return str;
        }
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, ", lastReadErrorCode=");
        sbM.append(this.lastReadErrorCode);
        sbM.append(", lastReadErrorMessage=");
        sbM.append(describeError(this.lastReadErrorCode));
        return sbM.toString();
    }

    public long getBufferedPositionUs() {
        return this.lastReadPositionUs;
    }

    public final int handleError() {
        if (this.firstReadFailureTimeNs != -9223372036854775807L) {
            return -3;
        }
        if (this.debugLogsEnabled) {
            Log.d(this.logTag, "First read failure. Starting countdown before buffering event.");
        }
        this.firstReadFailureTimeNs = System.nanoTime();
        return -3;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public boolean isReady() {
        if (this.finished) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "isReady() = true due to finished");
            }
            return true;
        }
        if (this.bufferHolder.upstreamFormat == null) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "isReady() = false due to no upstream format set");
            }
            return false;
        }
        if (this.firstReadFailureTimeNs == -9223372036854775807L) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "isReady() = true due no recent read failure");
            }
            return true;
        }
        if (this.lastReadPositionUs <= this.lastSeekPositionUs) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "isReady() = true due to no data past the seek position having been read yet");
            }
            return true;
        }
        if (System.nanoTime() - this.firstReadFailureTimeNs < READ_FAILURE_UNDERRUN_THRESHOLD_NS) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "isReady() = true due to read failure within the rebuffering threshold");
            }
            return true;
        }
        if (this.debugLogsEnabled) {
            Log.d(this.logTag, "isReady() = false due to failing to read for too long");
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public void maybeThrowError() throws IOException {
        if (this.lastReadErrorCode == 0) {
            return;
        }
        throw new IOException("Read no sample on " + this.streamType + " stream. errorCode=" + this.lastReadErrorCode + " - " + describeError(this.lastReadErrorCode));
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
        boolean z = (i & 1) != 0;
        boolean z2 = (i & 2) != 0;
        boolean z3 = (i & 4) != 0;
        this.lastReadErrorCode = 0;
        if (this.finished) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "Read end of stream again");
            }
            decoderInputBuffer.flags = 4;
            return -4;
        }
        Format format = this.downstreamFormat;
        BufferHolder bufferHolder = this.bufferHolder;
        Format format2 = bufferHolder.upstreamFormat;
        if (format != format2) {
            readFormat(formatHolder, "Read pending format");
            return -5;
        }
        if (z2 && format2 != null) {
            readFormat(formatHolder, "Read required format again");
            return -5;
        }
        bufferHolder.prepare(decoderInputBuffer, this.lastSeekPositionUs);
        int i2 = this.reader.read(this.bufferHolder, decoderInputBuffer.data, z, z3);
        this.lastReadErrorCode = i2;
        if (i2 == 64002) {
            Log.d(this.logTag, "Attempting to resize buffer to: " + this.bufferHolder.getNewBufferSize());
            decoderInputBuffer.ensureSpaceForWrite(this.bufferHolder.getNewBufferSize());
            this.bufferHolder.prepare(decoderInputBuffer, this.lastSeekPositionUs);
            this.lastReadErrorCode = this.reader.read(this.bufferHolder, decoderInputBuffer.data, z, z3);
        }
        if (this.lastReadErrorCode != 0 || (!this.bufferHolder.wasBufferRead() && !this.bufferHolder.wasFormatRead())) {
            if (this.debugLogsEnabled) {
                if (this.lastReadErrorCode == 0) {
                    Log.d(this.logTag, "Nothing read (no error)");
                } else {
                    Log.d(this.logTag, "Read errorCode=" + this.lastReadErrorCode + " - " + describeError(this.lastReadErrorCode));
                }
            }
            handleError();
            return -3;
        }
        BufferHolder bufferHolder2 = this.bufferHolder;
        if (bufferHolder2.upstreamFormat == null) {
            Log.e(this.logTag, "Read from native succeeded but didn't set upstream format");
            this.lastReadErrorCode = ErrorCode.SAMPLE_STREAM_MISSING_FORMAT;
            return -3;
        }
        if (bufferHolder2.wasFormatRead()) {
            if (!this.bufferHolder.upstreamFormat.equals(this.downstreamFormat)) {
                readFormat(formatHolder, "Read new format");
                return -5;
            }
            Log.w(this.logTag, "Read a format, but it was the same as downstream: " + this.bufferHolder.upstreamFormat);
            return -3;
        }
        long j = decoderInputBuffer.timeUs;
        if (j != 0 || this.lastPTS == 0) {
            this.lastPTS = j;
        } else {
            MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.ZERO_PTS_SCHEMA_ID);
            metricEventCreateMetricEvent.addLong(this.zeroPTSMetricName, 1L);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
            if ((this.streamType == StreamType.Audio && ((Boolean) this.deviceProperties.get(DeviceProperties.REPLACE_AUDIO_ZERO_PTS)).booleanValue()) || (this.streamType == StreamType.Video && ((Boolean) this.deviceProperties.get(DeviceProperties.REPLACE_VIDEO_ZERO_PTS)).booleanValue())) {
                Log.w(this.logTag, "Sample with zero PTS received, replacing with last received PTS of " + this.lastPTS);
                decoderInputBuffer.timeUs = this.lastPTS;
            } else {
                Log.w(this.logTag, "Sample with zero PTS received, not being handled");
            }
        }
        this.firstReadFailureTimeNs = -9223372036854775807L;
        this.lastReadPositionUs = decoderInputBuffer.timeUs;
        if (decoderInputBuffer.getFlag(4)) {
            if (this.debugLogsEnabled) {
                Log.d(this.logTag, "Read EOS from native");
            }
            this.finished = true;
            return -4;
        }
        if (this.debugLogsEnabled) {
            String string = "Read sample: size=" + decoderInputBuffer.data.position() + " timeUs=" + decoderInputBuffer.timeUs + " isEncrypted=" + decoderInputBuffer.getFlag(1073741824) + " isDecodeOnly=" + decoderInputBuffer.getFlag(Integer.MIN_VALUE) + " isEndOfStream=" + decoderInputBuffer.getFlag(4) + " isKeyFrame=" + decoderInputBuffer.getFlag(1);
            if (decoderInputBuffer.getFlag(1073741824)) {
                StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(string, " keyId=");
                sbM.append(DrmUtils.toString(decoderInputBuffer.cryptoInfo.key));
                sbM.append(" numBytesOfClearData=");
                sbM.append(Arrays.toString(decoderInputBuffer.cryptoInfo.numBytesOfClearData));
                sbM.append(" numBytesOfEncryptedData=");
                sbM.append(Arrays.toString(decoderInputBuffer.cryptoInfo.numBytesOfEncryptedData));
                string = sbM.toString();
            }
            Log.d(this.logTag, string);
        }
        return -4;
    }

    public final int readFormat(FormatHolder formatHolder, String str) {
        if (this.debugLogsEnabled) {
            Log.d(this.logTag, str + ": " + str);
        }
        Format format = this.bufferHolder.upstreamFormat;
        formatHolder.format = format;
        formatHolder.drmSession = this.drmSessionManager.acquireSession(format);
        this.downstreamFormat = this.bufferHolder.upstreamFormat;
        return -5;
    }

    public void seekTo(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(this.logTag + ": Negative seek position: " + j);
        }
        this.lastSeekPositionUs = j;
        this.lastReadPositionUs = 0L;
        this.finished = false;
        this.firstReadFailureTimeNs = -9223372036854775807L;
        this.lastReadErrorCode = 0;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int skipData(long j) {
        return 0;
    }
}
