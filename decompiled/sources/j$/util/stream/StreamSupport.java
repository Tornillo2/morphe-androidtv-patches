package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class StreamSupport {
    public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean z) {
        Objects.requireNonNull(spliterator);
        return new d5(spliterator, c7.l(spliterator), z);
    }

    public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int i, boolean z) {
        Objects.requireNonNull(supplier);
        return new d5(supplier, i & c7.f, z);
    }

    public static IntStream intStream(Spliterator.OfInt ofInt, boolean z) {
        return new v0(ofInt, c7.l(ofInt), z);
    }

    public static LongStream longStream(Spliterator.OfLong ofLong, boolean z) {
        return new e1(ofLong, c7.l(ofLong), z);
    }

    public static DoubleStream doubleStream(Spliterator.OfDouble ofDouble, boolean z) {
        return new w(ofDouble, c7.l(ofDouble), z);
    }
}
