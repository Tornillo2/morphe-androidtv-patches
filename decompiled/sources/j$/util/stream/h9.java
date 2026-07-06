package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class h9 extends x {
    @Override // j$.util.stream.a
    public final Spliterator F0(a aVar, Spliterator spliterator) {
        return c7.ORDERED.o(aVar.m) ? E0(aVar, spliterator, new z0(25)).spliterator() : new p9((Spliterator.OfDouble) aVar.x0(spliterator));
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        return (d2) new n9(this, w3Var, spliterator, intFunction).invoke();
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        return new g9(this, o5Var);
    }
}
