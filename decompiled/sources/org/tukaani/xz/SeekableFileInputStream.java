package org.tukaani.xz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes4.dex */
public class SeekableFileInputStream extends SeekableInputStream {
    public RandomAccessFile randomAccessFile;

    public SeekableFileInputStream(File file) throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(file, "r");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long length() throws IOException {
        return this.randomAccessFile.length();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long position() throws IOException {
        return this.randomAccessFile.getFilePointer();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.randomAccessFile.read();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public void seek(long j) throws IOException {
        this.randomAccessFile.seek(j);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.randomAccessFile.read(bArr);
    }

    public SeekableFileInputStream(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.randomAccessFile.read(bArr, i, i2);
    }

    public SeekableFileInputStream(String str) throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(str, "r");
    }
}
