package j$.util.stream;

import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class u4 extends x4 implements m5 {
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

    @Override // j$.util.stream.s4, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.b);
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        this.b += ((x4) r4Var).b;
    }

    @Override // j$.util.stream.x4, j$.util.stream.o5
    public final void accept(int i) {
        this.b++;
    }
}
