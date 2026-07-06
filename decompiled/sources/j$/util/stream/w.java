package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class w extends z {
    @Override // j$.util.stream.a
    public final Spliterator D0(Supplier supplier) {
        return new l7(supplier);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final DoubleStream sequential() {
        this.h.s = false;
        return this;
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final DoubleStream parallel() {
        this.h.s = true;
        return this;
    }

    @Override // j$.util.stream.z, j$.util.stream.DoubleStream
    public final void forEach(DoubleConsumer doubleConsumer) {
        if (this.h.s) {
            super.forEach(doubleConsumer);
        } else {
            z.L0(J0()).forEachRemaining(doubleConsumer);
        }
    }

    @Override // j$.util.stream.z, j$.util.stream.DoubleStream
    public final void forEachOrdered(DoubleConsumer doubleConsumer) {
        if (this.h.s) {
            super.forEachOrdered(doubleConsumer);
        } else {
            z.L0(J0()).forEachRemaining(doubleConsumer);
        }
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream unordered() {
        return !c7.ORDERED.o(this.m) ? this : new r(this, c7.r, 1);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final /* bridge */ /* synthetic */ Spliterator<Double> spliterator() {
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
