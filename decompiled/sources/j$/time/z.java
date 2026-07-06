package j$.time;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class z extends ZoneId implements j$.time.temporal.n, j$.time.temporal.o, Comparable {
    public static final ConcurrentHashMap d = new ConcurrentHashMap(16, 0.75f, 4);
    public static final ConcurrentHashMap e = new ConcurrentHashMap(16, 0.75f, 4);
    public static final z f = U(0);
    public static final z g = U(-64800);
    public static final z h = U(64800);
    private static final long serialVersionUID = 2357656521762053153L;
    public final int b;
    public final transient String c;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return ((z) obj).b - this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static j$.time.z S(java.lang.String r7) {
        /*
            java.lang.String r0 = "offsetId"
            j$.util.Objects.requireNonNull(r7, r0)
            j$.util.concurrent.ConcurrentHashMap r0 = j$.time.z.e
            java.lang.Object r0 = r0.get(r7)
            j$.time.z r0 = (j$.time.z) r0
            if (r0 == 0) goto L10
            return r0
        L10:
            int r0 = r7.length()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L63
            r1 = 3
            if (r0 == r1) goto L7f
            r4 = 5
            if (r0 == r4) goto L5a
            r5 = 6
            r6 = 4
            if (r0 == r5) goto L50
            r5 = 7
            if (r0 == r5) goto L43
            r1 = 9
            if (r0 != r1) goto L37
            int r0 = V(r7, r2, r3)
            int r1 = V(r7, r6, r2)
            int r2 = V(r7, r5, r2)
            goto L85
        L37:
            j$.time.b r0 = new j$.time.b
            java.lang.String r1 = "Invalid ID for ZoneOffset, invalid format: "
            java.lang.String r7 = r1.concat(r7)
            r0.<init>(r7)
            throw r0
        L43:
            int r0 = V(r7, r2, r3)
            int r1 = V(r7, r1, r3)
            int r2 = V(r7, r4, r3)
            goto L85
        L50:
            int r0 = V(r7, r2, r3)
            int r1 = V(r7, r6, r2)
        L58:
            r2 = 0
            goto L85
        L5a:
            int r0 = V(r7, r2, r3)
            int r1 = V(r7, r1, r3)
            goto L58
        L63:
            char r0 = r7.charAt(r3)
            char r7 = r7.charAt(r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "0"
            r1.append(r0)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
        L7f:
            int r0 = V(r7, r2, r3)
            r1 = 0
            goto L58
        L85:
            char r3 = r7.charAt(r3)
            r4 = 43
            r5 = 45
            if (r3 == r4) goto L9e
            if (r3 != r5) goto L92
            goto L9e
        L92:
            j$.time.b r0 = new j$.time.b
            java.lang.String r1 = "Invalid ID for ZoneOffset, plus/minus not found when expected: "
            java.lang.String r7 = r1.concat(r7)
            r0.<init>(r7)
            throw r0
        L9e:
            if (r3 != r5) goto La8
            int r7 = -r0
            int r0 = -r1
            int r1 = -r2
            j$.time.z r7 = T(r7, r0, r1)
            return r7
        La8:
            j$.time.z r7 = T(r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.z.S(java.lang.String):j$.time.z");
    }

    @Override // j$.time.ZoneId
    public final j$.time.zone.f N() {
        Objects.requireNonNull(this, "offset");
        return new j$.time.zone.f(this);
    }

    public static int V(CharSequence charSequence, int i, boolean z) {
        if (z) {
            String str = (String) charSequence;
            if (str.charAt(i - 1) != ':') {
                throw new b("Invalid ID for ZoneOffset, colon not found when expected: " + ((Object) str));
            }
        }
        String str2 = (String) charSequence;
        char cCharAt = str2.charAt(i);
        char cCharAt2 = str2.charAt(i + 1);
        if (cCharAt >= '0' && cCharAt <= '9' && cCharAt2 >= '0' && cCharAt2 <= '9') {
            return (cCharAt2 - '0') + ((cCharAt - '0') * 10);
        }
        throw new b("Invalid ID for ZoneOffset, non numeric characters found: " + ((Object) str2));
    }

    public static z T(int i, int i2, int i3) {
        if (i < -18 || i > 18) {
            throw new b("Zone offset hours not in valid range: value " + i + " is not in the range -18 to 18");
        }
        if (i > 0) {
            if (i2 < 0 || i3 < 0) {
                throw new b("Zone offset minutes and seconds must be positive because hours is positive");
            }
        } else if (i < 0) {
            if (i2 > 0 || i3 > 0) {
                throw new b("Zone offset minutes and seconds must be negative because hours is negative");
            }
        } else if ((i2 > 0 && i3 < 0) || (i2 < 0 && i3 > 0)) {
            throw new b("Zone offset minutes and seconds must have the same sign");
        }
        if (i2 < -59 || i2 > 59) {
            throw new b("Zone offset minutes not in valid range: value " + i2 + " is not in the range -59 to 59");
        }
        if (i3 < -59 || i3 > 59) {
            throw new b("Zone offset seconds not in valid range: value " + i3 + " is not in the range -59 to 59");
        }
        if (Math.abs(i) == 18 && (i2 | i3) != 0) {
            throw new b("Zone offset not in valid range: -18:00 to +18:00");
        }
        return U((i2 * 60) + (i * 3600) + i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static z U(int i) {
        if (i < -64800 || i > 64800) {
            throw new b("Zone offset not in valid range: -18:00 to +18:00");
        }
        if (i % 900 == 0) {
            Integer numValueOf = Integer.valueOf(i);
            ConcurrentHashMap concurrentHashMap = d;
            z zVar = (z) concurrentHashMap.get(numValueOf);
            if (zVar != null) {
                return zVar;
            }
            concurrentHashMap.putIfAbsent(numValueOf, new z(i));
            z zVar2 = (z) concurrentHashMap.get(numValueOf);
            e.putIfAbsent(zVar2.c, zVar2);
            return zVar2;
        }
        return new z(i);
    }

    public z(int i) {
        String string;
        this.b = i;
        if (i == 0) {
            string = "Z";
        } else {
            int iAbs = Math.abs(i);
            StringBuilder sb = new StringBuilder();
            int i2 = iAbs / 3600;
            int i3 = (iAbs / 60) % 60;
            sb.append(i < 0 ? "-" : "+");
            sb.append(i2 < 10 ? "0" : "");
            sb.append(i2);
            sb.append(i3 < 10 ? ":0" : ":");
            sb.append(i3);
            int i4 = iAbs % 60;
            if (i4 != 0) {
                sb.append(i4 < 10 ? ":0" : ":");
                sb.append(i4);
            }
            string = sb.toString();
        }
        this.c = string;
    }

    @Override // j$.time.ZoneId
    public final String i() {
        return this.c;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.OFFSET_SECONDS : rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.b;
        }
        if (c.b(rVar)) {
            throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
        return j$.time.temporal.s.d(this, rVar).a(C(rVar), rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.b;
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
        return rVar.w(this);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        return (eVar == j$.time.temporal.s.d || eVar == j$.time.temporal.s.e) ? this : j$.time.temporal.s.c(this, eVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(this.b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    @Override // j$.time.ZoneId
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof z) && this.b == ((z) obj).b;
    }

    @Override // j$.time.ZoneId
    public final int hashCode() {
        return this.b;
    }

    @Override // j$.time.ZoneId
    public final String toString() {
        return this.c;
    }

    private Object writeReplace() {
        return new u((byte) 8, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.ZoneId
    public final void R(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(8);
        X(dataOutput);
    }

    public final void X(DataOutput dataOutput) {
        int i = this.b;
        int i2 = i % 900 == 0 ? i / 900 : 127;
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(i);
        }
    }

    public static z W(DataInput dataInput) {
        byte b = dataInput.readByte();
        return b == 127 ? U(dataInput.readInt()) : U(b * 900);
    }
}
