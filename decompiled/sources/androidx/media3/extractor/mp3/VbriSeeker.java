package androidx.media3.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VbriSeeker implements Seeker {
    public static final String TAG = "VbriSeeker";
    public final int bitrate;
    public final long dataEndPosition;
    public final long durationUs;
    public final long[] positions;
    public final long[] timesUs;

    public VbriSeeker(long[] jArr, long[] jArr2, long j, long j2, int i) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j;
        this.dataEndPosition = j2;
        this.bitrate = i;
    }

    @Nullable
    public static VbriSeeker create(long j, long j2, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int unsignedByte;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(10);
        int i = parsableByteArray2.readInt();
        VbriSeeker vbriSeeker = null;
        if (i <= 0) {
            return null;
        }
        int i2 = header.sampleRate;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(i, ((long) (i2 >= 32000 ? 1152 : 576)) * 1000000, i2);
        int unsignedShort = parsableByteArray2.readUnsignedShort();
        int unsignedShort2 = parsableByteArray2.readUnsignedShort();
        int unsignedShort3 = parsableByteArray2.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        long j3 = j2 + ((long) header.frameSize);
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        long j4 = j2;
        int i3 = 0;
        while (i3 < unsignedShort) {
            VbriSeeker vbriSeeker2 = vbriSeeker;
            int i4 = unsignedShort2;
            long j5 = j3;
            jArr[i3] = (((long) i3) * jScaleLargeTimestamp) / ((long) unsignedShort);
            jArr2[i3] = Math.max(j4, j5);
            if (unsignedShort3 == 1) {
                unsignedByte = parsableByteArray2.readUnsignedByte();
            } else if (unsignedShort3 == 2) {
                unsignedByte = parsableByteArray2.readUnsignedShort();
            } else if (unsignedShort3 == 3) {
                unsignedByte = parsableByteArray2.readUnsignedInt24();
            } else {
                if (unsignedShort3 != 4) {
                    return vbriSeeker2;
                }
                unsignedByte = parsableByteArray2.readUnsignedIntToInt();
            }
            j4 += ((long) unsignedByte) * ((long) i4);
            i3++;
            parsableByteArray2 = parsableByteArray;
            j3 = j5;
            unsignedShort2 = i4;
            vbriSeeker = vbriSeeker2;
            unsignedShort = unsignedShort;
        }
        if (j != -1 && j != j4) {
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("VBRI data size mismatch: ", j, ", ");
            sbM.append(j4);
            Log.w("VbriSeeker", sbM.toString());
        }
        return new VbriSeeker(jArr, jArr2, jScaleLargeTimestamp, j4, header.bitrate);
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

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j, true, true)];
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
