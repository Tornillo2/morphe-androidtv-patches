package j$.util.stream;

import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class s0 extends x0 {
    public final /* synthetic */ int t;
    public final /* synthetic */ Object u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s0(a aVar, int i, Object obj, int i2) {
        super(aVar, i);
        this.t = i2;
        this.u = obj;
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        switch (this.t) {
            case 0:
                return new r0(this, o5Var, 1);
            case 1:
                return new u0(this, o5Var);
            case 2:
                return new l(this, o5Var, 4);
            default:
                return new b5(this, o5Var);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(y0 y0Var, IntConsumer intConsumer) {
        super(y0Var, 0);
        this.t = 0;
        this.u = intConsumer;
    }
}
