package j$.util.stream;

import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k1 implements Supplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ q1 b;

    public /* synthetic */ k1(q1 q1Var, int i) {
        this.a = i;
        this.b = q1Var;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.a) {
            case 0:
                return new n1(this.b);
            case 1:
                return new m1(this.b);
            default:
                return new o1(this.b);
        }
    }
}
