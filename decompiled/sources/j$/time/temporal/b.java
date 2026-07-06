package j$.time.temporal;

import j$.time.Duration;

/* JADX INFO: loaded from: classes2.dex */
public enum b implements t {
    NANOS("Nanos"),
    MICROS("Micros"),
    MILLIS("Millis"),
    SECONDS("Seconds"),
    MINUTES("Minutes"),
    HOURS("Hours"),
    HALF_DAYS("HalfDays"),
    DAYS("Days"),
    WEEKS("Weeks"),
    MONTHS("Months"),
    YEARS("Years"),
    DECADES("Decades"),
    CENTURIES("Centuries"),
    MILLENNIA("Millennia"),
    ERAS("Eras"),
    FOREVER("Forever");

    public final String a;

    static {
        Duration.ofNanos(1L);
        Duration.ofNanos(1000L);
        Duration.ofNanos(1000000L);
        Duration.k(1L, 0);
        Duration.k(60L, 0);
        Duration.k(3600L, 0);
        Duration.k(43200L, 0);
        Duration.k(86400L, 0);
        Duration.k(604800L, 0);
        Duration.k(2629746L, 0);
        Duration.k(31556952L, 0);
        Duration.k(315569520L, 0);
        Duration.k(3155695200L, 0);
        Duration.k(31556952000L, 0);
        Duration.k(31556952000000000L, 0);
        Duration.ofSeconds(Long.MAX_VALUE, 999999999L);
    }

    b(String str) {
        this.a = str;
    }

    @Override // j$.time.temporal.t
    public final m j(m mVar, long j) {
        return mVar.d(j, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
