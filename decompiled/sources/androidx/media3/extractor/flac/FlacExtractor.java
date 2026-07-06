package androidx.media3.extractor.flac;

import androidx.annotation.Nullable;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacMetadataReader;
import androidx.media3.extractor.FlacSeekTableSeekMap;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class FlacExtractor implements Extractor {
    public static final int BUFFER_LENGTH = 32768;
    public static final ExtractorsFactory FACTORY = new FlacExtractor$$ExternalSyntheticLambda0();
    public static final int FLAG_DISABLE_ID3_METADATA = 1;
    public static final int SAMPLE_NUMBER_UNKNOWN = -1;
    public static final int STATE_GET_FRAME_START_MARKER = 4;
    public static final int STATE_GET_STREAM_MARKER_AND_INFO_BLOCK_BYTES = 1;
    public static final int STATE_READ_FRAMES = 5;
    public static final int STATE_READ_ID3_METADATA = 0;
    public static final int STATE_READ_METADATA_BLOCKS = 3;
    public static final int STATE_READ_STREAM_MARKER = 2;
    public FlacBinarySearchSeeker binarySearchSeeker;
    public final ParsableByteArray buffer;
    public int currentFrameBytesWritten;
    public long currentFrameFirstSampleNumber;
    public ExtractorOutput extractorOutput;
    public FlacStreamMetadata flacStreamMetadata;
    public int frameStartMarker;

    @Nullable
    public Metadata id3Metadata;
    public final boolean id3MetadataDisabled;
    public int minFrameSize;
    public final FlacFrameReader.SampleNumberHolder sampleNumberHolder;
    public int state;
    public final byte[] streamMarkerAndInfoBlock;
    public TrackOutput trackOutput;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static Extractor[] $r8$lambda$WFdhFS2w9xcH8MtglVnuJ0Dti4M() {
        return new Extractor[]{new FlacExtractor(0)};
    }

    public FlacExtractor() {
        this(0);
    }

    public final long findFrame(ParsableByteArray parsableByteArray, boolean z) {
        boolean zCheckAndReadFrameHeader;
        this.flacStreamMetadata.getClass();
        int i = parsableByteArray.position;
        while (i <= parsableByteArray.limit - 16) {
            parsableByteArray.setPosition(i);
            if (FlacFrameReader.checkAndReadFrameHeader(parsableByteArray, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder)) {
                parsableByteArray.setPosition(i);
                return this.sampleNumberHolder.sampleNumber;
            }
            i++;
        }
        if (!z) {
            parsableByteArray.setPosition(i);
            return -1L;
        }
        while (true) {
            int i2 = parsableByteArray.limit;
            if (i > i2 - this.minFrameSize) {
                parsableByteArray.setPosition(i2);
                return -1L;
            }
            parsableByteArray.setPosition(i);
            try {
                zCheckAndReadFrameHeader = FlacFrameReader.checkAndReadFrameHeader(parsableByteArray, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder);
            } catch (IndexOutOfBoundsException unused) {
                zCheckAndReadFrameHeader = false;
            }
            if (parsableByteArray.position <= parsableByteArray.limit ? zCheckAndReadFrameHeader : false) {
                parsableByteArray.setPosition(i);
                return this.sampleNumberHolder.sampleNumber;
            }
            i++;
        }
    }

    public final void getFrameStartMarker(ExtractorInput extractorInput) throws IOException {
        this.frameStartMarker = FlacMetadataReader.getFrameStartMarker(extractorInput);
        ExtractorOutput extractorOutput = this.extractorOutput;
        Util.castNonNull(extractorOutput);
        extractorOutput.seekMap(getSeekMap(extractorInput.getPosition(), extractorInput.getLength()));
        this.state = 5;
    }

    public final SeekMap getSeekMap(long j, long j2) {
        this.flacStreamMetadata.getClass();
        FlacStreamMetadata flacStreamMetadata = this.flacStreamMetadata;
        if (flacStreamMetadata.seekTable != null) {
            return new FlacSeekTableSeekMap(flacStreamMetadata, j);
        }
        if (j2 == -1 || flacStreamMetadata.totalSamples <= 0) {
            return new SeekMap.Unseekable(flacStreamMetadata.getDurationUs());
        }
        FlacBinarySearchSeeker flacBinarySearchSeeker = new FlacBinarySearchSeeker(flacStreamMetadata, this.frameStartMarker, j, j2);
        this.binarySearchSeeker = flacBinarySearchSeeker;
        return flacBinarySearchSeeker.seekMap;
    }

    public final void getStreamMarkerAndInfoBlockBytes(ExtractorInput extractorInput) throws IOException {
        byte[] bArr = this.streamMarkerAndInfoBlock;
        extractorInput.peekFully(bArr, 0, bArr.length);
        extractorInput.resetPeekPosition();
        this.state = 2;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 1);
        extractorOutput.endTracks();
    }

    public final void outputSampleMetadata() {
        long j = this.currentFrameFirstSampleNumber * 1000000;
        FlacStreamMetadata flacStreamMetadata = this.flacStreamMetadata;
        Util.castNonNull(flacStreamMetadata);
        this.trackOutput.sampleMetadata(j / ((long) flacStreamMetadata.sampleRate), 1, this.currentFrameBytesWritten, 0, null);
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i = this.state;
        if (i == 0) {
            readId3Metadata(extractorInput);
            return 0;
        }
        if (i == 1) {
            getStreamMarkerAndInfoBlockBytes(extractorInput);
            return 0;
        }
        if (i == 2) {
            readStreamMarker(extractorInput);
            return 0;
        }
        if (i == 3) {
            readMetadataBlocks(extractorInput);
            return 0;
        }
        if (i == 4) {
            getFrameStartMarker(extractorInput);
            return 0;
        }
        if (i == 5) {
            return readFrames(extractorInput, positionHolder);
        }
        throw new IllegalStateException();
    }

    public final int readFrames(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        boolean z;
        this.trackOutput.getClass();
        this.flacStreamMetadata.getClass();
        FlacBinarySearchSeeker flacBinarySearchSeeker = this.binarySearchSeeker;
        if (flacBinarySearchSeeker != null && flacBinarySearchSeeker.isSeeking()) {
            return this.binarySearchSeeker.handlePendingSeek(extractorInput, positionHolder);
        }
        if (this.currentFrameFirstSampleNumber == -1) {
            this.currentFrameFirstSampleNumber = FlacFrameReader.getFirstSampleNumber(extractorInput, this.flacStreamMetadata);
            return 0;
        }
        ParsableByteArray parsableByteArray = this.buffer;
        int i = parsableByteArray.limit;
        if (i < 32768) {
            int i2 = extractorInput.read(parsableByteArray.data, i, 32768 - i);
            z = i2 == -1;
            if (!z) {
                this.buffer.setLimit(i + i2);
            } else if (this.buffer.bytesLeft() == 0) {
                outputSampleMetadata();
                return -1;
            }
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray2 = this.buffer;
        int i3 = parsableByteArray2.position;
        int i4 = this.currentFrameBytesWritten;
        int i5 = this.minFrameSize;
        if (i4 < i5) {
            parsableByteArray2.skipBytes(Math.min(i5 - i4, parsableByteArray2.bytesLeft()));
        }
        long jFindFrame = findFrame(this.buffer, z);
        ParsableByteArray parsableByteArray3 = this.buffer;
        int i6 = parsableByteArray3.position - i3;
        parsableByteArray3.setPosition(i3);
        this.trackOutput.sampleData(this.buffer, i6);
        this.currentFrameBytesWritten += i6;
        if (jFindFrame != -1) {
            outputSampleMetadata();
            this.currentFrameBytesWritten = 0;
            this.currentFrameFirstSampleNumber = jFindFrame;
        }
        if (this.buffer.bytesLeft() < 16) {
            int iBytesLeft = this.buffer.bytesLeft();
            ParsableByteArray parsableByteArray4 = this.buffer;
            byte[] bArr = parsableByteArray4.data;
            System.arraycopy(bArr, parsableByteArray4.position, bArr, 0, iBytesLeft);
            this.buffer.setPosition(0);
            this.buffer.setLimit(iBytesLeft);
        }
        return 0;
    }

    public final void readId3Metadata(ExtractorInput extractorInput) throws IOException {
        this.id3Metadata = FlacMetadataReader.readId3Metadata(extractorInput, !this.id3MetadataDisabled);
        this.state = 1;
    }

    public final void readMetadataBlocks(ExtractorInput extractorInput) throws IOException {
        FlacMetadataReader.FlacStreamMetadataHolder flacStreamMetadataHolder = new FlacMetadataReader.FlacStreamMetadataHolder(this.flacStreamMetadata);
        boolean metadataBlock = false;
        while (!metadataBlock) {
            metadataBlock = FlacMetadataReader.readMetadataBlock(extractorInput, flacStreamMetadataHolder);
            FlacStreamMetadata flacStreamMetadata = flacStreamMetadataHolder.flacStreamMetadata;
            Util.castNonNull(flacStreamMetadata);
            this.flacStreamMetadata = flacStreamMetadata;
        }
        this.flacStreamMetadata.getClass();
        this.minFrameSize = Math.max(this.flacStreamMetadata.minFrameSize, 6);
        TrackOutput trackOutput = this.trackOutput;
        Util.castNonNull(trackOutput);
        trackOutput.format(this.flacStreamMetadata.getFormat(this.streamMarkerAndInfoBlock, this.id3Metadata));
        this.state = 4;
    }

    public final void readStreamMarker(ExtractorInput extractorInput) throws IOException {
        FlacMetadataReader.readStreamMarker(extractorInput);
        this.state = 3;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        if (j == 0) {
            this.state = 0;
        } else {
            FlacBinarySearchSeeker flacBinarySearchSeeker = this.binarySearchSeeker;
            if (flacBinarySearchSeeker != null) {
                flacBinarySearchSeeker.setSeekTargetUs(j2);
            }
        }
        this.currentFrameFirstSampleNumber = j2 != 0 ? -1L : 0L;
        this.currentFrameBytesWritten = 0;
        this.buffer.reset(0);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        FlacMetadataReader.peekId3Metadata(extractorInput, false);
        return FlacMetadataReader.checkAndPeekStreamMarker(extractorInput);
    }

    public FlacExtractor(int i) {
        this.streamMarkerAndInfoBlock = new byte[42];
        this.buffer = new ParsableByteArray(new byte[32768], 0);
        this.id3MetadataDisabled = (i & 1) != 0;
        this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        this.state = 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
