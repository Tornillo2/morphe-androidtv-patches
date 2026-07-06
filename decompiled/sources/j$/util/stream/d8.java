package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class d8 extends g8 implements Spliterator.OfDouble, DoubleConsumer {
    public double f;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.e(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.s(this, consumer);
    }

    @Override // j$.util.stream.j8
    public final Spliterator b(Spliterator spliterator) {
        return new d8((Spliterator.OfDouble) spliterator, this);
    }

    @Override // j$.util.stream.g8
    public final void g(Object obj) {
        ((DoubleConsumer) obj).accept(this.f);
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.f = d;
    }

    @Override // j$.util.stream.g8
    public final i7 i(int i) {
        return new f7(i);
    }
}
