package j$.time.format;

import j$.time.Instant;
import j$.time.ZoneId;

/* JADX INFO: loaded from: classes2.dex */
public final class s implements j$.time.temporal.n {
    public final /* synthetic */ j$.time.chrono.b a;
    public final /* synthetic */ Instant b;
    public final /* synthetic */ j$.time.chrono.m c;
    public final /* synthetic */ ZoneId d;

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.a(this, rVar);
    }

    public s(j$.time.chrono.b bVar, Instant instant, j$.time.chrono.m mVar, ZoneId zoneId) {
        this.a = bVar;
        this.b = instant;
        this.c = mVar;
        this.d = zoneId;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        j$.time.chrono.b bVar = this.a;
        if (bVar != null && rVar.isDateBased()) {
            return bVar.e(rVar);
        }
        return this.b.e(rVar);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        j$.time.chrono.b bVar = this.a;
        if (bVar != null && rVar.isDateBased()) {
            return bVar.l(rVar);
        }
        return j$.time.temporal.s.d(this.b, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        j$.time.chrono.b bVar = this.a;
        if (bVar != null && rVar.isDateBased()) {
            return bVar.C(rVar);
        }
        return this.b.C(rVar);
    }

    @Override // j$.time.temporal.n
    public final Object B(j$.time.e eVar) {
        if (eVar == j$.time.temporal.s.b) {
            return this.c;
        }
        if (eVar == j$.time.temporal.s.a) {
            return this.d;
        }
        if (eVar == j$.time.temporal.s.c) {
            return this.b.B(eVar);
        }
        return eVar.g(this);
    }

    public final String toString() {
        String str;
        String str2 = "";
        j$.time.chrono.m mVar = this.c;
        if (mVar != null) {
            str = " with chronology " + mVar;
        } else {
            str = "";
        }
        ZoneId zoneId = this.d;
        if (zoneId != null) {
            str2 = " with zone " + zoneId;
        }
        return this.b + str + str2;
    }
}
