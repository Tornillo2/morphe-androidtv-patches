package org.apache.commons.compress.compressors;

import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class CompressorInputStream extends InputStream {
    public long bytesRead = 0;

    public void count(int i) {
        count(i);
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    @Deprecated
    public int getCount() {
        return (int) this.bytesRead;
    }

    public void count(long j) {
        if (j != -1) {
            this.bytesRead += j;
        }
    }
}
