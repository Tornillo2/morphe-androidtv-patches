package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b0 implements java.util.stream.DoubleStream {
    public final /* synthetic */ DoubleStream a;

    public /* synthetic */ b0(DoubleStream doubleStream) {
        this.a = doubleStream;
    }

    public static /* synthetic */ java.util.stream.DoubleStream f(DoubleStream doubleStream) {
        if (doubleStream == null) {
            return null;
        }
        return doubleStream instanceof a0 ? ((a0) doubleStream).a : new b0(doubleStream);
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ boolean allMatch(DoublePredicate doublePredicate) {
        return this.a.t();
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ boolean anyMatch(DoublePredicate doublePredicate) {
        return this.a.o();
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble average() {
        return j$.com.android.tools.r8.a.A(this.a.average());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.Stream boxed() {
        return Stream.Wrapper.convert(this.a.boxed());
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objDoubleConsumer, biConsumer);
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream distinct() {
        return f(this.a.distinct());
    }

    public final /* synthetic */ java.util.stream.DoubleStream dropWhile(DoublePredicate doublePredicate) {
        return f(this.a.c());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        DoubleStream doubleStream = this.a;
        if (obj instanceof b0) {
            obj = ((b0) obj).a;
        }
        return doubleStream.equals(obj);
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream filter(DoublePredicate doublePredicate) {
        return f(this.a.b());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble findAny() {
        return j$.com.android.tools.r8.a.A(this.a.findAny());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble findFirst() {
        return j$.com.android.tools.r8.a.A(this.a.findFirst());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
        this.a.forEach(doubleConsumer);
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
        this.a.forEachOrdered(doubleConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    public final /* synthetic */ Iterator<Double> iterator() {
        return this.a.iterator2();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [j$.util.PrimitiveIterator$OfDouble] */
    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Iterator<Double> iterator2() {
        ?? it = this.a.iterator2();
        if (it == 0) {
            return null;
        }
        return it instanceof j$.util.e0 ? ((j$.util.e0) it).a : new j$.util.f0(it);
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream limit(long j) {
        return f(this.a.limit(j));
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream map(DoubleUnaryOperator doubleUnaryOperator) {
        return f(this.a.e());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.IntStream mapToInt(DoubleToIntFunction doubleToIntFunction) {
        return IntStream.Wrapper.convert(this.a.z());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.LongStream mapToLong(DoubleToLongFunction doubleToLongFunction) {
        return j1.f(this.a.u());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.Stream mapToObj(DoubleFunction doubleFunction) {
        return Stream.Wrapper.convert(this.a.mapToObj(doubleFunction));
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble max() {
        return j$.com.android.tools.r8.a.A(this.a.max());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble min() {
        return j$.com.android.tools.r8.a.A(this.a.min());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ boolean noneMatch(DoublePredicate doublePredicate) {
        return this.a.B();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
        return f.f(this.a.onClose(runnable));
    }

    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    public final /* synthetic */ java.util.stream.BaseStream parallel() {
        return f.f(this.a.parallel());
    }

    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    public final /* synthetic */ java.util.stream.DoubleStream parallel() {
        return f(this.a.parallel());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream peek(DoubleConsumer doubleConsumer) {
        return f(this.a.peek(doubleConsumer));
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        return this.a.reduce(d, doubleBinaryOperator);
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        return j$.com.android.tools.r8.a.A(this.a.reduce(doubleBinaryOperator));
    }

    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    public final /* synthetic */ java.util.stream.BaseStream sequential() {
        return f.f(this.a.sequential());
    }

    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    public final /* synthetic */ java.util.stream.DoubleStream sequential() {
        return f(this.a.sequential());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream skip(long j) {
        return f(this.a.skip(j));
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ java.util.stream.DoubleStream sorted() {
        return f(this.a.sorted());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [j$.util.Spliterator$OfDouble] */
    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    public final /* synthetic */ Spliterator<Double> spliterator() {
        return j$.util.p0.a(this.a.spliterator());
    }

    @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Spliterator<Double> spliterator2() {
        return Spliterator.Wrapper.convert(this.a.spliterator());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ double sum() {
        return this.a.sum();
    }

    public final /* synthetic */ java.util.stream.DoubleStream takeWhile(DoublePredicate doublePredicate) {
        return f(this.a.a());
    }

    @Override // java.util.stream.DoubleStream
    public final /* synthetic */ double[] toArray() {
        return this.a.toArray();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ java.util.stream.BaseStream unordered() {
        return f.f(this.a.unordered());
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleSummaryStatistics summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert to java.util.DoubleSummaryStatistics");
    }

    @Override // java.util.stream.DoubleStream
    public final java.util.stream.DoubleStream flatMap(DoubleFunction doubleFunction) {
        DoubleStream doubleStream = this.a;
        j$.util.q qVar = new j$.util.q(5);
        qVar.b = doubleFunction;
        return f(doubleStream.d(qVar));
    }
}
