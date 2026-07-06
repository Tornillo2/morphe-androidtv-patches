package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f2 implements d2 {
    public final d2 a;
    public final d2 b;
    public final long c;

    @Override // j$.util.stream.d2
    public final int o() {
        return 2;
    }

    public f2(d2 d2Var, d2 d2Var2) {
        this.a = d2Var;
        this.b = d2Var2;
        this.c = d2Var2.count() + d2Var.count();
    }

    @Override // j$.util.stream.d2
    public final d2 a(int i) {
        if (i == 0) {
            return this.a;
        }
        if (i == 1) {
            return this.b;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.d2
    public final long count() {
        return this.c;
    }

    @Override // j$.util.stream.d2
    public /* bridge */ /* synthetic */ c2 a(int i) {
        return (c2) a(i);
    }
}
