package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class k0 extends o0 implements l5 {
    public final DoubleConsumer b;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        w3.D(this, d);
    }

    @Override // j$.util.stream.t8
    public final Object f(a aVar, Spliterator spliterator) {
        aVar.v0(spliterator, this);
        return null;
    }

    @Override // j$.util.stream.t8
    public final /* bridge */ /* synthetic */ Object i(w3 w3Var, Spliterator spliterator) {
        a(w3Var, spliterator);
        return null;
    }

    public k0(DoubleConsumer doubleConsumer, boolean z) {
        super(z);
        this.b = doubleConsumer;
    }

    @Override // j$.util.stream.o0, j$.util.stream.o5
    public final void accept(double d) {
        this.b.accept(d);
    }
}
