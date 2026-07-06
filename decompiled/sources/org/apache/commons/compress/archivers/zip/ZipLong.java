package org.apache.commons.compress.archivers.zip;

import com.google.common.base.Ascii;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ZipLong implements Cloneable, Serializable {
    public static final int BYTE_1 = 1;
    public static final int BYTE_1_MASK = 65280;
    public static final int BYTE_1_SHIFT = 8;
    public static final int BYTE_2 = 2;
    public static final int BYTE_2_MASK = 16711680;
    public static final int BYTE_2_SHIFT = 16;
    public static final int BYTE_3 = 3;
    public static final long BYTE_3_MASK = 4278190080L;
    public static final int BYTE_3_SHIFT = 24;
    public static final long serialVersionUID = 1;
    public final long value;
    public static final ZipLong CFH_SIG = new ZipLong(33639248);
    public static final ZipLong LFH_SIG = new ZipLong(67324752);
    public static final ZipLong DD_SIG = new ZipLong(134695760);
    public static final ZipLong ZIP64_MAGIC = new ZipLong(4294967295L);
    public static final ZipLong SINGLE_SEGMENT_SPLIT_MARKER = new ZipLong(808471376);
    public static final ZipLong AED_SIG = new ZipLong(134630224);

    public ZipLong(long j) {
        this.value = j;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipLong) && this.value == ((ZipLong) obj).value;
    }

    public byte[] getBytes() {
        return getBytes(this.value);
    }

    public long getValue() {
        return this.value;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public String toString() {
        return "ZipLong value: " + this.value;
    }

    public static byte[] getBytes(long j) {
        return new byte[]{(byte) (255 & j), (byte) ((65280 & j) >> 8), (byte) ((16711680 & j) >> 16), (byte) ((j & 4278190080L) >> 24)};
    }

    public static long getValue(byte[] bArr, int i) {
        return (((long) (bArr[i + 3] << Ascii.CAN)) & 4278190080L) + ((long) ((bArr[i + 2] << 16) & 16711680)) + ((long) ((bArr[i + 1] << 8) & 65280)) + ((long) (bArr[i] & 255));
    }

    public ZipLong(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipLong(byte[] bArr, int i) {
        this.value = getValue(bArr, i);
    }

    public static long getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }
}
