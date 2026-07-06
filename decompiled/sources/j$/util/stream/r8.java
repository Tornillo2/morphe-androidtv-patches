package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class r8 implements Spliterator.OfInt {
    public int a;
    public final int b;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 17749;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.f(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return null;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return Spliterator.CC.$default$getExactSizeIfKnown(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.t(this, consumer);
    }

    public r8(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        int i = this.a;
        if (i >= this.b) {
            return false;
        }
        this.a = i + 1;
        intConsumer.accept(i);
        return true;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        int i = this.b;
        this.a = i;
        for (int i2 = this.a; i2 < i; i2++) {
            intConsumer.accept(i2);
        }
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return (((long) this.b) - ((long) this.a)) + ((long) 0);
    }

    @Override // j$.util.Spliterator
    public final Spliterator.OfInt trySplit() {
        long jEstimateSize = estimateSize();
        if (jEstimateSize <= 1) {
            return null;
        }
        int i = this.a;
        int i2 = ((int) (jEstimateSize / ((long) (jEstimateSize < 16777216 ? 2 : 8)))) + i;
        this.a = i2;
        return new r8(i, i2);
    }
}
