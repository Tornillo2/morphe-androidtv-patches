package com.google.android.exoplayer2.extractor.jpeg;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class StartOffsetExtractorOutput implements ExtractorOutput {
    public final ExtractorOutput extractorOutput;
    public final long startOffset;

    public StartOffsetExtractorOutput(long j, ExtractorOutput extractorOutput) {
        this.startOffset = j;
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        this.extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(final SeekMap seekMap) {
        this.extractorOutput.seekMap(new SeekMap() { // from class: com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorOutput.1
            @Override // com.google.android.exoplayer2.extractor.SeekMap
            public long getDurationUs() {
                return seekMap.getDurationUs();
            }

            @Override // com.google.android.exoplayer2.extractor.SeekMap
            public SeekMap.SeekPoints getSeekPoints(long j) {
                SeekMap.SeekPoints seekPoints = seekMap.getSeekPoints(j);
                SeekPoint seekPoint = seekPoints.first;
                long j2 = seekPoint.timeUs;
                long j3 = seekPoint.position;
                long j4 = StartOffsetExtractorOutput.this.startOffset;
                SeekPoint seekPoint2 = new SeekPoint(j2, j3 + j4);
                SeekPoint seekPoint3 = seekPoints.second;
                return new SeekMap.SeekPoints(seekPoint2, new SeekPoint(seekPoint3.timeUs, seekPoint3.position + j4));
            }

            @Override // com.google.android.exoplayer2.extractor.SeekMap
            public boolean isSeekable() {
                return seekMap.isSeekable();
            }
        });
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        return this.extractorOutput.track(i, i2);
    }
}
