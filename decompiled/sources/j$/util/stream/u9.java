package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class u9 extends w9 {
    @Override // j$.util.stream.y9
    public final Spliterator b(Spliterator spliterator) {
        return new u9((Spliterator.OfLong) spliterator, this);
    }

    @Override // j$.util.Spliterator.OfLong
    public final boolean tryAdvance(LongConsumer longConsumer) {
        boolean z = this.c;
        Spliterator spliterator = this.a;
        if (z) {
            this.c = false;
            boolean zTryAdvance = ((Spliterator.OfLong) spliterator).tryAdvance((LongConsumer) this);
            if (zTryAdvance && a()) {
                LongPredicate longPredicate = null;
                longPredicate.test(this.e);
                throw null;
            }
            if (zTryAdvance) {
                longConsumer.accept(this.e);
            }
            return zTryAdvance;
        }
        return ((Spliterator.OfLong) spliterator).tryAdvance(longConsumer);
    }
}
