package com.google.android.exoplayer2.extractor.ts;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.Ac4Util;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Ac4Reader implements ElementaryStreamReader {
    public static final int STATE_FINDING_SYNC = 0;
    public static final int STATE_READING_HEADER = 1;
    public static final int STATE_READING_SAMPLE = 2;
    public int bytesRead;
    public Format format;
    public String formatId;
    public boolean hasCRC;
    public final ParsableBitArray headerScratchBits;
    public final ParsableByteArray headerScratchBytes;

    @Nullable
    public final String language;
    public boolean lastByteWasAC;
    public TrackOutput output;
    public long sampleDurationUs;
    public int sampleSize;
    public int state;
    public long timeUs;

    public Ac4Reader() {
        this(null);
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i2 = this.bytesRead + iMin;
        this.bytesRead = i2;
        return i2 == i;
    }

    @RequiresNonNull({"output"})
    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        Ac4Util.SyncFrameInfo ac4SyncframeInfo = Ac4Util.parseAc4SyncframeInfo(this.headerScratchBits);
        Format format = this.format;
        if (format == null || 2 != format.channelCount || ac4SyncframeInfo.sampleRate != format.sampleRate || !"audio/ac4".equals(format.sampleMimeType)) {
            Format.Builder builder = new Format.Builder();
            builder.id = this.formatId;
            builder.sampleMimeType = "audio/ac4";
            builder.channelCount = 2;
            builder.sampleRate = ac4SyncframeInfo.sampleRate;
            builder.language = this.language;
            Format format2 = new Format(builder);
            this.format = format2;
            this.output.format(format2);
        }
        this.sampleSize = ac4SyncframeInfo.frameSize;
        this.sampleDurationUs = (((long) ac4SyncframeInfo.sampleCount) * 1000000) / ((long) this.format.sampleRate);
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        int unsignedByte;
        while (true) {
            if (parsableByteArray.bytesLeft() <= 0) {
                return false;
            }
            if (this.lastByteWasAC) {
                unsignedByte = parsableByteArray.readUnsignedByte();
                this.lastByteWasAC = unsignedByte == 172;
                if (unsignedByte == 64 || unsignedByte == 65) {
                    break;
                }
            } else {
                this.lastByteWasAC = parsableByteArray.readUnsignedByte() == 172;
            }
        }
        this.hasCRC = unsignedByte == 65;
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, iMin);
                        int i2 = this.bytesRead + iMin;
                        this.bytesRead = i2;
                        int i3 = this.sampleSize;
                        if (i2 == i3) {
                            long j = this.timeUs;
                            if (j != -9223372036854775807L) {
                                this.output.sampleMetadata(j, 1, i3, 0, null);
                                this.timeUs += this.sampleDurationUs;
                            }
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 16)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 16);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
                byte[] bArr = this.headerScratchBytes.data;
                bArr[0] = -84;
                bArr[1] = (byte) (this.hasCRC ? 65 : 64);
                this.bytesRead = 2;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        this.formatId = trackIdGenerator.formatId;
        trackIdGenerator.maybeThrowUninitializedError();
        this.output = extractorOutput.track(trackIdGenerator.trackId, 1);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        if (j != -9223372036854775807L) {
            this.timeUs = j;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = -9223372036854775807L;
    }

    public Ac4Reader(@Nullable String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[16], 16);
        this.headerScratchBits = parsableBitArray;
        this.headerScratchBytes = new ParsableByteArray(parsableBitArray.data);
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = -9223372036854775807L;
        this.language = str;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
