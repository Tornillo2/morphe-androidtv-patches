package androidx.media3.extractor;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.SeekMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class ConstantBitrateSeekMap implements SeekMap {
    public final boolean allowSeeksIfLengthUnknown;
    public final int bitrate;
    public final long dataSize;
    public final long durationUs;
    public final long firstFrameBytePosition;
    public final int frameSize;
    public final long inputLength;

    public ConstantBitrateSeekMap(long j, long j2, int i, int i2) {
        this(j, j2, i, i2, false);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    public final long getFramePositionForTimeUs(long j) {
        long j2 = (j * ((long) this.bitrate)) / 8000000;
        int i = this.frameSize;
        long jMin = (j2 / ((long) i)) * ((long) i);
        long j3 = this.dataSize;
        if (j3 != -1) {
            jMin = Math.min(jMin, j3 - ((long) i));
        }
        return this.firstFrameBytePosition + Math.max(jMin, 0L);
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        if (this.dataSize == -1 && !this.allowSeeksIfLengthUnknown) {
            SeekPoint seekPoint = new SeekPoint(0L, this.firstFrameBytePosition);
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        long framePositionForTimeUs = getFramePositionForTimeUs(j);
        long timeUsAtPosition = getTimeUsAtPosition(framePositionForTimeUs);
        SeekPoint seekPoint2 = new SeekPoint(timeUsAtPosition, framePositionForTimeUs);
        if (this.dataSize != -1 && timeUsAtPosition < j) {
            int i = this.frameSize;
            if (((long) i) + framePositionForTimeUs < this.inputLength) {
                long j2 = framePositionForTimeUs + ((long) i);
                return new SeekMap.SeekPoints(seekPoint2, new SeekPoint(getTimeUsAtPosition(j2), j2));
            }
        }
        return new SeekMap.SeekPoints(seekPoint2, seekPoint2);
    }

    public long getTimeUsAtPosition(long j) {
        return getTimeUsAtPosition(j, this.firstFrameBytePosition, this.bitrate);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return this.dataSize != -1 || this.allowSeeksIfLengthUnknown;
    }

    public ConstantBitrateSeekMap(long j, long j2, int i, int i2, boolean z) {
        this.inputLength = j;
        this.firstFrameBytePosition = j2;
        this.frameSize = i2 == -1 ? 1 : i2;
        this.bitrate = i;
        this.allowSeeksIfLengthUnknown = z;
        if (j == -1) {
            this.dataSize = -1L;
            this.durationUs = -9223372036854775807L;
        } else {
            this.dataSize = j - j2;
            this.durationUs = getTimeUsAtPosition(j, j2, i);
        }
    }

    public static long getTimeUsAtPosition(long j, long j2, int i) {
        return (Math.max(0L, j - j2) * 8000000) / ((long) i);
    }
}
