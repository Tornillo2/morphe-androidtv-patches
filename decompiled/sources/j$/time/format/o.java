package j$.time.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class o implements f {
    public final j$.time.temporal.r a;
    public final a0 b;
    public final b c;
    public volatile i d;

    public o(j$.time.temporal.r rVar, a0 a0Var, b bVar) {
        this.a = rVar;
        this.b = a0Var;
        this.c = bVar;
    }

    @Override // j$.time.format.f
    public final boolean j(t tVar, StringBuilder sb) {
        String strA;
        Long lA = tVar.a(this.a);
        a aVar = tVar.b;
        if (lA == null) {
            return false;
        }
        j$.time.chrono.m mVar = (j$.time.chrono.m) tVar.a.B(j$.time.temporal.s.b);
        if (mVar == null || mVar == j$.time.chrono.t.c) {
            b bVar = this.c;
            long jLongValue = lA.longValue();
            a0 a0Var = this.b;
            Locale locale = aVar.b;
            strA = bVar.a.a(jLongValue, a0Var);
        } else {
            b bVar2 = this.c;
            long jLongValue2 = lA.longValue();
            a0 a0Var2 = this.b;
            Locale locale2 = aVar.b;
            strA = bVar2.a.a(jLongValue2, a0Var2);
        }
        if (strA != null) {
            sb.append(strA);
            return true;
        }
        if (this.d == null) {
            this.d = new i(this.a, 1, 19, z.NORMAL);
        }
        return this.d.j(tVar, sb);
    }

    @Override // j$.time.format.f
    public final int k(q qVar, CharSequence charSequence, int i) {
        b bVar = this.c;
        j$.time.temporal.r rVar = this.a;
        int length = charSequence.length();
        if (i >= 0 && i <= length) {
            boolean z = qVar.c;
            a aVar = qVar.a;
            Iterator it = null;
            a0 a0Var = z ? this.b : null;
            j$.time.chrono.m mVar = qVar.c().c;
            if (mVar == null && (mVar = qVar.a.e) == null) {
                mVar = j$.time.chrono.t.c;
            }
            if (mVar == null || mVar == j$.time.chrono.t.c) {
                Locale locale = aVar.b;
                List list = (List) ((HashMap) ((Map) bVar.a.c)).get(a0Var);
                it = list != null ? list.iterator() : null;
            } else {
                Locale locale2 = aVar.b;
                List list2 = (List) ((HashMap) ((Map) bVar.a.c)).get(a0Var);
                if (list2 != null) {
                    it = list2.iterator();
                }
            }
            Iterator it2 = it;
            if (it2 != null) {
                while (it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it2.next();
                    String str = (String) entry.getKey();
                    if (qVar.g(str, 0, charSequence, i, str.length())) {
                        return qVar.f(this.a, ((Long) entry.getValue()).longValue(), i, str.length() + i);
                    }
                }
                if (rVar == j$.time.temporal.a.ERA && !qVar.c) {
                    Iterator it3 = mVar.r().iterator();
                    while (it3.hasNext()) {
                        String string = ((j$.time.chrono.n) it3.next()).toString();
                        if (qVar.g(string, 0, charSequence, i, string.length())) {
                            return qVar.f(this.a, r7.getValue(), i, string.length() + i);
                        }
                    }
                }
                if (qVar.c) {
                    return ~i;
                }
            }
            if (this.d == null) {
                this.d = new i(this.a, 1, 19, z.NORMAL);
            }
            return this.d.k(qVar, charSequence, i);
        }
        throw new IndexOutOfBoundsException();
    }

    public final String toString() {
        a0 a0Var = a0.FULL;
        j$.time.temporal.r rVar = this.a;
        a0 a0Var2 = this.b;
        if (a0Var2 == a0Var) {
            return "Text(" + rVar + ")";
        }
        return "Text(" + rVar + "," + a0Var2 + ")";
    }
}
