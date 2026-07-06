package androidx.media3.extractor.mp3;

import android.util.Pair;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.metadata.id3.MlltFrame;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MlltSeeker implements Seeker {
    public final long durationUs;
    public final long[] referencePositions;
    public final long[] referenceTimesMs;

    public MlltSeeker(long[] jArr, long[] jArr2, long j) {
        this.referencePositions = jArr;
        this.referenceTimesMs = jArr2;
        this.durationUs = j == -9223372036854775807L ? Util.msToUs(jArr2[jArr2.length - 1]) : j;
    }

    public static MlltSeeker create(long j, MlltFrame mlltFrame, long j2) {
        int length = mlltFrame.bytesDeviations.length;
        int i = length + 1;
        long[] jArr = new long[i];
        long[] jArr2 = new long[i];
        jArr[0] = j;
        long j3 = 0;
        jArr2[0] = 0;
        for (int i2 = 1; i2 <= length; i2++) {
            int i3 = i2 - 1;
            j += (long) (mlltFrame.bytesBetweenReference + mlltFrame.bytesDeviations[i3]);
            j3 += (long) (mlltFrame.millisecondsBetweenReference + mlltFrame.millisecondsDeviations[i3]);
            jArr[i2] = j;
            jArr2[i2] = j3;
        }
        return new MlltSeeker(jArr, jArr2, j2);
    }

    public static Pair<Long, Long> linearlyInterpolate(long j, long[] jArr, long[] jArr2) {
        int iBinarySearchFloor = Util.binarySearchFloor(jArr, j, true, true);
        long j2 = jArr[iBinarySearchFloor];
        long j3 = jArr2[iBinarySearchFloor];
        int i = iBinarySearchFloor + 1;
        if (i == jArr.length) {
            return Pair.create(Long.valueOf(j2), Long.valueOf(j3));
        }
        return Pair.create(Long.valueOf(j), Long.valueOf(((long) ((jArr[i] == j2 ? 0.0d : (j - j2) / (r6 - j2)) * (jArr2[i] - j3))) + j3));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return -2147483647;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return -1L;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        Pair<Long, Long> pairLinearlyInterpolate = linearlyInterpolate(Util.usToMs(Util.constrainValue(j, 0L, this.durationUs)), this.referenceTimesMs, this.referencePositions);
        SeekPoint seekPoint = new SeekPoint(Util.msToUs(((Long) pairLinearlyInterpolate.first).longValue()), ((Long) pairLinearlyInterpolate.second).longValue());
        return new SeekMap.SeekPoints(seekPoint, seekPoint);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j) {
        return Util.msToUs(((Long) linearlyInterpolate(j, this.referencePositions, this.referenceTimesMs).second).longValue());
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
