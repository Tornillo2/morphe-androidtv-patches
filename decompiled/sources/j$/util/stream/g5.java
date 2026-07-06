package j$.util.stream;

import j$.util.Objects;
import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Collector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g5 extends a implements Stream {
    @Override // j$.util.stream.Stream
    public final Stream sorted() {
        return new k6(this);
    }

    @Override // j$.util.stream.Stream
    public final Stream distinct() {
        return new m(this, c7.m | c7.t);
    }

    @Override // j$.util.stream.Stream
    public final Optional min(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new j$.util.function.a(comparator, 1));
    }

    @Override // j$.util.stream.Stream
    public final Optional findAny() {
        return (Optional) y0(g0.d);
    }

    @Override // j$.util.stream.Stream
    public final Optional findFirst() {
        return (Optional) y0(g0.c);
    }

    @Override // j$.util.stream.Stream
    public final Stream sorted(Comparator comparator) {
        return new k6(this, comparator);
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return y0(new b4(d7.REFERENCE, binaryOperator, biFunction, obj, 2));
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        Objects.requireNonNull(binaryOperator);
        return y0(new b4(d7.REFERENCE, binaryOperator, binaryOperator, obj, 2));
    }

    public void forEach(Consumer consumer) {
        Objects.requireNonNull(consumer);
        y0(new n0(consumer, false));
    }

    public void forEachOrdered(Consumer consumer) {
        Objects.requireNonNull(consumer);
        y0(new n0(consumer, true));
    }

    @Override // j$.util.stream.Stream
    public final Optional max(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new j$.util.function.a(comparator, 0));
    }

    @Override // j$.util.stream.a
    public final d7 C0() {
        return d7.REFERENCE;
    }

    @Override // j$.util.stream.Stream
    public final Optional reduce(BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return (Optional) y0(new z3(d7.REFERENCE, binaryOperator, 2));
    }

    @Override // j$.util.stream.a
    public final d2 A0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return w3.X(aVar, spliterator, z, intFunction);
    }

    @Override // j$.util.stream.a
    public final Spliterator K0(a aVar, Supplier supplier, boolean z) {
        return new l8(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator D0(Supplier supplier) {
        return new p7(supplier);
    }

    @Override // j$.util.stream.a
    public final boolean B0(Spliterator spliterator, o5 o5Var) {
        boolean zE;
        do {
            zE = o5Var.e();
            if (zE) {
                break;
            }
        } while (spliterator.tryAdvance(o5Var));
        return zE;
    }

    @Override // j$.util.stream.w3
    public final v1 s0(long j, IntFunction intFunction) {
        return w3.W(j, intFunction);
    }

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    public final Iterator iterator2() {
        return Spliterators.iterator(spliterator());
    }

    @Override // j$.util.stream.Stream
    public final Stream filter(Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new q(this, c7.t, predicate, 4);
    }

    @Override // j$.util.stream.Stream
    public final Stream map(Function function) {
        Objects.requireNonNull(function);
        return new q(this, c7.p | c7.n, function, 5);
    }

    @Override // j$.util.stream.Stream
    public final IntStream mapToInt(ToIntFunction toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new s0(this, c7.p | c7.n, toIntFunction, 2);
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return y0(new b4(d7.REFERENCE, biConsumer2, biConsumer, supplier, 3));
    }

    @Override // j$.util.stream.Stream
    public final LongStream mapToLong(ToLongFunction toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new d1(this, c7.p | c7.n, toLongFunction, 3);
    }

    @Override // j$.util.stream.Stream
    public final DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new v(this, c7.p | c7.n, toDoubleFunction, 2);
    }

    @Override // j$.util.stream.Stream
    public final long count() {
        return ((Long) y0(new d4(2))).longValue();
    }

    @Override // j$.util.stream.Stream
    public final Stream d(j$.util.q qVar) {
        Objects.requireNonNull(qVar);
        return new q(this, c7.p | c7.n | c7.t, qVar, 6);
    }

    @Override // j$.util.stream.Stream
    public final IntStream n(j$.util.q qVar) {
        Objects.requireNonNull(qVar);
        return new s0(this, c7.p | c7.n | c7.t, qVar, 3);
    }

    @Override // j$.util.stream.Stream
    public final DoubleStream y(j$.util.q qVar) {
        Objects.requireNonNull(qVar);
        return new v(this, c7.p | c7.n | c7.t, qVar, 3);
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Collector collector) {
        Collector collector2;
        Object objY0;
        if (!this.h.s || !collector.characteristics().contains(Collector.Characteristics.CONCURRENT) || (c7.ORDERED.o(this.m) && !collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            Supplier supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
            collector2 = collector;
            objY0 = y0(new i4(d7.REFERENCE, collector.combiner(), collector.accumulator(), supplier, collector2));
        } else {
            objY0 = collector.supplier().get();
            forEach(new j$.time.format.v(8, collector.accumulator(), objY0));
            collector2 = collector;
        }
        return collector2.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? objY0 : collector2.finisher().apply(objY0);
    }

    @Override // j$.util.stream.Stream
    public final LongStream l(j$.util.q qVar) {
        Objects.requireNonNull(qVar);
        return new d1(this, c7.p | c7.n | c7.t, qVar, 2);
    }

    @Override // j$.util.stream.Stream
    public final Stream peek(Consumer consumer) {
        Objects.requireNonNull(consumer);
        return new q(this, consumer);
    }

    @Override // j$.util.stream.Stream
    public final Stream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return z5.h(this, 0L, j);
    }

    @Override // j$.util.stream.Stream
    public final Stream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : z5.h(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.Stream
    public final Stream takeWhile(Predicate predicate) {
        int i = z9.a;
        Objects.requireNonNull(predicate);
        return new w8(this, z9.a, predicate, 0);
    }

    @Override // j$.util.stream.Stream
    public final Stream dropWhile(Predicate predicate) {
        int i = z9.a;
        Objects.requireNonNull(predicate);
        return new w8(this, z9.b, predicate, 1);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray(IntFunction intFunction) {
        return w3.i0(z0(intFunction), intFunction).m(intFunction);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray() {
        return toArray(new z0(15));
    }

    @Override // j$.util.stream.Stream
    public final boolean anyMatch(Predicate predicate) {
        return ((Boolean) y0(w3.t0(q1.ANY, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean allMatch(Predicate predicate) {
        return ((Boolean) y0(w3.t0(q1.ALL, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean noneMatch(Predicate predicate) {
        return ((Boolean) y0(w3.t0(q1.NONE, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final List toList() {
        return Collections.unmodifiableList(new ArrayList(Arrays.asList(toArray())));
    }
}
