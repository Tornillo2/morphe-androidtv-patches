package org.apache.commons.lang3.mutable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MutableByte extends Number implements Comparable<MutableByte>, Mutable<Number> {
    public static final long serialVersionUID = -1585823265;
    public byte value;

    public MutableByte() {
    }

    public void add(byte b) {
        this.value = (byte) (this.value + b);
    }

    public byte addAndGet(byte b) {
        byte b2 = (byte) (this.value + b);
        this.value = b2;
        return b2;
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return this.value;
    }

    public void decrement() {
        this.value = (byte) (this.value - 1);
    }

    public byte decrementAndGet() {
        byte b = (byte) (this.value - 1);
        this.value = b;
        return b;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableByte) && this.value == ((MutableByte) obj).byteValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public byte getAndAdd(byte b) {
        byte b2 = this.value;
        this.value = (byte) (b + b2);
        return b2;
    }

    public byte getAndDecrement() {
        byte b = this.value;
        this.value = (byte) (b - 1);
        return b;
    }

    public byte getAndIncrement() {
        byte b = this.value;
        this.value = (byte) (b + 1);
        return b;
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value = (byte) (this.value + 1);
    }

    public byte incrementAndGet() {
        byte b = (byte) (this.value + 1);
        this.value = b;
        return b;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(byte b) {
        this.value = (byte) (this.value - b);
    }

    public Byte toByte() {
        return Byte.valueOf(byteValue());
    }

    public String toString() {
        return String.valueOf((int) this.value);
    }

    public MutableByte(byte b) {
        this.value = b;
    }

    public void add(Number number) {
        this.value = (byte) (number.byteValue() + this.value);
    }

    public byte addAndGet(Number number) {
        byte bByteValue = (byte) (number.byteValue() + this.value);
        this.value = bByteValue;
        return bByteValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableByte mutableByte) {
        return this.value - mutableByte.value;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* JADX INFO: renamed from: getValue, reason: merged with bridge method [inline-methods] */
    public Number getValue2() {
        return Byte.valueOf(this.value);
    }

    public void setValue(byte b) {
        this.value = b;
    }

    public void subtract(Number number) {
        this.value = (byte) (this.value - number.byteValue());
    }

    public byte getAndAdd(Number number) {
        byte b = this.value;
        this.value = (byte) (number.byteValue() + b);
        return b;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.byteValue();
    }

    public MutableByte(Number number) {
        this.value = number.byteValue();
    }

    public MutableByte(String str) {
        this.value = Byte.parseByte(str);
    }
}
