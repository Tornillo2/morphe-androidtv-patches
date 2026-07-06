package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class n1 extends p1 implements n5 {
    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.n5
    public final /* synthetic */ void l(Long l) {
        w3.H(this, l);
    }

    @Override // j$.util.stream.p1, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        if (this.a) {
            return;
        }
        LongPredicate longPredicate = null;
        longPredicate.test(j);
        throw null;
    }
}
