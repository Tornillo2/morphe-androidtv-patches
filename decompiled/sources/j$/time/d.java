package j$.time;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements j$.time.temporal.n, j$.time.temporal.o {
    public static final d FRIDAY;
    public static final d MONDAY;
    public static final d SATURDAY;
    public static final d SUNDAY;
    public static final d THURSDAY;
    public static final d TUESDAY;
    public static final d WEDNESDAY;
    public static final d[] a;
    public static final /* synthetic */ d[] b;

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) b.clone();
    }

    static {
        d dVar = new d("MONDAY", 0);
        MONDAY = dVar;
        d dVar2 = new d("TUESDAY", 1);
        TUESDAY = dVar2;
        d dVar3 = new d("WEDNESDAY", 2);
        WEDNESDAY = dVar3;
        d dVar4 = new d("THURSDAY", 3);
        THURSDAY = dVar4;
        d dVar5 = new d("FRIDAY", 4);
        FRIDAY = dVar5;
        d dVar6 = new d("SATURDAY", 5);
        SATURDAY = dVar6;
        d dVar7 = new d("SUNDAY", 6);
        SUNDAY = dVar7;
        b = new d[]{dVar, dVar2, dVar3, dVar4, dVar5, dVar6, dVar7};
        a = values();
    }

    public static d N(int i) {
        if (i < 1 || i > 7) {
            throw new b("Invalid value for DayOfWeek: " + i);
        }
        return a[i - 1];
    }

    public final int getValue() {
        return ordinal() + 1;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.DAY_OF_WEEK : rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return rVar.o();
        }
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return getValue();
        }
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return getValue();
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
        return rVar.w(this);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.c) {
            return j$.time.temporal.b.DAYS;
        }
        return j$.time.temporal.s.c(this, eVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(getValue(), j$.time.temporal.a.DAY_OF_WEEK);
    }
}
