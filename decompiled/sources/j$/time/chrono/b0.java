package j$.time.chrono;

import j$.time.Instant;
import j$.time.ZoneId;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class b0 extends a implements Serializable {
    public static final b0 c = new b0();
    private static final long serialVersionUID = 1039765215346859963L;

    @Override // j$.time.chrono.m
    public final String i() {
        return "Minguo";
    }

    @Override // j$.time.chrono.m
    public final n s(int i) {
        if (i == 0) {
            return e0.BEFORE_ROC;
        }
        if (i == 1) {
            return e0.ROC;
        }
        throw new j$.time.b("Invalid era: " + i);
    }

    @Override // j$.time.chrono.m
    public final String m() {
        return "roc";
    }

    @Override // j$.time.chrono.m
    public final b H(int i, int i2, int i3) {
        return new d0(j$.time.h.X(i + 1911, i2, i3));
    }

    @Override // j$.time.chrono.m
    public final b n(int i, int i2) {
        return new d0(j$.time.h.Z(i + 1911, i2));
    }

    @Override // j$.time.chrono.m
    public final b h(long j) {
        return new d0(j$.time.h.Y(j));
    }

    @Override // j$.time.chrono.a
    public final b k() {
        return new d0(j$.time.h.P(j$.time.h.W(j$.com.android.tools.r8.a.Q())));
    }

    @Override // j$.time.chrono.m
    public final b y(j$.time.temporal.n nVar) {
        if (nVar instanceof d0) {
            return (d0) nVar;
        }
        return new d0(j$.time.h.P(nVar));
    }

    @Override // j$.time.chrono.m
    public final int t(n nVar, int i) {
        if (nVar instanceof e0) {
            return nVar == e0.ROC ? i : 1 - i;
        }
        throw new ClassCastException("Era must be MinguoEra");
    }

    @Override // j$.time.chrono.m
    public final List r() {
        return j$.com.android.tools.r8.a.G(e0.values());
    }

    @Override // j$.time.chrono.m
    public final j$.time.temporal.v q(j$.time.temporal.a aVar) {
        int i = a0.a[aVar.ordinal()];
        if (i == 1) {
            j$.time.temporal.v vVar = j$.time.temporal.a.PROLEPTIC_MONTH.b;
            return j$.time.temporal.v.f(vVar.a - 22932, vVar.d - 22932);
        }
        if (i == 2) {
            j$.time.temporal.v vVar2 = j$.time.temporal.a.YEAR.b;
            return j$.time.temporal.v.g(vVar2.d - 1911, (-vVar2.a) + 1912);
        }
        if (i != 3) {
            return aVar.b;
        }
        j$.time.temporal.v vVar3 = j$.time.temporal.a.YEAR.b;
        return j$.time.temporal.v.f(vVar3.a - 1911, vVar3.d - 1911);
    }

    @Override // j$.time.chrono.a, j$.time.chrono.m
    public final b J(Map map, j$.time.format.y yVar) {
        return (d0) super.J(map, yVar);
    }

    private b0() {
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.m
    public final j K(Instant instant, ZoneId zoneId) {
        return l.O(this, instant, zoneId);
    }

    public Object writeReplace() {
        return new f0((byte) 1, this);
    }
}
