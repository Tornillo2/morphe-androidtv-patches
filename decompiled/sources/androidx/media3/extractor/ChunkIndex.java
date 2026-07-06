package androidx.media3.extractor;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ChunkIndex implements SeekMap {
    public final long durationUs;
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    public ChunkIndex(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.sizes = iArr;
        this.offsets = jArr;
        this.durationsUs = jArr2;
        this.timesUs = jArr3;
        int length = iArr.length;
        this.length = length;
        if (length > 0) {
            this.durationUs = jArr2[length - 1] + jArr3[length - 1];
        } else {
            this.durationUs = 0L;
        }
    }

    public int getChunkIndex(long j) {
        return Util.binarySearchFloor(this.timesUs, j, true, true);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        int chunkIndex = getChunkIndex(j);
        long[] jArr = this.timesUs;
        long j2 = jArr[chunkIndex];
        long[] jArr2 = this.offsets;
        SeekPoint seekPoint = new SeekPoint(j2, jArr2[chunkIndex]);
        if (j2 >= j || chunkIndex == this.length - 1) {
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        int i = chunkIndex + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(jArr[i], jArr2[i]));
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public String toString() {
        return "ChunkIndex(length=" + this.length + ", sizes=" + Arrays.toString(this.sizes) + ", offsets=" + Arrays.toString(this.offsets) + ", timeUs=" + Arrays.toString(this.timesUs) + ", durationsUs=" + Arrays.toString(this.durationsUs) + ")";
    }
}
