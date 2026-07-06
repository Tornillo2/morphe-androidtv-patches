package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m0 extends o0 implements n5 {
    public final LongConsumer b;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.n5
    public final /* synthetic */ void l(Long l) {
        w3.H(this, l);
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

    public m0(LongConsumer longConsumer, boolean z) {
        super(z);
        this.b = longConsumer;
    }

    @Override // j$.util.stream.o0, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        this.b.accept(j);
    }
}
