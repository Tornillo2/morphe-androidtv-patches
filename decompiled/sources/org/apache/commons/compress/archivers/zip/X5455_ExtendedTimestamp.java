package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.util.Date;
import java.util.zip.ZipException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class X5455_ExtendedTimestamp implements ZipExtraField, Cloneable, Serializable {
    public static final byte ACCESS_TIME_BIT = 2;
    public static final byte CREATE_TIME_BIT = 4;
    public static final ZipShort HEADER_ID = new ZipShort(21589);
    public static final byte MODIFY_TIME_BIT = 1;
    public static final long serialVersionUID = 1;
    public ZipLong accessTime;
    public boolean bit0_modifyTimePresent;
    public boolean bit1_accessTimePresent;
    public boolean bit2_createTimePresent;
    public ZipLong createTime;
    public byte flags;
    public ZipLong modifyTime;

    public static ZipLong dateToZipLong(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime() / 1000;
        if (time < 4294967296L) {
            return new ZipLong(time);
        }
        throw new IllegalArgumentException("Cannot set an X5455 timestamp larger than 2^32: " + time);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        ZipLong zipLong;
        ZipLong zipLong2;
        ZipLong zipLong3;
        ZipLong zipLong4;
        if (obj instanceof X5455_ExtendedTimestamp) {
            X5455_ExtendedTimestamp x5455_ExtendedTimestamp = (X5455_ExtendedTimestamp) obj;
            if ((this.flags & 7) == (x5455_ExtendedTimestamp.flags & 7) && (((zipLong = this.modifyTime) == (zipLong2 = x5455_ExtendedTimestamp.modifyTime) || (zipLong != null && zipLong.equals(zipLong2))) && ((zipLong3 = this.accessTime) == (zipLong4 = x5455_ExtendedTimestamp.accessTime) || (zipLong3 != null && zipLong3.equals(zipLong4))))) {
                ZipLong zipLong5 = this.createTime;
                ZipLong zipLong6 = x5455_ExtendedTimestamp.createTime;
                if (zipLong5 == zipLong6) {
                    return true;
                }
                if (zipLong5 != null && zipLong5.equals(zipLong6)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Date getAccessJavaTime() {
        if (this.accessTime != null) {
            return new Date(this.accessTime.value * 1000);
        }
        return null;
    }

    public ZipLong getAccessTime() {
        return this.accessTime;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        int i = getCentralDirectoryLength().value;
        byte[] bArr = new byte[i];
        System.arraycopy(getLocalFileDataData(), 0, bArr, 0, i);
        return bArr;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort((this.bit0_modifyTimePresent ? 4 : 0) + 1);
    }

    public Date getCreateJavaTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.value * 1000);
        }
        return null;
    }

    public ZipLong getCreateTime() {
        return this.createTime;
    }

    public byte getFlags() {
        return this.flags;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        ZipLong zipLong;
        ZipLong zipLong2;
        byte[] bArr = new byte[getLocalFileDataLength().value];
        bArr[0] = 0;
        int i = 1;
        if (this.bit0_modifyTimePresent) {
            bArr[0] = (byte) 1;
            System.arraycopy(ZipLong.getBytes(this.modifyTime.value), 0, bArr, 1, 4);
            i = 5;
        }
        if (this.bit1_accessTimePresent && (zipLong2 = this.accessTime) != null) {
            bArr[0] = (byte) (bArr[0] | 2);
            System.arraycopy(ZipLong.getBytes(zipLong2.value), 0, bArr, i, 4);
            i += 4;
        }
        if (this.bit2_createTimePresent && (zipLong = this.createTime) != null) {
            bArr[0] = (byte) (bArr[0] | 4);
            System.arraycopy(ZipLong.getBytes(zipLong.value), 0, bArr, i, 4);
        }
        return bArr;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        int i = 0;
        int i2 = (this.bit0_modifyTimePresent ? 4 : 0) + 1 + ((!this.bit1_accessTimePresent || this.accessTime == null) ? 0 : 4);
        if (this.bit2_createTimePresent && this.createTime != null) {
            i = 4;
        }
        return new ZipShort(i2 + i);
    }

    public Date getModifyJavaTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.value * 1000);
        }
        return null;
    }

    public ZipLong getModifyTime() {
        return this.modifyTime;
    }

    public int hashCode() {
        int iRotateLeft = (this.flags & 7) * (-123);
        ZipLong zipLong = this.modifyTime;
        if (zipLong != null) {
            iRotateLeft ^= (int) zipLong.value;
        }
        ZipLong zipLong2 = this.accessTime;
        if (zipLong2 != null) {
            iRotateLeft ^= Integer.rotateLeft((int) zipLong2.value, 11);
        }
        ZipLong zipLong3 = this.createTime;
        return zipLong3 != null ? iRotateLeft ^ Integer.rotateLeft((int) zipLong3.value, 22) : iRotateLeft;
    }

    public boolean isBit0_modifyTimePresent() {
        return this.bit0_modifyTimePresent;
    }

    public boolean isBit1_accessTimePresent() {
        return this.bit1_accessTimePresent;
    }

    public boolean isBit2_createTimePresent() {
        return this.bit2_createTimePresent;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        parseFromLocalFileData(bArr, i, i2);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        int i3;
        reset();
        int i4 = i2 + i;
        int i5 = i + 1;
        setFlags(bArr[i]);
        if (this.bit0_modifyTimePresent) {
            this.modifyTime = new ZipLong(bArr, i5);
            i5 = i + 5;
        }
        if (this.bit1_accessTimePresent && (i3 = i5 + 4) <= i4) {
            this.accessTime = new ZipLong(bArr, i5);
            i5 = i3;
        }
        if (!this.bit2_createTimePresent || i5 + 4 > i4) {
            return;
        }
        this.createTime = new ZipLong(bArr, i5);
    }

    public final void reset() {
        setFlags((byte) 0);
        this.modifyTime = null;
        this.accessTime = null;
        this.createTime = null;
    }

    public void setAccessJavaTime(Date date) {
        setAccessTime(dateToZipLong(date));
    }

    public void setAccessTime(ZipLong zipLong) {
        this.accessTime = zipLong;
    }

    public void setCreateJavaTime(Date date) {
        setCreateTime(dateToZipLong(date));
    }

    public void setCreateTime(ZipLong zipLong) {
        this.createTime = zipLong;
    }

    public void setFlags(byte b) {
        this.flags = b;
        this.bit0_modifyTimePresent = (b & 1) == 1;
        this.bit1_accessTimePresent = (b & 2) == 2;
        this.bit2_createTimePresent = (b & 4) == 4;
    }

    public void setModifyJavaTime(Date date) {
        setModifyTime(dateToZipLong(date));
    }

    public void setModifyTime(ZipLong zipLong) {
        this.modifyTime = zipLong;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("0x5455 Zip Extra Field: Flags=");
        sb.append(Integer.toBinaryString(ZipUtil.unsignedIntToSignedByte(this.flags)));
        sb.append(StringUtils.SPACE);
        if (this.bit0_modifyTimePresent && this.modifyTime != null) {
            Date modifyJavaTime = getModifyJavaTime();
            sb.append(" Modify:[");
            sb.append(modifyJavaTime);
            sb.append("] ");
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            Date accessJavaTime = getAccessJavaTime();
            sb.append(" Access:[");
            sb.append(accessJavaTime);
            sb.append("] ");
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            Date createJavaTime = getCreateJavaTime();
            sb.append(" Create:[");
            sb.append(createJavaTime);
            sb.append("] ");
        }
        return sb.toString();
    }
}
