package org.apache.commons.compress.compressors.bzip2;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import org.apache.commons.compress.compressors.CompressorInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class BZip2CompressorInputStream extends CompressorInputStream implements BZip2Constants {
    public static final int EOF = 0;
    public static final int NO_RAND_PART_A_STATE = 5;
    public static final int NO_RAND_PART_B_STATE = 6;
    public static final int NO_RAND_PART_C_STATE = 7;
    public static final int RAND_PART_A_STATE = 2;
    public static final int RAND_PART_B_STATE = 3;
    public static final int RAND_PART_C_STATE = 4;
    public static final int START_BLOCK_STATE = 1;
    public boolean blockRandomised;
    public int blockSize100k;
    public int bsBuff;
    public int bsLive;
    public int computedBlockCRC;
    public int computedCombinedCRC;
    public final CRC crc;
    public int currentChar;
    public int currentState;
    public Data data;
    public final boolean decompressConcatenated;
    public InputStream in;
    public int last;
    public int nInUse;
    public int origPtr;
    public int storedBlockCRC;
    public int storedCombinedCRC;
    public int su_ch2;
    public int su_chPrev;
    public int su_count;
    public int su_i2;
    public int su_j2;
    public int su_rNToGo;
    public int su_rTPos;
    public int su_tPos;
    public char su_z;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Data {
        public final int[][] base;
        public final int[] cftab;
        public final char[] getAndMoveToFrontDecode_yy;
        public final int[][] limit;
        public byte[] ll8;
        public final int[] minLens;
        public final int[][] perm;
        public final byte[] recvDecodingTables_pos;
        public final char[][] temp_charArray2d;
        public int[] tt;
        public final boolean[] inUse = new boolean[256];
        public final byte[] seqToUnseq = new byte[256];
        public final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        public final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        public final int[] unzftab = new int[256];

        public Data(int i) {
            int[] iArr = {6, BZip2Constants.MAX_ALPHA_SIZE};
            Class cls = Integer.TYPE;
            this.limit = (int[][]) Array.newInstance((Class<?>) cls, iArr);
            this.base = (int[][]) Array.newInstance((Class<?>) cls, 6, BZip2Constants.MAX_ALPHA_SIZE);
            this.perm = (int[][]) Array.newInstance((Class<?>) cls, 6, BZip2Constants.MAX_ALPHA_SIZE);
            this.minLens = new int[6];
            this.cftab = new int[257];
            this.getAndMoveToFrontDecode_yy = new char[256];
            this.temp_charArray2d = (char[][]) Array.newInstance((Class<?>) Character.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
            this.recvDecodingTables_pos = new byte[6];
            this.ll8 = new byte[i * 100000];
        }

        public int[] initTT(int i) {
            int[] iArr = this.tt;
            if (iArr != null && iArr.length >= i) {
                return iArr;
            }
            int[] iArr2 = new int[i];
            this.tt = iArr2;
            return iArr2;
        }
    }

    public BZip2CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public static void hbCreateDecodeTables(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        for (int i6 = i; i6 <= i2; i6++) {
            for (int i7 = 0; i7 < i3; i7++) {
                if (cArr[i7] == i6) {
                    iArr3[i5] = i7;
                    i5++;
                }
            }
        }
        int i8 = 23;
        while (true) {
            i8--;
            if (i8 <= 0) {
                break;
            }
            iArr2[i8] = 0;
            iArr[i8] = 0;
        }
        for (int i9 = 0; i9 < i3; i9++) {
            int i10 = cArr[i9] + 1;
            iArr2[i10] = iArr2[i10] + 1;
        }
        int i11 = iArr2[0];
        for (int i12 = 1; i12 < 23; i12++) {
            i11 += iArr2[i12];
            iArr2[i12] = i11;
        }
        int i13 = iArr2[i];
        int i14 = i;
        while (i14 <= i2) {
            int i15 = i14 + 1;
            int i16 = iArr2[i15];
            int i17 = (i16 - i13) + i4;
            iArr[i14] = i17 - 1;
            i4 = i17 << 1;
            i14 = i15;
            i13 = i16;
        }
        for (int i18 = i + 1; i18 <= i2; i18++) {
            iArr2[i18] = ((iArr[i18 - 1] + 1) << 1) - iArr2[i18];
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        return i >= 3 && bArr[0] == 66 && bArr[1] == 90 && bArr[2] == 104;
    }

    public final boolean bsGetBit() throws IOException {
        int i = this.bsLive;
        int i2 = this.bsBuff;
        if (i < 1) {
            int i3 = this.in.read();
            if (i3 < 0) {
                throw new IOException("unexpected end of stream");
            }
            i2 = (i2 << 8) | i3;
            i += 8;
            this.bsBuff = i2;
        }
        int i4 = i - 1;
        this.bsLive = i4;
        return ((i2 >> i4) & 1) != 0;
    }

    public final int bsGetInt() throws IOException {
        return bsR(8) | (((((bsR(8) << 8) | bsR(8)) << 8) | bsR(8)) << 8);
    }

    public final char bsGetUByte() throws IOException {
        return (char) bsR(8);
    }

    public final int bsR(int i) throws IOException {
        int i2 = this.bsLive;
        int i3 = this.bsBuff;
        if (i2 < i) {
            InputStream inputStream = this.in;
            do {
                int i4 = inputStream.read();
                if (i4 < 0) {
                    throw new IOException("unexpected end of stream");
                }
                i3 = (i3 << 8) | i4;
                i2 += 8;
            } while (i2 < i);
            this.bsBuff = i3;
        }
        int i5 = i2 - i;
        this.bsLive = i5;
        return ((1 << i) - 1) & (i3 >> i5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                if (inputStream != System.in) {
                    inputStream.close();
                }
            } finally {
                this.data = null;
                this.in = null;
            }
        }
    }

    public final boolean complete() throws IOException {
        int iBsGetInt = bsGetInt();
        this.storedCombinedCRC = iBsGetInt;
        this.currentState = 0;
        this.data = null;
        if (iBsGetInt == this.computedCombinedCRC) {
            return (this.decompressConcatenated && init(false)) ? false : true;
        }
        throw new IOException("BZip2 CRC error");
    }

    public final void createHuffmanDecodingTables(int i, int i2) {
        Data data = this.data;
        char[][] cArr = data.temp_charArray2d;
        int[] iArr = data.minLens;
        int[][] iArr2 = data.limit;
        int[][] iArr3 = data.base;
        int[][] iArr4 = data.perm;
        for (int i3 = 0; i3 < i2; i3++) {
            char[] cArr2 = cArr[i3];
            int i4 = i;
            char c = 0;
            char c2 = ' ';
            while (true) {
                i4--;
                if (i4 >= 0) {
                    char c3 = cArr2[i4];
                    if (c3 > c) {
                        c = c3;
                    }
                    if (c3 < c2) {
                        c2 = c3;
                    }
                }
            }
            hbCreateDecodeTables(iArr2[i3], iArr3[i3], iArr4[i3], cArr[i3], c2, c, i);
            iArr[i3] = c2;
        }
    }

    public final void endBlock() throws IOException {
        int finalCRC = this.crc.getFinalCRC();
        this.computedBlockCRC = finalCRC;
        int i = this.storedBlockCRC;
        if (i == finalCRC) {
            int i2 = this.computedCombinedCRC;
            this.computedCombinedCRC = finalCRC ^ ((i2 >>> 31) | (i2 << 1));
        } else {
            int i3 = this.storedCombinedCRC;
            this.computedCombinedCRC = ((i3 >>> 31) | (i3 << 1)) ^ i;
            throw new IOException("BZip2 CRC error");
        }
    }

    public final void getAndMoveToFrontDecode() throws IOException {
        int i;
        char c;
        this.origPtr = bsR(24);
        recvDecodingTables();
        InputStream inputStream = this.in;
        Data data = this.data;
        byte[] bArr = data.ll8;
        int[] iArr = data.unzftab;
        byte[] bArr2 = data.selector;
        byte[] bArr3 = data.seqToUnseq;
        char[] cArr = data.getAndMoveToFrontDecode_yy;
        int[] iArr2 = data.minLens;
        int[][] iArr3 = data.limit;
        int[][] iArr4 = data.base;
        int[][] iArr5 = data.perm;
        int i2 = this.blockSize100k * 100000;
        int i3 = 256;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            cArr[i3] = (char) i3;
            iArr[i3] = 0;
        }
        int i4 = this.nInUse + 1;
        int andMoveToFrontDecode0 = getAndMoveToFrontDecode0(0);
        int i5 = this.bsBuff;
        int i6 = this.bsLive;
        int i7 = bArr2[0] & 255;
        int[] iArr6 = iArr4[i7];
        int[] iArr7 = iArr3[i7];
        int[] iArr8 = iArr5[i7];
        int i8 = iArr2[i7];
        int i9 = andMoveToFrontDecode0;
        int i10 = -1;
        int i11 = 0;
        int i12 = 49;
        while (i9 != i4) {
            byte[] bArr4 = bArr;
            int[] iArr9 = iArr;
            byte[] bArr5 = bArr2;
            if (i9 == 0 || i9 == 1) {
                int i13 = 1;
                int i14 = -1;
                while (true) {
                    if (i9 == 0) {
                        i14 += i13;
                        i = i13;
                    } else {
                        i = i13;
                        if (i9 == 1) {
                            i14 += i << 1;
                        } else {
                            byte b = bArr3[cArr[0]];
                            int i15 = b & 255;
                            iArr9[i15] = i14 + 1 + iArr9[i15];
                            while (true) {
                                int i16 = i14 - 1;
                                if (i14 < 0) {
                                    break;
                                }
                                i10++;
                                bArr4[i10] = b;
                                i14 = i16;
                            }
                            if (i10 >= i2) {
                                throw new IOException("block overrun");
                            }
                            bArr = bArr4;
                            iArr = iArr9;
                            bArr2 = bArr5;
                        }
                    }
                    if (i12 == 0) {
                        i11++;
                        int i17 = bArr5[i11] & 255;
                        iArr6 = iArr4[i17];
                        iArr7 = iArr3[i17];
                        iArr8 = iArr5[i17];
                        i8 = iArr2[i17];
                        i12 = 49;
                    } else {
                        i12--;
                    }
                    while (i6 < i8) {
                        int i18 = inputStream.read();
                        if (i18 < 0) {
                            throw new IOException("unexpected end of stream");
                        }
                        i5 = (i5 << 8) | i18;
                        i6 += 8;
                    }
                    i6 -= i8;
                    int i19 = (i5 >> i6) & ((1 << i8) - 1);
                    int i20 = i8;
                    while (i19 > iArr7[i20]) {
                        int i21 = i20 + 1;
                        int i22 = i19;
                        while (i6 < 1) {
                            int i23 = inputStream.read();
                            if (i23 < 0) {
                                throw new IOException("unexpected end of stream");
                            }
                            i5 = (i5 << 8) | i23;
                            i6 += 8;
                        }
                        i6--;
                        i19 = (i22 << 1) | ((i5 >> i6) & 1);
                        i20 = i21;
                    }
                    i9 = iArr8[i19 - iArr6[i20]];
                    i13 = i << 1;
                }
            } else {
                int i24 = i10 + 1;
                if (i24 >= i2) {
                    throw new IOException("block overrun");
                }
                int i25 = i9 - 1;
                char c2 = cArr[i25];
                byte b2 = bArr3[c2];
                int i26 = b2 & 255;
                iArr9[i26] = iArr9[i26] + 1;
                bArr4[i24] = b2;
                if (i9 <= 16) {
                    while (i25 > 0) {
                        int i27 = i25 - 1;
                        cArr[i25] = cArr[i27];
                        i25 = i27;
                    }
                    c = 0;
                } else {
                    c = 0;
                    System.arraycopy(cArr, 0, cArr, 1, i25);
                }
                cArr[c] = c2;
                if (i12 == 0) {
                    i11++;
                    int i28 = bArr5[i11] & 255;
                    int[] iArr10 = iArr4[i28];
                    int[] iArr11 = iArr3[i28];
                    int[] iArr12 = iArr5[i28];
                    i8 = iArr2[i28];
                    iArr6 = iArr10;
                    iArr7 = iArr11;
                    iArr8 = iArr12;
                    i12 = 49;
                } else {
                    i12--;
                }
                while (i6 < i8) {
                    int i29 = inputStream.read();
                    if (i29 < 0) {
                        throw new IOException("unexpected end of stream");
                    }
                    i5 = (i5 << 8) | i29;
                    i6 += 8;
                }
                i6 -= i8;
                int i30 = 1;
                int i31 = (i5 >> i6) & ((1 << i8) - 1);
                int i32 = i8;
                while (i31 > iArr7[i32]) {
                    i32++;
                    while (i6 < i30) {
                        int i33 = inputStream.read();
                        if (i33 < 0) {
                            throw new IOException("unexpected end of stream");
                        }
                        i5 = (i5 << 8) | i33;
                        i6 += 8;
                        i30 = 1;
                    }
                    i6--;
                    i31 = (i31 << 1) | ((i5 >> i6) & 1);
                    i30 = 1;
                }
                i9 = iArr8[i31 - iArr6[i32]];
                bArr = bArr4;
                iArr = iArr9;
                bArr2 = bArr5;
                i10 = i24;
            }
        }
        this.last = i10;
        this.bsLive = i6;
        this.bsBuff = i5;
    }

    public final int getAndMoveToFrontDecode0(int i) throws IOException {
        InputStream inputStream = this.in;
        Data data = this.data;
        int i2 = data.selector[i] & 255;
        int[] iArr = data.limit[i2];
        int i3 = data.minLens[i2];
        int iBsR = bsR(i3);
        int i4 = this.bsLive;
        int i5 = this.bsBuff;
        while (iBsR > iArr[i3]) {
            i3++;
            while (i4 < 1) {
                int i6 = inputStream.read();
                if (i6 < 0) {
                    throw new IOException("unexpected end of stream");
                }
                i5 = (i5 << 8) | i6;
                i4 += 8;
            }
            i4--;
            iBsR = (iBsR << 1) | (1 & (i5 >> i4));
        }
        this.bsLive = i4;
        this.bsBuff = i5;
        return data.perm[i2][iBsR - data.base[i2][i3]];
    }

    public final boolean init(boolean z) throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            throw new IOException("No InputStream");
        }
        int i = inputStream.read();
        if (i == -1 && !z) {
            return false;
        }
        int i2 = this.in.read();
        int i3 = this.in.read();
        if (i != 66 || i2 != 90 || i3 != 104) {
            throw new IOException(z ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream");
        }
        int i4 = this.in.read();
        if (i4 < 49 || i4 > 57) {
            throw new IOException("BZip2 block size is invalid");
        }
        this.blockSize100k = i4 - 48;
        this.bsLive = 0;
        this.computedCombinedCRC = 0;
        return true;
    }

    public final void initBlock() throws IOException {
        do {
            char cBsGetUByte = bsGetUByte();
            char cBsGetUByte2 = bsGetUByte();
            char cBsGetUByte3 = bsGetUByte();
            char cBsGetUByte4 = bsGetUByte();
            char cBsGetUByte5 = bsGetUByte();
            char cBsGetUByte6 = bsGetUByte();
            if (cBsGetUByte != 23 || cBsGetUByte2 != 'r' || cBsGetUByte3 != 'E' || cBsGetUByte4 != '8' || cBsGetUByte5 != 'P' || cBsGetUByte6 != 144) {
                if (cBsGetUByte != '1' || cBsGetUByte2 != 'A' || cBsGetUByte3 != 'Y' || cBsGetUByte4 != '&' || cBsGetUByte5 != 'S' || cBsGetUByte6 != 'Y') {
                    this.currentState = 0;
                    throw new IOException("bad block header");
                }
                this.storedBlockCRC = bsGetInt();
                this.blockRandomised = bsR(1) == 1;
                if (this.data == null) {
                    this.data = new Data(this.blockSize100k);
                }
                getAndMoveToFrontDecode();
                this.crc.initialiseCRC();
                this.currentState = 1;
                return;
            }
        } while (!complete());
    }

    public final void makeMaps() {
        Data data = this.data;
        boolean[] zArr = data.inUse;
        byte[] bArr = data.seqToUnseq;
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (zArr[i2]) {
                bArr[i] = (byte) i2;
                i++;
            }
        }
        this.nInUse = i;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.in == null) {
            throw new IOException("stream closed");
        }
        int i = read0();
        count(i < 0 ? -1 : 1);
        return i;
    }

    public final int read0() throws IOException {
        int i = this.currentChar;
        switch (this.currentState) {
            case 0:
                return -1;
            case 1:
                throw new IllegalStateException();
            case 2:
                throw new IllegalStateException();
            case 3:
                setupRandPartB();
                return i;
            case 4:
                setupRandPartC();
                return i;
            case 5:
                throw new IllegalStateException();
            case 6:
                setupNoRandPartB();
                return i;
            case 7:
                setupNoRandPartC();
                return i;
            default:
                throw new IllegalStateException();
        }
    }

    public final void recvDecodingTables() throws IOException {
        Data data = this.data;
        boolean[] zArr = data.inUse;
        byte[] bArr = data.recvDecodingTables_pos;
        byte[] bArr2 = data.selector;
        byte[] bArr3 = data.selectorMtf;
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            if (bsGetBit()) {
                i |= 1 << i2;
            }
        }
        int i3 = 256;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            } else {
                zArr[i3] = false;
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            if (((1 << i4) & i) != 0) {
                int i5 = i4 << 4;
                for (int i6 = 0; i6 < 16; i6++) {
                    if (bsGetBit()) {
                        zArr[i5 + i6] = true;
                    }
                }
            }
        }
        makeMaps();
        int i7 = this.nInUse + 2;
        int iBsR = bsR(3);
        int iBsR2 = bsR(15);
        for (int i8 = 0; i8 < iBsR2; i8++) {
            int i9 = 0;
            while (bsGetBit()) {
                i9++;
            }
            bArr3[i8] = (byte) i9;
        }
        int i10 = iBsR;
        while (true) {
            i10--;
            if (i10 < 0) {
                break;
            } else {
                bArr[i10] = (byte) i10;
            }
        }
        for (int i11 = 0; i11 < iBsR2; i11++) {
            int i12 = bArr3[i11] & 255;
            byte b = bArr[i12];
            while (i12 > 0) {
                bArr[i12] = bArr[i12 - 1];
                i12--;
            }
            bArr[0] = b;
            bArr2[i11] = b;
        }
        char[][] cArr = data.temp_charArray2d;
        for (int i13 = 0; i13 < iBsR; i13++) {
            int iBsR3 = bsR(5);
            char[] cArr2 = cArr[i13];
            for (int i14 = 0; i14 < i7; i14++) {
                while (bsGetBit()) {
                    iBsR3 += bsGetBit() ? -1 : 1;
                }
                cArr2[i14] = (char) iBsR3;
            }
        }
        createHuffmanDecodingTables(i7, iBsR);
    }

    public final void setupBlock() throws IOException {
        Data data = this.data;
        if (data == null) {
            return;
        }
        int[] iArr = data.cftab;
        int[] iArrInitTT = data.initTT(this.last + 1);
        Data data2 = this.data;
        byte[] bArr = data2.ll8;
        iArr[0] = 0;
        System.arraycopy(data2.unzftab, 0, iArr, 1, 256);
        int i = iArr[0];
        for (int i2 = 1; i2 <= 256; i2++) {
            i += iArr[i2];
            iArr[i2] = i;
        }
        int i3 = this.last;
        for (int i4 = 0; i4 <= i3; i4++) {
            int i5 = bArr[i4] & 255;
            int i6 = iArr[i5];
            iArr[i5] = i6 + 1;
            iArrInitTT[i6] = i4;
        }
        int i7 = this.origPtr;
        if (i7 < 0 || i7 >= iArrInitTT.length) {
            throw new IOException("stream corrupted");
        }
        this.su_tPos = iArrInitTT[i7];
        this.su_count = 0;
        this.su_i2 = 0;
        this.su_ch2 = 256;
        if (!this.blockRandomised) {
            setupNoRandPartA();
            return;
        }
        this.su_rNToGo = 0;
        this.su_rTPos = 0;
        setupRandPartA();
    }

    public final void setupNoRandPartA() throws IOException {
        int i = this.su_i2;
        if (i > this.last) {
            this.currentState = 5;
            endBlock();
            initBlock();
            setupBlock();
            return;
        }
        this.su_chPrev = this.su_ch2;
        Data data = this.data;
        byte[] bArr = data.ll8;
        int i2 = this.su_tPos;
        int i3 = bArr[i2] & 255;
        this.su_ch2 = i3;
        this.su_tPos = data.tt[i2];
        this.su_i2 = i + 1;
        this.currentChar = i3;
        this.currentState = 6;
        this.crc.updateCRC(i3);
    }

    public final void setupNoRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.su_count = 1;
            setupNoRandPartA();
            return;
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i < 4) {
            setupNoRandPartA();
            return;
        }
        Data data = this.data;
        byte[] bArr = data.ll8;
        int i2 = this.su_tPos;
        this.su_z = (char) (bArr[i2] & 255);
        this.su_tPos = data.tt[i2];
        this.su_j2 = 0;
        setupNoRandPartC();
    }

    public final void setupNoRandPartC() throws IOException {
        if (this.su_j2 >= this.su_z) {
            this.su_i2++;
            this.su_count = 0;
            setupNoRandPartA();
        } else {
            int i = this.su_ch2;
            this.currentChar = i;
            this.crc.updateCRC(i);
            this.su_j2++;
            this.currentState = 7;
        }
    }

    public final void setupRandPartA() throws IOException {
        int i = this.su_i2;
        if (i > this.last) {
            endBlock();
            initBlock();
            setupBlock();
            return;
        }
        this.su_chPrev = this.su_ch2;
        Data data = this.data;
        byte[] bArr = data.ll8;
        int i2 = this.su_tPos;
        int i3 = bArr[i2] & 255;
        this.su_tPos = data.tt[i2];
        int i4 = this.su_rNToGo;
        if (i4 == 0) {
            int i5 = this.su_rTPos;
            this.su_rNToGo = Rand.RNUMS[i5] - 1;
            int i6 = i5 + 1;
            this.su_rTPos = i6;
            if (i6 == 512) {
                this.su_rTPos = 0;
            }
        } else {
            this.su_rNToGo = i4 - 1;
        }
        int i7 = i3 ^ (this.su_rNToGo == 1 ? 1 : 0);
        this.su_ch2 = i7;
        this.su_i2 = i + 1;
        this.currentChar = i7;
        this.currentState = 3;
        this.crc.updateCRC(i7);
    }

    public final void setupRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.currentState = 2;
            this.su_count = 1;
            setupRandPartA();
            return;
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i < 4) {
            this.currentState = 2;
            setupRandPartA();
            return;
        }
        Data data = this.data;
        byte[] bArr = data.ll8;
        int i2 = this.su_tPos;
        char c = (char) (bArr[i2] & 255);
        this.su_z = c;
        this.su_tPos = data.tt[i2];
        int i3 = this.su_rNToGo;
        if (i3 == 0) {
            int i4 = this.su_rTPos;
            this.su_rNToGo = Rand.RNUMS[i4] - 1;
            int i5 = i4 + 1;
            this.su_rTPos = i5;
            if (i5 == 512) {
                this.su_rTPos = 0;
            }
        } else {
            this.su_rNToGo = i3 - 1;
        }
        this.su_j2 = 0;
        this.currentState = 4;
        if (this.su_rNToGo == 1) {
            this.su_z = (char) (c ^ 1);
        }
        setupRandPartC();
    }

    public final void setupRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            int i = this.su_ch2;
            this.currentChar = i;
            this.crc.updateCRC(i);
            this.su_j2++;
            return;
        }
        this.currentState = 2;
        this.su_i2++;
        this.su_count = 0;
        setupRandPartA();
    }

    public BZip2CompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        this.crc = new CRC();
        this.currentChar = -1;
        this.currentState = 1;
        this.in = inputStream;
        this.decompressConcatenated = z;
        init(true);
        initBlock();
        setupBlock();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("offs(", i, ") < 0."));
        }
        if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 <= bArr.length) {
                if (this.in != null) {
                    int i4 = i;
                    while (i4 < i3) {
                        int i5 = read0();
                        if (i5 < 0) {
                            break;
                        }
                        bArr[i4] = (byte) i5;
                        i4++;
                    }
                    int i6 = i4 == i ? -1 : i4 - i;
                    count(i6);
                    return i6;
                }
                throw new IOException("stream closed");
            }
            throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(MutableFloatList$$ExternalSyntheticOutline0.m("offs(", i, ") + len(", i2, ") > dest.length("), bArr.length, ")."));
        }
        throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("len(", i2, ") < 0."));
    }
}
