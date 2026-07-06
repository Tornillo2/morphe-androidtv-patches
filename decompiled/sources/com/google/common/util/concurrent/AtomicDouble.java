package com.google.common.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AtomicDouble extends Number {
    public static final long serialVersionUID = 0;
    public transient AtomicLong value;

    public AtomicDouble(double initialValue) {
        this.value = new AtomicLong(Double.doubleToRawLongBits(initialValue));
    }

    @CanIgnoreReturnValue
    public final double addAndGet(double delta) {
        long j;
        double dLongBitsToDouble;
        do {
            j = this.value.get();
            dLongBitsToDouble = Double.longBitsToDouble(j) + delta;
        } while (!this.value.compareAndSet(j, Double.doubleToRawLongBits(dLongBitsToDouble)));
        return dLongBitsToDouble;
    }

    public final boolean compareAndSet(double expect, double update) {
        return this.value.compareAndSet(Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return get();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) get();
    }

    public final double get() {
        return Double.longBitsToDouble(this.value.get());
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(double delta) {
        long j;
        double dLongBitsToDouble;
        do {
            j = this.value.get();
            dLongBitsToDouble = Double.longBitsToDouble(j);
        } while (!this.value.compareAndSet(j, Double.doubleToRawLongBits(dLongBitsToDouble + delta)));
        return dLongBitsToDouble;
    }

    public final double getAndSet(double newValue) {
        return Double.longBitsToDouble(this.value.getAndSet(Double.doubleToRawLongBits(newValue)));
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) get();
    }

    public final void lazySet(double newValue) {
        this.value.lazySet(Double.doubleToRawLongBits(newValue));
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) get();
    }

    public final void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        this.value = new AtomicLong();
        set(s.readDouble());
    }

    public final void set(double newValue) {
        this.value.set(Double.doubleToRawLongBits(newValue));
    }

    public String toString() {
        return Double.toString(get());
    }

    public final boolean weakCompareAndSet(double expect, double update) {
        return this.value.weakCompareAndSet(Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeDouble(get());
    }

    public AtomicDouble() {
        this(0.0d);
    }
}
