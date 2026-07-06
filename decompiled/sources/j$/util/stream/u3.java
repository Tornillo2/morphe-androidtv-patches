package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class u3 extends v3 {
    public final Object[] c;

    public u3(d2 d2Var, Object[] objArr) {
        super(d2Var);
        this.c = objArr;
    }

    public u3(u3 u3Var, d2 d2Var, int i) {
        super(u3Var, d2Var, i);
        this.c = u3Var.c;
    }

    @Override // j$.util.stream.v3
    public final v3 b(int i, int i2) {
        return new u3(this, this.a.a(i), i2);
    }

    @Override // j$.util.stream.v3
    public final void a() {
        this.a.k(this.c, this.b);
    }
}
