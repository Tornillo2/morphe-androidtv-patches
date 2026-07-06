package org.tukaani.xz;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public class CountingInputStream extends FilterInputStream {
    public long size;

    public CountingInputStream(InputStream inputStream) {
        super(inputStream);
        this.size = 0L;
    }

    public long getSize() {
        return this.size;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = ((FilterInputStream) this).in.read();
        if (i != -1) {
            long j = this.size;
            if (j >= 0) {
                this.size = j + 1;
            }
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (i3 > 0) {
            long j = this.size;
            if (j >= 0) {
                this.size = j + ((long) i3);
            }
        }
        return i3;
    }
}
