package j$.util.stream;

import j$.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class c5 extends k5 {
    public boolean b;
    public final j$.util.d0 c;
    public final /* synthetic */ v d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c5(v vVar, o5 o5Var) {
        super(o5Var);
        this.d = vVar;
        o5 o5Var2 = this.a;
        Objects.requireNonNull(o5Var2);
        this.c = new j$.util.d0(o5Var2, 1);
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(-1L);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.Spliterator$OfDouble] */
    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        DoubleStream doubleStream = (DoubleStream) ((j$.util.q) this.d.u).apply(obj);
        if (doubleStream != null) {
            try {
                boolean z = this.b;
                j$.util.d0 d0Var = this.c;
                if (!z) {
                    doubleStream.sequential().forEach(d0Var);
                } else {
                    ?? Spliterator = doubleStream.sequential().spliterator();
                    while (!this.a.e() && Spliterator.tryAdvance(d0Var)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    doubleStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (doubleStream != null) {
            doubleStream.close();
        }
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public final boolean e() {
        this.b = true;
        return this.a.e();
    }
}
