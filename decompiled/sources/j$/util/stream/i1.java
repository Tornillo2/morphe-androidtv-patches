package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.Spliterator;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class i1 implements LongStream {
    public final /* synthetic */ java.util.stream.LongStream a;

    public /* synthetic */ i1(java.util.stream.LongStream longStream) {
        this.a = longStream;
    }

    public static /* synthetic */ LongStream f(java.util.stream.LongStream longStream) {
        if (longStream == null) {
            return null;
        }
        return longStream instanceof j1 ? ((j1) longStream).a : new i1(longStream);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ IntStream A() {
        return IntStream.VivifiedWrapper.convert(this.a.mapToInt(null));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream a() {
        return f(this.a.takeWhile(null));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ DoubleStream asDoubleStream() {
        return a0.f(this.a.asDoubleStream());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ OptionalDouble average() {
        return j$.com.android.tools.r8.a.w(this.a.average());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream b() {
        return f(this.a.filter(null));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ Stream boxed() {
        return Stream.VivifiedWrapper.convert(this.a.boxed());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream c() {
        return f(this.a.dropWhile(null));
    }

    @Override // j$.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objLongConsumer, biConsumer);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream distinct() {
        return f(this.a.distinct());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream e() {
        return f(this.a.map(null));
    }

    public final /* synthetic */ boolean equals(Object obj) {
        java.util.stream.LongStream longStream = this.a;
        if (obj instanceof i1) {
            obj = ((i1) obj).a;
        }
        return longStream.equals(obj);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ OptionalLong findAny() {
        return j$.com.android.tools.r8.a.y(this.a.findAny());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ OptionalLong findFirst() {
        return j$.com.android.tools.r8.a.y(this.a.findFirst());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ void forEach(LongConsumer longConsumer) {
        this.a.forEach(longConsumer);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
        this.a.forEachOrdered(longConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfLong] */
    @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    public final /* synthetic */ Iterator<Long> iterator2() {
        ?? it = this.a.iterator();
        if (it == 0) {
            return null;
        }
        return it instanceof j$.util.l0 ? ((j$.util.l0) it).a : new j$.util.k0(it);
    }

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Iterator<Long> iterator2() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ DoubleStream j() {
        return a0.f(this.a.mapToDouble(null));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream limit(long j) {
        return f(this.a.limit(j));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ boolean m() {
        return this.a.noneMatch(null);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ Stream mapToObj(LongFunction longFunction) {
        return Stream.VivifiedWrapper.convert(this.a.mapToObj(longFunction));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ OptionalLong max() {
        return j$.com.android.tools.r8.a.y(this.a.max());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ OptionalLong min() {
        return j$.com.android.tools.r8.a.y(this.a.min());
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream onClose(Runnable runnable) {
        return e.f(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream parallel() {
        return e.f(this.a.parallel());
    }

    @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
    public final /* synthetic */ LongStream parallel() {
        return f(this.a.parallel());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream peek(LongConsumer longConsumer) {
        return f(this.a.peek(longConsumer));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ boolean q() {
        return this.a.anyMatch(null);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ long reduce(long j, LongBinaryOperator longBinaryOperator) {
        return this.a.reduce(j, longBinaryOperator);
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        return j$.com.android.tools.r8.a.y(this.a.reduce(longBinaryOperator));
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream sequential() {
        return e.f(this.a.sequential());
    }

    @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
    public final /* synthetic */ LongStream sequential() {
        return f(this.a.sequential());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream skip(long j) {
        return f(this.a.skip(j));
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ LongStream sorted() {
        return f(this.a.sorted());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfLong] */
    @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
    public final /* synthetic */ Spliterator<Long> spliterator() {
        return j$.util.s0.a(this.a.spliterator());
    }

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Spliterator<Long> spliterator2() {
        return j$.util.w0.a(this.a.spliterator());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ long sum() {
        return this.a.sum();
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ long[] toArray() {
        return this.a.toArray();
    }

    @Override // j$.util.stream.BaseStream
    public final /* synthetic */ BaseStream unordered() {
        return e.f(this.a.unordered());
    }

    @Override // j$.util.stream.LongStream
    public final /* synthetic */ boolean w() {
        return this.a.allMatch(null);
    }

    @Override // j$.util.stream.LongStream
    public final j$.util.c0 summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.LongSummaryStatistics");
    }

    @Override // j$.util.stream.LongStream
    public final LongStream d(j$.util.q qVar) {
        java.util.stream.LongStream longStream = this.a;
        j$.util.q qVar2 = new j$.util.q(7);
        qVar2.b = qVar;
        return f(longStream.flatMap(qVar2));
    }
}
