package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class FlacFrameReader {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SampleNumberHolder {
        public long sampleNumber;
    }

    public static boolean checkAndReadBlockSizeSamples(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i) {
        int frameBlockSizeSamplesFromKey = readFrameBlockSizeSamplesFromKey(parsableByteArray, i);
        return frameBlockSizeSamplesFromKey != -1 && frameBlockSizeSamplesFromKey <= flacStreamMetadata.maxBlockSizeSamples;
    }

    public static boolean checkAndReadCrc(ParsableByteArray parsableByteArray, int i) {
        return parsableByteArray.readUnsignedByte() == Util.crc8(parsableByteArray.data, i, parsableByteArray.position - 1, 0);
    }

    public static boolean checkAndReadFirstSampleNumber(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, boolean z, SampleNumberHolder sampleNumberHolder) {
        try {
            long utf8EncodedLong = parsableByteArray.readUtf8EncodedLong();
            if (!z) {
                utf8EncodedLong *= (long) flacStreamMetadata.maxBlockSizeSamples;
            }
            sampleNumberHolder.sampleNumber = utf8EncodedLong;
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean checkAndReadFrameHeader(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i, SampleNumberHolder sampleNumberHolder) {
        int i2 = parsableByteArray.position;
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long j = unsignedInt >>> 16;
        if (j != i) {
            return false;
        }
        return checkChannelAssignment((int) (15 & (unsignedInt >> 4)), flacStreamMetadata) && checkBitsPerSample((int) ((unsignedInt >> 1) & 7), flacStreamMetadata) && !(((unsignedInt & 1) > 1L ? 1 : ((unsignedInt & 1) == 1L ? 0 : -1)) == 0) && checkAndReadFirstSampleNumber(parsableByteArray, flacStreamMetadata, ((j & 1) > 1L ? 1 : ((j & 1) == 1L ? 0 : -1)) == 0, sampleNumberHolder) && checkAndReadBlockSizeSamples(parsableByteArray, flacStreamMetadata, (int) ((unsignedInt >> 12) & 15)) && checkAndReadSampleRate(parsableByteArray, flacStreamMetadata, (int) ((unsignedInt >> 8) & 15)) && checkAndReadCrc(parsableByteArray, i2);
    }

    public static boolean checkAndReadSampleRate(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i) {
        int i2 = flacStreamMetadata.sampleRate;
        if (i == 0) {
            return true;
        }
        if (i <= 11) {
            return i == flacStreamMetadata.sampleRateLookupKey;
        }
        if (i == 12) {
            return parsableByteArray.readUnsignedByte() * 1000 == i2;
        }
        if (i <= 14) {
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if (i == 14) {
                unsignedShort *= 10;
            }
            if (unsignedShort == i2) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBitsPerSample(int i, FlacStreamMetadata flacStreamMetadata) {
        return i == 0 || i == flacStreamMetadata.bitsPerSampleLookupKey;
    }

    public static boolean checkChannelAssignment(int i, FlacStreamMetadata flacStreamMetadata) {
        return i <= 7 ? i == flacStreamMetadata.channels - 1 : i <= 10 && flacStreamMetadata.channels == 2;
    }

    public static boolean checkFrameHeaderFromPeek(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata, int i, SampleNumberHolder sampleNumberHolder) throws IOException {
        long peekPosition = extractorInput.getPeekPosition();
        byte[] bArr = new byte[2];
        extractorInput.peekFully(bArr, 0, 2);
        if ((((bArr[0] & 255) << 8) | (bArr[1] & 255)) != i) {
            extractorInput.resetPeekPosition();
            extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
            return false;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        System.arraycopy(bArr, 0, parsableByteArray.data, 0, 2);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.data, 2, 14));
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
        return checkAndReadFrameHeader(parsableByteArray, flacStreamMetadata, i, sampleNumberHolder);
    }

    public static long getFirstSampleNumber(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata) throws IOException {
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(1);
        byte[] bArr = new byte[1];
        extractorInput.peekFully(bArr, 0, 1);
        boolean z = (bArr[0] & 1) == 1;
        extractorInput.advancePeekPosition(2);
        int i = z ? 7 : 6;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.data, 0, i));
        extractorInput.resetPeekPosition();
        SampleNumberHolder sampleNumberHolder = new SampleNumberHolder();
        if (checkAndReadFirstSampleNumber(parsableByteArray, flacStreamMetadata, z, sampleNumberHolder)) {
            return sampleNumberHolder.sampleNumber;
        }
        throw ParserException.createForMalformedContainer(null, null);
    }

    public static int readFrameBlockSizeSamplesFromKey(ParsableByteArray parsableByteArray, int i) {
        switch (i) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i - 2);
            case 6:
                return parsableByteArray.readUnsignedByte() + 1;
            case 7:
                return parsableByteArray.readUnsignedShort() + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i - 8);
            default:
                return -1;
        }
    }
}
