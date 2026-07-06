package org.apache.commons.compress.archivers.tar;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.base.Ascii;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TarArchiveOutputStream extends ArchiveOutputStream {
    public static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding("ASCII");
    public static final int BIGNUMBER_ERROR = 0;
    public static final int BIGNUMBER_POSIX = 2;
    public static final int BIGNUMBER_STAR = 1;
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_POSIX = 3;
    public static final int LONGFILE_TRUNCATE = 1;
    public boolean addPaxHeadersForNonAsciiNames;
    public final byte[] assemBuf;
    public int assemLen;
    public int bigNumberMode;
    public final TarBuffer buffer;
    public boolean closed;
    public long currBytes;
    public String currName;
    public long currSize;
    public final ZipEncoding encoding;
    public boolean finished;
    public boolean haveUnclosedEntry;
    public int longFileMode;
    public final OutputStream out;
    public final byte[] recordBuf;

    public TarArchiveOutputStream(OutputStream outputStream, String str) {
        this(outputStream, 10240, 512, str);
    }

    public final void addPaxHeaderForBigNumber(Map<String, String> map, String str, long j, long j2) {
        if (j < 0 || j > j2) {
            map.put(str, String.valueOf(j));
        }
    }

    public final void addPaxHeadersForBigNumbers(Map<String, String> map, TarArchiveEntry tarArchiveEntry) {
        addPaxHeaderForBigNumber(map, "size", tarArchiveEntry.getSize(), TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map, "gid", tarArchiveEntry.getGroupId(), 2097151L);
        addPaxHeaderForBigNumber(map, "mtime", tarArchiveEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map, "uid", tarArchiveEntry.getUserId(), 2097151L);
        addPaxHeaderForBigNumber(map, "SCHILY.devmajor", tarArchiveEntry.getDevMajor(), 2097151L);
        addPaxHeaderForBigNumber(map, "SCHILY.devminor", tarArchiveEntry.getDevMinor(), 2097151L);
        failForBigNumber("mode", tarArchiveEntry.getMode(), 2097151L);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
        if (this.closed) {
            return;
        }
        this.buffer.close();
        this.out.close();
        this.closed = true;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        byte[] bArr;
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (!this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        }
        int i = this.assemLen;
        if (i > 0) {
            while (true) {
                bArr = this.assemBuf;
                if (i >= bArr.length) {
                    break;
                }
                bArr[i] = 0;
                i++;
            }
            this.buffer.writeRecord(bArr);
            this.currBytes += (long) this.assemLen;
            this.assemLen = 0;
        }
        if (this.currBytes >= this.currSize) {
            this.haveUnclosedEntry = false;
            return;
        }
        StringBuilder sb = new StringBuilder("entry '");
        sb.append(this.currName);
        sb.append("' closed at '");
        sb.append(this.currBytes);
        sb.append("' before the '");
        throw new IOException(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, this.currSize, "' bytes specified in the header were written"));
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new TarArchiveEntry(file, str);
    }

    public final void failForBigNumber(String str, long j, long j2) {
        if (j < 0 || j > j2) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" '");
            sb.append(j);
            sb.append("' is too big ( > ");
            throw new RuntimeException(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, j2, " )"));
        }
    }

    public final void failForBigNumbers(TarArchiveEntry tarArchiveEntry) {
        failForBigNumber("entry size", tarArchiveEntry.getSize(), TarConstants.MAXSIZE);
        failForBigNumber("group id", tarArchiveEntry.getGroupId(), 2097151L);
        failForBigNumber("last modification time", tarArchiveEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        failForBigNumber("user id", tarArchiveEntry.getUserId(), 2097151L);
        failForBigNumber("mode", tarArchiveEntry.getMode(), 2097151L);
        failForBigNumber("major device number", tarArchiveEntry.getDevMajor(), 2097151L);
        failForBigNumber("minor device number", tarArchiveEntry.getDevMinor(), 2097151L);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        if (this.haveUnclosedEntry) {
            throw new IOException("This archives contains unclosed entries.");
        }
        writeEOFRecord();
        writeEOFRecord();
        this.buffer.flushBlock();
        this.finished = true;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public long getBytesWritten() {
        return ((CountingOutputStream) this.out).getBytesWritten();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    @Deprecated
    public int getCount() {
        return (int) getBytesWritten();
    }

    public int getRecordSize() {
        return this.buffer.getRecordSize();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        boolean z;
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        TarArchiveEntry tarArchiveEntry = (TarArchiveEntry) archiveEntry;
        HashMap map = new HashMap();
        String name = tarArchiveEntry.getName();
        ByteBuffer byteBufferEncode = this.encoding.encode(name);
        int iLimit = byteBufferEncode.limit() - byteBufferEncode.position();
        if (iLimit < 100) {
            z = false;
        } else {
            int i = this.longFileMode;
            if (i == 3) {
                map.put("path", name);
                z = true;
            } else {
                if (i == 2) {
                    TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(TarConstants.GNU_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, false);
                    tarArchiveEntry2.setSize(iLimit + 1);
                    putArchiveEntry(tarArchiveEntry2);
                    write(byteBufferEncode.array(), byteBufferEncode.arrayOffset(), iLimit);
                    write(0);
                    closeArchiveEntry();
                } else if (i != 1) {
                    throw new RuntimeException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("file name '", name, "' is too long ( > 100 bytes)"));
                }
                z = false;
            }
        }
        int i2 = this.bigNumberMode;
        if (i2 == 2) {
            addPaxHeadersForBigNumbers(map, tarArchiveEntry);
        } else if (i2 != 1) {
            failForBigNumbers(tarArchiveEntry);
        }
        if (this.addPaxHeadersForNonAsciiNames && !z && !ASCII.canEncode(name)) {
            map.put("path", name);
        }
        if (this.addPaxHeadersForNonAsciiNames && ((tarArchiveEntry.isLink() || tarArchiveEntry.isSymbolicLink()) && !ASCII.canEncode(tarArchiveEntry.getLinkName()))) {
            map.put("linkpath", tarArchiveEntry.getLinkName());
        }
        if (map.size() > 0) {
            writePaxHeaders(name, map);
        }
        tarArchiveEntry.writeEntryHeader(this.recordBuf, this.encoding, this.bigNumberMode == 1);
        this.buffer.writeRecord(this.recordBuf);
        this.currBytes = 0L;
        if (tarArchiveEntry.isDirectory()) {
            this.currSize = 0L;
        } else {
            this.currSize = tarArchiveEntry.getSize();
        }
        this.currName = name;
        this.haveUnclosedEntry = true;
    }

    public void setAddPaxHeadersForNonAsciiNames(boolean z) {
        this.addPaxHeadersForNonAsciiNames = z;
    }

    public void setBigNumberMode(int i) {
        this.bigNumberMode = i;
    }

    public void setLongFileMode(int i) {
        this.longFileMode = i;
    }

    public final String stripTo7Bits(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = (char) (str.charAt(i) & Ascii.MAX);
            if (cCharAt != 0) {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.currBytes + ((long) i2) > this.currSize) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("request to write '", i2, "' bytes exceeds size in header of '");
            sbM.append(this.currSize);
            sbM.append("' bytes for entry '");
            throw new IOException(ActivityCompat$$ExternalSyntheticOutline0.m(sbM, this.currName, "'"));
        }
        int i3 = this.assemLen;
        if (i3 > 0) {
            int i4 = i3 + i2;
            byte[] bArr2 = this.recordBuf;
            if (i4 >= bArr2.length) {
                int length = bArr2.length - i3;
                System.arraycopy(this.assemBuf, 0, bArr2, 0, i3);
                System.arraycopy(bArr, i, this.recordBuf, this.assemLen, length);
                this.buffer.writeRecord(this.recordBuf);
                this.currBytes += (long) this.recordBuf.length;
                i += length;
                i2 -= length;
                this.assemLen = 0;
            } else {
                System.arraycopy(bArr, i, this.assemBuf, i3, i2);
                i += i2;
                this.assemLen += i2;
                i2 = 0;
            }
        }
        while (i2 > 0) {
            if (i2 < this.recordBuf.length) {
                System.arraycopy(bArr, i, this.assemBuf, this.assemLen, i2);
                this.assemLen += i2;
                return;
            } else {
                this.buffer.writeRecord(bArr, i);
                int length2 = this.recordBuf.length;
                this.currBytes += (long) length2;
                i2 -= length2;
                i += length2;
            }
        }
    }

    public final void writeEOFRecord() throws IOException {
        Arrays.fill(this.recordBuf, (byte) 0);
        this.buffer.writeRecord(this.recordBuf);
    }

    public void writePaxHeaders(String str, Map<String, String> map) throws IOException {
        String strSubstring = "./PaxHeaders.X/" + stripTo7Bits(str);
        if (strSubstring.length() >= 100) {
            strSubstring = strSubstring.substring(0, 99);
        }
        while (strSubstring.endsWith(SchemaId.METRIC_SCHEMA_ID_DELIMITER)) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(strSubstring, (byte) 120, false);
        StringWriter stringWriter = new StringWriter();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int length = value.length() + key.length() + 5;
            String str2 = length + StringUtils.SPACE + key + "=" + value + StringUtils.LF;
            int length2 = str2.getBytes("UTF-8").length;
            while (length != length2) {
                str2 = length2 + StringUtils.SPACE + key + "=" + value + StringUtils.LF;
                int i = length2;
                length2 = str2.getBytes("UTF-8").length;
                length = i;
            }
            stringWriter.write(str2);
        }
        byte[] bytes = stringWriter.toString().getBytes("UTF-8");
        tarArchiveEntry.setSize(bytes.length);
        putArchiveEntry(tarArchiveEntry);
        write(bytes);
        closeArchiveEntry();
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i, String str) {
        this(outputStream, i, 512, str);
    }

    public TarArchiveOutputStream(OutputStream outputStream) {
        this(outputStream, 10240, 512, null);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, 512, null);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i, int i2) {
        this(outputStream, i, i2, null);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i, int i2, String str) {
        this.longFileMode = 0;
        this.bigNumberMode = 0;
        this.closed = false;
        this.haveUnclosedEntry = false;
        this.finished = false;
        this.addPaxHeadersForNonAsciiNames = false;
        CountingOutputStream countingOutputStream = new CountingOutputStream(outputStream);
        this.out = countingOutputStream;
        this.encoding = ZipEncodingHelper.getZipEncoding(str);
        this.buffer = new TarBuffer(null, countingOutputStream, i, i2);
        this.assemLen = 0;
        this.assemBuf = new byte[i2];
        this.recordBuf = new byte[i2];
    }
}
