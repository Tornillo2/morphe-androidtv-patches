package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MutableInt extends Number implements Comparable<MutableInt>, Mutable<Number> {
    public static final long serialVersionUID = 512176391864L;
    public int value;

    public MutableInt() {
    }

    public void add(int i) {
        this.value += i;
    }

    public int addAndGet(int i) {
        int i2 = this.value + i;
        this.value = i2;
        return i2;
    }

    public void decrement() {
        this.value--;
    }

    public int decrementAndGet() {
        int i = this.value - 1;
        this.value = i;
        return i;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableInt) && this.value == ((MutableInt) obj).intValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int getAndAdd(int i) {
        int i2 = this.value;
        this.value = i + i2;
        return i2;
    }

    public int getAndDecrement() {
        int i = this.value;
        this.value = i - 1;
        return i;
    }

    public int getAndIncrement() {
        int i = this.value;
        this.value = i + 1;
        return i;
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value++;
    }

    public int incrementAndGet() {
        int i = this.value + 1;
        this.value = i;
        return i;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(int i) {
        this.value -= i;
    }

    public Integer toInteger() {
        return Integer.valueOf(intValue());
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public MutableInt(int i) {
        this.value = i;
    }

    public void add(Number number) {
        this.value = number.intValue() + this.value;
    }

    public int addAndGet(Number number) {
        int iIntValue = number.intValue() + this.value;
        this.value = iIntValue;
        return iIntValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableInt mutableInt) {
        return NumberUtils.compare(this.value, mutableInt.value);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* JADX INFO: renamed from: getValue */
    public Number getValue2() {
        return Integer.valueOf(this.value);
    }

    public void setValue(int i) {
        this.value = i;
    }

    public void subtract(Number number) {
        this.value -= number.intValue();
    }

    public int getAndAdd(Number number) {
        int i = this.value;
        this.value = number.intValue() + i;
        return i;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.intValue();
    }

    public MutableInt(Number number) {
        this.value = number.intValue();
    }

    public MutableInt(String str) {
        this.value = Integer.parseInt(str);
    }
}
