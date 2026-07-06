package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Id3Reader implements ElementaryStreamReader {
    public static final String TAG = "Id3Reader";
    public TrackOutput output;
    public int sampleBytesRead;
    public int sampleSize;
    public boolean writingSample;
    public final ParsableByteArray id3Header = new ParsableByteArray(10);
    public long sampleTimeUs = -9223372036854775807L;

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample) {
            int iBytesLeft = parsableByteArray.bytesLeft();
            int i = this.sampleBytesRead;
            if (i < 10) {
                int iMin = Math.min(iBytesLeft, 10 - i);
                System.arraycopy(parsableByteArray.data, parsableByteArray.position, this.id3Header.data, this.sampleBytesRead, iMin);
                if (this.sampleBytesRead + iMin == 10) {
                    this.id3Header.setPosition(0);
                    if (73 != this.id3Header.readUnsignedByte() || 68 != this.id3Header.readUnsignedByte() || 51 != this.id3Header.readUnsignedByte()) {
                        Log.w("Id3Reader", "Discarding invalid ID3 tag");
                        this.writingSample = false;
                        return;
                    } else {
                        this.id3Header.skipBytes(3);
                        this.sampleSize = this.id3Header.readSynchSafeInt() + 10;
                    }
                }
            }
            int iMin2 = Math.min(iBytesLeft, this.sampleSize - this.sampleBytesRead);
            this.output.sampleData(parsableByteArray, iMin2);
            this.sampleBytesRead += iMin2;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 5);
        this.output = trackOutputTrack;
        Format.Builder builder = new Format.Builder();
        trackIdGenerator.maybeThrowUninitializedError();
        builder.id = trackIdGenerator.formatId;
        builder.sampleMimeType = "application/id3";
        trackOutputTrack.format(new Format(builder));
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
        int i;
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample && (i = this.sampleSize) != 0 && this.sampleBytesRead == i) {
            long j = this.sampleTimeUs;
            if (j != -9223372036854775807L) {
                this.output.sampleMetadata(j, 1, i, 0, null);
            }
            this.writingSample = false;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        if ((i & 4) == 0) {
            return;
        }
        this.writingSample = true;
        if (j != -9223372036854775807L) {
            this.sampleTimeUs = j;
        }
        this.sampleSize = 0;
        this.sampleBytesRead = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
