package org.apache.commons.compress.archivers.cpio;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CpioArchiveInputStream extends ArchiveInputStream implements CpioConstants {
    public final byte[] FOUR_BYTES_BUF;
    public final byte[] SIX_BYTES_BUF;
    public final byte[] TWO_BYTES_BUF;
    public final int blockSize;
    public boolean closed;
    public long crc;
    public CpioArchiveEntry entry;
    public long entryBytesRead;
    public boolean entryEOF;
    public final InputStream in;
    public final byte[] tmpbuf;

    public CpioArchiveInputStream(InputStream inputStream) {
        this(inputStream, 512);
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < 6) {
            return false;
        }
        byte b = bArr[0];
        if (b == 113 && (bArr[1] & 255) == 199) {
            return true;
        }
        byte b2 = bArr[1];
        if (b2 == 113 && (b & 255) == 199) {
            return true;
        }
        if (b != 48 || b2 != 55 || bArr[2] != 48 || bArr[3] != 55 || bArr[4] != 48) {
            return false;
        }
        byte b3 = bArr[5];
        return b3 == 49 || b3 == 50 || b3 == 55;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        ensureOpen();
        return this.entryEOF ? 0 : 1;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.in.close();
        this.closed = true;
    }

    public final void closeEntry() throws IOException {
        byte[] bArr;
        ensureOpen();
        do {
            bArr = this.tmpbuf;
        } while (read(bArr, 0, bArr.length) != -1);
        this.entryEOF = true;
    }

    public final void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    public CpioArchiveEntry getNextCPIOEntry() throws IOException {
        ensureOpen();
        if (this.entry != null) {
            closeEntry();
        }
        byte[] bArr = this.TWO_BYTES_BUF;
        readFully(bArr, 0, bArr.length);
        if (CpioUtil.byteArray2long(this.TWO_BYTES_BUF, false) == 29127) {
            this.entry = readOldBinaryEntry(false);
        } else if (CpioUtil.byteArray2long(this.TWO_BYTES_BUF, true) == 29127) {
            this.entry = readOldBinaryEntry(true);
        } else {
            byte[] bArr2 = this.TWO_BYTES_BUF;
            System.arraycopy(bArr2, 0, this.SIX_BYTES_BUF, 0, bArr2.length);
            readFully(this.SIX_BYTES_BUF, this.TWO_BYTES_BUF.length, this.FOUR_BYTES_BUF.length);
            String asciiString = ArchiveUtils.toAsciiString(this.SIX_BYTES_BUF);
            if (asciiString.equals(CpioConstants.MAGIC_NEW)) {
                this.entry = readNewEntry(false);
            } else if (asciiString.equals(CpioConstants.MAGIC_NEW_CRC)) {
                this.entry = readNewEntry(true);
            } else {
                if (!asciiString.equals(CpioConstants.MAGIC_OLD_ASCII)) {
                    StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Unknown magic [", asciiString, "]. Occured at byte: ");
                    sbM.append(getBytesRead());
                    throw new IOException(sbM.toString());
                }
                this.entry = readOldAsciiEntry();
            }
        }
        this.entryBytesRead = 0L;
        this.entryEOF = false;
        this.crc = 0L;
        if (!this.entry.getName().equals(CpioConstants.CPIO_TRAILER)) {
            return this.entry;
        }
        this.entryEOF = true;
        skipRemainderOfLastBlock();
        return null;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextCPIOEntry();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ensureOpen();
        if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        CpioArchiveEntry cpioArchiveEntry = this.entry;
        if (cpioArchiveEntry == null || this.entryEOF) {
            return -1;
        }
        if (this.entryBytesRead == cpioArchiveEntry.getSize()) {
            skip(this.entry.getDataPadCount());
            this.entryEOF = true;
            if (this.entry.getFormat() != 2 || this.crc == this.entry.getChksum()) {
                return -1;
            }
            throw new IOException("CRC Error. Occured at byte: " + getBytesRead());
        }
        int iMin = (int) Math.min(i2, this.entry.getSize() - this.entryBytesRead);
        if (iMin < 0) {
            return -1;
        }
        int fully = readFully(bArr, i, iMin);
        if (this.entry.getFormat() == 2) {
            for (int i3 = 0; i3 < fully; i3++) {
                this.crc += (long) (bArr[i3] & 255);
            }
        }
        this.entryBytesRead += (long) fully;
        return fully;
    }

    public final long readAsciiLong(int i, int i2) throws IOException {
        byte[] bArr = new byte[i];
        readFully(bArr, 0, i);
        return Long.parseLong(ArchiveUtils.toAsciiString(bArr), i2);
    }

    public final long readBinaryLong(int i, boolean z) throws IOException {
        byte[] bArr = new byte[i];
        readFully(bArr, 0, i);
        return CpioUtil.byteArray2long(bArr, z);
    }

    public final String readCString(int i) throws IOException {
        byte[] bArr = new byte[i];
        readFully(bArr, 0, i);
        return new String(bArr, 0, i - 1);
    }

    public final int readFully(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = 0;
        while (i3 < i2) {
            int i4 = this.in.read(bArr, i + i3, i2 - i3);
            count(i4);
            if (i4 < 0) {
                throw new EOFException();
            }
            i3 += i4;
        }
        return i3;
    }

    public final CpioArchiveEntry readNewEntry(boolean z) throws IOException {
        CpioArchiveEntry cpioArchiveEntry = z ? new CpioArchiveEntry((short) 2) : new CpioArchiveEntry((short) 1);
        cpioArchiveEntry.setInode(readAsciiLong(8, 16));
        long asciiLong = readAsciiLong(8, 16);
        if (asciiLong != 0) {
            cpioArchiveEntry.setMode(asciiLong);
        }
        cpioArchiveEntry.setUID(readAsciiLong(8, 16));
        cpioArchiveEntry.setGID(readAsciiLong(8, 16));
        cpioArchiveEntry.setNumberOfLinks(readAsciiLong(8, 16));
        cpioArchiveEntry.setTime(readAsciiLong(8, 16));
        cpioArchiveEntry.setSize(readAsciiLong(8, 16));
        cpioArchiveEntry.setDeviceMaj(readAsciiLong(8, 16));
        cpioArchiveEntry.setDeviceMin(readAsciiLong(8, 16));
        cpioArchiveEntry.setRemoteDeviceMaj(readAsciiLong(8, 16));
        cpioArchiveEntry.setRemoteDeviceMin(readAsciiLong(8, 16));
        long asciiLong2 = readAsciiLong(8, 16);
        cpioArchiveEntry.setChksum(readAsciiLong(8, 16));
        String cString = readCString((int) asciiLong2);
        cpioArchiveEntry.setName(cString);
        if (asciiLong != 0 || cString.equals(CpioConstants.CPIO_TRAILER)) {
            skip(cpioArchiveEntry.getHeaderPadCount());
            return cpioArchiveEntry;
        }
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Mode 0 only allowed in the trailer. Found entry name: ", cString, " Occured at byte: ");
        sbM.append(getBytesRead());
        throw new IOException(sbM.toString());
    }

    public final CpioArchiveEntry readOldAsciiEntry() throws IOException {
        CpioArchiveEntry cpioArchiveEntry = new CpioArchiveEntry((short) 4);
        cpioArchiveEntry.setDevice(readAsciiLong(6, 8));
        cpioArchiveEntry.inode = readAsciiLong(6, 8);
        long asciiLong = readAsciiLong(6, 8);
        if (asciiLong != 0) {
            cpioArchiveEntry.setMode(asciiLong);
        }
        cpioArchiveEntry.uid = readAsciiLong(6, 8);
        cpioArchiveEntry.gid = readAsciiLong(6, 8);
        cpioArchiveEntry.nlink = readAsciiLong(6, 8);
        cpioArchiveEntry.setRemoteDevice(readAsciiLong(6, 8));
        cpioArchiveEntry.mtime = readAsciiLong(11, 8);
        long asciiLong2 = readAsciiLong(6, 8);
        cpioArchiveEntry.setSize(readAsciiLong(11, 8));
        String cString = readCString((int) asciiLong2);
        cpioArchiveEntry.name = cString;
        if (asciiLong != 0 || cString.equals(CpioConstants.CPIO_TRAILER)) {
            return cpioArchiveEntry;
        }
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Mode 0 only allowed in the trailer. Found entry: ", cString, " Occured at byte: ");
        sbM.append(getBytesRead());
        throw new IOException(sbM.toString());
    }

    public final CpioArchiveEntry readOldBinaryEntry(boolean z) throws IOException {
        CpioArchiveEntry cpioArchiveEntry = new CpioArchiveEntry((short) 8);
        cpioArchiveEntry.setDevice(readBinaryLong(2, z));
        cpioArchiveEntry.inode = readBinaryLong(2, z);
        long binaryLong = readBinaryLong(2, z);
        if (binaryLong != 0) {
            cpioArchiveEntry.setMode(binaryLong);
        }
        cpioArchiveEntry.uid = readBinaryLong(2, z);
        cpioArchiveEntry.gid = readBinaryLong(2, z);
        cpioArchiveEntry.nlink = readBinaryLong(2, z);
        cpioArchiveEntry.setRemoteDevice(readBinaryLong(2, z));
        cpioArchiveEntry.mtime = readBinaryLong(4, z);
        long binaryLong2 = readBinaryLong(2, z);
        cpioArchiveEntry.setSize(readBinaryLong(4, z));
        String cString = readCString((int) binaryLong2);
        cpioArchiveEntry.name = cString;
        if (binaryLong != 0 || cString.equals(CpioConstants.CPIO_TRAILER)) {
            skip(cpioArchiveEntry.getHeaderPadCount());
            return cpioArchiveEntry;
        }
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Mode 0 only allowed in the trailer. Found entry: ", cString, "Occured at byte: ");
        sbM.append(getBytesRead());
        throw new IOException(sbM.toString());
    }

    public final void skip(int i) throws IOException {
        if (i > 0) {
            readFully(this.FOUR_BYTES_BUF, 0, i);
        }
    }

    public final void skipRemainderOfLastBlock() throws IOException {
        long bytesRead = getBytesRead();
        long j = this.blockSize;
        long j2 = bytesRead % j;
        long j3 = j2 == 0 ? 0L : j - j2;
        while (j3 > 0) {
            long jSkip = skip(((long) this.blockSize) - j2);
            if (jSkip <= 0) {
                return;
            } else {
                j3 -= jSkip;
            }
        }
    }

    public CpioArchiveInputStream(InputStream inputStream, int i) {
        this.closed = false;
        this.entryBytesRead = 0L;
        this.entryEOF = false;
        this.tmpbuf = new byte[4096];
        this.crc = 0L;
        this.TWO_BYTES_BUF = new byte[2];
        this.FOUR_BYTES_BUF = new byte[4];
        this.SIX_BYTES_BUF = new byte[6];
        this.in = inputStream;
        this.blockSize = i;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("negative skip length");
        }
        ensureOpen();
        int iMin = (int) Math.min(j, 2147483647L);
        int i = 0;
        while (true) {
            if (i >= iMin) {
                break;
            }
            int length = iMin - i;
            byte[] bArr = this.tmpbuf;
            if (length > bArr.length) {
                length = bArr.length;
            }
            int i2 = read(bArr, 0, length);
            if (i2 == -1) {
                this.entryEOF = true;
                break;
            }
            i += i2;
        }
        return i;
    }
}
