package j$.time.format;

import j$.util.Objects;
import java.util.HashMap;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.time.TimeZones;

/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static final a f;
    public static final a g;
    public final e a;
    public final Locale b;
    public final w c;
    public final y d;
    public final j$.time.chrono.m e;

    static {
        p pVar = new p();
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        z zVar = z.EXCEEDS_PAD;
        pVar.h(aVar, 4, 10, zVar);
        pVar.c('-');
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        pVar.g(aVar2, 2);
        pVar.c('-');
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        pVar.g(aVar3, 2);
        y yVar = y.STRICT;
        j$.time.chrono.t tVar = j$.time.chrono.t.c;
        a aVarK = pVar.k(yVar, tVar);
        f = aVarK;
        p pVar2 = new p();
        m mVar = m.INSENSITIVE;
        pVar2.b(mVar);
        pVar2.a(aVarK);
        j jVar = j.e;
        pVar2.b(jVar);
        pVar2.k(yVar, tVar);
        p pVar3 = new p();
        pVar3.b(mVar);
        pVar3.a(aVarK);
        pVar3.j();
        pVar3.b(jVar);
        pVar3.k(yVar, tVar);
        p pVar4 = new p();
        j$.time.temporal.a aVar4 = j$.time.temporal.a.HOUR_OF_DAY;
        pVar4.g(aVar4, 2);
        pVar4.c(':');
        j$.time.temporal.a aVar5 = j$.time.temporal.a.MINUTE_OF_HOUR;
        pVar4.g(aVar5, 2);
        pVar4.j();
        pVar4.c(':');
        j$.time.temporal.a aVar6 = j$.time.temporal.a.SECOND_OF_MINUTE;
        pVar4.g(aVar6, 2);
        pVar4.j();
        pVar4.b(new g(j$.time.temporal.a.NANO_OF_SECOND));
        a aVarK2 = pVar4.k(yVar, null);
        p pVar5 = new p();
        pVar5.b(mVar);
        pVar5.a(aVarK2);
        pVar5.b(jVar);
        pVar5.k(yVar, null);
        p pVar6 = new p();
        pVar6.b(mVar);
        pVar6.a(aVarK2);
        pVar6.j();
        pVar6.b(jVar);
        pVar6.k(yVar, null);
        p pVar7 = new p();
        pVar7.b(mVar);
        pVar7.a(aVarK);
        pVar7.c('T');
        pVar7.a(aVarK2);
        a aVarK3 = pVar7.k(yVar, tVar);
        p pVar8 = new p();
        pVar8.b(mVar);
        pVar8.a(aVarK3);
        m mVar2 = m.LENIENT;
        pVar8.b(mVar2);
        pVar8.b(jVar);
        m mVar3 = m.STRICT;
        pVar8.b(mVar3);
        a aVarK4 = pVar8.k(yVar, tVar);
        p pVar9 = new p();
        pVar9.a(aVarK4);
        pVar9.j();
        pVar9.c(AbstractJsonLexerKt.BEGIN_LIST);
        m mVar4 = m.SENSITIVE;
        pVar9.b(mVar4);
        pVar9.b(new h(1));
        pVar9.c(AbstractJsonLexerKt.END_LIST);
        pVar9.k(yVar, tVar);
        p pVar10 = new p();
        pVar10.a(aVarK3);
        pVar10.j();
        pVar10.b(jVar);
        pVar10.j();
        pVar10.c(AbstractJsonLexerKt.BEGIN_LIST);
        pVar10.b(mVar4);
        pVar10.b(new h(1));
        pVar10.c(AbstractJsonLexerKt.END_LIST);
        pVar10.k(yVar, tVar);
        p pVar11 = new p();
        pVar11.b(mVar);
        pVar11.h(aVar, 4, 10, zVar);
        pVar11.c('-');
        pVar11.g(j$.time.temporal.a.DAY_OF_YEAR, 3);
        pVar11.j();
        pVar11.b(jVar);
        pVar11.k(yVar, tVar);
        p pVar12 = new p();
        pVar12.b(mVar);
        pVar12.h(j$.time.temporal.j.c, 4, 10, zVar);
        pVar12.d("-W");
        pVar12.g(j$.time.temporal.j.b, 2);
        pVar12.c('-');
        j$.time.temporal.a aVar7 = j$.time.temporal.a.DAY_OF_WEEK;
        pVar12.g(aVar7, 1);
        pVar12.j();
        pVar12.b(jVar);
        pVar12.k(yVar, tVar);
        p pVar13 = new p();
        pVar13.b(mVar);
        pVar13.b(new h(0));
        g = pVar13.k(yVar, null);
        p pVar14 = new p();
        pVar14.b(mVar);
        pVar14.g(aVar, 4);
        pVar14.g(aVar2, 2);
        pVar14.g(aVar3, 2);
        pVar14.j();
        pVar14.b(mVar2);
        pVar14.b(new j("+HHMMss", "Z"));
        pVar14.b(mVar3);
        pVar14.k(yVar, tVar);
        HashMap map = new HashMap();
        map.put(1L, "Mon");
        map.put(2L, "Tue");
        map.put(3L, "Wed");
        map.put(4L, "Thu");
        map.put(5L, "Fri");
        map.put(6L, "Sat");
        map.put(7L, "Sun");
        HashMap map2 = new HashMap();
        map2.put(1L, "Jan");
        map2.put(2L, "Feb");
        map2.put(3L, "Mar");
        map2.put(4L, "Apr");
        map2.put(5L, "May");
        map2.put(6L, "Jun");
        map2.put(7L, "Jul");
        map2.put(8L, "Aug");
        map2.put(9L, "Sep");
        map2.put(10L, "Oct");
        map2.put(11L, "Nov");
        map2.put(12L, "Dec");
        p pVar15 = new p();
        pVar15.b(mVar);
        pVar15.b(mVar2);
        pVar15.j();
        pVar15.e(aVar7, map);
        pVar15.d(", ");
        pVar15.i();
        pVar15.h(aVar3, 1, 2, z.NOT_NEGATIVE);
        pVar15.c(' ');
        pVar15.e(aVar2, map2);
        pVar15.c(' ');
        pVar15.g(aVar, 4);
        pVar15.c(' ');
        pVar15.g(aVar4, 2);
        pVar15.c(':');
        pVar15.g(aVar5, 2);
        pVar15.j();
        pVar15.c(':');
        pVar15.g(aVar6, 2);
        pVar15.i();
        pVar15.c(' ');
        pVar15.b(new j("+HHMM", TimeZones.GMT_ID));
        pVar15.k(y.SMART, tVar);
    }

    public a(e eVar, Locale locale, y yVar, j$.time.chrono.m mVar) {
        w wVar = w.a;
        this.a = (e) Objects.requireNonNull(eVar, "printerParser");
        this.b = (Locale) Objects.requireNonNull(locale, "locale");
        this.c = (w) Objects.requireNonNull(wVar, "decimalStyle");
        this.d = (y) Objects.requireNonNull(yVar, "resolverStyle");
        this.e = mVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0275  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.time.format.x a(java.lang.CharSequence r27) {
        /*
            Method dump skipped, instruction units count: 1106
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.a.a(java.lang.CharSequence):j$.time.format.x");
    }

    public final String toString() {
        String string = this.a.toString();
        return string.startsWith("[") ? string : string.substring(1, string.length() - 1);
    }
}
