package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public interface DoubleStream extends BaseStream<Double, DoubleStream> {
    boolean B();

    DoubleStream a();

    OptionalDouble average();

    DoubleStream b();

    Stream<Double> boxed();

    DoubleStream c();

    <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> objDoubleConsumer, BiConsumer<R, R> biConsumer);

    long count();

    DoubleStream d(j$.util.q qVar);

    DoubleStream distinct();

    DoubleStream e();

    OptionalDouble findAny();

    OptionalDouble findFirst();

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    Iterator<Double> iterator2();

    DoubleStream limit(long j);

    Stream mapToObj(DoubleFunction doubleFunction);

    OptionalDouble max();

    OptionalDouble min();

    boolean o();

    @Override // j$.util.stream.BaseStream
    DoubleStream parallel();

    DoubleStream peek(DoubleConsumer doubleConsumer);

    double reduce(double d, DoubleBinaryOperator doubleBinaryOperator);

    OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator);

    @Override // j$.util.stream.BaseStream
    DoubleStream sequential();

    DoubleStream skip(long j);

    DoubleStream sorted();

    @Override // j$.util.stream.BaseStream
    Spliterator<Double> spliterator();

    double sum();

    j$.util.z summaryStatistics();

    boolean t();

    double[] toArray();

    LongStream u();

    IntStream z();

    /* JADX INFO: renamed from: j$.util.stream.DoubleStream$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static DoubleStream empty() {
            return StreamSupport.doubleStream(Spliterators.d, false);
        }

        public static DoubleStream of(double d) {
            o8 o8Var = new o8();
            o8Var.b = d;
            o8Var.a = -2;
            return StreamSupport.doubleStream(o8Var, false);
        }
    }
}
