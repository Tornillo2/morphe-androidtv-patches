package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f8 extends g8 implements Spliterator.OfLong, LongConsumer {
    public long f;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.g(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.u(this, consumer);
    }

    @Override // j$.util.stream.j8
    public final Spliterator b(Spliterator spliterator) {
        return new f8((Spliterator.OfLong) spliterator, this);
    }

    @Override // j$.util.stream.g8
    public final void g(Object obj) {
        ((LongConsumer) obj).accept(this.f);
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.f = j;
    }

    @Override // j$.util.stream.g8
    public final i7 i(int i) {
        return new h7(i);
    }
}
