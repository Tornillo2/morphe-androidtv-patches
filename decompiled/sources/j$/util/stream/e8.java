package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class e8 extends g8 implements Spliterator.OfInt, IntConsumer {
    public int f;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.f(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.t(this, consumer);
    }

    @Override // j$.util.stream.j8
    public final Spliterator b(Spliterator spliterator) {
        return new e8((Spliterator.OfInt) spliterator, this);
    }

    @Override // j$.util.stream.g8
    public final void g(Object obj) {
        ((IntConsumer) obj).accept(this.f);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.f = i;
    }

    @Override // j$.util.stream.g8
    public final i7 i(int i) {
        return new g7(i);
    }
}
