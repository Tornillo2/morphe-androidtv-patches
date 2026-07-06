package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class v9 extends w9 {
    @Override // j$.util.stream.y9
    public final Spliterator b(Spliterator spliterator) {
        return new v9((Spliterator.OfLong) spliterator, this);
    }

    @Override // j$.util.stream.w9, j$.util.Spliterator.OfPrimitive
    public final /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        tryAdvance((LongConsumer) obj);
        return false;
    }

    @Override // j$.util.Spliterator.OfLong
    public final boolean tryAdvance(LongConsumer longConsumer) {
        if (this.c && a() && ((Spliterator.OfLong) this.a).tryAdvance((LongConsumer) this)) {
            LongPredicate longPredicate = null;
            longPredicate.test(this.e);
            throw null;
        }
        this.c = false;
        return false;
    }

    @Override // j$.util.stream.y9, j$.util.Spliterator
    public final Spliterator.OfLong trySplit() {
        if (this.b.get()) {
            return null;
        }
        return (Spliterator.OfLong) super.trySplit();
    }
}
