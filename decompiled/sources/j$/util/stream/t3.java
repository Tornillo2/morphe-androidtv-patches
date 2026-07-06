package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public class t3 extends v3 {
    public final Object c;

    public t3(c2 c2Var, Object obj) {
        super(c2Var);
        this.c = obj;
    }

    public t3(t3 t3Var, c2 c2Var, int i) {
        super(t3Var, c2Var, i);
        this.c = t3Var.c;
    }

    @Override // j$.util.stream.v3
    public final v3 b(int i, int i2) {
        return new t3(this, ((c2) this.a).a(i), i2);
    }

    @Override // j$.util.stream.v3
    public final void a() {
        ((c2) this.a).f(this.b, this.c);
    }
}
