package j$.util.stream;

import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class d1 extends g1 {
    public final /* synthetic */ int t;
    public final /* synthetic */ Object u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d1(a aVar, int i, Object obj, int i2) {
        super(aVar, i);
        this.t = i2;
        this.u = obj;
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new c1(this, o5Var);
            case 1:
                return new a1(this, o5Var, 5);
            case 2:
                return new z4(this, o5Var);
            default:
                return new l(this, o5Var, 5);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(h1 h1Var, LongConsumer longConsumer) {
        super(h1Var, 0);
        this.t = 1;
        this.u = longConsumer;
    }
}
