package androidx.media3.datasource;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DataSourceInputStream extends InputStream {
    public final DataSource dataSource;
    public final DataSpec dataSpec;
    public long totalBytesRead;
    public boolean opened = false;
    public boolean closed = false;
    public final byte[] singleByteArray = new byte[1];

    public DataSourceInputStream(DataSource dataSource, DataSpec dataSpec) {
        this.dataSource = dataSource;
        this.dataSpec = dataSpec;
    }

    public long bytesRead() {
        return this.totalBytesRead;
    }

    public final void checkOpened() throws IOException {
        if (this.opened) {
            return;
        }
        this.dataSource.open(this.dataSpec);
        this.opened = true;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.dataSource.close();
        this.closed = true;
    }

    public void open() throws IOException {
        checkOpened();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = this.singleByteArray;
        if (read(bArr, 0, bArr.length) == -1) {
            return -1;
        }
        return this.singleByteArray[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Assertions.checkState(!this.closed);
        checkOpened();
        int i3 = this.dataSource.read(bArr, i, i2);
        if (i3 == -1) {
            return -1;
        }
        this.totalBytesRead += (long) i3;
        return i3;
    }
}
