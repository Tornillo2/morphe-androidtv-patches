package j$.util.stream;

import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final class l1 extends p1 {
    public final /* synthetic */ q1 c;
    public final /* synthetic */ Predicate d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l1(q1 q1Var, Predicate predicate) {
        super(q1Var);
        this.c = q1Var;
        this.d = predicate;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        if (this.a) {
            return;
        }
        boolean zTest = this.d.test(obj);
        q1 q1Var = this.c;
        if (zTest == q1Var.a) {
            this.a = true;
            this.b = q1Var.b;
        }
    }
}
