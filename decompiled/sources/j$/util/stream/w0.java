package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class w0 extends y0 {
    @Override // j$.util.stream.a
    public final boolean G0() {
        return true;
    }

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

    @Override // j$.util.stream.BaseStream
    public final BaseStream unordered() {
        return !c7.ORDERED.o(this.m) ? this : new s(this, c7.r, 2);
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final /* bridge */ /* synthetic */ Spliterator<Integer> spliterator() {
        return spliterator();
    }
}
