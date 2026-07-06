package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.Spliterator;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class a0 implements DoubleStream {
    public final /* synthetic */ java.util.stream.DoubleStream a;

    public /* synthetic */ a0(java.util.stream.DoubleStream doubleStream) {
        this.a = doubleStream;
    }

    public static /* synthetic */ DoubleStream f(java.util.stream.DoubleStream doubleStream) {
        if (doubleStream == null) {
            return null;
        }
        return doubleStream instanceof b0 ? ((b0) doubleStream).a : new a0(doubleStream);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ boolean B() {
        return this.a.noneMatch(null);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream a() {
        return f(this.a.takeWhile(null));
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble average() {
        return j$.com.android.tools.r8.a.w(this.a.average());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream b() {
        return f(this.a.filter(null));
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ Stream boxed() {
        return Stream.VivifiedWrapper.convert(this.a.boxed());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream c() {
        return f(this.a.dropWhile(null));
    }

    @Override // j$.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objDoubleConsumer, biConsumer);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream distinct() {
        return f(this.a.distinct());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream e() {
        return f(this.a.map(null));
    }

    public final /* synthetic */ boolean equals(Object obj) {
        java.util.stream.DoubleStream doubleStream = this.a;
        if (obj instanceof a0) {
            obj = ((a0) obj).a;
        }
        return doubleStream.equals(obj);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble findAny() {
        return j$.com.android.tools.r8.a.w(this.a.findAny());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble findFirst() {
        return j$.com.android.tools.r8.a.w(this.a.findFirst());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
        this.a.forEach(doubleConsumer);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
        this.a.forEachOrdered(doubleConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfDouble] */
    @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    public final /* synthetic */ Iterator<Double> iterator2() {
        ?? it = this.a.iterator();
        if (it == 0) {
            return null;
        }
        return it instanceof j$.util.f0 ? ((j$.util.f0) it).a : new j$.util.e0(it);
    }

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Iterator<Double> iterator2() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream limit(long j) {
        return f(this.a.limit(j));
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ Stream mapToObj(DoubleFunction doubleFunction) {
        return Stream.VivifiedWrapper.convert(this.a.mapToObj(doubleFunction));
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble max() {
        return j$.com.android.tools.r8.a.w(this.a.max());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble min() {
        return j$.com.android.tools.r8.a.w(this.a.min());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ boolean o() {
        return this.a.anyMatch(null);
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream onClose(Runnable runnable) {
        return e.f(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream parallel() {
        return e.f(this.a.parallel());
    }

    @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
    public final /* synthetic */ DoubleStream parallel() {
        return f(this.a.parallel());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream peek(DoubleConsumer doubleConsumer) {
        return f(this.a.peek(doubleConsumer));
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        return this.a.reduce(d, doubleBinaryOperator);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        return j$.com.android.tools.r8.a.w(this.a.reduce(doubleBinaryOperator));
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream sequential() {
        return e.f(this.a.sequential());
    }

    @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
    public final /* synthetic */ DoubleStream sequential() {
        return f(this.a.sequential());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream skip(long j) {
        return f(this.a.skip(j));
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ DoubleStream sorted() {
        return f(this.a.sorted());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfDouble] */
    @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
    public final /* synthetic */ Spliterator<Double> spliterator() {
        return j$.util.o0.a(this.a.spliterator());
    }

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Spliterator<Double> spliterator2() {
        return j$.util.w0.a(this.a.spliterator());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ double sum() {
        return this.a.sum();
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ boolean t() {
        return this.a.allMatch(null);
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ double[] toArray() {
        return this.a.toArray();
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ LongStream u() {
        return i1.f(this.a.mapToLong(null));
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream unordered() {
        return e.f(this.a.unordered());
    }

    @Override // j$.util.stream.DoubleStream
    public final /* synthetic */ IntStream z() {
        return IntStream.VivifiedWrapper.convert(this.a.mapToInt(null));
    }

    @Override // j$.util.stream.DoubleStream
    public final j$.util.z summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.DoubleSummaryStatistics");
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream d(j$.util.q qVar) {
        java.util.stream.DoubleStream doubleStream = this.a;
        j$.util.q qVar2 = new j$.util.q(5);
        qVar2.b = qVar;
        return f(doubleStream.flatMap(qVar2));
    }
}
