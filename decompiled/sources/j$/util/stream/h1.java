package j$.util.stream;

import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class h1 extends a implements LongStream {
    @Override // j$.util.stream.LongStream
    public final OptionalLong findAny() {
        return (OptionalLong) y0(f0.d);
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong findFirst() {
        return (OptionalLong) y0(f0.c);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream sorted() {
        return new j6(this, c7.q | c7.o);
    }

    public void forEach(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        y0(new m0(longConsumer, false));
    }

    public void forEachOrdered(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        y0(new m0(longConsumer, true));
    }

    public static Spliterator.OfLong L0(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) spliterator;
        }
        if (v8.a) {
            v8.a(a.class, "using LongStream.adapt(Spliterator<Long> s)");
            throw null;
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    @Override // j$.util.stream.a
    public final d7 C0() {
        return d7.LONG_VALUE;
    }

    @Override // j$.util.stream.a
    public final d2 A0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return w3.a0(aVar, spliterator, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator K0(a aVar, Supplier supplier, boolean z) {
        return new w7(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean B0(Spliterator spliterator, o5 o5Var) {
        LongConsumer j0Var;
        boolean zE;
        Spliterator.OfLong ofLongL0 = L0(spliterator);
        if (o5Var instanceof LongConsumer) {
            j0Var = (LongConsumer) o5Var;
        } else {
            if (v8.a) {
                v8.a(a.class, "using LongStream.adapt(Sink<Long> s)");
                throw null;
            }
            Objects.requireNonNull(o5Var);
            j0Var = new j$.util.j0(o5Var, 1);
        }
        do {
            zE = o5Var.e();
            if (zE) {
                break;
            }
        } while (ofLongL0.tryAdvance(j0Var));
        return zE;
    }

    @Override // j$.util.stream.w3
    public final v1 s0(long j, IntFunction intFunction) {
        return w3.o0(j);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.Spliterator$OfLong] */
    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public final Iterator<Long> iterator2() {
        return Spliterators.iterator((Spliterator.OfLong) spliterator());
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final Spliterator<Long> spliterator() {
        return L0(super.spliterator());
    }

    @Override // j$.util.stream.LongStream
    public final DoubleStream asDoubleStream() {
        return new r(this, c7.n, 5);
    }

    @Override // j$.util.stream.LongStream
    public final Stream boxed() {
        return new q(this, 0, new z0(0), 2);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream e() {
        Objects.requireNonNull(null);
        return new t(this, c7.p | c7.n, 3);
    }

    @Override // j$.util.stream.LongStream
    public final Stream mapToObj(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return new q(this, c7.p | c7.n, longFunction, 2);
    }

    @Override // j$.util.stream.LongStream
    public final IntStream A() {
        Objects.requireNonNull(null);
        return new s(this, c7.p | c7.n, 4);
    }

    @Override // j$.util.stream.LongStream
    public final DoubleStream j() {
        Objects.requireNonNull(null);
        return new r(this, c7.p | c7.n, 6);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream d(j$.util.q qVar) {
        Objects.requireNonNull(qVar);
        return new d1(this, c7.p | c7.n | c7.t, qVar, 0);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream b() {
        Objects.requireNonNull(null);
        return new t(this, c7.t, 5);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream peek(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new d1(this, longConsumer);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return z5.g(this, 0L, j);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : z5.g(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.LongStream
    public final LongStream a() {
        int i = z9.a;
        Objects.requireNonNull(null);
        return new d9(this, z9.a);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream c() {
        int i = z9.a;
        Objects.requireNonNull(null);
        return new f9(this, z9.b);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream distinct() {
        return ((g5) boxed()).distinct().mapToLong(new n(27));
    }

    @Override // j$.util.stream.LongStream
    public final long sum() {
        return reduce(0L, new z0(5));
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong min() {
        return reduce(new n(26));
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong max() {
        return reduce(new z0(4));
    }

    @Override // j$.util.stream.LongStream
    public final OptionalDouble average() {
        long j = ((long[]) collect(new z0(1), new z0(2), new z0(3)))[0];
        return j > 0 ? OptionalDouble.of(r0[1] / j) : OptionalDouble.empty();
    }

    @Override // j$.util.stream.LongStream
    public final long reduce(long j, LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return ((Long) y0(new x3(d7.LONG_VALUE, longBinaryOperator, j))).longValue();
    }

    @Override // j$.util.stream.LongStream
    public final j$.util.c0 summaryStatistics() {
        return (j$.util.c0) collect(new j$.time.e(16), new n(25), new n(28));
    }

    @Override // j$.util.stream.LongStream
    public final Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        o oVar = new o(biConsumer, 2);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(oVar);
        return y0(new b4(d7.LONG_VALUE, oVar, objLongConsumer, supplier, 0));
    }

    @Override // j$.util.stream.LongStream
    public final boolean q() {
        return ((Boolean) y0(w3.r0(q1.ANY))).booleanValue();
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return (OptionalLong) y0(new z3(d7.LONG_VALUE, longBinaryOperator, 0));
    }

    @Override // j$.util.stream.LongStream
    public final boolean w() {
        return ((Boolean) y0(w3.r0(q1.ALL))).booleanValue();
    }

    @Override // j$.util.stream.LongStream
    public final boolean m() {
        return ((Boolean) y0(w3.r0(q1.NONE))).booleanValue();
    }

    @Override // j$.util.stream.LongStream
    public final long[] toArray() {
        return (long[]) w3.l0((b2) z0(new n(29))).b();
    }

    @Override // j$.util.stream.LongStream
    public final long count() {
        return ((Long) y0(new d4(0))).longValue();
    }
}
