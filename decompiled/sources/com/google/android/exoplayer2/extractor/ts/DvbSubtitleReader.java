package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DvbSubtitleReader implements ElementaryStreamReader {
    public int bytesToCheck;
    public final TrackOutput[] outputs;
    public int sampleBytesWritten;
    public long sampleTimeUs = -9223372036854775807L;
    public final List<TsPayloadReader.DvbSubtitleInfo> subtitleInfos;
    public boolean writingSample;

    public DvbSubtitleReader(List<TsPayloadReader.DvbSubtitleInfo> list) {
        this.subtitleInfos = list;
        this.outputs = new TrackOutput[list.size()];
    }

    public final boolean checkNextByte(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.bytesLeft() == 0) {
            return false;
        }
        if (parsableByteArray.readUnsignedByte() != i) {
            this.writingSample = false;
        }
        this.bytesToCheck--;
        return this.writingSample;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        if (this.writingSample) {
            if (this.bytesToCheck != 2 || checkNextByte(parsableByteArray, 32)) {
                if (this.bytesToCheck != 1 || checkNextByte(parsableByteArray, 0)) {
                    int i = parsableByteArray.position;
                    int iBytesLeft = parsableByteArray.bytesLeft();
                    for (TrackOutput trackOutput : this.outputs) {
                        parsableByteArray.setPosition(i);
                        trackOutput.sampleData(parsableByteArray, iBytesLeft);
                    }
                    this.sampleBytesWritten += iBytesLeft;
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.outputs.length; i++) {
            TsPayloadReader.DvbSubtitleInfo dvbSubtitleInfo = this.subtitleInfos.get(i);
            trackIdGenerator.generateNewId();
            trackIdGenerator.maybeThrowUninitializedError();
            TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 3);
            Format.Builder builder = new Format.Builder();
            trackIdGenerator.maybeThrowUninitializedError();
            builder.id = trackIdGenerator.formatId;
            builder.sampleMimeType = "application/dvbsubs";
            builder.initializationData = Collections.singletonList(dvbSubtitleInfo.initializationData);
            builder.language = dvbSubtitleInfo.language;
            trackOutputTrack.format(new Format(builder));
            this.outputs[i] = trackOutputTrack;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
        if (this.writingSample) {
            if (this.sampleTimeUs != -9223372036854775807L) {
                for (TrackOutput trackOutput : this.outputs) {
                    trackOutput.sampleMetadata(this.sampleTimeUs, 1, this.sampleBytesWritten, 0, null);
                }
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
        this.sampleBytesWritten = 0;
        this.bytesToCheck = 2;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
