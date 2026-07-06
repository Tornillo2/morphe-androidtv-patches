package j$.util.stream;

import j$.util.function.DoubleConsumer$CC;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class t4 extends x4 implements l5 {
    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        w3.D(this, d);
    }

    @Override // j$.util.stream.s4, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.b);
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        this.b += ((x4) r4Var).b;
    }

    @Override // j$.util.stream.x4, j$.util.stream.o5
    public final void accept(double d) {
        this.b++;
    }
}
