package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class p2 implements x1 {
    public final double[] a;
    public int b;

    @Override // j$.util.stream.d2
    public final /* synthetic */ void forEach(Consumer consumer) {
        w3.P(this, consumer);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.S(this, j, j2);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ Object[] m(IntFunction intFunction) {
        return w3.L(this, intFunction);
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

    @Override // j$.util.stream.c2
    public final void f(int i, Object obj) {
        int i2 = this.b;
        System.arraycopy(this.a, 0, (double[]) obj, i, i2);
    }

    @Override // j$.util.stream.c2
    public final void g(Object obj) {
        DoubleConsumer doubleConsumer = (DoubleConsumer) obj;
        for (int i = 0; i < this.b; i++) {
            doubleConsumer.accept(this.a[i]);
        }
    }

    public p2(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.a = new double[(int) j];
        this.b = 0;
    }

    public p2(double[] dArr) {
        this.a = dArr;
        this.b = dArr.length;
    }

    @Override // j$.util.stream.c2, j$.util.stream.d2
    public final Spliterator.OfPrimitive spliterator() {
        return Spliterators.spliterator(this.a, 0, this.b, 1040);
    }

    @Override // j$.util.stream.d2
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this.a, 0, this.b, 1040);
    }

    @Override // j$.util.stream.c2
    public final Object b() {
        double[] dArr = this.a;
        int length = dArr.length;
        int i = this.b;
        return length == i ? dArr : Arrays.copyOf(dArr, i);
    }

    @Override // j$.util.stream.d2
    public final long count() {
        return this.b;
    }

    public String toString() {
        double[] dArr = this.a;
        return String.format("DoubleArrayNode[%d][%s]", Integer.valueOf(dArr.length - this.b), Arrays.toString(dArr));
    }
}
