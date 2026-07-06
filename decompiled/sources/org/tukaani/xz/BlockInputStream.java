package org.tukaani.xz;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;

/* JADX INFO: loaded from: classes4.dex */
public class BlockInputStream extends InputStream {
    public final Check check;
    public long compressedSizeInHeader;
    public long compressedSizeLimit;
    public InputStream filterChain;
    public final int headerSize;
    public final InputStream in;
    public final CountingInputStream inCounted;
    public final DataInputStream inData;
    public long uncompressedSizeInHeader;
    public long uncompressedSize = 0;
    public boolean endReached = false;

    public BlockInputStream(InputStream inputStream, Check check, int i, long j, long j2) throws IndexIndicatorException, IOException {
        long[] jArr;
        this.uncompressedSizeInHeader = -1L;
        this.compressedSizeInHeader = -1L;
        this.in = inputStream;
        this.check = check;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.inData = dataInputStream;
        byte[] bArr = new byte[1024];
        dataInputStream.readFully(bArr, 0, 1);
        byte b = bArr[0];
        if (b == 0) {
            throw new IndexIndicatorException();
        }
        int i2 = ((b & 255) + 1) * 4;
        this.headerSize = i2;
        dataInputStream.readFully(bArr, 1, i2 - 1);
        int i3 = i2 - 4;
        if (!DecoderUtil.isCRC32Valid(bArr, 0, i3, i3)) {
            throw new CorruptedInputException("XZ Block Header is corrupt");
        }
        int i4 = bArr[1];
        if ((i4 & 60) != 0) {
            throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
        }
        int i5 = i4 & 3;
        int i6 = i5 + 1;
        long[] jArr2 = new long[i6];
        byte[][] bArr2 = new byte[i6][];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 2, i2 - 6);
        try {
            this.compressedSizeLimit = (9223372036854775804L - ((long) i2)) - ((long) check.getSize());
            if ((bArr[1] & 64) != 0) {
                long jDecodeVLI = DecoderUtil.decodeVLI(byteArrayInputStream);
                this.compressedSizeInHeader = jDecodeVLI;
                if (jDecodeVLI != 0) {
                    jArr = jArr2;
                    if (jDecodeVLI <= this.compressedSizeLimit) {
                        this.compressedSizeLimit = jDecodeVLI;
                    }
                }
                throw new CorruptedInputException();
            }
            jArr = jArr2;
            if ((bArr[1] & 128) != 0) {
                this.uncompressedSizeInHeader = DecoderUtil.decodeVLI(byteArrayInputStream);
            }
            int i7 = 0;
            while (i7 < i6) {
                jArr[i7] = DecoderUtil.decodeVLI(byteArrayInputStream);
                long jDecodeVLI2 = DecoderUtil.decodeVLI(byteArrayInputStream);
                int i8 = i7;
                if (jDecodeVLI2 > byteArrayInputStream.available()) {
                    throw new CorruptedInputException();
                }
                byte[] bArr3 = new byte[(int) jDecodeVLI2];
                bArr2[i8] = bArr3;
                byteArrayInputStream.read(bArr3);
                i7 = i8 + 1;
            }
            for (int iAvailable = byteArrayInputStream.available(); iAvailable > 0; iAvailable--) {
                if (byteArrayInputStream.read() != 0) {
                    throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
                }
            }
            if (j != -1) {
                long size = check.getSize() + this.headerSize;
                if (size >= j) {
                    throw new CorruptedInputException("XZ Index does not match a Block Header");
                }
                long j3 = j - size;
                if (j3 <= this.compressedSizeLimit) {
                    long j4 = this.compressedSizeInHeader;
                    if (j4 == -1 || j4 == j3) {
                        long j5 = this.uncompressedSizeInHeader;
                        if (j5 != -1 && j5 != j2) {
                            throw new CorruptedInputException("XZ Index does not match a Block Header");
                        }
                        this.compressedSizeLimit = j3;
                        this.compressedSizeInHeader = j3;
                        this.uncompressedSizeInHeader = j2;
                    }
                }
                throw new CorruptedInputException("XZ Index does not match a Block Header");
            }
            FilterDecoder[] filterDecoderArr = new FilterDecoder[i6];
            for (int i9 = 0; i9 < i6; i9++) {
                long j6 = jArr[i9];
                if (j6 == 33) {
                    filterDecoderArr[i9] = new LZMA2Decoder(bArr2[i9]);
                } else if (j6 == 3) {
                    filterDecoderArr[i9] = new DeltaDecoder(bArr2[i9]);
                } else {
                    if (!BCJCoder.isBCJFilterID(j6)) {
                        StringBuffer stringBuffer = new StringBuffer("Unknown Filter ID ");
                        stringBuffer.append(jArr[i9]);
                        throw new UnsupportedOptionsException(stringBuffer.toString());
                    }
                    filterDecoderArr[i9] = new BCJDecoder(jArr[i9], bArr2[i9]);
                }
            }
            RawCoder.validate(filterDecoderArr);
            if (i >= 0) {
                int memoryUsage = 0;
                for (int i10 = 0; i10 < i6; i10++) {
                    memoryUsage += filterDecoderArr[i10].getMemoryUsage();
                }
                if (memoryUsage > i) {
                    throw new MemoryLimitException(memoryUsage, i);
                }
            }
            CountingInputStream countingInputStream = new CountingInputStream(inputStream);
            this.inCounted = countingInputStream;
            this.filterChain = countingInputStream;
            for (int i11 = i5; i11 >= 0; i11--) {
                this.filterChain = filterDecoderArr[i11].getInputStream(this.filterChain);
            }
        } catch (IOException unused) {
            throw new CorruptedInputException("XZ Block Header is corrupt");
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.filterChain.available();
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public long getUnpaddedSize() {
        return this.inCounted.getSize() + ((long) this.headerSize) + ((long) this.check.getSize());
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    public final void validate() throws IOException {
        long size = this.inCounted.getSize();
        long j = this.compressedSizeInHeader;
        if (j == -1 || j == size) {
            long j2 = this.uncompressedSizeInHeader;
            if (j2 == -1 || j2 == this.uncompressedSize) {
                while (true) {
                    long j3 = 1 + size;
                    if ((size & 3) == 0) {
                        byte[] bArr = new byte[this.check.getSize()];
                        this.inData.readFully(bArr);
                        if (Arrays.equals(this.check.finish(), bArr)) {
                            return;
                        }
                        StringBuffer stringBuffer = new StringBuffer("Integrity check (");
                        stringBuffer.append(this.check.getName());
                        stringBuffer.append(") does not match");
                        throw new CorruptedInputException(stringBuffer.toString());
                    }
                    if (this.inData.readUnsignedByte() != 0) {
                        throw new CorruptedInputException();
                    }
                    size = j3;
                }
            }
        }
        throw new CorruptedInputException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        if (r0 == (-1)) goto L22;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(byte[] r9, int r10, int r11) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.endReached
            r1 = -1
            if (r0 == 0) goto L6
            return r1
        L6:
            java.io.InputStream r0 = r8.filterChain
            int r0 = r0.read(r9, r10, r11)
            r2 = 1
            if (r0 <= 0) goto L5e
            org.tukaani.xz.check.Check r3 = r8.check
            r3.update(r9, r10, r0)
            long r9 = r8.uncompressedSize
            long r3 = (long) r0
            long r9 = r9 + r3
            r8.uncompressedSize = r9
            org.tukaani.xz.CountingInputStream r9 = r8.inCounted
            long r9 = r9.getSize()
            r3 = 0
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 < 0) goto L58
            long r5 = r8.compressedSizeLimit
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 > 0) goto L58
            long r9 = r8.uncompressedSize
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 < 0) goto L58
            long r3 = r8.uncompressedSizeInHeader
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L3e
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 > 0) goto L58
        L3e:
            if (r0 < r11) goto L44
            int r11 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r11 != 0) goto L61
        L44:
            java.io.InputStream r9 = r8.filterChain
            int r9 = r9.read()
            if (r9 != r1) goto L52
        L4c:
            r8.validate()
            r8.endReached = r2
            return r0
        L52:
            org.tukaani.xz.CorruptedInputException r9 = new org.tukaani.xz.CorruptedInputException
            r9.<init>()
            throw r9
        L58:
            org.tukaani.xz.CorruptedInputException r9 = new org.tukaani.xz.CorruptedInputException
            r9.<init>()
            throw r9
        L5e:
            if (r0 != r1) goto L61
            goto L4c
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.BlockInputStream.read(byte[], int, int):int");
    }
}
