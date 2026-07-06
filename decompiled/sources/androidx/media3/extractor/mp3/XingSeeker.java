package androidx.media3.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class XingSeeker implements Seeker {
    public static final String TAG = "XingSeeker";
    public final int bitrate;
    public final long dataEndPosition;
    public final long dataSize;
    public final long dataStartPosition;
    public final long durationUs;

    @Nullable
    public final long[] tableOfContents;
    public final int xingFrameSize;

    public XingSeeker(long j, int i, long j2, int i2) {
        this(j, i, j2, i2, -1L, null);
    }

    @Nullable
    public static XingSeeker create(long j, XingFrame xingFrame, long j2) {
        long j3 = xingFrame.frameCount;
        if (j3 == -1 && j3 == 0) {
            return null;
        }
        MpegAudioUtil.Header header = xingFrame.header;
        long jSampleCountToDurationUs = Util.sampleCountToDurationUs((j3 * ((long) header.samplesPerFrame)) - 1, header.sampleRate);
        long j4 = xingFrame.dataSize;
        if (j4 == -1 || xingFrame.tableOfContents == null) {
            MpegAudioUtil.Header header2 = xingFrame.header;
            return new XingSeeker(j2, header2.frameSize, jSampleCountToDurationUs, header2.bitrate);
        }
        if (j != -1 && j != j2 + j4) {
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("XING data size mismatch: ", j, ", ");
            sbM.append(j2 + xingFrame.dataSize);
            Log.w("XingSeeker", sbM.toString());
        }
        MpegAudioUtil.Header header3 = xingFrame.header;
        return new XingSeeker(j2, header3.frameSize, jSampleCountToDurationUs, header3.bitrate, xingFrame.dataSize, xingFrame.tableOfContents);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.bitrate;
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
        if (!isSeekable()) {
            SeekPoint seekPoint = new SeekPoint(0L, this.dataStartPosition + ((long) this.xingFrameSize));
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        long jConstrainValue = Util.constrainValue(j, 0L, this.durationUs);
        double d = (jConstrainValue * 100.0d) / this.durationUs;
        double d2 = 0.0d;
        if (d > 0.0d) {
            if (d >= 100.0d) {
                d2 = 256.0d;
            } else {
                int i = (int) d;
                long[] jArr = this.tableOfContents;
                Assertions.checkStateNotNull(jArr);
                double d3 = jArr[i];
                d2 = d3 + (((i == 99 ? 256.0d : r3[i + 1]) - d3) * (d - ((double) i)));
            }
        }
        SeekPoint seekPoint2 = new SeekPoint(jConstrainValue, this.dataStartPosition + Util.constrainValue(Math.round((d2 / 256.0d) * this.dataSize), this.xingFrameSize, this.dataSize - 1));
        return new SeekMap.SeekPoints(seekPoint2, seekPoint2);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j) {
        long j2 = j - this.dataStartPosition;
        if (!isSeekable() || j2 <= this.xingFrameSize) {
            return 0L;
        }
        long[] jArr = this.tableOfContents;
        Assertions.checkStateNotNull(jArr);
        long[] jArr2 = jArr;
        double d = (j2 * 256.0d) / this.dataSize;
        int iBinarySearchFloor = Util.binarySearchFloor(jArr2, (long) d, true, true);
        long timeUsForTableIndex = getTimeUsForTableIndex(iBinarySearchFloor);
        long j3 = jArr2[iBinarySearchFloor];
        int i = iBinarySearchFloor + 1;
        long timeUsForTableIndex2 = getTimeUsForTableIndex(i);
        return Math.round((j3 == (iBinarySearchFloor == 99 ? 256L : jArr2[i]) ? 0.0d : (d - j3) / (r0 - j3)) * (timeUsForTableIndex2 - timeUsForTableIndex)) + timeUsForTableIndex;
    }

    public final long getTimeUsForTableIndex(int i) {
        return (this.durationUs * ((long) i)) / 100;
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    public XingSeeker(long j, int i, long j2, int i2, long j3, @Nullable long[] jArr) {
        this.dataStartPosition = j;
        this.xingFrameSize = i;
        this.durationUs = j2;
        this.bitrate = i2;
        this.dataSize = j3;
        this.tableOfContents = jArr;
        this.dataEndPosition = j3 != -1 ? j + j3 : -1L;
    }
}
