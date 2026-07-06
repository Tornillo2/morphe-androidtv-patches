package j$.util.stream;

import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class g9 extends h5 {
    public final boolean b;

    public g9(h9 h9Var, o5 o5Var) {
        super(o5Var);
        this.b = true;
    }

    @Override // j$.util.stream.h5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(-1L);
    }

    @Override // j$.util.stream.l5, j$.util.stream.o5
    public final void accept(double d) {
        if (this.b) {
            DoublePredicate doublePredicate = null;
            doublePredicate.test(d);
            throw null;
        }
    }

    @Override // j$.util.stream.h5, j$.util.stream.o5
    public final boolean e() {
        return !this.b || this.a.e();
    }
}
