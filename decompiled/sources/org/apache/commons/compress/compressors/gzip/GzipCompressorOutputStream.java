package org.apache.commons.compress.compressors.gzip;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class GzipCompressorOutputStream extends CompressorOutputStream {
    public final GZIPOutputStream out;

    public GzipCompressorOutputStream(OutputStream outputStream) throws IOException {
        this.out = new GZIPOutputStream(outputStream);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }
}
