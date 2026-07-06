package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ZipShort implements Cloneable, Serializable {
    public static final int BYTE_1_MASK = 65280;
    public static final int BYTE_1_SHIFT = 8;
    public static final long serialVersionUID = 1;
    public final int value;

    public ZipShort(int i) {
        this.value = i;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipShort) && this.value == ((ZipShort) obj).value;
    }

    public byte[] getBytes() {
        int i = this.value;
        return new byte[]{(byte) (i & 255), (byte) ((i & 65280) >> 8)};
    }

    public int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public String toString() {
        return "ZipShort value: " + this.value;
    }

    public static int getValue(byte[] bArr, int i) {
        return ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public ZipShort(byte[] bArr) {
        this(bArr, 0);
    }

    public static byte[] getBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i & 65280) >> 8)};
    }

    public ZipShort(byte[] bArr, int i) {
        this.value = getValue(bArr, i);
    }

    public static int getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }
}
