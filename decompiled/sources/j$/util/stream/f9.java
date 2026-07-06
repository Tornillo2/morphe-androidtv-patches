package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class f9 extends f1 implements k9 {
    @Override // j$.util.stream.a
    public final Spliterator F0(a aVar, Spliterator spliterator) {
        return c7.ORDERED.o(aVar.m) ? E0(aVar, spliterator, new z0(24)).spliterator() : new u9((Spliterator.OfLong) aVar.x0(spliterator));
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        return (d2) new m9(this, w3Var, spliterator, intFunction).invoke();
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        return new e9(this, o5Var, false);
    }

    @Override // j$.util.stream.k9
    public final l9 h(v1 v1Var, boolean z) {
        return new e9(this, v1Var, z);
    }
}
