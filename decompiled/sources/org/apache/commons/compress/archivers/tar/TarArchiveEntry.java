package org.apache.commons.compress.archivers.tar;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ArchiveUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TarArchiveEntry implements TarConstants, ArchiveEntry {
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    public static final int MAX_NAMELEN = 31;
    public static final int MILLIS_PER_SECOND = 1000;
    public boolean checkSumOK;
    public int devMajor;
    public int devMinor;
    public final File file;
    public int groupId;
    public String groupName;
    public boolean isExtended;
    public byte linkFlag;
    public String linkName;
    public String magic;
    public long modTime;
    public int mode;
    public String name;
    public long realSize;
    public long size;
    public int userId;
    public String userName;
    public String version;

    public TarArchiveEntry() {
        this.name = "";
        this.userId = 0;
        this.groupId = 0;
        this.size = 0L;
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = TarConstants.VERSION_POSIX;
        this.groupName = "";
        this.devMajor = 0;
        this.devMinor = 0;
        String property = System.getProperty("user.name", "");
        this.userName = property.length() > 31 ? property.substring(0, 31) : property;
        this.file = null;
    }

    public static String normalizeFileName(String str, boolean z) {
        int iIndexOf;
        String lowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (lowerCase != null) {
            if (lowerCase.startsWith("windows")) {
                if (str.length() > 2) {
                    char cCharAt = str.charAt(0);
                    if (str.charAt(1) == ':' && ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z'))) {
                        str = str.substring(2);
                    }
                }
            } else if (lowerCase.indexOf("netware") > -1 && (iIndexOf = str.indexOf(58)) != -1) {
                str = str.substring(iIndexOf + 1);
            }
        }
        String strReplace = str.replace(File.separatorChar, '/');
        while (!z && strReplace.startsWith(SchemaId.METRIC_SCHEMA_ID_DELIMITER)) {
            strReplace = strReplace.substring(1);
        }
        return strReplace;
    }

    public boolean equals(TarArchiveEntry tarArchiveEntry) {
        return getName().equals(tarArchiveEntry.getName());
    }

    public final int evaluateType(byte[] bArr) {
        if (ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_GNU, bArr, 257, 6)) {
            return 2;
        }
        return ArchiveUtils.matchAsciiBuffer("ustar\u0000", bArr, 257, 6) ? 3 : 0;
    }

    public int getDevMajor() {
        return this.devMajor;
    }

    public int getDevMinor() {
        return this.devMinor;
    }

    public TarArchiveEntry[] getDirectoryEntries() {
        File file = this.file;
        if (file == null || !file.isDirectory()) {
            return new TarArchiveEntry[0];
        }
        String[] list = this.file.list();
        TarArchiveEntry[] tarArchiveEntryArr = new TarArchiveEntry[list.length];
        for (int i = 0; i < list.length; i++) {
            tarArchiveEntryArr[i] = new TarArchiveEntry(new File(this.file, list[i]));
        }
        return tarArchiveEntryArr;
    }

    public File getFile() {
        return this.file;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public String getGroupName() {
        return this.groupName.toString();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return getModTime();
    }

    public String getLinkName() {
        return this.linkName.toString();
    }

    public Date getModTime() {
        return new Date(this.modTime * 1000);
    }

    public int getMode() {
        return this.mode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name.toString();
    }

    public long getRealSize() {
        return this.realSize;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.size;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName.toString();
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isBlockDevice() {
        return this.linkFlag == 52;
    }

    public boolean isCharacterDevice() {
        return this.linkFlag == 51;
    }

    public boolean isCheckSumOK() {
        return this.checkSumOK;
    }

    public boolean isDescendent(TarArchiveEntry tarArchiveEntry) {
        return tarArchiveEntry.getName().startsWith(getName());
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        File file = this.file;
        return file != null ? file.isDirectory() : this.linkFlag == 53 || getName().endsWith(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    public boolean isFIFO() {
        return this.linkFlag == 54;
    }

    public boolean isFile() {
        File file = this.file;
        if (file != null) {
            return file.isFile();
        }
        byte b = this.linkFlag;
        if (b == 0 || b == 48) {
            return true;
        }
        return !getName().endsWith(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
    }

    public boolean isGNULongNameEntry() {
        return this.linkFlag == 76 && this.name.equals(TarConstants.GNU_LONGLINK);
    }

    public boolean isGNUSparse() {
        return this.linkFlag == 83;
    }

    public boolean isGlobalPaxHeader() {
        return this.linkFlag == 103;
    }

    public boolean isLink() {
        return this.linkFlag == 49;
    }

    public boolean isPaxHeader() {
        byte b = this.linkFlag;
        return b == 120 || b == 88;
    }

    public boolean isSymbolicLink() {
        return this.linkFlag == 50;
    }

    public void parseTarHeader(byte[] bArr) {
        try {
            try {
                parseTarHeader(bArr, TarUtils.DEFAULT_ENCODING);
            } catch (IOException unused) {
                parseTarHeader(bArr, TarUtils.DEFAULT_ENCODING, true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDevMajor(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Major device number is out of range: ", i));
        }
        this.devMajor = i;
    }

    public void setDevMinor(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Minor device number is out of range: ", i));
        }
        this.devMinor = i;
    }

    public void setGroupId(int i) {
        this.groupId = i;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public void setIds(int i, int i2) {
        setUserId(i);
        setGroupId(i2);
    }

    public void setLinkName(String str) {
        this.linkName = str;
    }

    public void setModTime(long j) {
        this.modTime = j / 1000;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setName(String str) {
        this.name = normalizeFileName(str, false);
    }

    public void setNames(String str, String str2) {
        setUserName(str);
        setGroupName(str2);
    }

    public void setSize(long j) {
        if (j >= 0) {
            this.size = j;
        } else {
            throw new IllegalArgumentException("Size is out of range: " + j);
        }
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void writeEntryHeader(byte[] bArr) {
        try {
            try {
                writeEntryHeader(bArr, TarUtils.DEFAULT_ENCODING, false);
            } catch (IOException unused) {
                writeEntryHeader(bArr, TarUtils.FALLBACK_ENCODING, false);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final int writeEntryHeaderField(long j, byte[] bArr, int i, int i2, boolean z) {
        return (z || (j >= 0 && j < (1 << ((i2 + (-1)) * 3)))) ? TarUtils.formatLongOctalOrBinaryBytes(j, bArr, i, i2) : TarUtils.formatLongOctalBytes(0L, bArr, i, i2);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return equals((TarArchiveEntry) obj);
    }

    public void setModTime(Date date) {
        this.modTime = date.getTime() / 1000;
    }

    public void parseTarHeader(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        parseTarHeader(bArr, zipEncoding, false);
    }

    public void writeEntryHeader(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        int iWriteEntryHeaderField = writeEntryHeaderField(this.modTime, bArr, writeEntryHeaderField(this.size, bArr, writeEntryHeaderField(this.groupId, bArr, writeEntryHeaderField(this.userId, bArr, writeEntryHeaderField(this.mode, bArr, TarUtils.formatNameBytes(this.name, bArr, 0, 100, zipEncoding), 8, z), 8, z), 8, z), 12, z), 12, z);
        int i = iWriteEntryHeaderField;
        int i2 = 0;
        while (i2 < 8) {
            bArr[i] = 32;
            i2++;
            i++;
        }
        bArr[i] = this.linkFlag;
        for (int iWriteEntryHeaderField2 = writeEntryHeaderField(this.devMinor, bArr, writeEntryHeaderField(this.devMajor, bArr, TarUtils.formatNameBytes(this.groupName, bArr, TarUtils.formatNameBytes(this.userName, bArr, TarUtils.formatNameBytes(this.version, bArr, TarUtils.formatNameBytes(this.magic, bArr, TarUtils.formatNameBytes(this.linkName, bArr, i + 1, 100, zipEncoding), 6), 2), 32, zipEncoding), 32, zipEncoding), 8, z), 8, z); iWriteEntryHeaderField2 < bArr.length; iWriteEntryHeaderField2++) {
            bArr[iWriteEntryHeaderField2] = 0;
        }
        TarUtils.formatCheckSumOctalBytes(TarUtils.computeCheckSum(bArr), bArr, iWriteEntryHeaderField, 8);
    }

    public final void parseTarHeader(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        this.name = z ? TarUtils.parseName(bArr, 0, 100) : TarUtils.parseName(bArr, 0, 100, zipEncoding);
        this.mode = (int) TarUtils.parseOctalOrBinary(bArr, 100, 8);
        this.userId = (int) TarUtils.parseOctalOrBinary(bArr, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 8);
        this.groupId = (int) TarUtils.parseOctalOrBinary(bArr, 116, 8);
        this.size = TarUtils.parseOctalOrBinary(bArr, 124, 12);
        this.modTime = TarUtils.parseOctalOrBinary(bArr, 136, 12);
        this.checkSumOK = TarUtils.verifyCheckSum(bArr);
        this.linkFlag = bArr[156];
        this.linkName = z ? TarUtils.parseName(bArr, 157, 100) : TarUtils.parseName(bArr, 157, 100, zipEncoding);
        this.magic = TarUtils.parseName(bArr, 257, 6);
        this.version = TarUtils.parseName(bArr, TarConstants.VERSION_OFFSET, 2);
        this.userName = z ? TarUtils.parseName(bArr, 265, 32) : TarUtils.parseName(bArr, 265, 32, zipEncoding);
        this.groupName = z ? TarUtils.parseName(bArr, 297, 32) : TarUtils.parseName(bArr, 297, 32, zipEncoding);
        this.devMajor = (int) TarUtils.parseOctalOrBinary(bArr, 329, 8);
        this.devMinor = (int) TarUtils.parseOctalOrBinary(bArr, 337, 8);
        if (evaluateType(bArr) != 2) {
            String name = z ? TarUtils.parseName(bArr, 345, 155) : TarUtils.parseName(bArr, 345, 155, zipEncoding);
            if (isDirectory() && !this.name.endsWith(SchemaId.METRIC_SCHEMA_ID_DELIMITER)) {
                this.name = ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.name, SchemaId.METRIC_SCHEMA_ID_DELIMITER);
            }
            if (name.length() > 0) {
                StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(name, SchemaId.METRIC_SCHEMA_ID_DELIMITER);
                sbM.append(this.name);
                this.name = sbM.toString();
                return;
            }
            return;
        }
        this.isExtended = bArr[482] == 1;
        this.realSize = TarUtils.parseOctal(bArr, 483, 12);
    }

    public TarArchiveEntry(String str) {
        this(str, false);
    }

    public TarArchiveEntry(String str, boolean z) {
        this();
        String strNormalizeFileName = normalizeFileName(str, z);
        boolean zEndsWith = strNormalizeFileName.endsWith(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
        this.name = strNormalizeFileName;
        this.mode = zEndsWith ? 16877 : 33188;
        this.linkFlag = zEndsWith ? TarConstants.LF_DIR : (byte) 48;
        this.modTime = new Date().getTime() / 1000;
        this.userName = "";
    }

    public TarArchiveEntry(String str, byte b) {
        this(str, b, false);
    }

    public TarArchiveEntry(String str, byte b, boolean z) {
        this(str, z);
        this.linkFlag = b;
        if (b == 76) {
            this.magic = TarConstants.MAGIC_GNU;
            this.version = TarConstants.VERSION_GNU_SPACE;
        }
    }

    public TarArchiveEntry(File file) {
        this(file, normalizeFileName(file.getPath(), false));
    }

    public TarArchiveEntry(File file, String str) {
        this.name = "";
        this.userId = 0;
        this.groupId = 0;
        this.size = 0L;
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = TarConstants.VERSION_POSIX;
        this.groupName = "";
        this.devMajor = 0;
        this.devMinor = 0;
        this.file = file;
        if (file.isDirectory()) {
            this.mode = 16877;
            this.linkFlag = TarConstants.LF_DIR;
            int length = str.length();
            if (length != 0 && str.charAt(length - 1) == '/') {
                this.name = str;
            } else {
                this.name = str.concat(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
            }
        } else {
            this.mode = 33188;
            this.linkFlag = (byte) 48;
            this.size = file.length();
            this.name = str;
        }
        this.modTime = file.lastModified() / 1000;
        this.userName = "";
    }

    public TarArchiveEntry(byte[] bArr) {
        this();
        parseTarHeader(bArr);
    }

    public TarArchiveEntry(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        this();
        parseTarHeader(bArr, zipEncoding);
    }
}
