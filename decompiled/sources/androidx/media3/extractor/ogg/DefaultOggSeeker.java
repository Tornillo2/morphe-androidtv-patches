package androidx.media3.extractor.ogg;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultOggSeeker implements OggSeeker {
    public static final int DEFAULT_OFFSET = 30000;
    public static final int MATCH_BYTE_RANGE = 100000;
    public static final int MATCH_RANGE = 72000;
    public static final int STATE_IDLE = 4;
    public static final int STATE_READ_LAST_PAGE = 1;
    public static final int STATE_SEEK = 2;
    public static final int STATE_SEEK_TO_END = 0;
    public static final int STATE_SKIP = 3;
    public long end;
    public long endGranule;
    public final OggPageHeader pageHeader;
    public final long payloadEndPosition;
    public final long payloadStartPosition;
    public long positionBeforeSeekToEnd;
    public long start;
    public long startGranule;
    public int state;
    public final StreamReader streamReader;
    public long targetGranule;
    public long totalGranules;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class OggSeekMap implements SeekMap {
        public OggSeekMap() {
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            DefaultOggSeeker defaultOggSeeker = DefaultOggSeeker.this;
            return defaultOggSeeker.streamReader.convertGranuleToTime(defaultOggSeeker.totalGranules);
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            long jConvertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j);
            long j2 = DefaultOggSeeker.this.payloadStartPosition;
            BigInteger bigIntegerValueOf = BigInteger.valueOf(jConvertTimeToGranule);
            DefaultOggSeeker defaultOggSeeker = DefaultOggSeeker.this;
            long jLongValue = (bigIntegerValueOf.multiply(BigInteger.valueOf(defaultOggSeeker.payloadEndPosition - defaultOggSeeker.payloadStartPosition)).divide(BigInteger.valueOf(DefaultOggSeeker.this.totalGranules)).longValue() + j2) - 30000;
            DefaultOggSeeker defaultOggSeeker2 = DefaultOggSeeker.this;
            SeekPoint seekPoint = new SeekPoint(j, Util.constrainValue(jLongValue, defaultOggSeeker2.payloadStartPosition, defaultOggSeeker2.payloadEndPosition - 1));
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    public DefaultOggSeeker(StreamReader streamReader, long j, long j2, long j3, long j4, boolean z) {
        Assertions.checkArgument(j >= 0 && j2 > j);
        this.streamReader = streamReader;
        this.payloadStartPosition = j;
        this.payloadEndPosition = j2;
        if (j3 == j2 - j || z) {
            this.totalGranules = j4;
            this.state = 4;
        } else {
            this.state = 0;
        }
        this.pageHeader = new OggPageHeader();
    }

    public final long getNextSeekPosition(ExtractorInput extractorInput) throws IOException {
        if (this.start == this.end) {
            return -1L;
        }
        long position = extractorInput.getPosition();
        if (!this.pageHeader.skipToNextPage(extractorInput, this.end)) {
            long j = this.start;
            if (j != position) {
                return j;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput, false);
        extractorInput.resetPeekPosition();
        long j2 = this.targetGranule;
        OggPageHeader oggPageHeader = this.pageHeader;
        long j3 = oggPageHeader.granulePosition;
        long j4 = j2 - j3;
        int i = oggPageHeader.headerSize + oggPageHeader.bodySize;
        if (0 <= j4 && j4 < 72000) {
            return -1L;
        }
        if (j4 < 0) {
            this.end = position;
            this.endGranule = j3;
        } else {
            this.start = extractorInput.getPosition() + ((long) i);
            this.startGranule = this.pageHeader.granulePosition;
        }
        long j5 = this.end;
        long j6 = this.start;
        if (j5 - j6 < 100000) {
            this.end = j6;
            return j6;
        }
        long position2 = extractorInput.getPosition() - (((long) i) * (j4 <= 0 ? 2L : 1L));
        long j7 = this.end;
        long j8 = this.start;
        return Util.constrainValue((((j7 - j8) * j4) / (this.endGranule - this.startGranule)) + position2, j8, j7 - 1);
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public long read(ExtractorInput extractorInput) throws IOException {
        int i = this.state;
        if (i == 0) {
            long position = extractorInput.getPosition();
            this.positionBeforeSeekToEnd = position;
            this.state = 1;
            long j = this.payloadEndPosition - 65307;
            if (j > position) {
                return j;
            }
        } else if (i != 1) {
            if (i == 2) {
                long nextSeekPosition = getNextSeekPosition(extractorInput);
                if (nextSeekPosition != -1) {
                    return nextSeekPosition;
                }
                this.state = 3;
            } else if (i != 3) {
                if (i == 4) {
                    return -1L;
                }
                throw new IllegalStateException();
            }
            skipToPageOfTargetGranule(extractorInput);
            this.state = 4;
            return -(this.startGranule + 2);
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 4;
        return this.positionBeforeSeekToEnd;
    }

    @VisibleForTesting
    public long readGranuleOfLastPage(ExtractorInput extractorInput) throws IOException {
        this.pageHeader.reset();
        if (!this.pageHeader.skipToNextPage(extractorInput, -1L)) {
            throw new EOFException();
        }
        this.pageHeader.populate(extractorInput, false);
        OggPageHeader oggPageHeader = this.pageHeader;
        extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
        long j = this.pageHeader.granulePosition;
        while (true) {
            OggPageHeader oggPageHeader2 = this.pageHeader;
            if ((oggPageHeader2.type & 4) == 4 || !oggPageHeader2.skipToNextPage(extractorInput, -1L) || extractorInput.getPosition() >= this.payloadEndPosition || !this.pageHeader.populate(extractorInput, true)) {
                break;
            }
            OggPageHeader oggPageHeader3 = this.pageHeader;
            if (!ExtractorUtil.skipFullyQuietly(extractorInput, oggPageHeader3.headerSize + oggPageHeader3.bodySize)) {
                break;
            }
            j = this.pageHeader.granulePosition;
        }
        return j;
    }

    public final void skipToPageOfTargetGranule(ExtractorInput extractorInput) throws IOException {
        while (true) {
            this.pageHeader.skipToNextPage(extractorInput);
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            if (oggPageHeader.granulePosition > this.targetGranule) {
                extractorInput.resetPeekPosition();
                return;
            } else {
                extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
                this.start = extractorInput.getPosition();
                this.startGranule = this.pageHeader.granulePosition;
            }
        }
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public void startSeek(long j) {
        this.targetGranule = Util.constrainValue(j, 0L, this.totalGranules - 1);
        this.state = 2;
        this.start = this.payloadStartPosition;
        this.end = this.payloadEndPosition;
        this.startGranule = 0L;
        this.endGranule = this.totalGranules;
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    @Nullable
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }
}
