package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Cut;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import j$.util.Objects;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    public static final RangeMap<Comparable<?>, Object> EMPTY_SUB_RANGE_MAP = new AnonymousClass1();
    public final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsMapOfRanges extends Maps.IteratorBasedAbstractMap<Range<K>, V> {
        public final Iterable<Map.Entry<Range<K>, V>> entryIterable;

        public AsMapOfRanges(Iterable<RangeMapEntry<K, V>> entryIterable) {
            this.entryIterable = entryIterable;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return get(key) != null;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
            return this.entryIterable.iterator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            if (!(key instanceof Range)) {
                return null;
            }
            Range range = (Range) key;
            RangeMapEntry<K, V> rangeMapEntry = TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
            if (rangeMapEntry == null || !rangeMapEntry.range.equals(range)) {
                return null;
            }
            return rangeMapEntry.value;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return TreeRangeMap.this.entriesByLowerBound.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
        public final Range<K> range;
        public final V value;

        public RangeMapEntry(Range<K> range, V value) {
            this.range = range;
            this.value = value;
        }

        public boolean contains(K value) {
            return this.range.contains(value);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public Range<K> getKey() {
            return this.range;
        }

        public Cut<K> getLowerBound() {
            return (Cut<K>) this.range.lowerBound;
        }

        public Cut<K> getUpperBound() {
            return (Cut<K>) this.range.upperBound;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public Object getKey() {
            return this.range;
        }

        public RangeMapEntry(Cut<K> lowerBound, Cut<K> upperBound, V value) {
            this(Range.create(lowerBound, upperBound), value);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SubRangeMap implements RangeMap<K, V> {
        public final Range<K> subRange;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class SubRangeMapAsMap extends AbstractMap<Range<K>, V> {

            /* JADX INFO: renamed from: com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap$2, reason: invalid class name */
            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            public class AnonymousClass2 extends Maps.EntrySet<Range<K>, V> {
                public AnonymousClass2() {
                }

                @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean isEmpty() {
                    return !iterator().hasNext();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<Range<K>, V>> iterator() {
                    return SubRangeMapAsMap.this.entryIterator();
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public Map<Range<K>, V> map() {
                    return SubRangeMapAsMap.this;
                }

                @Override // com.google.common.collect.Maps.EntrySet, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> c) {
                    return SubRangeMapAsMap.this.removeEntryIf(new Predicates.NotPredicate(new Predicates.InPredicate(c)));
                }

                @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return Iterators.size(iterator());
                }
            }

            public SubRangeMapAsMap() {
            }

            @Override // java.util.AbstractMap, java.util.Map
            public void clear() {
                SubRangeMap.this.clear();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public boolean containsKey(Object key) {
                return get(key) != null;
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
                if (SubRangeMap.this.subRange.isEmpty()) {
                    return Iterators.ArrayItr.EMPTY;
                }
                SubRangeMap subRangeMap = SubRangeMap.this;
                final Iterator<RangeMapEntry<K, V>> it = TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.this.entriesByLowerBound.floorKey((Cut<K>) subRangeMap.subRange.lowerBound), SubRangeMap.this.subRange.lowerBound), true).values().iterator();
                return new AbstractIterator<Map.Entry<Range<K>, V>>(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.3
                    public final /* synthetic */ SubRangeMapAsMap this$2;

                    {
                        this.this$2 = this;
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
                    @Override // com.google.common.collect.AbstractIterator
                    public Map.Entry<Range<K>, V> computeNext() {
                        while (it.hasNext()) {
                            RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                            if (rangeMapEntry.range.lowerBound.compareTo((Cut) SubRangeMap.this.subRange.upperBound) >= 0) {
                                endOfData();
                                return null;
                            }
                            if (rangeMapEntry.range.upperBound.compareTo((Cut) SubRangeMap.this.subRange.lowerBound) > 0) {
                                return new ImmutableEntry(rangeMapEntry.range.intersection(SubRangeMap.this.subRange), rangeMapEntry.value);
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Map.Entry<Range<K>, V>> entrySet() {
                return new AnonymousClass2();
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
            @Override // java.util.AbstractMap, java.util.Map
            public V get(Object obj) {
                RangeMapEntry<K, V> rangeMapEntry;
                try {
                    if (obj instanceof Range) {
                        Range range = (Range) obj;
                        if (SubRangeMap.this.subRange.encloses(range) && !range.isEmpty()) {
                            if (range.lowerBound.compareTo((Cut) SubRangeMap.this.subRange.lowerBound) == 0) {
                                Map.Entry entryFloorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(range.lowerBound);
                                rangeMapEntry = entryFloorEntry != null ? (RangeMapEntry) entryFloorEntry.getValue() : null;
                            } else {
                                rangeMapEntry = TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                            }
                            if (rangeMapEntry != null && rangeMapEntry.range.isConnected(SubRangeMap.this.subRange) && rangeMapEntry.range.intersection(SubRangeMap.this.subRange).equals(range)) {
                                return rangeMapEntry.value;
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Range<K>> keySet() {
                return new Maps.KeySet<Range<K>, V>(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.1
                    @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public boolean remove(Object o) {
                        return SubRangeMapAsMap.this.remove(o) != null;
                    }

                    @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public boolean retainAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(new Predicates.CompositionPredicate(new Predicates.NotPredicate(new Predicates.InPredicate(c)), Maps.EntryFunction.KEY));
                    }
                };
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V remove(Object obj) {
                V v = (V) get(obj);
                if (v == null) {
                    return null;
                }
                Objects.requireNonNull(obj);
                TreeRangeMap.this.remove((Range) obj);
                return v;
            }

            public final boolean removeEntryIf(Predicate<? super Map.Entry<Range<K>, V>> predicate) {
                ArrayList arrayList = new ArrayList();
                Iterator<Map.Entry<Range<K>, V>> itEntryIterator = SubRangeMapAsMap.this.entryIterator();
                while (itEntryIterator.hasNext()) {
                    Map.Entry<Range<K>, V> next = itEntryIterator.next();
                    if (predicate.apply(next)) {
                        arrayList.add(next.getKey());
                    }
                }
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    TreeRangeMap.this.remove((Range) obj);
                }
                return !arrayList.isEmpty();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Collection<V> values() {
                return new Maps.Values<Range<K>, V>(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.4
                    @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                    public boolean removeAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(new Predicates.CompositionPredicate(new Predicates.InPredicate(c), Maps.EntryFunction.VALUE));
                    }

                    @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                    public boolean retainAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(new Predicates.CompositionPredicate(new Predicates.NotPredicate(new Predicates.InPredicate(c)), Maps.EntryFunction.VALUE));
                    }
                };
            }
        }

        public SubRangeMap(Range<K> subRange) {
            this.subRange = subRange;
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asDescendingMapOfRanges() {
            return new TreeRangeMap<K, V>.SubRangeMap.SubRangeMapAsMap() { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.1
                /* JADX WARN: Type inference incomplete: some casts might be missing */
                @Override // com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap
                public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
                    if (SubRangeMap.this.subRange.isEmpty()) {
                        return Iterators.ArrayItr.EMPTY;
                    }
                    SubRangeMap subRangeMap = SubRangeMap.this;
                    final Iterator<RangeMapEntry<K, V>> it = TreeRangeMap.this.entriesByLowerBound.headMap((Cut<K>) subRangeMap.subRange.upperBound, false).descendingMap().values().iterator();
                    return new AbstractIterator<Map.Entry<Range<K>, V>>(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.1.1
                        public final /* synthetic */ AnonymousClass1 this$2;

                        {
                            this.this$2 = this;
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
                        @Override // com.google.common.collect.AbstractIterator
                        public Map.Entry<Range<K>, V> computeNext() {
                            if (!it.hasNext()) {
                                endOfData();
                                return null;
                            }
                            RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                            if (rangeMapEntry.range.upperBound.compareTo((Cut) SubRangeMap.this.subRange.lowerBound) > 0) {
                                return new ImmutableEntry(rangeMapEntry.range.intersection(SubRangeMap.this.subRange), rangeMapEntry.value);
                            }
                            endOfData();
                            return null;
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asMapOfRanges() {
            return new SubRangeMapAsMap();
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
            TreeRangeMap.this.remove(this.subRange);
        }

        @Override // com.google.common.collect.RangeMap
        public boolean equals(Object o) {
            if (!(o instanceof RangeMap)) {
                return false;
            }
            return ((AbstractMap) asMapOfRanges()).equals(((RangeMap) o).asMapOfRanges());
        }

        @Override // com.google.common.collect.RangeMap
        public V get(K key) {
            Map.Entry<Range<K>, V> entry;
            if (!this.subRange.contains(key) || (entry = TreeRangeMap.this.getEntry(key)) == null) {
                return null;
            }
            return entry.getValue();
        }

        @Override // com.google.common.collect.RangeMap
        public Map.Entry<Range<K>, V> getEntry(K k) {
            Map.Entry<Range<K>, V> entry;
            if (!this.subRange.contains(k) || (entry = TreeRangeMap.this.getEntry(k)) == null) {
                return null;
            }
            return new ImmutableEntry(entry.getKey().intersection(this.subRange), entry.getValue());
        }

        @Override // com.google.common.collect.RangeMap
        public int hashCode() {
            return ((AbstractMap) asMapOfRanges()).hashCode();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range<K> range, V value) {
            Preconditions.checkArgument(this.subRange.encloses(range), "Cannot put range %s into a subRangeMap(%s)", range, this.subRange);
            TreeRangeMap.this.put(range, value);
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap<K, ? extends V> rangeMap) {
            if (rangeMap.asMapOfRanges().isEmpty()) {
                return;
            }
            Range<K> rangeSpan = rangeMap.span();
            Preconditions.checkArgument(this.subRange.encloses(rangeSpan), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", rangeSpan, this.subRange);
            TreeRangeMap.this.putAll(rangeMap);
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range<K> range, V v) {
            if (TreeRangeMap.this.entriesByLowerBound.isEmpty() || !this.subRange.encloses(range)) {
                put(range, v);
                return;
            }
            TreeRangeMap treeRangeMap = TreeRangeMap.this;
            v.getClass();
            put(treeRangeMap.coalescedRange(range, v).intersection(this.subRange), v);
        }

        /* JADX WARN: Multi-variable type inference failed */
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
        @Override // com.google.common.collect.RangeMap
        public void remove(Range<K> range) {
            if (range.isConnected(this.subRange)) {
                TreeRangeMap.this.remove(range.intersection(this.subRange));
            }
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
        @Override // com.google.common.collect.RangeMap
        public Range<K> span() {
            Cut cut;
            Map.Entry entryFloorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry((Cut<K>) this.subRange.lowerBound);
            if (entryFloorEntry == null || ((RangeMapEntry) entryFloorEntry.getValue()).range.upperBound.compareTo((Cut) this.subRange.lowerBound) <= 0) {
                cut = (Cut) TreeRangeMap.this.entriesByLowerBound.ceilingKey((Cut<K>) this.subRange.lowerBound);
                if (cut == null || cut.compareTo((Cut) this.subRange.upperBound) >= 0) {
                    throw new NoSuchElementException();
                }
            } else {
                cut = this.subRange.lowerBound;
            }
            Map.Entry entryLowerEntry = TreeRangeMap.this.entriesByLowerBound.lowerEntry((Cut<K>) this.subRange.upperBound);
            if (entryLowerEntry != null) {
                return new Range<>(cut, ((RangeMapEntry) entryLowerEntry.getValue()).range.upperBound.compareTo((Cut) this.subRange.upperBound) >= 0 ? this.subRange.upperBound : ((RangeMapEntry) entryLowerEntry.getValue()).range.upperBound);
            }
            throw new NoSuchElementException();
        }

        /* JADX WARN: Multi-variable type inference failed */
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
        @Override // com.google.common.collect.RangeMap
        public RangeMap<K, V> subRangeMap(Range<K> range) {
            if (range.isConnected(this.subRange)) {
                return TreeRangeMap.this.subRangeMap(range.intersection(this.subRange));
            }
            TreeRangeMap.this.getClass();
            return TreeRangeMap.EMPTY_SUB_RANGE_MAP;
        }

        @Override // com.google.common.collect.RangeMap
        public String toString() {
            return asMapOfRanges().toString();
        }
    }

    public TreeRangeMap() {
        this.entriesByLowerBound = new TreeMap();
    }

    public static RangeMap access$200(TreeRangeMap treeRangeMap) {
        treeRangeMap.getClass();
        return EMPTY_SUB_RANGE_MAP;
    }

    public static <K extends Comparable, V> Range<K> coalesce(Range<K> range, V v, Map.Entry<Cut<K>, RangeMapEntry<K, V>> entry) {
        return (entry != null && entry.getValue().range.isConnected(range) && entry.getValue().value.equals(v)) ? (Range<K>) range.span(entry.getValue().range) : range;
    }

    public static <K extends Comparable<?>, V> TreeRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
        if (rangeMap instanceof TreeRangeMap) {
            TreeMap treeMap = new TreeMap();
            treeMap.putAll(((TreeRangeMap) rangeMap).entriesByLowerBound);
            return new TreeRangeMap<>(treeMap);
        }
        TreeMap treeMap2 = new TreeMap();
        for (Map.Entry<Range<K>, ? extends V> entry : rangeMap.asMapOfRanges().entrySet()) {
            treeMap2.put(entry.getKey().lowerBound, new RangeMapEntry(entry.getKey(), entry.getValue()));
        }
        return new TreeRangeMap<>(treeMap2);
    }

    public static <K extends Comparable, V> TreeRangeMap<K, V> create() {
        return new TreeRangeMap<>();
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asDescendingMapOfRanges() {
        return new AsMapOfRanges(this.entriesByLowerBound.descendingMap().values());
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asMapOfRanges() {
        return new AsMapOfRanges(this.entriesByLowerBound.values());
    }

    @Override // com.google.common.collect.RangeMap
    public void clear() {
        this.entriesByLowerBound.clear();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final Range<K> coalescedRange(Range<K> range, V v) {
        return coalesce(coalesce(range, v, this.entriesByLowerBound.lowerEntry((Cut<K>) range.lowerBound)), v, this.entriesByLowerBound.floorEntry((Cut<K>) range.upperBound));
    }

    public final RangeMap<K, V> emptySubRangeMap() {
        return EMPTY_SUB_RANGE_MAP;
    }

    @Override // com.google.common.collect.RangeMap
    public boolean equals(Object o) {
        if (!(o instanceof RangeMap)) {
            return false;
        }
        return ((AbstractMap) asMapOfRanges()).equals(((RangeMap) o).asMapOfRanges());
    }

    @Override // com.google.common.collect.RangeMap
    public V get(K key) {
        Map.Entry<Range<K>, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    public Map.Entry<Range<K>, V> getEntry(K key) {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> entryFloorEntry = this.entriesByLowerBound.floorEntry(new Cut.BelowValue(key));
        if (entryFloorEntry == null || !entryFloorEntry.getValue().range.contains(key)) {
            return null;
        }
        return entryFloorEntry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    public int hashCode() {
        return ((AbstractMap) asMapOfRanges()).hashCode();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.common.collect.RangeMap
    public void put(Range<K> range, V v) {
        if (range.isEmpty()) {
            return;
        }
        v.getClass();
        remove(range);
        this.entriesByLowerBound.put((Cut<K>) range.lowerBound, new RangeMapEntry<>(range, v));
    }

    @Override // com.google.common.collect.RangeMap
    public void putAll(RangeMap<K, ? extends V> rangeMap) {
        for (Map.Entry<Range<K>, ? extends V> entry : rangeMap.asMapOfRanges().entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.common.collect.RangeMap
    public void putCoalescing(Range<K> range, V value) {
        if (this.entriesByLowerBound.isEmpty()) {
            put(range, value);
        } else {
            value.getClass();
            put(coalescedRange(range, value), value);
        }
    }

    public final void putRangeMapEntry(Cut<K> lowerBound, Cut<K> upperBound, V value) {
        this.entriesByLowerBound.put(lowerBound, new RangeMapEntry<>(lowerBound, upperBound, value));
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    @Override // com.google.common.collect.RangeMap
    public void remove(Range<K> range) {
        if (range.isEmpty()) {
            return;
        }
        Map.Entry entryLowerEntry = this.entriesByLowerBound.lowerEntry((Cut<K>) range.lowerBound);
        if (entryLowerEntry != null) {
            RangeMapEntry rangeMapEntry = (RangeMapEntry) entryLowerEntry.getValue();
            if (rangeMapEntry.range.upperBound.compareTo((Cut) range.lowerBound) > 0) {
                if (rangeMapEntry.range.upperBound.compareTo((Cut) range.upperBound) > 0) {
                    putRangeMapEntry(range.upperBound, rangeMapEntry.range.upperBound, ((RangeMapEntry) entryLowerEntry.getValue()).value);
                }
                putRangeMapEntry(rangeMapEntry.range.lowerBound, range.lowerBound, ((RangeMapEntry) entryLowerEntry.getValue()).value);
            }
        }
        Map.Entry entryLowerEntry2 = this.entriesByLowerBound.lowerEntry((Cut<K>) range.upperBound);
        if (entryLowerEntry2 != null) {
            RangeMapEntry rangeMapEntry2 = (RangeMapEntry) entryLowerEntry2.getValue();
            if (rangeMapEntry2.range.upperBound.compareTo((Cut) range.upperBound) > 0) {
                putRangeMapEntry(range.upperBound, rangeMapEntry2.range.upperBound, ((RangeMapEntry) entryLowerEntry2.getValue()).value);
            }
        }
        this.entriesByLowerBound.subMap((Cut<K>) range.lowerBound, (Cut<K>) range.upperBound).clear();
    }

    @Override // com.google.common.collect.RangeMap
    public Range<K> span() {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> entryFirstEntry = this.entriesByLowerBound.firstEntry();
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> entryLastEntry = this.entriesByLowerBound.lastEntry();
        if (entryFirstEntry == null || entryLastEntry == null) {
            throw new NoSuchElementException();
        }
        return new Range<>(entryFirstEntry.getValue().range.lowerBound, entryLastEntry.getValue().range.upperBound);
    }

    @Override // com.google.common.collect.RangeMap
    public RangeMap<K, V> subRangeMap(Range<K> subRange) {
        return subRange.equals(Range.all()) ? this : new SubRangeMap(subRange);
    }

    @Override // com.google.common.collect.RangeMap
    public String toString() {
        return this.entriesByLowerBound.values().toString();
    }

    public TreeRangeMap(NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound) {
        this.entriesByLowerBound = entriesByLowerBound;
    }

    /* JADX INFO: renamed from: com.google.common.collect.TreeRangeMap$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements RangeMap<Comparable<?>, Object> {
        @Override // com.google.common.collect.RangeMap
        public Map<Range<Comparable<?>>, Object> asDescendingMapOfRanges() {
            return Collections.EMPTY_MAP;
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<Comparable<?>>, Object> asMapOfRanges() {
            return Collections.EMPTY_MAP;
        }

        @Override // com.google.common.collect.RangeMap
        public Object get(Comparable<?> key) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        public Map.Entry<Range<Comparable<?>>, Object> getEntry(Comparable<?> key) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range<Comparable<?>> range, Object value) {
            range.getClass();
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap<Comparable<?>, ? extends Object> rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
            }
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range<Comparable<?>> range, Object value) {
            range.getClass();
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        @Override // com.google.common.collect.RangeMap
        public void remove(Range<Comparable<?>> range) {
            range.getClass();
        }

        @Override // com.google.common.collect.RangeMap
        public Range<Comparable<?>> span() {
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public RangeMap<Comparable<?>, Object> subRangeMap(Range<Comparable<?>> range) {
            range.getClass();
            return this;
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
        }
    }
}
