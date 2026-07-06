package j$.util.stream;

import j$.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class b5 extends k5 {
    public boolean b;
    public final j$.util.g0 c;
    public final /* synthetic */ s0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b5(s0 s0Var, o5 o5Var) {
        super(o5Var);
        this.d = s0Var;
        o5 o5Var2 = this.a;
        Objects.requireNonNull(o5Var2);
        this.c = new j$.util.g0(o5Var2, 1);
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(-1L);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.Spliterator$OfInt] */
    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        IntStream intStream = (IntStream) ((j$.util.q) this.d.u).apply(obj);
        if (intStream != null) {
            try {
                boolean z = this.b;
                j$.util.g0 g0Var = this.c;
                if (!z) {
                    intStream.sequential().forEach(g0Var);
                } else {
                    ?? Spliterator = intStream.sequential().spliterator();
                    while (!this.a.e() && Spliterator.tryAdvance(g0Var)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    intStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (intStream != null) {
            intStream.close();
        }
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public final boolean e() {
        this.b = true;
        return this.a.e();
    }
}
