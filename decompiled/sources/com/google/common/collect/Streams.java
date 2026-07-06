package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.google.common.math.LongMath;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.InlineMeValidationDisabled;
import j$.lang.Iterable$EL;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.Optional;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.OptionalLong;
import j$.util.PrimitiveIterator$OfDouble;
import j$.util.PrimitiveIterator$OfInt;
import j$.util.PrimitiveIterator$OfLong;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
@IgnoreJRERequirement
public final class Streams {

    /* JADX INFO: renamed from: com.google.common.collect.Streams$1OptionalState, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class C1OptionalState {
        public boolean set = false;
        public T value = null;

        public T get() {
            T t = this.value;
            Objects.requireNonNull(t);
            return t;
        }

        public void set(T value) {
            this.set = true;
            this.value = value;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: com.google.common.collect.Streams$1Splitr, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class C1Splitr<R, T> extends MapWithIndexSpliterator<Spliterator<T>, R, C1Splitr> implements Consumer<T> {
        public T holder;
        public final /* synthetic */ FunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$FunctionWithIndex), (r0 I:com.google.common.collect.Streams$1Splitr) (LINE:1) com.google.common.collect.Streams.1Splitr.val$function com.google.common.collect.Streams$FunctionWithIndex, block:B:2:0x0000 */
        public C1Splitr(Spliterator splitr, Spliterator<T> index, final long val$function) {
            FunctionWithIndex functionWithIndex;
            super(splitr, index);
            this.val$function = functionWithIndex;
        }

        @Override // java.util.function.Consumer
        /* JADX INFO: renamed from: accept */
        public void n(@ParametricNullness T t) {
            this.holder = t;
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        @Override // j$.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!this.fromSpliterator.tryAdvance(this)) {
                return false;
            }
            try {
                FunctionWithIndex functionWithIndex = this.val$function;
                T t = this.holder;
                long j = this.index;
                this.index = 1 + j;
                consumer.n((Object) functionWithIndex.apply(t, j));
                this.holder = null;
                return true;
            } catch (Throwable th) {
                this.holder = null;
                throw th;
            }
        }

        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C1Splitr createSplit(Spliterator<T> from, long i) {
            return new C1Splitr(from, i, this.val$function);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.google.common.collect.Streams$2Splitr, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class C2Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfInt, R, C2Splitr> implements IntConsumer {
        public int holder;
        public final /* synthetic */ IntFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$IntFunctionWithIndex), (r0 I:com.google.common.collect.Streams$2Splitr) (LINE:1) com.google.common.collect.Streams.2Splitr.val$function com.google.common.collect.Streams$IntFunctionWithIndex, block:B:2:0x0000 */
        public C2Splitr(Spliterator.OfInt splitr, Spliterator.OfInt index, final long val$function) {
            IntFunctionWithIndex intFunctionWithIndex;
            super(splitr, index);
            this.val$function = intFunctionWithIndex;
        }

        @Override // java.util.function.IntConsumer
        public void accept(int t) {
            this.holder = t;
        }

        public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return IntConsumer$CC.$default$andThen(this, intConsumer);
        }

        @Override // j$.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfInt) this.fromSpliterator).tryAdvance((IntConsumer) this)) {
                return false;
            }
            IntFunctionWithIndex intFunctionWithIndex = this.val$function;
            int i = this.holder;
            long j = this.index;
            this.index = 1 + j;
            consumer.n((Object) intFunctionWithIndex.apply(i, j));
            return true;
        }

        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C2Splitr createSplit(Spliterator.OfInt from, long i) {
            return new C2Splitr(from, i, this.val$function);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.google.common.collect.Streams$3Splitr, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class C3Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfLong, R, C3Splitr> implements LongConsumer {
        public long holder;
        public final /* synthetic */ LongFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$LongFunctionWithIndex), (r0 I:com.google.common.collect.Streams$3Splitr) (LINE:1) com.google.common.collect.Streams.3Splitr.val$function com.google.common.collect.Streams$LongFunctionWithIndex, block:B:2:0x0000 */
        public C3Splitr(Spliterator.OfLong splitr, Spliterator.OfLong index, final long val$function) {
            LongFunctionWithIndex longFunctionWithIndex;
            super(splitr, index);
            this.val$function = longFunctionWithIndex;
        }

        @Override // java.util.function.LongConsumer
        public void accept(long t) {
            this.holder = t;
        }

        public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return LongConsumer$CC.$default$andThen(this, longConsumer);
        }

        @Override // j$.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfLong) this.fromSpliterator).tryAdvance((LongConsumer) this)) {
                return false;
            }
            LongFunctionWithIndex longFunctionWithIndex = this.val$function;
            long j = this.holder;
            long j2 = this.index;
            this.index = 1 + j2;
            consumer.n((Object) longFunctionWithIndex.apply(j, j2));
            return true;
        }

        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C3Splitr createSplit(Spliterator.OfLong from, long i) {
            return new C3Splitr(from, i, this.val$function);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.google.common.collect.Streams$4Splitr, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class C4Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfDouble, R, C4Splitr> implements DoubleConsumer {
        public double holder;
        public final /* synthetic */ DoubleFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$DoubleFunctionWithIndex), (r0 I:com.google.common.collect.Streams$4Splitr) (LINE:1) com.google.common.collect.Streams.4Splitr.val$function com.google.common.collect.Streams$DoubleFunctionWithIndex, block:B:2:0x0000 */
        public C4Splitr(Spliterator.OfDouble splitr, Spliterator.OfDouble index, final long val$function) {
            DoubleFunctionWithIndex doubleFunctionWithIndex;
            super(splitr, index);
            this.val$function = doubleFunctionWithIndex;
        }

        @Override // java.util.function.DoubleConsumer
        public void accept(double t) {
            this.holder = t;
        }

        public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        @Override // j$.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfDouble) this.fromSpliterator).tryAdvance((DoubleConsumer) this)) {
                return false;
            }
            DoubleFunctionWithIndex doubleFunctionWithIndex = this.val$function;
            double d = this.holder;
            long j = this.index;
            this.index = 1 + j;
            consumer.n((Object) doubleFunctionWithIndex.apply(d, j));
            return true;
        }

        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C4Splitr createSplit(Spliterator.OfDouble from, long i) {
            return new C4Splitr(from, i, this.val$function);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DoubleFunctionWithIndex<R> {
        @ParametricNullness
        R apply(double from, long index);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface FunctionWithIndex<T, R> {
        @ParametricNullness
        R apply(@ParametricNullness T from, long index);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface IntFunctionWithIndex<R> {
        @ParametricNullness
        R apply(int from, long index);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface LongFunctionWithIndex<R> {
        @ParametricNullness
        R apply(long from, long index);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static abstract class MapWithIndexSpliterator<F extends Spliterator<?>, R, S extends MapWithIndexSpliterator<F, R, S>> implements Spliterator<R> {
        public final F fromSpliterator;
        public long index;

        public MapWithIndexSpliterator(F fromSpliterator, long index) {
            this.fromSpliterator = fromSpliterator;
            this.index = index;
        }

        @Override // j$.util.Spliterator
        public int characteristics() {
            return this.fromSpliterator.characteristics() & 16464;
        }

        public abstract S createSplit(F from, long i);

        @Override // j$.util.Spliterator
        public long estimateSize() {
            return this.fromSpliterator.estimateSize();
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.CC.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public S trySplit() {
            Spliterator spliteratorTrySplit = this.fromSpliterator.trySplit();
            if (spliteratorTrySplit == null) {
                return null;
            }
            S s = (S) createSplit(spliteratorTrySplit, this.index);
            this.index = spliteratorTrySplit.getExactSizeIfKnown() + this.index;
            return s;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TemporaryPair<A, B> {

        @ParametricNullness
        public final A a;

        @ParametricNullness
        public final B b;

        public TemporaryPair(@ParametricNullness A a, @ParametricNullness B b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void closeAll(BaseStream<?, ?>[] toClose) throws Exception {
        Exception exc = null;
        for (BaseStream<?, ?> baseStream : toClose) {
            try {
                baseStream.close();
            } catch (Exception e) {
                if (exc == null) {
                    exc = e;
                } else {
                    exc.addSuppressed(e);
                }
            }
        }
        if (exc != null) {
            throw exc;
        }
    }

    @SafeVarargs
    public static <T> Stream<T> concat(final Stream<? extends T>... streams) {
        ImmutableList.Builder builder = new ImmutableList.Builder(streams.length);
        long jSaturatedAdd = 0;
        boolean zIsParallel = false;
        int iCharacteristics = 336;
        for (Stream<? extends T> stream : streams) {
            zIsParallel |= stream.isParallel();
            Spliterator<? extends T> spliterator = stream.spliterator();
            builder.add(spliterator);
            iCharacteristics &= spliterator.characteristics();
            jSaturatedAdd = LongMath.saturatedAdd(jSaturatedAdd, spliterator.estimateSize());
        }
        return StreamSupport.stream(CollectSpliterators.flatMap(builder.build().spliterator(), new Streams$$ExternalSyntheticLambda6(), iCharacteristics, jSaturatedAdd), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() throws Exception {
                Streams.closeAll(streams);
            }
        });
    }

    public static <T> Optional<T> findLast(Stream<T> stream) {
        final C1OptionalState c1OptionalState = new C1OptionalState();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addLast(stream.spliterator());
        while (!arrayDeque.isEmpty()) {
            Spliterator<T> spliterator = (Spliterator) arrayDeque.removeLast();
            if (spliterator.getExactSizeIfKnown() != 0) {
                if (spliterator.hasCharacteristics(16384)) {
                    while (true) {
                        Spliterator<T> spliteratorTrySplit = spliterator.trySplit();
                        if (spliteratorTrySplit == null || spliteratorTrySplit.getExactSizeIfKnown() == 0) {
                            break;
                        }
                        if (spliterator.getExactSizeIfKnown() == 0) {
                            spliterator = spliteratorTrySplit;
                            break;
                        }
                    }
                    spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda13
                        @Override // java.util.function.Consumer
                        /* JADX INFO: renamed from: accept */
                        public final void n(Object obj) {
                            c1OptionalState.set(obj);
                        }

                        public /* synthetic */ Consumer andThen(Consumer consumer) {
                            return Consumer$CC.$default$andThen(this, consumer);
                        }
                    });
                    T t = c1OptionalState.value;
                    Objects.requireNonNull(t);
                    return Optional.of(t);
                }
                Spliterator<T> spliteratorTrySplit2 = spliterator.trySplit();
                if (spliteratorTrySplit2 == null || spliteratorTrySplit2.getExactSizeIfKnown() == 0) {
                    spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda13
                        @Override // java.util.function.Consumer
                        /* JADX INFO: renamed from: accept */
                        public final void n(Object obj) {
                            c1OptionalState.set(obj);
                        }

                        public /* synthetic */ Consumer andThen(Consumer consumer) {
                            return Consumer$CC.$default$andThen(this, consumer);
                        }
                    });
                    if (c1OptionalState.set) {
                        T t2 = c1OptionalState.value;
                        Objects.requireNonNull(t2);
                        return Optional.of(t2);
                    }
                } else {
                    arrayDeque.addLast(spliteratorTrySplit2);
                    arrayDeque.addLast(spliterator);
                }
            }
        }
        return Optional.empty();
    }

    @Beta
    public static <A, B> void forEachPair(Stream<A> streamA, Stream<B> streamB, final BiConsumer<? super A, ? super B> consumer) {
        consumer.getClass();
        if (streamA.isParallel() || streamB.isParallel()) {
            zip(streamA, streamB, new Streams$$ExternalSyntheticLambda11()).forEach(new Consumer() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda12
                @Override // java.util.function.Consumer
                /* JADX INFO: renamed from: accept */
                public final void n(Object obj) {
                    Streams.TemporaryPair temporaryPair = (Streams.TemporaryPair) obj;
                    consumer.accept(temporaryPair.a, temporaryPair.b);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer2) {
                    return Consumer$CC.$default$andThen(this, consumer2);
                }
            });
            return;
        }
        Iterator<A> it = streamA.iterator2();
        Iterator<B> it2 = streamB.iterator2();
        while (it.hasNext() && it2.hasNext()) {
            consumer.accept(it.next(), it2.next());
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [j$.util.Spliterator, j$.util.Spliterator$OfDouble] */
    public static <R> Stream<R> mapWithIndex(final DoubleStream stream, final DoubleFunctionWithIndex<R> function) {
        stream.getClass();
        function.getClass();
        boolean zIsParallel = stream.isParallel();
        ?? Spliterator = stream.spliterator();
        if (Spliterator.hasCharacteristics(16384)) {
            return (Stream) StreamSupport.stream(new C4Splitr(Spliterator, 0L, function), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    stream.close();
                }
            });
        }
        final PrimitiveIterator$OfDouble it = Spliterators.iterator((Spliterator.OfDouble) Spliterator);
        return (Stream) StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.5
            public long index = 0;

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.CC.$default$forEachRemaining(this, consumer);
            }

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ Comparator getComparator() {
                return Spliterator.CC.$default$getComparator(this);
            }

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ long getExactSizeIfKnown() {
                return Spliterator.CC.$default$getExactSizeIfKnown(this);
            }

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ boolean hasCharacteristics(int i) {
                return Spliterator.CC.$default$hasCharacteristics(this, i);
            }

            @Override // j$.util.Spliterator
            public boolean tryAdvance(Consumer<? super R> consumer) {
                if (!it.hasNext()) {
                    return false;
                }
                DoubleFunctionWithIndex doubleFunctionWithIndex = function;
                double dNextDouble = it.nextDouble();
                long j = this.index;
                this.index = 1 + j;
                consumer.n((Object) doubleFunctionWithIndex.apply(dNextDouble, j));
                return true;
            }
        }, zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                stream.close();
            }
        });
    }

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return iterable instanceof Collection ? Collection.EL.stream((java.util.Collection) iterable) : StreamSupport.stream(Iterable$EL.spliterator(iterable), false);
    }

    @Beta
    public static <A, B, R> Stream<R> zip(Stream<A> streamA, Stream<B> streamB, final BiFunction<? super A, ? super B, R> function) {
        streamA.getClass();
        streamB.getClass();
        function.getClass();
        boolean z = streamA.isParallel() || streamB.isParallel();
        Spliterator<A> spliterator = streamA.spliterator();
        Spliterator<B> spliterator2 = streamB.spliterator();
        int iCharacteristics = spliterator.characteristics() & spliterator2.characteristics() & 80;
        final Iterator it = Spliterators.iterator(spliterator);
        final Iterator it2 = Spliterators.iterator(spliterator2);
        return (Stream) StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Math.min(spliterator.estimateSize(), spliterator2.estimateSize()), iCharacteristics) { // from class: com.google.common.collect.Streams.1
            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.CC.$default$forEachRemaining(this, consumer);
            }

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ Comparator getComparator() {
                return Spliterator.CC.$default$getComparator(this);
            }

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ long getExactSizeIfKnown() {
                return Spliterator.CC.$default$getExactSizeIfKnown(this);
            }

            @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
            public /* synthetic */ boolean hasCharacteristics(int i) {
                return Spliterator.CC.$default$hasCharacteristics(this, i);
            }

            @Override // j$.util.Spliterator
            public boolean tryAdvance(Consumer<? super R> consumer) {
                if (!it.hasNext() || !it2.hasNext()) {
                    return false;
                }
                consumer.n((Object) function.apply(it.next(), it2.next()));
                return true;
            }
        }, z).onClose(new Streams$$ExternalSyntheticLambda5(streamA)).onClose(new Streams$$ExternalSyntheticLambda5(streamB));
    }

    @InlineMe(replacement = "collection.stream()")
    @Deprecated
    public static <T> Stream<T> stream(java.util.Collection<T> collection) {
        return Collection.EL.stream(collection);
    }

    public static <T> Stream<T> stream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    public static <T> Stream<T> stream(com.google.common.base.Optional<T> optional) {
        return optional.isPresent() ? Stream.CC.of(optional.get()) : Stream.CC.empty();
    }

    @Beta
    @InlineMe(replacement = "optional.stream()")
    @InlineMeValidationDisabled("Java 9+ API only")
    public static <T> Stream<T> stream(Optional<T> optional) {
        return optional.isPresent() ? Stream.CC.of(optional.get()) : Stream.CC.empty();
    }

    @Beta
    @InlineMe(replacement = "optional.stream()")
    @InlineMeValidationDisabled("Java 9+ API only")
    public static IntStream stream(OptionalInt optional) {
        return optional.isPresent() ? IntStream.CC.of(optional.getAsInt()) : IntStream.CC.empty();
    }

    @Beta
    @InlineMe(replacement = "optional.stream()")
    @InlineMeValidationDisabled("Java 9+ API only")
    public static LongStream stream(OptionalLong optional) {
        return optional.isPresent() ? LongStream.CC.of(optional.getAsLong()) : LongStream.CC.empty();
    }

    @Beta
    @InlineMe(replacement = "optional.stream()")
    @InlineMeValidationDisabled("Java 9+ API only")
    public static DoubleStream stream(OptionalDouble optional) {
        return optional.isPresent() ? DoubleStream.CC.of(optional.getAsDouble()) : DoubleStream.CC.empty();
    }

    public static IntStream concat(final IntStream... streams) {
        ImmutableList.Builder builder = new ImmutableList.Builder(streams.length);
        long jSaturatedAdd = 0;
        boolean zIsParallel = false;
        int iCharacteristics = 336;
        for (IntStream intStream : streams) {
            zIsParallel |= intStream.isParallel();
            Spliterator<Integer> spliterator = intStream.spliterator();
            builder.add(spliterator);
            iCharacteristics &= spliterator.characteristics();
            jSaturatedAdd = LongMath.saturatedAdd(jSaturatedAdd, spliterator.estimateSize());
        }
        return StreamSupport.intStream(CollectSpliterators.flatMapToInt(builder.build().spliterator(), new Streams$$ExternalSyntheticLambda9(), iCharacteristics, jSaturatedAdd), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() throws Exception {
                Streams.closeAll(streams);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [j$.util.Spliterator, j$.util.Spliterator$OfInt] */
    public static <R> Stream<R> mapWithIndex(final IntStream stream, final IntFunctionWithIndex<R> function) {
        stream.getClass();
        function.getClass();
        boolean zIsParallel = stream.isParallel();
        ?? Spliterator = stream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator$OfInt it = Spliterators.iterator((Spliterator.OfInt) Spliterator);
            return (Stream) StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.3
                public long index = 0;

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ void forEachRemaining(Consumer consumer) {
                    Spliterator.CC.$default$forEachRemaining(this, consumer);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ Comparator getComparator() {
                    return Spliterator.CC.$default$getComparator(this);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ long getExactSizeIfKnown() {
                    return Spliterator.CC.$default$getExactSizeIfKnown(this);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ boolean hasCharacteristics(int i) {
                    return Spliterator.CC.$default$hasCharacteristics(this, i);
                }

                @Override // j$.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    IntFunctionWithIndex intFunctionWithIndex = function;
                    int iNextInt = it.nextInt();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.n((Object) intFunctionWithIndex.apply(iNextInt, j));
                    return true;
                }
            }, zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    stream.close();
                }
            });
        }
        return (Stream) StreamSupport.stream(new C2Splitr(Spliterator, 0L, function), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                stream.close();
            }
        });
    }

    public static /* synthetic */ Spliterator.OfDouble $r8$lambda$E9SX_Kp0hwaPVx9WCNImHaCQeH4(Spliterator.OfDouble ofDouble) {
        return ofDouble;
    }

    public static /* synthetic */ Spliterator.OfInt $r8$lambda$Mb2MhAMMewXc2neUxLdIdwNb4qw(Spliterator.OfInt ofInt) {
        return ofInt;
    }

    public static /* synthetic */ Spliterator.OfLong $r8$lambda$Y3Gyf9RWqrFPOlpqZF6Gqq03ElQ(Spliterator.OfLong ofLong) {
        return ofLong;
    }

    public static /* synthetic */ Spliterator $r8$lambda$q29gWsZFtkLPjcWl_wlLgY1_bms(Spliterator spliterator) {
        return spliterator;
    }

    public static OptionalInt findLast(IntStream stream) {
        return (OptionalInt) findLast(stream.boxed()).map(new Streams$$ExternalSyntheticLambda0()).orElse(OptionalInt.empty());
    }

    public static LongStream concat(final LongStream... streams) {
        ImmutableList.Builder builder = new ImmutableList.Builder(streams.length);
        long jSaturatedAdd = 0;
        boolean zIsParallel = false;
        int iCharacteristics = 336;
        for (LongStream longStream : streams) {
            zIsParallel |= longStream.isParallel();
            Spliterator<Long> spliterator = longStream.spliterator();
            builder.add(spliterator);
            iCharacteristics &= spliterator.characteristics();
            jSaturatedAdd = LongMath.saturatedAdd(jSaturatedAdd, spliterator.estimateSize());
        }
        return StreamSupport.longStream(CollectSpliterators.flatMapToLong(builder.build().spliterator(), new Streams$$ExternalSyntheticLambda3(), iCharacteristics, jSaturatedAdd), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws Exception {
                Streams.closeAll(streams);
            }
        });
    }

    public static OptionalLong findLast(LongStream stream) {
        return (OptionalLong) findLast(stream.boxed()).map(new Streams$$ExternalSyntheticLambda8()).orElse(OptionalLong.empty());
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [j$.util.Spliterator, j$.util.Spliterator$OfLong] */
    public static <R> Stream<R> mapWithIndex(final LongStream stream, final LongFunctionWithIndex<R> function) {
        stream.getClass();
        function.getClass();
        boolean zIsParallel = stream.isParallel();
        ?? Spliterator = stream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator$OfLong it = Spliterators.iterator((Spliterator.OfLong) Spliterator);
            return (Stream) StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.4
                public long index = 0;

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ void forEachRemaining(Consumer consumer) {
                    Spliterator.CC.$default$forEachRemaining(this, consumer);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ Comparator getComparator() {
                    return Spliterator.CC.$default$getComparator(this);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ long getExactSizeIfKnown() {
                    return Spliterator.CC.$default$getExactSizeIfKnown(this);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ boolean hasCharacteristics(int i) {
                    return Spliterator.CC.$default$hasCharacteristics(this, i);
                }

                @Override // j$.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    LongFunctionWithIndex longFunctionWithIndex = function;
                    long jNextLong = it.nextLong();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.n((Object) longFunctionWithIndex.apply(jNextLong, j));
                    return true;
                }
            }, zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    stream.close();
                }
            });
        }
        return (Stream) StreamSupport.stream(new C3Splitr(Spliterator, 0L, function), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                stream.close();
            }
        });
    }

    public static OptionalDouble findLast(DoubleStream stream) {
        return (OptionalDouble) findLast(stream.boxed()).map(new Streams$$ExternalSyntheticLambda15()).orElse(OptionalDouble.empty());
    }

    public static DoubleStream concat(final DoubleStream... streams) {
        ImmutableList.Builder builder = new ImmutableList.Builder(streams.length);
        long jSaturatedAdd = 0;
        boolean zIsParallel = false;
        int iCharacteristics = 336;
        for (DoubleStream doubleStream : streams) {
            zIsParallel |= doubleStream.isParallel();
            Spliterator<Double> spliterator = doubleStream.spliterator();
            builder.add(spliterator);
            iCharacteristics &= spliterator.characteristics();
            jSaturatedAdd = LongMath.saturatedAdd(jSaturatedAdd, spliterator.estimateSize());
        }
        return StreamSupport.doubleStream(CollectSpliterators.flatMapToDouble(builder.build().spliterator(), new Streams$$ExternalSyntheticLambda1(), iCharacteristics, jSaturatedAdd), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.Streams$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws Exception {
                Streams.closeAll(streams);
            }
        });
    }

    public static <T, R> Stream<R> mapWithIndex(Stream<T> stream, final FunctionWithIndex<? super T, ? extends R> functionWithIndex) {
        stream.getClass();
        functionWithIndex.getClass();
        boolean zIsParallel = stream.isParallel();
        Spliterator<T> spliterator = stream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            final Iterator it = Spliterators.iterator(spliterator);
            return StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(spliterator.estimateSize(), spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.2
                public long index = 0;

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ void forEachRemaining(Consumer consumer) {
                    Spliterator.CC.$default$forEachRemaining(this, consumer);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ Comparator getComparator() {
                    return Spliterator.CC.$default$getComparator(this);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ long getExactSizeIfKnown() {
                    return Spliterator.CC.$default$getExactSizeIfKnown(this);
                }

                @Override // j$.util.Spliterators.AbstractSpliterator, j$.util.Spliterator
                public /* synthetic */ boolean hasCharacteristics(int i) {
                    return Spliterator.CC.$default$hasCharacteristics(this, i);
                }

                @Override // j$.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    FunctionWithIndex functionWithIndex2 = functionWithIndex;
                    Object next = it.next();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.n((Object) functionWithIndex2.apply(next, j));
                    return true;
                }
            }, zIsParallel).onClose(new Streams$$ExternalSyntheticLambda5(stream));
        }
        return StreamSupport.stream(new C1Splitr(spliterator, 0L, functionWithIndex), zIsParallel).onClose(new Streams$$ExternalSyntheticLambda5(stream));
    }
}
