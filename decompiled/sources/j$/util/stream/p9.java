package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class p9 extends q9 {
    @Override // j$.util.stream.y9
    public final Spliterator b(Spliterator spliterator) {
        return new p9((Spliterator.OfDouble) spliterator, this);
    }

    @Override // j$.util.stream.q9, j$.util.Spliterator.OfPrimitive
    public final /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        tryAdvance((DoubleConsumer) obj);
        return false;
    }

    @Override // j$.util.Spliterator.OfDouble
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        if (this.c && a() && ((Spliterator.OfDouble) this.a).tryAdvance((DoubleConsumer) this)) {
            DoublePredicate doublePredicate = null;
            doublePredicate.test(this.e);
            throw null;
        }
        this.c = false;
        return false;
    }

    @Override // j$.util.stream.y9, j$.util.Spliterator
    public final Spliterator.OfDouble trySplit() {
        if (this.b.get()) {
            return null;
        }
        return (Spliterator.OfDouble) super.trySplit();
    }
}
