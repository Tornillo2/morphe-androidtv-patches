package j$.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class OptionalLong {
    public static final OptionalLong c = new OptionalLong();
    public final boolean a;
    public final long b;

    public OptionalLong() {
        this.a = false;
        this.b = 0L;
    }

    public static OptionalLong empty() {
        return c;
    }

    public OptionalLong(long j) {
        this.a = true;
        this.b = j;
    }

    public static OptionalLong of(long j) {
        return new OptionalLong(j);
    }

    public long getAsLong() {
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
        if (!(obj instanceof OptionalLong)) {
            return false;
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        boolean z = optionalLong.a;
        boolean z2 = this.a;
        return (z2 && z) ? this.b == optionalLong.b : z2 == z;
    }

    public final int hashCode() {
        if (!this.a) {
            return 0;
        }
        long j = this.b;
        return (int) (j ^ (j >>> 32));
    }

    public final String toString() {
        if (this.a) {
            return "OptionalLong[" + this.b + "]";
        }
        return "OptionalLong.empty";
    }
}
