package j$.time;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class Duration implements j$.time.temporal.q, Comparable<Duration>, Serializable {
    public static final Duration c = new Duration(0, 0);
    private static final long serialVersionUID = 3078945930695997490L;
    public final long a;
    public final int b;

    @Override // java.lang.Comparable
    public final int compareTo(Duration duration) {
        Duration duration2 = duration;
        int iCompare = Long.compare(this.a, duration2.a);
        return iCompare != 0 ? iCompare : this.b - duration2.b;
    }

    static {
        BigInteger.valueOf(1000000000L);
    }

    public static Duration ofSeconds(long j, long j2) {
        return k(j$.com.android.tools.r8.a.F(j, j$.com.android.tools.r8.a.K(j2, 1000000000L)), (int) j$.com.android.tools.r8.a.J(j2, 1000000000L));
    }

    public static Duration ofMillis(long j) {
        long j2 = j / 1000;
        int i = (int) (j % 1000);
        if (i < 0) {
            i += 1000;
            j2--;
        }
        return k(j2, i * 1000000);
    }

    public static Duration ofNanos(long j) {
        long j2 = j / 1000000000;
        int i = (int) (j % 1000000000);
        if (i < 0) {
            i = (int) (((long) i) + 1000000000);
            j2--;
        }
        return k(j2, i);
    }

    public static Duration k(long j, int i) {
        if ((((long) i) | j) == 0) {
            return c;
        }
        return new Duration(j, i);
    }

    public Duration(long j, int i) {
        this.a = j;
        this.b = i;
    }

    public boolean isZero() {
        return (this.a | ((long) this.b)) == 0;
    }

    public boolean isNegative() {
        return this.a < 0;
    }

    public long getSeconds() {
        return this.a;
    }

    public int getNano() {
        return this.b;
    }

    @Override // j$.time.temporal.q
    public final j$.time.temporal.m j(j$.time.temporal.m mVar) {
        long j = this.a;
        if (j != 0) {
            mVar = mVar.d(j, j$.time.temporal.b.SECONDS);
        }
        int i = this.b;
        return i != 0 ? mVar.d(i, j$.time.temporal.b.NANOS) : mVar;
    }

    public long toMillis() {
        long j = this.a;
        long j2 = this.b;
        if (j < 0) {
            j++;
            j2 -= 1000000000;
        }
        return j$.com.android.tools.r8.a.F(j$.com.android.tools.r8.a.L(j, 1000), j2 / 1000000);
    }

    public long toNanos() {
        long j = this.a;
        long j2 = this.b;
        if (j < 0) {
            j++;
            j2 -= 1000000000;
        }
        return j$.com.android.tools.r8.a.F(j$.com.android.tools.r8.a.L(j, 1000000000L), j2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Duration) {
            Duration duration = (Duration) obj;
            if (this.a == duration.a && this.b == duration.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.a;
        return (this.b * 51) + ((int) (j ^ (j >>> 32)));
    }

    public final String toString() {
        if (this == c) {
            return "PT0S";
        }
        long j = this.a;
        if (j < 0 && this.b > 0) {
            j++;
        }
        long j2 = j / 3600;
        int i = (int) ((j % 3600) / 60);
        int i2 = (int) (j % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j2 != 0) {
            sb.append(j2);
            sb.append('H');
        }
        if (i != 0) {
            sb.append(i);
            sb.append('M');
        }
        if (i2 == 0 && this.b == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (this.a < 0 && this.b > 0 && i2 == 0) {
            sb.append("-0");
        } else {
            sb.append(i2);
        }
        if (this.b > 0) {
            int length = sb.length();
            if (this.a < 0) {
                sb.append(2000000000 - ((long) this.b));
            } else {
                sb.append(((long) this.b) + 1000000000);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, '.');
        }
        sb.append('S');
        return sb.toString();
    }

    private Object writeReplace() {
        return new u((byte) 1, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
