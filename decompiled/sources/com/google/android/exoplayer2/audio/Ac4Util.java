package com.google.android.exoplayer2.audio;

import androidx.annotation.Nullable;
import com.amazon.livingroom.deviceproperties.VideoCapabilitiesProvider;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Ac4Util {
    public static final int AC40_SYNCWORD = 44096;
    public static final int AC41_SYNCWORD = 44097;
    public static final int CHANNEL_COUNT_2 = 2;
    public static final int HEADER_SIZE_FOR_PARSER = 16;
    public static final int MAX_RATE_BYTES_PER_SECOND = 336000;
    public static final int[] SAMPLE_COUNT = {2002, 2000, VideoCapabilitiesProvider.FULL_HD_WIDTH, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};
    public static final int SAMPLE_HEADER_SIZE = 7;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        public SyncFrameInfo(int i, int i2, int i3, int i4, int i5) {
            this.bitstreamVersion = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
        }
    }

    public static void getAc4SampleHeader(int i, ParsableByteArray parsableByteArray) {
        parsableByteArray.reset(7);
        byte[] bArr = parsableByteArray.data;
        bArr[0] = -84;
        bArr[1] = 64;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 8) & 255);
        bArr[6] = (byte) (i & 255);
    }

    public static Format parseAc4AnnexEFormat(ParsableByteArray parsableByteArray, String str, String str2, @Nullable DrmInitData drmInitData) {
        parsableByteArray.skipBytes(1);
        int i = ((parsableByteArray.readUnsignedByte() & 32) >> 5) == 1 ? 48000 : 44100;
        Format.Builder builder = new Format.Builder();
        builder.id = str;
        builder.sampleMimeType = "audio/ac4";
        builder.channelCount = 2;
        builder.sampleRate = i;
        builder.drmInitData = drmInitData;
        builder.language = str2;
        return new Format(builder);
    }

    public static int parseAc4SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        int iPosition = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(iPosition);
        return parseAc4SyncframeInfo(new ParsableBitArray(bArr, 16)).sampleCount;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0086, code lost:
    
        if (r10 != 11) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008d, code lost:
    
        if (r10 != 11) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0092, code lost:
    
        if (r10 != 8) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.audio.Ac4Util.SyncFrameInfo parseAc4SyncframeInfo(com.google.android.exoplayer2.util.ParsableBitArray r10) {
        /*
            r0 = 16
            int r1 = r10.readBits(r0)
            int r0 = r10.readBits(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 4
            if (r0 != r2) goto L18
            r0 = 24
            int r0 = r10.readBits(r0)
            r2 = 7
            goto L19
        L18:
            r2 = 4
        L19:
            int r0 = r0 + r2
            r2 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r2) goto L21
            int r0 = r0 + 2
        L21:
            r8 = r0
            r0 = 2
            int r1 = r10.readBits(r0)
            r2 = 3
            if (r1 != r2) goto L2f
            int r4 = readVariableBits(r10, r0)
            int r1 = r1 + r4
        L2f:
            r5 = r1
            r1 = 10
            int r1 = r10.readBits(r1)
            boolean r4 = r10.readBit()
            if (r4 == 0) goto L45
            int r4 = r10.readBits(r2)
            if (r4 <= 0) goto L45
            r10.skipBits(r0)
        L45:
            boolean r4 = r10.readBit()
            r6 = 44100(0xac44, float:6.1797E-41)
            r7 = 48000(0xbb80, float:6.7262E-41)
            if (r4 == 0) goto L55
        L51:
            r4 = 48000(0xbb80, float:6.7262E-41)
            goto L59
        L55:
            r7 = 44100(0xac44, float:6.1797E-41)
            goto L51
        L59:
            int r10 = r10.readBits(r3)
            if (r7 != r6) goto L69
            r6 = 13
            if (r10 != r6) goto L69
            int[] r0 = com.google.android.exoplayer2.audio.Ac4Util.SAMPLE_COUNT
            r10 = r0[r10]
        L67:
            r9 = r10
            goto L99
        L69:
            if (r7 != r4) goto L97
            int[] r4 = com.google.android.exoplayer2.audio.Ac4Util.SAMPLE_COUNT
            int r6 = r4.length
            if (r10 >= r6) goto L97
            r4 = r4[r10]
            int r1 = r1 % 5
            r6 = 8
            r9 = 1
            if (r1 == r9) goto L90
            r9 = 11
            if (r1 == r0) goto L8b
            if (r1 == r2) goto L90
            if (r1 == r3) goto L82
            goto L95
        L82:
            if (r10 == r2) goto L88
            if (r10 == r6) goto L88
            if (r10 != r9) goto L95
        L88:
            int r10 = r4 + 1
            goto L67
        L8b:
            if (r10 == r6) goto L88
            if (r10 != r9) goto L95
            goto L88
        L90:
            if (r10 == r2) goto L88
            if (r10 != r6) goto L95
            goto L88
        L95:
            r9 = r4
            goto L99
        L97:
            r10 = 0
            r9 = 0
        L99:
            com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo r4 = new com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo
            r6 = 2
            r4.<init>(r5, r6, r7, r8, r9)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.Ac4Util.parseAc4SyncframeInfo(com.google.android.exoplayer2.util.ParsableBitArray):com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo");
    }

    public static int parseAc4SyncframeSize(byte[] bArr, int i) {
        int i2 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        int i3 = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        if (i3 == 65535) {
            i3 = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        } else {
            i2 = 4;
        }
        if (i == 44097) {
            i2 += 2;
        }
        return i3 + i2;
    }

    public static int readVariableBits(ParsableBitArray parsableBitArray, int i) {
        int i2 = 0;
        while (true) {
            int bits = parsableBitArray.readBits(i) + i2;
            if (!parsableBitArray.readBit()) {
                return bits;
            }
            i2 = (bits + 1) << i;
        }
    }
}
