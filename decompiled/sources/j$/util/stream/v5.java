package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class v5 extends h5 {
    public long b;
    public long c;
    public final /* synthetic */ w5 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v5(w5 w5Var, o5 o5Var) {
        super(o5Var);
        this.d = w5Var;
        this.b = w5Var.t;
        long j = w5Var.u;
        this.c = j < 0 ? Long.MAX_VALUE : j;
    }

    @Override // j$.util.stream.h5, j$.util.stream.o5
    public final void c(long j) {
        this.a.c(z5.a(j, this.d.t, this.c));
    }

    @Override // j$.util.stream.l5, j$.util.stream.o5
    public final void accept(double d) {
        long j = this.b;
        if (j == 0) {
            long j2 = this.c;
            if (j2 > 0) {
                this.c = j2 - 1;
                this.a.accept(d);
                return;
            }
            return;
        }
        this.b = j - 1;
    }

    @Override // j$.util.stream.h5, j$.util.stream.o5
    public final boolean e() {
        return this.c == 0 || this.a.e();
    }
}
