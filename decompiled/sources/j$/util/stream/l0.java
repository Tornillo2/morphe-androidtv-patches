package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class l0 extends o0 implements m5 {
    public final IntConsumer b;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        d((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.m5
    public final /* synthetic */ void d(Integer num) {
        w3.F(this, num);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
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

    public l0(IntConsumer intConsumer, boolean z) {
        super(z);
        this.b = intConsumer;
    }

    @Override // j$.util.stream.o0, j$.util.stream.o5
    public final void accept(int i) {
        this.b.accept(i);
    }
}
