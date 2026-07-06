package org.apache.commons.compress.archivers.zip;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;
import org.apache.commons.compress.utils.IOUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ZipFile {
    public static final int BYTE_SHIFT = 8;
    public static final int CFD_LOCATOR_OFFSET = 16;
    public static final int CFH_LEN = 42;
    public static final long CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG, 0);
    public static final int HASH_SIZE = 509;
    public static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26;
    public static final int MAX_EOCD_SIZE = 65557;
    public static final int MIN_EOCD_SIZE = 22;
    public static final int NIBLET_MASK = 15;
    public static final int POS_0 = 0;
    public static final int POS_1 = 1;
    public static final int POS_2 = 2;
    public static final int POS_3 = 3;
    public static final int ZIP64_EOCDL_LENGTH = 20;
    public static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
    public static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
    public final byte[] CFH_BUF;
    public final byte[] DWORD_BUF;
    public final Comparator<ZipArchiveEntry> OFFSET_COMPARATOR;
    public final byte[] SHORT_BUF;
    public final byte[] WORD_BUF;
    public final RandomAccessFile archive;
    public final String archiveName;
    public boolean closed;
    public final String encoding;
    public final Map<ZipArchiveEntry, OffsetEntry> entries;
    public final Map<String, ZipArchiveEntry> nameMap;
    public final boolean useUnicodeExtraFields;
    public final ZipEncoding zipEncoding;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NameAndComment {
        public final byte[] comment;
        public final byte[] name;

        public NameAndComment(byte[] bArr, byte[] bArr2) {
            this.name = bArr;
            this.comment = bArr2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OffsetEntry {
        public long dataOffset;
        public long headerOffset;

        public OffsetEntry() {
            this.headerOffset = -1L;
            this.dataOffset = -1L;
        }
    }

    public ZipFile(File file) throws IOException {
        this(file, "UTF8", true);
    }

    public static void closeQuietly(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException unused) {
            }
        }
    }

    public boolean canReadEntryData(ZipArchiveEntry zipArchiveEntry) {
        return ZipUtil.canHandleEntryData(zipArchiveEntry);
    }

    public void close() throws IOException {
        this.closed = true;
        this.archive.close();
    }

    public void finalize() throws Throwable {
        try {
            if (!this.closed) {
                System.err.println("Cleaning up unclosed ZipFile for archive " + this.archiveName);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public Enumeration<ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.entries.keySet());
    }

    public Enumeration<ZipArchiveEntry> getEntriesInPhysicalOrder() {
        ZipArchiveEntry[] zipArchiveEntryArr = (ZipArchiveEntry[]) this.entries.keySet().toArray(new ZipArchiveEntry[0]);
        Arrays.sort(zipArchiveEntryArr, this.OFFSET_COMPARATOR);
        return Collections.enumeration(Arrays.asList(zipArchiveEntryArr));
    }

    public ZipArchiveEntry getEntry(String str) {
        return this.nameMap.get(str);
    }

    public InputStream getInputStream(ZipArchiveEntry zipArchiveEntry) throws IOException {
        OffsetEntry offsetEntry = this.entries.get(zipArchiveEntry);
        if (offsetEntry == null) {
            return null;
        }
        ZipUtil.checkRequestedFeatures(zipArchiveEntry);
        BoundedInputStream boundedInputStream = new BoundedInputStream(offsetEntry.dataOffset, zipArchiveEntry.getCompressedSize());
        int method = zipArchiveEntry.getMethod();
        if (method == 0) {
            return boundedInputStream;
        }
        if (method == 8) {
            boundedInputStream.addDummyByte = true;
            final Inflater inflater = new Inflater(true);
            return new InflaterInputStream(boundedInputStream, inflater) { // from class: org.apache.commons.compress.archivers.zip.ZipFile.1
                @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    super.close();
                    inflater.end();
                }
            };
        }
        throw new ZipException("Found unsupported compression method " + zipArchiveEntry.getMethod());
    }

    public String getUnixSymlink(ZipArchiveEntry zipArchiveEntry) throws IOException {
        InputStream inputStream = null;
        if (zipArchiveEntry == null || !zipArchiveEntry.isUnixSymlink()) {
            return null;
        }
        try {
            inputStream = getInputStream(zipArchiveEntry);
            return this.zipEncoding.decode(IOUtils.toByteArray(inputStream));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public final Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory() throws IOException {
        HashMap map = new HashMap();
        positionAtCentralDirectory();
        this.archive.readFully(this.WORD_BUF);
        long value = ZipLong.getValue(this.WORD_BUF, 0);
        if (value != CFH_SIG && startsWithLocalFileHeader()) {
            throw new IOException("central directory is empty, can't expand corrupt archive.");
        }
        while (value == CFH_SIG) {
            readCentralDirectoryEntry(map);
            this.archive.readFully(this.WORD_BUF);
            value = ZipLong.getValue(this.WORD_BUF, 0);
        }
        return map;
    }

    public final void positionAtCentralDirectory() throws IOException {
        positionAtEndOfCentralDirectoryRecord();
        boolean zEquals = false;
        boolean z = this.archive.getFilePointer() > 20;
        if (z) {
            RandomAccessFile randomAccessFile = this.archive;
            randomAccessFile.seek(randomAccessFile.getFilePointer() - 20);
            this.archive.readFully(this.WORD_BUF);
            zEquals = Arrays.equals(ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG, this.WORD_BUF);
        }
        if (zEquals) {
            positionAtCentralDirectory64();
            return;
        }
        if (z) {
            skipBytes(16);
        }
        positionAtCentralDirectory32();
    }

    public final void positionAtCentralDirectory32() throws IOException {
        skipBytes(16);
        this.archive.readFully(this.WORD_BUF);
        this.archive.seek(ZipLong.getValue(this.WORD_BUF, 0));
    }

    public final void positionAtCentralDirectory64() throws IOException {
        skipBytes(4);
        this.archive.readFully(this.DWORD_BUF);
        this.archive.seek(ZipEightByteInteger.getLongValue(this.DWORD_BUF));
        this.archive.readFully(this.WORD_BUF);
        if (!Arrays.equals(this.WORD_BUF, ZipArchiveOutputStream.ZIP64_EOCD_SIG)) {
            throw new ZipException("archive's ZIP64 end of central directory locator is corrupt.");
        }
        skipBytes(44);
        this.archive.readFully(this.DWORD_BUF);
        this.archive.seek(ZipEightByteInteger.getLongValue(this.DWORD_BUF, 0));
    }

    public final void positionAtEndOfCentralDirectoryRecord() throws IOException {
        if (!tryToLocateSignature(22L, 65557L, ZipArchiveOutputStream.EOCD_SIG)) {
            throw new ZipException("archive is not a ZIP archive");
        }
    }

    public final void readCentralDirectoryEntry(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        this.archive.readFully(this.CFH_BUF);
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry();
        zipArchiveEntry.platform = (ZipShort.getValue(this.CFH_BUF, 0) >> 8) & 15;
        GeneralPurposeBit generalPurposeBit = GeneralPurposeBit.parse(this.CFH_BUF, 4);
        boolean z = generalPurposeBit.languageEncodingFlag;
        ZipEncoding zipEncoding = z ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
        zipArchiveEntry.gpb = generalPurposeBit;
        zipArchiveEntry.setMethod(ZipShort.getValue(this.CFH_BUF, 6));
        zipArchiveEntry.setTime(ZipUtil.dosToJavaTime(ZipLong.getValue(this.CFH_BUF, 8)));
        zipArchiveEntry.setCrc(ZipLong.getValue(this.CFH_BUF, 12));
        zipArchiveEntry.setCompressedSize(ZipLong.getValue(this.CFH_BUF, 16));
        zipArchiveEntry.setSize(ZipLong.getValue(this.CFH_BUF, 20));
        int value = ZipShort.getValue(this.CFH_BUF, 24);
        int value2 = ZipShort.getValue(this.CFH_BUF, 26);
        int value3 = ZipShort.getValue(this.CFH_BUF, 28);
        int value4 = ZipShort.getValue(this.CFH_BUF, 30);
        zipArchiveEntry.internalAttributes = ZipShort.getValue(this.CFH_BUF, 32);
        zipArchiveEntry.externalAttributes = ZipLong.getValue(this.CFH_BUF, 34);
        byte[] bArr = new byte[value];
        this.archive.readFully(bArr);
        zipArchiveEntry.setName(zipEncoding.decode(bArr), bArr);
        OffsetEntry offsetEntry = new OffsetEntry();
        offsetEntry.headerOffset = ZipLong.getValue(this.CFH_BUF, 38);
        this.entries.put(zipArchiveEntry, offsetEntry);
        this.nameMap.put(zipArchiveEntry.getName(), zipArchiveEntry);
        byte[] bArr2 = new byte[value2];
        this.archive.readFully(bArr2);
        zipArchiveEntry.setCentralDirectoryExtra(bArr2);
        setSizesAndOffsetFromZip64Extra(zipArchiveEntry, offsetEntry, value4);
        byte[] bArr3 = new byte[value3];
        this.archive.readFully(bArr3);
        zipArchiveEntry.setComment(zipEncoding.decode(bArr3));
        if (z || !this.useUnicodeExtraFields) {
            return;
        }
        map.put(zipArchiveEntry, new NameAndComment(bArr, bArr3));
    }

    public final void resolveLocalFileHeaderData(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
        this.entries.clear();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) entry.getKey();
            OffsetEntry offsetEntry = (OffsetEntry) entry.getValue();
            long j = offsetEntry.headerOffset;
            this.archive.seek(26 + j);
            this.archive.readFully(this.SHORT_BUF);
            int value = ZipShort.getValue(this.SHORT_BUF, 0);
            this.archive.readFully(this.SHORT_BUF);
            int value2 = ZipShort.getValue(this.SHORT_BUF, 0);
            int i = value;
            while (i > 0) {
                int iSkipBytes = this.archive.skipBytes(i);
                if (iSkipBytes <= 0) {
                    throw new IOException("failed to skip file name in local file header");
                }
                i -= iSkipBytes;
            }
            byte[] bArr = new byte[value2];
            this.archive.readFully(bArr);
            zipArchiveEntry.setExtra(bArr);
            offsetEntry.dataOffset = j + 30 + ((long) value) + ((long) value2);
            if (map.containsKey(zipArchiveEntry)) {
                String name = zipArchiveEntry.getName();
                NameAndComment nameAndComment = map.get(zipArchiveEntry);
                ZipUtil.setNameAndCommentFromExtraFields(zipArchiveEntry, nameAndComment.name, nameAndComment.comment);
                if (!name.equals(zipArchiveEntry.getName())) {
                    this.nameMap.remove(name);
                    this.nameMap.put(zipArchiveEntry.getName(), zipArchiveEntry);
                }
            }
            this.entries.put(zipArchiveEntry, offsetEntry);
        }
    }

    public final void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry zipArchiveEntry, OffsetEntry offsetEntry, int i) throws IOException {
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (zip64ExtendedInformationExtraField != null) {
            boolean z = zipArchiveEntry.getSize() == 4294967295L;
            boolean z2 = zipArchiveEntry.getCompressedSize() == 4294967295L;
            boolean z3 = offsetEntry.headerOffset == 4294967295L;
            zip64ExtendedInformationExtraField.reparseCentralDirectoryData(z, z2, z3, i == 65535);
            if (z) {
                zipArchiveEntry.setSize(zip64ExtendedInformationExtraField.getSize().value.longValue());
            } else if (z2) {
                zip64ExtendedInformationExtraField.setSize(new ZipEightByteInteger(zipArchiveEntry.getSize()));
            }
            if (z2) {
                zipArchiveEntry.setCompressedSize(zip64ExtendedInformationExtraField.getCompressedSize().value.longValue());
            } else if (z) {
                zip64ExtendedInformationExtraField.setCompressedSize(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
            }
            if (z3) {
                offsetEntry.headerOffset = zip64ExtendedInformationExtraField.getRelativeHeaderOffset().value.longValue();
            }
        }
    }

    public final void skipBytes(int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int iSkipBytes = this.archive.skipBytes(i - i2);
            if (iSkipBytes <= 0) {
                throw new EOFException();
            }
            i2 += iSkipBytes;
        }
    }

    public final boolean startsWithLocalFileHeader() throws IOException {
        this.archive.seek(0L);
        this.archive.readFully(this.WORD_BUF);
        return Arrays.equals(this.WORD_BUF, ZipArchiveOutputStream.LFH_SIG);
    }

    public final boolean tryToLocateSignature(long j, long j2, byte[] bArr) throws IOException {
        long length = this.archive.length() - j;
        long jMax = Math.max(0L, this.archive.length() - j2);
        boolean z = false;
        if (length >= 0) {
            while (true) {
                if (length < jMax) {
                    break;
                }
                this.archive.seek(length);
                int i = this.archive.read();
                if (i == -1) {
                    break;
                }
                if (i == bArr[0] && this.archive.read() == bArr[1] && this.archive.read() == bArr[2] && this.archive.read() == bArr[3]) {
                    z = true;
                    break;
                }
                length--;
            }
        }
        if (z) {
            this.archive.seek(length);
        }
        return z;
    }

    public ZipFile(String str) throws IOException {
        this(new File(str), "UTF8", true);
    }

    public ZipFile(String str, String str2) throws IOException {
        this(new File(str), str2, true);
    }

    public ZipFile(File file, String str) throws IOException {
        this(file, str, true);
    }

    public ZipFile(File file, String str, boolean z) throws IOException {
        this.entries = new LinkedHashMap(HASH_SIZE);
        this.nameMap = new HashMap(HASH_SIZE);
        this.DWORD_BUF = new byte[8];
        this.WORD_BUF = new byte[4];
        this.CFH_BUF = new byte[42];
        this.SHORT_BUF = new byte[2];
        this.OFFSET_COMPARATOR = new Comparator<ZipArchiveEntry>() { // from class: org.apache.commons.compress.archivers.zip.ZipFile.2
            @Override // java.util.Comparator
            public int compare(ZipArchiveEntry zipArchiveEntry, ZipArchiveEntry zipArchiveEntry2) {
                if (zipArchiveEntry == zipArchiveEntry2) {
                    return 0;
                }
                OffsetEntry offsetEntry = (OffsetEntry) ZipFile.this.entries.get(zipArchiveEntry);
                OffsetEntry offsetEntry2 = ZipFile.this.entries.get(zipArchiveEntry2);
                if (offsetEntry == null) {
                    return 1;
                }
                if (offsetEntry2 == null) {
                    return -1;
                }
                long j = offsetEntry.headerOffset - offsetEntry2.headerOffset;
                if (j == 0) {
                    return 0;
                }
                return j < 0 ? -1 : 1;
            }
        };
        this.archiveName = file.getAbsolutePath();
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.useUnicodeExtraFields = z;
        this.archive = new RandomAccessFile(file, "r");
        try {
            resolveLocalFileHeaderData(populateFromCentralDirectory());
        } catch (Throwable th) {
            try {
                this.closed = true;
                this.archive.close();
            } catch (IOException unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class BoundedInputStream extends InputStream {
        public boolean addDummyByte = false;
        public long loc;
        public long remaining;

        public BoundedInputStream(long j, long j2) {
            this.remaining = j2;
            this.loc = j;
        }

        public void addDummy() {
            this.addDummyByte = true;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int i;
            long j = this.remaining;
            this.remaining = j - 1;
            if (j <= 0) {
                if (!this.addDummyByte) {
                    return -1;
                }
                this.addDummyByte = false;
                return 0;
            }
            synchronized (ZipFile.this.archive) {
                RandomAccessFile randomAccessFile = ZipFile.this.archive;
                long j2 = this.loc;
                this.loc = 1 + j2;
                randomAccessFile.seek(j2);
                i = ZipFile.this.archive.read();
            }
            return i;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            long j = this.remaining;
            if (j <= 0) {
                if (!this.addDummyByte) {
                    return -1;
                }
                this.addDummyByte = false;
                bArr[i] = 0;
                return 1;
            }
            if (i2 <= 0) {
                return 0;
            }
            if (i2 > j) {
                i2 = (int) j;
            }
            synchronized (ZipFile.this.archive) {
                ZipFile.this.archive.seek(this.loc);
                i3 = ZipFile.this.archive.read(bArr, i, i2);
            }
            if (i3 > 0) {
                long j2 = i3;
                this.loc += j2;
                this.remaining -= j2;
            }
            return i3;
        }
    }
}
