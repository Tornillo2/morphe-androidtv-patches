package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.MultimapBuilder;
import j$.util.Map;
import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.stream.Collector;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
@IgnoreJRERequirement
public final class CollectCollectors {
    public static final Collector<Object, ?, ImmutableList<Object>> TO_IMMUTABLE_LIST = Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda17(), new CollectCollectors$$ExternalSyntheticLambda20(), new CollectCollectors$$ExternalSyntheticLambda21(), new CollectCollectors$$ExternalSyntheticLambda22(), new Collector.Characteristics[0]);
    public static final Collector<Object, ?, ImmutableSet<Object>> TO_IMMUTABLE_SET = Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda23(), new CollectCollectors$$ExternalSyntheticLambda24(), new CollectCollectors$$ExternalSyntheticLambda25(), new CollectCollectors$$ExternalSyntheticLambda26(), new Collector.Characteristics[0]);

    @GwtIncompatible
    public static final Collector<Range<Comparable<?>>, ?, ImmutableRangeSet<Comparable<?>>> TO_IMMUTABLE_RANGE_SET = Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda27(), new CollectCollectors$$ExternalSyntheticLambda28(), new CollectCollectors$$ExternalSyntheticLambda18(), new CollectCollectors$$ExternalSyntheticLambda19(), new Collector.Characteristics[0]);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static class EnumMapAccumulator<K extends Enum<K>, V> {
        public EnumMap<K, V> map = null;
        public final BinaryOperator<V> mergeFunction;

        public EnumMapAccumulator(BinaryOperator<V> mergeFunction) {
            this.mergeFunction = mergeFunction;
        }

        public EnumMapAccumulator<K, V> combine(EnumMapAccumulator<K, V> other) {
            if (this.map == null) {
                return other;
            }
            EnumMap<K, V> enumMap = other.map;
            if (enumMap == null) {
                return this;
            }
            Map.EL.forEach(enumMap, new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$EnumMapAccumulator$$ExternalSyntheticLambda0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.put((Enum) obj, obj2);
                }

                public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                    return BiConsumer$CC.$default$andThen(this, biConsumer);
                }
            });
            return this;
        }

        public void put(K key, V value) {
            EnumMap<K, V> enumMap = this.map;
            if (enumMap == null) {
                this.map = new EnumMap<>(Collections.singletonMap(key, value));
            } else {
                Map.EL.merge(enumMap, key, value, this.mergeFunction);
            }
        }

        public ImmutableMap<K, V> toImmutableMap() {
            EnumMap<K, V> enumMap = this.map;
            return enumMap == null ? (ImmutableMap<K, V>) RegularImmutableMap.EMPTY : ImmutableEnumMap.asImmutable(enumMap);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class EnumSetAccumulator<E extends Enum<E>> {
        public static final Collector<Enum<?>, ?, ImmutableSet<? extends Enum<?>>> TO_IMMUTABLE_ENUM_SET = CollectCollectors.toImmutableEnumSetGeneric();
        public EnumSet<E> set;

        public EnumSetAccumulator() {
        }

        public void add(E e) {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                this.set = EnumSet.of((Enum) e);
            } else {
                enumSet.add(e);
            }
        }

        public EnumSetAccumulator<E> combine(EnumSetAccumulator<E> other) {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                return other;
            }
            EnumSet<E> enumSet2 = other.set;
            if (enumSet2 == null) {
                return this;
            }
            enumSet.addAll(enumSet2);
            return this;
        }

        public ImmutableSet<E> toImmutableSet() {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                return ImmutableSet.of();
            }
            ImmutableSet<E> immutableSetAsImmutable = ImmutableEnumSet.asImmutable(enumSet);
            this.set = null;
            return immutableSetAsImmutable;
        }

        public EnumSetAccumulator(AnonymousClass1 anonymousClass1) {
        }
    }

    public static ImmutableSortedMap.Builder $r8$lambda$8aJvpZleY0o2mQPiKKeZotwRqIg(Comparator comparator) {
        return new ImmutableSortedMap.Builder(comparator, 4);
    }

    public static void $r8$lambda$FYdNppJ7O7qSuFGL1iLionXb1mw(Function function, ToIntFunction toIntFunction, Multiset multiset, Object obj) {
        Object objApply = function.apply(obj);
        objApply.getClass();
        multiset.add(objApply, toIntFunction.applyAsInt(obj));
    }

    public static /* synthetic */ EnumMapAccumulator $r8$lambda$GzQjHM_6uE2w7X2yo482lUpUc8M() {
        return new EnumMapAccumulator(new CollectCollectors$$ExternalSyntheticLambda0());
    }

    public static Object $r8$lambda$HSgMwDsZYwXY760293mX5biimcg(Function function, Object obj) {
        Object objApply = function.apply(obj);
        objApply.getClass();
        return objApply;
    }

    public static /* synthetic */ ImmutableSortedSet.Builder $r8$lambda$JcFkuqhtTBVLwxIpW3JNm1P0zfQ(Comparator comparator) {
        return new ImmutableSortedSet.Builder(comparator);
    }

    /* JADX INFO: renamed from: $r8$lambda$K_PICa7vQP-Vnd1CMyOTvJJ4FJQ, reason: not valid java name */
    public static /* synthetic */ Multimap m502$r8$lambda$K_PICa7vQPVnd1CMyOTvJJ4FJQ(Multimap multimap, Multimap multimap2) {
        multimap.putAll(multimap2);
        return multimap;
    }

    public static Object $r8$lambda$Zf5RQ_KlFzNt8ngOSrleldJw3_U(Function function, Object obj) {
        Object objApply = function.apply(obj);
        objApply.getClass();
        return objApply;
    }

    /* JADX INFO: renamed from: $r8$lambda$eTJ7F-IZQjWSVxpWWLlYnEd3qIM, reason: not valid java name */
    public static /* synthetic */ Multiset m503$r8$lambda$eTJ7FIZQjWSVxpWWLlYnEd3qIM(Multiset multiset, Multiset multiset2) {
        multiset.addAll(multiset2);
        return multiset;
    }

    public static /* synthetic */ void $r8$lambda$fdLPItj9rVXIYnNMYJXAV3LsHB4(Function function, Function function2, EnumMapAccumulator enumMapAccumulator, Object obj) {
        Enum r1 = (Enum) function.apply(obj);
        Object objApply = function2.apply(obj);
        Preconditions.checkNotNull(r1, "Null key for input %s", obj);
        Preconditions.checkNotNull(objApply, "Null value for input %s", obj);
        enumMapAccumulator.put(r1, objApply);
    }

    public static /* synthetic */ Multiset $r8$lambda$gIfFnfKhv10KffU7fEhM8bsVAQQ(Multiset multiset, Multiset multiset2) {
        multiset.addAll(multiset2);
        return multiset;
    }

    /* JADX INFO: renamed from: $r8$lambda$hD8-sIS_B-WqNik3M8YKAeHZJss, reason: not valid java name */
    public static /* synthetic */ TreeMap m504$r8$lambda$hD8sIS_BWqNik3M8YKAeHZJss(Comparator comparator) {
        return new TreeMap(comparator);
    }

    public static EnumSetAccumulator $r8$lambda$hclcd1MzLdzqIpyTa_8YAW68HKE() {
        return new EnumSetAccumulator();
    }

    public static /* synthetic */ void $r8$lambda$p9R64A7HMlGDc_YHornqiuE6ZhA(Function function, Function function2, Multimap multimap, Object obj) {
        final Collection collection = multimap.get(function.apply(obj));
        Stream stream = (Stream) function2.apply(obj);
        Objects.requireNonNull(collection);
        stream.forEachOrdered(new Consumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda68
            @Override // java.util.function.Consumer
            /* JADX INFO: renamed from: accept */
            public final void n(Object obj2) {
                collection.add(obj2);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    public static /* synthetic */ Object $r8$lambda$s_6x6y1tPx2oLGP6k0rZmxuBckY(Object obj, Object obj2) {
        throw new IllegalArgumentException("Multiple values for key: " + obj + ", " + obj2);
    }

    /* JADX INFO: renamed from: $r8$lambda$sdBxzZmHX-qeqdpD9c-R0eFtPC4, reason: not valid java name */
    public static /* synthetic */ void m506$r8$lambda$sdBxzZmHXqeqdpD9cR0eFtPC4(Function function, Function function2, EnumMapAccumulator enumMapAccumulator, Object obj) {
        Enum r1 = (Enum) function.apply(obj);
        Object objApply = function2.apply(obj);
        Preconditions.checkNotNull(r1, "Null key for input %s", obj);
        Preconditions.checkNotNull(objApply, "Null value for input %s", obj);
        enumMapAccumulator.put(r1, objApply);
    }

    /* JADX INFO: renamed from: $r8$lambda$vfW5TDRP5AmMudKeAEK-tp5jBnY, reason: not valid java name */
    public static /* synthetic */ EnumMapAccumulator m507$r8$lambda$vfW5TDRP5AmMudKeAEKtp5jBnY(BinaryOperator binaryOperator) {
        return new EnumMapAccumulator(binaryOperator);
    }

    /* JADX INFO: renamed from: $r8$lambda$wo-T5fBV04R1oWLhFYWZeZFUaNM, reason: not valid java name */
    public static /* synthetic */ Multimap m508$r8$lambda$woT5fBV04R1oWLhFYWZeZFUaNM(Multimap multimap, Multimap multimap2) {
        multimap.putAll(multimap2);
        return multimap;
    }

    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> flatteningToImmutableListMultimap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends Stream<? extends V>> valuesFunction) {
        keyFunction.getClass();
        valuesFunction.getClass();
        Function function = new Function() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda31
            @Override // java.util.function.Function
            /* JADX INFO: renamed from: andThen */
            public /* synthetic */ Function mo567andThen(Function function2) {
                return Function$CC.$default$andThen(this, function2);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CollectCollectors.$r8$lambda$Zf5RQ_KlFzNt8ngOSrleldJw3_U(keyFunction, obj);
            }

            public /* synthetic */ Function compose(Function function2) {
                return Function$CC.$default$compose(this, function2);
            }
        };
        Function function2 = new Function() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda32
            @Override // java.util.function.Function
            /* JADX INFO: renamed from: andThen */
            public /* synthetic */ Function mo567andThen(Function function3) {
                return Function$CC.$default$andThen(this, function3);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Stream) valuesFunction.apply(obj)).peek(new CollectCollectors$$ExternalSyntheticLambda45());
            }

            public /* synthetic */ Function compose(Function function3) {
                return Function$CC.$default$compose(this, function3);
            }
        };
        final MultimapBuilder.ListMultimapBuilder<Object, Object> listMultimapBuilderArrayListValues = MultimapBuilder.linkedHashKeys(8).arrayListValues(2);
        return Collectors.collectingAndThen(flatteningToMultimap(function, function2, new Supplier() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda33
            @Override // java.util.function.Supplier
            public final Object get() {
                return listMultimapBuilderArrayListValues.build();
            }
        }), new CollectCollectors$$ExternalSyntheticLambda34());
    }

    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> flatteningToImmutableSetMultimap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends Stream<? extends V>> valuesFunction) {
        keyFunction.getClass();
        valuesFunction.getClass();
        Function function = new Function() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda39
            @Override // java.util.function.Function
            /* JADX INFO: renamed from: andThen */
            public /* synthetic */ Function mo567andThen(Function function2) {
                return Function$CC.$default$andThen(this, function2);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CollectCollectors.$r8$lambda$HSgMwDsZYwXY760293mX5biimcg(keyFunction, obj);
            }

            public /* synthetic */ Function compose(Function function2) {
                return Function$CC.$default$compose(this, function2);
            }
        };
        Function function2 = new Function() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda40
            @Override // java.util.function.Function
            /* JADX INFO: renamed from: andThen */
            public /* synthetic */ Function mo567andThen(Function function3) {
                return Function$CC.$default$andThen(this, function3);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Stream) valuesFunction.apply(obj)).peek(new CollectCollectors$$ExternalSyntheticLambda45());
            }

            public /* synthetic */ Function compose(Function function3) {
                return Function$CC.$default$compose(this, function3);
            }
        };
        final MultimapBuilder.SetMultimapBuilder<Object, Object> setMultimapBuilderLinkedHashSetValues = MultimapBuilder.linkedHashKeys(8).linkedHashSetValues(2);
        return Collectors.collectingAndThen(flatteningToMultimap(function, function2, new Supplier() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda41
            @Override // java.util.function.Supplier
            public final Object get() {
                return setMultimapBuilderLinkedHashSetValues.build();
            }
        }), new CollectCollectors$$ExternalSyntheticLambda42());
    }

    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends Stream<? extends V>> valueFunction, Supplier<M> multimapSupplier) {
        keyFunction.getClass();
        valueFunction.getClass();
        multimapSupplier.getClass();
        return Collector.CC.of(multimapSupplier, new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda29
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CollectCollectors.$r8$lambda$p9R64A7HMlGDc_YHornqiuE6ZhA(keyFunction, valueFunction, (Multimap) obj, obj2);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda30(), new Collector.Characteristics[0]);
    }

    public static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        keyFunction.getClass();
        valueFunction.getClass();
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda9(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda10
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableBiMap.Builder) obj).put(keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda11(), new CollectCollectors$$ExternalSyntheticLambda12(), new Collector.Characteristics[0]);
    }

    public static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        keyFunction.getClass();
        valueFunction.getClass();
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda46(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda47
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CollectCollectors.$r8$lambda$fdLPItj9rVXIYnNMYJXAV3LsHB4(keyFunction, valueFunction, (CollectCollectors.EnumMapAccumulator) obj, obj2);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda48(), new CollectCollectors$$ExternalSyntheticLambda49(), Collector.Characteristics.UNORDERED);
    }

    public static <E extends Enum<E>> Collector<E, ?, ImmutableSet<E>> toImmutableEnumSet() {
        return (Collector<E, ?, ImmutableSet<E>>) EnumSetAccumulator.TO_IMMUTABLE_ENUM_SET;
    }

    public static <E extends Enum<E>> Collector<E, EnumSetAccumulator<E>, ImmutableSet<E>> toImmutableEnumSetGeneric() {
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda13(), new CollectCollectors$$ExternalSyntheticLambda14(), new CollectCollectors$$ExternalSyntheticLambda15(), new CollectCollectors$$ExternalSyntheticLambda16(), Collector.Characteristics.UNORDERED);
    }

    public static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
        return (Collector<E, ?, ImmutableList<E>>) TO_IMMUTABLE_LIST;
    }

    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> toImmutableListMultimap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(keyFunction, "keyFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda58(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda59
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableListMultimap.Builder) obj).put(keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda60(), new CollectCollectors$$ExternalSyntheticLambda61(), new Collector.Characteristics[0]);
    }

    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        keyFunction.getClass();
        valueFunction.getClass();
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda50(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda51
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableMap.Builder) obj).put(keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda52(), new CollectCollectors$$ExternalSyntheticLambda53(), new Collector.Characteristics[0]);
    }

    public static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(final Function<? super T, ? extends E> elementFunction, final ToIntFunction<? super T> countFunction) {
        elementFunction.getClass();
        countFunction.getClass();
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda35(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda36
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CollectCollectors.$r8$lambda$FYdNppJ7O7qSuFGL1iLionXb1mw(elementFunction, countFunction, (Multiset) obj, obj2);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda37(), new CollectCollectors$$ExternalSyntheticLambda38(), new Collector.Characteristics[0]);
    }

    @GwtIncompatible
    public static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(final Function<? super T, Range<K>> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        keyFunction.getClass();
        valueFunction.getClass();
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda1(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableRangeMap.Builder) obj).put((Range) keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda3(), new CollectCollectors$$ExternalSyntheticLambda4(), new Collector.Characteristics[0]);
    }

    @GwtIncompatible
    public static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
        return (Collector<Range<E>, ?, ImmutableRangeSet<E>>) TO_IMMUTABLE_RANGE_SET;
    }

    public static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return (Collector<E, ?, ImmutableSet<E>>) TO_IMMUTABLE_SET;
    }

    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> toImmutableSetMultimap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(keyFunction, "keyFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        return Collector.CC.of(new CollectCollectors$$ExternalSyntheticLambda62(), new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda63
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSetMultimap.Builder) obj).put(keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda64(), new CollectCollectors$$ExternalSyntheticLambda65(), new Collector.Characteristics[0]);
    }

    public static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(final Comparator<? super K> comparator, final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction) {
        comparator.getClass();
        keyFunction.getClass();
        valueFunction.getClass();
        return Collector.CC.of(new Supplier() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.$r8$lambda$8aJvpZleY0o2mQPiKKeZotwRqIg(comparator);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSortedMap.Builder) obj).put(keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda7(), new CollectCollectors$$ExternalSyntheticLambda8(), Collector.Characteristics.UNORDERED);
    }

    public static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(final Comparator<? super E> comparator) {
        comparator.getClass();
        return Collector.CC.of(new Supplier() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda54
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.$r8$lambda$JcFkuqhtTBVLwxIpW3JNm1P0zfQ(comparator);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda55(), new CollectCollectors$$ExternalSyntheticLambda56(), new CollectCollectors$$ExternalSyntheticLambda57(), new Collector.Characteristics[0]);
    }

    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction, Supplier<M> multimapSupplier) {
        keyFunction.getClass();
        valueFunction.getClass();
        multimapSupplier.getClass();
        return Collector.CC.of(multimapSupplier, new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda73
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Multimap) obj).put(keyFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda74(), new Collector.Characteristics[0]);
    }

    public static <T, E, M extends Multiset<E>> Collector<T, ?, M> toMultiset(final Function<? super T, E> elementFunction, final ToIntFunction<? super T> countFunction, Supplier<M> multisetSupplier) {
        elementFunction.getClass();
        countFunction.getClass();
        multisetSupplier.getClass();
        return Collector.CC.of(multisetSupplier, new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda69
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Multiset) obj).add(elementFunction.apply(obj2), countFunction.applyAsInt(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda70(), new Collector.Characteristics[0]);
    }

    public static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(final Function<? super T, ? extends K> keyFunction, final Function<? super T, ? extends V> valueFunction, final BinaryOperator<V> mergeFunction) {
        keyFunction.getClass();
        valueFunction.getClass();
        mergeFunction.getClass();
        return Collector.CC.of(new Supplier() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda71
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.m507$r8$lambda$vfW5TDRP5AmMudKeAEKtp5jBnY(mergeFunction);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda72
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CollectCollectors.m506$r8$lambda$sdBxzZmHXqeqdpD9cR0eFtPC4(keyFunction, valueFunction, (CollectCollectors.EnumMapAccumulator) obj, obj2);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new CollectCollectors$$ExternalSyntheticLambda48(), new CollectCollectors$$ExternalSyntheticLambda49(), new Collector.Characteristics[0]);
    }

    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        keyFunction.getClass();
        valueFunction.getClass();
        mergeFunction.getClass();
        return Collectors.collectingAndThen(Collectors.toMap(keyFunction, valueFunction, mergeFunction, new CollectCollectors$$ExternalSyntheticLambda66()), new CollectCollectors$$ExternalSyntheticLambda67());
    }

    public static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(final Comparator<? super K> comparator, Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        comparator.getClass();
        keyFunction.getClass();
        valueFunction.getClass();
        mergeFunction.getClass();
        return Collectors.collectingAndThen(Collectors.toMap(keyFunction, valueFunction, mergeFunction, new Supplier() { // from class: com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda43
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.m504$r8$lambda$hD8sIS_BWqNik3M8YKAeHZJss(comparator);
            }
        }), new CollectCollectors$$ExternalSyntheticLambda44());
    }
}
