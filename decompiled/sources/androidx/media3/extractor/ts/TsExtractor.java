package androidx.media3.extractor.ts;

import android.net.Uri;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.annotation.Nullable;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class TsExtractor implements Extractor {
    public static final long AC3_FORMAT_IDENTIFIER = 1094921523;
    public static final long AC4_FORMAT_IDENTIFIER = 1094921524;
    public static final int BUFFER_SIZE = 9400;
    public static final int DEFAULT_TIMESTAMP_SEARCH_BYTES = 112800;
    public static final long E_AC3_FORMAT_IDENTIFIER = 1161904947;

    @Deprecated
    public static final ExtractorsFactory FACTORY = new TsExtractor$$ExternalSyntheticLambda1();
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 1;
    public static final long HEVC_FORMAT_IDENTIFIER = 1212503619;
    public static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    public static final int SNIFF_TS_PACKET_COUNT = 5;
    public static final int TS_PACKET_SIZE = 188;
    public static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC_ADTS = 15;
    public static final int TS_STREAM_TYPE_AAC_LATM = 17;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_AC4 = 172;
    public static final int TS_STREAM_TYPE_AIT = 257;
    public static final int TS_STREAM_TYPE_DC2_H262 = 128;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DTS_HD = 136;
    public static final int TS_STREAM_TYPE_DTS_UHD = 139;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H263 = 16;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    public static final int TS_SYNC_BYTE = 71;
    public int bytesSinceLastSync;
    public final SparseIntArray continuityCounters;
    public final TsDurationReader durationReader;
    public final int extractorFlags;
    public boolean hasOutputSeekMap;

    @Nullable
    public TsPayloadReader id3Reader;
    public final int mode;
    public ExtractorOutput output;
    public final TsPayloadReader.Factory payloadReaderFactory;
    public int pcrPid;
    public boolean pendingSeekToStart;
    public int remainingPmts;
    public final SubtitleParser.Factory subtitleParserFactory;
    public final List<TimestampAdjuster> timestampAdjusters;
    public final int timestampSearchBytes;
    public final SparseBooleanArray trackIds;
    public final SparseBooleanArray trackPids;
    public boolean tracksEnded;
    public TsBinarySearchSeeker tsBinarySearchSeeker;
    public final ParsableByteArray tsPacketBuffer;
    public final SparseArray<TsPayloadReader> tsPayloadReaders;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public static /* synthetic */ Extractor[] $r8$lambda$7A6xD9j_oCVPmIlJoNJiJvwSydQ() {
        return new Extractor[]{new TsExtractor(1, SubtitleParser.Factory.UNSUPPORTED)};
    }

    public static /* synthetic */ Extractor[] $r8$lambda$BZAkJlQ0uiqLjxveSejhTcTV8kY(SubtitleParser.Factory factory) {
        return new Extractor[]{new TsExtractor(factory)};
    }

    @Deprecated
    public TsExtractor() {
        this(1, 1, SubtitleParser.Factory.UNSUPPORTED, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(0), 112800);
    }

    public static /* synthetic */ int access$108(TsExtractor tsExtractor) {
        int i = tsExtractor.remainingPmts;
        tsExtractor.remainingPmts = i + 1;
        return i;
    }

    private void maybeOutputSeekMap(long j) {
        if (this.hasOutputSeekMap) {
            return;
        }
        this.hasOutputSeekMap = true;
        TsDurationReader tsDurationReader = this.durationReader;
        long j2 = tsDurationReader.durationUs;
        if (j2 == -9223372036854775807L) {
            this.output.seekMap(new SeekMap.Unseekable(j2));
            return;
        }
        TsBinarySearchSeeker tsBinarySearchSeeker = new TsBinarySearchSeeker(tsDurationReader.pcrTimestampAdjuster, j2, j, this.pcrPid, this.timestampSearchBytes);
        this.tsBinarySearchSeeker = tsBinarySearchSeeker;
        this.output.seekMap(tsBinarySearchSeeker.seekMap);
    }

    public static ExtractorsFactory newFactory(final SubtitleParser.Factory factory) {
        return new ExtractorsFactory() { // from class: androidx.media3.extractor.ts.TsExtractor$$ExternalSyntheticLambda0
            @Override // androidx.media3.extractor.ExtractorsFactory
            public final Extractor[] createExtractors() {
                return TsExtractor.$r8$lambda$BZAkJlQ0uiqLjxveSejhTcTV8kY(factory);
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
                ExtractorsFactory.CC.$default$experimentalSetTextTrackTranscodingEnabled(this, z);
                return this;
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory2) {
                ExtractorsFactory.CC.$default$setSubtitleParserFactory(this, factory2);
                return this;
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
                return createExtractors();
            }
        };
    }

    public final boolean fillBufferWithAtLeastOnePacket(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = this.tsPacketBuffer;
        byte[] bArr = parsableByteArray.data;
        if (9400 - parsableByteArray.position < 188) {
            int iBytesLeft = parsableByteArray.bytesLeft();
            if (iBytesLeft > 0) {
                System.arraycopy(bArr, this.tsPacketBuffer.position, bArr, 0, iBytesLeft);
            }
            this.tsPacketBuffer.reset(bArr, iBytesLeft);
        }
        while (this.tsPacketBuffer.bytesLeft() < 188) {
            int i = this.tsPacketBuffer.limit;
            int i2 = extractorInput.read(bArr, i, 9400 - i);
            if (i2 == -1) {
                return false;
            }
            this.tsPacketBuffer.setLimit(i + i2);
        }
        return true;
    }

    public final int findEndOfFirstTsPacketInBuffer() throws ParserException {
        ParsableByteArray parsableByteArray = this.tsPacketBuffer;
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        int iFindSyncBytePosition = TsUtil.findSyncBytePosition(parsableByteArray.data, i, i2);
        this.tsPacketBuffer.setPosition(iFindSyncBytePosition);
        int i3 = iFindSyncBytePosition + 188;
        if (i3 <= i2) {
            this.bytesSinceLastSync = 0;
            return i3;
        }
        int i4 = (iFindSyncBytePosition - i) + this.bytesSinceLastSync;
        this.bytesSinceLastSync = i4;
        if (this.mode != 2 || i4 <= 376) {
            return i3;
        }
        throw ParserException.createForMalformedContainer("Cannot find sync byte. Most likely not a Transport Stream.", null);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        if ((this.extractorFlags & 1) == 0) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.output = extractorOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        long j;
        long length = extractorInput.getLength();
        if (this.tracksEnded) {
            if (length != -1 && this.mode != 2) {
                TsDurationReader tsDurationReader = this.durationReader;
                if (!tsDurationReader.isDurationRead) {
                    return tsDurationReader.readDuration(extractorInput, positionHolder, this.pcrPid);
                }
            }
            maybeOutputSeekMap(length);
            if (this.pendingSeekToStart) {
                this.pendingSeekToStart = false;
                seek(0L, 0L);
                if (extractorInput.getPosition() != 0) {
                    positionHolder.position = 0L;
                    return 1;
                }
            }
            TsBinarySearchSeeker tsBinarySearchSeeker = this.tsBinarySearchSeeker;
            if (tsBinarySearchSeeker != null && tsBinarySearchSeeker.isSeeking()) {
                return this.tsBinarySearchSeeker.handlePendingSeek(extractorInput, positionHolder);
            }
        }
        if (!fillBufferWithAtLeastOnePacket(extractorInput)) {
            return -1;
        }
        int iFindEndOfFirstTsPacketInBuffer = findEndOfFirstTsPacketInBuffer();
        ParsableByteArray parsableByteArray = this.tsPacketBuffer;
        int i = parsableByteArray.limit;
        if (iFindEndOfFirstTsPacketInBuffer > i) {
            return 0;
        }
        int i2 = parsableByteArray.readInt();
        if ((8388608 & i2) != 0) {
            this.tsPacketBuffer.setPosition(iFindEndOfFirstTsPacketInBuffer);
            return 0;
        }
        int i3 = (4194304 & i2) != 0 ? 1 : 0;
        int i4 = (2096896 & i2) >> 8;
        boolean z = (i2 & 32) != 0;
        TsPayloadReader tsPayloadReader = (i2 & 16) != 0 ? this.tsPayloadReaders.get(i4) : null;
        if (tsPayloadReader == null) {
            this.tsPacketBuffer.setPosition(iFindEndOfFirstTsPacketInBuffer);
            return 0;
        }
        if (this.mode != 2) {
            int i5 = i2 & 15;
            j = -1;
            int i6 = this.continuityCounters.get(i4, i5 - 1);
            this.continuityCounters.put(i4, i5);
            if (i6 == i5) {
                this.tsPacketBuffer.setPosition(iFindEndOfFirstTsPacketInBuffer);
                return 0;
            }
            if (i5 != ((i6 + 1) & 15)) {
                tsPayloadReader.seek();
            }
        } else {
            j = -1;
        }
        if (z) {
            int unsignedByte = this.tsPacketBuffer.readUnsignedByte();
            i3 |= (this.tsPacketBuffer.readUnsignedByte() & 64) != 0 ? 2 : 0;
            this.tsPacketBuffer.skipBytes(unsignedByte - 1);
        }
        boolean z2 = this.tracksEnded;
        if (shouldConsumePacketPayload(i4)) {
            this.tsPacketBuffer.setLimit(iFindEndOfFirstTsPacketInBuffer);
            tsPayloadReader.consume(this.tsPacketBuffer, i3);
            this.tsPacketBuffer.setLimit(i);
        }
        if (this.mode != 2 && !z2 && this.tracksEnded && length != j) {
            this.pendingSeekToStart = true;
        }
        this.tsPacketBuffer.setPosition(iFindEndOfFirstTsPacketInBuffer);
        return 0;
    }

    public final void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> sparseArrayCreateInitialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
        int size = sparseArrayCreateInitialPayloadReaders.size();
        for (int i = 0; i < size; i++) {
            this.tsPayloadReaders.put(sparseArrayCreateInitialPayloadReaders.keyAt(i), sparseArrayCreateInitialPayloadReaders.valueAt(i));
        }
        this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
        this.id3Reader = null;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        TsBinarySearchSeeker tsBinarySearchSeeker;
        Assertions.checkState(this.mode != 2);
        int size = this.timestampAdjusters.size();
        for (int i = 0; i < size; i++) {
            TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i);
            boolean z = timestampAdjuster.getTimestampOffsetUs() == -9223372036854775807L;
            if (!z) {
                long firstSampleTimestampUs = timestampAdjuster.getFirstSampleTimestampUs();
                z = (firstSampleTimestampUs == -9223372036854775807L || firstSampleTimestampUs == 0 || firstSampleTimestampUs == j2) ? false : true;
            }
            if (z) {
                timestampAdjuster.reset(j2);
            }
        }
        if (j2 != 0 && (tsBinarySearchSeeker = this.tsBinarySearchSeeker) != null) {
            tsBinarySearchSeeker.setSeekTargetUs(j2);
        }
        this.tsPacketBuffer.reset(0);
        this.continuityCounters.clear();
        for (int i2 = 0; i2 < this.tsPayloadReaders.size(); i2++) {
            this.tsPayloadReaders.valueAt(i2).seek();
        }
        this.bytesSinceLastSync = 0;
    }

    public final boolean shouldConsumePacketPayload(int i) {
        return this.mode == 2 || this.tracksEnded || !this.trackPids.get(i, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
    
        r1 = r1 + 1;
     */
    @Override // androidx.media3.extractor.Extractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean sniff(androidx.media3.extractor.ExtractorInput r7) throws java.io.IOException {
        /*
            r6 = this;
            androidx.media3.common.util.ParsableByteArray r0 = r6.tsPacketBuffer
            byte[] r0 = r0.data
            r1 = 940(0x3ac, float:1.317E-42)
            r2 = 0
            r7.peekFully(r0, r2, r1)
            r1 = 0
        Lb:
            r3 = 188(0xbc, float:2.63E-43)
            if (r1 >= r3) goto L27
            r3 = 0
        L10:
            r4 = 5
            if (r3 >= r4) goto L22
            int r4 = r3 * 188
            int r4 = r4 + r1
            r4 = r0[r4]
            r5 = 71
            if (r4 == r5) goto L1f
            int r1 = r1 + 1
            goto Lb
        L1f:
            int r3 = r3 + 1
            goto L10
        L22:
            r7.skipFully(r1)
            r7 = 1
            return r7
        L27:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.TsExtractor.sniff(androidx.media3.extractor.ExtractorInput):boolean");
    }

    public TsExtractor(SubtitleParser.Factory factory) {
        this(1, 0, factory, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(0), 112800);
    }

    public TsExtractor(int i, SubtitleParser.Factory factory) {
        this(1, i, factory, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(0), 112800);
    }

    @Deprecated
    public TsExtractor(int i) {
        this(1, 1, SubtitleParser.Factory.UNSUPPORTED, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(i), 112800);
    }

    @Deprecated
    public TsExtractor(int i, int i2, int i3) {
        this(i, 1, SubtitleParser.Factory.UNSUPPORTED, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(i2), i3);
    }

    @Deprecated
    public TsExtractor(int i, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory) {
        this(i, 1, SubtitleParser.Factory.UNSUPPORTED, timestampAdjuster, factory, 112800);
    }

    @Deprecated
    public TsExtractor(int i, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory, int i2) {
        this(i, 1, SubtitleParser.Factory.UNSUPPORTED, timestampAdjuster, factory, i2);
    }

    public TsExtractor(int i, int i2, SubtitleParser.Factory factory, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory2, int i3) {
        factory2.getClass();
        this.payloadReaderFactory = factory2;
        this.timestampSearchBytes = i3;
        this.mode = i;
        this.extractorFlags = i2;
        this.subtitleParserFactory = factory;
        if (i != 1 && i != 2) {
            ArrayList arrayList = new ArrayList();
            this.timestampAdjusters = arrayList;
            arrayList.add(timestampAdjuster);
        } else {
            this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
        }
        this.tsPacketBuffer = new ParsableByteArray(new byte[9400], 0);
        this.trackIds = new SparseBooleanArray();
        this.trackPids = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray<>();
        this.continuityCounters = new SparseIntArray();
        this.durationReader = new TsDurationReader(i3);
        this.output = ExtractorOutput.PLACEHOLDER;
        this.pcrPid = -1;
        resetPayloadReaders();
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class PatReader implements SectionPayloadReader {
        public final ParsableBitArray patScratch = new ParsableBitArray(new byte[4], 4);

        public PatReader() {
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void consume(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.readUnsignedByte() == 0 && (parsableByteArray.readUnsignedByte() & 128) != 0) {
                parsableByteArray.skipBytes(6);
                int iBytesLeft = parsableByteArray.bytesLeft() / 4;
                for (int i = 0; i < iBytesLeft; i++) {
                    parsableByteArray.readBytes(this.patScratch, 4);
                    int bits = this.patScratch.readBits(16);
                    this.patScratch.skipBits(3);
                    if (bits == 0) {
                        this.patScratch.skipBits(13);
                    } else {
                        int bits2 = this.patScratch.readBits(13);
                        if (TsExtractor.this.tsPayloadReaders.get(bits2) == null) {
                            TsExtractor tsExtractor = TsExtractor.this;
                            tsExtractor.tsPayloadReaders.put(bits2, new SectionReader(tsExtractor.new PmtReader(bits2)));
                            TsExtractor.access$108(TsExtractor.this);
                        }
                    }
                }
                TsExtractor tsExtractor2 = TsExtractor.this;
                if (tsExtractor2.mode != 2) {
                    tsExtractor2.tsPayloadReaders.remove(0);
                }
            }
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class PmtReader implements SectionPayloadReader {
        public static final int TS_PMT_DESC_AC3 = 106;
        public static final int TS_PMT_DESC_AIT = 111;
        public static final int TS_PMT_DESC_DTS = 123;
        public static final int TS_PMT_DESC_DVBSUBS = 89;
        public static final int TS_PMT_DESC_DVB_EXT = 127;
        public static final int TS_PMT_DESC_DVB_EXT_AC4 = 21;
        public static final int TS_PMT_DESC_DVB_EXT_DTS_HD = 14;
        public static final int TS_PMT_DESC_DVB_EXT_DTS_UHD = 33;
        public static final int TS_PMT_DESC_EAC3 = 122;
        public static final int TS_PMT_DESC_ISO639_LANG = 10;
        public static final int TS_PMT_DESC_REGISTRATION = 5;
        public final int pid;
        public final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5], 5);
        public final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();
        public final SparseIntArray trackIdToPidScratch = new SparseIntArray();

        public PmtReader(int i) {
            this.pid = i;
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void consume(ParsableByteArray parsableByteArray) {
            TimestampAdjuster timestampAdjuster;
            if (parsableByteArray.readUnsignedByte() != 2) {
                return;
            }
            TsExtractor tsExtractor = TsExtractor.this;
            int i = tsExtractor.mode;
            if (i == 1 || i == 2 || tsExtractor.remainingPmts == 1) {
                timestampAdjuster = tsExtractor.timestampAdjusters.get(0);
            } else {
                timestampAdjuster = new TimestampAdjuster(tsExtractor.timestampAdjusters.get(0).getFirstSampleTimestampUs());
                TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
            }
            if ((parsableByteArray.readUnsignedByte() & 128) == 0) {
                return;
            }
            parsableByteArray.skipBytes(1);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            int i2 = 3;
            parsableByteArray.skipBytes(3);
            parsableByteArray.readBytes(this.pmtScratch, 2);
            this.pmtScratch.skipBits(3);
            int i3 = 13;
            TsExtractor.this.pcrPid = this.pmtScratch.readBits(13);
            parsableByteArray.readBytes(this.pmtScratch, 2);
            int i4 = 4;
            this.pmtScratch.skipBits(4);
            parsableByteArray.skipBytes(this.pmtScratch.readBits(12));
            TsExtractor tsExtractor2 = TsExtractor.this;
            if (tsExtractor2.mode == 2 && tsExtractor2.id3Reader == null) {
                TsPayloadReader.EsInfo esInfo = new TsPayloadReader.EsInfo(21, null, 0, null, Util.EMPTY_BYTE_ARRAY);
                TsExtractor tsExtractor3 = TsExtractor.this;
                tsExtractor3.id3Reader = tsExtractor3.payloadReaderFactory.createPayloadReader(21, esInfo);
                TsExtractor tsExtractor4 = TsExtractor.this;
                TsPayloadReader tsPayloadReader = tsExtractor4.id3Reader;
                if (tsPayloadReader != null) {
                    tsPayloadReader.init(timestampAdjuster, tsExtractor4.output, new TsPayloadReader.TrackIdGenerator(unsignedShort, 21, 8192));
                }
            }
            this.trackIdToReaderScratch.clear();
            this.trackIdToPidScratch.clear();
            int iBytesLeft = parsableByteArray.bytesLeft();
            while (iBytesLeft > 0) {
                parsableByteArray.readBytes(this.pmtScratch, 5);
                int bits = this.pmtScratch.readBits(8);
                this.pmtScratch.skipBits(i2);
                int bits2 = this.pmtScratch.readBits(i3);
                this.pmtScratch.skipBits(i4);
                int bits3 = this.pmtScratch.readBits(12);
                TsPayloadReader.EsInfo esInfo2 = readEsInfo(parsableByteArray, bits3);
                if (bits == 6 || bits == 5) {
                    bits = esInfo2.streamType;
                }
                iBytesLeft -= bits3 + 5;
                TsExtractor tsExtractor5 = TsExtractor.this;
                int i5 = tsExtractor5.mode == 2 ? bits : bits2;
                if (!tsExtractor5.trackIds.get(i5)) {
                    TsExtractor tsExtractor6 = TsExtractor.this;
                    TsPayloadReader tsPayloadReaderCreatePayloadReader = (tsExtractor6.mode == 2 && bits == 21) ? tsExtractor6.id3Reader : tsExtractor6.payloadReaderFactory.createPayloadReader(bits, esInfo2);
                    if (TsExtractor.this.mode != 2 || bits2 < this.trackIdToPidScratch.get(i5, 8192)) {
                        this.trackIdToPidScratch.put(i5, bits2);
                        this.trackIdToReaderScratch.put(i5, tsPayloadReaderCreatePayloadReader);
                    }
                }
                i2 = 3;
                i4 = 4;
                i3 = 13;
            }
            int size = this.trackIdToPidScratch.size();
            for (int i6 = 0; i6 < size; i6++) {
                int iKeyAt = this.trackIdToPidScratch.keyAt(i6);
                int iValueAt = this.trackIdToPidScratch.valueAt(i6);
                TsExtractor.this.trackIds.put(iKeyAt, true);
                TsExtractor.this.trackPids.put(iValueAt, true);
                TsPayloadReader tsPayloadReaderValueAt = this.trackIdToReaderScratch.valueAt(i6);
                if (tsPayloadReaderValueAt != null) {
                    TsExtractor tsExtractor7 = TsExtractor.this;
                    if (tsPayloadReaderValueAt != tsExtractor7.id3Reader) {
                        tsPayloadReaderValueAt.init(timestampAdjuster, tsExtractor7.output, new TsPayloadReader.TrackIdGenerator(unsignedShort, iKeyAt, 8192));
                    }
                    TsExtractor.this.tsPayloadReaders.put(iValueAt, tsPayloadReaderValueAt);
                }
            }
            TsExtractor tsExtractor8 = TsExtractor.this;
            if (tsExtractor8.mode == 2) {
                if (tsExtractor8.tracksEnded) {
                    return;
                }
                tsExtractor8.output.endTracks();
                TsExtractor tsExtractor9 = TsExtractor.this;
                tsExtractor9.remainingPmts = 0;
                tsExtractor9.tracksEnded = true;
                return;
            }
            tsExtractor8.tsPayloadReaders.remove(this.pid);
            TsExtractor tsExtractor10 = TsExtractor.this;
            int i7 = tsExtractor10.mode == 1 ? 0 : tsExtractor10.remainingPmts - 1;
            tsExtractor10.remainingPmts = i7;
            if (i7 == 0) {
                tsExtractor10.output.endTracks();
                TsExtractor.this.tracksEnded = true;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.media3.extractor.ts.TsPayloadReader.EsInfo readEsInfo(androidx.media3.common.util.ParsableByteArray r18, int r19) {
            /*
                Method dump skipped, instruction units count: 233
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.TsExtractor.PmtReader.readEsInfo(androidx.media3.common.util.ParsableByteArray, int):androidx.media3.extractor.ts.TsPayloadReader$EsInfo");
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }
}
