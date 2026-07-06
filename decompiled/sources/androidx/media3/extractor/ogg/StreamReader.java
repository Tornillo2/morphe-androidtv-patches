package androidx.media3.extractor.ogg;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class StreamReader {
    public static final int STATE_END_OF_INPUT = 3;
    public static final int STATE_READ_HEADERS = 0;
    public static final int STATE_READ_PAYLOAD = 2;
    public static final int STATE_SKIP_HEADERS = 1;
    public long currentGranule;
    public ExtractorOutput extractorOutput;
    public boolean formatSet;
    public long lengthOfReadPacket;
    public OggSeeker oggSeeker;
    public long payloadStartPosition;
    public int sampleRate;
    public boolean seekMapSet;
    public int state;
    public long targetGranule;
    public TrackOutput trackOutput;
    public final OggPacket oggPacket = new OggPacket();
    public SetupData setupData = new SetupData();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SetupData {
        public Format format;
        public OggSeeker oggSeeker;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnseekableOggSeeker implements OggSeeker {
        public UnseekableOggSeeker() {
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public SeekMap createSeekMap() {
            return new SeekMap.Unseekable(-9223372036854775807L);
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public long read(ExtractorInput extractorInput) {
            return -1L;
        }

        public UnseekableOggSeeker(AnonymousClass1 anonymousClass1) {
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public void startSeek(long j) {
        }
    }

    @EnsuresNonNull({"trackOutput", "extractorOutput"})
    public final void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    public long convertGranuleToTime(long j) {
        return (j * 1000000) / ((long) this.sampleRate);
    }

    public long convertTimeToGranule(long j) {
        return (((long) this.sampleRate) * j) / 1000000;
    }

    public void init(ExtractorOutput extractorOutput, TrackOutput trackOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = trackOutput;
        reset(true);
    }

    public void onSeekEnd(long j) {
        this.currentGranule = j;
    }

    public abstract long preparePayload(ParsableByteArray parsableByteArray);

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        int i = this.state;
        if (i == 0) {
            return readHeadersAndUpdateState(extractorInput);
        }
        if (i == 1) {
            extractorInput.skipFully((int) this.payloadStartPosition);
            this.state = 2;
            return 0;
        }
        if (i == 2) {
            return readPayload(extractorInput, positionHolder);
        }
        if (i == 3) {
            return -1;
        }
        throw new IllegalStateException();
    }

    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public abstract boolean readHeaders(ParsableByteArray parsableByteArray, long j, SetupData setupData) throws IOException;

    @EnsuresNonNullIf(expression = {"setupData.format"}, result = true)
    public final boolean readHeaders(ExtractorInput extractorInput) throws IOException {
        while (this.oggPacket.populate(extractorInput)) {
            long position = extractorInput.getPosition();
            long j = this.payloadStartPosition;
            this.lengthOfReadPacket = position - j;
            if (!readHeaders(this.oggPacket.packetArray, j, this.setupData)) {
                return true;
            }
            this.payloadStartPosition = extractorInput.getPosition();
        }
        this.state = 3;
        return false;
    }

    @RequiresNonNull({"trackOutput"})
    public final int readHeadersAndUpdateState(ExtractorInput extractorInput) throws IOException {
        if (!readHeaders(extractorInput)) {
            return -1;
        }
        Format format = this.setupData.format;
        this.sampleRate = format.sampleRate;
        if (!this.formatSet) {
            this.trackOutput.format(format);
            this.formatSet = true;
        }
        OggSeeker oggSeeker = this.setupData.oggSeeker;
        if (oggSeeker != null) {
            this.oggSeeker = oggSeeker;
        } else if (extractorInput.getLength() == -1) {
            this.oggSeeker = new UnseekableOggSeeker();
        } else {
            OggPageHeader oggPageHeader = this.oggPacket.pageHeader;
            this.oggSeeker = new DefaultOggSeeker(this, this.payloadStartPosition, extractorInput.getLength(), oggPageHeader.headerSize + oggPageHeader.bodySize, oggPageHeader.granulePosition, (oggPageHeader.type & 4) != 0);
        }
        this.state = 2;
        this.oggPacket.trimPayload();
        return 0;
    }

    @RequiresNonNull({"trackOutput", "oggSeeker", "extractorOutput"})
    public final int readPayload(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        long j = this.oggSeeker.read(extractorInput);
        if (j >= 0) {
            positionHolder.position = j;
            return 1;
        }
        if (j < -1) {
            onSeekEnd(-(j + 2));
        }
        if (!this.seekMapSet) {
            SeekMap seekMapCreateSeekMap = this.oggSeeker.createSeekMap();
            Assertions.checkStateNotNull(seekMapCreateSeekMap);
            this.extractorOutput.seekMap(seekMapCreateSeekMap);
            this.seekMapSet = true;
        }
        if (this.lengthOfReadPacket <= 0 && !this.oggPacket.populate(extractorInput)) {
            this.state = 3;
            return -1;
        }
        this.lengthOfReadPacket = 0L;
        ParsableByteArray parsableByteArray = this.oggPacket.packetArray;
        long jPreparePayload = preparePayload(parsableByteArray);
        if (jPreparePayload >= 0) {
            long j2 = this.currentGranule;
            if (j2 + jPreparePayload >= this.targetGranule) {
                long jConvertGranuleToTime = convertGranuleToTime(j2);
                this.trackOutput.sampleData(parsableByteArray, parsableByteArray.limit);
                this.trackOutput.sampleMetadata(jConvertGranuleToTime, 1, parsableByteArray.limit, 0, null);
                this.targetGranule = -1L;
            }
        }
        this.currentGranule += jPreparePayload;
        return 0;
    }

    public void reset(boolean z) {
        if (z) {
            this.setupData = new SetupData();
            this.payloadStartPosition = 0L;
            this.state = 0;
        } else {
            this.state = 1;
        }
        this.targetGranule = -1L;
        this.currentGranule = 0L;
    }

    public final void seek(long j, long j2) {
        this.oggPacket.reset();
        if (j == 0) {
            reset(!this.seekMapSet);
            return;
        }
        if (this.state != 0) {
            this.targetGranule = convertTimeToGranule(j2);
            OggSeeker oggSeeker = this.oggSeeker;
            Util.castNonNull(oggSeeker);
            oggSeeker.startSeek(this.targetGranule);
            this.state = 2;
        }
    }
}
