package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends d {
    public final AtomicReference h;
    public volatile boolean i;

    public abstract Object h();

    public b(w3 w3Var, Spliterator spliterator) {
        super(w3Var, spliterator);
        this.h = new AtomicReference(null);
    }

    public b(b bVar, Spliterator spliterator) {
        super(bVar, spliterator);
        this.h = bVar.h;
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void compute() {
        Object objH;
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.b;
        long jEstimateSize = spliterator.estimateSize();
        long jE = this.c;
        if (jE == 0) {
            jE = d.e(jEstimateSize);
            this.c = jE;
        }
        AtomicReference atomicReference = this.h;
        boolean z = false;
        b bVar = this;
        while (true) {
            objH = atomicReference.get();
            if (objH != null) {
                break;
            }
            boolean z2 = bVar.i;
            if (!z2) {
                CountedCompleter<?> completer = bVar.getCompleter();
                while (true) {
                    b bVar2 = (b) ((d) completer);
                    if (z2 || bVar2 == null) {
                        break;
                    }
                    z2 = bVar2.i;
                    completer = bVar2.getCompleter();
                }
            }
            if (z2) {
                objH = bVar.h();
                break;
            }
            if (jEstimateSize <= jE || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                break;
            }
            b bVar3 = (b) bVar.c(spliteratorTrySplit);
            bVar.d = bVar3;
            b bVar4 = (b) bVar.c(spliterator);
            bVar.e = bVar4;
            bVar.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                bVar = bVar3;
                bVar3 = bVar4;
            } else {
                bVar = bVar4;
            }
            z = !z;
            bVar3.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        objH = bVar.a();
        bVar.d(objH);
        bVar.tryComplete();
    }

    @Override // j$.util.stream.d
    public final void d(Object obj) {
        if (!b()) {
            this.f = obj;
        } else if (obj != null) {
            AtomicReference atomicReference = this.h;
            while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
            }
        }
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final Object getRawResult() {
        return i();
    }

    public final Object i() {
        if (b()) {
            Object obj = this.h.get();
            return obj == null ? h() : obj;
        }
        return this.f;
    }

    public void f() {
        this.i = true;
    }

    public final void g() {
        b bVar = this;
        for (b bVar2 = (b) ((d) getCompleter()); bVar2 != null; bVar2 = (b) ((d) bVar2.getCompleter())) {
            if (bVar2.d == bVar) {
                b bVar3 = (b) bVar2.e;
                if (!bVar3.i) {
                    bVar3.f();
                }
            }
            bVar = bVar2;
        }
    }
}
