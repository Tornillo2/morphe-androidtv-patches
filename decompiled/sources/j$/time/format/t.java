package j$.time.format;

import j$.time.Instant;
import j$.time.ZoneId;
import j$.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class t {
    public final j$.time.temporal.n a;
    public final a b;
    public int c;

    public t(Instant instant, a aVar) {
        j$.time.chrono.m mVar = aVar.e;
        j$.time.temporal.n sVar = instant;
        if (mVar != null) {
            j$.time.chrono.m mVar2 = (j$.time.chrono.m) instant.B(j$.time.temporal.s.b);
            ZoneId zoneId = (ZoneId) instant.B(j$.time.temporal.s.a);
            j$.time.chrono.b bVarY = null;
            mVar = Objects.equals(mVar, mVar2) ? null : mVar;
            Objects.equals(null, zoneId);
            sVar = instant;
            if (mVar != null) {
                j$.time.chrono.m mVar3 = mVar != null ? mVar : mVar2;
                if (mVar != null) {
                    if (instant.e(j$.time.temporal.a.EPOCH_DAY)) {
                        bVarY = mVar3.y(instant);
                    } else if (mVar != j$.time.chrono.t.c || mVar2 != null) {
                        for (j$.time.temporal.a aVar2 : j$.time.temporal.a.values()) {
                            if (aVar2.isDateBased() && instant.e(aVar2)) {
                                throw new j$.time.b("Unable to apply override chronology '" + mVar + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + instant);
                            }
                        }
                    }
                }
                sVar = new s(bVarY, instant, mVar3, zoneId);
            }
        }
        this.a = sVar;
        this.b = aVar;
    }

    public final Long a(j$.time.temporal.r rVar) {
        int i = this.c;
        j$.time.temporal.n nVar = this.a;
        if (i <= 0 || nVar.e(rVar)) {
            return Long.valueOf(nVar.C(rVar));
        }
        return null;
    }

    public final String toString() {
        return this.a.toString();
    }
}
