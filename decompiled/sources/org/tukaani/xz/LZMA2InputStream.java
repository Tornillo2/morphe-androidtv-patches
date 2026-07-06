package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoder;

/* JADX INFO: loaded from: classes4.dex */
public class LZMA2InputStream extends InputStream {
    public static final int COMPRESSED_SIZE_MAX = 65536;
    public static final int DICT_SIZE_MAX = 2147483632;
    public static final int DICT_SIZE_MIN = 4096;
    public boolean endReached;
    public IOException exception;
    public DataInputStream in;
    public boolean isLZMAChunk;
    public final LZDecoder lz;
    public LZMADecoder lzma;
    public boolean needDictReset;
    public boolean needProps;
    public final RangeDecoder rc;
    public int uncompressedSize;

    public LZMA2InputStream(InputStream inputStream, int i) {
        this(inputStream, i, null);
    }

    public static int getDictSize(int i) {
        if (i >= 4096 && i <= 2147483632) {
            return (i + 15) & (-16);
        }
        StringBuffer stringBuffer = new StringBuffer("Unsupported dictionary size ");
        stringBuffer.append(i);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static int getMemoryUsage(int i) {
        return (getDictSize(i) / 1024) + 104;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException == null) {
            return this.uncompressedSize;
        }
        throw iOException;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        DataInputStream dataInputStream = this.in;
        if (dataInputStream != null) {
            try {
                dataInputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    public final void decodeChunkHeader() throws IOException {
        int unsignedByte = this.in.readUnsignedByte();
        if (unsignedByte == 0) {
            this.endReached = true;
            return;
        }
        if (unsignedByte >= 224 || unsignedByte == 1) {
            this.needProps = true;
            this.needDictReset = false;
            this.lz.reset();
        } else if (this.needDictReset) {
            throw new CorruptedInputException();
        }
        if (unsignedByte < 128) {
            if (unsignedByte > 2) {
                throw new CorruptedInputException();
            }
            this.isLZMAChunk = false;
            this.uncompressedSize = this.in.readUnsignedShort() + 1;
            return;
        }
        this.isLZMAChunk = true;
        int i = (unsignedByte & 31) << 16;
        this.uncompressedSize = i;
        this.uncompressedSize = this.in.readUnsignedShort() + 1 + i;
        int unsignedShort = this.in.readUnsignedShort() + 1;
        if (unsignedByte >= 192) {
            this.needProps = false;
            decodeProps();
        } else {
            if (this.needProps) {
                throw new CorruptedInputException();
            }
            if (unsignedByte >= 160) {
                this.lzma.reset();
            }
        }
        this.rc.prepareInputBuffer(this.in, unsignedShort);
    }

    public final void decodeProps() throws IOException {
        int unsignedByte = this.in.readUnsignedByte();
        if (unsignedByte > 224) {
            throw new CorruptedInputException();
        }
        int i = unsignedByte / 45;
        int i2 = unsignedByte - (i * 45);
        int i3 = i2 / 9;
        int i4 = i2 - (i3 * 9);
        if (i4 + i3 > 4) {
            throw new CorruptedInputException();
        }
        this.lzma = new LZMADecoder(this.lz, this.rc, i4, i3, i);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    public LZMA2InputStream(InputStream inputStream, int i, byte[] bArr) {
        this.rc = new RangeDecoder(65536);
        this.uncompressedSize = 0;
        this.needDictReset = true;
        this.needProps = true;
        this.endReached = false;
        this.exception = null;
        inputStream.getClass();
        this.in = new DataInputStream(inputStream);
        this.lz = new LZDecoder(getDictSize(i), bArr);
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        this.needDictReset = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0071, code lost:
    
        throw new org.tukaani.xz.CorruptedInputException();
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            if (r7 < 0) goto L7f
            if (r8 < 0) goto L7f
            int r0 = r7 + r8
            if (r0 < 0) goto L7f
            int r1 = r6.length
            if (r0 > r1) goto L7f
            r0 = 0
            if (r8 != 0) goto Lf
            return r0
        Lf:
            java.io.DataInputStream r1 = r5.in
            if (r1 == 0) goto L77
            java.io.IOException r1 = r5.exception
            if (r1 != 0) goto L76
            boolean r1 = r5.endReached
            r2 = -1
            if (r1 == 0) goto L1d
            return r2
        L1d:
            if (r8 <= 0) goto L75
            int r1 = r5.uncompressedSize     // Catch: java.io.IOException -> L2d
            if (r1 != 0) goto L2f
            r5.decodeChunkHeader()     // Catch: java.io.IOException -> L2d
            boolean r1 = r5.endReached     // Catch: java.io.IOException -> L2d
            if (r1 == 0) goto L2f
            if (r0 != 0) goto L75
            return r2
        L2d:
            r6 = move-exception
            goto L72
        L2f:
            int r1 = r5.uncompressedSize     // Catch: java.io.IOException -> L2d
            int r1 = java.lang.Math.min(r1, r8)     // Catch: java.io.IOException -> L2d
            boolean r3 = r5.isLZMAChunk     // Catch: java.io.IOException -> L2d
            if (r3 != 0) goto L41
            org.tukaani.xz.lz.LZDecoder r3 = r5.lz     // Catch: java.io.IOException -> L2d
            java.io.DataInputStream r4 = r5.in     // Catch: java.io.IOException -> L2d
            r3.copyUncompressed(r4, r1)     // Catch: java.io.IOException -> L2d
            goto L4b
        L41:
            org.tukaani.xz.lz.LZDecoder r3 = r5.lz     // Catch: java.io.IOException -> L2d
            r3.setLimit(r1)     // Catch: java.io.IOException -> L2d
            org.tukaani.xz.lzma.LZMADecoder r1 = r5.lzma     // Catch: java.io.IOException -> L2d
            r1.decode()     // Catch: java.io.IOException -> L2d
        L4b:
            org.tukaani.xz.lz.LZDecoder r1 = r5.lz     // Catch: java.io.IOException -> L2d
            int r1 = r1.flush(r6, r7)     // Catch: java.io.IOException -> L2d
            int r7 = r7 + r1
            int r8 = r8 - r1
            int r0 = r0 + r1
            int r3 = r5.uncompressedSize     // Catch: java.io.IOException -> L2d
            int r3 = r3 - r1
            r5.uncompressedSize = r3     // Catch: java.io.IOException -> L2d
            if (r3 != 0) goto L1d
            org.tukaani.xz.rangecoder.RangeDecoder r1 = r5.rc     // Catch: java.io.IOException -> L2d
            boolean r1 = r1.isFinished()     // Catch: java.io.IOException -> L2d
            if (r1 == 0) goto L6c
            org.tukaani.xz.lz.LZDecoder r1 = r5.lz     // Catch: java.io.IOException -> L2d
            boolean r1 = r1.hasPending()     // Catch: java.io.IOException -> L2d
            if (r1 != 0) goto L6c
            goto L1d
        L6c:
            org.tukaani.xz.CorruptedInputException r6 = new org.tukaani.xz.CorruptedInputException     // Catch: java.io.IOException -> L2d
            r6.<init>()     // Catch: java.io.IOException -> L2d
            throw r6     // Catch: java.io.IOException -> L2d
        L72:
            r5.exception = r6
            throw r6
        L75:
            return r0
        L76:
            throw r1
        L77:
            org.tukaani.xz.XZIOException r6 = new org.tukaani.xz.XZIOException
            java.lang.String r7 = "Stream closed"
            r6.<init>(r7)
            throw r6
        L7f:
            java.lang.IndexOutOfBoundsException r6 = new java.lang.IndexOutOfBoundsException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.LZMA2InputStream.read(byte[], int, int):int");
    }
}
