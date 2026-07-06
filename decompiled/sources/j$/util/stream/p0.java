package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public final class p0 extends CountedCompleter {
    public final w3 a;
    public Spliterator b;
    public final long c;
    public final ConcurrentHashMap d;
    public final o0 e;
    public final p0 f;
    public d2 g;

    public p0(w3 w3Var, Spliterator spliterator, o0 o0Var) {
        super(null);
        this.a = w3Var;
        this.b = spliterator;
        this.c = d.e(spliterator.estimateSize());
        this.d = new ConcurrentHashMap(Math.max(16, d.g << 1));
        this.e = o0Var;
        this.f = null;
    }

    public p0(p0 p0Var, Spliterator spliterator, p0 p0Var2) {
        super(p0Var);
        this.a = p0Var.a;
        this.b = spliterator;
        this.c = p0Var.c;
        this.d = p0Var.d;
        this.e = p0Var.e;
        this.f = p0Var2;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.b;
        long j = this.c;
        boolean z = false;
        p0 p0Var = this;
        while (spliterator.estimateSize() > j && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            p0 p0Var2 = new p0(p0Var, spliteratorTrySplit, p0Var.f);
            p0 p0Var3 = new p0(p0Var, spliterator, p0Var2);
            p0Var.addToPendingCount(1);
            p0Var3.addToPendingCount(1);
            p0Var.d.put(p0Var2, p0Var3);
            if (p0Var.f != null) {
                p0Var2.addToPendingCount(1);
                if (p0Var.d.replace(p0Var.f, p0Var, p0Var2)) {
                    p0Var.addToPendingCount(-1);
                } else {
                    p0Var2.addToPendingCount(-1);
                }
            }
            if (z) {
                spliterator = spliteratorTrySplit;
                p0Var = p0Var2;
                p0Var2 = p0Var3;
            } else {
                p0Var = p0Var3;
            }
            z = !z;
            p0Var2.fork();
        }
        if (p0Var.getPendingCount() > 0) {
            n nVar = new n(13);
            w3 w3Var = p0Var.a;
            v1 v1VarS0 = w3Var.s0(w3Var.h0(spliterator), nVar);
            p0Var.a.v0(spliterator, v1VarS0);
            p0Var.g = v1VarS0.build();
            p0Var.b = null;
        }
        p0Var.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d2 d2Var = this.g;
        if (d2Var != null) {
            d2Var.forEach(this.e);
            this.g = null;
        } else {
            Spliterator spliterator = this.b;
            if (spliterator != null) {
                this.a.v0(spliterator, this.e);
                this.b = null;
            }
        }
        p0 p0Var = (p0) this.d.remove(this);
        if (p0Var != null) {
            p0Var.tryComplete();
        }
    }
}
