package j$.util.stream;

import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class z extends a implements DoubleStream {
    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble findAny() {
        return (OptionalDouble) y0(d0.d);
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble findFirst() {
        return (OptionalDouble) y0(d0.c);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream sorted() {
        return new h6(this, c7.q | c7.o);
    }

    public static Spliterator.OfDouble L0(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfDouble) {
            return (Spliterator.OfDouble) spliterator;
        }
        if (v8.a) {
            v8.a(a.class, "using DoubleStream.adapt(Spliterator<Double> s)");
            throw null;
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    public void forEach(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        y0(new k0(doubleConsumer, false));
    }

    public void forEachOrdered(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        y0(new k0(doubleConsumer, true));
    }

    @Override // j$.util.stream.a
    public final d7 C0() {
        return d7.DOUBLE_VALUE;
    }

    @Override // j$.util.stream.a
    public final d2 A0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return w3.Y(aVar, spliterator, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator K0(a aVar, Supplier supplier, boolean z) {
        return new s7(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean B0(Spliterator spliterator, o5 o5Var) {
        DoubleConsumer d0Var;
        boolean zE;
        Spliterator.OfDouble ofDoubleL0 = L0(spliterator);
        if (o5Var instanceof DoubleConsumer) {
            d0Var = (DoubleConsumer) o5Var;
        } else {
            if (v8.a) {
                v8.a(a.class, "using DoubleStream.adapt(Sink<Double> s)");
                throw null;
            }
            Objects.requireNonNull(o5Var);
            d0Var = new j$.util.d0(o5Var, 1);
        }
        do {
            zE = o5Var.e();
            if (zE) {
                break;
            }
        } while (ofDoubleL0.tryAdvance(d0Var));
        return zE;
    }

    @Override // j$.util.stream.w3
    public final v1 s0(long j, IntFunction intFunction) {
        return w3.e0(j);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.Spliterator$OfDouble] */
    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    public final Iterator<Double> iterator2() {
        return Spliterators.iterator((Spliterator.OfDouble) spliterator());
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final Spliterator<Double> spliterator() {
        return L0(super.spliterator());
    }

    @Override // j$.util.stream.DoubleStream
    public final Stream boxed() {
        return new q(this, 0, new j$.time.e(29), 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream e() {
        Objects.requireNonNull(null);
        return new r(this, c7.p | c7.n, 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final Stream mapToObj(DoubleFunction doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return new q(this, c7.p | c7.n, doubleFunction, 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final IntStream z() {
        Objects.requireNonNull(null);
        return new s(this, c7.p | c7.n, 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final LongStream u() {
        Objects.requireNonNull(null);
        return new t(this, c7.p | c7.n, 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream d(j$.util.q qVar) {
        Objects.requireNonNull(qVar);
        return new v(this, c7.p | c7.n | c7.t, qVar, 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream b() {
        Objects.requireNonNull(null);
        return new r(this, c7.t, 2);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream peek(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return new v(this, doubleConsumer);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return z5.e(this, 0L, j);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : z5.e(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream a() {
        int i = z9.a;
        Objects.requireNonNull(null);
        return new h9(this, z9.a);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream c() {
        int i = z9.a;
        Objects.requireNonNull(null);
        return new j9(this, z9.b);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream distinct() {
        return ((g5) boxed()).distinct().mapToDouble(new n(0));
    }

    @Override // j$.util.stream.DoubleStream
    public final double sum() {
        double[] dArr = (double[]) collect(new n(3), new n(4), new j$.time.e(22));
        Set set = Collectors.a;
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        return (Double.isNaN(d) && Double.isInfinite(d2)) ? d2 : d;
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble min() {
        return reduce(new j$.time.e(23));
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble max() {
        return reduce(new n(2));
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble average() {
        double[] dArr = (double[]) collect(new j$.time.e(24), new j$.time.e(25), new j$.time.e(26));
        if (dArr[2] <= 0.0d) {
            return OptionalDouble.empty();
        }
        Set set = Collectors.a;
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        if (Double.isNaN(d) && Double.isInfinite(d2)) {
            d = d2;
        }
        return OptionalDouble.of(d / dArr[2]);
    }

    @Override // j$.util.stream.DoubleStream
    public final j$.util.z summaryStatistics() {
        return (j$.util.z) collect(new j$.time.e(12), new j$.time.e(27), new j$.time.e(28));
    }

    @Override // j$.util.stream.DoubleStream
    public final Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        o oVar = new o(biConsumer, 0);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(oVar);
        return y0(new b4(d7.DOUBLE_VALUE, oVar, objDoubleConsumer, supplier, 1));
    }

    @Override // j$.util.stream.DoubleStream
    public final boolean o() {
        return ((Boolean) y0(w3.p0(q1.ANY))).booleanValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final boolean t() {
        return ((Boolean) y0(w3.p0(q1.ALL))).booleanValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final boolean B() {
        return ((Boolean) y0(w3.p0(q1.NONE))).booleanValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final double[] toArray() {
        return (double[]) w3.j0((x1) z0(new n(1))).b();
    }

    @Override // j$.util.stream.DoubleStream
    public final double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return ((Double) y0(new f4(d7.DOUBLE_VALUE, doubleBinaryOperator, d))).doubleValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return (OptionalDouble) y0(new z3(d7.DOUBLE_VALUE, doubleBinaryOperator, 1));
    }

    @Override // j$.util.stream.DoubleStream
    public final long count() {
        return ((Long) y0(new d4(1))).longValue();
    }
}
