package org.apache.commons.compress.archivers;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class ArchiveInputStream extends InputStream {
    public static final int BYTE_MASK = 255;
    public final byte[] SINGLE = new byte[1];
    public long bytesRead = 0;

    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

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

    public abstract ArchiveEntry getNextEntry() throws IOException;

    public void pushedBackBytes(long j) {
        this.bytesRead -= j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.SINGLE, 0, 1) == -1) {
            return -1;
        }
        return this.SINGLE[0] & 255;
    }

    public void count(long j) {
        if (j != -1) {
            this.bytesRead += j;
        }
    }
}
