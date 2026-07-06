package com.google.android.exoplayer2.extractor.ts;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DtsReader implements ElementaryStreamReader {
    public static final int HEADER_SIZE = 18;
    public static final int STATE_FINDING_SYNC = 0;
    public static final int STATE_READING_HEADER = 1;
    public static final int STATE_READING_SAMPLE = 2;
    public int bytesRead;
    public Format format;
    public String formatId;

    @Nullable
    public final String language;
    public TrackOutput output;
    public long sampleDurationUs;
    public int sampleSize;
    public int syncBytes;
    public final ParsableByteArray headerScratchBytes = new ParsableByteArray(new byte[18]);
    public int state = 0;
    public long timeUs = -9223372036854775807L;

    public DtsReader(@Nullable String str) {
        this.language = str;
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
        byte[] bArr = this.headerScratchBytes.data;
        if (this.format == null) {
            Format dtsFormat = DtsUtil.parseDtsFormat(bArr, this.formatId, this.language, null);
            this.format = dtsFormat;
            this.output.format(dtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(bArr);
        this.sampleDurationUs = (int) ((((long) DtsUtil.parseDtsAudioSampleCount(bArr)) * 1000000) / ((long) this.format.sampleRate));
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.syncBytes << 8;
            this.syncBytes = i;
            int unsignedByte = i | parsableByteArray.readUnsignedByte();
            this.syncBytes = unsignedByte;
            if (DtsUtil.isSyncWord(unsignedByte)) {
                byte[] bArr = this.headerScratchBytes.data;
                int i2 = this.syncBytes;
                bArr[0] = (byte) ((i2 >> 24) & 255);
                bArr[1] = (byte) ((i2 >> 16) & 255);
                bArr[2] = (byte) ((i2 >> 8) & 255);
                bArr[3] = (byte) (i2 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException();
                    }
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
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 18)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 18);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
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
        this.syncBytes = 0;
        this.timeUs = -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
