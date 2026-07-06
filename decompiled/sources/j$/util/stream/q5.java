package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class q5 extends e5 {
    public final /* synthetic */ long t;
    public final /* synthetic */ long u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q5(g5 g5Var, int i, long j, long j2) {
        super(g5Var, i);
        this.t = j;
        this.u = j2;
    }

    @Override // j$.util.stream.a
    public final Spliterator F0(a aVar, Spliterator spliterator) {
        long jH0 = aVar.h0(spliterator);
        if (jH0 > 0 && spliterator.hasCharacteristics(16384)) {
            Spliterator spliteratorX0 = aVar.x0(spliterator);
            long j = this.t;
            return new b8(spliteratorX0, j, z5.c(j, this.u));
        }
        if (c7.ORDERED.o(aVar.m)) {
            return ((d2) new y5(this, aVar, spliterator, new z0(7), this.t, this.u).invoke()).spliterator();
        }
        Spliterator spliteratorX02 = aVar.x0(spliterator);
        long j2 = this.t;
        long j3 = this.u;
        if (j2 <= jH0) {
            long jMin = jH0 - j2;
            if (j3 >= 0) {
                jMin = Math.min(j3, jMin);
            }
            j3 = jMin;
            j2 = 0;
        }
        return new h8(spliteratorX02, j2, j3);
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        long jMin;
        long j;
        long jH0 = w3Var.h0(spliterator);
        if (jH0 > 0 && spliterator.hasCharacteristics(16384)) {
            a aVar = (a) w3Var;
            while (aVar.l > 0) {
                aVar = aVar.i;
            }
            return w3.X(w3Var, z5.b(aVar.C0(), spliterator, this.t, this.u), true, intFunction);
        }
        if (!c7.ORDERED.o(((a) w3Var).m)) {
            Spliterator spliteratorX0 = w3Var.x0(spliterator);
            long j2 = this.t;
            long j3 = this.u;
            if (j2 <= jH0) {
                long j4 = jH0 - j2;
                jMin = j3 >= 0 ? Math.min(j3, j4) : j4;
                j = 0;
            } else {
                jMin = j3;
                j = j2;
            }
            return w3.X(this, new h8(spliteratorX0, j, jMin), true, intFunction);
        }
        return (d2) new y5(this, w3Var, spliterator, intFunction, this.t, this.u).invoke();
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        return new p5(this, o5Var);
    }
}
