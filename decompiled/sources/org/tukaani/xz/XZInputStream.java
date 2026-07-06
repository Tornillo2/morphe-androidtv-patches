package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public class XZInputStream extends InputStream {
    public boolean endReached;
    public IOException exception;
    public InputStream in;
    public final int memoryLimit;
    public SingleXZInputStream xzIn;

    public XZInputStream(InputStream inputStream) throws IOException {
        this(inputStream, -1);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        SingleXZInputStream singleXZInputStream = this.xzIn;
        if (singleXZInputStream == null) {
            return 0;
        }
        return singleXZInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    public final void prepareNextStream() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(this.in);
        byte[] bArr = new byte[12];
        while (dataInputStream.read(bArr, 0, 1) != -1) {
            dataInputStream.readFully(bArr, 1, 3);
            if (bArr[0] != 0 || bArr[1] != 0 || bArr[2] != 0 || bArr[3] != 0) {
                dataInputStream.readFully(bArr, 4, 8);
                try {
                    this.xzIn = new SingleXZInputStream(this.in, this.memoryLimit, bArr);
                    return;
                } catch (XZFormatException unused) {
                    throw new CorruptedInputException("Garbage after a valid XZ Stream");
                }
            }
        }
        this.endReached = true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    public XZInputStream(InputStream inputStream, int i) throws IOException {
        this.endReached = false;
        this.exception = null;
        this.in = inputStream;
        this.memoryLimit = i;
        this.xzIn = new SingleXZInputStream(inputStream, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0047, code lost:
    
        return r0;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(byte[] r4, int r5, int r6) throws java.io.IOException {
        /*
            r3 = this;
            if (r5 < 0) goto L51
            if (r6 < 0) goto L51
            int r0 = r5 + r6
            if (r0 < 0) goto L51
            int r1 = r4.length
            if (r0 > r1) goto L51
            r0 = 0
            if (r6 != 0) goto Lf
            return r0
        Lf:
            java.io.InputStream r1 = r3.in
            if (r1 == 0) goto L49
            java.io.IOException r1 = r3.exception
            if (r1 != 0) goto L48
            boolean r1 = r3.endReached
            r2 = -1
            if (r1 == 0) goto L1d
            goto L2c
        L1d:
            if (r6 <= 0) goto L47
            org.tukaani.xz.SingleXZInputStream r1 = r3.xzIn     // Catch: java.io.IOException -> L2d
            if (r1 != 0) goto L2f
            r3.prepareNextStream()     // Catch: java.io.IOException -> L2d
            boolean r1 = r3.endReached     // Catch: java.io.IOException -> L2d
            if (r1 == 0) goto L2f
            if (r0 != 0) goto L47
        L2c:
            return r2
        L2d:
            r4 = move-exception
            goto L41
        L2f:
            org.tukaani.xz.SingleXZInputStream r1 = r3.xzIn     // Catch: java.io.IOException -> L2d
            int r1 = r1.read(r4, r5, r6)     // Catch: java.io.IOException -> L2d
            if (r1 <= 0) goto L3b
            int r0 = r0 + r1
            int r5 = r5 + r1
            int r6 = r6 - r1
            goto L1d
        L3b:
            if (r1 != r2) goto L1d
            r1 = 0
            r3.xzIn = r1     // Catch: java.io.IOException -> L2d
            goto L1d
        L41:
            r3.exception = r4
            if (r0 == 0) goto L46
            goto L47
        L46:
            throw r4
        L47:
            return r0
        L48:
            throw r1
        L49:
            org.tukaani.xz.XZIOException r4 = new org.tukaani.xz.XZIOException
            java.lang.String r5 = "Stream closed"
            r4.<init>(r5)
            throw r4
        L51:
            java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.XZInputStream.read(byte[], int, int):int");
    }
}
