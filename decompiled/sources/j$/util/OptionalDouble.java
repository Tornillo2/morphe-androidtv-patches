package j$.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class OptionalDouble {
    public static final OptionalDouble c = new OptionalDouble();
    public final boolean a;
    public final double b;

    public OptionalDouble() {
        this.a = false;
        this.b = Double.NaN;
    }

    public static OptionalDouble empty() {
        return c;
    }

    public OptionalDouble(double d) {
        this.a = true;
        this.b = d;
    }

    public static OptionalDouble of(double d) {
        return new OptionalDouble(d);
    }

    public double getAsDouble() {
        if (!this.a) {
            throw new NoSuchElementException("No value present");
        }
        return this.b;
    }

    public boolean isPresent() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalDouble)) {
            return false;
        }
        OptionalDouble optionalDouble = (OptionalDouble) obj;
        boolean z = optionalDouble.a;
        boolean z2 = this.a;
        return (z2 && z) ? Double.compare(this.b, optionalDouble.b) == 0 : z2 == z;
    }

    public final int hashCode() {
        if (!this.a) {
            return 0;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(this.b);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public final String toString() {
        if (this.a) {
            return "OptionalDouble[" + this.b + "]";
        }
        return "OptionalDouble.empty";
    }
}
