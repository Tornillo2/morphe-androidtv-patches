package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.StandardTable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import j$.util.Objects;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public class TreeBasedTable<R, C, V> extends StandardRowSortedTable<R, C, V> {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final Comparator<? super C> columnComparator;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Factory<C, V> implements Supplier<Map<C, V>>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Comparator<? super C> comparator;

        public Factory(Comparator<? super C> comparator) {
            this.comparator = comparator;
        }

        @Override // com.google.common.base.Supplier
        public Map<C, V> get() {
            return new TreeMap(this.comparator);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class TreeRow extends StandardTable<R, C, V>.Row implements SortedMap<C, V> {
        public final C lowerBound;
        public final C upperBound;
        public transient SortedMap<C, V> wholeRow;

        public TreeRow(final TreeBasedTable this$0, R rowKey) {
            this(rowKey, null, null);
        }

        @Override // java.util.SortedMap
        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        public int compare(Object a, Object b) {
            return comparator().compare(a, b);
        }

        @Override // com.google.common.collect.StandardTable.Row, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return rangeContains(key) && super.containsKey(key);
        }

        @Override // java.util.SortedMap
        public C firstKey() {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map != null) {
                return (C) ((SortedMap) map).firstKey();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> headMap(C toKey) {
            toKey.getClass();
            Preconditions.checkArgument(rangeContains(toKey));
            return new TreeRow(this.rowKey, this.lowerBound, toKey);
        }

        @Override // java.util.SortedMap
        public C lastKey() {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map != null) {
                return (C) ((SortedMap) map).lastKey();
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.StandardTable.Row
        public void maintainEmptyInvariant() {
            updateWholeRowField();
            SortedMap<C, V> sortedMap = this.wholeRow;
            if (sortedMap == null || !sortedMap.isEmpty()) {
                return;
            }
            TreeBasedTable.this.backingMap.remove(this.rowKey);
            this.wholeRow = null;
            this.backingRowMap = null;
        }

        @Override // com.google.common.collect.StandardTable.Row, java.util.AbstractMap, java.util.Map
        public V put(C c, V v) {
            c.getClass();
            Preconditions.checkArgument(rangeContains(c));
            return (V) super.put(c, v);
        }

        public boolean rangeContains(Object o) {
            if (o == null) {
                return false;
            }
            C c = this.lowerBound;
            if (c != null && compare(c, o) > 0) {
                return false;
            }
            C c2 = this.upperBound;
            return c2 == null || compare(c2, o) > 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        @Override // java.util.SortedMap
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.SortedMap<C, V> subMap(C r4, C r5) {
            /*
                r3 = this;
                r4.getClass()
                boolean r0 = r3.rangeContains(r4)
                if (r0 == 0) goto L14
                r5.getClass()
                boolean r0 = r3.rangeContains(r5)
                if (r0 == 0) goto L14
                r0 = 1
                goto L15
            L14:
                r0 = 0
            L15:
                com.google.common.base.Preconditions.checkArgument(r0)
                com.google.common.collect.TreeBasedTable$TreeRow r0 = new com.google.common.collect.TreeBasedTable$TreeRow
                com.google.common.collect.TreeBasedTable r1 = com.google.common.collect.TreeBasedTable.this
                R r2 = r3.rowKey
                r0.<init>(r2, r4, r5)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeBasedTable.TreeRow.subMap(java.lang.Object, java.lang.Object):java.util.SortedMap");
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> tailMap(C fromKey) {
            fromKey.getClass();
            Preconditions.checkArgument(rangeContains(fromKey));
            return new TreeRow(this.rowKey, fromKey, this.upperBound);
        }

        public void updateWholeRowField() {
            SortedMap<C, V> sortedMap = this.wholeRow;
            if (sortedMap == null || (sortedMap.isEmpty() && TreeBasedTable.this.backingMap.containsKey(this.rowKey))) {
                this.wholeRow = (SortedMap) TreeBasedTable.this.backingMap.get(this.rowKey);
            }
        }

        public TreeRow(R rowKey, C lowerBound, C upperBound) {
            super(rowKey);
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            Preconditions.checkArgument(lowerBound == null || upperBound == null || compare(lowerBound, upperBound) <= 0);
        }

        @Override // com.google.common.collect.StandardTable.Row
        public SortedMap<C, V> computeBackingRowMap() {
            updateWholeRowField();
            SortedMap<C, V> sortedMapTailMap = this.wholeRow;
            if (sortedMapTailMap == null) {
                return null;
            }
            C c = this.lowerBound;
            if (c != null) {
                sortedMapTailMap = sortedMapTailMap.tailMap(c);
            }
            C c2 = this.upperBound;
            return c2 != null ? sortedMapTailMap.headMap(c2) : sortedMapTailMap;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public SortedSet<C> keySet() {
            return new Maps.SortedKeySet((Map) this);
        }
    }

    public TreeBasedTable(Comparator<? super R> rowComparator, Comparator<? super C> columnComparator) {
        super((Map) new TreeMap(rowComparator), (Supplier) new Factory(columnComparator));
        this.columnComparator = columnComparator;
    }

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        NaturalOrdering naturalOrdering = NaturalOrdering.INSTANCE;
        return new TreeBasedTable<>(naturalOrdering, naturalOrdering);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Set cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public Map column(Object columnKey) {
        return new StandardTable.Column(columnKey);
    }

    @Deprecated
    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Set columnKeySet() {
        return super.columnKeySet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Map columnMap() {
        return super.columnMap();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean contains(Object rowKey, Object columnKey) {
        return super.contains(rowKey, columnKey);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsColumn(Object columnKey) {
        return super.containsColumn(columnKey);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsRow(Object rowKey) {
        return super.containsRow(rowKey);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override // com.google.common.collect.StandardTable
    public Iterator<C> createColumnKeyIterator() {
        final Comparator<? super C> comparatorColumnComparator = columnComparator();
        final UnmodifiableIterator unmodifiableIteratorMergeSorted = Iterators.mergeSorted(Iterables.transform(this.backingMap.values(), new TreeBasedTable$$ExternalSyntheticLambda0()), comparatorColumnComparator);
        return new AbstractIterator<C>(this) { // from class: com.google.common.collect.TreeBasedTable.1
            public C lastValue;
            public final /* synthetic */ TreeBasedTable this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.collect.AbstractIterator
            public C computeNext() {
                while (unmodifiableIteratorMergeSorted.hasNext()) {
                    C c = (C) unmodifiableIteratorMergeSorted.next();
                    C c2 = this.lastValue;
                    if (c2 == null || comparatorColumnComparator.compare(c, c2) != 0) {
                        this.lastValue = c;
                        return c;
                    }
                }
                this.lastValue = null;
                endOfData();
                return null;
            }
        };
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean equals(Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Object get(Object rowKey, Object columnKey) {
        return super.get(rowKey, columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object put(Object rowKey, Object columnKey, Object value) {
        return super.put(rowKey, columnKey, value);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ void putAll(Table table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object remove(Object rowKey, Object columnKey) {
        return super.remove(rowKey, columnKey);
    }

    @InlineMe(replacement = "requireNonNull(this.rowKeySet().comparator())", staticImports = {"java.util.Objects.requireNonNull"})
    @Deprecated
    public final Comparator<? super R> rowComparator() {
        Comparator<? super R> comparator = rowKeySet().comparator();
        Objects.requireNonNull(comparator);
        return comparator;
    }

    @Override // com.google.common.collect.StandardRowSortedTable, com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ SortedSet rowKeySet() {
        return super.rowKeySet();
    }

    @Override // com.google.common.collect.StandardRowSortedTable, com.google.common.collect.StandardTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ SortedMap rowMap() {
        return super.rowMap();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public SortedMap<C, V> row(R rowKey) {
        return new TreeRow(rowKey, null, null);
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> table) {
        Comparator comparator = table.rowKeySet().comparator();
        Objects.requireNonNull(comparator);
        TreeBasedTable<R, C, V> treeBasedTable = new TreeBasedTable<>(comparator, table.columnComparator());
        super.putAll(table);
        return treeBasedTable;
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> rowComparator, Comparator<? super C> columnComparator) {
        rowComparator.getClass();
        columnComparator.getClass();
        return new TreeBasedTable<>(rowComparator, columnComparator);
    }
}
