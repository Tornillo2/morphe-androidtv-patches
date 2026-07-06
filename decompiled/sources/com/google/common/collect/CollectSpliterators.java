package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.j2objc.annotations.Weak;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.stream.IntStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
@IgnoreJRERequirement
public final class CollectSpliterators {

    /* JADX INFO: Add missing generic type declarations: [OutElementT] */
    /* JADX INFO: renamed from: com.google.common.collect.CollectSpliterators$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1<OutElementT> implements Spliterator<OutElementT> {
        public final /* synthetic */ Spliterator val$fromSpliterator;
        public final /* synthetic */ Function val$function;

        public AnonymousClass1(final Spliterator val$fromSpliterator, final Function val$function) {
            this.val$fromSpliterator = val$fromSpliterator;
            this.val$function = val$function;
        }

        @Override // j$.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & (-262);
        }

        @Override // j$.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize();
        }

        @Override // j$.util.Spliterator
        public void forEachRemaining(final Consumer<? super OutElementT> action) {
            Spliterator spliterator = this.val$fromSpliterator;
            final Function function = this.val$function;
            spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.CollectSpliterators$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    action.accept(function.apply(obj));
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
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
        public boolean tryAdvance(final Consumer<? super OutElementT> action) {
            Spliterator spliterator = this.val$fromSpliterator;
            final Function function = this.val$function;
            return spliterator.tryAdvance(new Consumer() { // from class: com.google.common.collect.CollectSpliterators$1$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    action.accept(function.apply(obj));
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        }

        @Override // j$.util.Spliterator
        public Spliterator<OutElementT> trySplit() {
            Spliterator spliteratorTrySplit = this.val$fromSpliterator.trySplit();
            if (spliteratorTrySplit != null) {
                return CollectSpliterators.map(spliteratorTrySplit, this.val$function);
            }
            return null;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.CollectSpliterators$1Splitr, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public class C1Splitr<T> implements Spliterator<T>, Consumer<T> {
        public T holder = null;
        public final /* synthetic */ Spliterator val$fromSpliterator;
        public final /* synthetic */ Predicate val$predicate;

        public C1Splitr(final Spliterator val$fromSpliterator, final Predicate val$predicate) {
            this.val$fromSpliterator = val$fromSpliterator;
            this.val$predicate = val$predicate;
        }

        @Override // java.util.function.Consumer
        public void accept(@ParametricNullness T t) {
            this.holder = t;
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        @Override // j$.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & 277;
        }

        @Override // j$.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize() / 2;
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.CC.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public Comparator<? super T> getComparator() {
            return this.val$fromSpliterator.getComparator();
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // j$.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            while (this.val$fromSpliterator.tryAdvance(this)) {
                try {
                    T t = this.holder;
                    if (this.val$predicate.test(t)) {
                        consumer.accept(t);
                        this.holder = null;
                        return true;
                    }
                } finally {
                    this.holder = null;
                }
            }
            return false;
        }

        @Override // j$.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator<T> spliteratorTrySplit = this.val$fromSpliterator.trySplit();
            if (spliteratorTrySplit == null) {
                return null;
            }
            return CollectSpliterators.filter(spliteratorTrySplit, this.val$predicate);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.CollectSpliterators$1WithCharacteristics, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public class C1WithCharacteristics<T> implements Spliterator<T> {
        public final Spliterator.OfInt delegate;
        public final /* synthetic */ Comparator val$comparator;
        public final /* synthetic */ int val$extraCharacteristics;
        public final /* synthetic */ IntFunction val$function;

        public C1WithCharacteristics(Spliterator.OfInt delegate, final IntFunction val$function, final int val$extraCharacteristics, final Comparator val$comparator) {
            this.val$function = val$function;
            this.val$extraCharacteristics = val$extraCharacteristics;
            this.val$comparator = val$comparator;
            this.delegate = delegate;
        }

        @Override // j$.util.Spliterator
        public int characteristics() {
            return this.val$extraCharacteristics | 16464;
        }

        @Override // j$.util.Spliterator
        public long estimateSize() {
            return this.delegate.estimateSize();
        }

        @Override // j$.util.Spliterator
        public void forEachRemaining(final Consumer<? super T> action) {
            Spliterator.OfInt ofInt = this.delegate;
            final IntFunction intFunction = this.val$function;
            ofInt.forEachRemaining(new IntConsumer() { // from class: com.google.common.collect.CollectSpliterators$1WithCharacteristics$$ExternalSyntheticLambda0
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    action.accept(intFunction.apply(i));
                }

                public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                    return IntConsumer$CC.$default$andThen(this, intConsumer);
                }
            });
        }

        @Override // j$.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return this.val$comparator;
            }
            throw new IllegalStateException();
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
        public boolean tryAdvance(final Consumer<? super T> action) {
            Spliterator.OfInt ofInt = this.delegate;
            final IntFunction intFunction = this.val$function;
            return ofInt.tryAdvance(new IntConsumer() { // from class: com.google.common.collect.CollectSpliterators$1WithCharacteristics$$ExternalSyntheticLambda1
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    action.accept(intFunction.apply(i));
                }

                public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                    return IntConsumer$CC.$default$andThen(this, intConsumer);
                }
            });
        }

        @Override // j$.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator.OfInt ofIntTrySplit = this.delegate.trySplit();
            if (ofIntTrySplit == null) {
                return null;
            }
            return new C1WithCharacteristics(ofIntTrySplit, this.val$function, this.val$extraCharacteristics, this.val$comparator);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static abstract class FlatMapSpliterator<InElementT, OutElementT, OutSpliteratorT extends Spliterator<OutElementT>> implements Spliterator<OutElementT> {
        public int characteristics;
        public long estimatedSize;
        public final Factory<InElementT, OutSpliteratorT> factory;
        public final Spliterator<InElementT> from;
        public final Function<? super InElementT, OutSpliteratorT> function;

        @Weak
        public OutSpliteratorT prefix;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @IgnoreJRERequirement
        public interface Factory<InElementT, OutSpliteratorT extends Spliterator<?>> {
            OutSpliteratorT newFlatMapSpliterator(OutSpliteratorT prefix, Spliterator<InElementT> fromSplit, Function<? super InElementT, OutSpliteratorT> function, int splitCharacteristics, long estSplitSize);
        }

        public static /* synthetic */ void $r8$lambda$p9ptQIkLXWjRnMw2XxQkmK61RTg(FlatMapSpliterator flatMapSpliterator, Consumer consumer, Object obj) {
            OutSpliteratorT outspliteratortApply = flatMapSpliterator.function.apply(obj);
            if (outspliteratortApply != null) {
                outspliteratortApply.forEachRemaining(consumer);
            }
        }

        public FlatMapSpliterator(OutSpliteratorT prefix, Spliterator<InElementT> from, Function<? super InElementT, OutSpliteratorT> function, Factory<InElementT, OutSpliteratorT> factory, int characteristics, long estimatedSize) {
            this.prefix = prefix;
            this.from = from;
            this.function = function;
            this.factory = factory;
            this.characteristics = characteristics;
            this.estimatedSize = estimatedSize;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            OutSpliteratorT outspliteratort = this.prefix;
            if (outspliteratort != null) {
                this.estimatedSize = Math.max(this.estimatedSize, outspliteratort.estimateSize());
            }
            return Math.max(this.estimatedSize, 0L);
        }

        @Override // j$.util.Spliterator
        public void forEachRemaining(final Consumer<? super OutElementT> action) {
            OutSpliteratorT outspliteratort = this.prefix;
            if (outspliteratort != null) {
                outspliteratort.forEachRemaining(action);
                this.prefix = null;
            }
            this.from.forEachRemaining(new Consumer() { // from class: com.google.common.collect.CollectSpliterators$FlatMapSpliterator$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliterator.$r8$lambda$p9ptQIkLXWjRnMw2XxQkmK61RTg(this.f$0, action, obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            this.estimatedSize = 0L;
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
        public boolean tryAdvance(Consumer<? super OutElementT> action) {
            do {
                OutSpliteratorT outspliteratort = this.prefix;
                if (outspliteratort != null && outspliteratort.tryAdvance(action)) {
                    long j = this.estimatedSize;
                    if (j == Long.MAX_VALUE) {
                        return true;
                    }
                    this.estimatedSize = j - 1;
                    return true;
                }
                this.prefix = null;
            } while (this.from.tryAdvance(new Consumer() { // from class: com.google.common.collect.CollectSpliterators$FlatMapSpliterator$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliterator flatMapSpliterator = this.f$0;
                    flatMapSpliterator.prefix = flatMapSpliterator.function.apply(obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            }));
            return false;
        }

        @Override // j$.util.Spliterator
        public final OutSpliteratorT trySplit() {
            Spliterator<InElementT> spliteratorTrySplit = this.from.trySplit();
            if (spliteratorTrySplit == null) {
                OutSpliteratorT outspliteratort = this.prefix;
                if (outspliteratort == null) {
                    return null;
                }
                this.prefix = null;
                return outspliteratort;
            }
            int i = this.characteristics & (-65);
            long jEstimateSize = estimateSize();
            if (jEstimateSize < Long.MAX_VALUE) {
                jEstimateSize /= 2;
                this.estimatedSize -= jEstimateSize;
                this.characteristics = i;
            }
            OutSpliteratorT outspliteratort2 = (OutSpliteratorT) this.factory.newFlatMapSpliterator(this.prefix, spliteratorTrySplit, this.function, i, jEstimateSize);
            this.prefix = null;
            return outspliteratort2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class FlatMapSpliteratorOfDouble<InElementT> extends FlatMapSpliteratorOfPrimitive<InElementT, Double, DoubleConsumer, Spliterator.OfDouble> implements Spliterator.OfDouble {
        public FlatMapSpliteratorOfDouble(Spliterator.OfDouble prefix, Spliterator<InElementT> from, Function<? super InElementT, Spliterator.OfDouble> function, int characteristics, long estimatedSize) {
            super(prefix, (Spliterator) from, function, (FlatMapSpliterator.Factory<InElementT, Spliterator.OfDouble>) new CollectSpliterators$FlatMapSpliteratorOfDouble$$ExternalSyntheticLambda0(), characteristics, estimatedSize);
        }

        @Override // j$.util.Spliterator.OfDouble
        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer action) {
            forEachRemaining(action);
        }

        @Override // j$.util.Spliterator.OfDouble
        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer action) {
            return tryAdvance(action);
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliteratorOfPrimitive, com.google.common.collect.CollectSpliterators.FlatMapSpliterator, j$.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) trySplit();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class FlatMapSpliteratorOfInt<InElementT> extends FlatMapSpliteratorOfPrimitive<InElementT, Integer, IntConsumer, Spliterator.OfInt> implements Spliterator.OfInt {
        public FlatMapSpliteratorOfInt(Spliterator.OfInt prefix, Spliterator<InElementT> from, Function<? super InElementT, Spliterator.OfInt> function, int characteristics, long estimatedSize) {
            super(prefix, (Spliterator) from, function, (FlatMapSpliterator.Factory<InElementT, Spliterator.OfInt>) new CollectSpliterators$FlatMapSpliteratorOfInt$$ExternalSyntheticLambda0(), characteristics, estimatedSize);
        }

        @Override // j$.util.Spliterator.OfInt
        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer action) {
            forEachRemaining(action);
        }

        @Override // j$.util.Spliterator.OfInt
        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer action) {
            return tryAdvance(action);
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliteratorOfPrimitive, com.google.common.collect.CollectSpliterators.FlatMapSpliterator, j$.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) trySplit();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class FlatMapSpliteratorOfLong<InElementT> extends FlatMapSpliteratorOfPrimitive<InElementT, Long, LongConsumer, Spliterator.OfLong> implements Spliterator.OfLong {
        public FlatMapSpliteratorOfLong(Spliterator.OfLong prefix, Spliterator<InElementT> from, Function<? super InElementT, Spliterator.OfLong> function, int characteristics, long estimatedSize) {
            super(prefix, (Spliterator) from, function, (FlatMapSpliterator.Factory<InElementT, Spliterator.OfLong>) new CollectSpliterators$FlatMapSpliteratorOfLong$$ExternalSyntheticLambda0(), characteristics, estimatedSize);
        }

        @Override // j$.util.Spliterator.OfLong
        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer action) {
            forEachRemaining(action);
        }

        @Override // j$.util.Spliterator.OfLong
        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer action) {
            return tryAdvance(action);
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliteratorOfPrimitive, com.google.common.collect.CollectSpliterators.FlatMapSpliterator, j$.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) trySplit();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class FlatMapSpliteratorOfObject<InElementT, OutElementT> extends FlatMapSpliterator<InElementT, OutElementT, Spliterator<OutElementT>> {
        public FlatMapSpliteratorOfObject(Spliterator<OutElementT> prefix, Spliterator<InElementT> from, Function<? super InElementT, Spliterator<OutElementT>> function, int characteristics, long estimatedSize) {
            super(prefix, from, function, new CollectSpliterators$FlatMapSpliteratorOfObject$$ExternalSyntheticLambda0(), characteristics, estimatedSize);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static abstract class FlatMapSpliteratorOfPrimitive<InElementT, OutElementT, OutConsumerT, OutSpliteratorT extends Spliterator.OfPrimitive<OutElementT, OutConsumerT, OutSpliteratorT>> extends FlatMapSpliterator<InElementT, OutElementT, OutSpliteratorT> implements Spliterator.OfPrimitive<OutElementT, OutConsumerT, OutSpliteratorT> {
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public static /* synthetic */ void $r8$lambda$QNM_4RYPvQyw1uaWihSBlKMwM0s(FlatMapSpliteratorOfPrimitive flatMapSpliteratorOfPrimitive, Object obj, Object obj2) {
            Spliterator.OfPrimitive ofPrimitive = (Spliterator.OfPrimitive) flatMapSpliteratorOfPrimitive.function.apply((Object) obj2);
            if (ofPrimitive != null) {
                ofPrimitive.forEachRemaining(obj);
            }
        }

        public FlatMapSpliteratorOfPrimitive(OutSpliteratorT prefix, Spliterator<InElementT> from, Function<? super InElementT, OutSpliteratorT> function, FlatMapSpliterator.Factory<InElementT, OutSpliteratorT> factory, int characteristics, long estimatedSize) {
            super(prefix, from, function, factory, characteristics, estimatedSize);
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(final OutConsumerT action) {
            OutSpliteratorT outspliteratort = this.prefix;
            if (outspliteratort != 0) {
                ((Spliterator.OfPrimitive) outspliteratort).forEachRemaining(action);
                this.prefix = null;
            }
            this.from.forEachRemaining(new Consumer() { // from class: com.google.common.collect.CollectSpliterators$FlatMapSpliteratorOfPrimitive$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliteratorOfPrimitive.$r8$lambda$QNM_4RYPvQyw1uaWihSBlKMwM0s(this.f$0, action, obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            this.estimatedSize = 0L;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(OutConsumerT action) {
            do {
                OutSpliteratorT outspliteratort = this.prefix;
                if (outspliteratort != 0 && ((Spliterator.OfPrimitive) outspliteratort).tryAdvance(action)) {
                    long j = this.estimatedSize;
                    if (j == Long.MAX_VALUE) {
                        return true;
                    }
                    this.estimatedSize = j - 1;
                    return true;
                }
                this.prefix = null;
            } while (this.from.tryAdvance(new Consumer() { // from class: com.google.common.collect.CollectSpliterators$FlatMapSpliteratorOfPrimitive$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliteratorOfPrimitive flatMapSpliteratorOfPrimitive = this.f$0;
                    flatMapSpliteratorOfPrimitive.prefix = (OutSpliteratorT) flatMapSpliteratorOfPrimitive.function.apply((Object) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            }));
            return false;
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator, j$.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return trySplit();
        }
    }

    public static <T> Spliterator<T> filter(Spliterator<T> fromSpliterator, Predicate<? super T> predicate) {
        fromSpliterator.getClass();
        predicate.getClass();
        return new C1Splitr(fromSpliterator, predicate);
    }

    public static <InElementT, OutElementT> Spliterator<OutElementT> flatMap(Spliterator<InElementT> fromSpliterator, Function<? super InElementT, Spliterator<OutElementT>> function, int topCharacteristics, long topSize) {
        Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
        fromSpliterator.getClass();
        function.getClass();
        return new FlatMapSpliteratorOfObject(null, fromSpliterator, function, topCharacteristics, topSize);
    }

    public static <InElementT> Spliterator.OfDouble flatMapToDouble(Spliterator<InElementT> fromSpliterator, Function<? super InElementT, Spliterator.OfDouble> function, int topCharacteristics, long topSize) {
        Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
        fromSpliterator.getClass();
        function.getClass();
        return new FlatMapSpliteratorOfDouble(null, fromSpliterator, function, topCharacteristics, topSize);
    }

    public static <InElementT> Spliterator.OfInt flatMapToInt(Spliterator<InElementT> fromSpliterator, Function<? super InElementT, Spliterator.OfInt> function, int topCharacteristics, long topSize) {
        Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
        fromSpliterator.getClass();
        function.getClass();
        return new FlatMapSpliteratorOfInt(null, fromSpliterator, function, topCharacteristics, topSize);
    }

    public static <InElementT> Spliterator.OfLong flatMapToLong(Spliterator<InElementT> fromSpliterator, Function<? super InElementT, Spliterator.OfLong> function, int topCharacteristics, long topSize) {
        Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
        fromSpliterator.getClass();
        function.getClass();
        return new FlatMapSpliteratorOfLong(null, fromSpliterator, function, topCharacteristics, topSize);
    }

    public static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function) {
        return indexed(size, extraCharacteristics, function, null);
    }

    public static <InElementT, OutElementT> Spliterator<OutElementT> map(Spliterator<InElementT> fromSpliterator, Function<? super InElementT, ? extends OutElementT> function) {
        fromSpliterator.getClass();
        function.getClass();
        return new AnonymousClass1(fromSpliterator, function);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [j$.util.Spliterator$OfInt] */
    public static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function, Comparator<? super T> comparator) {
        if (comparator != null) {
            Preconditions.checkArgument((extraCharacteristics & 4) != 0);
        }
        return new C1WithCharacteristics(IntStream.CC.range(0, size).spliterator(), function, extraCharacteristics, comparator);
    }
}
