package org.apache.commons.compress.archivers.ar;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArArchiveInputStream extends ArchiveInputStream {
    public static final String BSD_LONGNAME_PATTERN = "^#1/\\d+";
    public static final String BSD_LONGNAME_PREFIX = "#1/";
    public static final int BSD_LONGNAME_PREFIX_LEN = 3;
    public static final String GNU_LONGNAME_PATTERN = "^/\\d+";
    public static final String GNU_STRING_TABLE_NAME = "//";
    public final InputStream input;
    public long offset = 0;
    public ArArchiveEntry currentEntry = null;
    public byte[] namebuffer = null;
    public long entryOffset = -1;
    public final byte[] NAME_BUF = new byte[16];
    public final byte[] LAST_MODIFIED_BUF = new byte[12];
    public final byte[] ID_BUF = new byte[6];
    public final byte[] FILE_MODE_BUF = new byte[8];
    public final byte[] LENGTH_BUF = new byte[10];
    public boolean closed = false;

    public ArArchiveInputStream(InputStream inputStream) {
        this.input = inputStream;
    }

    public static boolean isBSDLongName(String str) {
        return str != null && str.matches(BSD_LONGNAME_PATTERN);
    }

    public static boolean isGNUStringTable(String str) {
        return GNU_STRING_TABLE_NAME.equals(str);
    }

    public static boolean matches(byte[] bArr, int i) {
        return i >= 8 && bArr[0] == 33 && bArr[1] == 60 && bArr[2] == 97 && bArr[3] == 114 && bArr[4] == 99 && bArr[5] == 104 && bArr[6] == 62 && bArr[7] == 10;
    }

    public final int asInt(byte[] bArr) {
        return asInt(bArr, 10, false);
    }

    public final long asLong(byte[] bArr) {
        return Long.parseLong(ArchiveUtils.toAsciiString(bArr).trim());
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.input.close();
        }
        this.currentEntry = null;
    }

    public final String getBSDLongName(String str) throws IOException {
        int i = Integer.parseInt(str.substring(BSD_LONGNAME_PREFIX_LEN));
        byte[] bArr = new byte[i];
        int i2 = 0;
        do {
            int i3 = this.input.read(bArr, i2, i - i2);
            if (i3 < 0) {
                break;
            }
            i2 += i3;
            count(i3);
        } while (i2 != i);
        if (i2 == i) {
            return ArchiveUtils.toAsciiString(bArr);
        }
        throw new EOFException();
    }

    public final String getExtendedName(int i) throws IOException {
        if (this.namebuffer == null) {
            throw new IOException("Cannot process GNU long filename as no // record was found");
        }
        int i2 = i;
        while (true) {
            byte[] bArr = this.namebuffer;
            if (i2 >= bArr.length) {
                throw new IOException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Failed to read entry: ", i));
            }
            if (bArr[i2] == 10) {
                if (bArr[i2 - 1] == 47) {
                    i2--;
                }
                return ArchiveUtils.toAsciiString(bArr, i, i2 - i);
            }
            i2++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0161  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.compress.archivers.ar.ArArchiveEntry getNextArEntry() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.ar.ArArchiveInputStream.getNextArEntry():org.apache.commons.compress.archivers.ar.ArArchiveEntry");
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextArEntry();
    }

    public final boolean isGNULongName(String str) {
        return str != null && str.matches(GNU_LONGNAME_PATTERN);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ArArchiveEntry arArchiveEntry = this.currentEntry;
        if (arArchiveEntry != null) {
            long length = arArchiveEntry.getLength() + this.entryOffset;
            if (i2 <= 0) {
                return -1;
            }
            long j = this.offset;
            if (length <= j) {
                return -1;
            }
            i2 = (int) Math.min(i2, length - j);
        }
        int i3 = this.input.read(bArr, i, i2);
        count(i3);
        this.offset += (long) (i3 > 0 ? i3 : 0);
        return i3;
    }

    public final ArArchiveEntry readGNUStringTable(byte[] bArr) throws IOException {
        int iAsInt = asInt(bArr);
        byte[] bArr2 = new byte[iAsInt];
        this.namebuffer = bArr2;
        int i = read(bArr2, 0, iAsInt);
        if (i == iAsInt) {
            return new ArArchiveEntry(GNU_STRING_TABLE_NAME, iAsInt);
        }
        throw new IOException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Failed to read complete // record: expected=", iAsInt, " read=", i));
    }

    public final int asInt(byte[] bArr, boolean z) {
        return asInt(bArr, 10, z);
    }

    public final int asInt(byte[] bArr, int i) {
        return asInt(bArr, i, false);
    }

    public final int asInt(byte[] bArr, int i, boolean z) {
        String strTrim = ArchiveUtils.toAsciiString(bArr).trim();
        if (strTrim.length() == 0 && z) {
            return 0;
        }
        return Integer.parseInt(strTrim, i);
    }
}
