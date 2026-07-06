package com.google.android.exoplayer2.source.mediaparser;

import android.annotation.SuppressLint;
import android.media.DrmInitData;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaParser;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.exoplayer.source.MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.source.mediaparser.OutputConsumerAdapterV30$DataReaderAdapter$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.DummyExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MediaFormatUtil;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(30)
@SuppressLint({"Override"})
public final class OutputConsumerAdapterV30 implements MediaParser.OutputConsumer {
    public static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_DURATIONS = "chunk-index-long-us-durations";
    public static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_OFFSETS = "chunk-index-long-offsets";
    public static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_SIZES = "chunk-index-int-sizes";
    public static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_TIMES = "chunk-index-long-us-times";
    public static final String MEDIA_FORMAT_KEY_TRACK_TYPE = "track-type-string";
    public static final Pattern REGEX_CRYPTO_INFO_PATTERN;
    public static final Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> SEEK_POINT_PAIR_START;
    public static final String TAG = "OConsumerAdapterV30";

    @Nullable
    public String containerMimeType;

    @Nullable
    public MediaParser.SeekMap dummySeekMap;
    public final boolean expectDummySeekMap;
    public ExtractorOutput extractorOutput;

    @Nullable
    public ChunkIndex lastChunkIndex;
    public final ArrayList<TrackOutput.CryptoData> lastOutputCryptoDatas;
    public final ArrayList<MediaCodec.CryptoInfo> lastReceivedCryptoInfos;

    @Nullable
    public MediaParser.SeekMap lastSeekMap;
    public List<Format> muxedCaptionFormats;
    public int primaryTrackIndex;

    @Nullable
    public final Format primaryTrackManifestFormat;
    public final int primaryTrackType;
    public long sampleTimestampUpperLimitFilterUs;
    public final DataReaderAdapter scratchDataReaderAdapter;
    public boolean seekingDisabled;

    @Nullable
    public TimestampAdjuster timestampAdjuster;
    public final ArrayList<Format> trackFormats;
    public final ArrayList<TrackOutput> trackOutputs;
    public boolean tracksEnded;
    public boolean tracksFoundCalled;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DataReaderAdapter implements DataReader {

        @Nullable
        public MediaParser.InputReader input;

        public DataReaderAdapter() {
        }

        @Override // com.google.android.exoplayer2.upstream.DataReader
        public int read(byte[] bArr, int i, int i2) throws IOException {
            MediaParser.InputReader inputReader = this.input;
            Util.castNonNull(inputReader);
            return OutputConsumerAdapterV30$DataReaderAdapter$$ExternalSyntheticApiModelOutline0.m(inputReader).read(bArr, i, i2);
        }

        public DataReaderAdapter(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SeekMapAdapter implements SeekMap {
        public final MediaParser.SeekMap adaptedSeekMap;

        public SeekMapAdapter(MediaParser.SeekMap seekMap) {
            this.adaptedSeekMap = seekMap;
        }

        public static SeekPoint asExoPlayerSeekPoint(MediaParser.SeekPoint seekPoint) {
            return new SeekPoint(seekPoint.timeMicros, seekPoint.position);
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public long getDurationUs() {
            long durationMicros = this.adaptedSeekMap.getDurationMicros();
            if (durationMicros != -2147483648L) {
                return durationMicros;
            }
            return -9223372036854775807L;
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> seekPoints = this.adaptedSeekMap.getSeekPoints(j);
            Object obj = seekPoints.first;
            if (obj != seekPoints.second) {
                return new SeekMap.SeekPoints(asExoPlayerSeekPoint(MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(obj)), asExoPlayerSeekPoint(MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(seekPoints.second)));
            }
            SeekPoint seekPointAsExoPlayerSeekPoint = asExoPlayerSeekPoint(MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(obj));
            return new SeekMap.SeekPoints(seekPointAsExoPlayerSeekPoint, seekPointAsExoPlayerSeekPoint);
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public boolean isSeekable() {
            return this.adaptedSeekMap.isSeekable();
        }
    }

    static {
        MediaParser.SeekPoint seekPoint = MediaParser.SeekPoint.START;
        SEEK_POINT_PAIR_START = Pair.create(seekPoint, seekPoint);
        REGEX_CRYPTO_INFO_PATTERN = Pattern.compile("pattern \\(encrypt: (\\d+), skip: (\\d+)\\)");
    }

    public OutputConsumerAdapterV30() {
        this(null, -2, false);
    }

    public static int getFlag(MediaFormat mediaFormat, String str, int i) {
        if (mediaFormat.getInteger(str, 0) != 0) {
            return i;
        }
        return 0;
    }

    public static List<byte[]> getInitializationData(MediaFormat mediaFormat) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            StringBuilder sb = new StringBuilder("csd-");
            int i2 = i + 1;
            sb.append(i);
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer(sb.toString());
            if (byteBuffer == null) {
                return arrayList;
            }
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            arrayList.add(bArr);
            i = i2;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String getMimeType(String str) {
        str.getClass();
        byte b = -1;
        switch (str.hashCode()) {
            case -2063506020:
                if (str.equals("android.media.mediaparser.Mp4Parser")) {
                    b = 0;
                }
                break;
            case -1870824006:
                if (str.equals("android.media.mediaparser.OggParser")) {
                    b = 1;
                }
                break;
            case -1566427438:
                if (str.equals("android.media.mediaparser.TsParser")) {
                    b = 2;
                }
                break;
            case -900207883:
                if (str.equals("android.media.mediaparser.AdtsParser")) {
                    b = 3;
                }
                break;
            case -589864617:
                if (str.equals("android.media.mediaparser.WavParser")) {
                    b = 4;
                }
                break;
            case 52265814:
                if (str.equals("android.media.mediaparser.PsParser")) {
                    b = 5;
                }
                break;
            case 116768877:
                if (str.equals("android.media.mediaparser.FragmentedMp4Parser")) {
                    b = 6;
                }
                break;
            case 376876796:
                if (str.equals("android.media.mediaparser.Ac3Parser")) {
                    b = 7;
                }
                break;
            case 703268017:
                if (str.equals("android.media.mediaparser.AmrParser")) {
                    b = 8;
                }
                break;
            case 768643067:
                if (str.equals("android.media.mediaparser.FlacParser")) {
                    b = 9;
                }
                break;
            case 965962719:
                if (str.equals("android.media.mediaparser.MatroskaParser")) {
                    b = 10;
                }
                break;
            case 1264380477:
                if (str.equals("android.media.mediaparser.Ac4Parser")) {
                    b = Ascii.VT;
                }
                break;
            case 1343957595:
                if (str.equals("android.media.mediaparser.Mp3Parser")) {
                    b = Ascii.FF;
                }
                break;
            case 2063134683:
                if (str.equals("android.media.mediaparser.FlvParser")) {
                    b = 13;
                }
                break;
        }
        switch (b) {
            case 0:
            case 6:
                return "video/mp4";
            case 1:
                return "audio/ogg";
            case 2:
                return "video/mp2t";
            case 3:
                return "audio/mp4a-latm";
            case 4:
                return "audio/raw";
            case 5:
                return "video/mp2p";
            case 7:
                return "audio/ac3";
            case 8:
                return "audio/amr";
            case 9:
                return "audio/flac";
            case 10:
                return "video/webm";
            case 11:
                return "audio/ac4";
            case 12:
                return "audio/mpeg";
            case 13:
                return "video/x-flv";
            default:
                throw new IllegalArgumentException("Illegal parser name: ".concat(str));
        }
    }

    public static int getSelectionFlags(MediaFormat mediaFormat) {
        return getFlag(mediaFormat, "is-autoselect", 4) | (mediaFormat.getInteger("is-default", 0) != 0 ? 1 : 0) | (mediaFormat.getInteger("is-forced-subtitle", 0) != 0 ? 2 : 0);
    }

    @Nullable
    public static DrmInitData toExoPlayerDrmInitData(@Nullable String str, @Nullable android.media.DrmInitData drmInitData) {
        if (drmInitData == null) {
            return null;
        }
        int schemeInitDataCount = drmInitData.getSchemeInitDataCount();
        DrmInitData.SchemeData[] schemeDataArr = new DrmInitData.SchemeData[schemeInitDataCount];
        for (int i = 0; i < schemeInitDataCount; i++) {
            DrmInitData.SchemeInitData schemeInitDataAt = drmInitData.getSchemeInitDataAt(i);
            schemeDataArr[i] = new DrmInitData.SchemeData(schemeInitDataAt.uuid, null, schemeInitDataAt.mimeType, schemeInitDataAt.data);
        }
        return new com.google.android.exoplayer2.drm.DrmInitData(str, true, schemeDataArr);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int toTrackTypeConstant(@androidx.annotation.Nullable java.lang.String r5) {
        /*
            r0 = -1
            if (r5 != 0) goto L4
            return r0
        L4:
            int r1 = r5.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case -450004177: goto L3c;
                case -284840886: goto L31;
                case 3556653: goto L26;
                case 93166550: goto L1b;
                case 112202875: goto L10;
                default: goto Le;
            }
        Le:
            r1 = -1
            goto L46
        L10:
            java.lang.String r1 = "video"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L19
            goto Le
        L19:
            r1 = 4
            goto L46
        L1b:
            java.lang.String r1 = "audio"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L24
            goto Le
        L24:
            r1 = 3
            goto L46
        L26:
            java.lang.String r1 = "text"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L2f
            goto Le
        L2f:
            r1 = 2
            goto L46
        L31:
            java.lang.String r1 = "unknown"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L3a
            goto Le
        L3a:
            r1 = 1
            goto L46
        L3c:
            java.lang.String r1 = "metadata"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L45
            goto Le
        L45:
            r1 = 0
        L46:
            switch(r1) {
                case 0: goto L52;
                case 1: goto L51;
                case 2: goto L50;
                case 3: goto L4f;
                case 4: goto L4e;
                default: goto L49;
            }
        L49:
            int r5 = com.google.android.exoplayer2.util.MimeTypes.getTrackType(r5)
            return r5
        L4e:
            return r3
        L4f:
            return r4
        L50:
            return r2
        L51:
            return r0
        L52:
            r5 = 5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.mediaparser.OutputConsumerAdapterV30.toTrackTypeConstant(java.lang.String):int");
    }

    public void disableSeeking() {
        this.seekingDisabled = true;
    }

    public final void ensureSpaceForTrackIndex(int i) {
        for (int size = this.trackOutputs.size(); size <= i; size++) {
            this.trackOutputs.add(null);
            this.trackFormats.add(null);
            this.lastReceivedCryptoInfos.add(null);
            this.lastOutputCryptoDatas.add(null);
        }
    }

    @Nullable
    public ChunkIndex getChunkIndex() {
        return this.lastChunkIndex;
    }

    @Nullable
    public MediaParser.SeekMap getDummySeekMap() {
        return this.dummySeekMap;
    }

    @Nullable
    public Format[] getSampleFormats() {
        if (!this.tracksFoundCalled) {
            return null;
        }
        Format[] formatArr = new Format[this.trackFormats.size()];
        for (int i = 0; i < this.trackFormats.size(); i++) {
            Format format = this.trackFormats.get(i);
            format.getClass();
            formatArr[i] = format;
        }
        return formatArr;
    }

    public Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> getSeekPoints(long j) {
        MediaParser.SeekMap seekMap = this.lastSeekMap;
        return seekMap != null ? seekMap.getSeekPoints(j) : SEEK_POINT_PAIR_START;
    }

    public final void maybeEndTracks() {
        if (!this.tracksFoundCalled || this.tracksEnded) {
            return;
        }
        int size = this.trackOutputs.size();
        for (int i = 0; i < size; i++) {
            if (this.trackOutputs.get(i) == null) {
                return;
            }
        }
        this.extractorOutput.endTracks();
        this.tracksEnded = true;
    }

    public final boolean maybeObtainChunkIndex(MediaFormat mediaFormat) {
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("chunk-index-int-sizes");
        if (byteBuffer == null) {
            return false;
        }
        IntBuffer intBufferAsIntBuffer = byteBuffer.asIntBuffer();
        ByteBuffer byteBuffer2 = mediaFormat.getByteBuffer("chunk-index-long-offsets");
        byteBuffer2.getClass();
        LongBuffer longBufferAsLongBuffer = byteBuffer2.asLongBuffer();
        ByteBuffer byteBuffer3 = mediaFormat.getByteBuffer("chunk-index-long-us-durations");
        byteBuffer3.getClass();
        LongBuffer longBufferAsLongBuffer2 = byteBuffer3.asLongBuffer();
        ByteBuffer byteBuffer4 = mediaFormat.getByteBuffer("chunk-index-long-us-times");
        byteBuffer4.getClass();
        LongBuffer longBufferAsLongBuffer3 = byteBuffer4.asLongBuffer();
        int[] iArr = new int[intBufferAsIntBuffer.remaining()];
        long[] jArr = new long[longBufferAsLongBuffer.remaining()];
        long[] jArr2 = new long[longBufferAsLongBuffer2.remaining()];
        long[] jArr3 = new long[longBufferAsLongBuffer3.remaining()];
        intBufferAsIntBuffer.get(iArr);
        longBufferAsLongBuffer.get(jArr);
        longBufferAsLongBuffer2.get(jArr2);
        longBufferAsLongBuffer3.get(jArr3);
        ChunkIndex chunkIndex = new ChunkIndex(iArr, jArr, jArr2, jArr3);
        this.lastChunkIndex = chunkIndex;
        this.extractorOutput.seekMap(chunkIndex);
        return true;
    }

    @Override // android.media.MediaParser.OutputConsumer
    public void onSampleCompleted(int i, long j, int i2, int i3, int i4, @Nullable MediaCodec.CryptoInfo cryptoInfo) {
        long j2 = this.sampleTimestampUpperLimitFilterUs;
        if (j2 == -9223372036854775807L || j < j2) {
            TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
            if (timestampAdjuster != null) {
                j = timestampAdjuster.adjustSampleTimestamp(j);
            }
            TrackOutput trackOutput = this.trackOutputs.get(i);
            trackOutput.getClass();
            trackOutput.sampleMetadata(j, i2, i3, i4, toExoPlayerCryptoData(i, cryptoInfo));
        }
    }

    @Override // android.media.MediaParser.OutputConsumer
    public void onSampleDataFound(int i, MediaParser.InputReader inputReader) throws IOException {
        ensureSpaceForTrackIndex(i);
        this.scratchDataReaderAdapter.input = inputReader;
        TrackOutput trackOutputTrack = this.trackOutputs.get(i);
        if (trackOutputTrack == null) {
            trackOutputTrack = this.extractorOutput.track(i, -1);
            this.trackOutputs.set(i, trackOutputTrack);
        }
        trackOutputTrack.sampleData((DataReader) this.scratchDataReaderAdapter, (int) inputReader.getLength(), true);
    }

    @Override // android.media.MediaParser.OutputConsumer
    public void onSeekMapFound(MediaParser.SeekMap seekMap) {
        SeekMap seekMapAdapter;
        if (this.expectDummySeekMap && this.dummySeekMap == null) {
            this.dummySeekMap = seekMap;
            return;
        }
        this.lastSeekMap = seekMap;
        long durationMicros = seekMap.getDurationMicros();
        ExtractorOutput extractorOutput = this.extractorOutput;
        if (this.seekingDisabled) {
            if (durationMicros == -2147483648L) {
                durationMicros = -9223372036854775807L;
            }
            seekMapAdapter = new SeekMap.Unseekable(durationMicros);
        } else {
            seekMapAdapter = new SeekMapAdapter(seekMap);
        }
        extractorOutput.seekMap(seekMapAdapter);
    }

    @Override // android.media.MediaParser.OutputConsumer
    public void onTrackCountFound(int i) {
        this.tracksFoundCalled = true;
        maybeEndTracks();
    }

    @Override // android.media.MediaParser.OutputConsumer
    public void onTrackDataFound(int i, MediaParser.TrackData trackData) {
        if (maybeObtainChunkIndex(trackData.mediaFormat)) {
            return;
        }
        ensureSpaceForTrackIndex(i);
        TrackOutput trackOutput = this.trackOutputs.get(i);
        if (trackOutput == null) {
            String string = trackData.mediaFormat.getString("track-type-string");
            int trackTypeConstant = toTrackTypeConstant(string != null ? string : trackData.mediaFormat.getString("mime"));
            if (trackTypeConstant == this.primaryTrackType) {
                this.primaryTrackIndex = i;
            }
            TrackOutput trackOutputTrack = this.extractorOutput.track(i, trackTypeConstant);
            this.trackOutputs.set(i, trackOutputTrack);
            if (string != null) {
                return;
            } else {
                trackOutput = trackOutputTrack;
            }
        }
        Format exoPlayerFormat = toExoPlayerFormat(trackData);
        Format format = this.primaryTrackManifestFormat;
        trackOutput.format((format == null || i != this.primaryTrackIndex) ? exoPlayerFormat : exoPlayerFormat.withManifestFormatInfo(format));
        this.trackFormats.set(i, exoPlayerFormat);
        maybeEndTracks();
    }

    public void setExtractorOutput(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    public void setMuxedCaptionFormats(List<Format> list) {
        this.muxedCaptionFormats = list;
    }

    public void setSampleTimestampUpperLimitFilterUs(long j) {
        this.sampleTimestampUpperLimitFilterUs = j;
    }

    public void setSelectedParserName(String str) {
        this.containerMimeType = getMimeType(str);
    }

    public void setTimestampAdjuster(TimestampAdjuster timestampAdjuster) {
        this.timestampAdjuster = timestampAdjuster;
    }

    @Nullable
    public final TrackOutput.CryptoData toExoPlayerCryptoData(int i, @Nullable MediaCodec.CryptoInfo cryptoInfo) {
        int i2;
        int i3;
        if (cryptoInfo == null) {
            return null;
        }
        if (this.lastReceivedCryptoInfos.get(i) == cryptoInfo) {
            TrackOutput.CryptoData cryptoData = this.lastOutputCryptoDatas.get(i);
            cryptoData.getClass();
            return cryptoData;
        }
        try {
            Matcher matcher = REGEX_CRYPTO_INFO_PATTERN.matcher(cryptoInfo.toString());
            matcher.find();
            String strGroup = matcher.group(1);
            Util.castNonNull(strGroup);
            i2 = Integer.parseInt(strGroup);
            i3 = Integer.parseInt(matcher.group(2));
        } catch (RuntimeException e) {
            Log.e("OConsumerAdapterV30", "Unexpected error while parsing CryptoInfo: " + cryptoInfo, e);
            i2 = 0;
            i3 = 0;
        }
        TrackOutput.CryptoData cryptoData2 = new TrackOutput.CryptoData(cryptoInfo.mode, cryptoInfo.key, i2, i3);
        this.lastReceivedCryptoInfos.set(i, cryptoInfo);
        this.lastOutputCryptoDatas.set(i, cryptoData2);
        return cryptoData2;
    }

    public final Format toExoPlayerFormat(MediaParser.TrackData trackData) {
        MediaFormat mediaFormat = trackData.mediaFormat;
        String string = mediaFormat.getString("mime");
        int integer = mediaFormat.getInteger("caption-service-number", -1);
        Format.Builder builder = new Format.Builder();
        builder.drmInitData = toExoPlayerDrmInitData(mediaFormat.getString("crypto-mode-fourcc"), trackData.drmInitData);
        builder.containerMimeType = this.containerMimeType;
        builder.peakBitrate = mediaFormat.getInteger("bitrate", -1);
        builder.channelCount = mediaFormat.getInteger("channel-count", -1);
        builder.colorInfo = MediaFormatUtil.getColorInfo(mediaFormat);
        builder.sampleMimeType = string;
        builder.codecs = mediaFormat.getString("codecs-string");
        builder.frameRate = mediaFormat.getFloat("frame-rate", -1.0f);
        builder.width = mediaFormat.getInteger("width", -1);
        builder.height = mediaFormat.getInteger("height", -1);
        builder.initializationData = getInitializationData(mediaFormat);
        builder.language = mediaFormat.getString("language");
        builder.maxInputSize = mediaFormat.getInteger("max-input-size", -1);
        builder.pcmEncoding = mediaFormat.getInteger("exo-pcm-encoding", -1);
        int i = 0;
        builder.rotationDegrees = mediaFormat.getInteger("rotation-degrees", 0);
        builder.sampleRate = mediaFormat.getInteger("sample-rate", -1);
        builder.selectionFlags = getSelectionFlags(mediaFormat);
        builder.encoderDelay = mediaFormat.getInteger("encoder-delay", 0);
        builder.encoderPadding = mediaFormat.getInteger("encoder-padding", 0);
        builder.pixelWidthHeightRatio = mediaFormat.getFloat("pixel-width-height-ratio-float", 1.0f);
        builder.subsampleOffsetUs = mediaFormat.getLong("subsample-offset-us-long", Long.MAX_VALUE);
        builder.accessibilityChannel = integer;
        while (true) {
            if (i >= this.muxedCaptionFormats.size()) {
                break;
            }
            Format format = this.muxedCaptionFormats.get(i);
            if (Util.areEqual(format.sampleMimeType, string) && format.accessibilityChannel == integer) {
                builder.language = format.language;
                builder.roleFlags = format.roleFlags;
                builder.selectionFlags = format.selectionFlags;
                builder.label = format.label;
                builder.metadata = format.metadata;
                break;
            }
            i++;
        }
        return new Format(builder);
    }

    public OutputConsumerAdapterV30(@Nullable Format format, int i, boolean z) {
        this.expectDummySeekMap = z;
        this.primaryTrackManifestFormat = format;
        this.primaryTrackType = i;
        this.trackOutputs = new ArrayList<>();
        this.trackFormats = new ArrayList<>();
        this.lastReceivedCryptoInfos = new ArrayList<>();
        this.lastOutputCryptoDatas = new ArrayList<>();
        this.scratchDataReaderAdapter = new DataReaderAdapter();
        this.extractorOutput = new DummyExtractorOutput();
        this.sampleTimestampUpperLimitFilterUs = -9223372036854775807L;
        this.muxedCaptionFormats = ImmutableList.of();
    }
}
