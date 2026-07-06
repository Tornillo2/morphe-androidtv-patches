package org.apache.commons.compress.archivers.tar;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TarArchiveInputStream extends ArchiveInputStream {
    public static final int BUFFER_SIZE = 8192;
    public static final int SMALL_BUFFER_SIZE = 256;
    public final byte[] SKIP_BUF;
    public final byte[] SMALL_BUF;
    public final TarBuffer buffer;
    public TarArchiveEntry currEntry;
    public final ZipEncoding encoding;
    public long entryOffset;
    public long entrySize;
    public boolean hasHitEOF;
    public byte[] readBuf;

    public TarArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, 10240, 512, str);
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < 265) {
            return false;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", bArr, 257, 6) && ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_POSIX, bArr, TarConstants.VERSION_OFFSET, 2)) {
            return true;
        }
        if (ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_GNU, bArr, 257, 6) && (ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_GNU_SPACE, bArr, TarConstants.VERSION_OFFSET, 2) || ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_GNU_ZERO, bArr, TarConstants.VERSION_OFFSET, 2))) {
            return true;
        }
        return ArchiveUtils.matchAsciiBuffer("ustar\u0000", bArr, 257, 6) && ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_ANT, bArr, TarConstants.VERSION_OFFSET, 2);
    }

    public final void applyPaxHeadersToCurrentEntry(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ("path".equals(key)) {
                this.currEntry.setName(value);
            } else if ("linkpath".equals(key)) {
                this.currEntry.setLinkName(value);
            } else if ("gid".equals(key)) {
                this.currEntry.setGroupId(Integer.parseInt(value));
            } else if ("gname".equals(key)) {
                this.currEntry.setGroupName(value);
            } else if ("uid".equals(key)) {
                this.currEntry.setUserId(Integer.parseInt(value));
            } else if ("uname".equals(key)) {
                this.currEntry.setUserName(value);
            } else if ("size".equals(key)) {
                this.currEntry.setSize(Long.parseLong(value));
            } else if ("mtime".equals(key)) {
                this.currEntry.setModTime((long) (Double.parseDouble(value) * 1000.0d));
            } else if ("SCHILY.devminor".equals(key)) {
                this.currEntry.setDevMinor(Integer.parseInt(value));
            } else if ("SCHILY.devmajor".equals(key)) {
                this.currEntry.setDevMajor(Integer.parseInt(value));
            }
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        long j = this.entrySize;
        long j2 = this.entryOffset;
        if (j - j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) (j - j2);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        if (archiveEntry instanceof TarArchiveEntry) {
            return !((TarArchiveEntry) archiveEntry).isGNUSparse();
        }
        return false;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.buffer.close();
    }

    public final TarArchiveEntry getCurrentEntry() {
        return this.currEntry;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextTarEntry();
    }

    public TarArchiveEntry getNextTarEntry() throws IOException {
        if (!this.hasHitEOF) {
            if (this.currEntry != null) {
                long j = this.entrySize;
                long jSkip = this.entryOffset;
                do {
                    j -= jSkip;
                    if (j > 0) {
                        jSkip = skip(j);
                    } else {
                        this.readBuf = null;
                    }
                } while (jSkip > 0);
                throw new RuntimeException("failed to skip current tar entry");
            }
            byte[] record = getRecord();
            if (this.hasHitEOF) {
                this.currEntry = null;
                return null;
            }
            try {
                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(record, this.encoding);
                this.currEntry = tarArchiveEntry;
                this.entryOffset = 0L;
                this.entrySize = tarArchiveEntry.size;
                if (tarArchiveEntry.isGNULongNameEntry()) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int i = read(this.SMALL_BUF);
                        if (i < 0) {
                            break;
                        }
                        byteArrayOutputStream.write(this.SMALL_BUF, 0, i);
                    }
                    getNextEntry();
                    if (this.currEntry != null) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        int length = byteArray.length;
                        while (length > 0 && byteArray[length - 1] == 0) {
                            length--;
                        }
                        if (length != byteArray.length) {
                            byte[] bArr = new byte[length];
                            System.arraycopy(byteArray, 0, bArr, 0, length);
                            byteArray = bArr;
                        }
                        this.currEntry.setName(this.encoding.decode(byteArray));
                    }
                }
                if (this.currEntry.isPaxHeader()) {
                    paxHeaders();
                }
                if (this.currEntry.isGNUSparse()) {
                    readGNUSparse();
                }
                this.entrySize = this.currEntry.getSize();
                return this.currEntry;
            } catch (IllegalArgumentException e) {
                IOException iOException = new IOException("Error detected parsing the header");
                iOException.initCause(e);
                throw iOException;
            }
        }
        return null;
    }

    public final byte[] getRecord() throws IOException {
        if (this.hasHitEOF) {
            return null;
        }
        byte[] record = this.buffer.readRecord();
        if (record == null) {
            this.hasHitEOF = true;
        } else if (this.buffer.isEOFRecord(record)) {
            this.hasHitEOF = true;
            this.buffer.tryToConsumeSecondEOFRecord();
        }
        if (this.hasHitEOF) {
            return null;
        }
        return record;
    }

    public int getRecordSize() {
        return this.buffer.getRecordSize();
    }

    public final boolean isAtEOF() {
        return this.hasHitEOF;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
    
        r4 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<java.lang.String, java.lang.String> parsePaxHeaders(java.io.InputStream r10) throws java.io.IOException {
        /*
            r9 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
        L5:
            r1 = 0
            r2 = 0
            r3 = 0
        L8:
            int r4 = r10.read()
            r5 = -1
            if (r4 == r5) goto L5b
            int r2 = r2 + 1
            r6 = 32
            if (r4 != r6) goto L55
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream
            r4.<init>()
        L1a:
            int r6 = r10.read()
            if (r6 == r5) goto L53
            int r2 = r2 + 1
            r7 = 61
            if (r6 != r7) goto L4e
            java.lang.String r7 = "UTF-8"
            java.lang.String r4 = r4.toString(r7)
            int r3 = r3 - r2
            byte[] r2 = new byte[r3]
            int r8 = r10.read(r2)
            if (r8 != r3) goto L40
            java.lang.String r8 = new java.lang.String
            int r3 = r3 + (-1)
            r8.<init>(r2, r1, r3, r7)
            r0.put(r4, r8)
            goto L53
        L40:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "Failed to read Paxheader. Expected "
            java.lang.String r1 = " bytes, read "
            java.lang.String r0 = androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m(r0, r3, r1, r8)
            r10.<init>(r0)
            throw r10
        L4e:
            byte r6 = (byte) r6
            r4.write(r6)
            goto L1a
        L53:
            r4 = r6
            goto L5b
        L55:
            int r3 = r3 * 10
            int r4 = r4 + (-48)
            int r3 = r3 + r4
            goto L8
        L5b:
            if (r4 != r5) goto L5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveInputStream.parsePaxHeaders(java.io.InputStream):java.util.Map");
    }

    public final void paxHeaders() throws IOException {
        Map<String, String> paxHeaders = parsePaxHeaders(this);
        getNextEntry();
        applyPaxHeadersToCurrentEntry(paxHeaders);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int length;
        long j = this.entryOffset;
        long j2 = this.entrySize;
        if (j >= j2) {
            return -1;
        }
        if (((long) i2) + j > j2) {
            i2 = (int) (j2 - j);
        }
        byte[] bArr2 = this.readBuf;
        if (bArr2 != null) {
            length = i2 > bArr2.length ? bArr2.length : i2;
            System.arraycopy(bArr2, 0, bArr, i, length);
            byte[] bArr3 = this.readBuf;
            if (length >= bArr3.length) {
                this.readBuf = null;
            } else {
                int length2 = bArr3.length - length;
                byte[] bArr4 = new byte[length2];
                System.arraycopy(bArr3, length, bArr4, 0, length2);
                this.readBuf = bArr4;
            }
            i2 -= length;
            i += length;
        } else {
            length = 0;
        }
        while (i2 > 0) {
            byte[] record = this.buffer.readRecord();
            if (record == null) {
                StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("unexpected EOF with ", i2, " bytes unread. Occured at byte: ");
                sbM.append(getBytesRead());
                throw new IOException(sbM.toString());
            }
            count(record.length);
            int length3 = record.length;
            if (length3 > i2) {
                System.arraycopy(record, 0, bArr, i, i2);
                int i3 = length3 - i2;
                byte[] bArr5 = new byte[i3];
                this.readBuf = bArr5;
                System.arraycopy(record, i2, bArr5, 0, i3);
                length3 = i2;
            } else {
                System.arraycopy(record, 0, bArr, i, length3);
            }
            length += length3;
            i2 -= length3;
            i += length3;
        }
        this.entryOffset += (long) length;
        return length;
    }

    public final void readGNUSparse() throws IOException {
        byte[] record;
        if (this.currEntry.isExtended()) {
            do {
                record = getRecord();
                if (this.hasHitEOF) {
                    this.currEntry = null;
                    return;
                }
            } while (new TarArchiveSparseEntry(record).isExtended);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
    }

    public final void setAtEOF(boolean z) {
        this.hasHitEOF = z;
    }

    public final void setCurrentEntry(TarArchiveEntry tarArchiveEntry) {
        this.currEntry = tarArchiveEntry;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = j;
        while (j2 > 0) {
            byte[] bArr = this.SKIP_BUF;
            int i = read(bArr, 0, (int) (j2 > ((long) bArr.length) ? bArr.length : j2));
            if (i == -1) {
                break;
            }
            j2 -= (long) i;
        }
        return j - j2;
    }

    public TarArchiveInputStream(InputStream inputStream, int i, String str) {
        this(inputStream, i, 512, str);
    }

    public TarArchiveInputStream(InputStream inputStream) {
        this(inputStream, 10240, 512, null);
    }

    public TarArchiveInputStream(InputStream inputStream, int i) {
        this(inputStream, i, 512, null);
    }

    public TarArchiveInputStream(InputStream inputStream, int i, int i2) {
        this(inputStream, i, i2, null);
    }

    public TarArchiveInputStream(InputStream inputStream, int i, int i2, String str) {
        this.SKIP_BUF = new byte[8192];
        this.SMALL_BUF = new byte[256];
        this.buffer = new TarBuffer(inputStream, null, i, i2);
        this.readBuf = null;
        this.hasHitEOF = false;
        this.encoding = ZipEncodingHelper.getZipEncoding(str);
    }
}
