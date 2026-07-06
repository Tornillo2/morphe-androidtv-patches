package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new AdtsExtractor$$ExternalSyntheticLambda0();
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    public static final int MAX_PACKET_SIZE = 2048;
    public static final int MAX_SNIFF_BYTES = 8192;
    public static final int NUM_FRAMES_FOR_AVERAGE_FRAME_SIZE = 1000;
    public int averageFrameSize;
    public ExtractorOutput extractorOutput;
    public long firstFramePosition;
    public long firstSampleTimestampUs;
    public final int flags;
    public boolean hasCalculatedAverageFrameSize;
    public boolean hasOutputSeekMap;
    public final ParsableByteArray packetBuffer;
    public final AdtsReader reader;
    public final ParsableByteArray scratch;
    public final ParsableBitArray scratchBits;
    public boolean startedPacket;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* JADX INFO: renamed from: $r8$lambda$6GRiHfq73DxGOaOHADb9-lQQUcs, reason: not valid java name */
    public static Extractor[] m458$r8$lambda$6GRiHfq73DxGOaOHADb9lQQUcs() {
        return new Extractor[]{new AdtsExtractor(0)};
    }

    public AdtsExtractor() {
        this(0);
    }

    private static int getBitrateFromFrameSize(int i, long j) {
        return (int) ((((long) i) * 8000000) / j);
    }

    private SeekMap getConstantBitrateSeekMap(long j, boolean z) {
        return new ConstantBitrateSeekMap(j, this.firstFramePosition, getBitrateFromFrameSize(this.averageFrameSize, this.reader.sampleDurationUs), this.averageFrameSize, z);
    }

    public final void calculateAverageFrameSize(ExtractorInput extractorInput) throws IOException {
        int bits;
        if (this.hasCalculatedAverageFrameSize) {
            return;
        }
        this.averageFrameSize = -1;
        extractorInput.resetPeekPosition();
        long j = 0;
        if (extractorInput.getPosition() == 0) {
            peekId3Header(extractorInput);
        }
        int i = 0;
        int i2 = 0;
        do {
            try {
                if (!extractorInput.peekFully(this.scratch.data, 0, 2, true)) {
                    break;
                }
                this.scratch.setPosition(0);
                if (!AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort())) {
                    break;
                }
                if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
                    break;
                }
                this.scratchBits.setPosition(14);
                bits = this.scratchBits.readBits(13);
                if (bits <= 6) {
                    this.hasCalculatedAverageFrameSize = true;
                    throw ParserException.createForMalformedContainer("Malformed ADTS stream", null);
                }
                j += (long) bits;
                i2++;
                if (i2 == 1000) {
                    break;
                }
            } catch (EOFException unused) {
            }
        } while (extractorInput.advancePeekPosition(bits - 6, true));
        i = i2;
        extractorInput.resetPeekPosition();
        if (i > 0) {
            this.averageFrameSize = (int) (j / ((long) i));
        } else {
            this.averageFrameSize = -1;
        }
        this.hasCalculatedAverageFrameSize = true;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
    }

    @RequiresNonNull({"extractorOutput"})
    public final void maybeOutputSeekMap(long j, boolean z) {
        if (this.hasOutputSeekMap) {
            return;
        }
        int i = this.flags;
        boolean z2 = (i & 1) != 0 && this.averageFrameSize > 0;
        if (z2 && this.reader.sampleDurationUs == -9223372036854775807L && !z) {
            return;
        }
        if (!z2 || this.reader.sampleDurationUs == -9223372036854775807L) {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        } else {
            this.extractorOutput.seekMap(getConstantBitrateSeekMap(j, (i & 2) != 0));
        }
        this.hasOutputSeekMap = true;
    }

    public final int peekId3Header(ExtractorInput extractorInput) throws IOException {
        int i = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.data, 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != 4801587) {
                break;
            }
            this.scratch.skipBytes(3);
            int synchSafeInt = this.scratch.readSynchSafeInt();
            i += synchSafeInt + 10;
            extractorInput.advancePeekPosition(synchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        if (this.firstFramePosition == -1) {
            this.firstFramePosition = i;
        }
        return i;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkStateNotNull(this.extractorOutput);
        long length = extractorInput.getLength();
        int i = this.flags;
        if ((i & 2) != 0 || ((i & 1) != 0 && length != -1)) {
            calculateAverageFrameSize(extractorInput);
        }
        int i2 = extractorInput.read(this.packetBuffer.data, 0, 2048);
        boolean z = i2 == -1;
        maybeOutputSeekMap(length, z);
        if (z) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(i2);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = j2;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        int iPeekId3Header = peekId3Header(extractorInput);
        int i = iPeekId3Header;
        int i2 = 0;
        int i3 = 0;
        do {
            extractorInput.peekFully(this.scratch.data, 0, 2);
            this.scratch.setPosition(0);
            if (AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort())) {
                i2++;
                if (i2 >= 4 && i3 > 188) {
                    return true;
                }
                extractorInput.peekFully(this.scratch.data, 0, 4);
                this.scratchBits.setPosition(14);
                int bits = this.scratchBits.readBits(13);
                if (bits <= 6) {
                    i++;
                    extractorInput.resetPeekPosition();
                    extractorInput.advancePeekPosition(i);
                } else {
                    extractorInput.advancePeekPosition(bits - 6);
                    i3 += bits;
                }
            } else {
                i++;
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i);
            }
            i2 = 0;
            i3 = 0;
        } while (i - iPeekId3Header < 8192);
        return false;
    }

    public AdtsExtractor(int i) {
        this.flags = (i & 2) != 0 ? i | 1 : i;
        this.reader = new AdtsReader(true, null);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1L;
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        this.scratch = parsableByteArray;
        byte[] bArr = parsableByteArray.data;
        this.scratchBits = new ParsableBitArray(bArr, bArr.length);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }
}
