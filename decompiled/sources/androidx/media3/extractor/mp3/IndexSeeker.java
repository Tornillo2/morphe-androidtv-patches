package androidx.media3.extractor.mp3;

import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import java.math.RoundingMode;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IndexSeeker implements Seeker {

    @VisibleForTesting
    public static final long MIN_TIME_BETWEEN_POINTS_US = 100000;
    public final int averageBitrate;
    public final long dataEndPosition;
    public long durationUs;
    public final LongArray positions;
    public final LongArray timesUs;

    public IndexSeeker(long j, long j2, long j3) {
        this.durationUs = j;
        this.dataEndPosition = j3;
        LongArray longArray = new LongArray();
        this.timesUs = longArray;
        LongArray longArray2 = new LongArray();
        this.positions = longArray2;
        longArray.add(0L);
        longArray2.add(j2);
        int i = -2147483647;
        if (j == -9223372036854775807L) {
            this.averageBitrate = -2147483647;
            return;
        }
        long jScaleLargeValue = Util.scaleLargeValue(j2 - j3, 8L, j, RoundingMode.HALF_UP);
        if (jScaleLargeValue > 0 && jScaleLargeValue <= 2147483647L) {
            i = (int) jScaleLargeValue;
        }
        this.averageBitrate = i;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.averageBitrate;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        int iBinarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        long j2 = this.timesUs.get(iBinarySearchFloor);
        SeekPoint seekPoint = new SeekPoint(j2, this.positions.get(iBinarySearchFloor));
        if (j2 != j) {
            LongArray longArray = this.timesUs;
            if (iBinarySearchFloor != longArray.size - 1) {
                int i = iBinarySearchFloor + 1;
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(longArray.get(i), this.positions.get(i)));
            }
        }
        return new SeekMap.SeekPoints(seekPoint, seekPoint);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j) {
        return this.timesUs.get(Util.binarySearchFloor(this.positions, j, true, true));
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public boolean isTimeUsInIndex(long j) {
        LongArray longArray = this.timesUs;
        return j - longArray.get(longArray.size - 1) < 100000;
    }

    public void maybeAddSeekPoint(long j, long j2) {
        if (isTimeUsInIndex(j)) {
            return;
        }
        this.timesUs.add(j);
        this.positions.add(j2);
    }

    public void setDurationUs(long j) {
        this.durationUs = j;
    }
}
