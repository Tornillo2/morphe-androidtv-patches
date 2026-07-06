package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class WavSeekMap implements SeekMap {
    public final long blockCount;
    public final long durationUs;
    public final long firstBlockPosition;
    public final int framesPerBlock;
    public final WavFormat wavFormat;

    public WavSeekMap(WavFormat wavFormat, int i, long j, long j2) {
        this.wavFormat = wavFormat;
        this.framesPerBlock = i;
        this.firstBlockPosition = j;
        long j3 = (j2 - j) / ((long) wavFormat.blockSize);
        this.blockCount = j3;
        this.durationUs = blockIndexToTimeUs(j3);
    }

    public final long blockIndexToTimeUs(long j) {
        return Util.scaleLargeTimestamp(j * ((long) this.framesPerBlock), 1000000L, this.wavFormat.frameRateHz);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        long jConstrainValue = Util.constrainValue((((long) this.wavFormat.frameRateHz) * j) / (((long) this.framesPerBlock) * 1000000), 0L, this.blockCount - 1);
        long j2 = (((long) this.wavFormat.blockSize) * jConstrainValue) + this.firstBlockPosition;
        long jBlockIndexToTimeUs = blockIndexToTimeUs(jConstrainValue);
        SeekPoint seekPoint = new SeekPoint(jBlockIndexToTimeUs, j2);
        if (jBlockIndexToTimeUs >= j || jConstrainValue == this.blockCount - 1) {
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        long j3 = jConstrainValue + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(blockIndexToTimeUs(j3), (((long) this.wavFormat.blockSize) * j3) + this.firstBlockPosition));
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
