package j$.util.stream;

import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class y0 extends a implements IntStream {
    @Override // j$.util.stream.IntStream
    public final OptionalInt findAny() {
        return (OptionalInt) y0(e0.d);
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt findFirst() {
        return (OptionalInt) y0(e0.c);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream sorted() {
        return new i6(this, c7.q | c7.o);
    }

    public void forEach(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        y0(new l0(intConsumer, false));
    }

    public void forEachOrdered(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        y0(new l0(intConsumer, true));
    }

    public static Spliterator.OfInt L0(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) spliterator;
        }
        if (v8.a) {
            v8.a(a.class, "using IntStream.adapt(Spliterator<Integer> s)");
            throw null;
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    @Override // j$.util.stream.a
    public final d7 C0() {
        return d7.INT_VALUE;
    }

    @Override // j$.util.stream.a
    public final d2 A0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return w3.Z(aVar, spliterator, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator K0(a aVar, Supplier supplier, boolean z) {
        return new u7(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean B0(Spliterator spliterator, o5 o5Var) {
        IntConsumer g0Var;
        boolean zE;
        Spliterator.OfInt ofIntL0 = L0(spliterator);
        if (o5Var instanceof IntConsumer) {
            g0Var = (IntConsumer) o5Var;
        } else {
            if (v8.a) {
                v8.a(a.class, "using IntStream.adapt(Sink<Integer> s)");
                throw null;
            }
            Objects.requireNonNull(o5Var);
            g0Var = new j$.util.g0(o5Var, 1);
        }
        do {
            zE = o5Var.e();
            if (zE) {
                break;
            }
        } while (ofIntL0.tryAdvance(g0Var));
        return zE;
    }

    @Override // j$.util.stream.w3
    public final v1 s0(long j, IntFunction intFunction) {
        return w3.n0(j);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.Spliterator$OfInt] */
    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    public final Iterator<Integer> iterator2() {
        return Spliterators.iterator((Spliterator.OfInt) spliterator());
    }

    @Override // j$.util.stream.a, j$.util.stream.BaseStream
    public final Spliterator<Integer> spliterator() {
        return L0(super.spliterator());
    }

    @Override // j$.util.stream.IntStream
    public final LongStream asLongStream() {
        return new t(this, 0, 1);
    }

    @Override // j$.util.stream.IntStream
    public final DoubleStream asDoubleStream() {
        return new r(this, 0, 3);
    }

    @Override // j$.util.stream.IntStream
    public final Stream boxed() {
        return new q(this, 0, new n(16), 1);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream e() {
        Objects.requireNonNull(null);
        return new s(this, c7.p | c7.n, 1);
    }

    @Override // j$.util.stream.IntStream
    public final Stream mapToObj(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return new q(this, c7.p | c7.n, intFunction, 1);
    }

    @Override // j$.util.stream.IntStream
    public final LongStream k() {
        Objects.requireNonNull(null);
        return new t(this, c7.p | c7.n, 2);
    }

    @Override // j$.util.stream.IntStream
    public final DoubleStream g() {
        Objects.requireNonNull(null);
        return new r(this, c7.p | c7.n, 4);
    }

    @Override // j$.util.stream.IntStream
    public final int reduce(int i, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return ((Integer) y0(new m4(d7.INT_VALUE, intBinaryOperator, i))).intValue();
    }

    @Override // j$.util.stream.IntStream
    public final IntStream r(j0 j0Var) {
        Objects.requireNonNull(j0Var);
        return new s0(this, c7.p | c7.n | c7.t, j0Var, 1);
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return (OptionalInt) y0(new z3(d7.INT_VALUE, intBinaryOperator, 3));
    }

    @Override // j$.util.stream.IntStream
    public final IntStream b() {
        Objects.requireNonNull(null);
        return new s(this, c7.t, 3);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream peek(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new s0(this, intConsumer);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return z5.f(this, 0L, j);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : z5.f(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.IntStream
    public final IntStream a() {
        int i = z9.a;
        Objects.requireNonNull(null);
        return new z8(this, z9.a);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream c() {
        int i = z9.a;
        Objects.requireNonNull(null);
        return new b9(this, z9.b);
    }

    @Override // j$.util.stream.IntStream
    public final long count() {
        return ((Long) y0(new d4(3))).longValue();
    }

    @Override // j$.util.stream.IntStream
    public final IntStream distinct() {
        return ((g5) boxed()).distinct().mapToInt(new n(15));
    }

    @Override // j$.util.stream.IntStream
    public final int sum() {
        return reduce(0, new n(20));
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt min() {
        return reduce(new n(17));
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt max() {
        return reduce(new n(21));
    }

    @Override // j$.util.stream.IntStream
    public final OptionalDouble average() {
        long j = ((long[]) collect(new n(22), new n(23), new n(24)))[0];
        return j > 0 ? OptionalDouble.of(r0[1] / j) : OptionalDouble.empty();
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.a0 summaryStatistics() {
        return (j$.util.a0) collect(new j$.time.e(15), new n(18), new n(19));
    }

    @Override // j$.util.stream.IntStream
    public final Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        o oVar = new o(biConsumer, 1);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(oVar);
        return y0(new b4(d7.INT_VALUE, oVar, objIntConsumer, supplier, 4));
    }

    @Override // j$.util.stream.IntStream
    public final boolean x() {
        return ((Boolean) y0(w3.q0(q1.ANY))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final boolean p() {
        return ((Boolean) y0(w3.q0(q1.ALL))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final boolean s() {
        return ((Boolean) y0(w3.q0(q1.NONE))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final int[] toArray() {
        return (int[]) w3.k0((z1) z0(new n(14))).b();
    }
}
