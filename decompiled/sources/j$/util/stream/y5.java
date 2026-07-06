package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class y5 extends b {
    public final a j;
    public final IntFunction k;
    public final long l;
    public final long m;
    public long n;
    public volatile boolean o;

    @Override // j$.util.stream.b
    public final void f() {
        this.i = true;
        if (this.o) {
            d(w3.f0(this.j.C0()));
        }
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        y5 y5Var;
        d2 d2VarF0;
        d dVar = this.d;
        if (dVar != null) {
            this.n = ((y5) dVar).n + ((y5) this.e).n;
            if (this.i) {
                this.n = 0L;
                d2VarF0 = w3.f0(this.j.C0());
            } else {
                d2VarF0 = this.n == 0 ? w3.f0(this.j.C0()) : ((y5) this.d).n == 0 ? (d2) ((y5) this.e).i() : w3.b0(this.j.C0(), (d2) ((y5) this.d).i(), (d2) ((y5) this.e).i());
            }
            d2 d2VarI = d2VarF0;
            if (b()) {
                d2VarI = d2VarI.i(this.l, this.m >= 0 ? Math.min(d2VarI.count(), this.l + this.m) : this.n, this.k);
            }
            d(d2VarI);
            this.o = true;
        }
        if (this.m >= 0 && !b()) {
            long j = this.l + this.m;
            long j2 = this.o ? this.n : j(j);
            if (j2 >= j) {
                g();
            } else {
                y5 y5Var2 = (y5) ((d) getCompleter());
                Object obj = this;
                while (true) {
                    if (y5Var2 == null) {
                        if (j2 >= j) {
                            break;
                        }
                    } else {
                        if (obj == y5Var2.e && (y5Var = (y5) y5Var2.d) != null) {
                            long j3 = y5Var.j(j) + j2;
                            if (j3 >= j) {
                                break;
                            } else {
                                j2 = j3;
                            }
                        }
                        obj = y5Var2;
                        y5Var2 = (y5) ((d) y5Var2.getCompleter());
                    }
                }
                g();
            }
        }
        super.onCompletion(countedCompleter);
    }

    public y5(a aVar, w3 w3Var, Spliterator spliterator, IntFunction intFunction, long j, long j2) {
        super(w3Var, spliterator);
        this.j = aVar;
        this.k = intFunction;
        this.l = j;
        this.m = j2;
    }

    public y5(y5 y5Var, Spliterator spliterator) {
        super(y5Var, spliterator);
        this.j = y5Var.j;
        this.k = y5Var.k;
        this.l = y5Var.l;
        this.m = y5Var.m;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new y5(this, spliterator);
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return w3.f0(this.j.C0());
    }

    @Override // j$.util.stream.d
    public final Object a() {
        if (b()) {
            c7 c7Var = c7.SIZED;
            a aVar = this.j;
            int i = aVar.j;
            int i2 = c7Var.e;
            v1 v1VarS0 = this.j.s0((i & i2) == i2 ? aVar.h0(this.b) : -1L, this.k);
            o5 o5VarH0 = this.j.H0(((a) this.a).m, v1VarS0);
            w3 w3Var = this.a;
            w3Var.d0(this.b, w3Var.w0(o5VarH0));
            return v1VarS0.build();
        }
        v1 v1VarS02 = this.j.s0(-1L, this.k);
        if (this.l == 0) {
            o5 o5VarH02 = this.j.H0(((a) this.a).m, v1VarS02);
            w3 w3Var2 = this.a;
            w3Var2.d0(this.b, w3Var2.w0(o5VarH02));
        } else {
            this.a.v0(this.b, v1VarS02);
        }
        d2 d2VarBuild = v1VarS02.build();
        this.n = d2VarBuild.count();
        this.o = true;
        this.b = null;
        return d2VarBuild;
    }

    public final long j(long j) {
        if (this.o) {
            return this.n;
        }
        y5 y5Var = (y5) this.d;
        y5 y5Var2 = (y5) this.e;
        if (y5Var == null || y5Var2 == null) {
            return this.n;
        }
        long j2 = y5Var.j(j);
        return j2 >= j ? j2 : y5Var2.j(j) + j2;
    }
}
