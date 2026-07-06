package androidx.media3.extractor.avi;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.DummyExtractorOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class AviExtractor implements Extractor {
    public static final int AVIIF_KEYFRAME = 16;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 1;
    public static final int FOURCC_AVI_ = 541677121;
    public static final int FOURCC_JUNK = 1263424842;
    public static final int FOURCC_LIST = 1414744396;
    public static final int FOURCC_RIFF = 1179011410;
    public static final int FOURCC_auds = 1935963489;
    public static final int FOURCC_avih = 1751742049;
    public static final int FOURCC_hdrl = 1819436136;
    public static final int FOURCC_idx1 = 829973609;
    public static final int FOURCC_movi = 1769369453;
    public static final int FOURCC_strf = 1718776947;
    public static final int FOURCC_strh = 1752331379;
    public static final int FOURCC_strl = 1819440243;
    public static final int FOURCC_strn = 1852994675;
    public static final int FOURCC_txts = 1937012852;
    public static final int FOURCC_vids = 1935960438;
    public static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    public static final int STATE_FINDING_IDX1_HEADER = 4;
    public static final int STATE_FINDING_MOVI_HEADER = 3;
    public static final int STATE_READING_HDRL_BODY = 2;
    public static final int STATE_READING_HDRL_HEADER = 1;
    public static final int STATE_READING_IDX1_BODY = 5;
    public static final int STATE_READING_SAMPLES = 6;
    public static final int STATE_SKIPPING_TO_HDRL = 0;
    public static final String TAG = "AviExtractor";
    public AviMainHeaderChunk aviHeader;
    public final ChunkHeaderHolder chunkHeaderHolder;
    public ChunkReader[] chunkReaders;

    @Nullable
    public ChunkReader currentChunkReader;
    public long durationUs;
    public ExtractorOutput extractorOutput;
    public int hdrlSize;
    public int idx1BodySize;
    public long moviEnd;
    public long moviStart;
    public final boolean parseSubtitlesDuringExtraction;
    public long pendingReposition;
    public final ParsableByteArray scratch;
    public boolean seekMapHasBeenOutput;
    public int state;
    public final SubtitleParser.Factory subtitleParserFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AviSeekMap implements SeekMap {
        public final long durationUs;

        public AviSeekMap(long j) {
            this.durationUs = j;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            SeekMap.SeekPoints seekPoints = AviExtractor.this.chunkReaders[0].getSeekPoints(j);
            int i = 1;
            while (true) {
                ChunkReader[] chunkReaderArr = AviExtractor.this.chunkReaders;
                if (i >= chunkReaderArr.length) {
                    return seekPoints;
                }
                SeekMap.SeekPoints seekPoints2 = chunkReaderArr[i].getSeekPoints(j);
                if (seekPoints2.first.position < seekPoints.first.position) {
                    seekPoints = seekPoints2;
                }
                i++;
            }
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ChunkHeaderHolder {
        public int chunkType;
        public int listType;
        public int size;

        public ChunkHeaderHolder() {
        }

        public void populateFrom(ParsableByteArray parsableByteArray) {
            this.chunkType = parsableByteArray.readLittleEndianInt();
            this.size = parsableByteArray.readLittleEndianInt();
            this.listType = 0;
        }

        public void populateWithListHeaderFrom(ParsableByteArray parsableByteArray) throws ParserException {
            populateFrom(parsableByteArray);
            if (this.chunkType == 1414744396) {
                this.listType = parsableByteArray.readLittleEndianInt();
            } else {
                throw ParserException.createForMalformedContainer("LIST expected, found: " + this.chunkType, null);
            }
        }

        public ChunkHeaderHolder(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    @Deprecated
    public AviExtractor() {
        this(1, SubtitleParser.Factory.UNSUPPORTED);
    }

    public static void alignInputToEvenPosition(ExtractorInput extractorInput) throws IOException {
        if ((extractorInput.getPosition() & 1) == 1) {
            extractorInput.skipFully(1);
        }
    }

    @Nullable
    public final ChunkReader getChunkReader(int i) {
        for (ChunkReader chunkReader : this.chunkReaders) {
            if (chunkReader.handlesChunkId(i)) {
                return chunkReader;
            }
        }
        return null;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.state = 0;
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput;
        this.pendingReposition = -1L;
    }

    public final void parseHdrlBody(ParsableByteArray parsableByteArray) throws IOException {
        ListChunk from = ListChunk.parseFrom(1819436136, parsableByteArray);
        if (from.type != 1819436136) {
            throw ParserException.createForMalformedContainer("Unexpected header list type " + from.type, null);
        }
        AviMainHeaderChunk aviMainHeaderChunk = (AviMainHeaderChunk) from.getChild(AviMainHeaderChunk.class);
        if (aviMainHeaderChunk == null) {
            throw ParserException.createForMalformedContainer("AviHeader not found", null);
        }
        this.aviHeader = aviMainHeaderChunk;
        this.durationUs = ((long) aviMainHeaderChunk.totalFrames) * ((long) aviMainHeaderChunk.frameDurationUs);
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<AviChunk> it = from.children.iterator();
        int i = 0;
        while (it.hasNext()) {
            AviChunk next = it.next();
            if (next.getType() == 1819440243) {
                int i2 = i + 1;
                ChunkReader chunkReaderProcessStreamList = processStreamList((ListChunk) next, i);
                if (chunkReaderProcessStreamList != null) {
                    arrayList.add(chunkReaderProcessStreamList);
                }
                i = i2;
            }
        }
        this.chunkReaders = (ChunkReader[]) arrayList.toArray(new ChunkReader[0]);
        this.extractorOutput.endTracks();
    }

    public final void parseIdx1Body(ParsableByteArray parsableByteArray) {
        long jPeekSeekOffset = peekSeekOffset(parsableByteArray);
        while (parsableByteArray.bytesLeft() >= 16) {
            int littleEndianInt = parsableByteArray.readLittleEndianInt();
            int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
            long littleEndianInt3 = ((long) parsableByteArray.readLittleEndianInt()) + jPeekSeekOffset;
            parsableByteArray.readLittleEndianInt();
            ChunkReader chunkReader = getChunkReader(littleEndianInt);
            if (chunkReader != null) {
                if ((littleEndianInt2 & 16) == 16) {
                    chunkReader.appendKeyFrameToIndex(littleEndianInt3);
                }
                chunkReader.incrementIndexChunkCount();
            }
        }
        for (ChunkReader chunkReader2 : this.chunkReaders) {
            chunkReader2.compactIndex();
        }
        this.seekMapHasBeenOutput = true;
        this.extractorOutput.seekMap(new AviSeekMap(this.durationUs));
    }

    public final long peekSeekOffset(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 16) {
            return 0L;
        }
        int i = parsableByteArray.position;
        parsableByteArray.skipBytes(8);
        long littleEndianInt = parsableByteArray.readLittleEndianInt();
        long j = this.moviStart;
        long j2 = littleEndianInt <= j ? 8 + j : 0L;
        parsableByteArray.setPosition(i);
        return j2;
    }

    @Nullable
    public final ChunkReader processStreamList(ListChunk listChunk, int i) {
        AviStreamHeaderChunk aviStreamHeaderChunk = (AviStreamHeaderChunk) listChunk.getChild(AviStreamHeaderChunk.class);
        StreamFormatChunk streamFormatChunk = (StreamFormatChunk) listChunk.getChild(StreamFormatChunk.class);
        if (aviStreamHeaderChunk == null) {
            Log.w("AviExtractor", "Missing Stream Header");
            return null;
        }
        if (streamFormatChunk == null) {
            Log.w("AviExtractor", "Missing Stream Format");
            return null;
        }
        long durationUs = aviStreamHeaderChunk.getDurationUs();
        Format format = streamFormatChunk.format;
        format.getClass();
        Format.Builder builder = new Format.Builder(format);
        builder.setId(i);
        int i2 = aviStreamHeaderChunk.suggestedBufferSize;
        if (i2 != 0) {
            builder.maxInputSize = i2;
        }
        StreamNameChunk streamNameChunk = (StreamNameChunk) listChunk.getChild(StreamNameChunk.class);
        if (streamNameChunk != null) {
            builder.label = streamNameChunk.name;
        }
        int trackType = MimeTypes.getTrackType(format.sampleMimeType);
        if (trackType != 1 && trackType != 2) {
            return null;
        }
        TrackOutput trackOutputTrack = this.extractorOutput.track(i, trackType);
        trackOutputTrack.format(new Format(builder));
        ChunkReader chunkReader = new ChunkReader(i, trackType, durationUs, aviStreamHeaderChunk.length, trackOutputTrack);
        this.durationUs = durationUs;
        return chunkReader;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (resolvePendingReposition(extractorInput, positionHolder)) {
            return 1;
        }
        switch (this.state) {
            case 0:
                if (!sniff(extractorInput)) {
                    throw ParserException.createForMalformedContainer("AVI Header List not found", null);
                }
                extractorInput.skipFully(12);
                this.state = 1;
                return 0;
            case 1:
                extractorInput.readFully(this.scratch.data, 0, 12);
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateWithListHeaderFrom(this.scratch);
                ChunkHeaderHolder chunkHeaderHolder = this.chunkHeaderHolder;
                if (chunkHeaderHolder.listType == 1819436136) {
                    this.hdrlSize = chunkHeaderHolder.size;
                    this.state = 2;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("hdrl expected, found: " + this.chunkHeaderHolder.listType, null);
            case 2:
                int i = this.hdrlSize - 4;
                ParsableByteArray parsableByteArray = new ParsableByteArray(i);
                extractorInput.readFully(parsableByteArray.data, 0, i);
                parseHdrlBody(parsableByteArray);
                this.state = 3;
                return 0;
            case 3:
                if (this.moviStart != -1) {
                    long position = extractorInput.getPosition();
                    long j = this.moviStart;
                    if (position != j) {
                        this.pendingReposition = j;
                        return 0;
                    }
                }
                extractorInput.peekFully(this.scratch.data, 0, 12);
                extractorInput.resetPeekPosition();
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateFrom(this.scratch);
                int littleEndianInt = this.scratch.readLittleEndianInt();
                int i2 = this.chunkHeaderHolder.chunkType;
                if (i2 == 1179011410) {
                    extractorInput.skipFully(12);
                    return 0;
                }
                if (i2 != 1414744396 || littleEndianInt != 1769369453) {
                    this.pendingReposition = extractorInput.getPosition() + ((long) this.chunkHeaderHolder.size) + 8;
                    return 0;
                }
                long position2 = extractorInput.getPosition();
                this.moviStart = position2;
                this.moviEnd = position2 + ((long) this.chunkHeaderHolder.size) + 8;
                if (!this.seekMapHasBeenOutput) {
                    AviMainHeaderChunk aviMainHeaderChunk = this.aviHeader;
                    aviMainHeaderChunk.getClass();
                    if (aviMainHeaderChunk.hasIndex()) {
                        this.state = 4;
                        this.pendingReposition = this.moviEnd;
                        return 0;
                    }
                    this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                    this.seekMapHasBeenOutput = true;
                }
                this.pendingReposition = extractorInput.getPosition() + 12;
                this.state = 6;
                return 0;
            case 4:
                extractorInput.readFully(this.scratch.data, 0, 8);
                this.scratch.setPosition(0);
                int littleEndianInt2 = this.scratch.readLittleEndianInt();
                int littleEndianInt3 = this.scratch.readLittleEndianInt();
                if (littleEndianInt2 != 829973609) {
                    this.pendingReposition = extractorInput.getPosition() + ((long) littleEndianInt3);
                    return 0;
                }
                this.state = 5;
                this.idx1BodySize = littleEndianInt3;
                return 0;
            case 5:
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(this.idx1BodySize);
                extractorInput.readFully(parsableByteArray2.data, 0, this.idx1BodySize);
                parseIdx1Body(parsableByteArray2);
                this.state = 6;
                this.pendingReposition = this.moviStart;
                return 0;
            case 6:
                return readMoviChunks(extractorInput);
            default:
                throw new AssertionError();
        }
    }

    public final int readMoviChunks(ExtractorInput extractorInput) throws IOException {
        if (extractorInput.getPosition() >= this.moviEnd) {
            return -1;
        }
        ChunkReader chunkReader = this.currentChunkReader;
        if (chunkReader == null) {
            alignInputToEvenPosition(extractorInput);
            extractorInput.peekFully(this.scratch.data, 0, 12);
            this.scratch.setPosition(0);
            int littleEndianInt = this.scratch.readLittleEndianInt();
            if (littleEndianInt == 1414744396) {
                this.scratch.setPosition(8);
                extractorInput.skipFully(this.scratch.readLittleEndianInt() != 1769369453 ? 8 : 12);
                extractorInput.resetPeekPosition();
                return 0;
            }
            int littleEndianInt2 = this.scratch.readLittleEndianInt();
            if (littleEndianInt == 1263424842) {
                this.pendingReposition = extractorInput.getPosition() + ((long) littleEndianInt2) + 8;
                return 0;
            }
            extractorInput.skipFully(8);
            extractorInput.resetPeekPosition();
            ChunkReader chunkReader2 = getChunkReader(littleEndianInt);
            if (chunkReader2 == null) {
                this.pendingReposition = extractorInput.getPosition() + ((long) littleEndianInt2);
                return 0;
            }
            chunkReader2.currentChunkSize = littleEndianInt2;
            chunkReader2.bytesRemainingInCurrentChunk = littleEndianInt2;
            this.currentChunkReader = chunkReader2;
        } else if (chunkReader.onChunkData(extractorInput)) {
            this.currentChunkReader = null;
        }
        return 0;
    }

    public final boolean resolvePendingReposition(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        boolean z;
        if (this.pendingReposition != -1) {
            long position = extractorInput.getPosition();
            long j = this.pendingReposition;
            if (j < position || j > 262144 + position) {
                positionHolder.position = j;
                z = true;
            } else {
                extractorInput.skipFully((int) (j - position));
                z = false;
            }
        } else {
            z = false;
        }
        this.pendingReposition = -1L;
        return z;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        this.pendingReposition = -1L;
        this.currentChunkReader = null;
        for (ChunkReader chunkReader : this.chunkReaders) {
            chunkReader.seekToPosition(j);
        }
        if (j != 0) {
            this.state = 6;
        } else if (this.chunkReaders.length == 0) {
            this.state = 0;
        } else {
            this.state = 3;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.data, 0, 12);
        this.scratch.setPosition(0);
        if (this.scratch.readLittleEndianInt() != 1179011410) {
            return false;
        }
        this.scratch.skipBytes(4);
        return this.scratch.readLittleEndianInt() == 541677121;
    }

    public AviExtractor(int i, SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        this.parseSubtitlesDuringExtraction = (i & 1) == 0;
        this.scratch = new ParsableByteArray(12);
        this.chunkHeaderHolder = new ChunkHeaderHolder();
        this.extractorOutput = new DummyExtractorOutput();
        this.chunkReaders = new ChunkReader[0];
        this.moviStart = -1L;
        this.moviEnd = -1L;
        this.hdrlSize = -1;
        this.durationUs = -9223372036854775807L;
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
