package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final class w8 extends e5 implements k9 {
    public final /* synthetic */ int t;
    public final /* synthetic */ Predicate u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w8(g5 g5Var, int i, Predicate predicate, int i2) {
        super(g5Var, i);
        this.t = i2;
        this.u = predicate;
    }

    @Override // j$.util.stream.a
    public final Spliterator F0(a aVar, Spliterator spliterator) {
        switch (this.t) {
            case 0:
                return c7.ORDERED.o(aVar.m) ? E0(aVar, spliterator, new z0(7)).spliterator() : new x9(aVar.x0(spliterator), this.u, 1);
            default:
                return c7.ORDERED.o(aVar.m) ? E0(aVar, spliterator, new z0(7)).spliterator() : new x9(aVar.x0(spliterator), this.u, 0);
        }
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        switch (this.t) {
            case 0:
                return (d2) new n9(this, w3Var, spliterator, intFunction).invoke();
            default:
                return (d2) new m9(this, w3Var, spliterator, intFunction).invoke();
        }
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new k(this, o5Var);
            default:
                return new x8(this, o5Var, false);
        }
    }

    @Override // j$.util.stream.k9
    public l9 h(v1 v1Var, boolean z) {
        return new x8(this, v1Var, z);
    }
}
