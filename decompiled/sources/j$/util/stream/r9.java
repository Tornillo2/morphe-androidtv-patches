package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class r9 extends t9 {
    @Override // j$.util.stream.y9
    public final Spliterator b(Spliterator spliterator) {
        return new r9((Spliterator.OfInt) spliterator, this);
    }

    @Override // j$.util.Spliterator.OfInt
    public final boolean tryAdvance(IntConsumer intConsumer) {
        boolean z = this.c;
        Spliterator spliterator = this.a;
        if (z) {
            this.c = false;
            boolean zTryAdvance = ((Spliterator.OfInt) spliterator).tryAdvance((IntConsumer) this);
            if (zTryAdvance && a()) {
                IntPredicate intPredicate = null;
                intPredicate.test(this.e);
                throw null;
            }
            if (zTryAdvance) {
                intConsumer.accept(this.e);
            }
            return zTryAdvance;
        }
        return ((Spliterator.OfInt) spliterator).tryAdvance(intConsumer);
    }
}
