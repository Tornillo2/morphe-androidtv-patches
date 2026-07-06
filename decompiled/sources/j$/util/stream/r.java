package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends y {
    public final /* synthetic */ int t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(a aVar, int i, int i2) {
        super(aVar, i);
        this.t = i2;
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new p(this, o5Var, 1);
            case 1:
                return o5Var;
            case 2:
                return new p(this, o5Var, 4);
            case 3:
                return new t0(1, o5Var);
            case 4:
                return new r0(this, o5Var, 4);
            case 5:
                return new b1(o5Var);
            default:
                return new a1(this, o5Var, 3);
        }
    }
}
