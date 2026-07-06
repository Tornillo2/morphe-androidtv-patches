package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class k6 extends e5 {
    public final boolean t;
    public final Comparator u;

    public k6(g5 g5Var) {
        super(g5Var, c7.q | c7.o);
        this.t = true;
        this.u = j$.util.e.INSTANCE;
    }

    public k6(g5 g5Var, Comparator comparator) {
        super(g5Var, c7.q | c7.p);
        this.t = false;
        this.u = (Comparator) Objects.requireNonNull(comparator);
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        Objects.requireNonNull(o5Var);
        if (c7.SORTED.o(i) && this.t) {
            return o5Var;
        }
        if (c7.SIZED.o(i)) {
            return new p6(o5Var, this.u);
        }
        return new l6(o5Var, this.u);
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        if (c7.SORTED.o(((a) w3Var).m) && this.t) {
            return w3Var.g0(spliterator, false, intFunction);
        }
        Object[] objArrM = w3Var.g0(spliterator, true, intFunction).m(intFunction);
        Arrays.sort(objArrM, this.u);
        return new g2(objArrM);
    }
}
