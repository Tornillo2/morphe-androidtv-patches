package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ZipArchiveInputStream extends ArchiveInputStream {
    public static final int CFH_LEN = 46;
    public static final int LFH_LEN = 30;
    public static final long TWO_EXP_32 = 4294967296L;
    public final byte[] LFH_BUF;
    public final byte[] SHORT_BUF;
    public final byte[] SKIP_BUF;
    public final byte[] TWO_DWORD_BUF;
    public final byte[] WORD_BUF;
    public boolean allowStoredEntriesWithDataDescriptor;
    public final Buffer buf;
    public boolean closed;
    public final CRC32 crc;
    public CurrentEntry current;
    public int entriesRead;
    public boolean hitCentralDirectory;
    public final InputStream in;
    public final Inflater inf;
    public ByteArrayInputStream lastStoredEntry;
    public final boolean useUnicodeExtraFields;
    public final ZipEncoding zipEncoding;
    public static final byte[] LFH = ZipLong.getBytes(ZipLong.LFH_SIG.value);
    public static final byte[] CFH = ZipLong.getBytes(ZipLong.CFH_SIG.value);
    public static final byte[] DD = ZipLong.getBytes(ZipLong.DD_SIG.value);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Buffer {
        public final byte[] buf;
        public int lengthOfLastRead;
        public int offsetInBuffer;

        public Buffer() {
            this.buf = new byte[512];
            this.offsetInBuffer = 0;
            this.lengthOfLastRead = 0;
        }

        public static /* synthetic */ int access$712(Buffer buffer, int i) {
            int i2 = buffer.offsetInBuffer + i;
            buffer.offsetInBuffer = i2;
            return i2;
        }

        public final void reset() {
            this.lengthOfLastRead = 0;
            this.offsetInBuffer = 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CurrentEntry {
        public long bytesRead;
        public long bytesReadFromStream;
        public final ZipArchiveEntry entry;
        public boolean hasDataDescriptor;
        public boolean usesZip64;

        public CurrentEntry() {
            this.entry = new ZipArchiveEntry();
        }

        public static /* synthetic */ long access$614(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.bytesRead + j;
            currentEntry.bytesRead = j2;
            return j2;
        }

        public static /* synthetic */ long access$914(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.bytesReadFromStream + j;
            currentEntry.bytesReadFromStream = j2;
            return j2;
        }
    }

    public ZipArchiveInputStream(InputStream inputStream) {
        this(inputStream, "UTF8");
    }

    public static boolean checksig(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private void closeEntry() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        CurrentEntry currentEntry = this.current;
        if (currentEntry == null) {
            return;
        }
        if (currentEntry.bytesReadFromStream > currentEntry.entry.getCompressedSize() || this.current.hasDataDescriptor) {
            skip(Long.MAX_VALUE);
            int bytesInflated = (int) (this.current.bytesReadFromStream - (this.current.entry.getMethod() == 8 ? getBytesInflated() : this.current.bytesRead));
            if (bytesInflated > 0) {
                Buffer buffer = this.buf;
                pushback(buffer.buf, buffer.lengthOfLastRead - bytesInflated, bytesInflated);
            }
        } else {
            drainCurrentEntryData();
        }
        if (this.lastStoredEntry == null && this.current.hasDataDescriptor) {
            readDataDescriptor();
        }
        this.inf.reset();
        this.buf.reset();
        this.crc.reset();
        this.current = null;
        this.lastStoredEntry = null;
    }

    public static boolean matches(byte[] bArr, int i) {
        byte[] bArr2 = ZipArchiveOutputStream.LFH_SIG;
        if (i < bArr2.length) {
            return false;
        }
        return checksig(bArr, bArr2) || checksig(bArr, ZipArchiveOutputStream.EOCD_SIG) || checksig(bArr, ZipArchiveOutputStream.DD_SIG) || checksig(bArr, ZipLong.getBytes(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER.value));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean bufferContainsSignature(java.io.ByteArrayOutputStream r13, int r14, int r15, int r16) throws java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
        L4:
            if (r1 != 0) goto L6e
            int r4 = r15 + (-4)
            if (r2 >= r4) goto L6e
            org.apache.commons.compress.archivers.zip.ZipArchiveInputStream$Buffer r4 = r12.buf
            byte[] r4 = r4.buf
            r5 = r4[r2]
            byte[] r6 = org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.LFH
            r7 = r6[r0]
            if (r5 != r7) goto L6b
            int r7 = r2 + 1
            r7 = r4[r7]
            r8 = 1
            r9 = r6[r8]
            if (r7 != r9) goto L6b
            int r7 = r2 + 2
            r7 = r4[r7]
            r9 = 2
            r10 = r6[r9]
            r11 = 3
            if (r7 != r10) goto L31
            int r10 = r2 + 3
            r10 = r4[r10]
            r6 = r6[r11]
            if (r10 == r6) goto L3f
        L31:
            byte[] r6 = org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.CFH
            r10 = r6[r9]
            if (r5 != r10) goto L46
            int r5 = r2 + 3
            r5 = r4[r5]
            r6 = r6[r11]
            if (r5 != r6) goto L46
        L3f:
            int r1 = r14 + r15
            int r1 = r1 - r2
            int r3 = r1 - r16
        L44:
            r1 = 1
            goto L59
        L46:
            byte[] r5 = org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.DD
            r6 = r5[r9]
            if (r7 != r6) goto L59
            int r6 = r2 + 3
            r6 = r4[r6]
            r5 = r5[r11]
            if (r6 != r5) goto L59
            int r1 = r14 + r15
            int r3 = r1 - r2
            goto L44
        L59:
            if (r1 == 0) goto L6b
            int r5 = r14 + r15
            int r5 = r5 - r3
            r12.pushback(r4, r5, r3)
            org.apache.commons.compress.archivers.zip.ZipArchiveInputStream$Buffer r4 = r12.buf
            byte[] r4 = r4.buf
            r13.write(r4, r0, r2)
            r12.readDataDescriptor()
        L6b:
            int r2 = r2 + 1
            goto L4
        L6e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.bufferContainsSignature(java.io.ByteArrayOutputStream, int, int, int):boolean");
    }

    public final int cacheBytesRead(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, int i3) {
        int i4 = i + i2;
        int i5 = (i4 - i3) - 3;
        if (i5 <= 0) {
            return i4;
        }
        byteArrayOutputStream.write(this.buf.buf, 0, i5);
        byte[] bArr = this.buf.buf;
        int i6 = i3 + 3;
        System.arraycopy(bArr, i5, bArr, 0, i6);
        return i6;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        if (archiveEntry instanceof ZipArchiveEntry) {
            ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
            if (ZipUtil.canHandleEntryData(zipArchiveEntry) && supportsDataDescriptorFor(zipArchiveEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.in.close();
        this.inf.end();
    }

    public final void drainCurrentEntryData() throws IOException {
        long compressedSize = this.current.entry.getCompressedSize() - this.current.bytesReadFromStream;
        while (compressedSize > 0) {
            long j = this.in.read(this.buf.buf, 0, (int) Math.min(r5.length, compressedSize));
            if (j < 0) {
                throw new EOFException("Truncated ZIP entry: " + this.current.entry.getName());
            }
            count(j);
            compressedSize -= j;
        }
    }

    public final void fill() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        Buffer buffer = this.buf;
        int i = this.in.read(buffer.buf);
        buffer.lengthOfLastRead = i;
        if (i > 0) {
            count(this.buf.lengthOfLastRead);
            Inflater inflater = this.inf;
            Buffer buffer2 = this.buf;
            inflater.setInput(buffer2.buf, 0, buffer2.lengthOfLastRead);
        }
    }

    public final void findEocdRecord() throws IOException {
        boolean zIsFirstByteOfEocdSig = false;
        int oneByte = -1;
        while (true) {
            if (!zIsFirstByteOfEocdSig) {
                oneByte = readOneByte();
                if (oneByte <= -1) {
                    return;
                }
            }
            if (isFirstByteOfEocdSig(oneByte)) {
                oneByte = readOneByte();
                byte[] bArr = ZipArchiveOutputStream.EOCD_SIG;
                if (oneByte == bArr[1]) {
                    oneByte = readOneByte();
                    if (oneByte == bArr[2]) {
                        oneByte = readOneByte();
                        if (oneByte == -1 || oneByte == bArr[3]) {
                            return;
                        } else {
                            zIsFirstByteOfEocdSig = isFirstByteOfEocdSig(oneByte);
                        }
                    } else if (oneByte == -1) {
                        return;
                    } else {
                        zIsFirstByteOfEocdSig = isFirstByteOfEocdSig(oneByte);
                    }
                } else if (oneByte == -1) {
                    return;
                } else {
                    zIsFirstByteOfEocdSig = isFirstByteOfEocdSig(oneByte);
                }
            } else {
                zIsFirstByteOfEocdSig = false;
            }
        }
    }

    public final long getBytesInflated() {
        long bytesRead = this.inf.getBytesRead();
        if (this.current.bytesReadFromStream >= 4294967296L) {
            while (true) {
                long j = bytesRead + 4294967296L;
                if (j > this.current.bytesReadFromStream) {
                    break;
                }
                bytesRead = j;
            }
        }
        return bytesRead;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextZipEntry();
    }

    public ZipArchiveEntry getNextZipEntry() throws IOException {
        ZipLong zipLong;
        ZipLong zipLong2;
        if (!this.closed && !this.hitCentralDirectory) {
            try {
                if (this.current != null) {
                    closeEntry();
                    readFully(this.LFH_BUF);
                } else {
                    readFirstLocalFileHeader(this.LFH_BUF);
                }
                ZipLong zipLong3 = new ZipLong(this.LFH_BUF, 0);
                if (zipLong3.equals(ZipLong.CFH_SIG) || zipLong3.equals(ZipLong.AED_SIG)) {
                    this.hitCentralDirectory = true;
                    skipRemainderOfArchive();
                }
                if (zipLong3.equals(ZipLong.LFH_SIG)) {
                    this.current = new CurrentEntry();
                    this.current.entry.setPlatform((ZipShort.getValue(this.LFH_BUF, 4) >> 8) & 15);
                    GeneralPurposeBit generalPurposeBit = GeneralPurposeBit.parse(this.LFH_BUF, 6);
                    boolean z = generalPurposeBit.languageEncodingFlag;
                    ZipEncoding zipEncoding = z ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
                    CurrentEntry currentEntry = this.current;
                    currentEntry.hasDataDescriptor = generalPurposeBit.dataDescriptorFlag;
                    currentEntry.entry.setGeneralPurposeBit(generalPurposeBit);
                    this.current.entry.setMethod(ZipShort.getValue(this.LFH_BUF, 8));
                    this.current.entry.setTime(ZipUtil.dosToJavaTime(ZipLong.getValue(this.LFH_BUF, 10)));
                    CurrentEntry currentEntry2 = this.current;
                    if (currentEntry2.hasDataDescriptor) {
                        zipLong = null;
                        zipLong2 = null;
                    } else {
                        currentEntry2.entry.setCrc(ZipLong.getValue(this.LFH_BUF, 14));
                        zipLong = new ZipLong(this.LFH_BUF, 18);
                        zipLong2 = new ZipLong(this.LFH_BUF, 22);
                    }
                    int value = ZipShort.getValue(this.LFH_BUF, 26);
                    int value2 = ZipShort.getValue(this.LFH_BUF, 28);
                    byte[] bArr = new byte[value];
                    readFully(bArr);
                    this.current.entry.setName(zipEncoding.decode(bArr), bArr);
                    byte[] bArr2 = new byte[value2];
                    readFully(bArr2);
                    this.current.entry.setExtra(bArr2);
                    if (!z && this.useUnicodeExtraFields) {
                        ZipUtil.setNameAndCommentFromExtraFields(this.current.entry, bArr, null);
                    }
                    processZip64Extra(zipLong2, zipLong);
                    this.entriesRead++;
                    return this.current.entry;
                }
            } catch (EOFException unused) {
            }
        }
        return null;
    }

    public final boolean isFirstByteOfEocdSig(int i) {
        return i == ZipArchiveOutputStream.EOCD_SIG[0];
    }

    public final void processZip64Extra(ZipLong zipLong, ZipLong zipLong2) {
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) this.current.entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        CurrentEntry currentEntry = this.current;
        boolean z = zip64ExtendedInformationExtraField != null;
        currentEntry.usesZip64 = z;
        if (currentEntry.hasDataDescriptor) {
            return;
        }
        if (z) {
            ZipLong zipLong3 = ZipLong.ZIP64_MAGIC;
            if (zipLong2.equals(zipLong3) || zipLong.equals(zipLong3)) {
                this.current.entry.setCompressedSize(zip64ExtendedInformationExtraField.getCompressedSize().value.longValue());
                this.current.entry.setSize(zip64ExtendedInformationExtraField.getSize().value.longValue());
                return;
            }
        }
        this.current.entry.setCompressedSize(zipLong2.value);
        this.current.entry.setSize(zipLong.value);
    }

    public final void pushback(byte[] bArr, int i, int i2) throws IOException {
        ((PushbackInputStream) this.in).unread(bArr, i, i2);
        pushedBackBytes(i2);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        CurrentEntry currentEntry;
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.inf.finished() || (currentEntry = this.current) == null) {
            return -1;
        }
        if (i > bArr.length || i2 < 0 || i < 0 || bArr.length - i < i2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ZipUtil.checkRequestedFeatures(currentEntry.entry);
        if (supportsDataDescriptorFor(this.current.entry)) {
            return this.current.entry.getMethod() == 0 ? readStored(bArr, i, i2) : readDeflated(bArr, i, i2);
        }
        throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.current.entry);
    }

    public final void readDataDescriptor() throws IOException {
        readFully(this.WORD_BUF);
        ZipLong zipLong = new ZipLong(this.WORD_BUF, 0);
        if (ZipLong.DD_SIG.equals(zipLong)) {
            readFully(this.WORD_BUF);
            zipLong = new ZipLong(this.WORD_BUF, 0);
        }
        this.current.entry.setCrc(zipLong.value);
        readFully(this.TWO_DWORD_BUF);
        ZipLong zipLong2 = new ZipLong(this.TWO_DWORD_BUF, 8);
        if (!zipLong2.equals(ZipLong.CFH_SIG) && !zipLong2.equals(ZipLong.LFH_SIG)) {
            this.current.entry.setCompressedSize(ZipEightByteInteger.getLongValue(this.TWO_DWORD_BUF));
            this.current.entry.setSize(ZipEightByteInteger.getLongValue(this.TWO_DWORD_BUF, 8));
        } else {
            pushback(this.TWO_DWORD_BUF, 8, 8);
            this.current.entry.setCompressedSize(ZipLong.getValue(this.TWO_DWORD_BUF, 0));
            this.current.entry.setSize(ZipLong.getValue(this.TWO_DWORD_BUF, 4));
        }
    }

    public final int readDeflated(byte[] bArr, int i, int i2) throws IOException {
        int fromInflater = readFromInflater(bArr, i, i2);
        if (fromInflater == 0) {
            if (this.inf.finished()) {
                return -1;
            }
            if (this.inf.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            }
            if (this.buf.lengthOfLastRead == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        this.crc.update(bArr, i, fromInflater);
        return fromInflater;
    }

    public final void readFirstLocalFileHeader(byte[] bArr) throws IOException {
        readFully(bArr);
        ZipLong zipLong = new ZipLong(bArr, 0);
        if (zipLong.equals(ZipLong.DD_SIG)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.SPLITTING);
        }
        if (zipLong.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER)) {
            byte[] bArr2 = new byte[4];
            readFully(bArr2);
            System.arraycopy(bArr, 4, bArr, 0, 26);
            System.arraycopy(bArr2, 0, bArr, 26, 4);
        }
    }

    public final int readFromInflater(byte[] bArr, int i, int i2) throws IOException {
        int iInflate = 0;
        do {
            if (this.inf.needsInput()) {
                fill();
                int i3 = this.buf.lengthOfLastRead;
                if (i3 <= 0) {
                    return iInflate;
                }
                CurrentEntry.access$914(this.current, i3);
            }
            try {
                iInflate = this.inf.inflate(bArr, i, i2);
                if (iInflate != 0) {
                    break;
                }
            } catch (DataFormatException e) {
                throw new ZipException(e.getMessage());
            }
        } while (this.inf.needsInput());
        return iInflate;
    }

    public final void readFully(byte[] bArr) throws IOException {
        int i = 0;
        while (i != bArr.length) {
            int i2 = this.in.read(bArr, i, bArr.length - i);
            i += i2;
            if (i2 == -1) {
                throw new EOFException();
            }
            count(i2);
        }
    }

    public final int readOneByte() throws IOException {
        int i = this.in.read();
        if (i != -1) {
            count(1);
        }
        return i;
    }

    public final int readStored(byte[] bArr, int i, int i2) throws IOException {
        CurrentEntry currentEntry = this.current;
        if (currentEntry.hasDataDescriptor) {
            if (this.lastStoredEntry == null) {
                readStoredEntry();
            }
            return this.lastStoredEntry.read(bArr, i, i2);
        }
        long size = currentEntry.entry.getSize();
        if (this.current.bytesRead < size) {
            Buffer buffer = this.buf;
            if (buffer.offsetInBuffer >= buffer.lengthOfLastRead) {
                buffer.offsetInBuffer = 0;
                int i3 = this.in.read(buffer.buf);
                buffer.lengthOfLastRead = i3;
                if (i3 != -1) {
                    count(this.buf.lengthOfLastRead);
                    CurrentEntry.access$914(this.current, this.buf.lengthOfLastRead);
                }
            }
            Buffer buffer2 = this.buf;
            int iMin = Math.min(buffer2.lengthOfLastRead - buffer2.offsetInBuffer, i2);
            long j = size - this.current.bytesRead;
            if (j < iMin) {
                iMin = (int) j;
            }
            Buffer buffer3 = this.buf;
            System.arraycopy(buffer3.buf, buffer3.offsetInBuffer, bArr, i, iMin);
            Buffer.access$712(this.buf, iMin);
            CurrentEntry.access$614(this.current, iMin);
            this.crc.update(bArr, i, iMin);
            return iMin;
        }
        return -1;
    }

    public final void readStoredEntry() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = this.current.usesZip64 ? 20 : 12;
        boolean zBufferContainsSignature = false;
        int iCacheBytesRead = 0;
        while (!zBufferContainsSignature) {
            int i2 = this.in.read(this.buf.buf, iCacheBytesRead, 512 - iCacheBytesRead);
            if (i2 <= 0) {
                throw new IOException("Truncated ZIP file");
            }
            int i3 = i2 + iCacheBytesRead;
            if (i3 < 4) {
                iCacheBytesRead = i3;
            } else {
                zBufferContainsSignature = bufferContainsSignature(byteArrayOutputStream, iCacheBytesRead, i2, i);
                if (!zBufferContainsSignature) {
                    iCacheBytesRead = cacheBytesRead(byteArrayOutputStream, iCacheBytesRead, i2, i);
                }
            }
        }
        this.lastStoredEntry = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public final void realSkip(long j) throws IOException {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        while (j2 < j) {
            long length = j - j2;
            InputStream inputStream = this.in;
            byte[] bArr = this.SKIP_BUF;
            if (bArr.length <= length) {
                length = bArr.length;
            }
            int i = inputStream.read(bArr, 0, (int) length);
            if (i == -1) {
                return;
            }
            count(i);
            j2 += (long) i;
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        while (j2 < j) {
            long length = j - j2;
            byte[] bArr = this.SKIP_BUF;
            if (bArr.length <= length) {
                length = bArr.length;
            }
            int i = read(bArr, 0, (int) length);
            if (i == -1) {
                break;
            }
            j2 += (long) i;
        }
        return j2;
    }

    public final void skipRemainderOfArchive() throws IOException {
        realSkip((this.entriesRead * 46) - 30);
        findEocdRecord();
        realSkip(16L);
        readFully(this.SHORT_BUF);
        realSkip(ZipShort.getValue(this.SHORT_BUF, 0));
    }

    public final boolean supportsDataDescriptorFor(ZipArchiveEntry zipArchiveEntry) {
        return this.allowStoredEntriesWithDataDescriptor || !zipArchiveEntry.getGeneralPurposeBit().dataDescriptorFlag || zipArchiveEntry.getMethod() == 8;
    }

    public ZipArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, str, true, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z) {
        this(inputStream, str, z, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z, boolean z2) {
        this.inf = new Inflater(true);
        this.crc = new CRC32();
        Buffer buffer = new Buffer();
        this.buf = buffer;
        this.current = null;
        this.closed = false;
        this.hitCentralDirectory = false;
        this.lastStoredEntry = null;
        this.allowStoredEntriesWithDataDescriptor = false;
        this.LFH_BUF = new byte[30];
        this.SKIP_BUF = new byte[1024];
        this.SHORT_BUF = new byte[2];
        this.WORD_BUF = new byte[4];
        this.TWO_DWORD_BUF = new byte[16];
        this.entriesRead = 0;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.useUnicodeExtraFields = z;
        this.in = new PushbackInputStream(inputStream, buffer.buf.length);
        this.allowStoredEntriesWithDataDescriptor = z2;
    }
}
