package j$.util.stream;

import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class v extends y {
    public final /* synthetic */ int t;
    public final /* synthetic */ Object u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(a aVar, int i, Object obj, int i2) {
        super(aVar, i);
        this.t = i2;
        this.u = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(z zVar, DoubleConsumer doubleConsumer) {
        super(zVar, 0);
        this.t = 1;
        this.u = doubleConsumer;
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new u(this, o5Var);
            case 1:
                return new p(this, o5Var, 5);
            case 2:
                return new l(this, o5Var, 6);
            default:
                return new c5(this, o5Var);
        }
    }
}
