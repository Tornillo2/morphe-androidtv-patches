package j$.util.stream;

import java.util.function.LongPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class c9 extends j5 {
    public final boolean b;

    public c9(d9 d9Var, o5 o5Var) {
        super(o5Var);
        this.b = true;
    }

    @Override // j$.util.stream.j5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(-1L);
    }

    @Override // j$.util.stream.n5, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        if (this.b) {
            LongPredicate longPredicate = null;
            longPredicate.test(j);
            throw null;
        }
    }

    @Override // j$.util.stream.j5, j$.util.stream.o5
    public final boolean e() {
        return !this.b || this.a.e();
    }
}
