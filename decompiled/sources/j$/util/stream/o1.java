package j$.util.stream;

import j$.util.function.DoubleConsumer$CC;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class o1 extends p1 implements l5 {
    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        w3.D(this, d);
    }

    @Override // j$.util.stream.p1, j$.util.stream.o5
    public final void accept(double d) {
        if (this.a) {
            return;
        }
        DoublePredicate doublePredicate = null;
        doublePredicate.test(d);
        throw null;
    }
}
