package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class v4 extends x4 implements n5 {
    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.n5
    public final /* synthetic */ void l(Long l) {
        w3.H(this, l);
    }

    @Override // j$.util.stream.s4, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.b);
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        this.b += ((x4) r4Var).b;
    }

    @Override // j$.util.stream.x4, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        this.b++;
    }
}
