package j$.time.zone;

import androidx.media3.extractor.DtsUtil;
import j$.time.Instant;
import j$.time.chrono.t;
import j$.time.j;
import j$.time.l;
import j$.time.n;
import j$.time.temporal.p;
import j$.time.z;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements Serializable {
    public static final long[] i = new long[0];
    public static final e[] j = new e[0];
    public static final j[] k = new j[0];
    public static final b[] l = new b[0];
    private static final long serialVersionUID = 3044319355680032515L;
    public final long[] a;
    public final z[] b;
    public final long[] c;
    public final j[] d;
    public final z[] e;
    public final e[] f;
    public final TimeZone g;
    public final transient ConcurrentHashMap h = new ConcurrentHashMap();

    public static Object a(j jVar, b bVar) {
        j jVar2 = bVar.b;
        if (bVar.j()) {
            if (jVar.P(jVar2)) {
                return bVar.c;
            }
            if (!jVar.P(bVar.b.T(bVar.d.b - bVar.c.b))) {
                return bVar.d;
            }
        } else {
            if (!jVar.P(jVar2)) {
                return bVar.d;
            }
            if (jVar.P(bVar.b.T(bVar.d.b - bVar.c.b))) {
                return bVar.c;
            }
        }
        return bVar;
    }

    public f(long[] jArr, z[] zVarArr, long[] jArr2, z[] zVarArr2, e[] eVarArr) {
        this.a = jArr;
        this.b = zVarArr;
        this.c = jArr2;
        this.e = zVarArr2;
        this.f = eVarArr;
        if (jArr2.length == 0) {
            this.d = k;
        } else {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < jArr2.length) {
                int i3 = i2 + 1;
                b bVar = new b(jArr2[i2], zVarArr2[i2], zVarArr2[i3]);
                if (bVar.j()) {
                    arrayList.add(bVar.b);
                    arrayList.add(bVar.b.T(bVar.d.b - bVar.c.b));
                } else {
                    arrayList.add(bVar.b.T(bVar.d.b - bVar.c.b));
                    arrayList.add(bVar.b);
                }
                i2 = i3;
            }
            this.d = (j[]) arrayList.toArray(new j[arrayList.size()]);
        }
        this.g = null;
    }

    public f(z zVar) {
        z[] zVarArr = {zVar};
        this.b = zVarArr;
        long[] jArr = i;
        this.a = jArr;
        this.c = jArr;
        this.d = k;
        this.e = zVarArr;
        this.f = j;
        this.g = null;
    }

    public f(TimeZone timeZone) {
        z[] zVarArr = {g(timeZone.getRawOffset())};
        this.b = zVarArr;
        long[] jArr = i;
        this.a = jArr;
        this.c = jArr;
        this.d = k;
        this.e = zVarArr;
        this.f = j;
        this.g = timeZone;
    }

    public static z g(int i2) {
        return z.U(i2 / 1000);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a(this.g != null ? DtsUtil.FIRST_BYTE_EXTSS_BE : (byte) 1, this);
    }

    public static int c(long j2, z zVar) {
        return j$.time.h.Y(j$.com.android.tools.r8.a.K(j2 + ((long) zVar.b), 86400)).a;
    }

    public final z d(Instant instant) {
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            return g(timeZone.getOffset(instant.toEpochMilli()));
        }
        if (this.c.length == 0) {
            return this.b[0];
        }
        long epochSecond = instant.getEpochSecond();
        if (this.f.length > 0) {
            if (epochSecond > this.c[r8.length - 1]) {
                b[] bVarArrB = b(c(epochSecond, this.e[r8.length - 1]));
                b bVar = null;
                for (int i2 = 0; i2 < bVarArrB.length; i2++) {
                    bVar = bVarArrB[i2];
                    if (epochSecond < bVar.a) {
                        return bVar.c;
                    }
                }
                return bVar.d;
            }
        }
        int iBinarySearch = Arrays.binarySearch(this.c, epochSecond);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        }
        return this.e[iBinarySearch + 1];
    }

    public final List f(j jVar) {
        Object objE = e(jVar);
        if (!(objE instanceof b)) {
            return Collections.singletonList((z) objE);
        }
        b bVar = (b) objE;
        return bVar.j() ? Collections.EMPTY_LIST : j$.com.android.tools.r8.a.G(new Object[]{bVar.c, bVar.d});
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0063, code lost:
    
        if (r9.N(r0) > 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0086, code lost:
    
        if (r9.b.Z() <= r0.b.Z()) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(j$.time.j r9) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.zone.f.e(j$.time.j):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final b[] b(int i2) {
        j$.time.h hVarO;
        b[] bVarArr = l;
        Integer numValueOf = Integer.valueOf(i2);
        b[] bVarArr2 = (b[]) this.h.get(numValueOf);
        if (bVarArr2 != null) {
            return bVarArr2;
        }
        long j2 = 1;
        int i3 = 0;
        int i4 = 1;
        if (this.g != null) {
            if (i2 < 1800) {
                return bVarArr;
            }
            j jVar = j.c;
            j$.time.h hVarX = j$.time.h.X(i2 - 1, 12, 31);
            j$.time.temporal.a.HOUR_OF_DAY.C(0);
            long jQ = j$.com.android.tools.r8.a.q(new j(hVarX, l.h[0]), this.b[0]);
            long j3 = 1000;
            int offset = this.g.getOffset(jQ * 1000);
            long j4 = 31968000 + jQ;
            while (jQ < j4) {
                long j5 = jQ + 7776000;
                long j6 = j3;
                if (offset != this.g.getOffset(j5 * j6)) {
                    while (j5 - jQ > j2) {
                        long jK = j$.com.android.tools.r8.a.K(j5 + jQ, 2L);
                        if (this.g.getOffset(jK * j6) == offset) {
                            jQ = jK;
                        } else {
                            j5 = jK;
                        }
                        j2 = 1;
                    }
                    if (this.g.getOffset(jQ * j6) == offset) {
                        jQ = j5;
                    }
                    z zVarG = g(offset);
                    int offset2 = this.g.getOffset(jQ * j6);
                    z zVarG2 = g(offset2);
                    if (c(jQ, zVarG2) == i2) {
                        bVarArr = (b[]) Arrays.copyOf(bVarArr, bVarArr.length + 1);
                        bVarArr[bVarArr.length - 1] = new b(jQ, zVarG, zVarG2);
                    }
                    offset = offset2;
                } else {
                    jQ = j5;
                }
                j3 = j6;
                j2 = 1;
            }
            if (1916 <= i2 && i2 < 2100) {
                this.h.putIfAbsent(numValueOf, bVarArr);
            }
            return bVarArr;
        }
        e[] eVarArr = this.f;
        b[] bVarArr3 = new b[eVarArr.length];
        int i5 = 0;
        while (i5 < eVarArr.length) {
            e eVar = eVarArr[i5];
            byte b = eVar.b;
            if (b < 0) {
                n nVar = eVar.a;
                long j7 = i2;
                t.c.getClass();
                int iO = nVar.O(t.N(j7)) + 1 + eVar.b;
                j$.time.h hVar = j$.time.h.d;
                j$.time.temporal.a.YEAR.C(j7);
                Objects.requireNonNull(nVar, "month");
                j$.time.temporal.a.DAY_OF_MONTH.C(iO);
                hVarO = j$.time.h.O(i2, nVar.getValue(), iO);
                j$.time.d dVar = eVar.c;
                if (dVar != null) {
                    hVarO = hVarO.k(new p(dVar.getValue(), i4));
                }
            } else {
                n nVar2 = eVar.a;
                j$.time.h hVar2 = j$.time.h.d;
                j$.time.temporal.a.YEAR.C(i2);
                Objects.requireNonNull(nVar2, "month");
                j$.time.temporal.a.DAY_OF_MONTH.C(b);
                hVarO = j$.time.h.O(i2, nVar2.getValue(), b);
                j$.time.d dVar2 = eVar.c;
                if (dVar2 != null) {
                    hVarO = hVarO.k(new p(dVar2.getValue(), i3));
                }
            }
            if (eVar.e) {
                hVarO = hVarO.b0(1L);
            }
            j jVarQ = j.Q(hVarO, eVar.d);
            d dVar3 = eVar.f;
            z zVar = eVar.g;
            z zVar2 = eVar.h;
            dVar3.getClass();
            int i6 = c.a[dVar3.ordinal()];
            if (i6 == 1) {
                jVarQ = jVarQ.T(zVar2.b - z.f.b);
            } else if (i6 == 2) {
                jVarQ = jVarQ.T(zVar2.b - zVar.b);
            }
            bVarArr3[i5] = new b(jVarQ, eVar.h, eVar.i);
            i5++;
            i3 = 0;
        }
        if (i2 < 2100) {
            this.h.putIfAbsent(numValueOf, bVarArr3);
        }
        return bVarArr3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (Objects.equals(this.g, fVar.g) && Arrays.equals(this.a, fVar.a) && Arrays.equals(this.b, fVar.b) && Arrays.equals(this.c, fVar.c) && Arrays.equals(this.e, fVar.e) && Arrays.equals(this.f, fVar.f)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((Objects.hashCode(this.g) ^ Arrays.hashCode(this.a)) ^ Arrays.hashCode(this.b)) ^ Arrays.hashCode(this.c)) ^ Arrays.hashCode(this.e)) ^ Arrays.hashCode(this.f);
    }

    public final String toString() {
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            return "ZoneRules[timeZone=" + timeZone.getID() + "]";
        }
        return "ZoneRules[currentStandardOffset=" + this.b[r0.length - 1] + "]";
    }
}
