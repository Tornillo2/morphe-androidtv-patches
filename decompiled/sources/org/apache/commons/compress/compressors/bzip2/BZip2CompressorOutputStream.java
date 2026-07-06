package org.apache.commons.compress.compressors.bzip2;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import org.apache.commons.compress.compressors.CompressorOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class BZip2CompressorOutputStream extends CompressorOutputStream implements BZip2Constants {
    public static final int GREATER_ICOST = 15;
    public static final int LESSER_ICOST = 0;
    public static final int MAX_BLOCKSIZE = 9;
    public static final int MIN_BLOCKSIZE = 1;
    public final int allowableBlockSize;
    public int blockCRC;
    public final int blockSize100k;
    public BlockSort blockSorter;
    public int bsBuff;
    public int bsLive;
    public int combinedCRC;
    public final CRC crc;
    public int currentChar;
    public Data data;
    public int last;
    public int nInUse;
    public int nMTF;
    public OutputStream out;
    public int runLength;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Data {
        public final byte[] block;
        public final int[] fmap;
        public final int[] heap;
        public int origPtr;
        public final int[] parent;
        public final byte[] sendMTFValues2_pos;
        public final int[][] sendMTFValues_code;
        public final short[] sendMTFValues_cost;
        public final int[] sendMTFValues_fave;
        public final int[][] sendMTFValues_rfreq;
        public final boolean[] sentMTFValues4_inUse16;
        public final char[] sfmap;
        public final int[] weight;
        public final boolean[] inUse = new boolean[256];
        public final byte[] unseqToSeq = new byte[256];
        public final int[] mtfFreq = new int[BZip2Constants.MAX_ALPHA_SIZE];
        public final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        public final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        public final byte[] generateMTFValues_yy = new byte[256];
        public final byte[][] sendMTFValues_len = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);

        public Data(int i) {
            int[] iArr = {6, BZip2Constants.MAX_ALPHA_SIZE};
            Class cls = Integer.TYPE;
            this.sendMTFValues_rfreq = (int[][]) Array.newInstance((Class<?>) cls, iArr);
            this.sendMTFValues_fave = new int[6];
            this.sendMTFValues_cost = new short[6];
            this.sendMTFValues_code = (int[][]) Array.newInstance((Class<?>) cls, 6, BZip2Constants.MAX_ALPHA_SIZE);
            this.sendMTFValues2_pos = new byte[6];
            this.sentMTFValues4_inUse16 = new boolean[16];
            this.heap = new int[260];
            this.weight = new int[516];
            this.parent = new int[516];
            int i2 = 100000 * i;
            this.block = new byte[i2 + 21];
            this.fmap = new int[i2];
            this.sfmap = new char[i * 200000];
        }
    }

    public BZip2CompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.crc = new CRC();
        this.currentChar = -1;
        this.runLength = 0;
        if (i < 1) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("blockSize(", i, ") < 1"));
        }
        if (i > 9) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("blockSize(", i, ") > 9"));
        }
        this.blockSize100k = i;
        this.out = outputStream;
        this.allowableBlockSize = (i * 100000) - 20;
        init();
    }

    public static int chooseBlockSize(long j) {
        if (j > 0) {
            return (int) Math.min((j / 132000) + 1, 9L);
        }
        return 9;
    }

    private void endBlock() throws IOException {
        int finalCRC = this.crc.getFinalCRC();
        this.blockCRC = finalCRC;
        int i = this.combinedCRC;
        this.combinedCRC = finalCRC ^ ((i >>> 31) | (i << 1));
        if (this.last == -1) {
            return;
        }
        blockSort();
        bsPutUByte(49);
        bsPutUByte(65);
        bsPutUByte(89);
        bsPutUByte(38);
        bsPutUByte(83);
        bsPutUByte(89);
        bsPutInt(this.blockCRC);
        bsW(1, 0);
        moveToFrontCodeAndSend();
    }

    public static void hbAssignCodes(int[] iArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            for (int i5 = 0; i5 < i3; i5++) {
                if ((bArr[i5] & 255) == i) {
                    iArr[i5] = i4;
                    i4++;
                }
            }
            i4 <<= 1;
            i++;
        }
    }

    public static void hbMakeCodeLengths(byte[] bArr, int[] iArr, Data data, int i, int i2) {
        int i3;
        int[] iArr2 = data.heap;
        int[] iArr3 = data.weight;
        int[] iArr4 = data.parent;
        int i4 = i;
        while (true) {
            int i5 = i4 - 1;
            i3 = 1;
            if (i5 < 0) {
                break;
            }
            int i6 = iArr[i5];
            if (i6 != 0) {
                i3 = i6;
            }
            iArr3[i4] = i3 << 8;
            i4 = i5;
        }
        boolean z = true;
        while (z) {
            iArr2[0] = 0;
            iArr3[0] = 0;
            iArr4[0] = -2;
            int i7 = 0;
            for (int i8 = 1; i8 <= i; i8++) {
                iArr4[i8] = -1;
                i7++;
                iArr2[i7] = i8;
                int i9 = i7;
                while (true) {
                    int i10 = iArr3[i8];
                    int i11 = i9 >> 1;
                    int i12 = iArr2[i11];
                    if (i10 < iArr3[i12]) {
                        iArr2[i9] = i12;
                        i9 = i11;
                    }
                }
                iArr2[i9] = i8;
            }
            int i13 = i;
            while (i7 > i3) {
                int i14 = iArr2[i3];
                int i15 = iArr2[i7];
                iArr2[i3] = i15;
                int i16 = i7 - 1;
                int i17 = 1;
                while (true) {
                    int i18 = i17 << 1;
                    if (i18 > i16) {
                        break;
                    }
                    if (i18 < i16) {
                        int i19 = i18 + 1;
                        if (iArr3[iArr2[i19]] < iArr3[iArr2[i18]]) {
                            i18 = i19;
                        }
                    }
                    int i20 = iArr3[i15];
                    int i21 = iArr2[i18];
                    if (i20 < iArr3[i21]) {
                        break;
                    }
                    iArr2[i17] = i21;
                    i17 = i18;
                    i3 = 1;
                }
                iArr2[i17] = i15;
                int i22 = iArr2[i3];
                int i23 = iArr2[i16];
                iArr2[i3] = i23;
                int i24 = i7 - 2;
                int i25 = 1;
                while (true) {
                    int i26 = i25 << 1;
                    if (i26 > i24) {
                        break;
                    }
                    if (i26 < i24) {
                        int i27 = i26 + 1;
                        if (iArr3[iArr2[i27]] < iArr3[iArr2[i26]]) {
                            i26 = i27;
                        }
                    }
                    int i28 = iArr3[i23];
                    int i29 = iArr2[i26];
                    if (i28 < iArr3[i29]) {
                        break;
                    }
                    iArr2[i25] = i29;
                    i25 = i26;
                }
                iArr2[i25] = i23;
                i13++;
                iArr4[i22] = i13;
                iArr4[i14] = i13;
                int i30 = iArr3[i14];
                int i31 = iArr3[i22];
                int i32 = (i30 & (-256)) + (i31 & (-256));
                int i33 = i30 & 255;
                int i34 = i31 & 255;
                if (i33 <= i34) {
                    i33 = i34;
                }
                iArr3[i13] = i32 | (i33 + 1);
                iArr4[i13] = -1;
                i7--;
                iArr2[i7] = i13;
                int i35 = iArr3[i13];
                int i36 = i7;
                while (true) {
                    int i37 = i36 >> 1;
                    int i38 = iArr2[i37];
                    if (i35 < iArr3[i38]) {
                        iArr2[i36] = i38;
                        i36 = i37;
                    }
                }
                iArr2[i36] = i13;
                i3 = 1;
            }
            z = false;
            for (int i39 = 1; i39 <= i; i39++) {
                int i40 = i39;
                int i41 = 0;
                while (true) {
                    i40 = iArr4[i40];
                    if (i40 < 0) {
                        break;
                    } else {
                        i41++;
                    }
                }
                bArr[i39 - 1] = (byte) i41;
                if (i41 > i2) {
                    z = true;
                }
            }
            if (z) {
                for (int i42 = 1; i42 < i; i42++) {
                    iArr3[i42] = ((iArr3[i42] >> 9) + 1) << 8;
                }
            }
            i3 = 1;
        }
    }

    private void initBlock() {
        this.crc.initialiseCRC();
        this.last = -1;
        boolean[] zArr = this.data.inUse;
        int i = 256;
        while (true) {
            i--;
            if (i < 0) {
                return;
            } else {
                zArr[i] = false;
            }
        }
    }

    public final void blockSort() {
        this.blockSorter.blockSort(this.data, this.last);
    }

    public final void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            this.out.write(this.bsBuff >> 24);
            this.bsBuff <<= 8;
            this.bsLive -= 8;
        }
    }

    public final void bsPutInt(int i) throws IOException {
        bsW(8, (i >> 24) & 255);
        bsW(8, (i >> 16) & 255);
        bsW(8, (i >> 8) & 255);
        bsW(8, i & 255);
    }

    public final void bsPutUByte(int i) throws IOException {
        bsW(8, i);
    }

    public final void bsW(int i, int i2) throws IOException {
        OutputStream outputStream = this.out;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        while (i3 >= 8) {
            outputStream.write(i4 >> 24);
            i4 <<= 8;
            i3 -= 8;
        }
        this.bsBuff = (i2 << ((32 - i3) - i)) | i4;
        this.bsLive = i3 + i;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            finish();
            outputStream.close();
        }
    }

    public final void endCompression() throws IOException {
        bsPutUByte(23);
        bsPutUByte(114);
        bsPutUByte(69);
        bsPutUByte(56);
        bsPutUByte(80);
        bsPutUByte(144);
        bsPutInt(this.combinedCRC);
        bsFinishedWithStream();
    }

    public void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    public void finish() throws IOException {
        if (this.out != null) {
            try {
                if (this.runLength > 0) {
                    writeRun();
                }
                this.currentChar = -1;
                endBlock();
                endCompression();
                this.out = null;
                this.data = null;
                this.blockSorter = null;
            } catch (Throwable th) {
                this.out = null;
                this.data = null;
                this.blockSorter = null;
                throw th;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    public final void generateMTFValues() {
        int i = this.last;
        Data data = this.data;
        boolean[] zArr = data.inUse;
        byte[] bArr = data.block;
        int[] iArr = data.fmap;
        char[] cArr = data.sfmap;
        int[] iArr2 = data.mtfFreq;
        byte[] bArr2 = data.unseqToSeq;
        byte[] bArr3 = data.generateMTFValues_yy;
        char c = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            if (zArr[i3]) {
                bArr2[i3] = (byte) i2;
                i2++;
            }
        }
        this.nInUse = i2;
        int i4 = i2 + 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            iArr2[i5] = 0;
        }
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            } else {
                bArr3[i2] = (byte) i2;
            }
        }
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 <= i) {
            byte b = bArr2[bArr[iArr[i6]] & 255];
            byte b2 = bArr3[c];
            int i9 = 0;
            while (b != b2) {
                i9++;
                byte b3 = bArr3[i9];
                bArr3[i9] = b2;
                b2 = b3;
            }
            bArr3[0] = b2;
            if (i9 == 0) {
                i7++;
            } else {
                if (i7 > 0) {
                    int i10 = i7 - 1;
                    while (true) {
                        if ((i10 & 1) == 0) {
                            cArr[i8] = 0;
                            i8++;
                            iArr2[0] = iArr2[0] + 1;
                        } else {
                            cArr[i8] = 1;
                            i8++;
                            iArr2[1] = iArr2[1] + 1;
                        }
                        if (i10 < 2) {
                            break;
                        } else {
                            i10 = (i10 - 2) >> 1;
                        }
                    }
                    i7 = 0;
                }
                int i11 = i9 + 1;
                cArr[i8] = (char) i11;
                i8++;
                iArr2[i11] = iArr2[i11] + 1;
            }
            i6++;
            c = 0;
        }
        if (i7 > 0) {
            int i12 = i7 - 1;
            while (true) {
                if ((i12 & 1) == 0) {
                    cArr[i8] = 0;
                    i8++;
                    iArr2[0] = iArr2[0] + 1;
                } else {
                    cArr[i8] = 1;
                    i8++;
                    iArr2[1] = iArr2[1] + 1;
                }
                if (i12 < 2) {
                    break;
                } else {
                    i12 = (i12 - 2) >> 1;
                }
            }
        }
        cArr[i8] = (char) i4;
        iArr2[i4] = iArr2[i4] + 1;
        this.nMTF = i8 + 1;
    }

    public final int getBlockSize() {
        return this.blockSize100k;
    }

    public final void init() throws IOException {
        bsPutUByte(66);
        bsPutUByte(90);
        Data data = new Data(this.blockSize100k);
        this.data = data;
        this.blockSorter = new BlockSort(data);
        bsPutUByte(104);
        bsPutUByte(this.blockSize100k + 48);
        this.combinedCRC = 0;
        initBlock();
    }

    public final void moveToFrontCodeAndSend() throws IOException {
        bsW(24, this.data.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    public final void sendMTFValues() throws IOException {
        byte[][] bArr = this.data.sendMTFValues_len;
        int i = this.nInUse + 2;
        int i2 = 6;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            byte[] bArr2 = bArr[i2];
            int i3 = i;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    bArr2[i3] = Ascii.SI;
                }
            }
        }
        int i4 = this.nMTF;
        int i5 = i4 >= 200 ? i4 < 600 ? 3 : i4 < 1200 ? 4 : i4 < 2400 ? 5 : 6 : 2;
        sendMTFValues0(i5, i);
        int iSendMTFValues1 = sendMTFValues1(i5, i);
        sendMTFValues2(i5, iSendMTFValues1);
        sendMTFValues3(i5, i);
        sendMTFValues4();
        sendMTFValues5(i5, iSendMTFValues1);
        sendMTFValues6(i5, i);
        sendMTFValues7();
    }

    public final void sendMTFValues0(int i, int i2) {
        Data data = this.data;
        byte[][] bArr = data.sendMTFValues_len;
        int[] iArr = data.mtfFreq;
        int i3 = this.nMTF;
        int i4 = 0;
        for (int i5 = i; i5 > 0; i5--) {
            int i6 = i3 / i5;
            int i7 = i4 - 1;
            int i8 = i2 - 1;
            int i9 = 0;
            while (i9 < i6 && i7 < i8) {
                i7++;
                i9 += iArr[i7];
            }
            if (i7 > i4 && i5 != i && i5 != 1 && (1 & (i - i5)) != 0) {
                i9 -= iArr[i7];
                i7--;
            }
            byte[] bArr2 = bArr[i5 - 1];
            int i10 = i2;
            while (true) {
                i10--;
                if (i10 >= 0) {
                    if (i10 < i4 || i10 > i7) {
                        bArr2[i10] = Ascii.SI;
                    } else {
                        bArr2[i10] = 0;
                    }
                }
            }
            i4 = i7 + 1;
            i3 -= i9;
        }
    }

    public final int sendMTFValues1(int i, int i2) {
        int[][] iArr;
        Data data = this.data;
        int[][] iArr2 = data.sendMTFValues_rfreq;
        int[] iArr3 = data.sendMTFValues_fave;
        short[] sArr = data.sendMTFValues_cost;
        char[] cArr = data.sfmap;
        byte[] bArr = data.selector;
        byte[][] bArr2 = data.sendMTFValues_len;
        byte[] bArr3 = bArr2[0];
        byte[] bArr4 = bArr2[1];
        byte[] bArr5 = bArr2[2];
        byte[] bArr6 = bArr2[3];
        byte[] bArr7 = bArr2[4];
        byte[] bArr8 = bArr2[5];
        int i3 = this.nMTF;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 4; i4 < i6; i6 = 4) {
            int i7 = i;
            while (true) {
                i7--;
                if (i7 < 0) {
                    break;
                }
                iArr3[i7] = 0;
                int[] iArr4 = iArr2[i7];
                int i8 = i2;
                while (true) {
                    i8--;
                    if (i8 >= 0) {
                        iArr4[i8] = 0;
                    }
                }
            }
            int i9 = 0;
            i5 = 0;
            while (i9 < this.nMTF) {
                byte[][] bArr9 = bArr2;
                int iMin = Math.min(i9 + 49, i3 - 1);
                if (i == 6) {
                    int i10 = i9;
                    short s = 0;
                    short s2 = 0;
                    short s3 = 0;
                    short s4 = 0;
                    short s5 = 0;
                    short s6 = 0;
                    while (i10 <= iMin) {
                        char c = cArr[i10];
                        s = (short) (s + (bArr3[c] & 255));
                        s2 = (short) (s2 + (bArr4[c] & 255));
                        s3 = (short) (s3 + (bArr5[c] & 255));
                        s4 = (short) (s4 + (bArr6[c] & 255));
                        s5 = (short) (s5 + (bArr7[c] & 255));
                        i10++;
                        s6 = (short) (s6 + (bArr8[c] & 255));
                        iArr2 = iArr2;
                    }
                    iArr = iArr2;
                    sArr[0] = s;
                    sArr[1] = s2;
                    sArr[2] = s3;
                    sArr[3] = s4;
                    sArr[4] = s5;
                    sArr[5] = s6;
                } else {
                    iArr = iArr2;
                    int i11 = i;
                    while (true) {
                        i11--;
                        if (i11 < 0) {
                            break;
                        }
                        sArr[i11] = 0;
                    }
                    int i12 = i9;
                    while (i12 <= iMin) {
                        char c2 = cArr[i12];
                        int i13 = i;
                        while (true) {
                            i13--;
                            if (i13 >= 0) {
                                sArr[i13] = (short) (sArr[i13] + (bArr9[i13][c2] & 255));
                                i12 = i12;
                            }
                        }
                        i12++;
                    }
                }
                short s7 = 999999999;
                int i14 = i;
                int[] iArr5 = iArr3;
                int i15 = -1;
                while (true) {
                    i14--;
                    if (i14 < 0) {
                        break;
                    }
                    short[] sArr2 = sArr;
                    short s8 = sArr2[i14];
                    if (s8 < s7) {
                        s7 = s8;
                        i15 = i14;
                    }
                    sArr = sArr2;
                }
                short[] sArr3 = sArr;
                iArr5[i15] = iArr5[i15] + 1;
                bArr[i5] = (byte) i15;
                i5++;
                int[] iArr6 = iArr[i15];
                while (i9 <= iMin) {
                    char c3 = cArr[i9];
                    iArr6[c3] = iArr6[c3] + 1;
                    i9++;
                }
                i9 = iMin + 1;
                bArr2 = bArr9;
                iArr3 = iArr5;
                sArr = sArr3;
                iArr2 = iArr;
            }
            byte[][] bArr10 = bArr2;
            int[][] iArr7 = iArr2;
            int[] iArr8 = iArr3;
            short[] sArr4 = sArr;
            for (int i16 = 0; i16 < i; i16++) {
                hbMakeCodeLengths(bArr10[i16], iArr7[i16], this.data, i2, 20);
            }
            i4++;
            bArr2 = bArr10;
            iArr3 = iArr8;
            sArr = sArr4;
            iArr2 = iArr7;
        }
        return i5;
    }

    public final void sendMTFValues2(int i, int i2) {
        Data data = this.data;
        byte[] bArr = data.sendMTFValues2_pos;
        while (true) {
            i--;
            if (i < 0) {
                break;
            } else {
                bArr[i] = (byte) i;
            }
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = data.selector[i3];
            byte b2 = bArr[0];
            int i4 = 0;
            while (b != b2) {
                i4++;
                byte b3 = bArr[i4];
                bArr[i4] = b2;
                b2 = b3;
            }
            bArr[0] = b2;
            data.selectorMtf[i3] = (byte) i4;
        }
    }

    public final void sendMTFValues3(int i, int i2) {
        Data data = this.data;
        int[][] iArr = data.sendMTFValues_code;
        byte[][] bArr = data.sendMTFValues_len;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = 32;
            int i5 = i2;
            int i6 = 0;
            while (true) {
                i5--;
                if (i5 >= 0) {
                    int i7 = bArr2[i5] & 255;
                    if (i7 > i6) {
                        i6 = i7;
                    }
                    if (i7 < i4) {
                        i4 = i7;
                    }
                }
            }
            hbAssignCodes(iArr[i3], bArr[i3], i4, i6, i2);
        }
    }

    public final void sendMTFValues4() throws IOException {
        Data data = this.data;
        boolean[] zArr = data.inUse;
        boolean[] zArr2 = data.sentMTFValues4_inUse16;
        int i = 16;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            zArr2[i] = false;
            int i2 = i * 16;
            int i3 = 16;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (zArr[i2 + i3]) {
                        zArr2[i] = true;
                    }
                }
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            bsW(1, zArr2[i4] ? 1 : 0);
        }
        OutputStream outputStream = this.out;
        int i5 = this.bsLive;
        int i6 = this.bsBuff;
        for (int i7 = 0; i7 < 16; i7++) {
            if (zArr2[i7]) {
                int i8 = i7 * 16;
                for (int i9 = 0; i9 < 16; i9++) {
                    while (i5 >= 8) {
                        outputStream.write(i6 >> 24);
                        i6 <<= 8;
                        i5 -= 8;
                    }
                    if (zArr[i8 + i9]) {
                        i6 |= 1 << (31 - i5);
                    }
                    i5++;
                }
            }
        }
        this.bsBuff = i6;
        this.bsLive = i5;
    }

    public final void sendMTFValues5(int i, int i2) throws IOException {
        bsW(3, i);
        bsW(15, i2);
        OutputStream outputStream = this.out;
        byte[] bArr = this.data.selectorMtf;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = bArr[i5] & 255;
            for (int i7 = 0; i7 < i6; i7++) {
                while (i3 >= 8) {
                    outputStream.write(i4 >> 24);
                    i4 <<= 8;
                    i3 -= 8;
                }
                i4 |= 1 << (31 - i3);
                i3++;
            }
            while (i3 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i3 -= 8;
            }
            i3++;
        }
        this.bsBuff = i4;
        this.bsLive = i3;
    }

    public final void sendMTFValues6(int i, int i2) throws IOException {
        byte[][] bArr = this.data.sendMTFValues_len;
        OutputStream outputStream = this.out;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        for (int i5 = 0; i5 < i; i5++) {
            byte[] bArr2 = bArr[i5];
            int i6 = bArr2[0] & 255;
            while (i3 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i3 -= 8;
            }
            i4 |= i6 << (27 - i3);
            i3 += 5;
            for (int i7 = 0; i7 < i2; i7++) {
                int i8 = bArr2[i7] & 255;
                while (i6 < i8) {
                    while (i3 >= 8) {
                        outputStream.write(i4 >> 24);
                        i4 <<= 8;
                        i3 -= 8;
                    }
                    i4 |= 2 << (30 - i3);
                    i3 += 2;
                    i6++;
                }
                while (i6 > i8) {
                    while (i3 >= 8) {
                        outputStream.write(i4 >> 24);
                        i4 <<= 8;
                        i3 -= 8;
                    }
                    i4 |= 3 << (30 - i3);
                    i3 += 2;
                    i6--;
                }
                while (i3 >= 8) {
                    outputStream.write(i4 >> 24);
                    i4 <<= 8;
                    i3 -= 8;
                }
                i3++;
            }
        }
        this.bsBuff = i4;
        this.bsLive = i3;
    }

    public final void sendMTFValues7() throws IOException {
        Data data = this.data;
        byte[][] bArr = data.sendMTFValues_len;
        int[][] iArr = data.sendMTFValues_code;
        OutputStream outputStream = this.out;
        byte[] bArr2 = data.selector;
        char[] cArr = data.sfmap;
        int i = this.nMTF;
        int i2 = this.bsLive;
        int i3 = this.bsBuff;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int iMin = Math.min(i4 + 49, i - 1);
            int i6 = bArr2[i5] & 255;
            int[] iArr2 = iArr[i6];
            byte[] bArr3 = bArr[i6];
            while (i4 <= iMin) {
                char c = cArr[i4];
                while (i2 >= 8) {
                    outputStream.write(i3 >> 24);
                    i3 <<= 8;
                    i2 -= 8;
                }
                int i7 = bArr3[c] & 255;
                i3 |= iArr2[c] << ((32 - i2) - i7);
                i2 += i7;
                i4++;
            }
            i4 = iMin + 1;
            i5++;
        }
        this.bsBuff = i3;
        this.bsLive = i2;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.out == null) {
            throw new IOException("closed");
        }
        write0(i);
    }

    public final void write0(int i) throws IOException {
        int i2 = this.currentChar;
        if (i2 == -1) {
            this.currentChar = i & 255;
            this.runLength++;
            return;
        }
        int i3 = i & 255;
        if (i2 != i3) {
            writeRun();
            this.runLength = 1;
            this.currentChar = i3;
            return;
        }
        int i4 = this.runLength + 1;
        this.runLength = i4;
        if (i4 > 254) {
            writeRun();
            this.currentChar = -1;
            this.runLength = 0;
        }
    }

    public final void writeRun() throws IOException {
        int i = this.last;
        if (i >= this.allowableBlockSize) {
            endBlock();
            initBlock();
            writeRun();
            return;
        }
        int i2 = this.currentChar;
        Data data = this.data;
        data.inUse[i2] = true;
        byte b = (byte) i2;
        int i3 = this.runLength;
        this.crc.updateCRC(i2, i3);
        if (i3 == 1) {
            data.block[i + 2] = b;
            this.last = i + 1;
            return;
        }
        if (i3 == 2) {
            byte[] bArr = data.block;
            int i4 = i + 2;
            bArr[i4] = b;
            bArr[i + 3] = b;
            this.last = i4;
            return;
        }
        if (i3 == 3) {
            byte[] bArr2 = data.block;
            bArr2[i + 2] = b;
            int i5 = i + 3;
            bArr2[i5] = b;
            bArr2[i + 4] = b;
            this.last = i5;
            return;
        }
        int i6 = i3 - 4;
        data.inUse[i6] = true;
        byte[] bArr3 = data.block;
        bArr3[i + 2] = b;
        bArr3[i + 3] = b;
        bArr3[i + 4] = b;
        int i7 = i + 5;
        bArr3[i7] = b;
        bArr3[i + 6] = (byte) i6;
        this.last = i7;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("offs(", i, ") < 0."));
        }
        if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 <= bArr.length) {
                if (this.out == null) {
                    throw new IOException("stream closed");
                }
                while (i < i3) {
                    write0(bArr[i]);
                    i++;
                }
                return;
            }
            throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(MutableFloatList$$ExternalSyntheticOutline0.m("offs(", i, ") + len(", i2, ") > buf.length("), bArr.length, ")."));
        }
        throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("len(", i2, ") < 0."));
    }

    public BZip2CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 9);
    }
}
