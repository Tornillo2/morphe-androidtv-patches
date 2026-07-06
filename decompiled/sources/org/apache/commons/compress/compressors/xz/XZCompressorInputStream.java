package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.tukaani.xz.SingleXZInputStream;
import org.tukaani.xz.XZ;
import org.tukaani.xz.XZInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class XZCompressorInputStream extends CompressorInputStream {
    public final InputStream in;

    public XZCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < XZ.HEADER_MAGIC.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = XZ.HEADER_MAGIC;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.in.read();
        count(i != -1 ? 1 : -1);
        return i;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.in.skip(j);
    }

    public XZCompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        if (z) {
            this.in = new XZInputStream(inputStream, -1);
        } else {
            this.in = new SingleXZInputStream(inputStream);
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.in.read(bArr, i, i2);
        count(i3);
        return i3;
    }
}
