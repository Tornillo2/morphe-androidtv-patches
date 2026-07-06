package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public interface LongStream extends BaseStream<Long, LongStream> {
    IntStream A();

    LongStream a();

    DoubleStream asDoubleStream();

    OptionalDouble average();

    LongStream b();

    Stream<Long> boxed();

    LongStream c();

    <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> objLongConsumer, BiConsumer<R, R> biConsumer);

    long count();

    LongStream d(j$.util.q qVar);

    LongStream distinct();

    LongStream e();

    OptionalLong findAny();

    OptionalLong findFirst();

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    @Override // j$.util.stream.BaseStream
    /* JADX INFO: renamed from: iterator */
    Iterator<Long> iterator2();

    DoubleStream j();

    LongStream limit(long j);

    boolean m();

    Stream mapToObj(LongFunction longFunction);

    OptionalLong max();

    OptionalLong min();

    @Override // j$.util.stream.BaseStream
    LongStream parallel();

    LongStream peek(LongConsumer longConsumer);

    boolean q();

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    OptionalLong reduce(LongBinaryOperator longBinaryOperator);

    @Override // j$.util.stream.BaseStream
    LongStream sequential();

    LongStream skip(long j);

    LongStream sorted();

    @Override // j$.util.stream.BaseStream
    Spliterator<Long> spliterator();

    long sum();

    j$.util.c0 summaryStatistics();

    long[] toArray();

    boolean w();

    /* JADX INFO: renamed from: j$.util.stream.LongStream$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static LongStream empty() {
            return StreamSupport.longStream(Spliterators.c, false);
        }

        public static LongStream of(long j) {
            q8 q8Var = new q8();
            q8Var.b = j;
            q8Var.a = -2;
            return StreamSupport.longStream(q8Var, false);
        }
    }
}
