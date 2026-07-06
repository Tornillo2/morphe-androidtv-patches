package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class r2 extends s6 implements x1, s1 {
    @Override // j$.util.stream.o5
    public final /* synthetic */ void accept(int i) {
        w3.J();
        throw null;
    }

    @Override // j$.util.stream.o5, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        w3.K();
        throw null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.v1
    public final d2 build() {
        return this;
    }

    @Override // j$.util.stream.s1, j$.util.stream.v1
    public final x1 build() {
        return this;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final void end() {
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.S(this, j, j2);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ Object[] m(IntFunction intFunction) {
        return w3.L(this, intFunction);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        w3.D(this, d);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.d2
    public final /* bridge */ /* synthetic */ d2 a(int i) {
        a(i);
        throw null;
    }

    @Override // j$.util.stream.c2, j$.util.stream.d2
    public final c2 a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ void k(Object[] objArr, int i) {
        w3.M(this, (Double[]) objArr, i);
    }

    @Override // j$.util.stream.y6, j$.util.stream.c2
    public final void f(int i, Object obj) {
        super.f(i, (double[]) obj);
    }

    @Override // j$.util.stream.y6, j$.util.stream.c2
    public final void g(Object obj) {
        super.g((DoubleConsumer) obj);
    }

    @Override // j$.util.stream.s6, j$.util.stream.y6, java.lang.Iterable, j$.lang.a, j$.util.Collection
    public final Spliterator.OfPrimitive spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.s6, j$.util.stream.y6, java.lang.Iterable, j$.lang.a, j$.util.Collection
    public final Spliterator spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        clear();
        s(j);
    }

    @Override // j$.util.stream.y6, j$.util.stream.c2
    public final Object b() {
        return (double[]) super.b();
    }
}
