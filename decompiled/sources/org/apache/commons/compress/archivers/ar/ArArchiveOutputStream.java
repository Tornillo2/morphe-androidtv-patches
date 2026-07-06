package org.apache.commons.compress.archivers.ar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArArchiveOutputStream extends ArchiveOutputStream {
    public static final int LONGFILE_BSD = 1;
    public static final int LONGFILE_ERROR = 0;
    public final OutputStream out;
    public ArArchiveEntry prevEntry;
    public long entryOffset = 0;
    public boolean haveUnclosedEntry = false;
    public int longFileMode = 0;
    public boolean finished = false;

    public ArArchiveOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
        this.out.close();
        this.prevEntry = null;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (this.prevEntry == null || !this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        }
        if (this.entryOffset % 2 != 0) {
            this.out.write(10);
        }
        this.haveUnclosedEntry = false;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ArArchiveEntry(file, str);
    }

    public final long fill(long j, long j2, char c) throws IOException {
        long j3 = j2 - j;
        if (j3 > 0) {
            for (int i = 0; i < j3; i++) {
                write(c);
            }
        }
        return j2;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void finish() throws IOException {
        if (this.haveUnclosedEntry) {
            throw new IOException("This archive contains unclosed entries.");
        }
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        this.finished = true;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        ArArchiveEntry arArchiveEntry = (ArArchiveEntry) archiveEntry;
        ArArchiveEntry arArchiveEntry2 = this.prevEntry;
        if (arArchiveEntry2 == null) {
            writeArchiveHeader();
        } else {
            if (arArchiveEntry2.getLength() != this.entryOffset) {
                throw new IOException("length does not match entry (" + this.prevEntry.getLength() + " != " + this.entryOffset);
            }
            if (this.haveUnclosedEntry) {
                closeArchiveEntry();
            }
        }
        this.prevEntry = arArchiveEntry;
        writeEntryHeader(arArchiveEntry);
        this.entryOffset = 0L;
        this.haveUnclosedEntry = true;
    }

    public void setLongFileMode(int i) {
        this.longFileMode = i;
    }

    public final long write(String str) throws IOException {
        write(str.getBytes("ascii"));
        return r3.length;
    }

    public final long writeArchiveHeader() throws IOException {
        this.out.write(ArchiveUtils.toAsciiBytes(ArArchiveEntry.HEADER));
        return r0.length;
    }

    public final long writeEntryHeader(ArArchiveEntry arArchiveEntry) throws IOException {
        long jWrite;
        boolean z;
        String name = arArchiveEntry.getName();
        if (this.longFileMode == 0 && name.length() > 16) {
            throw new IOException("filename too long, > 16 chars: ".concat(name));
        }
        if (1 != this.longFileMode || (name.length() <= 16 && name.indexOf(StringUtils.SPACE) <= -1)) {
            jWrite = write(name);
            z = false;
        } else {
            jWrite = write(ArArchiveInputStream.BSD_LONGNAME_PREFIX + String.valueOf(name.length()));
            z = true;
        }
        fill(jWrite, 16L, ' ');
        String str = "" + arArchiveEntry.getLastModified();
        if (str.length() > 12) {
            throw new IOException("modified too long");
        }
        fill(write(str) + 16, 28L, ' ');
        String str2 = "" + arArchiveEntry.getUserId();
        if (str2.length() > 6) {
            throw new IOException("userid too long");
        }
        fill(write(str2) + 28, 34L, ' ');
        String str3 = "" + arArchiveEntry.getGroupId();
        if (str3.length() > 6) {
            throw new IOException("groupid too long");
        }
        fill(write(str3) + 34, 40L, ' ');
        String str4 = "" + Integer.toString(arArchiveEntry.getMode(), 8);
        if (str4.length() > 8) {
            throw new IOException("filemode too long");
        }
        fill(write(str4) + 40, 48L, ' ');
        String strValueOf = String.valueOf(arArchiveEntry.getLength() + ((long) (z ? name.length() : 0)));
        if (strValueOf.length() > 10) {
            throw new IOException("size too long");
        }
        fill(write(strValueOf) + 48, 58L, ' ');
        long jWrite2 = write(ArArchiveEntry.TRAILER) + 58;
        return z ? write(name) + jWrite2 : jWrite2;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        count(i2);
        this.entryOffset += (long) i2;
    }
}
