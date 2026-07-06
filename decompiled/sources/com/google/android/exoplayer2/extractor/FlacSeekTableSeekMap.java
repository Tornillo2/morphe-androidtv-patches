package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.FlacStreamMetadata;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FlacSeekTableSeekMap implements SeekMap {
    public final long firstFrameOffset;
    public final FlacStreamMetadata flacStreamMetadata;

    public FlacSeekTableSeekMap(FlacStreamMetadata flacStreamMetadata, long j) {
        this.flacStreamMetadata = flacStreamMetadata;
        this.firstFrameOffset = j;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.flacStreamMetadata.getDurationUs();
    }

    public final SeekPoint getSeekPoint(long j, long j2) {
        return new SeekPoint((j * 1000000) / ((long) this.flacStreamMetadata.sampleRate), this.firstFrameOffset + j2);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        Assertions.checkStateNotNull(this.flacStreamMetadata.seekTable);
        FlacStreamMetadata flacStreamMetadata = this.flacStreamMetadata;
        FlacStreamMetadata.SeekTable seekTable = flacStreamMetadata.seekTable;
        long[] jArr = seekTable.pointSampleNumbers;
        long[] jArr2 = seekTable.pointOffsets;
        int iBinarySearchFloor = Util.binarySearchFloor(jArr, flacStreamMetadata.getSampleNumber(j), true, false);
        SeekPoint seekPoint = getSeekPoint(iBinarySearchFloor == -1 ? 0L : jArr[iBinarySearchFloor], iBinarySearchFloor != -1 ? jArr2[iBinarySearchFloor] : 0L);
        if (seekPoint.timeUs == j || iBinarySearchFloor == jArr.length - 1) {
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        int i = iBinarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, getSeekPoint(jArr[i], jArr2[i]));
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
