package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class RegularImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class CellSet extends IndexedImmutableSet<Table.Cell<R, C, V>> {
        public CellSet() {
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            if (object instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell) object;
                Object obj = RegularImmutableTable.this.get(cell.getRowKey(), cell.getColumnKey());
                if (obj != null && obj.equals(cell.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public Table.Cell<R, C, V> get(int index) {
            return RegularImmutableTable.this.getCell(index);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return RegularImmutableTable.this.size();
        }

        @Override // com.google.common.collect.IndexedImmutableSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public Object get(int index) {
            return RegularImmutableTable.this.getCell(index);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class Values extends ImmutableList<V> {
        public Values() {
        }

        @Override // java.util.List
        public V get(int i) {
            return (V) RegularImmutableTable.this.getValue(i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return RegularImmutableTable.this.size();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$-mpm-2JojsBx19cKaywjIM1500A, reason: not valid java name */
    public static /* synthetic */ int m523$r8$lambda$mpm2JojsBx19cKaywjIM1500A(Comparator comparator, Comparator comparator2, Table.Cell cell, Table.Cell cell2) {
        int iCompare = comparator == null ? 0 : comparator.compare(cell.getRowKey(), cell2.getRowKey());
        if (iCompare != 0) {
            return iCompare;
        }
        if (comparator2 == null) {
            return 0;
        }
        return comparator2.compare(cell.getColumnKey(), cell2.getColumnKey());
    }

    public static <R, C, V> RegularImmutableTable<R, C, V> forCells(Iterable<Table.Cell<R, C, V>> cells) {
        return forCellsInternal(cells, null, null);
    }

    public static <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<Table.Cell<R, C, V>> cells, Comparator<? super R> rowComparator, Comparator<? super C> columnComparator) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ImmutableList immutableListCopyOf = ImmutableList.copyOf(cells);
        for (Table.Cell<R, C, V> cell : cells) {
            linkedHashSet.add(cell.getRowKey());
            linkedHashSet2.add(cell.getColumnKey());
        }
        return forOrderedComponents(immutableListCopyOf, rowComparator == null ? ImmutableSet.copyOf((Collection) linkedHashSet) : ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(rowComparator, linkedHashSet)), columnComparator == null ? ImmutableSet.copyOf((Collection) linkedHashSet2) : ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(columnComparator, linkedHashSet2)));
    }

    public static <R, C, V> RegularImmutableTable<R, C, V> forOrderedComponents(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        return ((long) cellList.size()) > (((long) rowSpace.size()) * ((long) columnSpace.size())) / 2 ? new DenseImmutableTable(cellList, rowSpace, columnSpace) : new SparseImmutableTable(cellList, rowSpace, columnSpace);
    }

    public final void checkNoDuplicate(R rowKey, C columnKey, V existingValue, V newValue) {
        Preconditions.checkArgument(existingValue == null, "Duplicate key: (row=%s, column=%s), values: [%s, %s].", rowKey, columnKey, newValue, existingValue);
    }

    public abstract Table.Cell<R, C, V> getCell(int iterationIndex);

    public abstract V getValue(int iterationIndex);

    @Override // com.google.common.collect.ImmutableTable
    @J2ktIncompatible
    @GwtIncompatible
    public abstract Object writeReplace();

    public static <R, C, V> RegularImmutableTable<R, C, V> forCells(List<Table.Cell<R, C, V>> cells, final Comparator<? super R> rowComparator, final Comparator<? super C> columnComparator) {
        cells.getClass();
        if (rowComparator != null || columnComparator != null) {
            Collections.sort(cells, new Comparator() { // from class: com.google.common.collect.RegularImmutableTable$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return RegularImmutableTable.m523$r8$lambda$mpm2JojsBx19cKaywjIM1500A(rowComparator, columnComparator, (Table.Cell) obj, (Table.Cell) obj2);
                }
            });
        }
        return forCellsInternal(cells, rowComparator, columnComparator);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    public final ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return isEmpty() ? ImmutableSet.of() : new CellSet();
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    public final ImmutableCollection<V> createValues() {
        return isEmpty() ? ImmutableList.of() : new Values();
    }
}
