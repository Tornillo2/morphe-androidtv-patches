package j$.util.stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class b4 extends w3 {
    public final /* synthetic */ int h;
    public final /* synthetic */ Object i;
    public final /* synthetic */ Object j;
    public final /* synthetic */ Object k;

    public /* synthetic */ b4(d7 d7Var, Object obj, Object obj2, Object obj3, int i) {
        this.h = i;
        this.j = obj;
        this.k = obj2;
        this.i = obj3;
    }

    @Override // j$.util.stream.w3
    public final r4 u0() {
        switch (this.h) {
            case 0:
                return new y3((Supplier) this.i, (ObjLongConsumer) this.k, (o) this.j);
            case 1:
                return new e4((Supplier) this.i, (ObjDoubleConsumer) this.k, (o) this.j);
            case 2:
                return new g4(this.i, (BiFunction) this.k, (BinaryOperator) this.j);
            case 3:
                return new k4((Supplier) this.i, (BiConsumer) this.k, (BiConsumer) this.j);
            default:
                return new o4((Supplier) this.i, (ObjIntConsumer) this.k, (o) this.j);
        }
    }
}
