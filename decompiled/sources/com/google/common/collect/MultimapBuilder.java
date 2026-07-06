package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Multimaps;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class MultimapBuilder<K0, V0> {
    public static final int DEFAULT_EXPECTED_KEYS = 8;

    /* JADX INFO: renamed from: com.google.common.collect.MultimapBuilder$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 extends MultimapBuilderWithKeys<K0> {
        public final /* synthetic */ Class val$keyClass;

        public AnonymousClass4(final Class val$keyClass) {
            this.val$keyClass = val$keyClass;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K extends K0, V> Map<K, Collection<V>> createMap() {
            return new EnumMap(this.val$keyClass);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ArrayListSupplier<V> implements Supplier<List<V>>, Serializable {
        public final int expectedValuesPerKey;

        public ArrayListSupplier(int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            this.expectedValuesPerKey = expectedValuesPerKey;
        }

        @Override // com.google.common.base.Supplier
        public List<V> get() {
            return new ArrayList(this.expectedValuesPerKey);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EnumSetSupplier<V extends Enum<V>> implements Supplier<Set<V>>, Serializable {
        public final Class<V> clazz;

        public EnumSetSupplier(Class<V> clazz) {
            clazz.getClass();
            this.clazz = clazz;
        }

        @Override // com.google.common.base.Supplier
        public Object get() {
            return EnumSet.noneOf(this.clazz);
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return EnumSet.noneOf(this.clazz);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LinkedListSupplier implements Supplier<List<?>> {
        INSTANCE;

        public static /* synthetic */ LinkedListSupplier[] $values() {
            return new LinkedListSupplier[]{INSTANCE};
        }

        public static <V> Supplier<List<V>> instance() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Supplier
        public List<?> get() {
            return new LinkedList();
        }

        @Override // com.google.common.base.Supplier
        public List<?> get() {
            return new LinkedList();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class MultimapBuilderWithKeys<K0> {
        public static final int DEFAULT_EXPECTED_VALUES_PER_KEY = 2;

        public ListMultimapBuilder<K0, Object> arrayListValues() {
            return arrayListValues(2);
        }

        public abstract <K extends K0, V> Map<K, Collection<V>> createMap();

        public <V0 extends Enum<V0>> SetMultimapBuilder<K0, V0> enumSetValues(final Class<V0> valueClass) {
            Preconditions.checkNotNull(valueClass, "valueClass");
            return new SetMultimapBuilder<K0, V0>(this) { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.6
                public final /* synthetic */ MultimapBuilderWithKeys this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V extends V0> SetMultimap<K, V> build() {
                    EnumSetSupplier enumSetSupplier = new EnumSetSupplier(valueClass);
                    Multimaps.CustomSetMultimap customSetMultimap = new Multimaps.CustomSetMultimap(this.this$0.createMap());
                    customSetMultimap.factory = enumSetSupplier;
                    return customSetMultimap;
                }
            };
        }

        public SetMultimapBuilder<K0, Object> hashSetValues() {
            return hashSetValues(2);
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues() {
            return linkedHashSetValues(2);
        }

        public ListMultimapBuilder<K0, Object> linkedListValues() {
            return new ListMultimapBuilder<K0, Object>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.2
                @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> ListMultimap<K, V> build() {
                    Map<K, Collection<V>> mapCreateMap = MultimapBuilderWithKeys.this.createMap();
                    LinkedListSupplier linkedListSupplier = LinkedListSupplier.INSTANCE;
                    Multimaps.CustomListMultimap customListMultimap = new Multimaps.CustomListMultimap(mapCreateMap);
                    linkedListSupplier.getClass();
                    customListMultimap.factory = linkedListSupplier;
                    return customListMultimap;
                }
            };
        }

        public SortedSetMultimapBuilder<K0, Comparable> treeSetValues() {
            return treeSetValues(NaturalOrdering.INSTANCE);
        }

        public ListMultimapBuilder<K0, Object> arrayListValues(final int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            return new ListMultimapBuilder<K0, Object>(this) { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.1
                public final /* synthetic */ MultimapBuilderWithKeys this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> ListMultimap<K, V> build() {
                    Map<K, Collection<V>> mapCreateMap = this.this$0.createMap();
                    ArrayListSupplier arrayListSupplier = new ArrayListSupplier(expectedValuesPerKey);
                    Multimaps.CustomListMultimap customListMultimap = new Multimaps.CustomListMultimap(mapCreateMap);
                    customListMultimap.factory = arrayListSupplier;
                    return customListMultimap;
                }
            };
        }

        public SetMultimapBuilder<K0, Object> hashSetValues(final int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>(this) { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.3
                public final /* synthetic */ MultimapBuilderWithKeys this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> SetMultimap<K, V> build() {
                    Map<K, Collection<V>> mapCreateMap = this.this$0.createMap();
                    HashSetSupplier hashSetSupplier = new HashSetSupplier(expectedValuesPerKey);
                    Multimaps.CustomSetMultimap customSetMultimap = new Multimaps.CustomSetMultimap(mapCreateMap);
                    customSetMultimap.factory = hashSetSupplier;
                    return customSetMultimap;
                }
            };
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues(final int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>(this) { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.4
                public final /* synthetic */ MultimapBuilderWithKeys this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> SetMultimap<K, V> build() {
                    Map<K, Collection<V>> mapCreateMap = this.this$0.createMap();
                    LinkedHashSetSupplier linkedHashSetSupplier = new LinkedHashSetSupplier(expectedValuesPerKey);
                    Multimaps.CustomSetMultimap customSetMultimap = new Multimaps.CustomSetMultimap(mapCreateMap);
                    customSetMultimap.factory = linkedHashSetSupplier;
                    return customSetMultimap;
                }
            };
        }

        public <V0> SortedSetMultimapBuilder<K0, V0> treeSetValues(final Comparator<V0> comparator) {
            Preconditions.checkNotNull(comparator, "comparator");
            return new SortedSetMultimapBuilder<K0, V0>(this) { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.5
                public final /* synthetic */ MultimapBuilderWithKeys this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.MultimapBuilder.SortedSetMultimapBuilder, com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V extends V0> SortedSetMultimap<K, V> build() {
                    return new Multimaps.CustomSortedSetMultimap(this.this$0.createMap(), new TreeSetSupplier(comparator));
                }
            };
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TreeSetSupplier<V> implements Supplier<SortedSet<V>>, Serializable {
        public final Comparator<? super V> comparator;

        public TreeSetSupplier(Comparator<? super V> comparator) {
            comparator.getClass();
            this.comparator = comparator;
        }

        @Override // com.google.common.base.Supplier
        public SortedSet<V> get() {
            return new TreeSet(this.comparator);
        }
    }

    public MultimapBuilder() {
    }

    public static <K0 extends Enum<K0>> MultimapBuilderWithKeys<K0> enumKeys(Class<K0> keyClass) {
        keyClass.getClass();
        return new AnonymousClass4(keyClass);
    }

    public static MultimapBuilderWithKeys<Object> hashKeys() {
        return hashKeys(8);
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys() {
        return linkedHashKeys(8);
    }

    public static MultimapBuilderWithKeys<Comparable> treeKeys() {
        return treeKeys(NaturalOrdering.INSTANCE);
    }

    public abstract <K extends K0, V extends V0> Multimap<K, V> build();

    public <K extends K0, V extends V0> Multimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
        Multimap<K, V> multimapBuild = build();
        multimapBuild.putAll(multimap);
        return multimapBuild;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class HashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
        public final int expectedValuesPerKey;

        public HashSetSupplier(int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            this.expectedValuesPerKey = expectedValuesPerKey;
        }

        @Override // com.google.common.base.Supplier
        public Object get() {
            return CompactHashSet.createWithExpectedSize(this.expectedValuesPerKey);
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return CompactHashSet.createWithExpectedSize(this.expectedValuesPerKey);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LinkedHashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
        public final int expectedValuesPerKey;

        public LinkedHashSetSupplier(int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            this.expectedValuesPerKey = expectedValuesPerKey;
        }

        @Override // com.google.common.base.Supplier
        public Object get() {
            return CompactLinkedHashSet.createWithExpectedSize(this.expectedValuesPerKey);
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return CompactLinkedHashSet.createWithExpectedSize(this.expectedValuesPerKey);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ListMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        @Override // com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> ListMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> ListMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (ListMultimap) super.build((Multimap) multimap);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SetMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        @Override // com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> SetMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> SetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (SetMultimap) super.build((Multimap) multimap);
        }
    }

    public MultimapBuilder(AnonymousClass1 anonymousClass1) {
    }

    public static MultimapBuilderWithKeys<Object> hashKeys(final int expectedKeys) {
        CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>() { // from class: com.google.common.collect.MultimapBuilder.1
            @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
            public <K, V> Map<K, Collection<V>> createMap() {
                return CompactHashMap.createWithExpectedSize(expectedKeys);
            }
        };
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys(final int expectedKeys) {
        CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>() { // from class: com.google.common.collect.MultimapBuilder.2
            @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
            public <K, V> Map<K, Collection<V>> createMap() {
                return CompactLinkedHashMap.createWithExpectedSize(expectedKeys);
            }
        };
    }

    public static <K0> MultimapBuilderWithKeys<K0> treeKeys(final Comparator<K0> comparator) {
        comparator.getClass();
        return new MultimapBuilderWithKeys<K0>() { // from class: com.google.common.collect.MultimapBuilder.3
            @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
            public <K extends K0, V> Map<K, Collection<V>> createMap() {
                return new TreeMap(comparator);
            }
        };
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SortedSetMultimapBuilder<K0, V0> extends SetMultimapBuilder<K0, V0> {
        @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> SortedSetMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> SortedSetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (SortedSetMultimap) super.build((Multimap) multimap);
        }
    }
}
