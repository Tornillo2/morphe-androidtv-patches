package j$.time.temporal;

import j$.time.Duration;

/* JADX INFO: loaded from: classes2.dex */
public enum i implements t {
    WEEK_BASED_YEARS("WeekBasedYears"),
    QUARTER_YEARS("QuarterYears");

    public final String a;

    static {
        Duration.k(31556952L, 0);
        Duration.k(7889238L, 0);
    }

    i(String str) {
        this.a = str;
    }

    @Override // j$.time.temporal.t
    public final m j(m mVar, long j) {
        int i = c.a[ordinal()];
        if (i == 1) {
            return mVar.c(j$.com.android.tools.r8.a.F(mVar.j(r0), j), j.c);
        }
        if (i == 2) {
            return mVar.d(j / 4, b.YEARS).d((j % 4) * 3, b.MONTHS);
        }
        throw new IllegalStateException("Unreachable");
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
