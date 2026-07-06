package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Cut;
import com.google.common.collect.Iterators;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public class TreeRangeSet<C extends Comparable<?>> extends AbstractRangeSet<C> implements Serializable {

    @LazyInit
    public transient Set<Range<C>> asDescendingSetOfRanges;

    @LazyInit
    public transient Set<Range<C>> asRanges;

    @LazyInit
    public transient RangeSet<C> complement;

    @VisibleForTesting
    public final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsRanges extends ForwardingCollection<Range<C>> implements Set<Range<C>> {
        public final Collection<Range<C>> delegate;

        public AsRanges(Collection<Range<C>> delegate) {
            this.delegate = delegate;
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.delegate;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object o) {
            return Sets.equalsImpl(this, o);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Range<C>> delegate() {
            return this.delegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class Complement extends TreeRangeSet<C> {
        public Complement() {
            super(new ComplementRangesByLowerBound(TreeRangeSet.this.rangesByLowerBound));
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void add(Range<C> rangeToAdd) {
            TreeRangeSet.this.remove(rangeToAdd);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.RangeSet
        public RangeSet<C> complement() {
            return TreeRangeSet.this;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public boolean contains(C value) {
            return !TreeRangeSet.this.contains(value);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void remove(Range<C> rangeToRemove) {
            TreeRangeSet.this.add(rangeToRemove);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ComplementRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        public final Range<Cut<C>> complementLowerBoundWindow;
        public final NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound;
        public final NavigableMap<Cut<C>, Range<C>> positiveRangesByUpperBound;

        public ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound) {
            this(positiveRangesByLowerBound, Range.all());
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return NaturalOrdering.INSTANCE;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return get(key) != null;
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            Cut<C> cutHigherKey;
            PeekingIterator peekingIterator = Iterators.peekingIterator(this.positiveRangesByUpperBound.headMap(this.complementLowerBoundWindow.hasUpperBound() ? (Cut) this.complementLowerBoundWindow.upperBound.endpoint() : Cut.AboveAll.INSTANCE, this.complementLowerBoundWindow.hasUpperBound() && this.complementLowerBoundWindow.upperBound.typeAsUpperBound() == BoundType.CLOSED).descendingMap().values().iterator());
            if (peekingIterator.hasNext()) {
                cutHigherKey = ((Range) peekingIterator.peek()).upperBound == Cut.AboveAll.INSTANCE ? ((Range) peekingIterator.next()).lowerBound : this.positiveRangesByLowerBound.higherKey(((Range) peekingIterator.peek()).upperBound);
            } else {
                Range<Cut<C>> range = this.complementLowerBoundWindow;
                Cut.BelowAll belowAll = Cut.BelowAll.INSTANCE;
                if (!range.contains(belowAll) || this.positiveRangesByLowerBound.containsKey(belowAll)) {
                    return Iterators.ArrayItr.EMPTY;
                }
                cutHigherKey = this.positiveRangesByLowerBound.higherKey(belowAll);
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(this, (Cut) MoreObjects.firstNonNull(cutHigherKey, Cut.AboveAll.INSTANCE), peekingIterator) { // from class: com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound.2
                public Cut<C> nextComplementRangeUpperBound;
                public final /* synthetic */ ComplementRangesByLowerBound this$0;
                public final /* synthetic */ Cut val$firstComplementRangeUpperBound;
                public final /* synthetic */ PeekingIterator val$positiveItr;

                {
                    this.val$firstComplementRangeUpperBound = val$firstComplementRangeUpperBound;
                    this.val$positiveItr = peekingIterator;
                    this.this$0 = this;
                    this.nextComplementRangeUpperBound = val$firstComplementRangeUpperBound;
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
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    Cut<C> cut = this.nextComplementRangeUpperBound;
                    Cut.BelowAll belowAll2 = Cut.BelowAll.INSTANCE;
                    if (cut == belowAll2) {
                        endOfData();
                        return null;
                    }
                    if (this.val$positiveItr.hasNext()) {
                        Range range2 = (Range) this.val$positiveItr.next();
                        Cut<C> cut2 = range2.upperBound;
                        Range range3 = new Range(cut2, this.nextComplementRangeUpperBound);
                        this.nextComplementRangeUpperBound = range2.lowerBound;
                        if (this.this$0.complementLowerBoundWindow.lowerBound.isLessThan(cut2)) {
                            return new ImmutableEntry(cut2, range3);
                        }
                    } else if (this.this$0.complementLowerBoundWindow.lowerBound.isLessThan(belowAll2)) {
                        Range range4 = new Range(belowAll2, this.nextComplementRangeUpperBound);
                        this.nextComplementRangeUpperBound = belowAll2;
                        return new ImmutableEntry(belowAll2, range4);
                    }
                    endOfData();
                    return null;
                }
            };
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            Collection<Range<C>> collectionValues;
            if (this.complementLowerBoundWindow.hasLowerBound()) {
                collectionValues = this.positiveRangesByUpperBound.tailMap((Cut) this.complementLowerBoundWindow.lowerBound.endpoint(), this.complementLowerBoundWindow.lowerBound.typeAsLowerBound() == BoundType.CLOSED).values();
            } else {
                collectionValues = this.positiveRangesByUpperBound.values();
            }
            PeekingIterator peekingIterator = Iterators.peekingIterator(collectionValues.iterator());
            Range<Cut<C>> range = this.complementLowerBoundWindow;
            Cut cut = Cut.BelowAll.INSTANCE;
            if (!range.contains(cut) || (peekingIterator.hasNext() && ((Range) peekingIterator.peek()).lowerBound == cut)) {
                if (!peekingIterator.hasNext()) {
                    return Iterators.ArrayItr.EMPTY;
                }
                cut = ((Range) peekingIterator.next()).upperBound;
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(this, cut, peekingIterator) { // from class: com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound.1
                public Cut<C> nextComplementRangeLowerBound;
                public final /* synthetic */ ComplementRangesByLowerBound this$0;
                public final /* synthetic */ Cut val$firstComplementRangeLowerBound;
                public final /* synthetic */ PeekingIterator val$positiveItr;

                {
                    this.val$firstComplementRangeLowerBound = cut;
                    this.val$positiveItr = peekingIterator;
                    this.this$0 = this;
                    this.nextComplementRangeLowerBound = cut;
                }

                @Override // com.google.common.collect.AbstractIterator
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    Range range2;
                    if (!this.this$0.complementLowerBoundWindow.upperBound.isLessThan(this.nextComplementRangeLowerBound)) {
                        Cut<C> cut2 = this.nextComplementRangeLowerBound;
                        Cut.AboveAll aboveAll = Cut.AboveAll.INSTANCE;
                        if (cut2 != aboveAll) {
                            if (this.val$positiveItr.hasNext()) {
                                Range range3 = (Range) this.val$positiveItr.next();
                                range2 = new Range(this.nextComplementRangeLowerBound, range3.lowerBound);
                                this.nextComplementRangeLowerBound = range3.upperBound;
                            } else {
                                range2 = new Range(this.nextComplementRangeLowerBound, aboveAll);
                                this.nextComplementRangeLowerBound = aboveAll;
                            }
                            return new ImmutableEntry(range2.lowerBound, range2);
                        }
                    }
                    endOfData();
                    return null;
                }
            };
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.size(entryIterator());
        }

        public ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound, Range<Cut<C>> window) {
            this.positiveRangesByLowerBound = positiveRangesByLowerBound;
            this.positiveRangesByUpperBound = new RangesByUpperBound(positiveRangesByLowerBound);
            this.complementLowerBoundWindow = window;
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public Range<C> get(Object key) {
            if (key instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) key;
                    Map.Entry<Cut<C>, Range<C>> entryFirstEntry = tailMap((Cut) cut, true).firstEntry();
                    if (entryFirstEntry != null && entryFirstEntry.getKey().equals(cut)) {
                        return entryFirstEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        public final NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> subWindow) {
            if (!this.complementLowerBoundWindow.isConnected(subWindow)) {
                return ImmutableSortedMap.of();
            }
            return new ComplementRangesByLowerBound(this.positiveRangesByLowerBound, subWindow.intersection(this.complementLowerBoundWindow));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class RangesByUpperBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        public final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        public final Range<Cut<C>> upperBoundWindow;

        public RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> rangesByLowerBound) {
            this.rangesByLowerBound = rangesByLowerBound;
            this.upperBoundWindow = Range.all();
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return NaturalOrdering.INSTANCE;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return get(key) != null;
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
        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            final PeekingIterator peekingIterator = Iterators.peekingIterator((this.upperBoundWindow.hasUpperBound() ? this.rangesByLowerBound.headMap((Cut) this.upperBoundWindow.upperBound.endpoint(), false).descendingMap().values() : this.rangesByLowerBound.descendingMap().values()).iterator());
            if (peekingIterator.hasNext() && this.upperBoundWindow.upperBound.isLessThan(((Range) peekingIterator.peek()).upperBound)) {
                peekingIterator.next();
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(this) { // from class: com.google.common.collect.TreeRangeSet.RangesByUpperBound.2
                public final /* synthetic */ RangesByUpperBound this$0;

                {
                    this.this$0 = this;
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
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!peekingIterator.hasNext()) {
                        endOfData();
                        return null;
                    }
                    Range range = (Range) peekingIterator.next();
                    if (this.this$0.upperBoundWindow.lowerBound.isLessThan(range.upperBound)) {
                        return new ImmutableEntry(range.upperBound, range);
                    }
                    endOfData();
                    return null;
                }
            };
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
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            Map.Entry<Cut<C>, Range<C>> entryLowerEntry;
            final Iterator<Range<C>> it = (this.upperBoundWindow.hasLowerBound() && (entryLowerEntry = this.rangesByLowerBound.lowerEntry((Cut) this.upperBoundWindow.lowerBound.endpoint())) != null) ? this.upperBoundWindow.lowerBound.isLessThan(entryLowerEntry.getValue().upperBound) ? this.rangesByLowerBound.tailMap(entryLowerEntry.getKey(), true).values().iterator() : this.rangesByLowerBound.tailMap((Cut) this.upperBoundWindow.lowerBound.endpoint(), true).values().iterator() : this.rangesByLowerBound.values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(this) { // from class: com.google.common.collect.TreeRangeSet.RangesByUpperBound.1
                public final /* synthetic */ RangesByUpperBound this$0;

                {
                    this.this$0 = this;
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
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!it.hasNext()) {
                        endOfData();
                        return null;
                    }
                    Range range = (Range) it.next();
                    if (!this.this$0.upperBoundWindow.upperBound.isLessThan(range.upperBound)) {
                        return new ImmutableEntry(range.upperBound, range);
                    }
                    endOfData();
                    return null;
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.isEmpty() : !((AbstractIterator) entryIterator()).hasNext();
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.size() : Iterators.size(entryIterator());
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> window) {
            return window.isConnected(this.upperBoundWindow) ? new RangesByUpperBound(this.rangesByLowerBound, window.intersection(this.upperBoundWindow)) : ImmutableSortedMap.of();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public Range<C> get(Object key) {
            Map.Entry<Cut<C>, Range<C>> entryLowerEntry;
            if (key instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) key;
                    if (this.upperBoundWindow.contains(cut) && (entryLowerEntry = this.rangesByLowerBound.lowerEntry(cut)) != null && entryLowerEntry.getValue().upperBound.equals(cut)) {
                        return entryLowerEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        public RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> rangesByLowerBound, Range<Cut<C>> upperBoundWindow) {
            this.rangesByLowerBound = rangesByLowerBound;
            this.upperBoundWindow = upperBoundWindow;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SubRangeSet extends TreeRangeSet<C> {
        public final Range<C> restriction;

        public SubRangeSet(Range<C> restriction) {
            super(new SubRangeSetRangesByLowerBound(Range.all(), restriction, TreeRangeSet.this.rangesByLowerBound));
            this.restriction = restriction;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void add(Range<C> rangeToAdd) {
            Preconditions.checkArgument(this.restriction.encloses(rangeToAdd), "Cannot add range %s to subRangeSet(%s)", rangeToAdd, this.restriction);
            TreeRangeSet.this.add(rangeToAdd);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public boolean contains(C value) {
            return this.restriction.contains(value) && TreeRangeSet.this.contains(value);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public boolean encloses(Range<C> range) {
            Range<C> rangeRangeEnclosing;
            return (this.restriction.isEmpty() || !this.restriction.encloses(range) || (rangeRangeEnclosing = TreeRangeSet.this.rangeEnclosing(range)) == null || rangeRangeEnclosing.intersection(this.restriction).isEmpty()) ? false : true;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public Range<C> rangeContaining(C value) {
            Range<C> rangeRangeContaining;
            if (this.restriction.contains(value) && (rangeRangeContaining = TreeRangeSet.this.rangeContaining(value)) != null) {
                return rangeRangeContaining.intersection(this.restriction);
            }
            return null;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void remove(Range<C> rangeToRemove) {
            if (rangeToRemove.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(rangeToRemove.intersection(this.restriction));
            }
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.RangeSet
        public RangeSet<C> subRangeSet(Range<C> view) {
            return view.encloses(this.restriction) ? this : view.isConnected(this.restriction) ? new SubRangeSet(this.restriction.intersection(view)) : ImmutableRangeSet.of();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SubRangeSetRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        public final Range<Cut<C>> lowerBoundWindow;
        public final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        public final NavigableMap<Cut<C>, Range<C>> rangesByUpperBound;
        public final Range<C> restriction;

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return NaturalOrdering.INSTANCE;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return get(key) != null;
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            if (this.restriction.isEmpty()) {
                return Iterators.ArrayItr.EMPTY;
            }
            Cut cut = (Cut) NaturalOrdering.INSTANCE.min(this.lowerBoundWindow.upperBound, new Cut.BelowValue(this.restriction.upperBound));
            final Iterator<Range<C>> it = this.rangesByLowerBound.headMap((Cut) cut.endpoint(), cut.typeAsUpperBound() == BoundType.CLOSED).descendingMap().values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(this) { // from class: com.google.common.collect.TreeRangeSet.SubRangeSetRangesByLowerBound.2
                public final /* synthetic */ SubRangeSetRangesByLowerBound this$0;

                {
                    this.this$0 = this;
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
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!it.hasNext()) {
                        endOfData();
                        return null;
                    }
                    Range range = (Range) it.next();
                    if (this.this$0.restriction.lowerBound.compareTo((Cut) range.upperBound) >= 0) {
                        endOfData();
                        return null;
                    }
                    Range rangeIntersection = range.intersection(this.this$0.restriction);
                    if (this.this$0.lowerBoundWindow.contains(rangeIntersection.lowerBound)) {
                        return new ImmutableEntry(rangeIntersection.lowerBound, rangeIntersection);
                    }
                    endOfData();
                    return null;
                }
            };
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
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            final Iterator<Range<C>> it;
            if (this.restriction.isEmpty()) {
                return Iterators.ArrayItr.EMPTY;
            }
            if (this.lowerBoundWindow.upperBound.isLessThan(this.restriction.lowerBound)) {
                return Iterators.ArrayItr.EMPTY;
            }
            if (this.lowerBoundWindow.lowerBound.isLessThan(this.restriction.lowerBound)) {
                it = this.rangesByUpperBound.tailMap(this.restriction.lowerBound, false).values().iterator();
            } else {
                it = this.rangesByLowerBound.tailMap((Cut) this.lowerBoundWindow.lowerBound.endpoint(), this.lowerBoundWindow.lowerBound.typeAsLowerBound() == BoundType.CLOSED).values().iterator();
            }
            final Cut cut = (Cut) NaturalOrdering.INSTANCE.min(this.lowerBoundWindow.upperBound, new Cut.BelowValue(this.restriction.upperBound));
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(this) { // from class: com.google.common.collect.TreeRangeSet.SubRangeSetRangesByLowerBound.1
                public final /* synthetic */ SubRangeSetRangesByLowerBound this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.AbstractIterator
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!it.hasNext()) {
                        endOfData();
                        return null;
                    }
                    Range range = (Range) it.next();
                    if (cut.isLessThan(range.lowerBound)) {
                        endOfData();
                        return null;
                    }
                    Range rangeIntersection = range.intersection(this.this$0.restriction);
                    return new ImmutableEntry(rangeIntersection.lowerBound, rangeIntersection);
                }
            };
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.size(entryIterator());
        }

        public SubRangeSetRangesByLowerBound(Range<Cut<C>> lowerBoundWindow, Range<C> restriction, NavigableMap<Cut<C>, Range<C>> rangesByLowerBound) {
            lowerBoundWindow.getClass();
            this.lowerBoundWindow = lowerBoundWindow;
            restriction.getClass();
            this.restriction = restriction;
            rangesByLowerBound.getClass();
            this.rangesByLowerBound = rangesByLowerBound;
            this.rangesByUpperBound = new RangesByUpperBound(rangesByLowerBound);
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> window) {
            return !window.isConnected(this.lowerBoundWindow) ? ImmutableSortedMap.of() : new SubRangeSetRangesByLowerBound(this.lowerBoundWindow.intersection(window), this.restriction, this.rangesByLowerBound);
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
        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public Range<C> get(Object key) {
            if (key instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) key;
                    if (this.lowerBoundWindow.contains(cut) && cut.compareTo((Cut) this.restriction.lowerBound) >= 0 && cut.compareTo((Cut) this.restriction.upperBound) < 0) {
                        if (cut.equals(this.restriction.lowerBound)) {
                            Range range = (Range) Maps.valueOrNull(this.rangesByLowerBound.floorEntry(cut));
                            if (range != null && range.upperBound.compareTo((Cut) this.restriction.lowerBound) > 0) {
                                return range.intersection(this.restriction);
                            }
                        } else {
                            Range<C> range2 = this.rangesByLowerBound.get(cut);
                            if (range2 != null) {
                                return range2.intersection(this.restriction);
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }
    }

    public TreeRangeSet(NavigableMap<Cut<C>, Range<C>> rangesByLowerCut) {
        this.rangesByLowerBound = rangesByLowerCut;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<>(new TreeMap());
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public void add(Range<C> range) {
        range.getClass();
        if (range.isEmpty()) {
            return;
        }
        Cut<C> cut = range.lowerBound;
        Cut<C> cut2 = range.upperBound;
        Map.Entry<Cut<C>, Range<C>> entryLowerEntry = this.rangesByLowerBound.lowerEntry(cut);
        if (entryLowerEntry != null) {
            Range<C> value = entryLowerEntry.getValue();
            if (value.upperBound.compareTo((Cut) cut) >= 0) {
                if (value.upperBound.compareTo((Cut) cut2) >= 0) {
                    cut2 = value.upperBound;
                }
                cut = value.lowerBound;
            }
        }
        Map.Entry<Cut<C>, Range<C>> entryFloorEntry = this.rangesByLowerBound.floorEntry(cut2);
        if (entryFloorEntry != null) {
            Range<C> value2 = entryFloorEntry.getValue();
            if (value2.upperBound.compareTo((Cut) cut2) >= 0) {
                cut2 = value2.upperBound;
            }
        }
        this.rangesByLowerBound.subMap(cut, cut2).clear();
        replaceRangeWithSameLowerBound(new Range<>(cut, cut2));
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void addAll(RangeSet other) {
        super.addAll(other);
    }

    @Override // com.google.common.collect.RangeSet
    public Set<Range<C>> asDescendingSetOfRanges() {
        Set<Range<C>> set = this.asDescendingSetOfRanges;
        if (set != null) {
            return set;
        }
        AsRanges asRanges = new AsRanges(this.rangesByLowerBound.descendingMap().values());
        this.asDescendingSetOfRanges = asRanges;
        return asRanges;
    }

    @Override // com.google.common.collect.RangeSet
    public Set<Range<C>> asRanges() {
        Set<Range<C>> set = this.asRanges;
        if (set != null) {
            return set;
        }
        AsRanges asRanges = new AsRanges(this.rangesByLowerBound.values());
        this.asRanges = asRanges;
        return asRanges;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.RangeSet
    public RangeSet<C> complement() {
        RangeSet<C> rangeSet = this.complement;
        if (rangeSet != null) {
            return rangeSet;
        }
        Complement complement = new Complement();
        this.complement = complement;
        return complement;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean contains(Comparable value) {
        return super.contains(value);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean encloses(Range<C> range) {
        range.getClass();
        Map.Entry<Cut<C>, Range<C>> entryFloorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        return entryFloorEntry != null && entryFloorEntry.getValue().encloses(range);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet other) {
        return super.enclosesAll(other);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean intersects(Range<C> range) {
        range.getClass();
        Map.Entry<Cut<C>, Range<C>> entryCeilingEntry = this.rangesByLowerBound.ceilingEntry(range.lowerBound);
        if (entryCeilingEntry != null && entryCeilingEntry.getValue().isConnected(range) && !entryCeilingEntry.getValue().intersection(range).isEmpty()) {
            return true;
        }
        Map.Entry<Cut<C>, Range<C>> entryLowerEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
        return (entryLowerEntry == null || !entryLowerEntry.getValue().isConnected(range) || entryLowerEntry.getValue().intersection(range).isEmpty()) ? false : true;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public Range<C> rangeContaining(C value) {
        value.getClass();
        Map.Entry<Cut<C>, Range<C>> entryFloorEntry = this.rangesByLowerBound.floorEntry(new Cut.BelowValue(value));
        if (entryFloorEntry == null || !entryFloorEntry.getValue().contains(value)) {
            return null;
        }
        return entryFloorEntry.getValue();
    }

    public final Range<C> rangeEnclosing(Range<C> range) {
        range.getClass();
        Map.Entry<Cut<C>, Range<C>> entryFloorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        if (entryFloorEntry == null || !entryFloorEntry.getValue().encloses(range)) {
            return null;
        }
        return entryFloorEntry.getValue();
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
    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public void remove(Range<C> rangeToRemove) {
        rangeToRemove.getClass();
        if (rangeToRemove.isEmpty()) {
            return;
        }
        Map.Entry<Cut<C>, Range<C>> entryLowerEntry = this.rangesByLowerBound.lowerEntry(rangeToRemove.lowerBound);
        if (entryLowerEntry != null) {
            Range<C> value = entryLowerEntry.getValue();
            if (value.upperBound.compareTo((Cut) rangeToRemove.lowerBound) >= 0) {
                if (rangeToRemove.hasUpperBound() && value.upperBound.compareTo((Cut) rangeToRemove.upperBound) >= 0) {
                    replaceRangeWithSameLowerBound(new Range<>(rangeToRemove.upperBound, value.upperBound));
                }
                replaceRangeWithSameLowerBound(new Range<>(value.lowerBound, rangeToRemove.lowerBound));
            }
        }
        Map.Entry<Cut<C>, Range<C>> entryFloorEntry = this.rangesByLowerBound.floorEntry(rangeToRemove.upperBound);
        if (entryFloorEntry != null) {
            Range<C> value2 = entryFloorEntry.getValue();
            if (rangeToRemove.hasUpperBound() && value2.upperBound.compareTo((Cut) rangeToRemove.upperBound) >= 0) {
                replaceRangeWithSameLowerBound(new Range<>(rangeToRemove.upperBound, value2.upperBound));
            }
        }
        this.rangesByLowerBound.subMap(rangeToRemove.lowerBound, rangeToRemove.upperBound).clear();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void removeAll(RangeSet other) {
        super.removeAll(other);
    }

    public final void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.rangesByLowerBound.remove(range.lowerBound);
        } else {
            this.rangesByLowerBound.put(range.lowerBound, range);
        }
    }

    @Override // com.google.common.collect.RangeSet
    public Range<C> span() {
        Map.Entry<Cut<C>, Range<C>> entryFirstEntry = this.rangesByLowerBound.firstEntry();
        Map.Entry<Cut<C>, Range<C>> entryLastEntry = this.rangesByLowerBound.lastEntry();
        if (entryFirstEntry == null || entryLastEntry == null) {
            throw new NoSuchElementException();
        }
        return new Range<>(entryFirstEntry.getValue().lowerBound, entryLastEntry.getValue().upperBound);
    }

    @Override // com.google.common.collect.RangeSet
    public RangeSet<C> subRangeSet(Range<C> view) {
        return view.equals(Range.all()) ? this : new SubRangeSet(view);
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(RangeSet<C> rangeSet) {
        TreeRangeSet<C> treeRangeSetCreate = create();
        super.addAll(rangeSet);
        return treeRangeSetCreate;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void addAll(Iterable ranges) {
        super.addAll(ranges);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(Iterable ranges) {
        return super.enclosesAll(ranges);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void removeAll(Iterable ranges) {
        super.removeAll(ranges);
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(Iterable<Range<C>> ranges) {
        TreeRangeSet<C> treeRangeSetCreate = create();
        super.addAll(ranges);
        return treeRangeSetCreate;
    }
}
