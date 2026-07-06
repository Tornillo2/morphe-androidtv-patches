package com.google.android.exoplayer2.extractor.ts;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LatmReader implements ElementaryStreamReader {
    public static final int INITIAL_BUFFER_SIZE = 1024;
    public static final int STATE_FINDING_SYNC_1 = 0;
    public static final int STATE_FINDING_SYNC_2 = 1;
    public static final int STATE_READING_HEADER = 2;
    public static final int STATE_READING_SAMPLE = 3;
    public static final int SYNC_BYTE_FIRST = 86;
    public static final int SYNC_BYTE_SECOND = 224;
    public int audioMuxVersionA;
    public int bytesRead;
    public int channelCount;

    @Nullable
    public String codecs;
    public Format format;
    public String formatId;
    public int frameLengthType;

    @Nullable
    public final String language;
    public int numSubframes;
    public long otherDataLenBits;
    public boolean otherDataPresent;
    public TrackOutput output;
    public final ParsableBitArray sampleBitArray;
    public final ParsableByteArray sampleDataBuffer;
    public long sampleDurationUs;
    public int sampleRateHz;
    public int sampleSize;
    public int secondHeaderByte;
    public int state;
    public boolean streamMuxRead;
    public long timeUs;

    public LatmReader(@Nullable String str) {
        this.language = str;
        ParsableByteArray parsableByteArray = new ParsableByteArray(1024);
        this.sampleDataBuffer = parsableByteArray;
        byte[] bArr = parsableByteArray.data;
        this.sampleBitArray = new ParsableBitArray(bArr, bArr.length);
        this.timeUs = -9223372036854775807L;
    }

    public static long latmGetValue(ParsableBitArray parsableBitArray) {
        return parsableBitArray.readBits((parsableBitArray.readBits(2) + 1) * 8);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i == 1) {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    if ((unsignedByte & 224) == 224) {
                        this.secondHeaderByte = unsignedByte;
                        this.state = 2;
                    } else if (unsignedByte != 86) {
                        this.state = 0;
                    }
                } else if (i == 2) {
                    int unsignedByte2 = ((this.secondHeaderByte & (-225)) << 8) | parsableByteArray.readUnsignedByte();
                    this.sampleSize = unsignedByte2;
                    if (unsignedByte2 > this.sampleDataBuffer.data.length) {
                        resetBufferForSize(unsignedByte2);
                    }
                    this.bytesRead = 0;
                    this.state = 3;
                } else {
                    if (i != 3) {
                        throw new IllegalStateException();
                    }
                    int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    parsableByteArray.readBytes(this.sampleBitArray.data, this.bytesRead, iMin);
                    int i2 = this.bytesRead + iMin;
                    this.bytesRead = i2;
                    if (i2 == this.sampleSize) {
                        this.sampleBitArray.setPosition(0);
                        parseAudioMuxElement(this.sampleBitArray);
                        this.state = 0;
                    }
                }
            } else if (parsableByteArray.readUnsignedByte() == 86) {
                this.state = 1;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        this.output = extractorOutput.track(trackIdGenerator.trackId, 1);
        trackIdGenerator.maybeThrowUninitializedError();
        this.formatId = trackIdGenerator.formatId;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        if (j != -9223372036854775807L) {
            this.timeUs = j;
        }
    }

    @RequiresNonNull({"output"})
    public final void parseAudioMuxElement(ParsableBitArray parsableBitArray) throws ParserException {
        if (!parsableBitArray.readBit()) {
            this.streamMuxRead = true;
            parseStreamMuxConfig(parsableBitArray);
        } else if (!this.streamMuxRead) {
            return;
        }
        if (this.audioMuxVersionA != 0) {
            throw ParserException.createForMalformedContainer(null, null);
        }
        if (this.numSubframes != 0) {
            throw ParserException.createForMalformedContainer(null, null);
        }
        parsePayloadMux(parsableBitArray, parsePayloadLengthInfo(parsableBitArray));
        if (this.otherDataPresent) {
            parsableBitArray.skipBits((int) this.otherDataLenBits);
        }
    }

    public final int parseAudioSpecificConfig(ParsableBitArray parsableBitArray) throws ParserException {
        int iBitsLeft = parsableBitArray.bitsLeft();
        AacUtil.Config audioSpecificConfig = AacUtil.parseAudioSpecificConfig(parsableBitArray, true);
        this.codecs = audioSpecificConfig.codecs;
        this.sampleRateHz = audioSpecificConfig.sampleRateHz;
        this.channelCount = audioSpecificConfig.channelCount;
        return iBitsLeft - parsableBitArray.bitsLeft();
    }

    public final void parseFrameLength(ParsableBitArray parsableBitArray) {
        int bits = parsableBitArray.readBits(3);
        this.frameLengthType = bits;
        if (bits == 0) {
            parsableBitArray.skipBits(8);
            return;
        }
        if (bits == 1) {
            parsableBitArray.skipBits(9);
            return;
        }
        if (bits == 3 || bits == 4 || bits == 5) {
            parsableBitArray.skipBits(6);
        } else {
            if (bits != 6 && bits != 7) {
                throw new IllegalStateException();
            }
            parsableBitArray.skipBits(1);
        }
    }

    public final int parsePayloadLengthInfo(ParsableBitArray parsableBitArray) throws ParserException {
        int bits;
        if (this.frameLengthType != 0) {
            throw ParserException.createForMalformedContainer(null, null);
        }
        int i = 0;
        do {
            bits = parsableBitArray.readBits(8);
            i += bits;
        } while (bits == 255);
        return i;
    }

    @RequiresNonNull({"output"})
    public final void parsePayloadMux(ParsableBitArray parsableBitArray, int i) {
        int position = parsableBitArray.getPosition();
        if ((position & 7) == 0) {
            this.sampleDataBuffer.setPosition(position >> 3);
        } else {
            parsableBitArray.readBits(this.sampleDataBuffer.data, 0, i * 8);
            this.sampleDataBuffer.setPosition(0);
        }
        this.output.sampleData(this.sampleDataBuffer, i);
        long j = this.timeUs;
        if (j != -9223372036854775807L) {
            this.output.sampleMetadata(j, 1, i, 0, null);
            this.timeUs += this.sampleDurationUs;
        }
    }

    @RequiresNonNull({"output"})
    public final void parseStreamMuxConfig(ParsableBitArray parsableBitArray) throws ParserException {
        boolean bit;
        int bits = parsableBitArray.readBits(1);
        int bits2 = bits == 1 ? parsableBitArray.readBits(1) : 0;
        this.audioMuxVersionA = bits2;
        if (bits2 != 0) {
            throw ParserException.createForMalformedContainer(null, null);
        }
        if (bits == 1) {
            latmGetValue(parsableBitArray);
        }
        if (!parsableBitArray.readBit()) {
            throw ParserException.createForMalformedContainer(null, null);
        }
        this.numSubframes = parsableBitArray.readBits(6);
        int bits3 = parsableBitArray.readBits(4);
        int bits4 = parsableBitArray.readBits(3);
        if (bits3 != 0 || bits4 != 0) {
            throw ParserException.createForMalformedContainer(null, null);
        }
        if (bits == 0) {
            int position = parsableBitArray.getPosition();
            int audioSpecificConfig = parseAudioSpecificConfig(parsableBitArray);
            parsableBitArray.setPosition(position);
            byte[] bArr = new byte[(audioSpecificConfig + 7) / 8];
            parsableBitArray.readBits(bArr, 0, audioSpecificConfig);
            Format.Builder builder = new Format.Builder();
            builder.id = this.formatId;
            builder.sampleMimeType = "audio/mp4a-latm";
            builder.codecs = this.codecs;
            builder.channelCount = this.channelCount;
            builder.sampleRate = this.sampleRateHz;
            builder.initializationData = Collections.singletonList(bArr);
            builder.language = this.language;
            Format format = new Format(builder);
            if (!format.equals(this.format)) {
                this.format = format;
                this.sampleDurationUs = 1024000000 / ((long) format.sampleRate);
                this.output.format(format);
            }
        } else {
            parsableBitArray.skipBits(((int) latmGetValue(parsableBitArray)) - parseAudioSpecificConfig(parsableBitArray));
        }
        parseFrameLength(parsableBitArray);
        boolean bit2 = parsableBitArray.readBit();
        this.otherDataPresent = bit2;
        this.otherDataLenBits = 0L;
        if (bit2) {
            if (bits == 1) {
                this.otherDataLenBits = latmGetValue(parsableBitArray);
            } else {
                do {
                    bit = parsableBitArray.readBit();
                    this.otherDataLenBits = (this.otherDataLenBits << 8) + ((long) parsableBitArray.readBits(8));
                } while (bit);
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(8);
        }
    }

    public final void resetBufferForSize(int i) {
        this.sampleDataBuffer.reset(i);
        ParsableBitArray parsableBitArray = this.sampleBitArray;
        byte[] bArr = this.sampleDataBuffer.data;
        parsableBitArray.getClass();
        parsableBitArray.reset(bArr, bArr.length);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.timeUs = -9223372036854775807L;
        this.streamMuxRead = false;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
