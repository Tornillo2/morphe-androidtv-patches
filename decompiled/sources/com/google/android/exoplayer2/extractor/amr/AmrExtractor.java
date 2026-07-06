package com.google.android.exoplayer2.extractor.amr;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Charsets;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AmrExtractor implements Extractor {
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    public static final int MAX_FRAME_SIZE_BYTES;
    public static final int NUM_SAME_SIZE_CONSTANT_BIT_RATE_THRESHOLD = 20;
    public static final int SAMPLE_RATE_NB = 8000;
    public static final int SAMPLE_RATE_WB = 16000;
    public static final int SAMPLE_TIME_PER_FRAME_US = 20000;
    public static final int[] frameSizeBytesByTypeWb;
    public int currentSampleBytesRemaining;
    public int currentSampleSize;
    public long currentSampleTimeUs;
    public ExtractorOutput extractorOutput;
    public long firstSamplePosition;
    public int firstSampleSize;
    public final int flags;
    public boolean hasOutputFormat;
    public boolean hasOutputSeekMap;
    public boolean isWideBand;
    public int numSamplesWithSameSize;
    public final byte[] scratch;
    public SeekMap seekMap;
    public long timeOffsetUs;
    public TrackOutput trackOutput;
    public static final ExtractorsFactory FACTORY = new AmrExtractor$$ExternalSyntheticLambda0();
    public static final int[] frameSizeBytesByTypeNb = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    public static final byte[] amrSignatureNb = Util.getUtf8Bytes("#!AMR\n");
    public static final byte[] amrSignatureWb = "#!AMR-WB\n".getBytes(Charsets.UTF_8);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static Extractor[] $r8$lambda$2GugIB3gTFjbkdLe5Ec_orWdrkw() {
        return new Extractor[]{new AmrExtractor(0)};
    }

    static {
        int[] iArr = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
        frameSizeBytesByTypeWb = iArr;
        MAX_FRAME_SIZE_BYTES = iArr[8];
    }

    public AmrExtractor() {
        this(0);
    }

    public static byte[] amrSignatureNb() {
        byte[] bArr = amrSignatureNb;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public static byte[] amrSignatureWb() {
        byte[] bArr = amrSignatureWb;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public static int frameSizeBytesByTypeNb(int i) {
        return frameSizeBytesByTypeNb[i];
    }

    public static int frameSizeBytesByTypeWb(int i) {
        return frameSizeBytesByTypeWb[i];
    }

    public static int getBitrateFromFrameSize(int i, long j) {
        return (int) ((((long) i) * 8000000) / j);
    }

    public static boolean peekAmrSignature(ExtractorInput extractorInput, byte[] bArr) throws IOException {
        extractorInput.resetPeekPosition();
        byte[] bArr2 = new byte[bArr.length];
        extractorInput.peekFully(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    @EnsuresNonNull({"extractorOutput", "trackOutput"})
    public final void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    public final SeekMap getConstantBitrateSeekMap(long j, boolean z) {
        return new ConstantBitrateSeekMap(j, this.firstSamplePosition, getBitrateFromFrameSize(this.firstSampleSize, 20000L), this.firstSampleSize, z);
    }

    public final int getFrameSizeInBytes(int i) throws ParserException {
        if (isValidFrameType(i)) {
            return this.isWideBand ? frameSizeBytesByTypeWb[i] : frameSizeBytesByTypeNb[i];
        }
        StringBuilder sb = new StringBuilder("Illegal AMR ");
        sb.append(this.isWideBand ? "WB" : "NB");
        sb.append(" frame type ");
        sb.append(i);
        throw ParserException.createForMalformedContainer(sb.toString(), null);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 1);
        extractorOutput.endTracks();
    }

    public final boolean isNarrowBandValidFrameType(int i) {
        if (this.isWideBand) {
            return false;
        }
        return i < 12 || i > 14;
    }

    public final boolean isValidFrameType(int i) {
        if (i < 0 || i > 15) {
            return false;
        }
        return isWideBandValidFrameType(i) || isNarrowBandValidFrameType(i);
    }

    public final boolean isWideBandValidFrameType(int i) {
        if (this.isWideBand) {
            return i < 10 || i > 13;
        }
        return false;
    }

    @RequiresNonNull({"trackOutput"})
    public final void maybeOutputFormat() {
        if (this.hasOutputFormat) {
            return;
        }
        this.hasOutputFormat = true;
        boolean z = this.isWideBand;
        String str = z ? "audio/amr-wb" : "audio/3gpp";
        int i = z ? 16000 : 8000;
        TrackOutput trackOutput = this.trackOutput;
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = str;
        builder.maxInputSize = MAX_FRAME_SIZE_BYTES;
        builder.channelCount = 1;
        builder.sampleRate = i;
        trackOutput.format(new Format(builder));
    }

    @RequiresNonNull({"extractorOutput"})
    public final void maybeOutputSeekMap(long j, int i) {
        int i2;
        if (this.hasOutputSeekMap) {
            return;
        }
        int i3 = this.flags;
        if ((i3 & 1) == 0 || j == -1 || !((i2 = this.firstSampleSize) == -1 || i2 == this.currentSampleSize)) {
            SeekMap.Unseekable unseekable = new SeekMap.Unseekable(-9223372036854775807L);
            this.seekMap = unseekable;
            this.extractorOutput.seekMap(unseekable);
            this.hasOutputSeekMap = true;
            return;
        }
        if (this.numSamplesWithSameSize >= 20 || i == -1) {
            SeekMap constantBitrateSeekMap = getConstantBitrateSeekMap(j, (i3 & 2) != 0);
            this.seekMap = constantBitrateSeekMap;
            this.extractorOutput.seekMap(constantBitrateSeekMap);
            this.hasOutputSeekMap = true;
        }
    }

    public final int peekNextSampleSize(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.scratch, 0, 1);
        byte b = this.scratch[0];
        if ((b & 131) <= 0) {
            return getFrameSizeInBytes((b >> 3) & 15);
        }
        throw ParserException.createForMalformedContainer("Invalid padding bits for frame header " + ((int) b), null);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        if (extractorInput.getPosition() == 0 && !readAmrHeader(extractorInput)) {
            throw ParserException.createForMalformedContainer("Could not find AMR header.", null);
        }
        maybeOutputFormat();
        int sample = readSample(extractorInput);
        maybeOutputSeekMap(extractorInput.getLength(), sample);
        return sample;
    }

    public final boolean readAmrHeader(ExtractorInput extractorInput) throws IOException {
        byte[] bArr = amrSignatureNb;
        if (peekAmrSignature(extractorInput, bArr)) {
            this.isWideBand = false;
            extractorInput.skipFully(bArr.length);
            return true;
        }
        byte[] bArr2 = amrSignatureWb;
        if (!peekAmrSignature(extractorInput, bArr2)) {
            return false;
        }
        this.isWideBand = true;
        extractorInput.skipFully(bArr2.length);
        return true;
    }

    @RequiresNonNull({"trackOutput"})
    public final int readSample(ExtractorInput extractorInput) throws IOException {
        if (this.currentSampleBytesRemaining == 0) {
            try {
                int iPeekNextSampleSize = peekNextSampleSize(extractorInput);
                this.currentSampleSize = iPeekNextSampleSize;
                this.currentSampleBytesRemaining = iPeekNextSampleSize;
                if (this.firstSampleSize == -1) {
                    this.firstSamplePosition = extractorInput.getPosition();
                    this.firstSampleSize = this.currentSampleSize;
                }
                if (this.firstSampleSize == this.currentSampleSize) {
                    this.numSamplesWithSameSize++;
                }
            } catch (EOFException unused) {
                return -1;
            }
        }
        int iSampleData = this.trackOutput.sampleData((DataReader) extractorInput, this.currentSampleBytesRemaining, true);
        if (iSampleData == -1) {
            return -1;
        }
        int i = this.currentSampleBytesRemaining - iSampleData;
        this.currentSampleBytesRemaining = i;
        if (i > 0) {
            return 0;
        }
        this.trackOutput.sampleMetadata(this.timeOffsetUs + this.currentSampleTimeUs, 1, this.currentSampleSize, 0, null);
        this.currentSampleTimeUs += 20000;
        return 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.currentSampleTimeUs = 0L;
        this.currentSampleSize = 0;
        this.currentSampleBytesRemaining = 0;
        if (j != 0) {
            SeekMap seekMap = this.seekMap;
            if (seekMap instanceof ConstantBitrateSeekMap) {
                this.timeOffsetUs = ((ConstantBitrateSeekMap) seekMap).getTimeUsAtPosition(j);
                return;
            }
        }
        this.timeOffsetUs = 0L;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return readAmrHeader(extractorInput);
    }

    public AmrExtractor(int i) {
        this.flags = (i & 2) != 0 ? i | 1 : i;
        this.scratch = new byte[1];
        this.firstSampleSize = -1;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }
}
