package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.OptIn;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.container.ParsableNalUnitBitArray;
import com.amazon.reporting.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public final class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final int EXTENDED_SAR = 255;
    public static final String TAG = "NalUnitUtil";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;
        public final int height;
        public final float pixelWidthHeightRatio;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(int i, boolean z, int i2, int i3, int i4, int i5, int i6, int[] iArr, int i7, int i8, int i9, int i10, float f, int i11, int i12, int i13) {
            this.generalProfileSpace = i;
            this.generalTierFlag = z;
            this.generalProfileIdc = i2;
            this.generalProfileCompatibilityFlags = i3;
            this.chromaFormatIdc = i4;
            this.bitDepthLumaMinus8 = i5;
            this.bitDepthChromaMinus8 = i6;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i7;
            this.seqParameterSetId = i8;
            this.width = i9;
            this.height = i10;
            this.pixelWidthHeightRatio = f;
            this.colorSpace = i11;
            this.colorRange = i12;
            this.colorTransfer = i13;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SpsData {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, boolean z, boolean z2, int i8, int i9, int i10, boolean z3, int i11, int i12, int i13) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.maxNumRefFrames = i5;
            this.width = i6;
            this.height = i7;
            this.pixelWidthHeightRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i8;
            this.picOrderCountType = i9;
            this.picOrderCntLsbLength = i10;
            this.deltaPicOrderAlwaysZeroFlag = z3;
            this.colorSpace = i11;
            this.colorRange = i12;
            this.colorTransfer = i13;
        }
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static int findNalUnit(byte[] bArr, int i, int i2, boolean[] zArr) {
        int i3 = i2 - i;
        Assertions.checkState(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i - 3;
        }
        if (i3 > 1 && zArr[1] && bArr[i] == 1) {
            clearPrefixFlags(zArr);
            return i - 2;
        }
        if (i3 > 2 && zArr[2] && bArr[i] == 0 && bArr[i + 1] == 1) {
            clearPrefixFlags(zArr);
            return i - 1;
        }
        int i4 = i2 - 1;
        int i5 = i + 2;
        while (i5 < i4) {
            byte b = bArr[i5];
            if ((b & 254) == 0) {
                int i6 = i5 - 2;
                if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && b == 1) {
                    clearPrefixFlags(zArr);
                    return i6;
                }
                i5 -= 2;
            }
            i5 += 3;
        }
        zArr[0] = i3 <= 2 ? !(i3 != 2 ? !(zArr[1] && bArr[i4] == 1) : !(zArr[2] && bArr[i2 + (-2)] == 0 && bArr[i4] == 1)) : bArr[i2 + (-3)] == 0 && bArr[i2 + (-2)] == 0 && bArr[i4] == 1;
        zArr[1] = i3 <= 1 ? zArr[2] && bArr[i4] == 0 : bArr[i2 + (-2)] == 0 && bArr[i4] == 0;
        zArr[2] = bArr[i4] == 0;
        return i2;
    }

    public static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i, int i2) {
        return parseH265SpsNalUnitPayload(bArr, i + 2, i2);
    }

    public static H265SpsData parseH265SpsNalUnitPayload(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        float f;
        int i6;
        int i7;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        parsableNalUnitBitArray.skipBits(4);
        int bits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        int bits2 = parsableNalUnitBitArray.readBits(2);
        boolean bit = parsableNalUnitBitArray.readBit();
        int bits3 = parsableNalUnitBitArray.readBits(5);
        int i8 = 0;
        for (int i9 = 0; i9 < 32; i9++) {
            if (parsableNalUnitBitArray.readBit()) {
                i8 |= 1 << i9;
            }
        }
        int[] iArr = new int[6];
        for (int i10 = 0; i10 < 6; i10++) {
            iArr[i10] = parsableNalUnitBitArray.readBits(8);
        }
        int bits4 = parsableNalUnitBitArray.readBits(8);
        int i11 = 0;
        for (int i12 = 0; i12 < bits; i12++) {
            if (parsableNalUnitBitArray.readBit()) {
                i11 += 89;
            }
            if (parsableNalUnitBitArray.readBit()) {
                i11 += 8;
            }
        }
        parsableNalUnitBitArray.skipBits(i11);
        if (bits > 0) {
            parsableNalUnitBitArray.skipBits((8 - bits) * 2);
        }
        int expGolombCodeNum = parsableNalUnitBitArray.readExpGolombCodeNum();
        int expGolombCodeNum2 = parsableNalUnitBitArray.readExpGolombCodeNum();
        if (expGolombCodeNum2 == 3) {
            parsableNalUnitBitArray.skipBit();
        }
        int expGolombCodeNum3 = parsableNalUnitBitArray.readExpGolombCodeNum();
        int expGolombCodeNum4 = parsableNalUnitBitArray.readExpGolombCodeNum();
        if (parsableNalUnitBitArray.readBit()) {
            int expGolombCodeNum5 = parsableNalUnitBitArray.readExpGolombCodeNum();
            int expGolombCodeNum6 = parsableNalUnitBitArray.readExpGolombCodeNum();
            int expGolombCodeNum7 = parsableNalUnitBitArray.readExpGolombCodeNum();
            int expGolombCodeNum8 = parsableNalUnitBitArray.readExpGolombCodeNum();
            expGolombCodeNum3 -= (expGolombCodeNum5 + expGolombCodeNum6) * ((expGolombCodeNum2 == 1 || expGolombCodeNum2 == 2) ? 2 : 1);
            expGolombCodeNum4 -= (expGolombCodeNum7 + expGolombCodeNum8) * (expGolombCodeNum2 == 1 ? 2 : 1);
        }
        int expGolombCodeNum9 = parsableNalUnitBitArray.readExpGolombCodeNum();
        int expGolombCodeNum10 = parsableNalUnitBitArray.readExpGolombCodeNum();
        int expGolombCodeNum11 = parsableNalUnitBitArray.readExpGolombCodeNum();
        for (int i13 = parsableNalUnitBitArray.readBit() ? 0 : bits; i13 <= bits; i13++) {
            parsableNalUnitBitArray.readExpGolombCodeNum();
            parsableNalUnitBitArray.readExpGolombCodeNum();
            parsableNalUnitBitArray.readExpGolombCodeNum();
        }
        parsableNalUnitBitArray.readExpGolombCodeNum();
        parsableNalUnitBitArray.readExpGolombCodeNum();
        parsableNalUnitBitArray.readExpGolombCodeNum();
        parsableNalUnitBitArray.readExpGolombCodeNum();
        parsableNalUnitBitArray.readExpGolombCodeNum();
        parsableNalUnitBitArray.readExpGolombCodeNum();
        if (parsableNalUnitBitArray.readBit() && parsableNalUnitBitArray.readBit()) {
            skipH265ScalingList(parsableNalUnitBitArray);
        }
        parsableNalUnitBitArray.skipBits(2);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(8);
            parsableNalUnitBitArray.readExpGolombCodeNum();
            parsableNalUnitBitArray.readExpGolombCodeNum();
            parsableNalUnitBitArray.skipBit();
        }
        skipShortTermReferencePictureSets(parsableNalUnitBitArray);
        if (parsableNalUnitBitArray.readBit()) {
            int expGolombCodeNum12 = parsableNalUnitBitArray.readExpGolombCodeNum();
            for (int i14 = 0; i14 < expGolombCodeNum12; i14++) {
                parsableNalUnitBitArray.skipBits(expGolombCodeNum11 + 5);
            }
        }
        parsableNalUnitBitArray.skipBits(2);
        float f2 = 1.0f;
        if (parsableNalUnitBitArray.readBit()) {
            if (parsableNalUnitBitArray.readBit()) {
                int bits5 = parsableNalUnitBitArray.readBits(8);
                if (bits5 == 255) {
                    int bits6 = parsableNalUnitBitArray.readBits(16);
                    int bits7 = parsableNalUnitBitArray.readBits(16);
                    if (bits6 != 0 && bits7 != 0) {
                        f2 = bits6 / bits7;
                    }
                } else {
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
                    if (bits5 < fArr.length) {
                        f2 = fArr[bits5];
                    } else {
                        Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + bits5);
                    }
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(3);
                int i15 = parsableNalUnitBitArray.readBit() ? 1 : 2;
                if (parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readBits(8);
                    parsableNalUnitBitArray.readBits(8);
                    parsableNalUnitBitArray.skipBits(8);
                }
                i7 = i15;
            } else {
                i7 = -1;
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readExpGolombCodeNum();
                parsableNalUnitBitArray.readExpGolombCodeNum();
            }
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                expGolombCodeNum4 *= 2;
            }
            i6 = i7;
            i3 = expGolombCodeNum;
            i4 = expGolombCodeNum3;
            i5 = expGolombCodeNum4;
            f = f2;
        } else {
            i3 = expGolombCodeNum;
            i4 = expGolombCodeNum3;
            i5 = expGolombCodeNum4;
            f = 1.0f;
            i6 = -1;
        }
        return new H265SpsData(bits2, bit, bits3, i8, expGolombCodeNum2, expGolombCodeNum9, expGolombCodeNum10, iArr, bits4, i3, i4, i5, f, -1, i6, -1);
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i, int i2) {
        return parseSpsNalUnitPayload(bArr, i + 1, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:96:0x019e A[PHI: r16
      0x019e: PHI (r16v5 float) = (r16v4 float), (r16v8 float) binds: [B:72:0x0135, B:89:0x0180] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.livingroom.mediapipelinebackend.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.livingroom.mediapipelinebackend.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):com.amazon.livingroom.mediapipelinebackend.NalUnitUtil$SpsData");
    }

    public static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = 1;
                if (parsableNalUnitBitArray.readBit()) {
                    int iMin = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i4 = 0; i4 < iMin; i4++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                } else {
                    parsableNalUnitBitArray.readExpGolombCodeNum();
                }
                if (i == 3) {
                    i3 = 3;
                }
                i2 += i3;
            }
        }
    }

    public static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int signedExpGolombCodedInt = 8;
        int i2 = 8;
        for (int i3 = 0; i3 < i; i3++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i2) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i2 = signedExpGolombCodedInt;
            }
        }
    }

    public static void skipShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int expGolombCodeNum = parsableNalUnitBitArray.readExpGolombCodeNum();
        int[] iArr = new int[0];
        int[] iArrCopyOf = new int[0];
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < expGolombCodeNum; i3++) {
            if (i3 == 0 || !parsableNalUnitBitArray.readBit()) {
                int expGolombCodeNum2 = parsableNalUnitBitArray.readExpGolombCodeNum();
                int expGolombCodeNum3 = parsableNalUnitBitArray.readExpGolombCodeNum();
                int[] iArr2 = new int[expGolombCodeNum2];
                int i4 = 0;
                while (i4 < expGolombCodeNum2) {
                    iArr2[i4] = (i4 > 0 ? iArr2[i4 - 1] : 0) - (parsableNalUnitBitArray.readExpGolombCodeNum() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i4++;
                }
                int[] iArr3 = new int[expGolombCodeNum3];
                int i5 = 0;
                while (i5 < expGolombCodeNum3) {
                    iArr3[i5] = parsableNalUnitBitArray.readExpGolombCodeNum() + 1 + (i5 > 0 ? iArr3[i5 - 1] : 0);
                    parsableNalUnitBitArray.skipBit();
                    i5++;
                }
                i = expGolombCodeNum2;
                iArr = iArr2;
                i2 = expGolombCodeNum3;
                iArrCopyOf = iArr3;
            } else {
                int i6 = i + i2;
                int expGolombCodeNum4 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readExpGolombCodeNum() + 1);
                int i7 = i6 + 1;
                boolean[] zArr = new boolean[i7];
                for (int i8 = 0; i8 <= i6; i8++) {
                    if (parsableNalUnitBitArray.readBit()) {
                        zArr[i8] = true;
                    } else {
                        zArr[i8] = parsableNalUnitBitArray.readBit();
                    }
                }
                int[] iArr4 = new int[i7];
                int[] iArr5 = new int[i7];
                int i9 = 0;
                for (int i10 = i2 - 1; i10 >= 0; i10--) {
                    int i11 = iArrCopyOf[i10] + expGolombCodeNum4;
                    if (i11 < 0 && zArr[i + i10]) {
                        iArr4[i9] = i11;
                        i9++;
                    }
                }
                if (expGolombCodeNum4 < 0 && zArr[i6]) {
                    iArr4[i9] = expGolombCodeNum4;
                    i9++;
                }
                for (int i12 = 0; i12 < i; i12++) {
                    int i13 = iArr[i12] + expGolombCodeNum4;
                    if (i13 < 0 && zArr[i12]) {
                        iArr4[i9] = i13;
                        i9++;
                    }
                }
                int[] iArrCopyOf2 = Arrays.copyOf(iArr4, i9);
                int i14 = 0;
                for (int i15 = i - 1; i15 >= 0; i15--) {
                    int i16 = iArr[i15] + expGolombCodeNum4;
                    if (i16 > 0 && zArr[i15]) {
                        iArr5[i14] = i16;
                        i14++;
                    }
                }
                if (expGolombCodeNum4 > 0 && zArr[i6]) {
                    iArr5[i14] = expGolombCodeNum4;
                    i14++;
                }
                for (int i17 = 0; i17 < i2; i17++) {
                    int i18 = iArrCopyOf[i17] + expGolombCodeNum4;
                    if (i18 > 0 && zArr[i + i17]) {
                        iArr5[i14] = i18;
                        i14++;
                    }
                }
                iArrCopyOf = Arrays.copyOf(iArr5, i14);
                iArr = iArrCopyOf2;
                i = i9;
                i2 = i14;
            }
        }
    }
}
