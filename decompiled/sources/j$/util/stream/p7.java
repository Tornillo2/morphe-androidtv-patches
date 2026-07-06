package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public class p7 implements Spliterator {
    public final Supplier a;
    public Spliterator b;

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    public p7(Supplier supplier) {
        this.a = supplier;
    }

    public final Spliterator a() {
        if (this.b == null) {
            this.b = (Spliterator) this.a.get();
        }
        return this.b;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        return a().trySplit();
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        return a().tryAdvance(consumer);
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        a().forEachRemaining(consumer);
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return a().estimateSize();
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return a().characteristics();
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return a().getComparator();
    }

    @Override // j$.util.Spliterator
    public final long getExactSizeIfKnown() {
        return a().getExactSizeIfKnown();
    }

    public final String toString() {
        return getClass().getName() + "[" + a() + "]";
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfPrimitive) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) trySplit();
    }
}
