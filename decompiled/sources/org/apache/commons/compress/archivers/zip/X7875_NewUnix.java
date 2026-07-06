package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.zip.ZipException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class X7875_NewUnix implements ZipExtraField, Cloneable, Serializable {
    public static final ZipShort HEADER_ID = new ZipShort(30837);
    public static final BigInteger ONE_THOUSAND = BigInteger.valueOf(1000);
    public static final long serialVersionUID = 1;
    public BigInteger gid;
    public BigInteger uid;
    public int version = 1;

    public X7875_NewUnix() {
        reset();
    }

    private void reset() {
        BigInteger bigInteger = ONE_THOUSAND;
        this.uid = bigInteger;
        this.gid = bigInteger;
    }

    public static byte[] trimLeadingZeroesForceMinLength(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length && bArr[i2] == 0; i2++) {
            i++;
        }
        int iMax = Math.max(1, bArr.length - i);
        byte[] bArr2 = new byte[iMax];
        int length2 = iMax - (bArr.length - i);
        System.arraycopy(bArr, i, bArr2, length2, iMax - length2);
        return bArr2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (obj instanceof X7875_NewUnix) {
            X7875_NewUnix x7875_NewUnix = (X7875_NewUnix) obj;
            if (this.version == x7875_NewUnix.version && this.uid.equals(x7875_NewUnix.uid) && this.gid.equals(x7875_NewUnix.gid)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public long getGID() {
        return ZipUtil.bigToLong(this.gid);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] byteArray = this.uid.toByteArray();
        byte[] byteArray2 = this.gid.toByteArray();
        byte[] bArrTrimLeadingZeroesForceMinLength = trimLeadingZeroesForceMinLength(byteArray);
        byte[] bArrTrimLeadingZeroesForceMinLength2 = trimLeadingZeroesForceMinLength(byteArray2);
        byte[] bArr = new byte[bArrTrimLeadingZeroesForceMinLength.length + 3 + bArrTrimLeadingZeroesForceMinLength2.length];
        ZipUtil.reverse(bArrTrimLeadingZeroesForceMinLength);
        ZipUtil.reverse(bArrTrimLeadingZeroesForceMinLength2);
        bArr[0] = ZipUtil.unsignedIntToSignedByte(this.version);
        bArr[1] = ZipUtil.unsignedIntToSignedByte(bArrTrimLeadingZeroesForceMinLength.length);
        System.arraycopy(bArrTrimLeadingZeroesForceMinLength, 0, bArr, 2, bArrTrimLeadingZeroesForceMinLength.length);
        int length = bArrTrimLeadingZeroesForceMinLength.length;
        bArr[2 + length] = ZipUtil.unsignedIntToSignedByte(bArrTrimLeadingZeroesForceMinLength2.length);
        System.arraycopy(bArrTrimLeadingZeroesForceMinLength2, 0, bArr, length + 3, bArrTrimLeadingZeroesForceMinLength2.length);
        return bArr;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(trimLeadingZeroesForceMinLength(this.uid.toByteArray()).length + 3 + trimLeadingZeroesForceMinLength(this.gid.toByteArray()).length);
    }

    public long getUID() {
        return ZipUtil.bigToLong(this.uid);
    }

    public int hashCode() {
        return (Integer.rotateLeft(this.uid.hashCode(), 16) ^ (this.version * (-1234567))) ^ this.gid.hashCode();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        parseFromLocalFileData(bArr, i, i2);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        int i3 = i + 1;
        this.version = ZipUtil.signedByteToUnsignedInt(bArr[i]);
        int i4 = i + 2;
        int iSignedByteToUnsignedInt = ZipUtil.signedByteToUnsignedInt(bArr[i3]);
        byte[] bArr2 = new byte[iSignedByteToUnsignedInt];
        System.arraycopy(bArr, i4, bArr2, 0, iSignedByteToUnsignedInt);
        int i5 = i4 + iSignedByteToUnsignedInt;
        ZipUtil.reverse(bArr2);
        this.uid = new BigInteger(1, bArr2);
        int i6 = i5 + 1;
        int iSignedByteToUnsignedInt2 = ZipUtil.signedByteToUnsignedInt(bArr[i5]);
        byte[] bArr3 = new byte[iSignedByteToUnsignedInt2];
        System.arraycopy(bArr, i6, bArr3, 0, iSignedByteToUnsignedInt2);
        ZipUtil.reverse(bArr3);
        this.gid = new BigInteger(1, bArr3);
    }

    public void setGID(long j) {
        this.gid = ZipUtil.longToBig(j);
    }

    public void setUID(long j) {
        this.uid = ZipUtil.longToBig(j);
    }

    public String toString() {
        return "0x7875 Zip Extra Field: UID=" + this.uid + " GID=" + this.gid;
    }
}
