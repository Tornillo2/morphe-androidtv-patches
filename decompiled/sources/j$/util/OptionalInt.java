package j$.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class OptionalInt {
    public static final OptionalInt c = new OptionalInt();
    public final boolean a;
    public final int b;

    public OptionalInt() {
        this.a = false;
        this.b = 0;
    }

    public static OptionalInt empty() {
        return c;
    }

    public OptionalInt(int i) {
        this.a = true;
        this.b = i;
    }

    public static OptionalInt of(int i) {
        return new OptionalInt(i);
    }

    public int getAsInt() {
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
        if (!(obj instanceof OptionalInt)) {
            return false;
        }
        OptionalInt optionalInt = (OptionalInt) obj;
        boolean z = optionalInt.a;
        boolean z2 = this.a;
        return (z2 && z) ? this.b == optionalInt.b : z2 == z;
    }

    public final int hashCode() {
        if (this.a) {
            return this.b;
        }
        return 0;
    }

    public final String toString() {
        if (this.a) {
            return "OptionalInt[" + this.b + "]";
        }
        return "OptionalInt.empty";
    }
}
