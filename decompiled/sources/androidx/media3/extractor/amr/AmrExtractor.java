package androidx.media3.extractor.amr;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import com.google.common.base.Charsets;
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
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
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

    /* JADX INFO: renamed from: $r8$lambda$SJh1mMR68eVuGS4--i-rR_1BeE4, reason: not valid java name */
    public static Extractor[] m177$r8$lambda$SJh1mMR68eVuGS4irR_1BeE4() {
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

    @Override // androidx.media3.extractor.Extractor
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
        builder.sampleMimeType = MimeTypes.normalizeMimeType(str);
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

    @Override // androidx.media3.extractor.Extractor
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

    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"trackOutput"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int readSample(androidx.media3.extractor.ExtractorInput r9) throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r8.currentSampleBytesRemaining
            r1 = 1
            r2 = -1
            if (r0 != 0) goto L27
            int r0 = r8.peekNextSampleSize(r9)     // Catch: java.io.EOFException -> L31
            r8.currentSampleSize = r0     // Catch: java.io.EOFException -> L31
            r8.currentSampleBytesRemaining = r0
            int r0 = r8.firstSampleSize
            if (r0 != r2) goto L1c
            long r3 = r9.getPosition()
            r8.firstSamplePosition = r3
            int r0 = r8.currentSampleSize
            r8.firstSampleSize = r0
        L1c:
            int r0 = r8.firstSampleSize
            int r3 = r8.currentSampleSize
            if (r0 != r3) goto L27
            int r0 = r8.numSamplesWithSameSize
            int r0 = r0 + r1
            r8.numSamplesWithSameSize = r0
        L27:
            androidx.media3.extractor.TrackOutput r0 = r8.trackOutput
            int r3 = r8.currentSampleBytesRemaining
            int r9 = r0.sampleData(r9, r3, r1)
            if (r9 != r2) goto L32
        L31:
            return r2
        L32:
            int r0 = r8.currentSampleBytesRemaining
            int r0 = r0 - r9
            r8.currentSampleBytesRemaining = r0
            r9 = 0
            if (r0 <= 0) goto L3b
            return r9
        L3b:
            androidx.media3.extractor.TrackOutput r1 = r8.trackOutput
            long r2 = r8.timeOffsetUs
            long r4 = r8.currentSampleTimeUs
            long r2 = r2 + r4
            int r5 = r8.currentSampleSize
            r6 = 0
            r7 = 0
            r4 = 1
            r1.sampleMetadata(r2, r4, r5, r6, r7)
            long r0 = r8.currentSampleTimeUs
            r2 = 20000(0x4e20, double:9.8813E-320)
            long r0 = r0 + r2
            r8.currentSampleTimeUs = r0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.amr.AmrExtractor.readSample(androidx.media3.extractor.ExtractorInput):int");
    }

    @Override // androidx.media3.extractor.Extractor
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

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return readAmrHeader(extractorInput);
    }

    public AmrExtractor(int i) {
        this.flags = (i & 2) != 0 ? i | 1 : i;
        this.scratch = new byte[1];
        this.firstSampleSize = -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
