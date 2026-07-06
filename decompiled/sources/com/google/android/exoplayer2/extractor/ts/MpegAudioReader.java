package com.google.android.exoplayer2.extractor.ts;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MpegAudioReader implements ElementaryStreamReader {
    public static final int HEADER_SIZE = 4;
    public static final int STATE_FINDING_HEADER = 0;
    public static final int STATE_READING_FRAME = 2;
    public static final int STATE_READING_HEADER = 1;
    public String formatId;
    public int frameBytesRead;
    public long frameDurationUs;
    public int frameSize;
    public boolean hasOutputFormat;
    public final MpegAudioUtil.Header header;
    public final ParsableByteArray headerScratch;

    @Nullable
    public final String language;
    public boolean lastByteWasFF;
    public TrackOutput output;
    public int state;
    public long timeUs;

    public MpegAudioReader() {
        this(null);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i == 0) {
                findHeader(parsableByteArray);
            } else if (i == 1) {
                readHeaderRemainder(parsableByteArray);
            } else {
                if (i != 2) {
                    throw new IllegalStateException();
                }
                readFrameRemainder(parsableByteArray);
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

    public final void findHeader(ParsableByteArray parsableByteArray) {
        byte[] bArr = parsableByteArray.data;
        int i = parsableByteArray.limit;
        for (int i2 = parsableByteArray.position; i2 < i; i2++) {
            byte b = bArr[i2];
            boolean z = (b & 255) == 255;
            boolean z2 = this.lastByteWasFF && (b & 224) == 224;
            this.lastByteWasFF = z;
            if (z2) {
                parsableByteArray.setPosition(i2 + 1);
                this.lastByteWasFF = false;
                this.headerScratch.data[1] = bArr[i2];
                this.frameBytesRead = 2;
                this.state = 1;
                return;
            }
        }
        parsableByteArray.setPosition(i);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        if (j != -9223372036854775807L) {
            this.timeUs = j;
        }
    }

    @RequiresNonNull({"output"})
    public final void readFrameRemainder(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), this.frameSize - this.frameBytesRead);
        this.output.sampleData(parsableByteArray, iMin);
        int i = this.frameBytesRead + iMin;
        this.frameBytesRead = i;
        int i2 = this.frameSize;
        if (i < i2) {
            return;
        }
        long j = this.timeUs;
        if (j != -9223372036854775807L) {
            this.output.sampleMetadata(j, 1, i2, 0, null);
            this.timeUs += this.frameDurationUs;
        }
        this.frameBytesRead = 0;
        this.state = 0;
    }

    @RequiresNonNull({"output"})
    public final void readHeaderRemainder(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), 4 - this.frameBytesRead);
        parsableByteArray.readBytes(this.headerScratch.data, this.frameBytesRead, iMin);
        int i = this.frameBytesRead + iMin;
        this.frameBytesRead = i;
        if (i < 4) {
            return;
        }
        this.headerScratch.setPosition(0);
        if (!this.header.setForHeaderData(this.headerScratch.readInt())) {
            this.frameBytesRead = 0;
            this.state = 1;
            return;
        }
        MpegAudioUtil.Header header = this.header;
        this.frameSize = header.frameSize;
        if (!this.hasOutputFormat) {
            long j = ((long) header.samplesPerFrame) * 1000000;
            int i2 = header.sampleRate;
            this.frameDurationUs = j / ((long) i2);
            Format.Builder builder = new Format.Builder();
            builder.id = this.formatId;
            builder.sampleMimeType = header.mimeType;
            builder.maxInputSize = 4096;
            builder.channelCount = header.channels;
            builder.sampleRate = i2;
            builder.language = this.language;
            this.output.format(new Format(builder));
            this.hasOutputFormat = true;
        }
        this.headerScratch.setPosition(0);
        this.output.sampleData(this.headerScratch, 4);
        this.state = 2;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.frameBytesRead = 0;
        this.lastByteWasFF = false;
        this.timeUs = -9223372036854775807L;
    }

    public MpegAudioReader(@Nullable String str) {
        this.state = 0;
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        this.headerScratch = parsableByteArray;
        parsableByteArray.data[0] = -1;
        this.header = new MpegAudioUtil.Header();
        this.timeUs = -9223372036854775807L;
        this.language = str;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
