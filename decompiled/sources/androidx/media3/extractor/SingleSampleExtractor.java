package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SingleSampleExtractor implements Extractor {
    public static final int FIXED_READ_LENGTH = 1024;
    public static final int IMAGE_TRACK_ID = 1024;
    public static final int STATE_ENDED = 2;
    public static final int STATE_READING = 1;
    public ExtractorOutput extractorOutput;
    public final int fileSignature;
    public final int fileSignatureLength;
    public final String sampleMimeType;
    public int size;
    public int state;
    public TrackOutput trackOutput;

    public SingleSampleExtractor(int i, int i2, String str) {
        this.fileSignature = i;
        this.fileSignatureLength = i2;
        this.sampleMimeType = str;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        outputImageTrackAndSeekMap(this.sampleMimeType);
    }

    @RequiresNonNull({"this.extractorOutput"})
    public final void outputImageTrackAndSeekMap(String str) {
        TrackOutput trackOutputTrack = this.extractorOutput.track(1024, 4);
        this.trackOutput = trackOutputTrack;
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType(str);
        trackOutputTrack.format(new Format(builder));
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SingleSampleSeekMap(-9223372036854775807L));
        this.state = 1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i = this.state;
        if (i == 1) {
            readSegment(extractorInput);
            return 0;
        }
        if (i == 2) {
            return -1;
        }
        throw new IllegalStateException();
    }

    public final void readSegment(ExtractorInput extractorInput) throws IOException {
        TrackOutput trackOutput = this.trackOutput;
        trackOutput.getClass();
        int iSampleData = trackOutput.sampleData((DataReader) extractorInput, 1024, true);
        if (iSampleData != -1) {
            this.size += iSampleData;
            return;
        }
        this.state = 2;
        this.trackOutput.sampleMetadata(0L, 1, this.size, 0, null);
        this.size = 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        if (j == 0 || this.state == 1) {
            this.state = 1;
            this.size = 0;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        Assertions.checkState((this.fileSignature == -1 || this.fileSignatureLength == -1) ? false : true);
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.fileSignatureLength);
        extractorInput.peekFully(parsableByteArray.data, 0, this.fileSignatureLength);
        return parsableByteArray.readUnsignedShort() == this.fileSignature;
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
