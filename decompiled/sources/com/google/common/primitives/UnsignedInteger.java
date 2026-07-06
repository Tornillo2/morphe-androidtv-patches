package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
    public final int value;
    public static final UnsignedInteger ZERO = new UnsignedInteger(0);
    public static final UnsignedInteger ONE = new UnsignedInteger(1);
    public static final UnsignedInteger MAX_VALUE = new UnsignedInteger(-1);

    public UnsignedInteger(int value) {
        this.value = value;
    }

    public static UnsignedInteger fromIntBits(int bits) {
        return new UnsignedInteger(bits);
    }

    public static UnsignedInteger valueOf(long value) {
        Preconditions.checkArgument((4294967295L & value) == value, "value (%s) is outside the range for an unsigned integer value", value);
        return new UnsignedInteger((int) value);
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(longValue());
    }

    public UnsignedInteger dividedBy(UnsignedInteger val) {
        int i = this.value;
        val.getClass();
        return new UnsignedInteger(UnsignedInts.divide(i, val.value));
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return longValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof UnsignedInteger) && this.value == ((UnsignedInteger) obj).value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return longValue();
    }

    public int hashCode() {
        return this.value;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return ((long) this.value) & 4294967295L;
    }

    public UnsignedInteger minus(UnsignedInteger val) {
        int i = this.value;
        val.getClass();
        return new UnsignedInteger(i - val.value);
    }

    public UnsignedInteger mod(UnsignedInteger val) {
        int i = this.value;
        val.getClass();
        return new UnsignedInteger(UnsignedInts.remainder(i, val.value));
    }

    public UnsignedInteger plus(UnsignedInteger val) {
        int i = this.value;
        val.getClass();
        return new UnsignedInteger(i + val.value);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public UnsignedInteger times(UnsignedInteger val) {
        int i = this.value;
        val.getClass();
        return new UnsignedInteger(i * val.value);
    }

    public String toString() {
        return UnsignedInts.toString(this.value, 10);
    }

    @Override // java.lang.Comparable
    public int compareTo(UnsignedInteger other) {
        other.getClass();
        return UnsignedInts.compare(this.value, other.value);
    }

    public String toString(int radix) {
        return UnsignedInts.toString(this.value, radix);
    }

    public static UnsignedInteger valueOf(String string) {
        return valueOf(string, 10);
    }

    public static UnsignedInteger valueOf(String string, int radix) {
        return new UnsignedInteger(UnsignedInts.parseUnsignedInt(string, radix));
    }

    public static UnsignedInteger valueOf(BigInteger value) {
        value.getClass();
        Preconditions.checkArgument(value.signum() >= 0 && value.bitLength() <= 32, "value (%s) is outside the range for an unsigned integer value", value);
        return new UnsignedInteger(value.intValue());
    }
}
