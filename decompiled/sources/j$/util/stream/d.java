package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends CountedCompleter {
    public static final int g = ForkJoinPool.getCommonPoolParallelism() << 2;
    public final w3 a;
    public Spliterator b;
    public long c;
    public d d;
    public d e;
    public Object f;

    public abstract Object a();

    public abstract d c(Spliterator spliterator);

    public d(w3 w3Var, Spliterator spliterator) {
        super(null);
        this.a = w3Var;
        this.b = spliterator;
        this.c = 0L;
    }

    public d(d dVar, Spliterator spliterator) {
        super(dVar);
        this.b = spliterator;
        this.a = dVar.a;
        this.c = dVar.c;
    }

    public static long e(long j) {
        long j2 = j / ((long) g);
        if (j2 > 0) {
            return j2;
        }
        return 1L;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return this.f;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    public void d(Object obj) {
        this.f = obj;
    }

    public final boolean b() {
        return ((d) getCompleter()) == null;
    }

    @Override // java.util.concurrent.CountedCompleter
    public void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.b;
        long jEstimateSize = spliterator.estimateSize();
        long jE = this.c;
        if (jE == 0) {
            jE = e(jEstimateSize);
            this.c = jE;
        }
        boolean z = false;
        d dVar = this;
        while (jEstimateSize > jE && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            d dVarC = dVar.c(spliteratorTrySplit);
            dVar.d = dVarC;
            d dVarC2 = dVar.c(spliterator);
            dVar.e = dVarC2;
            dVar.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                dVar = dVarC;
                dVarC = dVarC2;
            } else {
                dVar = dVarC2;
            }
            z = !z;
            dVarC.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        dVar.d(dVar.a());
        dVar.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter countedCompleter) {
        this.b = null;
        this.e = null;
        this.d = null;
    }
}
