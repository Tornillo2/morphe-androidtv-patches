package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MutableShort extends Number implements Comparable<MutableShort>, Mutable<Number> {
    public static final long serialVersionUID = -2135791679;
    public short value;

    public MutableShort() {
    }

    public void add(short s) {
        this.value = (short) (this.value + s);
    }

    public short addAndGet(short s) {
        short s2 = (short) (this.value + s);
        this.value = s2;
        return s2;
    }

    public void decrement() {
        this.value = (short) (this.value - 1);
    }

    public short decrementAndGet() {
        short s = (short) (this.value - 1);
        this.value = s;
        return s;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableShort) && this.value == ((MutableShort) obj).shortValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public short getAndAdd(short s) {
        short s2 = this.value;
        this.value = (short) (s + s2);
        return s2;
    }

    public short getAndDecrement() {
        short s = this.value;
        this.value = (short) (s - 1);
        return s;
    }

    public short getAndIncrement() {
        short s = this.value;
        this.value = (short) (s + 1);
        return s;
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value = (short) (this.value + 1);
    }

    public short incrementAndGet() {
        short s = (short) (this.value + 1);
        this.value = s;
        return s;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public short shortValue() {
        return this.value;
    }

    public void subtract(short s) {
        this.value = (short) (this.value - s);
    }

    public Short toShort() {
        return Short.valueOf(shortValue());
    }

    public String toString() {
        return String.valueOf((int) this.value);
    }

    public MutableShort(short s) {
        this.value = s;
    }

    public void add(Number number) {
        this.value = (short) (number.shortValue() + this.value);
    }

    public short addAndGet(Number number) {
        short sShortValue = (short) (number.shortValue() + this.value);
        this.value = sShortValue;
        return sShortValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableShort mutableShort) {
        return NumberUtils.compare(this.value, mutableShort.value);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* JADX INFO: renamed from: getValue, reason: avoid collision after fix types in other method */
    public Number getValue2() {
        return Short.valueOf(this.value);
    }

    public void setValue(short s) {
        this.value = s;
    }

    public void subtract(Number number) {
        this.value = (short) (this.value - number.shortValue());
    }

    public short getAndAdd(Number number) {
        short s = this.value;
        this.value = (short) (number.shortValue() + s);
        return s;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.shortValue();
    }

    public MutableShort(Number number) {
        this.value = number.shortValue();
    }

    public MutableShort(String str) {
        this.value = Short.parseShort(str);
    }
}
