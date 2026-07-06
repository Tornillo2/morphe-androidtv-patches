package org.apache.commons.compress.archivers.zip;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ZipArchiveOutputStream extends ArchiveOutputStream {
    public static final int BUFFER_SIZE = 512;
    public static final int DEFAULT_COMPRESSION = -1;
    public static final String DEFAULT_ENCODING = "UTF8";
    public static final int DEFLATED = 8;
    public static final int DEFLATER_BLOCK_SIZE = 8192;

    @Deprecated
    public static final int EFS_FLAG = 2048;
    public static final int STORED = 0;
    public final byte[] buf;
    public long cdLength;
    public long cdOffset;
    public String comment;
    public final CRC32 crc;
    public UnicodeExtraFieldPolicy createUnicodeExtraFields;
    public final Deflater def;
    public String encoding;
    public final List<ZipArchiveEntry> entries;
    public CurrentEntry entry;
    public boolean fallbackToUTF8;
    public boolean finished;
    public boolean hasCompressionLevelChanged;
    public boolean hasUsedZip64;
    public int level;
    public int method;
    public final Map<ZipArchiveEntry, Long> offsets;
    public final OutputStream out;
    public final RandomAccessFile raf;
    public boolean useUTF8Flag;
    public long written;
    public Zip64Mode zip64Mode;
    public ZipEncoding zipEncoding;
    public static final byte[] EMPTY = new byte[0];
    public static final byte[] ZERO = {0, 0};
    public static final byte[] LZERO = {0, 0, 0, 0};
    public static final byte[] LFH_SIG = ZipLong.getBytes(ZipLong.LFH_SIG.value);
    public static final byte[] DD_SIG = ZipLong.getBytes(ZipLong.DD_SIG.value);
    public static final byte[] CFH_SIG = ZipLong.getBytes(ZipLong.CFH_SIG.value);
    public static final byte[] EOCD_SIG = ZipLong.getBytes(101010256);
    public static final byte[] ZIP64_EOCD_SIG = ZipLong.getBytes(101075792);
    public static final byte[] ZIP64_EOCD_LOC_SIG = ZipLong.getBytes(117853008);
    public static final byte[] ONE = ZipLong.getBytes(1);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CurrentEntry {
        public long bytesRead;
        public boolean causedUseOfZip64;
        public long dataStart;
        public final ZipArchiveEntry entry;
        public boolean hasWritten;
        public long localDataStart;

        public static /* synthetic */ long access$314(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.bytesRead + j;
            currentEntry.bytesRead = j2;
            return j2;
        }

        public CurrentEntry(ZipArchiveEntry zipArchiveEntry) {
            this.localDataStart = 0L;
            this.dataStart = 0L;
            this.bytesRead = 0L;
            this.causedUseOfZip64 = false;
            this.entry = zipArchiveEntry;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnicodeExtraFieldPolicy {
        public static final UnicodeExtraFieldPolicy ALWAYS = new UnicodeExtraFieldPolicy("always");
        public static final UnicodeExtraFieldPolicy NEVER = new UnicodeExtraFieldPolicy("never");
        public static final UnicodeExtraFieldPolicy NOT_ENCODEABLE = new UnicodeExtraFieldPolicy("not encodeable");
        public final String name;

        public UnicodeExtraFieldPolicy(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    public ZipArchiveOutputStream(OutputStream outputStream) {
        this.finished = false;
        this.comment = "";
        this.level = -1;
        this.hasCompressionLevelChanged = false;
        this.method = 8;
        this.entries = new LinkedList();
        this.crc = new CRC32();
        this.written = 0L;
        this.cdOffset = 0L;
        this.cdLength = 0L;
        this.offsets = new HashMap();
        this.encoding = "UTF8";
        this.zipEncoding = ZipEncodingHelper.getZipEncoding("UTF8");
        this.def = new Deflater(this.level, true);
        this.buf = new byte[512];
        this.useUTF8Flag = true;
        this.fallbackToUTF8 = false;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.hasUsedZip64 = false;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.out = outputStream;
        this.raf = null;
    }

    public final void addUnicodeExtraFields(ZipArchiveEntry zipArchiveEntry, boolean z, ByteBuffer byteBuffer) throws IOException {
        UnicodeExtraFieldPolicy unicodeExtraFieldPolicy = this.createUnicodeExtraFields;
        UnicodeExtraFieldPolicy unicodeExtraFieldPolicy2 = UnicodeExtraFieldPolicy.ALWAYS;
        if (unicodeExtraFieldPolicy == unicodeExtraFieldPolicy2 || !z) {
            zipArchiveEntry.addExtraField(new UnicodePathExtraField(zipArchiveEntry.getName(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position()));
        }
        String comment = zipArchiveEntry.getComment();
        if (comment == null || "".equals(comment)) {
            return;
        }
        boolean zCanEncode = this.zipEncoding.canEncode(comment);
        if (this.createUnicodeExtraFields == unicodeExtraFieldPolicy2 || !zCanEncode) {
            ByteBuffer byteBufferEncode = getEntryEncoding(zipArchiveEntry).encode(comment);
            zipArchiveEntry.addExtraField(new UnicodeCommentExtraField(comment, byteBufferEncode.array(), byteBufferEncode.arrayOffset(), byteBufferEncode.limit() - byteBufferEncode.position()));
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        if (archiveEntry instanceof ZipArchiveEntry) {
            return ZipUtil.canHandleEntryData((ZipArchiveEntry) archiveEntry);
        }
        return false;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
        destroy();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        CurrentEntry currentEntry = this.entry;
        if (currentEntry == null) {
            throw new IOException("No current entry to close");
        }
        if (!currentEntry.hasWritten) {
            write(EMPTY, 0, 0);
        }
        flushDeflater();
        Zip64Mode effectiveZip64Mode = getEffectiveZip64Mode(this.entry.entry);
        long j = this.written - this.entry.dataStart;
        long value = this.crc.getValue();
        this.crc.reset();
        boolean zHandleSizesAndCrc = handleSizesAndCrc(j, value, effectiveZip64Mode);
        if (this.raf != null) {
            rewriteSizesAndCrc(zHandleSizesAndCrc);
        }
        writeDataDescriptor(this.entry.entry);
        this.entry = null;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ZipArchiveEntry(file, str);
    }

    public final void deflate() throws IOException {
        Deflater deflater = this.def;
        byte[] bArr = this.buf;
        int iDeflate = deflater.deflate(bArr, 0, bArr.length);
        if (iDeflate > 0) {
            writeOut(this.buf, 0, iDeflate);
            this.written += (long) iDeflate;
        }
    }

    public final void deflateUntilInputIsNeeded() throws IOException {
        while (!this.def.needsInput()) {
            deflate();
        }
    }

    public void destroy() throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.close();
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        if (this.entry != null) {
            throw new IOException("This archive contains unclosed entries.");
        }
        this.cdOffset = this.written;
        Iterator<ZipArchiveEntry> it = this.entries.iterator();
        while (it.hasNext()) {
            writeCentralFileHeader(it.next());
        }
        this.cdLength = this.written - this.cdOffset;
        writeZip64CentralDirectory();
        writeCentralDirectoryEnd();
        this.offsets.clear();
        this.entries.clear();
        this.def.end();
        this.finished = true;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    public final void flushDeflater() throws IOException {
        if (this.entry.entry.getMethod() == 8) {
            this.def.finish();
            while (!this.def.finished()) {
                deflate();
            }
        }
    }

    public final Zip64Mode getEffectiveZip64Mode(ZipArchiveEntry zipArchiveEntry) {
        return (this.zip64Mode == Zip64Mode.AsNeeded && this.raf == null && zipArchiveEntry.getMethod() == 8 && zipArchiveEntry.getSize() == -1) ? Zip64Mode.Never : this.zip64Mode;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public final ZipEncoding getEntryEncoding(ZipArchiveEntry zipArchiveEntry) {
        return (this.zipEncoding.canEncode(zipArchiveEntry.getName()) || !this.fallbackToUTF8) ? this.zipEncoding : ZipEncodingHelper.UTF8_ZIP_ENCODING;
    }

    public final ByteBuffer getName(ZipArchiveEntry zipArchiveEntry) throws IOException {
        return getEntryEncoding(zipArchiveEntry).encode(zipArchiveEntry.getName());
    }

    public final Zip64ExtendedInformationExtraField getZip64Extra(ZipArchiveEntry zipArchiveEntry) {
        CurrentEntry currentEntry = this.entry;
        if (currentEntry != null) {
            currentEntry.causedUseOfZip64 = !this.hasUsedZip64;
        }
        this.hasUsedZip64 = true;
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (zip64ExtendedInformationExtraField == null) {
            zip64ExtendedInformationExtraField = new Zip64ExtendedInformationExtraField();
        }
        zipArchiveEntry.addAsFirstExtraField(zip64ExtendedInformationExtraField);
        return zip64ExtendedInformationExtraField;
    }

    public final boolean handleSizesAndCrc(long j, long j2, Zip64Mode zip64Mode) throws ZipException {
        if (this.entry.entry.getMethod() == 8) {
            CurrentEntry currentEntry = this.entry;
            currentEntry.entry.setSize(currentEntry.bytesRead);
            this.entry.entry.setCompressedSize(j);
            this.entry.entry.setCrc(j2);
            this.def.reset();
        } else if (this.raf != null) {
            this.entry.entry.setSize(j);
            this.entry.entry.setCompressedSize(j);
            this.entry.entry.setCrc(j2);
        } else {
            if (this.entry.entry.getCrc() != j2) {
                throw new ZipException("bad CRC checksum for entry " + this.entry.entry.getName() + ": " + Long.toHexString(this.entry.entry.getCrc()) + " instead of " + Long.toHexString(j2));
            }
            if (this.entry.entry.getSize() != j) {
                throw new ZipException("bad size for entry " + this.entry.entry.getName() + ": " + this.entry.entry.getSize() + " instead of " + j);
            }
        }
        boolean z = zip64Mode == Zip64Mode.Always || this.entry.entry.getSize() >= 4294967295L || this.entry.entry.getCompressedSize() >= 4294967295L;
        if (z && zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
        return z;
    }

    public final void handleZip64Extra(ZipArchiveEntry zipArchiveEntry, long j, boolean z) {
        if (z) {
            Zip64ExtendedInformationExtraField zip64Extra = getZip64Extra(zipArchiveEntry);
            if (zipArchiveEntry.getCompressedSize() >= 4294967295L || zipArchiveEntry.getSize() >= 4294967295L) {
                zip64Extra.setCompressedSize(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
                zip64Extra.setSize(new ZipEightByteInteger(zipArchiveEntry.getSize()));
            } else {
                zip64Extra.setCompressedSize(null);
                zip64Extra.setSize(null);
            }
            if (j >= 4294967295L) {
                zip64Extra.setRelativeHeaderOffset(new ZipEightByteInteger(j));
            }
            zipArchiveEntry.setExtra();
        }
    }

    public final boolean hasZip64Extra(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID) != null;
    }

    public boolean isSeekable() {
        return this.raf != null;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (this.entry != null) {
            closeArchiveEntry();
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        this.entry = new CurrentEntry(zipArchiveEntry);
        this.entries.add(zipArchiveEntry);
        setDefaults(this.entry.entry);
        Zip64Mode effectiveZip64Mode = getEffectiveZip64Mode(this.entry.entry);
        validateSizeInformation(effectiveZip64Mode);
        if (shouldAddZip64Extra(this.entry.entry, effectiveZip64Mode)) {
            Zip64ExtendedInformationExtraField zip64Extra = getZip64Extra(this.entry.entry);
            ZipEightByteInteger zipEightByteInteger = ZipEightByteInteger.ZERO;
            if (this.entry.entry.getMethod() == 0 && this.entry.entry.getSize() != -1) {
                zipEightByteInteger = new ZipEightByteInteger(this.entry.entry.getSize());
            }
            zip64Extra.setSize(zipEightByteInteger);
            zip64Extra.setCompressedSize(zipEightByteInteger);
            this.entry.entry.setExtra();
        }
        if (this.entry.entry.getMethod() == 8 && this.hasCompressionLevelChanged) {
            this.def.setLevel(this.level);
            this.hasCompressionLevelChanged = false;
        }
        writeLocalFileHeader(this.entry.entry);
    }

    public final void rewriteSizesAndCrc(boolean z) throws IOException {
        long filePointer = this.raf.getFilePointer();
        this.raf.seek(this.entry.localDataStart);
        writeOut(ZipLong.getBytes(this.entry.entry.getCrc()), 0, 4);
        if (hasZip64Extra(this.entry.entry) && z) {
            ZipLong zipLong = ZipLong.ZIP64_MAGIC;
            writeOut(ZipLong.getBytes(zipLong.value), 0, 4);
            writeOut(ZipLong.getBytes(zipLong.value), 0, 4);
        } else {
            writeOut(ZipLong.getBytes(this.entry.entry.getCompressedSize()), 0, 4);
            writeOut(ZipLong.getBytes(this.entry.entry.getSize()), 0, 4);
        }
        if (hasZip64Extra(this.entry.entry)) {
            RandomAccessFile randomAccessFile = this.raf;
            CurrentEntry currentEntry = this.entry;
            randomAccessFile.seek(currentEntry.localDataStart + 16 + ((long) getName(currentEntry.entry).limit()) + 4);
            writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getSize()), 0, 8);
            writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getCompressedSize()), 0, 8);
            if (!z) {
                this.raf.seek(this.entry.localDataStart - 10);
                writeOut(ZipShort.getBytes(10), 0, 2);
                this.entry.entry.removeExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
                this.entry.entry.setExtra();
                if (this.entry.causedUseOfZip64) {
                    this.hasUsedZip64 = false;
                }
            }
        }
        this.raf.seek(filePointer);
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setCreateUnicodeExtraFields(UnicodeExtraFieldPolicy unicodeExtraFieldPolicy) {
        this.createUnicodeExtraFields = unicodeExtraFieldPolicy;
    }

    public final void setDefaults(ZipArchiveEntry zipArchiveEntry) {
        if (zipArchiveEntry.getMethod() == -1) {
            zipArchiveEntry.setMethod(this.method);
        }
        if (zipArchiveEntry.getTime() == -1) {
            zipArchiveEntry.setTime(System.currentTimeMillis());
        }
    }

    public void setEncoding(String str) {
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        if (!this.useUTF8Flag || ZipEncodingHelper.isUTF8(str)) {
            return;
        }
        this.useUTF8Flag = false;
    }

    public void setFallbackToUTF8(boolean z) {
        this.fallbackToUTF8 = z;
    }

    public void setLevel(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid compression level: ", i));
        }
        this.hasCompressionLevelChanged = this.level != i;
        this.level = i;
    }

    public void setMethod(int i) {
        this.method = i;
    }

    public void setUseLanguageEncodingFlag(boolean z) {
        this.useUTF8Flag = z && ZipEncodingHelper.isUTF8(this.encoding);
    }

    public void setUseZip64(Zip64Mode zip64Mode) {
        this.zip64Mode = zip64Mode;
    }

    public final boolean shouldAddZip64Extra(ZipArchiveEntry zipArchiveEntry, Zip64Mode zip64Mode) {
        if (zip64Mode == Zip64Mode.Always || zipArchiveEntry.getSize() >= 4294967295L || zipArchiveEntry.getCompressedSize() >= 4294967295L) {
            return true;
        }
        return (zipArchiveEntry.getSize() != -1 || this.raf == null || zip64Mode == Zip64Mode.Never) ? false : true;
    }

    public final void validateSizeInformation(Zip64Mode zip64Mode) throws ZipException {
        if (this.entry.entry.getMethod() == 0 && this.raf == null) {
            if (this.entry.entry.getSize() == -1) {
                throw new ZipException("uncompressed size is required for STORED method when not writing to a file");
            }
            if (this.entry.entry.getCrc() == -1) {
                throw new ZipException("crc checksum is required for STORED method when not writing to a file");
            }
            ZipArchiveEntry zipArchiveEntry = this.entry.entry;
            zipArchiveEntry.setCompressedSize(zipArchiveEntry.getSize());
        }
        if ((this.entry.entry.getSize() >= 4294967295L || this.entry.entry.getCompressedSize() >= 4294967295L) && zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        ZipUtil.checkRequestedFeatures(this.entry.entry);
        CurrentEntry currentEntry = this.entry;
        currentEntry.hasWritten = true;
        if (currentEntry.entry.getMethod() == 8) {
            writeDeflated(bArr, i, i2);
        } else {
            writeOut(bArr, i, i2);
            this.written += (long) i2;
        }
        this.crc.update(bArr, i, i2);
        count(i2);
    }

    public void writeCentralDirectoryEnd() throws IOException {
        writeOut(EOCD_SIG);
        byte[] bArr = ZERO;
        writeOut(bArr);
        writeOut(bArr, 0, bArr.length);
        int size = this.entries.size();
        if (size > 65535 && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.TOO_MANY_ENTRIES_MESSAGE);
        }
        if (this.cdOffset > 4294967295L && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.ARCHIVE_TOO_BIG_MESSAGE);
        }
        byte[] bytes = ZipShort.getBytes(Math.min(size, 65535));
        writeOut(bytes, 0, 2);
        writeOut(bytes, 0, 2);
        writeOut(ZipLong.getBytes(Math.min(this.cdLength, 4294967295L)), 0, 4);
        writeOut(ZipLong.getBytes(Math.min(this.cdOffset, 4294967295L)), 0, 4);
        ByteBuffer byteBufferEncode = this.zipEncoding.encode(this.comment);
        writeOut(ZipShort.getBytes(byteBufferEncode.limit()), 0, 2);
        writeOut(byteBufferEncode.array(), byteBufferEncode.arrayOffset(), byteBufferEncode.limit() - byteBufferEncode.position());
    }

    public void writeCentralFileHeader(ZipArchiveEntry zipArchiveEntry) throws IOException {
        writeOut(CFH_SIG);
        this.written += 4;
        long jLongValue = this.offsets.get(zipArchiveEntry).longValue();
        boolean z = hasZip64Extra(zipArchiveEntry) || zipArchiveEntry.getCompressedSize() >= 4294967295L || zipArchiveEntry.getSize() >= 4294967295L || jLongValue >= 4294967295L;
        if (z && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.ARCHIVE_TOO_BIG_MESSAGE);
        }
        handleZip64Extra(zipArchiveEntry, jLongValue, z);
        writeOut(ZipShort.getBytes((zipArchiveEntry.getPlatform() << 8) | (!this.hasUsedZip64 ? 20 : 45)), 0, 2);
        this.written += 2;
        int method = zipArchiveEntry.getMethod();
        writeVersionNeededToExtractAndGeneralPurposeBits(method, !this.zipEncoding.canEncode(zipArchiveEntry.getName()) && this.fallbackToUTF8, z);
        this.written += 4;
        writeOut(ZipShort.getBytes(method), 0, 2);
        this.written += 2;
        writeOut(ZipUtil.toDosTime(zipArchiveEntry.getTime()));
        this.written += 4;
        writeOut(ZipLong.getBytes(zipArchiveEntry.getCrc()), 0, 4);
        if (zipArchiveEntry.getCompressedSize() >= 4294967295L || zipArchiveEntry.getSize() >= 4294967295L) {
            ZipLong zipLong = ZipLong.ZIP64_MAGIC;
            writeOut(ZipLong.getBytes(zipLong.value), 0, 4);
            writeOut(ZipLong.getBytes(zipLong.value), 0, 4);
        } else {
            writeOut(ZipLong.getBytes(zipArchiveEntry.getCompressedSize()), 0, 4);
            writeOut(ZipLong.getBytes(zipArchiveEntry.getSize()), 0, 4);
        }
        this.written += 12;
        ByteBuffer byteBufferEncode = getEntryEncoding(zipArchiveEntry).encode(zipArchiveEntry.getName());
        writeOut(ZipShort.getBytes(byteBufferEncode.limit()), 0, 2);
        this.written += 2;
        byte[] centralDirectoryExtra = zipArchiveEntry.getCentralDirectoryExtra();
        writeOut(ZipShort.getBytes(centralDirectoryExtra.length), 0, 2);
        this.written += 2;
        String comment = zipArchiveEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        ByteBuffer byteBufferEncode2 = getEntryEncoding(zipArchiveEntry).encode(comment);
        writeOut(ZipShort.getBytes(byteBufferEncode2.limit()), 0, 2);
        this.written += 2;
        writeOut(ZERO);
        this.written += 2;
        writeOut(ZipShort.getBytes(zipArchiveEntry.getInternalAttributes()), 0, 2);
        this.written += 2;
        writeOut(ZipLong.getBytes(zipArchiveEntry.getExternalAttributes()), 0, 4);
        this.written += 4;
        writeOut(ZipLong.getBytes(Math.min(jLongValue, 4294967295L)), 0, 4);
        this.written += 4;
        writeOut(byteBufferEncode.array(), byteBufferEncode.arrayOffset(), byteBufferEncode.limit() - byteBufferEncode.position());
        this.written += (long) byteBufferEncode.limit();
        writeOut(centralDirectoryExtra, 0, centralDirectoryExtra.length);
        this.written += (long) centralDirectoryExtra.length;
        writeOut(byteBufferEncode2.array(), byteBufferEncode2.arrayOffset(), byteBufferEncode2.limit() - byteBufferEncode2.position());
        this.written += (long) byteBufferEncode2.limit();
    }

    public void writeDataDescriptor(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (zipArchiveEntry.getMethod() == 8 && this.raf == null) {
            writeOut(DD_SIG);
            int i = 4;
            writeOut(ZipLong.getBytes(zipArchiveEntry.getCrc()), 0, 4);
            if (hasZip64Extra(zipArchiveEntry)) {
                writeOut(ZipEightByteInteger.getBytes(zipArchiveEntry.getCompressedSize()), 0, 8);
                writeOut(ZipEightByteInteger.getBytes(zipArchiveEntry.getSize()), 0, 8);
                i = 8;
            } else {
                writeOut(ZipLong.getBytes(zipArchiveEntry.getCompressedSize()), 0, 4);
                writeOut(ZipLong.getBytes(zipArchiveEntry.getSize()), 0, 4);
            }
            this.written += (long) ((i * 2) + 8);
        }
    }

    public final void writeDeflated(byte[] bArr, int i, int i2) throws IOException {
        if (i2 <= 0 || this.def.finished()) {
            return;
        }
        CurrentEntry.access$314(this.entry, i2);
        if (i2 <= 8192) {
            this.def.setInput(bArr, i, i2);
            deflateUntilInputIsNeeded();
            return;
        }
        int i3 = i2 / 8192;
        for (int i4 = 0; i4 < i3; i4++) {
            this.def.setInput(bArr, (i4 * 8192) + i, 8192);
            deflateUntilInputIsNeeded();
        }
        int i5 = i3 * 8192;
        if (i5 < i2) {
            this.def.setInput(bArr, i + i5, i2 - i5);
            deflateUntilInputIsNeeded();
        }
    }

    public void writeLocalFileHeader(ZipArchiveEntry zipArchiveEntry) throws IOException {
        boolean zCanEncode = this.zipEncoding.canEncode(zipArchiveEntry.getName());
        ByteBuffer byteBufferEncode = getEntryEncoding(zipArchiveEntry).encode(zipArchiveEntry.getName());
        if (this.createUnicodeExtraFields != UnicodeExtraFieldPolicy.NEVER) {
            addUnicodeExtraFields(zipArchiveEntry, zCanEncode, byteBufferEncode);
        }
        this.offsets.put(zipArchiveEntry, Long.valueOf(this.written));
        writeOut(LFH_SIG);
        this.written += 4;
        int method = zipArchiveEntry.getMethod();
        writeVersionNeededToExtractAndGeneralPurposeBits(method, !zCanEncode && this.fallbackToUTF8, hasZip64Extra(zipArchiveEntry));
        this.written += 4;
        writeOut(ZipShort.getBytes(method), 0, 2);
        this.written += 2;
        writeOut(ZipUtil.toDosTime(zipArchiveEntry.getTime()));
        long j = this.written + 4;
        this.written = j;
        this.entry.localDataStart = j;
        if (method == 8 || this.raf != null) {
            byte[] bArr = LZERO;
            writeOut(bArr);
            if (hasZip64Extra(this.entry.entry)) {
                ZipLong zipLong = ZipLong.ZIP64_MAGIC;
                writeOut(ZipLong.getBytes(zipLong.value), 0, 4);
                writeOut(ZipLong.getBytes(zipLong.value), 0, 4);
            } else {
                writeOut(bArr, 0, bArr.length);
                writeOut(bArr, 0, bArr.length);
            }
        } else {
            writeOut(ZipLong.getBytes(zipArchiveEntry.getCrc()), 0, 4);
            byte[] bytes = ZipLong.getBytes(ZipLong.ZIP64_MAGIC.value);
            if (!hasZip64Extra(zipArchiveEntry)) {
                bytes = ZipLong.getBytes(zipArchiveEntry.getSize());
            }
            writeOut(bytes, 0, bytes.length);
            writeOut(bytes, 0, bytes.length);
        }
        this.written += 12;
        writeOut(ZipShort.getBytes(byteBufferEncode.limit()), 0, 2);
        this.written += 2;
        byte[] localFileDataExtra = zipArchiveEntry.getLocalFileDataExtra();
        writeOut(ZipShort.getBytes(localFileDataExtra.length), 0, 2);
        this.written += 2;
        writeOut(byteBufferEncode.array(), byteBufferEncode.arrayOffset(), byteBufferEncode.limit() - byteBufferEncode.position());
        this.written += (long) byteBufferEncode.limit();
        writeOut(localFileDataExtra, 0, localFileDataExtra.length);
        long length = this.written + ((long) localFileDataExtra.length);
        this.written = length;
        this.entry.dataStart = length;
    }

    public final void writeOut(byte[] bArr) throws IOException {
        writeOut(bArr, 0, bArr.length);
    }

    public final void writeVersionNeededToExtractAndGeneralPurposeBits(int i, boolean z, boolean z2) throws IOException {
        int i2;
        GeneralPurposeBit generalPurposeBit = new GeneralPurposeBit();
        generalPurposeBit.languageEncodingFlag = this.useUTF8Flag || z;
        if (i == 8 && this.raf == null) {
            generalPurposeBit.dataDescriptorFlag = true;
            i2 = 20;
        } else {
            i2 = 10;
        }
        if (z2) {
            i2 = 45;
        }
        writeOut(ZipShort.getBytes(i2), 0, 2);
        writeOut(generalPurposeBit.encode(), 0, 2);
    }

    public void writeZip64CentralDirectory() throws IOException {
        if (this.zip64Mode == Zip64Mode.Never) {
            return;
        }
        if (!this.hasUsedZip64 && (this.cdOffset >= 4294967295L || this.cdLength >= 4294967295L || this.entries.size() >= 65535)) {
            this.hasUsedZip64 = true;
        }
        if (this.hasUsedZip64) {
            long j = this.written;
            writeOut(ZIP64_EOCD_SIG);
            writeOut(ZipEightByteInteger.getBytes(44L), 0, 8);
            writeOut(ZipShort.getBytes(45), 0, 2);
            writeOut(ZipShort.getBytes(45), 0, 2);
            byte[] bArr = LZERO;
            writeOut(bArr);
            writeOut(bArr);
            byte[] bytes = ZipEightByteInteger.getBytes(this.entries.size());
            writeOut(bytes, 0, 8);
            writeOut(bytes, 0, 8);
            writeOut(ZipEightByteInteger.getBytes(this.cdLength), 0, 8);
            writeOut(ZipEightByteInteger.getBytes(this.cdOffset), 0, 8);
            writeOut(ZIP64_EOCD_LOC_SIG);
            writeOut(bArr);
            writeOut(ZipEightByteInteger.getBytes(j), 0, 8);
            writeOut(ONE);
        }
    }

    public final void writeOut(byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.write(bArr, i, i2);
        } else {
            this.out.write(bArr, i, i2);
        }
    }

    public ZipArchiveOutputStream(File file) throws IOException {
        RandomAccessFile randomAccessFile;
        this.finished = false;
        this.comment = "";
        this.level = -1;
        this.hasCompressionLevelChanged = false;
        this.method = 8;
        this.entries = new LinkedList();
        this.crc = new CRC32();
        this.written = 0L;
        this.cdOffset = 0L;
        this.cdLength = 0L;
        this.offsets = new HashMap();
        this.encoding = "UTF8";
        this.zipEncoding = ZipEncodingHelper.getZipEncoding("UTF8");
        this.def = new Deflater(this.level, true);
        this.buf = new byte[512];
        this.useUTF8Flag = true;
        this.fallbackToUTF8 = false;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.hasUsedZip64 = false;
        this.zip64Mode = Zip64Mode.AsNeeded;
        RandomAccessFile randomAccessFile2 = null;
        FileOutputStream fileOutputStream = null;
        randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
        } catch (IOException unused) {
            randomAccessFile = null;
        }
        try {
            randomAccessFile.setLength(0L);
        } catch (IOException unused2) {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException unused3) {
                }
            } else {
                randomAccessFile2 = randomAccessFile;
            }
            randomAccessFile = randomAccessFile2;
            fileOutputStream = new FileOutputStream(file);
        }
        this.out = fileOutputStream;
        this.raf = randomAccessFile;
    }
}
