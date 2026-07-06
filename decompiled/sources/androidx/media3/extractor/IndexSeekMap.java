package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class IndexSeekMap implements SeekMap {
    public final long durationUs;
    public final boolean isSeekable;
    public final long[] positions;
    public final long[] timesUs;

    public IndexSeekMap(long[] jArr, long[] jArr2, long j) {
        Assertions.checkArgument(jArr.length == jArr2.length);
        int length = jArr2.length;
        boolean z = length > 0;
        this.isSeekable = z;
        if (!z || jArr2[0] <= 0) {
            this.positions = jArr;
            this.timesUs = jArr2;
        } else {
            int i = length + 1;
            long[] jArr3 = new long[i];
            this.positions = jArr3;
            long[] jArr4 = new long[i];
            this.timesUs = jArr4;
            System.arraycopy(jArr, 0, jArr3, 1, length);
            System.arraycopy(jArr2, 0, jArr4, 1, length);
        }
        this.durationUs = j;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        if (!this.isSeekable) {
            SeekPoint seekPoint = SeekPoint.START;
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        int iBinarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        long[] jArr = this.timesUs;
        long j2 = jArr[iBinarySearchFloor];
        long[] jArr2 = this.positions;
        SeekPoint seekPoint2 = new SeekPoint(j2, jArr2[iBinarySearchFloor]);
        if (j2 == j || iBinarySearchFloor == jArr.length - 1) {
            return new SeekMap.SeekPoints(seekPoint2, seekPoint2);
        }
        int i = iBinarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint2, new SeekPoint(jArr[i], jArr2[i]));
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return this.isSeekable;
    }
}
