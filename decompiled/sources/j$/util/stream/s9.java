package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class s9 extends t9 {
    @Override // j$.util.stream.y9
    public final Spliterator b(Spliterator spliterator) {
        return new s9((Spliterator.OfInt) spliterator, this);
    }

    @Override // j$.util.stream.t9, j$.util.Spliterator.OfPrimitive
    public final /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        tryAdvance((IntConsumer) obj);
        return false;
    }

    @Override // j$.util.Spliterator.OfInt
    public final boolean tryAdvance(IntConsumer intConsumer) {
        if (this.c && a() && ((Spliterator.OfInt) this.a).tryAdvance((IntConsumer) this)) {
            IntPredicate intPredicate = null;
            intPredicate.test(this.e);
            throw null;
        }
        this.c = false;
        return false;
    }

    @Override // j$.util.stream.y9, j$.util.Spliterator
    public final Spliterator.OfInt trySplit() {
        if (this.b.get()) {
            return null;
        }
        return (Spliterator.OfInt) super.trySplit();
    }
}
