package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MutableLong extends Number implements Comparable<MutableLong>, Mutable<Number> {
    public static final long serialVersionUID = 62986528375L;
    public long value;

    public MutableLong() {
    }

    public void add(long j) {
        this.value += j;
    }

    public long addAndGet(long j) {
        long j2 = this.value + j;
        this.value = j2;
        return j2;
    }

    public void decrement() {
        this.value--;
    }

    public long decrementAndGet() {
        long j = this.value - 1;
        this.value = j;
        return j;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableLong) && this.value == ((MutableLong) obj).longValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public long getAndAdd(long j) {
        long j2 = this.value;
        this.value = j + j2;
        return j2;
    }

    public long getAndDecrement() {
        long j = this.value;
        this.value = j - 1;
        return j;
    }

    public long getAndIncrement() {
        long j = this.value;
        this.value = 1 + j;
        return j;
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >>> 32));
    }

    public void increment() {
        this.value++;
    }

    public long incrementAndGet() {
        long j = this.value + 1;
        this.value = j;
        return j;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(long j) {
        this.value -= j;
    }

    public Long toLong() {
        return Long.valueOf(longValue());
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public MutableLong(long j) {
        this.value = j;
    }

    public void add(Number number) {
        this.value = number.longValue() + this.value;
    }

    public long addAndGet(Number number) {
        long jLongValue = number.longValue() + this.value;
        this.value = jLongValue;
        return jLongValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableLong mutableLong) {
        return NumberUtils.compare(this.value, mutableLong.value);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* JADX INFO: renamed from: getValue */
    public Number getValue2() {
        return Long.valueOf(this.value);
    }

    public void setValue(long j) {
        this.value = j;
    }

    public void subtract(Number number) {
        this.value -= number.longValue();
    }

    public long getAndAdd(Number number) {
        long j = this.value;
        this.value = number.longValue() + j;
        return j;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.longValue();
    }

    public MutableLong(Number number) {
        this.value = number.longValue();
    }

    public MutableLong(String str) {
        this.value = Long.parseLong(str);
    }
}
