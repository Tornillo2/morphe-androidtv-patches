package androidx.media3.extractor.heif;

import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class HeifExtractor implements Extractor {
    public static final int FILE_SIGNATURE_SEGMENT_LENGTH = 4;
    public static final int HEIF_FILE_SIGNATURE_PART_1 = 1718909296;
    public static final int HEIF_FILE_SIGNATURE_PART_2 = 1751476579;
    public final ParsableByteArray scratch = new ParsableByteArray(4);
    public final SingleSampleExtractor imageExtractor = new SingleSampleExtractor(-1, -1, MimeTypes.IMAGE_HEIF);

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.imageExtractor.init(extractorOutput);
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        return this.imageExtractor.read(extractorInput, positionHolder);
    }

    public final boolean readAndCompareFourBytes(ExtractorInput extractorInput, int i) throws IOException {
        this.scratch.reset(4);
        extractorInput.peekFully(this.scratch.data, 0, 4);
        return this.scratch.readUnsignedInt() == ((long) i);
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        this.imageExtractor.seek(j, j2);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.advancePeekPosition(4);
        return readAndCompareFourBytes(extractorInput, 1718909296) && readAndCompareFourBytes(extractorInput, 1751476579);
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
