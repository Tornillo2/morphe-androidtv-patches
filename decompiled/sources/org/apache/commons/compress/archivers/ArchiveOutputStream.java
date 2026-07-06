package org.apache.commons.compress.archivers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class ArchiveOutputStream extends OutputStream {
    public static final int BYTE_MASK = 255;
    public final byte[] oneByte = new byte[1];
    public long bytesWritten = 0;

    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

    public abstract void closeArchiveEntry() throws IOException;

    public void count(int i) {
        count(i);
    }

    public abstract ArchiveEntry createArchiveEntry(File file, String str) throws IOException;

    public abstract void finish() throws IOException;

    public long getBytesWritten() {
        return this.bytesWritten;
    }

    @Deprecated
    public int getCount() {
        return (int) this.bytesWritten;
    }

    public abstract void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException;

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i & 255);
        write(bArr, 0, 1);
    }

    public void count(long j) {
        if (j != -1) {
            this.bytesWritten += j;
        }
    }
}
