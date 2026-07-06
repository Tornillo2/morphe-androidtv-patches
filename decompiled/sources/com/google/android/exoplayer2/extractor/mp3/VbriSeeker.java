package com.google.android.exoplayer2.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class VbriSeeker implements Seeker {
    public static final String TAG = "VbriSeeker";
    public final long dataEndPosition;
    public final long durationUs;
    public final long[] positions;
    public final long[] timesUs;

    public VbriSeeker(long[] jArr, long[] jArr2, long j, long j2) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j;
        this.dataEndPosition = j2;
    }

    @Nullable
    public static VbriSeeker create(long j, long j2, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int unsignedByte;
        parsableByteArray.skipBytes(10);
        int i = parsableByteArray.readInt();
        VbriSeeker vbriSeeker = null;
        if (i <= 0) {
            return null;
        }
        int i2 = header.sampleRate;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(i, ((long) (i2 >= 32000 ? 1152 : 576)) * 1000000, i2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        int unsignedShort3 = parsableByteArray.readUnsignedShort();
        int i3 = 2;
        parsableByteArray.skipBytes(2);
        long j3 = j2 + ((long) header.frameSize);
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        int i4 = 0;
        long j4 = j2;
        while (i4 < unsignedShort) {
            VbriSeeker vbriSeeker2 = vbriSeeker;
            int i5 = unsignedShort2;
            long[] jArr3 = jArr;
            jArr3[i4] = (((long) i4) * jScaleLargeTimestamp) / ((long) unsignedShort);
            jArr2[i4] = Math.max(j4, j3);
            if (unsignedShort3 == 1) {
                unsignedByte = parsableByteArray.readUnsignedByte();
            } else if (unsignedShort3 == i3) {
                unsignedByte = parsableByteArray.readUnsignedShort();
            } else if (unsignedShort3 == 3) {
                unsignedByte = parsableByteArray.readUnsignedInt24();
            } else {
                if (unsignedShort3 != 4) {
                    return vbriSeeker2;
                }
                unsignedByte = parsableByteArray.readUnsignedIntToInt();
            }
            j4 += ((long) unsignedByte) * ((long) i5);
            i4++;
            vbriSeeker = vbriSeeker2;
            unsignedShort2 = i5;
            jArr = jArr3;
            j3 = j3;
            i3 = 2;
        }
        long[] jArr4 = jArr;
        if (j != -1 && j != j4) {
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("VBRI data size mismatch: ", j, ", ");
            sbM.append(j4);
            Log.w("VbriSeeker", sbM.toString());
        }
        return new VbriSeeker(jArr4, jArr2, jScaleLargeTimestamp, j4);
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
        int iBinarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        long[] jArr = this.timesUs;
        long j2 = jArr[iBinarySearchFloor];
        long[] jArr2 = this.positions;
        SeekPoint seekPoint = new SeekPoint(j2, jArr2[iBinarySearchFloor]);
        if (j2 >= j || iBinarySearchFloor == jArr.length - 1) {
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        int i = iBinarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(jArr[i], jArr2[i]));
    }

    @Override // com.google.android.exoplayer2.extractor.mp3.Seeker
    public long getTimeUs(long j) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j, true, true)];
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
