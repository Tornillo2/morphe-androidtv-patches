package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class m9 extends d {
    public final a h;
    public final IntFunction i;
    public final boolean j;
    public long k;
    public long l;

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d dVar = this.d;
        if (dVar != null) {
            if (this.j) {
                m9 m9Var = (m9) dVar;
                long j = m9Var.l;
                this.l = j;
                if (j == m9Var.k) {
                    this.l = j + ((m9) this.e).l;
                }
            }
            m9 m9Var2 = (m9) dVar;
            long j2 = m9Var2.k;
            m9 m9Var3 = (m9) this.e;
            this.k = j2 + m9Var3.k;
            d2 d2VarB0 = m9Var2.k == 0 ? (d2) m9Var3.f : m9Var3.k == 0 ? (d2) m9Var2.f : w3.b0(this.h.C0(), (d2) ((m9) this.d).f, (d2) ((m9) this.e).f);
            if (b() && this.j) {
                d2VarB0 = d2VarB0.i(this.l, d2VarB0.count(), this.i);
            }
            this.f = d2VarB0;
        }
        super.onCompletion(countedCompleter);
    }

    public m9(a aVar, w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        super(w3Var, spliterator);
        this.h = aVar;
        this.i = intFunction;
        this.j = c7.ORDERED.o(((a) w3Var).m);
    }

    public m9(m9 m9Var, Spliterator spliterator) {
        super(m9Var, spliterator);
        this.h = m9Var.h;
        this.i = m9Var.i;
        this.j = m9Var.j;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new m9(this, spliterator);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    @Override // j$.util.stream.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a() {
        /*
            r5 = this;
            boolean r0 = r5.b()
            if (r0 != 0) goto L1c
            boolean r1 = r5.j
            if (r1 == 0) goto L1c
            j$.util.stream.c7 r1 = j$.util.stream.c7.SIZED
            j$.util.stream.a r2 = r5.h
            int r3 = r2.j
            int r1 = r1.e
            r3 = r3 & r1
            if (r3 != r1) goto L1c
            j$.util.Spliterator r1 = r5.b
            long r1 = r2.h0(r1)
            goto L1e
        L1c:
            r1 = -1
        L1e:
            j$.util.stream.w3 r3 = r5.a
            java.util.function.IntFunction r4 = r5.i
            j$.util.stream.v1 r1 = r3.s0(r1, r4)
            j$.util.stream.a r2 = r5.h
            j$.util.stream.k9 r2 = (j$.util.stream.k9) r2
            boolean r3 = r5.j
            if (r3 == 0) goto L32
            if (r0 != 0) goto L32
            r0 = 1
            goto L33
        L32:
            r0 = 0
        L33:
            j$.util.stream.l9 r0 = r2.h(r1, r0)
            j$.util.stream.w3 r2 = r5.a
            j$.util.Spliterator r3 = r5.b
            r2.v0(r3, r0)
            j$.util.stream.d2 r1 = r1.build()
            long r2 = r1.count()
            r5.k = r2
            long r2 = r0.h()
            r5.l = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.m9.a():java.lang.Object");
    }
}
