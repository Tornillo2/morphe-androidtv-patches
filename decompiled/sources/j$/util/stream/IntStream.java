package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Stream;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public interface IntStream extends BaseStream<Integer, IntStream> {
    IntStream a();

    DoubleStream asDoubleStream();

    LongStream asLongStream();

    OptionalDouble average();

    IntStream b();

    Stream<Integer> boxed();

    IntStream c();

    <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> objIntConsumer, BiConsumer<R, R> biConsumer);

    long count();

    IntStream distinct();

    IntStream e();

    OptionalInt findAny();

    OptionalInt findFirst();

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    DoubleStream g();

    @Override // j$.util.stream.BaseStream
    Iterator<Integer> iterator();

    LongStream k();

    IntStream limit(long j);

    Stream mapToObj(IntFunction intFunction);

    OptionalInt max();

    OptionalInt min();

    boolean p();

    @Override // j$.util.stream.BaseStream
    IntStream parallel();

    IntStream peek(IntConsumer intConsumer);

    IntStream r(j0 j0Var);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    OptionalInt reduce(IntBinaryOperator intBinaryOperator);

    boolean s();

    @Override // j$.util.stream.BaseStream
    IntStream sequential();

    IntStream skip(long j);

    IntStream sorted();

    @Override // j$.util.stream.BaseStream
    Spliterator<Integer> spliterator();

    int sum();

    j$.util.a0 summaryStatistics();

    int[] toArray();

    boolean x();

    public final /* synthetic */ class Wrapper implements java.util.stream.IntStream {
        public /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.IntStream convert(IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof VivifiedWrapper ? ((VivifiedWrapper) intStream).a : intStream.new Wrapper();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ boolean allMatch(IntPredicate intPredicate) {
            return IntStream.this.p();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ boolean anyMatch(IntPredicate intPredicate) {
            return IntStream.this.x();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.DoubleStream asDoubleStream() {
            return b0.f(IntStream.this.asDoubleStream());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.LongStream asLongStream() {
            return j1.f(IntStream.this.asLongStream());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalDouble average() {
            return j$.com.android.tools.r8.a.A(IntStream.this.average());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(IntStream.this.boxed());
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            IntStream.this.close();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return IntStream.this.collect(supplier, objIntConsumer, biConsumer);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ long count() {
            return IntStream.this.count();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream distinct() {
            return convert(IntStream.this.distinct());
        }

        public final /* synthetic */ java.util.stream.IntStream dropWhile(IntPredicate intPredicate) {
            return convert(IntStream.this.c());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            IntStream intStream = IntStream.this;
            if (obj instanceof Wrapper) {
                obj = IntStream.this;
            }
            return intStream.equals(obj);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream filter(IntPredicate intPredicate) {
            return convert(IntStream.this.b());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt findAny() {
            return j$.com.android.tools.r8.a.B(IntStream.this.findAny());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt findFirst() {
            return j$.com.android.tools.r8.a.B(IntStream.this.findFirst());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ void forEach(IntConsumer intConsumer) {
            IntStream.this.forEach(intConsumer);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
            IntStream.this.forEachOrdered(intConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return IntStream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return IntStream.this.isParallel();
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ Iterator<Integer> iterator() {
            return IntStream.this.iterator();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [j$.util.PrimitiveIterator$OfInt] */
        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        /* JADX INFO: renamed from: iterator, reason: avoid collision after fix types in other method */
        public final /* synthetic */ Iterator<Integer> iterator2() {
            ?? it = IntStream.this.iterator();
            if (it == 0) {
                return null;
            }
            return it instanceof j$.util.h0 ? ((j$.util.h0) it).a : new j$.util.i0(it);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream limit(long j) {
            return convert(IntStream.this.limit(j));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream map(IntUnaryOperator intUnaryOperator) {
            return convert(IntStream.this.e());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
            return b0.f(IntStream.this.g());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.LongStream mapToLong(IntToLongFunction intToLongFunction) {
            return j1.f(IntStream.this.k());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.Stream mapToObj(IntFunction intFunction) {
            return Stream.Wrapper.convert(IntStream.this.mapToObj(intFunction));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt max() {
            return j$.com.android.tools.r8.a.B(IntStream.this.max());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt min() {
            return j$.com.android.tools.r8.a.B(IntStream.this.min());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ boolean noneMatch(IntPredicate intPredicate) {
            return IntStream.this.s();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return f.f(IntStream.this.onClose(runnable));
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream parallel() {
            return f.f(IntStream.this.parallel());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.IntStream parallel() {
            return convert(IntStream.this.parallel());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream peek(IntConsumer intConsumer) {
            return convert(IntStream.this.peek(intConsumer));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return IntStream.this.reduce(i, intBinaryOperator);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return j$.com.android.tools.r8.a.B(IntStream.this.reduce(intBinaryOperator));
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream sequential() {
            return f.f(IntStream.this.sequential());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.IntStream sequential() {
            return convert(IntStream.this.sequential());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream skip(long j) {
            return convert(IntStream.this.skip(j));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream sorted() {
            return convert(IntStream.this.sorted());
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [j$.util.Spliterator$OfInt] */
        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Spliterator<Integer> spliterator() {
            return j$.util.r0.a(IntStream.this.spliterator());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator, reason: avoid collision after fix types in other method */
        public final /* synthetic */ java.util.Spliterator<Integer> spliterator2() {
            return Spliterator.Wrapper.convert(IntStream.this.spliterator());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ int sum() {
            return IntStream.this.sum();
        }

        public final /* synthetic */ java.util.stream.IntStream takeWhile(IntPredicate intPredicate) {
            return convert(IntStream.this.a());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ int[] toArray() {
            return IntStream.this.toArray();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream unordered() {
            return f.f(IntStream.this.unordered());
        }

        @Override // java.util.stream.IntStream
        public final IntSummaryStatistics summaryStatistics() {
            IntStream.this.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert to java.util.IntSummaryStatistics");
        }

        @Override // java.util.stream.IntStream
        public final java.util.stream.IntStream flatMap(IntFunction intFunction) {
            IntStream intStream = IntStream.this;
            j0 j0Var = new j0();
            j0Var.a = intFunction;
            return convert(intStream.r(j0Var));
        }
    }

    public final /* synthetic */ class VivifiedWrapper implements IntStream {
        public final /* synthetic */ java.util.stream.IntStream a;

        public /* synthetic */ VivifiedWrapper(java.util.stream.IntStream intStream) {
            this.a = intStream;
        }

        public static /* synthetic */ IntStream convert(java.util.stream.IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof Wrapper ? IntStream.this : new VivifiedWrapper(intStream);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream a() {
            return convert(this.a.takeWhile(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ DoubleStream asDoubleStream() {
            return a0.f(this.a.asDoubleStream());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ LongStream asLongStream() {
            return i1.f(this.a.asLongStream());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalDouble average() {
            return j$.com.android.tools.r8.a.w(this.a.average());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream b() {
            return convert(this.a.filter(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ Stream boxed() {
            return Stream.VivifiedWrapper.convert(this.a.boxed());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream c() {
            return convert(this.a.dropWhile(null));
        }

        @Override // j$.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            this.a.close();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return this.a.collect(supplier, objIntConsumer, biConsumer);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ long count() {
            return this.a.count();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream distinct() {
            return convert(this.a.distinct());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream e() {
            return convert(this.a.map(null));
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.IntStream intStream = this.a;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).a;
            }
            return intStream.equals(obj);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt findAny() {
            return j$.com.android.tools.r8.a.x(this.a.findAny());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt findFirst() {
            return j$.com.android.tools.r8.a.x(this.a.findFirst());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ void forEach(IntConsumer intConsumer) {
            this.a.forEach(intConsumer);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
            this.a.forEachOrdered(intConsumer);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ DoubleStream g() {
            return a0.f(this.a.mapToDouble(null));
        }

        public final /* synthetic */ int hashCode() {
            return this.a.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return this.a.isParallel();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfInt] */
        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ Iterator<Integer> iterator() {
            ?? it = this.a.iterator();
            if (it == 0) {
                return null;
            }
            return it instanceof j$.util.i0 ? ((j$.util.i0) it).a : new j$.util.h0(it);
        }

        @Override // j$.util.stream.BaseStream
        /* JADX INFO: renamed from: iterator, reason: avoid collision after fix types in other method */
        public final /* synthetic */ Iterator<Integer> iterator2() {
            return this.a.iterator();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ LongStream k() {
            return i1.f(this.a.mapToLong(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream limit(long j) {
            return convert(this.a.limit(j));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ Stream mapToObj(IntFunction intFunction) {
            return Stream.VivifiedWrapper.convert(this.a.mapToObj(intFunction));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt max() {
            return j$.com.android.tools.r8.a.x(this.a.max());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt min() {
            return j$.com.android.tools.r8.a.x(this.a.min());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream onClose(Runnable runnable) {
            return e.f(this.a.onClose(runnable));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ boolean p() {
            return this.a.allMatch(null);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream parallel() {
            return e.f(this.a.parallel());
        }

        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ IntStream parallel() {
            return convert(this.a.parallel());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream peek(IntConsumer intConsumer) {
            return convert(this.a.peek(intConsumer));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return this.a.reduce(i, intBinaryOperator);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return j$.com.android.tools.r8.a.x(this.a.reduce(intBinaryOperator));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ boolean s() {
            return this.a.noneMatch(null);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream sequential() {
            return e.f(this.a.sequential());
        }

        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ IntStream sequential() {
            return convert(this.a.sequential());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream skip(long j) {
            return convert(this.a.skip(j));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream sorted() {
            return convert(this.a.sorted());
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfInt] */
        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator<Integer> spliterator() {
            return j$.util.q0.a(this.a.spliterator());
        }

        @Override // j$.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator, reason: avoid collision after fix types in other method */
        public final /* synthetic */ Spliterator<Integer> spliterator2() {
            return j$.util.w0.a(this.a.spliterator());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ int sum() {
            return this.a.sum();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ int[] toArray() {
            return this.a.toArray();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream unordered() {
            return e.f(this.a.unordered());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ boolean x() {
            return this.a.anyMatch(null);
        }

        @Override // j$.util.stream.IntStream
        public final j$.util.a0 summaryStatistics() {
            this.a.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.IntSummaryStatistics");
        }

        @Override // j$.util.stream.IntStream
        public final IntStream r(j0 j0Var) {
            java.util.stream.IntStream intStream = this.a;
            j0 j0Var2 = new j0();
            j0Var2.a = j0Var;
            return convert(intStream.flatMap(j0Var2));
        }
    }

    /* JADX INFO: renamed from: j$.util.stream.IntStream$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static IntStream empty() {
            return StreamSupport.intStream(Spliterators.b, false);
        }

        public static IntStream of(int i) {
            p8 p8Var = new p8();
            p8Var.b = i;
            p8Var.a = -2;
            return StreamSupport.intStream(p8Var, false);
        }

        public static IntStream range(int i, int i2) {
            if (i >= i2) {
                return empty();
            }
            return StreamSupport.intStream(new r8(i, i2), false);
        }
    }
}
