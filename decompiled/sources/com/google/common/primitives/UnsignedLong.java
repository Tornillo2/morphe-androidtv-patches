package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class UnsignedLong extends Number implements Comparable<UnsignedLong> {
    public static final long UNSIGNED_MASK = Long.MAX_VALUE;
    public final long value;
    public static final UnsignedLong ZERO = new UnsignedLong(0);
    public static final UnsignedLong ONE = new UnsignedLong(1);
    public static final UnsignedLong MAX_VALUE = new UnsignedLong(-1);

    public UnsignedLong(long value) {
        this.value = value;
    }

    public static UnsignedLong fromLongBits(long bits) {
        return new UnsignedLong(bits);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(long value) {
        Preconditions.checkArgument(value >= 0, "value (%s) is outside the range for an unsigned long value", value);
        return new UnsignedLong(value);
    }

    public BigInteger bigIntegerValue() {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(this.value & Long.MAX_VALUE);
        return this.value < 0 ? bigIntegerValueOf.setBit(63) : bigIntegerValueOf;
    }

    public UnsignedLong dividedBy(UnsignedLong val) {
        long j = this.value;
        val.getClass();
        return new UnsignedLong(UnsignedLongs.divide(j, val.value));
    }

    @Override // java.lang.Number
    public double doubleValue() {
        long j = this.value;
        if (j >= 0) {
            return j;
        }
        return ((j & 1) | (j >>> 1)) * 2.0d;
    }

    public boolean equals(Object obj) {
        return (obj instanceof UnsignedLong) && this.value == ((UnsignedLong) obj).value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        long j = this.value;
        if (j >= 0) {
            return j;
        }
        return ((j & 1) | (j >>> 1)) * 2.0f;
    }

    public int hashCode() {
        return Longs.hashCode(this.value);
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public UnsignedLong minus(UnsignedLong val) {
        long j = this.value;
        val.getClass();
        return new UnsignedLong(j - val.value);
    }

    public UnsignedLong mod(UnsignedLong val) {
        long j = this.value;
        val.getClass();
        return new UnsignedLong(UnsignedLongs.remainder(j, val.value));
    }

    public UnsignedLong plus(UnsignedLong val) {
        long j = this.value;
        val.getClass();
        return new UnsignedLong(j + val.value);
    }

    public UnsignedLong times(UnsignedLong val) {
        long j = this.value;
        val.getClass();
        return new UnsignedLong(j * val.value);
    }

    public String toString() {
        return UnsignedLongs.toString(this.value, 10);
    }

    @Override // java.lang.Comparable
    public int compareTo(UnsignedLong o) {
        o.getClass();
        return UnsignedLongs.compare(this.value, o.value);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(String string) {
        return valueOf(string, 10);
    }

    public String toString(int radix) {
        return UnsignedLongs.toString(this.value, radix);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(String string, int radix) {
        return new UnsignedLong(UnsignedLongs.parseUnsignedLong(string, radix));
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(BigInteger value) {
        value.getClass();
        Preconditions.checkArgument(value.signum() >= 0 && value.bitLength() <= 64, "value (%s) is outside the range for an unsigned long value", value);
        return new UnsignedLong(value.longValue());
    }
}
