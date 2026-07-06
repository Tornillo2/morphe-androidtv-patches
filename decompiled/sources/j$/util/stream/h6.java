package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class h6 extends x {
    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        Objects.requireNonNull(o5Var);
        return c7.SORTED.o(i) ? o5Var : c7.SIZED.o(i) ? new m6(o5Var) : new e6(o5Var);
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        if (c7.SORTED.o(((a) w3Var).m)) {
            return w3Var.g0(spliterator, false, intFunction);
        }
        double[] dArr = (double[]) ((x1) w3Var.g0(spliterator, true, intFunction)).b();
        Arrays.sort(dArr);
        return new p2(dArr);
    }
}
