package j$.time;

import com.google.common.base.Ascii;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlin.time.InstantKt;

/* JADX INFO: loaded from: classes2.dex */
public final class l implements j$.time.temporal.m, j$.time.temporal.o, Comparable, Serializable {
    public static final l e;
    public static final l f;
    public static final l g;
    public static final l[] h = new l[24];
    private static final long serialVersionUID = 6414437269572265201L;
    public final byte a;
    public final byte b;
    public final byte c;
    public final int d;

    static {
        int i = 0;
        while (true) {
            l[] lVarArr = h;
            if (i < lVarArr.length) {
                lVarArr[i] = new l(i, 0, 0, 0);
                i++;
            } else {
                l lVar = lVarArr[0];
                g = lVar;
                l lVar2 = lVarArr[12];
                e = lVar;
                f = new l(23, 59, 59, 999999999);
                return;
            }
        }
    }

    public static l R(int i, int i2, int i3, int i4) {
        j$.time.temporal.a.HOUR_OF_DAY.C(i);
        j$.time.temporal.a.MINUTE_OF_HOUR.C(i2);
        j$.time.temporal.a.SECOND_OF_MINUTE.C(i3);
        j$.time.temporal.a.NANO_OF_SECOND.C(i4);
        return O(i, i2, i3, i4);
    }

    public static l S(long j) {
        j$.time.temporal.a.NANO_OF_DAY.C(j);
        int i = (int) (j / 3600000000000L);
        long j2 = j - (((long) i) * 3600000000000L);
        int i2 = (int) (j2 / 60000000000L);
        long j3 = j2 - (((long) i2) * 60000000000L);
        int i3 = (int) (j3 / 1000000000);
        return O(i, i2, i3, (int) (j3 - (((long) i3) * 1000000000)));
    }

    public static l P(j$.time.temporal.n nVar) {
        Objects.requireNonNull(nVar, "temporal");
        l lVar = (l) nVar.B(j$.time.temporal.s.g);
        if (lVar != null) {
            return lVar;
        }
        throw new b("Unable to obtain LocalTime from TemporalAccessor: " + nVar + " of type " + nVar.getClass().getName());
    }

    public static l O(int i, int i2, int i3, int i4) {
        if ((i2 | i3 | i4) == 0) {
            return h[i];
        }
        return new l(i, i2, i3, i4);
    }

    public l(int i, int i2, int i3, int i4) {
        this.a = (byte) i;
        this.b = (byte) i2;
        this.c = (byte) i3;
        this.d = i4;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).N();
        }
        return rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return Q(rVar);
        }
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.NANO_OF_DAY) {
                return Z();
            }
            if (rVar == j$.time.temporal.a.MICRO_OF_DAY) {
                return Z() / 1000;
            }
            return Q(rVar);
        }
        return rVar.w(this);
    }

    public final int Q(j$.time.temporal.r rVar) {
        switch (k.a[((j$.time.temporal.a) rVar).ordinal()]) {
            case 1:
                return this.d;
            case 2:
                throw new j$.time.temporal.u("Invalid field 'NanoOfDay' for get() method, use getLong() instead");
            case 3:
                return this.d / 1000;
            case 4:
                throw new j$.time.temporal.u("Invalid field 'MicroOfDay' for get() method, use getLong() instead");
            case 5:
                return this.d / 1000000;
            case 6:
                return (int) (Z() / 1000000);
            case 7:
                return this.c;
            case 8:
                return a0();
            case 9:
                return this.b;
            case 10:
                return (this.a * 60) + this.b;
            case 11:
                return this.a % Ascii.FF;
            case 12:
                int i = this.a % Ascii.FF;
                if (i % 12 == 0) {
                    return 12;
                }
                return i;
            case 13:
                return this.a;
            case 14:
                byte b = this.a;
                if (b == 0) {
                    return 24;
                }
                return b;
            case 15:
                return this.a / Ascii.FF;
            default:
                throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] */
    public final l c(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (l) rVar.B(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        switch (k.a[aVar.ordinal()]) {
            case 1:
                return c0((int) j);
            case 2:
                return S(j);
            case 3:
                return c0(((int) j) * 1000);
            case 4:
                return S(j * 1000);
            case 5:
                return c0(((int) j) * 1000000);
            case 6:
                return S(j * 1000000);
            case 7:
                int i = (int) j;
                if (this.c != i) {
                    j$.time.temporal.a.SECOND_OF_MINUTE.C(i);
                    return O(this.a, this.b, i, this.d);
                }
                return this;
            case 8:
                return X(j - ((long) a0()));
            case 9:
                int i2 = (int) j;
                if (this.b != i2) {
                    j$.time.temporal.a.MINUTE_OF_HOUR.C(i2);
                    return O(this.a, i2, this.c, this.d);
                }
                return this;
            case 10:
                return V(j - ((long) ((this.a * 60) + this.b)));
            case 11:
                return U(j - ((long) (this.a % Ascii.FF)));
            case 12:
                if (j == 12) {
                    j = 0;
                }
                return U(j - ((long) (this.a % Ascii.FF)));
            case 13:
                int i3 = (int) j;
                if (this.a != i3) {
                    j$.time.temporal.a.HOUR_OF_DAY.C(i3);
                    return O(i3, this.b, this.c, this.d);
                }
                return this;
            case 14:
                if (j == 24) {
                    j = 0;
                }
                int i4 = (int) j;
                if (this.a != i4) {
                    j$.time.temporal.a.HOUR_OF_DAY.C(i4);
                    return O(i4, this.b, this.c, this.d);
                }
                return this;
            case 15:
                return U((j - ((long) (this.a / Ascii.FF))) * 12);
            default:
                throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
    }

    public final l c0(int i) {
        if (this.d == i) {
            return this;
        }
        j$.time.temporal.a.NANO_OF_SECOND.C(i);
        return O(this.a, this.b, this.c, i);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public final l d(long j, j$.time.temporal.t tVar) {
        if (tVar instanceof j$.time.temporal.b) {
            switch (k.b[((j$.time.temporal.b) tVar).ordinal()]) {
                case 1:
                    return W(j);
                case 2:
                    return W((j % 86400000000L) * 1000);
                case 3:
                    return W((j % 86400000) * 1000000);
                case 4:
                    return X(j);
                case 5:
                    return V(j);
                case 6:
                    return U(j);
                case 7:
                    return U((j % 2) * 12);
                default:
                    throw new j$.time.temporal.u("Unsupported unit: " + tVar);
            }
        }
        return (l) tVar.j(this, j);
    }

    public final l U(long j) {
        return j == 0 ? this : O(((((int) (j % 24)) + this.a) + 24) % 24, this.b, this.c, this.d);
    }

    public final l V(long j) {
        if (j != 0) {
            int i = (this.a * 60) + this.b;
            int i2 = ((((int) (j % 1440)) + i) + 1440) % 1440;
            if (i != i2) {
                return O(i2 / 60, i2 % 60, this.c, this.d);
            }
        }
        return this;
    }

    public final l X(long j) {
        if (j != 0) {
            int i = (this.b * 60) + (this.a * 3600) + this.c;
            int i2 = ((((int) (j % 86400)) + i) + 86400) % 86400;
            if (i != i2) {
                return O(i2 / 3600, (i2 / 60) % 60, i2 % 60, this.d);
            }
        }
        return this;
    }

    public final l W(long j) {
        if (j != 0) {
            long jZ = Z();
            long j2 = (((j % 86400000000000L) + jZ) + 86400000000000L) % 86400000000000L;
            if (jZ != j2) {
                return O((int) (j2 / 3600000000000L), (int) ((j2 / 60000000000L) % 60), (int) ((j2 / 1000000000) % 60), (int) (j2 % 1000000000));
            }
        }
        return this;
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? d(Long.MAX_VALUE, bVar).d(1L, bVar) : d(-j, bVar);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.b || eVar == j$.time.temporal.s.a || eVar == j$.time.temporal.s.e || eVar == j$.time.temporal.s.d) {
            return null;
        }
        if (eVar == j$.time.temporal.s.g) {
            return this;
        }
        if (eVar == j$.time.temporal.s.f) {
            return null;
        }
        if (eVar == j$.time.temporal.s.c) {
            return j$.time.temporal.b.NANOS;
        }
        return eVar.g(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(Z(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public final int a0() {
        return (this.b * 60) + (this.a * 3600) + this.c;
    }

    public final long Z() {
        return (((long) this.c) * 1000000000) + (((long) this.b) * 60000000000L) + (((long) this.a) * 3600000000000L) + ((long) this.d);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public final int compareTo(l lVar) {
        int iCompare = Integer.compare(this.a, lVar.a);
        return (iCompare == 0 && (iCompare = Integer.compare(this.b, lVar.b)) == 0 && (iCompare = Integer.compare(this.c, lVar.c)) == 0) ? Integer.compare(this.d, lVar.d) : iCompare;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            if (this.a == lVar.a && this.b == lVar.b && this.c == lVar.c && this.d == lVar.d) {
                return true;
            }
        }
        return false;
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        return (l) j$.com.android.tools.r8.a.a(hVar, this);
    }

    public final int hashCode() {
        long jZ = Z();
        return (int) (jZ ^ (jZ >>> 32));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(18);
        byte b = this.a;
        byte b2 = this.b;
        byte b3 = this.c;
        int i = this.d;
        sb.append(b < 10 ? "0" : "");
        sb.append((int) b);
        sb.append(b2 < 10 ? ":0" : ":");
        sb.append((int) b2);
        if (b3 > 0 || i > 0) {
            sb.append(b3 < 10 ? ":0" : ":");
            sb.append((int) b3);
            if (i > 0) {
                sb.append('.');
                if (i % 1000000 == 0) {
                    sb.append(Integer.toString((i / 1000000) + 1000).substring(1));
                } else if (i % 1000 == 0) {
                    sb.append(Integer.toString((i / 1000) + 1000000).substring(1));
                } else {
                    sb.append(Integer.toString(i + InstantKt.NANOS_PER_SECOND).substring(1));
                }
            }
        }
        return sb.toString();
    }

    private Object writeReplace() {
        return new u((byte) 4, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final void d0(DataOutput dataOutput) {
        if (this.d == 0) {
            if (this.c == 0) {
                if (this.b == 0) {
                    dataOutput.writeByte(~this.a);
                    return;
                } else {
                    dataOutput.writeByte(this.a);
                    dataOutput.writeByte(~this.b);
                    return;
                }
            }
            dataOutput.writeByte(this.a);
            dataOutput.writeByte(this.b);
            dataOutput.writeByte(~this.c);
            return;
        }
        dataOutput.writeByte(this.a);
        dataOutput.writeByte(this.b);
        dataOutput.writeByte(this.c);
        dataOutput.writeInt(this.d);
    }

    public static l Y(DataInput dataInput) throws IOException {
        int i;
        int i2;
        int i3 = dataInput.readByte();
        int i4 = 0;
        if (i3 >= 0) {
            byte b = dataInput.readByte();
            if (b < 0) {
                int i5 = ~b;
                i = 0;
                i4 = i5;
                i2 = 0;
            } else {
                byte b2 = dataInput.readByte();
                if (b2 < 0) {
                    i2 = ~b2;
                    i4 = b;
                } else {
                    i = dataInput.readInt();
                    i4 = b;
                    i2 = b2;
                }
            }
            return R(i3, i4, i2, i);
        }
        i3 = ~i3;
        i2 = 0;
        i = 0;
        return R(i3, i4, i2, i);
    }
}
