package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class v0 extends y0 {
    @Override // j$.util.stream.a
    public final Spliterator D0(Supplier supplier) {
        return new m7(supplier);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final IntStream sequential() {
        this.h.s = false;
        return this;
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final IntStream parallel() {
        this.h.s = true;
        return this;
    }

    @Override // j$.util.stream.y0, j$.util.stream.IntStream
    public final void forEach(IntConsumer intConsumer) {
        if (this.h.s) {
            super.forEach(intConsumer);
        } else {
            y0.L0(J0()).forEachRemaining(intConsumer);
        }
    }

    @Override // j$.util.stream.y0, j$.util.stream.IntStream
    public final void forEachOrdered(IntConsumer intConsumer) {
        if (this.h.s) {
            super.forEachOrdered(intConsumer);
        } else {
            y0.L0(J0()).forEachRemaining(intConsumer);
        }
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream unordered() {
        return !c7.ORDERED.o(this.m) ? this : new s(this, c7.r, 2);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final /* bridge */ /* synthetic */ Spliterator<Integer> spliterator() {
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
