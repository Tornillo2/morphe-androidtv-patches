package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public final class q0 extends CountedCompleter {
    public Spliterator a;
    public final o5 b;
    public final w3 c;
    public long d;

    public q0(w3 w3Var, Spliterator spliterator, o5 o5Var) {
        super(null);
        this.b = o5Var;
        this.c = w3Var;
        this.a = spliterator;
        this.d = 0L;
    }

    public q0(q0 q0Var, Spliterator spliterator) {
        super(q0Var);
        this.a = spliterator;
        this.b = q0Var.b;
        this.d = q0Var.d;
        this.c = q0Var.c;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.a;
        long jEstimateSize = spliterator.estimateSize();
        long jE = this.d;
        if (jE == 0) {
            jE = d.e(jEstimateSize);
            this.d = jE;
        }
        boolean zO = c7.SHORT_CIRCUIT.o(((a) this.c).m);
        o5 o5Var = this.b;
        boolean z = false;
        q0 q0Var = this;
        while (true) {
            if (zO && o5Var.e()) {
                break;
            }
            if (jEstimateSize <= jE || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                break;
            }
            q0 q0Var2 = new q0(q0Var, spliteratorTrySplit);
            q0Var.addToPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
            } else {
                q0 q0Var3 = q0Var;
                q0Var = q0Var2;
                q0Var2 = q0Var3;
            }
            z = !z;
            q0Var.fork();
            q0Var = q0Var2;
            jEstimateSize = spliterator.estimateSize();
        }
        q0Var.c.c0(spliterator, o5Var);
        q0Var.a = null;
        q0Var.propagateCompletion();
    }
}
