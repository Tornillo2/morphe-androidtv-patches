package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class d0 extends h0 implements l5 {
    public static final c0 c;
    public static final c0 d;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.h0, j$.util.stream.o5
    public final void accept(double d2) {
        n(Double.valueOf(d2));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return OptionalDouble.of(((Double) this.b).doubleValue());
        }
        return null;
    }

    static {
        d7 d7Var = d7.DOUBLE_VALUE;
        c = new c0(true, d7Var, OptionalDouble.empty(), new n(5), new n(6));
        d = new c0(false, d7Var, OptionalDouble.empty(), new n(5), new n(6));
    }
}
