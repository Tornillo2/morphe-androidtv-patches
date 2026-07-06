package j$.util.stream;

import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.IntStream;
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
public interface Stream<T> extends BaseStream<T, Stream<T>> {

    public final /* synthetic */ class VivifiedWrapper implements Stream {
        public final /* synthetic */ java.util.stream.Stream a;

        public /* synthetic */ VivifiedWrapper(java.util.stream.Stream stream) {
            this.a = stream;
        }

        public static /* synthetic */ Stream convert(java.util.stream.Stream stream) {
            if (stream == null) {
                return null;
            }
            return stream instanceof Wrapper ? Stream.this : new VivifiedWrapper(stream);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ boolean allMatch(Predicate predicate) {
            return this.a.allMatch(predicate);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ boolean anyMatch(Predicate predicate) {
            return this.a.anyMatch(predicate);
        }

        @Override // j$.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            this.a.close();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object collect(Collector collector) {
            return this.a.collect(collector == null ? null : collector instanceof g ? ((g) collector).a : new h(collector));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            return this.a.collect(supplier, biConsumer, biConsumer2);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ long count() {
            return this.a.count();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream d(j$.util.q qVar) {
            return convert(this.a.flatMap(w3.m0(qVar)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream distinct() {
            return convert(this.a.distinct());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream dropWhile(Predicate predicate) {
            return convert(this.a.dropWhile(predicate));
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.Stream stream = this.a;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).a;
            }
            return stream.equals(obj);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream filter(Predicate predicate) {
            return convert(this.a.filter(predicate));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional findAny() {
            return j$.com.android.tools.r8.a.v(this.a.findAny());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional findFirst() {
            return j$.com.android.tools.r8.a.v(this.a.findFirst());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ void forEach(Consumer consumer) {
            this.a.forEach(consumer);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ void forEachOrdered(Consumer consumer) {
            this.a.forEachOrdered(consumer);
        }

        public final /* synthetic */ int hashCode() {
            return this.a.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return this.a.isParallel();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ Iterator iterator() {
            return this.a.iterator();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ LongStream l(j$.util.q qVar) {
            return i1.f(this.a.flatMapToLong(w3.m0(qVar)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream limit(long j) {
            return convert(this.a.limit(j));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream map(Function function) {
            return convert(this.a.map(function));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
            return a0.f(this.a.mapToDouble(toDoubleFunction));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ IntStream mapToInt(ToIntFunction toIntFunction) {
            return IntStream.VivifiedWrapper.convert(this.a.mapToInt(toIntFunction));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ LongStream mapToLong(ToLongFunction toLongFunction) {
            return i1.f(this.a.mapToLong(toLongFunction));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional max(Comparator comparator) {
            return j$.com.android.tools.r8.a.v(this.a.max(comparator));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional min(Comparator comparator) {
            return j$.com.android.tools.r8.a.v(this.a.min(comparator));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ IntStream n(j$.util.q qVar) {
            return IntStream.VivifiedWrapper.convert(this.a.flatMapToInt(w3.m0(qVar)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ boolean noneMatch(Predicate predicate) {
            return this.a.noneMatch(predicate);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream onClose(Runnable runnable) {
            return e.f(this.a.onClose(runnable));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream parallel() {
            return e.f(this.a.parallel());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream peek(Consumer consumer) {
            return convert(this.a.peek(consumer));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional reduce(BinaryOperator binaryOperator) {
            return j$.com.android.tools.r8.a.v(this.a.reduce(binaryOperator));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            return this.a.reduce(obj, biFunction, binaryOperator);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
            return this.a.reduce(obj, binaryOperator);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream sequential() {
            return e.f(this.a.sequential());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream skip(long j) {
            return convert(this.a.skip(j));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream sorted() {
            return convert(this.a.sorted());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream sorted(Comparator comparator) {
            return convert(this.a.sorted(comparator));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator spliterator() {
            return j$.util.w0.a(this.a.spliterator());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream takeWhile(Predicate predicate) {
            return convert(this.a.takeWhile(predicate));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object[] toArray() {
            return this.a.toArray();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return this.a.toArray(intFunction);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ List toList() {
            return this.a.toList();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream unordered() {
            return e.f(this.a.unordered());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ DoubleStream y(j$.util.q qVar) {
            return a0.f(this.a.flatMapToDouble(w3.m0(qVar)));
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.Stream {
        public /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.Stream convert(Stream stream) {
            if (stream == null) {
                return null;
            }
            return stream instanceof VivifiedWrapper ? ((VivifiedWrapper) stream).a : new Wrapper();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ boolean allMatch(Predicate predicate) {
            return Stream.this.allMatch(predicate);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ boolean anyMatch(Predicate predicate) {
            return Stream.this.anyMatch(predicate);
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            Stream.this.close();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            return Stream.this.collect(supplier, biConsumer, biConsumer2);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object collect(java.util.stream.Collector collector) {
            return Stream.this.collect(collector == null ? null : collector instanceof h ? ((h) collector).a : new g(collector));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ long count() {
            return Stream.this.count();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream distinct() {
            return convert(Stream.this.distinct());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream dropWhile(Predicate predicate) {
            return convert(Stream.this.dropWhile(predicate));
        }

        public final /* synthetic */ boolean equals(Object obj) {
            Stream stream = Stream.this;
            if (obj instanceof Wrapper) {
                obj = Stream.this;
            }
            return stream.equals(obj);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream filter(Predicate predicate) {
            return convert(Stream.this.filter(predicate));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional findAny() {
            return j$.com.android.tools.r8.a.z(Stream.this.findAny());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional findFirst() {
            return j$.com.android.tools.r8.a.z(Stream.this.findFirst());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream flatMap(Function function) {
            return convert(Stream.this.d(w3.m0(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.DoubleStream flatMapToDouble(Function function) {
            return b0.f(Stream.this.y(w3.m0(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.IntStream flatMapToInt(Function function) {
            return IntStream.Wrapper.convert(Stream.this.n(w3.m0(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.LongStream flatMapToLong(Function function) {
            return j1.f(Stream.this.l(w3.m0(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ void forEach(Consumer consumer) {
            Stream.this.forEach(consumer);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ void forEachOrdered(Consumer consumer) {
            Stream.this.forEachOrdered(consumer);
        }

        public final /* synthetic */ int hashCode() {
            return Stream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return Stream.this.isParallel();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ Iterator iterator() {
            return Stream.this.iterator();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream limit(long j) {
            return convert(Stream.this.limit(j));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream map(Function function) {
            return convert(Stream.this.map(function));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
            return b0.f(Stream.this.mapToDouble(toDoubleFunction));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.IntStream mapToInt(ToIntFunction toIntFunction) {
            return IntStream.Wrapper.convert(Stream.this.mapToInt(toIntFunction));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.LongStream mapToLong(ToLongFunction toLongFunction) {
            return j1.f(Stream.this.mapToLong(toLongFunction));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional max(Comparator comparator) {
            return j$.com.android.tools.r8.a.z(Stream.this.max(comparator));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional min(Comparator comparator) {
            return j$.com.android.tools.r8.a.z(Stream.this.min(comparator));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ boolean noneMatch(Predicate predicate) {
            return Stream.this.noneMatch(predicate);
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return f.f(Stream.this.onClose(runnable));
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream parallel() {
            return f.f(Stream.this.parallel());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream peek(Consumer consumer) {
            return convert(Stream.this.peek(consumer));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            return Stream.this.reduce(obj, biFunction, binaryOperator);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
            return Stream.this.reduce(obj, binaryOperator);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional reduce(BinaryOperator binaryOperator) {
            return j$.com.android.tools.r8.a.z(Stream.this.reduce(binaryOperator));
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream sequential() {
            return f.f(Stream.this.sequential());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream skip(long j) {
            return convert(Stream.this.skip(j));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream sorted() {
            return convert(Stream.this.sorted());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream sorted(Comparator comparator) {
            return convert(Stream.this.sorted(comparator));
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(Stream.this.spliterator());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream takeWhile(Predicate predicate) {
            return convert(Stream.this.takeWhile(predicate));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object[] toArray() {
            return Stream.this.toArray();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return Stream.this.toArray(intFunction);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ List toList() {
            return Stream.this.toList();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream unordered() {
            return f.f(Stream.this.unordered());
        }
    }

    boolean allMatch(Predicate predicate);

    boolean anyMatch(Predicate<? super T> predicate);

    <R, A> R collect(Collector<? super T, A, R> collector);

    Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2);

    long count();

    Stream d(j$.util.q qVar);

    Stream distinct();

    Stream dropWhile(Predicate predicate);

    Stream filter(Predicate predicate);

    Optional findAny();

    Optional findFirst();

    void forEach(Consumer<? super T> consumer);

    void forEachOrdered(Consumer<? super T> consumer);

    LongStream l(j$.util.q qVar);

    Stream limit(long j);

    Stream map(Function function);

    DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction);

    IntStream mapToInt(ToIntFunction toIntFunction);

    LongStream mapToLong(ToLongFunction toLongFunction);

    Optional max(Comparator comparator);

    Optional min(Comparator comparator);

    IntStream n(j$.util.q qVar);

    boolean noneMatch(Predicate predicate);

    Stream<T> peek(Consumer<? super T> consumer);

    Optional reduce(BinaryOperator binaryOperator);

    Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator);

    Object reduce(Object obj, BinaryOperator binaryOperator);

    Stream skip(long j);

    Stream sorted();

    Stream sorted(Comparator comparator);

    Stream takeWhile(Predicate predicate);

    Object[] toArray();

    Object[] toArray(IntFunction intFunction);

    List toList();

    DoubleStream y(j$.util.q qVar);

    /* JADX INFO: renamed from: j$.util.stream.Stream$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static <T> Stream<T> empty() {
            return StreamSupport.stream(Spliterators.a, false);
        }

        public static <T> Stream<T> of(T t) {
            s8 s8Var = new s8();
            s8Var.b = t;
            s8Var.a = -2;
            return StreamSupport.stream(s8Var, false);
        }
    }
}
