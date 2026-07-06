package j$.util.stream;

import j$.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class c1 extends j5 {
    public boolean b;
    public final j$.util.j0 c;
    public final /* synthetic */ d1 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c1(d1 d1Var, o5 o5Var) {
        super(o5Var);
        this.d = d1Var;
        o5 o5Var2 = this.a;
        Objects.requireNonNull(o5Var2);
        this.c = new j$.util.j0(o5Var2, 1);
    }

    @Override // j$.util.stream.j5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(-1L);
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [j$.util.Spliterator$OfLong] */
    @Override // j$.util.stream.n5, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        LongStream longStream = (LongStream) ((j$.util.q) this.d.u).apply(j);
        if (longStream != null) {
            try {
                boolean z = this.b;
                j$.util.j0 j0Var = this.c;
                if (!z) {
                    longStream.sequential().forEach(j0Var);
                } else {
                    ?? Spliterator = longStream.sequential().spliterator();
                    while (!this.a.e() && Spliterator.tryAdvance(j0Var)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    longStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (longStream != null) {
            longStream.close();
        }
    }

    @Override // j$.util.stream.j5, j$.util.stream.o5
    public final boolean e() {
        this.b = true;
        return this.a.e();
    }
}
