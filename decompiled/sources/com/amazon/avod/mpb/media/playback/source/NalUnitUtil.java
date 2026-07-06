package com.amazon.avod.mpb.media.playback.source;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;

    @NotNull
    public static final String TAG = "NalUnitUtil";

    @NotNull
    public static final NalUnitUtil INSTANCE = new NalUnitUtil();

    @NotNull
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;

        @NotNull
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

        public H265SpsData(int i, boolean z, int i2, int i3, int i4, int i5, int i6, @NotNull int[] constraintBytes, int i7, int i8, int i9, int i10, float f, int i11) {
            Intrinsics.checkNotNullParameter(constraintBytes, "constraintBytes");
            this.generalProfileSpace = i;
            this.generalTierFlag = z;
            this.generalProfileIdc = i2;
            this.generalProfileCompatibilityFlags = i3;
            this.chromaFormatIdc = i4;
            this.bitDepthLumaMinus8 = i5;
            this.bitDepthChromaMinus8 = i6;
            this.constraintBytes = constraintBytes;
            this.generalLevelIdc = i7;
            this.seqParameterSetId = i8;
            this.width = i9;
            this.height = i10;
            this.pixelWidthHeightRatio = f;
            this.colorRange = i11;
        }

        public final int getBitDepthChromaMinus8() {
            return this.bitDepthChromaMinus8;
        }

        public final int getBitDepthLumaMinus8() {
            return this.bitDepthLumaMinus8;
        }

        public final int getChromaFormatIdc() {
            return this.chromaFormatIdc;
        }

        public final int getColorRange() {
            return this.colorRange;
        }

        @NotNull
        public final int[] getConstraintBytes() {
            return this.constraintBytes;
        }

        public final int getGeneralLevelIdc() {
            return this.generalLevelIdc;
        }

        public final int getGeneralProfileCompatibilityFlags() {
            return this.generalProfileCompatibilityFlags;
        }

        public final int getGeneralProfileIdc() {
            return this.generalProfileIdc;
        }

        public final int getGeneralProfileSpace() {
            return this.generalProfileSpace;
        }

        public final boolean getGeneralTierFlag() {
            return this.generalTierFlag;
        }

        public final int getHeight() {
            return this.height;
        }

        public final float getPixelWidthHeightRatio() {
            return this.pixelWidthHeightRatio;
        }

        public final int getSeqParameterSetId() {
            return this.seqParameterSetId;
        }

        public final int getWidth() {
            return this.width;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SpsData {
        public final int colorRange;
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

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, boolean z, boolean z2, int i8, int i9, int i10, boolean z3, int i11) {
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
            this.colorRange = i11;
        }

        public final int getColorRange() {
            return this.colorRange;
        }

        public final int getConstraintsFlagsAndReservedZero2Bits() {
            return this.constraintsFlagsAndReservedZero2Bits;
        }

        public final boolean getDeltaPicOrderAlwaysZeroFlag() {
            return this.deltaPicOrderAlwaysZeroFlag;
        }

        public final boolean getFrameMbsOnlyFlag() {
            return this.frameMbsOnlyFlag;
        }

        public final int getFrameNumLength() {
            return this.frameNumLength;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getLevelIdc() {
            return this.levelIdc;
        }

        public final int getMaxNumRefFrames() {
            return this.maxNumRefFrames;
        }

        public final int getPicOrderCntLsbLength() {
            return this.picOrderCntLsbLength;
        }

        public final int getPicOrderCountType() {
            return this.picOrderCountType;
        }

        public final float getPixelWidthHeightRatio() {
            return this.pixelWidthHeightRatio;
        }

        public final int getProfileIdc() {
            return this.profileIdc;
        }

        public final boolean getSeparateColorPlaneFlag() {
            return this.separateColorPlaneFlag;
        }

        public final int getSeqParameterSetId() {
            return this.seqParameterSetId;
        }

        public final int getWidth() {
            return this.width;
        }
    }

    public final void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public final int findNalUnit(@NotNull byte[] data, int i, int i2, @NotNull boolean[] prefixFlags) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(prefixFlags, "prefixFlags");
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalStateException("Check failed.");
        }
        if (i3 == 0) {
            return i2;
        }
        if (prefixFlags[0]) {
            clearPrefixFlags(prefixFlags);
            return i - 3;
        }
        if (i3 > 1 && prefixFlags[1] && data[i] == 1) {
            clearPrefixFlags(prefixFlags);
            return i - 2;
        }
        if (i3 > 2 && prefixFlags[2] && data[i] == 0 && data[i + 1] == 1) {
            clearPrefixFlags(prefixFlags);
            return i - 1;
        }
        int i4 = i2 - 1;
        int i5 = i + 2;
        while (i5 < i4) {
            byte b = data[i5];
            if ((b & 254) == 0) {
                int i6 = i5 - 2;
                if (data[i6] == 0 && data[i5 - 1] == 0 && b == 1) {
                    clearPrefixFlags(prefixFlags);
                    return i6;
                }
                i5 -= 2;
            }
            i5 += 3;
        }
        prefixFlags[0] = i3 <= 2 ? !(i3 != 2 ? !(prefixFlags[1] && data[i4] == 1) : !(prefixFlags[2] && data[i2 + (-2)] == 0 && data[i4] == 1)) : data[i2 + (-3)] == 0 && data[i2 + (-2)] == 0 && data[i4] == 1;
        prefixFlags[1] = i3 <= 1 ? prefixFlags[2] && data[i4] == 0 : data[i2 + (-2)] == 0 && data[i4] == 0;
        prefixFlags[2] = data[i4] == 0;
        return i2;
    }

    public final int getH265NalUnitType(@NotNull byte[] data, int i) {
        Intrinsics.checkNotNullParameter(data, "data");
        return (data[i + 3] & 126) >> 1;
    }

    public final int getNalUnitType(@NotNull byte[] data, int i) {
        Intrinsics.checkNotNullParameter(data, "data");
        return data[i + 3] & 31;
    }

    @NotNull
    public final H265SpsData parseH265SpsNalUnit(@NotNull byte[] nalData, int i, int i2) {
        Intrinsics.checkNotNullParameter(nalData, "nalData");
        return parseH265SpsNalUnitPayload(nalData, i + 2, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.avod.mpb.media.playback.source.NalUnitUtil.H265SpsData parseH265SpsNalUnitPayload(byte[] r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.source.NalUnitUtil.parseH265SpsNalUnitPayload(byte[], int, int):com.amazon.avod.mpb.media.playback.source.NalUnitUtil$H265SpsData");
    }

    @NotNull
    public final SpsData parseSpsNalUnit(@NotNull byte[] nalData, int i, int i2) {
        Intrinsics.checkNotNullParameter(nalData, "nalData");
        return parseSpsNalUnitPayload(nalData, i + 1, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.avod.mpb.media.playback.source.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.playback.source.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):com.amazon.avod.mpb.media.playback.source.NalUnitUtil$SpsData");
    }

    public final void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
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

    public final void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
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

    public final void skipShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
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
                if (i6 >= 0) {
                    int i8 = 0;
                    while (true) {
                        if (parsableNalUnitBitArray.readBit()) {
                            zArr[i8] = true;
                        } else {
                            zArr[i8] = parsableNalUnitBitArray.readBit();
                        }
                        if (i8 == i6) {
                            break;
                        } else {
                            i8++;
                        }
                    }
                }
                int[] iArr4 = new int[i7];
                int[] iArr5 = new int[i7];
                int i9 = 0;
                for (int i10 = i2 - 1; -1 < i10; i10--) {
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
                Intrinsics.checkNotNullExpressionValue(iArrCopyOf2, "copyOf(...)");
                int i14 = 0;
                for (int i15 = i - 1; -1 < i15; i15--) {
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
                int i17 = i14;
                for (int i18 = 0; i18 < i2; i18++) {
                    int i19 = iArrCopyOf[i18] + expGolombCodeNum4;
                    if (i19 > 0 && zArr[i + i18]) {
                        iArr5[i17] = i19;
                        i17++;
                    }
                }
                iArrCopyOf = Arrays.copyOf(iArr5, i17);
                Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
                i2 = i17;
                iArr = iArrCopyOf2;
                i = i9;
            }
        }
    }
}
