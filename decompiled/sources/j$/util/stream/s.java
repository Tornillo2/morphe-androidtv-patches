package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class s extends x0 {
    public final /* synthetic */ int t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s(a aVar, int i, int i2) {
        super(aVar, i);
        this.t = i2;
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new p(this, o5Var, 2);
            case 1:
                return new r0(this, o5Var, 2);
            case 2:
                return o5Var;
            case 3:
                return new r0(this, o5Var, 5);
            default:
                return new a1(this, o5Var, 2);
        }
    }
}
