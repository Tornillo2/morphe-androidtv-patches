package androidx.media3.extractor.flac;

import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacStreamMetadata;
import j$.util.Objects;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class FlacBinarySearchSeeker extends BinarySearchSeeker {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FlacTimestampSeeker implements BinarySearchSeeker.TimestampSeeker {
        public final FlacStreamMetadata flacStreamMetadata;
        public final int frameStartMarker;
        public final FlacFrameReader.SampleNumberHolder sampleNumberHolder;

        public final long findNextFrame(ExtractorInput extractorInput) throws IOException {
            while (extractorInput.getPeekPosition() < extractorInput.getLength() - 6 && !FlacFrameReader.checkFrameHeaderFromPeek(extractorInput, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder)) {
                extractorInput.advancePeekPosition(1);
            }
            if (extractorInput.getPeekPosition() < extractorInput.getLength() - 6) {
                return this.sampleNumberHolder.sampleNumber;
            }
            extractorInput.advancePeekPosition((int) (extractorInput.getLength() - extractorInput.getPeekPosition()));
            return this.flacStreamMetadata.totalSamples;
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j) throws IOException {
            long position = extractorInput.getPosition();
            long jFindNextFrame = findNextFrame(extractorInput);
            long peekPosition = extractorInput.getPeekPosition();
            extractorInput.advancePeekPosition(Math.max(6, this.flacStreamMetadata.minFrameSize));
            long jFindNextFrame2 = findNextFrame(extractorInput);
            return (jFindNextFrame > j || jFindNextFrame2 <= j) ? jFindNextFrame2 <= j ? BinarySearchSeeker.TimestampSearchResult.underestimatedResult(jFindNextFrame2, extractorInput.getPeekPosition()) : BinarySearchSeeker.TimestampSearchResult.overestimatedResult(jFindNextFrame, position) : BinarySearchSeeker.TimestampSearchResult.targetFoundResult(peekPosition);
        }

        public FlacTimestampSeeker(FlacStreamMetadata flacStreamMetadata, int i) {
            this.flacStreamMetadata = flacStreamMetadata;
            this.frameStartMarker = i;
            this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public /* synthetic */ void onSeekFinished() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlacBinarySearchSeeker(final FlacStreamMetadata flacStreamMetadata, int i, long j, long j2) {
        super(new BinarySearchSeeker.SeekTimestampConverter() { // from class: androidx.media3.extractor.flac.FlacBinarySearchSeeker$$ExternalSyntheticLambda0
            @Override // androidx.media3.extractor.BinarySearchSeeker.SeekTimestampConverter
            public final long timeUsToTargetTime(long j3) {
                return flacStreamMetadata.getSampleNumber(j3);
            }
        }, new FlacTimestampSeeker(flacStreamMetadata, i), flacStreamMetadata.getDurationUs(), 0L, flacStreamMetadata.totalSamples, j, j2, flacStreamMetadata.getApproxBytesPerFrame(), Math.max(6, flacStreamMetadata.minFrameSize));
        Objects.requireNonNull(flacStreamMetadata);
    }
}
