package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class n9 extends b {
    public final a j;
    public final IntFunction k;
    public final boolean l;
    public long m;
    public boolean n;
    public volatile boolean o;

    @Override // j$.util.stream.b
    public final void f() {
        this.i = true;
        if (this.l && this.o) {
            d(w3.f0(this.j.C0()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCompletion(java.util.concurrent.CountedCompleter r9) {
        /*
            r8 = this;
            j$.util.stream.d r0 = r8.d
            if (r0 != 0) goto L6
            goto L8b
        L6:
            j$.util.stream.n9 r0 = (j$.util.stream.n9) r0
            boolean r0 = r0.n
            j$.util.stream.d r1 = r8.e
            j$.util.stream.n9 r1 = (j$.util.stream.n9) r1
            boolean r1 = r1.n
            r0 = r0 | r1
            r8.n = r0
            boolean r0 = r8.l
            r1 = 0
            if (r0 == 0) goto L2a
            boolean r0 = r8.i
            if (r0 == 0) goto L2a
            r8.m = r1
            j$.util.stream.a r0 = r8.j
            j$.util.stream.d7 r0 = r0.C0()
            j$.util.stream.w2 r0 = j$.util.stream.w3.f0(r0)
            goto L88
        L2a:
            boolean r0 = r8.l
            if (r0 == 0) goto L41
            j$.util.stream.d r0 = r8.d
            j$.util.stream.n9 r0 = (j$.util.stream.n9) r0
            boolean r3 = r0.n
            if (r3 == 0) goto L41
            long r1 = r0.m
            r8.m = r1
            java.lang.Object r0 = r0.i()
            j$.util.stream.d2 r0 = (j$.util.stream.d2) r0
            goto L88
        L41:
            j$.util.stream.d r0 = r8.d
            j$.util.stream.n9 r0 = (j$.util.stream.n9) r0
            long r3 = r0.m
            j$.util.stream.d r5 = r8.e
            j$.util.stream.n9 r5 = (j$.util.stream.n9) r5
            long r6 = r5.m
            long r3 = r3 + r6
            r8.m = r3
            long r3 = r0.m
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 != 0) goto L5d
            java.lang.Object r0 = r5.i()
            j$.util.stream.d2 r0 = (j$.util.stream.d2) r0
            goto L88
        L5d:
            long r3 = r5.m
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 != 0) goto L6a
            java.lang.Object r0 = r0.i()
            j$.util.stream.d2 r0 = (j$.util.stream.d2) r0
            goto L88
        L6a:
            j$.util.stream.a r0 = r8.j
            j$.util.stream.d7 r0 = r0.C0()
            j$.util.stream.d r1 = r8.d
            j$.util.stream.n9 r1 = (j$.util.stream.n9) r1
            java.lang.Object r1 = r1.i()
            j$.util.stream.d2 r1 = (j$.util.stream.d2) r1
            j$.util.stream.d r2 = r8.e
            j$.util.stream.n9 r2 = (j$.util.stream.n9) r2
            java.lang.Object r2 = r2.i()
            j$.util.stream.d2 r2 = (j$.util.stream.d2) r2
            j$.util.stream.f2 r0 = j$.util.stream.w3.b0(r0, r1, r2)
        L88:
            r8.d(r0)
        L8b:
            r0 = 1
            r8.o = r0
            super.onCompletion(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.n9.onCompletion(java.util.concurrent.CountedCompleter):void");
    }

    public n9(a aVar, w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        super(w3Var, spliterator);
        this.j = aVar;
        this.k = intFunction;
        this.l = c7.ORDERED.o(((a) w3Var).m);
    }

    public n9(n9 n9Var, Spliterator spliterator) {
        super(n9Var, spliterator);
        this.j = n9Var.j;
        this.k = n9Var.k;
        this.l = n9Var.l;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new n9(this, spliterator);
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return w3.f0(this.j.C0());
    }

    @Override // j$.util.stream.d
    public final Object a() {
        v1 v1VarS0 = this.a.s0(-1L, this.k);
        o5 o5VarH0 = this.j.H0(((a) this.a).m, v1VarS0);
        w3 w3Var = this.a;
        boolean zD0 = w3Var.d0(this.b, w3Var.w0(o5VarH0));
        this.n = zD0;
        if (zD0) {
            g();
        }
        d2 d2VarBuild = v1VarS0.build();
        this.m = d2VarBuild.count();
        return d2VarBuild;
    }
}
