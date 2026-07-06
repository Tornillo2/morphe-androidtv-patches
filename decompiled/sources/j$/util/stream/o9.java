package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class o9 extends q9 {
    @Override // j$.util.stream.y9
    public final Spliterator b(Spliterator spliterator) {
        return new o9((Spliterator.OfDouble) spliterator, this);
    }

    @Override // j$.util.Spliterator.OfDouble
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        boolean z = this.c;
        Spliterator spliterator = this.a;
        if (z) {
            this.c = false;
            boolean zTryAdvance = ((Spliterator.OfDouble) spliterator).tryAdvance((DoubleConsumer) this);
            if (zTryAdvance && a()) {
                DoublePredicate doublePredicate = null;
                doublePredicate.test(this.e);
                throw null;
            }
            if (zTryAdvance) {
                doubleConsumer.accept(this.e);
            }
            return zTryAdvance;
        }
        return ((Spliterator.OfDouble) spliterator).tryAdvance(doubleConsumer);
    }
}
