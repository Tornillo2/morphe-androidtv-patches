package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.LongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class e1 extends h1 {
    @Override // j$.util.stream.a
    public final Spliterator D0(Supplier supplier) {
        return new n7(supplier);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final LongStream sequential() {
        this.h.s = false;
        return this;
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final LongStream parallel() {
        this.h.s = true;
        return this;
    }

    @Override // j$.util.stream.h1, j$.util.stream.LongStream
    public final void forEach(LongConsumer longConsumer) {
        if (this.h.s) {
            super.forEach(longConsumer);
        } else {
            h1.L0(J0()).forEachRemaining(longConsumer);
        }
    }

    @Override // j$.util.stream.h1, j$.util.stream.LongStream
    public final void forEachOrdered(LongConsumer longConsumer) {
        if (this.h.s) {
            super.forEachOrdered(longConsumer);
        } else {
            h1.L0(J0()).forEachRemaining(longConsumer);
        }
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream unordered() {
        return !c7.ORDERED.o(this.m) ? this : new t(this, c7.r, 4);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final /* bridge */ /* synthetic */ Spliterator<Long> spliterator() {
        return spliterator();
    }

    @Override // j$.util.stream.a
    public final boolean G0() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        throw new UnsupportedOperationException();
    }
}
