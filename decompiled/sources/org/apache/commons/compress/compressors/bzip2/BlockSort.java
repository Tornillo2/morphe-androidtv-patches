package org.apache.commons.compress.compressors.bzip2;

import java.util.BitSet;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class BlockSort {
    public static final int CLEARMASK = -2097153;
    public static final int DEPTH_THRESH = 10;
    public static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    public static final int FALLBACK_QSORT_STACK_SIZE = 100;
    public static final int[] INCS = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    public static final int QSORT_STACK_SIZE = 1000;
    public static final int SETMASK = 2097152;
    public static final int SMALL_THRESH = 20;
    public static final int STACK_SIZE = 1000;
    public static final int WORK_FACTOR = 30;
    public int[] eclass;
    public boolean firstAttempt;
    public final char[] quadrant;
    public int workDone;
    public int workLimit;
    public final int[] stack_ll = new int[1000];
    public final int[] stack_hh = new int[1000];
    public final int[] stack_dd = new int[1000];
    public final int[] mainSort_runningOrder = new int[256];
    public final int[] mainSort_copy = new int[256];
    public final boolean[] mainSort_bigDone = new boolean[256];
    public final int[] ftab = new int[65537];

    public BlockSort(BZip2CompressorOutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x000d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x000e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte med3(byte r0, byte r1, byte r2) {
        /*
            if (r0 >= r1) goto L8
            if (r1 >= r2) goto L5
            goto La
        L5:
            if (r0 >= r2) goto Le
            goto Ld
        L8:
            if (r1 <= r2) goto Lb
        La:
            return r1
        Lb:
            if (r0 <= r2) goto Le
        Ld:
            return r2
        Le:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.med3(byte, byte, byte):byte");
    }

    public static void vswap(int[] iArr, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            int i5 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i5;
            i2++;
            i++;
        }
    }

    public void blockSort(BZip2CompressorOutputStream.Data data, int i) {
        this.workLimit = i * 30;
        this.workDone = 0;
        this.firstAttempt = true;
        if (i + 1 < 10000) {
            fallbackSort(data, i);
        } else {
            mainSort(data, i);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                fallbackSort(data, i);
            }
        }
        int[] iArr = data.fmap;
        data.origPtr = -1;
        for (int i2 = 0; i2 <= i; i2++) {
            if (iArr[i2] == 0) {
                data.origPtr = i2;
                return;
            }
        }
    }

    public final void fallbackQSort3(int[] iArr, int[] iArr2, int i, int i2) {
        int[] iArr3 = iArr2;
        char c = 0;
        fpush(0, i, i2);
        long j = 0;
        int i3 = 1;
        long j2 = 0;
        int i4 = 1;
        while (i4 > 0) {
            int i5 = i4 - 1;
            int[] iArrFpop = fpop(i5);
            int i6 = iArrFpop[c];
            int i7 = iArrFpop[i3];
            if (i7 - i6 < 10) {
                fallbackSimpleSort(iArr, iArr3, i6, i7);
                i4 = i5;
            } else {
                j2 = ((j2 * 7621) + 1) % 32768;
                long j3 = j2 % 3;
                long j4 = j3 == j ? iArr3[iArr[i6]] : j3 == 1 ? iArr3[iArr[(i6 + i7) >>> i3]] : iArr3[iArr[i7]];
                int i8 = i7;
                int i9 = i8;
                int i10 = i6;
                int i11 = i10;
                while (true) {
                    if (i11 <= i8) {
                        int i12 = iArr[i11];
                        int i13 = iArr3[i12] - ((int) j4);
                        if (i13 == 0) {
                            iArr[i11] = iArr[i10];
                            iArr[i10] = i12;
                            i10++;
                            i11++;
                        } else {
                            if (i13 <= 0) {
                                i11++;
                            }
                            iArr3 = iArr2;
                        }
                    }
                    while (i11 <= i8) {
                        int i14 = iArr[i8];
                        int i15 = iArr3[i14] - ((int) j4);
                        if (i15 == 0) {
                            iArr[i8] = iArr[i9];
                            iArr[i9] = i14;
                            i9--;
                        } else if (i15 < 0) {
                            break;
                        }
                        i8--;
                        iArr3 = iArr2;
                    }
                    if (i11 > i8) {
                        break;
                    }
                    fswap(iArr, i11, i8);
                    i11++;
                    i8--;
                    iArr3 = iArr2;
                }
                if (i9 < i10) {
                    iArr3 = iArr2;
                    i4 = i5;
                } else {
                    int i16 = i10 - i6;
                    int i17 = i11 - i10;
                    if (i16 >= i17) {
                        i16 = i17;
                    }
                    fvswap(iArr, i6, i11 - i16, i16);
                    int i18 = i7 - i9;
                    int i19 = i9 - i8;
                    if (i18 >= i19) {
                        i18 = i19;
                    }
                    fvswap(iArr, i8 + 1, (i7 - i18) + 1, i18);
                    int i20 = ((i11 + i6) - i10) - 1;
                    int i21 = (i7 - i19) + 1;
                    if (i20 - i6 > i7 - i21) {
                        fpush(i5, i6, i20);
                        fpush(i4, i21, i7);
                        i4++;
                    } else {
                        fpush(i5, i21, i7);
                        fpush(i4, i6, i20);
                        i4++;
                    }
                    iArr3 = iArr2;
                }
                c = 0;
                j = 0;
                i3 = 1;
            }
        }
    }

    public final void fallbackSimpleSort(int[] iArr, int[] iArr2, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 - i > 3) {
            for (int i3 = i2 - 4; i3 >= i; i3--) {
                int i4 = iArr[i3];
                int i5 = iArr2[i4];
                int i6 = i3 + 4;
                while (i6 <= i2) {
                    int i7 = iArr[i6];
                    if (i5 > iArr2[i7]) {
                        iArr[i6 - 4] = i7;
                        i6 += 4;
                    }
                }
                iArr[i6 - 4] = i4;
            }
        }
        for (int i8 = i2 - 1; i8 >= i; i8--) {
            int i9 = iArr[i8];
            int i10 = iArr2[i9];
            int i11 = i8 + 1;
            while (i11 <= i2) {
                int i12 = iArr[i11];
                if (i10 > iArr2[i12]) {
                    iArr[i11 - 1] = i12;
                    i11++;
                }
            }
            iArr[i11 - 1] = i9;
        }
    }

    public final void fallbackSort(BZip2CompressorOutputStream.Data data, int i) {
        byte[] bArr = data.block;
        int i2 = i + 1;
        bArr[0] = bArr[i2];
        fallbackSort(data.fmap, bArr, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            data.fmap[i3] = r2[i3] - 1;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            int[] iArr = data.fmap;
            if (iArr[i4] == -1) {
                iArr[i4] = i;
                return;
            }
        }
    }

    public final int fmin(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public final int[] fpop(int i) {
        return new int[]{this.stack_ll[i], this.stack_hh[i]};
    }

    public final void fpush(int i, int i2, int i3) {
        this.stack_ll[i] = i2;
        this.stack_hh[i] = i3;
    }

    public final void fswap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    public final void fvswap(int[] iArr, int i, int i2, int i3) {
        while (i3 > 0) {
            fswap(iArr, i, i2);
            i++;
            i2++;
            i3--;
        }
    }

    public final int[] getEclass() {
        int[] iArr = this.eclass;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[this.quadrant.length / 2];
        this.eclass = iArr2;
        return iArr2;
    }

    public final void mainQSort3(BZip2CompressorOutputStream.Data data, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        BlockSort blockSort = this;
        BZip2CompressorOutputStream.Data data2 = data;
        int[] iArr = blockSort.stack_ll;
        int[] iArr2 = blockSort.stack_hh;
        int[] iArr3 = blockSort.stack_dd;
        int[] iArr4 = data2.fmap;
        byte[] bArr = data2.block;
        iArr[0] = i;
        iArr2[0] = i2;
        iArr3[0] = i3;
        int i8 = 1;
        while (true) {
            int i9 = i8 - 1;
            if (i9 < 0) {
                return;
            }
            int i10 = i8;
            int i11 = iArr[i9];
            int i12 = iArr2[i9];
            int i13 = iArr3[i9];
            if (i12 - i11 < 20) {
                blockSort = this;
                data2 = data;
                i5 = i4;
            } else if (i13 > 10) {
                i5 = i4;
            } else {
                int i14 = i13 + 1;
                int iMed3 = med3(bArr[iArr4[i11] + i14], bArr[iArr4[i12] + i14], bArr[iArr4[(i11 + i12) >>> 1] + i14]) & 255;
                int i15 = i11;
                int i16 = i15;
                int i17 = i12;
                int i18 = i17;
                while (true) {
                    if (i16 <= i17) {
                        int i19 = iArr4[i16];
                        int i20 = (bArr[i19 + i14] & 255) - iMed3;
                        if (i20 == 0) {
                            iArr4[i16] = iArr4[i15];
                            iArr4[i15] = i19;
                            i15++;
                            i16++;
                        } else if (i20 < 0) {
                            i16++;
                        }
                    }
                    i6 = i18;
                    while (true) {
                        if (i16 > i17) {
                            i7 = i12;
                            break;
                        }
                        int i21 = iArr4[i17];
                        i7 = i12;
                        int i22 = (bArr[i21 + i14] & 255) - iMed3;
                        if (i22 != 0) {
                            if (i22 <= 0) {
                                break;
                            } else {
                                i17--;
                            }
                        } else {
                            iArr4[i17] = iArr4[i6];
                            iArr4[i6] = i21;
                            i6--;
                            i17--;
                        }
                        i12 = i7;
                    }
                    if (i16 > i17) {
                        break;
                    }
                    int i23 = iArr4[i16];
                    iArr4[i16] = iArr4[i17];
                    iArr4[i17] = i23;
                    i12 = i7;
                    i17--;
                    i16++;
                    i18 = i6;
                }
                if (i6 < i15) {
                    iArr[i9] = i11;
                    iArr2[i9] = i7;
                    iArr3[i9] = i14;
                    i8 = i10;
                } else {
                    int i24 = i15 - i11;
                    int i25 = i16 - i15;
                    if (i24 >= i25) {
                        i24 = i25;
                    }
                    vswap(iArr4, i11, i16 - i24, i24);
                    int i26 = i7 - i6;
                    int i27 = i6 - i17;
                    if (i26 >= i27) {
                        i26 = i27;
                    }
                    vswap(iArr4, i16, (i7 - i26) + 1, i26);
                    int i28 = (i16 + i11) - i15;
                    int i29 = i7 - i27;
                    iArr[i9] = i11;
                    iArr2[i9] = i28 - 1;
                    iArr3[i9] = i13;
                    iArr[i10] = i28;
                    iArr2[i10] = i29;
                    iArr3[i10] = i14;
                    int i30 = i10 + 1;
                    iArr[i30] = i29 + 1;
                    iArr2[i30] = i7;
                    iArr3[i30] = i13;
                    i8 = i10 + 2;
                }
                blockSort = this;
                data2 = data;
            }
            if (blockSort.mainSimpleSort(data2, i11, i12, i13, i5)) {
                return;
            }
            i8 = i9;
            blockSort = this;
            data2 = data;
        }
    }

    public final boolean mainSimpleSort(BZip2CompressorOutputStream.Data data, int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2;
        byte[] bArr;
        int[] iArr;
        int i5;
        int i6;
        int i7 = (i2 - i) + 1;
        if (i7 < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int i8 = 0;
        while (INCS[i8] < i7) {
            i8++;
        }
        int[] iArr2 = data.fmap;
        char[] cArr = this.quadrant;
        byte[] bArr2 = data.block;
        int i9 = i4 + 1;
        boolean z3 = this.firstAttempt;
        int i10 = this.workLimit;
        int i11 = this.workDone;
        loop1: while (true) {
            i8--;
            if (i8 < 0) {
                z = true;
                z2 = false;
                break;
            }
            int i12 = INCS[i8];
            int i13 = i + i12;
            int i14 = i13 - 1;
            while (i13 <= i2) {
                int i15 = 3;
                while (i13 <= i2) {
                    i15--;
                    if (i15 < 0) {
                        break;
                    }
                    int i16 = iArr2[i13];
                    int i17 = i16 + i3;
                    int i18 = i13;
                    boolean z4 = false;
                    int i19 = 0;
                    while (true) {
                        if (z4) {
                            iArr2[i18] = i19;
                            i5 = i18 - i12;
                            if (i5 <= i14) {
                                bArr = bArr2;
                                iArr = iArr2;
                                break;
                            }
                            i18 = i5;
                        } else {
                            z4 = true;
                        }
                        int i20 = iArr2[i18 - i12];
                        int i21 = i20 + i3;
                        byte b = bArr2[i21 + 1];
                        bArr = bArr2;
                        byte b2 = bArr[i17 + 1];
                        if (b != b2) {
                            iArr = iArr2;
                            i6 = i20;
                            if ((b & 255) <= (b2 & 255)) {
                                break;
                            }
                            bArr2 = bArr;
                            i19 = i6;
                            iArr2 = iArr;
                        } else {
                            byte b3 = bArr[i21 + 2];
                            byte b4 = bArr[i17 + 2];
                            if (b3 != b4) {
                                iArr = iArr2;
                                i6 = i20;
                                if ((b3 & 255) <= (b4 & 255)) {
                                    break;
                                }
                                bArr2 = bArr;
                                i19 = i6;
                                iArr2 = iArr;
                            } else {
                                byte b5 = bArr[i21 + 3];
                                byte b6 = bArr[i17 + 3];
                                if (b5 != b6) {
                                    iArr = iArr2;
                                    i6 = i20;
                                    if ((b5 & 255) <= (b6 & 255)) {
                                        break;
                                    }
                                    bArr2 = bArr;
                                    i19 = i6;
                                    iArr2 = iArr;
                                } else {
                                    byte b7 = bArr[i21 + 4];
                                    byte b8 = bArr[i17 + 4];
                                    if (b7 != b8) {
                                        iArr = iArr2;
                                        i6 = i20;
                                        if ((b7 & 255) <= (b8 & 255)) {
                                            break;
                                        }
                                        bArr2 = bArr;
                                        i19 = i6;
                                        iArr2 = iArr;
                                    } else {
                                        byte b9 = bArr[i21 + 5];
                                        byte b10 = bArr[i17 + 5];
                                        if (b9 != b10) {
                                            iArr = iArr2;
                                            i6 = i20;
                                            if ((b9 & 255) <= (b10 & 255)) {
                                                break;
                                            }
                                            bArr2 = bArr;
                                            i19 = i6;
                                            iArr2 = iArr;
                                        } else {
                                            int i22 = i21 + 6;
                                            byte b11 = bArr[i22];
                                            int i23 = i17 + 6;
                                            iArr = iArr2;
                                            byte b12 = bArr[i23];
                                            if (b11 != b12) {
                                                i6 = i20;
                                                if ((b11 & 255) <= (b12 & 255)) {
                                                    break;
                                                }
                                                bArr2 = bArr;
                                                i19 = i6;
                                                iArr2 = iArr;
                                            } else {
                                                int i24 = i4;
                                                while (true) {
                                                    if (i24 <= 0) {
                                                        break;
                                                    }
                                                    int i25 = i24 - 4;
                                                    int i26 = i22 + 1;
                                                    byte b13 = bArr[i26];
                                                    int i27 = i23 + 1;
                                                    byte b14 = bArr[i27];
                                                    if (b13 != b14) {
                                                        i6 = i20;
                                                        if ((b13 & 255) <= (b14 & 255)) {
                                                            break;
                                                        }
                                                    } else {
                                                        char c = cArr[i22];
                                                        char c2 = cArr[i23];
                                                        if (c != c2) {
                                                            i6 = i20;
                                                            if (c <= c2) {
                                                                break;
                                                            }
                                                        } else {
                                                            int i28 = i22 + 2;
                                                            byte b15 = bArr[i28];
                                                            int i29 = i23 + 2;
                                                            byte b16 = bArr[i29];
                                                            if (b15 != b16) {
                                                                i6 = i20;
                                                                if ((b15 & 255) <= (b16 & 255)) {
                                                                    break;
                                                                }
                                                            } else {
                                                                char c3 = cArr[i26];
                                                                char c4 = cArr[i27];
                                                                if (c3 != c4) {
                                                                    i6 = i20;
                                                                    if (c3 <= c4) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    int i30 = i22 + 3;
                                                                    byte b17 = bArr[i30];
                                                                    int i31 = i23 + 3;
                                                                    byte b18 = bArr[i31];
                                                                    if (b17 != b18) {
                                                                        i6 = i20;
                                                                        if ((b17 & 255) <= (b18 & 255)) {
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        char c5 = cArr[i28];
                                                                        char c6 = cArr[i29];
                                                                        if (c5 != c6) {
                                                                            i6 = i20;
                                                                            if (c5 <= c6) {
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            int i32 = i22 + 4;
                                                                            byte b19 = bArr[i32];
                                                                            i23 += 4;
                                                                            i6 = i20;
                                                                            byte b20 = bArr[i23];
                                                                            if (b19 != b20) {
                                                                                if ((b19 & 255) <= (b20 & 255)) {
                                                                                    break;
                                                                                }
                                                                            } else {
                                                                                char c7 = cArr[i30];
                                                                                char c8 = cArr[i31];
                                                                                if (c7 != c8) {
                                                                                    if (c7 <= c8) {
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    if (i32 >= i9) {
                                                                                        i32 -= i9;
                                                                                    }
                                                                                    if (i23 >= i9) {
                                                                                        i23 -= i9;
                                                                                    }
                                                                                    i11++;
                                                                                    i20 = i6;
                                                                                    i22 = i32;
                                                                                    i24 = i25;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                bArr2 = bArr;
                                                i19 = i6;
                                                iArr2 = iArr;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i5 = i18;
                    iArr[i5] = i16;
                    i13++;
                    bArr2 = bArr;
                    iArr2 = iArr;
                }
                byte[] bArr3 = bArr2;
                int[] iArr3 = iArr2;
                z = true;
                z2 = false;
                if (z3 && i13 <= i2 && i11 > i10) {
                    break loop1;
                }
                bArr2 = bArr3;
                iArr2 = iArr3;
            }
        }
        this.workDone = i11;
        return (!z3 || i11 <= i10) ? z2 : z;
    }

    public final void mainSort(BZip2CompressorOutputStream.Data data, int i) {
        int i2;
        int[] iArr;
        int i3;
        int i4;
        BZip2CompressorOutputStream.Data data2 = data;
        int i5 = i;
        int[] iArr2 = this.mainSort_runningOrder;
        int[] iArr3 = this.mainSort_copy;
        boolean[] zArr = this.mainSort_bigDone;
        int[] iArr4 = this.ftab;
        byte[] bArr = data2.block;
        int[] iArr5 = data2.fmap;
        char[] cArr = this.quadrant;
        int i6 = this.workLimit;
        boolean z = this.firstAttempt;
        int i7 = 65537;
        while (true) {
            i7--;
            if (i7 < 0) {
                break;
            } else {
                iArr4[i7] = 0;
            }
        }
        for (int i8 = 0; i8 < 20; i8++) {
            bArr[i5 + i8 + 2] = bArr[(i8 % (i5 + 1)) + 1];
        }
        int i9 = i5 + 21;
        while (true) {
            i9--;
            if (i9 < 0) {
                break;
            } else {
                cArr[i9] = 0;
            }
        }
        int i10 = i5 + 1;
        byte b = bArr[i10];
        bArr[0] = b;
        int i11 = 255;
        int i12 = b & 255;
        int i13 = 0;
        while (i13 <= i5) {
            i13++;
            int i14 = bArr[i13] & 255;
            int i15 = (i12 << 8) + i14;
            iArr4[i15] = iArr4[i15] + 1;
            i12 = i14;
        }
        for (int i16 = 1; i16 <= 65536; i16++) {
            iArr4[i16] = iArr4[i16] + iArr4[i16 - 1];
        }
        int i17 = bArr[1] & 255;
        int i18 = 0;
        while (i18 < i5) {
            int i19 = bArr[i18 + 2] & 255;
            int i20 = (i17 << 8) + i19;
            int i21 = iArr4[i20] - 1;
            iArr4[i20] = i21;
            iArr5[i21] = i18;
            i18++;
            i17 = i19;
        }
        int i22 = ((bArr[i10] & 255) << 8) + (bArr[1] & 255);
        int i23 = iArr4[i22] - 1;
        iArr4[i22] = i23;
        iArr5[i23] = i5;
        int i24 = 256;
        while (true) {
            i24--;
            if (i24 < 0) {
                break;
            }
            zArr[i24] = false;
            iArr2[i24] = i24;
        }
        int i25 = 364;
        while (i25 != 1) {
            i25 /= 3;
            int i26 = i25;
            while (i26 <= i11) {
                int i27 = iArr2[i26];
                int i28 = iArr4[(i27 + 1) << 8] - iArr4[i27 << 8];
                int i29 = i25 - 1;
                int i30 = iArr2[i26 - i25];
                int i31 = i26;
                while (true) {
                    i4 = i25;
                    if (iArr4[(i30 + 1) << 8] - iArr4[i30 << 8] > i28) {
                        iArr2[i31] = i30;
                        int i32 = i31 - i4;
                        if (i32 <= i29) {
                            i31 = i32;
                            break;
                        } else {
                            i30 = iArr2[i32 - i4];
                            i31 = i32;
                            i25 = i4;
                        }
                    }
                }
                iArr2[i31] = i27;
                i26++;
                i25 = i4;
                i11 = 255;
            }
        }
        int i33 = 0;
        while (true) {
            if (i33 > 255) {
                return;
            }
            int i34 = iArr2[i33];
            int i35 = 0;
            for (int i36 = 255; i35 <= i36; i36 = 255) {
                int i37 = (i34 << 8) + i35;
                int i38 = iArr4[i37];
                if ((i38 & 2097152) != 2097152) {
                    int i39 = i35;
                    int i40 = i38 & CLEARMASK;
                    int i41 = (iArr4[i37 + 1] & CLEARMASK) - 1;
                    if (i41 > i40) {
                        i3 = 2097152;
                        i2 = i39;
                        iArr = iArr2;
                        mainQSort3(data2, i40, i41, 2, i5);
                        if (z && this.workDone > i6) {
                            return;
                        }
                    } else {
                        i2 = i39;
                        iArr = iArr2;
                        i3 = 2097152;
                    }
                    iArr4[i37] = i38 | i3;
                } else {
                    i2 = i35;
                    iArr = iArr2;
                }
                i35 = i2 + 1;
                data2 = data;
                i5 = i;
                iArr2 = iArr;
            }
            int[] iArr6 = iArr2;
            for (int i42 = 0; i42 <= 255; i42++) {
                iArr3[i42] = iArr4[(i42 << 8) + i34] & CLEARMASK;
            }
            int i43 = i34 << 8;
            int i44 = (i34 + 1) << 8;
            int i45 = iArr4[i44] & CLEARMASK;
            for (int i46 = iArr4[i43] & CLEARMASK; i46 < i45; i46++) {
                int i47 = iArr5[i46];
                int i48 = bArr[i47] & 255;
                if (!zArr[i48]) {
                    iArr5[iArr3[i48]] = i47 == 0 ? i : i47 - 1;
                    iArr3[i48] = iArr3[i48] + 1;
                }
            }
            int i49 = 256;
            while (true) {
                i49--;
                if (i49 < 0) {
                    break;
                }
                int i50 = (i49 << 8) + i34;
                iArr4[i50] = iArr4[i50] | 2097152;
            }
            zArr[i34] = true;
            if (i33 < 255) {
                int i51 = iArr4[i43] & CLEARMASK;
                int i52 = (iArr4[i44] & CLEARMASK) - i51;
                int i53 = 0;
                while ((i52 >> i53) > 65534) {
                    i53++;
                }
                for (int i54 = 0; i54 < i52; i54++) {
                    int i55 = iArr5[i51 + i54];
                    char c = (char) (i54 >> i53);
                    cArr[i55] = c;
                    if (i55 < 20) {
                        cArr[i55 + i + 1] = c;
                    }
                }
            }
            i33++;
            data2 = data;
            i5 = i;
            iArr2 = iArr6;
        }
    }

    public final void fallbackSort(int[] iArr, byte[] bArr, int i) {
        int i2;
        int[] iArr2 = new int[257];
        int[] eclass = getEclass();
        for (int i3 = 0; i3 < i; i3++) {
            eclass[i3] = 0;
        }
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = bArr[i4] & 255;
            iArr2[i5] = iArr2[i5] + 1;
        }
        for (int i6 = 1; i6 < 257; i6++) {
            iArr2[i6] = iArr2[i6] + iArr2[i6 - 1];
        }
        for (int i7 = 0; i7 < i; i7++) {
            int i8 = bArr[i7] & 255;
            int i9 = iArr2[i8] - 1;
            iArr2[i8] = i9;
            iArr[i9] = i7;
        }
        BitSet bitSet = new BitSet(i + 64);
        for (int i10 = 0; i10 < 256; i10++) {
            bitSet.set(iArr2[i10]);
        }
        for (int i11 = 0; i11 < 32; i11++) {
            int i12 = (i11 * 2) + i;
            bitSet.set(i12);
            bitSet.clear(i12 + 1);
        }
        int i13 = 1;
        do {
            int i14 = 0;
            for (int i15 = 0; i15 < i; i15++) {
                if (bitSet.get(i15)) {
                    i14 = i15;
                }
                int i16 = iArr[i15] - i13;
                if (i16 < 0) {
                    i16 += i;
                }
                eclass[i16] = i14;
            }
            int iNextSetBit = -1;
            i2 = 0;
            while (true) {
                int iNextClearBit = bitSet.nextClearBit(iNextSetBit + 1);
                int i17 = iNextClearBit - 1;
                if (i17 >= i || (iNextSetBit = bitSet.nextSetBit(iNextClearBit + 1) - 1) >= i) {
                    break;
                }
                if (iNextSetBit > i17) {
                    i2 += (iNextSetBit - i17) + 1;
                    fallbackQSort3(iArr, eclass, i17, iNextSetBit);
                    int i18 = -1;
                    while (i17 <= iNextSetBit) {
                        int i19 = eclass[iArr[i17]];
                        if (i18 != i19) {
                            bitSet.set(i17);
                            i18 = i19;
                        }
                        i17++;
                    }
                }
            }
            i13 *= 2;
            if (i13 > i) {
                return;
            }
        } while (i2 != 0);
    }
}
