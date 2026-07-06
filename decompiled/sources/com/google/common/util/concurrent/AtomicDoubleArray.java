package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.primitives.ImmutableLongArray;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class AtomicDoubleArray implements Serializable {
    public static final long serialVersionUID = 0;
    public transient AtomicLongArray longs;

    public AtomicDoubleArray(int length) {
        this.longs = new AtomicLongArray(length);
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i, double delta) {
        while (true) {
            long j = this.longs.get(i);
            double dLongBitsToDouble = Double.longBitsToDouble(j) + delta;
            int i2 = i;
            if (this.longs.compareAndSet(i2, j, Double.doubleToRawLongBits(dLongBitsToDouble))) {
                return dLongBitsToDouble;
            }
            i = i2;
        }
    }

    public final boolean compareAndSet(int i, double expect, double update) {
        return this.longs.compareAndSet(i, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final double get(int i) {
        return Double.longBitsToDouble(this.longs.get(i));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i, double delta) {
        while (true) {
            long j = this.longs.get(i);
            double dLongBitsToDouble = Double.longBitsToDouble(j);
            int i2 = i;
            if (this.longs.compareAndSet(i2, j, Double.doubleToRawLongBits(dLongBitsToDouble + delta))) {
                return dLongBitsToDouble;
            }
            i = i2;
        }
    }

    public final double getAndSet(int i, double newValue) {
        return Double.longBitsToDouble(this.longs.getAndSet(i, Double.doubleToRawLongBits(newValue)));
    }

    public final void lazySet(int i, double newValue) {
        this.longs.lazySet(i, Double.doubleToRawLongBits(newValue));
    }

    public final int length() {
        return this.longs.length();
    }

    public final void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        int i = s.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int i2 = 0; i2 < i; i2++) {
            builder.add(Double.doubleToRawLongBits(s.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }

    public final void set(int i, double newValue) {
        this.longs.set(i, Double.doubleToRawLongBits(newValue));
    }

    public String toString() {
        int length = this.longs.length();
        int i = length - 1;
        if (i == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(length * 19);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int i2 = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(i2)));
            if (i2 == i) {
                sb.append(AbstractJsonLexerKt.END_LIST);
                return sb.toString();
            }
            sb.append(", ");
            i2++;
        }
    }

    public final boolean weakCompareAndSet(int i, double expect, double update) {
        return this.longs.weakCompareAndSet(i, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        int length = this.longs.length();
        s.writeInt(length);
        for (int i = 0; i < length; i++) {
            s.writeDouble(get(i));
        }
    }

    public AtomicDoubleArray(double[] array) {
        int length = array.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = Double.doubleToRawLongBits(array[i]);
        }
        this.longs = new AtomicLongArray(jArr);
    }
}
