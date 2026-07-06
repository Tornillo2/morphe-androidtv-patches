package org.apache.commons.compress.archivers.cpio;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.android.billingclient.api.ProxyBillingActivity;
import java.io.File;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CpioArchiveEntry implements CpioConstants, ArchiveEntry {
    public final int alignmentBoundary;
    public long chksum;
    public final short fileFormat;
    public long filesize;
    public long gid;
    public final int headerSize;
    public long inode;
    public long maj;
    public long min;
    public long mode;
    public long mtime;
    public String name;
    public long nlink;
    public long rmaj;
    public long rmin;
    public long uid;

    public CpioArchiveEntry(short s) {
        this.chksum = 0L;
        this.filesize = 0L;
        this.gid = 0L;
        this.inode = 0L;
        this.maj = 0L;
        this.min = 0L;
        this.mode = 0L;
        this.mtime = 0L;
        this.nlink = 0L;
        this.rmaj = 0L;
        this.rmin = 0L;
        this.uid = 0L;
        if (s == 1 || s == 2) {
            this.headerSize = ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW;
            this.alignmentBoundary = 4;
        } else if (s == 4) {
            this.headerSize = 76;
            this.alignmentBoundary = 0;
        } else {
            if (s != 8) {
                throw new IllegalArgumentException("Unknown header type");
            }
            this.headerSize = 26;
            this.alignmentBoundary = 2;
        }
        this.fileFormat = s;
    }

    public final void checkNewFormat() {
        if ((this.fileFormat & 3) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public final void checkOldFormat() {
        if ((this.fileFormat & 12) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CpioArchiveEntry cpioArchiveEntry = (CpioArchiveEntry) obj;
        String str = this.name;
        if (str == null) {
            if (cpioArchiveEntry.name != null) {
                return false;
            }
        } else if (!str.equals(cpioArchiveEntry.name)) {
            return false;
        }
        return true;
    }

    public int getAlignmentBoundary() {
        return this.alignmentBoundary;
    }

    public long getChksum() {
        checkNewFormat();
        return this.chksum;
    }

    public int getDataPadCount() {
        int i;
        int i2 = this.alignmentBoundary;
        if (i2 != 0 && (i = (int) (this.filesize % ((long) i2))) > 0) {
            return i2 - i;
        }
        return 0;
    }

    public long getDevice() {
        checkOldFormat();
        return this.min;
    }

    public long getDeviceMaj() {
        checkNewFormat();
        return this.maj;
    }

    public long getDeviceMin() {
        checkNewFormat();
        return this.min;
    }

    public short getFormat() {
        return this.fileFormat;
    }

    public long getGID() {
        return this.gid;
    }

    public int getHeaderPadCount() {
        if (this.alignmentBoundary == 0) {
            return 0;
        }
        int length = this.name.length() + this.headerSize + 1;
        int i = this.alignmentBoundary;
        int i2 = length % i;
        if (i2 > 0) {
            return i - i2;
        }
        return 0;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public long getInode() {
        return this.inode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return new Date(getTime() * 1000);
    }

    public long getMode() {
        if (this.mode != 0 || CpioConstants.CPIO_TRAILER.equals(this.name)) {
            return this.mode;
        }
        return 32768L;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name;
    }

    public long getNumberOfLinks() {
        long j = this.nlink;
        if (j == 0) {
            return isDirectory() ? 2 : 1;
        }
        return j;
    }

    public long getRemoteDevice() {
        checkOldFormat();
        return this.rmin;
    }

    public long getRemoteDeviceMaj() {
        checkNewFormat();
        return this.rmaj;
    }

    public long getRemoteDeviceMin() {
        checkNewFormat();
        return this.rmin;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.filesize;
    }

    public long getTime() {
        return this.mtime;
    }

    public long getUID() {
        return this.uid;
    }

    public int hashCode() {
        String str = this.name;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public boolean isBlockDevice() {
        return (this.mode & 61440) == 24576;
    }

    public boolean isCharacterDevice() {
        return (this.mode & 61440) == 8192;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        return (this.mode & 61440) == 16384;
    }

    public boolean isNetwork() {
        return (this.mode & 61440) == 36864;
    }

    public boolean isPipe() {
        return (this.mode & 61440) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public boolean isRegularFile() {
        return (this.mode & 61440) == 32768;
    }

    public boolean isSocket() {
        return (this.mode & 61440) == 49152;
    }

    public boolean isSymbolicLink() {
        return (this.mode & 61440) == 40960;
    }

    public void setChksum(long j) {
        checkNewFormat();
        this.chksum = j;
    }

    public void setDevice(long j) {
        checkOldFormat();
        this.min = j;
    }

    public void setDeviceMaj(long j) {
        checkNewFormat();
        this.maj = j;
    }

    public void setDeviceMin(long j) {
        checkNewFormat();
        this.min = j;
    }

    public void setGID(long j) {
        this.gid = j;
    }

    public void setInode(long j) {
        this.inode = j;
    }

    public void setMode(long j) {
        long j2 = 61440 & j;
        switch ((int) j2) {
            case 4096:
            case 8192:
            case 16384:
            case CpioConstants.C_ISBLK /* 24576 */:
            case 32768:
            case CpioConstants.C_ISNWK /* 36864 */:
            case 40960:
            case CpioConstants.C_ISSOCK /* 49152 */:
                this.mode = j;
                return;
            default:
                throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(j) + " Masked: " + Long.toHexString(j2));
        }
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNumberOfLinks(long j) {
        this.nlink = j;
    }

    public void setRemoteDevice(long j) {
        checkOldFormat();
        this.rmin = j;
    }

    public void setRemoteDeviceMaj(long j) {
        checkNewFormat();
        this.rmaj = j;
    }

    public void setRemoteDeviceMin(long j) {
        checkNewFormat();
        this.rmin = j;
    }

    public void setSize(long j) {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("invalid entry size <", j, ">"));
        }
        this.filesize = j;
    }

    public void setTime(long j) {
        this.mtime = j;
    }

    public void setUID(long j) {
        this.uid = j;
    }

    public CpioArchiveEntry(String str) {
        this((short) 1, str);
    }

    public CpioArchiveEntry(String str, long j) {
        this((short) 1, str);
        setSize(j);
    }

    public CpioArchiveEntry(short s, String str) {
        this(s);
        this.name = str;
    }

    public CpioArchiveEntry(short s, String str, long j) {
        this(s, str);
        setSize(j);
    }

    public CpioArchiveEntry(File file, String str) {
        this((short) 1, file, str);
    }

    public CpioArchiveEntry(short s, File file, String str) {
        long j;
        this(s, str, file.isFile() ? file.length() : 0L);
        if (file.isDirectory()) {
            j = 16384;
        } else {
            if (!file.isFile()) {
                throw new IllegalArgumentException("Cannot determine type of file " + file.getName());
            }
            j = 32768;
        }
        setMode(j);
        setTime(file.lastModified() / 1000);
    }
}
