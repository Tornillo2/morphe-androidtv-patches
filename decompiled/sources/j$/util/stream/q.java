package j$.util.stream;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class q extends f5 {
    public final /* synthetic */ int t;
    public final /* synthetic */ Object u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(a aVar, int i, Object obj, int i2) {
        super(aVar, i);
        this.t = i2;
        this.u = obj;
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new p(this, o5Var, 0);
            case 1:
                return new r0(this, o5Var, 0);
            case 2:
                return new a1(this, o5Var, 0);
            case 3:
                return new l(this, o5Var, 1);
            case 4:
                return new l(this, o5Var, 2);
            case 5:
                return new l(this, o5Var, 3);
            default:
                return new k(this, o5Var);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(g5 g5Var, Consumer consumer) {
        super(g5Var, 0);
        this.t = 3;
        this.u = consumer;
    }
}
