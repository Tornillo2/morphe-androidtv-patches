package j$.util.stream;

import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class y8 extends i5 {
    public final boolean b;

    public y8(z8 z8Var, o5 o5Var) {
        super(o5Var);
        this.b = true;
    }

    @Override // j$.util.stream.i5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(-1L);
    }

    @Override // j$.util.stream.m5, j$.util.stream.o5
    public final void accept(int i) {
        if (this.b) {
            IntPredicate intPredicate = null;
            intPredicate.test(i);
            throw null;
        }
    }

    @Override // j$.util.stream.i5, j$.util.stream.o5
    public final boolean e() {
        return !this.b || this.a.e();
    }
}
