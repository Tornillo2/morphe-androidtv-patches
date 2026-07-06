package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PsBinarySearchSeeker extends BinarySearchSeeker {
    public static final int MINIMUM_SEARCH_RANGE_BYTES = 1000;
    public static final long SEEK_TOLERANCE_US = 100000;
    public static final int TIMESTAMP_SEARCH_BYTES = 20000;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PsScrSeeker implements BinarySearchSeeker.TimestampSeeker {
        public final ParsableByteArray packetBuffer;
        public final TimestampAdjuster scrTimestampAdjuster;

        public static void skipToEndOfCurrentPack(ParsableByteArray parsableByteArray) {
            int iPeekIntAtPosition;
            int i = parsableByteArray.limit;
            if (parsableByteArray.bytesLeft() < 10) {
                parsableByteArray.setPosition(i);
                return;
            }
            parsableByteArray.skipBytes(9);
            int unsignedByte = parsableByteArray.readUnsignedByte() & 7;
            if (parsableByteArray.bytesLeft() < unsignedByte) {
                parsableByteArray.setPosition(i);
                return;
            }
            parsableByteArray.skipBytes(unsignedByte);
            if (parsableByteArray.bytesLeft() < 4) {
                parsableByteArray.setPosition(i);
                return;
            }
            if (PsBinarySearchSeeker.peekIntAtPosition(parsableByteArray.data, parsableByteArray.position) == 443) {
                parsableByteArray.skipBytes(4);
                int unsignedShort = parsableByteArray.readUnsignedShort();
                if (parsableByteArray.bytesLeft() < unsignedShort) {
                    parsableByteArray.setPosition(i);
                    return;
                }
                parsableByteArray.skipBytes(unsignedShort);
            }
            while (parsableByteArray.bytesLeft() >= 4 && (iPeekIntAtPosition = PsBinarySearchSeeker.peekIntAtPosition(parsableByteArray.data, parsableByteArray.position)) != 442 && iPeekIntAtPosition != 441 && (iPeekIntAtPosition >>> 8) == 1) {
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.bytesLeft() < 2) {
                    parsableByteArray.setPosition(i);
                    return;
                }
                parsableByteArray.setPosition(Math.min(parsableByteArray.limit, parsableByteArray.position + parsableByteArray.readUnsignedShort()));
            }
        }

        @Override // com.google.android.exoplayer2.extractor.BinarySearchSeeker.TimestampSeeker
        public void onSeekFinished() {
            ParsableByteArray parsableByteArray = this.packetBuffer;
            byte[] bArr = Util.EMPTY_BYTE_ARRAY;
            parsableByteArray.getClass();
            parsableByteArray.reset(bArr, bArr.length);
        }

        public final BinarySearchSeeker.TimestampSearchResult searchForScrValueInBuffer(ParsableByteArray parsableByteArray, long j, long j2) {
            int i = -1;
            long j3 = -9223372036854775807L;
            int i2 = -1;
            while (parsableByteArray.bytesLeft() >= 4) {
                if (PsBinarySearchSeeker.peekIntAtPosition(parsableByteArray.data, parsableByteArray.position) != 442) {
                    parsableByteArray.skipBytes(1);
                } else {
                    parsableByteArray.skipBytes(4);
                    long scrValueFromPack = PsDurationReader.readScrValueFromPack(parsableByteArray);
                    if (scrValueFromPack != -9223372036854775807L) {
                        long jAdjustTsTimestamp = this.scrTimestampAdjuster.adjustTsTimestamp(scrValueFromPack);
                        if (jAdjustTsTimestamp > j) {
                            return j3 == -9223372036854775807L ? BinarySearchSeeker.TimestampSearchResult.overestimatedResult(jAdjustTsTimestamp, j2) : BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j2 + ((long) i2));
                        }
                        if (100000 + jAdjustTsTimestamp > j) {
                            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j2 + ((long) parsableByteArray.position));
                        }
                        i2 = parsableByteArray.position;
                        j3 = jAdjustTsTimestamp;
                    }
                    skipToEndOfCurrentPack(parsableByteArray);
                    i = parsableByteArray.position;
                }
            }
            return j3 != -9223372036854775807L ? BinarySearchSeeker.TimestampSearchResult.underestimatedResult(j3, j2 + ((long) i)) : BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        @Override // com.google.android.exoplayer2.extractor.BinarySearchSeeker.TimestampSeeker
        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j) throws IOException {
            long position = extractorInput.getPosition();
            int iMin = (int) Math.min(20000L, extractorInput.getLength() - position);
            this.packetBuffer.reset(iMin);
            extractorInput.peekFully(this.packetBuffer.data, 0, iMin);
            return searchForScrValueInBuffer(this.packetBuffer, j, position);
        }

        public PsScrSeeker(TimestampAdjuster timestampAdjuster) {
            this.scrTimestampAdjuster = timestampAdjuster;
            this.packetBuffer = new ParsableByteArray();
        }
    }

    public PsBinarySearchSeeker(TimestampAdjuster timestampAdjuster, long j, long j2) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new PsScrSeeker(timestampAdjuster), j, 0L, j + 1, 0L, j2, 188L, 1000);
    }

    public static int peekIntAtPosition(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }
}
