package com.google.android.exoplayer2.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class XingSeeker implements Seeker {
    public static final String TAG = "XingSeeker";
    public final long dataEndPosition;
    public final long dataSize;
    public final long dataStartPosition;
    public final long durationUs;

    @Nullable
    public final long[] tableOfContents;
    public final int xingFrameSize;

    public XingSeeker(long j, int i, long j2) {
        this(j, i, j2, -1L, null);
    }

    @Nullable
    public static XingSeeker create(long j, long j2, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int unsignedIntToInt;
        int i = header.samplesPerFrame;
        int i2 = header.sampleRate;
        int i3 = parsableByteArray.readInt();
        if ((i3 & 1) != 1 || (unsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) == 0) {
            return null;
        }
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(unsignedIntToInt, ((long) i) * 1000000, i2);
        if ((i3 & 6) != 6) {
            return new XingSeeker(j2, header.frameSize, jScaleLargeTimestamp);
        }
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long[] jArr = new long[100];
        for (int i4 = 0; i4 < 100; i4++) {
            jArr[i4] = parsableByteArray.readUnsignedByte();
        }
        if (j != -1) {
            long j3 = j2 + unsignedInt;
            if (j != j3) {
                StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("XING data size mismatch: ", j, ", ");
                sbM.append(j3);
                Log.w("XingSeeker", sbM.toString());
            }
        }
        return new XingSeeker(j2, header.frameSize, jScaleLargeTimestamp, unsignedInt, jArr);
    }

    @Override // com.google.android.exoplayer2.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
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

    @Override // com.google.android.exoplayer2.extractor.mp3.Seeker
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

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    public XingSeeker(long j, int i, long j2, long j3, @Nullable long[] jArr) {
        this.dataStartPosition = j;
        this.xingFrameSize = i;
        this.durationUs = j2;
        this.tableOfContents = jArr;
        this.dataSize = j3;
        this.dataEndPosition = j3 != -1 ? j + j3 : -1L;
    }
}
